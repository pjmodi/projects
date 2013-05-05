#!/soft/perl5.8.7-bin/perl

use Fcntl ':flock';
use CGI qw(:standard);
$cgi = new CGI;
$range = 100;
$role = "";
$cookie;  

# Check if cookie exist with request
# if cookie exist then validate cookie in step 10
# If cookie does not exist then check for parameter.
# If parameter does not exist then this is fresh request
# so print the page.
# If paramter exist and control paramter equals submission
# so process the credential filled in the form.

# Now assume that cookie defined and validate 
# Check for the role associated with credentials and redirect to appropriate page accordingly.
# If cookie is defined but invalid then say sorry cookie expired
# print the login form

# Now suppose that cookie is not defined but it is a submit request
# process the credential and see if it is a valid request or not
# if credential match then take user to appropriate page 

if (defined $cgi->cookie('LOGIN_COOKIE')){
    my $cookie_l = $cgi->cookie('LOGIN_COOKIE');
    if (validate_cookie($cookie_l)){
	call_page();
    }else {
	print_header();
	print "<P class='redtxt' align='center'>Your cookie has expired or is invalid. Please login with valid credentials.<P>";
	print_form();
	print_end();
    }
}elsif (!$cgi->param()) {
    print_header();
    print_form();
    print_end();
}elsif ($cgi->param('control') eq "submission"){
    $match = authenticate_form($cgi->param('loginname'), $cgi->param('passwd') );
    if ($match == 1){
	success_handler();
    }
    else {
	print_header();
	print_failure();
	print_form();
	print_end();
    }
}

# If credential match then generate cookie for this user
# redirect user to show.cgi with generated cookie
sub success_handler(){
    $cookie = generate_cookie($cgi->param('loginname'), $role);
    call_page();
}

# print if user enters wrong credential
sub print_failure(){
    print "<P class='redtxt' align='center'>Incorrect username or password.<P>";
}

# print end portion of page
sub print_end(){
    print $cgi->end_html;
}

# print header portion of page
sub print_header(){
    
    my ($cookie_l) = @_;
    if (defined $cookie_l){
	print $cgi->header(-cookie => $cookie_l);
    }
    else{ 
	print $cgi->header();
    }
    print $cgi->start_html(-title=>'CSci 5131: Assignment 3',
  			-script=> {
				-src => 'includes/ajax.js',
				-language=> 'javascript'
			},
			-style=> {
			    -src => 'includes/a3.css'	
			}
		     );
   }

# Generate cookie for these credential
# cookie format: "UserName".X."Random number"
# this is stored in session.txt along with role mapping
sub generate_cookie(){

    my ($loginname, $role) = @_;
    $num = rand($range);
# we can remove this X if required
    my $sessionId = $loginname."X".rand($range);
    my $cookie = $cgi->cookie(-name => 'LOGIN_COOKIE',
                     -value => $sessionId,
		     -expires => '+300s');
   store_cookie($sessionId, $role);
   return $cookie;
}

# store this cookie in session.txt file
sub store_cookie(){
    
    my ($sessionId, $role) = @_;
# Note : not required to put in any file. If multiple CGI files then need to put in file    
    open(OUT, ">>session.txt") || die $!;
    flock OUT, LOCK_EX;
    print OUT $sessionId.",".$role."\n";
    close(OUT);
}

# redirect accordingly
sub call_page(){
    
    my $location = 'show.cgi';
    print $cgi->redirect(-status=>302,-location=>$location,-cookie=>$cookie);
}

# validate cookie
sub validate_cookie(){
    
    my ($cookieValue) = @_;
    open(IN, "session.txt");
    while ($line = <IN>){
	chomp($line);
	@arr = split(",", $line);
	if ($cookieValue eq $arr[0] ){
	    $role = $arr[1];
	    return 1;
	}
    }
    return 0;
}

# authenticate credential by reading login.txt file
# locks should be used for this purpose
sub authenticate_form (){
    my ($loginname, $password) = @_;
    my $match = 0;
    open(IN, "login.txt");
    while($line = <IN>){
        chomp($line);
        @arr = split(",", $line);
        if (($arr[0] eq $loginname) && ($arr[1] eq $password)){
	    $role = $arr[2];
            $match = 1;
            break;
        }
    }
    close(IN);
    return $match;
}

#print the login form 
sub print_form {
    print $cgi->start_form(-name=>'registration', -onSubmit=>'return checkForm()');
    print $cgi->hidden('control', "submission");
    push(@rows, td(["Username:", textfield(-name=>loginname, -size=>30, -id=>username), div({-class=>userCheck, -id=>formCheck_user}, '')]));
    push(@rows, td(["Password:", password_field(-name=>passwd,  -size=>30, -id=>passwd), div({-class=>passCheck, -id=>formCheck_pass}, '')]));
    push(@rows, td([submit(-name=>"Login")]));
    print table({-align=>'center'}, Tr(\@rows) );
    print $cgi->end_form;
}
