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
   	<bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.a1"/>
   	</span>
   	
   	<span class="letter_blue_small">
   	<bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.a1a"/>
   	</span>
   	</i>
   	</b><br><br><br>
   	</center>
   	
   	<%@ include file="commonStuff1.jsp" %>
		
   <%@ include file="nbsp.jsp" %>
   <b><bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.a2"/></b>
   <bean:define id="protocol" name="letterForm" property="protocol"/>
   <%= protocol%><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.a3"/>
   <bean:write name="letterForm" property="invFirstNm"/>&nbsp;
   <bean:write name="letterForm" property="invLastNm"/> :&nbsp;
   <br><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.a4"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.a5"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.a6"/><br>
   <br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.b1"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.b2"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.b3"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.b4"/><br>
   <br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.c1"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.c2"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.c3"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.c4"/>&nbsp;
   <u><bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.c4b"/></u><br>
   <%@ include file="nbsp.jsp" %>
   <u><bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.c5"/></u>&nbsp;
   <b><bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.c5b"/></b><br>
   <%@ include file="nbsp.jsp" %>
   <b><bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.c6"/></b>&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.c6b"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.c7"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.c8"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.c9"/><br><br>

   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.d1"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.d2"/><br>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.d3"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.e0"/>&nbsp;&nbsp;   
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.e1"/><br>
   <%@ include file="nbsp.jsp" %>&nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.e2"/><br>
   <%@ include file="nbsp.jsp" %>&nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.e3"/><br>
   <%@ include file="nbsp.jsp" %>&nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.e3"/><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.f0"/>&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.f1"/><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.g0"/>&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.g1"/><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.h0"/>&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.h1"/><br>
   <%@ include file="nbsp.jsp" %>&nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.h2"/><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.i0"/>&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.i1"/><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.j0"/>&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.j1"/><br>
   <%@ include file="nbsp.jsp" %>&nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.j2"/><br>
   <%@ include file="nbsp.jsp" %>&nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.j3"/><br><br>


   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.k0"/>&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.k1"/><br>
   <%@ include file="nbsp.jsp" %>&nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.k2"/><br>
   <%@ include file="nbsp.jsp" %>&nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.k3"/><br>
   <%@ include file="nbsp.jsp" %>&nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.k4"/><br>
   <%@ include file="nbsp.jsp" %>&nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.k5"/><br>
   <%@ include file="nbsp.jsp" %>&nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.k6"/><br>
   <%@ include file="nbsp.jsp" %>&nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.k7"/><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.l0"/>&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.l1"/><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.m0"/>&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.m1"/><br>
   <%@ include file="nbsp.jsp" %>&nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.m2"/><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.n0"/>&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.n1"/><br>
   <%@ include file="nbsp.jsp" %>&nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.n2"/><br>
   <%@ include file="nbsp.jsp" %>&nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.n3"/><br>
   <%@ include file="nbsp.jsp" %>&nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.n4"/><br>
   <%@ include file="nbsp.jsp" %>&nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.n5"/><br>
   <%@ include file="nbsp.jsp" %>&nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.n6"/><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.o1"/>&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.o1"/><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.p0"/>&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.p1"/><br>
   <%@ include file="nbsp.jsp" %>&nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.p2"/><br>
   <%@ include file="nbsp.jsp" %>&nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.p3"/><br>
   <%@ include file="nbsp.jsp" %>&nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.p4"/><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.q0"/>&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.q1"/><br>
   <%@ include file="nbsp.jsp" %>&nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.q2"/><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.r0"/>&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.r1"/><br>
   <%@ include file="nbsp.jsp" %>&nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.r2"/><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.s1"/><br><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.s2"/><br><br><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:write name="letterForm" property="smnFirstNm"/>&nbsp;
   <bean:write name="letterForm" property="smnLastNm"/><br>
 
   <%@ include file="nbsp.jsp" %>   
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.t1"/><br>
   <%@ include file="nbsp.jsp" %>   
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.t2"/><br>
   <%@ include file="nbsp.jsp" %>   
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.t3"/><br><br><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.u1"/>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.u2"/><br>   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   &nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.u3"/><br>
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   &nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="post_scv_no_subjects" key="scv.no.subjects.post.u4"/><br>
   </div>
</body>
