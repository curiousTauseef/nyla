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
   	<bean:message bundle="post_unblinded" key="unblinded.post.a1"/>&nbsp;
   	</span>
   	<span class="letter_blue_small">
   	<bean:message bundle="post_unblinded" key="unblinded.post.a1a"/>
   	</span>
   	
   	</i></b>
   	</center>
   	<br><br><br>
 
   	<%@ include file="commonStuff1.jsp" %>
		
   <%@ include file="nbsp.jsp" %>
   <b><bean:message bundle="post_unblinded" key="unblinded.post.a2"/></b>
   <bean:define id="protocol" name="letterForm" property="protocol"/>
   <%= protocol%><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_unblinded" key="unblinded.post.a3"/>
   <bean:write name="letterForm" property="invFirstNm"/>&nbsp;
   <bean:write name="letterForm" property="invLastNm"/>&nbsp;:<br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_unblinded" key="unblinded.post.b1"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_unblinded" key="unblinded.post.b2"/>   
   <bean:write name="letterForm" property="visitDate"/>.<br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_unblinded" key="unblinded.post.c1"/>&nbsp;<span class="letter_red"><bean:message bundle="post_unblinded" key="unblinded.post.c2"/></span><br> 
   
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_unblinded" key="unblinded.post.d1"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_unblinded"  key="unblinded.post.d2"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_unblinded" key="unblinded.post.d3"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_unblinded" key="unblinded.post.d4"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_unblinded" key="unblinded.post.d5"/><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_unblinded" key="unblinded.post.e1"/><br>
      <c:forEach items="${letterForm.issuePanelList}" var="issue">
   		<%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %> 		
        <c:out value="${issue.classification}"/>&nbsp;<c:out value="${issue.description}"/><br>        
   </c:forEach>
   <br>

   <%@ include file="nbsp.jsp" %>
   <span class="letter_green"><i><bean:message bundle="post_unblinded" key="unblinded.post.f1"/></i></span>&nbsp;
   <bean:message bundle="post_unblinded" key="unblinded.post.f2"/>&nbsp;
   <span class="letter_blue"><bean:message bundle="post_unblinded" key="unblinded.post.f3"/></span>&nbsp;
   <bean:message bundle="post_unblinded" key="unblinded.post.f4"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_unblinded" key="unblinded.post.f5"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <span class="letter_blue"><bean:message bundle="post_unblinded" key="unblinded.post.f6"/></span>
   <br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_unblinded" key="unblinded.post.g1"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_unblinded" key="unblinded.post.g2"/><br><br><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_unblinded" key="unblinded.post.g3"/><br><br><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:write name="letterForm" property="smnFirstNm"/>&nbsp;
   <bean:write name="letterForm" property="smnLastNm"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_unblinded" key="unblinded.post.h1"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_unblinded" key="unblinded.post.h2"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_unblinded" key="unblinded.post.h3"/><br><br><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_unblinded" key="unblinded.post.i1"/>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_unblinded" key="unblinded.post.i2"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   &nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_unblinded" key="unblinded.post.i3"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   &nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_unblinded" key="unblinded.post.i4"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   &nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_unblinded" key="unblinded.post.i5"/><br>

	</div>
</body>