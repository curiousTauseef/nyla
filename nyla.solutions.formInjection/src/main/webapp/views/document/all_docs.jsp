<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<html:form action="DocumentAction">

<logic:notEmpty name="documentPanelForm" property="documentDetailList">
	<nested:iterate name="documentPanelForm" property="documentDetailList" indexId="i" id="documentForm" offset="fromIndex" length="pageSize">
		<nested:equal property="source" value="PRISM">
        	<a href="<html:rewrite page="/do/DocumentDetailAction?method=initDocumentDetail"/>&documentId=<nested:write property="documentId"/>&source=<nested:write property="source"/>"></a>
		</nested:equal>			    	
		<nested:equal property="source" value="ESF">
        	<a href="<html:rewrite page="/do/DocumentDetailAction?method=initDocumentDetail"/>&documentId=<nested:write property="documentId"/>&source=<nested:write property="source"/>"></a>
		</nested:equal>			    	
        <nested:empty property="documentId">
            <a href="<nested:write property="documentName"/>"></a>
        </nested:empty>
        <nested:notEmpty property="documentId">
        	<a href="/gcsm/documentretrieve?id=<nested:write property="documentId"/>&source=<nested:write property="source"/>"></a>
        </nested:notEmpty>
	</nested:iterate>
</logic:notEmpty>	
</html:form>
