package nyla.solutions.global.patterns.decorator;


import nyla.solutions.global.data.Textable;
import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.patterns.decorator.TextDecorator;
import nyla.solutions.global.util.Text;


/**
 * 
 * @author Gregory Green
 *
 */
public class ReplaceRegExpTextDecorator implements TextDecorator<Textable>
{

	/**
	 * replace the text based on a RegExpr
	 * @return Text.replaceForRegExprWith(this.target.getText(), regExp, replacement)
	 */
	public String getText()
	{
		if(this.target == null)
			throw new RequiredException("this.target in ReplaceTextDecorator");
		
		return Text.replaceForRegExprWith(this.target.getText(), regExp, replacement);
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
	 * @return the replacement
	 */
	public String getReplacement()
	{
		return replacement;
	}

	/**
	 * @param replacement the replacement to set
	 */
	public void setReplacement(String replacement)
	{
		this.replacement = replacement;
	}

	/**
	 * @return the regExp
	 */
	public String getRegExp()
	{
		return regExp;
	}

	/**
	 * @param regExp the regExp to set
	 */
	public void setRegExp(String regExp)
	{
		this.regExp = regExp;
	}

	private String replacement = null;
	private String regExp = null;
	private Textable target = null;
}
