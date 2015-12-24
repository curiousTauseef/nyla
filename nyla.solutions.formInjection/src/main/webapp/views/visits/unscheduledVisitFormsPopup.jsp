<%--
  User: $Id
  Date: Jun 21, 2005
  Time: 4:55:24 PM
  Comments:$NAME
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<script>
   function goToForm(aFormTypeName)
   {
       var formLink = '<html:rewrite page="/do/formAction?op=createUnscheduledFormForUser&formTypeName="/>';
       var parentWindow = window.opener;
       var formURL = formLink+aFormTypeName;
       parentWindow.location = formURL;
   
      return false;
   }//--------------------------------------
</script>

<table>
<thead>
   <tr>
   <td bgcolor="#CCFFCC">
       UnScheduled Visit Forms
   </td>
   </tr>
</thead>
<tbody>
   <tr>
   <td>
        Select a visit form to conduct an <u>unscheduled visit</u>.<br>
        Scheduled visit forms are accessible from a specific<br>
        Site Overview page.<br>
   </td>
   </tr>
  <tr>
  <td>
        <a href="#" onclick="goToForm('Pre-Study Evaluation');return false;">Pre-Study Evaluation</a><br>
        <a href="#" onclick="goToForm('Site Initiation Visit');return false;">Site Initiation</a><br>
        <a href="#" onclick="goToForm('Site Monitoring Visit');return false;">Site Monitoring</a><br>
        <a href="#" onclick="goToForm('Site Closure Visit');return false;">Site Closure</a> <br>
</td>
  </tr>
  <tr>
    <td style="border-top:solid 1px black"><a href="javascript:void(0);" onclick="window.close();">Close Window</a></td>
  </tr>

</tbody>
</table>
