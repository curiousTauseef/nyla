<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

function clearSrch(aForm) {
	aForm.code.value = "";
	aForm.resultList.length = 0;
}

function search(aForm) {
	 //var startTime = new Date().getTime();
     var criteria = aForm.code.value;
     if(criteria == null || criteria.length == 0)
     {
      alert("Search Criteria not provided");
      return false;
     }

     //get Results list
     var resultList = aForm.resultList;
     resultList.options.length = 0;
	 //alert(gcsmUserList.text);
     var regex = new RegExp("^" + criteria, "i");

     for (var i = 0; i < userList.length; i++)
     {
	    if(userList[i].matches(regex))
	    {
		   //add to the list
		   //alert(userList[i].lastName+"::"+replaceEntity(userList[i].lastName));
		   userList[i].lastName = replaceEntity(userList[i].lastName);
		   resultList.options[resultList.length] = createOption(userList[i]);
		//   alert(resultList.options[resultList.length].text);
	    }
     }

     if(resultList.length == 0)
     {
	     resultList.options[resultList.length] = new Option("No data Found", "");
     }
     //var endTime = new Date().getTime();
     //alert("time to js search: " + (endTime-startTime) + " ms");
}

function createOption(user) {
	return new Option(user.lastName + ", " + user.firstName, user.id);
}

function GCSMUser(id, firstName, lastName) {
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.toString = userToString;
	this.name = id;
	this.matches = userMatches;
}

function userToString() {
	return this.id + ": " + this.firstName + " " + this.lastName;
}

function userMatches(regex) {
	return this.lastName.search(regex) != -1;
}

function replaceEntity(str) {
	var newStr = str;
	var entList = new Array();
	entList[0] = "&#39;";
	entList[1] = "'";
	for (i=0;i<entList.length;i=i+2) {
			newStr = newStr.replace(entList[i], entList[i+1]);
	}
	return newStr;
}

var userList = new Array();
<logic:iterate name="userList" id="usr" indexId="i">
	userList[<%= i %>] = new GCSMUser("<bean:write name="usr" property="id"/>","<bean:write name="usr" property="firstName"/>", "<bean:write name="usr" property="lastName"/>");
</logic:iterate>

if (document.all.Search)
	document.all.Search.disabled = false;
