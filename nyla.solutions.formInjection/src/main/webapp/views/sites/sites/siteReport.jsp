<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ page import="com.bms.informatics.gcsm.common.util.*,com.bms.informatics.gcsm.common.web.util.*, java.util.*,com.bms.informatics.gcsm.protocol.web.*" %>
<%
    String landingPage="";
    String contextPath=request.getContextPath();
    String siteAction="";
    String contactDateSortAction="";
    String siteReportTypeSortAction="";
    String siteReportStatusSortAction="";
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

    if (landingPage == null) {
        siteAction="/SiteReportPanelAction?method=doFilter";
    } else {
        siteAction="/SiteReportPanelAction?method=doFilter&landingPage=" + landingPage;
    }
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

    //contactDateSortAction="/SiteReportPanelAction?method=sort&sortField=contactDate&landingPage="+landingPage + uidUrl + siteIdUrl + roleUrl;
    //siteReportTypeSortAction="/SiteReportPanelAction?method=sort&sortField=type&landingPage="+landingPage + uidUrl + siteIdUrl + roleUrl;
    //siteReportStatusSortAction="/SiteReportPanelAction?method=sort&sortField=status&landingPage="+landingPage + uidUrl + siteIdUrl + roleUrl;
    contactDateSortAction="javascript:sortBy('site_report_data', 0, 'date')";
    siteReportTypeSortAction="javascript:sortBy('site_report_data', 1)";
    siteReportStatusSortAction="javascript:sortBy('site_report_data', 2)";

%>
<%--<%="landingPage" +  landingPage %>--%>
<%--<%="siteAction" + siteAction%>--%>
<html:form action="<%=siteAction%>">
	<table width="100%" cellpadding="0" cellspacing="0" border="0" id="site_body" align="left">
	    <tr>
	    <td class="portTitle" height="25">
		    <table width="100%" border="0" cellspacing="0" cellpadding="0"> 
		    <tr class="portFilterLbl" valign="middle">
				<td align=left width=10><html:img  border="0" page="/skins/default/images/phead_left.gif" height="29px" width="10"/></td>
				<td class="portTitle" align=left width=20>&nbsp;REPORTS&nbsp;&nbsp;</td>
				<td align=left width=10><html:img  border="0" page="/skins/default/images/psa_logo.gif" /></td>
				<td class="portFilterLbl" align=right width=40>&nbsp;&nbsp;Types&nbsp;&nbsp;</td>
				<td align=left width=20>
				 <logic:empty name="siteReportPanelForm" property="siteReportTypeList">
				      <html:select name="siteReportPanelForm" property="siteReportTypeId"  styleClass="portFilter" onchange="processFilters(this.form,'siteReportTypeId,siteReportStatusId','site_report_data');" >
					<html:option value="-1">All</html:option>
				    </html:select>
				 </logic:empty>

				 <logic:notEmpty name="siteReportPanelForm" property="siteReportTypeList">
				    <html:select name="siteReportPanelForm" property="siteReportTypeId"  styleClass="portFilter" onchange="processFilters(this.form,'siteReportTypeId,siteReportStatusId','site_report_data');" >
					   <bean:define id="siteReportTypeList"  name="siteReportPanelForm" property="siteReportTypeList"/>
					       <html:option value="-1">All</html:option>
							<html:options collection="siteReportTypeList"
						    name="siteReportPanelForm"
						      property="key"
						      labelProperty="name" />
					  </html:select>
				   </logic:notEmpty>   
				</td>
					
					<td class="portFilterLbl" align=left width=10>&nbsp;&nbsp;Status&nbsp;&nbsp;</td>
					<td align=left width=90>     
			 <logic:empty name="siteReportPanelForm" property="siteReportStatusList">
			       <html:select name="siteReportPanelForm" property="siteReportStatusId"  styleClass="portFilter" onchange="processFilters(this.form,'siteReportTypeId,siteReportStatusId','site_report_data');" >
				<html:option value="-1">All</html:option>
			    </html:select>
			 </logic:empty>
			 <logic:notEmpty name="siteReportPanelForm" property="siteReportStatusList">
			<html:select name="siteReportPanelForm" property="siteReportStatusId"  styleClass="portFilter" onchange="processFilters(this.form,'siteReportTypeId,siteReportStatusId','site_report_data');" >
			<bean:define id="siteReportStatusList"  name="siteReportPanelForm" property="siteReportStatusList"/>

			    <html:option value="-1">All</html:option>
			    <html:options collection="siteReportStatusList"
					  property="key"
					  labelProperty="name" />
			</html:select>
			</logic:notEmpty>
				      </td>
					<td align=right><html:img  border="0" page="/skins/default/images/blank.gif" width="15" height="1"/></td>
					<td align=right width=50><html:img  border="0" page="/skins/default/images/phead_expand_on.gif" onclick="resizePortlet('site_reports','sites');" style="cursor:hand"/></td>
					<td align=right width=10><html:img  border="0" page="/skins/default/images/phead_rt.gif"/></td>
			    </tr>
			  </table>
		 </td>
		 </tr>
		<tr>
                <td>
		<div id="inner_site_reports" style="overflow: auto;width: 100%; height: 100px; border: solid 1px #CCCCFF">
			
		<table width="100%" border="0" cellspacing="0" cellpadding="0" id="site_report_data">
			<thead>
			<tr class="noScroll">
			
			<td class="portColHdr">
				        <a href="<%=contactDateSortAction%>"
                           class="LinkUline"
                            target="_self">Contact Date
                          </a>
                	</td>
			<td class="portColHdr" width=5>&nbsp;</td>         
			<td class="portColHdr">
                    	<a href="<%=siteReportTypeSortAction%>"
                           class="LinkUline"
                            target="_self">Type</a>
                           
                 	 </td>
                 	 <td class="portColHdr" width=5>&nbsp;</td>         
                 	 
			<td class="portColHdr">
			<a href="<%=siteReportStatusSortAction%>"
		  		 class="LinkUline"
		   		 target="_self">Status</a></td>
			<td class="portColHdr" width=5>&nbsp;</td>         
			<td class="portColHdr" width="100">Originator</td>

			</tr>
			</thead>
			
			
	<tbody class="noScroll">
	 <logic:notEmpty name="siteReportPanelForm" property="siteReportDetailList" >
	   <c:forEach items="${siteReportPanelForm.siteReportDetailList}" var="form">
		      <tr style="display:none;">
		      
			  <td class="portBody">
                            <c:choose>
                                    <c:when test='${not empty form.contactDate}'>
                                    <fmt:formatDate value="${form.contactDate}" pattern="dd-MMM-yyyy"/>
                                    </c:when>
                                    <c:otherwise>
                                        &nbsp;&ndash;
                                    </c:otherwise>
                                </c:choose>
                           </td>
                           
                           <td class="portBody">&nbsp;</td>
			   <td class="portBody">
			     <a href="<c:url value="/do/formAction?siteID=${param.siteId}&op=view&formID=${form.primaryKey}&formTypeCode=${form.type.code}"/>">
				<c:out value="${form.type.name}"/>
			     </a></td>
			     
			   <td class="portBody">&nbsp;</td>			             
			  
			   <td class="portBody"><c:out value="${form.status.name}"/></td>
			   
			   <td class="portBody">&nbsp;</td>				         
			   <td class="portBody"><c:out value="${form.originator.lastName}"/>, <c:out value="${form.originator.firstName}"/></td>
				      </tr>
				   </c:forEach>
                 </logic:notEmpty>
                 <logic:empty name="siteReportPanelForm" property="siteReportDetailList" >
				      <tr>
				         <td class="portBody"> No Data Found.
                          </td>
                 </logic:empty>

				        <!-- =========================================== -->
        <!-- END FORM LIST -->
        <!-- =========================================== -->
		</tbody>
		</table>


		    </div>
					</td>
				</tr>
		</table>
   </html:form>
   
<script language="JavaScript">
   processFilters(document.siteReportPanelForm,'siteReportTypeId,siteReportStatusId','site_report_data');
</script>
