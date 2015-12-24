<!--
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://gcsm.bms.com/tld/form.tld" prefix="form"%>
-->
<html>
<head>
   <title>Help Text</title>
   <link rel="stylesheet" type="text/css" href="<c:url value="/skins/default/css/clinsight.css"/>"/>
   <link rel="stylesheet" type="text/css" href="<c:url value="/skins/default/css/form.css"/>"/>
   <script src="<c:url value="/views/common/js/form.js"/>"></script>
 </head>
  <body topmargin="0" leftmargin="0" rightmargin="0">
      <p class="standard_bold"> Help</p>
      <p>
         <form:questionHelp/>
      </p>
      
      <p class="standard_bold"> Structured Interview Guidance</p>
         <form:questionStudyHelp/>      
         
  </body>
   </html>