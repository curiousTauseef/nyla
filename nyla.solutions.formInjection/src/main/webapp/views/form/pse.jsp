<!--
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://gcsm.bms.com/tld/form.tld" prefix="form"%>
-->
<%
	String startIndent = "<tr><td colspan=\"2\">	<table width=\"100%\">	<tr>	<td width=\"50\"><img src=\"/gcsm/skins/default/images/blank.gif\" width=\"50\"></td>	<td>	<table width=\"100%\">";
	String endIndent = "</table>	</td>	</tr>	</table>	</td>	</tr>";

%>
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
conditionalQuestionsTable.put('<form:inputNameForQuestion id="20"/>','No');
conditionalQuestionsTable.put('<form:inputNameForQuestion id="22"/>','No');
requiredQuestionsTable.put('<form:inputNameForQuestion id="20"/>','<form:inputNameForQuestion id="21"/>');
requiredQuestionsTable.put('<form:inputNameForQuestion id="22"/>','<form:inputNameForQuestion id="23"/>');
 conditionalQuestionsTable.put('<form:inputNameForQuestion id="5"/>','Standard');
 var standardReqs ='<form:inputNameForQuestion id="18"/>:<form:inputNameForQuestion id="19"/>:<form:inputNameForQuestion id="20"/>:<form:inputNameForQuestion id="22"/>:<form:inputNameForQuestion id="24"/>:<form:inputNameForQuestion id="26"/>:<form:inputNameForQuestion id="27"/>:<form:inputNameForQuestion id="28"/>:<form:inputNameForQuestion id="29"/>';
 standardReqs =  standardReqs + ':<form:inputNameForQuestion id="34"/>:<form:inputNameForQuestion id="35"/>:<form:inputNameForQuestion id="37"/>:<form:inputNameForQuestion id="38"/>:<form:inputNameForQuestion id="40"/>:<form:inputNameForQuestion id="41"/>:<form:inputNameForQuestion id="42"/>:<form:inputNameForQuestion id="43"/>';
 standardReqs =  standardReqs +  ':<form:inputNameForQuestion id="45"/>:<form:inputNameForQuestion id="47"/>';
  standardReqs =  standardReqs + ':<form:inputNameForQuestion id="57"/>:<form:inputNameForQuestion id="58"/>:<form:inputNameForQuestion id="59"/>:<form:inputNameForQuestion id="62"/>';
  standardReqs =  standardReqs +  ':<form:inputNameForQuestion id="63"/>:<form:inputNameForQuestion id="64"/>:<form:inputNameForQuestion id="65"/>:<form:inputNameForQuestion id="66"/>:<form:inputNameForQuestion id="67"/>:<form:inputNameForQuestion id="69"/>';
  standardReqs =  standardReqs +  ':<form:inputNameForQuestion id="71"/>:<form:inputNameForQuestion id="73"/>:<form:inputNameForQuestion id="75"/>:<form:inputNameForQuestion id="76"/>:<form:inputNameForQuestion id="78"/>:<form:inputNameForQuestion id="79"/>';
  standardReqs =  standardReqs +  ':<form:inputNameForQuestion id="94"/>:<form:inputNameForQuestion id="96"/>:<form:inputNameForQuestion id="97"/>:<form:inputNameForQuestion id="98"/>:<form:inputNameForQuestion id="99"/>:<form:inputNameForQuestion id="101"/>';
 standardReqs =  standardReqs +  ':<form:inputNameForQuestion id="102"/>:<form:inputNameForQuestion id="104"/>:<form:inputNameForQuestion id="106"/>:<form:inputNameForQuestion id="107"/>:<form:inputNameForQuestion id="108"/>:<form:inputNameForQuestion id="109"/>:<form:inputNameForQuestion id="110"/>';
 standardReqs =  standardReqs +  ':<form:inputNameForQuestion id="112"/>:<form:inputNameForQuestion id="113"/>:<form:inputNameForQuestion id="114"/>:<form:inputNameForQuestion id="117"/>:<form:inputNameForQuestion id="118"/>:<form:inputNameForQuestion id="122"/>:<form:inputNameForQuestion id="125"/>';
 standardReqs =  standardReqs +  ':<form:inputNameForQuestion id="126"/>:<form:inputNameForQuestion id="128"/>:<form:inputNameForQuestion id="129"/>:<form:inputNameForQuestion id="131"/>';
      var aQuestionPrefix = '<form:inputNameForColumn id="44" tableID="7004" columnNumber="0"/>';
                       var elementId = null;
                       elementId =aQuestionPrefix+'[0][0];tablePK=7004;';
standardReqs =   standardReqs + ':' + elementId ;
 aQuestionPrefix = '<form:inputNameForColumn id="44" tableID="7004" columnNumber="1"/>';
 elementId =aQuestionPrefix+'[1][0];tablePK=7004;';
 standardReqs =   standardReqs + ':' + elementId ;
  aQuestionPrefix = '<form:inputNameForColumn id="44" tableID="7004" columnNumber="9"/>';
  elementId =aQuestionPrefix+'[9][0];tablePK=7004;';
  standardReqs =   standardReqs + ':' + elementId ;
  aQuestionPrefix = '<form:inputNameForColumn id="44" tableID="7004" columnNumber="10"/>';
  elementId =aQuestionPrefix+'[10][0];tablePK=7004;';
  standardReqs =   standardReqs + ':' + elementId ;
   aQuestionPrefix = '<form:inputNameForColumn id="46" tableID="7005" columnNumber="0"/>';
   elementId =aQuestionPrefix+'[0][0];tablePK=7005;';
   standardReqs =   standardReqs + ':' + elementId ;
    aQuestionPrefix = '<form:inputNameForColumn id="46" tableID="7005" columnNumber="1"/>';
      elementId =aQuestionPrefix+'[1][0];tablePK=7005;';
      standardReqs =   standardReqs + ':' + elementId ;
    aQuestionPrefix = '<form:inputNameForColumn id="56" tableID="7008" columnNumber="0"/>';
    elementId =aQuestionPrefix+'[0][0];tablePK=7008;';
    standardReqs =   standardReqs + ':' + elementId ;
   aQuestionPrefix = '<form:inputNameForColumn id="56" tableID="7008" columnNumber="1"/>';
      elementId =aQuestionPrefix+'[1][0];tablePK=7008;';
      standardReqs =   standardReqs + ':' + elementId ;
    aQuestionPrefix = '<form:inputNameForColumn id="56" tableID="7008" columnNumber="2"/>';
       elementId =aQuestionPrefix+'[2][0];tablePK=7008;';
       standardReqs =   standardReqs + ':' + elementId ;

 requiredQuestionsTable.put('<form:inputNameForQuestion id="5"/>', standardReqs);
 //alert("just set standard required fields");
 conditionalQuestionsTable.put('<form:inputNameForQuestion id="38"/>','Yes');
 requiredQuestionsTable.put('<form:inputNameForQuestion id="38"/>','<form:inputNameForQuestion id="39"/>');
conditionalQuestionsTable.put('<form:inputNameForQuestion id="69"/>','No');
conditionalQuestionsTable.put('<form:inputNameForQuestion id="71"/>','No');
conditionalQuestionsTable.put('<form:inputNameForQuestion id="73"/>','No');
conditionalQuestionsTable.put('<form:inputNameForQuestion id="76"/>','No');
requiredQuestionsTable.put('<form:inputNameForQuestion id="69"/>','<form:inputNameForQuestion id="70"/>');
requiredQuestionsTable.put('<form:inputNameForQuestion id="71"/>','<form:inputNameForQuestion id="72"/>');
requiredQuestionsTable.put('<form:inputNameForQuestion id="73"/>','<form:inputNameForQuestion id="74"/>');
requiredQuestionsTable.put('<form:inputNameForQuestion id="76"/>','<form:inputNameForQuestion id="77"/>');
 conditionalQuestionsTable.put('<form:inputNameForQuestion id="79"/>','Yes');
 requiredQuestionsTable.put('<form:inputNameForQuestion id="79"/>','<form:inputNameForQuestion id="80"/>');
<%-- conditionalQuestionsTable.put('<form:inputNameForQuestion id="81"/>','Yes');--%>
<%--requiredQuestionsTable.put('<form:inputNameForQuestion id="81"/>','<form:inputNameForQuestion id="82"/>:<form:inputNameForQuestion id="84"/>:<form:inputNameForQuestion id="86"/>');--%>
  conditionalQuestionsTable.put('<form:inputNameForQuestion id="82"/>','No');
requiredQuestionsTable.put('<form:inputNameForQuestion id="82"/>','<form:inputNameForQuestion id="83"/>');
  conditionalQuestionsTable.put('<form:inputNameForQuestion id="84"/>','No');
  requiredQuestionsTable.put('<form:inputNameForQuestion id="84"/>','<form:inputNameForQuestion id="85"/>');
 conditionalQuestionsTable.put('<form:inputNameForQuestion id="86"/>','No');
  requiredQuestionsTable.put('<form:inputNameForQuestion id="86"/>','<form:inputNameForQuestion id="87"/>');
 conditionalQuestionsTable.put('<form:inputNameForQuestion id="88"/>','Yes');
    requiredQuestionsTable.put('<form:inputNameForQuestion id="88"/>','<form:inputNameForQuestion id="89"/>');
 conditionalQuestionsTable.put('<form:inputNameForQuestion id="89"/>','No');
   requiredQuestionsTable.put('<form:inputNameForQuestion id="89"/>','<form:inputNameForQuestion id="90"/>');
 conditionalQuestionsTable.put('<form:inputNameForQuestion id="91"/>','Yes');
   requiredQuestionsTable.put('<form:inputNameForQuestion id="91"/>','<form:inputNameForQuestion id="92"/>');
    conditionalQuestionsTable.put('<form:inputNameForQuestion id="99"/>','Yes');
      requiredQuestionsTable.put('<form:inputNameForQuestion id="99"/>','<form:inputNameForQuestion id="100"/>');
     conditionalQuestionsTable.put('<form:inputNameForQuestion id="102"/>','Yes');
          requiredQuestionsTable.put('<form:inputNameForQuestion id="102"/>','<form:inputNameForQuestion id="103"/>');
   conditionalQuestionsTable.put('<form:inputNameForQuestion id="104"/>','Yes');
        requiredQuestionsTable.put('<form:inputNameForQuestion id="104"/>','<form:inputNameForQuestion id="105"/>');
 conditionalQuestionsTable.put('<form:inputNameForQuestion id="110"/>','No');
        requiredQuestionsTable.put('<form:inputNameForQuestion id="110"/>','<form:inputNameForQuestion id="111"/>');
         conditionalQuestionsTable.put('<form:inputNameForQuestion id="114"/>','Yes');
           requiredQuestionsTable.put('<form:inputNameForQuestion id="114"/>','<form:inputNameForQuestion id="115"/>');
         conditionalQuestionsTable.put('<form:inputNameForQuestion id="118"/>','Yes');
            requiredQuestionsTable.put('<form:inputNameForQuestion id="118"/>','<form:inputNameForQuestion id="119"/>:<form:inputNameForQuestion id="120"/>:<form:inputNameForQuestion id="121"/>');
          conditionalQuestionsTable.put('<form:inputNameForQuestion id="122"/>','Yes');
                  requiredQuestionsTable.put('<form:inputNameForQuestion id="122"/>','<form:inputNameForQuestion id="123"/>:<form:inputNameForQuestion id="124"/>');
          conditionalQuestionsTable.put('<form:inputNameForQuestion id="126"/>','Yes');
               requiredQuestionsTable.put('<form:inputNameForQuestion id="126"/>','<form:inputNameForQuestion id="127"/>');
conditionalQuestionsTable.put('<form:inputNameForQuestion id="129"/>','No');
        requiredQuestionsTable.put('<form:inputNameForQuestion id="129"/>','<form:inputNameForQuestion id="130"/>');
    conditionalQuestionsTable.put('<form:inputNameForQuestion id="131"/>','Yes');
            requiredQuestionsTable.put('<form:inputNameForQuestion id="131"/>','<form:inputNameForQuestion id="132"/>');

        conditionalQuestionsTable.put('<form:inputNameForQuestion id="47"/>','Yes');
     var ids = '';
     aQuestionPrefix = '<form:inputNameForColumn id="48" tableID="7006" columnNumber="0"/>';
       elementId =aQuestionPrefix+'[0][0];tablePK=7006;';
        ids =   elementId ;
       aQuestionPrefix = '<form:inputNameForColumn id="48" tableID="7006" columnNumber="1"/>';
            elementId =aQuestionPrefix+'[1][0];tablePK=7006;';
            ids =   ids + ':' + elementId ;
   aQuestionPrefix = '<form:inputNameForColumn id="48" tableID="7006" columnNumber="2"/>';
        elementId =aQuestionPrefix+'[2][0];tablePK=7006;';
        ids =   ids + ':' + elementId ;
         aQuestionPrefix = '<form:inputNameForColumn id="48" tableID="7006" columnNumber="5"/>';
               elementId =aQuestionPrefix+'[5][0];tablePK=7006;';
               ids =   ids + ':' + elementId ;
      aQuestionPrefix = '<form:inputNameForColumn id="48" tableID="7006" columnNumber="8"/>';
           elementId =aQuestionPrefix+'[8][0];tablePK=7006;';
           ids =   ids + ':' + elementId ;

   requiredQuestionsTable.put('<form:inputNameForQuestion id="47"/>',ids);
            conditionalQuestionsTable.put('<form:inputNameForQuestion id="102"/>','Yes');
            ids = '';
           aQuestionPrefix = '<form:inputNameForColumn id="103" tableID="7009" columnNumber="0"/>';
             elementId =aQuestionPrefix+'[0][0];tablePK=7009;';
              ids =   elementId ;
             aQuestionPrefix = '<form:inputNameForColumn id="103" tableID="7009" columnNumber="1"/>';
                  elementId =aQuestionPrefix+'[1][0];tablePK=7009;';
                  ids =   ids + ':' + elementId ;
         aQuestionPrefix = '<form:inputNameForColumn id="103" tableID="7009" columnNumber="2"/>';
              elementId =aQuestionPrefix+'[2][0];tablePK=7009;';
              ids =   ids + ':' + elementId ;
               aQuestionPrefix = '<form:inputNameForColumn id="103" tableID="7009" columnNumber="5"/>';
                     elementId =aQuestionPrefix+'[5][0];tablePK=7009;';
                     ids =   ids + ':' + elementId ;
            aQuestionPrefix = '<form:inputNameForColumn id="103" tableID="7009" columnNumber="8"/>';
                 elementId =aQuestionPrefix+'[8][0];tablePK=7009;';
                 ids =   ids + ':' + elementId ;

         requiredQuestionsTable.put('<form:inputNameForQuestion id="102"/>',ids);

         conditionalQuestionsTable.put('<form:inputNameForQuestion id="104"/>','Yes');
         ids = '';
        aQuestionPrefix = '<form:inputNameForColumn id="105" tableID="7009" columnNumber="0"/>';
          elementId =aQuestionPrefix+'[0][0];tablePK=7009;';
           ids =   elementId ;
          aQuestionPrefix = '<form:inputNameForColumn id="105" tableID="7009" columnNumber="1"/>';
               elementId =aQuestionPrefix+'[1][0];tablePK=7009;';
               ids =   ids + ':' + elementId ;
      aQuestionPrefix = '<form:inputNameForColumn id="105" tableID="7009" columnNumber="2"/>';
           elementId =aQuestionPrefix+'[2][0];tablePK=7009;';
           ids =   ids + ':' + elementId ;
            aQuestionPrefix = '<form:inputNameForColumn id="105" tableID="7009" columnNumber="5"/>';
                  elementId =aQuestionPrefix+'[5][0];tablePK=7009;';
                  ids =   ids + ':' + elementId ;
         aQuestionPrefix = '<form:inputNameForColumn id="105" tableID="7009" columnNumber="8"/>';
              elementId =aQuestionPrefix+'[8][0];tablePK=7009;';
              ids =   ids + ':' + elementId ;

      requiredQuestionsTable.put('<form:inputNameForQuestion id="104"/>',ids);

           conditionalQuestionsTable.put('<form:inputNameForQuestion id="126"/>','Yes');
               ids = '';
              aQuestionPrefix = '<form:inputNameForColumn id="127" tableID="7011" columnNumber="0"/>';
                elementId =aQuestionPrefix+'[0][0];tablePK=7011;';
                 ids =   elementId ;
                aQuestionPrefix = '<form:inputNameForColumn id="127" tableID="7011" columnNumber="1"/>';
                     elementId =aQuestionPrefix+'[1][0];tablePK=7011;';
                     ids =   ids + ':' + elementId ;

            requiredQuestionsTable.put('<form:inputNameForQuestion id="126"/>',ids);
 //      alert("just finished setting required and conditional table");
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
 function initializePerPSEVisitType(aVisitTypeElement)
  {
     var choice = aVisitTypeElement.options[ aVisitTypeElement.selectedIndex ];
     var phoneIds = new Array('<form:inputNameForQuestion id="19"/>','<form:inputNameForQuestion id="20"/>',
                     '<form:inputNameForQuestion id="22"/>','<form:inputNameForQuestion id="26"/>',
                                                   '<form:inputNameForQuestion id="27"/>','<form:inputNameForQuestion id="28"/>',
                                                    '<form:inputNameForQuestion id="29"/>','<form:inputNameForQuestion id="35"/>',
                                                  '<form:inputNameForQuestion id="38"/>','<form:inputNameForQuestion id="43"/>',
                                                   '<form:inputNameForQuestion id="47"/>','<form:inputNameForQuestion id="69"/>',
                                                   '<form:inputNameForQuestion id="71"/>','<form:inputNameForQuestion id="73"/>',
                                                  '<form:inputNameForQuestion id="75"/>',
                                                   '<form:inputNameForQuestion id="76"/>','<form:inputNameForQuestion id="79"/>',
                                                   '<form:inputNameForQuestion id="80"/>','<form:inputNameForQuestion id="82"/>',
                                                   '<form:inputNameForQuestion id="84"/>','<form:inputNameForQuestion id="86"/>',
                                                   '<form:inputNameForQuestion id="88"/>','<form:inputNameForQuestion id="89"/>',
                                                    '<form:inputNameForQuestion id="96"/>','<form:inputNameForQuestion id="97"/>',
                                                    '<form:inputNameForQuestion id="98"/>','<form:inputNameForQuestion id="99"/>',
                                                   '<form:inputNameForQuestion id="101"/>','<form:inputNameForQuestion id="102"/>',
                                                   '<form:inputNameForQuestion id="104"/>','<form:inputNameForQuestion id="106"/>',
                                                   '<form:inputNameForQuestion id="107"/>','<form:inputNameForQuestion id="108"/>',
                                                    '<form:inputNameForQuestion id="109"/>','<form:inputNameForQuestion id="110"/>',
                                                    '<form:inputNameForQuestion id="111"/>','<form:inputNameForQuestion id="112"/>',
                                                   '<form:inputNameForQuestion id="113"/>','<form:inputNameForQuestion id="114"/>',
                                                    '<form:inputNameForQuestion id="117"/>','<form:inputNameForQuestion id="118"/>',
                                                    '<form:inputNameForQuestion id="119"/>','<form:inputNameForQuestion id="120"/>',
                                                   '<form:inputNameForQuestion id="121"/>','<form:inputNameForQuestion id="122"/>',
                                                    '<form:inputNameForQuestion id="125"/>','<form:inputNameForQuestion id="126"/>',
                                                    '<form:inputNameForQuestion id="128"/>','<form:inputNameForQuestion id="129"/>',
                                                    '<form:inputNameForQuestion id="130"/>','<form:inputNameForQuestion id="131"/>',
                                                    '<form:inputNameForQuestion id="132"/>'
                                                    );
               var aQuestionPrefix = '<form:inputNameForColumn id="44" tableID="7004" columnNumber="1"/>';
               var elementId = null;
               elementId =aQuestionPrefix+'[1][0];tablePK=7004;';
               phoneIds = phoneIds.concat(elementId);
                 var aQuestionPrefix = '<form:inputNameForColumn id="48" tableID="7006" columnNumber="1"/>';
              elementId =aQuestionPrefix+'[1][0];tablePK=7006;';
              phoneIds = phoneIds.concat(elementId);
                var aQuestionPrefix = '<form:inputNameForColumn id="49" tableID="7006" columnNumber="1"/>';
              var elementId = null;
              elementId =aQuestionPrefix+'[1][0];tablePK=7006;';
              phoneIds = phoneIds.concat(elementId);
                var aQuestionPrefix = '<form:inputNameForColumn id="92" tableID="7010" columnNumber="1"/>';
              var elementId = null;
              elementId =aQuestionPrefix+'[1][0];tablePK=7010;';
              phoneIds = phoneIds.concat(elementId);
               var aQuestionPrefix = '<form:inputNameForColumn id="92" tableID="7010" columnNumber="2"/>';
             var elementId = null;
             elementId =aQuestionPrefix+'[2][0];tablePK=7010;';
             phoneIds = phoneIds.concat(elementId);
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

<OTML:OFFLINE-FORM OFFLINE-ID="Pre-Study Evaluation" TITLE="Pre-Study Evaluation Submission">
	<form id="aForm" name="aForm" action="<c:url value="/do/PSEFormAction"/>" method="POST">
   <table class="formHeader" border = 0 width="100%" cellpadding="0">
        <tr>
            <td>
            <!-- FORM STATUS PK <c:out value="${form.statusPK}"/>--> 
            <!-- FORM STATUS  <c:out value="${form.status}"/>-->             
            <!-- Form Type Name -->
	       <c:out value="${sessionScope.form.name}"/> <c:out value="${sessionScope.form.classification}"/><!-- <c:out value="${form.primaryKey}"/> -->
              <input type="hidden" name="op" value=""/>
               <input type="hidden" name="formID" value="<c:out value="${form.primaryKey}"/>"/>
               <input type="hidden" name="formTypeName" value="<c:out value="${sessionScope.form.name}"/>"/>
               <input type="hidden" name="protocolSiteTableId" value="7000"/>
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
<!-- Save Confirmation -->
    <jsp:include page="/views/form/save_confirmation.jsp"/>
<!-- ======================================= -->
   </table>
   <!-- ======================================================= -->

   <!-- SECTION 0 -->
   <div style="padding-left:30px;margin-top: 20px;">
      <table width="100%" border="0" cellspacing="0" cellpadding="3">
         <form:questionaire id="2" questionWidth="20%" textStyleClass="questionTextBold" answerWidth="80%"/>
		 <tr><td colspan="3" height="20"></td></tr>

         <form:questionaire id="4" questionWidth="20%" textStyleClass="questionTextBold" answerWidth="80%"/>

          <!-- Visit Type -->
         <form:question id="5" questionWidth="20%" textStyleClass="questionTextBold" answerWidth="80%" onChange="initializePerPSEVisitType(this);"/>


	  </table>
   </div>
   <!-- ======================================================= -->
   <!-- SECTION 1 -->
    <p>
		<form:sectionContainer id="1" questionWidth="90%">
			<tr>
			<td class="indent">

<%--			<table width="95%" >--%>
                <table width="100%" align=right border="0" cellpadding="3" cellspacing="4">
                 	<form:questionaire id="6,7" autoNumber="true"/>
	          	<tr>
	          		<td colspan="2" style="height: 12px;"></td>
	          	</tr>
	          	<tr class="questionHeader">
	          		<td colspan="2">Site/Investigator Details</td>
	          	</tr>

<%--	</form:sectionContainer>--%>
<%-- <form:sectionContainer id="2">--%>

    		<tr>
    			<td colspan="2" class="questionTextBold">Address</td>
    		</tr>
    		<tr>
    			<td colspan="2">
    			<table width="95%" align="right" border="0" class="bgLtGray" cellpadding="3" cellspacing="0">
    			     <form:questionaire  questionWidth="50%" answerWidth="50%" id="8,9,10,11,12,13,14"  autoNumber="true"/>
               </table>
    			</td>
    		</tr>

                  <tr>
    			<td colspan="2" class="questionTextBold">Phone</td>
    		</tr>
               <tr>
                 <td colspan="2">
                 <table width="95%" align="right" border="0" class="bgLtGray" cellpadding="3" cellspacing="0">
                   <tr><td><form:questionaire questionWidth="50%" answerWidth="50%" id="15"  autoNumber="true"/>
				</td>
                </tr>
              <tr >
						<td width="${questionWidth}" class="${textStyleClass}">
							<span class="questionTextSmall">CC (Area Code) Number</span>
						</td>
					<td>&nbsp</td>
					</tr>
               </table>
               </td>
                               </tr>

    		<tr>
    			<td class="questionTextBold">Fax</td>
    		</tr>
                 <tr>
                                 <td colspan="2">
                                 <table width="95%" align="right" border="0" class="bgLtGray" cellpadding="3" cellspacing="0">
                                   <tr><td><form:questionaire questionWidth="50%" answerWidth="50%" id="16"  autoNumber="true"/>
                				</td>
                                </tr>
                              <tr >
                						<td width="${questionWidth}" class="${textStyleClass}">
                							<span class="questionTextSmall">CC (Area Code) Number</span>
                						</td>
                					<td>&nbsp</td>
                					</tr>
                               </table>
                               </td>
                       </tr>
                 <tr><td>
              <form:questionaire questionWidth="50%" answerWidth="50%" id="17" autoNumber="true" styleClass="bgLtGray"/>
                  </td></tr>
                   </table>

		</form:sectionContainer>
     <table width="100%">
		<tr>
			<td colspan="2" align="right" height="20"></td>
		</tr>
	 </table>
       <table width="100%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>
      	</table>
    </p>
   <!-- ======================================================= -->
   <!-- SECTION 2 -->
    <p>
        <form:sectionContainer id="2" questionWidth="90%">
<%--			<tr>--%>
<%--			<td >--%>

     <table width="95%" align="right" border="0"  cellpadding="3" cellspacing="0">
      				<tr>
					<td colspan="2" class="questionTextBold">Investigator Qualifications</td>
				</tr>
	        	<form:questionaire questionWidth="50%" answerWidth="50%" id="18,19,20,21,22,23,24" altStyleClass="bgLtGray"  autoNumber="true"/>
	        	<form:questionaire id="25" questionWidth="50%" answerWidth="50%" name="question" autoNumber="true">
                <tr>&nbsp</tr>
					<tr class="${styleClass}">
						<td width="50%" class="${textStyleClass}">
							<span class="indent">
							${numberLabel} Study/Protocol Number(s) if the
										   Investigator previously conducted a
										   clinical trial for any division of BMS
							</span>
						</td>
						<td width="${answerWidth}"></td>
					</tr>
                     <tr>&nbsp</tr>

					<tr>
						<td colspan="2">
							<span class="indent">
							<span class="indent">
							<table width="90%">
							${answer}
							</table>
							</span>
							</span>
						</td>
					</tr>
                     <tr>&nbsp</tr>

	        	</form:questionaire>
	        	<form:questionaire id="26,27,28,29"  questionWidth="50%" answerWidth="50%" altStyleClass="bgLtGray"  autoNumber="true"/>
          	</table>

<%--          	</td>--%>
<%--          	</tr>--%>
		</form:sectionContainer>
       <table width="100%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>
      	</table>
    </p>
   <!-- ======================================================= -->
   <!-- SECTION 3 -->
  <p>
		<form:sectionContainer id="3" questionWidth="90%">

            <table width="95%" align="right" border="0"  cellpadding="3" cellspacing="0">
                 				<form:questionaire  id="33,34,35,36,37,38,39,40,,41,42,43" questionWidth="50%" answerWidth="50%"  autoNumber="true" altStyleClass="bgLtGray" />
          	</table>


		</form:sectionContainer>
       <table width="100%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>
      	</table>
    </p>
   <!-- ======================================================= -->
   <!-- SECTION 4 -->
    <p>
        <form:sectionContainer id="4" questionWidth="90%">
        <%=startIndent%>
<%--            <tr ><td class="indent">--%>
<%--            <table width="95%" align="right" border="1"  cellpadding="3" cellspacing="0">--%>
                <form:questionaire id="44" autoNumber="true" questionWidth="50%" answerWidth="50%"  styleClass="bgLtGray"/>
        		<form:questionaire id="45" autoNumber="true" questionWidth="50%" answerWidth="50%" />
        		<form:question id="46" answerStyleClass="inputWide" autoNumber="true" questionWidth="50%" answerWidth="50%" />
<%--          	</table>--%>
<%----%>      </td>
<%--        </tr>--%>
           <%=endIndent%>
		</form:sectionContainer>
       <table width="100%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>
      	</table>

    </p>
   <!-- ======================================================= -->
   <!-- SECTION 5 -->
  <p>
        <form:sectionContainer id="5" questionWidth="90%">
<%--            <table width="95%" align="right" border="0"  cellpadding="0" cellspacing="0">--%>

       <%=startIndent%>
<form:questionaire id="47" autoNumber="true"/>
        			   <tr><td colspan="2">
	        		<form:table id="48" autoNumber="true" styleClass="bgLtGray">
	        			<form:row>
		            		<form:column colNumbers="0,1" textStyleClass="unindent"/>
			        		<tr>
			        			<td colspan="4" class="questionText"><b>Address</b></td>
			        		</tr>
							<form:column colNumbers="2,3,4,5,6,7,8" textStyleClass="unindent"/>
			        		<tr>
			        			<td colspan="4" class="questionText"><b>Phone</b></td>
			        		</tr>
							<form:column colNumbers="9">
					            <tr id="col${colNumber}">
					                <td id="columnText" width="30%">
					                	${numberLabel} ${columnText}<br>
					                	<span class="indent"><span class="questionTextSmall">Country Code (Area Code) Number</span></span>
					                </td>
					                <td id="answer" width="69%">${answer}</td>
					      		</tr>
							</form:column>
			        		<tr>
			        			<td colspan="4" class="questionText"><b>Fax</b></td>
			        		</tr>
							<form:column colNumbers="10">
					            <tr id="col${colNumber}">
					                <td id="columnText" width="30%">
					                	${numberLabel} ${columnText}<br>
					                	<span class="indent"><span class="questionTextSmall">Country Code (Area Code) Number</span></span>
					                </td>
					                <td id="answer" width="69%">${answer}</td>
					      		</tr>
							</form:column>
	        			</form:row>
	        		</form:table>
        			</td>
        		</tr>
        		<tr>
        			<td colspan="2" style="height: 10px;"></td>
        		</tr>
        		<tr>
        			<td colspan="2">
        			<form:table id="49" autoNumber="true" >
	        			<form:row>
		            		<form:column colNumbers="0,1" textStyleClass="unindent"/>
			        		<tr>
			        			<td colspan="4" class="questionText"><b>Address</b></td>
			        		</tr>
							<form:column colNumbers="2,3,4,5,6,7,8" textStyleClass="unindent"/>
			        		<tr>
			        			<td colspan="4" class="questionText"><b>Phone</b></td>
			        		</tr>
							<form:column colNumbers="9">
					            <tr id="col${colNumber}">
					                <td id="columnText" width="30%">
					                	${numberLabel} ${columnText}<br>
					                	<span class="indent"><span class="questionTextSmall">Country Code (Area Code) Number</span></span>
					                </td>
					                <td id="answer" width="69%">${answer}</td>
					      		</tr>
							</form:column>
			        		<tr>
			        			<td colspan="4" class="questionText"><b>Fax</b></td>
			        		</tr>
							<form:column colNumbers="10">
					            <tr id="col${colNumber}">
					                <td id="columnText" width="30%">
					                	${numberLabel} ${columnText}<br>
					                	<span class="indent"><span class="questionTextSmall">Country Code (Area Code) Number</span></span>
					                </td>
					                <td id="answer" width="69%">${answer}</td>
					      		</tr>
							</form:column>
	        			</form:row>
	        		</form:table>
	        		</td>
	        	</tr>
        		<form:questionaire id="50" styleClass="bgLtGray" autoNumber="true"/>
<%--          	</table>--%>
              <%=endIndent%>

		</form:sectionContainer>
     <table width="100%">
		<tr>
			<td colspan="2" align="right" height="20"></td>
		</tr>
	 </table>
       <table width="100%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>
      	</table>
    </p>
   <!-- ======================================================= -->
   <!-- SECTION 6 -->
  <p>
	<form:sectionContainer id="6" questionWidth="90%">
                 <%=startIndent%>
<%--             <table width="95%"  border="0"  cellpadding="3" cellspacing="0">--%>
              		<form:questionaire id="51,52,53,54" autoNumber="true"/>
				<tr><td>&nbsp;</td></tr>
        		<tr>
        			<td colspan="2">
					<form:table id="55" width="100%" autoNumber="true" styleClass="bgLtGray">
	        			<form:row>
		            		<form:column colNumbers="0" textStyleClass="unindent"/>
			        		<tr>
			        			<td colspan="4" class="questionText"><b>Address</b></td>
			        		</tr>
							<form:column colNumbers="1,2,3,4,5,6,7" textStyleClass="unindent"/>
			        		<tr>
			        			<td colspan="4" class="questionText"><b>Phone</b></td>
			        		</tr>
							<form:column colNumbers="8">
					            <tr id="col${colNumber}">
					                <td id="columnText" width="30%">
					                	${numberLabel} ${columnText}<br>
					                	<span class="indent"><span class="questionTextSmall">Country Code (Area Code) Number</span></span>
					                </td>
					                <td id="answer" width="69%">${answer}</td>
					      		</tr>
							</form:column>
			        		<tr>
			        			<td colspan="4" class="questionText"><b>Fax</b></td>
			        		</tr>
							<form:column colNumbers="9">
					            <tr id="col${colNumber}">
					                <td id="columnText" width="30%">
					                	${numberLabel} ${columnText}<br>
					                	<span class="indent"><span class="questionTextSmall">Country Code (Area Code) Number</span></span>
					                </td>
					                <td id="answer" width="69%">${answer}</td>
					      		</tr>
							</form:column>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td colspan="4"  class="indent">
								<table width="100%" border="0">
								</table>
								</td>
							</tr>
	        			</form:row>
					</form:table>
					</td>
				</tr>
<%--			</table>--%>

<%--           <table width="95">--%>
              <form:questionaire id="56" autoNumber="true" answerStyleClass="inputMedium"/>
<%--           </table>--%>
            <%=endIndent%>
	</form:sectionContainer>
       <table width="100%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>
      	</table>

    </p>
   <!-- ======================================================= -->
   <!-- SECTION 7-->
  <p>
     <form:sectionContainer id="7" questionWidth="90%">
    <tr><td colspan=4 class="indent">
                             <table width="95%" align="right" border="0"  cellpadding="3" cellspacing="0">
                        			<tr>
     				<td colspan="2" class="questionTextBold">
     					Shipping Location
     				</td>
     			</tr>
     			<form:questionaire id="57,58" autoNumber="true"  questionWidth="50%" answerWidth="50%" />
     			<tr>
     				<td colspan="2" class="questionTextBold">
     					Address
     				</td>
     			</tr>
     			<form:questionaire id="59,60,61,62,63,64,65" autoNumber="true"  questionWidth="50%" answerWidth="50%" />
     			<tr>
     				<td colspan="2" class="questionTextBold">
     					Phone
     				</td>
     			</tr>
     			<form:questionaire id="66" autoNumber="true"  questionWidth="50%" answerWidth="50%" >
					<tr class="${question.styleClass}">
						<td width="${question.width}" valign="top">
							<span class="${question.textStyleClass}">${question.numberLabel} ${question.text}</span><br>
							<span class="questionTextSmall">CC (Area Code) Number</span>
						</td>
						<td width="${answer.width}" valign="top">
							<#include "answer.fhtml">
						</td>
					</tr>
     			</form:questionaire>
     			<tr>
     				<td colspan="2" class="questionTextBold">
     					Fax
     				</td>
     			</tr>
     			<form:questionaire id="67" autoNumber="true"  questionWidth="50%" answerWidth="50%" >
					<tr class="${question.styleClass}">
						<td width="${question.width}" valign="top">
							<span class="${question.textStyleClass}">${question.numberLabel} ${question.text}</span><br>
							<span class="questionTextSmall">CC (Area Code) Number</span>
						</td>
						<td width="${answer.width}" valign="top">
							<#include "answer.fhtml">
						</td>
					</tr>
     			</form:questionaire>
				<tr>
        			<td colspan="2">

					<form:table id="68" width="100%" autoNumber="true" styleClass="bgLtGray">
	        			<form:row>
		            		<form:column colNumbers="0,1" textStyleClass="unindent"/>
			        		<tr>
			        			<td colspan="4" class="questionText"><b>Address</b></td>
			        		</tr>
							<form:column colNumbers="2,3,4,5,6,7,8" textStyleClass="unindent"/>
			        		<tr>
			        			<td colspan="4" class="questionText"><b>Phone</b></td>
			        		</tr>
							<form:column colNumbers="9">
					            <tr id="col${colNumber}">
					                <td id="columnText" width="30%">
					                	${numberLabel} ${columnText}<br>
					                	<span class="indent"><span class="questionTextSmall">Country Code (Area Code) Number</span></span>
					                </td>
					                <td id="answer" width="69%">${answer}</td>
					      		</tr>
							</form:column>
			        		<tr>
			        			<td colspan="4" class="questionText"><b>Fax</b></td>
			        		</tr>
							<form:column colNumbers="10">
					            <tr id="col${colNumber}">
					                <td id="columnText" width="30%">
					                	${numberLabel} ${columnText}<br>
					                	<span class="indent"><span class="questionTextSmall">Country Code (Area Code) Number</span></span>
					                </td>
					                <td id="answer" width="69%">${answer}</td>
					      		</tr>
							</form:column>
	        			</form:row>
					</form:table>

					</td>
				</tr>
     			<tr>
     				<td colspan="2" class="questionTextBold">
     					&nbsp;<br>IP Supply
     				</td>
     			</tr>
     			<form:questionaire altStyleClass="bgLtGray" id="69,70,71,72,73,74,75,76,77,78,79,80" autoNumber="true"  questionWidth="50%" answerWidth="50%" />
     			<tr><td>&nbsp;</td></tr>
     			<tr class="bgLtGray">
     				<td colspan="2" class="questionTextBold">
     					Controlled Substances
     				</td>
     			</tr>
     			<form:questionaire altStyleClass="bgLtGray" id="81" autoNumber="true"  questionWidth="50%" answerWidth="50%" />
     			<form:questionaire styleClass="bgLtGray" altStyleClass="question" id="82,83,84,85,86,87" autoNumber="true"  questionWidth="50%" answerWidth="50%" />
     			<tr>
     				<td colspan="2" class="questionTextBold">
     					&nbsp;<br>Radiolabeled IP
     				</td>
     			</tr>
     			<form:questionaire id="88,89,90" autoNumber="true"  questionWidth="50%" answerWidth="50%" />
     			<tr><td>&nbsp;</td></tr>
     			<tr class="bgLtGray">
     				<td colspan="2" class="questionTextBold">
     					Infectious and Hazardous Material
     				</td>
     			</tr>
     			<form:questionaire id="91" styleClass="bgLtGray" autoNumber="true"  questionWidth="50%" answerWidth="50%" />
     			<tr class="bgLtGray">
     				<td colspan="2">
     					&nbsp;<br>
     					<div class="indent">
     						<table width="90%" cellpadding="0" cellspacing="0" class="bgLtGray">
     						<form:questionaire id="92" autoNumber="true" questionWidth="50%" answerWidth="50%" />
     						</table>
     					</div>
     				</td>
     			</tr>
     			<form:question id="93" autoNumber="true"  questionWidth="50%" answerWidth="50%" />
     		</table>
       </td></tr>


	 </form:sectionContainer>
       <table width="100%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>
      	</table>

    </p>
   <!-- ======================================================= -->
   <!-- SECTION 8-->
  <p>
     <form:sectionContainer id="8" questionWidth="90%">
            <tr><td colspan=2 class="indent">
         <table width="95%" align="right" border="0"  cellpadding="3" cellspacing="0">
                		 	<form:questionaire id="94,95,96,97,98,99,100,101,102" autoNumber="true" altStyleClass="bgLtGray"  questionWidth="50%" answerWidth="50%" />
				<tr>
					<td colspan="2">
					<div class="indent">
					<form:table id="103" width="95%" autoNumber="true" styleClass="bgLtGray">
						<form:row>
		            		<form:column colNumbers="0,1" textStyleClass="unindent"/>
			        		<tr>
			        			<td colspan="4" class="indent"><b>Address</b></td>
			        		</tr>
							<form:column colNumbers="2,3,4,5,6,7,8" textStyleClass="unindent"/>
			        		<tr>
			        			<td colspan="4" class="indent"><b>Phone</b></td>
			        		</tr>
							<form:column colNumbers="9">
                                <tr id="col${colNumber}">
					                <td id="columnText" width="30%">
					                	${numberLabel} ${columnText}<br>
					                	<span class="indent"><span class="questionTextSmall">Country Code (Area Code) Number</span></span>
					                </td>
					                <td id="answer" width="69%">${answer}</td>
					      		</tr>
							</form:column>
			        		<tr>
			        			<td colspan="4" class="indent"><b>Fax</b></td>
			        		</tr>
							<form:column colNumbers="10">
                                 <tr id="col${colNumber}">
					                <td id="columnText" width="30%">
					                	${numberLabel} ${columnText}<br>
					                	<span class="indent"><span class="questionTextSmall">Country Code (Area Code) Number</span></span>
					                </td>
					                <td id="answer" width="69%">${answer}</td>
					      		</tr>
							</form:column>
						</form:row>
					</form:table>
					</div>
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<form:questionaire id="104" autoNumber="true"/>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td colspan="2">
					<div class="indent">
					<form:table id="105" width="95%" autoNumber="true" styleClass="bgLtGray">
						<form:row>
		            		<form:column colNumbers="0,1" textStyleClass="unindent"/>
			        		<tr>
			        			<td colspan="4" class="indent"><b>Address</b></td>
			        		</tr>
							<form:column colNumbers="2,3,4,5,6,7,8" textStyleClass="unindent"/>
			        		<tr>
			        			<td colspan="4" class="indent"><b>Phone</b></td>
			        		</tr>
							<form:column colNumbers="9">
					          <tr id="col${colNumber}">
					                <td id="columnText" width="30%">
					                	${numberLabel} ${columnText}<br>
					                	<span class="indent"><span class="questionTextSmall">Country Code (Area Code) Number</span></span>
					                </td>
					                <td id="answer" width="69%">${answer}</td>
					      		</tr>
							</form:column>
			        		<tr>
			        			<td colspan="4" class="indent"><b>Fax</b></td>
			        		</tr>
							<form:column colNumbers="10">
					            <tr id="col${colNumber}">
					                <td id="columnText" width="30%">
					                	${numberLabel} ${columnText}<br>
					                	<span class="indent"><span class="questionTextSmall">Country Code (Area Code) Number</span></span>
					                </td>
					                <td id="answer" width="69%">${answer}</td>
					      		</tr>
							</form:column>
						</form:row>
					</form:table>
					</div>
					</td>
				</tr>
			</table>
	         </td>
         				</tr>

	</form:sectionContainer>
       <table width="100%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>
      	</table>
    </p>
   <!-- ======================================================= -->
   <!-- SECTION 9-->
  <p>
     <form:sectionContainer id="9" questionWidth="90%">
                     <%=startIndent%>


         <form:questionaire id="106,107,108,109,110,111,112,113,,114,115,116" autoNumber="true" questionWidth="60%" answerWidth="40" altStyleClass="bgLtGray"/>

                 <%=endIndent%>


     </form:sectionContainer>
       <table width="100%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>
      	</table>
    </p>
   <!-- ======================================================= -->
   <!-- SECTION 10-->
  <p>
     <form:sectionContainer id="10" questionWidth="90%">
            <tr> <td colspan="2">
			<table width="95%" align=right border="0" cellpadding="3" cellspacing="0">
     		<TR><TD colspan=2>&nbsp&nbsp</td></TR>
             <tr>
     				<td colspan="2" class="questionTextBold">
     					Site Staff Computer Literacy
     				</td>
     			</tr>
                   			<form:questionaire id="117,118,119,120,121" autoNumber="true"  questionWidth="50%" answerWidth="50%" />
                <TR><TD colspan=2>&nbsp&nbsp</td></TR>
                                     			<tr class="bgLtGray">
     				<td colspan="2" class="questionTextBold">
     					Site Computer Accessibility
     				</td>
     			</tr>

     			<form:questionaire id="122,123,124,125" autoNumber="true" styleClass="bgLtGray"  questionWidth="50%" answerWidth="50%" />
                  <TR><TD colspan=2>&nbsp&nbsp</td></TR>
     			<tr>
     				<td colspan="2" class="questionTextBold">
     					Site Training
     				</td>
     			</tr>
                       			<form:questionaire id="126" autoNumber="true"  questionWidth="50%" answerWidth="50%" />
     			<tr>
     				<td colspan="2">
     				<div class="indent">
     				<div class="indent">
     					<table width="85%">
     						<form:questionaire id="127" autoNumber="true" questionWidth="50%" answerWidth="50%" />
     					</table>
     				</div>
     				</div>
     				</td>
     			</tr>
                    <TR><TD colspan=2>&nbsp&nbsp</td></TR>
                       			<form:questionaire id="128" autoNumber="true"  questionWidth="50%" answerWidth="50%" />
                  <TR><TD colspan=2>&nbsp&nbsp</td></TR>
     			<tr class="bgLtGray">
     				<td colspan="2" class="questionTextBold">
     					Monitoring Logistics
     				</td>
     			</tr>
     			<form:questionaire id="129,130,131,132" autoNumber="true" styleClass="bgLtGray"  questionWidth="50%" answerWidth="50%" />
     		</table>
                     		</td>
              				</tr>

     </form:sectionContainer>
       <table width="100%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>
      	</table>
    </p>
   <!-- ======================================================= -->
   <!-- SECTION 11-->
  <p>
     <form:sectionContainer id="11" questionWidth="90%">
	       <tr>
       			<td colspan=2>

			 <table width="95%" align=right border="0" cellpadding="3" cellspacing="0">
		     	<form:questionaire id="133,134,135" autoNumber="true" altStyleClass="bgLtGray"  questionWidth="50%" answerWidth="50%" />
		     </table>

                 </td>
              				</tr>

     </form:sectionContainer>

       <table width="100%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>
      	</table>
    </p>

   <form:signatures/>
   <!-- ======================================================= -->            

   </form>
</OTML:OFFLINE-FORM>
<% org.apache.log4j.Category.getInstance("timer").info("jsp done"); %>