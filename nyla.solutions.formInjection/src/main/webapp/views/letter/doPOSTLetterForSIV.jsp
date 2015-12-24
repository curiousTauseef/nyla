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
   	<bean:message bundle="post_siv" key="siv.post.a1"/>&nbsp;
	</span>
   	<span class="letter_blue_small">
   	<bean:message bundle="post_siv" key="siv.post.a1a"/>
	</span>
   	</i></b>
   	<br><br><br>
   	</center>
   	
   	<%@ include file="commonStuff1.jsp" %>
		
   <%@ include file="nbsp.jsp" %>
   <b><bean:message bundle="post_siv" key="siv.post.a2"/></b>
   <bean:define id="protocol" name="letterForm" property="protocol"/>
   <%= protocol%><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.a3"/>
   <bean:write name="letterForm" property="invFirstNm"/>&nbsp;
   <bean:write name="letterForm" property="invLastNm"/>&nbsp;:<br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.b1"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.b2"/>   
   <bean:write name="letterForm" property="visitDate"/>
   <bean:message bundle="post_siv" key="siv.post.b3"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.b4"/><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.c1"/>&nbsp;
   <span class="letter_blue">
   <bean:message bundle="post_siv" key="siv.post.c2"/>
   </span>
   &nbsp;
   <bean:message bundle="post_siv" key="siv.post.c3"/>
   <br>
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   <span class="letter_blue">
   <bean:message bundle="post_siv" key="siv.post.c4"/>
   </span>
   <br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.d1"/>&nbsp;
   <span class="letter_red">
   <bean:message bundle="post_siv" key="siv.post.d2"/>
   </span>

   <br><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   <input type="checkbox"><b><u><bean:message bundle="post_siv" key="siv.post.e1"/></u></b><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.e2"/><br>
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.e3"/><br><br>

   
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <input type="checkbox"><u><b><bean:message bundle="post_siv" key="siv.post.f1"/></u></b><br>   
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.f2"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.f3"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.f4"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.f5"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.f6"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.f7"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.f8"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.f9"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.f10"/><br><br>


   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   <input type="checkbox"><u><b><bean:message bundle="post_siv" key="siv.post.g1"/></u></b><br>   
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.g2"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_siv" key="siv.post.g3"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.g4"/><br><br>


   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <input type="checkbox"><u><b><bean:message bundle="post_siv" key="siv.post.h1"/></u></b><br>   
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.h2"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.h3"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.h4"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.h5"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.h6"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.h7"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.h8"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.h9"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_siv" key="siv.post.h10"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.h11"/><br><br>

   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   <input type="checkbox">
   <b><u><bean:message bundle="post_siv" key="siv.post.i1"/></u></b><br>   
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.i2"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_siv" key="siv.post.i3"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.i4"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.i5"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.i6"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.i7"/><br><br>

   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   <input type="checkbox"><u><b><bean:message bundle="post_siv" key="siv.post.j1"/></u></b><br>   
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.j2"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.j3"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.j4"/><br><br>

   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <input type="checkbox"><u><b><bean:message bundle="post_siv" key="siv.post.k1"/></u></b><br>   
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.k2"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.k3"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.k4"/><br><br>

   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <input type="checkbox"><u><b><bean:message bundle="post_siv" key="siv.post.l1"/></u></b><br>   
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.l2"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.l3"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.l4"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.l5"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.l6"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.l7"/><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.m1"/><br>
   <span class="letter_blue">
   <c:forEach items="${letterForm.issuePanelList}" var="issue">
   		<%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %> 		
        <c:out value="${issue.classification}"/>&nbsp;<c:out value="${issue.description}"/><br>        
   </c:forEach>
   <br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.m2"/><br><br>
   </span>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.n1"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <span class="letter_blue">
   <bean:message bundle="post_siv" key="siv.post.n2"/><br><br>
   </span>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.o1"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <span class="letter_blue">
   <bean:message bundle="post_siv" key="siv.post.o2"/><br><br>
   </span>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.p1"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <span class="letter_blue">
   <bean:message bundle="post_siv" key="siv.post.p2"/><br><br>
   </span>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.q1"/><br>
   <%@ include file="nbsp.jsp" %><%@ include file="nbsp.jsp" %>
   <span class="letter_blue">
   <bean:message bundle="post_siv" key="siv.post.q2"/><br>
   </span>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.r1"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.r2"/><br><br><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.r3"/><br><br><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:write name="letterForm" property="smnFirstNm"/>&nbsp;
   <bean:write name="letterForm" property="smnLastNm"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.s1"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.s2"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.s3"/><br><br><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.t1"/>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_siv" key="siv.post.t2"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   &nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_siv" key="siv.post.t3"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   &nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_siv" key="siv.post.t4"/><br>
   </div>
</body>
