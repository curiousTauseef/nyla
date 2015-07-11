package nyla.solutions.global.patterns.expression;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;


/**
 * Implements a Object boolean using the Spring's "Spel" Expression language
 * @author Gregory Green
 *
 */
public class SpringBooleanExpression implements ObjectBooleanExpression
{
	public SpringBooleanExpression()
	{
		parser = new SpelExpressionParser();
	}//---------------------------------------------

	/**
	 * Evaluation the boolean based on the evaluation object
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public synchronized boolean getBoolean(Object obj)
	{
		if(this.evaluationObject == null)
			throw new RequiredException
			("this.evaluationObject in SpringBooleanExpression");
		
		 try
		{
			StandardEvaluationContext context = new StandardEvaluationContext(this.evaluationObject);
			
			Properties runtimeVariables = Config.getProperties();
			
			if(this.variables != null)
				runtimeVariables.putAll(this.variables);
			
			//Set variables
			context.setVariables((Map)runtimeVariables);
			Debugger.println(this,"Parsing expression="+expression);
			
			Expression expression = parser.parseExpression(this.expression);
			  
			 boolean results =((Boolean)expression.getValue(context)).booleanValue();
			 			
			 return results;
		} 
		catch (Exception e)
		{
			throw new SystemException("expression="+expression+" evaluationObject="+evaluationObject+" \n ERROR:"+Debugger.stackTrace(e));
		}
		  
	}//---------------------------------------------
	/**
	 * @return the evaluationObject
	 */
	public synchronized Object getEvaluationObject()
	{
		return evaluationObject;
	}//---------------------------------------------


	/**
	 * @param evaluationObject the evaluationObject to set
	 */
	public synchronized void setEvaluationObject(Object evaluationObject)
	{
		this.evaluationObject = evaluationObject;				
		
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

	private Map<Object,Object> variables = null;
	private ExpressionParser parser;
	private String expression = null;
	private Object evaluationObject = null;
}
