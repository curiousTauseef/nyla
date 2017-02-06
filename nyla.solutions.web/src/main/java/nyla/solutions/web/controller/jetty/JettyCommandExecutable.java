package nyla.solutions.web.controller.jetty;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;

import nyla.solutions.commas.Executable;
import nyla.solutions.core.data.Environment;
import nyla.solutions.core.patterns.servicefactory.ServiceFactory;

/**
 * <pre>
 * 	<bean id="MainExecutable" class="nyla.solutions.web.controller.jetty.JettyCommandExecutable">
	</bean>
 * </pre>
 **/
public class JettyCommandExecutable implements Executable
{
	/**
	 * 
	 * @see nyla.solutions.global.patterns.command.Command#execute(java.lang.Object)
	 */
	public Integer execute(Environment env)
	{	
		
		 try
			{
				 if(env.getArgs().length != 2)
				 {
					 System.err.println(" Required httpPort and handleName arguments");
					 return -1;
				 }
				 
				

				 int httpPort = Integer.parseInt(env.getArgs()[0]);
				 Server server = new Server(httpPort);
				 
				 String handlerName = env.getArgs()[1];
				 
				 Handler handler = ServiceFactory.getInstance().create(handlerName);
				 
				 server.setHandler(handler);
				 
			     server.start();
			     server.join();
			     
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		 
		 return 0;
	}
}
