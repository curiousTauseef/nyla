<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="com.bms.informatics.gcsm.common.util.*,com.bms.informatics.gcsm.common.web.util.*, java.util.*,com.bms.informatics.gcsm.protocol.web.*" %>
<%
    String landingPage="";
    String uid="";
    String siteId="";
    String uidUrl="";
    String siteIdUrl="";
    String role="";
    String roleUrl="";

    landingPage=request.getParameter("landingPage");
    uid=request.getParameter("uid");
    siteId=request.getParameter("siteId");
    role=request.getParameter("role");
    
    String staffAction="";
    String staffProtocolSiteSortAction="";
    String staffRoleSortAction="";
    String staffNameSortAction="";
    String staffStatusSortAction="";
    
    if (uid != null) {
       uidUrl = "&uid="+uid;
    } else {
        uidUrl="";
    }
    if (siteId != null) {
       siteIdUrl = "&siteId="+siteId;
    } else {
        siteIdUrl = "";
    }
    if (role != null) {
       roleUrl = "&role="+role;
    } else {
        roleUrl = "";
    }

    if (landingPage == null) {
         landingPage="home";
        staffAction="/StaffPanelAction?method=doFilter";
    } else {
        staffAction="/StaffPanelAction?method=doFilter&landingPage="+landingPage;
    }

/*
staffNameSortAction="/StaffPanelAction.do?method=sort&sortField=username&landingPage="+landingPage;
staffRoleSortAction="/StaffPanelAction.do?method=sort&sortField=roleCd&landingPage="+landingPage;
staffProtocolSiteSortAction="/StaffPanelAction.do?method=sort&sortField=studySiteId&landingPage="+landingPage;
staffStatusSortAction="/StaffPanelAction.do?method=sort&sortField=studySiteStatus&landingPage="+landingPage;
*/
staffNameSortAction="javascript:sortBy('staff_data', 0)";
staffRoleSortAction="javascript:sortBy('staff_data', 1)";
staffProtocolSiteSortAction="javascript:sortBy('staff_data', 2)";
staffStatusSortAction="javascript:sortBy('staff_data', 4)";

%>

<html:form action="<%=staffAction%>">
        <%-- Protlet Container --%>
            <table align="left" border="0" cellpadding="0" cellspacing="0"  id="staff_body" width="100%">
                <%-- Header --%>
                <tr>
                    <td class="portTitle" height="29">
                      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="staff_filter">
                        <tr class="portFilterLbl">
                        <td width="15"><html:img  border="0" page="/skins/default/images/phead_left.gif"/></td>
                        <td class="portTitle" width="30">STAFF</td>
                        <td width="20">&nbsp;</td>
                        <td width="10"><html:img  border="0" page="/skins/default/images/blank.gif" width="10" height="1"/></td>
                         <td width="60" class="portFilterLbl" nowrap>Protocol-Site</td>
                                <td width="50">
                                <logic:notEmpty name="staffPanelForm" property="staffProtocolList">
                                <html:select name="staffPanelForm" property="siteId"  styleClass="portFilter" onchange="processFilters(this.form,'siteId,roleCd.value,userId,staffStatusId','staff_data');" >
                                    <bean:define id="staffProtocolList"  name="staffPanelForm" property="staffProtocolList" />
                                     <html:option value="-1">All</html:option>
                                    <html:options collection="staffProtocolList"
                                                  property="value"
                                                  labelProperty="label" />
                                </html:select>
                                </logic:notEmpty>
                                <logic:empty name="staffPanelForm" property="staffProtocolList">
                                 <html:select name="staffPanelForm" property="siteId"  styleClass="portFilter" onchange="processFilters(this.form,'siteId,roleCd.value,userId,staffStatusId','staff_data');" >
                                    <html:option value="-1">All</html:option>
                                   </html:select>
                                </logic:empty>
                                </td>

                        <td width="50" class="portFilterLbl">&nbsp;&nbsp;Role&nbsp;&nbsp;</td>
                        <td width="40">
                        <logic:notEmpty name="staffPanelForm" property="staffRolesList">
                        <html:select name="staffPanelForm" property="roleCd"  styleClass="portFilter" onchange="processFilters(this.form,'siteId,roleCd.value,userId,staffStatusId','staff_data');" >
                            <bean:define id="staffRolesList"  name="staffPanelForm" property="staffRolesList" />
                             <html:option value="-1">All</html:option>
                            <html:options collection="staffRolesList"
                                          property="value"
                                          labelProperty="label" />
                        </html:select>
                        </logic:notEmpty>
                        <logic:empty name="staffPanelForm" property="staffRolesList">
                         <html:select  name="staffPanelForm" property="roleCd"  styleClass="portFilter" onchange="processFilters(this.form,'siteId,roleCd.value,userId,staffStatusId','staff_data');" >
                            <html:option value="-1">All</html:option>
                           </html:select>
                        </logic:empty>

                        </td>
                        <td width="10"><html:img  border="0" page="/skins/default/images/blank.gif" width="10" height="1"/></td>
                        <td width="40" class="portFilterLbl">&nbsp;&nbsp;Name&nbsp;&nbsp;</td>
                        <td width="50">
                        <logic:notEmpty name="staffPanelForm"   property="staffNamesList">
                        <html:select name="staffPanelForm" property="userId"  styleClass="portFilter" onchange="processFilters(this.form,'siteId,roleCd.value,userId,staffStatusId','staff_data');">
                            <bean:define id="staffNamesList"  name="staffPanelForm" property="staffNamesList" />
                             <html:option value="-1">All</html:option>
                            <html:options collection="staffNamesList"
                                                name="staffPanelForm"
                                                property="value"
                                                labelProperty="label"/>

                        </html:select>
                        </logic:notEmpty>
                        <logic:empty name="staffPanelForm"   property="staffNamesList">
                         <html:select  name="staffPanelForm" property="userId"  styleClass="portFilter" onchange="processFilters(this.form,'siteId,roleCd.value,userId,staffStatusId','staff_data');">
                            <html:option value="-1">All</html:option>
                           </html:select>
                        </logic:empty>

                        </td>
                        <td width="10"><html:img  border="0" page="/skins/default/images/blank.gif" width="10" height="1"/></td>
                        <td width="50" class="portFilterLbl">&nbsp;&nbsp;Status&nbsp;&nbsp;</td>
                        <td width="60">
                        <logic:notEmpty name="staffPanelForm"   property="staffStatusList">
                            <html:select name="staffPanelForm" property="staffStatusId"  styleClass="portFilter" onchange="processFilters(this.form,'siteId,roleCd.value,userId,staffStatusId','staff_data');" >
                                <bean:define id="staffStatusList"  name="staffPanelForm" property="staffStatusList" />
                                 <html:option value="-1">All</html:option>
                                <html:options collection="staffStatusList"
                                              property="value"
                                              labelProperty="label" />
                            </html:select>
                         </logic:notEmpty>
                        <logic:empty name="staffPanelForm"   property="staffStatusList">
                         <html:select  name="staffPanelForm" property="staffStatusId"  styleClass="portFilter" onchange="processFilters(this.form,'siteId,roleCd.value,userId,staffStatusId','staff_data');" >
                            <html:option value="-1">All</html:option>
                           </html:select>
                        </logic:empty>

                            </td>
                        <td width="100%"><!-- leave this blank for expansion/contraction --></td>
                        <td width="70"><html:img  border="0" page="/skins/default/images/blank.gif"/></td>
                        <td width="10"><html:img  border="0" page="/skins/default/images/blank.gif" width="10" height="1"/></td>
                        <td width="15"><html:img  border="0" page="/skins/default/images/phead_expand_on.gif" onclick="resizePortlet('staff');"  style="cursor:hand"/></td>
                        <td width="15"><html:img  border="0" page="/skins/default/images/phead_rt.gif"/></td>
                        </tr>
                    </table>
                    </td>
                </tr>
                <tr>
                    <td>
<div id="inner_staff" style="overflow: auto; height: 100px; border: solid 1px #CCCCFF">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" id="staff_data">
            <thead>
            <tr class="noScroll">
                <td class="portColHdr">
                <a href="<%=staffNameSortAction%>"
                           class="LinkUline"
                            target="_self">Name</a>

                </td>
                <td class="portColHdr" width=5>&nbsp;</td> 
                <td class="portColHdr">
                <a href="<%=staffRoleSortAction%>"
                           class="LinkUline"
                            target="_self">Role</a></td>

		<td class="portColHdr" width=5>&nbsp;</td> 
                <td class="portColHdr">
                <a href="<%=staffProtocolSiteSortAction%>"
                           class="LinkUline"
                            target="_self">Protocol/Site</a>

                </td>
                <td class="portColHdr" width=5>&nbsp;</td>           

               
                <td class="portColHdr">Investigator</td>
                
                <td class="portColHdr" width=5>&nbsp;</td> 
                <td class="portColHdr" >
                <a href="<%=staffStatusSortAction%>"
                           class="LinkUline"
                            target="_self">Status</a></td>
		<td class="portColHdr" width=5>&nbsp;</td>                             
		<td class="portColHdr">Close Date</td>
		<td class="portColHdr" width=5>&nbsp;</td> 
		<td class="portColHdr">E/R</td>
		
		
            </tr>
            </thead>
            <tbody class="noScroll">
    <logic:notEmpty name="staffPanelForm" property="staffDetailList">
        <nested:iterate   name="staffPanelForm" property="staffDetailList"
                                                indexId="i" id="staffPanelOverView">
                <tr style="display:none">
                
                    <td class="portBody">
                    <c:set var="uid" scope="page">
                    <nested:write  property="loginId"/>
                    </c:set>
                    <c:set var="role" scope="page">
                    <nested:write  property="roleCd"/>
                    </c:set>

                    <nested:notEmpty  property="username">
                         <html:link action='<%= "/StaffPanelAction?method=initStaff&landingTab=staff&landingPage=staff&uid="+ pageContext.getAttribute("uid") + "&role="+pageContext.getAttribute("role")%>' styleClass="LinkUline" target="_top">
                         <nested:write property="username"/>
                         </html:link>
                    </nested:notEmpty>
                    <nested:empty  property="username">
                        &ndash;
                    </nested:empty>
                     <nested:hidden  property="username"/>
                    </td>
                    <td class="portBody">&nbsp;</td>
                    <td class="portBody">
                    <nested:notEmpty   property="roleCd">
                        <nested:write property="roleCd"/>
                    </nested:notEmpty>
                    <nested:empty  property="roleCd">
                        &ndash;
                    </nested:empty>
                     <nested:hidden property="roleCd"/>
    
                    </td>
                    <td class="portBody">&nbsp;</td>
    
                    <td class="portBody">
                    <c:set var="siteId" scope="page">
                    <nested:write  property="studySiteId"/>
                    </c:set>
                    <c:set var="role" scope="page">
                    <nested:write  property="roleCd"/>
                    </c:set>
                <c:choose>
                    <c:when test='${param.landingPage == "staff"}'>
                        <nested:notEmpty  property="siteProtocolNbr">
                         <html:link action='<%= "/SitePanelAction?method=initSite&landingTab=overview&landingPage=staffSite&siteId="
                                                + pageContext.getAttribute("siteId")
                                                +"&uid="+ pageContext.getAttribute("uid")
                                                +"&role="+pageContext.getAttribute("role")%>' styleClass="LinkUline" target="_top">
                         <nested:write  property="siteProtocolNbr"/>
                         </html:link>
                        </nested:notEmpty>
                </c:when>
                <c:when test='${param.landingPage == "adminStaff"}'>
                        <nested:notEmpty  property="siteProtocolNbr">
                         <html:link action='<%= "/SitePanelAction?method=initSite&landingTab=overview&landingPage=adminSite&siteId="
                                                + pageContext.getAttribute("siteId")
                                                +"&uid="+ pageContext.getAttribute("uid")
                                                +"&role="+pageContext.getAttribute("role")%>' styleClass="LinkUline" target="_top">
                         <nested:write  property="siteProtocolNbr"/>
                         </html:link>
                        </nested:notEmpty>
                </c:when>
                <c:otherwise>
                        <nested:notEmpty  property="siteProtocolNbr">
                         <html:link action='<%= "/SitePanelAction?method=initSite&landingTab=overview&landingPage=sites&siteId="
                                                + pageContext.getAttribute("siteId")
                                                +"&uid="+ pageContext.getAttribute("uid")
                                                +"&role="+pageContext.getAttribute("role")%>' styleClass="LinkUline" target="_top">
                         <nested:write  property="siteProtocolNbr"/>
                         </html:link>
                        </nested:notEmpty>
                    </c:otherwise>
                </c:choose>

                        <nested:empty    property="siteProtocolNbr">
                            &ndash;
                        </nested:empty>
                         <nested:hidden    property="siteProtocolNbr"/>
                    </td>
                    <td class="portBody">&nbsp;</td>
                    
                    <td class="portBody">
                        <nested:notEmpty    property="investigatorName">
                            <nested:write   property="investigatorName"/>
                        </nested:notEmpty>
                        <nested:empty    property="investigatorName">
                            &ndash;
                        </nested:empty>
                         <nested:hidden    property="investigatorName"/>
                    </td>
                    
                    <td class="portBody">&nbsp;</td>
                    
                    <td class="portBody">
                        <nested:notEmpty    property="studySiteStatus">
                            <nested:write   property="studySiteStatus"/>
                        </nested:notEmpty>
                        <nested:empty    property="studySiteStatus">
                            &ndash;
                        </nested:empty>
                    </td>
                    <td class="portBody">&nbsp;</td>
                    
                    <td class="portBody">
                        <nested:notEmpty    property="actualSiteCloseDt" >
                            <nested:write   property="actualSiteCloseDt" format= "dd-MMM-yyyy"/>
                        </nested:notEmpty>
                        <nested:empty    property="actualSiteCloseDt">
                            <nested:notEmpty    property="plannedSiteCloseDt" >
                                <nested:write   property="plannedSiteCloseDt" format= "dd-MMM-yyyy"/>
                            </nested:notEmpty>
                            <nested:empty    property="plannedSiteCloseDt">
                                &ndash;
                            </nested:empty>
                        </nested:empty>
<%--                         <nested:hidden    property="actualSiteCloseDt"/>--%>
                    </td>
                    <td class="portBody">&nbsp;</td>
                    
                    <td class="portBody">
                        <nested:notEmpty    property="enrollmentCnt">
                            <nested:write   property="enrollmentCnt"/>
                        </nested:notEmpty>
                        <nested:empty    property="enrollmentCnt">
                            &ndash;
                        </nested:empty>
                        /
                        <nested:hidden    property="randomizationCnt"/>
                        <nested:notEmpty    property="randomizationCnt">
                            <nested:write   property="randomizationCnt"/>
                        </nested:notEmpty>
                        <nested:empty    property="randomizationCnt">
                            &ndash;
                        </nested:empty>
                        <nested:hidden    property="randomizationCnt"/>

                    </td>                   
                    
                </tr>
   </nested:iterate>
  </logic:notEmpty>
  <logic:empty name="staffPanelForm" property="staffDetailList">
                <td class="portBody" colspan="9">No Data Found</td>
  </logic:empty>

        </tbody>
        </table>
</div>
                    </td>
                </tr>
            </table>
    </html:form>

	<script language="JavaScript">
		processFilters(document.staffPanelForm,'siteId,roleCd.value,userId,staffStatusId','staff_data');
	</script>
