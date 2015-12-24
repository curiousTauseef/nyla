<!--
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://gcsm.bms.com/tld/form.tld" prefix="form"%>
-->
<script>

	function conditionalValidate(aForm) {
		
		var conditionalQuestionsTable = new Hashtable() ;
		var requiredQuestionsTable = new Hashtable() ;
		var conditionalQuestionsKeys = new Array();
		conditionalQuestionsTable.put('<form:inputNameForQuestion id="11"/>','No' );
		requiredQuestionsTable.put('<form:inputNameForQuestion id="11"/>','<form:inputNameForQuestion id="12"/>');
	
		var submitform = performValidation(conditionalQuestionsTable, requiredQuestionsTable);
		
		return submitForm;
	}
   function signPDCompletion(aForm)
   {
      if(validate(aForm,false) && conditionalValidate(aForm))
      {
         aForm.action = aForm.action+"?op=signCompletion";
          aForm.visitor.value = "com.bms.informatics.gcsm.form.visitor.PartialAnswerVisitor";
         aForm.submit();
      } else {
      		 printConfirmation('<span class=error>THE FORM CANNOT BE COMPLETED.</span>');
      }
   }//-----------------------------------------------------------------------------------
</script>
<OTML:OFFLINE-FORM OFFLINE-ID="Protocol Deviation" TITLE="Protocol Deviation Submission">
   <form id="aForm" name="aForm" action="<c:url value="/do/formAction"/>" method="POST">
   <table class="formHeader"width="100%" cellpadding="0">
        <tr>
            <td>
            <!-- Form Type Name -->
	       <c:out value="${sessionScope.form.name}"/> <c:out value="${sessionScope.form.classification}"/><!-- <c:out value="${form.primaryKey}"/> -->
              <input type="hidden" name="op" value=""/>
               <input type="hidden" name="formID" value="<c:out value="${form.primaryKey}"/>"/>
               <input type="hidden" name="visitor" value=""/>               
               <input type="hidden" name="formTypeName" value="<c:out value="${sessionScope.form.name}"/>"/>
	     </td>
	    <td align="right">
	         	         <!-- REVIEWER PK: <c:out value="${form.reviewerPK}"/>   -->
     	         <!-- STATUS NM <c:out value="${form.status.name}"/>   -->    
     	         <!-- STATUS PK <c:out value="${form.status.primaryKey}"/>   -->   
	       <form:guard>
   	       <input type="button" value="Save" onclick="saveForm(aForm);" />
   	       <input type="button" value="Delete" onclick="deleteForm(aForm);" />
   	       <input type="button" value="Submit" onclick="signForm(aForm);"/>
  	       </form:guard>
     	          <form:guard checkPoint="edit">
     	             <input type="button" value="   Edit   " onclick="renewForm(aForm);" />
     	           </form:guard>     	          	         
     	          <form:guard checkPoint="complete">
          	             <input type="button" value="Complete" onclick="signPDCompletion(aForm);" />
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
         <!-- Preparer , Prepared on, Protocol, status -->
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
            	<!-- start form:questionaire tag -->
                <form:questionaire id="5,6,7" autoNumber="true"/>
                <!-- end form:questionaire tag -->
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
                <form:questionaire id="8,9,10" autoNumber="true"/>
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
       <form:guard>        
              <table width="100%" border="0" cellspacing="0" cellpadding="3">
                  <tr class="sectionText">
                      <td colspan="2" class="sectionText"><a name="section3">3 - Distribution</a></td>
                  </tr>
              </table>
                  <table width="95%">
                      <form:questionaire id="15" autoNumber="true" styleClass="indent"/>
                      <tr>
                          <td colspan="2" style="height: 12px;"></td>
                      </tr>
                  </table>
       </form:guard> 
              <form:guard checkPoint="complete">
        <form:sectionContainer id="3" questionWidth="90%">              
            <tr>
            <td class="indent">
            <table width="100%" border="0" cellspacing="0" cellpadding="3">
                <form:answer id="11" readOnly="false"/> 
			</table>
            </td>
            </tr>
            <tr>
             <td class="indent">
                     <table width="100%" border="0" cellspacing="0" cellpadding="3">
                     <form:answer id="12" readOnly="false"/> 
                     </table>
             </td>
            </tr>
            <tr class="bgLtGray">
             <td class="indent">
                     <table width="100%" border="0" cellspacing="0" cellpadding="3">
                     <form:answer id="13" readOnly="false"/> 
                     </table>
             </td>
            </tr>
            <tr class="bgLtGray">
             <td class="indent">
                     <table width="100%" border="0" cellspacing="0" cellpadding="3">
                     <form:answer id="14" readOnly="false"/>
                     </table>
             </td>
            </tr>        
         </form:sectionContainer>           
            </form:guard> 
     
            <form:guard checkPoint="completion"> 
              <table width="100%" border="0" cellspacing="0" cellpadding="3">
                  <tr class="sectionText">
                      <td colspan="2" class="sectionText"><a name="section3">3 -  Distribution</a></td>
                  </tr>
              </table>
                  <table width="95%">
                      <form:questionaire id="15" autoNumber="true" textStyleClass="indent"/>
                      <tr>
                          <td colspan="2" style="height: 12px;"></td>
                      </tr>
                  </table>            
                    <form:sectionContainer id="3" questionWidth="90%">          
                        <tr>
                                    <td class="indent">
                                    <table width="95%">
                                        <form:questionaire id="11,12,13,14" autoNumber="true"/>
                                        <tr>
                                            <td colspan="2" style="height: 12px;"></td>
                                        </tr>
                                    </table>     
                               </td>
                         </tr>
                       </form:sectionContainer>
               </form:guard>
                    
   
    </p>
    <!-- SECTION Electronic signature -->
   <form:signatures/>

   </form>
</OTML:OFFLINE-FORM>