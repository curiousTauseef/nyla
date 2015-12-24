<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="com.bms.informatics.gcsm.common.util.*,com.bms.informatics.gcsm.common.web.util.*, java.util.*,com.bms.informatics.gcsm.protocol.web.*" %>

<script language="JavaScript">
	function checkSubmitButton(obj) {
	 //alert(document.documentForm.submit.name);
	 submitName = document.documentForm.submit.name;
	 if(submitName == null){
		document.documentForm.comment.disabled = true;	 
	 }
	 else{
	 	document.documentForm.comment.disabled = false;
	 }

	}

</script>

<%
String commentSaved = (String)request.getAttribute("CommentSaved");
%>

<html:form  action="/DocumentDetailAction?method=saveComment">
                    <table width="99%" cellpadding="1" cellspacing="1" border="0" id="site_body" align="left">
    				 	<tr>
                            <td colspan="2" width="69%" align="left" valign="top" style="font-family:verdana; font-size:16px;; font-weight:bold">
								<html:img  border="0" width ="20" height="20" page="/skins/default/images/library_i.gif"/> Document Detail                                    
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" width="69%" align="left" valign="top" style="font-family:verdana; font-size:16px;; font-weight:bold">
                            </td>
                        </tr>
<logic:messagesPresent>
    <tr>
        <td colspan="2">
        	<hr/>
        	<html:errors />
        	<hr/>
        </td>
    </tr>
</logic:messagesPresent>
<logic:messagesNotPresent>
                        <%--
						<tr>
                            <td width="30%" align="left" valign="top" style="font-family:verdana; font-size:11px; font-weight:bold">
                               Document Id
                            </td>
                            <td width="69%" align="left" valign="top" style="font-family:verdana; font-size:11px;">
                            	<bean:write name="DocumentForm" property="documentId"/>
                            </td>
                        </tr>
                        --%>
                        <tr>
                            <td width="30%" align="left" valign="top" style="font-family:verdana; font-size:11px; font-weight:bold">
                                Category
                            </td>
                            <td width="69%" align="left" valign="top" style="font-family:verdana; font-size:11px;">
                            	<bean:write name="DocumentForm" property="category"/>
                            </td>
                        </tr>
                        <tr>
                            <td width="30%"align="left" valign="top" style="font-family:verdana; font-size:11px; font-weight:bold">
                                Type
                            </td>
                            <td width="69%"align="left" valign="top" style="font-family:verdana; font-size:11px;">
                            <bean:write name="DocumentForm" property="type"/>
                            </td>
                        </tr>
                        <tr>
                            <td width="30%" align="left" valign="top" style="font-family:verdana; font-size:11px; font-weight:bold">
                                Protocol/Site
                            </td>
                            <td width="69%" align="left" valign="top" style="font-family:verdana; font-size:11px;">
                               <bean:write name="DocumentForm" property="dps"/>
                            </td>
                        </tr>
                        <tr>
                            <td width="30%" align="left" valign="top" style="font-family:verdana; font-size:11px; font-weight:bold">
                                Title
                            </td>
                            <td width="69%" align="left" valign="top" style="font-family:verdana; font-size:11px;">
                            	<bean:write name="DocumentForm" property="title"/>
							</td>
                        </tr>
                        <tr>
                            <td width="30%" align="left" valign="top" style="font-family:verdana; font-size:11px; font-weight:bold">
                               Language
                            </td>
                            <td width="69%" align="left" valign="top" style="font-family:verdana; font-size:11px;">
                            	<bean:write name="DocumentForm" property="language"/>
                            </td>
                        </tr>
                       <tr>
                            <td width="30%" align="left" valign="top" style="font-family:verdana; font-size:11px; font-weight:bold">
                               Indexed Date
                            </td>
                            <td width="69%" align="left" valign="top" style="font-family:verdana; font-size:11px;">
                            	<bean:write name="DocumentForm" property="indexedDate"/>
                            </td>
                        </tr>
                        <tr>
                            <td width="30%" align="left" valign="top" style="font-family:verdana; font-size:11px; font-weight:bold">
                               Document Name
                            </td>
                            <td width="69%" align="left" valign="top" style="font-family:verdana; font-size:11px;">
                            	<bean:write name="DocumentForm" property="documentName"/>
                            </td>
                        </tr>
                        <tr>
                            <td width="30%" align="left" valign="top" style="font-family:verdana; font-size:11px; font-weight:bold">
                               Indexer
                            </td>
                            <td width="69%" align="left" valign="top" style="font-family:verdana; font-size:11px;">
                            	<bean:write name="DocumentForm" property="indexer"/>
                            </td>
                        </tr>
                        <OTML:REMOVE>
                        <tr>
                            <td width="30%" align="left" valign="top" style="font-family:verdana; font-size:11px; font-weight:bold">
                               My Comments
                            </td>
                            <td width="69%" align="left" valign="top" style="font-family:verdana; font-size:11px;">
                                <html:textarea  name="DocumentForm" property="comment" onclick="checkSubmitButton();"/>
                            </td>
                        </tr>
                         <tr>
                            <td width="30%" align="left" valign="top" style="font-family:verdana; font-size:11px; font-weight:bold">
                            </td>
                            <td width="69%" align="left" valign="top" style="font-family:verdana; font-size:11px;">
                            </td>
                        </tr>
                        <tr>
                         	<td width="30%" align="left" valign="top" style="font-family:verdana; font-size:11px; font-weight:bold">  
                         	</td>
                            <td width="69%" align="left" valign="top" style="font-family:verdana; font-size:11px;">
                            <!--input type="button" onclick="window.opener.location.href='<html:rewrite page="/do/init" />';window.close();" value="Close"/-->
                            	<html:submit property="submit" value="submit"/>
                            	<%
                            	if(commentSaved != null && commentSaved.trim().equalsIgnoreCase("success")){
                            	%>
                            	Comment is submitted ...
                            	<%	
                            	}
                            	%>
                            </td>
                        </tr>
                        </OTML:REMOVE>
</logic:messagesNotPresent>
                    </table>
                    </html:form>