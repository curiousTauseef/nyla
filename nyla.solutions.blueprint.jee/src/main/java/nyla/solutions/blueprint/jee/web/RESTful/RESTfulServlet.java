
package nyla.solutions.blueprint.jee.web.RESTful;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nyla.solutions.global.patterns.servicefactory.ServiceFactory;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;


/**
 * Front controller for RESTFul web based services
 * @author Gregory Green
 *
 */
public class RESTfulServlet extends HttpServlet
{
	
	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = -5187885938900667842L;
	/**
	 * Initialize the RestFulCommand based on the given "cmd"
	 * The service will be looked up by the class if the cmdParameter is not provided 
	 */
	public void init(ServletConfig config) throws ServletException
	{				
		super.init(config);
	
		String serviceName = config.getInitParameter(cmdParameter);
		if(serviceName == null || serviceName.length() == 0)
			serviceName = this.getClass().getName();
		
		cmd = (RESTfulCommand)ServiceFactory.getInstance().create(serviceName);
		
		
		this.config = config;
	}//---------------------------------------------
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		try 
		{
			obtainCommand(req).post(req, resp);
		} 
		catch (RuntimeException e) 
		{	
			dispatchError(req,resp,e);
		}
		
	}//---------------------------------------------
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		try 
		{
			obtainCommand(req).put(req, resp);
		} 
		catch (RuntimeException e) 
		{	
			dispatchError(req,resp,e);
		}
    }//---------------------------------------------	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		try 
		{
			obtainCommand(req).delete(req, resp);
		} 
		catch (RuntimeException e) 
		{	
			dispatchError(req,resp,e);
		}
	}//---------------------------------------------
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{
		try 
		{
			obtainCommand(req).get(req, resp);
		} 
		catch (RuntimeException e) 
		{	
			dispatchError(req,resp,e);
		}
		
	}//---------------------------------------------	
	/**
	 * Forward to a path given in the servlet configuration
	 * @param request the HTTP request
	 * @param response the HTTP response
	 * @param e the exception
	 * @throws IOException
	 */
	private synchronized void dispatchError(HttpServletRequest request, HttpServletResponse response, RuntimeException e)
	throws IOException, ServletException
	{
		Debugger.printError(e);
		
		if(e == null)
			return;
		
		String uri = request.getRequestURI();
		Debugger.println(this,"uri="+uri);
		
		//set page exist for exception
		String className = e.getClass().getName();
		
		Debugger.println(this,"className="+className);
		
		String configParam = uri+"="+className;
		Debugger.println(this,"looking for configParam="+configParam);
		
		String path = this.config.getInitParameter(configParam);
		
		Debugger.println(this,"path="+path);
		
		if(path == null || path.length() == 0)
		{
			throw e; //rethrow error
		}
		
		Debugger.println(this,"forwarding request to path="+path);
		request.setAttribute(exceptionAttributeName, e);
		
		request.getRequestDispatcher(path).forward(request, response);
	}//---------------------------------------------
	private RESTfulCommand obtainCommand(HttpServletRequest request)
	{
		String cmdParam = request.getParameter(cmdParameter);
		
		if(cmdParam != null && cmdParam.length() > 0)
			return (RESTfulCommand)ServiceFactory.getInstance().create(cmdParam);
		
		return this.cmd;
	}//---------------------------------------------
	/**
	 * @return the cmdParameter
	 */
	public String getCmdParameter()
	{
		return cmdParameter;
	}
	/**
	 * @param cmdParameter the cmdParameter to set
	 */
	public void setCmdParameter(String cmdParameter)
	{
		this.cmdParameter = cmdParameter;
	}
	/**
	 * @return the cmd
	 */
	public RESTfulCommand getCmd()
	{
		return cmd;
	}
	/**
	 * @param cmd the cmd to set
	 */
	public void setCmd(RESTfulCommand cmd)
	{
		this.cmd = cmd;
	}

	
	/**
	 * @return the exceptionAttributeName
	 */
	public String getExceptionAttributeName()
	{
		return exceptionAttributeName;
	}
	/**
	 * @param exceptionAttributeName the exceptionAttributeName to set
	 */
	public void setExceptionAttributeName(String exceptionAttributeName)
	{
		this.exceptionAttributeName = exceptionAttributeName;
	}


	private String exceptionAttributeName = Config.getProperty(getClass(),"exceptionAttributeName","exception");
	private String cmdParameter = Config.getProperty(getClass(),"cmdParameter","cmd");
	private RESTfulCommand cmd = null;
	private ServletConfig config = null;
}
