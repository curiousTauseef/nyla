package nyla.solutions.web.commas;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nyla.solutions.commas.Command;
import nyla.solutions.commas.CommasBridge;
import nyla.solutions.commas.CommasConstants;
import nyla.solutions.commas.ContentType;
import nyla.solutions.core.data.Envelope;
import nyla.solutions.core.util.Config;
import nyla.solutions.core.util.Debugger;
import nyla.solutions.web.Web;
import nyla.solutions.web.controller.WebCommand;




/**
 * <pre>
 * Web Command to execute a COMMAS Command object.
 *
 * 
 * eXample URL usages
 * http://localhost:8080/services/commas/${NameOfComman$}
 * 
 * It will create an envelope based on the request payload.
 * 
 *  Envelope<String> env= new Envelope<String>(header,Web.getPayload(request));
 * 
 * It will use the CommasFactory to create a command passing in the envelope.
 * String response = getCommasFactory().createCommand(comamndName).execute(env);
 * 
 * The text response to written to the output stream.
 * It supports JSON and XML content types.
 *  
 * </pre>
 * @author Gregory Green
 *
 */
public class CommasWebBridgeCommand  extends CommasBridge implements WebCommand
{

	/**

	 * gridSevletPrefix = "grid/"

	 */

	public static final String COMMAND_NAME_PREFIX = Config.getProperty(CommasWebBridgeCommand.class,"COMMAND_NAME_PREFIX", "commas/"); 

	/** The User Agent key name */

	public static final String USER_AGENT_HEADER = "User-Agent";

	/** The Content Types currently supported  */

	public static final String CONTENT_TYPES_SUPPORTED = "application/json text/xml";
	

	
	/**
	 * Returns the requested content-type or, 
	 * if the requested content-type is unsupported
	 * the default supported content-type
	 * 
	 * @param request
	 *            The HttpServletRequest to use
	 * @return contentType
	 */
	public static ContentType getContentType(HttpServletRequest request) 
	{

		String content_type = request.getContentType();

		Debugger.println(CommasWebBridgeCommand.class,"content_type="+content_type);

		
		if("text/xml".equals(content_type))
			return ContentType.XML;
		else
			return ContentType.JSON; //default is JSON

	}// -----------------------------------------------

	/**
	 * Wrapper to execute a COMMAS command
	 * @see nyla.solutions.web.controller.WebCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		// Extract information from the request
		String uri = request.getRequestURI();
						
		//Get crate and function
		if(uri == null || uri.length() == 0)
			throw new IllegalArgumentException("URL:"+uri);
					
		int indexOfCratePrefix = uri.indexOf(COMMAND_NAME_PREFIX);
					
		if(indexOfCratePrefix < 0)
			throw new IllegalArgumentException("Missing command name prefix:"+COMMAND_NAME_PREFIX+" in url:"+uri);
				
		//parse crate and function ex: DemoOnServerFunctionWeb
		String comamndName = uri.substring(indexOfCratePrefix+COMMAND_NAME_PREFIX.length());
		

		Map<Object, Object> header = new HashMap<Object, Object>();
		
		ContentType contentType = getContentType(request);
		
		header.put(CommasConstants.CONTENT_TYPE_HEADER, contentType);
		header.put(CommasConstants.COMMAND_NAME_HEADER, comamndName);
				
		String payload = Web.getPayload(request);
				
		Envelope<String> env= new Envelope<String>(header,payload);
		
		Command<String,Envelope<String>> cmd = getCommasFactory().createCommand(comamndName);
		
		String responseText = cmd.execute(env);
		
		switch(contentType)
		{
			case JSON: response.setContentType("application/json"); break;
			case XML: response.setContentType("text/xml"); break;
			default: throw new IllegalArgumentException("Unknown Content Type:"+contentType);
		}
		
		
		final PrintWriter out = response.getWriter();

		out.print(responseText);	
		
		return null;
	}
	
}
