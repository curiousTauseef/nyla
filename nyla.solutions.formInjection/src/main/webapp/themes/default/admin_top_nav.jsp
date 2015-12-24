<%--
  User: $Id
  Date: Jul 30, 2005
  Time: 12:24:42 PM
  Comments:Dmane
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="java.math.BigDecimal, java.util.*, java.text.*"%>
<%
   String contextPath=request.getContextPath();
   String staffUrl=contextPath+"/do/StaffPanelAction?method=initStaff";
   String landingPage="";
   String uid="";
   String siteId="";
   String uidUrl="";
   String siteIdUrl="";
   String role="";
   String roleUrl="";

   uid=request.getParameter("uid");
   siteId=request.getParameter("siteId");
   role=request.getParameter("role");
   landingPage=request.getParameter("landingPage");
  if (landingPage == null) {
      landingPage="home";
  }

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
  staffUrl=staffUrl ;
  //staffUrl=staffUrl+ uidUrl + siteIdUrl + roleUrl;
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
                       <c:when test='${empty param.landingPage || param.landingPage == "home"}'>
                               <td align="right" width="12"><html:img  border="0" page="/skins/default/images/gtab_left_on.gif"/></td>
                               <td class="topNavFontOn" width="32" align="center" valign="middle">
                                           <bean:message key="gcsm.top.navigation.home"/>

                               </td>
                               <td align="left" width="12"><html:img  border="0" page="/skins/default/images/gtab_rt_on.gif"/></td>
                       </c:when>
                       <c:otherwise>
                               <td align="right" width="12"><html:img  border="0" page="/skins/default/images/tab_left_off.gif"/></td>
                               <td class="topNavBgOff" width="32" align="center" valign="middle">
                                 <a href='<html:rewrite page="/do/init?landingPage=home"/>' class="topNavOff"><bean:message key="gcsm.top.navigation.home"/></a>
                               </td>
                               <td align="left" width="12"><html:img  border="0" page="/skins/default/images/tab_rt_off.gif"/></td>
                       </c:otherwise>
                   </c:choose>
                   <c:choose>
                       <c:when test='${param.landingPage == "adminStaff"}'>
                               <td align="right" width="12"><html:img  border="0" page="/skins/default/images/gtab_left_on.gif"/></td>
                               <td class="topNavFontOn" width="180" align="center" valign="middle">
                                   <!-- <a href='<%=staffUrl+uidUrl+"&landingPage=adminStaff"%>' class="topNavFontOn"><bean:write name="staff"/></a> --><bean:write name="staff"/>
                               </td>
                               <td align="left" width="12"><html:img  border="0" page="/skins/default/images/gtab_rt_on.gif"/></td>
                       </c:when>
                       <c:when test='${param.landingPage == "sites" || param.landingPage == "staffSite" || param.landingPage == "adminSite"}'>
                               <td align="right" width="12"><html:img  border="0" page="/skins/default/images/tab_left_off.gif"/></td>
                               <td class="topNavBgOff" width="32" align="center" valign="middle">
                                 <a href='<%=staffUrl+ uidUrl+"&landingPage=adminStaff"%>' class="topNavOff"><bean:write name="staff"/></a>
                               </td>
                               <td align="left" width="12"><html:img  border="0" page="/skins/default/images/tab_rt_off.gif"/></td>
                       </c:when>
                   </c:choose>
                        <c:if test='${param.landingPage == "sites" || param.landingPage == "staffSite" || param.landingPage == "adminSite"}'>
                                <td align="right" width="12"><html:img  border="0" page="/skins/default/images/gtab_left_on.gif"/></td>
                                <td class="topNavFontOn" width="100" align="center" valign="middle">
<%--                                    <bean:message key="gcsm.top.navigation.sites"/>--%>
                                        <logic:notEmpty name="siteForm" property="protocolSiteNbr">
                                         <bean:write name="siteForm" property="protocolSiteNbr"/>
                                        </logic:notEmpty>
                                        <logic:empty name="siteForm" property="protocolSiteNbr">
                                                    <bean:message key="gcsm.top.navigation.sites"/>
                                        </logic:empty>

                                </td>
                                <td align="left" width="12"><html:img  border="0" page="/skins/default/images/gtab_rt_on.gif"/></td>
                         </c:if>
                <td>&nbsp;</td> <%-- Empty Column for autostretching --%>
               </tr>
           </table>
       </td>
       <td>&nbsp;</td><%-- Empty Column for autostretching --%>
   </tr>
</table>
