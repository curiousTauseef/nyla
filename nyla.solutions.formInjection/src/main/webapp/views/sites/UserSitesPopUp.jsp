<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="com.bms.informatics.gcsm.common.util.*,java.util.*"%>

<title>Protocol/Site List</title>
<link rel="stylesheet" href="<html:rewrite page="/skins/default/css/clinsight.css"/>" type="text/css">
<link rel="stylesheet" href="<html:rewrite page="/skins/default/css/form.css"/>" type="text/css">

<script>

function showSasc(form)
{

var siteId=form.siteMap.options[form.siteMap.options.selectedIndex].value;

//alert(siteId);

url='<html:rewrite page="/do/formAction?op=createForUnscheduled&landingPage=ssc&LinkSource=MyForms&selectTab=sasc&formTypeName=Significant Ad-hoc Site Contact&siteID="/>';
var parentWindow = window.opener;
parentWindow.location = url+siteId;

window.close();

}

</script>

<html:form action="/MyFormPanelAction?method=viewSites">

<table align="left" border="0" cellpadding="0" cellspacing="0"  id="sites_list" width="100%">

	

	<tr>
	   <td>	
		 <table align=center width="100%" border="0" cellspacing="0" cellpadding="0" id="sites_list_data_tbl" >
		    <thead>
		    <tr height='20'>
			<td class="psiTableHeader" align=center>Protocol/Site</td>	   
		    </tr>
		    </thead>
		    <tbody>
		    <tr>     
			<td align=center height=20>
			    <select name="siteMap">
			    <logic:iterate name="siteMap" id="entry">
			    <option value="<bean:write name="entry" property="value"/>"><bean:write name="entry" property="label"/></option>
			    </logic:iterate>
			    </select>
			</td>
		     </tr>   
		    </tbody>
		</table>

		    </td>
		</tr>
		
	<TR><TD>&nbsp;</TD></TR>
	<TR><TD>&nbsp;</TD></TR>
	<TR><TD>&nbsp;</TD></TR>
	<TR><TD>&nbsp;</TD></TR>
	<TR><TD>&nbsp;</TD></TR>
	
	
	
	<TR>
	<TD>
	
	<TABLE border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr align=right>
		<td align=right>
	
		  <input style="font-size:8pt" type="button" value="Submit" onclick="showSasc(this.form)"/>
	
		  </td>
		  <TD>&nbsp;</TD>
		<TD align=left> <input style="font-size:8pt" type="button" value="Cancel" onclick="javascript:window.close()"/></TD>
	</tr>
	</TABLE>
	</TD>
	</TR>
		
		
	    </table>
  
    
	
</html:form>