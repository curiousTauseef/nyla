package nyla.solutions.global.patterns.scripting;
import java.util.Hashtable;
import java.util.Map;

import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * <pre>
 * Execute a spring expression
 * 
 * Sample expression
 * 
 * ${T(solutions.global.util.Debugger).println('Hello World')}"
 * </pre>
 * @author Gregory Green
 *
 */
public class SpringFramework implements Scripting
{
	/**
	 * Default constructor
	 */
	public SpringFramework()
	{
		parser = new SpelExpressionParser();
	}//---------------------------------------------
	/**
	 * Evaluation the boolean based on the evaluation object
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object interpret(String expression, Object evaluationObject)
	{		
		//if(this.evaluationObject == null)
		//	throw new RequiredException
		//	("this.evaluationObject in SpringExecutable");
		
		 try
		{
			StandardEvaluationContext context = new StandardEvaluationContext(evaluationObject);
			
			Map runtimeVariables = Config.getProperties();			
						
			if(this.variables != null)
				runtimeVariables.putAll(this.variables);
			
			//Set variables
			context.setVariables(runtimeVariables);
			
			
			Debugger.println(this,"Parsing expression="+expression);
			
			Expression exp = parser.parseExpression(expression,new TemplatedParserContext());
			  
			return exp.getValue(context);
			 		
		} 
		catch (Exception e)
		{
			throw new SystemException("expression="+expression+" evaluationObject="+evaluationObject+" \n ERROR:"+Debugger.stackTrace(e));
		}
		  
	}//---------------------------------------------
	/**
	 * @return the variables
	 */
	public Map<Object,Object>  getVariables()
	{
		if(this.variables == null)
			this.variables = new Hashtable<Object,Object>();
		
		return variables;
	}//---------------------------------------------
	/**
	 * @param variables the variables to set
	 */
	public void setVariables(Map<Object,Object> variables)
	{
		this.variables = variables;
	}//---------------------------------------------
	public static class TemplatedParserContext implements ParserContext 
	{
		public String getExpressionPrefix() {
		return "${";
		}
		public String getExpressionSuffix() {
		return "}";
		}
		public boolean isTemplate() {
		return true;
		}
	}//---------------------------------------------

	private Map<Object,Object> variables = null;
	private ExpressionParser parser;
}
