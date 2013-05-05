#!/soft/perl5.8.7-bin/perl

use CGI qw(:standard);
$cgi = new CGI;
use CGI::Carp qw(fatalsToBrowser);
$role = "";
$cookie;

# Check if cookie exist with request
# if Yes then validate cookie then jummp to step 5
# if does not exist then redirect to login page
# finish; 
# If cookie is valid
# check for control parameter
# If control parameter is backLink then take to slideShow
# If control paramter is addUser then do add User functionality
# If control parameter is DeleteUser then do delete user functionality
# If none of above then simply print the basic page.

# Page printing
# page is divided in multiple component
# for example header portion, addForm portion, DeleteForm portion, end portion

if (defined $cgi->cookie('LOGIN_COOKIE')){
    my $cookie_l = $cgi->cookie('LOGIN_COOKIE');
    if (validate_cookie($cookie_l)){
    if ($cgi->param('control3') eq "backLink"){
    		my $location = 'show.cgi';
         print $cgi->redirect(-status=>302,-location=>$location);
    }elsif ($cgi->param('control1') eq "AddUser"){
	    print_header($cookie_l);
	    process_addUser();
	    print_delForm();
	    print_addForm();
	    print_backPage();
       print_end();
	}elsif($cgi->param('control2') eq "DeleteUser"){
	    print_header($cookie_l);
	    process_delUser();
	    print_delForm();
	    print_addForm();
	    print_backPage();
	    print_end();

	}else {
	    print_header($cookie_l);
	    my $x = $cgi->param('control');
	  #  print "<P> $x<P>";
	    print_delForm();
	    print_addForm();
	    print_backPage();
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
    open(IN, "session.txt");
    while ($line = <IN>){
        chomp($line);
        @arr = split(",", $line);
        $role = "";
        if ($cookieValue eq $arr[0] && $arr[1] eq "Owner"){
            $role = $arr[1];
            return 1;
        }
    }
    return 0;
}

# print the backLink button
sub print_backPage(){

	print "<div align='center'><br/><hr width='50%'/><br/><br/>";
	print $cgi->start_form(-name=>'BackPage');
   print $cgi->hidden('control3', "backLink");
   print $cgi->submit( -name=>"See SlideShow");
   print $cgi->end_form;
   print "</div>";
   
}

#print the delete form
sub print_delForm(){

    open(IN, "login.txt");
    my $BoxName;
    my @rows;
    print "<P align='center'><b>Delete User(s)</b><P>";
    print $cgi->start_form(-name=>'DeleteUser', -onSubmit=>'return confirm("Are you sure you want to delete the selected user(s)?")');
    print $cgi->hidden('control2', "DeleteUser");
    push(@rows, th(["USERNAME", "ROLE"]));
    while($line = <IN>){
	chomp($line);
	@arr = chomp($line);
	@arr = split(",", $line);
   	$BoxName = $arr[0];
	if ($arr[0]){ 
	    push(@rows, td([checkbox(-name=>$BoxName, -value=>'1'), $arr[2]]));
	}
    }
    push(@rows, td([submit(-name=>"Delete User")]));
    print $cgi->table({-name=>'UserList', -align=>'center'}, Tr(\@rows) );
    print $cgi->end_form;

    close(IN);
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
	my $newuser = $cgi->param('newUserName').",".$cgi->param('newPasswd').",".$cgi->param('newRole')."\n";
	addUser($newuser);
	print "<P align='center' class='redtxt'>User $username Added Successfully!<P>";
    }
   
}

#Process the delete User form
sub process_delUser(){

    open(IN, "<login.txt") or die "can't open file: $!";
    open(TEMP, ">temp.txt") or die "can't open file: $!";
    while($line = <IN>){
	chomp($line);
        @arr = split(",", $line);
	if ($cgi->param($arr[0]) ne '1') {	
	   print TEMP $line."\n";
	} 
      }
      close(IN);
      close(TEMP);

      system('mv login.txt login.txt.bak');
      system('mv temp.txt login.txt');
      system('rm -f temp.txt');
    
    print "<P align='center' class='redtxt'>User(s) Deleted Successfully!<P>";

}

# Add the user to login-password file
sub addUser(){

    my ($newEntry) = @_;
    open(OUT, ">>login.txt") || die $!;
    print OUT $newEntry;
    close(OUT);
}

# Check for existing user
sub check_existUser(){
    
    my ($loginname, $password, $role) = @_;
    my $match = 1;
    open(IN, "login.txt");
    while($line = <IN>){
        chomp($line);
        @arr = split(",", $line);
        if ($arr[0] eq $loginname){
            $match = 0;
            break;
        }
    }
    close(IN);
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
    push(@rows, td([submit(-name=>"Add User")]));
    print table({-name=>'addForm', -align=>'center'}, Tr(\@rows) );
    print $cgi->end_form;
}
