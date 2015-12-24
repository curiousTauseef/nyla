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

<c:if 
test="${sessionScope.form.type.code == 'SIV' || sessionScope.form.type.code == 'SMV' ||  sessionScope.form.type.code == 'SCV' ||  sessionScope.form.type.code == 'PSA' || sessionScope.form.type.code == 'PSE'}" 
>
<a href="/gcsm/do/LetterAction?method=doLetterAction&visitId=<c:out value="${form.schedulePK}"/>&preOrPost=POST&formId=<c:out value="${form.primaryKey}"/>&formTypeCd=<c:out value="${form.type.code}"/>" target="_blank">Standard</a><br>
</c:if>

<c:if test="${sessionScope.form.type.code == 'SCV'}">
 <a href="/gcsm/do/LetterAction?method=doLetterAction&visitId=<c:out value="${form.schedulePK}"/>&preOrPost=POST&formId=<c:out value="${form.primaryKey}"/>&formTypeCd=SCV_NO_SUBJECTS" target="_blank">Site Closure Visit (no subjects)</a><br>
</c:if>


<c:if test="${sessionScope.form.type.code == 'SMV'}">
 <a href="/gcsm/do/LetterAction?method=doLetterAction&visitId=<c:out value="${form.schedulePK}"/>&preOrPost=POST&formId=<c:out value="${form.primaryKey}"/>&formTypeCd=SMV_NON_STD" target="_blank">SMV Non Standard</a><br>
 <a href="/gcsm/do/LetterAction?method=doLetterAction&visitId=<c:out value="${form.schedulePK}"/>&preOrPost=POST&formId=<c:out value="${form.primaryKey}"/>&formTypeCd=PHONE" target="_blank">Non Standard (Phone)</a><br>
 <a href="/gcsm/do/LetterAction?method=doLetterAction&visitId=<c:out value="${form.schedulePK}"/>&preOrPost=POST&formId=<c:out value="${form.primaryKey}"/>&formTypeCd=UNBLINDED" target="_blank">Non Standard (Unblinded)</a><br>
</c:if>

	


	
	


