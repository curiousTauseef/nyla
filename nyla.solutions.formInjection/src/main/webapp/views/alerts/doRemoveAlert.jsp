<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page contentType="text/html; charset=windows-1252"%>
<html:html>
  <head>
    <title><bean:message key="bms.gcsm.alert.new.title"/></title>
    <link rel="stylesheet"
	  href="<html:rewrite page="/skins/default/css/clinsight.css"/>"
	  type="text/css">
  </head>
  <script language="javascript" type="text/javascript">
    function reloadParent() {
      window.opener.location.reload(true);
    }
  </script>
  <body onload="reloadParent()">
    <h2>
      <%=request.getParameter("opStatus").equalsIgnoreCase("ALERT_REMOVE_SUCCESS")?"Alert Removed":(request.getParameter("opStatus").equalsIgnoreCase("ALERT_RETAIN_SUCCESS"))?"Alert Retained":"Alert Remove Failed"%>
    </h2>
    <input type="button" onclick="window.close()" value="Close"/>
  </body>
</html:html>
