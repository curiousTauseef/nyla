<%@ page import="java.net.*,
		java.sql.*" %>

<html>
<body>
<a href="http://oraweb-smtd.pri.bms.com:7779/pls/gdi9/smt.getprojects">Home Page</a>
<br>
<a href="http://oraweb-smtd.pri.bms.com:7779/pls/gdi9/smt_select.setcountryparms?site_xbox_tbl=DUMMY&country=<%=URLEncoder.encode("No Hub/Country selected.","UTF8")%>&hub=<%=URLEncoder.encode("No hub selected.","UTF8")%>&ptstatus=A&EnrollTreatDate=ENROLL&prot=CA163-009&rpttitle=<%=URLEncoder.encode("Enrollment and Treatment Assignment Totals","UTF8")%>&rptprocedure=smt_enroll.site&rpttype=CURRENT&ptstatus=A&esite=0&visit_xboxlist=<%=URLEncoder.encode("!!TOT","UTF8")%>&country_xboxlist=DUMMY&aorlist=DUMMY&geohublist=DUMMY&geocountrylist=DUMMY&proj_xbox_tbl=DUMMY&sitechoice=ALL&hubchoice=ALL&countrychoice=ALL">Enrollment Summary Report</a>
<br>
<a href="http://oraweb-smtd.pri.bms.com:7779/pls/gdi9/smt_select.setcountryparms?site_xbox_tbl=DUMMY&country=<%=URLEncoder.encode("No Hub/Country selected.","UTF8")%>&hub=<%=URLEncoder.encode("No hub selected.","UTF8")%>&ptstatus=A&EnrollTreatDate=ENROLL&prot=CA163-009&rpttitle=<%=URLEncoder.encode("Enrollment and Treatment Assignment Details","UTF8")%>&rptprocedure=smt_enrolldetail.details&rpttype=CURRENT&ptstatus=A&esite=0&visit_xboxlist=<%=URLEncoder.encode("!!TOT","UTF8")%>&country_xboxlist=DUMMY&aorlist=DUMMY&geohublist=DUMMY&geocountrylist=DUMMY&proj_xbox_tbl=DUMMY&sitechoice=ALL&hubchoice=ALL&countrychoice=ALL">Enrollment Detail Report</a>
<br>
<a href="http://oraweb-smtd.pri.bms.com:7779/pls/gdi9/smt_select.setcountryparms?site_xbox_tbl=DUMMY&country=<%=URLEncoder.encode("No Hub/Country selected.","UTF8")%>&hub=<%=URLEncoder.encode("No hub selected.","UTF8")%>&ptstatus=A&prot=CA163-009&rpttitle=<%=URLEncoder.encode("Received/Missing Pages Summary","UTF8")%>&rptprocedure=smt_pages.summary&rpttype=ALL&ptstatus=A&esite=0&visit_xboxlist=<%=URLEncoder.encode("!!TOT","UTF8")%>&country_xboxlist=DUMMY&aorlist=DUMMY&geohublist=DUMMY&geocountrylist=DUMMY&proj_xbox_tbl=DUMMY&visitparm=V&visitchoice=ALL&sitechoice=ALL&hubchoice=ALL&countrychoice=ALL">Received/Missing Pages Summary</a>
<br>
<!--a href="http://oraweb-smtd.pri.bms.com:7779/pls/gdi9/smt_select.setcountryparms?site_xbox_tbl=DUMMY&country=<%=URLEncoder.encode("No Hub/Country selected.","UTF8")%>&hub=<%=URLEncoder.encode("No hub selected.","UTF8")%>&ptstatus=A&prot=CA163-009&rpttitle=<%=URLEncoder.encode("Queries Report","UTF8")%>&rptprocedure=smt_queries.queries&rpttype=ALL&ptstatus=A&esite=0&visit_xboxlist=<%=URLEncoder.encode("!!TOT","UTF8")%>&country_xboxlist=DUMMY&aorlist=DUMMY&geohublist=DUMMY&geocountrylist=DUMMY&proj_xbox_tbl=DUMMY&visitparm=V&visitchoice=ALL&sitechoice=ALL&hubchoice=ALL&countrychoice=ALL&panelchoice=ALL&panel_xbox_tbl=DUMMY">Queries Report</a-->
<a href="http://oraweb-smtd.pri.bms.com:7779/pls/gdi9/smt_queries.queries?fdatestring=08-DEC-2004&tdatestring=08-MAR-2005&ptstatus=A&prot=CA163-009&rpttype=ALL&ptstatus=A&esite=0&panelchoice=ALL&panel_xbox_tbl=DUMMY&siteselection=<%=URLEncoder.encode("= '00001' ","UTF8")%>">Queries Report</a>
<br>
<a href="http://oraweb-smtd.pri.bms.com:7779/pls/gdi9/smt_calendar.schedule?protocol=CA163-009">Visit Schedule</a>
<br>
<a href="http://oraweb-smtd.pri.bms.com:7779/pls/gdi9/smt_select.setcountryparms?site_xbox_tbl=DUMMY&country=<%=URLEncoder.encode("No Hub/Country selected.","UTF8")%>&hub=<%=URLEncoder.encode("No hub selected.","UTF8")%>&ptstatus=A&prot=CA163-009&rpttitle=<%=URLEncoder.encode("Patient Visit Calendar","UTF8")%>&rptprocedure=smt_calendar.calendar&ptstatus=A&esite=0&visit_xboxlist=<%=URLEncoder.encode("!!TOT","UTF8")%>&country_xboxlist=DUMMY&aorlist=DUMMY&geohublist=DUMMY&geocountrylist=DUMMY&proj_xbox_tbl=DUMMY&sitechoice=ALL&hubchoice=ALL&countrychoice=ALL">Visit Calendar</a>
<p>

<a href="http://oraweb-smtd.pri.bms.com:7779/pls/gdi9/smt.getprojects">Home Page</a>
<br>
<a href="http://oraweb-smtd.pri.bms.com:7779/pls/gdi9/smt_select.setcountryparms?site_xbox_tbl=DUMMY&country=<%=URLEncoder.encode("No Hub/Country selected.","UTF8")%>&hub=<%=URLEncoder.encode("No hub selected.","UTF8")%>&ptstatus=A&EnrollTreatDate=ENROLL&prot=CA163-010&rpttitle=<%=URLEncoder.encode("Enrollment and Treatment Assignment Totals","UTF8")%>&rptprocedure=smt_enroll.site&rpttype=CURRENT&ptstatus=A&esite=0&visit_xboxlist=<%=URLEncoder.encode("!!TOT","UTF8")%>&country_xboxlist=DUMMY&aorlist=DUMMY&geohublist=DUMMY&geocountrylist=DUMMY&proj_xbox_tbl=DUMMY&sitechoice=ALL&hubchoice=ALL&countrychoice=ALL">Enrollment Summary Report</a>
<br>
<a href="http://oraweb-smtd.pri.bms.com:7779/pls/gdi9/smt_select.setcountryparms?site_xbox_tbl=DUMMY&country=<%=URLEncoder.encode("No Hub/Country selected.","UTF8")%>&hub=<%=URLEncoder.encode("No hub selected.","UTF8")%>&ptstatus=A&EnrollTreatDate=ENROLL&prot=CA163-010&rpttitle=<%=URLEncoder.encode("Enrollment and Treatment Assignment Details","UTF8")%>&rptprocedure=smt_enrolldetail.details&rpttype=CURRENT&ptstatus=A&esite=0&visit_xboxlist=<%=URLEncoder.encode("!!TOT","UTF8")%>&country_xboxlist=DUMMY&aorlist=DUMMY&geohublist=DUMMY&geocountrylist=DUMMY&proj_xbox_tbl=DUMMY&sitechoice=ALL&hubchoice=ALL&countrychoice=ALL">Enrollment Detail Report</a>
<br>
<a href="http://oraweb-smtd.pri.bms.com:7779/pls/gdi9/smt_select.setcountryparms?site_xbox_tbl=DUMMY&country=<%=URLEncoder.encode("No Hub/Country selected.","UTF8")%>&hub=<%=URLEncoder.encode("No hub selected.","UTF8")%>&ptstatus=A&prot=CA163-010&rpttitle=<%=URLEncoder.encode("Received/Missing Pages Summary","UTF8")%>&rptprocedure=smt_pages.summary&rpttype=ALL&ptstatus=A&esite=0&visit_xboxlist=<%=URLEncoder.encode("!!TOT","UTF8")%>&country_xboxlist=DUMMY&aorlist=DUMMY&geohublist=DUMMY&geocountrylist=DUMMY&proj_xbox_tbl=DUMMY&visitparm=V&visitchoice=ALL&sitechoice=ALL&hubchoice=ALL&countrychoice=ALL">Received/Missing Pages Summary</a>
<br>
<a href="http://oraweb-smtd.pri.bms.com:7779/pls/gdi9/smt_queries.queries?fdatestring=08-DEC-2004&tdatestring=08-MAR-2005&ptstatus=A&prot=CA163-010&rpttype=ALL&ptstatus=A&esite=0&panelchoice=ALL&panel_xbox_tbl=DUMMY&siteselection=<%=URLEncoder.encode("= '00001' ","UTF8")%>">Queries Report</a>
<br>
<a href="http://oraweb-smtd.pri.bms.com:7779/pls/gdi9/smt_calendar.schedule?protocol=CA163-010">Visit Schedule</a>
<br>
<a href="http://oraweb-smtd.pri.bms.com:7779/pls/gdi9/smt_select.setcountryparms?site_xbox_tbl=DUMMY&country=<%=URLEncoder.encode("No Hub/Country selected.","UTF8")%>&hub=<%=URLEncoder.encode("No hub selected.","UTF8")%>&ptstatus=A&prot=CA163-010&rpttitle=<%=URLEncoder.encode("Patient Visit Calendar","UTF8")%>&rptprocedure=smt_calendar.calendar&ptstatus=A&esite=0&visit_xboxlist=<%=URLEncoder.encode("!!TOT","UTF8")%>&country_xboxlist=DUMMY&aorlist=DUMMY&geohublist=DUMMY&geocountrylist=DUMMY&proj_xbox_tbl=DUMMY&sitechoice=ALL&hubchoice=ALL&countrychoice=ALL">Visit Calendar</a>

<!-- FORM ACTION="http://oraweb-smtd.pri.bms.com:7779/pls/gdi9/smt_select.setcountryparms" METHOD="POST">
<INPUT TYPE="hidden" NAME="EnrollTreatDate" VALUE="ENROLL">
<input type="hidden" name="ptstatus" value="A">
<input type="hidden" name="hub" value="No hub selected.">
<input type="hidden" name="country" value="No Hub/Country selected.">
<INPUT TYPE="hidden" NAME="prot" VALUE="CA163-009">
<INPUT TYPE="hidden" NAME="rpttitle" VALUE="Enrollment and Treatment Assignment Totals">
<INPUT TYPE="hidden" NAME="rptprocedure" VALUE="smt_enroll.site">
<INPUT TYPE="hidden" NAME="rpttype" VALUE="CURRENT">
<INPUT TYPE="hidden" name="todate" value="02-MAR-2005">
<INPUT TYPE="hidden" name="ptstatus" value="A">
<input type="hidden" name="esite" value="0">
<INPUT TYPE="hidden" NAME="visit_xboxlist" VALUE="!!TOT">
<INPUT TYPE="hidden" NAME="country_xboxlist" VALUE="DUMMY">
<INPUT TYPE="hidden" NAME="aorlist" VALUE="DUMMY">
<INPUT TYPE="hidden" NAME="geohublist" VALUE="DUMMY">
<INPUT TYPE="hidden" NAME="geocountrylist" VALUE="DUMMY">
<INPUT TYPE="hidden" NAME="proj_xbox_tbl" VALUE="DUMMY">
<input type="hidden" name="sitechoice" value="ALL">
<input type="hidden" name="hubchoice" value="ALL">
<input type="hidden" name="countrychoice" value="ALL">
<INPUT TYPE="hidden" NAME="site_xbox_tbl" VALUE="DUMMY">
</form -->
<!--a href="javascript:document.forms[0].submit()">Enrollment Report - FORM</a>
<br-->
</body>
</html>