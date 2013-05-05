#!/soft/perl5.8.7-bin/perl

use CGI qw(:standard);
use DBI;
use DBD::mysql;
$cgi = new CGI;
use CGI::Carp qw(fatalsToBrowser);
$role = "";
$cookie;
$databaseHandle = DBI->connect("DBI:mysql:c5131s09u32:db.itlabs.umn.edu:3311", "c5131s09u32", "saurabh", {RaiseError=>1});

# Check if cookie exist with request
# if Yes then validate cookie then jummp to step 5
# if does not exist then redirect to login page
# finish; 
# If cookie is valid
# check for control parameter
# If control parameter ismysql -u c5131s09u32 -hdb.itlabs.umn.edu -P3311 -p  backLink then take to slideShow
# If control paramter is addUser then do add User functionality
# If control parameter is DeleteUser then do delete user functionality
# If none of above then simply print the basic page.

# Page printing
# page is divided in multiple component
# for example header portion, addForm portion, DeleteForm portion, end portion

if (defined $cgi->cookie('LOGIN_COOKIE')){
    my $cookie_l = $cgi->cookie('LOGIN_COOKIE');
    if (validate_cookie($cookie_l)){
    if ($cgi->param('control3') eq "slideLink" || $cgi->param('control4') eq "loginLink"){
	    handle_otherLink();
     }elsif ($cgi->param('control1') eq "AddUser"){
	    print_header($cookie_l);
	    process_addUser();
	    print_delForm();
	    print_addForm();
	    print_otherPage();
	    print_end();
	}elsif($cgi->param('control2') eq "DeleteUser"){
	    print_header($cookie_l);
	    process_delUser();
	    print_delForm();
	    print_addForm();
	    print_otherPage();
	    print_end();
	}else {
	    print_header($cookie_l);
	    my $x = $cgi->param('control');
	  #  print "<P> $x<P>";
	    print_delForm();
	    print_addForm();
	    print_otherPage();
	    print_end();
	}
    }else{
	my $location = 'login.cgi';
         print $cgi->redirect(-status=>302,-location=>$location);
     }
}else {
     my $location = 'login.cgi';
    print $cgi->redirect(-status=>302,-location=>$location);
}

# Print the header part of page
sub print_header(){
     ($cookie) = @_;
     print $cgi->header(-cookie=>$cookie, -cache_control=>"no-cache, no-store, must-revalidate");
     print $cgi->start_html(-title=>'Modify Users',
					  -script=> {
                            -src => 'includes/ajax.js',
                            -language => 'javascript'
                            },
        			-style=> {
			    -src => 'includes/a3.css'	
							}
			);
     }

# print the end part of page
sub print_end(){
    print $cgi->end_html;
}

# Validate cookie by reading session.txt file which stores the 
# randomSessionId along with role.
# Also checkes if user has permission to view this page or not
sub validate_cookie(){

    my ($cookieValue) = @_;
    my $result;
    my $query = "select role from SESSION_TABLE where sessionID='$cookieValue'";
    my $statementHandle = $databaseHandle->prepare($query);
    $statementHandle->execute();

    while(@result = $statementHandle->fetchrow_array()){
	$role = $result[0];
    }
    $databaseHandle->disconnect();
    $statementHandle->finish();
    if ($role eq  "Owner") {
    #if ($role eq ""){	
	return 1;
    }else{
	return 0;
    }
}

# print the backLink button
sub print_otherPage(){

	print "<div align='center'><br/><hr width='50%'/><br/><br/>";
	print $cgi->start_form(-name=>'slidePage');
	print $cgi->hidden('control3', "slideLink");
	print $cgi->submit( -name=>"submit_slide", -value=>"See SlideShow");
	print $cgi->end_form;

## how to put this side by side
 	print $cgi->start_form(-name=>'loginPage');
        print $cgi->hidden('control4', "loginLink");
        print $cgi->submit( -name=>"submit_login", -value=>"Log me out");
        print $cgi->end_form;
	print "</div>";
}

#print the delete form
sub print_delForm(){

    my @results;
    $query = "select * from LOGIN_TABLE ";
    $statementHandle = $databaseHandle->prepare($query);
    $statementHandle->execute();
 
    my $BoxName;
    my @rows;
    print "<P align='center'><b>Delete User(s)</b><P>";
    print $cgi->start_form(-name=>'DeleteUser', -onSubmit=>'return confirm("Are you sure you want to delete the selected user(s)?")');
    print $cgi->hidden('control2', "DeleteUser");
    push(@rows, th(["USERNAME", "ROLE"]));
   
    while(@results = $statementHandle->fetchrow_array()){
	$BoxName = $results[0];
	if ($results[0]){ 
	    push(@rows, td([checkbox(-name=>$BoxName, -value=>'1'), $results[2]]));
	}
    }
    push(@rows, td([submit(-name=>"Delete User")]));
    print $cgi->table({-name=>'UserList', -align=>'center'}, Tr(\@rows) );
    print $cgi->end_form;

    $databaseHandle->disconnect();
    $statementHandle->finish();

    print "<br/><hr width='50%'/>";
}

# Process the add user form
# check for existing user as well
sub process_addUser(){

    my $match = check_existUser($cgi->param('newUserName'), $cgi->param('newPasswd'), $cgi->param('newRole') );
    my $username = $cgi->param('newUserName');
    if ($match == 0 ){
	print "<P align='center' class='redtxt'>Username $username Already Exists!<P>";
    }else {
	addUser($cgi->param('newUserName'), $cgi->param('newPasswd'), $cgi->param('newRole'));
	print "<P align='center' class='redtxt'>User $username Added Successfully!<P>";
    }
  }

#Process the delete User form
sub process_delUser(){

    my @results;
    my @deleteList;
    my $query = "select loginname from LOGIN_TABLE";
    $statementHandle = $databaseHandle->prepare($query);
    $statementHandle->execute();

    while(@results = $statementHandle->fetchrow_array()){
	if ($cgi->param($results[0]) eq '1') {	
	   push(@deleteList, $results[0]);
	} 
      }
    $databaseHandle->disconnect();
    $statementHandle->finish();

   my $item = shift @deleteList;
   $query = "delete from LOGIN_TABLE where loginname = '$item' ";
 
   while(defined($item = shift @deleteList)){
      $query = $query."or loginname = '$item' ";
    }
   
    $statementHandle = $databaseHandle->prepare($query);
    $statementHandle->execute();
    $databaseHandle->disconnect();
    $statementHandle->finish();
   
    print "<P align='center' class='redtxt'>User(s) Deleted Successfully!<P>";
}

# Add the user to login-password file
sub addUser(){

    my ($loginname, $password, $role) = @_;
    my $query = "insert into LOGIN_TABLE (loginname, password, role) values('$loginname', '$password', '$role')";
    $statementHandle = $databaseHandle->prepare($query);
    $statementHandle->execute();
    $databaseHandle->disconnect();
    $statementHandle->finish();

}

# Check for existing user
sub check_existUser(){
    
    my ($loginname, $password, $role) = @_;
    my $match = 1;
   
    my $query = "select * from LOGIN_TABLE where loginname = '$loginname'";
    $statementHandle = $databaseHandle->prepare($query);
    $statementHandle->execute();
    @results = $statementHandle->fetchrow_array();
    if (defined $results[0]){
            $match = 0;
       }
 
   $databaseHandle->disconnect();
   $statementHandle->finish();
   return $match;
}

# print the add User form
sub print_addForm(){

    print "<p align='center'><b>Add New User</b><p>";
    my @rows;
    print $cgi->start_form(-name=>'NewUser', -onSubmit=>'return checkForm()');
    print $cgi->hidden('control1', "AddUser");
    push(@rows, td(["Username:"]));
    push(@rows, td([textfield(-name=>newUserName, -size=>30, -id=>username, -value=>""), div({-class=>userCheck, -id=>formCheck_user}, '')]));
	 push(@rows, td(["Password:"]));  
    push(@rows, td([password_field(-name=>newPasswd,  -size=>30, -id=>passwd, -value=>""), div({-class=>passCheck, -id=>formCheck_pass}, '')]));
  	 push(@rows, td(["Role:"]));
    push(@rows, td(radio_group(-name=>'newRole', -values=>['Owner', 'Editor', 'Visitor'], -default=>'Visitor')));
    push(@rows, td([submit(-name=>"Add User"), reset()]));
    print table({-name=>'addForm', -align=>'center'}, Tr(\@rows) );
    print $cgi->end_form;
}


sub handle_otherLink {

    if ( $cgi->param('control4') eq "loginLink"){
	delete_cookie();
	my $location = 'login.cgi';
	print $cgi->redirect(-status=>302, -location=>$location);
    }elsif ( $cgi->param('control3') eq "slideLink") {

	my $location = 'show.cgi';
	print $cgi->redirect(-status=>302, -location=>$location);
    }
}


sub delete_cookie{

    my $cookieVal = $cgi->cookie('LOGIN_COOKIE');
    my $query = "delete from SESSION_TABLE where sessionID = '$cookieVal'";
    $statementHandle = $databaseHandle->prepare($query);
    $statementHandle->execute();
    $databaseHandle->disconnect();
    $statementHandle->finish();
}
