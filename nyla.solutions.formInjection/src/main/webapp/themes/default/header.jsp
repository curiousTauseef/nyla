<%-- header.jsp
     Description: This file displays the header for all the site pages.
     It contains the logo, the link for help and logout functionality.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<bean:define id="bw" name="backweb" scope="application" />
<script language="JavaScript" type="text/javascript">
<!--
var isApplicationInstalled = false;

function openNew(url, plugin) {

	var PASClientID = "";
	try {
		//PASClientID = plugin.GetUserVariableEx( "<bean:write name="bw" property="channelAddress"/>","PASClientId", "" );
		PASClientID = plugin.GetUserVariableEx( "<bean:write name="bw" property="channelAddress"/>","PASClientId","PASClientId");
	} catch (err) {
		// got an error
		PASClientID = "";
		//alert(err.message);
	}

	if (PASClientID == "PASClientId")
		PASClientID = "";
	url += "?PASClientID="+PASClientID+"&method=view";

	window.open(url, 'myWin', 'toolbar=no, directories=no, location=no, status=yes, menubar=no, resizable=no, scrollbars=no, width=300, height=200');
}

// -->
</script>
<%--
function detectPlugin(plugin) {

	var checkInt = 0;
	try {
		checkInt = plugin.IsInstalled();
	} catch (err) {
		// Application is not installed but do
		// not want to allow user to click the link
		checkInt = 0;
	}

	if (checkInt == 0) {
		isApplicationInstalled = false;
	}
	else {
		isApplicationInstalled = true;
	}

}

var tid;

function checkInstalled(plugin) {

	//alert("Calling checkInstalled()");
	if (plugin.IsInstalled() == 0 || plugin.IsInstalled() == 2) {
		return false;
	} else {
		debugger;
		alert("calling registation code");
		<!-- Register the channel -->
		plugin.AddChannel(
		    "<bean:write name="bw" property="serverName"/>",
		    "<bean:write name="bw" property="serverAddress"/>",
		    "",
		    "",
		    "",
		    "",
		    "[channel]\nmanual_join=1\nmerge_user_profile=1\nonly_if_in_directory=1");

		alert("After AddChannel");
		<!-- Set up the proper pasClientID for the portal -->
		plugin.SetUserVariableEx( "<bean:write name="bw" property="channelAddress"/>","PASClientId", "<bean:write name="bw" property="userIDforPP"/>", "SR" );
		alert("After PASClientId");
		<!-- Set up the proper channel for the portal -->
		plugin.SetUserVariableEx( "<bean:write name="bw" property="channelAddress"/>","BWPortalContentChannel", "1", "" );
		alert("After BWPortalContentChannel");
		<!-- Initiate content acquisition for the user -->
		plugin.SetUserVariableEx( "<bean:write name="bw" property="channelAddress"/>","SyncState", "1", "SR" );
		alert("After SyncState");

		clearInterval(tid);
		alert("After clearInterval");
		detectPlugin(plugin);
		alert("After detect");

	}
}

function installIt(plugin) {

		var msg = "Press OK to begin the Backweb plugin installation.";
		if (confirm(msg)) {
			try {
				plugin.InstallEx("<bean:write name="bw" property="userID"/>");

//				var isInstalled = plugin.IsInstalled();
//				while (isInstalled == 0 || isInstalled == 2) {
//					isInstalled = plugin.IsInstalled();
//				}

				tid = setInterval('checkInstalled(instObj)', 1000);

			} catch (err) {
				alert(err);
				// Leave site the way it is if this fails
			}
		}
}

--%>
<OTML:REMOVE>
<c:choose>
<c:when test='${sessionScope.user.canOffline == "Y"}'>
  <OBJECT ID="instObj" CLASSID="CLSID:<bean:write name="bw" property="guid"/>"
      CODEBASE="<bean:write name="bw" property="codeBase"/>" width="0" height="0">
  </OBJECT>
</c:when>
<c:otherwise>
<script>var instObj = null;</script>
</c:otherwise>
</c:choose>
</OTML:REMOVE>

<table border="0" width="100%"  height="59"  cellspacing="0" cellpadding="0"  class="header">
  <tr>
    <td width="13%" align="left">
	<html:img border="0" page="/skins/default/images/logo_clinsite_old.gif" width="117" height="57"/>
    </td>
    <td>&nbsp;</td>
    <td width="55%" align="right">
      <OTML:REMOVE>
	<span id="backweb"><a href="#" onclick="javascript:openNew('/gcsm/do/OfflineAccessAction', instObj);return false;" class="headerLink">Offline Access Manager</a> |</span>
      </OTML:REMOVE>
      <a href="#" onclick="window.open('/gcsm/help/whskin_homepage.htm', 'myWin', 'toolbar=no, directories=no, location=no, status=yes, menubar=no, resizable=yes, scrollbars=yes, width=910, height=600');return false;" class="headerLink">Help</a>
      <OTML:REMOVE>
	 | <a href="LogoutAction" id="LogoutLink" class="headerLink">Log Out</a>
      </OTML:REMOVE>
      &nbsp;&nbsp;
    </td>
  </tr>
</table>



