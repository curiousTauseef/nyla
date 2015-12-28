<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<html>
<head>
<script src="<html:rewrite page='/skins/default/js/gcsm.js'/>" type="text/javascript"></script>
<link rel="stylesheet" href="<html:rewrite page="/skins/default/css/clinsight.css"/>" type="text/css">
<link rel="stylesheet" type="text/css" href="<c:url value="/skins/default/css/form.css"/>"/>
<%--<title><tiles:getAsString name="title" /></title>--%>
<script src="<html:rewrite page='/skins/default/js/hashtable.js'/>" type="text/javascript"> </script>
<script src="<html:rewrite page='/skins/default/js/div_functions.js'/>" type="text/javascript"></script>
<script src="<c:url value="/views/common/js/validation.js"/>"></script>
<script src="<c:url value="/views/common/js/form.js"/>"></script>
<script>
     var multSelectWindow = null;
   
   // Show Multi Selection
   function showMultiSelect(aMultiSelectName, aInputID)
   {
      
        var inputField = document.getElementById(aInputID);
        
        var aChoicesText = document.getElementById(aInputID+'_Choices').innerHTML;
        
       // alert(aChoicesText);
    
       //''+self.location,'mywin', , 'left=20,top=20,width=500,height=500,toolbar=0,resizable=0'      
     
     //get offline/offline multi-select link
     var multiSelectLink = document.getElementById('multiSelectLink');
     
     //alert('href='+multiSelectLink.href);
      multSelectWindow = window.open(multiSelectLink.href+"?name="+aMultiSelectName, "multi_select", "height=400,width=500,scrollbars=yes,resizable=yes", false);
      
       //wait for page to load
       while(multSelectWindow.document.getElementById('multiSelectForm') == null)
       {
          //do nothing
          setTimeout("",50);
       }
       
       var scriptCode = "multSelectWindow.populateChoices('"+aChoicesText+"','"+inputField.name+"','"+inputField.value+"','"+aMultiSelectName+"');";
       
      //alert('scriptCode='+scriptCode);
        setTimeout(scriptCode,50); //call populateChoices on window
     
   }//---------------------------------------------------------------
  function postFormDeviation(aForm)
  {
          //var postIssueWindow = window.open("/gcsm/do/PostIssueAction?method=init&formID="+aForm.formID.value,  "Post Issue", "height=400,width=500,scrollbars=yes,resizable=yes", false);
          var url = '<c:url value="/do/formAction?op=createForUnscheduled&siteID=${sessionScope.siteID}&formID=${form.primaryKey}&formTypeName=Protocol Deviation"/>';
          
          window.location = url;
}//--------------------------------------------
   
</script>
</head>
  <body topmargin="0" leftmargin="10" rightmargin="10" onclick="hideCalendar()">
	<A id="multiSelectLink" HREF="/gcsm/views/form/multi_select.jsp" ></A>
	<A id="helpLink" HREF="<c:url value="/views/form/help.jsp"/>"></A>
    <table width="100%" align="center" cellspacing="0" cellpadding="0" border="0" style="border: solid 1px #074A87">
       <tr>
         <td width="100%">
           <div id="header">
              <tiles:insert attribute="header" flush="false"/>
           </div>
         </td>
       </tr>
       <tr>
         <td width="100%">
           <div id="userInfo">
              <tiles:insert attribute="userInfo" flush="false"/>
           </div>
         </td>
       </tr>
       <tr>
         <td width="100%">
           <div id="topnav">
           	  <c:choose>
           	  	<c:when test='${sessionScope.form.type.code == "SMP"}'>
           	  		<c:set var="landingPage" value="smp" scope="request"/>
           	  	</c:when>
           	  	<c:otherwise>
           	  		<c:remove var="landingPage" scope="request"/>
           	  	</c:otherwise>
           	  </c:choose>
           	  <c:choose>
           	  	<c:when test='${(!empty sessionScope.form.sitePK) && sessionScope.form.type.code != "PSA"}'>
           	  		<c:set var="siteID" value="${sessionScope.form.sitePK}" scope="session"/>
           	  	</c:when>
           	  </c:choose>
           	  <c:choose>
           	  	<c:when test='${(!empty requestScope.form.sitePK) && requestScope.form.type.code != "PSA"}'>
           	  		<c:set var="siteID" value="${requestScope.form.sitePK}" scope="session"/>
           	  	</c:when>
           	  </c:choose>
           	  <c:choose>
           	  	<c:when test='${!empty sessionScope.form.type.code}'>
           	  		<c:set var="landingTab" value="${sessionScope.form.type.code}" scope="request"/>
           	  	</c:when>
           	  </c:choose>
           	  <c:choose>
           	  	<c:when test='${!empty requestScope.form.type.code}'>
           	  		<c:set var="landingTab" value="${requestScope.form.type.code}" scope="request"/>
           	  	</c:when>
           	  </c:choose>
              <tiles:insert attribute="topnav" flush="false"/>
           </div>
         </td>
       </tr>
       <tr>
        <td width="100%">
          <div id="main" style="position: relative; width:100%; overflow: none; border: solid 0px #CCCCFF">
            <table border="0" width="100%" cellspacing="0" cellpadding="0">
            <tr>
                <td colspan="2" style="background-color:#CCCCFF;height:3px">&nbsp;</td>
            </tr>
            <tr>
              <td width="100%">
                <div id="container" style="position: relative; width:100%; overflow: none; border: solid 0px #CCCCFF">
                  <table cellpadding="0" cellspacing="0" border="0" width="100%">
                  	<c:if test="${!(empty sessionScope.siteID)}">
						  <tr style="background-color:#CCCCFF" height="15px">
						  	<td style="font-family:Verdana; font-size:12px; text-align: right;">
                         		<a href="#" onclick="showHideNav()"><div id="leftNavMinMax">&laquo;</div></a>
							</td>
							<td></td>
						  </tr>
				    </c:if>
                    <tr>
                      <td id="leftNavCell" height="350px" align="left" valign="top" style="width: 160px; background-color:#CCCCFF; border:solid 1px #CCCCFF; border-top:none">
                         <%-- Left Navigation --%>
                         <div id="leftnav">
                			  <% String leftNavAttribute = "leftNav";
                			  	 if ("SMP".equalsIgnoreCase((String) request.getAttribute("landingTab")) ||
                			  			"PSA".equalsIgnoreCase((String) request.getAttribute("landingTab")))
                			  		leftNavAttribute = "leftNav" + request.getAttribute("landingTab");
                			  	 
                			  %>
				           	  <tiles:insert name="<%= leftNavAttribute %>" flush="false"/>
                         </div>
                      </td>
                      <td width="100%" bgcolor="#FFFFFF" align="left" valign="top">
                        <div id="body">
                        <table cellspacing="0" cellpadding="0" width="100%">
                          <tr>
                            <td align="left" valign="top" width="100%">
                                   <tr>
                                        <td colspan="2" valign="top">
                                            <div align="left" id="overview" style="position: relative; overflow: none; width:100%; border: solid 2px #FFFFFF">
                                              <table height="500" width="100%" border="0">
                                                 <tr>
                                                    <td width="100%" height="100%" valign="top">
                                                        <tiles:insert attribute="body" flush="false"/>
                                                     </td>
                                                    </tr>
                                                  </table>
                                           </div>
                                        </td>
                                   </tr>
                            </td>
                          </tr>
                        </table>
                        </div>
                      </td>
                    </tr>
                  </table>
                </div>
              </td>
            </tr>
            </table>
          </div>
        </td>
       </tr>
       <tr>
        <td width="100%">
          <div align="left" id="footer" style="position: relative; width:100%; overflow: none; border: solid 2px #FFFFFF">
            <tiles:insert attribute="footer" flush="false"/>
          </div>
        </td>
       </tr>
    </table>
    <tiles:insert attribute="extra" flush="false" ignore="true"/>
    <%-- Calendar div --%>
    <div style="position:absolute; display:none" id="cal">
		<iframe width="146" height="124" name="gToday:mini:agenda.js:gfFlat_mydate:mini_plugins.js" id="gToday:mini:agenda.js:gfFlat_mydate:mini_plugins.js" src="/gcsm/skins/default/js/calendar/iflateng.htm" scrolling="no" frameborder="0"></iframe>
	</div>
  <div style="display:none"><img id="img_delRow" src="<html:rewrite page="/skins/default/images/form_delete_dynamic_row.gif"/>"/></div>
  </body>
</html>
