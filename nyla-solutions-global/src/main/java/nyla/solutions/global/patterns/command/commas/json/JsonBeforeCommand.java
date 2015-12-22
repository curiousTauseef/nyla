package nyla.solutions.global.patterns.command.commas.json;

import java.util.Map;

import nyla.solutions.global.data.Envelope;
import nyla.solutions.global.exception.fault.FormatFaultException;
import nyla.solutions.global.json.JSON;
import nyla.solutions.global.patterns.command.Command;
import nyla.solutions.global.patterns.command.commas.CommasConstants;
import nyla.solutions.global.patterns.command.commas.CommasServiceFactory;
import nyla.solutions.global.patterns.command.commas.ContentType;
import nyla.solutions.global.util.Debugger;

import com.google.gson.JsonParseException;

/**
 * Used with the JsonAdvice and envelope to convert payloads to the Object
 * @author Gregory Green
 *
 */
public class JsonBeforeCommand implements Command<Object,Envelope<Object>>
{	
	
	/**
	 * Setup the before processing 
	 */
	public JsonBeforeCommand()
	{		
		json = JSON.newInstance();
	}// -----------------------------------------------

	/**
	 * Convert the Payload of an envelope to a Java object
	 * @see nyla.solutions.global.patterns.command.Command#execute(java.lang.Object)
	 */
	public Object execute(Envelope<Object> envelope)
	{		
		Map<Object, Object> header = envelope.getHeader();
		
		Object payload = envelope.getPayload();
		
		if(header == null)
			return payload;
		
		//TODO: Check version 
		
		//Get function 
		String cmdName  = (String)header.get(CommasConstants.COMMAND_NAME_HEADER);
		
		//Get "Content-Type"       
		ContentType contentType = (ContentType)header.get(CommasConstants.CONTENT_TYPE_HEADER);
		
		if(!ContentType.JSON.equals(contentType))
		     return envelope; //not a JSON string so ignore

		//Convert 
		//Get first class for JSON string
		String jsonText = (String)payload;
				   
		try
		{
			CommasServiceFactory factory = CommasServiceFactory.getCommasServiceFactory();
			
			payload = json.fromJson(jsonText, 
							factory.getCommandFacts(cmdName)
							   .getArgumentClassInfo().getBeanClass());
			
			//TODO: may need to save original payload
		    
			return payload;
		}
		catch(JsonParseException e)
		{
					   Debugger.printError(e);
					  FormatFaultException exp = new FormatFaultException(jsonText+" ERROR:"+e.getMessage(),e);
					  throw exp;
	    }

	}// --------------------------------------------------------

	private transient JSON json = null;
}
