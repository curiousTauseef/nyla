<%--
  User: DMane
  Date: Apr 27, 2005
  Time: 10:08:30 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://myfaces.sourceforge.net/tld/myfaces_ext_0_9.tld" prefix="x"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<f:view>
    <f:verbatim>
        <table border="0" width="100%"  height="100"  cellspacing="0" cellpadding="0">
            <tr>
                <td colspan="2" style="background-color:#CCCCFF" height="10">&nbsp;</td>
            </tr>
            <tr>
                <td width="50" style="background-color:#CCCCFF">
                <%-- Left Navigation --%>
                    <div id="main" style="position: relative; width:100%; overflow: none; border: solid 0px #CCCCFF">
                        <table cellspacing="0" cellpadding="0" style="border: solid 2px #CCCCFF" width="100%" >
                            <tr>
				                <td width="100%" valign="top">
				                    <div align="container" id="container" style="position: relative; width:100%; overflow: none; border: solid 2px #CCCCFF">
                                        <f:subview id="leftNav">
                                            <tiles:insert attribute="leftNav" flush="false"/>
                                        </f:subview>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>
                </td>
                <td style="background-color:#FFF">
                <%-- Main Content --%>
                    <div id="main" style="position: relative; width:100%; overflow: none; border: solid 0px #CCCCFF">
                        <table cellspacing="0" cellpadding="0" style="border: solid 2px #CCCCFF" width="100%" >
                            <tr>
				                <td width="100%" valign="top">
				                    <div align="container" id="container" style="position: relative; width:100%; overflow: none; border: solid 2px #CCCCFF">
                                        <f:subview id="content">
                                            <tiles:insert attribute="content" flush="false"/>
                                        </f:subview>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>
                </td>
            </tr>
        </table>
    </f:verbatim>
</f:view>