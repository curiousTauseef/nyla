<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://gcsm.bms.com/tld/security.tld" prefix="security"%>
<%@page contentType="text/html; charset=windows-1252"%>

<table id="left_nav_tbl" cellpadding="1" cellspacing="1" border="0" style="width: 160px; background-color:#FFF">
  <c:choose>
    <c:when test='${landingTab != "SMP" && (param.selectTab == null || empty param.selectTab || param.selectTab == "smpList")}'>
      <tr>
        <td class="leftNavOn">
          Site Monitoring Plan List
        </td>
      </tr>
    </c:when>
    <c:otherwise>
      <tr>
        <td class="leftNavOff">
			<a href='<html:rewrite page="/do/SMPListAction?method=list&amp;landingPage=smp&amp;selectTab=smpList"/>' class="leftNavOff">
				Site Monitoring Plan List
			</a>
        </td>
      </tr>
    </c:otherwise>
  </c:choose>

  <security:guard permission="CreateSMP">
  <c:choose>
    <c:when test='${landingTab == "SMP" || param.selectTab == "smp"}'>
      <tr>
        <td class="leftNavOn">
          Site Monitoring Plan
        </td>
      </tr>
    </c:when>
    <c:otherwise>
      <tr>
        <td class="leftNavOff">
			<a href='<html:rewrite page="/do/SMPFormAction?op=createSMP&amp;landingPage=smp&amp;selectTab=smp"/>' class="leftNavOff">
				Site Monitoring Plan
			</a>
        </td>
      </tr>
    </c:otherwise>
  </c:choose>
  </security:guard>
</table>
