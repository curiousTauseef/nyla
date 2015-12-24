<!--
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://gcsm.bms.com/tld/form.tld" prefix="form"%>
-->
<html>
<head>
   <title>Edit <c:out value="${param.name}"/></title>
   <link rel="stylesheet" type="text/css" href="<c:url value="/skins/default/css/clinsight.css"/>"/>
   <link rel="stylesheet" type="text/css" href="<c:url value="/skins/default/css/form.css"/>"/>
   <script src="<c:url value="/views/common/js/form.js"/>"></script>
</head>
<body topmargin="0" leftmargin="0" rightmargin="0">
  <table width="100%"  class="formHeader" cellspacing="0" cellpadding="0" border="0">  
     <tr>
        <td id="formHeaderText">&nbsp;Edit <c:out value="${param.name}"/></td>
     </tr>
   </table>
  <p>
     <form id="multiSelectForm" name="multiSelectForm">
     <table width="50%">

        <tr>
           <td width="10%" align="left" class="multiSelectHeaderText"><c:out value="${param.name}"/></td>
           <td width="1%" align="center">&nbsp;</td>
           <td width="89%" align="left" class="multiSelectHeaderText">Selected <c:out value="${param.name}"/></td>
        </tr>
        <tr>
           <td width="10%"  align="left" valign="top">
              <select id="choices" size="10" name="choices" multiple>                                                                          
               </select>
            </td>
           <td width="1%" align="center">
                                <input type="button" value="&nbsp;&nbsp;&gt;&gt;&nbsp;&nbsp;" onclick="pickMultiSelectedChoices(multiSelectForm);"/><br/>
                                <input type="button" value="&nbsp;&nbsp;&lt;&lt;&nbsp;&nbsp;" onclick="unpickMultiSelectedChoices(multiSelectForm);"/>                                
           </td>
           <td width="89%" align="left" valign="top">
              <select id="selectedChoices" size="10" name="selectedChoices" multiple>
               </select>
               <br/>
              <p>&nbsp;
               <input type="button" value="Clear" onclick="clearMultiSelect(multiSelectForm);"/>
              </p>
           </td>
        </tr>
     </table>
     <br/>
    <hr/>    
    <input type="button" value="Cancel" onclick="cancelMultiSelect(multiSelectForm);"/><input type="button" value="Submit" onclick="submitMultiSelect(multiSelectForm);"/>
    <input id="answerID" type="hidden" name="answerID"  value=""/>
    <input id="answerIDText" type="hidden" name="answerIDText"  value=""/>
    <input id="answerValue" type="hidden" name="answerValue" value=""/>

     </form>
   </body>
   
   </html>