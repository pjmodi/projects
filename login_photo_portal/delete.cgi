#!/soft/perl5.8.7-bin/perl -w
use CGI;
use Cwd;
use File::Path;
$cgi = new CGI();

# FOR DEBUGGING:
#use strict;
use CGI::Carp qw(fatalsToBrowser);
BEGIN {
open (STDERR, '>>delete.log');
}
# END FOR DEBUGGING

# Get the appropriate file name
my $folder = getcwd . "/MyPhotos";
my $deleteimg = $cgi->param("delete");	#of the form img_0.jpg
my $lastimg = $cgi->param("last");		#of the form img_15.JPG
($delfilename, $delextension) = split(/\./, $deleteimg);
($lastfilename, $lastextension) = split(/\./, $lastimg);

my $newfile = $delfilename . "." . $lastextension;
# We open the text files for the image
opendir(DELIMG, $folder ) or die "Could not open directory " . $folder . ": $?";
$delfile = unlink($folder . "/" . $deleteimg) || die "Error in deleting file " . $deleteimg . ": $?";
if ($deleteimg ne $lastimg)# && $delfile == 1)
{
	rename($folder . "/" . $lastimg, $folder . "/" . $newfile);
}
closedir(DELIMG);

$folder = $folder . "/info";
opendir(DELINFO, $folder ) or die "Could not open directory " . $folder . ": $?";
$delsuccess = rmtree($folder . "/" . $delfilename) || die "Error in deleting folder $delfilename: $?";
if ($deleteimg ne $lastimg)# && $delsuccess > 0)
{
	rename($folder . "/" . $lastfilename, $folder . "/" . $delfilename) || die "Error in renaming folder $lastfilename: $?";
}
closedir(DELINFO);

# We reload the slideshow page at this point
print "Location: show.cgi", "\n\n";