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
<script language="JavaScript">
     function doSubmit() {
        var form = document.forms[0];
        var operation = form.elements['operation'].value;
        
        var ymd = null;
          form.elements['doValidate'].value = 'yes';
          
          if (form.elements['arrDate'].value != '')
          { 
             ymd = (form.elements['arrDate'].value).split('-');
           	  form.elements['yyyy'].value = ymd[0];
		      form.elements['mm'].value = ymd[1]-1;
		      form.elements['dd'].value = ymd[2];

		      var hr = form.startingHour.value;
		      var min = form.startingMinute.value;
		      if (hr.charAt(0) == '0')
		      	hr = hr.substring(1);
		      if (min.charAt(1) == '0')
		      	min = min.substring(1);
		      
		      var today = new Date();
		      var scheduledStart = new Date(ymd[0], ymd[1]-1, ymd[2], hr, min);
		      //alert("today=" + today + ", scheduledStart=" + scheduledStart);
		      if (scheduledStart.getTime() <= today.getTime()) {
		      	if (!confirm("The scheduled date is in the past.  Click OK to continue, Cancel to modify."))
		      		return false;
		      }
		  }
         
          if (form.elements['clinicalSite']) {
	          var clinicalSite = form.elements['clinicalSite'];
	          form.elements['protocolSiteLabel'].value = clinicalSite.options[clinicalSite.selectedIndex].text;
          }
          if (form.elements['visitType']) {
	          var visitType = form.elements['visitType'];
	          form.elements['visitTypeLabel'].value = visitType.options[visitType.selectedIndex].text;
          }
              //printForm(form);
              //alert('before submit');
            form.submit();
    }

    function printForm(frm) {

             var msg = "";
        
        for(i=0;i < frm.elements.length; i++) {
            msg += "\n" + frm.elements[i].name + ": \t\t" + frm.elements[i].value;
        } 
        
        alert(msg);
        
    }
    
      var monthArray = new Array("JAN", "FEB", "MAR", "APR", "MAY", "JUN",
            "JUL", "AUG", "SEP", "OCT", "NOV", "DEC");
    function init() {
      form = document.forms[0];
      if (form.elements['yyyy'].value != '') {
         document.getElementById('selectedDate').innerHTML = 
           form.elements['dd'].value + '-' + 
           monthArray[parseInt(form.elements['mm'].value)] + '-' +
           form.elements['yyyy'].value;
      }
    }

    </script>
</head>
<%
String investigatorId = request.getParameter("investigatorId");
%>
<body onload="init()">
<html:form action="newVisitAction?method=doNewVisitForm">    
<html:hidden property="investigatorId"/>
<table class="displayLabel">
<tr>
   <td><span id="body">
   <input type="hidden" id="arrDate" name="arrDate" value=""/>
   <table border="0" cellpadding="0" cellspacing="0" class="displayLabel">
   <tbody>
   <tr>
      <td>
         <table border="0" cellpadding="1" cellspacing="1" class="displayLabel">
            <tbody>
            <logic:notEqual name="newVisitForm" property="visitTypeLabel" value="PSA">
              <tr>
                 <td><b>Protocol-Site:</b></td>
                 <td>
                 	<logic:equal name="newVisitForm" property="fromSitePage" value="false">
	                 	<logic:equal name="newVisitForm" property="editable" value="true">
	                     <bean:define id="clinicalSiteList" name="newVisitForm" property="clinicalSiteList"/>
	                     <html:select property="clinicalSite">
	                       <html:options collection="clinicalSiteList" property="value" labelProperty="label"/>
	                     </html:select>
	                     </logic:equal>
	                     <logic:notEqual name="newVisitForm" property="editable" value="true">
	                     	<bean:write name="newVisitForm" property="protocolSiteLabel"/>
	                     </logic:notEqual>
                     </logic:equal>
                     <logic:equal name="newVisitForm" property="fromSitePage" value="true">
                     	<bean:write name="newVisitForm" property="protocolSiteLabel"/>
                     </logic:equal>
                 </td>
              </tr>
            </logic:notEqual>
			<logic:equal name="newVisitForm" property="visitTypeLabel" value="PSA">
			<tr>
			   <td><b>Investigator:</b></td>
			   <td><bean:write name="newVisitForm" property="investigatorName"/></td>
			</tr>
			</logic:equal>
              <tr>
                 <td><b>Visit Type*:</b></td>
                 <td>
                 	<logic:notEqual name="newVisitForm" property="visitTypeLabel" value="PSA">
                 		<logic:equal name="newVisitForm" property="editable" value="true">
                     		<bean:define id="visitList" name="newVisitForm" property="visitList"/>
                     		<html:select property="visitType">
                       			<html:options collection="visitList" property="value" labelProperty="label"/>
                     		</html:select>
                     	</logic:equal>
                     	<logic:notEqual name="newVisitForm" property="editable" value="true">
                     		<bean:write name="newVisitForm" property="visitTypeLabel"/>
                     	</logic:notEqual>
                    </logic:notEqual>
                    <logic:equal name="newVisitForm" property="visitTypeLabel" value="PSA">
                    	<bean:write name="newVisitForm" property="visitTypeLabel"/>
                    </logic:equal>
                 </td>
              </tr>
            </tbody>
         </table>
      </td>
   </tr>
   <tr>
      <td>
         <table border="0" cellpadding="1" cellspacing="1" class="displayLabel">
            <tbody>
               <tr>
                  <td>
                     <b>Date: <span id="selectedDate" name="selectedDate"></span></b>
                  </td>
               </tr>
               <tr>
                  <td>
                     <iframe width=194 height=189 name="gToday:normal:agenda.js:gfFlat_arrDate" id="gToday:normal:agenda.js:gfFlat_arrDate" src="/gcsm/skins/default/js/calendar/iflateng.htm" scrolling="no" frameborder="0"></iframe>
                 </td>
               </tr>
               <tr>
                  <td>
                     <input type="hidden" id="selectDate" name="selectDate" value=""/>
                  </td>
               </tr>
           </tbody>
         </table>
      </td>
   </tr>
   <tr>
      <td><b>Start Time:</b></td>
   </tr>
   <tr>
      <td>
         <table border="0" cellpadding="0" cellspacing="0" class="displayLabel">
            <tbody>
               <tr>
                  <td>&nbsp;&nbsp;&nbsp;
                     <bean:define id="startingHourOpt" name="newVisitForm" property="hourList"/>                  
                     <html:select property="startingHour">
                       <html:options collection="startingHourOpt" property="value" labelProperty="label"/>
                     </html:select>
                  </td>
                  <td>
                     <span style="width:5px">&nbsp;</span>
                  </td>
                  <td>
                     <bean:define id="startingMinuteOpt" name="newVisitForm" property="minuteList"/>
                     <html:select property="startingMinute">
                       <html:options collection="startingMinuteOpt" property="value" labelProperty="label"/>
                     </html:select>
                  </td>
               </tr>
            </tbody>
         </table>
      </td>
   </tr>
   <tr>
      <td height="5px"></td>
   </tr>
   <tr>
      <td><b>End Time:</b></td>
   </tr>
   <tr>
      <td>
         <table border="0" cellpadding="0" cellspacing="0" class="displayLabel">
            <tbody>
            <tr>
               <td>&nbsp;&nbsp;&nbsp;
                     <bean:define id="endingHourOpt" name="newVisitForm" property="hourList"/>               
                     <html:select property="endingHour">
                       <html:options collection="endingHourOpt" property="value" labelProperty="label"/>
                     </html:select>
               </td>
               <td>
                  <span style="width:5px">&nbsp;</span>
               </td>
               <td>
                     <bean:define id="endingMinuteOpt" name="newVisitForm" property="minuteList"/>               
                     <html:select property="endingMinute">
                       <html:options collection="endingMinuteOpt" property="value" labelProperty="label"/>
                     </html:select>
               </td>
            </tr>
            </tbody>
         </table>
      </td>
   </tr>
   <tr>
      <td>
         <table border="0" cellpadding="1" cellspacing="1" class="displayLabel" width="100%">
            <tbody>
            <tr>
	            <td colspan="2">
	            	<html:errors />
	            <hr/>
	            </td>
            </tr>
            <tr>
	            <td colspan="2">
	               <img alt="Cancel" style="cursor:hand" src="<html:rewrite page="/skins/default/images/button_cancel_on.gif" />" onclick="window.close();" />
	               <logic:equal name="newVisitForm" property="editable" value="true">
	               &nbsp;
	               <img alt="Submit" style="cursor:hand" src="<html:rewrite page="/skins/default/images/button_submit_on.gif" />" onclick="doSubmit();" />
	               </logic:equal>
	            </td>
            </tr>
            </tbody>
         </table>
      </td>
   </tr>
   </tbody>
 </table>
 <html:hidden property="yyyy" name="newVisitForm" />
 <html:hidden property="mm" name="newVisitForm" />
 <html:hidden property="dd" name="newVisitForm" />
 <html:hidden property="doValidate" name="newVisitForm" />
 <html:hidden property="protocolSiteLabel" name="newVisitForm" />
 <html:hidden property="visitTypeLabel" name="newVisitForm" />
 <html:hidden property="operation" name="newVisitForm" />
 <html:hidden property="ignoreConflict" name="newVisitForm" />

</body>
</html:form>
