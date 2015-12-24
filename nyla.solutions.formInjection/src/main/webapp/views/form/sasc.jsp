<!--
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://gcsm.bms.com/tld/form.tld" prefix="form"%>
-->

<OTML:OFFLINE-FORM OFFLINE-ID="Site Closure Visit Form" 
    TITLE="<c:out value="${sessionScope.form.name}"/>">
    
    <head>
  
  <script>
  
  function showAlert(pIndex)
  {
  
  //alert(pIndex);
  
  if(pIndex==2)  
  alert("If Yes, Issue Tracking Form to be created");
  
  }
  
  function loadFn()
  {
      var question1 = document.getElementById('<form:inputNameForQuestion id="21"/>');
   
   
      question1.options[1].selected=true;
  }

  
  </script>
  </head>
  
  
    <BODY onLoad=loadFn()>
    
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
                    <jsp:include page="/views/form/menu.jsp"/>
	    </td>
         </tr>        
<!-- ======================================= -->               
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
		 <table width="95%" align=right border="0" cellpadding="3" cellspacing="0">
		 <form:questionaire styleClass="questionTextBold" questionWidth="40%" answerWidth="60%"  id="2" />
		 </table>
	</TD>
	
	

	</TR>	
	<tr>
		<td colspan="2" class="questionTextBold">&nbsp;</td>
	</tr>
	
<!-- ======================================================= -->

   <!-- SECTION 1 -->   
    <p> 
        <form:sectionContainer id="1" questionWidth="90%">
	<tr>
	<td colspan="2">
	<table width="95%" align="right" border="0" cellpadding="3" cellspacing="0">
	
		<tr>
			<td colspan="2">
			
			<form:questionaire id="3,4"  altStyleClass="bgLtGray" textStyleClass="indent" questionWidth="40%" answerWidth="60%" autoNumber="true"/>
			
			</td>
		</tr>
		
		<tr>
			<td colspan="2" class="questionTextBold">&nbsp;</td>
		</tr>
	
		<tr>
			<td class="questionTextBold">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Person Contacted</td>
		</tr>
		<tr>
			<td colspan="2">
			<table width="95%" align="right" border="0" cellpadding="3" cellspacing="0">
			<form:questionaire textStyleClass="indent" questionWidth="37%" answerWidth="63%" id="5,6,7,8,9" autoNumber="true"/>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="questionTextBold">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2" class="questionTextBold">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Address</td>
		</tr>
		<tr>
			<td colspan="2">
			
			<table width="95%" align="right" border="0" class="bgLtGray"  cellpadding="3" cellspacing="0">
			
			<form:questionaire altStyleClass="bgLtGray" textStyleClass="indent" questionWidth="37%" answerWidth="63%" id="10,11,12,13,14,15,16" autoNumber="true"/>
			
			
			</table>
			</td>
		</tr>
		
	
	</table>
	</td>
	</tr>
	</form:sectionContainer>  
               
    </p>
   <!-- ======================================================= -->    	     

   
   <!-- ======================================================= -->
  
   <!-- SECTION 2 -->       
    <p>
       <form:sectionContainer id="2" questionWidth="90%">
       
	
	<tr>
	<td colspan="2">
	<table border="0" cellpadding="3" cellspacing="0" width="95%" align=right>
	<form:question id="17" textStyleClass="indent" altStyleClass="bgLtGray" questionWidth="41%" answerWidth="59%" autoNumber="true"/>
	</table>
	</td>
	</tr>
	<tr>
	<td colspan="2">
	
	<div class="indent">
	
	<table border="0" cellpadding="3" cellspacing="0" width="91%" align="right">
	
		<form:question id="18" autoNumber="true"/>
	</table>
	
	
	</div>
	
	
	</td>
	</tr>
	<tr>	
	<td colspan="2">
	<table width="95%" align=right border="0" cellpadding="3" cellspacing="0">
	<form:questionaire questionWidth="41%" answerWidth="59%" textStyleClass="indent" altStyleClass="bgLtGray" id="19,20,21,22" autoNumber="true"/>
	</table>
	</td>
	</tr>
<%--	<tr>	--%>
<%--	<td colspan="2">--%>
<%--	<table width="95%" align=right border="0" cellpadding="3" cellspacing="0">--%>
<%--	<form:questionaire questionWidth="41%" answerWidth="59%" textStyleClass="indent" altStyleClass="bgLtGray" id="23" onChange="showAlert(this.selectedIndex)" autoNumber="true"/>--%>
<%--	</table>--%>
<%--	</td>--%>
<%--	</tr>--%>
	
       </form:sectionContainer>       
    </p>    
   <!-- ======================================================= -->    
        <form:signatures/>
      </form>

   </BODY>
   
     
</OTML:OFFLINE-FORM>