<%@ taglib uri="http://myfaces.sourceforge.net/tld/myfaces_ext_0_9.tld" prefix="x"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="com.bms.informatics.gcsm.common.web.util.*, java.util.*" %>

<f:view>
	<h:outputText value="Hello World"/>
	
	<jsp:useBean id="myBean" class="com.bms.informatics.gcsm.common.web.util.UIPage" scope="session">
		<%
		
			if(!myBean.getPage().containsKey("experiment")) {
				UITile tile = new UITile();
				tile.setId ("experiment");
				
				tile.setData( MockUtils.getSomeSiteDetails() );
				tile.setDisplayData( tile.getData() );
				
				myBean.addTile( tile ); 
			}
		%>
	</jsp:useBean>
	<%
			/*
			
			//out.println(" sortProperty = " + request.getParameter("sortProperty"));
				
			out.println("\n HELLO MOTO");
			if(request.getParameter("sortProperty")!= null) {
			
				if(((UITile)myBean.getPage().get("experiment")).getLookup().get("sortProperty") != null) {
					((UITile)myBean.getPage().get("experiment")).getLookup().remove("sortProperty");
				}
				
				((UITile)myBean.getPage().get("experiment")).addProperty("sortProperty",request.getParameter("sortProperty"));
			}
			
			*/
	%>
	<%
		for(Enumeration i = request.getParameterNames(); i.hasMoreElements();) {
			String parameter = i.nextElement().toString();
			if(((UITile)myBean.getPage().get("experiment")).getLookup().get(parameter) != null) {
					((UITile)myBean.getPage().get("experiment")).getLookup().remove(parameter);
				}
				
				((UITile)myBean.getPage().get("experiment")).addProperty(parameter,request.getParameter(parameter));
		
		
		}
		
	%>
	<h:outputLink id="protocol1" value="/gcsm/themes/default/example.jsf">
		<h:outputText value="Click Me"/>
		<f:param name="sortProperty" value="strProtocol" /> 		
	</h:outputLink>
	
	<h:outputLink id="protocol2" value="/gcsm/themes/default/example.jsf">
		<h:outputText value="Click Me"/>
		<f:param name="sortProperty" value="strValue" /> 		
	</h:outputLink>
	
	<h:outputLink id="protocol3" value="/gcsm/themes/default/example.jsf">
		<h:outputText value="Click Me"/>
		<f:param name="sortName" value="strDonkey" /> 		
	</h:outputLink>
	
	<h:outputLink id="protocol4" value="/gcsm/themes/default/example.jsf">
		<h:outputText value="Click Me"/>
	</h:outputLink>
	<f:verbatim><p></f:verbatim>
	<%
		for(Enumeration i = myBean.getPage().keys(); i.hasMoreElements();) {
			out.println(i.nextElement());
		}
		
		for(Enumeration i = ((UITile) myBean.getPage().get("experiment")).getLookup().elements(); i.hasMoreElements(); )
			out.println(i.nextElement());
	 %>
</f:view>
