<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/gcsm/skins/default/css/clinsight.css" type="text/css"/>
<table width="100%" cellpadding="0" cellspacing="0" border="0" style="margin-top:0px; margin-left:0px;">
  <tr>
    <td class="header" width="100%">
      <html:img  border="0" page="/skins/default/images/logo_clinsite_old.gif" width="117" height="57"/>
    </td>
  </tr>
  <tr>
    <td height="350px" width="100%" valign="top" style="padding-top:30px; padding-left:30px; font-family:Verdana; font-size:10px">
      <h2>Error</h2>
      <br />
      An error has been generated by the application.
    </td>
  </tr>
  <tr>
    <td class="statusBarFont" style="border: solid 2px #CCCCFF">
                BMS Confidential <br>
                The information contained in this application is intended for distribution to Bristol-Myers Squibb employees and contractors only. You are expressly prohibited from forwarding all or part of this information or otherwise sharing its contents with anyone outside the company. This document should be disposed of properly.
                
                </p>
      <!-- 
    ERROR:     <c:out value="${pageContext.exception}"/>
       -->                
    </td>
  </tr>
</table>
