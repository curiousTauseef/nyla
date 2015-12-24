<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="com.bms.informatics.gcsm.common.util.*,com.bms.informatics.gcsm.common.web.util.*, java.util.*,com.bms.informatics.gcsm.protocol.web.*" %>

<table width="96%" cellpadding="1" cellspacing="1" align="left" border="0" style="background-color:#FFF"id="site_protocol_overview">
  <tr>

  <td width="42%" align="left" valign="top"><%-- Column 1 --%>
    <table width="100%" cellpadding="1" cellspacing="1" border="0" id="protocol_site_tbl">
      <tr>
	<td width="52%" align="left" valign="top" style="font-family:verdana; font-size:11px; font-weight:bold"> Protocol/Site: </td>
	<td width="48%" align="left" valign="top" style="font-family:verdana; font-size:11px;"><bean:write name="siteForm" property="protocolSiteNbr"/> </td>
      </tr>
      <tr>
	<td align="left" valign="top" style="font-family:verdana; font-size:11px; font-weight:bold"> Enrolled/Randomized: </td>
	<td align="left" valign="top" style="font-family:verdana; font-size:11px;"><logic:notEmpty   name="siteForm" property="enrollmentCnt"> <bean:write name="siteForm"  property="enrollmentCnt"/> </logic:notEmpty> <logic:empty   name="siteForm" property="enrollmentCnt"> &ndash; </logic:empty> / <html:hidden   name="siteForm" property="randomizationCnt"/> <logic:notEmpty  name="siteForm"  property="randomizationCnt"> <bean:write  name="siteForm" property="randomizationCnt"/> </logic:notEmpty> <logic:empty  name="siteForm"  property="randomizationCnt"> &ndash; </logic:empty> <html:hidden   name="siteForm" property="randomizationCnt"/> </td>
      </tr>
      <tr>
	<td align="left" valign="top" style="font-family:verdana; font-size:11px; font-weight:bold"> Study Status: </td>
	<td align="left" valign="top" style="font-family:verdana; font-size:11px;"><bean:write name="siteForm" property="studySiteStatus"/> </td>
      </tr>
      <tr>
	<td align="left" valign="top" style="font-family:verdana; font-size:11px; font-weight:bold"><%--                                Study Status:--%>
	</td>
	<td align="left" valign="top" style="font-family:verdana; font-size:11px;"><%--                                <bean:write name="siteForm" property="studySiteStatus"/>--%>
	</td>
      </tr>
      <tr>
      	<td align="left" valign="top" style="font-family:verdana; font-size:11px; font-weight:bold"> STM: </td>
      	<td align="left" valign="top" style="font-family:verdana; font-size:11px;">
      	 <logic:notEmpty   name="sitePanelForm" property="stmLastName">      	 
      	 <bean:write name="sitePanelForm" property="stmLastName"/> ,       	 
      	 </logic:notEmpty>     	 
      	 <bean:write name="sitePanelForm" property="stmFirstName"/>
      	</td>
      </tr>
    </table></td>
  <td width="58%" align="left" valign="top"><%-- Column 2 --%>
    <table width="100%" cellpadding="1" cellspacing="1" border="0">
    
       <tr>
	  <td width="42%" align="left" valign="top" style="font-family:verdana; font-size:11px; font-weight:bold">
	Investigator Name: </td>
	  <td width="58%" align="left" valign="top" style="font-family:verdana; font-size:11px;"><logic:notEmpty  name="siteForm" property="lastnm"> <bean:write name="siteForm" property="lastnm"/> <logic:notEmpty  name="siteForm" property="firstNm"> , <bean:write name="siteForm" property="firstNm"/> </logic:notEmpty> </logic:notEmpty> <logic:empty  name="siteForm" property="lastnm"> <logic:notEmpty  property="firstNm"> &ndash;,<bean:write name="siteForm" property="firstNm"/> </logic:notEmpty> </logic:empty></td>
	</td>
      </tr>    
      
      <tr>
	<td align="left" valign="top" style="font-family:verdana; font-size:11px; font-weight:bold"> Address: </td>
	<td align="left" valign="top" style="font-family:verdana; font-size:11px;">
	
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
	    <tr>
	      	      <td style="font-family:verdana; font-size:11px;"><bean:write name="siteForm" property="siteAddress"/></td>
	    </tr>
	<!--    <tr>
	    	      <td style="font-family:verdana; font-size:11px;"><bean:write name="siteForm" property="address2"/></td>
	    </tr>
	    <tr>
	    	      <td style="font-family:verdana; font-size:11px;"><bean:write name="siteForm" property="address3"/></td>
	    </tr>
	    <tr>
	    	      <td style="font-family:verdana; font-size:11px;"><bean:write name="siteForm" property="address4"/></td>
	    </tr> -->
	    <tr>
	      <td style="font-family:verdana; font-size:11px;"><bean:write name="siteForm" property="siteCity"/> , <bean:write name="siteForm" property="siteState"/> <bean:write name="siteForm" property="siteZip"/> </td>
	    </tr>
	  </table></td>
      </tr>
      
      	   <tr>
            <td align="left" valign="top" style="font-family:verdana; font-size:11px;"> <B>Phone:</B>
      	
            &nbsp; <bean:write name="siteForm" property="sitePhone"/> </td>
          
            <td align="left" valign="top" style="font-family:verdana; font-size:11px;"><B> Fax: &nbsp; </B><bean:write name="siteForm" property="siteFax"/> </td>
          </tr>
          <tr>
            <td align="left" valign="top" style="font-family:verdana; font-size:11px; font-weight:bold"> Email: </td>
            <td align="left" valign="top" style="font-family:verdana; font-size:11px;"><a href="mailto:<bean:write name="siteForm" property="email"/>"><bean:write name="siteForm" property="email"/></a> </td>
    </tr>
    </table></td>
  
  </tr>
  
  <TR>
    <TD colspan="2">
  
  
  <TABLE width="550">

  <tr>
    <td align="left" valign="top">
    
    <table width="100%" cellpadding="0" cellspacing="0" border="0" style="border:1px solid #000">
	<tr>
	  	<td align="left" valign="middle" nowrap>
			  <span style="font-family:verdana; font-size:11px; font-weight:bold">
			   <logic:notEmpty name="siteForm" property="actualFpfvDt">
			     Actual
			   </logic:notEmpty>
			   <logic:empty name="siteForm" property="actualFpfvDt">
			     Planned
			   </logic:empty>
			   FPFV </span>&nbsp;&nbsp;
			  <span style="font-family:verdana; font-size:11px;">
			   <logic:notEmpty name="siteForm" property="actualFpfvDt">
			    <bean:write  name="siteForm"  property="actualFpfvDt" format= "dd-MMM-yyyy"/>
			   </logic:notEmpty>
			   <logic:empty name="siteForm" property="actualFpfvDt">
			     <bean:write name="siteForm" property="plannedFpfvDt" format="dd-MMM-yyyy"/>
			   </logic:empty>
			  </span>
		  </td>
	  	<td align="left" valign="middle">
			  <span style="font-family:verdana; font-size:11px; font-weight:bold">
			    <logic:notEmpty name="siteForm" property="actualLplvDt">
			     Actual
			   </logic:notEmpty>
			   <logic:empty name="siteForm" property="actualLplvDt">
			     Planned
			   </logic:empty>
			    LPLV </span>&nbsp;&nbsp;
			  <span style="font-family:verdana; font-size:11px;">
			    <logic:notEmpty name="siteForm" property="actualLplvDt">
			    <bean:write  name="siteForm"  property="actualLplvDt" format= "dd-MMM-yyyy"/>
			   </logic:notEmpty>
			   <logic:empty name="siteForm" property="actualLplvDt">
			     <bean:write name="siteForm" property="plannedLplvDt" format="dd-MMM-yyyy"/>
			   </logic:empty>
			  </span>
		  </td>
		  <!-- SDV Lock Date -->
	  	<td align="left" valign="middle">
			  <span style="font-family:verdana; font-size:11px; font-weight:bold">
			    <logic:notEmpty name="siteForm" property="actualSdvDate">
			     Actual
			   </logic:notEmpty>
			   <logic:empty name="siteForm" property="actualSdvDate">
			     Planned
			   </logic:empty>
			    SDV </span>&nbsp;&nbsp;
			  <span style="font-family:verdana; font-size:11px;">
			    <logic:notEmpty name="siteForm" property="actualSdvDate">
			    <bean:write  name="siteForm"  property="actualSdvDate" format= "dd-MMM-yyyy"/>
			   </logic:notEmpty>
			   <logic:empty name="siteForm" property="actualSdvDate">
			     <bean:write name="siteForm" property="plannedSdvDt" format="dd-MMM-yyyy"/>
			   </logic:empty>
			  </span>
		  </td>		  
	</tr>
   </TABLE>
</TD>
</TR>
</table>
</table>


<tr>
    <td colspan="4"><hr width="100%" height="0" style="border:1px black"/></td>
</tr>
  