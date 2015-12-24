<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://gcsm.bms.com/tld/security.tld" prefix="security"%>
<%
    String landingPage="";
    String uid="";
    String siteId="";
    String uidUrl="";
    String siteIdUrl="";
    String role="";
    String roleUrl="";
    String scrollNoScroll="auto";
    String contextPath=request.getContextPath();
    String visitAction="";
    String visitTypeSortAction="";
    String protocolSiteSortAction="";   
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
        visitAction="/VisitPanelAction?method=doFilter&landingPage="+landingPage + uidUrl + siteIdUrl + roleUrl;
    } else {
        visitAction="/VisitPanelAction?method=doFilter&landingPage="+landingPage + uidUrl + siteIdUrl + roleUrl;
    }
    //visitTypeSortAction="/VisitPanelAction?method=sort&sortField=visitType&landingPage="+landingPage +  uidUrl + siteIdUrl + roleUrl;
    //protocolSiteSortAction="/VisitPanelAction?method=sort&sortField=protocol&landingPage="+landingPage +  uidUrl + siteIdUrl + roleUrl;
    
    visitTypeSortAction="javascript:sortBy('visit_table_data', 1)";
    protocolSiteSortAction="javascript:sortBy('visit_table_data', 2)";
%>

<html:form action="<%=visitAction%>">
    	<%-- Protlet Container --%>
            <script language="JavaScript">
            var url="";
		<!--
		function getVisitPanelWidth() {
			var leftNav = document.getElementById('left_nav_tbl');
			return leftNav != null && leftNav.style.display == 'none' ? '330px' : '290px';
		}
		function newVisitSchedule() {

			<%
				if (landingPage.equalsIgnoreCase("sites")) {
				//scrollNoScroll="scroll; width: expression(getVisitPanelWidth())";                                          
			%>
				url = "<html:rewrite page="/do/newVisitAction?method=initNewVisitForm"/>" + "&operation=&siteId=" + <%= request.getParameter("siteId") %> + "&doValidate=no";
			<% } else {
				//scrollNoScroll="auto";
			 %>
				url = "<html:rewrite page="/do/newVisitAction?method=initNewVisitForm"/>" + "&operation=" + "&doValidate=no";

			<% } %>

			window.open(url, "newVisitSchedule", "width=280, height=420, resizable=no, scrollbars=yes, location=no, status=no, directories=no, menubar=no");
		}

		function editVisitSchedule(visitId, type) {

			<%
				if (landingPage.equalsIgnoreCase("sites")) {
			%>			
						url = "<html:rewrite page="/do/newVisitAction?method=initNewVisitForm"/>" + "&doValidate=no&operation=" + type + "&visitScheduleId=" + visitId + "&siteId=" + <%= request.getParameter("siteId") %>;

			<% } else { %>
					url = "<html:rewrite page="/do/newVisitAction?method=initNewVisitForm"/>" + "&doValidate=no&operation=" + type + "&visitScheduleId=" + visitId;
				<% } %>
				
			window.open(url, "newVisitSchedule", "width=280, height=420, resizable=no, scrollbars=yes, location=no, status=no, directories=no, menubar=no");
		}

		function confirmDelete(visitScheduleId) {
		  if (confirm('Do you really want to delete this visit?')) {
			var backurl = escape(location.href);
			var url = '<html:rewrite page="/do/newVisitAction?method=doDeleteSchedule&visitScheduleId=" />' + visitScheduleId + '&backurl=' + backurl + '&doValidate=no';
			window.location = url;
			/*
			with (document.visitPanelForm) {
				action = '<html:rewrite page="/do/VisitPanelAction?method=doDeleteSchedule&visitScheduleId=" />' + visitScheduleId + '&landingPage=' + '<%=landingPage %>';
				submit();
			}
			*/
		  }
		}
		-->
       </script>        
            <link rel="stylesheet" href="<html:rewrite page="/skins/default/css/clinsight.css"/>" type="text/css">
            <table align="left" border="0" cellpadding="0" cellspacing="0" width="100%">
                <%-- Header --%>
                <tr>
                    <td class="portTitle" height="27">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr class="portFilterLbl">
                                <td width="15" style="background-color:#FFF"><html:img  border="0" page="/skins/default/images/phead_left.gif"/></td>
                                <td class="portTitle" nowrap>SCHEDULED VISITS</td>
                                <td width="5">&nbsp;</td>
                                <td align="left" width="180"><html:img  border="0" page="/skins/default/images/phead_visits_icon.gif"/></td>
                                <td width="700">&nbsp;</td>
                                <td width="15" align="right">
                                
                                <security:guard permission="VisitCreate">
					<OTML:REMOVE>
						<html:img alt="create new schedule visit" border="0" page="/skins/default/images/button_new_on.gif" onclick="newVisitSchedule()" style="cursor:hand"/>
					</OTML:REMOVE> 
				</security:guard>
				
                                </td>
                                <td><html:img border="0" page="/skins/default/images/blank.gif" width="10" height="1"/></td>
                                <td width="15"><html:img alt="resize" border="0" style="cursor:hand" page="/skins/default/images/phead_expand_on.gif"  onclick="resizePortlet('visits');"/></td>
                                <td width="15" style="background-color:#FFF"><html:img  border="0" page="/skins/default/images/phead_rt.gif"/></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <%-- Data --%>
                <tr>
                    <td>
                        <div id="inner_visits" style="overflow: <%=scrollNoScroll%>; height: 100px; border: solid 1px #CCCCFF">
                            <table width="100%" border="0" cellspacing="0" cellpadding="0" id="visit_table_data">
                            <thead>
				    <tr class="noScroll">

					<td align="left" class="portColHdr">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="sortBy('visit_table_data', 0, 'date')" class="LinkUline" target="_self">Date/Time</a>
					</td>

					<td align="left" class="portColHdr">
					  <a href="<%=visitTypeSortAction%>" class="LinkUline" target="_self">Visit Type</a>
					</td>


					<%                                
					if(landingPage!=null && !landingPage.equals("sites"))
					{                                
					%>                     

					<td align="left" class="portColHdr">
					  <a href="<%=protocolSiteSortAction%>" class="LinkUline" target="_self">Protocol/Site</a>
					</td>

					<% }  %>
				    </tr>
                            </thead>
                            
		<tbody class="noScroll">

		<logic:notEmpty name="visitPanelForm" property="visitDetailList">   

			<nested:iterate  name="visitPanelForm" property="visitDetailList" id="visitList"> 
			  <tr>
                               	<td class="portBody">

                           	   <nested:notEmpty property="visitScheduleId">
                           	   
                           	   	<img border=0 width="1px" height="1px" src="<html:rewrite page="/skins/default/images/blank.gif" />" />
					
					
					<OTML:DISABLE APPLICABLE-TO-DHTML-EVENTS="yes">
					         <img alt="cancel visit" style="cursor:hand" border=0 onclick="confirmDelete('<nested:write property="visitScheduleId"/>')" src="<html:rewrite page="/skins/default/images/img_delete.gif" />" />
                            		</OTML:DISABLE>
                            		
                            		<OTML:DISABLE>
                            		
                            		   <nested:equal property="plannedVisit" value="true">
						<a style="color:red; cursor:hand" href="javascript:editVisitSchedule('<nested:write property="visitScheduleId"/>','planEdit');" >
	                                     <nested:write property="strDate"/>
	                                    </a>
					   </nested:equal> 
					   
					    <nested:notEqual property="plannedVisit" value="true">
						<a style="cursor:hand" href="javascript:editVisitSchedule('<nested:write property="visitScheduleId"/>','scheduleEdit');" >
	                                     <nested:write property="strDate"/>
	                                    </a>
					   </nested:notEqual> 
					   
					 </OTML:DISABLE>
                                   </nested:notEmpty>
									                          
                                   <nested:empty property="visitScheduleId">
						<nested:write  property="strDate"/>
			       	   </nested:empty>
				       <nested:empty property="strDate">
						&ndash;
				       </nested:empty>
				   </td>
				   <td class="portBody">
                                   <nested:notEmpty  property="strVisitType">
						<nested:equal property="plannedVisit" value="true">
				   <!-- link to create form for visit schedule -->
				     <span style="color:red"><nested:write  property="strVisitType"/></span>
						</nested:equal>
						<nested:notEqual property="plannedVisit" value="true">
							<nested:notEqual  property="strVisitType" value="PSA">
				   <!-- link to create form for visit schedule -->
				     <a style="cursor:hand"
					href="<c:url value="/do/formAction?op=createForScheduled&scheduleID="/><nested:write property="visitScheduleId"/>&siteID=<nested:write property="siteID"/>&formTypeCode=<nested:write  property="strVisitType"/>"><nested:write  property="strVisitType"/></a>
							</nested:notEqual>
							<nested:equal property="strVisitType" value="PSA">
				     <a style="cursor:hand"
					href="<c:url value="/do/formAction?op=createForScheduled&scheduleID="/><nested:write property="visitScheduleId"/>&formTypeCode=<nested:write  property="strVisitType"/>"><nested:write  property="strVisitType"/></a>
							</nested:equal>
						</nested:notEqual>
				       </nested:notEmpty>
				       <nested:empty  property="strVisitType">
						&ndash;
				       </nested:empty>
				   </td>
				   <nested:equal property="plannedVisit" value="true">
				     <td class="portBody" style="color:red">
				   </nested:equal>
				   <nested:notEqual property="plannedVisit" value="true">
				     <td class="portBody">
				   </nested:notEqual>
							   
					<%
                                	if(landingPage!=null && !landingPage.equals("sites"))
					{
					%>   		   
							   
					<nested:notEmpty  property="strProtocol">
						<nested:write  property="strProtocol"/>
					 </nested:notEmpty>
					 <nested:empty  property="strProtocol">
						&ndash;
					 </nested:empty>
						         
					<% } %>
				
				
			</td>
	    </tr>
</nested:iterate>


</logic:notEmpty>

<logic:empty  name="visitPanelForm" property="visitDetailList" >
	<tr>
	<td class="portBody" colspan="4" align="center"><i>No Data Found</i></td>
        </tr>
</logic:empty>

                            </tbody>
                            </table>
                        </div>
                    </td>
                </tr>
            </table>
</html:form>

