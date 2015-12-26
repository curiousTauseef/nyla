<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>


 <head> 
    <link href="<html:rewrite page="/skins/default/css/clinsight.css"/>" rel="stylesheet" type="text/css"/> 
 </head> 
 
<body>
<table>
	
	<tr>  
	<td>
	   	<bean:define id="reportTitle" name="reportForm" property="reportTitle"/>  
  		<h3 class="reportsHeader"><%=reportTitle%></h3> 
	</td>
	</tr>  
	
	

	<tr>		
		<td>
            <bean:define id="reportForm" name="reportForm" property="html"/>
            <table id="report_tbl">
               <tr><td>
               <span id="body">
                  <%=reportForm%>
               </span>
               </td></tr>
            </table>
		</td>
				
	</tr>
</table>
</body>



