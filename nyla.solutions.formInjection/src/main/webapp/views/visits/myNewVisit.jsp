<%@ page import="java.util.Collection,
                 java.util.Iterator,
                  com.bms.informatics.gcsm.common.web.util.*"%>
<%@ taglib uri="http://myfaces.sourceforge.net/tld/myfaces_ext_0_9.tld" prefix="x"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>


<f:view id="newVisit">
	<h:form>
	<f:verbatim>
		<table cellpadding="1" cellspacing="1" border="0">


		<tr>
		<td >I/U/D/P</td>
		<td>
	</f:verbatim>
		<h:inputText value="#{myVisitAction.newVisit.operationFlag}"></h:inputText>
	<f:verbatim>	
		</td>
		</tr>
		<tr>
		<td >S-V-ID</td>
		<td>
	</f:verbatim>
		<h:inputText value="#{myVisitAction.newVisit.scheduleVisitID}"></h:inputText>
	<f:verbatim>	
		</td>
		</tr>
		<tr>
		<td >Inv-ID</td>
		<td>
	</f:verbatim>
		<h:inputText value="#{myVisitAction.newVisit.investigatorID}"></h:inputText>
	<f:verbatim>	
		</td>
		</tr>
		




		<tr>
		<td >Protocol</td>
		<td>
	</f:verbatim>
		<h:selectOneMenu value="#{myVisitAction.newVisit.protocolID}" styleClass="portFilter">
		<f:selectItems value="#{myVisitAction.protocolList}"/>
		</h:selectOneMenu>
	<f:verbatim>                            
		</td>
		</tr>





		<tr>
		<td >Site</td>
		<td>
	</f:verbatim>
		<h:selectOneMenu value="#{myVisitAction.newVisit.siteID}" styleClass="portFilter">
		<f:selectItems value="#{myVisitAction.siteList}"/>
		</h:selectOneMenu>
	<f:verbatim>                            
		</td>
		</tr>
		<tr>
		<td>Visit Type*</td>
		<td>
	</f:verbatim>
		<h:selectOneMenu value="#{myVisitAction.newVisit.visitTypeID}" styleClass="portFilter">
		<f:selectItems value="#{myVisitAction.visitList}"/>
		</h:selectOneMenu>
	<f:verbatim>
		</td>
		<tr height="90">
		<td colspan="2" align="center" valign="middle">
		<%-- Date JavaScript --%>
		<iframe width=194 height=189 name="gToday:normal:agenda.js:gfFlat_arrDate" id="gToday:normal:agenda.js:gfFlat_arrDate" src="/gcsm/skins/default/js/calendar/iflateng.htm" scrolling="no" frameborder="0">
		</iframe>
		<br>
    </f:verbatim>
        <h:inputHidden id="arrDate" />
     <f:verbatim>
		</td>
		</tr>
		

		<tr>
		<%-- Times --%>
		<td>
		<table cellpadding="0" cellspacing="0" border="0">
		<tr>
		<td colspan="2" align="left" valign="middle"><strong>Start Time</strong></td>
		</tr>
		<tr>
		<td width="25">&nbsp;</td>
		<td>
	</f:verbatim>
	    <h:selectOneMenu id="sHour"  value="#{myVisitAction.newVisit.startingHour}">
	    <f:selectItems value="#{myVisitAction.startHour}"/>
	    </h:selectOneMenu>
	    <h:selectOneMenu id="sMin" value="#{myVisitAction.newVisit.startingMinute}">
	    <f:selectItems value="#{myVisitAction.startMin}"/>
	    </h:selectOneMenu>			             
	<f:verbatim>
		</td>
		</tr>


		<tr>
		<td colspan="2" align="left" valign="middle"><strong>End Time</strong></td>
		</tr>
		<tr>
		<td width="20">&nbsp;</td>
		<td>
	</f:verbatim>
	    <h:selectOneMenu id="eHour" value="#{myVisitAction.newVisit.endingHour}">
	    <f:selectItems value="#{myVisitAction.endHour}"/>
		</h:selectOneMenu>
		<h:selectOneMenu  id="eMin" value="#{myVisitAction.newVisit.endingMinute}">
		<f:selectItems value="#{myVisitAction.endMin}"/>
		</h:selectOneMenu>		
	<f:verbatim>
		</td>
		</tr>
		</table>
		</td>
		</tr>
	

		<tr>
		<%-- HR Rule --%>
		<td align="center" valign="middle"><hr width="85%"></td>
		</tr>



	
	
	
	
	
	
	

		</table>
	</f:verbatim>
		<h:commandButton action="#{myVisitAction.takeAction}" value="Submit" />
		</h:form>
</f:view>