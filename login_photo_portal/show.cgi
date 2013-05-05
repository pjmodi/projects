#!/soft/perl5.8.7-bin/perl -w
use CGI;
use Cwd;
$cgi = new CGI();

# FOR DEBUGGING:
#use strict;
#use CGI::Carp qw(fatalsToBrowser);
BEGIN {
open (STDERR, '>>show.log');
}
# END FOR DEBUGGING

$newfolder = 1;
$folder = getcwd . "/MyPhotos";
$owner = "Owner";
#$visitor = "Visitor";
$editor = "Editor";
$role = get_role();
$count = 0;

print $cgi->header();

$newfolder = check_dir();

# Check to see if we need to show a particular photo or the first photo
my $img = 0;
$img = $cgi->param("i");
if (!($img =~ /\d+/ && $img >= 0 && $img < $count))
{
	$img = 0;
}

if ($newfolder == 0)
{
	print_header();
	slideshow();
}

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
		    <td colspan="2" id="thumbs"><script language="JavaScript" type="text/javascript">loadThumbs();</script>
		      <hr /><br />
		    </td>
		  </tr>
		  <tr>
		    <td width="55%" valign="top"><img id="imgSlide" src="" width="500" /></td>
		    <td valign="top"><table class="info" width="100%" border="0" cellspacing="10" cellpadding="0">
		  <tr>
			<td id="imgFile">&nbsp;</td>
			</tr>
		      <tr>
			<td id="imgTitle">&nbsp;</td>
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
	<title>CSci 5131: Assignment 3</title>
	<link href="includes/a3.css" rel="stylesheet" type="text/css" />
	);
	
	if ($newfolder == 0)
	{
		js_slideshow();
	}

	print qq(
	<script src="includes/ajax.js" language="JavaScript" type="text/javascript"></script>
	</head>
	);
}


sub js_slideshow
{
	print qq(
		<script language="JavaScript" type="text/javascript">
		<!--
		var firstIndex = 0;
		var lastIndex = $count - 1;
		var currentIndex = null;
		var filePrefix = "img_";

		function loadThumbs()
		{
	);
		
	print (	"document.getElementById(\"thumbs\").innerHTML = \"");

	for($i=0; $i<$count; $i++)
	{
		($file, $extension) = split(/\./,$files[$i]);
		($prefix, $index) = split(/_/,$file);
		$filename = $file . "." . $extension;
		$random = int(rand(100000000)) + 100000;
		$filepath = "MyPhotos/" . $filename . "?r=" . $random; 

		print "<img class='noborder' width='50' height='50' src='" . $filepath . "' alt='" . $filename . "' onclick='showImage(" . $index . ")' id='" . $file . "' />";
	}			

	print qq(\";

		(new Image()).src = "includes/loading.gif";						
		}		
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
    open(IN, "session.txt");
    while ($line = <IN>){
        chomp($line);
        @arr = split(",", $line);
        $role = "";
        if ($cookieValue eq $arr[0] ){
            $role = $arr[1];
            return $role;
        }
    }
    return $role;
}

