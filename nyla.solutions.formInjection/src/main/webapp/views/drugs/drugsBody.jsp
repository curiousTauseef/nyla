<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://gcsm.bms.com/tld/security.tld" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<div id="drugReconciliation_body">
<script type="text/javascript" language="Javascript">
<!--
 function sortMyForm(frm, val) {
   //alert(frm.name);
   frm.elements['sortByFieldName'].value = val;
   //alert(frm.elements['sortByFieldName'].value);

   frm.action += "?method=sortDrugReconcilationPanel";
   //alert(frm.action);
   frm.submit();
 }
-->
</script>
<html:form action="/DRWAction">
  <html:hidden property="sortFieldName"/>
  <table width="100%" cellpadding="1" cellspacing="1" border="0">
    <tr>
      <td height="29px"><h3><br>
          <span class="reportsHeader">Drug Reconciliation</span><br>
        </h3></td>
    </tr>
    <tr>
<%--      <td><a href="<c:url value="/do/formAction?op=createForUnscheduled&siteID=${param.siteId}&formTypeName=Drug Reconciliation Worksheet"/>">Create New Drug Reconciliation Worksheet</a></td>--%>
        <td>
        <c:if test='${requestScope.canViewDRW == "true"}'>
        <a href="<c:url value="/do/formAction?op=createForUnscheduled&siteID=${param.siteID}&formTypeName=Drug Reconciliation Worksheet&formTypeCode=DRW"/>">Create New Drug Reconciliation Worksheet</a>
        </c:if>
        </td>
    </tr>
    <tr>
      <td width="100%" style="padding:15px;"><table width="100%" border="0" cellpadding="0" cellspacing="0" style="border:1px solid #000">
          <tr>
            <td height="21" colspan="5" align="left" valign="middle" class="portTitle">&nbsp;&nbsp;Worksheets</td>
          </tr>
          <tr>
            <td width="8" height="21" align="left" valign="middle" class="portColHdr">&nbsp;</td>
            <td width="226" valign="middle" class="portColHdr" style="padding-left:5px">Description</td>
            <td width="6" valign="middle" class="portColHdr" style="padding-left:5px">&nbsp;</td>
            <td width="109" valign="middle" class="portColHdr" style="padding-left:5px"><a href="<c:url value="/do/DRWAction?siteID=${param.siteID}&method=sortDrugReconcilationPanel&landingPage=sites&landingTab=drug"/>">Date</a></td>
            <td class="portColHdr" style="padding-left:5px">&nbsp;</td>
          </tr>
          <c:if test="${not empty drugReconciliationPanelForm.worksheetList}">
            <c:forEach items="${drugReconciliationPanelForm.worksheetList}" var="drw">
              <tr>
                <td width="8" height="21" align="left" class="psiNavText">&nbsp;</td>
                <td class="psiTableData" valign="top" style="padding-left:5px">
                  <c:if test="${not empty drw.description}">
                     <a href="<c:url value="/do/formAction?op=view&formID=${drw.primaryKey}&siteID=${drw.sitePK}&formTypeCode=${drw.type.code}"/>" class="Link_Text"><c:out value="${drw.description}"/></a>
                  </c:if>
                  <c:if test="${empty drw.description}">
                    <a href="<c:url value="/do/formAction?op=view&formID=${drw.primaryKey}&siteID=${drw.sitePK}&formTypeCode=${drw.type.code}"/>" class="Link_Text">N/A</a>
                  </c:if>
                </td>
                <td class="psiTableData"></td>
                <td valign="top" class="psiTableData" style="padding-left:5px">
                  <c:if test="${not empty drw.updateDate}">
                     <fmt:formatDate value="${drw.updateDate}" pattern="dd-MMM-yyyy"/>
                  </c:if>
                  <c:if test="${ empty drw.updateDate}">
                    ---
                  </c:if>

                </td>
                <td class="psiTableData"></td>
              </tr>
            </c:forEach>
          </c:if>
          <c:if test="${empty drugReconciliationPanelForm.worksheetList}">
            <tr>
              <td height="21">&nbsp;</td>
              <c:choose>
              <c:when test='${requestScope.canViewDRW == "true"}'>
              <td class="portBody" colspan="10" style="padding-left:5px" align="center"> --- No data found ---- </td>
              </c:when>
              <c:otherwise>
               <td class="portBody" colspan="10" style="padding-left:5px" align="center">You are not authorized to view worksheets for this study </td>
               </c:otherwise>
               </c:choose>
            </tr>
          </c:if>
        </table></td>
    </tr>
  </table>
  </html:form>
</div>
