<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:latex="http://forge.scilab.org/p/jlatexmath"
                version='1.0'>

<xsl:include href="/usr/share/xml/docbook/stylesheet/nwalsh/fo/docbook.xsl"/>

<xsl:param name="draft.watermark.image"/>

<xsl:template match="latex:*">
  <fo:instream-foreign-object>
    <xsl:copy>
      <xsl:copy-of select="@*"/>
      <xsl:apply-templates/>
    </xsl:copy>
  </fo:instream-foreign-object>
</xsl:template>

</xsl:stylesheet>
