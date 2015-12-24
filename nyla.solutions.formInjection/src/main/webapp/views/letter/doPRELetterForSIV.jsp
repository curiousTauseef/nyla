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
	<div class="letter" >

   	<center>
   	<b><i>
   	<span class="letter_small">
   	<bean:message bundle="post_phone" key="phone.post.a1"/>&nbsp;
   	</span>
   	
   	<span class="letter_blue_small">
   	<bean:message bundle="post_phone" key="phone.post.a1a"/>
   	</span>
   	
   	</b></i>
	</center>

   	<%@ include file="commonStuff1.jsp" %>
		
   <%@ include file="nbsp.jsp" %>
   <b><bean:message bundle="pre_siv" key="siv.pre.a2"/></b>
   <bean:define id="protocol" name="letterForm" property="protocol"/>
   <%= protocol%><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_siv" key="siv.pre.a3"/>
   <bean:write name="letterForm" property="invFirstNm"/>&nbsp;
   <bean:write name="letterForm" property="invLastNm"/>&nbsp;
   <br><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_siv" key="siv.pre.lb1"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:write name="letterForm" property="visitDateTime"/>
   <bean:message bundle="pre_siv" key="siv.pre.lb2"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_siv" key="siv.pre.lb3"/><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_siv" key="siv.pre.lc1"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_siv" key="siv.pre.lc2"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_siv" key="siv.pre.lc3"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   <span class="letter_blue">
   <bean:message bundle="pre_siv" key="siv.pre.lc4"/>
   </span><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_siv" key="siv.pre.ld1"/>&nbsp;
      <span class="letter_blue">
	<bean:message bundle="pre_siv" key="siv.pre.ld1a"/></span>&nbsp;
   <bean:message bundle="pre_siv" key="siv.pre.ld1b"/>&nbsp;
   <span class="letter_red"><bean:message bundle="pre_siv" key="siv.pre.ld1c"/></span><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_siv" key="siv.pre.ld2"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_siv" key="siv.pre.ld3"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_siv" key="siv.pre.ld4"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_siv" key="siv.pre.ld5"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_siv" key="siv.pre.ld6"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_siv" key="siv.pre.ld7"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_siv" key="siv.pre.ld8"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_siv" key="siv.pre.ld9"/><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_siv" key="siv.pre.le1"/>&nbsp;
   
   <bean:write name="letterForm" property="stmFirstNm"/>&nbsp;
   <bean:write name="letterForm" property="stmLastNm"/>
   <bean:message bundle="pre_siv" key="siv.pre.le2"/>&nbsp;
   <bean:message bundle="pre_siv" key="siv.pre.le3"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_siv" key="siv.pre.le4"/><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_siv" key="siv.pre.lf1"/><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_siv" key="siv.pre.lg1"/><br><br><br><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:write name="letterForm" property="smnFirstNm"/>&nbsp;
   <bean:write name="letterForm" property="smnLastNm"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_siv" key="siv.pre.lh2"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_siv" key="siv.pre.lh3"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_siv" key="siv.pre.lh4"/><br><br><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_siv" key="siv.pre.li1"/>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_siv" key="siv.pre.li2"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   &nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="pre_siv" key="siv.pre.li3"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   &nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="pre_siv" key="siv.pre.li4"/><br>
   </div>
</body>
