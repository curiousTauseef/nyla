<!--
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://gcsm.bms.com/tld/form.tld" prefix="form"%>
-->
<div class="confirmationText">
   <p  class="confirmationHeader">
         Deleted
   </p>
   <br/>

   <p>   
         The form has been deleted.
   </p>   
   <p>
       <c:if test="${not empty form.schedulePK}">
            <!-- Schedule visit -->
            <a href="<c:url value="/do/formAction?op=createForScheduled&scheduleID=${form.schedulePK}"/>">Create New <c:out value="${form.type.name}"/> Form</a>
         </c:if>
       <c:if test="${empty form.schedulePK}">
            <!--Un Schedule visit -->       
            <a href="<c:url value="/do/formAction?op=createForUnscheduled&siteID=${form.sitePK}&formTypeName=${form.type.name}"/>">Create New <c:out value="${form.type.name}"/> Form</a>
         </c:if>
   </p>
</div>