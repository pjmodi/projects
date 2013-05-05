<!--
var bldg = new Array(lastIndex - firstIndex + 1);

function initShow()
{
	loadThumbs();
	populateData();
}

function showInfo()
{
	document.getElementById("bldgName").innerHTML = "Building: " + bldg[currentIndex].name;
	
	var selection = document.getElementById("mnuInfoType").value;
	
	if (selection == "optYear")
		document.getElementById("bldgInfo").innerHTML = "Year: " + bldg[currentIndex].year;	
	else if (selection == "optArch")
		document.getElementById("bldgInfo").innerHTML = "Architect: " + bldg[currentIndex].arch;
	else if (selection == "optDesc")
		document.getElementById("bldgInfo").innerHTML = "Description: " + bldg[currentIndex].desc;		
	else
		document.getElementById("bldgInfo").innerHTML = "&nbsp;";
}

function populateData()
{
	for(var i=firstIndex; i<=lastIndex; i++)
		bldg[i] = new Object();
		
	bldg[0].name = "Armory";
	bldg[0].year = 1896;
	bldg[0].arch = "Charles Aldrich";
	bldg[0].desc = "Built for athletics and military drill, as well as performing arts and social activities. Memorial plaques at the front entrance honor students, faculty, and alumni who fought in the Spanish-American War.";
	
	bldg[1].name = "Pillsbury Hall";
	bldg[1].year = 1889;
	bldg[1].arch = "Leroy Buffington with Harvey Ellis";
	bldg[1].desc = "Built as Science Hall. Named for Governor John S. Pillsbury.";
	
	bldg[2].name = "Folwell Hall";
	bldg[2].year = 1907;
	bldg[2].arch = "Clarence H. Johnston, Sr.";
	bldg[2].desc = "When Old Main burned in 1904, Folwell Hall was built to house displaced departments. Named for William Watts Folwell, first president of the University, 1869-84.";
	
	bldg[3].name = "Jones Hall";
	bldg[3].year = 1901;
	bldg[3].arch = "Charles Aldrich";
	bldg[3].desc = "Built as Physics Building. Named for Frederick S. Jones, professor of physics and dean of the College of Engineering.";	
	
	bldg[4].name = "Pillsbury Statue";
	bldg[4].year = 1900;
	bldg[4].arch = "Daniel C. French, sculptor";
	bldg[4].desc = "Pillsbury statue located across the street from Burton Hall.";
	
	bldg[5].name = "Wesbrook Hall";
	bldg[5].year = 1898;
	bldg[5].arch = "Frederick Corser";
	bldg[5].desc = "Built as Laboratory of Medical Science. In 1912, dentistry moved here. Named for Frank Wesbrook, professor of pathology and bacteriology and dean of the College of Medicine and Surgery.";
	
	bldg[6].name = "Nicholson Hall";
	bldg[6].year = 1890;
	bldg[6].arch = "LeRoy Buffington with Harvey Ellis";
	bldg[6].desc = "Built as chemical laboratory. In 1914, chemistry moved to the mall area and Nicholson was remodeled for the men's union until Coffman Memorial Union was built as a coed student union. Named for Edward E. Nicholson, professor of chemistry and later dean of Student Affairs.";
	
	bldg[7].name = "Eddy Hall"; 
	bldg[7].year = 1886;
	bldg[7].arch = "LeRoy Buffington";
	bldg[7].desc = "Built as Mechanic Arts. It is the oldest existing building on campus. Named for Henry Turner Eddy, professor of engineering and mathematics and dean of the Graduate School.";
	
	bldg[8].name = "Music Education";
	bldg[8].year = 1888;
	bldg[8].arch = "Warren H. Hayes";
	bldg[8].desc = "Built as Student Christian Association building. Acquired by the University, it housed Child Welfare and Music Education.";
	
	bldg[9].name = "Wulling Hall";
	bldg[9].year = 1892;
	bldg[9].arch = "Allen Stem and Charles Reed";
	bldg[9].desc = "Built as Medical Hall; named Millard Hall in 1906. Fire damaged the building. It later became the site for the pharmacy building. Named for Frederick J. Wulling, first dean and founder of the College of Pharmacy.";
}
-->