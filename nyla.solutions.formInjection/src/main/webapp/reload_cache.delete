<%@ page import="com.bms.informatics.gcsm.common.web.CacheManager,
				 com.bms.informatics.gcsm.common.cache.CacheService,
				 com.bms.informatics.gcsm.common.cache.CacheLocator,
				 java.util.Date" %>
<%
	if ("full".equals(request.getParameter("reload"))) {
		CacheService controller = CacheLocator.getCacheController();
		controller.reload();
		Date d = controller.getLastReloadTime();
		while (true) {
			try { Thread.sleep(500); }
			catch (InterruptedException e) { }
			if (!d.equals(controller.getLastReloadTime()))
				break;
		}
		response.sendRedirect("reload_cache.jsp");
		return;
	}
	else if ("web".equals(request.getParameter("reload"))) {
		CacheManager.getInstance().reloadCache();
		response.sendRedirect("reload_cache.jsp");
	}
	else {
%>

<h2>Cache</h2>

<b>Last data reload time:</b> <%= CacheLocator.getCacheController().getLastReloadTime() %><br>
<p>
	<input type="button" value="Reload Data" onclick="location.href='reload_cache.jsp?reload=full'">
</p>

<b>Last web reload time:</b> <%= CacheManager.getInstance().getLastReloadTime() %><br>
<p><input type="button" value="Refresh Web Cache" onclick="location.href='reload_cache.jsp?reload=web'"></p>

<br>
<b>Notes:</b>
	<br>
	Reloading the data forces a database query by the EJB.<br>
	Refreshing the Web Cache causes the web side to request updates from the EJB.  It does not force a database query.<br>
	After reloading the data, click 'Refresh Web Cache' for immediate update to the web cache.

<% } %>