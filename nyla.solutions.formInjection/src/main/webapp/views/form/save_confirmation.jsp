<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://gcsm.bms.com/tld/form.tld" prefix="form"%>
<!-- ======================================= -->
<!-- Save Confirmation -->
<tr>
   <td colspan="2" align="center">
             <span class="confirmationText" id="confirmation">
                   <c:if test="${requestScope.actionResult == 'success' }">THE FORM HAS BEEN SAVED.</c:if>
             </span>&nbsp;
     </td>
</tr>
<!-- ======================================= -->