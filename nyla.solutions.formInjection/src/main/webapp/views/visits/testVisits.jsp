<%@ page session="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://myfaces.sourceforge.net/tld/myfaces_ext_0_9.tld"
	prefix="x"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<f:loadBundle
	basename="com.bms.informatics.gcsm.common.web.Presenter_bundle"
	var="msgs" />
<html>
		<script language="JavaScript">
		 <!--
		 function processFilters(form, box, table) {
					// eval("var selectBox = form."+box);
					form.submit();


		 } // -->
                </script>
<f:view>
    <head>
         <link rel="stylesheet" type="text/css" href="/gcsm/skins/default/css/clinsight.css"/>
          <title>GCSM HOME</title>
          <script src="/gcsm/skins/default/js/hashtable.js"></script>
          <script src="/gcsm/skins/default/js/div_functions.js"></script>
    </head>
	<div align="center" id="visits" style="position: relative">
	<tiles:insert attribute="visits" flush="false" /></div>
</f:view>
</html>
