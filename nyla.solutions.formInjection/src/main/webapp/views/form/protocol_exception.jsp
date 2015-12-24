<!--
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://gcsm.bms.com/tld/form.tld" prefix="form"%>
-->
<script>
   function signPECompletion(aForm)
   {
      if(validate(aForm,false))
      {
         aForm.action = aForm.action+"?op=signCompletion";
          aForm.visitor.value = "com.bms.informatics.gcsm.form.visitor.PartialAnswerVisitor";
         aForm.submit();
      }
   }//-----------------------------------------------------------------------------------
</script>
<OTML:OFFLINE-FORM OFFLINE-ID="Protocol Exception" TITLE="Protocol Exception Submission">
   <form id="aForm" name="aForm" action="<c:url value="/do/formAction"/>" method="POST">
   <table class="formHeader"width="100%" cellpadding="0">
        <tr>
            <td>
            <!-- Form Type Name -->
	       <c:out value="${sessionScope.form.name}"/> <c:out value="${sessionScope.form.classification}"/><!-- <c:out value="${form.primaryKey}"/> -->
              <input type="hidden" name="op" value=""/>
              <input type="hidden" name="visitor" value=""/>              
               <input type="hidden" name="formID" value="<c:out value="${form.primaryKey}"/>"/>
               <input type="hidden" name="formTypeName" value="<c:out value="${sessionScope.form.name}"/>"/>
	     </td>
	    <td align="right">
	       <form:guard>
   	       <input type="button" value="Save" onclick="saveForm(aForm);" />
   	       <input type="button" value="Delete" onclick="deleteForm(aForm);" />
   	       <input type="button" value="Submit" onclick="signForm(aForm);"/>
  	       </form:guard>
     	      <form:guard checkPoint="edit">
     	             <input type="button" value="Edit" onclick="renewForm(aForm);" />
     	      </form:guard>
	    </td>
         </tr>
<!-- Save Confirmation -->
    <jsp:include page="/views/form/save_confirmation.jsp"/>
<!-- ======================================= -->                
   </table>
   <!-- ======================================================= -->

   <!-- SECTION 0 -->
   <p>
      <table width="100%" border="0" cellspacing="0" cellpadding="3">
         <!-- Preparer , Prepared on-->
         <form:questionaire  id="2" questionWidth="20%" answerWidth="80%"/>
      </table>
   </p>
   <!-- ======================================================= -->
    <!-- SECTION 1 -->
     <p>
        <form:sectionContainer id="1" questionWidth="90%">
            <tr>
            <td class="indent">

            <table width="95%">
                <form:questionaire id="3" autoNumber="true"/>
                <tr>
                    <td colspan="2" style="height: 12px;"></td>
                </tr>
            </table>

            </td>
            </tr>
        </form:sectionContainer>
    </p>
 <%--Section 2--%>
     <p>
        <form:sectionContainer id="2" questionWidth="90%">
            <tr>
            <td class="indent">

            <table width="95%">
                <form:questionaire id="4,5,6,7" autoNumber="true" altStyleClass="bgLtGray" />
                <tr>
                    <td colspan="2" style="height: 12px;"></td>
                </tr>
            </table>

            </td>
            </tr>
        </form:sectionContainer>
    </p>

 <%--Section 3--%>
     <p>
        <form:sectionContainer id="3" questionWidth="90%">
            <tr>
            <td class="indent">
                <form:questionaire id="8,9" textStyleClass="indent" autoNumber="true"/>
            </td>
            </tr>
        </form:sectionContainer>
    </p>
 <%--Section 4--%>
     <p>
        <form:sectionContainer id="4" questionWidth="90%">
            <tr>
            <td class="indent">
                <form:questionaire id="10" textStyleClass="indent" autoNumber="true"/>
            </td>
            </tr>
        </form:sectionContainer>
    </p>
 <p>
        		
            
            <form:guard checkPoint="complete">
            
            
            <%--Section 5--%>
	         
        	<form:sectionContainer id="5" questionWidth="90%">
        
        
                  <tr>
                        <td class="indent">
                            <table width="95%">
                            <form:answer id="12"/>
							</table>
                        </td>
                  </tr>
                     <tr>
                              <td class="indent">
                                     <table width="95%">
                                     <form:answer id="13"/>
									 </table>                                                              
                            </td>
                         </tr>
                  <tr>
                        <td class="indent">
                           <input type="button" value="Sign" onclick="signPECompletion(aForm);"/>
                        </td>
                  </tr>
       
             </form:sectionContainer>
             
              </form:guard>
              
              
                  <!-- ================================================= -->
            <form:guard checkPoint="completion">
                      <table width="95%">            
                               <tr>
                                             <td class="indent">
                                                 <form:questionaire id="12,13" textStyleClass="indent" autoNumber="true"/>
                                             </td>
                              </tr>
                           </table>
                  </form:guard>                  

    </p>

    <!-- SECTION Electronic signature -->
   <form:signatures/>

   </form>
</OTML:OFFLINE-FORM>