package nyla.solutions.global.patterns.expression;

import java.util.HashMap;
import java.util.Map;

import nyla.solutions.core.exception.SystemException;
import nyla.solutions.core.exception.fault.FaultException;
import nyla.solutions.core.patterns.scripting.Scripting;
import nyla.solutions.core.util.Debugger;


/**
 * Implements boolean expressing using a scripting instance
 * @author Gregory Green
 *
 */
public class ScriptingBooleanExpression implements BooleanExpression<Object>
{
	public ScriptingBooleanExpression()
	{
	}//---------------------------------------------

	/**
	 * Evaluation the boolean based on the evaluation object
	 */
	public synchronized Boolean execute(Object evaluationObject)
	{
		
		 try
		{
			
			Boolean bool = this.scripting.interpret(expression, evaluationObject);
			 return bool;
		} 
		catch (FaultException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			throw new SystemException("expression="+expression+" evaluationObject="+evaluationObject+" \n ERROR:"+Debugger.stackTrace(e));
		}
		  
	}//---------------------------------------------
	
	/**
	 * @return the expression
	 */
	public synchronized String getExpression()
	{
		return expression;
	}//---------------------------------------------


	/**
	 * @param expression the expression to set
	 */
	public  synchronized void setExpression(String expression)
	{
		this.expression = expression;
	}//---------------------------------------------

	/**
	 * @return the variables
	 */
	public Map<Object, Object> getVariables()
	{
		if(variables == null)
			return null;
		
		return new HashMap<Object,Object>(variables);
	}

	/**
	 * @param variables the variables to set
	 */
	public void setVariables(Map<Object, Object> variables)
	{
		if(variables == null)
		{
			this.variables = null;
			return;
		}
		
		this.variables = new HashMap<Object,Object>(variables);
	}
	
	

	/**
	 * @return the scripting
	 */
	public Scripting<Boolean, Object> getScripting()
	{
		return scripting;
	}

	/**
	 * @param scripting the scripting to set
	 */
	public void setScripting(Scripting<Boolean, Object> scripting)
	{
		this.scripting = scripting;
	}



	private Map<Object,Object> variables = null;
	private Scripting<Boolean, Object> scripting;
	private String expression = null;
}
