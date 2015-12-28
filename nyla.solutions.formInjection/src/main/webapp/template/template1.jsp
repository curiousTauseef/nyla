<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
    <title> GCSM HOME </title>
    <link rel="stylesheet" type="text/css" href="css/clinsight.css" />
<%--  <meta http-equiv="Content-Type" content="text/html;CHARSET=iso-8859-1" />--%>
<%--  <title>GCSM WEB</title>--%>
<%--  <link rel="stylesheet" type="text/css" href="css/tiles.css" />--%>
</head>
<f:view>
<body topmargin="0%" leftmargin="10%" rightmargin="10%">

<%--  <div id="lftBar">--%>
<%--    <f:subview id="menu">--%>
<%--      <tiles:insert attribute="menu" flush="false" />--%>
<%--    </f:subview>--%>
<%--  </div>--%>
  <div id="bms">
    <div id="gcsm">
           <div id="header">
              <f:subview id="header">
                <tiles:insert attribute="header" flush="false"/>
              </f:subview>
           </div>
           <div id="userInfo">
              <f:subview id="userInfo">
                <tiles:insert attribute="userInfo" flush="false"/>
              </f:subview>
           </div>
           <div id="topnav">
              <f:subview id="topnav">
                <tiles:insert attribute="topnav" flush="false"/>
              </f:subview>
           </div>

           <div id="body">
             <f:subview id="body">
                <tiles:insert attribute="body" flush="false"/>
             </f:subview>
          </div>
<%--           <div id="footer">--%>
<%--             <f:subview id="footer">--%>
<%--                <tiles:insert attribute="footer" flush="false"/>--%>
<%--             </f:subview>--%>
<%--          </div>--%>

    </div>
  </div>
</body>
</f:view>
</html>