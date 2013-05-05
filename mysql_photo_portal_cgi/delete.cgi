#!/soft/perl5.8.7-bin/perl -w
use CGI;
use Cwd;
use File::Path;
use DBI;
use DBD::mysql;
$cgi = new CGI();
$databaseHandle = DBI->connect("DBI:mysql:c5131s09u32:db.itlabs.umn.edu:3311","c5131s09u32", "saurabh", {RaiseError=>1});


# FOR DEBUGGING:
#use strict;
#use CGI::Carp qw(fatalsToBrowser);
BEGIN {
open (STDERR, '>>delete.log');
}
# END FOR DEBUGGING

# Get the appropriate file name
my $folder = getcwd . "/MyPhotos";
my $deleteimg = $cgi->param("delete");		#of the form img_0.jpg
my $lastfilename = $cgi->param("last");	#of the form img_15
my $editu = $cgi->param("u");	
my $editc = $cgi->param("c");	
($delfilename, $delextension) = split(/\./, $deleteimg);
$lastextension = fetchext($lastfilename);

my $lastimg = $lastfilename . "." . $lastextension;
my $newfile = $delfilename . "." . $lastextension;
# We open the text files for the image
opendir(DELIMG, $folder ) or die "Could not open directory " . $folder . ": $?";
$delfile = unlink($folder . "/" . $deleteimg) || die "Error in deleting file " . $deleteimg . ": $?";
if ($delfilename ne $lastfilename)# && $delfile == 1)
{
	rename($folder . "/" . $lastimg, $folder . "/" . $newfile) || die "Error in renaming file " . $newfile . ": $?";
}
closedir(DELIMG);

$folder = $folder . "/info";
opendir(DELINFO, $folder ) or die "Could not open directory " . $folder . ": $?";
$delsuccess = rmtree($folder . "/" . $delfilename) || die "Error in deleting folder $delfilename: $?";
if ($delfilename ne $lastfilename)# && $delsuccess > 0)
{
	rename($folder . "/" . $lastfilename, $folder . "/" . $delfilename) || die "Error in renaming folder $lastfilename: $?";
}
closedir(DELINFO);


# SQL
my $q_del = "DELETE FROM PhotoFileList WHERE id='" . $delfilename . "';";
my $delHandle = $databaseHandle->prepare($q_del);
$delHandle->execute();
$delHandle->finish();

if ($delfilename ne $lastfilename)
{
	my $q_up = "UPDATE PhotoFileList SET id='" . $delfilename . "' WHERE id='" . $lastfilename . "';";
	my $upHandle = $databaseHandle->prepare($q_up);
	$upHandle->execute();
	$upHandle->finish();	
}

$databaseHandle->disconnect();

# We reload the slideshow page at this point
print "Location: show.cgi?u=$editu&c=$editc", "\n\n";


sub fetchext {
	my $q_ext = "SELECT fname FROM PhotoFileList WHERE id='" . $lastfilename . "';";
	my $fetchHandle = $databaseHandle->prepare($q_ext);
	$fetchHandle->execute();
	
	@results = $fetchHandle->fetchrow_array();
	my $fname = $results[0];	

	$fetchHandle->finish();	
	(my $name, my $ext) = split(/\./, $fname);
	
	return $ext;
}
