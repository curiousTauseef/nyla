<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<head>
<head>
<script src="<html:rewrite page='/skins/default/js/gcsm.js'/>" ></script>
<link rel="stylesheet" href="<html:rewrite page="/skins/default/css/clinsight.css"/>" type="text/css">
<script src="<html:rewrite page='/skins/default/js/hashtable.js'/>"> </script>
<script src="<html:rewrite page='/skins/default/js/div_functions.js'/>"></script>
</head>
<body topmargin="0%" leftmargin="10%" rightmargin="10%">
    <table align="center" cellspacing="0" cellpadding="0"  style="border: solid 1px #074A87">
       <tr><td>
           <div id="header">
              <tiles:insert attribute="header" flush="false"/>
           </div></td>
       </tr>
       <tr><td>
           <div id="userInfo">
              <tiles:insert attribute="userInfo" flush="false"/>
           </div></td>
       </tr>
       <tr><td>
           <div id="topnav">
              <tiles:insert attribute="topnav" flush="false"/>
           </div></td>
       </tr>
       <tr>
        <td width="100%">
        <div id="main" style="position: relative; width:100%; overflow: none; border: solid 0px #CCCCFF">
        <table border="0" width="100%"  height="100"  cellspacing="0" cellpadding="0">
            <tr>
                <td colspan="2" style="background-color:#CCCCFF" height="10">&nbsp;</td>
            </tr>
            <tr>
                <td width="160" align="left" valign="top" style="background-color:#CCCCFF; border:solid 1px #FFFFFF; border-top:none">
                <div style="text-align: right">??</div>??
                <%-- Left Navigation --%>
                             <tiles:insert attribute="leftNav" flush="false"/>
                </td>
                <td style="background-color:#FFF">
                <div id="container" style="position: relative; width:100%; overflow: none; border: solid 0px #CCCCFF">
                    <table cellspacing="0" cellpadding="0" style="border: solid 2px #CCCCFF" width="100%" >
                        <tr>
                            <td width="100%" valign="top">
                             <div align="container" id="container" style="position: relative; width:100%; overflow: none; border: solid 2px #CCCCFF">
                                <table width="100%" >
                                   <tr>
                                        <td colspan="2" valign="top">
                                            <div align="center" id="overview" style="position: relative; overflow: none; width:100%; border: solid 2px #FFFFFF">

                                                <tiles:insert attribute="overview" flush="false"/>

                                           </div>
                                        </td>
                                   </tr>
                                   <tr>
                                        <td width="65%" valign="top" align="left" style="background-color:#FFF">
                                            <div align="center" id="site_reports" style="position:relative;">

                                                <tiles:insert attribute="site_reports" flush="false"/>

                                            </div>
                                        </td>
                                        <td width="35%" valign="top" align="right">
                                            <div align="center" id="visits" style="position:relative;">

                                                <tiles:insert attribute="visits" flush="false"/>

                                           </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" valign="top" >
                                            <div align="center" id="issues" style="position:relative;">

                                                <tiles:insert attribute="issues" flush="false"/>

                                            </div>
                                        </td>
                                     </tr>
                                     <tr>
                                        <td colspan="2" valign="top" >
                                            <div align="center" id="alerts" style="position:relative;">

                                                <tiles:insert attribute="alerts" flush="false"/>

                                            </div>
                                        </td>
                                     </tr>
                                  </table>
                                </div>
                            <%--   Container Div Ends Here --%>
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
</body>
