<!--
var firstIndex = 0;
var lastIndex = 9;
var currentIndex = firstIndex;
var filePrefix = "img_0";
var slideShowOn = false;
var showInterval = 1000;
var timerIndex = null;

function loadThumbs()
{
	// Initialize all the thumbnails
	for(var i=firstIndex; i<=lastIndex; i++)
	{
		var fileName = filePrefix + i;
		
		document.write('<img class=noborder width=50 height=50 ');
		document.write('src=images/thumbs/' + fileName + '.jpg ');
		document.write('onclick="showImage(' + i + ');"');		
		document.write('id=' + fileName + ' />');
		
		// Prefetch corresponding image
		(new Image()).src = "images/" + fileName + ".jpg";
	}
	
	// Set the border of the initial thumbnail
	document.getElementById(filePrefix + currentIndex).className = "redborder";
}

function showImage(imgIndex)
{
	// If the first image is to be shown, Start button should be disabled
	if (imgIndex == firstIndex)
		document.getElementById('btnStart').disabled = true;
	else
		document.getElementById('btnStart').disabled = false;
	
	// Switch off the border of the previously selected thumbnail
	var prevImg = filePrefix + currentIndex;
	document.getElementById(prevImg).className = "noborder";
	
	// Set current image to the give image
	currentIndex = imgIndex;	

	// Set red border and show appropriate image
	var nextImg = filePrefix + imgIndex;
	document.getElementById('imgSlide').src = "images/" + nextImg + ".jpg";
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
-->