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
    .data { font-weight:normal }
    .PopupHeader {
      background-color:#FFFF99;
      font-family:Verdana;
      font-size:14px;
      padding-left:5px;
      font-weight:bold;
    }
    .data1 {width:97%; font-weight:normal }
  </style>
  <script type="text/javascript" language="javascript">
   var checkvalue = "";

    function checkedone(frm) {
    var o = frm.elements['closeStatus'];
    var checkedone;
    checkedone = false;
    for(i = 0; o.length; i++) {
    if(o[i].checked) {
         checkedone = true;
         checkvalue = o[i].value;
      }
   }
   alert("CheckedOne:"+checkedone);
    return checkedone;

    }
    function getRadioValue(frm) {
      var o = frm.elements['closeStatus'];
      var val;
            for(i = 0; o.length; i++) {
	if(o[i].checked) {
	  val = i;
       checkvalue = o[i].value;

         switch(val){
	  case 0:
	       withResolution(frm);
	  break;
	  case 1:
	       withoutResolution(frm);
	  break;
	  case 2:
	       reassign(frm);
	  break;
	  }
	}
      }

    }

    function withResolution(frm) {
      frm.elements['comment'].disabled = false;
      frm.elements['code'].disabled = true;
      frm.elements['Search'].disabled = true;
      frm.elements['resultList'].disabled = true;
      frm.elements['selectResolver'].disabled = true;
      frm.elements['resolverPK'].disabled = true;
      frm.elements['Clear'].disabled = true;
      frm.elements['submitbutton'].disabled = false;
      frm.elementsfrm.elements['resolverPK'].required = false;
       clearSrch(frm);
    }

    function withoutResolution(frm) {

      frm.elements['comment'].disabled = true;
      frm.elements['code'].disabled = true;
      frm.elements['Search'].disabled = true;
      frm.elements['resultList'].disabled = true;
      frm.elements['selectResolver'].disabled = true;
      frm.elements['resolverPK'].disabled = true;
      frm.elements['Clear'].disabled = true;
     frm.elements['submitbutton'].disabled = false;
      frm.elementsfrm.elements['resolverPK'].required = false;
        clearSrch(frm);
      }

    function reassign(frm) {
      frm.elements['comment'].disabled = false;
      frm.elements['code'].disabled = false;
      frm.elements['Search'].disabled = false;
      frm.elements['resultList'].disabled = false;
      frm.elements['selectResolver'].disabled = false;
      frm.elements['resolverPK'].disabled = false;
      frm.elements['Clear'].disabled = false;
    frm.elements['submitbutton'].disabled = false;
   frm.elementsfrm.elements['resolverPK'].required = true;
        frm.elements['resolverPK'].label = "Resolver";

          }

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

    function submitIssue(frm) {
     buildForm(frm);
	frm.submit();


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
	  if ((frm.elements[i].type == "text" || frm.elements[i].type == "textarea")
	      && frm.elements[i].value.length <= 0) {
	      error = true;
	      msg += "\n" + frm.elements[i].label +" - is a required field.";

	  }
      }
      if (!checkedone(frm)) {
           error = true;
           msg += "\nYou must select close or reassign";

	}
      }
      msg += "\n--------------------------------------------------------------------------";
      if(error)
      alert(msg);
      return error;
    }
     function checkLength(what,maxlen) {
      if (what.length > maxlen) {
          alert('This text field must be less than ' + maxlen + ' characters long');
          return false;
      }
      return true;
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


    function buildForm(frm) {
      clearSrch(frm);
      var msg = "";
      for(i = 0; i < frm.elements.length; i++){
	if(frm.elements[i].type == "select-one"
	   && frm.elements[i].name == "resolverPK") {
	   if(frm.elements[i].options.length > 0)
	     frm.elements[i].options[0].selected = true;
      }
     msg += "\n" + frm.elements[i].name + ": [" + frm.elements[i].type + "] = " + frm.elements[i].value;
      }
      return true;
    }
    function enterKeyPress() {
          if (window.event.keyCode == 13){
                   return false;
            }
            else return true;
      }

  </script>
<title>Resolve Issue</title>
</head>
<body style="margin:0px">
<html:form action="/EditIssueAction?method=submit">
<html:hidden property="issueID" name="editIssueForm"/>
<table width="432px" border="0" cellpadding="0" cellspacing="0">
  <!--DWLayoutTable-->
  <tr>
    <td width="100%" height="31px" valign="top" class="PopupHeader">Issue</td>
  </tr>
  <tr>
    <td height="426" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
	<!--DWLayoutTable-->
	<tr>
	  <td width="110" valign="top" class="label">Site</td>
	  <td colspan="3" valign="top" class="data"><bean:write name="editIssueForm" property="protocolNbr"/> </td>
	</tr>
	<tr>
	  <td class="label">Investigator</td>
	  <td colspan="4" class="data"><bean:write name="editIssueForm" property="investigatorName"/> </td>
	</tr>
	<tr>
	  <td class="label">Issue Posted </td>
	  <td class="data"><bean:write name="editIssueForm" property="originatorName"/> </td>
	  <td class="label" >Issue Post Date</td>
	  <td class="data"><bean:write name="editIssueForm" property="postDate"/> </td>
	</tr>
	<tr>
	  <td class="label">Resolver</td>
	  <td class="data"><bean:write name="editIssueForm" property="resolverName"/> </td>
	  <td class="label" >Target Resolution Date</td>
	  <td class="data"><bean:write name="editIssueForm" property="targetDate"/> </td>
	</tr>
	<tr>
      <td valign="top" class="label">Previous Resolvers </td>
	  <td colspan="4" class="data"><bean:write name="editIssueForm" property="previousResolverList"/> </td>
	</tr>
     <tr>
    <td height="1" >&nbsp;</td>
  </tr>
	<tr bgcolor="#CCFFCC">
	  <td class="label">Classification </td>
	  <td width="110" valign="top" class="data"><bean:write name="editIssueForm" property="issue.classification.name"/> </td>
	  <td width="110" valign="top" class="label">Priority</td>
	  <td width="102" valign="top" class="data"><bean:write name="editIssueForm" property="issue.priority.name"/> </td>
	</tr>
	<tr bgcolor="#CCFFCC">
	  <td valign="top" class="label">Description </td>
	  <td colspan="3" valign="top" class="data"><div style="border:1px solid #CCFFCC; width:380px; height:100px; overflow:auto;" class="data"> <bean:write name="editIssueForm" property="issue.description"/> </div></td>
	</tr>
	<tr bgcolor="#CCFFCC">
	  <td valign="top" class="label">Action </td>
	  <td colspan="3" valign="top" class="data"><div style="border:1px solid #CCFFCC; width:380px; height:100px; overflow:auto;" class="data"> <bean:write name="editIssueForm" property="issue.action"/> </div></td>
	</tr>
	<tr>
	  <td colspan="4" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0" style="border:1px solid black; border-left:none; border-right:none;">
	      <!--DWLayoutTable-->
	      <tr>
		<td width="720" height="29" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
		    <!--DWLayoutTable-->
             <logic:equal value="F" name="editIssueForm" property="viewMode" >

		    <tr>
		      <td width="112" height="29" style="vertical-align:middle;" class="label">Issue Status<span style="color:red">&nbsp;*</span> </td>
		      <td width="397" style="width:380px"><table border="0" cellspacing="0" cellpadding="0" width="100%">
			  <tr>
			    <td nowrap style="width:25px; padding-left:5px; vertical-align:middle; text-align:right"><html:radio property="closeStatus"
value="cl_with_resolve" onclick="getRadioValue(this.form);"
name="editIssueForm" styleClass="data" style="width:20px"/> </td>
			    <td align="left" nowrap class="data"
style="padding:0px; width:125px; vertical-align:middle">Close with resolution</td>
			    <td>&nbsp;</td>
			    <td align="right" valign="top" style="width:20px; vertical-align:middle; text-align:right"><html:radio property="closeStatus" value="cl_without_resolve" onclick="getRadioValue(this.form);"
name="editIssueForm" styleClass="data" style="width:20px"/> </td>
			    <td align="left" nowrap class="data" style="padding-left:0px; width:145px; vertical-align:middle;">Close without resolution</td>
			    <td>&nbsp;</td>
			    <td align="right" valign="top" style="width:20px; vertical-align:middle"><html:radio property="closeStatus" value="reassign" onclick="getRadioValue(this.form);"
name="editIssueForm" style="width:20px"/> </td>
			    <td align="left" valign="top" class="data" style="padding-left:0px; width:40px; vertical-align:middle">Reassign</td>
			  </tr>
			</table></td>
		    </tr>
            </logic:equal>
		    <tr>
		      <td height="29" style="vertical-align:top;" class="label">Issue Resolution</td>
		      <td align="left" valign="top">
				<table width="100%" cellpadding="0" cellspacing="0" border="0">
					<logic:notEmpty name="editIssueForm" property="issue.comments">
						<tr>
							<td class="data" style="padding:0px">
								<div style="border:1px solid #CCFFCC; width:380px; height:100px; overflow:auto;" class="data">
									<nested:iterate property="issue.comments">
									  <nested:write property="comment"/>
									  <br/>
									</nested:iterate>
								</div>
							</td>
						</tr>
					</logic:notEmpty>
                    <logic:equal value="F" name="editIssueForm" property="viewMode" >

					<tr>
					  <td>
					    <html:textarea property="comment"
							   styleClass="data" style="width:380px"
							   rows="4" cols="15" onchange="return checkLength(this.value,4000)"/>
					  </td>
					</tr>
				</table>

			  </td>
		    </tr>

		    <tr>
		      <td height="19" colspan="2" valign="top"><!--DWLayoutEmptyCell-->&nbsp;</td>
		    </tr>
		    <tr>
		      <td height="84" valign="top"><span class="label">Reassign to:</span></td>
		      <td valign="top">
						  <!-- BEGIN Resolver -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td colspan="2" valign="top" style="padding-right:10px">
								<input type="text" name="code" style="width:200px;" class="data" onKeyPress="return enterKeyPress()" disabled="true" />
								<input type="button" name="Search" value="Search" onClick="search(this.form);"
									   disabled="true" class="data" style="padding:0px; width:52px"/>
			    </td>
			  </tr>
			  <tr>
			    <!-- Search Result Box -->
			    <td width="200" rowspan="2" valign="top">
								<select name="resultList" size="5" multiple class="data" style="width:200px">
					<option>Search Results will be placed here</option>
				      </select>
			    </td>
			    <!-- Selected Resolver Box -->
			    <td width="789" valign="top"><table width="100%" height="85" border="0" cellpadding="0" cellspacing="0">
				<!--DWLayoutTable-->
				<tr>
				  <td height="13" colspan="2" valign="top" class="label" style="width:210px"> Resolver (Select One)</td>
				</tr>
				<tr>
				  <td width="39" height="32" valign="top" style="padding-left:5px"><input type="button" name="selectResolver" value=">>"
									   onclick="addResolver(this.form,resultList,resolverPK);"/>
				  </td>
				  <td width="750" align="left" valign="top">
								  <html:select property="resolverPK" name="editIssueForm" size="2" styleClass="data"
											 style="width:117px; padding:0px;">
								  </html:select> </td>
				</tr>
				<tr>
				  <td height="26" colspan="2" valign="top"  style="padding-right:10px"><input name="Clear" type="button" class="data" style="padding-left:none; width:45px;" value="Clear"
								       onclick="clearResolver(this.form, resolverPK,resultList);"/>
				  </td>
				</tr>
			      </table>
						</td>
			  </tr>
			</table>
			<!-- END Resolver -->
		      </td>
		    </tr>
		  </table></td>
	      </tr>
	    </table></td>
	</tr>
      </table></td>
  </tr>
  <tr>
    <td height="2" valign="top" class="data" style="width:none;">&nbsp;</td>
  </tr>
  </table>
  <table>
  <tr>

    <td height="26">

      <input type="button" value="Submit" class="label" name="submitbutton"
       style="text-align:center; padding:1px;"  disabled="true"
	   onclick="submitIssue(this.form);"/>

       </logic:equal>
     </td>
      <td>
	<input type="button" value="Close Window"
	     onclick="window.close();" class="label"
	     style=" text-align:center; padding:1px;"/>
      </td>
       <logic:equal value="F" name="editIssueForm" property="viewMode" >

      <tr>
	  <td colspan="2" style="padding-left:5px" width="100%">
	    <span class="label" style="width:100%;color:red">* = required<br />
	     	    </span>
	  </td>
	</tr>
     </logic:equal>
  </tr>
</table>
</html:form>
</body>
<script src="<html:rewrite page="/do/UserListAction"/>"></script>
