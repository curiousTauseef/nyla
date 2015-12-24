<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://gcsm.bms.com/tld/security.tld" prefix="security"%>
<%@ page import="com.bms.informatics.gcsm.common.util.*,com.bms.informatics.gcsm.common.web.util.*, java.util.*,com.bms.informatics.gcsm.protocol.web.*" %>

<%@page contentType="text/html; charset=windows-1252"%>

<link href="<html:rewrite page="/skins/default/css/clinsight.css"/>" rel="stylesheet" type="text/css"/>

<table id="left_nav_tbl" cellpadding="1" cellspacing="1" border="0" style="width: 160px; background-color:#FFF">
    	<tr>
      		<c:choose>
        	<c:when test='${param.selectTab == null || empty param.selectTab || param.selectTab == "reportsIndex"}'>
          	<td class="leftNavOn">Reports Index</td>
        	</c:when>  
        	<c:otherwise>
          	<td class="leftNavOff">
            <a href='<html:rewrite page="/do/reports?landingPage=reports&amp;selectTab=reportsIndex"/>' class="leftNavOff">Reports Index</a>
          	</td>
        	</c:otherwise>
      		</c:choose>
    	</tr>
    	                        	
    	<security:guard permission="MonitorReports">
    	<tr>
      		<c:choose>
        	<c:when test='${param.selectTab == "visitsTargets"}'>
          	<td class="leftNavOn">Visits Targets</td>
        	</c:when>
        	<c:otherwise>
          	<td class="leftNavOff">
            <a href='<html:rewrite page="/do/VisitTargetReportAction?method=doVisitTargetReportAction&amp;landingPage=reports&amp;selectTab=visitsTargets&reportName=VisitTargetReport"/>' class="leftNavOff">Visits Targets</a>
          	</td>
        	</c:otherwise>
      		</c:choose>
    	</tr>
    	</security:guard>

    	<tr>
    	<c:if test='${sessionScope.accessRole != "Protocol Manager" && sessionScope.accessRole != "Protocol Manager back up"}'>
      		<c:choose>                          		
        	<c:when test='${param.selectTab == "visitsReporting"}'>
          	<td class="leftNavOn">Visits Reporting</td>
        	</c:when>
        	<c:otherwise>
          	<td class="leftNavOff">
           	<a href='<html:rewrite page="/do/DummyReportAction?method=doVisitReportingAction&landingPage=reports&selectTab=visitsReporting"/>' class="leftNavOff">Visits Reporting</a>
          	</td>
      	  	</c:otherwise>                          	  
      		</c:choose>
      		</c:if>	
    	</tr>
								
    	<tr>
      		<c:choose>
        	<c:when test='${param.selectTab == "subjectActivity"}'>
          	<td class="leftNavOn">Subject Activity</td>
        	</c:when>
        	<c:otherwise>
          	<td class="leftNavOff">
            <a href='<html:rewrite page="/do/DummyReportAction?method=doSubjectActivityReportAction&landingPage=reports&selectTab=subjectActivity"/>' class="leftNavOff">Subject Activity</a>
          	</td>
        	</c:otherwise>
      		</c:choose>
    	</tr>

							 <c:if test="${sessionScope.accessRole != 'Protocol Manager' && sessionScope.accessRole != 'Protocol Manager back up'}">							  	
    	<security:guard permission="MonitorReports">                        
   		<tr>
      		<c:choose>
        	<c:when test='${param.selectTab == "smnWorkload"}'>
          	<td class="leftNavOn">SMN Workload</td>
        	</c:when>
        	<c:otherwise>
          	<td class="leftNavOff">
            <a href='<html:rewrite page="/do/SubjectReportAction?method=doSubjectReportAction&amp;landingPage=reports&amp;selectTab=smnWorkload&amp;reportName=SiteMonitorWorkload"/>' class="leftNavOff">SMN Workload</a>
          	</td>
        	</c:otherwise>
      		</c:choose>
    	</tr>
    	</security:guard>

    	<security:guard permission="ManagerReports">
    	<tr>
      		<c:choose>
        	<c:when test='${param.selectTab == "stmWorkload"}'>
          	<td class="leftNavOn">STM Workload</td>
        	</c:when>
        	<c:otherwise>
          	<td class="leftNavOff">
          	<a href='<html:rewrite page="/do/SubjectReportAction?method=doSubjectReportAction&amp;landingPage=reports&amp;selectTab=stmWorkload&amp;reportName=SiteManagerWorkload"/>' class="leftNavOff">STM Workload</a>
          	</td>
        	</c:otherwise>
      		</c:choose>
    	</tr>
    	</security:guard>
    
    	<security:guard permission="MonitorReports">
    	<tr>
      		<c:choose>
        	<c:when test='${param.selectTab == "smnSiteVisits"}'>
          	<td class="leftNavOn">SMN Site Visits</td>
        	</c:when>
        	<c:otherwise>
          	<td class="leftNavOff">
          	<a href='<html:rewrite page="/do/SubjectReportAction?method=doSubjectReportAction&amp;landingPage=reports&amp;selectTab=smnSiteVisits&amp;reportName=SiteMonitorSiteVisits"/>' class="leftNavOff">SMN Site Visits</a>
          	</td>
        	</c:otherwise>
      		</c:choose>
    	</tr>
    	</security:guard>
    	</c:if>
</table>

