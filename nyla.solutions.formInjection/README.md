This library is a legacy framework generic forms.
This project is currently under construction.

#Form Injection Overview

##Introduction
If you have ever been to the DMV, a doctor’s office, or any other place business, than you have probably filled out some paper form. The information that the form requested may have been different base on the place of business, but the concept is the same. A key purpose of a form is capable some information in structured format.

Electronic versions of forms have existed as long as computers. It does matter the software is used in the telecommunication, financial, industry, or pharmaceutical industry. Someone will evidently to enter data in structured format. 

**Dependency Injection**

The form injection provides the ability to retrieve information such as the form’s answers and author. Each form has a type. The form type determines the questions that are associated to the form.
 

**Form Presentation**
The FormView using a set of custom JSP tags to render the form’s information and questions.
 	

	Sample Form View JSP code
	<!--
	<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
	<%@ taglib uri="http://Form Injection.nyla.com/tld/form.tld" prefix="form"%>
	-->
	   <form id="aForm" name="aForm" action="<c:url value="/do/formAction"/>" method="POST">
	   <table class="formHeader"width="100%" cellpadding="0">
	         <tr  class="sectionHeader">
	            <td colspan="2"><form:sectionGlossary id="1,2,3,4,5,6,7,8,9"/></td>
	         </tr>         
	   </table>
	   <!-- ======================================================= -->
	   <!-- SECTION 0 -->
	   <p>
	      <table width="100%" border="0" cellspacing="0" cellpadding="3">
	         <!-- Preparer -->
	         <form:questiona  id="1" questionWidth="20%" answerWidth="80%"/>
	      </table>
	   </p>
	   <!-- ======================================================= -->
	    <!-- SECTION 1 -->
	     <p>
	        <form:sectionContainer id="1">
	            <tr>
	            <td class="indent">
	            <table width="95%">
	                <form:question id="2"/>
	                <tr>
	                    <td colspan="2" style="height: 12px;"></td>
	                </tr>
	            </table>
	            </td>
	            </tr>
	        </form:sectionContainer>
	    </p>
	   </form>


Sample Form View JSP code provides an example on how the form is rendered. The JSP code contains a mixture of static HTML content in addition to using some custom JSP tags for presenting form user interface components such as section headers, sections, and questions.

In order to use the custom form tags the JSP code must reference the tag library by including the following text; <%@ taglib uri="http://Form Injection..com/tld/form.tld" prefix="form"%>. 
The prefix “form” tells the J2EE application server’s JSP Servlet engine that any tag that begins with the prefix “form” references the form’s custom component. This example used the sectionGlossary, sectionContainer and question custom tags. 

The sectionGlossary displays the form’s sections where each section (Primary Contact, Investigator, Site and Past Research Experience in this example) is rendered as hyper-links to corresponding <form:sectionContainer> tag (rendered as a hyper-link anchor).
Figure 3 9 - Sample Section Headers Output
 
In following example the <form:sectionContainer id=”1”/> tag is included at the beginning the questions for the section “Primary Contact”. For example the “Primary Contact” will have an HTML href anchor to the section similar to the following;


**Sample Output for Section Header “Primary Contact” JSP Code	HTML Output**

	<form:sectionGlossary  id=”1”>	<A href="#section1" class=” sectionTitle”>Primary Contact</A>
	                …
	<form:sectionContainer id="1" />
		<A name="section1" class=”yellowBG”>Primary Contact</A>
	<!-- Questions in section 1-->
	…

The question tag displays the HTML version of questions and theirs corresponding answers. 

The versioning of forms and their corresponding questions/answers are based on the form’s type. All forms are associated with a particular form type and its questions. Each type may have one or more versions (see section 2.2).

When creating a new form the highest version number of the type (with its questions) is retrieved and associated with the form. The form’s associated type/version cannot be altered after the form is initially created.

For example, if there two versions of the form type (version 1 and 2), when a user schedules an visit after the second form type/questions have been added in the database, then the form will used  type version 2 and all of its questions. All  forms created prior to the addition of version 2 are associated with version 1.

A new version of a form type should always be added if changes to questions (such as the question’s text) are needed. This will allow new versions of created forms to be introduced without affecting previously submitted and or completed forms. 

The form controller logic knows what view handles which version of the form type. The forward mapping between JSP and form type versions are stored in the form management’s  configuration file. The action forward name has the following format: ${formTypeCode}.version.${formTypeVersionNumber}. New versions of the JSPs and form layouts may be introduced by modifying this file.
Figure 3 13 –  Form Action Forward Mapping (Auto Versioning)
<action name="formAction" path="/formAction" type="nyla.solutions.formInjection.Form Injection.form.web.FormAction" parameter="op" validate="true">
    <forward name="PE.version.1" path="nyla.Form Injection.form.pe.layout"/>	
     <forward name="SMV.version.1" path="nyla.Form Injection.form.smv.layout"/>				
    <forward name="PD.version.1" path="nyla.Form Injection.form.pd.layout"/>	
    <forward name="SMP.version.1" path="nyla.Form Injection.form.smp.layout"/>			
    <forward name="PSE.version.1" path="nyla.Form Injection.form.pse.layout"/>	
    <forward name="PSA.version.1" path="nyla.Form Injection.form.psa.layout"/>
    <forward name="DRW.version.1" path="nyla.Form Injection.form.drw.layout"/>
    <forward name="OSIF.version.1" path="nyla.Form Injection.form.osif.layout"/>
    <forward name="SCV.version.1" path="nyla.Form Injection.form.scv.layout"/>			
    <forward name="SIV.version.1" path="nyla.Form Injection.form.siv.layout"/>
     <forward name="SASC.version.1" path="nyla.Form Injection.form.sasc.layout"/>            	       
</action>


 
Only authorized users can view a form. The form’s originator, reviewer, approver, or users associate with the sites or studies are the only authorized users. The present component is responsible for authorization of form access based on the information retrieved from the FormService module.

##Questionnaire 

Form Management Questions/Response Output Descriptions


Response Type        |          Description
-------------        |          -------------- 
TEXT INTEGER CURRENCY DATE, PERCENTAGE |	Questions with a TEXT, INTEGER, CURRENCY, DATE, and PERCENTAGE response type are rendered with an HTML “input” of type “text”.
 
HIDDEN       | 	Questions with a HIDDEN response type are rendered with an HTML “input” of type “hidden”. <input type=”hidden value=”XXX”/>
TEXTAREA     |	Questions with a TEXTAREA response type are rendered with an HTML “textarea”.
DATE        |	Questions with a DATE response type are rendered with an HTML “input” of type “text”. An image icon displayed next to the input text field links to a calendar popup window. The input date field is populated with the selected date from calendar popup when the user clicks submit. All dates are displayed as ‘DD-MMM-YYYY,’ e.g. 17-MAR-2005.

LISTBOX   | 	Questions with a LISTBOX response type are rendered with an HTML “select” with a HTML “option” tag from each question option. 
 
RADIOBOXES   |	Questions with a RADIOBOXES response type are rendered with an HTML “input” with type “radio” for each question option. 

CHECKBOXES   |	Questions with a CHECKBOXES response type are rendered with an HTML “input” with type “checkbox” for each question option.
 

TABLE       |	Questions with a TABLE response type are rendered with an HTML “table”.
In the following example, “Specialty” and “Areas of interest” are RESPONSE COLUMNS. Each response column has a RESPONSE TYPE (such as TEXT, LISTBOX, etc. The input field in each row for these columns following standard displays conventions for the response type. Here “Specialty” and “Areas of interest” have a LISTBOX response type. The “Add” button calls a custom “addRow” JavaScript function that uses DHTML to dynamically add a row “TR” at the end of the “table”. Each added row has an “x” icon that allows the user to be deleted it as show below.

VERTICAL TABLE |	Questions with a VERTICALTABLE response type can be considered the same as a normal table except that all columns are display on separates line (i.e. vertically). The “add” button calls a custom “addVerticalRow” JavaScript function that uses DHTML to dynamically add a row “TR” at the end of the “table”. In this example the “first name”, “last name” and “title” will be repeatedly showed under the last “Title” row.

 
MULTI-SELECT-LISTBOX ||	Allow for one or more items to be selected in a list box style. 
 

#Form Rule Engine

The rule engine is a set of logical expressions, operations, and rules related to questions. The operations rules only works for server-side processing. 
The Business Rule Engine (BRE) displayed in the Figure 3 1 - Business Rule Engine Class Diagram controls what operations/scripts are executed on questions when certain logical expressions are evaluated to true or false. Note that client side processing is handled by web browser scripts (JavaScript) see the question JSP tag in section 3.2. 
  

 Business Rule Engine Classes
 
Object    |   	Description
------    |   	------------
Operation | 	An operation represents an action to be performed of a question within a form. The operation concept is based on the command design pattern. Operations implements an execute method. It contains custom logic to satisfy the needed business processing.

Business operations | deal with processing related to the retrieving data from the database and general logic that can only be performed on the server. For example, a business operation can populate a question on the current form with an answer a previous submitted form. Scripts should be used for client-side processing or offline form handling. Business rules provide a link between expressions and operations. Operations are executed when the form is retrieved for editing (in order to auto fill question answers see section 2.2 RESPONSE_TYPE.RESPONSE_OPERATION_ID) and when saving and submitting a form. Note that the complete list of operations will be maintained in a separate document (see FORM INJECTION Form Questionnaire spreadsheet). 

Rule				|  The rule object is used to make the link between expressions and operations for questions. The rule will execute an operation if a given logical expression is true or false. 
BRE	This is the rule engine that contains all expressions, rules and operations/scripts to be performed and passed on to the  form questions., In order to reduce database accessing and duplication of question rule information in memory, the BRE object is implemented as a singleton.
LogicalExpression	Logical expression provides an interface to determine whether a question or set of questions match a configured criteria. This design is based on the interpreter design pattern.  Each object that implements this interpret interface must return a boolean result of true or false. AND, OR, NOT, greater than, and less than are examples of  logical expressions.

Logical expressions |	are objects implementing an abstract method that states whether the expression is true or false. 

 
Object			|	 	Description
---------		|		-----------------
TrueExpression	|	Expression always true
HasAnswerExpression	|	Interprets to true if a question has been answered.
OptionExpression	|	Interprets to true if a question has an option equal with a given answer type
NotExpression		| Interprets to true if a given logical expression is false.
OrExpression		| Interprets to true if one of two given expression is true.
AndExpression		| Interprets to true if two given expression are both true.
RelationalExpression	|	Returns the result of a relational operation of two questions’ values. Relational expressions are provided with two input arguments and an operation. The supported operands are “>”, “>”, “=”, “>=” or “<=”. Relational expressions can only be used for questions that have a numerical response type (integers, float, etc.).
 
Architecture



#Building Notes

##Install geode into local repository

Create and execute a script with the following to install 
geode into your local repository

mvn install:install-file -Dfile=/Projects/Pivotal/dev/incubator-geode/gemfire-assembly/build/install/apache-geode/lib/gemfire-core-1.0.0-incubating-SNAPSHOT.jar -DgroupId=org.apache.geode   -DartifactId=gemfire-core -Dversion=1.0.0-incubating-SNAPSHOT -Dpackaging=jar

mvn install:install-file -Dfile=/Projects/Pivotal/dev/incubator-geode/gemfire-assembly/build/install/apache-geode/lib/gemfire-json-1.0.0-incubating-SNAPSHOT.jar -DgroupId=org.apache.geode   -DartifactId=gemfire-core -Dversion=1.0.0-incubating-SNAPSHOT -Dpackaging=jar

mvn install:install-file -Dfile=/Projects/Pivotal/dev/incubator-geode/gemfire-assembly/build/install/apache-geode/lib/gemfire-core-dependencies.jar	-DgroupId=org.apache.geode   -DartifactId=gemfire-core -Dversion=1.0.0-incubating-SNAPSHOT -Dpackaging=jar

mvn install:install-file -Dfile=/Projects/Pivotal/dev/incubator-geode/gemfire-assembly/build/install/apache-geode/lib/gemfire-web-1.0.0-incubating-SNAPSHOT.jar -DgroupId=org.apache.geode   -DartifactId=gemfire-core -Dversion=1.0.0-incubating-SNAPSHOT -Dpackaging=jar

mvn install:install-file -Dfile=/Projects/Pivotal/dev/incubator-geode/gemfire-assembly/build/install/apache-geode/lib/gemfire-jgroups-1.0.0-incubating-SNAPSHOT.jar	 -DgroupId=org.apache.geode   -DartifactId=gemfire-core -Dversion=1.0.0-incubating-SNAPSHOT -Dpackaging=jar

mvn install:install-file -Dfile=/Projects/Pivotal/dev/incubator-geode/gemfire-assembly/build/install/apache-geode/lib/gemfire-web-api-1.0.0-incubating-SNAPSHOT.jar -DgroupId=org.apache.geode   -DartifactId=gemfire-core -Dversion=1.0.0-incubating-SNAPSHOT -Dpackaging=jar

mvn install:install-file -Dfile=/Projects/Pivotal/dev/incubator-geode/gemfire-assembly/build/install/apache-geode/lib/gemfire-joptsimple-1.0.0-incubating-SNAPSHOT.jar -DgroupId=org.apache.geode   -DartifactId=gemfire-core -Dversion=1.0.0-incubating-SNAPSHOT -Dpackaging=jar