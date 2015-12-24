<%@ taglib uri="GCSMUserAttribute" prefix="GCSMUserAttribute"%>

<head>
    <title> GCSM Login </title>
    <link rel="stylesheet" type="text/css" href="/gcsm/skins/default/css/clinsight.css">
</head>
<body topmargin="0%" leftmargin="10%" rightmargin="10%" >
<jsp:include page="header.jsp" flush="false" />
<jsp:include page="userInfo.jsp" flush="false" />
<GCSMUserAttribute:loginAttempt id="gcsmuser" action="increment"/>
<p align="center" style="vertical-align: middle; padding-top: 50px;">
<table width="30%" style="border: 3px; border-color: #B6C4DA; border-style: solid;" cellpadding="5">
<!-- bgcolor="#074A87" -->
<TR>
      <TD align="center">
          <TABLE width="100%"  border="0" tyle="background-color: #DCDCDC;">
            <TR>
                <td class="ErrorMessage" align="left" nowrap>
                  Authentication Failed, Please try again.
                  <a class="ErrorMessage:link" href='<%= response.encodeURL("login.jsp") %>'>Login</a>
                 </td>
             </tr>
          </table>
      </TD>
   </TR>
</table>

</p>
<%--<%="Login Id used is"+loginId%>--%>


</body>
</html>
