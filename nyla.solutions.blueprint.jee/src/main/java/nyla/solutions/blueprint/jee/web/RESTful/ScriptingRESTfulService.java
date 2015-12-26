package nyla.solutions.blueprint.jee.web.RESTful;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nyla.solutions.global.patterns.scripting.Scripting;
import nyla.solutions.global.patterns.servicefactory.ServiceFactory;
import nyla.solutions.global.util.Config;


/**
 * Scripting based RESTful web service
 * @author Gregory Green
 *
 */
public class ScriptingRESTfulService extends AbstractRESTfulCommand
{
	/**
	 * Calls the execute method
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
	{
		Scripting scripting = (Scripting)ServiceFactory.getInstance().create(scriptingObjectId);
	
		
			
		scripting.getVariables().put(responseVariableKey, response);
		scripting.interpret(expression, request);		
	}//---------------------------------------------
	
	/**
	 * @return the responseVariableKey
	 */
	public String getResponseVariableKey()
	{
		return responseVariableKey;
	}
	/**
	 * @param responseVariableKey the responseVariableKey to set
	 */
	public void setResponseVariableKey(String responseVariableKey)
	{
		this.responseVariableKey = responseVariableKey;
	}
	/**
	 * @return the expression
	 */
	public String getExpression()
	{
		return expression;
	}
	/**
	 * @param expression the expression to set
	 */
	public void setExpression(String expression)
	{
		this.expression = expression;
	}
	/**
	 * @return the scriptingObjectId
	 */
	public String getScriptingObjectId()
	{
		return scriptingObjectId;
	}
	/**
	 * @param scriptingObjectId the scriptingObjectId to set
	 */
	public void setScriptingObjectId(String scriptingObjectId)
	{
		this.scriptingObjectId = scriptingObjectId;
	}

	private String responseVariableKey = Config.getProperty(ScriptingRESTfulService.class,"responseVariableKey","response");
	private String expression =  Config.getProperty(ScriptingRESTfulService.class,"expression","");
	private String scriptingObjectId = Config.getProperty(ScriptingRESTfulService.class,"scriptingObjectId",Scripting.class.getName());
}
