<!--
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://gcsm.bms.com/tld/form.tld" prefix="form"%>
-->
<p  class="">
   Deleted
</p>
<br/>
<p>   
   The form has been deleted.
<p>   
<p>
   <a href="<c:url value="/do/formAction?op=createForScheduled&scheduleID=${form.schedulePK}"/>">Create New <c:out value="${form.type,name}"/> Form</a>
</p>