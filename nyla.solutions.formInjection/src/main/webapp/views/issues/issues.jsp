<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://gcsm.bms.com/tld/security.tld" prefix="security"%>
<%
    String landingPage="";
    String landingTab="";

    String issueAction="";
    String issuesProtocolSiteSortAction="";
    String issuesPrioritySortAction="";
    String issuesClassificationSortAction="";
    String issuesResolverSortAction="";
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
    landingTab=request.getParameter("landingTab");
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
    if (landingTab != null) {
       landingTab = "&landingTab="+landingTab;
    } else {
	landingTab = "";
    }

    if (landingPage == null) {
	landingPage="home";
	issueAction="/IssuePanelAction?method=doFilter"+"&landingPage="+landingPage + uidUrl + siteIdUrl + roleUrl + landingTab;
    } else {
	issueAction="/IssuePanelAction?method=doFilter&landingPage="+landingPage + uidUrl + siteIdUrl + roleUrl + landingTab;
    }

/*
issuesProtocolSiteSortAction="/IssuePanelAction.do?method=sort&sortField=site&landingPage="+landingPage + uidUrl + siteIdUrl + roleUrl+ landingTab;
issuesPrioritySortAction="/IssuePanelAction.do?method=sort&sortField=priority&landingPage="+landingPage + uidUrl + siteIdUrl + roleUrl+ landingTab;
issuesClassificationSortAction="/IssuePanelAction.do?method=sort&sortField=classification&landingPage="+landingPage + uidUrl + siteIdUrl + roleUrl+ landingTab;
issuesResolverSortAction="/IssuePanelAction.do?method=sort&sortField=resolver&landingPage="+landingPage + uidUrl + siteIdUrl + roleUrl+ landingTab;
*/
issuesProtocolSiteSortAction="javascript:sortBy('issue_data_tbl', 0)";
issuesPrioritySortAction="javascript:sortBy('issue_data_tbl', 1)";
issuesClassificationSortAction="javascript:sortBy('issue_data_tbl', 2)";
issuesResolverSortAction="javascript:sortBy('issue_data_tbl', 7)";

%>

<html:form action="<%=issueAction%>">
	<%-- Protlet Container --%>
	    <table align="left" border="0" cellpadding="0" cellspacing="0"  id="issues_body" width="100%">
		<%-- Header --%>
		<tr>
		    <td class="portTitle" height="29">
		      <table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr class="portFilterLbl">
			<td width="15"><html:img  border="0" page="/skins/default/images/phead_left.gif"/></td>
			<td class="portTitle" width="30">ISSUES</td>
			<td width="20"><html:img  border="0" page="/skins/default/images/icon_issues.gif"/></td>
			<td width="10"><html:img  border="0" page="/skins/default/images/blank.gif" width="10" height="1"/></td>
			 <%
			 // for issues 59 and 61 on the issue panel
			 
			 if(landingPage!=null && !landingPage.equalsIgnoreCase("sites"))
			 {
			 
			 %>
			 
			 <td width="60" class="portFilterLbl" nowrap>Protocol-Site</td>
				<td width="40">
				<logic:notEmpty name="issuePanelForm" property="issueProtocolSiteList" >
				<html:select name="issuePanelForm" property="siteId"  styleClass="portFilter" onchange="processFilters(this.form,'siteId,priorityId,classificationId,issueStatusId','issue_data_tbl');" >
				    <bean:define id="issueProtocolSiteList"  name="issuePanelForm" property="issueProtocolSiteList" />
				     <html:option value="-1">All</html:option>
				    <html:options collection="issueProtocolSiteList"
						  property="value"
						  labelProperty="label" />
				</html:select>
			       </logic:notEmpty>
				 <logic:empty name="issuePanelForm" property="issueProtocolSiteList">
				  <html:select name="issuePanelForm" property="siteId"  styleClass="portFilter" onchange="processFilters(this.form,'siteId,priorityId,classificationId,issueStatusId','issue_data_tbl');" >
				  <html:option value="-1">All</html:option>
				  </html:select>
				 </logic:empty>
				</td>
				
			<% } %>

			<td width="10"><html:img  border="0" page="/skins/default/images/blank.gif" width="10" height="1"/></td>
			<td width="50" class="portFilterLbl">Priority</td>
			<td width="50">
			 <logic:notEmpty name="issuePanelForm" property="priorityList">
			    <html:select name="issuePanelForm" property="priorityId"  styleClass="portFilter" onchange="processFilters(this.form,'siteId,priorityId,classificationId,issueStatusId','issue_data_tbl');" >
				<bean:define id="priorityList"  name="issuePanelForm" property="priorityList" />
				 <html:option value="-1">All</html:option>
				<html:options collection="priorityList"
					      property="value"
					      labelProperty="label" />
			    </html:select>
			   </logic:notEmpty>
			 <logic:empty name="issuePanelForm" property="priorityList">
			  <html:select name="issuePanelForm" property="priorityId"  styleClass="portFilter" onchange="processFilters(this.form,'siteId,priorityId,classificationId,issueStatusId','issue_data_tbl');" >
			  <html:option value="-1">All</html:option>
			  </html:select>
			 </logic:empty>

			</td>
			<td width="10"><html:img  border="0" page="/skins/default/images/blank.gif" width="10" height="1"/></td>
			<td width="70" class="portFilterLbl">Classification</td>
			<td width="70">
			 <logic:notEmpty name="issuePanelForm" property="classificationList">
			    <html:select name="issuePanelForm" property="classificationId"  styleClass="portFilter" onchange="processFilters(this.form,'siteId,priorityId,classificationId,issueStatusId','issue_data_tbl');" >
				<bean:define id="classificationList"  name="issuePanelForm" property="classificationList" />
				 <html:option value="-1">All</html:option>
				<html:options collection="classificationList"
					      property="value"
					      labelProperty="label" />
			    </html:select>
			   </logic:notEmpty>
			 <logic:empty name="issuePanelForm" property="classificationList">
			  <html:select name="issuePanelForm" property="classificationId"  styleClass="portFilter" onchange="processFilters(this.form,'siteId,priorityId,classificationId,issueStatusId','issue_data_tbl');" >
			  <html:option value="-1">All</html:option>
			  </html:select>
			 </logic:empty>

			</td>
			<td width="10"><html:img  border="0" page="/skins/default/images/blank.gif" width="10" height="1"/></td>
			<td width="50" class="portFilterLbl">Status</td>
			<td width="50">
			 <logic:notEmpty name="issuePanelForm" property="issueStatusList">
			    <html:select name="issuePanelForm" property="issueStatusId"  styleClass="portFilter" onchange="processFilters(this.form,'siteId,priorityId,classificationId,issueStatusId','issue_data_tbl');" >
				<bean:define id="issueStatusList"  name="issuePanelForm" property="issueStatusList" />
				  <html:options collection="issueStatusList"
					      property="value"
					      labelProperty="label" />
			    </html:select>
			    </logic:notEmpty>
			 <logic:empty name="issuePanelForm" property="issueStatusList">
			  <html:select name="issuePanelForm" property="issueStatusId"  styleClass="portFilter" onchange="processFilters(this.form,'siteId,priorityId,classificationId,issueStatusId','issue_data_tbl');" >
			  <html:option value="-1">All</html:option>
			  </html:select>
			 </logic:empty>

			    </td>
			<td width="100%"><!-- leave this blank for expansion/contraction --></td>
			<td width="70">

<%--                              <html:img imageName="button_postissue_on"  border="0" page="/skins/default/images/button_postissue_on.gif" onclick="window.open('PostIssueAction?method=init','postIssue','width=490,height=640,menubar=no,scrollbars=no,status=no,toolbar=no,titlebar=no');"/>--%>


<% String postIssueUrl = "PostIssueAction?method=init";

if(siteId!=null)
postIssueUrl=postIssueUrl+"&siteID=" + siteId; 

%>

			    <security:guard permission="IssueCreate">
			    <OTML:HANDLE-DHTML-EVENTS>
				    <OTML:EVENT HANDLER="ONCLICK" BREAK-FOR-DEBUG="no"/>
				     <A href="<%= postIssueUrl%>" id="postNewIssueLink" target="postIssue"><html:img style="cursor:hand" imageName="button_postissue_on" border="0" page="/skins/default/images/button_postissue_on.gif" onclick="window.open('','postIssue','width=490,height=697,top=20, scrollbars=no, menubar=no, status=no,toolbar=no,titlebar=no');"/></A>
<%--                                <a href="#" id="button_postissue_on" onclick="window.open('<%= postIssueUrl%>','postIssue','width=490,height=640,menubar=no,scrollbars=no,status=no,toolbar=no,titlebar=no');"><html:img imageName="button_postissue_img"  border="0" page="/skins/default/images/button_postissue_on.gif"/></a>--%>
			     </OTML:HANDLE-DHTML-EVENTS>
			  </security:guard>

			</td>
			<td width="10"><html:img  border="0" page="/skins/default/images/blank.gif" width="10" height="1"/></td>
			<td width="15"><html:img  border="0" page="/skins/default/images/phead_expand_on.gif" onclick="resizePortlet('issues');" style="cursor:hand"/></td>
			<td width="15"><html:img  border="0" page="/skins/default/images/phead_rt.gif"/></td>
			</tr>
		    </table>
		    </td>
		</tr>
		<tr>
		    <td>
<div id="inner_issues" style="overflow: auto; height: 100px; border: solid 1px #CCCCFF">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="issue_data_tbl" >
	    <thead>
	    <tr class="noScroll">
	    
	    <%
	    // for issues 59 and 61 on the issue panel
	    
		 if(landingPage!=null && !landingPage.equalsIgnoreCase("sites"))
		 {

	    %>
		<td class="portColHdr">
		<a href="<%=issuesProtocolSiteSortAction%>"
			   class="LinkUline"
			    target="_self">Protocol/Site</a>

		</td>
		<td class="portColHdr" width=5>&nbsp;</td> 
		
	   <%
	   }
	   %>
		<td class="portColHdr">
		<a href="<%=issuesPrioritySortAction%>"
			   class="LinkUline"
			    target="_self">Priority</a></td>
		<td class="portColHdr" width=5>&nbsp;</td> 
		
		<td class="portColHdr">
		<a href="<%=issuesClassificationSortAction%>"
			   class="LinkUline"
			    target="_self">Classification</a></td>
		<td class="portColHdr" width=5>&nbsp;</td> 			    
		<td class="portColHdr">Description</td>
		<td class="portColHdr" width=5>&nbsp;</td> 
		<td class="portColHdr">Report</td>
		<td class="portColHdr" width=5>&nbsp;</td> 
		<td class="portColHdr">Contact Date</td>
		<td class="portColHdr" width=5>&nbsp;</td> 
		<td class="portColHdr">Originator</td>
		<td class="portColHdr" width=5>&nbsp;</td> 
		<td class="portColHdr">
		<a href="<%=issuesResolverSortAction%>"
			   class="LinkUline"
			    target="_self">Resolver</a></td>
					    
	    </tr>
	    </thead>
	    <tbody class="noScroll">
    <logic:notEmpty name="issuePanelForm" property="issueDetailList">
	<c:forEach items="${issuePanelForm.issueDetailList}" var="issueForm">

	    <tr style="display: none">
	    
	     <%
	     
	     // for issues 59 and 61 on the issue panel
	     
		 if(landingPage!=null && !landingPage.equalsIgnoreCase("sites"))
		 {
	    
	     %>
		<td class="portBody">
		   <c:if test="${not empty issueForm.protocolSite}">
     		    <c:out value="${issueForm.protocolSite}"/>
		    </c:if>
		    <c:if test="${empty issueForm.protocolSite}">
                 &ndash;
		    </c:if>
		    
		</td>
		<td class="portBody">&nbsp;</td>
		
	   <%
	   }
	   %>
		<td class="portBody">
		    <c:out value="${issueForm.priority}"/>
		</td>
		<td class="portBody">&nbsp;</td>
		<td class="portBody">
		    <c:out value="${issueForm.classification}"/>
	    </td>
		<td class="portBody">&nbsp;</td>		     

		<td class="portBody">
			<a href="<c:url value="/do/EditIssueAction?method=init&issueID=${issueForm.issueId}&context=${issueForm.context}&updateDate=${issueForm.updateTime}"/>" onclick="window.open('','postIssue','width=550,height=710,menubar=no, scrollbars=yes, status=no,toolbar=no,titlebar=no');" target="postIssue"><c:out value="${issueForm.description}"/></a>
		</td>
		
		<td class="portBody">&nbsp;</td>
		
		<td class="portBody" nowrap>
		    <c:out value="${issueForm.report}"/>
		</td>
		<td class="portBody">&nbsp;</td>
		<td class="portBody">
		    <c:out value="${issueForm.contactDt}"/>
		</td>
		<td class="portBody">&nbsp;</td>
		<td class="portBody" nowrap>
		    <c:out  value="${issueForm.originator}"/>
		</td>
		<td class="portBody">&nbsp;</td>
		<td class="portBody" nowrap>
		    <c:out value="${issueForm.resolver}"/>
		</td>
<%--   This is added for Backweb Do not remove              --%>
		<td style="display:none;" width="0px;"><c:out value="${issueForm.status}"/></td>
	    </tr>
   </c:forEach>
  </logic:notEmpty>
  <logic:empty name="issuePanelForm" property="issueDetailList">
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
		processFilters(document.issuePanelForm,'siteId,priorityId,classificationId,issueStatusId','issue_data_tbl');
	</script>

