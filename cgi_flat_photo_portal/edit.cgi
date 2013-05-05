#!/soft/perl5.8.7-bin/perl -w
use CGI;
use Cwd;
$cgi = new CGI();

# FOR DEBUGGING:
#use strict;
#use CGI::Carp qw(fatalsToBrowser);
BEGIN {
open (STDERR, '>>edit.log');
}
# END FOR DEBUGGING

# Get the new data from CGI
my $edittitle = $cgi->param("title");
my $editdesc = $cgi->param("desc");
my $editimg = $cgi->param("img");

# Get the appropriate file name
my $folder = getcwd . "/MyPhotos/info/" . $editimg;

# We open the text files for the image
opendir(EDIT, $folder ) or die "Could not open directory " . $folder;

my $title = "title.txt";
my $desc = "desc.txt";
open(TXTTITLE,">$folder/$title") or die("Can't open file " . $title);
print TXTTITLE ("$edittitle");
close(TXTTITLE);
open(TXTDESC,">$folder/$desc") or die("Can't open file " . $desc);
print TXTDESC ("$editdesc");
close(TXTDESC);

closedir(EDIT);

# We reload the slideshow page at this point
$editimg =~ m/(\d+)/;
$showimage = $1;
print "Location: show.cgi?i=$showimage", "\n\n";