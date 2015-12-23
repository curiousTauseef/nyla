<%@page import="solutions.gedi.exception.GediException"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true" %>
	<h2>Test error</h2>
	<p>
		# <%= exception.toString() %>
	</p>
	<p>
		# <%=FaultException.stackTrace(exception)%> 
	</p>

   <p>
   		Config.properties.gemfire.cache-xml-file=<%=nyla.solutions.global.util.Config.getProperty("gemfire.cache-xml-file","")%>
  </p>
  <p>
   	Config.properties.gemfirePropertyFile=<%=nyla.solutions.global.util.Config.getProperty("gemfirePropertyFile","")%>
   </p>
