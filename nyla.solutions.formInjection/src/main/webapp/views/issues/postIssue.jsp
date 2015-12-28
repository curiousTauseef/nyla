<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<head>
<style type="text/css">
  .label, .data {
	  font-family:Verdana;
	  font-size:10pt;
	  padding-left:5px;
	  width:110px;
	  text-align:left;
	  vertical-align:top;
	  font-weight:bold;
  }

  .data { width:97%; font-weight:normal; padding:none;}

  .PopupHeader { background-color:#FFFF99; font-family:Verdana; font-size:14px; padding-left:5px; font-weight:bold; }

</style>
<script language="Javascript"  type="text/javascript" >
 function refreshWindow() {
//  var parentLocation=window.opener.location;
//  window.parent.location.href=parentLocation;
//  window.opener.location.reload(1);
//  return false;
}
</script>

<script type="text/javascript" language="javascript">
<!--
  function getElementIndex(frm, el) {
    //getElementIndex - returns the index of the element object in the specified form.
    //Returns:
    //        -1 - If the element was not found in the form, this value is returned.
    //        n  - If the element was found, the integer index is returned.
    //frm - The form in which the search is to be performed.
    //el - The element whose index is being searched for.
    var index = -1;
    for(i = 0; i < frm.elements.length; i++) {
      if(frm.elements[i].name == el.name) {
	index = i;
	break;
      }
    }
    return index;
  }

  function addToList(frm, sf, st) {
    //Retrieve the index values of the elements.
    var from = getElementIndex(frm, sf);
    var to   = getElementIndex(frm, st);

    fromLen = frm.elements[from].length ;
    for ( i=0; i<fromLen ; i++){
      if (frm.elements[from].options[i].selected == true ) {
	toLen = frm.elements[to].length;
	frm.elements[to].options[toLen]= new Option(frm.elements[from].options[i].text,frm.elements[from].options[i].value);
      }
    }

    for ( i = (fromLen -1); i>=0; i--){
      if (frm.elements[from].options[i].selected == true ) {
      frm.elements[from].options[i] = null;
      }
    }
  }

  function moveAll (frm, sf, st) {
    var from = getElementIndex(frm,sf);
    var fromLen = frm.elements[from].length;

    for(var i = 0; i < fromLen; i++)
      frm.elements[from].options[i].selected = true;

    addToList(frm,sf,st);
  }

  function saveIssue(frm) {
    //saveIssue(frm)
    //- This method  submits the form for being saved as a draft.
    //  If there have been exceptions to the form validation then
    //  an error message is displayed and the form is not submitted
    //  for processing.
    buildForm(frm);
    if(!hasValidationErrors(frm)){
      frm.elements['actionMethod'].value = "saveForm";
      refreshWindow();
      clearSrch(frm);
      frm.submit();
    }
  }

  function submitIssue(frm) {
    //submitIssue(frm)
    //- This method submits the form for being saved as an issue.
    //  If there have been exceptions to the form validation then
    //  an error message is displayed and the form is not submitted
    //  for processing.

    frm.elements['resolverPK'].required = true;
    frm.elements['resolverPK'].label = "Resolver";
    buildForm(frm);
    if(!hasValidationErrors(frm)){
      frm.elements['actionMethod'].value = "submitForm";
      refreshWindow();
      clearSrch(frm);
      frm.submit();
    }
    frm.elements['resolverPK'].required = false;
  }

  function buildRules(frm) {
    //buildRules(frm)
    //- This method build the various validation rules for the
    //  form.

    frm.elements['sitePK'].required = true;
    frm.elements['sitePK'].label = "Site";
    frm.elements['classificationPK'].required = true;
    frm.elements['classificationPK'].label = "Classification";
    frm.elements['priorityPK'].required = true;
    frm.elements['priorityPK'].label = "Priority";
    frm.elements['description'].required = true;
    frm.elements['description'].label = "Description";
    return frm;
  }

  function hasValidationErrors(frm) {
    //printValidationErrors(frm)
    //- This method validates the form against the defined rules.
    //  It returns TRUE if there have been errors and prints them
    //  on a pop-up alert. Otherwise it returns FALSE.

    var msg = "--------------------------------------------------------------------------";
    var error = false;
    frm = buildRules(frm);

    for(i = 0; i < frm.elements.length; i++){
      if(frm.elements[i].required) {
	if(frm.elements[i].type == "select-one" &&
	   (frm.elements[i].value < 0 || frm.elements[i].selectedIndex < 0)) {
	    error = true;
	    msg += "\n" + frm.elements[i].label +" - is a required field.";
	}
	else
	  if((frm.elements[i].type == "text" || frm.elements[i].type == "textarea")
	      && frm.elements[i].value.length <= 0) {
	    error = true;
	    msg += "\n" + frm.elements[i].label +" - is a required field.";
	}
      }
    }
    msg += "\n--------------------------------------------------------------------------";
    if(error)
      alert(msg);

    return error;
  }

  function addResolver (frm, sf, st) {
    //empty the existing list.
    var to = getElementIndex(frm, st);
    for(i = frm.elements[to].options.length - 1; i > -1; i--)
      frm.elements[to].options[i] = null;

    addToList(frm,sf,st);

    if(frm.elements[to].options.length > 1) {
      var i = frm.elements[to].options.length - 1;
	while(i > 0) {
	  frm.elements[to].options[0].selected = true;
	  addToList(frm, st, sf);
	  i--;
	}
    }
  }

  function clearResolver(frm, sf, st) {
    var index = getElementIndex(frm, sf);
    frm.elements[index].options[0].selected = true;
    addToList(frm, sf, st);
  }

  function addReviewer(frm, sf, st) {
    addToList(frm, sf, st);
  }
   function checkLength(what,maxlen) {
      if (what.length > maxlen) {
          alert('This text field must be less than ' + maxlen + ' characters long');
          return false;
      }
      return true;
  }
  function removeReviewer(frm, sf, st) {
    addToList(frm, sf, st);
  }

  function clearAllReviewer(frm, sf, st) {
    moveAll(frm, sf, st);
  }

  function buildForm(frm) {
    var msg = "";
    for(i = 0; i < frm.elements.length; i++){
      if(frm.elements[i].type == "select-one"
	 && frm.elements[i].name == "resolverPK") {
	if(frm.elements[i].options.length > 0)
	  frm.elements[i].options[0].selected = true;
      }
      else if(frm.elements[i].type == "select-multiple"
	      && frm.elements[i].name == "reviewerList") {
	if(frm.elements[i].options.length > 0)
	  for(j = 0; j < frm.elements[i].options.length; j++)
	    frm.elements[i].options[j].selected = true;
      }
      msg += "\n" + frm.elements[i].name + ": [" + frm.elements[i].type + "] = " + frm.elements[i].value;
    }
    //alert(msg);
    return true;
  }
  function enterKeyPress() {
      if (window.event.keyCode == 13){
               return false;
        }
        else return true;
  }

-->
</script>
<title>Post Issue</title>
</head>
<body style="margin:0px" >

<html:form action="PostIssueAction?method=processForm">
<html:hidden property="actionMethod"/>
<html:hidden property="issuePK"/>
<html:hidden property="formID"/>
<table width="490" border="0" cellpadding="0" cellspacing="0">
  <!--DWLayoutTable-->
  <tr>
    <td width="638" height="22" align="left" valign="middle" class="PopupHeader" style="background-color:#FFFF99;padding-left:2px;"><h3 style="font-family:Verdana; font-size:14pt; font-weight:bold; color:#000000">Post Issue</h3></td>
  </tr>
  <tr>
    <td height="283" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
	<!--DWLayoutTable-->
	<tr>
	  <td width="110" height="31" align="left" valign="middle"
	      class="label">Site<span style="color:red">&nbsp;*</span></td>
	  <td colspan="4" align="left" valign="top" class="data">
	  <c:choose>
	    <c:when test='${param.siteID ne null}'>
	      <%-- <c:when test='${param.formID ne null}'> --%>
	      <html:select property="sitePK" name="postIssueForm"
			   styleClass="data" style="width:130px" disabled="true">
		<bean:define id="selectSitePK" name="postIssueForm" property="sitePK">
		</bean:define>
		<option value='<bean:write name="selectSitePK"/>' selected="selected">
		  <%
		     String label = "TODO";
		     //TODO CacheLookUp.getProtocolSiteListFromCache().get(selectSitePK).toString();
		     out.print(label);
		     %>
		</option>
	      </html:select>
	    </c:when>
	    <c:otherwise>
	     <html:select name="postIssueForm" property="sitePK"
			 styleClass="data" style="width:130px">
	      <html:option value="-1">Select</html:option>
	      <bean:define id="siteList" name="postIssueForm" property="siteList"/>
	      <html:options collection="siteList"
			      property="value"
			      labelProperty="label"/>
	     </html:select>
	    </c:otherwise>
	  </c:choose>

	  </td>
	</tr>
	<tr bgcolor="#CCCCCC">
	  <td height="31" align="left" valign="middle" class="label">Classification<span style="color:red">&nbsp;*</span></td>
	  <td align="left" valign="top" class="data" style="width:150px">
	    <bean:define id="classificationList" name="postIssueForm"
			 property="classificationList"/>
	      <html:select name="postIssueForm" property="classificationPK"
			   styleClass="data" style="width:150px" >
		<html:option value="-1">Select</html:option>
		<html:options collection="classificationList"
			      property="value" labelProperty="label"/>
	      </html:select>
	  </td>
	  <td width="70" style="text-align:right" valign="middle" class="label">Priority<span style="color:red">&nbsp;*</span></td>
	  <td align="left" valign="top" class="data" style="width:210px">
	    <bean:define id="priorityList" name="postIssueForm"
			 property="priorityList"/>
	      <html:select name="postIssueForm" property="priorityPK"
			   styleClass="data" style="width:80px">
		<html:option value="-1">Select</html:option>
		<html:options collection="priorityList"
			      property="value" labelProperty="label"/>
	      </html:select>
	  </td>
	  <td>&nbsp;</td>
	</tr>
	<tr bgcolor="#CCCCCC">
	  <td height="100" align="left" valign="top" class="label">Description<span style="color:red">&nbsp;*</span></td>
	  <td colspan="4" align="left" valign="top" class="data">
	    <html:textarea styleClass="data" style="width:90%; height:100px;"
	      name="postIssueForm" cols="15" rows="15" property="description" onchange="return checkLength(this.value,4000)">
	    </html:textarea>
	  </td>
	</tr>
	<tr bgcolor="#CCCCCC">
	  <td height="21" colspan="5" align="left" valign="middle"><!--DWLayoutEmptyCell-->&nbsp;</td>
	</tr>
	<tr bgcolor="#CCCCCC">
	  <td height="100" align="left" valign="top" class="label">Suggested<br>
	    Action</td>
	  <td colspan="4" align="left" valign="top" class="data">
	    <html:textarea styleClass="data" style="width:90%; height:100px;"
	      name="postIssueForm" cols="15" rows="15" property="suggestedAction" onchange="return checkLength(this.value,500)">
	    </html:textarea>
	  </td>
	</tr>
      </table></td>
  </tr>
  <tr>
    <td height="187" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
	<!--DWLayoutTable-->
	<tr>
	  <td height="21" colspan="3" valign="top" class="label" style="width:100%;">Resolvers and Reviewers</td>
	</tr>
	<tr>
	  <td width="121" height="24" valign="top" class="label" style="width:119px">Letter or Name: </td>
	  <td width="210" valign="top" style="padding-right:10px"><input type="text" name="code" style="width:200px;" class="data" onKeyPress="return enterKeyPress()"/></td>
	  <td width="307" valign="top" style="padding-right:10px"><input type="button" name="Search" disabled="true" value="Search" onClick="search(this.form);" class="data" style="padding:none;width:60px;"/>
	  </td>
	</tr>
	<tr>
	  <td height="73">&nbsp;</td>
	  <td rowspan="2" valign="top">
	    <select name="resultList" size="14" multiple class="data" style="width:200px">
	      <option>Search Results will be placed here</option>
	    </select>
	  </td>
	  <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
	      <!--DWLayoutTable-->
	      <tr>
		<td height="13" colspan="2" valign="top" class="label" style="width:210px">Resolver (Select One)<span style="color:red">&nbsp;&dagger;</span></td>
	      </tr>
	      <tr>
		<td width="63" height="32" valign="top" style="padding-left:5px">
					<input type="button" name="selectResolver" value=">>" onclick="addResolver(this.form,resultList,resolverPK);"/>
		</td>
		<td width="244" valign="top">
		  <html:select property="resolverPK" name="postIssueForm"
			       size="2" styleClass="data"
			       style="width:170px; padding:0px;">
		   <logic:notEmpty name="postIssueForm" property="resolverCol">
		     <html:optionsCollection name="postIssueForm"
					     property="resolverCol"
					     label="value"
					     value="label" />
		   </logic:notEmpty>
		  </html:select>
		</td>
	      </tr>
	      <tr>
		<td height="28">&nbsp;</td>
		<td valign="top" align="right" style="padding-right:10px"><input type="button" class="data" style="padding-left:none; width:50px;" value="Clear" onclick="clearResolver(this.form, resolverPK,resultList);"/>
		</td>
	      </tr>
	    </table></td>
	</tr>
	<tr>
	  <td height="101">&nbsp;</td>
	  <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
	      <!--DWLayoutTable-->
	      <tr>
		<td height="12" colspan="2" valign="top" class="label">Reviewers</td>
	      </tr>
	      <tr>
		<td width="64" rowspan="2" valign="top">
					<input type="button" value=">>" onclick="addReviewer(this.form, resultList, reviewerList);"/><br/>
					<input type="button" value="<<" onclick="removeReviewer(this.form, reviewerList, resultList);"/>
				</td>
		<td width="243" height="68" valign="top">
		  <html:select property="reviewerList" name="postIssueForm"
			       size="8" styleClass="data" multiple="true"
			       style="width:170px; padding:0px;">
		   <logic:notEmpty name="postIssueForm" property="reviewerCol">
		     <html:optionsCollection name="postIssueForm"
					     property="reviewerCol"
					     label="value"
					     value="label" />
		   </logic:notEmpty>
		  </html:select>
		</td>
	      </tr>
	      <tr>
		<td height="21" align="right" valign="top" style="padding-right:10px">
					<input type="button" class="data" style="padding-left:0px; width:50px;" name="ClearReviewer" value="Clear" onclick="clearAllReviewer(this.form,reviewerList,resultList);"></td>
	      </tr>
	  </table></td>
	</tr>
    </table></td>
  </tr>
  <tr>
    <td height="24" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0" style="border-top:1px solid #000">
	<tr>
	  <td width="638" height="24" valign="top" style="padding-left:5px"><input type="button" value="Cancel" onclick="window.close()"/>
	    <input type="button" value="Save" onClick="saveIssue(this.form)"/>
	    <input type="button" value="Submit" onClick="submitIssue(this.form)"/>
	  </td>
	</tr>
	<tr>
	  <td colspan="2" style="padding-left:5px" width="100%">
	    <span class="label" style="width:100%;color:red">* = required<br />
	      &dagger; = required when submitting the issue
	    </span>
	  </td>
	</tr>
      </table></td>
  </tr>
</table>
</html:form>
</body>
<script src="<html:rewrite page="/do/UserListAction"/>"></script>
