<%--
  User: Dmane
  Date: Jun 1, 2005
  Time: 1:35:21 PM
  Comments:Index.jsp redirects page to home page
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://gcsm.bms.com/tld/security.tld" prefix="security"%>
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
          <td align="left" colspan="3">
              <div id="header">
              <tiles:insert flush="false" attribute="header" />
              </div>
          </td>
      </tr>
      <%-- ===========================================================  --%>
      <%--    USER_INFO PORTLET                                         --%>
      <%-- ===========================================================  --%>
      <tr>
          <td align="left" colspan="3">
              <div id="userInfo">
                 <tiles:insert flush="false" attribute="userInfo" />
              </div>
          </td>
      </tr>
    <%-- ===========================================================  --%>
    <%--    TOP_NAV PORTLET                                           --%>
    <%-- ===========================================================  --%>
    <tr>
        <td align="left">
            <div id="topnav" style="border-bottom:5px solid #CCCCFF">
              <tiles:insert flush="false" attribute="topnav" />
            </div>
       </td>
       <td width="3">&nbsp;</td>
    </tr>
    <tr>
       <td width="100%">&nbsp;</td>
    </tr>

      <%-- ===========================================================  --%>
      <%--    MAIN BODY PORTLET                                         --%>
      <%-- ===========================================================  --%>
      <tr>
          <td align="left" colspan="3">
              <div id="admin_body">
                 <tiles:insert flush="false" attribute="body" />
              </div>
          </td>

      </tr>
      <tr>
          <td colspan="3">
             <div align="left" id="footer" style="position: relative; width:100%; overflow: none; border: solid 2px #FFFFFF">
                  <tiles:insert flush="false" attribute="footer" />
             </div>
         </td>
     </tr>
  </table>
</body>
</html>

