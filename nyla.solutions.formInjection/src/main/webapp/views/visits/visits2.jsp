<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ page import="com.bms.informatics.gcsm.common.util.*,com.bms.informatics.gcsm.common.web.util.*, java.util.*, com.bms.informatics.gcsm.schedule.web.*" %>

<f:subview id="visits">
    <h:form id="visitForm" name="visitForm">
        <f:verbatim>
            <script language="JavaScript">
        		<!--
        		function newVisitSchedule() {
        			var url = "";
        			<%
        					if (request.getParameter("landingPage") != null && request.getParameter("landingPage").equalsIgnoreCase("sites"))
        						out.println("url = \"/gcsm/themes/default/newVisit.jsf?siteProtocol="+ request.getParameter("siteProtocol") + "&siteDetail="+ request.getParameter("siteDetail") +"\" ;");
    						else
    							out.println("url = \"/gcsm/themes/default/newVisit.jsf\" ;");
					%>
        			window.open(url, "newVisitSchedule", "width=390, height=500, resizable=no, scrollbars=no, location=no, status=no, directories=no, menubar=no");
        		}
        		-->
        	</script>
        </f:verbatim>
        <f:verbatim>
            <table align="left" border="0" cellpadding="0" cellspacing="0" width="100%">
                <tr>
                    <td class="portTitle" height="27">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr class="portFilterLbl">
                                <td width="15" style="background-color:#FFF"><img src="/gcsm/skins/default/images/phead_left.gif"></td>
                                <td class="portTitle" nowrap>SCHEDULED VISITS</td>
                                <td align="left"><img src="/gcsm/skins/default/images/icon_schedule.gif" style="width:25px;height:25px;"></td>
                                <td width="100%">&nbsp;</td>
                                <td width="15"><img src="/gcsm/skins/default/images/button_new_on.gif" onclick="newVisitSchedule()" style="cursor:hand"></td>
                                <td width="15"><img src="/gcsm/skins/default/images/phead_expand_on.gif"  onclick="resizePortlet('visits');"></td>
                                <td width="15" style="background-color:#FFF"><img src="/gcsm/skins/default/images/phead_rt.gif"></td>
                            </tr>
                        </table>
                    </td>
                </tr>

                <tr>
                    <td>
                        <div id="inner_visits" style="overflow:auto; left:0px; top:0px; width:100%; height:100px; background-color:#ffffff; overflow:auto; border: solid 1px #CCCCFF">
        </f:verbatim>
                <h:dataTable value="#{visitPanel.visitDetailList}"  id="visitTable"
                                             var="visitRow"
                                             headerClass="jsfPortColHdr"
                                             rowClasses="portBody"
                                             columnClasses="jsfPortBody"
                                             border="0" cellspacing="0" cellpadding="0" width="100%">
                      <h:column>
                        <f:facet name="header">
                            <h:outputText value="Date/Time"/>
                        </f:facet>
                        <h:outputText value="#{visitRow.strDate}"/>                         

                      </h:column>
                      <h:column>
                        <f:facet name="header">
                            <h:commandLink actionListener="#{visitPanel.sortByVisitType}" immediate="true" value="Visit">
                            <f:param name="sortBy" value="byvisit" />
                            </h:commandLink>
                        </f:facet>
                        <h:outputText value="#{visitRow.strVisitType}"/>
                      </h:column>
                       <h:column>
                        <f:facet name="header">
                          <h:commandLink actionListener="#{visitPanel.sortByProtocol}" value="Protocol/Site">
                          </h:commandLink>
                        </f:facet>
                        <h:outputText value="#{visitRow.strProtocol}"/>
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