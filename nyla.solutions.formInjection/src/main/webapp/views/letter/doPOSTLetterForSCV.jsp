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
   	<bean:message bundle="post_scv" key="scv.post.a1"/>
   	</span>
   	<span class="letter_blue_small">
   	<bean:message bundle="post_scv" key="scv.post.a1a"/>
   	</span>
   	<br><br><br>
   	</i></b>
   	</center>
   	
   	<%@ include file="commonStuff1.jsp" %>
		
   <b>
   <bean:message bundle="post_scv" key="scv.post.a2"/></b>
   <bean:define id="protocol" name="letterForm" property="protocol"/>
   <%= protocol%><br><br>
   
   <bean:message bundle="post_scv" key="scv.post.a3"/>
   <bean:write name="letterForm" property="invFirstNm"/>&nbsp;
   <bean:write name="letterForm" property="invLastNm"/>&nbsp;:<br><br>

   <bean:message bundle="post_scv" key="scv.post.b1"/>
   <bean:message bundle="post_scv" key="scv.post.b2"/>
   <bean:message bundle="post_scv" key="scv.post.b3"/>
   <bean:message bundle="post_scv" key="scv.post.b4"/>
   <bean:message bundle="post_scv" key="scv.post.b5"/>
   
   <bean:message bundle="post_scv" key="scv.post.c1"/>
   <bean:message bundle="post_scv" key="scv.post.c2"/>
   <bean:message bundle="post_scv" key="scv.post.c3"/>
   <bean:message bundle="post_scv" key="scv.post.c4"/>
   <bean:message bundle="post_scv" key="scv.post.c5"/>
   <bean:message bundle="post_scv" key="scv.post.c6"/>
   <bean:message bundle="post_scv" key="scv.post.c7"/>
   <bean:message bundle="post_scv" key="scv.post.c8"/>
   <bean:message bundle="post_scv" key="scv.post.c9"/>
   <bean:message bundle="post_scv" key="scv.post.c10"/>
   <bean:message bundle="post_scv" key="scv.post.c11"/>
   <bean:message bundle="post_scv" key="scv.post.c12"/>
   <bean:message bundle="post_scv" key="scv.post.c13"/>
   <bean:message bundle="post_scv" key="scv.post.c14"/>
   <bean:message bundle="post_scv" key="scv.post.c15"/>
   <bean:message bundle="post_scv" key="scv.post.c16"/>
   <bean:message bundle="post_scv" key="scv.post.c17"/>

  
   <bean:message bundle="post_scv" key="scv.post.d1"/>
   <bean:message bundle="post_scv" key="scv.post.d2"/>
   <bean:message bundle="post_scv" key="scv.post.d3"/>
   <bean:message bundle="post_scv" key="scv.post.d4"/>
   <bean:message bundle="post_scv" key="scv.post.d5"/>
   <bean:message bundle="post_scv" key="scv.post.d6"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:write name="letterForm" property="smnFirstNm"/>&nbsp;
   <bean:write name="letterForm" property="smnLastNm"/><br>      
   
   <bean:message bundle="post_scv" key="scv.post.d7"/>
   <bean:message bundle="post_scv" key="scv.post.d8"/>
   <bean:message bundle="post_scv" key="scv.post.d9"/>
   <bean:message bundle="post_scv" key="scv.post.d10"/>
   <bean:message bundle="post_scv" key="scv.post.d11"/>
   <bean:message bundle="post_scv" key="scv.post.d12"/>
   </div>

   </body>
