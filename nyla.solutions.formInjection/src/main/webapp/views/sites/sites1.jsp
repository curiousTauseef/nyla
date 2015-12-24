<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://myfaces.sourceforge.net/tld/myfaces_ext_0_9.tld" prefix="x"%>
<%@ page import="com.bms.informatics.gcsm.common.util.*,com.bms.informatics.gcsm.common.web.util.*, java.util.*,com.bms.informatics.gcsm.protocol.web.*" %>
<f:subview id="sites">
<h:form id="siteForm">
<f:verbatim>
    <table width="99%" cellpadding="0" cellspacing="0" border="0" id="site_body" align="left">
        <tr>
            <td>
                <table cellpadding="0" cellspacing="0" width="100%"  border="0">
                      <tr>
                            <td>
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr class="portFilterLbl" align="left" valign="middle">
                                        <td><img src="/gcsm/skins/default/images/phead_left.gif"></td>
                                        <td class="portTitle" width="30">SITES </td>
                                        <td><img src="/gcsm/skins/default/images/phead_sites_icon.gif"></td>
                                        <td><img src="/gcsm/skins/default/images/blank.gif" width="10" height="1"></td>
                                        <td class="portFilterLbl">&nbsp;Sites&nbsp;&nbsp;</td>
                                        <td>
</f:verbatim>
                                          <h:selectOneMenu value="#{sitePanel.siteFilter}" id="siteFilter" onchange="processFilters(this.form, 'siteForm:siteFilter,siteForm:statusFilter', 'siteForm:siteTable');" immediate="true" styleClass="portFilter">
                                        <f:selectItems value="#{sitePanel.siteList}"/>
                                        </h:selectOneMenu>
<f:verbatim>
                                        </td>
                                        <td><img src="/gcsm/skins/default/images/blank.gif" width="10" height="1"></td>
                                        <td class="portFilterLbl">&nbsp;Statusl&nbsp;&nbsp;</td>
                                        <td>
</f:verbatim>
                                      <h:selectOneMenu value="#{sitePanel.statusFilter}"
                                                id="statusFilter"
                                                onchange="processFilters(this.form, 'siteForm:siteFilter,siteForm:statusFilter', 'siteForm:siteTable');"
                                                    immediate="true" styleClass="portFilter">
                                    <f:selectItems value="#{sitePanel.statusList}"/>
                                    </h:selectOneMenu>

<f:verbatim>
                                        </td>
                                        <td><img src="/gcsm/skins/default/images/blank.gif" width="10" height="1"></td>
                                        <td width="100%">&nbsp;</td>
                                        <td><img src="/gcsm/skins/default/images/phead_expand_on.gif" onclick="resizePortlet('sites');" ></td>
                                        <td><img src="/gcsm/skins/default/images/phead_rt.gif"></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                </table>
              </td>
            </tr>
            <tr>
            <td>
            <div id="inner_sites" style="overflow:auto; left:0px; top:0px; width:100%; height:100px; background-color:#ffffff; overflow:auto; border: solid 1px #CCCCFF">
</f:verbatim>
                <h:dataTable value="#{sitePanel.siteDetailList}" id="siteTable"
                                             var="siteRow"
                                             headerClass="jsfPortColHdr"
                                             rowClasses="portBody"
                                             columnClasses="jsfPortBody"
                                             border="0" cellspacing="0" cellpadding="0" width="100%">
                <h:column>
                    <f:facet name="header">
                        <h:commandLink actionListener="#{sitePanel.sortByProtocol}" immediate="true" value="Protocol">
                    </h:commandLink>
                    </f:facet>
                    <h:outputText value="#{siteRow.protocol}"/>
                </h:column>
                <h:column>
                    <h:outputText value="  "/>
                </h:column>
                <h:column>
                    <f:facet name="header">
                    <h:commandLink actionListener="#{sitePanel.sortBySite}" immediate="true" value="Site">
                    </h:commandLink>
                    </f:facet>
                    <h:commandLink value="#{siteRow.site}" action="#{sitePanel.setSite}" immediate="true">
                        <f:param name="siteID" value="#{siteRow.siteId}"/>
                        <f:param name="landingPage" value="sites"/>
                        <f:param name="siteProtocol" value="#{siteRow.protocol}"/>
                        <f:param name="siteDetail" value="#{siteRow.site}"/>
                    </h:commandLink>

<%--                    <h:outputLink value="sites.jsf">--%>
<%--                        <f:param name="landingPage" value="sites"/>--%>
<%--                        <f:param name="siteProtocol" value="#{siteRow.protocol}"/>--%>
<%--                         <f:param name="siteDetail" value="#{siteRow.site}"/>--%>
<%--                        <h:outputText value="#{siteRow.site}"/>--%>
<%--                    </h:outputLink>--%>


<%--                    <f:verbatim><a href="sites.jsf?landingPage=sites&siteProtocol=</f:verbatim>--%>
<%--                    <h:outputText value="#{siteRow.protocol}" />--%>
<%--                    <f:verbatim>&siteDetail="</f:verbatim>--%>
<%--                    <h:outputText value="#{siteRow.site}"/>--%>
<%--                    <f:verbatim>"></f:verbatim>--%>
<%--                    <h:outputText value="#{siteRow.site}"/>--%>
<%--                    <f:verbatim></a></f:verbatim>--%>
                </h:column>
<%--                <h:column>--%>
<%--                      <h:outputText value="  "/>--%>
<%--                </h:column>--%>
                <h:column>
                      <f:facet name="header">
                      <h:outputText value="Investigator"/>
                      </f:facet>
                      <h:outputText value="#{siteRow.investigator}"/>
                </h:column>
<%--                <h:column>--%>
<%--                      <h:outputText value="  "/>--%>
<%--                </h:column>--%>
                <h:column>
                    <f:facet name="header">
                    <h:commandLink actionListener="#{sitePanel.sortByStatus}" immediate="true" value="Status">
                    </h:commandLink>
                    </f:facet>
                    <h:outputText value="#{siteRow.status}" styleClass="standardTable_ColumnCentered" />
                </h:column>
<%--                <h:column>--%>
<%--                    <h:outputText value=""/>--%>
<%--                </h:column>--%>
                <h:column>
                     <f:facet name="header">
                     <h:outputText value="Close Date"/>
                     </f:facet>
                     <h:outputText value="#{siteRow.closeDate}"/>
                </h:column>
<%--                <h:column>--%>
<%--                    <h:outputText value=""/>--%>
<%--                </h:column>--%>
                <h:column>
                    <f:facet name="header">
                    <h:outputText value="Enrolled / Randomized"/>
                    </f:facet>
                    <h:outputText value="#{siteRow.enrolledRandomized}"/>
                </h:column>
                </h:dataTable>
<f:verbatim>
            </div>
            </td>
            </tr>
           </table>
</f:verbatim>
</h:form>
</f:subview>

