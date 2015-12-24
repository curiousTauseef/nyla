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
   	<bean:message bundle="post_pse" key="pse.post.a1"/>
	</span>
   	<span class="letter_blue_small">
   	<bean:message bundle="post_pse" key="pse.post.a1a"/>
	</span>
   	</i></b>
   	<br><br><br>
   	</center>
   	
   	<%@ include file="commonStuff1.jsp" %>
		
   <%@ include file="nbsp.jsp" %>
   <b><bean:message bundle="post_pse" key="pse.post.a2"/></b>
   <bean:define id="protocol" name="letterForm" property="protocol"/>
   <%= protocol%><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_pse" key="pse.post.a3"/>
   <bean:write name="letterForm" property="invFirstNm"/>&nbsp;
   <bean:write name="letterForm" property="invLastNm"/>&nbsp;:<br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_pse" key="pse.post.b1"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_pse" key="pse.post.b2"/>   
   <bean:write name="letterForm" property="visitDate"/>.<br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_pse" key="pse.post.c1"/><br>
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   <span class="letter_blue">
   <bean:message bundle="post_pse" key="pse.post.c2"/><br><br>
   </span>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_pse" key="pse.post.d1"/>&nbsp;
   <span class="letter_red"><b>
   <bean:message bundle="post_pse" key="pse.post.d1a"/>
   </b></span>
   <br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_pse" key="pse.post.d2"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_pse" key="pse.post.d3"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_pse" key="pse.post.d4"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_pse" key="pse.post.d5"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_pse" key="pse.post.d6"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_pse" key="pse.post.d7"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_pse" key="pse.post.d8"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_pse" key="pse.post.d9"/><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_pse" key="pse.post.e1"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_pse" key="pse.post.e2"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_pse" key="pse.post.e3"/><br><br>
   
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_pse" key="pse.post.f1"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
      <c:forEach items="${letterForm.issuePanelList}" var="issue">
   		<br>
   		<%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %> 		
        <c:out value="${issue.classification}"/><c:out value="${issue.description}"/><br>        
   </c:forEach><br>
   <span class="letter_blue">
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_pse" key="pse.post.f2"/><br><br>
   </span>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_pse" key="pse.post.f3"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <span class="letter_blue">
   <bean:message bundle="post_pse" key="pse.post.f4"/><br><br>
   </span>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_pse" key="pse.post.f5"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <span class="letter_blue">
   <bean:message bundle="post_pse" key="pse.post.f6"/><br><br>
   </span>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_pse" key="pse.post.f7"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_pse" key="pse.post.f8"/><br><br><br>

   <%@ include file="nbsp.jsp" %>   
   <bean:message bundle="post_pse" key="pse.post.f9"/><br><br><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:write name="letterForm" property="smnFirstNm"/>&nbsp;
   <bean:write name="letterForm" property="smnLastNm"/><br>   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_pse" key="pse.post.f10"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_pse" key="pse.post.f11"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_pse" key="pse.post.f12"/><br><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_pse" key="pse.post.g1"/>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_pse" key="pse.post.g2"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   &nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_pse" key="pse.post.g3"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   &nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_pse" key="pse.post.g4"/><br>
   </div>
</body>
