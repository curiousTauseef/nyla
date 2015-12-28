	<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ include file="nbsp.jsp" %>
 	<% 
 		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
 		Date dt = new Date();
 		String sdt = formatter.format(dt); 
 	%>
 	<%=sdt%><br><br>


 	<%@ include file="nbsp.jsp" %>
 	<bean:write name="letterForm" property="invFirstNm"/>&nbsp;
 	<bean:write name="letterForm" property="invLastNm"/><br>
 	
 	<%--
 	<bean:define id="addr1" name="letterForm" property="address1"/>
 	<bean:define id="addr2" name="letterForm" property="address2"/>

	<% if ( (!addr1.equals(null)) && (!addr1.equals("")) )%>
	<% { %>
 	<%@ include file="nbsp.jsp" %>
 	<%= addr1 %><br>
 	<% } %>
	
	<% if ( (!addr2.equals(null)) && (!addr2.equals("")) ) %>
	<% { %>
 	<%@ include file="nbsp.jsp" %>
 	<%= addr2 %><br>
 	<% } %>
 	--%>
 	<%@ include file="nbsp.jsp" %>
 	<bean:write name="letterForm" property="address1"/><br>
 	<%@ include file="nbsp.jsp" %>
 	<bean:write name="letterForm" property="address2"/><br>
 	

	<%@ include file="nbsp.jsp" %>
	<bean:write name="letterForm" property="cityNm"/>
	<bean:write name="letterForm" property="state"/>
	<bean:write name="letterForm" property="zipCode"/><br><br>
