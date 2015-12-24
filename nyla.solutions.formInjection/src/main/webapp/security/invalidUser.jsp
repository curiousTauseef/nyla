<%@ taglib uri="GCSMUserAttribute" prefix="GCSMUserAttribute"%>

<head>
    <title> GCSM Login </title>
    <link rel="stylesheet" type="text/css" href="/gcsm/skins/default/css/clinsight.css">
</head>
<body topmargin="0%" leftmargin="10%" rightmargin="10%" >
<jsp:include page="header.jsp" flush="false" />
<jsp:include page="userInfo.jsp" flush="false" />
<p align="center" style="vertical-align: middle; padding-top: 50px;">
<table width="60%" style="border: 3px; border-color: #B6C4DA; border-style: solid;" cellpadding="5">
<!-- bgcolor="#074A87" -->
<TR>
      <TD align="center">
          <TABLE width="100%"  border="0" tyle="background-color: #DCDCDC;">
            <TR>
                <td class="ErrorMessage" align="left">You have been authenticated, but have either been deleted from LDAP, IMPACT, or have not yet been activated in clinSIGHT.<br><br> Please contact your system administrator for further assistance.
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
