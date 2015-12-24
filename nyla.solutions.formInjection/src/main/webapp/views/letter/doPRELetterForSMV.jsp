<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="com.bms.informatics.gcsm.common.util.*,com.bms.informatics.gcsm.common.web.util.*, java.text.SimpleDateFormat, java.util.*,com.bms.informatics.gcsm.letter.web.action.*,
com.bms.informatics.gcsm.letter.web.*, com.bms.informatics.gcsm.schedule.data.*" %>
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
   	<bean:message bundle="pre_smv" key="smv.pre.la1"/>
   	</span>
   	
   	<span class="letter_blue_small">
   	<bean:message bundle="pre_smv" key="smv.pre.la2"/>
   	</span>
   	</i>
   	</b><br><br><br>
   	</center>
   	
   	<%@ include file="commonStuff1.jsp" %>
		
   <%@ include file="nbsp.jsp" %>
   <b><bean:message bundle="pre_smv" key="smv.pre.lb1"/></b>
   <bean:define id="protocol" name="letterForm" property="protocol"/>
   <%= protocol%><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_smv" key="smv.pre.lb2"/>
   <bean:write name="letterForm" property="invFirstNm"/>&nbsp;
   <bean:write name="letterForm" property="invLastNm"/> :&nbsp;
   <br><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_smv" key="smv.pre.ld1"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_smv" key="smv.pre.ld2"/>
   <bean:write name="letterForm" property="visitDateTime"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_smv" key="smv.pre.le1"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_smv" key="smv.pre.le2"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_smv" key="smv.pre.le3"/><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_smv" key="smv.pre.lf1"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_smv" key="smv.pre.lf2"/><br><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_smv" key="smv.pre.lf3"/><br><br><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:write name="letterForm" property="smnFirstNm"/>&nbsp;
   <bean:write name="letterForm" property="smnLastNm"/><br>
 
   <%@ include file="nbsp.jsp" %>   
   <bean:message bundle="pre_smv" key="smv.pre.lg1"/><br>
   <%@ include file="nbsp.jsp" %>   
   <bean:message bundle="pre_smv" key="smv.pre.lg2"/><br>
   <%@ include file="nbsp.jsp" %>   
   <bean:message bundle="pre_smv" key="smv.pre.lg3"/><br><br><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_smv" key="smv.pre.lh1"/>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_smv" key="smv.pre.lh2"/><br>   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   &nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="pre_smv" key="smv.pre.lh3"/><br>
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   &nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="pre_smv" key="smv.pre.lh4"/><br>
   </div>
</body>
