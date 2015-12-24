<!--
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://gcsm.bms.com/tld/form.tld" prefix="form"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
-->
<div class="standard">
 <p class="standard_bold">
    Confirmation
 </p>
  <p> 
   The <c:out value="${form.classify}"/> has been <c:if test="${param.op == 'submit'}">authored.</c:if>
                                      <c:if test="${param.op == 'complete'}">reviewed.</c:if>
  </p>
  <!-- Edit the Form. -->
<p>
   <table class="standard">
          <tr>           
          <td width="10%">Date</td>
          <td width="90%">
              <c:if test="${form.completed}">
                     <c:out value="${form.completeDateTime}"/>
              </c:if>
              <c:if test="${not form.completed}">
                     <c:out value="${form.submitDateTime}"/>
              </c:if>
           </td>
         </tr>
       <tr>           
          <c:if test="${not empty form.sitePK}"><td width="10%">Protocol/Site</td></c:if>
          <c:if test="${empty form.sitePK}"><td width="10%">Protocol</td></c:if>
           <td width="90%"><c:out value="${form.protocolSite}"/></td>
         </tr>
       <tr>       
           <td width="10%"><c:out value="${form.classification}"/> </td>
           <td width="90%">
              <a href="<c:url value="/do/formAction?op=view&formID=${form.primaryKey}"/>">
                 <c:out value="${form.type.name}"/>
                 </a>
              </td>
         </tr>
   </table>      
</p>
<jsp:include page="/views/letter/followUpLetterInc.jsp"/>
</div>