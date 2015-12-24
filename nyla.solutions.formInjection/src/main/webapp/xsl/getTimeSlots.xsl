<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<soapenv:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:off="http://office.global.ws.solutions.org">
		   <soapenv:Header/>
		   <soapenv:Body>
		      <off:listAvailableSlots soapenv:encodingStyle="http://schemas.xmlsoap.org/soap/encoding/">
		         <client xsi:type="sol:Client" xmlns:sol="solution">
		            <id xsi:type="xsd:string">default</id>
		         </client>
		         <time xsi:type="xsd:dateTime">2009-01-09T00:00:00-00:00</time>
		      </off:listAvailableSlots>
		   </soapenv:Body>
		</soapenv:Envelope>
	</xsl:template>	
</xsl:stylesheet>