package nyla.solutions.global.patterns.command.commas.json;

import nyla.solutions.core.data.Envelope;
import nyla.solutions.global.json.JSON;
import nyla.solutions.global.patterns.command.Command;


/**
 * @author Gregory Green
 *
 */
public class JsonAfterCommand implements Command<String,Envelope<Object>>
{

	/**
	 * Provide checks and transformation pass of the 
	 * @param resultAndOriginalArg array 0 - raw function result, 1 = Object[] containing the envelope and function context
	 */
	//@Override
	public String execute(Envelope<Object> env)
	{
		
		return json.toJson(env.getPayload());
			
	}// -----------------------------------------------

	private JSON json = JSON.newInstance();

}
