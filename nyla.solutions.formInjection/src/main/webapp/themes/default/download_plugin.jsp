<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
				

<bean:define id="bw" name="backweb" scope="application"/>
<% 	String PASClientID = (String)request.getAttribute("PASClientID");
		String userID = request.getRemoteUser(); 

%>

<html>
<head>

<script language="JavaScript">
<!--
var isApplicationInstalled = false;
var notInstalledStatus = "Plugin is not installed click <a href=\"javascript:installIt(instObj)\">here</a> to install it.";
var notRegisteredStatus = "Plugin is not registered with the server. Click <a href=\"javascript:checkInstalled(instObj)\">here</a> to register it.";
var inProgressStatus = "<i>Installing BackWeb Plug-in. Please wait...</i>";
var syncNowLink = "Click <a href=\"javascript:startSyncNow(deploymentObj)\">here</a> to start synchronization";

var syncInProgress = "<i>Content Synchronization in progress</i>";
var syncAborted = "<i>Synchronization Aborted</i>";
var syncCompletedWithErrors = "<i>Synchronization completed with errors</i>";
var syncError = "<i>Error during synchronization</i>";
var syncCompletedSuccessfully = "<i>Synchronization completed successfully</i>";

function startSyncNow(plugin) {
	//alert(plugin.GetSyncStatus());
	
		//<!-- Initiate content acquisition for the user -->
		//plugin.SetUserVariableEx( "<bean:write name="bw" property="channelAddress"/>","SyncState", "1", "SR" );
	
	if (plugin.GetSyncStatus() != 50)
	 	plugin.StartSync();
	
	tid1 = setInterval('checkSyncStatus(deploymentObj)', 1000);
}

function checkSyncStatus(plugin){
	
	var status = plugin.GetSyncStatus();
	//alert(status);
	//plugin.GetUserVariableEx( "<bean:write name="bw" property="channelAddress"/>","SyncState","100");
	
	switch(status) {
		case -1:
			BWSyncStatus.innerHTML = syncError;
			break;
		case -2:
			BWSyncStatus.innerHTML = syncCompletedWithErrors;
			break;
		case -3:
			BWSyncStatus.innerHTML =  syncAborted;
			break;
		case 50:
			BWSyncStatus.innerHTML = "Synchronization is "+plugin.GetTotalProgress()+"% complete.";
			return false;
			break;
		case 0:
			//BWSyncStatus.innerHTML =  "Synchronization is idle";
			break;
		case 100:
			BWSyncStatus.innerHTML =  "Synchronization is complete";
			break;
		default:
			BWSyncStatus.innerHTML =  "Unexpected status from plugin: " + status;
			break;
	}
	BWSyncStatus.innerHTML =  "Synchronization is complete";
	clearInterval(tid1);
	setTimeout("detectPlugin(instObj)", 2000);
}

function detectPlugin(plugin) {
	BWSyncStatus.innerHTML = "";
	var checkInt = 0;
	try {
		checkInt = plugin.IsInstalled();
	} catch (err) {
		// Application is not installed but do
		// not want to allow user to click the link
		checkInt = 0;
	}

	if (checkInt == 1) {
		isApplicationInstalled = true;
		//BWClientStatus.innerHTML = installComplete;
		if (deploymentObj.GetSyncStatus() == 50) {
			BWClientStatus.innerHTML = "Synchronization in progress.";
			tid1 = setInterval('checkSyncStatus(deploymentObj)', 1000);
		}
		else {
			var lastTime = deploymentObj.LastContentAcquisitionTimeEx();
			if (lastTime == 0)
				BWClientStatus.innerHTML = "Plug-in installation complete.";
			else
				BWClientStatus.innerHTML = "Your last content acquisition was<br>" + new Date(lastTime*1000);
			BWSyncStatus.innerHTML = syncNowLink;
		}
	}
	else if (checkInt == 2) {
		isApplicationInstalled = false;
		BWClientStatus.innerHTML = inProgressStatus;
	} else {
		isApplicationInstalled = false;
		BWClientStatus.innerHTML = notInstalledStatus;
	}

	try {
		var PASClientId = plugin.GetUserVariableEx( "<bean:write name="bw" property="channelAddress"/>","PASClientId","PASClientId");
		//alert(PASClientId + " == <%= PASClientID %> ??");
		if (PASClientId != <%= PASClientID %>) {
			//alert("registering new client");
			checkInstalled(plugin);
		}
		else {
			//alert("here?");
			//alert("last acquisition: " + plugin.LastContentAcquisitionTimeEx());
		}
		if (PASClientId == "PASClientId" && isApplicationInstalled) {
			BWClientStatus.innerHTML = notRegisteredStatus;
		}
	} catch (err) {}
	

}

var tid;
var tid1;

function checkInstalled(plugin) {

	//alert("Calling checkInstalled()");
	if (plugin.IsInstalled() == 0 || plugin.IsInstalled() == 2) {
		return false;
	} else {
		//debugger;
		//alert("calling registation code");
		<!-- Register the channel -->
		plugin.AddChannel3(
                    "<bean:write name="bw" property="serverName"/>",
                    "<bean:write name="bw" property="serverAddress"/>",
                    "[channel]\nmanual_join=1\nmerge_user_profile=1\nonly_if_in_directory=1");
	
		//alert("After AddChannel");
		<!-- Set up the proper pasClientID for the portal -->
		plugin.SetUserVariableEx( "<bean:write name="bw" property="channelAddress"/>","PASClientId", "<%=PASClientID%>", "SR" );
		//alert("After PASClientId");
		<!-- Set up the proper channel for the portal -->
		plugin.SetUserVariableEx( "<bean:write name="bw" property="channelAddress"/>","BWPortalContentChannel", "1", "" );
		//alert("After BWPortalContentChannel");
		<!-- Initiate content acquisition for the user -->
		plugin.SetUserVariableEx( "<bean:write name="bw" property="channelAddress"/>","SyncState", "1", "SR" );
		//alert("After SyncState");

		clearInterval(tid);
		//alert("After clearInterval");
		detectPlugin(plugin);
		//alert("After detect");
	
	}
}

function installIt(plugin) {

		var msg = "Press OK to begin the Backweb plugin installation.";
		if (confirm(msg)) {
			try {
				plugin.InstallEx("<%=userID%>");
				
//				var isInstalled = plugin.IsInstalled();
//				while (isInstalled == 0 || isInstalled == 2) {
//					isInstalled = plugin.IsInstalled();
//				}
				
				tid = setInterval('checkInstalled(instObj)', 1000);

			} catch (err) {
				//alert(err);
				// Leave site the way it is if this fails
			}
		}
}


// -->
</script>
<!-- In order to be notified when the synchronization is complete: -->
<script FOR=instObj EVENT=SyncStatus(EventStatus) DEFER>
    //alert(EventStatus);
    checkSyncStatus(EventStatus);
</script>
<style>
	body {
		font-family: Verdana;
		font-size: 8pt;
	}
	a:hover, a:visited
	{
		font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;
		color: #666666;
		text-decoration: underline;
		cursor: hand;
	}
	
	a
	{
		font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;
		color: #999999;
		font-weight: normal;
	}
	
</style>

</head>

<body>

<c:choose>
<c:when test='${requestScope.PASClientID == ""}'>
	You are not activated for offline use. Please contact your system administrator to be activated.
</c:when>	
<c:otherwise>
	<OBJECT ID="instObj" CLASSID="CLSID:<bean:write name="bw" property="guid"/>"
		CODEBASE="<bean:write name="bw" property="codeBase"/>" width="0" height="0">
	</OBJECT>
	<OBJECT ID="deploymentObj" CLASSID="CLSID:<bean:write name="bw" property="deploymentGuid"/>"
		CODEBASE="<bean:write name="bw" property="codeBase"/>" width="0" height="0">
	</OBJECT>
	
		<br><span id="BWClientStatus"></span>
		<br><br><span id="BWSyncStatus"></span>
		
		<script language="JavaScript">detectPlugin(instObj);</script>
</c:otherwise>
</c:choose>

</body>
</html>
	
	