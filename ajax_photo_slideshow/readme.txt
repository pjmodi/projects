Name: Pushkar Modi
ID: 3798201

FOLDERS
-------
1) images/ - stores the 10 images provided
2) images/thumbs - stores the thumbnails of the 10 images
3) includes/ - stores the .css and .js files
4) info/ - stores the information for each image (used for part 2 only)


CODE FILES
----------
1) index.htm - part 1 of the assignment (available at http://www.pmodi.net/push)
2) ajax.htm - part 2 of the assignment (available at http://www.pmodi.net/push/ajax.htm)
3) includes/a2.css - CSS code for both 1 & 2.
4) includes/slideshow.js - JavaScript that applies to both 1 & 2.
5) includes/sync.js - JavaScript that applies to 1.
6) includes/ajax.js - JavaScript that applies to 2.

- The only difference between "index.htm" and "ajax.htm" is line 8 which includes 5 or 6.


NOTES
-----
1) I decided to create 50x50 thumbnails of each of the images to make the initial loading of the page as efficient as possible.

2) I have used a incremental, numeric naming convention for the images of the format "img_00", "img_01" and so on as per the order specd. on the class webpage. The code works on this assumption with some hard-code. I believe this is fairly extensible and can be modularized into a function.

3) The "Start" button is disabled when the first thumbnail is active.

4) The "Stop slide show" button is disabled when the slide show is OFF.

5) The "Start slide show" button is disabled when the slide show is ON. Thus, there is no alert functionality.

6) For the display information, the drop-down's onchange event takes care of the changes. Thus, the actual button is not required.

7) I have pre-fetched the bigger images to prevent a delay when the slide-show is turned on.

8) While the ajax call is being carried out, a loading image is showed.

9) The positioning of the assignment is slightly different for aesthitics.

10) While the slide-show is on, you can use the manual mode or click on the images directly.

11) In the slide-show or manual mode, the images loop around.

12) The cursor changes when you roll over a thumbnail to indicate that clicking is an option.

13) The implementation works in Firefox 3, IE7 and Safari 4. Appropriate XmlHttpRequest objects are created in code.