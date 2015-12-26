package nyla.solutions.blueprint.jee.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;


/**
 * Write configuration properties
 * @author Gregory Green
 */

public class ConfigTag extends TagSupport
{

	/**
	 * serialVersionUID = 1355394504372104634L
	 */
	private static final long serialVersionUID = 1355394504372104634L;

	/**
	 * Write a configuration property indicated in the tag id
	 */
	public int doStartTag()
    throws JspException
	{
		try
		{
			String propName = this.getId();
			
			Debugger.println(this,"looking for config property id "+propName);
			
			this.pageContext.getOut().write(Config.getProperty(this.getId(),defaultValue));			
		} 
		catch (IOException e)
		{			
			Debugger.printError(e);

		}
		return BodyTagSupport.SKIP_BODY;
	}//---------------------------------------------
	
	/**
	 * @return the defaultValue
	 */
	public String getDefaultValue()
	{
		return defaultValue;
	}

	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue)
	{
		if(defaultValue == null)
			defaultValue = "";
		
		this.defaultValue = defaultValue;
	}//---------------------------------------------

	private String defaultValue = ""; 
	
}
