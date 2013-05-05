#!/soft/perl5.8.7-bin/perl -w
use CGI;
use Cwd;
use DBI;
use DBD::mysql;
$cgi = new CGI();
$databaseHandle = DBI->connect("DBI:mysql:c5131s09u32:db.itlabs.umn.edu:3311","c5131s09u32", "saurabh", {RaiseError=>1});

# FOR DEBUGGING:
#use strict;
use CGI::Carp qw(fatalsToBrowser);
BEGIN {
open (STDERR, '>>show.log');
}
# END FOR DEBUGGING

$newfolder = 1;
$folder = getcwd . "/MyPhotos";
$owner = "Owner";
$editor = "Editor";
$role = get_role();
$user = "\'" . getUserName() . "\'";
$count = 0;
$query_photos = "SELECT id FROM PhotoFileList";

print $cgi->header();
$newfolder = check_dir();

# Check to see if we need to show a particular photo or the first photo
my $img = 0;
my $filteruser;
my $filtercat;
$img = $cgi->param("i");
$filteruser = $cgi->param("u");
$filtercat = $cgi->param("c");
@listUsers = listUsers();
@listCategories = listCategories();

if ($newfolder == 0)
{
	if (present($filteruser, @listUsers) == 0)
	{
		$filteruser = "all";
	}
	if (present($filtercat, @listCategories) == 0)
	{
		$filtercat = "all";
	}
	
	# Generate query
	if ($filteruser eq "all" && $filtercat ne "all")
	{
		$query_photos = $query_photos . " where category = '" . $filtercat . "'";
	}
	elsif ($filteruser ne "all" && $filtercat eq "all")
	{
		$query_photos = $query_photos . " where loginname = '" . $filteruser . "'";
	}
	elsif ($filteruser ne "all" && $filtercat ne "all")
	{
		$query_photos = $query_photos . " where loginname = '" . $filteruser . "' and category = '" . $filtercat . "'";
	}
	$query_photos = $query_photos . ";";
	
	@photowhitelist = getPhotoList();
	@photowhitelist = sort {length $a <=> length $b || $a cmp $b} @photowhitelist;
	
	if ($img eq "")
	{
		(my $pr, my $id) = split(/_/, $photowhitelist[0]);
		$img = $id;
	}
	elsif (!($img =~ /\d+/ && $img >= 0 && $img < $count))
	{
		$img = 0;
	}	
	
	print_header();
	slideshow();
}

# Disconnect from the database before exiting
$databaseHandle->disconnect();

# This is sub-routine will either create the folder or 
sub check_dir
{
	my $returnvalue;
	
	# Check to see if the folder exists, else create it
	if  (! -d $folder)
	{
		system("mkdir $folder");
		system("chmod 755 $folder");
		system("mkdir $folder/info");
		system("chmod 755 $folder/info");		
		
		print_header();
		$returnvalue = print_nophotos();
	}
	else # Go through the folder to see how many photos are there
	{
		# Try opening the directory and reading the files
		opendir(BIN, $folder ) or die "Could not open directory " . $folder;
		while (defined ($file = readdir BIN))
		{
			next if $file =~ /^\.\.?$/;		#skip . and ..
			next if $file =~ /info$/;			#skip folder "info"
			push (@files, $file);
		}
		closedir(BIN);	

		# We would like to sort filenames on length and then alphabetically
		@files = sort {length $a <=> length $b || $a cmp $b} @files;	
		$count = $#files + 1;
		
		if ($count eq 0)
		{
			print_header();
			$returnvalue = print_nophotos();
		}
		else
		{
			$returnvalue = 0;
		}
	}
	
	return $returnvalue;
}	


sub print_nophotos
{
	print qq(
	<body>
	<div align="center">There are no photos in your folder.
	);
	
	if ($role eq $owner || $role eq $editor)
	{
		print qq(
		<br><br>
		<div id="userUpload">&nbsp;</div>
		<script language="JavaScript" type="text/javascript">addUploadLink();</script>
		);
	}
	
	if ($role eq $owner)
	{
		print qq(
		<br>
		<a href="owner.cgi">Modify users</a>
		);
	}
	
	print qq(
	</div>
	</body>
	</html>
	);
	
	return 1;
}

sub slideshow
{
	print	("<body onload='showImage($img)'>");
	print qq(
		<table width="950" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td colspan="2"><form id="form1" name="form1" method="post" action="owner.cgi">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
			    <td width="30%"><input name="btnStart" onclick="first();" type="button" id="btnStart" value="Start" disabled="disabled" />
			      <input name="btnNext" onclick="next();" type="button" id="btnNext" value="Next" />
			      <input name="btnPrevious" onclick="previous();" type="button" id="btnPrevious" value="Previous" /></td>
			    <td><div align="center">);

	if ($role eq $owner)
	{
		#	print qq(	<input name="btnUsers" type="submit" id="btnUsers" value="Modify Users" />);
	    print $cgi->hidden('control', "OwnerPage");
	    print $cgi->submit(-name=>"Modify Users");
	}
				
	print qq(</div></td>
			    <td width="35%"><div align="right">
				<input name="btnStartShow" onclick="startShow();" type="button" id="btnStartShow" value="Start Slide Show" />
				<input name="btnStopShow" onclick="stopShow();"  type="button" id="btnStopShow" value="Stop Slide Show" disabled="disabled"/>
			      </div></td>
			  </tr>
			</table>
		      </form>
		      <hr /></td>
		  </tr>
		  <tr>
		    <td colspan="2" id="thumbs">
		    <script language="JavaScript" type="text/javascript">loadThumbs();</script>
		      <hr /><br />
		    </td>
		  </tr>
		  <tr>
		    <td width="55%" valign="top"><img id="imgSlide" src="" width="500" /></td>
		    <td valign="top"><table class="info" width="100%" border="0" cellspacing="10" cellpadding="0">
		  <tr>
		  <td>
		  	 <b>Show Photos For:</b>
		  </td>
		  </tr>
		  <tr>
		  <td>
			 <form id="frmFilter" name="frmFilter" method="post" action="show.cgi">
			 	User <select name="u" id="selUser"></select>&nbsp;
				Category <select name="c" id="selCat"></select>&nbsp;
				<input type="submit" value="Go!"/><hr />
			 </form>
		  	 <script language="JavaScript" type="text/javascript">addUsers();</script>
	       <script language="JavaScript" type="text/javascript">addCats();</script>			 
		  </td>
		  </tr>
		  <tr>
			<td id="imgFile">&nbsp;</td>
			</tr>
		      <tr>
			<td id="imgTitle">&nbsp;</td>
			</tr>
		      <tr>
			<td id="imgCat">&nbsp;</td>
			</tr>			
		      <tr>
			<td id="imgDesc">&nbsp;</td>
			</tr>
			);

	if ($role eq $owner || $role eq $editor)
	{
		print qq(
		<tr><td><br></td></tr>		
		<tr><td id="userEdit">&nbsp;</td></tr>
		<tr><td id="userDelete">&nbsp;</td></tr>
		<tr><td><hr></td></tr>
		<tr><td id="userUpload">&nbsp;</td></tr>			
		<script language="JavaScript" type="text/javascript">addPhotoLinks();</script>
		);
	}
	
	print qq(
		    </table>	</td>
		  </tr>
		</table>
		</body>
		</html>
	);
}


sub print_header
{
	print qq(
	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<meta http-equiv="Pragma" content="no-cache"> 	
	<title>CSci 5131: Assignment 4</title>
	<link href="includes/a3.css" rel="stylesheet" type="text/css" />
	<script src="includes/ajax.js" language="JavaScript" type="text/javascript"></script>
	);
	
	if ($newfolder == 0)
	{
		populateFilters();
		js_slideshow();
	}

	print qq(
		<script language="JavaScript" type="text/javascript">
		<!--
		var userLoggedIn = $user;
		-->
		</script>
	);

	print qq(
	</head>
	);
}


sub populateFilters
{
	print qq(
	<script language="JavaScript" type="text/javascript">
	<!--
	
	function addUsers()
	{
		var select = document.getElementById("selUser");
		select.options[select.options.length] = new Option('All', 'All');
	);
	
	foreach(@listUsers)
	{
		print "select.options[select.options.length] = new Option('" . $_ . "','" . $_ . "');\n";
		if ($_ eq $filteruser)
		{
			print "var lastelem = select.options.length - 1\n";
			print "select.options[lastelem].selected = true;\n";
		}
	}	
		
	print qq(
	}
	
	function addCats()
	{
		var select = document.getElementById("selCat");
		select.options[select.options.length] = new Option('All', 'All');
	);
	
	foreach(@listCategories)
	{
		print "select.options[select.options.length] = new Option('" . $_ . "','" . $_ . "');\n";
		if ($_ eq $filtercat)
		{
			print "var lastelem = select.options.length - 1\n";
			print "select.options[lastelem].selected = true;\n";
		}
	}	
		
	print qq(
	}
	-->
	</script>
	);	
}


sub js_slideshow
{
	my $seluser = "'" . $filteruser . "'";
	my $selcat = "'" . $filtercat . "'";
	my $blacklistphotos = "'";	

	print qq(
		<script language="JavaScript" type="text/javascript">
		<!--
		var firstIndex = null;
		var lastIndex = $count - 1;
		var currentIndex = null;
		var filePrefix = "img_";
		var selectedUser = $seluser;
		var selectedCat = $selcat;
		var blackList;
		var skipPhotos;
		var countAfterFilter;

		function loadThumbs()
		{
	);
		
	print (	
			"document.getElementById(\"thumbs\").innerHTML = \"");

	for($i=0; $i<$count; $i++)
	{
		($file, $extension) = split(/\./,$files[$i]);

		if (present($file, @photowhitelist) == 1)
		{			
			($prefix, $index) = split(/_/,$file);
			$filename = $file . "." . $extension;
			$random = int(rand(100000000)) + 100000;
			$filepath = "MyPhotos/" . $filename . "?r=" . $random; 
			print "<img class='noborder' width='50' height='50' src='" . $filepath . "' alt='" . $filename . "' onclick='showImage(" . $index . ")' id='" . $file . "' />";
		}
		else
		{
			$blacklistphotos = $blacklistphotos . $file . ",";
		}
	}			

	$blacklistphotos = $blacklistphotos . "'";

	print qq(\";
		blackList = $blacklistphotos;
		skipPhotos = delimString(blackList);
		if (skipPhotos[0] != "")
			countAfterFilter = $count - skipPhotos.length;
		else 
			countAfterFilter = $count;
		(new Image()).src = "includes/loading.gif";						
		}		
	);
			
		
	print qq(
	-->
	</script>
	);

}


sub get_role
{
    my $roleVar;
    if (defined $cgi->cookie('LOGIN_COOKIE')){
        my $cookie_l = $cgi->cookie('LOGIN_COOKIE');
        $roleVar = validate_cookie($cookie_l);
        if ($roleVar ne ""){
            return $roleVar;
        }else {
            my $location = 'login.cgi';
            print $cgi->redirect(-status=>302,-location=>$location);
         }
    }else {
        print "<P>reaching here<P>";
        my $location = 'login.cgi';
        print $cgi->redirect(-status=>302,-location=>$location);
    }
}

sub validate_cookie(){

    my $role = "";
    my ($cookieValue) = @_;
    my $result;
    my $query = "select role from SESSION_TABLE where sessionID='$cookieValue'";
    my $statementHandle = $databaseHandle->prepare($query);
    $statementHandle->execute();

    while(@result = $statementHandle->fetchrow_array()){
            $role = $result[0];
    }
    #$databaseHandle->disconnect();
    $statementHandle->finish();

    return $role;
}

sub getUserName(){

    my $cookieValue = $cgi->cookie('LOGIN_COOKIE');
    @cookieDetails = split(/#/, $cookieValue);
    return $cookieDetails[0]; 
}

sub listUsers(){

    my $query = "select DISTINCT loginname from PhotoFileList";
    my $statementHandle = $databaseHandle->prepare($query);
    $statementHandle->execute();
    my @users;

    while (@result = $statementHandle->fetchrow_array()){
		push(@users, $result[0]); 
    }
    #$databaseHandle->disconnect();
    $statementHandle->finish();

    return @users;
}

sub listCategories(){

    my $query = "select DISTINCT category from PhotoFileList";
    my $statementHandle = $databaseHandle->prepare($query);
    $statementHandle->execute();
    my @cats;

    while (@result = $statementHandle->fetchrow_array()){
		push(@cats, $result[0]); 
    }
    #$databaseHandle->disconnect();
    $statementHandle->finish();

    return @cats;
}

sub delete_cookie(){

    my $cookieVal = $cgi->cookie('LOGIN_COOKIE');
    my $query = "delete from SESSION_TABLE where sessionID = '$cookieVal'";
    $statementHandle = $databaseHandle->prepare($query);
    $statementHandle->execute();
    #$databaseHandle->disconnect();
    $statementHandle->finish();
}


sub getPhotoList(){
   my $statementHandle = $databaseHandle->prepare($query_photos);
   $statementHandle->execute();
   my @showphotos;

   while (@result = $statementHandle->fetchrow_array()){
		push(@showphotos, $result[0]); 
   }
   $statementHandle->finish();
   
   return @showphotos;
}


sub present{

    my $i = 0;
    my ($x, @elements) = @_;

    while( $i <= $#elements && ($elements[$i] ne $x)  ){

        ++$i;
    }
    if ($i <= $#elements){
        return 1;
    }else{
        return 0;
    }
}