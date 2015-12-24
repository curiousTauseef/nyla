<%--
  User: Oscar Lakra
  Date: May 26, 2005
  Time: 1:10:46 PM
  Description: This is the Alerts tiles and it displays the data table with the
	       Alert information bound to the logged in User.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://gcsm.bms.com/tld/security.tld" prefix="security"%>
<%@ page import="com.bms.informatics.gcsm.common.util.*,com.bms.informatics.gcsm.common.web.util.*, java.util.*,com.bms.informatics.gcsm.protocol.web.*" %>
<script language="JavaScript" type="text/javascript">
function openWin(url) {
  window.open(url, "ALERT_POPUP", "width=460, height=530, resizable=no, scrollbars=no, location=no, status=no, directories=no, menubar=no");
}

</script>
<%
    String alertAction="";
    String alertsProtocolSiteSortAction="";
    String landingPage="";
    String uid="";
    String siteId="";
    String uidUrl="";
    String siteIdUrl="";
    String role="";
    String roleUrl="";
    String contextPath=request.getContextPath();
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
	alertAction="/AlertPanelAction?method=doFilter&landingPage="+landingPage + uidUrl + siteIdUrl + roleUrl;
    } else {
	alertAction="/AlertPanelAction?method=doFilter&landingPage="+landingPage+ uidUrl + siteIdUrl + roleUrl;
    }
    //alertsProtocolSiteSortAction="/AlertPanelAction.do?method=sort&sortField=site&landingPage="+landingPage+ uidUrl + siteIdUrl + roleUrl;
	alertsProtocolSiteSortAction = "javascript:sortBy('alert_data_tbl', 0)";
%>


<html:form  action="<%=alertAction%>">
	    <table align="left" border="0" cellpadding="0" cellspacing="0"  id="alerts_body" width="100%">
	    <%-- This is the Header section of the tile. --%>
	    <%-- TODO: add the logic to dynamically check and then attach the new button --%>
		<tr>
		    <td align="left" class="portTitle" height="29">
			<table align="left" width="100%" border="0" cellspacing="0" cellpadding="0">
			    <tr class="portFilterLbl">
				<td width="15" style="background-color:#074A87"><html:img  border="0" page="/skins/default/images/phead_left.gif"/></td>
				<td class="portTitle" width="30">Alerts</td>
				<td width="20"><html:img  border="0" page="/skins/default/images/icon_alerts.gif"/></td>
					<td width="30" class="portFilterLbl">Severity</td>
					<td width="40">
					<html:select name="alertPanelForm" property="severityId"  styleClass="portFilter" onchange="processFilters(this.form,'severityId,siteId,alertStatusId','alert_data_tbl','8,2,9');" >
					    <bean:define id="severityList"  name="alertPanelForm" property="severityList" />
					     <html:option value="-1">All</html:option>
					    <html:options collection="severityList"
							  property="value"
							  labelProperty="label" />
					</html:select>
					</td>
				<td width="10" class="portFilterLbl"></td>
				    <c:choose>
				      <c:when test='${param.landingPage eq "sites"}'>
					<!--<td width="100" colspan="2">&nbsp;</td>-->
				      </c:when>
				      <c:otherwise>
					<td width="60" class="portFilterLbl">Protocol-Site</td>
			
					<td width="40">
					  <html:select name="alertPanelForm" property="siteId"  styleClass="portFilter" onchange="processFilters(this.form,'severityId,siteId,alertStatusId','alert_data_tbl','8,2,9');" >
					      <bean:define id="protocolSiteList"  name="alertPanelForm" property="protocolSiteList" />
					       <html:option value="-1">All</html:option>
					      <html:options collection="protocolSiteList"
							    property="value"
							    labelProperty="label" />
					  </html:select>
					</td>
				      </c:otherwise>
				    </c:choose>

				<td width="10" class="portFilterLbl"></td>
				 <td width="30" class="portFilterLbl">Status</td>
				    <td width="40">
					<html:select name="alertPanelForm" property="alertStatusId"  styleClass="portFilter" onchange="processFilters(this.form,'severityId,siteId,alertStatusId','alert_data_tbl','8,2,9');" >
					    <bean:define id="statusList"  name="alertPanelForm" property="statusList" />
<%--                                             <html:option value="-1">All</html:option>--%>
					    <html:options collection="statusList"
							  property="value"
							  labelProperty="label" />
					</html:select>
			    </td>
							<td>&nbsp;</td>

				<td width="15">
				    <security:guard permission="AlertCreate">
					<html:img style="cursor:hand" border="0" page="/skins/default/images/button_createalert_on.gif" onclick="openWin('newAlertAction?method=initAlert');"/>
				    </security:guard>
				    </td>

							<td width="10"><html:img  border="0" page="/skins/default/images/blank.gif" width="10" height="1"/></td>
				<td width="15"><html:img  border="0" page="/skins/default/images/phead_expand_on.gif" onclick="resizePortlet('alerts');" style="cursor:hand"/></td>
				<td width="15" style="background-color:#FFF"><html:img  border="0" page="/skins/default/images/phead_rt.gif"/></td>
			    </tr>
			</table>
		    </td>
		</tr>
		<tr>
		    <td>
<div id="inner_alerts" style="overflow: auto;width: 100%; height: 100px; border: solid 1px #CCCCFF">
<table width="100%" border="0" cellspacing="0" cellpadding="0" id="alert_data_tbl">

<thead>
	<tr class="noScroll">
		<td class="portColHdr"  width="2">
		<html:img  border="0"
			    page="/skins/default/images/alert_grey.gif"
			    style="vertical-align:middle"/></td>
		<td class="portColHdr" width=5>&nbsp;</td>		    
		  <c:choose>
			  <c:when test='${param.landingPage != "sites"}'>
			    <td class="portColHdr"  width="140">
			      <a href="<%=alertsProtocolSiteSortAction%>"
					 styleClass="LinkUline"
					 target="_self">Protocol/Site</a>
			    </td>
			  </c:when>
			  <c:otherwise>
				<%-- blank td so filtering doesn't break --%>
				<td style="display:none"></td>
			  </c:otherwise>		  
		  </c:choose>
		
		<td class="portColHdr">Subject</td>
		<td class="portColHdr" width=5>&nbsp;</td>
		<td class="portColHdr"  width="100">Create Date</td>
		<td class="portColHdr" width=5>&nbsp;</td>
		<td class="portColHdr"  width="80">Created by</td>
	</tr>
</thead>

<tbody class="noScroll">
    <logic:notEmpty name="alertPanelForm" property="alertDetailList">
	<nested:iterate   name="alertPanelForm" property="alertDetailList"
					     indexId="i"
					     id="alertForm">
	    <tr style="display: none;">

		<td class="portBody">
		    <nested:equal value="High" property="severity">
		       <nested:image  border="0"
				    page="/skins/default/images/alert_high.gif"
				    style="vertical-align:middle"/>
		    </nested:equal>
		    <nested:equal value="Low" property="severity">
		       <nested:image  border="0"
				    page="/skins/default/images/alert_low.gif"
				    style="vertical-align:middle"/>
		    </nested:equal>
		    <nested:equal value="Medium" property="severity">
		       <nested:image  border="0"
				    page="/skins/default/images/alert_medium.gif"
				    style="vertical-align:middle"/>
		    </nested:equal>

		</td>
		
		<td class="portBody">&nbsp;</td>
	<c:choose>
	
		<c:when test='${param.landingPage != "sites"}'>
		
		  <td class="portBody">
               
		      <nested:notEmpty property="dps">
		      <nested:write property="dps"></nested:write>
		      </nested:notEmpty>
		      <nested:empty property="dps">
		      &ndash;
		      </nested:empty>
		       <nested:hidden  property="alertID"/>
		       <nested:hidden  property="protocol"/>
		
		  </td>
		  
		</c:when>
		<c:otherwise>
			<%-- blank cell for filtering still works --%>
			<td style="display:none"></td>
		</c:otherwise>
		
	</c:choose>
	
	
<%--                <td class="portBody">--%>
<%--                        <nested:notEmpty  property="site">--%>
<%--                            <nested:write property="site"/>--%>
<%--                        </nested:notEmpty>--%>
<%--                        <nested:empty  property="site">--%>
<%--                            &ndash;--%>
<%--                        </nested:empty>--%>
<%--                         <nested:hidden  property="site"/>--%>
<%----%>
<%--                </td>--%>
		<td class="portBody">
		    <nested:notEmpty  property="subject">
<%--                      href='/do/AlertAction.do?method=initAlert&alertID=<nested:write property="alertID"/>' --%>
		    <a onclick="openWin('AlertAction?method=initAlert&amp;primaryKey=<nested:write property="primaryKey"/>');return false;" href="#">
		    <bean:write name="alertForm" property="subject"/>
		    </a>
<%--TODO: FixMe => Change the Code   to struts                  --%>
<%--                    <nested:define id="alertID" property="alertID" name="alertForm"/> --%>
<%--                    <html:link  href="/AlertAction.do?method=initAlert" paramName="alertID" name="alertID" property="alertID" >--%>
<%--                        <bean:write name="alertForm" property="description"/>--%>
<%--                    </html:link>--%>
		    </nested:notEmpty>
		    <nested:empty  property="subject">
			&ndash;
		    </nested:empty>
		     <nested:hidden  property="subject"/>
		</td>
		<td class="portBody">&nbsp;</td>
		<td class="portBody">
		    <nested:notEmpty  property="dateCreated">
			<nested:write property="dateCreated"/>
		    </nested:notEmpty>
		    <nested:empty  property="dateCreated">
			&ndash;
		    </nested:empty>
		     <nested:hidden  property="dateCreated"/>
		</td>
		<td class="portBody">&nbsp;</td>
		<td class="portBody" nowrap>
		    <nested:notEmpty  property="createUser">
			<nested:write property="createUser"/>
		    </nested:notEmpty>
		    <nested:empty  property="createUser">
			&ndash;
		    </nested:empty>
		     <nested:hidden  property="createUser"/>
		</td>
		
<%--  Following is added for backweb do not remove  --%>
		<td style="display:none;" width="0px;"><nested:write property="severity"/></td>
		<td style="display:none;" width="0px;"><nested:write property="status"/></td>
	    </tr>
   </nested:iterate>
  </logic:notEmpty>
  <logic:empty name="alertPanelForm" property="alertDetailList">
		<td class="portBody" colspan="5">No Data Found</td>
  </logic:empty>
</tbody>
</table>
</div>
		    </td>
		</tr>
	    </table>

    </html:form>
	<script language="JavaScript">
		processFilters(document.alertPanelForm,'severityId,siteId,alertStatusId','alert_data_tbl');
	</script>

