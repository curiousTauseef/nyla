<!--
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
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
    for (var  i=0; i<conditionalQuestionsKeys.length; i++) {
   // alert ("I=<"+i+">");
         //var conditionalQuestionsKeysArray=conditionalQuestionsKeys[i].split(":");
        if (conditionalQuestionsKeys[i].search(/list/) != -1 ) {
         var listBoxElement = document.getElementById(conditionalQuestionsKeys[i]);

         var selectedAnswer = listBoxElement.options[listBoxElement.selectedIndex].text;
         var requiredAnswer =  conditionalQuestionsTable.get(conditionalQuestionsKeys[i]);
      //    alert("selectedAnswer<"+selectedAnswer+"> requiredAnswer<"+requiredAnswer+">");
             if (selectedAnswer.match(requiredAnswer) != null) {
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
                      //   alert("about to do conditional listbox check");
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
var submitform = true;
//Following Questions are required for conditional validation
//for questionx=questiony means for questionx question y is required.
//20=21 & 22=23 is required,
// Collect all Ids
var conditionalQuestionsTable = new Hashtable() ;
var requiredQuestionsTable = new Hashtable() ;
var conditionalQuestionsKeys = new Array();
conditionalQuestionsTable.put('<form:inputNameForQuestion id="46"/>','Yes');
requiredQuestionsTable.put('<form:inputNameForQuestion id="46"/>','<form:inputNameForQuestion id="47"/>');
 conditionalQuestionsTable.put('<form:inputNameForQuestion id="5"/>','Standard');
 var standardReqs ='<form:inputNameForQuestion id="20"/>:<form:inputNameForQuestion id="21"/>:<form:inputNameForQuestion id="22"/>:<form:inputNameForQuestion id="23"/>:<form:inputNameForQuestion id="24"/>:<form:inputNameForQuestion id="25"/>:<form:inputNameForQuestion id="26"/>:<form:inputNameForQuestion id="27"/>:<form:inputNameForQuestion id="28"/>';
 standardReqs =  standardReqs + ':<form:inputNameForQuestion id="29"/>:<form:inputNameForQuestion id="30"/>:<form:inputNameForQuestion id="31"/>:<form:inputNameForQuestion id="32"/>:<form:inputNameForQuestion id="33"/>:<form:inputNameForQuestion id="34"/>:<form:inputNameForQuestion id="35"/>:<form:inputNameForQuestion id="36"/>';
 standardReqs =  standardReqs +  ':<form:inputNameForQuestion id="37"/>:<form:inputNameForQuestion id="38"/>';
  standardReqs =  standardReqs + ':<form:inputNameForQuestion id="39"/>:<form:inputNameForQuestion id="40"/>:<form:inputNameForQuestion id="41"/>:<form:inputNameForQuestion id="42"/>:<form:inputNameForQuestion id="43"/>:<form:inputNameForQuestion id="44"/>';
  standardReqs =  standardReqs +  ':<form:inputNameForQuestion id="45"/>:<form:inputNameForQuestion id="46"/>:<form:inputNameForQuestion id="48"/>:<form:inputNameForQuestion id="49"/>:<form:inputNameForQuestion id="50"/>:<form:inputNameForQuestion id="51"/>';
  standardReqs =  standardReqs +  ':<form:inputNameForQuestion id="52"/>:<form:inputNameForQuestion id="53"/>:<form:inputNameForQuestion id="54"/>:<form:inputNameForQuestion id="55"/>:<form:inputNameForQuestion id="56"/>:<form:inputNameForQuestion id="57"/>';
  standardReqs =  standardReqs +  ':<form:inputNameForQuestion id="58"/>:<form:inputNameForQuestion id="59"/>:<form:inputNameForQuestion id="60"/>:<form:inputNameForQuestion id="61"/>:<form:inputNameForQuestion id="62"/>:<form:inputNameForQuestion id="63"/>';
 standardReqs =  standardReqs +  ':<form:inputNameForQuestion id="64"/>:<form:inputNameForQuestion id="65"/>:<form:inputNameForQuestion id="66"/>:<form:inputNameForQuestion id="67"/>:<form:inputNameForQuestion id="68"/>:<form:inputNameForQuestion id="69"/>:<form:inputNameForQuestion id="70"/>';
 standardReqs =  standardReqs +  ':<form:inputNameForQuestion id="71"/>:<form:inputNameForQuestion id="72"/>:<form:inputNameForQuestion id="73"/>:<form:inputNameForQuestion id="75"/>:<form:inputNameForQuestion id="76"/>:<form:inputNameForQuestion id="77"/>';
 standardReqs =  standardReqs +  ':<form:inputNameForQuestion id="78"/>:<form:inputNameForQuestion id="79"/>:<form:inputNameForQuestion id="80"/>';
  standardReqs =  standardReqs +  ':<form:inputNameForQuestion id="81"/>:<form:inputNameForQuestion id="82"/>:<form:inputNameForQuestion id="83"/>:<form:inputNameForQuestion id="84"/>:<form:inputNameForQuestion id="85"/>:<form:inputNameForQuestion id="86"/>:<form:inputNameForQuestion id="87"/>';
   standardReqs =  standardReqs +  ':<form:inputNameForQuestion id="92"/>';
<%--:<form:inputNameForQuestion id="6111"/>:<form:inputNameForQuestion id="6112"/>';--%>
    var aQuestionPrefix = '<form:inputNameForColumn id="8" tableID="46" columnNumber="0"/>';
                          var elementId = null;
                          elementId =aQuestionPrefix+'[0][0];tablePK=46;';
    standardReqs =  standardReqs +  ':' + elementId;
     aQuestionPrefix = '<form:inputNameForColumn id="8" tableID="46" columnNumber="1"/>';
                              elementId = null;
                              elementId =aQuestionPrefix+'[1][0];tablePK=46;';
        standardReqs =  standardReqs +  ':' + elementId;

 requiredQuestionsTable.put('<form:inputNameForQuestion id="5"/>', standardReqs);

// loop through conditionalQuestions Table

// This can be seperate generic function
// function validate(conditionalQuestionsTable, requiredQuestionsTable)
// return value false and check if form needs to be submitted.
      var generalValidate = false;
      var conditionalValidate = false;
        generalValidate=validate(aForm,false);
 //       alert("did generalValidate"+generalValidate);
        conditionalValidate=performValidation(conditionalQuestionsTable, requiredQuestionsTable,aForm);
//   alert("did conditionalValidate" +    conditionalValidate);
    if (generalValidate && conditionalValidate ) {

    	signForm(aForm);
    }
    else printConfirmation('<span class=error>THE FORM CANNOT BE SUBMITTED.</span>');

}
function initializePerSIVVisitType(aVisitTypeElement)
   {
      var choice = aVisitTypeElement.options[ aVisitTypeElement.selectedIndex ];
      var phoneIds = new Array('<form:inputNameForQuestion id="20"/>','<form:inputNameForQuestion id="21"/>',
                      '<form:inputNameForQuestion id="22"/>','<form:inputNameForQuestion id="23"/>',
                      '<form:inputNameForQuestion id="24"/>','<form:inputNameForQuestion id="25"/>',
                       '<form:inputNameForQuestion id="26"/>','<form:inputNameForQuestion id="27"/>',
                       '<form:inputNameForQuestion id="28"/>','<form:inputNameForQuestion id="29"/>',
                      '<form:inputNameForQuestion id="30"/>','<form:inputNameForQuestion id="31"/>',
                    '<form:inputNameForQuestion id="32"/>','<form:inputNameForQuestion id="33"/>',
                    '<form:inputNameForQuestion id="34"/>','<form:inputNameForQuestion id="35"/>',
                    '<form:inputNameForQuestion id="36"/>','<form:inputNameForQuestion id="37"/>',
               '<form:inputNameForQuestion id="38"/>','<form:inputNameForQuestion id="39"/>',
                    '<form:inputNameForQuestion id="40"/>','<form:inputNameForQuestion id="41"/>',
                    '<form:inputNameForQuestion id="42"/>','<form:inputNameForQuestion id="43"/>',
                   '<form:inputNameForQuestion id="44"/>',
                    '<form:inputNameForQuestion id="45"/>','<form:inputNameForQuestion id="46"/>',
                    '<form:inputNameForQuestion id="48"/>',
                    '<form:inputNameForQuestion id="49"/>','<form:inputNameForQuestion id="50"/>',
                    '<form:inputNameForQuestion id="51"/>','<form:inputNameForQuestion id="52"/>',
                     '<form:inputNameForQuestion id="53"/>','<form:inputNameForQuestion id="54"/>',
                     '<form:inputNameForQuestion id="55"/>','<form:inputNameForQuestion id="56"/>',
                    '<form:inputNameForQuestion id="57"/>','<form:inputNameForQuestion id="58"/>',
                    '<form:inputNameForQuestion id="59"/>','<form:inputNameForQuestion id="60"/>',
                    '<form:inputNameForQuestion id="61"/>','<form:inputNameForQuestion id="62"/>',
                     '<form:inputNameForQuestion id="63"/>','<form:inputNameForQuestion id="64"/>',
                     '<form:inputNameForQuestion id="65"/>','<form:inputNameForQuestion id="66"/>',
                    '<form:inputNameForQuestion id="67"/>','<form:inputNameForQuestion id="68"/>',
                     '<form:inputNameForQuestion id="69"/>','<form:inputNameForQuestion id="70"/>',
                     '<form:inputNameForQuestion id="71"/>','<form:inputNameForQuestion id="72"/>',
                    '<form:inputNameForQuestion id="73"/>','<form:inputNameForQuestion id="76"/>',
                     '<form:inputNameForQuestion id="77"/>','<form:inputNameForQuestion id="78"/>',
                     '<form:inputNameForQuestion id="79"/>','<form:inputNameForQuestion id="80"/>',
                     '<form:inputNameForQuestion id="81"/>', '<form:inputNameForQuestion id="82"/>',
                     '<form:inputNameForQuestion id="83"/>', '<form:inputNameForQuestion id="84"/>',
                        '<form:inputNameForQuestion id="85"/>', '<form:inputNameForQuestion id="86"/>',
                         '<form:inputNameForQuestion id="87"/>'  ,   '<form:inputNameForQuestion id="92"/>'
                     );
<%--                var aQuestionPrefix = '<form:inputNameForColumn id="44" tableID="7004" columnNumber="1"/>';--%>
<%--                var elementId = null;--%>
<%--                elementId =aQuestionPrefix+'[1][0];tablePK=7004;';--%>
<%--                phoneIds = phoneIds.concat(elementId);--%>
<%--                  var aQuestionPrefix = '<form:inputNameForColumn id="48" tableID="7006" columnNumber="1"/>';--%>
<%--               elementId =aQuestionPrefix+'[1][0];tablePK=7006;';--%>
<%--               phoneIds = phoneIds.concat(elementId);--%>
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
  <form:useQuestion id="6111" var="q6111"/>
    <form:useQuestion id="6112" var="q6112"/>

<OTML:OFFLINE-FORM OFFLINE-ID="Site Initiation Visit" TITLE="Site Initiation Visit Submission">
   <form id="aForm" name="aForm" action="<c:url value="/do/PSEFormAction"/>" method="POST">

   <table class="formHeader" width="100%" cellpadding="0">
        <tr>
            <td>
            <!-- Form Type Name -->
	       <c:out value="${sessionScope.form.name}"/> <c:out value="${sessionScope.form.classification}"/><!-- <c:out value="${form.primaryKey}"/> -->
              <input type="hidden" name="op" value=""/>
               <input type="hidden" name="formID" value="<c:out value="${form.primaryKey}"/>"/>
               <input type="hidden" name="formTypeName" value="<c:out value="${sessionScope.form.name}"/>"/>
               <input type="hidden" name="protocolSiteTableId" value="6000"/>
               <input type="hidden" name="protocolSiteColNum" value="0"/>
	     </td>
   	    <td align="right">
                <form:guard checkPoint="edit">
     	                         <input type="button" value="   Edit   " onclick="renewForm(aForm);" />
     	           </form:guard>     	          	         
     	          <form:guard checkPoint="complete">
     	                         <input type="button" value="Complete" onclick="signCompletionForm(aForm);" />
     	           </form:guard>   	       
   	        <form:guard>
                    	  <form:guard checkPoint="site">
                           	       <input type="button" value="Post Issue" onclick="postFormIssue(aForm);" /> 
                  	  </form:guard>   	    	        
          	           <input type="button" value="Save" onclick="saveForm(aForm);" />
               	               <input type="button" value="Delete" onclick="deleteForm(aForm);" />
                   	            <input type="button" value="Submit" onclick="validateForm(aForm);"/>
        	       </form:guard>   	       
   	       </td>
         </tr>
         <tr  class="sectionHeader">
            <td colspan="2"><form:sectionGlossary id="1,2,3,4,5,6,7,8,9,10,11"/></td>
         </tr>
<!-- ======================================= -->
<!-- Save Confirmation -->
    <jsp:include page="/views/form/save_confirmation.jsp"/>
<!--======================================= -->         
   </table>
   <!-- ======================================================= -->

   <!-- SECTION 0 -->
   <div style="padding-left:30px;margin-top: 20px;">
         
         <table width="90%" border="0" cellspacing="0" cellpadding="3">
         
         <form:questionaire id="2,3,4" questionWidth="40%" textStyleClass="questionTextBold" answerWidth="60%"/>
                <!-- Visit Type -->
      <form:question id="5" questionWidth="40%" textStyleClass="questionTextBold" answerWidth="60%" onChange="initializePerSIVVisitType(this);"/>

	</div>
   <!-- ======================================================= -->
   <!-- SECTION 1 -->   
    <p> 
		<form:sectionContainer id="1" questionWidth="90%">
			<!--<tr>
			<td class="indent">
			
			<table width="95%"> -->
	          	<form:questionaire id="6,7,8" autoNumber="true"/>
	       <!--   	<tr>
	          		<td colspan="2" style="height: 12px;"></td>
	          	</tr>
          	</table>

          	</td>
          	</tr> -->
		</form:sectionContainer>
       <table width="95%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>    	
      	</table>          		
<%--Section 2--%>
    <p>
    
    <form:sectionContainer id="2" questionWidth="90%">
    	<tr>
    	<td colspan="2">
    	<table width="95%" align="right" border="0" cellpadding="3" cellspacing="0">
    	
    	
	<tr>
		<td colspan="2">

		<form:questionaire questionWidth="40%" answerWidth="60%" id="9" autoNumber="skip" />

		</td>
	</tr>
    		
	<TR>
	<td colspan="2">

    		<table width="100%" class="bgLtGray">		
    	
    		<tr>
    			<td colspan="2" class="questionTextBold">Address</td>
    		</tr>
    		<tr>
    			<td colspan="2">
    			<table width="95%" align="right" border="0" class="bgLtGray" cellpadding="3" cellspacing="0">
    			<form:questionaire questionWidth="37%" answerWidth="63%" autoNumber="true" id="10,11,12,13,14,15,16"/>
    			</table>
    			</td>
    		</tr>
    		
    		</table>
    		</td>
    		</tr>
    		
    		
    		<tr>
    			<td colspan="2" class="questionTextBold">Phone</td>
    		</tr>
    		<tr>
    			<td colspan="2">
    			<table width="95%" align="right" border="0" cellpadding="3" cellspacing="0">
    			<TR>
    			<form:questionaire questionWidth="37%" answerWidth="63%" id="17" autoNumber="true"/>
    			</TR>
    			<TR>
    				<TD>&nbsp;&nbsp;<FONT SIZE=1>Country Code (Area Code) Number</FONT></TD>
    			</TR>
    			</table>
    			</td>
    		</tr>
    		
    		
    		<TR>
    		<td colspan="2">
    		<table width="100%" class="bgLtGray">
    		
    		<tr>
    			<td class="questionTextBold">Fax</td>
    		</tr>
    		<tr>
    			<td>
    			<table width="95%" align="right" border="0" cellpadding="3" cellspacing="0">
    			<TR>
    				<form:questionaire questionWidth="37%" answerWidth="63%" id="18" autoNumber="true"/>
    			</TR>
    			<TR>
    				<TD>&nbsp;&nbsp;<FONT SIZE=1>Country Code (Area Code) Number</FONT></TD>
    			</TR>
    			</table>
    			</td>
    		</tr>
    		</table>
    		
    		</td>
    		</tr>
    		
    		
    		
    		<tr>
    			<td colspan="2" class="questionTextBold">Email</td>
    		</tr>
    		<tr>
    			<td colspan="2">
    			<table width="95%" align="right" border="0" cellpadding="3" cellspacing="0">
    			<form:questionaire questionWidth="37%" answerWidth="63%" id="19" autoNumber="true"/>
    			</table>
    			</td>
    		</tr>	
    	</table>
    	</td>
    	</tr>
	</form:sectionContainer>
           <table width="95%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>    	
      	</table>      
    </p>
   <!-- ======================================================= -->    
   <!-- SECTION 3 -->
    <p>
        <form:sectionContainer id="3" questionWidth="90%">
			<tr>
			<td class="indent">
                <form:questionaire id="20,21,22,23" altStyleClass="bgLtGray" textStyleClass="indent" autoNumber="true"/>
             </td>
          	</tr>
		</form:sectionContainer>
           <table width="95%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>    	
      	</table>      
    </p>

   <!-- SECTION 4 -->
    <p>
        <form:sectionContainer id="4" questionWidth="90%">
			<tr>
			<td class="indent">
                <form:questionaire id="24,25,26,27,28,29,30,31,32,33,34,35,36" altStyleClass="bgLtGray" textStyleClass="indent" autoNumber="true"/>
             </td>
          	</tr>
		</form:sectionContainer>
           <table width="95%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>    	
      	</table>      
    </p>

   <!-- ======================================================= -->
   <!-- SECTION 5 -->
    <p>
        <form:sectionContainer id="5" questionWidth="90%">
			<tr>
			<td class="indent">
                <form:questionaire id="37,38,39,40" altStyleClass="bgLtGray" textStyleClass="indent" autoNumber="true"/>
             </td>
          	</tr>
		</form:sectionContainer>
           <table width="95%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>    	
      	</table>      
    </p>
   <!-- ======================================================= -->
   <!-- SECTION 6 -->
<%--<form:questionaire id="41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60" altStyleClass="bgLtGray" textStyleClass="indent" autoNumber="true"/>    --%>
<p>
        <form:sectionContainer id="6" questionWidth="90%">
			<tr>
			<td class="indent">
                <form:questionaire id="41,42,43,44,45,46,47" altStyleClass="bgLtGray" textStyleClass="indent" autoNumber="true"/>
             </td>
          	</tr>
			<tr>
			<td class="bgLtGray" colspan="2">
                <span class="indent"><c:out value="${q6111.text}"/></span>
             </td>
          	</tr>

			<tr>
                <td class="indent">
                        <form:questionaire id="48,49,50,51,52,53,54" styleClass="bgLtGray" textStyleClass="indent" autoNumber="true"/>
                 </td>
          	</tr>
		<tr>
		<td class="indent">
                <form:questionaire id="55,56,57,58" altStyleClass="bgLtGray" textStyleClass="indent" autoNumber="true"/>
             	</td>
          	</tr>
          	
          	<tr>
			<td colspan="2">
			<table width="95%" align="right" border="0" cellpadding="0" cellspacing="0">
			<form:questionaire id="59" autoNumber="true"/>
			</table>
			</td>
    		</tr>	
          	
          	
		
		<tr>
			<td class="indent">
			<form:questionaire id="60" altStyleClass="bgLtGray" textStyleClass="indent" autoNumber="true"/>
			</td>
          	</tr>

		</form:sectionContainer>
           <table width="95%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>    	
      	</table>      
    </p>
  <!-- ======================================================= -->
   <!-- SECTION 7 -->
    <p>
        <form:sectionContainer id="7" questionWidth="90%">
			<tr>
			<td class="bgLtGray" colspan="2">
                <span class="indent"><c:out value="${q6112.text}"/></span>
             </td>
          	</tr>

			<tr>
			<td class="indent">

                <form:questionaire id="61,62,63,64,65,66,67,68,69,70" styleClass="bgLtGray" textStyleClass="indent" autoNumber="true"/>
             </td>
          	</tr>
			<tr>
			<td class="indent">
                <form:questionaire id="71,72,73" altStyleClass="bgLtGray" textStyleClass="indent" autoNumber="true"/>
             </td>
          	</tr>

		</form:sectionContainer>
           <table width="95%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>    	
      	</table>      
    </p>

   <!-- ======================================================= -->
   <!-- SECTION 8 -->

    <p>
         <form:sectionContainer id="8" questionWidth="90%">
	<tr>
	<td colspan="2">
	<div class="indent">
	<form:table id="74" width="95%" autoNumber="true">

	<form:row>
	      <form:column colNumbers="0" >
		<tr id="col${colNumber}">
		    <td id="columnText" width="30%">
			${numberLabel} ${columnText}
		    </td>
		    <td id="answer" width="69%">${answer}</td>
		</tr>
	    </form:column>
		<tr>
			<td colspan="4" class="unindent"><b>Address</b></td>
		</tr>
			<form:column colNumbers="1,2,3,4,5,6,7" textStyleClass="unindent"/>
		<tr>
			<td colspan="4" class="unindent"><b>Phone</b></td>
		</tr>
			<form:column colNumbers="8">
		    <tr id="col${colNumber}">
			<td id="columnText" width="30%">
				${numberLabel} ${columnText}<br>
				<span class="indent"><span><span class="questionTextSmall">Country Code (Area Code) Number</span></span></span>
			</td>
			<td id="answer" width="69%">${answer}</td>
			</tr>
			</form:column>
		<tr>
			<td colspan="4" class="unindent"><b>Fax</b></td>
		</tr>
			<form:column colNumbers="9">
		    <tr id="col${colNumber}">
			<td id="columnText" width="30%">
				${numberLabel} ${columnText}<br>
				<span class="indent"><span><span class="questionTextSmall">Country Code (Area Code) Number</span></span></span>
			</td>
			<td id="answer" width="69%">${answer}</td>
			</tr>
			</form:column>
		<tr>
			<td colspan="4" class="unindent"><b>Email</b></td>
		</tr>
			<form:column colNumbers="10">
		    	<tr id="col${colNumber}">
			<td id="columnText" width="30%">
				${numberLabel} ${columnText}
			</td>
			<td id="answer" width="69%">${answer}</td>
			</tr>
			</form:column>		
		</form:row>

		

				</form:table>
				</div>
				</td>
	</tr>
	
	
	<tr>
	     <td class="indent">
		<form:questionaire id="92" questionWidth="40%" answerWidth="60%" textStyleClass="indent" autoNumber="true"/>
	     </td>
	</tr>
	
	<tr>
		<td colspan="2">
		<table width="95%" align="right" border="0" cellpadding="0" cellspacing="0">
		<form:questionaire id="75" autoNumber="true" textStyleClass="unindent"/>
		</table>
		</td>
	</tr>	
	
	<tr>
	     <td class="indent">
		<form:questionaire id="76,77,78" altStyleClass="bgLtGray" textStyleClass="indent" autoNumber="true"/>
	     </td>
	</tr>
	
	
	</form:sectionContainer>
	
           <table width="95%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>    	
      	</table>      

        
    </p>

   <!-- ======================================================= -->
   <!-- SECTION 9 -->
    <p>
        <form:sectionContainer id="9" questionWidth="90%">
			<tr>
			<td class="indent">
                <form:questionaire id="79,80,81" altStyleClass="bgLtGray" textStyleClass="indent" autoNumber="true"/>
             </td>
          	</tr>
		</form:sectionContainer>
           <table width="95%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>    	
      	</table>      
    </p>

   <!-- ======================================================= -->
   <!-- SECTION 10 -->
    <p>
        <form:sectionContainer id="10" questionWidth="90%">
			<tr>
			<td class="indent">
                <form:questionaire id="82,83,84,85,86,87" altStyleClass="bgLtGray" textStyleClass="indent" autoNumber="true"/>
             </td>
          	</tr>
		</form:sectionContainer>
           <table width="95%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>    	
      	</table>      
    </p>


   <!-- ======================================================= -->
   <!-- SECTION 11 -->
    <p>
        <form:sectionContainer id="11" questionWidth="90%">
			<tr>
			<td class="indent">
                <form:questionaire id="88,89,90,91" textStyleClass="indent" autoNumber="true"/>
             </td>
          	</tr>
		</form:sectionContainer>
           <table width="95%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>    	
      	</table>      
    </p>
   <!-- ======================================================= -->
     <form:signatures/>
   </form>
</OTML:OFFLINE-FORM>