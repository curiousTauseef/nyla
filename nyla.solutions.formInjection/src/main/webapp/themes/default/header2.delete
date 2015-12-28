<%-- header.jsp
	Description: This file displays the header for all the site pages. It contains the logo, the 	
	                 link for help and logout functionality. 
	Versions: 
				1.0 - Original created by DMane
				1.1 - Links to HELP and LOGOUT were added and CSS was modified 
--%>
<%@ taglib uri="http://myfaces.sourceforge.net/tld/myfaces_ext_0_9.tld" prefix="x"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ page import="com.backweb.proactiveportal.*, 
				java.util.*,
				com.bms.informatics.gcsm.common.util.Config" %>
<%

	String codeBase = "";
	String guid = "";
	String channelAddress = "";
	String userIdforPP = "";
	String userID = request.getRemoteUser();
	
	boolean isWorking = true;
	try {
	Properties p = new Properties();
	p.setProperty("com.backweb.proactiveportal.pasRootUrl", Config.getProperty("com.backweb.proactiveportal.pasRootUrl"));
	p.setProperty("com.backweb.proactiveportal.pasUsername", Config.getProperty("com.backweb.proactiveportal.pasUsername"));
	p.setProperty("com.backweb.proactiveportal.pasPassword", Config.getProperty("com.backweb.proactiveportal.pasPassword"));

	ProactivePortal.init("instance", p);
	ProactivePortal pp = ProactivePortal.instance("instance");
	
	ClientConfiguration cc = pp.getClientConfiguration();
	
	codeBase = cc.getWebPackageCodeBase();
	guid = cc.getWebPackageGUID();
	channelAddress = pp.getContentServerAddress();
	userIdforPP = null;
	
	
	User ppUser = pp.getUser(userID);
	
	if (ppUser == null) {
		userIdforPP = pp.registerClient(userID, true, 
		Config.getProperty("gcsm.homePage"), "");
		ppUser = pp.getUser(userID);
		ppUser.triggerContentAcquisition(1);
	}
	} catch (Exception e) {
		isWorking = false;

		e.printStackTrace();
	}

%>
<script language="JavaScript">
<!--
var isApplicationInstalled = false;

function openNew() {

	window.open('/gcsm/themes/default/download_plugin.jsp', 'myWin', 'toolbar=no, directories=no, location=no, status=yes, menubar=no, resizable=no, scrollbars=no, width=300, height=200');
}

function detectPlugin(plugin) {
	
	var checkInt = 0;
	try {
		checkInt = plugin.IsInstalled();
	} catch (err) {
		checkInt = 0;
	}
	
	if (checkInt == 0) {
		isApplicationInstalled = false;
	}
	else {
		isApplicationInstalled = true;
	}
		
}

function installIt(plugin) {

		var msg = "Press OK to begin the Backweb plugin installation.";
		if (confirm(msg)) {
			plugin.InstallEx("<%=userID%>");
			plugin.SetUserVariableEx( "<%=channelAddress%>","PASClientId", "<%=userIdforPP%>", "SR" );
		<!-- Set up the proper channel for the portal -->
			plugin.SetUserVariableEx( "<%=channelAddress%>","BWPortalContentChannel", "1", "" );
		<!-- Initiate content acquisition for the user -->
			plugin.SetUserVariableEx( "<%=channelAddress%>","SyncState", "1", "SR" );
			
			backweb.innerHTML = "";
		}
}
// -->
</script>

<f:subview id="header">
	<% if (isWorking) { %>
	<OBJECT ID="instObj" CLASSID="CLSID:<%=guid%>"
		CODEBASE="http://A122758.HPW.PRI.BMS.COM:8080/PluginInstallers/Initial Configuration/7.2.0.147/Lite/Offline localhost Setup.cab" width="0" height="0">
	</OBJECT>
	<% } %>
	
		<table border="0" width="100%"  height="59"  cellspacing="0" cellpadding="0"  class="header">
	        <tr>
	               <td width="13%" align="left">
	                    <img border="0" src="/gcsm/skins/default/images/logo_clinsite_old.gif" width="117" height="57">
	                </td>
	                <td>&nbsp;</td>
	                <td width="55%" align="right" class="headerLink">
	                	<span id="backweb"><a href="javascript:installIt(instObj)" class="headerLink">download backweb plugin</a> |</span> <a href="#" class="headerLink">help</a> | <a href="#" class="headerLink">log out</a>&nbsp;&nbsp;&nbsp;
	                </td>
	        </tr>
		</table>


<script language="JavaScript">
<!--
	detectPlugin(instObj);
	//alert(isApplicationInstalled);
	if (isApplicationInstalled) 
		backweb.innerHTML = "";
// -->
</script>

</f:subview>
