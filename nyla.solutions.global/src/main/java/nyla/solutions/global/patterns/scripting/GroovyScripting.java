package nyla.solutions.global.patterns.scripting;

import groovy.lang.Binding;
import groovy.lang.GroovyRuntimeException;
import groovy.lang.GroovyShell;

import java.util.Map;

import nyla.solutions.global.exception.SetupException;

/**
 * <pre>
 * Scripting implements for Groovy
 * Default  evaluationObjectPropertyName  = "evaluationObject";
 * 
 * 
 * <b>Example</b>
 * <code>
 
  	GroovyScripting<String,Object> groovyScripting = new GroovyScripting<String,Object>();

	String results = groovyScripting.interpret("System.out.println('hello'); "
		+"return new nyla.solutions.global.patterns.scripting.GroovyScriptingTest().getName();", null);
		
	Assert.assertEquals("GroovyScriptingTest",results );
		
	HashMap<String,String> variables = new HashMap<String,String>();
	variables.put("hello", "world");
	groovyScripting.setVariables(variables);
		
	Assert.assertEquals("world",groovyScripting.interpret("return hello;", null));
		
		
	Assert.assertEquals("Nyla Test",groovyScripting.interpret(
						"return "+groovyScripting.getEvaluationObjectPropertyName()+";", 
										"Nyla Test"));
	 </code>
 * </pre>
 * @author Gregory Green
 *
 */
public class GroovyScripting<ReturnType,EvaluationObjectType> implements Scripting<ReturnType,EvaluationObjectType>
{
	public GroovyScripting()
	{
	}// --------------------------------------------------------
	/**
	 * @param evaluationObject This method will set binding.setProperty("evaluationObject", evaluationObject);
	 * 
	 * @see nyla.solutions.global.patterns.scripting.Scripting#interpret(java.lang.String, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public ReturnType interpret(String expression, EvaluationObjectType evaluationObject)
	{
		
		try
		{
			if(expression == null)
						throw new IllegalArgumentException("expression is required");
			
			GroovyShell groovyShell = null;
			
			
			if(variables != null && !variables.isEmpty())
			{
				Binding binding = new Binding(variables);

				if(evaluationObject != null)
					binding.setProperty(evaluationObjectPropertyName,evaluationObject);
				groovyShell = new GroovyShell(binding);
			}
			else
			{
				if(evaluationObject != null)
				{
					Binding binding = new Binding();
					binding.setProperty(evaluationObjectPropertyName,evaluationObject);
					groovyShell = new GroovyShell();
				}
				else
					groovyShell = new GroovyShell();
			}
			
			return (ReturnType)groovyShell.evaluate(expression);
		}
		catch (GroovyRuntimeException e)
		{
			throw new SetupException(e.getMessage()+" "+expression,e);
		}
	}// --------------------------------------------------------
	public Map<String, ?> getVariables()
	{
		return variables;
	}// --------------------------------------------------------
	public void setVariables(Map<String, ?> variables)
	{
		this.variables = variables;
	}// --------------------------------------------------------
	public String getEvaluationObjectPropertyName()
	{
		return evaluationObjectPropertyName;
	}// --------------------------------------------------------
	public void setEvaluationObjectPropertyName(String evaluationObjectPropertyName)
	{
		this.evaluationObjectPropertyName = evaluationObjectPropertyName;
	}// --------------------------------------------------------


	private String evaluationObjectPropertyName  = "evaluationObject";
	private Map<String,?> variables = null;
}
