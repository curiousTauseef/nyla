package nyla.solutions.global.patterns.command;

import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.patterns.scripting.Scripting;

/**
 * Executable wrapper to execute a scripting expression
 * @author Gregory Green
 *
 */
public class ScriptingExecutable implements Executable
{

	/**
	 * @return scripting.interpret(expression, input)
	 * @see nyla.solutions.global.patterns.command.Command#execute(java.lang.Object)
	 */
	@Override
	public Integer execute(Environment input)
	{
		if(scripting == null)
			throw new RequiredException("this.scripting");
		
		return scripting.interpret(expression, input);
	}// --------------------------------------------------------
	
	/**
	 * @return the scripting
	 */
	public Scripting<Integer, Environment> getScripting()
	{
		return scripting;
	}// --------------------------------------------------------
	/**
	 * @param scripting the scripting to set
	 */
	public void setScripting(Scripting<Integer, Environment> scripting)
	{
		this.scripting = scripting;
	}// --------------------------------------------------------
	/**
	 * @return the expression
	 */
	public String getExpression()
	{
		return expression;
	}// --------------------------------------------------------
	/**
	 * @param expression the expression to set
	 */
	public void setExpression(String expression)
	{
		this.expression = expression;
	}// --------------------------------------------------------


	private Scripting<Integer, Environment> scripting;
	private String expression = null;
}
