/*
var oldDiv = new Hashtable() ;
var oldDivWidth = new Hashtable();
var oldDivHeight = new Hashtable();
var oldDivPosition =  new Hashtable();
var oldDivTop =  new Hashtable();
var containerDiv = new  Hashtable();
var currentDiv = new Hashtable();

var newDiv= new Hashtable() ;
var zeroPx="0"+'px';
var screen_size="100";
var screen_height="500";
var screen_width="1000";
function resize_div(divId) {
	var signature="resize_div(divId)";
	var coll = document.all.tags("div");
	var mydiv=" ";
	var divIdToresize="";
	// find all Div tags on home page
	// Identify wich one to expand == passed as parameter to file
	// Identify 
	for (i=0; i<coll.length; i++) {
		//alert("coll[i].id" + coll[i].id);
		// Bug in IE From Some where it gets the Div Tags that dont even exists
		if (coll[i].id == "" || coll[i].id == null) continue; 
	try {
		divElement=document.getElementById(coll[i].id) ;
		//alert(coll[i].id + "== " + divId );
			if (coll[i].id == divId )  {
				divIdToresize = coll[i].id ;
				
			} else {
				switch (coll[i].id) {
						case 'main': //alert("Div Id is main ") ;
								break;
						case 'container':
								//alert("Div Id is container") ;
									break;
						case 'header': //alert("Div Id is header ") ;
								break;
						case 'userInfo':
								//alert("Div Id is userInfo") ;
									break;
						case 'topnav': //alert("Div Id is topnav ") ;
								break;
						case 'body':
								//alert("Div Id is body") ;
									break;
						case 'footer':
								//alert("Div Id is footer") ;
									break;

						default:
									//alert("hiding div" + coll[i].id);
									var key=coll[i].id ;
									if (divElement.style.visibility != "hidden")  {
										oldDivWidth.put(key,divElement.style.width);
										oldDivHeight.put(key,divElement.style.height);
										divElement.style.visibility = "hidden";	
										divElement.style.height = zeroPx;
										divElement.style.width = zeroPx;
										divElement.style.position = "relative";
										divElement.style.top = zeroPx;

									} else {
										//oldDivHeight.put(key,divElement.style.height);
										divElement.style.visibility = "visible";	
										divElement.style.height = oldDivHeight.get(key);
										divElement.style.width =oldDivWidth.get(key);
										divElement.style.position = "relative";
										divElement.style.top = zeroPx;

									}
									break;
				}// switch
				
				
			}
		}catch(e) {
			alert("Javascript Exception in For Loop"+signature + e);
			
		}
	}
	try {
			//alert("divIdToresize=" +divIdToresize);
			divElement=document.getElementById(divIdToresize) ;
		//	alert("width "+ divElement.style.width + "and height " + divElement.style.height + "of " +divIdToresize);
			// if width and height is more then 800 px then resize it to default.
			var existingWidth=getDivSizes(divElement.style.width);
			var existingHeight=getDivSizes(divElement.style.height);
			if (existingWidth == screen_width && existingHeight == screen_height ) {
				divElement.style.visibility = "visible";	
				divElement.style.height = oldDivHeight.get(divIdToresize);
				divElement.style.width = oldDivWidth.get(divIdToresize);
				divElement.style.position = "relative";
				divElement.style.top = zeroPx;

			}else {
				oldDivWidth.put(divIdToresize,divElement.style.width);
				oldDivHeight.put(divIdToresize,divElement.style.height);
				divElement.style.width= screen_width + 'px';
				divElement.style.height= screen_height + 'px';
				divElement.style.visibility = "visible";
				divElement.style.position = "absolute";
				divElement.style.top = zeroPx;
			}
	}catch(e) {
		alert("Javascript Exception in "+signature + e);
	}
}
// This Function returns size and input as 
function getDivSizes(size) {
	var signature="getDivSizes(size)";
	var strSize="";
	try {
	//alert("oldSize" + size);
	//alert("indexOfP" + size.indexOf("p"));
	strSize=size.substring(0,size.indexOf("p"));
	//alert("Size " +strSize);
	}catch(e) {
		alert("Javascript Exception in "+signature + e);
	}
	return strSize;
}
function resizeBody() {
document.getElementById('main_table').style.width=document.body.clientWidth;
}
*/


var maxDiv = null;
var origDiv = null;

function resizePortlet(portletDiv) {
	if (origDiv != null) {
		min();
		origDiv = null;
	}
	else {
		var portlet = document.getElementById(portletDiv);
		var container = document.getElementById("container");
		max(portlet, container);
	}
}

function max(portlet, container) {
	if (maxDiv == null) {
		maxDiv = container.cloneNode(false);
		maxDiv.style.display = "none";
	}
	origDiv = container;
	
	var portletCopy = null;
	if (portlet.id == "visits") {
		// cloneNode does something strange to the visits panel
		// so we'll do it this way
		portletCopy = document.createElement(portlet.tagName);
		portletCopy.innerHTML = portlet.innerHTML;
	}
	else {
		portletCopy = portlet.cloneNode(true);
	}
	
	// find first table and set width=100%
	portletCopy.getElementsByTagName("table")[0].style.width = "100%";
	var innerDiv = null;
	var divList = portletCopy.getElementsByTagName("div");
	for (var i = 0; innerDiv == null && i < divList.length; i++) {
		if (divList[i].id = "inner_" + portlet.id)
			innerDiv = divList[i];
	}
	if (innerDiv != null) {
		innerDiv.style.width = "100%";
		innerDiv.style.overflow = "auto";
		var innerTables = innerDiv.getElementsByTagName("table");
		for (var i = 0; i < innerTables.length; i++)
			innerTables[i].style.width = "100%";
	}
	else {
		//alert("no inner div");
		return;
	}
	
	if (maxDiv.hasChildNodes())
		maxDiv.replaceChild(portletCopy, maxDiv.firstChild);
	else
		maxDiv.appendChild(portletCopy);
	maxDiv.style.display = "";
	
	// calculate appropriate height for the containerDiv
	// calculate container offset
	var currentDiv = container;
	var containerTop = 0;
	while (currentDiv != null) {
		containerTop += currentDiv.offsetTop;
		currentDiv = currentDiv.offsetParent;
	}
	
	// calculate inner div offset from portlet div
	var innerDivOffset = 0;
	currentDiv = document.getElementById("inner_" + portlet.id);
	while (currentDiv != null && currentDiv.id != portlet.id) {
		if (currentDiv.tagName == "DIV" ||
				currentDiv.tagName == "TABLE" ||
				currentDiv.tagName == "TD") {
			//alert(currentDiv.tagName + ", " + currentDiv.id + currentDiv.offsetTop);
			innerDivOffset += currentDiv.offsetTop;
		}
		currentDiv = currentDiv.parentNode;
	}

	var height = document.body.clientHeight-containerTop-innerDivOffset+document.body.scrollTop;
	// padding
	height -= 2;
	
	// check minimum height
	if (height < 150)
		height = 150;

	if (containerTop-document.body.scrollTop < 0) {
		height -= (containerTop-document.body.scrollTop);
		window.scrollTo(0, containerTop);
	}
	innerDiv.style.height = height;
	container.parentNode.replaceChild(maxDiv, container);
}

function min() {
	maxDiv.parentNode.replaceChild(origDiv, maxDiv);
}

function showHideNav() {
	//alert(document.getElementById('visits').offsetWidth);
	var leftNavTable = document.getElementById("left_nav_tbl");
	if (leftNavTable.style.display == "none") {
		leftNavTable.style.display = "";
		document.getElementById("leftNavMinMax").innerHTML = "&laquo;";
		document.getElementById("leftNavCell").style.width = "160px";
	}
	else {
		leftNavTable.style.display = "none";
		document.getElementById("leftNavMinMax").innerHTML = "&#187;";
		document.getElementById("leftNavCell").style.width = "0px";
	}
}
