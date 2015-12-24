<!--
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://gcsm.bms.com/tld/form.tld" prefix="form"%>
-->
<script type="text/javascript" language="JavaScript">
/**********************************
 * performValidation(conditionalQuestionsTable, requiredQuestionsTable)
 * Performs actual validation,
 * This function needs to go into validation.js
 ********************************/
function performValidation(conditionalQuestionsTable, requiredQuestionsTable) {
var submitform = true;
var conditionalQuestionsKeys = new Array();
conditionalQuestionsKeys =  conditionalQuestionsTable.keys();
var conditionsArray = new Array();
conditionsArray = conditionalQuestionsTable.values();

    for (var  i=0; i<conditionalQuestionsKeys.length; i++) {
         //var conditionalQuestionsKeysArray=conditionalQuestionsKeys[i].split(":");
        if (conditionalQuestionsKeys[i].search(/list/) != -1 ) {
         var listBoxElement = document.getElementById(conditionalQuestionsKeys[i]);
         var selectedAnswer = listBoxElement.options[listBoxElement.selectedIndex].text;
         var requiredAnswer =  conditionalQuestionsTable.get(conditionalQuestionsKeys[i]);
            var foundMatch = false;
            var answerArray = requiredAnswer.split(":");
                for (var index=0; index < answerArray.length; index++) {
                            if (selectedAnswer.match(answerArray[index]) != null)
                                      foundMatch=true;
                                                    }
              if (foundMatch == true) {
<%--             if (selectedAnswer.match(requiredAnswer) != null) {--%>
              var requiredQuestionsArray = requiredQuestionsTable.get(conditionalQuestionsKeys[i]).split(":");
                 for (var index=0; index < requiredQuestionsArray.length; index++) {
                        if (requiredQuestionsArray[index].search(/text/) != -1) {
                            //alert(document.getElementById(requiredQuestionsArray[index]).value);
                            //alert(isEmpty(document.getElementById(requiredQuestionsArray[index]).value));
                          if (document.getElementById(requiredQuestionsArray[index]).value == "" ||
                                document.getElementById(requiredQuestionsArray[index]).value == null) {
                                printError( document.getElementById(requiredQuestionsArray[index]), "Required");
                                submitform = false ;
                           } // If isEmpty

                        } // If text required
                        /***
                         *Conditional Check for List Box
                         */
                        if (requiredQuestionsArray[index].search(/list/) != -1) {
                             listBoxElement = document.getElementById(requiredQuestionsArray[index]);
                             selectedAnswer = listBoxElement.options[listBoxElement.selectedIndex].text;

                          if (selectedAnswer == "" || selectedAnswer == null) {
                                printError( document.getElementById(requiredQuestionsArray[index]), "Required");
                                submitform = false ;
                           } // If isEmpty

                        } // If list required

                 }  // for Index
            } // if Answer
          } // If search list
    } // for i
     return submitform;
}
/*****************************
 * This validateForm(aForm) Function is specific to a form
 * It generates the hashtable which contains a conditionalQuestions and requiredQuestions
 * This Function is specific to each form.
 ******************/
function validateForm(aForm) {

var submitform = false;
 submitform=validate(aForm,false);

//Following Questions are required for conditional validation
//for questionx=questiony means for questionx question y is required.
//18=19 & 20 is required,
// Collect all Ids
var conditionalQuestionsTable = new Hashtable() ;
var requiredQuestionsTable = new Hashtable() ;
var conditionalQuestionsKeys = new Array();
conditionalQuestionsTable.put('<form:inputNameForQuestion id="18"/>','Yes');
requiredQuestionsTable.put('<form:inputNameForQuestion id="18"/>','<form:inputNameForQuestion id="19"/>:<form:inputNameForQuestion id="20"/>');
conditionalQuestionsTable.put('<form:inputNameForQuestion id="30"/>','No');
requiredQuestionsTable.put('<form:inputNameForQuestion id="30"/>','<form:inputNameForQuestion id="31"/>');
conditionalQuestionsTable.put('<form:inputNameForQuestion id="6"/>','Site Initiated Reasons:GCP Issues');
requiredQuestionsTable.put('<form:inputNameForQuestion id="6"/>','<form:inputNameForQuestion id="35"/>');
conditionalQuestionsTable.put('<form:inputNameForQuestion id="36"/>','Phone');
requiredQuestionsTable.put('<form:inputNameForQuestion id="36"/>','<form:inputNameForQuestion id="35"/>');

// loop through conditionalQuestions Table

// This can be seperate generic function
// function validate(conditionalQuestionsTable, requiredQuestionsTable)
// return value false and check if form needs to be submitted.
    var valid =  false;
    valid = performValidation(conditionalQuestionsTable, requiredQuestionsTable);
    if ( valid  && submitform ) {

    	var visitDate = document.getElementById('<form:inputNameForQuestion id="5"/>');

    	if(checkIfDateLaterThanTodayDDMMMYYYY('Visit Date',visitDate,false))
       	signForm(aForm);
    }
     else printConfirmation('<span class=error>THE FORM CANNOT BE SUBMITTED.</span>');

}

 function showAlert(pElement,pIndex)
  {

  if(pElement.options[pIndex].text=='GCP Issues ' || pElement.options[pIndex].text=='GCP Issues')
  alert("If the site is closed for GCP Issues, an issue should be raised");

  }
  function initializePerSCVVisitType(aVisitTypeElement)
    {
       var choice = aVisitTypeElement.options[ aVisitTypeElement.selectedIndex ];
      var phoneIds = new Array('<form:inputNameForQuestion id="7"/>','<form:inputNameForQuestion id="8"/>',
     '<form:inputNameForQuestion id="9"/>','<form:inputNameForQuestion id="10"/>',
     '<form:inputNameForQuestion id="11"/>','<form:inputNameForQuestion id="12"/>',
     '<form:inputNameForQuestion id="13"/>','<form:inputNameForQuestion id="14"/>',
      '<form:inputNameForQuestion id="15"/>','<form:inputNameForQuestion id="16"/>',
      '<form:inputNameForQuestion id="17"/>','<form:inputNameForQuestion id="18"/>',
    '<form:inputNameForQuestion id="20"/>','<form:inputNameForQuestion id="21"/>',
       '<form:inputNameForQuestion id="22"/>','<form:inputNameForQuestion id="23"/>',
       '<form:inputNameForQuestion id="24"/>','<form:inputNameForQuestion id="25"/>',
        '<form:inputNameForQuestion id="26"/>','<form:inputNameForQuestion id="27"/>',
        '<form:inputNameForQuestion id="28"/>','<form:inputNameForQuestion id="29"/>',
       '<form:inputNameForQuestion id="30"/>',
     '<form:inputNameForQuestion id="32"/>','<form:inputNameForQuestion id="33"/>'
                 );
<%--                 var aQuestionPrefix = '<form:inputNameForColumn id="44" tableID="7004" columnNumber="1"/>';--%>
<%--                 var elementId = null;--%>
<%--                 elementId =aQuestionPrefix+'[1][0];tablePK=7004;';--%>
<%--                 phoneIds = phoneIds.concat(elementId);--%>
<%--                   var aQuestionPrefix = '<form:inputNameForColumn id="48" tableID="7006" columnNumber="1"/>';--%>
<%--                elementId =aQuestionPrefix+'[1][0];tablePK=7006;';--%>
<%--                phoneIds = phoneIds.concat(elementId);--%>
                   var visitType = choice.text;
                      switch(visitType) {
                       case "Phone": {
                            setDefaultToNE(phoneIds);
                            break;
                             }
                      default: {
                            clearDefaultToNE(phoneIds);
                           break
                             }
                      }

                   }//----------------------------------------------------

  </script>
  <jsp:include page="/views/form/initial_visit_type.jsp"/>

<OTML:OFFLINE-FORM OFFLINE-ID="Site Closure Visit Form"
    TITLE="<c:out value="${sessionScope.form.name}"/>">
    
  <form id="aForm" name="aForm" action="<c:url value="/do/formAction"/>" method="POST">
   
   <input type="hidden" name="formID" value="<c:out value="${form.primaryKey}"/>"/>



     
   <table class="formHeader" width="100%" cellpadding="0">
   
        <tr>
            <td>
            <!-- Form Type Name -->
	       <c:out value="${sessionScope.form.name}"/> <c:out value="${sessionScope.form.classification}"/> <!-- <c:out value="${form.primaryKey}"/> -->
               <input type="hidden" name="op" value="save"/>
	     </td>
	    <td align="right">
<%--                    <jsp:include page="/views/form/menu.jsp"/>--%>
  	         	 <!-- SITE PK: <c:out value="${form.sitePK}"/>   -->
     	         <!-- REVIEWER PK: <c:out value="${form.reviewerPK}"/>   -->
     	         <!-- STATUS NM <c:out value="${form.status.name}"/>   -->
     	         <!-- STATUS PK <c:out value="${form.status.primaryKey}"/>   -->
     	          <form:guard checkPoint="edit">
     	                         <input type="button" value="   Edit   " onclick="renewForm(aForm);" />
     	           </form:guard>
     	          <form:guard checkPoint="complete">
     	                         <input type="button" value="Complete" onclick="signCompletionForm(aForm);" />
     	           </form:guard>
       	        <form:guard>
                   	        <form:guard checkPoint="site">
                                 	       <input type="button" value="Protocol Deviation" onclick="postFormDeviation(aForm);" />
                                 	       <input type="button" value="Post Issue" onclick="postFormIssue(aForm);" />
                    	        </form:guard>
          	                 <input type="button" value="Save" onclick="saveForm(aForm);" />
               	          <input type="button" value="Delete" onclick="deleteForm(aForm);" />
                   	      <input type="button" value="Submit" onclick="validateForm(aForm);"/>
        	       </form:guard>
	    </td>
         </tr>        
<!-- Save Confirmation -->
    <jsp:include page="/views/form/save_confirmation.jsp"/>
<!-- ======================================= -->                
   </table>
   <!-- ======================================================= -->
   
   
   <!-- SECTION 0 -->   
   
<table width="100%" border="0" cellspacing="0" cellpadding="3">
	
	<tr>
		<td colspan="2" class="questionTextBold">&nbsp;</td>
	</tr> 
	
	<tr>
	
	 <TD>
		 <table width="93%" align=right border="0" cellpadding="3" cellspacing="0">
		 <form:questionaire styleClass="questionTextBold" questionWidth="20%" answerWidth="80%"  id="2,3,4,5" />
                   <!-- Visit Type -->
         <form:question id="36" questionWidth="20%" textStyleClass="questionTextBold" answerWidth="80%" onChange="initializePerSCVVisitType(this);"/>

        </table>
	</TD>

	</TR>
    <tr><td>&nbsp</td></tr>
    <tr><td>&nbsp</td></tr>
    		<tr>

		 <TD>
			 <table width="93%" align=right border="0" cellpadding="3" cellspacing="0">
			 <form:question styleClass="questionTextBold" questionWidth="54%" answerWidth="46%"  id="6" onChange="showAlert(this,this.selectedIndex)" />

</table>
		</TD>
	
	</TR>
	     
    <tr><td>&nbsp</td></tr>

    <tr><td>&nbsp</td></tr>

   <!-- ======================================================= -->
  
   <!-- SECTION 2 -->       
<%--    <p>--%>
    <tr>
     <TD>
       <form:sectionContainer id="2" alignment = "right" questionWidth="92.5%" styleClass="questionTextBold">

       		</TD>

    </tr>
      	<tr>
	<td colspan="2">
	<table width="98%" align=right border="0" cellpadding="3" cellspacing="4">
	<form:questionaire questionWidth="53%" answerWidth="47%" altStyleClass="bgLtGray" textStyleClass="indent" id="7,8,9,10,11,12,13,14,15,16,17,18" autoNumber="true"/>
	</table>
	</td>
	</tr>
	<tr>		
	<td colspan="2">
	<table width="93%" align=right border="0" cellpadding="3" cellspacing="4" class="bgLtGray">
	<form:question id="19,20" questionWidth="50%" answerWidth="50%" altStyleClass="bgLtGray" textStyleClass="indent" autoNumber="true"/>
	</table>
	</td>
	</tr>
	
	<tr>	
	<td colspan="2">
	<table width="98%" align=right border="0" cellpadding="3" cellspacing="4">
	<form:questionaire questionWidth="53%" answerWidth="47%" altStyleClass="bgLtGray" textStyleClass="indent" id="21,22,23,24,25,26,27,28,29,30" autoNumber="true"/>
	</table>
	</td>
	</tr>
	
	<tr>		
		<td colspan="2">
		<table width="93%" align=right border="0" cellpadding="3" cellspacing="4" class="bgLtGray">
		<form:question id="31" questionWidth="50%" answerWidth="50%" altStyleClass="bgLtGray" textStyleClass="indent" autoNumber="true"/>
		</table>
		</td>
	</tr>
	
	<tr>	
	<td colspan="2">
	<table width="98%" align=right border="0" cellpadding="3" cellspacing="4">
	<form:questionaire questionWidth="53%" answerWidth="47%" altStyleClass="bgLtGray" textStyleClass="indent" id="32,33,34,35" autoNumber="true"/>
	</table>
	</td>
	</tr>	
	
	
       </form:sectionContainer>

    </p>
    </table>
   <!-- ======================================================= -->
     <form:signatures/>


</form>
</OTML:OFFLINE-FORM>