package nyla.solutions.global.patterns.decorator.w3cdom;

import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.xml.XML;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * Return the value of the first node in a document by the node name.
 * @author Gregory Green
 *
 */
public class AllNodeValueByNameCacheText extends AbstractCacheText
{
	/**
	 * @return the node value of a give node name
	 */
	public String getText()
	{
		Document dom = this.getDocument();
		
		if(this.name == null || this.name.length() == 0)
			throw new RequiredException(this.getClass().getName()+"name"); 
			
		Debugger.println(this,"Looking for node="+name);
		
		NodeList nodeList = dom.getElementsByTagName(name);
		if(nodeList == null || nodeList.getLength() == 0)
			return "";
		
		StringBuffer results = new StringBuffer();
		for (int i = 0; i < nodeList.getLength(); i++)
		{
			results.append(XML.getText(nodeList.item(i))).append(this.separator);
		}
		
		return results.toString();
	}// --------------------------------------------
	
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	private String name = Config.getProperty(this.getClass(),"name","");
	private String separator = Config.getProperty(this.getClass(),"separator"," ");
	
}