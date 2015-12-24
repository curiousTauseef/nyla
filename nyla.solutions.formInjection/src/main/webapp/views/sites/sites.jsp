<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://gcsm.bms.com/tld/security.tld" prefix="security"%>
<%@ page import="com.bms.informatics.gcsm.common.util.*,com.bms.informatics.gcsm.common.web.util.*, java.util.*,com.bms.informatics.gcsm.protocol.web.*, org.apache.commons.lang.StringUtils" %>
<%--<bean:define id="userAgent" name="userAgent" type="java.lang.String" value="BackWeb-ProactivePortal"/>--%>
<script type="text/javascript" language="JavaScript">
var url="";
    <!--
    function openUnScheduledVisitFormsPopup() {
         url="<html:rewrite page="/do/unscheduledVisitFormsPopup"/>";
         if (document.userPanelForm.roleId.tagName == "SELECT")
         	url += "?roleId=" + document.userPanelForm.roleId[document.userPanelForm.roleId.selectedIndex].value;
        window.open(url, "unscheduledVisitFormsPopup", "width=300, height=300, resizable=no, scrollbars=no, location=no, status=no, directories=no, menubar=no");
    }
     -->
</script>
<%     
    String landingPage="";
    String uid="";
    String siteId="";
    String uidUrl="";
    String siteIdUrl="";
    String role="";
    String roleUrl="";

    String sitesAction="";
    String sitesProtocolSiteSortAction="";
    String sitesStatusSortAction="";
    String protocolSiteNbrUrl="";
    landingPage=request.getParameter("landingPage");
    uid=request.getParameter("uid");
    siteId=request.getParameter("siteId");
    role=request.getParameter("role");
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
        sitesAction="/SitePanelAction.do?method=doFilter&landingPage="+landingPage + uidUrl + siteIdUrl + roleUrl;
    } else {
        sitesAction="/SitePanelAction.do?method=doFilter&landingPage="+landingPage + uidUrl + siteIdUrl + roleUrl;
    }
    
protocolSiteNbrUrl="/SitePanelAction?method=initSite&landingTab=overview" + uidUrl + roleUrl;
//sitesProtocolSiteSortAction="/SitePanelAction.do?method=sort&sortField=protocol&landingPage="+landingPage + uidUrl + siteIdUrl + roleUrl;
//sitesStatusSortAction="/SitePanelAction.do?method=sort&sortField=status&landingPage="+landingPage +  uidUrl + siteIdUrl + roleUrl;
sitesProtocolSiteSortAction="javascript:sortBy('site_data_tbl', 0)";
sitesStatusSortAction="javascript:sortBy('site_data_tbl', 2)";
boolean  isbackweb= StringUtils.contains(request.getHeader("User-Agent"),"BackWeb");

%>
<bean:define id="accessRole" name="accessRole"/>
<%--<bean:write name="accessRole"/>--%>
<html:form  action="<%=sitesAction%>">
<table width="99%" cellpadding="0" cellspacing="0" border="0" id="site_body" align="left">
<tr>
    <td>
        <table cellpadding="0" cellspacing="0" width="100%"  border="0">
              <tr>
                    <td height="27">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr class="portFilterLbl">
                                <td width="15"><html:img  border="0" page="/skins/default/images/phead_left.gif"/></td>
                                <td class="portTitle" width="30">SITES</td>
                                <td width="20"><html:img  border="0" page="/skins/default/images/phead_sites_icon.gif" /></td>
                                <td width="10"><html:img  border="0" page="/skins/default/images/blank.gif" width="10" height="1"/></td>
                                <td width="30" class="portFilterLbl">Protocol</td>
                                <td width="40" class="portFilterLbl">
                                <bean:define id="siteProtocolList" name="sitePanelForm" property="siteProtocolList"/>
                                <html:select name="sitePanelForm" property="protocolId"  styleClass="portFilter" onchange="processFilters(this.form,'protocolId,siteStatusId','site_data_tbl');" >
                                <html:option value="-1">All</html:option>
                                <html:options collection="siteProtocolList"
                                                name="sitePanelForm"
                                                property="value"
                                                labelProperty="label"
                                                />
                                </html:select>

                                </td>
                                <td width="10"><html:img  border="0" page="/skins/default/images/blank.gif" width="10" height="1"/></td>
                                <td width="40" class="portFilterLbl">Status</td>
                                <td width="40" class="portFilterLbl">
                                    <html:select name="sitePanelForm" property="siteStatusId"  styleClass="portFilter" onchange="processFilters(this.form,'protocolId,siteStatusId','site_data_tbl');" >
                                        <bean:define id="siteStatusList"  name="sitePanelForm" property="siteStatusList" />
                                         <html:option value="-1">All</html:option>
                                        <html:options collection="siteStatusList"
                                                      property="value"
                                                      labelProperty="label" />
                                    </html:select>

                                </td>
                                <td width="100%"><!-- leave this blank for expansion/contraction --></td>
                                    <%--Show Following Row Only If the user Agent is Backweb--%>
                                 <security:guard permission="VisitFormPopup">
                                 <%  if (request.getParameter("showForms") != null || isbackweb) { %>
                                 <OTML:HANDLE-DHTML-EVENTS>
                                    <OTML:EVENT HANDLER="ONCLICK" BREAK-FOR-DEBUG="no"/>
                                    <td width="15"><html:img  border="0" page="/skins/default/images/visit_form.gif"  onclick="openUnScheduledVisitFormsPopup();" style="cursor:hand"/></td>
                                 </OTML:HANDLE-DHTML-EVENTS>
                                 <%}%>
                                 </security:guard>

                                <td width="10"><html:img  border="0" page="/skins/default/images/blank.gif" width="10" height="1"/></td>
                                <td width="15"><html:img  border="0" page="/skins/default/images/phead_expand_on.gif" onclick="resizePortlet('sites');"  style="cursor:hand"/></td>
                                <td width="15"><html:img  border="0" page="/skins/default/images/phead_rt.gif"/></td>
                            </tr>
                        </table>
                    </td>
                </tr>
        </table>
      </td>
    </tr>
    <tr>
    <td>
    <div id="inner_sites" style="overflow: auto;width: 100%; height: 100px; border: solid 1px #CCCCFF">
    
	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="site_data_tbl">	
	<thead>
		<tr class="noScroll">
		<td class="portColHdr">
                <a href="<%=sitesProtocolSiteSortAction%>"
                           styleClass="LinkUline"
                            target="_self">Protocol/Site</a></td>
		<td class="portColHdr" width=5>&nbsp;</td>                            
		<td class="portColHdr">Investigator</td>
		<td class="portColHdr" width=5>&nbsp;</td>
		<td class="portColHdr">
						
                <a href="<%=sitesStatusSortAction%>"
                           styleClass="LinkUline"
                            target="_self">Status</a></td>
		<td class="portColHdr" width=5>&nbsp;</td>
		<td class="portColHdr"><a href="javascript:sortBy('site_data_tbl', 3, 'date')">Close Date</a></td>
		<td class="portColHdr" width=5>&nbsp;</td>
		<td class="portColHdr">E/R</td>
                
		</tr>
	</thead>
	
	
	<tbody class="noScroll">
	
        <logic:notEmpty name="sitePanelForm" property="siteDetailList">
        
        <nested:iterate name="sitePanelForm" property="siteDetailList" indexId="i" id="siteForm">
        
	<tr>
	
	<td class="portBody" nowrap>
	<c:set var="studySiteId" scope="page">
	    <nested:write property="studySiteId"/>
	</c:set>
	    <c:choose>
		<c:when test='${param.landingPage == "staff"}'>
		    <nested:notEmpty  property="protocolSiteNbr">
		     <html:link action='<%= protocolSiteNbrUrl+"&landingPage=staffSite&siteId="+ pageContext.getAttribute("studySiteId") %>' styleClass="LinkUline" target="_top">
		     <nested:write  property="protocolSiteNbr"/>
		     </html:link>
		    </nested:notEmpty>
		</c:when>
		<c:when test='${param.landingPage == "adminStaff"}'>
		    <nested:notEmpty  property="protocolSiteNbr">
		     <html:link action='<%= protocolSiteNbrUrl+"&landingPage=adminSite&siteId="+ pageContext.getAttribute("studySiteId") %>' styleClass="LinkUline" target="_top">
		     <nested:write  property="protocolSiteNbr"/>
		     </html:link>
		    </nested:notEmpty>
		</c:when>

		<c:otherwise>
		    <nested:notEmpty  property="protocolSiteNbr">
		     <html:link action='<%= protocolSiteNbrUrl+"&landingPage=sites&siteId="+ pageContext.getAttribute("studySiteId") %>' styleClass="LinkUline" target="_top">
		     <nested:write  property="protocolSiteNbr"/>
		     </html:link>
		    </nested:notEmpty>
		</c:otherwise>
	    </c:choose>
	</td>
	<td class="portBody">&nbsp;</td>
	<td class="portBody">

	    <nested:notEmpty  property="lastnm"><nested:write property="lastnm"/><nested:notEmpty  property="firstNm">, <nested:write property="firstNm"/></nested:notEmpty></nested:notEmpty>
	    <nested:empty  property="lastnm">
		<nested:notEmpty  property="firstNm">
		    &ndash;,<nested:write property="firstNm"/>
		</nested:notEmpty>
	    </nested:empty>

	</td>
	<td class="portBody">&nbsp;</td>
	<td class="portBody">
	    <nested:notEmpty  property="studySiteStatus">
		<nested:write property="studySiteStatus"/>
	    </nested:notEmpty>
	    <nested:empty  property="studySiteStatus">
		&ndash;
	    </nested:empty>
	</td>
	<td class="portBody">&nbsp;</td>			
	<td class="portBody" nowrap>
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

	</td>
	<td class="portBody">&nbsp;</td>
	<td class="portBody" nowrap>
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
        
        <logic:empty  name="sitePanelForm" property="siteDetailList">
	      <tr>
		<td class="portBody" colspan="6">No Data Found</td>
             </tr>
        </logic:empty>
        
		</tbody>
		</table>

    </div>
    </td>
    </tr>
   </table>

</html:form>

	<script language="JavaScript">
		processFilters(document.sitePanelForm,'protocolId,siteStatusId','site_data_tbl');
	</script>

