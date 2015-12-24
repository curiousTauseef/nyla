<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page contentType="text/html; charset=windows-1252"%>

<script>

function openSitePopUp(url)
{

url="<html:rewrite page="/do/MyFormPanelAction?method=viewSites&selectTab=myForm"/>";

window.open(url, "gcsm", "width=350, height=150,left=177, top=262,resizable=no, scrollbars=yes, location=no, status=no, directories=no, menubar=no");

}

</script>

<table id="left_nav_tbl" cellpadding="1" cellspacing="1" border="0" style="width: 160px; background-color:#FFF">
  <c:choose>
    <c:when test='${param.selectTab == null || empty param.selectTab || param.selectTab == "myForm"}'>
      <tr>
        <td class="leftNavOn">
          <bean:message key="gcsm.top.navigation.ssc"/>
        </td>
      </tr>
    </c:when>
    <c:otherwise>
      <tr>
        <td class="leftNavOff">
	<a href='<html:rewrite page="/do/MyFormPanelAction?method=initMyForm&landingPage=ssc&selectTab=myForm" />' class="leftNavOff">
		<bean:message key="gcsm.top.navigation.ssc"/>
	</a>
        </td>
      </tr>
    </c:otherwise>
  </c:choose>

  <c:choose>
    <c:when test='${param.selectTab == "sasc"}'>
      <tr>
        <td class="leftNavOn">
          <bean:message key="bms.gcsm.site.leftnav.significant"/>
        </td>
      </tr>
    </c:when>
    <c:otherwise>
      <tr>
        <td class="leftNavOff">
            <a class="leftNavOff" href="#" onclick="javascript:openSitePopUp();return false;">
		<bean:message key="bms.gcsm.site.leftnav.significant"/>
	    </a>
        </td>
      </tr>
    </c:otherwise>
  </c:choose>
</table>


