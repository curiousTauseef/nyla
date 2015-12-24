<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page contentType="text/html; charset=windows-1252"%>
<html:html>
<head>
<title>
  <bean:message key="bms.gcsm.alert.new.title"/>
</title>
  <html:base/>
  <link href="<html:rewrite page="/skins/default/css/clinsight.css"/>" rel="stylesheet" type="text/css"/>
<script type="text/JavaScript">
      <!--
      function moveAll (frm, sf, st) {
	var from;
		for(i = 0; i < frm.elements.length; i++)
	  if(frm.elements[i].name == sf.name)
			  from = i;

	var fromLen = frm.elements[from].length;

		for(var i = 0; i < fromLen; i++)
		frm.elements[from].options[i].selected = true;

		addToList(frm,sf,st);
      }


      function addToList(frm, sf, st) {
	for(i = 0; i < frm.elements.length; i++) {
	  if(frm.elements[i].name == sf.name)
	  from = i;

	  if(frm.elements[i].name == st.name)
	  to = i;
	}

	fromLen = frm.elements[from].length ;
	for ( i=0; i<fromLen ; i++){
	  if (frm.elements[from].options[i].selected == true ) {
	    toLen = frm.elements[to].length;
	    frm.elements[to].options[toLen]= new Option(frm.elements[from].options[i].text,frm.elements[from].options[i].value);
	  }
	}

	for ( i = (fromLen -1); i>=0; i--){
	  if (frm.elements[from].options[i].selected == true ) {
	    frm.elements[from].options[i] = null;
	  }
	}
      }

      function buildForm(frm) {
	var msg = "";

	var name = "";
	for(var i = 0; i < frm.elements.length; i++) {
	  if(frm.elements[i].name == "roles")
	  name = i;
	}

	for(var i = 0; i < frm.elements[name].options.length; i++) {
	  frm.elements[name].options[i].selected = true;
	  msg += "i[" + i + "] = " + frm.elements[name].options[i].value;
	}

	frm.submit();
      }
      -->
      </script></head>
<body>
  <html:form action="/newAlertAction?method=createAlert">
    <html:hidden property="validate" value="Yes"/>
    <table cellpadding="0" cellspacing="0" border="0" width="100%" class="newAlert">
      <tr>
	<td colspan="2" align="left" valign="middle">
	  <table width="100%" cellpadding="1" cellspacing="1" border="0" class="newAlertTitle">
	    <tr>
	      <td width="50px" align="left" valign="middle">
		<bean:message key="bms.gcsm.alert.new.label.title"/>
	      </td>
	      <td align="left">
		<html:img page="/skins/default/images/icon_alerts.gif" style="vertical-align:top"/>
	      </td>
	    </tr>
	  </table>
	</td>
      </tr>
      <tr>
	<td height="5px" colspan="2">&nbsp;</td>
      </tr>
      <tr>
	<td width="100px" class="newAlertLabel" align="left" valign="top">
	  <bean:message key="bms.gcsm.alert.new.label.protocol"/>
	  <span style="color:red; font-size:8pt">*</span>
	</td>
	<td>
	  <bean:define id="studyIdMap" name="alertForm" property="studyIdMap"/>
	  <html:select name="alertForm" property="studyId" style="margin-left:10px; border:1px solid #000; font-family:Verdana; font-size:10; width:323px">
	    <html:options collection="studyIdMap"
			  property="value"
			  labelProperty="label"/>
	  </html:select>
	</td>
      </tr>
      <tr>
	<td height="3px" colspan="2">&nbsp;</td>
      </tr>
      <tr>
	<td width="100px" class="newAlertLabel" align="left" valign="top" colspan="2">
	  <bean:message key="bms.gcsm.alert.new.label.roles"/>
	  <span style="color:red; font-size:8pt">*</span>
	</td>
      </tr>
      <tr>
	<td height="3px" colspan="2">&nbsp;</td>
      </tr>
      <tr>
	<td colspan="2">
	  <table cellpadding="1" cellspacing="1" border="0" width="100%">
	    <tr>
	      <td align="left">
		<bean:define id="rolesMap" name="alertForm" property="rolesMap"/>
		<html:select multiple="true" size="8" name="alertForm" property="rolesMap" style="margin-left:10px; border:1px solid #000; font-family:Verdana; font-size:10; width:199px">
		  <html:options collection="rolesMap" property="value" labelProperty="label"/>
		</html:select>
	      </td>
	      <td align="center" valign="middle">
		<html:button property="page"
		       onclick="addToList(this.form,rolesMap,roles)"
		       style="width:25px"
		       alt="Select">
		       <bean:message key="bms.gcsm.alert.new.label.button.select"/>
		</html:button>
		<br/>
		<br/>
		<html:button property="page"
		       onclick="addToList(this.form,roles,rolesMap)"
		       style="width:25px"
		       alt="Deselect">
		       <bean:message key="bms.gcsm.alert.new.label.button.deselect"/>
		</html:button>
	      </td>
	      <td align="left">
		<html:select multiple="true" size="8" name="alertForm" property="roles" style="border:1px solid #000; font-family:Verdana; font-size:10; width:199px">
		</html:select>
	      </td>
	    </tr>
	    <tr>
	      <td align="right" valign="bottom">
		<html:button property="page"
		       onclick="moveAll(this.form,rolesMap,roles)"
		       style="width:75px"
		       alt="Select All">
		       <bean:message key="bms.gcsm.alert.new.label.button.selectall"/>
		</html:button>
	      </td>
	      <td>&nbsp;</td>
	      <td align="right" valign="middle">
		<html:button property="page"
		       onclick="moveAll(this.form,roles,rolesMap)"
		       style="width:65px"
		       alt="Clear">
		       <bean:message key="bms.gcsm.alert.new.label.button.clear"/>
		</html:button>
	      </td>
	    </tr>
	  </table>
	</td>
      </tr>
      <tr>
	<td height="5px" colspan="2">&nbsp;</td>
      </tr>
      <tr bgcolor="#F5F5F5">
	<td height="5px" colspan="2">&nbsp;</td>
      </tr>
      <tr bgcolor="#F5F5F5">
	<td width="100px" class="newAlertLabel" align="left" valign="top">
	  <bean:message key="bms.gcsm.alert.new.label.severity"/>
	  <span style="color:red; font-size:8pt">*</span>
	</td>
	<td style="color:red; text-transform:uppercase; padding-left:10px">
	  <bean:write name="alertForm" property="severity"/>
	</td>
      </tr>
      <tr bgcolor="#F5F5F5">
	<td height="3px" colspan="2">&nbsp;</td>
      </tr>
      <tr bgcolor="#F5F5F5">
	<td width="100px" class="newAlertLabel" align="left" style="vertical-align:top">
	  <bean:message key="bms.gcsm.alert.new.label.description"/>
	  <span style="color:red; font-size:8pt">*</span>
	</td>
	<td>
	  <html:textarea name="alertForm" property="description" rows="10" cols="80" style="border:1px solid #000; color: #000; font-family:Verdana; font-size:10; width:323; margin-left:10px; margin-right: 2 px">          </html:textarea>
	</td>
      </tr>
      <tr bgcolor="#F5F5F5">
	<td height="15px" colspan="2">
	  <html:errors/>
	</td>
      </tr>
      <tr>
	<td height="5px" colspan="2">&nbsp;</td>
      </tr>
      <tr>
	<td colspan="2" class="newAlertLabel">
	  <html:button property="page" onclick="javascript:window.close()" style="width:65px">
	    <bean:message key="bms.gcsm.alert.new.label.button.cancel"/>
	  </html:button>
	  &nbsp;&nbsp;&nbsp;&nbsp;
	  <html:submit style="width:65px"  onclick="buildForm(this.form)" property="page">
	    <bean:message key="bms.gcsm.alert.new.label.button.submit"/>
	  </html:submit>
	</td>
      </tr>
      <tr>
	<td height="5px" colspan="2">&nbsp;</td>
      </tr>
    </table>
    <div id="messages" style="vertical-align:top; margin-left:10px; margin-top:10px; font-family:Arial; font-size:8pt; color:red">
      <span style="font-size:9pt; vertical-align:bottom">*</span>
      = required
</div>
  </html:form>
</body>
</html:html>
