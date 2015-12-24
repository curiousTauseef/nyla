<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<%
	String baseUrl = "DocumentAction?landingTab=library&siteId=" + request.getParameter("siteId");
	String pagingBaseUrl = "/do/" + baseUrl + "&method=doPaging";
	String sortBaseUrl = "/do/" + baseUrl + "&method=sort";
	String filterUrl = baseUrl + "&method=doFilter";
%>

<script language="JavaScript">

	function doPaging(listbox) {
		var selected = listbox.options[listbox.selectedIndex].value;
		location.href = "<html:rewrite page="<%= pagingBaseUrl %>"/>&pageSelected=" + selected;
	}

	function openWin(url) {
  		window.open(url, "gcsm", "width=400, height=300, resizable=no, scrollbars=no, location=no, status=no, directories=no, menubar=no");
	}
	function showProgress() {
		document.documentPanelForm.startDate.value += 'a';
		//alert(document.documentPanelForm.startDate.value);
	}
	
	function sortAll(field, colIndex) {
		if (document.userPanelForm.isBackweb.value == "false") {
			location.href = "<html:rewrite page="<%= sortBaseUrl %>"/>&sortField=" + field;
		}
		else {
			//var startTime = new Date().getTime();
			window.status = "Sorting...";
			//showProgress();
			//var timer = setTimeout("showProgress()", 100);
			
			if (field == 'modifiedDate')
				sortBy('document_list_data', colIndex, 'date');
			else
				sortBy('document_list_data', colIndex);
			
			var table = document.getElementById('document_list_data');
			var tbody = table.getElementsByTagName("tbody")[0];
			var rows = tbody.getElementsByTagName("tr");
			var style1 = "portBody";
			var style2 = "portColHdr1";
			var style = style1;
			for (var i = 0; i < rows.length; i++) {
				if (rows[i].style.display == "") {
	        		rows[i].className = style;
	        		style = style == style1 ? style2 : style1;
	        	}
			}
			//window.clearInterval(timer);
			window.status = "";
			//var endTime = new Date().getTime();
			//alert("time to sort: " + (endTime-startTime) + " ms");
			
		}
	}
	function processDocFilters() {
		if (document.userPanelForm.isBackweb.value == "false") {
			document.documentPanelForm.submit();
		}
		else {
			//var startTime = new Date().getTime();
			window.status = "Filtering...";
			var form = document.documentPanelForm;
			var category = form.documentCategoryId.options[form.documentCategoryId.selectedIndex].value;
			var startDateStr = trim(form.startDate.value);
			var endDateStr = trim(form.endDate.value);
			var startDate = null;
			var endDate = null;
			if (startDateStr != null && startDateStr != "" &&
					endDateStr != null && endDateStr != "") {
				try {
					startDate = parseDate(startDateStr).getTime();
					endDate = parseDate(endDateStr).getTime();
				}
				catch (err) {
					startDate == null;
					endDate == null;
				}
			}
			
			var table = document.getElementById('document_list_data');
			var tbody = table.getElementsByTagName("tbody")[0];
			var rows = tbody.getElementsByTagName("tr");
			var style1 = "portBody";
			var style2 = "portColHdr1";
			var style = style1;
			//alert("filtering by: category=" + category + ", startDate=" + startDateStr + ", endDate=" + endDateStr);
			var displayTotal = 0;
			for (var i = 0; i < rows.length; i++) {
				var cells = rows[i].getElementsByTagName("td");
				var dateCol = cells[4];
				var cat = trim(cells[0].innerText);
				var date = trim(cells[4].innerText);
				if (date != "")
					date = parseDate(date).getTime();
					
				if (category == 'All' || cat == category) {
					if (startDate != null && (date == "" || date < startDate || date > endDate)) {
						rows[i].style.display = "none";
					}
					else {
						rows[i].className = style;
						rows[i].style.display = "";
						style = (style == style1 ? style2 : style1);
						displayTotal++;
					}
				}
				else {
					rows[i].style.display = "none";
				}
			}
			var sum = document.getElementById("pageSum");
			var start = displayTotal == 0 ? "0" : "1";
			sum.innerText = "Showing " + start + " - " + displayTotal + " of " + displayTotal;
			window.status = "";
			//var endTime = new Date().getTime();
			//alert("time to filter: " + (endTime-startTime) + " ms");
		}
	}
</script>

<table width="100%" cellpadding="1" cellspacing="1" border="0" id="document_container_tbl" valign="top">
<tr>
	<td width="100%">

<html:form  action="<%= filterUrl %>" method="POST">
<table width="100%" cellpadding="1" cellspacing="1" border="0" id="document_overview" align="left">
	<tr>
		<td width="20%" align="left" valign="top" style="font-family:verdana; font-size:16px; font-weight:bold">
			Library
		</td>
		<td width="80%" align="left" valign="top" style="font-family:verdana; font-size:11px;">
		</td>
	</tr>
	<tr>
		<td width="20%" align="left" valign="top" style="font-family:verdana; font-size:11px; font-weight:bold">
			Protocol/Site:
		</td>
		<td width="80%" align="left" valign="top" style="font-family:verdana; font-size:11px;">
			<bean:write id="protocolId" name="documentPanelForm" property="protocolId"/>
		</td>
	</tr>
	<tr>
		<td width="20%"align="left" valign="top" style="font-family:verdana; font-size:11px; font-weight:bold">
			Investigator Name:
		</td>
		<td width="80%"align="left" valign="top" style="font-family:verdana; font-size:11px;">
			<bean:write name="documentPanelForm" property="investigator"/>
		</td>
	</tr>
                        
	<tr>
		<td width="20%" align="left" valign="top" style="font-family:verdana; font-size:11px; font-weight:bold">
			Category:
		</td>
		<td width="80%" align="left" valign="top" style="font-family:verdana; font-size:11px;">
			<bean:define id="categoryList" name="documentPanelForm" property="categoryList"/>
			<html:select name="documentPanelForm" property="documentCategoryId"  styleClass="portFilter">
				<html:option value="All">All</html:option>
				<html:options collection="categoryList" name="documentPanelForm" property="key" labelProperty="value"/>
			</html:select>

		</td>
	</tr>
	<tr>
		<td width="20%" align="left" valign="top" style="font-family:verdana; font-size:11px; font-weight:bold">
			Date Range:
		</td>
		<td width="80%" align="left" valign="top" style="font-family:verdana; font-size:11px;">
			<table width="100%" cellpadding="1" cellspacing="1" border="0">
				<tr>
					<td width="10%" border=1px align="left" valign="top" style="font-family:verdana; font-size:11px;">
						<html:text name="documentPanelForm" property="startDate"/>
                	</td>
                    <td width="5%" align="left" valign="center" style="font-family:verdana; font-size:11px;">
                    	<img border="0" src="<html:rewrite page="/skins/default/images/button_calendar.gif"/>" id="img_startDate" style="cursor:hand" onclick="showCalendar(this)"/>
                    </td>
                    <td width="10%" border=1px align="left" valign="top" style="font-family:verdana; font-size:11px;">
						<html:text name="documentPanelForm" property="endDate"/>
                    </td>
                    <td width="75%" align="left" valign="center" style="font-family:verdana; font-size:11px;">
                    	<img border="0" src="<html:rewrite page="/skins/default/images/button_calendar.gif" />" id="img_endDate" style="cursor:hand" onclick="showCalendar(this)"/>
                    </td>
				</tr>                            	  
			</table>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left" valign="top" style="font-family:verdana; font-size:11px; font-weight:bold">
        </td>
        <td width="80%" align="left" valign="top" style="font-family:verdana; font-size:11px;">														
            <%--
            	<html:img  border="0" page="/skins/default/images/button_go_on.gif"  onclick="doSubmitOnDate(this)"/>
            --%>
            <html:img  style="cursor:hand" border="0" page="/skins/default/images/button_go_on.gif"  onclick="processDocFilters()"/>                            
		</td>
	</tr>
	<tr>
		<td colspan="2" height="21" align="center" valign="top"><hr width="100%" height="1" style="border:1px solid black"/></td>
	</tr>
</table>

	</td>
</tr>

<tr>
	<td width="100%">
	
<bean:define id="totalCount" name="documentPanelForm" property="totalCount" type="java.lang.Integer"/>
<bean:define id="fromIndex" name="documentPanelForm" property="fromIndex" type="java.lang.Integer"/>
<bean:define id="toIndex" name="documentPanelForm" property="toIndex" type="java.lang.Integer"/>
<bean:define id="pageSize" name="documentPanelForm" property="pageSize" type="java.lang.Integer"/>

<table id="documentTable" width="100%" border="0" cellspacing="0" cellpadding="0"  align="left">
<tbody>
	<tr>
	  <td colspan="7">
		<table id="pagination" width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="bottom" class="portBody" width="50%">
					<div id="pageSum">
					Showing <bean:write name="documentPanelForm" property="fromIndexDisplay"/>
						-
							<bean:write name="documentPanelForm" property="toIndexDisplay"/>
						of
							<bean:write name="documentPanelForm" property="totalCount"/>
					</div>
				</td>
				<td valign="bottom" class="portBody" style="text-align:right" width="50%">
				  <logic:notEqual name="documentPanelForm" property="displayAll" value="true">
				  <%--<logic:notEmpty name="documentPanelForm" property="pageCountList">--%>
					<logic:notEqual name="documentPanelForm" property="firstPage" value="true">
					<html:link page="<%= pagingBaseUrl %>"
                            id="page"
                            styleClass="PaginationActive"
                            paramId="pageSelected"
                            paramName="documentPanelForm"
                            paramProperty="prevPage"><< Previous</html:link>
                    </logic:notEqual>
                    <logic:equal name="documentPanelForm" property="firstPage" value="true">
                    	<span class="PaginationInactive"><< Previous</span>
                    </logic:equal>

					<bean:define id="pageCountList" name="documentPanelForm" property="pageCountList"/>

									<html:select name="documentPanelForm" property="pageSelected" styleClass="portFilter" onchange="doPaging(this);">
										<html:options collection="pageCountList" name="documentPanelForm" property="key" labelProperty="value"/>
									</html:select>

					<logic:notEqual name="documentPanelForm" property="lastPage" value="true">
					<html:link page="<%= pagingBaseUrl %>"
                            id="page"
                            styleClass="PaginationActive"
                            paramId="pageSelected"
                            paramName="documentPanelForm"
                            paramProperty="nextPage">Next >></html:link>
					</logic:notEqual>
					<logic:equal name="documentPanelForm" property="lastPage" value="true">
						<span class="PaginationInactive">Next >></span>
					</logic:equal>
				  <%--</logic:notEmpty>--%>
				  </logic:notEqual>
				</td>
			</tr>
			<tr>
				<td style="height: 10px;"></td>
			</tr>
		</table>
	  </td>
   <tr>
     <td>
	<table width="100%" border="0" cellspacing="0" cellpadding="4" id="document_list_data">
		<thead>
			<tr class="portBody1">
				<td class="portColHdr" width="10%">
				<a href="#"
       		                class="LinkUline"
               		        target="_self"
                       		onclick="sortAll('category', 0)">Category</a>
                </td>
                <td class="portColHdr" width="5%">
                </td>
				<td class="portColHdr" width="10%">
				<a href="#"
       		                class="LinkUline"
               		        target="_self"
                       		onclick="sortAll('type', 2)">Type</a>
                </td>                            
				<td class="portColHdr" width="45%">
				<a href="#"
       		                class="LinkUline"
               		        target="_self"
                       		onclick="sortAll('title', 3)">Title</a>
                </td>
                <td class="portColHdr" width="10%">
				<a href="#"
       		                class="LinkUline"
               		        target="_self"
                       		onclick="sortAll('modifiedDate', 4)">Date</a>
                </td>
                <td class="portColHdr" width="10%">		
				<a href="#"
       		                class="LinkUline"
               		        target="_self"
                       		onclick="sortAll('source', 5)">Source</a>
                </td>
			</tr>
		</thead>
		<tbody>
		<% int mod=0; %>
		<logic:notEmpty name="documentPanelForm" property="documentDetailList">
        <nested:iterate name="documentPanelForm" property="documentDetailList" indexId="i" id="documentForm" offset="fromIndex" length="pageSize">
        <%
        	mod = (i.intValue()%2);
        	String style = mod == 0 ? "portBody" : "portColHdr1";
        %>
        	<tr class="<%= style %>">
				<td class="portBody" valign="top">
	                <nested:write property="category"/>
                </td>
                <td valign="top" align="left" valign="center" style="font-family:verdana; font-size:11px;">
					<nested:equal property="source" value="PRISM">
	                	<a href="javascript:void(0)" onclick="openWin('/gcsm/do/DocumentDetailAction?method=initDocumentDetail&documentId=<nested:write property="documentId"/>&source=<nested:write property="source"/>');">	    
						   <html:img  border="0" page="/skins/default/images/library_i.gif"/>
				    	</a>
					</nested:equal>			    	
					<nested:equal property="source" value="ESF">
	                	<a href="javascript:void(0)" onclick="openWin('/gcsm/do/DocumentDetailAction?method=initDocumentDetail&documentId=<nested:write property="documentId"/>&source=<nested:write property="source"/>');">	    
						   <html:img  border="0" page="/skins/default/images/library_i.gif"/>
				    	</a>
					</nested:equal>			    	
                </td>
				<td  class="portBody" valign="top">
	                <nested:write property="type"/>
                </td>
                <nested:equal property="source" value="GCSM">
                	<bean:define id="target" value="_self"/>
                </nested:equal>
                <nested:notEqual property="source" value="GCSM">
                	<bean:define id="target" value="new"/>
                </nested:notEqual>
				<td  class="portBody" valign="top">
				<div style="width: 300px; overflow: hidden; text-overflow: ellipsis">
                <nested:empty property="documentId">
                    <a target="<bean:write name="target"/>" title="<nested:write property="title"/>" href="<nested:write property="documentName"/>">
                        <nested:write property="title"/>
                    </a>
                </nested:empty>
                <nested:notEmpty property="documentId">
                	<a target="<bean:write name="target"/>" title="<nested:write property="title"/>" href="/gcsm/documentretrieve?id=<nested:write property="documentId"/>&source=<nested:write property="source"/>&date=<nested:write property="displayDate"/>&version=<nested:write property="documentVersion"/>">
                    	<nested:write property="title"/>
                	</a>
                </nested:notEmpty>
                </div>
                </td>
                <td  class="portBody" valign="top" nowrap="true">		
	                <nested:write property="displayDate"/>
             
                <td  class="portBody" valign="top">		
				    <nested:write property="source"/>
                </td>
			</tr>
		</nested:iterate>
	  	</logic:notEmpty>	
	  	</tbody>
		</table>
    </td>
    </tr>
</tbody>
   </table>
</html:form>

	</td>
</tr>
</table>
