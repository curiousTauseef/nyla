<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://gcsm.bms.com/tld/form.tld" prefix="form"%>

<%
	String startIndent = "<tr><td colspan=\"2\">	<table width=\"100%\">	<tr>	<td width=\"50\"><img src=\"/gcsm/skins/default/images/blank.gif\" width=\"50\"></td>	<td>	<table width=\"100%\">";
	String endIndent = "</table>	</td>	</tr>	</table>	</td>	</tr>";
	
%>

<html>
<head>
<script type="text/javascript" language="JavaScript">


/*****************************
 * This validateForm(aForm) Function is specific to a form
 * It generates the hashtable which contains a conditionalQuestions and requiredQuestions
 * This Function is specific to each form.
 ******************/
function validateForm(aForm) {
var submitform = true;
//Following Questions are required for conditional validation
//for questionx=questiony means for questionx question y is required.
//26=27, 29=38 & 43 , 52=53, 56=57, 59=60, 62=63, 65=66
//29 & 42 = 44 is required
//68=69, 70=71, 73=74, 76=77, 25={91,93, 94,96,98,100,102,104,106,108,110,112,114,116,118}
// Collect all Ids
var conditionalQuestionsTable = new Hashtable() ;
var requiredQuestionsTable = new Hashtable() ;
var conditionalQuestionsKeys = new Array();
conditionalQuestionsTable.put('<form:inputNameForQuestion id="26"/>','Yes' );
requiredQuestionsTable.put('<form:inputNameForQuestion id="26"/>','<form:inputNameForQuestion id="27"/>');

conditionalQuestionsTable.put('<form:inputNameForQuestion id="29"/>','Yes');
requiredQuestionsTable.put('<form:inputNameForQuestion id="29"/>','<form:inputNameForQuestion id="38"/>:<form:inputNameForQuestion id="43"/>');
/*************************
// Still Thiking how to handle "and" Condition
/***********************************
conditionalQuestionsTable.put('<form:inputNameForQuestion id="29"/>' + '&&' +
'<form:inputNameForQuestion id="42"/>','Yes'+'&&'+'Yes');
requiredQuestionsTable.put('<form:inputNameForQuestion id="29"/>' + '&&&' +
'<form:inputNameForQuestion id="42"/>','<form:inputNameForQuestion id="44"/>');
************************************************/
conditionalQuestionsTable.put('<form:inputNameForQuestion id="52"/>','Yes');
requiredQuestionsTable.put('<form:inputNameForQuestion id="52"/>','<form:inputNameForQuestion id="53"/>');

conditionalQuestionsTable.put('<form:inputNameForQuestion id="56"/>','Yes');
requiredQuestionsTable.put('<form:inputNameForQuestion id="56"/>','<form:inputNameForQuestion id="57"/>');

conditionalQuestionsTable.put('<form:inputNameForQuestion id="62"/>','Yes');
requiredQuestionsTable.put('<form:inputNameForQuestion id="62"/>','<form:inputNameForQuestion id="63"/>');

conditionalQuestionsTable.put('<form:inputNameForQuestion id="65"/>','Yes');
requiredQuestionsTable.put('<form:inputNameForQuestion id="65"/>','<form:inputNameForQuestion id="66"/>');

conditionalQuestionsTable.put('<form:inputNameForQuestion id="68"/>','Yes');
requiredQuestionsTable.put('<form:inputNameForQuestion id="68"/>','<form:inputNameForQuestion id="69"/>');

conditionalQuestionsTable.put('<form:inputNameForQuestion id="70"/>','Yes');
requiredQuestionsTable.put('<form:inputNameForQuestion id="70"/>','<form:inputNameForQuestion id="71"/>');

conditionalQuestionsTable.put('<form:inputNameForQuestion id="73"/>','Yes');
requiredQuestionsTable.put('<form:inputNameForQuestion id="73"/>','<form:inputNameForQuestion id="74"/>');


conditionalQuestionsTable.put('<form:inputNameForQuestion id="25"/>','Yes');
requiredQuestionsTable.put('<form:inputNameForQuestion id="25"/>',
                                '<form:inputNameForQuestion id="93"/>:' +
                                    '<form:inputNameForQuestion id="94"/>:' +
                                    '<form:inputNameForQuestion id="96"/>:' +
                                    '<form:inputNameForQuestion id="98"/>:' +
                                    '<form:inputNameForQuestion id="100"/>:' +
                                    '<form:inputNameForQuestion id="102"/>:' +
                                    '<form:inputNameForQuestion id="104"/>:' +
                                    '<form:inputNameForQuestion id="106"/>:' +
                                    '<form:inputNameForQuestion id="108"/>:' +
                                    '<form:inputNameForQuestion id="110"/>:' +
                                    '<form:inputNameForQuestion id="112"/>:' +
                                    '<form:inputNameForQuestion id="114"/>:' +
                                    '<form:inputNameForQuestion id="116"/>:' +
                                    '<form:inputNameForQuestion id="118"/>');
// loop through conditionalQuestions Table

// This can be seperate generic function
// function validate(conditionalQuestionsTable, requiredQuestionsTable)
// return value false and check if form needs to be submitted.

    submitform = performValidation(conditionalQuestionsTable, requiredQuestionsTable);

// And Case    uniques for SMP

 var question29 = document.getElementById('<form:inputNameForQuestion id="29"/>');
 var selectedAnswerFor29 = question29.options[question29.selectedIndex].text;
 var question42 = document.getElementById('<form:inputNameForQuestion id="42"/>');
 var selectedAnswerFor42 = question42.options[question42.selectedIndex].text;
 var requiredQuestion = '<form:inputNameForQuestion id="44"/>' ;
  if (selectedAnswerFor29.match('Yes') != null &&
        selectedAnswerFor42.match('Yes') != null) {
        /************************************
         *Conditional Check for Text and TextArea
         ************************************/
          if (document.getElementById(requiredQuestion).value == "" ||
                document.getElementById(requiredQuestion).value == null) {
                printError( document.getElementById(requiredQuestion), "   Required");
                submitform = false ;
           } // If isEmpty
  }

    if (submitform == true) {
       signForm(aForm);
    }
     else printConfirmation('<span class=error>THE FORM CANNOT BE SUBMITTED.</span>');;

}
</script>

</head>
<body>
<form name="aForm" action="<c:url value="/do/formAction"/>" method="POST">

<table class="formHeader" width="100%">
<tr>
            <td>
            <!-- Form Type Name -->
	      <c:out value="${sessionScope.form.name}"/> <!-- FORM_ID: <c:out value="${form.primaryKey}"/> -->
              <input type="hidden" name="op" value=""/>
               <input type="hidden" name="formID" value="<c:out value="${form.primaryKey}"/>"/>
	     </td>
  	    <td align="right">
                 <form:guard checkPoint="edit">
     	                         <input type="button" value="   Edit   " onclick="renewForm(aForm);" />
     	           </form:guard>     	          	         
     	          <form:guard checkPoint="complete">
     	                         <input type="button" value="Complete" onclick="signCompletionForm(aForm);" />
     	           </form:guard>   	       
       	  <form:guard>
          	           <input type="button" value="Save" onclick="saveForm(aForm);" />
               	       <input type="button" value="Delete" onclick="deleteForm(aForm);" />
                   	   <input type="button" value="Submit" onclick="validateForm(aForm);"/>
            </form:guard>   	       
   	       </td>
         </tr>
<tr>
<td colspan="2" align="left"><form:sectionGlossary id="1,2,3,4,5,6"/></td>
</tr>
<!-- ======================================= -->
<!-- Save Confirmation -->
    <jsp:include page="/views/form/save_confirmation.jsp"/>
<!-- ======================================= -->
</table>

<form:sectionContainer id="1" questionWidth="90%">
	<%=startIndent%>
	<tr class="questionTableHeader"><td colspan="2">1-1. Drug-Protocol</td></tr>
	<%=startIndent%>
	<table width="100%">
	<form:questionaire autoNumber="true" id="3,5,6,8"/>
	<%=endIndent%>
	<tr class="questionHeader"><td colspan="2">1-2. Global Medical Monitor</td></tr>
	<%=startIndent%>
	<form:questionaire id="11,12,14"/>
	<%=endIndent%>
	<tr><td colspan="2">&nbsp;</td></tr>
	<form:question autoNumber="true" id="17" styleClass="questionTextBold"/>
	<tr><td colspan="2">&nbsp;</td></tr>
	<form:question autoNumber="true" id="23"/>
	<form:questionaire autoNumber="true" id="25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44"/>
	<%=endIndent%>
</form:sectionContainer>
       <table width="100%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>    	
      	</table>      	
<form:sectionContainer id="2" questionWidth="90%">
	<%=startIndent%>
	<form:questionaire  autoNumber="true"  id="45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75"/>
	<%=endIndent%>
</form:sectionContainer>
       <table width="100%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>    	
      	</table>      
<form:sectionContainer id="3" questionWidth="90%">
	<%=startIndent%>
	<form:questionaire  autoNumber="true" id="76,78,79,80,82"/>
	<%=endIndent%>
</form:sectionContainer>
       <table width="100%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>    	
      	</table>      
<form:sectionContainer id="4" questionWidth="90%">
	<%=startIndent%>
	<form:questionaire id="84,85,86,87" autoNumber="true"/>
	<tr class="questionHeader"><td colspan="2">Phone Visit Help Text</td></tr>
	<form:questionaire autoNumber="skip" id="90" styleClass="questionTextBold"/>
	<form:questionaire autoNumber="true" id="91"/>
	<form:questionaire autoNumber="skip" id="92" styleClass="questionTextBold"/>
	<form:questionaire autoNumber="true" id="93"/>
	<form:questionaire autoNumber="true" id="94"/>
	<form:questionaire autoNumber="skip" id="95" styleClass="questionTextBold"/>
	<form:questionaire autoNumber="true" id="96"/>
	<form:questionaire autoNumber="skip" id="97" styleClass="questionTextBold"/>
	<form:questionaire autoNumber="true" id="98"/>
	<form:questionaire autoNumber="skip" id="99" styleClass="questionTextBold"/>
	<form:questionaire autoNumber="true" id="100"/>
	<form:questionaire autoNumber="skip" id="101" styleClass="questionTextBold"/>
	<form:questionaire autoNumber="true" id="102"/>
	<form:questionaire autoNumber="skip" id="103" styleClass="questionTextBold"/>
	<form:questionaire autoNumber="true" id="104"/>
	<form:questionaire autoNumber="skip" id="105" styleClass="questionTextBold"/>
	<form:questionaire autoNumber="true" id="106"/>
	<form:questionaire autoNumber="skip" id="107" styleClass="questionTextBold"/>
	<form:questionaire autoNumber="true" id="108"/>
	<form:questionaire autoNumber="skip" id="109" styleClass="questionTextBold"/>
	<form:questionaire autoNumber="true" id="110"/>
	<form:questionaire autoNumber="skip" id="111" styleClass="questionTextBold"/>
	<form:questionaire autoNumber="true" id="112"/>
	<form:questionaire autoNumber="skip" id="113" styleClass="questionTextBold"/>
	<form:questionaire autoNumber="true" id="114"/>
	<form:questionaire autoNumber="skip" id="115" styleClass="questionTextBold"/>
	<form:questionaire autoNumber="true" id="116"/>
	<form:questionaire autoNumber="skip" id="117" styleClass="questionTextBold"/>
	<form:questionaire autoNumber="true" id="118"/>
	<form:questionaire autoNumber="skip" id="119" styleClass="questionTextBold"/>
	<form:questionaire autoNumber="true" id="120"/>
	<%--form:questionaire autoNumber="true"  id="90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120"/ --%>
	<%=endIndent%>
</form:sectionContainer>
       <table width="100%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>    	
      	</table>      
<form:sectionContainer id="5" questionWidth="90%">
	<%=startIndent%>
	<form:questionaire autoNumber="true"  id="121,122"/>
	<%=endIndent%>
</form:sectionContainer>
       <table width="100%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>    	
      	</table>      
<form:sectionContainer id="6" questionWidth="90%">
	<%=startIndent%>
	<form:questionaire id="89"/>
	<%=endIndent%>
</form:sectionContainer>
       <table width="100%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>    	
      	</table>     
<!--
<table class="formHeader" width="100%">
<tr>
<td>Site Monitoring Plan <c:out value="${form.primaryKey}"/> </td>
<td align="right"><input type="button" value="Save" onclick="saveForm(aForm);" />
<input type="button" value="Submit" onclick="submitForm(aForm);"/></td>
</tr>
</table>
-->
</form>
<form:signatures/>
</body>
</html>