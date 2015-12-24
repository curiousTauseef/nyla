<%-- userInfo.jsp
	 Description: This file displays the logged in user's name, role,
	 			  the user's date, and the user's time zone.  It also
				  allows the user to switch between assigned multiple
				  roles.
	 Versions:
				1.0 - Original created by DMane
				1.1 - Date information and CSS was modified
				1.2 - Time zone information for display was added

	 Copyright (c) 2005 Bristol-Myers Squibb. All Rights Reserved.
	 BMS Confidential
         The  information contained  in this application  is intended for
	 distribution  to  Bristol-Myers Squibb employees and contractors
	 only. You are expressly prohibited  from forwarding all  or part
	 of  this  information or  otherwise  sharing its  contents  with
	 anyone outside the company.

	 This document should be disposed of properly.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="java.math.BigDecimal, java.util.*, java.text.*,
                 org.apache.commons.lang.StringUtils,
                 com.bms.informatics.gcsm.common.web.WebTags,
                 com.bms.informatics.gcsm.security.user.data.GCSMUser"%>
<bean:define id="accessRole" name="accessRole"/>
<%
	boolean  isbackweb= StringUtils.contains(request.getHeader("User-Agent"),"BackWeb");
	Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
	String DATE_FORMAT = "dd MMMMM yyyy";
	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
	sdf.setTimeZone(TimeZone.getDefault());
	String fullDate=sdf.format(calendar.getTime());

	//String landingPage="home";
	//String landingPage=request.getParameter("landingPage");
	String userAction="/UserPanelAction";
	String userActioneURL=response.encodeURL(userAction);
%>

<%--<otml:get depth="1" activation="on-end-of-current-context" alias="javascript:'home?roleId=' + extractedURL.queryParam('roleId')">--%>
<%--<otml:get depth="1" activation="on-end-of-current-context">
	<OTML:DRIVE-DHTML-EVENTS CODE="resource:driveDropDownEvent.js">--%>
		<html:form action="<%=userActioneURL%>" method="GET">
			<html:hidden property="method" value="changeRole"/>
			<input type="hidden" name="landingPage" value="home">
			<input type="hidden" name="isBackweb" value="<%=isbackweb%>">
				<table border="0" width="100%"  height="23"  cellspacing="0" cellpadding="0" bgcolor="#C9C9C9" id="user_info_data_tbl">
					<tr class="statusBarBg">
						<bean:define id="userBean" name="userPanelForm" property="user"/>
						<td valign="middle" align="left" nowrap class="statusBarFont" style="padding-left:10px; width:10%">
							<bean:write name="userBean" property="firstName"/>&nbsp;
							<bean:write name="userBean" property="lastName"/>&nbsp;:
						</td>
						<td class="statusBarFont" style="width:5px">
							<OTML:REPLACE TEXT="OFFLINE">ONLINE</OTML:REPLACE>
						</td>
						<td width="55" class="statusBarFont">
							&nbsp;|&nbsp;&nbsp;Role:
						</td>

						<bean:size id="userRolesSize" name="userBean" property="userRoles"/>
						<logic:greaterThan value="1" name="userRolesSize">
							<td style="width:208px" class="statusBarFont">
								<html:select name="userPanelForm" property="roleId"
											 styleClass="FormEntry" onchange="submitUserPanelForm(this.form);">
									<bean:define id="userRoles"  name="userBean" property="userRoles" />
									<html:options collection="userRoles"
												  property="label"
												  labelProperty="label" />
								</html:select>
							</td>
							<%--
							<script language="JavaScript">
							   	selectBackwebCurrentRole()
							</script>
							--%>
						</logic:greaterThan>
						<logic:equal value="1" name="userRolesSize">
							<td width="368" nowrap class="statusBarFont">
								<bean:write name="accessRole"/>
							</td>
							<html:hidden property="roleId"/>
						</logic:equal>

						<td align="right" valign="middle" nowrap class="statusBarFont" style="padding-right:10px">
							<%=fullDate%>&nbsp;
                                                        <%
                                                          GCSMUser gcsmUser = (GCSMUser)session.getAttribute(WebTags.USER);
                                                        %>
                                                        <%=gcsmUser.getTimeZone() != null ? gcsmUser.getTimeZone() : ""%>
					  </td>
			  	</tr>
		</table>
		</html:form>
<%--	</OTML:DRIVE-DHTML-EVENTS>
</otml:get>--%>
