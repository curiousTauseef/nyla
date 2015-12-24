
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
    <title> GCSM HOME </title>
    <link rel="stylesheet" type="text/css" href="/gcsm/skins/default/css/clinsight.css" />

</head>

<body topmargin="0%" leftmargin="10%" rightmargin="10%">


  <div id="bms">
    <div id="gcsm">
           <div id="header">
              
                <tiles:insert attribute="header" flush="false"/>
              
           </div>


           <div id="body">
             
                <tiles:insert attribute="body" flush="false"/>
             
          </div>
    </div>
  </div>
</body>

</html>