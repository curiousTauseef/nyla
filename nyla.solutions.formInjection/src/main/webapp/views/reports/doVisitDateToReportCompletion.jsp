<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="com.bms.informatics.gcsm.common.util.*,com.bms.informatics.gcsm.common.web.util.*, java.util.*,com.bms.informatics.gcsm.reports.web.action.*,
com.bms.informatics.gcsm.reports.web.form.*" %>
<script src="<c:url value="/views/common/js/form.js"/>"></script>



<html:form action="/VisitTypeReportAction?method=doVisitTypeReportAction&amp;landingPage=reports&amp;selectTab=visitsReporting">
<link href="<html:rewrite page="/skins/default/css/clinsight.css"/>" rel="stylesheet" type="text/css"/>

	<bean:define id="reportTitle" name="reportForm" property="reportTitle"/>
	<h3 class="reportsHeader"><%=reportTitle%></h3>   

	<bean:define id="monitorNm" name="reportForm" property="monitorNm"/>
	<bean:define id="reportName" name="reportForm" property="reportName"/>	
	<bean:define id="stm" name="reportForm" property="stm"/>

	
<input class="reportsLink" type="hidden" id="reportName" name="reportName" value=<%=reportName%> >
<input class="reportsLink" type="hidden" id="monitorNm" name="monitorNm" value="<%=monitorNm%>" >
<input type="hidden" id="stm" name="stm" value="<%=stm%>" >

<table width="100%" cellpadding="0" cellspacing="0" border="0" style="text-align:left;vertical-align:top">
	<tr style="padding-left: 15px">
   		<td align="left" valign="top">
   			<table border="0" cellpadding="0" cellspacing="0">
   				<tbody>
   					<tr>
      					<td>
         					<table border="0" cellpadding="1" cellspacing="1">
            					<tbody>
              						<tr>
                 						<td class="reportsLink">Report Type</td>
                 						<td class="reportsLink">
                     						<bean:define id="visitTypeDropDown" name="reportForm" property="visitTypeDropDown"/>
                     						<html:select property="visitTypeCd">
                       							<html:options collection="visitTypeDropDown" property="value" labelProperty="label"/>
                     						</html:select>
                 						</td>
              						</tr>

              						<tr>
                 						<td class="reportsLink">Date Range &nbsp;&nbsp; Begin</td>
                 						<td class="reportsLink">
                 							<html:text id="startDt" name="reportForm" property="startDt" style="width:100px" readonly="true"/>
											<img id="img_startDt" src="/gcsm/skins/default/images/button_calendar.gif" style="cursor:hand" onclick="showCalendar(this)">
                    						End
                    						<html:text id="endDt" name="reportForm" property="endDt" style="width:100px" readonly="true"/>
                    						<img id="img_endDt" src="/gcsm/skins/default/images/button_calendar.gif" style="cursor:hand" onclick="showCalendar(this)">
                 						</td>
              						</tr>
            					</tbody>
         					</table>
      					</td>
   					</tr>
   					<tr>
      					<td>
         					<table border="0" cellpadding="1" cellspacing="1">
            					<tbody>
            						<tr>
	            						<td class="reportsLink">
	               							<input class="reportsLink" id="btnCreate" name="btnCreate" type="submit" value="Go" onclick=""/>
	            						</td>
            						</tr>
            					</tbody>
         					</table>
      					</td>
   					</tr>
   				</tbody>
 			</table>
 		</td>
 	</tr>
 	<tr>
		<td>
            <bean:define id="reportForm" name="reportForm" property="html"/>
            <table id="report_tbl">
               <tr><td>
               <span id="body">
                  <%=reportForm%>
               </span>
               </td></tr>
            </table>
		</td>
 	</tr>
 </table>

</html:form>


