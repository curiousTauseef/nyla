<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://gcsm.bms.com/tld/security.tld" prefix="security"%>
<div id="myForm_body">

<html:form action="/MyFormPanelAction">
  <html:hidden property="sortByFieldName"/>
  <table width="100%" cellpadding="1" cellspacing="1" border="0">
    <tr>
      <td height="29px"><h3><br>
          <span class="reportsHeader">My Forms</span><br>
        </h3></td>
    </tr>
    <tr>
      <td width="100%" style="padding:15px;">
      <table id="myForms_tbl" width="100%" border="0" cellpadding="0" cellspacing="0" style="border:1px solid #000">
		<thead>
          <tr>
            <td height="21" colspan="7" align="left" valign="middle" class="portTitle">&nbsp;&nbsp;My Forms</td>
            <td colspan="3" align="right" valign="middle" class="portFilterLbl"> Form Types &nbsp;&nbsp; </td>
            <td width="137" align="left" valign="middle" class="portFilterLbl">
              <html:select name="myFormPanelForm" property="filterByValue"  styleClass="portFilter" onchange="processFilters(this.form,'filterByValue','myForms_tbl');" >
                <bean:define id="formTypeCollection"  name="myFormPanelForm" property="formTypeCollection" />
                <html:option value="-1">All</html:option>
                <html:options collection="formTypeCollection"
                   property="label"/>
              </html:select>
            </td>
            <td width="51" class="portFilterLbl">&nbsp;</td>
          </tr>
          <tr>
            <td width="8" height="21" align="left" valign="middle" class="portColHdr">&nbsp;</td>
            <td width="126" valign="middle" class="portColHdr" style="padding-left:5px">Investigator</td>
            <td width="6" valign="middle" class="portColHdr" style="padding-left:5px">&nbsp;</td>
            <td width="178" valign="middle" class="portColHdr" style="padding-left:5px">Status</td>
            <td width="6" valign="middle" class="portColHdr" style="padding-left:5px">&nbsp;</td>
            <td width="133" valign="middle" class="portColHdr" style="padding-left:5px"><a href="#" onclick="sortBy('myForms_tbl',5);">Protocol/Site</a></td>
            <td width="6" valign="middle" class="portColHdr" style="padding-left:5px">&nbsp;</td>
            <td width="109" valign="middle" class="portColHdr" style="padding-left:5px"><a href="#" onclick="sortBy('myForms_tbl',5, 'date');">Date</a></td>
            <td width="10" class="portColHdr" style="padding-left:5px">&nbsp;</td>
            <td colspan="2" valign="middle" class="portColHdr" style="padding-left:5px">Form Type</td>
            <td class="portColHdr" style="padding-left:5px">&nbsp;</td>
          </tr>
          </thead>
          <tbody>
          <logic:notEmpty name="myFormPanelForm" property="formsCollection">
            <nested:iterate name="myFormPanelForm" property="formsCollection">
              <tr>
                <td height="21" valign="middle" align="left" class="psiNavText">&nbsp;</td>
                <td class="psiTableData" valign="top" style="padding-left:5px">
                  <nested:notEmpty property="investigator">
                    <nested:write property="investigator"/>
                  </nested:notEmpty>
                  <nested:empty>
                    &dash;
                  </nested:empty>
                </td>
                <td class="psiTableData"></td>
                <td valign="top" class="psiTableData" style="padding-left:5px">
                  <nested:notEmpty property="formStatus">
                    <nested:write property="formStatus"/>
                  </nested:notEmpty>
                  <nested:empty>
                    &dash;
                  </nested:empty>
                </td>
                <td class="psiTableData"></td>
                <td valign="top" class="psiTableData" style="padding-left:5px">
                  <nested:notEmpty property="protocolSite">
                    <nested:write property="protocolSite"/>
                  </nested:notEmpty>
                  <nested:empty>
                    &dash;
                  </nested:empty>
                </td>
                <td class="psiTableData"></td>
                <td valign="top" class="psiTableData" style="padding-left:5px">
                  <nested:notEmpty property="date">
                    <nested:write property="date"/>
                  </nested:notEmpty>
                  <nested:empty>
                    &dash;
                  </nested:empty>
                </td>
                <td class="psiTableData"></td>
                <td colspan="2" valign="top" class="psiTableData" style="padding-left:5px">
                  <nested:notEmpty property="formType">
                  	<% String formPage = ""; %>
                    <a href="<html:rewrite page="/do/formAction?op=view"/>&formID=<nested:write property="formId"/>"><nested:write property="formType"/></a>
                  </nested:notEmpty>
                  <nested:empty>
                    &dash;
                  </nested:empty>
                </td>
                <td class="psiTableData"></td>
              </tr>
            </nested:iterate>
          </logic:notEmpty>
          <logic:empty name="myFormPanelForm" property="formsCollection">
            <tr>
              <td height="21">&nbsp;</td>
              <td class="portBody" colspan="10" style="padding-left:5px"> --- No Data Found --- </td>
            </tr>
          </logic:empty>
		</tbody>
        </table>
        </td>
    </tr>
  </table>
  </html:form>
</div>
