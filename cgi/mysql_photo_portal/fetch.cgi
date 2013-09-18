#!/soft/perl5.8.7-bin/perl

use DBI;
use CGI qw(:standard);
use DBD::mysql;
$databaseHandle = DBI->connect("DBI:mysql:c5131s09u32:db.itlabs.umn.edu:3311","c5131s09u32", "saurabh", {RaiseError=>1});
$cgi = new CGI;

my $toReturn;
my $colname = $cgi->param('col');
my $id = $cgi->param('id');
#my $colname = "descfile";
#my $id = "img_0";

my $query = "select $colname from PhotoFileList where id='$id'";
my $statementHandle = $databaseHandle->prepare($query);
$statementHandle->execute();

while(@results = $statementHandle->fetchrow_array()){
     $toReturn = $results[0];
}
$databaseHandle->disconnect();
$statementHandle->finish();

print  "Content-Type: text/plain\r\n";
print $cgi->header();
if ($colname eq "descfile"){
	my $fname = "MyPhotos/info/".$id."/desc.txt";
	open(IN, $fname);
	$toReturn = "";	
	while (<IN>){
   		$toReturn=$toReturn.$_;
	}
	close(IN);
	print $toReturn;	
}else {
	print $toReturn;
} 
