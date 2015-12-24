<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://gcsm.bms.com/tld/security.tld" prefix="security"%>
<%@page contentType="text/html; charset=windows-1252"%>
<%@page import="com.bms.informatics.gcsm.common.util.CacheLookUp,com.bms.informatics.gcsm.common.util.Config"%>
<script type="text/javascript" language="JavaScript">
<!--
function openWin(url) {
  window.open(url, "gcsm", "width=800, height=180,left=175, top=277,resizable=no, scrollbars=yes, location=no, status=no, directories=no, menubar=no");
}

function openGIDWin(url) {
  window.open(url, "GID", "width=screen.availWidth,height=screen.availHeight,resizable=no, scrollbars=yes, location=no");
  
  //var aw = screen.availWidth;
  //var ah = screen.availHeight;
  
  //window.moveTo(0, 0);
  //window.resizeTo(aw, ah);
}


function createSchedule(frm) {
  var index = -1;
  for(i = 0; i < frm.elements.length; i++) {
    if(frm.elements[i].name == "investigatorId") {
      if(frm.elements[i].checked) {
        index = frm.elements[i].value; 
        break;
      }
    }
  }
  if (index == -1) {
  	alert("Please select an investigator.");
  }
  else {
	  var url = '<html:rewrite page="/do/newVisitAction?method=initNewVisitForm"/>' + "&investigatorId=" + index + "&operation=&doValidate=no";
	  window.open(url, "newVisitSchedule", "width=280, height=420, resizable=no, scrollbars=yes, location=no, status=no, directories=no, menubar=no");
  }
}

function validate(frm) {
  var doSubmit = false;

  for(i = 0; i < frm.elements.length && !doSubmit; i++) {
    if(frm.elements[i].type == "text" && frm.elements[i].value.length > 0)
        doSubmit = true;
  }
  if(!doSubmit) {
    if(frm.elements['country'].selectedIndex > 0)
     doSubmit = true;
  }

  if(doSubmit) {
    frm.submit();
    return true;
  }
  else {
    alert("Please enter at least 'a' search criteria");
    return false;
  }
}
-->
</script>
<html:form action="/ResearchSitePanelAction?method=search&landingPage=psi">
  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
    <tr>
      
      <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
      
      	<tr>
                <td colspan="2">&nbsp;</td>
        </tr>
      	
        <tr>
          <td colspan="2" valign="top"><h4>&nbsp;Search</h4></td>
        </tr>
        <tr>
          <td width="48" valign="top">&nbsp;</td>
          <td width="625" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td width="109" valign="top"><div align="left" class="displayLabel">First Name </div></td>
              <td width="510"><div align="left"><html:text property="firstName" style="width:220px"/></div></td>
            </tr>
			<tr>
              <td width="109" valign="top"><div align="left" class="displayLabel">Last Name </div></td>
              <td width="510"><div align="left"><html:text property="lastName" style="width:220px"/></div></td>
            </tr>
            <tr>
              <td><div align="left" class="displayLabel">Address</div></td>
              <td><div align="left"><html:text property="address" style="width:220px"/></div></td>
            </tr>
            <tr>
              <td><div align="left" class="displayLabel">City</div></td>
              <td><div align="left"><html:text property="city" style="width:220px"/></div></td>
            </tr>
            <tr>
              <td><div align="left" class="displayLabel">State/Province</div></td>
              <td><div align="left"><html:text property="state" style="width:220px"/></div></td>
            </tr>
            <tr>
              <td><div align="left" class="displayLabel">Country</div></td>
              <td><div align="left">
                <bean:define id="countryList" name="researchSitePanelForm" property="countryList"/>
                <html:select property="country" style="width:220px">
                  <option value="" SELECTED>Select</option>
                  <html:options collection="countryList"
                          property="value"
                          labelProperty="label"/>
                </html:select>
              </div></td>
            </tr>
            <tr>
              <td><div align="left"></div></td>
              <td><div align="left" style="padding-top:5px">
                <table width="100%"  border="0" cellspacing="1" cellpadding="1">
                  <tr>
                    <td width="21%"><div align="left" style="padding-top:5px">
                      <input style="font-size:8pt" type="button" value="Search" onclick="validate(this.form)"/></div></td>
                    <td style="font-size:8pt">Note: Default view of this screen will<br />show the list box as BLANK</td>
                  </tr>
                </table></div>
              </td>
            </tr>
            <tr><td colspan="2" height="10px">&nbsp;</td>
            </tr>
          </table></td>
        </tr>
      </table></td>
    </tr>
    <tr>      
      <td valign="top" align="center">
        <table width="96%" border="0" cellpadding="0" cellspacing="0" style="border-top:1px solid #000; border-bottom:1px solid #000">
        
       
           
           <tr align="left" valign="top" class="noScroll"  style="border-top:1px solid #000; border-bottom:1px solid #000">
              <td width="15px" class="psiTableHeader">&nbsp;</td>
              <td width="2px" class="psiTableHeader">&nbsp;</td>
              <td width="120px" class="psiTableHeader">Investigator</td>
              <td width="6px" class="psiTableHeader">&nbsp;</td>
              <td style="width:150px;" class="psiTableHeader">Address</td>
              <td width="2px" class="psiTableHeader">&nbsp;</td>
              <td style="width:170px;" class="psiTableHeader">City/State/Province/Country</td>
              <td width="2px" class="psiTableHeader">&nbsp;</td>
              <td width="85px" class="psiTableHeader">Significant
                Issues?</td>
              <td width="2px" class="psiTableHeader">&nbsp;</td>
              <td width="40px" class="psiTableHeader">PSA</td>
              <td width="2px" class="psiTableHeader">&nbsp;</td>
              <td width="55px" class="psiTableHeader">GID debarred flag? </td>
              <td width="2px" class="psiTableHeader">&nbsp;</td>
            </tr>
            
            <tr valign="top" style="border-top:1px solid #000; border-bottom:1px solid #000">
            
            <td colspan="13" width="100%">
            
            
            <div id="inner_researchSite" style="overflow: auto;width: 100%; height: 100px; border:none">
            
            <table width="100%" cellpadding="0" cellspacing="0" border="0">
            
            <logic:notEmpty name="researchSitePanelForm" property="investigatorCollection">
              <nested:iterate name="researchSitePanelForm" property="investigatorCollection" indexId="index">
                <c:choose>
                  <c:when test="${index % 2 == 0}">
                    <tr align="left" valign="top" bgcolor="#FFFFFF">
                  </c:when>
                  <c:otherwise>
                    <tr align="left" valign="top" bgcolor="#CCCCCC">
                  </c:otherwise>
                </c:choose>
                <td class="psiTableData" style="height:21px" width="6px" align="left" valign="top">
                  <input type="radio" name="investigatorId" value='<nested:write property="investigatorId"/>'/>
                </td>
                <td class="psiTableData" width="2px">&nbsp;</td>
                <td class="psiTableData" style="width:120px"><nested:write property="lastNm"/> , <nested:write property="firstNm"/></td>
                <td class="psiTableData" width="2px">&nbsp;</td>
                <td class="psiTableData" style="width:150px">
                  <nested:write property="address1"/> <nested:write property="address2"/><br />
                  <nested:write property="address3"/> <nested:write property="address4"/>
                </td>
                <td class="psiTableData" width="2px">&nbsp;</td>
                <td class="psiTableData" style="width:170px;">
                  <nested:write property="cityNm"/>, <nested:write property="stateProvinceNm"/><BR>
                  <nested:write property="countryNm"/>
                </td>
                <td class="psiTableData" width="2px">&nbsp;</td>
                <td class="psiTableData" style="width:85px;vertical-align:middle">
		  <nested:empty property="hasSigIssue">&nbsp;</nested:empty>
		  <nested:notEmpty property="hasSigIssue">
		     <nested:equal value="Y" property="hasSigIssue">
		     <a href="#" onclick="openWin('/gcsm/do/IssuePanelAction?method=doFilterByInvestigator&investigatorId=<nested:write property="investigatorId"/>');">	        			  
		     <html:img page="/skins/default/images/icon_issues.gif" align="right" border="0"/>
		     </a>
		     </nested:equal>
		     &nbsp;
		  </nested:notEmpty>
                </td>
                <td class="psiTableData" width="12px">&nbsp;</td>
                <td class="psiTableData" style="width:40px;vertical-align:middle;">
                  <nested:equal value="0" property="PSAFormId">&nbsp;</nested:equal>
                  <nested:greaterThan value="0" property="PSAFormId">                  
                  <a href="/gcsm/do/formAction?op=view&formID=<nested:write property='PSAFormId'/>&formTypeCode=PSA"/>	    
		  <html:img page="/skins/default/images/psa_logo.gif" align="right" border="0"/>
		  </a>	                       
                  </nested:greaterThan>
                </td>
                <td class="psiTableData" width="2px">&nbsp;</td>
                <td class="psiTableData" width="55px" style="text-align:center;vertical-align:middle">
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<nested:write property="gidIssue"/>
                </td>
                <td class="psiTableData" width="2px">&nbsp;</td>
                </tr>
              </nested:iterate>
            </logic:notEmpty>
      </table></div></td></tr></table></td>
    </tr>
    <security:guard permission="PSACreate">
    <tr>
      <td height="33" valign="top" style="padding-left:22px;padding-top:10px"><table width="100%"  border="0" cellspacing="1" cellpadding="1">
        <tr>
          <td width="18%"><html:button style="font-size:8pt;width:180px" property="page" value="Schedule Assessment Visit" title="Schedule Assessment Visit" onclick="createSchedule(this.form);"/></td>
            <td style="font-size:8pt">Note: PSA Form is pre-populated with data<br />from the selected Site/Investigator
            </td>
        </tr>
        </table>
      </td>
    </tr>
    </security:guard>
    <% String lsGidLink=Config.getProperty("gid.investigator.link"); %>
    <tr>
      
      <td valign="top" style="padding-left:22px; padding-top:22px" class="displayLabel"><a href="#" onclick="openGIDWin('<%=lsGidLink%>')">Create New Investigator</a> (links to GID) </td>
    </tr>
  </table>
</html:form>

