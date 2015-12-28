<!--<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://myfaces.sourceforge.net/tld/myfaces_ext_0_9.tld" prefix="m"%>
<%@ taglib uri="http://gcsm.bms.com/tld/smart_forms.tld" prefix="sf"%>-->

<f:view>
  <span class="sectionTitle">
  Potent Site Assessment
  </span>
   <h:commandButton action="${formAction.saveForm}" value="Save"/>
   <h:commandButton action="${formAction.submitForm}" value="Submit"/>
   <br/>
   <sf:sectionsTitle style="sectionTitle"/>
   <br/>
   <h:form action="#{formAction.saveForm}">
        Completed by
	  <!--First Name -->
         <sf:questionText id="1" style="whiteBG">
	     <f:attribute name="indent" value="1"/>
	 </sf:questionText> 
	  <!--Last Name -->
         <sf:questionText id="2" style="whiteBG">
	     <f:attribute name="indent" value="1"/>
	 </sf:questionText> 
        <!-- Date  -->
         <sf:questionDate id="4"/> 
	 <div class="yellowBG">1- Primary Contact</div>
	 <!-- First Name -->
         <sf:questionText id="5" style="whiteBG">
	     <f:attribute name="numberLabel" value="1-1"/>
	 </sf:questionText>
	 <!-- Last Name -->
         <sf:questionText id="6" style="whiteBG">
	     <f:attribute name="numberLabel" value="1-2"/>
	     <f:attribute name="indent" value="1"/>
	 </sf:questionText>
	 <!-- Title -->
         <sf:questionListBox id="6" style="whiteBG">
	     <f:attribute name="numberLabel" value="1-3"/>
	     <f:attribute name="indent" value="1"/>
	 </sf:questionListBox>
	 <!--Role --> 
         <sf:questionListBox id="7" style="whiteBG">
	     <f:attribute name="numberLabel" value="1-4"/>
	     <f:attribute name="indent" value="1"/>
	 </sf:questionListBox>
   </h:form>	 
</f:view>

