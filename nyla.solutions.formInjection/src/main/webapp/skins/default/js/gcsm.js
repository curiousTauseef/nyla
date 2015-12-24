function setBackwebRole() {
   	if (window.BWPortalPlugin) {
   		try {
	   		var form = document.userPanelForm;
	   		var roleName = form.roleId.options[form.roleId.selectedIndex].value;
	   		window.BWPortalPlugin.cookie="currentRole=" + roleName + " ; path=/";
	   		//alert(window.BWPortalPlugin.cookie);
   		}
   		catch (err) {
   			alert("setBackwebRole(): " + err.msg);
   		}
   	}
}

function selectBackwebCurrentRole() {
   	if (window.BWPortalPlugin && document.userPanelForm) {
   		var cookie = window.BWPortalPlugin.cookie;
   		//alert(cookie);
   		if (cookie != null) {
   			var currentRole = cookie.split("=")[1];
   			//alert(currentRole);
   			if (typeof(currentRole) == undefined || currentRole == null) {
   				currentRole = document.userPanelForm.roleId.value;
   				setBackwebRole(currentRole);
   			}
   			document.userPanelForm.roleId.value = currentRole;
   		}
   	}
}

/*
	parses a date in dd-MMM-yyyy [hh:mm] format
*/
var _monthArray = ["JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"];
function parseDate(datestr) {
	datestr = trim(datestr);
	//alert("[" + datestr + "]");
	if (datestr.length < 11) {
		return new Date(1980, 1, 1, 0, 0);
	}
	var tokens = datestr.split("-");
	var day = unpad(tokens[0], "0");
	var monthStr = tokens[1].toUpperCase();
	//alert(monthStr);
	var yr = tokens[2];
	var hr = 0;
	var min = 0;
	if (yr.indexOf(":") != -1) {
		var yrhrmin = yr.split(" ");
		var hrmin = yrhrmin[1].split(":");
		yr = trim(yrhrmin[0]);
		hr = unpad(hrmin[0], "0");
		min = unpad(hrmin[1], "0");
	}
	var month = -1;
	for (var i = 0; month == -1 && i < _monthArray.length; i++) {
		//alert(_monthArray[i]);
		if (_monthArray[i] == monthStr) {
			month = i;
		}
	}
	//alert("new Date(" + yr + ", " + month + ", " + day + ", " + hr + ", " + min + ")");
	return new Date(yr, month, day, hr, min);
}

function trim(str) {
	//alert("trim " + str);
	if (!str || str == null)
		return null;
	str = str.replace(/^\s*/g, '');
	str = str.replace(/\s*$/g, '');
	return str;
}

function unpad(str, padchar) {
	//alert("unpad " + str);
	if (padchar == null)
		padchar = "0";
	var pattern = "/^" + padchar + "*/g";
	var regex = new RegExp();
	str = str.replace(regex, '');
	return str;
}

function numberSort(a, b) {
	return a-b;
}

function getColText(row, colIndex, colType) {
	var cells = row.getElementsByTagName("td");
	var col = cells[colIndex] ? cells[colIndex].innerText : null;
	if (col != null)
		col = trim(col);
	if (col != null && (col.charCodeAt(0) == 8211 || col.indexOf("No Data Found") != -1))
		col = null;
	//alert(col);

	/*
	var col = null;
	
	var currentCol = cells[colIndex];
	var txtNode = findTextNode(currentCol);
	if (txtNode != null)
		col = txtNode.nodeValue;
	//alert(col);
	*/
	
	var val = null;
	if (colType == "date") {
		if (!col || col == null)
			val = new Number(-1);
		else
			val = new Number(parseDate(col).getTime());
		//alert(val);
		Number.prototype.row = null;
	}
	else if (colType == "number") {
		if (!col || col == null)
			val = new Number(-1);
		else
			val = new Number(parseFloat(col));
		Number.prototype.row = null;
	}
	else {
		if (!col || col == null)
			val = new String("");
		else
			val = new String(col.toLowerCase());
		String.prototype.row = null;
	}
	return val;
}

function sortBy(tableId, colIndex, colType) {
	var startTime = new Date().getTime();
	var table = document.getElementById(tableId);
	//alert(table.sortCol);
	//alert(table.sortCol);
	if (table.sortCol == colIndex)
		return;
	else
		table.sortCol = colIndex;
	//alert("sorting col by colIndex " + colIndex);
	var tbody = table.getElementsByTagName("tbody")[0];
	var rows = tbody.getElementsByTagName("tr");
	if (!rows || rows.length < 1)
		return;
	//alert(rows.length + ", " + rows[rows.length-1]);
	var colText = new Array(rows.length);
	//var rowArray = new Array(rows.length);
	var startPrep = new Date().getTime();
	for (var i = 0; i < rows.length; i++) {
		colText[i] = getColText(rows[i], colIndex, colType);
		//if (i == rows.length-1)
		//	alert("[" + trim(colText[i]) + "], charCode=" + colText[i].charCodeAt(0));
		/*
		if (colText[i] != null) {
			colText[i] = new String(colText[i].link(""));
			String.prototype.row = null;
		}
		*/
		colText[i].row = rows[i];
		//alert(colText[i].row);
		//rowArray[i] = rows[i];
	}
	var endPrep = new Date().getTime();
	//alert("time to prep: " + (endPrep-startPrep));
	
	var startSort = new Date().getTime();
	if (colType == "number" || colType == "date")
		colText.sort(numberSort);
	else
		colText.sort();
	var endSort = new Date().getTime();
	//alert("time to sort: " + (endSort-startSort));
	
	var startReorder = new Date().getTime();
	var newTBody = document.createElement("tbody");
	for (var i = 0; i < colText.length; i++) {
		//alert(colText[i].row);
		newTBody.appendChild(colText[i].row);
	}
	var endReorder = new Date().getTime();
	//alert("time to re-order: " + (endReorder-startReorder));
	
	var startDOM = new Date().getTime();
	table.replaceChild(newTBody, tbody);
	//alert("replacing tbody: " + newTBody + ", " + tbody + ", table=" + table);
	var endDOM = new Date().getTime();
	//alert("time to change DOM: " + (endDOM-startDOM));

	var endTime = new Date().getTime();
	//alert("time to sort and re-order: " + (endTime-startTime));
}

function submitUserPanelForm(form) 
{
	try {
		var formName=form.name;
		var isbackWeb=document.userPanelForm.isBackweb.value;
		if (isbackWeb == "false") {
			form.submit();
		}
		else if (formName == "userPanelForm") {
			window.onunload = setBackwebRole;
			form.submit();
		}
 	}
	catch (err) {
		// happens on form pages when clicking 'cancel' on prompt
	}
}

function processFilters(form, box, table, colIndexes) {
	var filters = box.split(",");
	var filterValue = new Array();
	for (var i=0; i < filters.length; i++) {
		var useValue = false;
		var dotIndex = filters[i].indexOf('.');
		if (dotIndex != -1) {
			if (filters[i].substring(dotIndex+1) == 'value')
				useValue = true;
			filters[i] = filters[i].substring(0, dotIndex);
		}
		filters[i] = document.forms[form.name].elements[filters[i]];
		if (filters[i]) {
			if (useValue)
				filterValue[i] = filters[i].options[filters[i].selectedIndex].value;
			else
				filterValue[i] = filters[i].options[filters[i].selectedIndex].text;
		}
		else
			filterValue[i] = "All";
		//alert(filterValue[i]);
	}
	
	var table = document.getElementById(table);
	var startIndex = 1;
	var tbodies = table.getElementsByTagName("tbody");
	if (tbodies != null && tbodies.length > 0) {
		table = tbodies[0];
		startIndex = 0;
	}
	var tableItem = table.getElementsByTagName("tr");
	//alert("processFilters()");
	//alert("filterValues: " + filterValue);
	var cols = colIndexes ? colIndexes.split(',') : null;
	for (var i=startIndex; i < tableItem.length; i++) {
		//alert(tableItem[i].innerHTML);
		var found = true;
		for (var j=0; j < filterValue.length && found; j++) {
			//if (i == 1)
				//alert("filtering by: " + filterValue[j]);
			if (!colIndexes) {
				var rowHTML = tableItem[i].innerHTML;
	        	if (filterValue[j] == "All" || rowHTML.indexOf(filterValue[j]) != -1) { 
	        		//if (filterValue[j] == "All")
	        			//alert("filterValue[j] == 'All'");
	            	found = true;
	            	//alert("found: " + tableItem[i].innerHTML.match(new RegExp(filterValue[j])));
	            }
	            else
	            	found = false;
            }
            else {
            	var col = tableItem[i].getElementsByTagName("td")[cols[j]];
            	var colText = col ? trim(col.innerText) : null;
            	//if (i == 0) {
            		//alert("filtering by " + filterValue[j] + ", colIndex=" + j + ", colText=" + colText);
            	//}
	        	if (filterValue[j] == "All" || colText == filterValue[j] || (filterValue[j] == "None" && colText.charCodeAt(0) == 8211)) { 
	            	found = true;
	            }
	            else
	            	found = false;
            }
		}

		if (found) {
			tableItem[i].className = "visible"; // for actiwate
			tableItem[i].style.display="block";
		}
		else {
			tableItem[i].className = "hidden";	// for actiwate
			tableItem[i].style.display="none";
		}
	}
}
