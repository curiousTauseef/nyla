<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="com.bms.informatics.gcsm.common.util.*,com.bms.informatics.gcsm.common.web.util.*, java.util.*,com.bms.informatics.gcsm.protocol.web.*" %>
<%
    String landingPage="";
    landingPage=request.getParameter("landingPage");
    String adminAction="";
    String adminEmailSortAction="";
    String adminNameSortAction="";
    if (landingPage == null) {
         landingPage="home";
        adminAction="/AdminPanelAction?method=doFilter";
    } else {
        adminAction="/AdminPanelAction?method=doFilter&landingPage="+landingPage;
    }

adminNameSortAction="/AdminPanelAction.do?method=sort&sortField=staffName&landingPage="+landingPage;
adminEmailSortAction="/AdminPanelAction.do?method=sort&sortField=email&landingPage="+landingPage;

%>

<script language="JavaScript">
function openNew(uid) {

	window.open('RoleSelectorAction?method=showRoles&uid='+uid, 'myWin', 'toolbar=no, directories=no, location=no, status=yes, menubar=no, resizable=no, scrollbars=no, width=300, height=80');
}
</script>

<html:form action="<%=adminAction%>">
        <%-- Protlet Container --%>
            <table align="left" border="0" cellpadding="0" cellspacing="0"  id="admin_body" width="100%">
                <%-- Header --%>
                <tr>
                    <td class="portTitle" height="29">
                      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="admin_filter">
                        <tr class="portFilterLbl">
                        <td width="15"><html:img  border="0" page="/skins/default/images/phead_left.gif"/></td>
                        <td class="portTitle" width="30">STAFF</td>
                        <td width="20">&nbsp;</td>
                        <td width="10"><html:img  border="0" page="/skins/default/images/blank.gif" width="10" height="1"/></td>
                         <td width="60" class="portFilterLbl" nowrap>&nbsp;</td>
                                <td width="40">
<%--                                <logic:notEmpty name="adminPanelForm" property="staffNamesList">--%>
<%--                                <html:select name="adminPanelForm" property="staffLoginId"  styleClass="portFilter" onchange="submitForm(this.form);" >--%>
<%--                                    <bean:define id="staffNamesList"  name="adminPanelForm" property="staffNamesList" />--%>
<%--                                     <html:option value="-1">All</html:option>--%>
<%--                                    <html:options collection="staffNamesList"--%>
<%--                                                  property="value"--%>
<%--                                                  labelProperty="label" />--%>
<%--                                </html:select>--%>
<%--                                </logic:notEmpty>--%>
<%--                                <logic:empty name="adminPanelForm" property="staffNamesList">--%>
<%--                                 <html:select name="adminPanelForm" property="staffLoginId"  styleClass="portFilter" onchange="submitForm(this.form);" >--%>
<%--                                    <html:option value="-1">--No Users--</html:option>--%>
<%--                                   </html:select>--%>
<%--                                </logic:empty>--%>
                                </td>
                        <td width="40">&nbsp;</td>
                        <td width="100" class="portFilterLbl" nowrap>&nbsp;</td>
                        <td width="40">
<%--                        <logic:notEmpty name="adminPanelForm" property="staffEmailList">--%>
<%--                        <html:select name="adminPanelForm" property="staffEmail"  styleClass="portFilter" onchange="submitForm(this.form);" >--%>
<%--                            <bean:define id="staffEmailList"  name="adminPanelForm" property="staffEmailList" />--%>
<%--                             <html:option value="-1">All</html:option>--%>
<%--                            <html:options collection="staffEmailList"--%>
<%--                                          property="value"--%>
<%--                                          labelProperty="label" />--%>
<%--                        </html:select>--%>
<%--                        </logic:notEmpty>--%>
<%--                        <logic:empty name="adminPanelForm" property="staffEmailList">--%>
<%--                         <html:select  name="adminPanelForm" property="staffEmail"  styleClass="portFilter" onchange="submitForm(this.form);" >--%>
<%--                            <html:option value="-1">--No Users--</html:option>--%>
<%--                           </html:select>--%>
<%--                        </logic:empty>--%>
<%----%>
                        </td>
                        <td width="100%"><!-- leave this blank for expansion/contraction --></td>
                        <td width="70"><html:img  border="0" page="/skins/default/images/blank.gif"/></td>
                        <td width="10"><html:img  border="0" page="/skins/default/images/blank.gif" width="10" height="1"/></td>
<%--                        <td width="15"><html:img  border="0" page="/skins/default/images/phead_expand_on.gif" onclick="resizePortlet('admin');"  style="cursor:hand"/></td>--%>
                        <td width="15"><html:img  border="0" page="/skins/default/images/phead_rt.gif"/></td>
                        </tr>
                    </table>
                    </td>
                </tr>
                <tr>
                    <td>
<div id="inner_admin" style="overflow: auto; height: 400px; border: solid 1px #CCCCFF">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" id="admin_data">
            <thead>
            <tr class="noScroll">
                <td class="portColHdr" width="120">Name</td>
<%--                <html:link action="<%=adminNameSortAction%>"--%>
<%--                           styleClass="LinkUline"--%>
<%--                            target="_self">Name</html:link>--%>

                <td class="portColHdr" width="120">Email</td>
<%--                <html:link action="<%=adminEmailSortAction%>"--%>
<%--                           styleClass="LinkUline"--%>
<%--                            target="_self">Email</html:link></td>--%>

                <td class="portColHdr" width="100%">&nbsp;</td>
            </tr>
            </thead>
            <tbody>
    <logic:notEmpty name="adminPanelForm" property="staffDetailList">
        <nested:iterate   name="adminPanelForm" property="staffDetailList"
                                                indexId="i" id="UserOverview">
                <tr>
                    <c:set var="uid" scope="page">
                        <nested:write  property="loginID"/>
                    </c:set>

                    <td class="portBody">
                    <nested:notEmpty  property="lastName">
                    <nested:notEmpty  property="firstName">
                         <a href="javascript:openNew('<nested:write  property="loginID"/>')">
                         <nested:write property="lastName"/>,<nested:write property="firstName"/>
                         </a>
                    </nested:notEmpty>
                    <nested:empty  property="lastName">
                        &ndash;
                    </nested:empty>

                    </nested:notEmpty>
                    <nested:empty  property="firstName">
                        &ndash;
                    </nested:empty>
                    </td>
                    <td class="portBody">
                    <nested:notEmpty   property="email">
                     <a href="mailto:<nested:write property="email"/>"><nested:write property="email"/></a>
                    </nested:notEmpty>
                    <nested:empty  property="email">
                        &ndash;
                    </nested:empty>
                     <nested:hidden property="email"/>
                    </td>
                    <td class="portBody" width="100%">&nbsp;</td>
                </tr>
   </nested:iterate>
  </logic:notEmpty>
  <logic:empty name="adminPanelForm" property="staffDetailList">
                <td class="portBody" colspan="3">No Data Found</td>
  </logic:empty>

        </tbody>
        </table>
</div>
                    </td>
                </tr>
            </table>
    </html:form>

