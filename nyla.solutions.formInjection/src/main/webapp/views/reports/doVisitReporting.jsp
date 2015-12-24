<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://gcsm.bms.com/tld/security.tld" prefix="security"%>
<%@ page import="com.bms.informatics.gcsm.common.util.*,com.bms.informatics.gcsm.common.web.util.*, java.util.*,com.bms.informatics.gcsm.protocol.web.*" %>
<%@page contentType="text/html; charset=windows-1252"%>

<html:html>
	<table width="100%" height="57px" border="0" cellpadding="0" cellspacing="0">
	<br>
		<tr>
			<td>&nbsp;</td>
			<td><h3 class="reportsLink">Visit Reporting</h3></td>
		</tr>
		
		<security:guard permission="MonitorReports">
		<tr>
			<td>&nbsp;</td>
			<td><a href='<html:rewrite page="/do/VisitTypeReportAction?method=doVisitTypeReportAction&amp;landingPage=reports&amp;selectTab=visitsReporting&amp;reportName=VisitDateToReportCompletion"/>' class="reportsLink">Visit Date to Report Completion</a>
		</tr>
		</security:guard>
		
		<security:guard permission="ManagerReports">
		<tr>
			<td>&nbsp;</td>
			<td><a href='<html:rewrite page="/do/VisitTypeReportAction?method=doVisitTypeReportAction&amp;landingPage=reports&amp;selectTab=visitsReporting&amp;reportName=ReportSubmissionToESF"/>' class="reportsLink">Report Submission to ESF</a>
		</tr>
		</security:guard>
		
	</table>
</html:html>
