package nyla.solutions.global.web.commas;

import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import nyla.solutions.global.data.Envelope;
import nyla.solutions.global.patterns.command.Command;
import nyla.solutions.global.patterns.command.commas.CommasServiceFactory;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;

/**
 * <pre>
 * Executes command based on session events.
 * 
 * Config.properties Example
 * 
 * nyla.solutions.global.web.commas.SessionCommandListener.sessionDestroyedCommand=solutions.dao.mongodb.MongoPaginationCleanupCommand.execute
 * 
 * Web.xml (Example section)
 * 
 * 	</servlet>
	<listener>
	   <listener-class>nyla.solutions.global.web.commas.SessionCommandListener</listener-class>
	</listener>


  </pre>
 * @author Gregory Green
 *
 */
public class SessionCommandListener implements HttpSessionListener
{
	/**
	 * SESSION_ID_PARAM = Config.getProperty(SessionCommandListener.class,"SESSION_ID_PARAM","JSESSIONID")
	 */
	public static final String SESSION_ID_PARAM = Config.getProperty(SessionCommandListener.class,"SESSION_ID_PARAM","JSESSIONID");
	
	/**
	 * Execute the sessionCreatedCommand
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent httpSessionEvent)
	{
		executeSessionCommand(this.sessionCreatedCommand,httpSessionEvent);

	}// --------------------------------------------------------
	/**
	 * Execute the sessionDestroyedCommand command
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent)
	{
		executeSessionCommand(this.sessionDestroyedCommand,httpSessionEvent);
	}// --------------------------------------------------------
	/**
	 * Execute the command passing a given session
	 * @param commandName the command
	 * @param httpSessionEvent
	 */
	private void executeSessionCommand(String commandName, HttpSessionEvent httpSessionEvent)
	{
		
		if(commandName == null || commandName.length() == 0)
			return; //nothing to execute
		
        Command<Object,HttpSession> command = CommasServiceFactory.getCommasServiceFactory().createCommand(commandName);
		
		if(command == null)
			return; //no command to execute
		
		
		HttpSession session = httpSessionEvent.getSession();
		
		if(session == null)
		{
			Debugger.println(this,"Session object is null, command will not be executed");
		}
		
		//Create envelope 
		
		Envelope<HttpSession> env = new Envelope<HttpSession>();
		HashMap<Object,Object> header = new HashMap<Object,Object>();
		
		header.put(SESSION_ID_PARAM, session.getId());
		
		env.setHeader(header);
		env.setPayload(session);
		
		command.execute(httpSessionEvent.getSession());
		
		
	}// --------------------------------------------------------
	
	/**
	 * @return the sessionDestroyedCommand
	 */
	public String getSessionDestroyedCommand()
	{
		return sessionDestroyedCommand;
	}

	/**
	 * @param sessionDestroyedCommand the sessionDestroyedCommand to set
	 */
	public void setSessionDestroyedCommand(String sessionDestroyedCommand)
	{
		this.sessionDestroyedCommand = sessionDestroyedCommand;
	}

	/**
	 * @return the sessionCreateCommand
	 */
	public String getSessionCreatedCommand()
	{
		return sessionCreatedCommand;
	} 

	/**
	 * @param sessionCreateCommand the sessionCreateCommand to set
	 */
	public void setSessionCreatedCommand(String sessionCreatedCommand)
	{
		this.sessionCreatedCommand = sessionCreatedCommand;
	}


	private String sessionDestroyedCommand = Config.getProperty(SessionCommandListener.class,"sessionDestroyedCommand", "");
	private String sessionCreatedCommand = Config.getProperty(SessionCommandListener.class,"sessionCreateCommand", "");

}
