#!/soft/perl5.8.7-bin/perl -w
use CGI;
use Cwd;
use DBI;
use DBD::mysql;
$cgi = new CGI();
$databaseHandle = DBI->connect("DBI:mysql:c5131s09u32:db.itlabs.umn.edu:3311","c5131s09u32", "saurabh", {RaiseError=>1});

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
my $editcat = $cgi->param("category");
my $editimg = $cgi->param("img");	# of the form "img_0"
my $editu = $cgi->param("u");	

# Get the appropriate file name
my $folder = getcwd . "/MyPhotos/info/" . $editimg;

# We open the text files for the image
opendir(EDIT, $folder ) or die "Could not open directory " . $folder;
my $desc = "desc.txt";
open(TXTDESC,">$folder/$desc") or die("Can't open file " . $desc);
print TXTDESC ("$editdesc");
close(TXTDESC);
closedir(EDIT);


#SQL
my $q_update = "UPDATE PhotoFileList SET ";
my $q_values = "title='" . $edittitle . "', category='" . $editcat . "' where id='". $editimg ."';";
# "', loginname='" . $username .
my $query = $q_update . $q_values; 
my $statementHandle = $databaseHandle->prepare($query);
$statementHandle->execute();
$databaseHandle->disconnect();
$statementHandle->finish();


# We reload the slideshow page at this point
$editimg =~ m/(\d+)/;
$showimage = $1;
print "Location: show.cgi?i=$showimage&u=$editu&c=$editcat", "\n\n";
