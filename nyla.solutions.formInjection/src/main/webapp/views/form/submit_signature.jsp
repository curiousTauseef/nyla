<!--
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://gcsm.bms.com/tld/form.tld" prefix="form"%>
-->
<form name="aForm" action="<c:url value="/do/formAction"/>" method="POST">
<input type="hidden" name="formID" value="<c:out value="${form.primaryKey}"/>"/>
<p>
   <table class="formHeader" width="100%" cellpadding="0">
        <tr>
            <td>eSignature</td?
          </tr>
   </table>
 </p>
<p>
   <table>
         <c:if test="${not empty requestScope.submitException || not empty pageContext.exception}">
            <tr class="error">
               <td colspan="2" class="error">Submission Error
                <!--<c:out value="${requestScope.submitException}"/> -->
                </td>
            </tr>                 
         </c:if>

      <tr>
         <td>&nbsp;</td>
         <td>
           If you acknowledge the content of this report, enter your username and password below.  
           Submitting your username/password will apply your electronic signature to this document.
         </td>
        </tr>
        <tr>
            <td colspan="2">&nbsp;</td>
        </tr>
        <tr>
            <td>Username</td>
            <td>
               <input type="text" name="loginID" value="<c:out value="${param.loginID}"/>"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
               <div id="loginIDError" class="error">&nbsp;</div> &nbsp;
            </td>
        </tr>        
        <c:if test="${not empty requestScope.userNameError}">
        <tr>
            <td>&nbsp;</td>
            <td>
               <span class="error"><c:out value="${requestScope.userNameError}"/></span>
            </td>
        </tr>
         </c:if>
          <tr>
            <td>Password</td>
            <td>
               <input type="password" name="password"/>
               <input type="hidden" name="formID" value="${form.primaryKey}"/>
            </td>
        </tr>
          <tr>
            <td colspan="2">
               <div id="passwordError" class="error">&nbsp;</div> &nbsp;
            </td>
        </tr>        
        <c:if test="${not empty requestScope.passwordError}">
        <tr>
            <td>&nbsp;</td>
            <td>
               <span class="error"><c:out value="${requestScope.passwordError}"/></span>
            </td>
        </tr>
         </c:if>        
        <tr>
           <td colspan="2">&nbsp;</td>
         </tr>
        <tr>
           <td colspan="2">
                     <input type="button" value="Cancel" onclick="viewForm(aForm);"/> &nbsp;
                     <c:if test="${param.op == 'signCompletion'}">
                            <input type="button" value="Submit" onclick="completeForm(aForm);"/>
                      </c:if>
                     <c:if test="${param.op != 'signCompletion'}">
                            <input type="button" value="Submit" onclick="submitForm(aForm);"/>
                      </c:if>
             </td>
         </tr>
   </table>
</p>
</form>