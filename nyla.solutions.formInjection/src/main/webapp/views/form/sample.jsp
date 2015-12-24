<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!--
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://gcsm.bms.com/tld/form.tld" prefix="form"%>
-->

<head>
    <title>Sample HOME </title>
    <SCRIPT LANGUAGE="JavaScript" SRC="<c:url value="/views/common/calendar/calendar.js"/>"></SCRIPT>
       
	<!--  from ipresentation -->
</head>
    <BODY>
    <c:out value="${param.formType}"/> Application
    <hr/>
    <form id="aForm" name="aForm" action="<c:url value="/controller"/>" method="POST">
	   <table id="1" width="450">
	     <tr>
	       <td>ID:</td><td><c:out value="${form.primaryKey}"/></td>
	     </tr>
		    <form:questionaire/>		    
	   </table>
	   <input  type="hidden" name="cmd" value="command.save"/> 
	   <input  type="hidden" name="nv" value="/views/form/sample.jsp"/>
	   <input name="submit" value="submit "type="submit"/>
	   
      </form>
   </BODY>
   
     
