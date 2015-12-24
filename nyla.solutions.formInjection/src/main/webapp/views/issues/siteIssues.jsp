<%@ taglib uri="http://myfaces.sourceforge.net/tld/myfaces_ext_0_9.tld" prefix="x"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ page import="com.bms.informatics.gcsm.common.util.*,com.bms.informatics.gcsm.common.web.util.*, java.util.*,com.bms.informatics.gcsm.issue.web.*" %>

<f:subview id="issues">
<h:form id="issueForm" name="issueForm">
        <%-- Protlet Container --%>
<f:verbatim>
            <table align="left" border="0" cellpadding="0" cellspacing="0" width="100%">
                <%-- Header --%>
                <tr>
                    <td class="portTitle" height="29">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr class="portFilterLbl">
                                <td width="15"><img src="/gcsm/skins/default/images/phead_left.gif"></td>
                                <td class="portTitle" width="30">ISSUES</td>
                                <td width="20"><img src="/gcsm/skins/default/images/icon_issues.gif"></td>
                                <td width="10"><img src="/gcsm/skins/default/images/blank.gif" width="10" height="1"></td>
                                <td width="30" class="portFilterLbl">Protocol</td>
                                <td width="40">
</f:verbatim>
				  <h:selectOneMenu value="#{issuePanel.protocolFilter}" id="protocolFilter" onchange="processFilters(this.form, this.form.name+':protocolFilter,'+this.form.name+':siteFilter,'+this.form.name+':priorityFilter,'+this.form.name+':classificationFilter', this.form.name+':issueTable');" immediate="true" styleClass="portFilter">
				  <f:selectItems value="#{issuePanel.protocolList}"/>
			          </h:selectOneMenu>
<f:verbatim>
                                </td>
                                <td width="10"><img src="/gcsm/skins/default/images/blank.gif" width="10" height="1"></td>
                                <td width="20" class="portFilterLbl">Site</td>
                                <td width="40">
</f:verbatim>
				   <h:selectOneMenu value="#{issuePanel.siteFilter}" id="siteFilter" onchange="processFilters(this.form, this.form.name+':protocolFilter,'+this.form.name+':siteFilter,'+this.form.name+':priorityFilter,'+this.form.name+':classificationFilter', this.form.name+':issueTable');" immediate="true" styleClass="portFilter">
				   <f:selectItems value="#{issuePanel.siteList}"/>
				   </h:selectOneMenu>  
<f:verbatim>
                                </td>
                                <td width="10"><img src="/gcsm/skins/default/images/blank.gif" width="10" height="1"></td>
                                <td width="15" class="portFilterLbl">Priority</td>
                                <td width="50">
                                </f:verbatim>
				   <h:selectOneMenu value="#{issuePanel.priorityFilter}" id="priorityFilter" onchange="processFilters(this.form, this.form.name+':protocolFilter,'+this.form.name+':siteFilter,'+this.form.name+':priorityFilter,'+this.form.name+':classificationFilter', this.form.name+':issueTable');" immediate="true" styleClass="portFilter">
				   <f:selectItems value="#{issuePanel.priorityList}"/>
				   </h:selectOneMenu>
<f:verbatim>
				</td>
                                <td width="10"><img src="/gcsm/skins/default/images/blank.gif" width="10" height="1"></td>
                                <td width="40" class="portFilterLbl">Classification</td>
                                <td width="70">
                                </f:verbatim>
				   <h:selectOneMenu value="#{issuePanel.classificationFilter}" id="classificationFilter" onchange="processFilters(this.form, this.form.name+':protocolFilter,'+this.form.name+':siteFilter,'+this.form.name+':priorityFilter,'+this.form.name+':classificationFilter', this.form.name+':issueTable');" immediate="true" styleClass="portFilter">
				   <f:selectItems value="#{issuePanel.classificationList}"/>
				   </h:selectOneMenu>    
<f:verbatim>
                                </td>
                                <td width="10"><img src="/gcsm/skins/default/images/blank.gif" width="10" height="1"></td>

              				<td><img src="/gcsm/skins/default/images/blank.gif" width="10" height="1"></td>
							<td width="100%">&nbsp;</td>
                            <td width="70" align="right"><img src="/gcsm/skins/default/images/button_postissue_on.gif" style="cursor:hand">&nbsp;</td>
							<td><img src="/gcsm/skins/default/images/phead_expand_on.gif" onclick="resizePortlet('issues');" ></td>
							<td><img src="/gcsm/skins/default/images/phead_rt.gif"></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div id="inner_issues" style="overflow: auto; height: 100px; border: solid 1px #CCCCFF">
</f:verbatim>
	      <h:dataTable value="#{issuePanel.issueDetailList}" id="issueTable"
			 var="issueRow"
			 headerClass="jsfPortColHdr"
			 rowClasses="portBody"
			 border="0" cellspacing="0" cellpadding="0" style="width:100%">  
	      <h:column>
		<f:facet name="header">
		  <h:commandLink actionListener="#{issuePanel.sortByProtocol}" immediate="true" value="Protocol">
		  </h:commandLink> 					    
		</f:facet>
		<h:outputText value="#{issueRow.protocol}"/>
		</h:column>
	      <h:column>
		  <h:outputText value="    "/>
	      </h:column>	
	      <h:column>
		<f:facet name="header">
		  <h:commandLink actionListener="#{issuePanel.sortBySite}" immediate="true" value="Site">
		  </h:commandLink> 					    
		</f:facet>
		<h:outputText value="#{issueRow.site}"/>
		</h:column>
	      <h:column>
		  <h:outputText value="    "/>
	      </h:column>		      
	      <h:column>
		<f:facet name="header">
		  <h:commandLink actionListener="#{issuePanel.sortByPriority}" immediate="true" value="Priority">
		  </h:commandLink> 					    
		</f:facet>
		<h:outputText value="#{issueRow.priority}"/>
		</h:column>
	      <h:column>
		  <h:outputText value="    "/>
	      </h:column>
	      <h:column>
		<f:facet name="header">
		  <h:commandLink actionListener="#{issuePanel.sortByClassification}" immediate="true" value="Classification">
		  </h:commandLink> 					    
		</f:facet>
		<h:outputText value="#{issueRow.classification}"/>
		</h:column>
	      <h:column>
		  <h:outputText value="    "/>
	      </h:column>
	      <h:column>
		 <f:facet name="header">
		 <h:outputText value="Description"/>
		 </f:facet>                              
		 <h:outputText value="#{issueRow.description}"/>
	      </h:column>
	      <h:column>
		  <h:outputText value="  "/>
	      </h:column>
	      <h:column>
		 <f:facet name="header">
		 <h:outputText value="Report"/>
		 </f:facet>                              
		 <h:outputText value="#{issueRow.report}"/>
	      </h:column>
	      <h:column>
		  <h:outputText value="  "/>
	      </h:column>
	      <h:column>
		 <f:facet name="header">
		 <h:outputText value="Contact Date"/>
		 </f:facet>                              
		 <h:outputText value="#{issueRow.contactDate}"/>
	      </h:column>
	      <h:column>
		  <h:outputText value="  "/>
	      </h:column>
	      <h:column>
		 <f:facet name="header">
		 <h:outputText value="Originator"/>
		 </f:facet>                              
		 <h:outputText value="#{issueRow.originator}"/>
	      </h:column>
	      <h:column>
		  <h:outputText value="  "/>
	      </h:column>
	      <h:column>
		<f:facet name="header">
		  <h:commandLink actionListener="#{issuePanel.sortByResolver}" immediate="true" value="Resolver">
		  </h:commandLink> 					    
	      </f:facet>
	      <h:outputText value="#{issueRow.resolver}"/>
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
