<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<html>
<head>
<script src="<html:rewrite page='/skins/default/js/gcsm.js'/>" type="text/javascript"></script>
<link rel="stylesheet" href="<html:rewrite page="/skins/default/css/clinsight.css"/>" type="text/css">
<%--<title><tiles:getAsString name="title" /></title>--%>
<script src="<html:rewrite page='/skins/default/js/hashtable.js'/>" type="text/javascript"> </script>
<script src="<html:rewrite page='/skins/default/js/div_functions.js'/>" type="text/javascript"></script>
</head>
  <body topmargin="0" leftmargin="10" rightmargin="10">
    <table width="100%" align="center" cellspacing="0" cellpadding="0" border="0" style="border: solid 1px #074A87">
       <tr>
         <td width="100%">
           <div id="header">
              <tiles:insert attribute="header" flush="false"/>
           </div>
         </td>
       </tr>
       <tr>
         <td width="100%">
           <div id="userInfo">
              <tiles:insert attribute="userInfo" flush="false"/>
           </div>
         </td>
       </tr>
       <tr>
         <td width="100%">
           <div id="topnav">
              <tiles:insert attribute="topnav" flush="false"/>
           </div>
         </td>
       </tr>
       <tr>
        <td width="100%">
          <div id="main" style="position: relative; width:100%; overflow: none; border: solid 0px #CCCCFF">
            <table border="0" width="100%" cellspacing="0" cellpadding="0">
            <tr>
                <td colspan="2" style="background-color:#CCCCFF;height:3px">&nbsp;</td>
            </tr>
            <tr>
              <td width="100%">
                <div id="container" style="position: relative; width:100%; overflow: none; border: solid 0px #CCCCFF">
                  <table cellpadding="0" cellspacing="0" border="0" width="100%">
						  <tr style="background-color:#CCCCFF" height="15px">
						  	<td style="font-family:Verdana; font-size:12px; text-align: right;">
                         		<a href="#" onclick="showHideNav()"><div id="leftNavMinMax">&laquo;</div></a>
							</td>
							<td></td>
						  </tr>
                    <tr>
                      <td id="leftNavCell" height="350px" align="left" valign="top" style="width: 160px; background-color:#CCCCFF; border:solid 1px #CCCCFF; border-top:none">
                         <%-- Left Navigation --%>
                         <div id="leftnav">
                           <tiles:insert attribute="leftnav" flush="false"/>
                         </div>
                      </td>
                      <td width="100%" bgcolor="#FFFFFF" align="left" valign="top">
                        <div id="body">
                        <table cellspacing="0" cellpadding="0" width="100%">
                          <tr>
                            <td align="left" valign="top">
                                <tiles:insert attribute="body" flush="false"/>
                            </td>
                          </tr>
                        </table>
                        </div>
                      </td>
                    </tr>
                  </table>
                </div>
              </td>
            </tr>
            </table>
          </div>
        </td>
       </tr>
       <tr>
        <td width="100%">
          <div align="left" id="footer" style="position: relative; width:100%; overflow: none; border: solid 2px #FFFFFF">
            <tiles:insert attribute="footer" flush="false"/>
          </div>
        </td>
       </tr>
    </table>
    <tiles:insert attribute="extra" flush="false" ignore="true"/>
  </body>
</html>
