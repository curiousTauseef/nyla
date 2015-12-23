package nyla.solutions.global.patterns.scripting;

import java.util.Map;

/**
 * Interface for using bean scripting frameworks (such as GROOVY, SpEL, JRuby, etc)
 * @author Gregory Green
 *
 */
public interface Scripting<ReturnType,EvalObjectType>
{
	ReturnType interpret(String expression, EvalObjectType evaluationObject);
	
	/**
	 * @return the variables
	 */
	Map<String,?> getVariables();
	
	/**
	 * @param variables the variables to set
	 */
	void setVariables(Map<String,?> variables);
	
	
}
