package nyla.solutions.global.patterns.scripting;

import java.util.Map;

/**
 * Interface for using bean scripting frameworks (such as GROOVY, SpEL, JRuby, etc)
 * @author Gregory Green
 *
 */
public interface Scripting
{
	Object interpret(String expression, Object evaluationObject);
	
	/**
	 * @return the variables
	 */
	Map<Object,Object> getVariables();
	
	/**
	 * @param variables the variables to set
	 */
	void setVariables(Map<Object,Object> variables);
	
	
}
