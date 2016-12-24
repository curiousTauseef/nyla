package nyla.solutions.core.patterns.decorator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import nyla.solutions.core.data.Textable;


/**
 * Macro implement for text objects
 * @author Gregory Green
 *
 */
public class MacroTextDecorator implements TextDecorator<Textable>
{

	/**
	 * Get the target text and execute each text decorator on its results
	 * @return the text results
	 */
	public String getText()
	{
		//loop thru text
		StringText stringText = new StringText();
		
		stringText.setText(this.target.getText());
		
		TextDecorator<Textable> textDecorator = null;
		//loop thru decorator and get results from each
		for(Iterator<TextDecorator<Textable>> i = textables.iterator();i.hasNext();)
		{
			textDecorator = i.next();
			
			textDecorator.setTarget(stringText);
			
			stringText.setText(textDecorator.getText());
		}
		
		return stringText.getText();
	}//--------------------------------------------- 
	
	/**
	 * @return the target
	 */
	public Textable getTarget()
	{
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(Textable target)
	{
		this.target = target;
	}

	/**
	 * @return the textables
	 */
	public Collection<TextDecorator<Textable>> getTextables()
	{
		return textables;
	}

	/**
	 * @param textables the textables to set
	 */
	public void setTextables(Collection<TextDecorator<Textable>>  textables)
	{
		this.textables = textables;
	}

	
	private Textable target = null;

	private Collection<TextDecorator<Textable>>  textables = new ArrayList<TextDecorator<Textable>> ();
}
