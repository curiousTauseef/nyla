<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="com.bms.informatics.gcsm.common.util.*,com.bms.informatics.gcsm.common.web.util.*, java.util.*,com.bms.informatics.gcsm.reports.web.action.*,
com.bms.informatics.gcsm.reports.web.form.*" %>

<head>
    <h3 class="reportsHeader"><%=reportTitle%></h3>
<%--    <title>Visit Date to Report Completion Detail</title>    --%>
</head>

<link href="<html:rewrite page="/skins/default/css/clinsight.css"/>" rel="stylesheet" type="text/css"/>

<body>
<table>
	<tr>
		<td><span id="body">
	 	<bean:define id="reportForm" name="reportForm" property="html"/>
		<%=reportForm%>
		</td>
	</tr>
</table>
</body>



