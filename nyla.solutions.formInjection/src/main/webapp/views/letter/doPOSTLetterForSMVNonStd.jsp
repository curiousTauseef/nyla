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
   	<bean:message bundle="post_smv_non_std" key="smv.non.std.post.a1"/>
	</span>
   	<span class="letter_blue_small">
   	<bean:message bundle="post_smv_non_std" key="smv.non.std.post.a1a"/>
	</span>
   	</i></b>
   	<br><br><br>
   	</center>
   	
   	<%@ include file="commonStuff1.jsp" %>
		
   <%@ include file="nbsp.jsp" %>
   <b><bean:message bundle="post_smv_non_std" key="smv.non.std.post.a2"/></b>
   <bean:define id="protocol" name="letterForm" property="protocol"/>
   <%= protocol%><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_smv_non_std" key="smv.non.std.post.a3"/>
   <bean:write name="letterForm" property="invFirstNm"/>&nbsp;
   <bean:write name="letterForm" property="invLastNm"/>&nbsp;:<br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_smv_non_std" key="smv.non.std.post.b1"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_smv_non_std" key="smv.non.std.post.b2"/>   
   <bean:write name="letterForm" property="visitDate"/>.<br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_smv_non_std" key="smv.non.std.post.c1"/><br>
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   <span class="letter_blue">
   <bean:message bundle="post_smv_non_std" key="smv.non.std.post.c2"/><br><br>
   </span><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_smv_non_std" key="smv.non.std.post.d1"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>   
   <span class="letter_blue">
   <bean:message bundle="post_smv_non_std" key="smv.non.std.post.d2"/>&nbsp;
   </span>
   <br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_smv_non_std" key="smv.non.std.post.f1"/>
   <c:forEach items="${letterForm.issuePanelList}" var="issue">
   		<br>
   		<%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %> 		
        <c:out value="${issue.classification}"/>&nbsp;<c:out value="${issue.description}"/><br>        
   </c:forEach>
   <br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_smv_non_std" key="smv.non.std.post.f3"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <span class="letter_blue">
   <bean:message bundle="post_smv_non_std" key="smv.non.std.post.f4"/><br><br>
   </span>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_smv_non_std" key="smv.non.std.post.f5"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <span class="letter_blue">
   <bean:message bundle="post_smv_non_std" key="smv.non.std.post.f6"/><br><br>
   </span>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_smv_non_std" key="smv.non.std.post.f7"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <span class="letter_blue">
   <bean:message bundle="post_smv_non_std" key="smv.non.std.post.f8"/><br><br>
   </span>
   
   <%@ include file="nbsp.jsp" %>   
   <bean:message bundle="post_smv_non_std" key="smv.non.std.post.f9"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <span class="letter_blue">
   <bean:message bundle="post_smv_non_std" key="smv.non.std.post.f10"/><br><br>
   </span>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_smv_non_std" key="smv.non.std.post.f11"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_smv_non_std" key="smv.non.std.post.f12"/><br><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_smv_non_std" key="smv.non.std.post.g0"/><br><br><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:write name="letterForm" property="smnFirstNm"/>&nbsp;
   <bean:write name="letterForm" property="smnLastNm"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_smv_non_std" key="smv.non.std.post.g1"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_smv_non_std" key="smv.non.std.post.g2"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_smv_non_std" key="smv.non.std.post.g3"/><br><br><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_smv_non_std" key="smv.non.std.post.h1"/>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_smv_non_std" key="smv.non.std.post.h2"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   &nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_smv_non_std" key="smv.non.std.post.h3"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   &nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_smv_non_std" key="smv.non.std.post.h4"/><br>
   </div>

</body>
