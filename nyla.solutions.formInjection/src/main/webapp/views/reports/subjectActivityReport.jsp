<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://gcsm.bms.com/tld/security.tld" prefix="security"%>
<%@ page import="com.bms.informatics.gcsm.common.util.*,com.bms.informatics.gcsm.common.web.util.*, java.util.*,com.bms.informatics.gcsm.protocol.web.*" %>

<%@page contentType="text/html; charset=windows-1252"%>

<html:html>
<link href="<html:rewrite page="/skins/default/css/clinsight.css"/>" rel="stylesheet" type="text/css"/>
	<table width="100%" height="57px" border="0" cellpadding="0" cellspacing="0">
	<br>
		<tr>
			<td>&nbsp;</td>
			<td><h3 class="reportsLink">Subject Activity</h3></td>
		</tr>

	 <c:if test="${sessionScope.accessRole != 'Protocol Manager' && sessionScope.accessRole != 'Protocol Manager back up'}">							  			
		<security:guard permission="MonitorReports">
		<tr>
			<td>&nbsp;</td>
			<td><a href='<html:rewrite page="/do/SubjectReportAction?method=doSubjectReportAction&amp;landingPage=reports&amp;selectTab=subjectActivity&amp;reportName=SDVSummary"/>' class="reportsLink">SDV Summary</a></td>
		</tr>
		</security:guard>
		</c:if>
		
		<security:guard permission="MonitorManagerReports">
		<tr>
			<td>&nbsp;</td>
			<td><a href='<html:rewrite page="/do/RandomReportAction?method=doRandomReportAction&amp;landingPage=reports&amp;selectTab=subjectActivity&amp;reportName=RandomTargetReport"/>' class="reportsLink">Randomized Target Achieved</a></td>
		</tr>
		</security:guard>
		

		<security:guard permission="MonitorManagerReports">
		<tr>
			<td>&nbsp;</td>
            <td><a href='<html:rewrite page="/do/LowEnrollReportAction?method=doLowEnrollReportAction&amp;landingPage=reports&amp;selectTab=subjectActivity&amp;reportName=LowEnrollSites"/>' class="reportsLink">Low-Enrolling Sites</a></td>					
		</tr>
		</security:guard>
		

		<security:guard permission="MonitorManagerReports">
		<tr>
			<td>&nbsp;</td>
            <td><a href='<html:rewrite page="/do/ProtocolDevAction?method=doProtocolDevAction&amp;landingPage=reports&amp;selectTab=subjectActivity&amp;reportName=ProtocolDev"/>' class="reportsLink">Significant Protocol Deviations</a></td>					
		</tr>		
		</security:guard>
	</table>
</html:html>
