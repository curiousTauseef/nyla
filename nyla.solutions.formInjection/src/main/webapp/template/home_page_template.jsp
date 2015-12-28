<%--
  User: Dmane
  Date: Jun 1, 2005
  Time: 1:35:21 PM
  Comments:Index.jsp redirects page to home page
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://gcsm.bms.com/tld/security.tld" prefix="security"%>
<%--<%@ taglib uri="http://jakarta.apache.org/taglibs/request-1.0" prefix="req" %>--%>

<c:remove var="siteID" scope="session"/>
<c:remove var="uid" scope="session"/>

<html>
	    <%-- ===========================================================  --%>
	    <%--    PAGE HEADER                                               --%>
	    <%-- ===========================================================  --%>
	    <head>
	    <script src="<html:rewrite page='/skins/default/js/gcsm.js'/>" ></script>
	    <link rel="stylesheet" href="<html:rewrite page="/skins/default/css/clinsight.css"/>" type="text/css">
		  <title><tiles:getAsString name="title" /></title>
		  <script src="<html:rewrite page='/skins/default/js/hashtable.js'/>"> </script>
		  <script src="<html:rewrite page='/skins/default/js/div_functions.js'/>"></script>
	    </head>
	    <body topmargin="0%" leftmargin="10%" rightmargin="10%" id="main_body">

		<table  width="100%" id="main_table" align="center" valign="top" cellspacing="0" border="0" cellpadding="0" style="border:solid 5px #074A87">
		    <%-- ===========================================================  --%>
		    <%--    HEADER PORTLET                                            --%>
		    <%-- ===========================================================  --%>
		    <tr>
			<td align="left" colspan="3" class="header">
			    <div id="header">
			    <tiles:insert flush="false" attribute="header" />
			    </div>
			</td>
		    </tr>
		    <%-- ===========================================================  --%>
		    <%--    USER_INFO PORTLET                                         --%>
		    <%-- ===========================================================  --%>
		    <tr>
			<td align="left" colspan="3">
			    <div id="userInfo">
			       <tiles:insert flush="false" attribute="userInfo" />
			    </div>
			</td>
		    </tr>
		    <%-- ===========================================================  --%>
		    <%--    TOP_NAV PORTLET                                           --%>
		    <%-- ===========================================================  --%>
		    <tr>
			<td width="3">&nbsp;</td>
			<td align="left">
			    <div id="topnav" style="border-bottom:5px solid #CCCCFF">
			    	<% request.setAttribute("landingPage", "home"); %>
			      <tiles:insert flush="false" attribute="topnav" />
			    </div>
		       </td>
		       <td width="3">&nbsp;</td>
		    </tr>
		    <%-- ===========================================================  --%>
		    <%--    MAIN BODY PORTLET                                         --%>
		    <%-- ===========================================================  --%>
		    <tr>
			<td width="3">&nbsp;</td>
			<td align="left" valign="top">
			    <table width="100%" cellpadding="0" cellspacing="0" border="0">
			    <tr height="5"><td colspan="3">&nbsp;</td></tr>
			    <tr>
				<td align="left" width="3">&nbsp;</td>
				<td align="left" valign="top">
				<div align="container" id="container" style="position: relative; width:100%; overflow: none;">
				     <table width="100%" cellpadding="1" cellspacing="1" border="0">
					<tr>
			    <security:guard permission="DispalySitesVisitsPanel">
				<td width="55%" valign="top" align="left"><div align="center" id="sites">
				     <tiles:insert flush="false" attribute="sites"/>
				     
				</div>
				</td>
				<td colspan="2" width="45%" valign="top" align="left">
				    <div align="center" id="visits" style="overflow: none; width:100%; border: solid 2px #FFFFFF">
					    <tiles:insert flush="false" attribute="visits" />
				    </div>
				</td>
			    </security:guard>
			    <security:guard permission="DisplaySitesPanel">
				<td colspan="3" width="100%" valign="top" align="left"><div align="center" id="sites">
				     <tiles:insert flush="false" attribute="sites"/>
				</div>
				</td>
			     </security:guard>

			    <security:guard permission="DisplayPTMSites">
				<td colspan="3" width="100%" valign="top" align="left"><div align="center" id="sites">
				     <tiles:insert flush="false" attribute="sites"/>
				</div>
				</td>
			     </security:guard>
			    <security:guard permission="DisplayStaffPanel">
				<td colspan="3" width="100%" valign="top" align="left">
				<div align="center" id="staff">
				     <tiles:insert flush="false" attribute="staff"/>
				</div>
				</td>
			     </security:guard>

					</tr>
					<tr>
					<td colspan="3" valign="top" >
					<div align="center" id="issues" style="overflow: none; width:100%; border: solid 2px #FFFFFF">
						    <tiles:insert flush="false" attribute="issues" />
										</div>
					</td>
					</tr>
					<tr>
					<td colspan="3" valign="top" >
					<div align="center" id="alerts" style="position: relative; width:100%; overflow: none;  border: solid 2px #FFFFFF">
						<tiles:insert flush="false" attribute="alerts" />
					</div>
					</td>
					</tr>
				    </table>
				</div>
				</td>
				<td width="3">&nbsp;</td>
			    </tr>
			    </table>
			</td>
			<td width="3">&nbsp;</td>
		    </tr>
		    <%-- ===========================================================  --%>
		    <%--    FOOTER PORTLET                                            --%>
		    <%-- ===========================================================  --%>
		    <tr>
			<td colspan="3">
			   <div align="left" id="footer" style="position: relative; width:100%; overflow: none; border: solid 2px #FFFFFF">
				<tiles:insert flush="false" attribute="footer" />
			   </div>
		       </td>
		   </tr>
		</table>

	    </body>


</html>
