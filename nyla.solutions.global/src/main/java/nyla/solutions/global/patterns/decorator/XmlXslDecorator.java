package nyla.solutions.global.patterns.decorator;

import nyla.solutions.global.data.Textable;
import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.patterns.decorator.TextDecorator;
import nyla.solutions.global.patterns.decorator.XmlXslDecorator;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Text;
import nyla.solutions.global.xml.DOM4J;
import nyla.solutions.global.xml.XML;
import nyla.solutions.global.xml.XSL;

public class XmlXslDecorator implements TextDecorator<Textable>
{
   /**
    * 
    *
    * @see nyla.solutions.global.patterns.decorator.TextDecorator#getTarget(java.lang.Object)
    */
   public Textable getTarget()
   {
      return xml;
   }//--------------------------------------------
   /**
    * Calls target.getText if target is a textable else
    * @param target
    */
   public void setTarget(Textable target)
   {
      if(target == null)
         throw new RequiredException("target in XmlXslDecorator.setTarget");
      

       this.xml = target;   
      
   }//--------------------------------------------
   /**
    * 
    *
    * @see nyla.solutions.global.data.Textable#getText()
    */
   public String getText()
   {      
      try
      {
         String text = xml.getText();
         
         if(Text.isNull(text))
         {
            return emptyResults;
         }
         
         String results = null;
         
         if(this.xslUrl != null)             
         {
      	DOM4J xmlService = new DOM4J(this.xml.getText());
      	results = XML.transform(xmlService.getXml(), xslUrl);
        }
         else
         {
        	 if(this.xsl == null || this.xsl.length() == 0)
        		 throw new RequiredException("xsl");
        	    
        	 if(this.stripHeader)
        	  {
        	    xsl = XML.stripHeader(this.xsl);
        	  }
        	 
        	  results = XSL.transform(XSL.toStreamSource(this.xsl), this.xml.getText());
         }
         
         if(this.stripHeader)
      	results = XML.stripHeader(results);
                  
         return results;
      }
      catch (Exception e)
      {
         throw new SystemException(Debugger.stackTrace(e)+ "xml="+xml);
      }
   }//--------------------------------------------
   /**
    * @return the xslUrl
    */
   public String getXslUrl()
   {
      return xslUrl;
   }//--------------------------------------------
   /**
    * @param xslUrl the xslUrl to set
    */
   public void setXslUrl(String xslUrl)
   {
      this.xslUrl = xslUrl;
   }//--------------------------------------------
   /**
    * @return the stripHeader
    */
   public boolean isStripHeader()
   {
      return stripHeader;
   }//--------------------------------------------
   /**
    * @param stripHeader the stripHeader to set
    */
   public void setStripHeader(boolean stripHeader)
   {
      this.stripHeader = stripHeader;
   }//--------------------------------------------   
   /**
    * @return the emptyResults
    */
   public String getEmptyResults()
   {
      return emptyResults;
   }
   /**
    * @param emptyResults the emptyResults to set
    */
   public void setEmptyResults(String emptyResults)
   {
      this.emptyResults = emptyResults;
   }
   
   /**
	 * @return the xsl
	 */
	public String getXsl()
	{
		return xsl;
	}
	/**
	 * @param xsl the xsl to set
	 */
	public void setXsl(String xsl)
	{
		this.xsl = xsl;
	}

	private String emptyResults = "";
   private String xslUrl = null;
   private String xsl = null;
   private Textable xml = null;
   private boolean stripHeader = Config.getPropertyBoolean(XmlXslDecorator.class.getName()+".stripHeader", true).booleanValue();

}