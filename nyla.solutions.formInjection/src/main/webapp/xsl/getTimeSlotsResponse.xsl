<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ws="http://office.global.ws.solutions.org" xmlns:fn="http://www.w3.org/2005/02/xpath-functions" exclude-result-prefixes="soapenv "
xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
	<xsl:output method="xml" version="1.0" encoding="iso-8859-1" indent="yes" />
	<xsl:template match="/">
		<list>
		<xsl:for-each select="soapenv:Envelope/soapenv:Body/ws:listAvailableSlotsResponse/*">
			<xsl:apply-templates select="."/>
		</xsl:for-each>
		</list>
	</xsl:template>
	<xsl:template match="ws:listAvailableSlotsReturn">
	<org.solutions.form.data.QuestionChoice>
			<code>
			 <xsl:value-of select="ws:endTime/ws:hour"/><xsl:text>:</xsl:text> <xsl:value-of select="ws:endTime/ws:minutes"/> <xsl:value-of select="ws:endTime/ws:amOrPm"/>
			  <xsl:text>-</xsl:text>
			  <xsl:value-of select="ws:startTime/ws:hour"/><xsl:text>:</xsl:text> <xsl:value-of select="ws:startTime/ws:minutes"/> <xsl:value-of select="ws:startTime/ws:amOrPm"/>
                   </code>
			<text>
			 <xsl:value-of select="ws:endTime/ws:hour"/><xsl:text>:</xsl:text> <xsl:value-of select="ws:endTime/ws:minutes"/> <xsl:value-of select="ws:endTime/ws:amOrPm"/>
			  <xsl:text>-</xsl:text>
			  <xsl:value-of select="ws:startTime/ws:hour"/><xsl:text>:</xsl:text> <xsl:value-of select="ws:startTime/ws:minutes"/> <xsl:value-of select="ws:startTime/ws:amOrPm"/>
			</text>
		</org.solutions.form.data.QuestionChoice>

	 
	</xsl:template>
</xsl:stylesheet>
