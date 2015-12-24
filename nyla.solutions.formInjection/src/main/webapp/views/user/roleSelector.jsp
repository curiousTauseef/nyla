<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<html>
<head>
<script language="JavaScript">

function selectRole(form) {
	
	var selectBox = form.elements['role'];
	var choice = selectBox.options[selectBox.selectedIndex].value;
	var uid = "<%=request.getParameter("uid")%>";
	
	//alert(choice);
	
	if (choice == "") {
		alert("You must select a role.");
		return;
	}
	
	var loc =  "/gcsm/do/StaffPanelAction?method=initStaff&landingTab=admin&landingPage=adminStaff&uid="+uid+"&role="+choice;
	window.opener.location.href=loc;
    window.close();
    
    return false;

}


</script>
<link rel="stylesheet" href="/gcsm/skins/default/css/clinsight.css" type="text/css">
</head>
<body>
<form>
<table>
<tr>
<td class="portBody">Select role for user:</td>
<td>

<select name="role" class="portBody">
	<option value="">Select Role</option>
	<logic:iterate id="roleItem" name="roleList">
	<option value="<bean:write name="roleItem" property="key"/>"><bean:write name="roleItem" property="value"/></option>
	</logic:iterate>
</select>

</td>
<td><input type="button" value="Go" class="portBody" onClick="selectRole(this.form);"/></td>
</tr>
</table>
</form>
</body>
</html>