
<html>
<head>
    <title> GCSM Login </title>
    <link rel="stylesheet" type="text/css" href="/gcsm/skins/default/css/clinsight.css">
<%--<SCRIPT language="JavaScript">--%>
<%----%>
<%--	function SubmitForm() {--%>
<%--	    if(checkForm())--%>
<%--	    {--%>
<%--	       document.forms[0].submit();--%>
<%--	    }--%>
<%--	}--%>
<%--    function submitOnEnter(event, submitFunction)--%>
<%--    {--%>
<%--        var code = 0;--%>
<%--        code = event.keyCode;--%>
<%--        alert("Key Code :"+code);--%>
<%--        if (code==13)--%>
<%--        {--%>
<%--            //alert("Calling :"+submitFunction);--%>
<%--            eval(submitFunction());--%>
<%--        }--%>
<%--    }--%>
<%--</script>--%>
<!-- onKeyPress="submitOnEnter(window.event,SubmitForm)" -->
</head>
<body topmargin="0%" leftmargin="10%" rightmargin="10%" >
<jsp:include page="header.jsp" flush="false" />
<jsp:include page="userInfo.jsp" flush="false" />
<form method="POST" action='j_security_check' >
<p align="center" style="vertical-align: middle; padding-top: 50px;">
<table width="30%" style="border: 3px; border-color: #B6C4DA; border-style: solid;" cellpadding="5">
<!-- bgcolor="#074A87" -->
<TR>
      <TD align="center">
          <TABLE width="100%"  border="0" tyle="background-color: #DCDCDC;">
            <TR>
                <TD colspan="2" class="MainLink_Text_ActiveLink" nowrap>
                   Enter your username and password to sign in
                 </td>
             </tr>
            <tr>
               <td class="MainLink_Text_ActiveLink" align="right">
                    Username:</td>
              <td align="left"><input type="text" name="j_username"></td>
            </tr>
            <tr>
               <td class="MainLink_Text_ActiveLink" align="right">
                Password:
              </td>
              <td align="left"><input type="password" name="j_password"></td>
            </tr>
            <tr align="center">
              <td align="right"><input type="submit" value="Log In"></td>
              <td align="left"><input type="reset"></td>
            </tr>
          </table>

      </TD>
   </TR>
</table>

</p>

</form>
</body>
</html>
