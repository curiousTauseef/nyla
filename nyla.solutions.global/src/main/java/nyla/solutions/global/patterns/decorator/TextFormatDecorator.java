package nyla.solutions.global.patterns.decorator;

import java.util.Map;

import nyla.solutions.global.data.Textable;
import nyla.solutions.global.exception.FormatException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.util.Text;

/**
 * Text format decorator using the target Textable
 * @author Gregory Green
 *
 */
public class TextFormatDecorator implements TextDecorator<Textable>
{
	/**
	 * 
	 * @return Text.formatMap(template,this.map)
	 */
	public String getText()
	{
		if(this.target == null)
			return null;
		
		String template = this.target.getText();
		
		if(this.map == null)
			return template;
			
		try
		{
			return Text.format(template,this.map);
			
		}
		catch (FormatException e)
		{
			throw new SystemException(e);
		}
	}// --------------------------------------------------------

	/**
	 * Set the target 
	 */
	public void setTarget(Textable target)
	{
		this.target = target;
		
	}// --------------------------------------------------------
	/**
	 * 
	 * @see nyla.solutions.global.patterns.decorator.TextDecorator#getTarget()
	 */
	public Textable getTarget()
	{
		return target;
	}

	/**
	 * @return the map
	 */
	public Map<Object, Object> getMap()
	{
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map<Object, Object> map)
	{
		this.map = map;
	}

	private Textable target;
	private Map<Object, Object> map;

}
