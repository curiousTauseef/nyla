<!--

1. Ship Date and return date must be in past. less then or equal to  todays date.
2. All quantities  Shipped and/ or transferred are "whole numbers" i.e  no decimals.
3. No specific formating required on batch # and serial #.
4. Totals , Totals Supplies at Site, Total Containers, and Sub totals  are disabled field (No user input) on DRW form

-->
<!--
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://gcsm.bms.com/tld/form.tld" prefix="form"%>
-->
<!-- question 2 totals -->
<form:useQuestion id="2000" var="q2000"/>
<form:useQuestion id="2001" var="q2001"/>
<form:useQuestion id="2002" var="q2002"/>
<form:useQuestion id="2003" var="q2003"/>
<form:useQuestion id="6" var="supplies"/>

  <!-- question 3 3- Total Supply Containers -->
  <form:useQuestion id="3000" var="q3000"/>
  <form:useQuestion id="3001" var="q3001"/>
  <form:useQuestion id="3002" var="q3002"/>
  
  
  <!-- question 4-1- Total Supply Containers -->
  <form:useQuestion id="4000" var="q4000"/>
  <form:useQuestion id="4001" var="q4001"/>
  <form:useQuestion id="4002" var="q4002"/>      
  <form:useQuestion id="4003" var="q4003"/>      
    <form:useQuestion id="4004" var="q4004"/>      
  <form:useQuestion id="8" var="csrInventory"/>
  

<script>
   function calculateDRW()
   {
      calculateSupplies();
      calculateCsrInventory();
   }//---------------------------------------------
   function calculateCsrInventory()
   {

         calculateCsrInventoryColumn(1, "Full",'<c:out value="${q4000.inputName}"/>');
         calculateCsrInventoryColumn(2, "Partial",'<c:out value="${q4001.inputName}"/>');
         calculateCsrInventoryColumn(3, "Empty",'<c:out value="${q4002.inputName}"/>');          
         calculateCsrInventoryColumn(4, "Missing",'<c:out value="${q4003.inputName}"/>'); 
         
         var tableId = "t10003q8";
         //var prefix = 'v_false_integer__0_10_q<c:out value="${csrInventory.primaryKey}"/>';
         var prefix = '<form:inputNameForColumn id="8" tableID="10003" columnNumber="1"/>';
         var tablePK = '10003';
         //tableId , aQuestionPrefix, aStartColumn, aSummaryColumn)
         sumRows(tableId,prefix, tablePK, 1, 5);
         
       csrInventoryTotals();      
   }//--------------------------------------------
   function csrInventoryTotals()
   {
       
        var colElement0 = document.getElementById('<c:out value="${q4000.inputName}"/>');
        var colElement1 = document.getElementById('<c:out value="${q4001.inputName}"/>');
        var colElement2 = document.getElementById('<c:out value="${q4002.inputName}"/>');
        var colElement3 = document.getElementById('<c:out value="${q4003.inputName}"/>');
        var total              = document.getElementById('<c:out value="${q4004.inputName}"/>');        
        
        total.value = colElement0.value*1 +colElement1.value*1+colElement2.value*1+colElement3.value*1;
   }//----------------------------------------------
   function calculateCsrInventoryColumn(aColumnNumber, aColumnName, aElementID)
   {
       var totalElement = document.getElementById(aElementID);              
       
      //example input name v_false_integer__0_10_q6[1][0];tablePK=10001;
     // var qPrefix = 'v_false_integer__0_10_q<c:out value="${csrInventory.primaryKey}"/>';
      var qPrefix = '<form:inputNameForColumn id="8" columnNumber="1" tableID="10003"/>';
      var aTablePK = '<c:out value="${csrInventory.formTable.primaryKey}"/>';
     sumColumn(aColumnNumber,totalElement,aColumnName,qPrefix,aTablePK);
 }//--------------------------------------------------------
   function calculateSupplies()
   {
       //v_false_date__0_2000_q6[0][0];tablePK=10001;
       // var shipDatePrefix = "v_false_date__0_2000_q<c:out value="${supplies.primaryKey}"/>";
        var shipDatePrefix = '<form:inputNameForColumn id="6" columnNumber="0" tableID="10001"/>';
        validateDateLessThanTodayColumn(0,"Ship Date", shipDatePrefix, '<c:out value="${supplies.formTable.primaryKey}"/>');
         calculateSupplyShippmentColumn(1, "Suppiles Shipped",'<c:out value="${q2000.inputName}"/>');
         calculateSupplyShippmentColumn(2, "Transferred to Site",'<c:out value="${q2001.inputName}"/>');
         calculateSupplyShippmentColumn(3, "Transferred OUT of Site",'<c:out value="${q2002.inputName}"/>');   
         calculateSupplyShippmentTotals(); 
   }//--------------------------------------------------

   function calculateSupplyShippmentTotals()
   {
         var supplyTotalsElement  = document.getElementById('<c:out value="${q2000.inputName}"/>' );              
         var toSiteTotalsElement  = document.getElementById('<c:out value="${q2001.inputName}"/>' );              
          var outOfSiteTotalsElement  = document.getElementById('<c:out value="${q2002.inputName}"/>' );   
          var totalAtSiteElement = document.getElementById('<c:out value="${q2003.inputName}"/>' );
          
          totalAtSiteElement.value =( supplyTotalsElement.value)*1 + (toSiteTotalsElement.value) * 1 
                                                        - (outOfSiteTotalsElement.value) *1;
          
   }//----------------------------------------------------
   //3- Total Containers
 function calculateSupplyContainers()
 {
         var validated = true;
         
         var supplyKitsElement  = document.getElementById('<c:out value="${q3000.inputName}"/>' );                          
         if(!isValidNumber("Total Supply Kits", supplyKitsElement, 1, 20, true))
             validated = false;
               
         var containerKeyElement  = document.getElementById('<c:out value="${q3001.inputName}"/>' );              
         if(!isValidNumber("# of Containers/Kit", containerKeyElement, 1, 20, true))
             validated = false;
                  
         var totalContainers  = document.getElementById('<c:out value="${q3002.inputName}"/>' );
                      
         if(validated)
         {
              totalContainers.value = (supplyKitsElement.value * 1)  * (containerKeyElement.value * 1);
          }
 }//-------------------------------------------------
   function calculateSupplyShippmentColumn(aColumnNumber, aColumnName, aElementID)
   {
       var totalElement = document.getElementById(aElementID);              
       
      //example input name v_false_integer__0_10_q6[1][0];tablePK=10001;
     // var qPrefix = 'v_false_integer__0_10_q<c:out value="${supplies.primaryKey}"/>';
      var qPrefix = '<form:inputNameForColumn id="6" columnNumber="1" tableID="10001"/>';
      var aTablePK = '<c:out value="${supplies.formTable.primaryKey}"/>';
     sumColumn(aColumnNumber,totalElement,aColumnName,qPrefix,aTablePK);
 }//--------------------------------------------------------
   function saveDRW(aForm)
  {
     calculateDRW();  
     
     saveForm(aForm);
  }//---------------------------------------------------------------
  function signDRW(aForm)
  {
     calculateDRW();       
     
     if(!validateDRW(aForm))
     {
        return false;
     }
     
     return signForm(aForm);
  }//---------------------------------------------------------------
  function validateDRW(aForm)
  {
     var bool = true;
     var shipDatePrefix = '<form:inputNameForColumn id="6" columnNumber="0" tableID="10001"/>';
     if(!validateDateLessThanTodayColumn(0,"Ship Date", shipDatePrefix, '<c:out value="${supplies.formTable.primaryKey}"/>'))
     {
        bool =  false;
     }
     
     var dorPrefix  =  '<form:inputNameForColumn id="8" columnNumber="0" tableID="10003"/>';
     if(!validateDateLessThanTodayColumn(0,"Date of Return", dorPrefix, '<c:out value="${csrInventory.formTable.primaryKey}"/>'))
     {
        bool =  false;
     }
     
     return bool;
  }//---------------------------------------------------------------
</script>
<OTML:OFFLINE-FORM OFFLINE-ID="Drug Reconciliation Worksheet" TITLE="Drug Reconciliation Worksheet">
   <form id="aForm" name="aForm" action="<c:url value="/do/formAction"/>" method="POST">
   <table class="formHeader" width="100%" cellpadding="0">
        <tr>
            <td>
            <!-- Form Type Name -->
	       <c:out value="${sessionScope.form.name}"/><!-- FORM_ID: <c:out value="${form.primaryKey}"/> -->
              <input type="hidden" name="op" value=""/>
               <input type="hidden" name="formID" value="<c:out value="${form.primaryKey}"/>"/>
               <input type="hidden" name="siteID" value="<c:out value="${param.siteID}"/>"/>               
	     </td>
  	    <td align="right">
     	         <!-- REVIEWER PK: <c:out value="${form.reviewerPK}"/>   -->
     	         <!-- STATUS NM <c:out value="${form.status.name}"/>   -->
                 <form:guard checkPoint="edit">
     	                         <input type="button" value="   Edit   " onclick="renewForm(aForm);" />
     	           </form:guard>          	         
     	          <form:guard checkPoint="complete">
     	             <input type="button" value="Complete" onclick="signCompletionForm(aForm);" />
     	           </form:guard>
   	        <form:guard>
          	           <input type="button" value="Save" onclick="saveDRW(aForm);" />
               	               <input type="button" value="Delete" onclick="deleteForm(aForm);" />
                   	            <input type="button" value="Submit" onclick="signDRW(aForm);"/>
        	       </form:guard>
   	       </td>
         </tr>
<!-- ======================================= -->               
   <!-- Save Confirmation -->
    <jsp:include page="/views/form/save_confirmation.jsp"/>
<!-- ======================================= -->                
</table>
<!-- ======================================================= -->
      <table width="100%" border="0" cellspacing="0">
      	<tr>
      		<td colspan="3">&nbsp;</td>
	</tr> 
         <form:questionaire id="2,3,4" questionWidth="20%" textStyleClass="questionTextBold" answerWidth="80%"/>
		 <tr><td colspan="3">&nbsp;</td></tr>
         <form:question id="5" questionWidth="20%" textStyleClass="questionTextBold" answerWidth="80%"/>
	  </table>
<p>
<!-- question 2 totals -->
   <table width="100%">
    <!--form:question id="6" questionWidth="20%" answerWidth="80%" textStyleClass="questionTextSmall"  answerStyleClass="answer" /-->
	        		<form:table id="6" autoNumber="false" template="vertical_table" 
	        		                        onChange="calculateSupplies();" onclick="calculateSupplies();">
	        			<form:row template="vertical_table_rows">		            	
							<form:column>
					                <td id="answer">${answer}</td>
							</form:column>   
	        			</form:row>	        			   	
	        <form:footer>
                <c:if test="${!form.readOnly}">
                     <tr class="bgLtGray">
                        <td id="footer" class="questionTextBold">Totals </td>                    
                        <td><input  onfocus="blur();"  id="<c:out value="${q2000.inputName}"/>" class="answer" maxlength="20" type="text" name="<c:out value="${q2000.inputName}"/>" value="<c:out value="${q2000.answer.value}"/>"/></td>
                        <td><input  onfocus="blur();" id="<c:out value="${q2001.inputName}"/>" class="answer" maxlength="20"type="text"  name="<c:out value="${q2001.inputName}"/>" value="<c:out value="${q2001.answer.value}"/>"/></td>
                       <td><input  onfocus="blur();" id="<c:out value="${q2002.inputName}"/>" class="answer" maxlength="20" type="text"  name="<c:out value="${q2002.inputName}"/>" value="<c:out value="${q2002.answer.value}"/>"/></td>
                       <td><c:out value="${q2003.text}"/> </td>
                       <td><input  onfocus="blur();"  id="<c:out value="${q2003.inputName}"/>" class="answer" maxlength="20" type="text"  name="<c:out value="${q2003.inputName}"/>" value="<c:out value="${q2003.answer.value}"/>"/></td>              
                     </tr>                       
                </c:if>            
                <c:if test="${form.readOnly}">
                     <tr class="bgLtGray">
                        <td class="questionTextBold">Totals: </td>            
                        <td><c:out value="${q2000.answer.value}"/></td>
                        <td><c:out value="${q2001.answer.value}"/></td>
                        <td> <c:out value="${q2002.answer.value}"/></td>
                       <td><c:out value="${q2003.text}"/> </td>     
                        <td> <c:out value="${q2003.answer.value}"/></td>               
                     </tr>       
                </c:if>  	 	   	        		
	        </form:footer>
    </form:table> 
</p>

<!-- question 3 3- Total Supply Containers -->
<p>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
       <tr class="questionTableHeader"><td colspan="6">3- Total Containers</td></tr>
       <tr class="questionTableHeader"><td class="question"><c:out value="${q3000.text}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;X</td><td class="question"><c:out value="${q3001.text}"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; =</td><td class="questionTextBold"> <c:out value="${q3002.text}"/></td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
       <c:if test="${!form.readOnly}">
            <tr>
               <td><input class="answer"  onChange="calculateSupplyContainers();" onclick="calculateSupplyContainers();" type="text" id="<c:out value="${q3000.inputName}"/>" name="<c:out value="${q3000.inputName}"/>" value="<c:out value="${q3000.answer.value}"/>"/></td>
               
               <td><input class="answer"  onChange="calculateSupplyContainers();" onclick="calculateSupplyContainers();" type="text" id="<c:out value="${q3001.inputName}"/>"  name="<c:out value="${q3001.inputName}"/>"value="<c:out value="${q3001.answer.value}"/>"/></td>
               
              <td><input class="answer" onfocus="blur();"  type="text"   id="<c:out value="${q3002.inputName}"/>" name="<c:out value="${q3002.inputName}"/>" value="<c:out value="${q3002.answer.value}"/>"/></td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              
             </tr>
             <!-- --> 
            <tr>
               <td><span id="<c:out value="${q3000.inputName}"/>Error" class="error"></span></td>
               <td><span id="<c:out value="${q3001.inputName}"/>Error" class="error"></span></td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
	      <td>&nbsp;</td>
	      <td>&nbsp;</td>
              
             </tr>             
       </c:if>            
       <c:if test="${form.readOnly}">
            <tr>
               <td><c:out value="${q3000.answer.value}"/></td>
               
               <td><c:out value="${q3001.answer.value}"/></td>               
               <td><c:out value="${q3002.answer.value}"/></td>
               <td>&nbsp;</td>
               <td>&nbsp;</td>
               <td>&nbsp;</td>
            </tr>       
       </c:if>                  
    </table>
</p>
<!-- question 4 -->
<p>
	        		<form:table id="8" autoNumber="false" template="vertical_table" 
	        		                        onChange="calculateCsrInventory();" onClick="calculateCsrInventory();">
	        			<form:row template="vertical_table_rows">		            	
							<form:column>
					                <td id="answer">${answer}</td>
							</form:column>   
	        			</form:row>	        			   	
	        <form:footer>
                <c:if test="${!form.readOnly}">
                     <tr class="bgLtGray">
                        <td id="footer" class="questionTextBold">Sub-Totals</td>                    
                        <td><input  onfocus="blur();"  id="<c:out value="${q4000.inputName}"/>" class="answer" maxlength="20" type="text" name="<c:out value="${q4000.inputName}"/>" value="<c:out value="${q4000.answer.value}"/>"/></td>
                        <td><input  onfocus="blur();" id="<c:out value="${q4001.inputName}"/>" class="answer" maxlength="20"type="text"  name="<c:out value="${q4001.inputName}"/>" value="<c:out value="${q4001.answer.value}"/>"/></td>
                       <td><input  onfocus="blur();" id="<c:out value="${q4002.inputName}"/>" class="answer" maxlength="20" type="text"  name="<c:out value="${q4002.inputName}"/>" value="<c:out value="${q4002.answer.value}"/>"/></td>
                       <td><input  onfocus="blur();" id="<c:out value="${q4003.inputName}"/>" class="answer" maxlength="20" type="text"  name="<c:out value="${q4003.inputName}"/>" value="<c:out value="${q4003.answer.value}"/>"/></td>
                       <td><input  onfocus="blur();" id="<c:out value="${q4004.inputName}"/>" class="answer" maxlength="20" type="text"  name="<c:out value="${q4004.inputName}"/>" value="<c:out value="${q4004.answer.value}"/>"/></td>
                    </tr>                       
                </c:if>            
                <c:if test="${form.readOnly}">
                     <tr class="bgLtGray">
                        <td class="questionTextBold">Sub-Totals: </td>            
                        <td><c:out value="${q4000.answer.value}"/></td>
                        <td><c:out value="${q4001.answer.value}"/></td>
                        <td> <c:out value="${q4002.answer.value}"/></td>
                       <td><c:out  value="${q4003.answer.value}"/> </td>   
                        <td> <c:out value="${q4004.answer.value}"/></td>               
                     </tr>
                </c:if> 	   	        		
	        </form:footer>
    </form:table>    
</p>
<p>
   <!-- Comment -->
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <form:question id="9" questionWidth="30%" answerWidth="80%"  styleClass="questionTextBold" />
    </table>
</p>
<p>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr> <td class="note">* Note</td>
       <td> If there is a difference of >=5% between Total containers in question 3 &amp; 4
       <br> then describe the discrepancy in General comments field.
       </td>
  </tr>
  </table>
</p>
   <form:signatures/>
   </form>
</OTML:OFFLINE-FORM>
<script>
   <c:if test="${not form.readOnly}">
       calculateDRW();
    </c:if>
</script>