<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page contentType="text/html; charset=windows-1252"%>
<html:html>
  <head>
    <title>
      Alert
    </title>
    <html:base/>
    <link href="<html:rewrite page="/skins/default/css/clinsight.css"/>" rel="stylesheet" type="text/css"/>
  </head>
  <body>
    <html:form action="/AlertAction?method=removeAlert">
      <table width="100%" cellpadding="0" cellspacing="0" border="0" style="border:1px solid #F30">
	<tr style="background-color:#F30">
	    <td align="left" valign="middle" width="30%" height="30px">
		<span style="font-family:Verdana; font-weight:bold; font-size:16; padding-left:10px">Alert</span>
	    </td>
	    <td align="right">
		<span style="padding-right:10px"><html:img page="/skins/default/images/icon_alerts.gif" /></span>
	    </td>
	</tr>
	<tr>
	    <td width="30%" align="left" valign="middle" height="30px">
		<span style="font-family:Verdana; font-weight:bold; font-size:10; padding-left:10px">
		    Protocol/Site
		</span>
	    </td>
	    <td width="70%" align="left" valign="middle">
		<span style="font-family:Verdana; font-size:10; padding-left:10px">
		  <bean:write name="alertForm" property="protocol"/>-
		  <bean:write name="alertForm" property="site"/>
		</span>
	    </td>
	</tr>
	<tr>
	    <td width="30%" align="left" valign="middle" height="30px">
		<span style="font-family:Verdana; font-weight:bold; font-size:10; padding-left:10px">
		    Alert Created
		</span>
	    </td>
	    <td width="70%" align="left" valign="middle">
		<span style="font-family:Verdana; font-size:10; padding-left:10px">
		  <bean:write name="alertForm" property="dateCreated"/>
		</span>
	    </td>
	</tr>
	<tr style="background-color:#CCC">
	    <td width="30%" align="left" valign="middle" height="30px">
		<span style="font-family:Verdana; font-weight:bold; font-size:10; padding-left:10px">
		    Severity
		</span>
	    </td>
	    <td width="70%" align="left" valign="middle">
		<span style="font-family:Verdana; font-size:10; padding-left:10px">
		  <bean:write name="alertForm" property="severity"/>
		</span>
	    </td>
	</tr>
	<tr style="background-color:#CCC">
	    <td width="30%" align="left" valign="top" height="120px">
		<span style="font-family:Verdana; font-weight:bold; font-size:10; padding-top: 10px; padding-left:10px">
		    Description
		</span>
	    </td>
	    <td width="70%" align="left" valign="top">
		<span style="font-family:Verdana; font-size:10; padding-left:10px">
		  <bean:write name="alertForm" property="description"/>
		  <br />
          <br />
          <br />

		  <logic:notEmpty name="alertForm" property="formLink">
           <bean:define id="formId" name="alertForm" property="event_name"/>
           <bean:define id="siteId" name="alertForm" property="siteID"/>
           <bean:define id="formTypeCode" name="alertForm" property="formTypeCode"/>
   <a id="formLink" href="#" onclick='window.opener.location="/gcsm/do/formAction?&op=view&formID=<%=formId%>&siteID=<%=siteId%>&formTypeCode=<%=formTypeCode%>";return false;'>
                    <bean:write name="alertForm" property="formLink"/></a>
<%--            <a href="/gcsm/do/formAction?&op=view&formID=<%=formId%>&siteID=<%=siteId%>" TARGET="main">--%>
<%--          <bean:write name="alertForm" property="formLink"/></a>--%>
		    <%--html:link target="_parent" forward="/do/formAction?op=view"
		       paramId="formID" paramProperty="event_name"
		      style="font-family:Verdana; font-size:10; padding-left:10px">
		      <bean:write name="alertForm" property="formLink"/>
		    </html:link--%>
		  </logic:notEmpty>
		</span>
	    </td>
	</tr>

	<tr>
	    <td colspan="2" align="left" valign="middle" style="padding-left:10px" height="40px">
		<input type="button" value="Retain" onclick="window.close();"/>&nbsp;&nbsp;
		<c:choose>
		  <c:when test='${alertForm.canDismiss == "Y"}'>
		      <html:submit value="Remove" />
		  </c:when>
		  <c:otherwise>
		      <html:submit value="Remove" disabled="true"/>
		  </c:otherwise>
		</c:choose>
	    </td>
	</tr>
      </table>
    </html:form>
  </body>
</html:html>
