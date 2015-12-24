<%--
  User: $Id
  Date: Jun 21, 2005
  Time: 4:43:35 PM
  Comments:$NAME
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<%-- $Id$ --%>

<html>
            <%-- ===========================================================  --%>
            <%--    PAGE HEADER                                               --%>
            <%-- ===========================================================  --%>
<head>
<script src="<html:rewrite page='/skins/default/js/gcsm.js'/>" ></script>
<link rel="stylesheet" href="<html:rewrite page="/skins/default/css/clinsight.css"/>" type="text/css">
<title><tiles:getAsString name="title" /></title>
<script src="<html:rewrite page='/skins/default/js/hashtable.js'/>"> </script>
<script src="<html:rewrite page='/skins/default/js/div_functions.js'/>"></script>
  </head>
  <body topmargin="0%" leftmargin="10%" rightmargin="10%">
      <table width="100%" align="center" valign="top" cellspacing="0" border="0" cellpadding="0" style="border: solid 5px #074A87">
          <%-- ===========================================================  --%>
          <%--    HEADER PORTLET                                            --%>
          <%-- ===========================================================  --%>
          <tr>
              <td align="left">
                  <div id="header">
                  <tiles:insert flush="false" attribute="header" />
                  </div>
              </td>
          </tr>
          <tr>
              <td align="left">
                  <div id="body">
                  <tiles:insert flush="false" attribute="body" />
                  </div>
              </td>
          </tr>
          <tr>
              <td align="left">
                  <div id="footer">
                  <tiles:insert flush="false" attribute="footer" />
                  </div>
              </td>
          </tr>
    </table>
</body>
</html>
