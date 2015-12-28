<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<head>

<link rel="stylesheet" href="<html:rewrite page="/skins/default/css/clinsight.css"/>" type="text/css">
<title>Letter</title>
</head>
<body>
<%
response.setContentType("application/msword");
%>

<div class="letter">	
<center>
<b><i>
<span class="letter_small">
<bean:message bundle="pre_pse" key="pse.pre.la1"/>&nbsp;
</span>
<span class="letter_blue_small">
<bean:message bundle="pre_pse" key="pse.pre.la2"/>
</span>

</i></b>
<br><br><br>
</center>
<%@ include file="commonStuff1.jsp" %>

<%@ include file="nbsp.jsp" %>
<b><bean:message bundle="pre_pse" key="pse.pre.lb1"/></b>
<bean:write name="letterForm" property="protocol"/>&nbsp;
<br><br>	

<%@ include file="nbsp.jsp" %>
<bean:message bundle="pre_pse" key="pse.pre.lc1"/>
<bean:write name="letterForm" property="invFirstNm"/>&nbsp;
<bean:write name="letterForm" property="invLastNm"/>&nbsp;
<bean:message bundle="pre_pse" key="pse.pre.lc2"/>
<br><br><br>

<%@ include file="nbsp.jsp" %>
<bean:message bundle="pre_pse" key="pse.pre.ld1"/><br>

<%@ include file="nbsp.jsp" %>
<bean:write name="letterForm" property="visitDate"/>
<bean:message bundle="pre_pse" key="pse.pre.ld2"/><br>

<%@ include file="nbsp.jsp" %>
<bean:message bundle="pre_pse" key="pse.pre.ld3"/><br>
<br>

<%@ include file="nbsp.jsp" %>
<bean:message bundle="pre_pse" key="pse.pre.le0"/><br>

<%@ include file="nbsp.jsp" %>
<bean:message bundle="pre_pse" key="pse.pre.le1"/>&nbsp;
<span class="letter_red"><b>
<bean:message bundle="pre_pse" key="pse.pre.le1a"/>
</b>
</span>
<br>

<%@ include file="nbsp.jsp" %>
<%@ include file="nbsp.jsp" %>
<bean:message bundle="pre_pse" key="pse.pre.le2"/><br>

<%@ include file="nbsp.jsp" %>
<%@ include file="nbsp.jsp" %>
<bean:message bundle="pre_pse" key="pse.pre.le3"/><br>

<%@ include file="nbsp.jsp" %>
<%@ include file="nbsp.jsp" %>
<bean:message bundle="pre_pse" key="pse.pre.le4"/><br>

<%@ include file="nbsp.jsp" %>
<%@ include file="nbsp.jsp" %>
<bean:message bundle="pre_pse" key="pse.pre.le5"/><br>

<%@ include file="nbsp.jsp" %>
<%@ include file="nbsp.jsp" %>
<bean:message bundle="pre_pse" key="pse.pre.le6"/><br>

<%@ include file="nbsp.jsp" %>
<%@ include file="nbsp.jsp" %>
<bean:message bundle="pre_pse" key="pse.pre.le7"/><br>

<%@ include file="nbsp.jsp" %>
<%@ include file="nbsp.jsp" %>
<bean:message bundle="pre_pse" key="pse.pre.le8"/><br>

<%@ include file="nbsp.jsp" %>
<%@ include file="nbsp.jsp" %>
<bean:message bundle="pre_pse" key="pse.pre.le9"/><br><br>

<%@ include file="nbsp.jsp" %>
<bean:message bundle="pre_pse" key="pse.pre.lg1"/><br><br>

<%@ include file="nbsp.jsp" %>
<bean:message bundle="pre_pse" key="pse.pre.lg2"/><br><br><br><br>


<%@ include file="nbsp.jsp" %>
<bean:write name="letterForm" property="smnFirstNm"/>&nbsp;
<bean:write name="letterForm" property="smnLastNm"/><br>

<%@ include file="nbsp.jsp" %>
<bean:message bundle="pre_pse" key="pse.pre.lh1"/><br>

<%@ include file="nbsp.jsp" %>
<bean:message bundle="pre_pse" key="pse.pre.lh2"/><br>

<%@ include file="nbsp.jsp" %>
<bean:message bundle="pre_pse" key="pse.pre.lh3"/><br><br><br>

<%@ include file="nbsp.jsp" %>
<bean:message bundle="pre_pse" key="pse.pre.li1"/>
<%@ include file="nbsp.jsp" %>
<bean:message bundle="pre_pse" key="pse.pre.li2"/><br>

<%@ include file="nbsp.jsp" %>
<%@ include file="nbsp.jsp" %>
&nbsp;&nbsp;&nbsp;&nbsp;
<bean:message bundle="pre_pse" key="pse.pre.li3"/><br>

<%@ include file="nbsp.jsp" %>
<%@ include file="nbsp.jsp" %>
&nbsp;&nbsp;&nbsp;&nbsp;
<bean:message bundle="pre_pse" key="pse.pre.li4"/><br>
</div>
	
	
</body>