<!--
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://gcsm.bms.com/tld/form.tld" prefix="form"%>
-->

<c:if test="${form.submitted}">
<p class="sectionText">
   Electronic Signature
</p>
   <p>
      <table class="standard">
         <tr>
             <td class="standard_bold">&nbsp;&nbsp;&nbsp;Signed By:</td><td><c:out value="${form.submitUserName}"/></td><td width>&nbsp;<c:out value="${form.submitDateTime}"/></td>
        </tr>
         <tr>
             <td class="standard_bold">&nbsp;&nbsp;&nbsp;Reason:</td><td>Authored</td><td></td>
        </tr>
      </table>
   </p>
   <c:if test="${form.completed}">
      <p>
         <table class="standard">
            <tr>
                <td class="standard_bold">&nbsp;&nbsp;&nbsp;Signed By:</td><td><c:out value="${form.completeUserName}"/></td><td width>&nbsp;<c:out value="${form.completeDateTime}"/></td>
           </tr>
            <tr>
                <td class="standard_bold">&nbsp;&nbsp;&nbsp;Reason:</td><td>Reviewed</td><td></td>
           </tr>
         </table>
      </p>
   </c:if>
</c:if>
