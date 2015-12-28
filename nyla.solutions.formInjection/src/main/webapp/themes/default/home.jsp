<%@ page import="java.math.BigDecimal,
                 java.util.Date"%>
<%@ page session="false" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<html>
   <body>
       GCSM Home Page 
       <hr/>
       <br/>
       <br/>
       Hello <c:out value="${sessionScope.user.firstName}"/> 
       <c:out value="${sessionScope.user.lastName}"/> 
       <br/>
       <c:out value="${param.j_username}"/>, 
       <c:out value="${param.j_password}"/> 
       <br/>
        <a href="<c:url value="/views/issue/list_issues.jsf"/>">Issue POC</a>
   </body>
</html>
