<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://gcsm.bms.com/tld/security.tld" prefix="security"%>
<%@ page import="com.bms.informatics.gcsm.common.util.*,com.bms.informatics.gcsm.form.ClinicalGuide,com.bms.informatics.gcsm.common.web.util.*, java.util.*,com.bms.informatics.gcsm.protocol.web.*" %>
<%
String letterAction="";
String libraryAction="";
String osifAction="";
String siteContactAction="";
String siteId = request.getParameter("siteId");
if (siteId == null)
	siteId = request.getParameter("siteID");
if (siteId == null)
	siteId = session.getAttribute("siteID") != null ? String.valueOf(session.getAttribute("siteID")) : "";
if (siteId != null) {
	letterAction="/LetterPanelAction?method=init&siteID="+siteId+"&landingTab=letters";
	libraryAction="/DocumentAction?method=initDocumentPanel&siteId="+siteId+"&landingTab=library" ;
	osifAction="/formAction?op=createForUnscheduled&landingTab=osif&siteID="+siteId+"&formTypeName="+ClinicalGuide.OSIF_FORM_NM;
	siteContactAction="/formAction?op=createForUnscheduled&landingTab=sasc&siteID="+siteId+"&formTypeName="+ClinicalGuide.SITE_CONTACT;
} else {
   libraryAction="/DocumentAction?method=initDocumentPanel&landingTab=library" ;
}
%>

<c:if test="${!(empty sessionScope.siteID)}">
   <table id="left_nav_tbl" width="100%" cellpadding="1" cellspacing="1" border="0" style="background-color:#FFF">
     <%-- BEGIN OVERVIEW Tab --%>
     <c:choose>
       <c:when test='${param.landingTab == "overview"}'>
         <tr>
           <td class="leftNavOn">
             <bean:message key="bms.gcsm.site.leftnav.overview" />
           </td>
         </tr>
       </c:when>
       <c:otherwise>
         <tr>
           <td class="leftNavOff">
            <a class="leftNavOff" href=" <c:url value="/do/SitePanelAction?method=initSite&landingTab=overview&landingPage=sites"/>&siteId=<%= siteId %>">
               <bean:message key="bms.gcsm.site.leftnav.overview" />
             </a>
           </td>
         </tr>
       </c:otherwise>
     </c:choose>
     
     <%-- BEGIN LIBRARY Tab --%>
     <c:choose>
       <c:when test='${landingTab == "library" || param.landingTab == "library"}'>
         <tr>
           <td class="leftNavOn" valign="middle">
             <bean:message key="bms.gcsm.site.leftnav.library" />
           </td>
         </tr>
       </c:when>
       <c:otherwise>
         <tr>
           <td class="leftNavOff">
             <html:link styleClass="leftNavOff" action="<%=libraryAction%>">          
               <bean:message key="bms.gcsm.site.leftnav.library"/>
             </html:link>
           </td>
         </tr>
       </c:otherwise>
     </c:choose>
   
     <%-- BEGIN LETTERS Tab --%>
     <c:choose>
       <c:when test='${param.landingTab == "letters"}'>
         <tr>
           <td class="leftNavOn">
             <bean:message key="bms.gcsm.site.leftnav.visit.followUp"/>
           </td>
         </tr>
       </c:when>
       <c:otherwise>
         <tr>
           <td class="leftNavOff">
             <html:link  styleClass="leftNavOff" action="<%=letterAction%>">          
               <bean:message key="bms.gcsm.site.leftnav.visit.followUp"/>
             </html:link>
           </td>
         </tr>
       </c:otherwise>
     </c:choose>
   
   
   	<tr>
   	  <td class="leftNavOff" style="height:40px; vertical-align:bottom">MONITORING FORMS: </td>
   	</tr>
     
     <%-- BEGIN DRUG RECONCILIATION Tab --%>
     <c:choose>
       <c:when test='${landingTab == "drug" || param.landingTab == "drug" || landingTab == "DRW" || param.landingTab == "DRW"}'>
         <tr>
           <td class="leftNavOn">
             <div class="leftNavIndent"><bean:message key="bms.gcsm.site.leftnav.drug" /></div>
           </td>
         </tr>
       </c:when>
       <c:otherwise>
         <tr>
           <td class="leftNavOff" valign="middle">
             <a href="<c:url value="/do/DRWAction?method=initDrugReconcilationPanel&landingTab=drug"/>&siteID=<%= siteId %>" class="leftNavOff">
               <span class="leftNavIndent"><bean:message key="bms.gcsm.site.leftnav.drug"/></span>
             </a>
           </td>
         </tr>
       </c:otherwise>
     </c:choose>
     <%-- BEGIN On-site Investigator File Tab --%>
       <c:choose>
         <c:when test='${landingTab == "osif" || param.landingTab == "osif" || landingTab == "OSIF" || param.landingTab == "OSIF"}'>
           <tr>
             <td class="leftNavOn" valign="middle">
               <span class="leftNavIndent"><bean:message key="bms.gcsm.site.leftnav.osif" /></span>
             </td>
           </tr>
         </c:when>
         <c:otherwise>
           <tr>
             <td class="leftNavOff" valign="middle">
               <a class="leftNavOff" href="<c:url value="/do/formAction?op=saveUnscheduledSite&landingTab=osif&formTypeCode=OSIF&formTypeName=On-Site Investigator File"/>&siteID=<%= siteId %>">
                 <span class="leftNavIndent"><bean:message key="bms.gcsm.site.leftnav.osif"/></span>
               </a>
             </td>
           </tr>
         </c:otherwise>
     </c:choose>
     
   	<tr>
   	  <td class="leftNavOff">
   	  	  <% String postIssueUrl = "PostIssueAction?method=init&siteID=" + siteId; %>
   	      <html:link styleClass="leftNavOff"
   	        onclick="window.open('','postIssue','width=490,height=697,menubar=no,top=20, scrollbars=no, status=no,toolbar=no,titlebar=no');"
   	        href="<%= postIssueUrl %>"
   	        target="postIssue">
   	        	<span class="leftNavIndent"><bean:message key="bms.gcsm.site.leftnav.visit.post"/></span>
   	      </html:link>  
   	  </td>
     </tr>
   
     <%-- BEGIN PROTOCOL EXCEPTION Tab --%>
     <c:choose>
       <c:when test='${landingTab == "pe" || param.landingTab == "pe" || landingTab == "PE" || param.landingTab == "PE"}'>
         <tr>
           <td class="leftNavOn" valign="middle">
             <bean:message key="bms.gcsm.site.leftnav.protocolException" />
           </td>
         </tr>
       </c:when>
       <c:otherwise>
         <tr>
           <td class="leftNavOff" valign="middle">
             <a class="leftNavOff" href="<c:url value="formAction?op=createForUnscheduled&formTypeCode=PE&formTypeName=Protocol Exception"/>&siteID=<%= siteId %>">
               <span class="leftNavIndent"><bean:message key="bms.gcsm.site.leftnav.protocolException"/></span>
             </a>
           </td>
         </tr>
       </c:otherwise>
     </c:choose>
   <%--Protocol Deviation--%>
       <c:choose>
         <c:when test='${landingTab == "pd" || param.landingTab == "pd" || landingTab == "PD" || param.landingTab == "PD"}'>
           <tr>
             <td class="leftNavOn" valign="middle">
               <bean:message key="bms.gcsm.site.leftnav.visit.protocolDeviation" />
             </td>
           </tr>
         </c:when>
         <c:otherwise>
           <tr>
             <td class="leftNavOff" valign="middle">
               <a class="leftNavOff" href="<c:url value="/do/formAction?op=createForUnscheduled&formTypeCode=PD&formTypeName=Protocol Deviation"/>&siteID=<%= siteId %>">
                 <span class="leftNavIndent"><bean:message key="bms.gcsm.site.leftnav.visit.protocolDeviation"/></span>
               </a>
             </td>
           </tr>
         </c:otherwise>
     </c:choose>
   <%-- BEGIN Site Contact Form Tab --%>
       <c:choose>
         <c:when test='${landingTab == "sasc" || param.landingTab == "sasc" || landingTab == "SASC" || param.landingTab == "SASC"}'>
           <tr>
             <td class="leftNavOn" valign="middle">
               <span class="leftNavIndent"><bean:message key="bms.gcsm.site.leftnav.significant" /></span>
             </td>
           </tr>
         </c:when>
         <c:otherwise>
           <tr>
             <td class="leftNavOff" valign="middle">
               <a class="leftNavOff" href="<c:url value="/do/formAction?op=createForUnscheduled&landingTab=sasc&formTypeCode=SASC&formTypeName=Significant Ad-hoc Site Contact"/>&siteID=<%= siteId %>">
                 <span class="leftNavIndent"><bean:message key="bms.gcsm.site.leftnav.significant"/></span>
               </a>
             </td>
           </tr>
         </c:otherwise>
     </c:choose>
     	
   	
   	
   </table>
</c:if>