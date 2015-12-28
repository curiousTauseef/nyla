<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://gcsm.bms.com/tld/security.tld" prefix="security"%>
<%@page contentType="text/html; charset=windows-1252"%>
<style type="text/css">
  .links {padding-left: 20px}
  .links:hover { color: red; }
</style>
<bean:define id="siteID" value="<%=request.getParameter("siteID")%>" scope="page"/>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td colspan="2" align="left" valign="top">
      <table width="100%" height="305" border="0" cellpadding="0" cellspacing="0">
	<tr>
	  <td><h3 class="reportsLink">Letters</h3></td>
	</tr>
	<tr>
	  <td><html:link paramId="siteId" paramName="siteID" styleClass="links"
	    href="/gcsm/do/LetterAction?method=doLetterAction&preOrPost=PRE&visitTypeCd=SIV">Site based Pre Visit (SIV)</html:link></td>
	</tr>
	<tr>
	  <td><html:link paramId="siteId" paramName="siteID" styleClass="links"
	    href="/gcsm/do/LetterAction?method=doLetterAction&preOrPost=POST&visitTypeCd=SIV">Site based POST (SIV)</html:link></td>
	</tr>
	<tr>
	  <td>
	    <html:link href="/gcsm/do/LetterAction?method=doLetterAction&visitTypeCd=SMV&preOrPost=PRE"
		       paramId="siteId" paramName="siteID" styleClass="links">
	      Site based Pre Visit (SMV)
	    </html:link>
	  </td>
	</tr>
	<tr>
	  <td><html:link paramId="siteId" paramName="siteID" styleClass="links"
	    href="/gcsm/do/LetterAction?method=doLetterAction&preOrPost=POST&visitTypeCd=SMV">Site based POST (SMV)</html:link></td>
	</tr>
	<tr>
	  <td><html:link paramId="siteId" paramName="siteID" styleClass="links"
	    href="/gcsm/do/LetterAction?method=doLetterAction&preOrPost=PRE&visitTypeCd=PSA">Site based Pre Visit (PSA)</html:link></td>
	</tr>
	<tr>
	  <td><html:link paramId="siteId" paramName="siteID" styleClass="links"
	    href="/gcsm/do/LetterAction?method=doLetterAction&preOrPost=POST&visitTypeCd=PSA">Site based POST (PSA)</html:link></td>
	</tr>
	<tr>
	  <td><html:link paramId="siteId" paramName="siteID" styleClass="links"
	    href="/gcsm/do/LetterAction?method=doLetterAction&preOrPost=PRE&visitTypeCd=PSE">Site based Pre Visit (PSE)</html:link></td>
	</tr>
	<tr>
	  <td><html:link paramId="siteId" paramName="siteID" styleClass="links"
	    href="/gcsm/do/LetterAction?method=doLetterAction&preOrPost=POST&visitTypeCd=PSE">Site based POST (PSE)</html:link></td>
	</tr>
	<tr>
	  <td><html:link paramId="siteId" paramName="siteID" styleClass="links"
	    href="/gcsm/do/LetterAction?method=doLetterAction&preOrPost=PRE&visitTypeCd=SCV">Site based Pre Visit (SCV)</html:link></td>
	</tr>
	<tr>
	  <td><html:link paramId="siteId" paramName="siteID" styleClass="links"
	    href="/gcsm/do/LetterAction?method=doLetterAction&preOrPost=POST&visitTypeCd=SCV">Site based POST (SCV)</html:link></td>
	</tr>
	<tr>
	  <td><html:link paramId="siteId" paramName="siteID" styleClass="links"
	    href="/gcsm/do/LetterAction?method=doLetterAction&preOrPost=POST&VisitTypeCd=PHONE">SiteBased (PHONE)</html:link></td>
	</tr>
	<tr>
	  <td><html:link paramId="siteId" paramName="siteID" styleClass="links"
	    href="/gcsm/do/LetterAction?method=doLetterAction&preOrPost=POST&VisitTypeCd=UNBLINDED">SiteBased (UNBLINDED)</html:link></td>
	</tr>
	<tr>
	  <td>&nbsp;</td>
	</tr>
	<tr>
	  <td>&nbsp;</td>
	</tr>
      </table></td>
  </tr>
</table>
