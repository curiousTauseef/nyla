<!--
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://gcsm.bms.com/tld/form.tld" prefix="form"%>
-->
<% long start = System.currentTimeMillis(); %>
<script>
   function reconcileForm(aForm)
   {
      clearConfirmation();
      
        if(aForm.action.indexOf("op") < 0)
        {
           // aForm.action = aForm.action+"?op=reconcile";
         }
        
        aForm.op.value = "reconcile";
        
        //alert(' aForm.op.value='+ aForm.op.value);    
        if(validate(aForm))
        {
            aForm.submit();
            return true;
          }
          else
          {
             printConfirmation('<span class=error>THE FORM HAS NOT BEEN RECONCILED.</span>');
          }
          
          return false;
   }//------------------------------
</script>
<OTML:OFFLINE-FORM OFFLINE-ID="Potential Site Assessment Form" 
    TITLE="<c:out value="${sessionScope.form.name}"/>">

    <form id="aForm" name="aForm" action="<c:url value="/do/OSIFFormAction"/>" method="POST">
   
   <table class="formHeader" width="100%" cellpadding="0">
        <tr>
            <td>
            <!-- Form Type Name -->
	       <c:out value="${sessionScope.form.name}"/> <c:out value="${sessionScope.form.classification}"/><!-- <c:out value="${form.primaryKey}"/> -->
               <input type="hidden" name="op" value="save"/>
               <input type="hidden" name="formID" value="<c:out value="${form.primaryKey}"/>"/>
               <input type="hidden" name="filed" value="<c:out value="${form.filed}"/>"/>      
                 <!-- OP= <c:out value="${param.op}"/> -->
	     </td>
	    <td align="right">
     	        <form:guard>
         	       <input type="button" value="Save" onclick="saveForm(aForm);" />
         	       <input type="button" value="Reconcile" onclick="reconcileForm(aForm);"/>
       	        </form:guard>
	       </td>
         </tr>        
         
         <tr  class="sectionHeader">         
            <td colspan="2"><form:sectionGlossary id="1,2,3,4,5,6,7,8,9"/></td>
         </tr>
      <!-- Save Confirmation -->
          <jsp:include page="/views/form/save_confirmation.jsp"/>
      <!-- ======================================= -->                
   </table>
   <!-- ======================================================= -->
   
   <!-- SECTION 0 -->
   
   
<table width="100%" border="0" cellspacing="0" cellpadding="3">
	
	<tr><td>&nbsp;</td></tr>
	<tr>
	
		 <TD>
			 <table width="95%" align=right border="0" cellpadding="3" cellspacing="0">
			 <form:questionaire styleClass="questionText" questionWidth="40%" answerWidth="60%"  id="1,2,3,4,5" />
			 </table>
		</TD>

	</TR>	 
	     

   
   <!-- ======================================================= -->
   <!-- SECTION 1 -->   
    <p> 
        <form:sectionContainer id="1" questionWidth="90%">
	<tr>
	<td colspan="2">
	<table width="95%" align="right" border="0" cellpadding="3" cellspacing="0">
	
	
		<tr>
			<td colspan="2">					
			
			<form:question id="6"/>			
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<table  class="questionGrayBG" width="100%" align="right" border="0" cellpadding="3" cellspacing="0">
			<form:questionaire questionWidth="40%" answerWidth="60%" id="10"/>
			</table>
			</td>
		</tr>
		
		<tr>
			<td colspan="2">
			<table  class="questionGrayBG" width="100%" align="right" border="0" cellpadding="3" cellspacing="0">
			<form:questionaire questionWidth="40%" answerWidth="60%" id="11"/>
			</table>
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
   <!-- SECTION 2 -->       
    <p>
       <form:sectionContainer id="2" questionWidth="90%">
	
               	<tr>
               		<td colspan="2"  align="right">
               		<table border="0" cellpadding="0" cellspacing="0" width="95%">
                  		<tr>
               	         <td>		
                   		      <form:question id="12" styleClass="questionGrayBG"/>
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
   <!-- SECTION 3 -->       
 <p>
 
 <form:sectionContainer id="3" questionWidth="90%">
	<tr>
		<td colspan="2">
		<table border="0" cellpadding="3" cellspacing="0" width="95%" align="right">
		<tr>
			<td colspan="2">
			<table  class="questionGrayBG" border="0" cellpadding="0" cellspacing="0" width="100%">
			<form:question id="13"/>
			</table>
			</td>
		</tr>
		<!-- Removed question 14 -->	
		
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
   <!-- SECTION 4 --> 
    <p>
	<form:sectionContainer id="4" questionWidth="90%">
	
	<tr>
   	<td colspan="2" align=right>
   	   	<form:question  id="15" styleClass="questionGrayBG"/>
   	</td>
	</tr>	

		
	<tr>
		<td colspan="2">
		<table border="0" cellpadding="3" cellspacing="0" width="95%" align="right">
		<tr>
			<td colspan="2">
			<table border="0" cellpadding="0" cellspacing="0" width="95%" align="right">
			<form:question id="16"/>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<table  border="0" cellpadding="0" cellspacing="0" width="100%">
			<form:question id="17"/>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<table  border="0" cellpadding="0" cellspacing="0" width="100%">
			<form:question id="18"/>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<table  border="0" cellpadding="0" cellspacing="0" width="100%">
			<form:question id="19"/>
			</table>
			</td>
		</tr>
				

		</table>
		</td>
	</tr>
	
	<tr>
		<td colspan="2">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<form:question id="20"/>
		</table>
		</td>
	</tr>
	
	<tr>
		<td colspan="2">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<form:question id="21"/>
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
   <!-- SECTION 5 --> 
  <p>
        <form:sectionContainer id="5" questionWidth="90%">
	<tr>
	<td colspan="2">
	<table border="0" cellpadding="3" cellspacing="0" width="95%" align="right">
	<tr>
	<td colspan="2">
	<div class="indent">
      	<form:question  id="22"/>
	</div>
	</td>
	</tr>
	
	<tr>
		<td colspan="2">
		
		<form:question  id="24" styleClass="bgLtGray"/>
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
   <!-- SECTION 6 --> 
   
  <p>
	<form:sectionContainer id="6" questionWidth="90%">
	
	<tr>
		<td colspan="2" align=right>
		<form:question  id="25" styleClass="questionGrayBG"/>		
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
   <!-- SECTION 7--> 
 <p>
        <form:sectionContainer id="7" questionWidth="90%">
        
        <tr>
	<td colspan="2">
	<table border="0" cellpadding="3" cellspacing="0" width="95%" align="right">
	<tr>
	
	<tr>
		<td colspan="2">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<form:questionaire questionWidth="40%" answerWidth="60%" id="26"/>
		</table>
		</td>
	</tr>
	<tr>
		<td colspan="2">
		<table border="0" cellpadding="0" cellspacing="0" width="95%" align=right>
		<form:questionaire questionWidth="37%" answerWidth="63%" id="27,28"/>
		</table>
		</td>
	</tr>
	
	<tr>
	<td colspan="2">

	<table border="0" cellpadding="3" cellspacing="0" width="100%" class="bgLtGray">
	
	
	<tr>
		<td colspan="2">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<form:questionaire questionWidth="40%" answerWidth="60%" id="29"/>
		</table>
		</td>
	</tr>
	<tr>
		<td colspan="2">
		<table border="0" cellpadding="0" cellspacing="0" width="95%" align=right>
		<form:questionaire questionWidth="37%" answerWidth="63%" id="30"/>
		</table>
		</td>
	</tr>
	<tr>
		<td colspan="2">
		<table border="0" cellpadding="0" cellspacing="0" width="95%" align=right>
		<form:questionaire questionWidth="37%" answerWidth="63%" id="31"/>
		</table>
		</td>
	</tr>
	
	</table>
	
	</td>
	</tr>
	
	
	<tr>
		<td colspan="2">
		<table border="0" width="100%" cellpadding="0" cellspacing="0">
		<form:questionaire questionWidth="40%" answerWidth="60%" id="32"/>
		</table>
		</td>
	</tr>
	<tr>
		<td colspan="2">
		<table border="0" width="100%" class="bgLtGray"  cellpadding="0" cellspacing="0">
		<form:questionaire questionWidth="40%" answerWidth="60%" id="33"/>
		</table>
		</td>
	</tr>
	<tr>
		<td colspan="2">
		<table border="0" width="100%" cellpadding="0" cellspacing="0">
		<form:questionaire questionWidth="40%" answerWidth="60%" id="34"/>
		</table>
		</td>
	</tr>
	<tr>
		<td colspan="2">
		<table border="0" cellpadding="0" cellspacing="0" width="95%" align=right>
		<form:questionaire questionWidth="37%" answerWidth="63%" id="35"/>
		</table>
		</td>
	</tr>
	<tr>
		<td colspan="2" class="bgLtGray">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<form:questionaire questionWidth="40%" answerWidth="60%" id="36"/>
		</table>
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
   <!-- SECTION 8--> 
   <p>
	<form:sectionContainer id="8" questionWidth="90%">	
	
	<tr>
	<td colspan="2">
	
		<table border="0" cellpadding="3" cellspacing="0" width="95%" align="right">
		
		
		<tr>
			<td colspan="2">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<form:questionaire questionWidth="40%" answerWidth="60%" id="37"/>
			</table>
			</td>
		</tr>
		
		<tr>
			<td colspan="2">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<form:questionaire questionWidth="40%" answerWidth="60%" id="38"/>
			</table>
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
  	
	<tr>
	<td colspan="2">
	
		<table border="0" cellpadding="3" cellspacing="0" width="95%" align="right">
  	
  	
	<tr>
		<td colspan="2">
		<table class="questionGrayBG" border="0" cellpadding="0" cellspacing="0" width="100%">
		<form:questionaire questionWidth="40%" answerWidth="60%" id="39"/>
		</table>
		</td>
	</tr>

	<tr>
		<td colspan="2">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<form:questionaire questionWidth="40%" answerWidth="60%" id="40"/>
		</table>
		</td>
	</tr>

	<tr>
		<td colspan="2">
		<table class="questionGrayBG" border="0" cellpadding="0" cellspacing="0" width="100%">
		<form:questionaire questionWidth="40%" answerWidth="60%" id="41"/>
		</table>
		</td>
	</tr>
	<tr>
		<td colspan="2">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<form:questionaire questionWidth="40%" answerWidth="60%" id="42"/>
		</table>
		</td>
	</tr>

	<tr>
		<td colspan="2">
		<table class="questionGrayBG" border="0" cellpadding="0" cellspacing="0" width="100%">
		<form:questionaire questionWidth="40%" answerWidth="60%" id="43"/>
		</table>
		</td>
	</tr> 		
  	
  	<tr>
		<td colspan="2">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<form:questionaire questionWidth="40%" answerWidth="60%" id="44"/>
		</table>
		</td>
	</tr>

	<tr class="questionGrayBG">
	<td colspan="2">
	<table>
	
	<tr>
	   	<td colspan="2">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<form:questionaire questionWidth="40%" answerWidth="60%" id="45"/>
		</table>
		</td>
	</tr>
		<tr>
		<td colspan="2">
		<table border="0" cellpadding="0" cellspacing="0" width="95%" align=right>
		<form:questionaire questionWidth="37%" answerWidth="63%" id="46"/>
		</table>
		</td>
	</tr>
	</table>
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

   </form>
   
</OTML:OFFLINE-FORM>

<% long end = System.currentTimeMillis(); %>
<% System.out.println("time to render osif: " + (end-start) + " ms"); %>