<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
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
   	<bean:message bundle="pre_psa" key="psa.pre.la1"/>&nbsp;
   	</span>
   	<span class="letter_blue_small">
   	<bean:message bundle="pre_psa" key="psa.pre.la2"/>
   	</span>

   	</i></b>
   	<br><br><br>
   	</center>
   	
   	<%@ include file="commonStuff1.jsp" %>
		
   <%@ include file="nbsp.jsp" %>
   <b><bean:message bundle="pre_psa" key="psa.pre.lb1"/></b><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_psa" key="psa.pre.lb2"/>
   <bean:write name="letterForm" property="invFirstNm"/>&nbsp;
   <bean:write name="letterForm" property="invLastNm"/>&nbsp;:
   <br><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_psa" key="psa.pre.lc1"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_psa" key="psa.pre.lc2"/>
   <bean:write name="letterForm" property="visitDate"/>.<br><br>
      
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_psa" key="psa.pre.ld1"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_psa" key="psa.pre.ld2"/><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_psa" key="psa.pre.le1"/>&nbsp;
   <span class="letter_red">
   <bean:message bundle="pre_psa" key="psa.pre.le1a"/><br>
   </span>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_psa" key="psa.pre.le2"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_psa" key="psa.pre.le3"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_psa" key="psa.pre.le4"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_psa" key="psa.pre.le5"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_psa" key="psa.pre.le6"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_psa" key="psa.pre.le7"/><br><br>

   <%@ include file="nbsp.jsp" %>
   <span class="letter_green"><i>
   <bean:message bundle="pre_psa" key="psa.pre.lf0"/>
   </span></i>
   &nbsp;
   <bean:message bundle="pre_psa" key="psa.pre.lf1"/><br>
  
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_psa" key="psa.pre.lf2"/><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_psa" key="psa.pre.lg1"/><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_psa" key="psa.pre.lh1"/><br><br><br><br>

   <%@ include file="nbsp.jsp" %>
   <bean:write name="letterForm" property="smnFirstNm"/>&nbsp;
   <bean:write name="letterForm" property="smnLastNm"/><br>

   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_psa" key="psa.pre.li1"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_psa" key="psa.pre.li2"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_psa" key="psa.pre.li3"/><br><br>
   
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_psa" key="psa.pre.lj1"/>
   <%@ include file="nbsp.jsp" %>
   <bean:message bundle="pre_psa" key="psa.pre.lj2"/><br>
   
   <%@ include file="nbsp.jsp" %>
   <%@ include file="nbsp.jsp" %>
   &nbsp;&nbsp;&nbsp;&nbsp;
   <bean:message bundle="pre_psa" key="psa.pre.lj3"/><br>
   </div>
</body>
