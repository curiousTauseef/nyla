<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://gcsm.bms.com/tld/security.tld" prefix="security"%>
<%@ page import="com.bms.informatics.gcsm.common.util.*,com.bms.informatics.gcsm.common.web.util.*, java.util.*,com.bms.informatics.gcsm.protocol.web.*" %>

<%@page contentType="text/html; charset=windows-1252"%>
<html:html>
<link href="<html:rewrite page="/skins/default/css/clinsight.css"/>" rel="stylesheet" type="text/css"/>

	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	  <tr>
		<td colspan="2" align="left" valign="top">

			  <table width="100%" height="57px" border="0" cellpadding="0" cellspacing="0">
				<tr>
				  <td><h3 class="reportsLink">Reports</h3></td>
				</tr>
				

				<security:guard permission="MonitorReports">				
				<tr>
				  <td><a href='<html:rewrite page="/do/VisitTargetReportAction?method=doVisitTargetReportAction&amp;landingPage=reports&amp;selectTab=visitsTargets&reportName=VisitTargetReport"/>' class="reportsLink">Visit Targets</a></td>
				</tr>
				</security:guard>
								
				<tr>
				  <td>&nbsp;</td>
				</tr>
				
		 <c:if test="${sessionScope.accessRole != 'Protocol Manager' && sessionScope.accessRole != 'Protocol Manager back up'}">							  					
				<tr>				  
				  <td><div class="reportsLink">Visits Reporting</div></td>				  
				</tr>
				
				<tr>
				  	<td class="reportsLink">
				  	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			  		<security:guard permission="MonitorReports">
						<tr>
							<td><a href='<html:rewrite page="/do/VisitTypeReportAction?method=doVisitTypeReportAction&amp;landingPage=reports&amp;selectTab=visitsReporting&amp;reportName=VisitDateToReportCompletion"/>' class="reportsLink">Visit Date to Report Completion</a></td>
						</tr>
						</security:guard>
		 
						<security:guard permission="ManagerReports">
						<tr>
							<td><a href='<html:rewrite page="/do/VisitTypeReportAction?method=doVisitTypeReportAction&amp;landingPage=reports&amp;selectTab=visitsReporting&amp;reportName=ReportSubmissionToESF"/>' class="reportsLink">Report Submission to ESF</a></td>
						</tr>
						</security:guard>
					  </table>
				   	</td>
				</tr>
				
				<tr>
				  <td>&nbsp;</td>
				</tr>
		</c:if>
					
				<tr>
				  <td><div class="reportsLink">Subject Activity</div></td>
				</tr>
								
				<tr>
				  <td class="reportsLink"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
				  
 				  <c:if test="${sessionScope.accessRole != 'Protocol Manager' && sessionScope.accessRole != 'Protocol Manager back up'}">																				  
					<security:guard permission="MonitorReports">
					<tr>
					  <td><a href='<html:rewrite page="/do/SubjectReportAction?method=doSubjectReportAction&amp;landingPage=reports&amp;selectTab=subjectActivity&amp;reportName=SDVSummary"/>' class="reportsLink">SDV Summary</a></td>
					</tr>
					</security:guard>
				  </c:if>	
				  
					<security:guard permission="MonitorManagerReports">
					<tr>
					  <td><a href='<html:rewrite page="/do/RandomReportAction?method=doRandomReportAction&amp;landingPage=reports&amp;selectTab=subjectActivity&amp;reportName=RandomTargetReport"/>' class="reportsLink">Randomized Target Achieved</a></td>					
					</tr>
					</security:guard>
					
					<security:guard permission="MonitorManagerReports">
					<tr>
                      <td><a href='<html:rewrite page="/do/LowEnrollReportAction?method=doLowEnrollReportAction&amp;landingPage=reports&amp;selectTab=subjectActivity&amp;reportName=LowEnrollSites"/>' class="reportsLink">Low-Enrolling Sites</a></td>					
					</tr>
					</security:guard>
					
					<security:guard permission="MonitorManagerReports">
					<tr>
                      <td><a href='<html:rewrite page="/do/ProtocolDevAction?method=doProtocolDevAction&amp;landingPage=reports&amp;selectTab=subjectActivity&amp;reportName=ProtocolDev"/>' class="reportsLink">Significant Protocol Deviations</a></td>					
					</tr>
					</security:guard>

				  </table></td>
				</tr>
				<tr>
				  <td>&nbsp;</td>
				</tr>
				<!-- ACCESS ROLE <c:out value="${sessionScope.accessRole}"/> -->
				
				 <c:if test="${sessionScope.accessRole != 'Protocol Manager' && sessionScope.accessRole != 'Protocol Manager back up'}">				
      				<security:guard permission="MonitorReports">
      
               				<tr>
               				  <td><a href='<html:rewrite page="/do/SubjectReportAction?method=doSubjectReportAction&amp;landingPage=reports&amp;selectTab=smnWorkload&amp;reportName=SiteMonitorWorkload"/>' class="reportsLink">Site Monitor Workload</a></td>
               				</tr>
      
      				</security:guard>
				

      				<security:guard permission="ManagerReports">
      				<tr>
      				  <td><a href='<html:rewrite page="/do/SubjectReportAction?method=doSubjectReportAction&amp;landingPage=reports&amp;selectTab=stmWorkload&amp;reportName=SiteManagerWorkload"/>' class="reportsLink">Site Manager Workload</a></td>
      				</tr>
      				</security:guard>
      				
      				
      				<security:guard permission="MonitorReports">
      				<tr>
      				  <td><a href='<html:rewrite page="/do/SubjectReportAction?method=doSubjectReportAction&amp;landingPage=reports&amp;selectTab=smnSiteVisits&amp;reportName=SiteMonitorSiteVisits"/>' class="reportsLink">Site Monitor Site Visits</a></td>
      				</tr>
				</security:guard>
           </c:if>   																				
			  </table>
		</td>
	  </tr>
	</table>
</html:html>
