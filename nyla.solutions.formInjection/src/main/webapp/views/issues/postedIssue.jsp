<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<head>
<style type="text/css">
.label, .data {
font-family:Verdana;
font-size:7pt;
padding-left:5px;
width:110px;
text-align:left;
vertical-align:top;
font-weight:bold;
}

.data { width:97%; font-weight:normal }

.PopupHeader { background-color:#FFFF99; font-family:Verdana; font-size:14px; padding-left:5px; font-weight:bold; }
.data1 {width:97%; font-weight:normal }
</style>
<title>Issue Status</title>
<script language="Javascript">
function closeWindow() {
  var parentLocation=window.opener.location;
  window.parent.location.href=parentLocation;
  window.close();

  return false;

}
</script>
</head>
<body style="margin:0px">
<html:form action="/PostIssueAction?method=submitForm"  >
<table width="490" border="0" cellpadding="0" cellspacing="0" scrolling ="auto">
  <tr>
    <td width="539" height="22" align="left" valign="middle" class="PopupHeader" style="background-color:#FFFF99;padding-left:2px;"><h3 style="font-family:Verdana; font-size:14pt; font-weight:bold; color:#000000">Post Issue</h3></td>
  </tr>
  <tr>
    <td align="left" valign="top" style="padding:10px;">
      <table width="95%"  border="0" cellpadding="1" cellspacing="1" bgcolor="#CCCCCC" style="border:1px dashed #000;">

    <tr>
	  <td class="label">Post Date:</td>
	  <td class="data">
	      <bean:write name="postIssueForm" property="postDate"/>
	  </td>
	</tr>
	<tr>
	  <td class="label">Site:</td>
	  <td class="data">
	      <bean:write name="postIssueForm" property="dps"/>
	  </td>
	</tr>
	<tr>
	  <td class="label">Classification: </td>
	  <td class="data">
	      <bean:write name="postIssueForm" property="classificationPK"/>
	  </td>
	</tr>
	<tr>
	  <td class="label">Priority:</td>
	  <td class="data">
	      <bean:write name="postIssueForm" property="priorityPK"/>
	  </td>
	</tr>
	<tr>
	  <td class="label">Description:</td>
	  <td class="data">
	      <bean:write name="postIssueForm" property="description"/>
	  </td>
	</tr>
	<tr>
	  <td class="label">Action:</td>
	  <td class="data">
	      <bean:write name="postIssueForm" property="suggestedAction"/>
	  </td>
	</tr>
	<tr>
	  <td class="label">Resolver:</td>
	  <td class="data">
	      <logic:notEmpty name="postIssueForm" property="resolverCol">
		<logic:iterate id="index" name="postIssueForm" property="resolverCol">
		  <nested:write name="index" property="value"/>
		  <br />
		</logic:iterate>
	      </logic:notEmpty>
	      <logic:empty name="postIssueForm" property="resolverCol">
		Not Available
	      </logic:empty>
	  </td>
	</tr>
	<tr>
	  <td class="label">Reviewer:</td>
	  <td class="data">
	      <logic:notEmpty name="postIssueForm" property="reviewerCol">
		<logic:iterate id="index" name="postIssueForm" property="reviewerCol">
		  <nested:write name="index" property="value"/>
		  <br />
		</logic:iterate>
	      </logic:notEmpty>
	      <logic:empty name="postIssueForm" property="reviewerCol">
		Not Available
	      </logic:empty>
	  </td>
	</tr>
      </table></td>
  </tr>
  <tr>
    <td height="24" valign="top">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
	  <td width="638" height="24" valign="top" style="padding-left:5px">
	    <logic:empty name="postIssueForm" property="formID">
	      <%--formID = empty --%>
	      <input type="button" value="Close" onclick="window.opener.location.reload();window.close();return true;"/>
	    </logic:empty>
	    <logic:notEmpty name="postIssueForm" property="formID">
	      <%--formID != empty --%>
	      <input type="button" value="Close" onclick="window.close();return true;"/>
	    </logic:notEmpty>

	  </td>
     <%--      <td width="638" height="24" valign="top" style="padding-left:5px"><input type="button" value="Close" onclick="window.opener.document.location = '/gcsm/do/init';window.close();return false;"/></td> --%>

    <%--      <td width="638" height="24" valign="top" style="padding-left:5px"><input type="button" value="Close" onclick="submitForm();"/></td> --%>
	</tr>
      </table></td>
  </tr>
</table>
</html:form>
</body>
