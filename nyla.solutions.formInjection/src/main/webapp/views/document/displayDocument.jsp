<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@page contentType="text/html; charset=windows-1252"%>
<% String docId = null; %>
<html:html>
<head>
<title>
</title>
<html:base/>
<link href="../../skins/default/css/clinsight.css" rel="stylesheet" type="text/css"/>
<body>
  <html:form action="/documentAction?method=retrieveDocumentID">
          <!--bean:define id="df" name="documentForm" property="id"/-->
          <!--bean:write name="documentForm" property="id"/-->          
  <logic:notEmpty name="documentForm" property="ids">                         
  <!--nested:iterate  name="documentForm" property="ids" id="idList"--> 
		   <!--<nested:write  name="documentForm" property="ids"/-->
          <bean:define id="idList" name="documentForm" property="ids"/>
          <!--bean:write id="idList" name="documentForm" property="ids"/-->          
		   <table>
		   <%
		    int cnt =  ((ArrayList)idList).size();
		    for(int i = 0; i < cnt; i++){
		    docId = (String) ((ArrayList)idList).get(i);
	        %>
	        <tr>
	        <a href="/gcsm/documentretrieve?id=<%=docId%>">
			<%=docId%>	        
	        </a>
		    </tr>
		    <%}%>
		   </table>
  <!--/nested:iterate-->
  </logic:notEmpty>   
  </html:form>
</body>
</html:html>
