<%-- topnav.jsp
	Description: This file displays the main navigation page for GCSM.
	Versions:
				1.0 - Original created by DMane
				1.1 - Fixed the resize problem - OLakra

	Note : There is no need to load resourse bundle as it is already loaded into template
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%
    //String staffUrl="<html:rewrite page='/SitePanelAction?method=doFilter&landingPage=staff'/>";
    String contextPath=request.getContextPath();
    String staffUrl=contextPath+"/do/SitePanelAction?method=doFilter&landingPage=staff";
    String uid="";
    String siteId="";
    String uidUrl="";
    String siteIdUrl="";
    String role="";
    String roleUrl="";
    String landingPage = request.getParameter("landingPage");
    if (landingPage == null || landingPage.equals(""))
    	landingPage = (String) pageContext.findAttribute("landingPage");
    if (landingPage == null)
    	landingPage = "";
    uid=request.getParameter("uid");
    role=request.getParameter("role");
    if (!landingPage.equals("home"))
    	siteId=session.getAttribute("siteID") != null ? String.valueOf(session.getAttribute("siteID")) : "";

    if (uid != null) {
       uidUrl = "&uid="+uid;
    } else {
	uidUrl="";
    }
    if (siteId != null) {
       siteIdUrl = "&siteId="+siteId;
    } else {
	siteIdUrl = "";
    }
    if (role != null) {
       roleUrl = "&role="+role;
    } else {
	roleUrl = "";
    }
    staffUrl=staffUrl+ uidUrl + siteIdUrl + roleUrl;
%>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <%-- Empty Row --%>
    <tr><td width="100%" colspan="2" height="2">&nbsp;</td></tr>
    <%-- Navigator Container --%>
    <tr>
	<td height="30"> <%-- Navigation Container --%>
	    <table border="0" cellpadding="0" cellspacing="0" id="top_nav_tbl">
		<tr>
		<td height="30" width="5">&nbsp;</td>
	    <%-- Spacer cell between 2 tabs --%>
		<%-- BEGIN HOME Tab --%>
		    <c:choose>
			<c:when test='${landingPage == "home" || param.landingPage == "home"}'>
				<td align="right" width="12"><html:img  border="0" page="/skins/default/images/gtab_left_on.gif"/></td>
				<td class="topNavFontOn" width="32" align="center" valign="middle">
					    <bean:message key="gcsm.top.navigation.home"/>

				</td>
				<td align="left" width="12"><html:img  border="0" page="/skins/default/images/gtab_rt_on.gif"/></td>
			</c:when>
			<c:otherwise>
				<td align="right" width="12"><html:img  border="0" page="/skins/default/images/tab_left_off.gif"/></td>

				<td class="topNavBgOff" width="32" align="center" valign="middle">

				  <a href='<html:rewrite page="/do/init"/>' class="topNavOff"><bean:message key="gcsm.top.navigation.home"/></a>


				</td>
				<td align="left" width="12"><html:img  border="0" page="/skins/default/images/tab_rt_off.gif"/></td>
			</c:otherwise>
		    </c:choose>
		    
		    <c:choose>
			<c:when test='${landingPage == "smp" || param.landingPage == "smp"}'>
				<td align="right" width="12"><html:img  border="0" page="/skins/default/images/gtab_left_on.gif"/></td>
				<td class="topNavFontOn" width="180" align="center" valign="middle">
					   <bean:message key="gcsm.top.navigation.smp"/>

				</td>
				<td align="left" width="12"><html:img  border="0" page="/skins/default/images/gtab_rt_on.gif"/></td>
			</c:when>
			<c:otherwise>
				<td align="right" width="12"><html:img  border="0" page="/skins/default/images/tab_left_off.gif"/></td>
				<td class="topNavBgOff" width="180" align="center" valign="middle">
				     <a href='<html:rewrite page="/do/SMPListAction?method=list&landingPage=smp"/>' class="topNavOff">
					    <bean:message key="gcsm.top.navigation.smp"/></a>
				</td>
				<td align="left" width="12"><html:img  border="0" page="/skins/default/images/tab_rt_off.gif"/></td>
			</c:otherwise>
		    </c:choose>
		    <c:choose>

			<c:when test='${landingPage == "psi" || param.landingPage == "psi"}'>
				<td align="right" width="12"><html:img  border="0" page="/skins/default/images/gtab_left_on.gif"/></td>
				<td class="topNavFontOn" width="180" align="center" valign="middle">
					   <bean:message key="gcsm.top.navigation.psi"/>

				</td>
				<td align="left" width="12"><html:img  border="0" page="/skins/default/images/gtab_rt_on.gif"/></td>
			</c:when>
			<c:otherwise>
				<td align="right" width="12"><html:img  border="0" page="/skins/default/images/tab_left_off.gif"/></td>
				<td class="topNavBgOff" width="180" align="center" valign="middle">
				     <a href='<html:rewrite page="/do/ResearchSitePanelAction?method=init&landingPage=psi"/>' class="topNavOff">
					    <bean:message key="gcsm.top.navigation.psi"/></a>
				</td>
				<td align="left" width="12"><html:img  border="0" page="/skins/default/images/tab_rt_off.gif"/></td>
			</c:otherwise>
		    </c:choose>
		    <c:choose>
			<c:when test='${landingPage == "ssc" || param.landingPage == "ssc"}'>
				<td align="right" width="12"><html:img  border="0" page="/skins/default/images/gtab_left_on.gif"/></td>
				<td class="topNavFontOn" width="100" align="center" valign="middle">
					<bean:message key="gcsm.top.navigation.ssc"/>
				</td>
				<td align="left" width="12"><html:img  border="0" page="/skins/default/images/gtab_rt_on.gif"/></td>
			</c:when>
			<c:otherwise>
				<td align="right" width="12"><html:img  border="0" page="/skins/default/images/tab_left_off.gif"/></td>
				<td class="topNavBgOff" width="100" align="center" valign="middle">
				    <a href='<html:rewrite page="/do/MyFormPanelAction?landingPage=ssc&method=initMyForm"/>' class="topNavOff">
				    <bean:message key="gcsm.top.navigation.ssc"/></a>
				</td>
				<td align="left" width="12"><html:img  border="0" page="/skins/default/images/tab_rt_off.gif"/></td>
			</c:otherwise>
		    </c:choose>
		    <c:choose>
			<c:when test='${landingPage == "reports" || param.landingPage == "reports"}'>
				<td align="right" width="12"><html:img  border="0" page="/skins/default/images/gtab_left_on.gif"/></td>
				<td class="topNavFontOn" width="42" align="center" valign="middle">
				 <bean:message key="gcsm.top.navigation.reports"/>

				</td>
				<td align="left" width="12"><html:img  border="0" page="/skins/default/images/gtab_rt_on.gif"/></td>
			</c:when>
			<c:otherwise>
				<td align="right" width="12"><html:img  border="0" page="/skins/default/images/tab_left_off.gif"/></td>
				<td class="topNavBgOff" width="42" align="center" valign="middle">
				    <a href='<html:rewrite page="/do/reports?landingPage=reports"/>' class="topNavOff">
				    <bean:message key="gcsm.top.navigation.reports"/></a>
				</td>
				<td align="left" width="12"><html:img  border="0" page="/skins/default/images/tab_rt_off.gif"/></td>
			</c:otherwise>
		    </c:choose>

		 <%-- END REPORTS Tab --%>
		 <%-- BEGIN STAFF PANEL--%>
<%--                                         <c:if test='${param.landingPage == "staff" || param.landingPage == "staffSite"}'>--%>
	<c:choose>
		<c:when test='${!empty uid || !empty param.uid}'>
		    <c:choose>
			<c:when test='${landingPage == "staff" || param.landingPage == "staff"}'>
				<td align="right" width="12"><html:img  border="0" page="/skins/default/images/gtab_left_on.gif"/></td>
				<td class="topNavFontOn" width="180" align="center" valign="middle">
				    <bean:write name="staff"/>
				</td>
				<td align="left" width="12"><html:img  border="0" page="/skins/default/images/gtab_rt_on.gif"/></td>
			</c:when>
			<c:when test='${!empty uid || landingPage == "staffSite" || param.landingPage == "staffSite"}'>
				<td align="right" width="12"><html:img  border="0" page="/skins/default/images/tab_left_off.gif"/></td>
				<td class="topNavBgOff"  width="180"align="center" valign="middle">
				  <a href="<%=staffUrl%>" class="topNavOff"><bean:write name="staff"/></a>
				</td>
				<td align="left" width="12"><html:img  border="0" page="/skins/default/images/tab_rt_off.gif"/></td>
			</c:when>
		   </c:choose>
		</c:when>
	</c:choose>
		     
		 <%-- BEGIN SITE Tab --%>
		 <logic:notEmpty name="siteID">
		      <c:choose>
			<c:when test='${param.landingPage == "sites" || param.landingPage == "staffSite" || param.landingPage == "adminSite" || landingPage == "sites" || landingPage == "staffSite" || landingPage == "adminSite"}'>
			    <td align="right" width="12"><html:img  border="0" page="/skins/default/images/gtab_left_on.gif"/></td>
			    <td class="topNavFontOn" width="100" align="center" valign="middle">
<%--                                    <bean:message key="gcsm.top.navigation.sites"/>--%>
				    <%--
				    <logic:notEmpty name="siteForm" property="protocolSiteNbr">
				     <bean:write name="siteForm" property="protocolSiteNbr"/>
				    </logic:notEmpty>
				    <logic:empty name="siteForm" property="protocolSiteNbr">
						<bean:message key="gcsm.top.navigation.sites"/>
				    </logic:empty>
				    --%>
				    <logic:notEmpty name="siteID">
				    	<CacheLookUp.getProtocolSiteListFromCache().get(siteId) >
				    </logic:notEmpty>
				    <%--
				    <logic:empty name="siteID">
						<bean:message key="gcsm.top.navigation.sites"/>
				    </logic:empty>
				    --%>
			    </td>
			    <td align="left" width="12"><html:img  border="0" page="/skins/default/images/gtab_rt_on.gif"/></td>
			  </c:when>
			  <c:otherwise>
			    <logic:notEmpty name="siteID">
				<td align="right" width="12"><html:img  border="0" page="/skins/default/images/tab_left_off.gif"/></td>
				<td class="topNavBgOff" width="100" align="center" valign="middle">
				    <a href='<html:rewrite page="/do/SitePanelAction?method=initSite&landingTab=overview&landingPage=sites"/>&siteId=<%= siteId %>' class="topNavOff">
				    <CacheLookUp.getProtocolSiteListFromCache().get(siteId) ></a>
				</td>
				<td align="left" width="12"><html:img  border="0" page="/skins/default/images/tab_rt_off.gif"/></td>
			    </logic:notEmpty>
			  </c:otherwise>
			</c:choose>
			</logic:notEmpty>
		 <td>&nbsp;</td> <%-- Empty Column for autostretching --%>
		</tr>
	    </table>
	</td>
	<td>&nbsp;</td><%-- Empty Column for autostretching --%>
    </tr>
</table>
