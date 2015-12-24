<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://myfaces.sourceforge.net/tld/myfaces_ext_0_9.tld" prefix="m"%>
User: <c:out value="${param.j_username}"/> <br/>
Password: <c:out value="${param.j_password}"/>
<jsp:forward page="/j_security_check"/>
