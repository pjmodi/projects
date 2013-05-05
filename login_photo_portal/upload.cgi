#!/soft/perl5.8.7-bin/perl -w
use CGI;
use Cwd;
use File::Basename;
$cgi = new CGI();
$sizelimit = 1;						# file size limit in MB
$CGI::POST_MAX = 1024 * 1 * $sizelimit;


# FOR DEBUGGING:
#use strict;
#use CGI::Carp qw(fatalsToBrowser);
BEGIN {
open (STDERR, '>>upload.log');
}
# END FOR DEBUGGING

# Check to see if the file is of appropriate size
my $file = $cgi->param("file");
my $usertitle = $cgi->param("title");
my $userdesc = $cgi->param("desc");
if ( !$file )
{
	 print $query->header ( );
	 print "There was a problem uploading your photo. Please ensure that the filesize is not more than " . $sizelimit . "MB.";
	 exit;
} 


# Create the appropriate file name
my $folder = getcwd . "/MyPhotos";
my ( $name, $path, $extension ) = fileparse ( $file, '\..*' );
my $originalname = $name . $extension;
my $newname = "img_" . getcount();

if ($extension eq ".JPEG" || $extension eq ".jpeg")
	{ $extension = ".jpg"; }

my $filename = $newname . $extension;


# Upload the file in binary mode
my $file_handle = $cgi->upload("file");
open ( UPLOAD, ">$folder/$filename" ) or die "$!";
binmode UPLOAD;
while ( <$file_handle> )
{
	print UPLOAD;
}
system ("chmod 644 $folder/$filename");
close UPLOAD; 


# Create appropriate folder to store the information for the image
my $newfolder = "$folder" . "/info/" . $newname;
system("mkdir $newfolder");
system("chmod 755 $newfolder");


# We create the text files for the image
opendir(IMG, $newfolder ) or die "Could not open directory " . $newfolder;
my $userfile = "filename.txt";
my $title = "title.txt";
my $desc = "desc.txt";
 
open(TXTFILE,">>$newfolder/$userfile") or die("Can't open file " . $userfile);
print TXTFILE ("$originalname");
close(TXTFILE);
open(TXTTITLE,">>$newfolder/$title") or die("Can't open file " . $title);
print TXTTITLE ("$usertitle");
close(TXTTITLE);
open(TXTDESC,">>$newfolder/$desc") or die("Can't open file " . $desc);
print TXTDESC ("$userdesc");
close(TXTDESC);

system ("chmod 644 $newfolder/*.*");
closedir(IMG);


# We reload the slideshow page at this point
$showimage = getcount() - 1;
print "Location: show.cgi?i=$showimage", "\n\n";

sub getcount
{
	my $count = 0;
	
	# Try opening the directory and reading the files
	opendir(BIN, $folder ) or die "Could not open directory " . $folder;
	while (defined ($file = readdir BIN))
	{
		next if $file =~ /^\.\.?$/;		#skip . and ..
		next if $file =~ /info$/;			#skip folder "info"
		$count = $count + 1;
	}
	closedir(BIN);	

	# We would like to sort filenames on length and then alphabetically
	return $count;
}
