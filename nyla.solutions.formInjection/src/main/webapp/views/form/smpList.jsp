<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://gcsm.bms.com/tld/security.tld" prefix="security"%>
<div id="myForm_body">

<html:form action="/SMPListAction">
	<input type="hidden" name="landingPage" value="smp" />
	<input type="hidden" name="method" value="list" />
  <table width="100%" cellpadding="1" cellspacing="1" border="0">
    <tr>
      <td height="29px"><h3><br>
          <span class="reportsHeader">Site Monitoring Plans</span><br>
        </h3></td>
    </tr>
    <tr>
      <td width="100%" style="padding:15px;">
      <table id="smp_list_tbl" width="100%" border="0" cellpadding="0" cellspacing="0" style="border:1px solid #000">
      <thead>
          <tr>
            <td height="21" align="left" valign="middle" class="portTitle">&nbsp;&nbsp;SMPs</td>
            <td colspan="6" align="right" valign="middle" class="portFilterLbl"> Status &nbsp;&nbsp; </td>
            <td width="137" align="left" valign="middle" class="portFilterLbl">
              <html:select name="smpListForm" property="statusFilter" styleClass="portFilter" onchange="processFilters(this.form,'statusFilter','smp_list_tbl');" >
                <html:option value="-1">All</html:option>
                <html:options collection="formStatus"
                   property="value" labelProperty="name" />
              </html:select>
            </td>
            <td width="10" class="portFilterLbl">&nbsp;</td> 
          </tr>
          <tr>
            <td width="8" height="21" align="left" valign="middle" class="portColHdr">&nbsp;</td>
            <td width="126" valign="middle" class="portColHdr" style="padding-left:5px"><a href="#" onclick="javascript:sortBy('smp_list_tbl', 1)">Protocol</a></td>
            <td width="6" valign="middle" class="portColHdr" style="padding-left:5px">&nbsp;</td>
            <td width="178" valign="middle" class="portColHdr" style="padding-left:5px"><a href="#" onclick="javascript:sortBy('smp_list_tbl', 3, 'date')">Update Date</a></td>
            <td width="6" valign="middle" class="portColHdr" style="padding-left:5px">&nbsp;</td>
            <td width="133" valign="middle" class="portColHdr" style="padding-left:5px"><a href="#" onclick="javascript:sortBy('smp_list_tbl', 5)">Status</a></td>
            <td width="6" valign="middle" class="portColHdr" style="padding-left:5px">&nbsp;</td>
            <td width="109" valign="middle" class="portColHdr" style="padding-left:5px"><a href="#" onclick="javascript:sortBy('smp_list_tbl', 7)">PTM</a></td>
            <td class="portColHdr" style="padding-left:5px" colspan="2">&nbsp;</td> 
          </tr>
        </thead>
        <tbody>
          <logic:notEmpty name="smpList">
            <logic:iterate id="smpList" name="smpList">
   				<c:choose>
   					<c:when test='${smpList.status == "Incomplete"}'>
   						<c:set var="style" value="redPsiTableData" scope="page" />
   					</c:when>
   					<c:otherwise>
   						<c:set var="style" value="psiTableData" scope="page" />
   					</c:otherwise>
   				</c:choose>
              <tr>
                <td height="21" valign="middle" align="left" class="psiNavText">&nbsp;</td>
                <td class="<c:out value='${pageScope.style}'/>" valign="top" style="padding-left:5px">
                  <logic:notEmpty name="smpList" property="protocol">
                    <a href="formAction?op=view&formID=<bean:write name="smpList" property="formID" />"><bean:write name="smpList" property="protocol"/></a>
                  </logic:notEmpty>
                  <logic:empty name="smpList" property="protocol">
                    <a href="formAction?op=view&formID=<bean:write name="smpList" property="formID" />">Unassigned</a>
                  </logic:empty>
                </td>
            <td class="<c:out value='${pageScope.style}'/>"></td>
                <td valign="top" class="<c:out value='${pageScope.style}'/>" style="padding-left:5px">
            	<logic:notEmpty name="smpList" property="updateDate">
                    <bean:write name="smpList"  property="updateDate"/>
                  </logic:notEmpty>
                  <logic:empty name="smpList" property="updateDate">
                    &#045;
                  </logic:empty>
                </td>
                <td class="<c:out value='${pageScope.style}'/>"></td>
                <td valign="top" class="<c:out value='${pageScope.style}'/>" style="padding-left:5px">
                  <logic:notEmpty name="smpList" property="status">
                    <bean:write name="smpList"  property="status"/>
                  </logic:notEmpty>
                  <logic:empty name="smpList" property="status">
                    &#045;
                  </logic:empty>
                </td>
                <td class="<c:out value='${pageScope.style}'/>"></td>
                <td valign="top" class="<c:out value='${pageScope.style}'/>" style="padding-left:5px">
                  <logic:notEmpty name="smpList" property="PTM">
                    <bean:write name="smpList"  property="PTM"/>
                  </logic:notEmpty>
                  <logic:empty name="smpList" property="PTM">
                    &#045;
                  </logic:empty>
                </td>
                <td class="<c:out value='${pageScope.style}'/>" colspan="2"></td>
              </tr> 
            </logic:iterate>
          </logic:notEmpty>
          <logic:empty name="smpList">
            <tr height = "100%" valign="middle">
              <td class="portBody" colspan="11" style="padding-left:5px" align="center"><i> No Data Found</i> </td>
            </tr>
          </logic:empty>
		</tbody>
        </table>
        </td>
    </tr>
  </table>
  </html:form>
</div>
