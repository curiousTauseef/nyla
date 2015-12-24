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
   	<bean:message bundle="post_phone" key="phone.post.a1"/>&nbsp;
   	</span>
   	
   	<span class="letter_blue_small">
   	<bean:message bundle="post_phone" key="phone.post.a1a"/>
   	</span>
   	
   	</i></b><br><br><br>
   	</center>
   	<%@ include file="commonStuff1.jsp" %>
		
   <%@ include file="nbsp.jsp" %>
   <b><bean:message bundle="post_phone" key="phone.post.a2"/></b>
   <bean:define id="protocol" name="letterForm" property="protocol"/>
   <%= protocol%><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_phone" key="phone.post.a3"/>
   <bean:write name="letterForm" property="invFirstNm"/>&nbsp;
   <bean:write name="letterForm" property="invLastNm"/>&nbsp;:<br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_phone" key="phone.post.b1"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_phone" key="phone.post.b2"/>   
   <bean:write name="letterForm" property="visitDate"/>.<br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_phone" key="phone.post.b3"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <span class="letter_blue"><bean:message bundle="post_phone" key="phone.post.b4"/></span><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_phone" key="phone.post.b5"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_phone" key="phone.post.b6"/><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_phone" key="phone.post.c1"/>&nbsp;<span class="letter_red"><bean:message bundle="post_phone" key="phone.post.c2"/></span><br> 
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_phone" key="phone.post.c3"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_phone"  key="phone.post.c4"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_phone" key="phone.post.c5"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_phone" key="phone.post.c6"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_phone" key="phone.post.c7"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_phone" key="phone.post.c8"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_phone" key="phone.post.c9"/><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_phone" key="phone.post.d1"/><br>
      <c:forEach items="${letterForm.issuePanelList}" var="issue">
   		<%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %> 		
        <c:out value="${issue.classification}"/>&nbsp;<c:out value="${issue.description}"/><br>        
   </c:forEach>
   <br>

   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <span class="letter_blue"><bean:message bundle="post_phone" key="phone.post.d2"/></span><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_phone" key="phone.post.d3"/>&nbsp;
   <span class="letter_blue"><bean:message bundle="post_phone" key="phone.post.d3a"/></span>&nbsp;
   <bean:message bundle="post_phone" key="phone.post.d3b"/>
   <br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <span class="letter_blue"><bean:message bundle="post_phone" key="phone.post.d4"/></span><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_phone" key="phone.post.d5"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <span class="letter_blue"><bean:message bundle="post_phone" key="phone.post.d6"/></span><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_phone" key="phone.post.d7"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <span class="letter_blue"><bean:message bundle="post_phone" key="phone.post.d8"/></span><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_phone" key="phone.post.d9"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <span class="letter_blue"><bean:message bundle="post_phone" key="phone.post.d10"/></span><br><br>

   <%@ include file="nbsp.jsp" %>
   <span class="letter_green"><i><bean:message bundle="post_phone" key="phone.post.e1"/></i></span>&nbsp;
   <bean:message bundle="post_phone" key="phone.post.e2"/>&nbsp;
   <span class="letter_blue"><bean:message bundle="post_phone" key="phone.post.e3"/></span><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_phone" key="phone.post.e4"/>&nbsp;
   <span class="letter_blue"><bean:message bundle="post_phone" key="phone.post.e5"/></span>&nbsp;
   <bean:message bundle="post_phone" key="phone.post.e6"/>&nbsp;
   <span class="letter_blue"><bean:message bundle="post_phone" key="phone.post.e7"/></span><br>
   <%@ include file="nbsp.jsp" %>
   <span class="letter_blue"><bean:message bundle="post_phone" key="phone.post.e8"/></span><br><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <span class="letter_blue"><bean:message bundle="post_phone" key="phone.post.e9"/></span><br><br>
   

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_phone" key="phone.post.f1"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_phone" key="phone.post.f2"/><br><br><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_phone" key="phone.post.f3"/><br><br><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:write name="letterForm" property="smnFirstNm"/>&nbsp;
   <bean:write name="letterForm" property="smnLastNm"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_phone" key="phone.post.g1"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_phone" key="phone.post.g2"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_phone" key="phone.post.g3"/><br><br><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_phone" key="phone.post.h1"/>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_phone" key="phone.post.h2"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   &nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_phone" key="phone.post.h3"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   &nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_phone" key="phone.post.h4"/><br>
   </div>

</body>
