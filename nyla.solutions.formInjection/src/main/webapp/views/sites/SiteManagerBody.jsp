<%--
  User: Oscar
  Date: May 05, 2005
  Time: 1:03:59 PM
--%>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://myfaces.sourceforge.net/tld/myfaces_ext_0_9.tld" prefix="x"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
	<f:subview>
	            <tiles:insert attribute="issues" flush="false"/>
	</f:subview>

	<f:subview>
	            <tiles:insert attribute="alerts" flush="false"/>
	</f:subview>

<h:outputText value="Oscar is here"/>