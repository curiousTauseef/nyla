<%@page isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/gcsm/skins/default/css/clinsight.css" type="text/css"/>
<table width="100%" cellpadding="0" cellspacing="0" border="0" style="margin-top:0px; margin-left:0px;">
  <tr>
    <td class="header" width="100%">
      <a href="<c:url value="/"/>"></a>
    </td>
  </tr>
  <tr>
    <td height="350px" width="100%" valign="top" style="padding-top:30px; padding-left:30px; font-family:Verdana; font-size:10px">
      <h2>500 Error</h2>
      <br />
      An internal error occured that has prevented the rendering of this page.
      Please report to the site administrator.
      
      <!-- 
    ERROR:     <c:out value="${pageContext.exception}"/>
       -->
    </td>
  </tr>
</table>
