<!--
var fileRequest = null; 
var titleRequest = null; 
var descRequest = null;
var catRequest = null;
var uploadRequest = null;
var editRequest = null;
var clickNext = false;
var clickPrev = false;

var filename = "File Name: ";
var title = "Title: ";
var description = "Description: ";
var category = "Category: ";
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
	document.getElementById("editCat").value = catRequest.responseText;	
	document.getElementById("img").value = filePrefix + currentIndex;
	document.getElementById("editu").value = selectedUser;
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
	document.getElementById("userDelete").innerHTML = "<form id='frmDelete' name='frmDelete' action='delete.cgi' method='post'>	<input type='hidden' name='delete' id='delImg' />	<input type='hidden' name='last' id='lastImg' />	<input type='hidden' name='u' id='deleteu' />  <input type='hidden' name='c' id='deletec' />  <a href='#' onclick='deletePhoto();'>Delete this photo</a>	</form>";
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
	// We need to initialize firstindex when the slideshow starts
	if (firstIndex == null)
		firstIndex = imgIndex;

	// If the first image is to be shown, Start button should be disabled
	if (imgIndex == firstIndex)
		document.getElementById('btnStart').disabled = true;
	else if (countAfterFilter > 1)
		document.getElementById('btnStart').disabled = false;
	
	// If there is just one photo, we don't need the slideshow functionality
	if (countAfterFilter == 1 && currentIndex != null)
		return;
	// If our index points to a skipped photo, we take appropriate action
	else if (skipPhotos.contains(filePrefix + imgIndex) && currentIndex != null)
	{
		///*
		if (clickNext)
			autoNext(imgIndex++);
		else if (clickPrev)
			autoPrevious(imgIndex--);
		//*/
	}	
	else
	{
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
	clickNext = true;
	clickPrev = false;

	// If we are on the last image - show the first image
	if (currentIndex == lastIndex)
		showImage(firstIndex);
	else // Else, show the next image
		showImage(currentIndex + 1);
}

function previous()
{
	clickNext = false;
	clickPrev = true;

	// If we are on the first image, show the last image
	if (currentIndex == firstIndex)
		showImage(lastIndex);
	else // Else, show the previous image
		showImage(currentIndex - 1);
}

function autoNext(imgIndex)
{
	// If we are on the last image - show the first image
	if (imgIndex == lastIndex)
		showImage(firstIndex);
	else // Else, show the next image
		showImage(imgIndex + 1);
}

function autoPrevious(imgIndex)
{
	// If we are on the first image, show the last image
	if (imgIndex == firstIndex)
		showImage(lastIndex);
	else // Else, show the previous image
		showImage(imgIndex - 1);
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
	document.getElementById("lastImg").value = filePrefix + lastIndex;
	//document.getElementById("lastImg").value = document.getElementById(filePrefix + lastIndex).alt;
	document.getElementById("deleteu").value = selectedUser;
	document.getElementById("deletec").value = selectedCat;
	
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
	document.getElementById("imgFile").innerHTML = "<b>" + filename + " </b><img src='includes/loading.gif' width='12' height='12'>";
	document.getElementById("imgTitle").innerHTML = "<b>" + title + " </b><img src='includes/loading.gif' width='12' height='12'>";	
	document.getElementById("imgDesc").innerHTML = "<b>" + description + " </b><img src='includes/loading.gif' width='12' height='12'>";
	document.getElementById("imgCat").innerHTML = "<b>" + category + " </b><img src='includes/loading.gif' width='12' height='12'>";	
	
	var id = filePrefix + currentIndex;
	getContent(filename, "fetch.cgi?ms=" + new Date().getTime() + "&id=" + id + "&col=fname");
	getContent(title, "fetch.cgi?ms=" + new Date().getTime() + "&id=" + id + "&col=title");
	getContent(category, "fetch.cgi?ms=" + new Date().getTime() + "&id=" + id + "&col=category");	
	getContent(description, "fetch.cgi?ms=" + new Date().getTime() + "&id=" + id + "&col=descfile");				
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
		else if (msgPrefix == category)
		{
			try
			{
				// Firefox, Opera 8.0+, Safari
				catRequest = new XMLHttpRequest();
			}
			catch (e)
			{
				// Internet Explorer
				try
				{
					catRequest = new ActiveXObject("Msxml2.XMLHTTP");
				}
				catch (e)
				{
					catRequest = new ActiveXObject("Microsoft.XMLHTTP");
				}
			}
			catRequest.onreadystatechange = catChange; 		// register event handler	
			catRequest.open( 'GET', url, true ); 				// make the ajax request
			catRequest.send( null ); 			
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


function catChange()
{
	if ( catRequest.readyState == 4 && catRequest.status == 200 )
	{
		document.getElementById("imgCat").innerHTML = "<b>" + category + "</b>" + catRequest.responseText;
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
		document.getElementById("userName").value = userLoggedIn;
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


function delimString(x){
	var index = x.lastIndexOf(",");
	if (index != -1)
	{
		x = x.substring(0, index);
	}
	var myArray = x.split(",");
	return myArray;
}


Array.prototype.contains = function (element) 
{
	for (var i = 0; i < this.length; i++) 
	{
		if (this[i] == element) 
		{
			return true;
		}
	}
	return false;
}

// -->
