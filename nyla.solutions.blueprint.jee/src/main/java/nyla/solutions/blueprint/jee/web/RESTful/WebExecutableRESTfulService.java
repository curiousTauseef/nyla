package nyla.solutions.blueprint.jee.web.RESTful;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.patterns.command.Environment;
import nyla.solutions.global.patterns.command.Executable;
import nyla.solutions.global.util.Config;


/**
 * execute executable for post operations
 * @author Gregory Green
 *
 */
public class WebExecutableRESTfulService extends AbstractRESTfulCommand
		implements Executable
{
	/**
	 * Calls the execute method
	 */
	public void post(HttpServletRequest request, HttpServletResponse response)
	{
		Environment env = new Environment();
		
		this.execute(env);
		
		try
		{
			request.getRequestDispatcher(this.nextView).forward(request, response);
			
			
		} 
		catch (ServletException e)
		{
			throw new SystemException(e);
		} 
		catch (IOException e)
		{
			throw new SystemException(e);
		}
	}//---------------------------------------------
	/**
	 * @see com.merck.mrl.global.patterns.command.Executable#execute(com.merck.mrl.global.patterns.command.Environment, java.lang.String[])
	 */
	public Integer execute(Environment env)
	{
		if(this.executable == null)
			throw new RequiredException("this.executable in WebExecutableRESTfulService");
		
		return this.executable.execute(env);
	}//---------------------------------------------
	/**
	 * @return the executable
	 */
	public Executable getExecutable()
	{
		return executable;
	}//---------------------------------------------
	/**
	 * @param executable the executable to set
	 */
	public void setExecutable(Executable executable)
	{
		this.executable = executable;
	}//---------------------------------------------
	/**
	 * @return the nextView
	 */
	public String getNextView()
	{
		return nextView;
	}//---------------------------------------------
	/**
	 * @param nextView the nextView to set
	 */
	public void setNextView(String nextView)
	{
		this.nextView = nextView;
	}//---------------------------------------------

	private Executable executable = null;
	private String nextView = Config.getProperty(WebExecutableRESTfulService.class,"nextView", "/");

}
