<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="com.bms.informatics.gcsm.common.util.*,com.bms.informatics.gcsm.common.web.util.*, java.util.*,com.bms.informatics.gcsm.schedule.web.action.*,
com.bms.informatics.gcsm.schedule.web.*" %>
<head>
<link rel="stylesheet" href="<html:rewrite page="/skins/default/css/clinsight.css"/>" type="text/css">

<title>Visit Schedule</title>
</head>
<body onload="reloadParent()">
    <script language="JavaScript">
	function reloadParent() {
		<logic:equal name="newVisitForm" property="editable" value="true">
		if (window.opener.location.search.indexOf("landingPage=psi") == -1)
			window.opener.location.reload(true);
		</logic:equal>
	}

        function printForm(frm) {
            var frm = document.forms[0];
            var _index = -1;
            var k = 'selectDate';

            for(i = 0; i < frm.elements.length; i++) {
                if(frm.elements[i].name == k)
                    _index = i;
            }
            
            frm.elements[_index].value = frm.elements['arrDate'].value;
            var msg = "";
            for(i=0;i < frm.elements.length; i++) {
                msg += "\n" + frm.elements[i].name + ": \t\t" + frm.elements[i].value;
            }
            alert(msg);
                       
            
        }

    </script>
<table class="displayLabel">
<tbody>
<tr>
   <td colspan=2>
   	<h3>
   	<logic:equal name="newVisitForm" property="editable" value="true">
   		Confirmation
   	</logic:equal>
   	<logic:notEqual name="newVisitForm" property="editable" value="true">
   		Visit Details
   	</logic:notEqual>
   	</h3>
   </td>
</tr>
<tr>
   <td colspan=2>This visit has been scheduled as follows:</td>
</tr>
<logic:notEqual name="newVisitForm" property="visitTypeLabel" value="PSA">
<tr>
   <td>Protocol-Site:</td>
   <td><bean:write name="newVisitForm" property="protocolSiteLabel"/></td>
</tr>
</logic:notEqual>
<logic:equal name="newVisitForm" property="visitTypeLabel" value="PSA">
<tr>
   <td>Investigator:</td>
   <td><bean:write name="newVisitForm" property="investigatorName"/></td>
</tr>
</logic:equal>
<tr>
   <td>Visit type:</td>
   <td><bean:write name="newVisitForm" property="visitTypeLabel"/></td>
</tr>
<tr>
   <td colspan=2>&nbsp;</td>
</tr>
<tr>
   <td>Date:</td>
   <td>
   <bean:write name="newVisitForm" property="dd"/>-<bean:write name="newVisitForm" property="mmLabel"/>-<bean:write name="newVisitForm" property="yyyy"/>
   </td>
</tr>
<tr>
   <td>Time:</td>
   <td><bean:write name="newVisitForm" property="startingHour"/>:<bean:write name="newVisitForm" property="startingMinute"/> -
 <bean:write name="newVisitForm" property="endingHour"/>:<bean:write name="newVisitForm" property="endingMinute"/></td>
</tr>
<tr>
   <td colspan=2>&nbsp;</td>
</tr>
<logic:messagesPresent>
    <tr>
        <td colspan="2">
        	<hr/>
        	<html:errors />
        	<hr/>
        </td>
    </tr>
</logic:messagesPresent>
<tr>
   <td colspan=2>&nbsp;</td>
</tr>
<logic:equal name="newVisitForm" property="editable" value="true">
<tr>
   <td colspan=2>
   <logic:equal name="newVisitForm" property="fromSitePage" value="true">
   <a href='<html:rewrite page="/do/newVisitAction?method=initNewVisitForm&operation=scheduleEdit&visitScheduleId="/><bean:write name="newVisitForm" property="visitScheduleId"/>&siteId=<bean:write name="newVisitForm" property="clinicalSite"/>&investigatorId=<bean:write name="newVisitForm" property="investigatorId"/>'>Edit this Visit</a>
   </logic:equal>
   <logic:notEqual name="newVisitForm" property="fromSitePage" value="true">
   <a href='<html:rewrite page="/do/newVisitAction?method=initNewVisitForm&operation=scheduleEdit&visitScheduleId="/><bean:write name="newVisitForm" property="visitScheduleId"/>&investigatorId=<bean:write name="newVisitForm" property="investigatorId"/>'>Edit this Visit</a>
   </logic:notEqual>
   </td>
</tr>
</logic:equal>
<tr>
   <td colspan=2>&nbsp;</td>
</tr>


<tr>
   <td colspan=2>
      <c:if test="${newVisitForm.visitTypeLabel != 'PSA'}">
         <a target="new" onclick="window.close();" href='<html:rewrite page="/do/LetterAction?method=doLetterAction&preOrPost=PRE&visitId="/><bean:write name="newVisitForm" property="visitScheduleId"/>'>Download Pre-visit Letter</a>
     </c:if> 
   </td>
</tr>

<tr>
   <td colspan=2>
   <input type="button" onclick="window.close();" value="Close" />
   </td>
</tr>

</tbody>
</table>
</body>




