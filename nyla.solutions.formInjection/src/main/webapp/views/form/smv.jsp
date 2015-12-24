<!--
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://gcsm.bms.com/tld/form.tld" prefix="form"%>
-->
<% long start = System.currentTimeMillis(); %>
<%
	String startIndent = "<tr><td colspan=\"2\">	<table width=\"100%\">	<tr>	<td width=\"50\"><img src=\"/gcsm/skins/default/images/blank.gif\" width=\"50\"></td>	<td>	<table width=\"100%\">";
	String endIndent = "</table>	</td>	</tr>	</table>	</td>	</tr>";

%>
<script>
 var visitType='';
   function signSMV(aForm)
   {
      if(!validateVisitDate(aForm))
      {
      printConfirmation('<span class=error>THE FORM CANNOT BE SUBMITTED.</span>');
         return  false;
      }
      if (!validateForm(aForm))
<%--      if(!signForm(aForm))--%>
      {
         return false;
      }

      return true;
   }//--------------------------------------------------------------
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
  var unblindedReqs ='<form:inputNameForQuestion id="57"/>:<form:inputNameForQuestion id="58"/>:<form:inputNameForQuestion id="59"/>:<form:inputNameForQuestion id="60"/>';
 unblindedReqs = unblindedReqs + ':<form:inputNameForQuestion id="61"/>:<form:inputNameForQuestion id="62"/>:<form:inputNameForQuestion id="63"/>:<form:inputNameForQuestion id="64"/>';
   unblindedReqs = unblindedReqs +  ':<form:inputNameForQuestion id="65"/>:<form:inputNameForQuestion id="67"/>:<form:inputNameForQuestion id="68"/>:<form:inputNameForQuestion id="69"/>';
   unblindedReqs = unblindedReqs +  ':<form:inputNameForQuestion id="70"/>:<form:inputNameForQuestion id="71"/>:<form:inputNameForQuestion id="72"/>:<form:inputNameForQuestion id="74"/>';
   var aQuestionPrefix = '<form:inputNameForColumn id="10" tableID="46" columnNumber="0"/>';
                       var elementId = null;
                       elementId =aQuestionPrefix+'[0][0];tablePK=46;';
 unblindedReqs = unblindedReqs +  ':' + elementId;
var phoneReqs = '<form:inputNameForQuestion id="29"/>:<form:inputNameForQuestion id="30"/>:<form:inputNameForQuestion id="31"/>:<form:inputNameForQuestion id="32"/>';
 phoneReqs = phoneReqs +  ':<form:inputNameForQuestion id="33"/>:<form:inputNameForQuestion id="34"/>:<form:inputNameForQuestion id="35"/>:<form:inputNameForQuestion id="36"/>';
 phoneReqs = phoneReqs +   ':<form:inputNameForQuestion id="37"/>:<form:inputNameForQuestion id="41"/>:<form:inputNameForQuestion id="42"/>:<form:inputNameForQuestion id="43"/>';
phoneReqs = phoneReqs +   ':<form:inputNameForQuestion id="45"/>:<form:inputNameForQuestion id="4500"/>:<form:inputNameForQuestion id="46"/>';
 phoneReqs = phoneReqs +   ':<form:inputNameForQuestion id="47"/>:<form:inputNameForQuestion id="48"/>:<form:inputNameForQuestion id="49"/>:<form:inputNameForQuestion id="50"/>';
phoneReqs = phoneReqs +  ':<form:inputNameForQuestion id="51"/>:<form:inputNameForQuestion id="52"/>:<form:inputNameForQuestion id="53"/>';
phoneReqs = phoneReqs + ':<form:inputNameForQuestion id="54"/>:<form:inputNameForQuestion id="55"/>:<form:inputNameForQuestion id="56"/>';
phoneReqs = phoneReqs + ':'+ unblindedReqs;

var standardReqs =phoneReqs;
<%--      --%>

 switch(visitType) {
             case "Phone": {
                   conditionalQuestionsTable.put('<form:inputNameForQuestion id="7"/>','Phone');
                requiredQuestionsTable.put('<form:inputNameForQuestion id="7"/>', phoneReqs);
                                                break;
                   }
              case "Un-blinded": {
                conditionalQuestionsTable.put('<form:inputNameForQuestion id="7"/>','Un-blinded');
                 requiredQuestionsTable.put('<form:inputNameForQuestion id="7"/>', unblindedReqs);
                                                  break
                   }
                 case "Standard":  {
                   conditionalQuestionsTable.put('<form:inputNameForQuestion id="7"/>','Standard');
                   requiredQuestionsTable.put('<form:inputNameForQuestion id="7"/>', standardReqs);
                   break
                    }
                     default:  {
                   conditionalQuestionsTable.put('<form:inputNameForQuestion id="7"/>','Standard');
                   requiredQuestionsTable.put('<form:inputNameForQuestion id="7"/>', standardReqs);
                   break
                    }

             }



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

    if (!signForm(aForm)) return false;
     return true;
    }
    else {
//alert("Please populate required fields.")
printConfirmation('<span class=error>THE FORM CANNOT BE SUBMITTED.</span>');
return false;
}
}
   function validateVisitDate(aForm)
   {

       var rowCnt = 0;
       var visitDate1Element = null;
       var visitDate0Element = null;
       var visitDate1Prefix = '<form:inputNameForColumn id="5" columnNumber="1" tableID="6"/>';
      var visitDate0Prefix = '<form:inputNameForColumn id="5" columnNumber="0" tableID="6"/>';
       //prefix [1][0];tablePK=6;
      var visitDate1ID = null;
      var visitDate0ID = null;
      var now = new Date();

      var bool = true;
       do
       {
           visitDate1ID = visitDate1Prefix + "[1]["+rowCnt+"];tablePK=6;";
           visitDate0ID = visitDate0Prefix + "[0]["+rowCnt+"];tablePK=6;";
           visitDate1Element = document.getElementById(visitDate1ID);
           visitDate0Element =document.getElementById(visitDate0ID);

           //Check VISIT DATE 1
           if(visitDate1Element != null)
           {

              if(!checkIfDateLaterThanTodayDDMMMYYYY("Visit date", visitDate1Element, true))
              {
                 bool =  false;
              }
           }//end if

           //Check visitDate0
           if(visitDate0Element != null )
           {
              //TODO: validate Less Than today
              if(!checkIfDateLaterThanTodayDDMMMYYYY("Visit date", visitDate0Element, false))
              {
                 bool = false;
              }
           }//end if

          if( visitDate0Element != null && visitDate0Element.value != "" &&
               visitDate1Element != null && visitDate1Element.value != "" )
          {
              if( toDateDDMMMYYY(visitDate0Element) > toDateDDMMMYYY(visitDate1Element))
              {
                 bool = false;
                 printError(visitDate0Element, "Visit date must be less than or equal to "+visitDate1Element.value);
              }
          }

            rowCnt =  rowCnt +  1;
       }
       while(visitDate1Element !=  null);

       return bool;
   }//----------------------------------------------------
         function initializePerSMVVisitType(aVisitTypeElement)
         {
   var choice = aVisitTypeElement.options[ aVisitTypeElement.selectedIndex ];
             var phoneIds = new Array('<form:inputNameForQuestion id="75"/>','<form:inputNameForQuestion id="7200"/>');
   var aQuestionPrefix = '<form:inputNameForColumn id="78" tableID="3003" columnNumber="1"/>';
               var elementId = null;
               elementId =aQuestionPrefix+'[1][0];tablePK=3003;';
               phoneIds = phoneIds.concat(elementId);
                 var aQuestionPrefix = '<form:inputNameForColumn id="78" tableID="3003" columnNumber="5"/>';
              elementId =aQuestionPrefix+'[5][0];tablePK=3003;';
              phoneIds = phoneIds.concat(elementId);
                  var aQuestionPrefix = '<form:inputNameForColumn id="78" tableID="3003" columnNumber="6"/>';
              elementId =aQuestionPrefix+'[6][0];tablePK=3003;';
              phoneIds = phoneIds.concat(elementId);
var unblindedIds = new Array('<form:inputNameForQuestion id="29"/>','<form:inputNameForQuestion id="30"/>',
                                           '<form:inputNameForQuestion id="31"/>','<form:inputNameForQuestion id="32"/>',
                                            '<form:inputNameForQuestion id="33"/>','<form:inputNameForQuestion id="34"/>',
                                          '<form:inputNameForQuestion id="35"/>','<form:inputNameForQuestion id="36"/>',
                                           '<form:inputNameForQuestion id="37"/>','<form:inputNameForQuestion id="41"/>',
                                           '<form:inputNameForQuestion id="42"/>','<form:inputNameForQuestion id="43"/>',
                                          '<form:inputNameForQuestion id="45"/>',
                                           '<form:inputNameForQuestion id="4500"/>','<form:inputNameForQuestion id="46"/>',
                                           '<form:inputNameForQuestion id="47"/>','<form:inputNameForQuestion id="48"/>',
                                           '<form:inputNameForQuestion id="49"/>','<form:inputNameForQuestion id="50"/>',
                                           '<form:inputNameForQuestion id="51"/>','<form:inputNameForQuestion id="52"/>',
                                            '<form:inputNameForQuestion id="53"/>','<form:inputNameForQuestion id="54"/>',
                                            '<form:inputNameForQuestion id="55"/>','<form:inputNameForQuestion id="56"/>'
                                              );
         unblindedIds = unblindedIds.concat(phoneIds);
             var nonstandardIds = new Array(  '<form:inputNameForQuestion id="57"/>','<form:inputNameForQuestion id="58"/>',
                                            '<form:inputNameForQuestion id="59"/>','<form:inputNameForQuestion id="60"/>',
                                             '<form:inputNameForQuestion id="61"/>','<form:inputNameForQuestion id="62"/>',
                                               '<form:inputNameForQuestion id="63"/>','<form:inputNameForQuestion id="64"/>',
                                              '<form:inputNameForQuestion id="65"/>','<form:inputNameForQuestion id="67"/>',
                                                '<form:inputNameForQuestion id="68"/>','<form:inputNameForQuestion id="69"/>',
                                               '<form:inputNameForQuestion id="70"/>','<form:inputNameForQuestion id="71"/>',
                                               '<form:inputNameForQuestion id="72"/>','<form:inputNameForQuestion id="74"/>'
                                             );

         nonstandardIds = nonstandardIds.concat(unblindedIds);
        visitType = choice.text;
            switch(visitType) {
             case "Phone": {
                  clearDefaultToNE(nonstandardIds);
                  setDefaultToNE(phoneIds);
                  break;
                   }
            case "Non-Standard": {
                 setDefaultToNE(nonstandardIds);
                 break;
                   }
             case "Un-blinded": {
                  clearDefaultToNE(nonstandardIds);
                  setDefaultToNE(unblindedIds);
                 break
                   }
             default: {
                    clearDefaultToNE(nonstandardIds);
                  break;
                  }
             }

         }//----------------------------------------------------
</script>
<jsp:include page="/views/form/initial_visit_type.jsp"/>

<OTML:OFFLINE-FORM OFFLINE-ID="SMV Form" TITLE="SMV Submission">

       <form id="aForm" name="aForm" action="<c:url value="/do/formAction"/>" method="POST">

   <table class="formHeader" width="100%" cellpadding="0">
        <tr>
            <td>
            <!-- Form Type Name -->
	       <c:out value="${sessionScope.form.name}"/>/Assessment <c:out value="${sessionScope.form.classification}"/><!-- FORM_ID: <c:out value="${form.primaryKey}"/> -->
              <input type="hidden" name="op" value=""/>
               <input type="hidden" name="formID" value="<c:out value="${form.primaryKey}"/>"/>
               <input type="hidden" name="filed" value="<c:out value="${form.filed}"/>"/>

               <!-- isEdited =  <c:out value="${form.edited}"/> -->
                <!-- createUserId =  <c:out value="${form.createUserID}"/> -->
                <!-- sitePK =  <c:out value="${form.sitePK}"/> -->
	     </td>
  	    <td align="right">
                    <jsp:include page="/views/form/menu.jsp">
                        <jsp:param name="signForm" value="signSMV"/>
                    </jsp:include>
       </td>
         </tr>
         <tr  class="sectionHeader">
            <td colspan="2"><form:sectionGlossary id="1,2,3,4,5,6,7,8,9"/></td>
         </tr>
<!-- ======================================= -->
<!-- Save Confirmation -->
    <jsp:include page="/views/form/save_confirmation.jsp"/>
<!-- ======================================= -->
   </table>
   <!-- ======================================================= -->
   <!-- SECTION 0 -->
   <p>
      <table width="100%" border="0" cellspacing="0" cellpadding="3">
         <!-- Preparer , Prepared on, Protocol, status -->
         <form:questionaire  id="2,3,4" questionWidth="20%" answerWidth="80%"/>
      </table>
      <!-- Visit date -->
      <table width="70%">
         <form:question id="5"/>
        </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="3">
          <!-- Visit Type -->
         <form:question id="7" questionWidth="20%" answerWidth="80%" onChange="initializePerSMVVisitType(this);"/>

         <!-- What will eval.. -->
<%--         <form:question id="8" questionWidth="20%" answerWidth="80%" styleClass="questionGrayBG"/>--%>
     </table>
   </p>
   <!-- ======================================================= -->
   <!-- SECTION 1 -->
    <p>
          <form:sectionContainer id="1"  questionWidth="90%">
            <%=startIndent%>
                <form:questionaire id="9,10,11"/>
   	      <%=endIndent%>
          <%=startIndent%>
                 <tr><td  class="questionHeader" width="80%">Site/Investigator Details</td>  </tr>
                    <%=startIndent%>
                                   <form:questionaire id="12,13,,16,17,18,19"/>
                    <%=endIndent%>
          <%=endIndent%>
          </form:sectionContainer>
       <table width="100%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>
      	</table>
    </p>
   <!-- ======================================================= -->
   <!-- SECTION 2 -->
    <p>
          <form:sectionContainer id="2"   questionWidth="90%">
           <%=startIndent%>
<%--                             --%>
               <tr class="questionGrayBG">
                  <td width="80%">
                      <table  align="right" width="100%">
                              <form:question id="20" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion20');return false;">Specify</a>
                     </td>
               </tr>
                 <%=endIndent%>

               <tr class="questionGrayBG">
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion20">
                         <tr>
                            <td width="40%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="24"/>
                                           <!-- Reason  Others -->
                                           <form:question id="26"/>
                                    </table>
                             </td>
                            <td width="40%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="25"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="27"/>
                                    </table>
                             </td>
                            <td width="20%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="28"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
                <!-- Question 2-2 -->
<%--                 <tr>--%>
<%--                                   <td colspan="2">--%>
<%--                                       <table  align="right" width="98%">--%>
                  <%=startIndent%>
<%--                             --%>
               <tr >
                  <td width="80%">
                      <table  align="right" width="100%">
                         <tr>
                        <form:question id="21" questionWidth="72%"  answerWidth="28%"/>
                         </tr>
                     </table>
                                    </td>
                                 </tr>
                     <%=endIndent%>
                <!-- Staff Changes -->
<%--                <tr>--%>
<%--                   <td colspan="2">--%>
<%--                       <table  align="right" width="98%">--%>
                          <%=startIndent%>
                         <form:question id="22"/>
                          <%=endIndent%>
<%--                        </table>--%>
<%--                     </td>--%>
<%--                  </tr>--%>

                  <!--- question 23 -->
                   <%=startIndent%>
                <tr class="questionGrayBG">
                           <td width="80%">
                               <table  align="right" width="100%">
                                       <form:question id="23" questionWidth="95%" answerWidth="5%"/>
                                </table>
                              </td>
                              <td width="20%" align="left">
                                  <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion23');return false;">Specify</a>
                              </td>
                        </tr>
                         <%=endIndent%>
               <tr class="questionGrayBG">
                             <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion23">
                         <tr>
                            <td width="34%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="340"/>
                                           <!-- Reason  Others -->
                                           <form:question id="342"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="341"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="343"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="344"/>
                                    </table>
                             </td>
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
   <!-- SECTION 3 -->
  <p>
       <form:sectionContainer id="3"   questionWidth="90%">
            <!-- 3-1 -->
       <tr align=right><td colspan="2">	<table width="100%">	<tr>	<td width="50"></td>	<td>	<table width="100%">
            <tr>
                  <td width="80%">
                      <table align="right" width="100%">
                              <form:question id="29" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion29');return false;">Specify</a>
                     </td>
               </tr>
<%--                               <%=endIndent%>--%>

               <tr >
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion29">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="290"/>
                                           <!-- Reason  Others -->
                                           <form:question id="292"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="291"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="293"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                            <form:question id="294"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
                <!-- ========================================================= -->
              <!--   Question 3-2 -->

                <tr  align=right class="questionGrayBG">
                 <td width="80%">
                      <table  align="right" width="100%">

                    <form:question id="30"  questionWidth="90%" answerWidth="10%"/>

                    </table>
                  </td>

               </tr>

                <!-- ========================================================= -->
              <!--   Question 3-2-1 -->
               <tr class="questionGrayBG">&nbsp</tr>

               </tr> <tr  align=right class="questionGrayBG">
                  <td width="80%">
                      <table  align="right" width="100%">
                              <form:question id="31" questionWidth="95%" answerWidth="5%" textStyleClass="textIndent"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion31');return false;">Specify</a>
                     </td>
               </tr>

               <tr class="questionGrayBG">
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion31">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="310"/>
                                           <!-- Reason  Others -->
                                           <form:question id="312"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="311"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="313"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="314"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
                <!-- ========================================================= -->
              <!--   Question 3-3-->
<%--               <%=startIndent%>--%>
                                           <tr>
                  <td width="80%">
                      <table  align="right" width="100%">
                              <form:question id="32" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion32');return false;">Specify</a>
                     </td>
               </tr>
<%--                <%=endIndent%>--%>

               <tr>
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion32">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="320"/>
                                           <!-- Reason  Others -->
                                           <form:question id="322"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="321"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="323"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="324"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
                <!-- ========================================================= -->
              <!--   Question 3-4 -->
<%--                <%=startIndent%>--%>
                                            <tr  align=right class="questionGrayBG">
                  <td width="80%">
                      <table align="right"  width="100%">
                              <form:question id="33" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion33');return false;">Specify</a>
                     </td>
               </tr>
<%--               <%=endIndent%>--%>
                                           <tr class="questionGrayBG">
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion33">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="330"/>
                                           <!-- Reason  Others -->
                                           <form:question id="332"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="331"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="333"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="334"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
                <!-- ========================================================= -->
              <!--   Question 3-5  -->
<%--               <%=startIndent%>--%>
                       <tr  align=right >
                  <td width="80%">
                      <table  align="right" width="100%">

                              <form:question id="34"questionWidth="90%" answerWidth="10%"/>
                       </table>
                     </td>
                     <td>&nbsp</td>
               </tr>
                                <tr class="questionGrayBG">&nbsp</tr>

                <!-- ========================================================= -->
              <!--   Question 3-5-1 -->

               <tr>
                  <td width="80%">
                      <table  align="right" width="100%">
                              <form:question id="35" questionWidth="95%" answerWidth="5%" textStyleClass="textIndent"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion35');return false;">Specify</a>
                     </td>
               </tr>
<%--                <%=endIndent%>--%>
               <tr>
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion35">
                         <tr>
                            <td width="35%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="350"/>
                                           <!-- Reason  Others -->
                                           <form:question id="352"/>
                                    </table>
                             </td>
                            <td width="35%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="351"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="353"/>
                                    </table>
                             </td>
                            <td width="35%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="354"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
                <!-- ========================================================= -->
              <!-- ========================================================= -->
              <!--   Question 3-6 -->
<%--               <%=startIndent%>--%>

                  <tr  align=right class="questionGrayBG">
                  <td width="80%">
                      <table  align="right" width="100%">
                              <form:question id="36" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion36');return false;">Specify</a>
                     </td>
               </tr>
               <tr class="questionGrayBG">
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion36">
                         <tr>
                            <td width="36%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="360"/>
                                           <!-- Reason  Others -->
                                           <form:question id="362"/>
                                    </table>
                             </td>
                            <td width="36%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="361"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="363"/>
                                    </table>
                             </td>
                            <td width="36%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="364"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
              <tr class="questionGrayBG">&nbsp</tr>
               <!-- ========================================================= -->
                <!-- 3-6-1 -->
             <tr  align=right class="questionGrayBG">
                  <td colspan=2 width="80%">
                      <table  align="right" width="100%">
                      <tr><td>
 <form:question id="37" questionWidth="95%" answerWidth="5%" textStyleClass="textIndent"/>

                     </td>
                </tr>

                <tr class="questionGrayBG">
                <td  width="95%" class="textIndent"> 3-6.2. If yes, then fill out a Protocol Deviation Form.</td>
                <td width="5%" align="left">
                   <c:if test="${!form.readOnly}">
                       <c:if test="${!(empty sessionScope.siteID)}">
                           <a href="<c:url value="/do/formAction?op=createForUnscheduled&siteID=${sessionScope.siteID}&relatedFormIDProp=${form.primaryKey}&formTypeName=Protocol Deviation"/>">Protocol Deviation Form</a>
                        </c:if>
                       <c:if test="${empty sessionScope.siteID}">
                          Protocol Deviation Form
                        </c:if>
                    </c:if>

                    &nbsp;</td>
              </tr>
              <!-- 3-6-3 -->
              <tr  align=right styleClass="questionGrayBG" >
                  <td width="80%">

 <form:question id="40" questionWidth="95%" answerWidth="5%" textStyleClass="textIndent"/>

                                 </td>
                            </tr>
                </table>
              <!-- ========================================================= -->
              <!--   Question 3-7 -->
               <tr>
                  <td width="80%">
                      <table  align="right" width="100%">
                              <form:question id="41" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion41');return false;">Specify</a>
                     </td>
               </tr>
               <tr>
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion41">
                         <tr>
                            <td width="41%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="410"/>
                                           <!-- Reason  Others -->
                                           <form:question id="412"/>
                                    </table>
                             </td>
                            <td width="41%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="411"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="413"/>
                                    </table>
                             </td>
                            <td width="41%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="414"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
                <!-- ========================================================= -->
              <!-- ========================================================= -->
              <!--   Question 3-7-1 -->
               <tr>
                  <td width="80%">
                      <table  align="right" width="100%">
                              <form:question id="42" questionWidth="95%" answerWidth="5%" textStyleClass="textIndent"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion42');return false;">Specify</a>
                     </td>
               </tr>
               <tr>
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion42">
                         <tr>
                            <td width="42%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="420"/>
                                           <!-- Reason  Others -->
                                           <form:question id="422"/>
                                    </table>
                             </td>
                            <td width="42%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="421"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="423"/>
                                    </table>
                             </td>
                            <td width="42%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="424"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
                <!-- ========================================================= -->
              <!-- ========================================================= -->
              <!--   Question 3-8 -->
               <tr  align=right class="questionGrayBG">
                  <td width="80%">
                      <table  align="right"  width="100%">
                              <form:question id="43" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion43');return false;">Specify</a>
                     </td>
               </tr>
               <tr>
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion43">
                         <tr>
                            <td width="43%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="430"/>
                                           <!-- Reason  Others -->
                                           <form:question id="432"/>
                                    </table>
                             </td>
                            <td width="43%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="431"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="433"/>
                                    </table>
                             </td>
                            <td width="43%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="434"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
                <!-- ========================================================= -->
              <!-- ========================================================= -->
              <!--   Question 3-9 -->
               <tr>
                  <td width="80%">
                      <table  align="right" width="100%">
                              <form:question id="45" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion45');return false;">Specify</a>
                     </td>
               </tr>
               <tr>
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion45">
                         <tr>
                            <td width="45%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="450"/>
                                           <!-- Reason  Others -->
                                           <form:question id="452"/>
                                    </table>
                             </td>
                            <td width="45%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="451"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="453"/>
                                    </table>
                             </td>
                            <td width="45%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="454"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
                 <%=endIndent%>
                <!-- ========================================================= -->
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
        <form:sectionContainer  id="4" questionWidth="90%">
               <!-- ========================================================= -->
              <!--   Question 4-1 -->
              <%=startIndent%>

               <tr>
                  <td width="80%">
                      <table width="100%">
                              <form:question id="46" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion46');return false;">Specify</a>
                     </td>
               </tr>
               <tr>
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion46">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="460"/>
                                           <!-- Reason  Others -->
                                           <form:question id="462"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="461"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="463"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="464"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
                <!-- ======================================================== -->
                <!-- ========================================================= -->
              <!--   Question 4-2 -->
               <tr class="questionGrayBG">
                  <td width="80%">
                      <table width="100%">
                              <form:question id="47" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="40%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion47');return false;">Specify</a>
                     </td>
               </tr>
               <tr class="questionGrayBG">
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion47">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="470"/>
                                           <!-- Reason  Others -->
                                           <form:question id="472"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="471"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="473"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="474"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
                <!-- ========================================================= -->
                <!-- ========================================================= -->
              <!--   Question 4-3 -->
               <tr>
                  <td width="80%">
                      <table width="100%">
                              <form:question id="48" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion48');return false;">Specify</a>
                     </td>
               </tr>
               <tr>
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion48">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="480"/>
                                           <!-- Reason  Others -->
                                           <form:question id="482"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="481"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="483"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="484"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
                <!-- ========================================================= -->
                <!-- ========================================================= -->
              <!--   Question 4-4 -->
               <tr class="questionGrayBG">
                  <td width="80%">
                      <table width="100%">
                              <form:question id="49" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion49');return false;">Specify</a>
                     </td>
               </tr>
               <tr class="questionGrayBG">
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion49">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="490"/>
                                           <!-- Reason  Others -->
                                           <form:question id="492"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="491"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="493"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="494"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
                <!-- ========================================================= -->
                <!-- 4-5 -->
               <tr>
                  <td width="80%">
                      <table width="100%">
                              <form:question id="4500" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion4500');return false;">Specify</a>
                     </td>
               </tr>
               <tr>
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion4500">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="4501"/>
                                           <!-- Reason  Others -->
                                           <form:question id="4503"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="4502"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="4504"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="4505"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
               <!-- ========================================================= -->
              <!--   Question 50/4-6 -->
               <tr class="questionGrayBG">
                  <td width="80%">
                      <table width="100%">
                              <form:question id="50" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion50');return false;">Specify</a>
                     </td>
               </tr>
               <tr class="questionGrayBG">
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion50">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="500"/>
                                           <!-- Reason  Others -->
                                           <form:question id="502"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="501"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="503"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="504"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
                <!-- ========================================================= -->
               <!-- ========================================================= -->
              <!--   Question 51 4-7-->
               <tr>
                  <td width="80%">
                      <table width="100%">
                              <form:question id="51" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion51');return false;">Specify</a>
                     </td>
               </tr>
               <tr>
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion51">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="510"/>
                                           <!-- Reason  Others -->
                                           <form:question id="512"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="511"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="513"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="514"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
                <!-- ========================================================= -->
                <!-- ========================================================= -->
              <!--   Question 52  4-8-->
               <tr class="questionGrayBG">
                  <td width="80%">
                      <table width="100%">
                              <form:question id="52" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion52');return false;">Specify</a>
                     </td>
               </tr>
               <tr class="questionGrayBG">
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion52">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="520"/>
                                           <!-- Reason  Others -->
                                           <form:question id="522"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="521"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="523"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="524"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
                <!-- ========================================================= -->
         <!-- ========================================================= -->
              <!--   Question 53 4-9 -->
               <tr>
                  <td width="80%">
                      <table width="100%">
                              <form:question id="53" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion53');return false;">Specify</a>
                     </td>
               </tr>
               <tr>
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion53">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="530"/>
                                           <!-- Reason  Others -->
                                           <form:question id="532"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="531"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="533"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="534"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
                <!-- ========================================================= -->
                <!-- ========================================================= -->
              <!--   Question 54, 4-10 -->
               <tr class="questionGrayBG">
                  <td width="80%">
                      <table width="100%">
                              <form:question id="54" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion54');return false;">Specify</a>
                     </td>
               </tr>
               <tr class="questionGrayBG">
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion54">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="540"/>
                                           <!-- Reason  Others -->
                                           <form:question id="542"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="541"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="543"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="544"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
                <!-- ========================================================= -->
                <!-- ========================================================= -->
              <!--   Question 55, 4-11 -->
               <tr>
                  <td width="80%">
                      <table width="100%">
                              <form:question id="55" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion55');return false;">Specify</a>
                     </td>
               </tr>
               <tr>
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion55">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="550"/>
                                           <!-- Reason  Others -->
                                           <form:question id="552"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="551"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="553"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="554"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
                <!-- ========================================================= -->
                <!-- ========================================================= -->
              <!--   Question 4-12 -->
               <tr class="questionGrayBG">
                  <td width="80%">
                      <table width="100%">
                              <form:question id="56" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion56');return false;">Specify</a>
                     </td>
               </tr>
               <tr class="questionGrayBG">
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion56">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="560"/>
                                           <!-- Reason  Others -->
                                           <form:question id="562"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="561"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="563"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="564"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
                <%=endIndent%>

                <!-- ========================================================= -->
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
             <%=startIndent%>
             	              <!--   Question  5-1 -->
               <tr>
                  <td width="80%">
                      <table width="100%" align="right">
                <form:question id="57" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion57');return false;">Specify</a>
                     </td>
               </tr>
               <tr>
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion57">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="570"/>
                                           <!-- Reason  Others -->
                                           <form:question id="572"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="571"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="573"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="574"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>

              <!--   Question 5-2 -->
               <tr class="questionGrayBG">
                  <td width="80%">
                      <table width="100%">
                              <form:question id="58" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion58');return false;">Specify</a>
                     </td>
               </tr>
               <tr class="questionGrayBG">
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion58">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="580"/>
                                           <!-- Reason  Others -->
                                           <form:question id="582"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="581"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="583"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="584"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
              <!--   Question 5-3 -->
               <tr>
                  <td width="80%">
                      <table width="100%">
                              <form:question id="59" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion59');return false;">Specify</a>
                     </td>
               </tr>
               <tr>
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion59">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="590"/>
                                           <!-- Reason  Others -->
                                           <form:question id="592"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="591"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="593"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="594"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
              <!--   Question 5-4 -->
               <tr class="questionGrayBG">
                  <td width="80%">
                      <table width="100%">
                              <form:question id="60" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion60');return false;">Specify</a>
                     </td>
               </tr>
               <tr class="questionGrayBG">
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion60">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="600"/>
                                           <!-- Reason  Others -->
                                           <form:question id="602"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="601"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="603"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="604"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
              <!--   Question 5-5  -->
               <tr>
                  <td width="61%">
                      <table width="100%">
                              <form:question id="61" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion61');return false;">Specify</a>
                     </td>
               </tr>
               <tr>
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion61">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="610"/>
                                           <!-- Reason  Others -->
                                           <form:question id="612"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="611"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="613"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="614"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
              <!--   Question 5-6 -->
               <tr class="questionGrayBG">
                  <td width="62%">
                      <table width="100%">
                              <form:question id="62" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion62');return false;">Specify</a>
                     </td>
               </tr>
               <tr class="questionGrayBG">
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion62">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="620"/>
                                           <!-- Reason  Others -->
                                           <form:question id="622"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="621"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="623"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="624"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>

              <!--   Question 5-7  -->
               <tr>
                  <td width="80%">
                      <table width="100%">
                              <form:question id="63" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion63');return false;">Specify</a>
                     </td>
               </tr>
               <tr>
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion63">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="630"/>
                                           <!-- Reason  Others -->
                                           <form:question id="632"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="631"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="633"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="634"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
              <!--   Question 5-8 -->
              <tr class="questionGrayBG">
                      <td colspan="2">
                          <table width="100%">
                <form:question id="64"questionWidth="95%" answerWidth="5%" styleClass="questionGrayBG"/>
                </table>
                                    </td>
                                  </tr>
                                  <!--   Question 5-8.1 -->
                <tr class="questionGrayBG">
                      <td colspan="2">
                          <table align="right" width="100%">
                              <tr>
<%--                                  <td width="10%"> &nbsp;</td>--%>
                                  <td width="80%">
                                       <table width="100%">
                                             <form:question id="65" styleClass="questionGrayBG"  questionWidth="72%" answerWidth="28%"  textStyleClass="textIndent"/>
                                         </table>
                                    </td>
                                  </tr>
                            </table>
                         </td>
                   </tr>
                   <!-- Please provide details -->
                <tr class="questionGrayBG">
                      <td class="textIndent" colspan="2">
                          <table  align="right" width="100%">
                              <tr>
<%--                                  <td width="15%"> &nbsp;</td>--%>
                                  <td width="80%">
                                       <table width="100%">
                                                  <form:question id="66" styleClass="questionGrayBG" questionWidth="71%" answerWidth="29%"  textStyleClass="textIndent"/>
                                         </table>
                                    </td>
                                  </tr>
                            </table>
                         </td>
                   </tr>
              <!-- 5- 9 -->
               <tr >
                  <td width="80%">
                      <table width="100%">
                              <form:question id="67" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion67');return false;">Specify</a>
                     </td>
               </tr>
               <tr>
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion67">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="670"/>
                                           <!-- Reason  Others -->
                                           <form:question id="672"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="671"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="673"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="674"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
                <%=endIndent%>

        </form:sectionContainer>
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
              <!--   Question 68 -->
               <tr>
                  <td width="80%">
                      <table width="100%">
                              <form:question id="68"  questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion68');return false;">Specify</a>
                     </td>
               </tr>
               <tr>
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion68">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="680"/>
                                           <!-- Reason  Others -->
                                           <form:question id="682"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="681"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="683"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="684"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
              <!--   Question 6-2 -->
               <tr class="questionGrayBG">
                  <td width="80%">
                      <table width="100%">
                              <form:question id="69" questionWidth="95%" answerWidth="5%" />
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion69');return false;">Specify</a>
                     </td>
               </tr>
               <tr class="questionGrayBG">
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion69">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="690"/>
                                           <!-- Reason  Others -->
                                           <form:question id="692"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="691"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="693"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="694"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
              <!--   Question 6-3  -->
               <tr>
                  <td width="80%">
                      <table width="100%">
                              <form:question id="70"  questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion70');return false;">Specify</a>
                     </td>
               </tr>
               <tr>
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion70">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="700"/>
                                           <!-- Reason  Others -->
                                           <form:question id="702"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="701"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="703"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="704"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
              <!--   Question 6-4 -->
               <tr class="questionGrayBG">
                  <td width="80%">
                      <table width="100%">
                              <form:question id="71"  questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion71');return false;">Specify</a>
                     </td>
               </tr>
               <tr  class="questionGrayBG">
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion71">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="710"/>
                                           <!-- Reason  Others -->
                                           <form:question id="712"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="711"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="713"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="714"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
                   <table width="100%" align=right>
           <tr >

                  <td  width="80%">
                      <table width="100%" align=right>
           <form:question id="72" questionWidth="72%" answerWidth="28%"/>

           <form:question id="73"  textStyleClass="textIndent"questionWidth="72%" answerWidth="28%"/>
           <!-- if other traing -->
               <form:question id="6512"  textStyleClass="textIndent"questionWidth="72%" answerWidth="28%"/>
           <!-- 6-6 -->
              <form:question id="74"  styleClass="questionGrayBG"questionWidth="72%" answerWidth="28%"/>
         </table>
                     </td>
                </tr>  <%=endIndent%>
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
     <%=startIndent%>
              <!--   Question 75 -->
               <tr>
                  <td width="80%">
                      <table width="100%">
                              <form:question id="75"  questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion75');return false;">Specify</a>
                     </td>
               </tr>
               <tr>
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion75">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="750"/>
                                           <!-- Reason  Others -->
                                           <form:question id="752"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="751"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="753"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="754"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>

               <tr class="questionGrayBG">
                  <td width="80%">
                      <table width="100%">
                              <form:question id="7200" questionWidth="95%" answerWidth="5%"/>
                       </table>
                     </td>
                     <td width="20%" align="left">
                         <a href="#" onclick="javascript: showSpecifyControl('specifyForQuestion7200');return false;">Specify</a>
                     </td>
               </tr>
               <tr class="questionGrayBG">
                  <td width="100%" colspan="2">
                      <table width="100%" class="specify" id="specifyForQuestion7200">
                         <tr>
                            <td width="33%" valign="top">
                                <table width="100%">
                                          <!-- Reasons -->
                                          <form:question id="7201"/>
                                           <!-- Reason  Others -->
                                           <form:question id="7202"/>
                                    </table>
                             </td>
                            <td width="33%" valign="top">
                                <table width="100%">
                                           <!-- Resolutions -->
                                            <form:question id="7203"/>
                                           <!-- Resolutions Other -->
                                            <form:question id="7204"/>
                                    </table>
                             </td>
                            <td width="34%" valign="top">
                                <table width="100%">
                                     <!-- Comment Other -->
                                       <form:question id="7205"/>
                                    </table>
                             </td>
                       </table>
                     </td>
                </tr>
           <!-- 7-3 SAEs -->
           <form:question id="76"/>
           <%=endIndent%>
     </form:sectionContainer>
<table width="100%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>
      	</table>
    </p>

   <!-- SECTION 8-->
  <p>
     <form:sectionContainer id="8" questionWidth="90%">
     <%=startIndent%>
           <form:question id="77"/>
           <!-- Informed Consent -->
           <form:question id="78"/>
           <%=endIndent%>
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
           <form:question id="79"/>
               <%=endIndent%>
     </form:sectionContainer>
<table width="100%">
          	<tr>
             	<td align="right"><a href="#sectionGlossary">Back to Top</a></td>
         	</tr>
      	</table>
    </p>
    <!-- SECTION Electronic signature -->
   <form:signatures/>
   </form>
</OTML:OFFLINE-FORM>


<% long end = System.currentTimeMillis(); %>
<% System.out.println("time to render smv: " + (end-start) + " ms"); %>