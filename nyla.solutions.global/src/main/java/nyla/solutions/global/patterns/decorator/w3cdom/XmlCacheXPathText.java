package nyla.solutions.global.patterns.decorator.w3cdom;



import nyla.solutions.core.exception.RequiredException;
import nyla.solutions.core.exception.SystemException;
import nyla.solutions.core.util.Debugger;
import nyla.solutions.global.xml.DOM4J;

public class XmlCacheXPathText extends AbstractCacheText
{
	/**
	 * @return the element value for an XPATH
	 */
	public String getText()
	{
		try
		{
			//get cache
			DOM4J xml = this.getXML();
			
			if(this.xpath == null || this.xpath.length() == 0)
				throw new RequiredException("this.xpath in XmlCacheXPathText.getText");
			
			return xml.retrieveElementValue(xpath);
		}
		catch (Exception e)
		{
			throw new SystemException(Debugger.stackTrace(e));
		}

	}// --------------------------------------------

	/**
	 * @return the xpath
	 */
	public String getXpath()
	{
		return xpath;
	}

	/**
	 * @param xpath the xpath to set
	 */
	public void setXpath(String xpath)
	{
		this.xpath = xpath;
	}


	private String xpath = "";
	

}
