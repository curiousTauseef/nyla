<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page contentType="text/html; charset=windows-1252"%>
<table id="left_nav_tbl" cellpadding="1" cellspacing="1" border="0" style="width: 160px; background-color:#FFF">
  <c:choose>
    <c:when test='${landingTab != "PSA" && (param.selectTab == null || empty param.selectTab || param.selectTab == "researchSite")}'>
      <tr>
        <td class="leftNavOn">
          Research Site
        </td>
      </tr>
    </c:when>
    <c:otherwise>
      <tr>
        <td class="leftNavOff">
			<a href='<html:rewrite page="/do/ResearchSitePanelAction?method=init&landingPage=psi"/>' class="leftNavOff">
				Research Site
			</a>
        </td>
      </tr>
    </c:otherwise>
  </c:choose>
  
                          <tr>
                          <td style="height: 8px; background-color: #CCCCFF;"></td>
                        </tr>
                          <tr>
                            <td class="leftNavOff"><table width="100%"  border="0" cellspacing="0" cellpadding="2">
                              <tr>
                                <td class="psiNavHeader">Research Tip </td>
                              </tr>
                              <tr>
                               <td class="psiNavText">Ineligible/restricted to<br>
                                 receive IP list<br>
                                 <br>
                                 FDA List of Disqualified and<br>
                                 Restricted Clinical<br>
                                 Investigators (Debarred List)<br>
                                 <br>
                                 Routine inspection for cause<br>
                                 list, with an official action<br>
                                 indicated (OAI)<br>
                                 <br>
                                 List of Excluded Individuals /<br>
                                 Entities (LEIE)</td>
                              </tr>
                            </table></td>
                          </tr>
</table>


<%--
<table width="160px"  border="0" cellspacing="0" cellpadding="0">
  <tr>
        <td width="100%" height="5" align="left" valign="top" bgcolor="#CCCCFF">
         <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td bgcolor="#CCCCFF" align="left" valign="top"><table width="100%" cellpadding="0" cellspacing="0" border="0">
                        <tr>
                          <c:choose>
                            <c:when test='${param.selectTab == null || empty param.selectTab || param.selectTab == "researchSite"}'>
                              <td style="height:25px; border-bottom:2px solid #FFF" class="leftNavSelected">Research Site </td>
                            </c:when>
                            <c:otherwise>
                              <td style="height:25px; border-bottom:2px solid #FFF" class="leftNav">
                                <a href='<html:rewrite page="/do/psi?landingPage=psi&amp;selectTab=researchSite"/>' class="leftNav">Research Site </a>
                              </td>
                            </c:otherwise>
                          </c:choose>
                        </tr>
                        <tr>
                          <td class="leftNav">&nbsp;</td>
                        </tr>
                          <tr>
                            <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td class="psiNavHeader">Research Tip </td>
                              </tr>
                              <tr>
                               <td class="psiNavText">Ineligible/restricted to<br>
                                 receive IP list<br>
                                 <br>
                                 FDA List of Disqualified and<br>
                                 Restricted Clinical<br>
                                 Investigators (Debarred List)<br>
                                 <br>
                                 Routine inspection for cause<br>
                                 list, with an official action<br>
                                 indicated (OAI)<br>
                                 <br>
                                 List of Excluded Individuals /<br>
                                 Entities (LEIE)</td>
                              </tr>
                            </table></td>
                          </tr>
                        <tr>
                          <td class="leftNav" style="height:30px">&nbsp;</td>
                        </tr>
                        <tr>
                          <td class="leftNav">&nbsp;</td>
                        </tr>
                  </table></td>
                </tr>
          </table>
        </td>
  </tr>
</table>
--%>
