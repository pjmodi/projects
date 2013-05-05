<!--
var fileRequest = null; 
var titleRequest = null; 
var descRequest = null;
var uploadRequest = null;
var editRequest = null;

var filename = "File Name: ";
var title = "Title: ";
var description = "Description: ";
var upload = "Upload New Photo: ";
var edit = "Edit Photo Details: ";

var slideShowOn = false;
var showInterval = 1000;
var timerIndex = null;
var valid_extensions = /(.jpg|.jpeg|.gif|.png|.bmp)$/i;



/////////////////////////////////////////////////////////////////////////////////

// PHOTO ADD/DELETE/EDIT CODE START HERE

/////////////////////////////////////////////////////////////////////////////////

function getPhotoInfo()
{
	document.getElementById("editFile").value = fileRequest.responseText;
	document.getElementById("editTitle").value = titleRequest.responseText;
	document.getElementById("editDesc").value = descRequest.responseText;
	document.getElementById("img").value = filePrefix + currentIndex;		
}

function addPhotoLinks()
{
	addEditLink();
	addDeleteLink();
	addUploadLink();
}

function addEditLink()
{
	document.getElementById("userEdit").innerHTML = "<a href='#' onclick='editPhoto();'>Edit photo details</a>";
}

function addDeleteLink()
{
	document.getElementById("userDelete").innerHTML = "<form id='frmDelete' name='frmDelete' action='delete.cgi' method='post'>	<input type='hidden' name='delete' id='delImg' />	<input type='hidden' name='last' id='lastImg' />	<a href='#' onclick='deletePhoto();'>Delete this photo</a>	</form>";
}

function addUploadLink()
{
	document.getElementById("userUpload").innerHTML = "<a href='#' onclick='uploadPhoto();'>Upload new photo</a>";		
}

function checkExtension(fld)
{
	document.getElementById('btnSubmit').disabled = true;
	if (valid_extensions.test(fld.value))
	{
		document.getElementById('btnSubmit').disabled = false;    	 
		return true;
	}

	alert('Invalid file type. Please ensure you are uploading an image of type GIF, JPG, BMP or PNG.');
	fld.value = " ";
	fld.select();
	fld.focus();

	return false;
}



/////////////////////////////////////////////////////////////////////////////////

// SLIDE SHOW CODE START HERE

/////////////////////////////////////////////////////////////////////////////////

function showImage(imgIndex)
{
	// If the first image is to be shown, Start button should be disabled
	if (imgIndex == firstIndex)
		document.getElementById('btnStart').disabled = true;
	else
		document.getElementById('btnStart').disabled = false;
	
	// Switch off the border of the previously selected thumbnail
	if (currentIndex != null)
	{
		var prevImg = filePrefix + currentIndex;
		document.getElementById(prevImg).className = "noborder";
	}
	
	// Set current image to the give image
	currentIndex = imgIndex;	

	var nextImg = filePrefix + imgIndex;
	var alt = document.getElementById(nextImg).alt;
	var fileparts = new Array();
	fileparts = alt.split(".");
	var extension = fileparts[1];

	// Set red border and show appropriate image
	document.getElementById('imgSlide').src = "MyPhotos/" + nextImg + "." + extension;
	document.getElementById(nextImg).className = "redborder";	
	
	// Show relevant information - defined in separate file
	showInfo();
}


// Start the slide show
function startShow()
{
	if (!slideShowOn)
	{
		document.getElementById('btnStartShow').disabled = true;
		document.getElementById('btnStopShow').disabled = false;	
		slideShowOn = true;
	}
	
	next();
	timerIndex = setTimeout("startShow()", showInterval);
}

// Stop the slide show
function stopShow()
{
	document.getElementById('btnStartShow').disabled = false;
	document.getElementById('btnStopShow').disabled = true;	
	slideShowOn = false;
	
	window.clearTimeout(timerIndex);
}

function first()
{
	// Show the first image
	showImage(firstIndex);
}

function next()
{
	// If we are on the last image - show the first image
	if (currentIndex == lastIndex)
		showImage(firstIndex);
	else // Else, show the next image
		showImage(currentIndex + 1);
}

function previous()
{
	// If we are on the first image, show the last image
	if (currentIndex == firstIndex)
		showImage(lastIndex);
	else // Else, show the previous image
		showImage(currentIndex - 1);
}



/////////////////////////////////////////////////////////////////////////////////

// AJAX CODE START HERE

/////////////////////////////////////////////////////////////////////////////////

function editPhoto()
{
	document.getElementById("userEdit").innerHTML = "Loading... " + "<img src='includes/loading.gif' width='12' height='12'>";
	getContent(edit, "edit.htm");	
}

function deletePhoto()
{
	document.getElementById("delImg").value = document.getElementById(filePrefix + currentIndex).alt;
	document.getElementById("lastImg").value = document.getElementById(filePrefix + lastIndex).alt;
	
	if (confirm("Are you sure you want to delete this photo?"))
		document.getElementById("frmDelete").submit();	
}

function uploadPhoto()
{
	document.getElementById("userUpload").innerHTML = "Loading... " + "<img src='includes/loading.gif' width='12' height='12'>";
	getContent(upload, "upload.htm");
}

function showInfo()
{
	// While the content is being fetched show the loading animation image
	document.getElementById("imgFile").innerHTML = filename + " <br><img src='includes/loading.gif' width='12' height='12'>";
	document.getElementById("imgTitle").innerHTML = title + " <br><img src='includes/loading.gif' width='12' height='12'>";	
	document.getElementById("imgDesc").innerHTML = description + " <br><img src='includes/loading.gif' width='12' height='12'>";
	
	getContent(filename, "MyPhotos/info/" + filePrefix + currentIndex + "/filename.txt?ms=" + new Date().getTime());
	getContent(title, "MyPhotos/info/" + filePrefix + currentIndex + "/title.txt?ms=" + new Date().getTime());
	getContent(description, "MyPhotos/info/" + filePrefix + currentIndex + "/desc.txt?ms=" + new Date().getTime());
}

function getContent( msgPrefix, url )
{
	try
	{
		if (msgPrefix == filename)
		{
			try
			{
				// Firefox, Opera 8.0+, Safari
				fileRequest = new XMLHttpRequest();
			}
			catch (e)
			{
				// Internet Explorer
				try
				{
					fileRequest = new ActiveXObject("Msxml2.XMLHTTP");
				}
				catch (e)
				{
					fileRequest = new ActiveXObject("Microsoft.XMLHTTP");
				}
			}
			fileRequest.onreadystatechange = fileChange; 		// register event handler	
			fileRequest.open( 'GET', url, true ); 				// make the ajax request
			fileRequest.send( null ); 			
		}
		else if (msgPrefix == title)
		{
			try
			{
				// Firefox, Opera 8.0+, Safari
				titleRequest = new XMLHttpRequest();
			}
			catch (e)
			{
				// Internet Explorer
				try
				{
					titleRequest = new ActiveXObject("Msxml2.XMLHTTP");
				}
				catch (e)
				{
					titleRequest = new ActiveXObject("Microsoft.XMLHTTP");
				}
			}
			titleRequest.onreadystatechange = titleChange; 		// register event handler	
			titleRequest.open( 'GET', url, true ); 				// make the ajax request
			titleRequest.send( null ); 			
		}
		else if (msgPrefix == description)
		{
			try
			{
				// Firefox, Opera 8.0+, Safari
				descRequest = new XMLHttpRequest();
			}
			catch (e)
			{
				// Internet Explorer
				try
				{
					descRequest = new ActiveXObject("Msxml2.XMLHTTP");
				}
				catch (e)
				{
					descRequest = new ActiveXObject("Microsoft.XMLHTTP");
				}
			}
			descRequest.onreadystatechange = descChange; 		// register event handler	
			descRequest.open( 'GET', url, true ); 				// make the ajax request
			descRequest.send( null ); 			
		}
		else if (msgPrefix == upload)
		{
			try
			{
				// Firefox, Opera 8.0+, Safari
				uploadRequest = new XMLHttpRequest();
			}
			catch (e)
			{
				// Internet Explorer
				try
				{
					uploadRequest = new ActiveXObject("Msxml2.XMLHTTP");
				}
				catch (e)
				{
					uploadRequest = new ActiveXObject("Microsoft.XMLHTTP");
				}
			}
			uploadRequest.onreadystatechange = uploadChange; 		// register event handler	
			uploadRequest.open( 'GET', url, true ); 			// make the ajax request
			uploadRequest.send( null ); 			
		}		
		else if (msgPrefix == edit)
		{
			try
			{
				// Firefox, Opera 8.0+, Safari
				editRequest = new XMLHttpRequest();
			}
			catch (e)
			{
				// Internet Explorer
				try
				{
					editRequest = new ActiveXObject("Msxml2.XMLHTTP");
				}
				catch (e)
				{
					editRequest = new ActiveXObject("Microsoft.XMLHTTP");
				}
			}
			editRequest.onreadystatechange = editChange; 		// register event handler	
			editRequest.open( 'GET', url, true ); 			// make the ajax request
			editRequest.send( null ); 			
		}			
	} 
	catch ( exception )
	{
		alert( 'Request failed.' );
	} 
} 


function fileChange()
{
	if ( fileRequest.readyState == 4 && fileRequest.status == 200 )
	{
		document.getElementById("imgFile").innerHTML = "<b>" + filename + "</b>" + fileRequest.responseText;
	}
}


function titleChange()
{
	if ( titleRequest.readyState == 4 && titleRequest.status == 200 )
	{
		document.getElementById("imgTitle").innerHTML = "<b>" + title + "</b>" + titleRequest.responseText;
	}
}


function descChange()
{
	if ( descRequest.readyState == 4 && descRequest.status == 200 )
	{
		document.getElementById("imgDesc").innerHTML = "<b>" + description + "</b>" + descRequest.responseText;
	}
}


function editChange()
{
	if ( editRequest.readyState == 4 && editRequest.status == 200 )
	{
		document.getElementById("userEdit").innerHTML = "<b>" + edit + "</b><br>" + editRequest.responseText;
		getPhotoInfo();
	}
}


function uploadChange()
{
	if ( uploadRequest.readyState == 4 && uploadRequest.status == 200 )
	{
		document.getElementById("userUpload").innerHTML = "<b>" + upload + "</b><br>" + uploadRequest.responseText;
	}
}


function checkForm(){
        if (document.getElementById('username').value == "" ){
                document.getElementById("formCheck_user").innerHTML = "<p class='redtxt'>Enter the Username!</p>";
                return false;
        }else if (document.getElementById('passwd').value == "") {
                document.getElementById("formCheck_pass").innerHTML = "<p class='redtxt'>Enter the Password!</p>";
                document.getElementById("formCheck_user").innerHTML = '';
                return false;
        }else {
                return true;
        }
}

// -->
