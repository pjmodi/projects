Name: Pushkar Modi, ID: 3798201
Name: Saurabh Jain, ID: 3939351


ONLINE
------
http://www-users.cs.umn.edu/~modi/a3/login.cgi


FOLDERS
-------
1) MyPhotos/ - Stores the images. This folder will be created if it doesn't exist by show.cgi
2) includes/ - Stores the .css and .js files
3) MyPhotos/info/ - Stores the information for each image under the respective image name. This folder will be created if it doesn't exist by show.cgi


CODE FILES
----------
1) login.cgi - Login page.
2) show.cgi - Shows the slideshow depending on the role of the user.
3) delete.cgi - Handles the deletion of an image.
4) upload.cgi/.htm - Handles the uploading of an image. Redirects back to show.cgi to show what has been uploaded.
5) edit.cgi/.htm - Handles the editing of an image's information. Redirects back to show.cgi to show what has been edited.
6) owner.cgi - Lets the owner add/delete users.
7) includes/a3.css - CSS code that applies to the entire project.
8) includes/ajax.js - JavaScript that applies to the entire project.
9) login.txt - Stores the <Usernam0e,Password,Role>.
10) session.txt - Stores the session id's with the correspoding roles. Used for authenticating the cookie.
11) other *.log files are created by the .cgi files for debugging purposes.


FLOW
----
1) User tries to log in. Appropriate errors are shown as applicable.
2) Depending on the role, appropriate options are shown.
- When the MyPhotos folder is blank or doesn't exist, the user sees a basic UI with only the option to Add Photos (if editor or owner) and Modify Users (if owner).
- If the user is an owner, we provide the option to Modify Users on the slide show page.
- If the user is an editor or owner, we provide the option to Add/Delete/Edit Photos on the slide show page.
- If the user is a visitor, he will only see the slideshow.


NOTES
-----
1) We have used an incremental, numeric naming convention (internally) for the images of the format "img_0", "img_1" and so on. The code works on this assumption with some hard-code.

2) The implementation works in Firefox 3 and IE 8.

3) A user will not be able to hack our system by bypassing role functionality in anyway. Cookie management take care of redirecting the user to appropriate page depending on his role.

4) We haven't checked our code for hacks or tainted inputs (-T option for perl). In other words, we assume that the users will not try to break our system deliberately.

5) When a photo is edited, we accept the changes and show the same photo with the changes.

6) When a photo is uploaded, we accept the image and show the new photo appended to the slideshow. We support files of type .jpg, .jpeg, .bmp, .gif and .png.

7) When a photo is deleted, we do the needful and rename the last photo to take the place of the deleted photo in the sequence (explained in 1).

8) We use simultaneous AJAX calls to fetch information for each photo. Additionally, we also inject the forms to edit or upload photos using AJAX.

9) Our intepretation of "Upload one or more Photos" was that the user should be able to upload one or more photos to his folder (not necessarily at once). As soon as the user has uploaded a photo, we show what has been uploaded and provide the option for an additional upload.

10) Forms are supported with Javascript validation. Javascript validation is also used for checking the extension of the image being uploaded.

11) We have incorporate techniques to prevent caching of AJAX calls to instantly show the changes of the user.

12) Slideshow support from Assignment 2.


FEATURE SUPPORT
---------------

Photo folder support		YES
Role support			YES
SlideShow support		YES
Add/modify/delete photo		YES	
Cookie management		YES
Javascript support		YES
use of CGI.pm			YES
Ajax based DOM manipulation 	YES
Add/delete users		YES


