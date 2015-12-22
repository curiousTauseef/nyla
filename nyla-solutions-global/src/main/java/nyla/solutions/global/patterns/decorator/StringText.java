package nyla.solutions.global.patterns.decorator;

import nyla.solutions.global.data.Textable;
import nyla.solutions.global.patterns.decorator.StringText;

public class StringText implements Textable
{
	
	/**
	 * @return the text
	 */
	public String getText()
	{
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text)
	{
		this.text = text;
	}
	
	private String text = null;

}
