<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

<title>Investigator Issues</title>
<link rel="stylesheet" href="<html:rewrite page="/skins/default/css/clinsight.css"/>" type="text/css">
<link rel="stylesheet" href="<html:rewrite page="/skins/default/css/form.css"/>" type="text/css">

<html:form action="/IssuePanelAction?method=doFilterByInvestigator">

	
	    <table align="left" border="0" cellpadding="0" cellspacing="0"  id="issues_body" width="100%">
		<%-- Header --%>
		
		<tr class="sectionText">
		        <td colspan=15>Issues Linked to this Location </td>
        	</tr>
        	
        	<tr><td colspan=15>&nbsp;</td></tr>
		
		<tr>
		    <td>
	<div id="inner_issues" style="overflow: auto; height: 100px; border: solid 1px #CCCCFF">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="issue_data_tbl" >
	    <thead>
	    <tr class="noScroll" style="border-top:1px solid #000; border-bottom:1px solid #000;padding-top:5px;padding-bottom:5px">
	    
	    
		<td class="psiTableHeader" width="120">
		<a href="javascript:void(0)"
			   class="LinkUline"
			    target="_self">Protocol/Site</a>

		</td>
		
	   
		<td class="psiTableHeader" width="60">
		<a href="javascript:void(0)"
			   class="LinkUline"
			    target="_self">Priority</a></td>
		<td class="psiTableHeader" width="100">
		<a href="javascript:void(0)"
			   class="LinkUline"
			    target="_self">Classification</a></td>
		<td class="psiTableHeader" width="100">Description</td>
		<td class="psiTableHeader" width="80">Report</td>
		<td class="psiTableHeader" width="100">Contact Date</td>
		<td class="psiTableHeader" width="80">
				<a href="javascript:void(0)"
					   class="LinkUline"
			    target="_self">Originator</a></td>		
		<td class="psiTableHeader" width="80">
		<a href="javascript:void(0)"
			   class="LinkUline"
			    target="_self">Resolver</a></td>
	    </tr>
	    </thead>
	    <tbody>
    <logic:notEmpty name="issuePanelForm" property="originalIssueDetailList">
	<nested:iterate name="issuePanelForm" property="originalIssueDetailList" indexId="i" id="issueForm">

	    <tr>
	    
	     
		<td>
		    <nested:notEmpty  property="protocolSite">
			<nested:write property="protocolSite"/>
		    </nested:notEmpty>
		    <nested:empty  property="protocolSite">
			&ndash;
		    </nested:empty>
		     <nested:hidden  property="protocolSite"/>

		</td>
		
	   	<td>
		    <nested:notEmpty  property="priority">
			<nested:write property="priority"/>
		    </nested:notEmpty>
		    <nested:empty  property="priority">
			&ndash;
		    </nested:empty>
		     <nested:hidden  property="priority"/>

		</td>
		<td>
		    <nested:notEmpty  property="classification">
			<nested:write property="classification"/>
		    </nested:notEmpty>
		    <nested:empty  property="classification">
			&ndash;
		    </nested:empty>
		     <nested:hidden  property="classification"/>

		<td>
		    <nested:notEmpty  property="description">

		      <nested:link
			onclick="window.open('','postIssue','width=550,height=710,menubar=no, scrollbars=yes, status=no,toolbar=no,titlebar=no');"
			href="EditIssueAction?method=init"
			paramId="issueID" paramProperty="issueId"
			target="postIssue">
			<nested:write property="description"/>
		      </nested:link>
		    </nested:notEmpty>
		    <nested:empty  property="description">
			&ndash;
		    </nested:empty>
		     <nested:hidden  property="description"/>

		</td>
		<td>
		    <nested:notEmpty  property="report">
			<nested:write property="report"/>
		    </nested:notEmpty>
		    <nested:empty  property="report">
			&ndash;
		    </nested:empty>
		     <nested:hidden  property="report"/>

		</td>
		<td>
		    <nested:notEmpty  property="contactDt">
			<nested:write property="contactDt"/>
		    </nested:notEmpty>
		    <nested:empty  property="contactDt">
			&ndash;
		    </nested:empty>
		     <nested:hidden  property="contactDt" />
		</td>
		<td>
		    <nested:notEmpty  property="originator">
			<nested:write property="originator"/>
		    </nested:notEmpty>
		    <nested:empty  property="originator">
			&ndash;
		    </nested:empty>
		     <nested:hidden  property="originator"/>
		</td>
		<td>
		    <nested:notEmpty  property="resolver">
			<nested:write property="resolver"/>
		    </nested:notEmpty>
		    <nested:empty  property="resolver">
			&ndash;
		    </nested:empty>
		     <nested:hidden  property="resolver"/>
		</td>
<%--   This is added for Backweb Do not remove              --%>
		<td style="display:none;" width="0px;"><nested:write property="status"/></td>
	    </tr>
   </nested:iterate>
  </logic:notEmpty>
  <logic:empty name="issuePanelForm" property="originalIssueDetailList">
		<td colspan="9">No Data Found</td>
  </logic:empty>
	</tbody>
	</table>
</div>
		    </td>
		</tr>
	    </table>
    </html:form>
	
