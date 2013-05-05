Name: Pushkar Modi, ID: 3798201
Name: Saurabh Jain, ID: 3939351


ONLINE
------
http://swedishchef.cs.umn.edu:8080/examples/servlets/a5.html


CODE FILES
----------
1) a5.html - The HTML page for accepting the user's input
2) newsObject.java - Class to store the title, description and link of each news item
3) getData.java - Class to get the news items from the RSS feed and then filter them
4) RSSView.java - Servlet that handle the users request and prints the relevant HTML to the response


WEB.XML FILE CHANGES
--------------------
    <servlet>
        <servlet-name>RSSView</servlet-name>
        <servlet-class>RSSView</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>RSSView</servlet-name>
        <url-pattern>/servlets/servlet/RSSView</url-pattern>
    </servlet-mapping> 



FEATURE SUPPORT
---------------

RSS FETCH	YES
XML PARSE	YES	
ANY OR ALL	YES
KEYWORD SEARCH	YES



