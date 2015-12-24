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
   	<bean:message bundle="post_psa" key="psa.post.a1"/>
   	</span>
   	<span class="letter_blue_small">
   	</span>
   	<bean:message bundle="post_psa" key="psa.post.a12"/>
   	</i></b>
   	<br><br><br>
   	</center>
   	
   	<%@ include file="commonStuff1.jsp" %>
		
   <%@ include file="nbsp.jsp" %>
   <b><bean:message bundle="post_psa" key="psa.post.a2"/></b>
   <bean:define id="protocol" name="letterForm" property="protocol"/>
   <%= protocol%><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_psa" key="psa.post.a3"/>
   <bean:write name="letterForm" property="invFirstNm"/>&nbsp;
   <bean:write name="letterForm" property="invLastNm"/>&nbsp;:<br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_psa" key="psa.post.b1"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_psa" key="psa.post.b2"/>   
   <bean:write name="letterForm" property="visitDate"/>.<br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_psa" key="psa.post.b3"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_psa" key="psa.post.b4"/><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <b><i><span class="letter_green">
   <bean:message bundle="post_psa" key="psa.post.c1"/>
   </span></i></b>
   <br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_psa" key="psa.post.line"/><br>
   <%@ include file="nbsp.jsp" %>
   <b><i><span class="letter_green">
   <bean:message bundle="post_psa" key="psa.post.c3"/>&nbsp;
   </span></i></b>
   <bean:message bundle="post_psa" key="psa.post.c4"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_psa" key="psa.post.c5"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_psa" key="psa.post.c6"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_psa" key="psa.post.line"/><br>
   <%@ include file="nbsp.jsp" %>
   
   <b><i><span class="letter_green">
   <bean:message bundle="post_psa" key="psa.post.c8"/>&nbsp;
   </span></i></b>
   <bean:message bundle="post_psa" key="psa.post.c9"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_psa" key="psa.post.c10"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_psa" key="psa.post.c11"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <span class="letter_blue">
   <bean:message bundle="post_psa" key="psa.post.c12"/><br><br>
   </span>
   <c:forEach items="${letterForm.issuePanelList}" var="issue">
   		<br>
   		<%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %> 		
        <c:out value="${issue.classification}"/><c:out value="${issue.description}"/><br>        
   </c:forEach>
   <br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_psa" key="psa.post.c13"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_psa" key="psa.post.c14"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_psa" key="psa.post.line"/><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_psa" key="psa.post.c16"/><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_psa" key="psa.post.c17"/><br><br><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:write name="letterForm" property="smnFirstNm"/>&nbsp;
   <bean:write name="letterForm" property="smnLastNm"/><br>      
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_psa" key="psa.post.d1"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_psa" key="psa.post.d2"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_psa" key="psa.post.d3"/><br><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_psa" key="psa.post.e1"/>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_psa" key="psa.post.e2"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   &nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_psa" key="psa.post.e3"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   &nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_psa" key="psa.post.e4"/><br>
   </div>
</body>
