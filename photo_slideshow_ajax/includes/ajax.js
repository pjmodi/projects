<!--
var bldg = new Array(lastIndex - firstIndex + 1);
var asyncRequest = null; 
var msgPrefix = "";

function initShow()
{
	loadThumbs();
	initData();
	
	// Prefetch the loading image
	(new Image()).src = "images/loading.gif";
}

function showInfo()
{
	// display the building's name
	document.getElementById("bldgName").innerHTML = "Building: " + bldg[currentIndex].name;
	
	var selection = document.getElementById("mnuInfoType").value;
	
	if (selection == "optYear")
	{
		// fetch year information
		msgPrefix = "Year: ";
		getContent("info/" + filePrefix + currentIndex + "/year.htm");
	}
	else if (selection == "optArch")
	{
		// fetch architect information
		msgPrefix = "Architect: ";
		getContent("info/" + filePrefix + currentIndex + "/architect.htm");	
	}
	else if (selection == "optDesc")
	{
		// fetch description	
		msgPrefix = "Description: ";
		getContent("info/" + filePrefix + currentIndex + "/description.htm");
	}
	else
	{
		msgPrefix = "";
		document.getElementById("bldgInfo").innerHTML = "&nbsp;";
	}
}

function getContent( url )
{
	try
	{
		// Get the appropriate object
		asyncRequest = GetXmlHttpObject(); 
		
		if (asyncRequest != null)
		{
			asyncRequest.onreadystatechange = stateChange; 		// register event handler
			asyncRequest.open( 'GET', url, true ); 				// make the ajax request
			asyncRequest.send( null ); 
		}
	} 
	catch ( exception )
	{
		alert( 'Request failed.' );
	} 
} 


function stateChange()
{
	// While the content is being fetched show the loading animation image
	if ( asyncRequest.readyState < 4)
	{
		document.getElementById("bldgInfo").innerHTML = msgPrefix + " <img src='images/loading.gif' width='16' height='16'>";
	} // Else, show the content retrieved
	else if ( asyncRequest.readyState == 4 && asyncRequest.status == 200 )
	{
		document.getElementById("bldgInfo").innerHTML = msgPrefix + asyncRequest.responseText;
	}
}


// This function gets the appropriate request object depending on the browser
function GetXmlHttpObject()
{
	var xmlHttp = null;
	
	try
	{
		// Firefox, Opera 8.0+, Safari
		xmlHttp = new XMLHttpRequest();
	}
	catch (e)
	{
		// Internet Explorer
		try
		{
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		}
		catch (e)
		{
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}

	return xmlHttp;
}

function initData()
{
	for(var i=firstIndex; i<=lastIndex; i++)
	{
		bldg[i] = new Object();

		// We could cache the content once fetched using AJAX
		// bldg[i].year = null;
		// bldg[i].arch = null;
		// bldg[i].desc = null;		
	}
		
	bldg[0].name = "Armory";
	bldg[1].name = "Pillsbury Hall";
	bldg[2].name = "Folwell Hall";
	bldg[3].name = "Jones Hall";
	bldg[4].name = "Pillsbury Statue";
	bldg[5].name = "Wesbrook Hall";
	bldg[6].name = "Nicholson Hall";
	bldg[7].name = "Eddy Hall"; 
	bldg[8].name = "Music Education";
	bldg[9].name = "Wulling Hall";
}
// -->