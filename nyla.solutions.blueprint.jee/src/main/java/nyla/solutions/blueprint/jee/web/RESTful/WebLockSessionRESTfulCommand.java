package nyla.solutions.blueprint.jee.web.RESTful;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nyla.solutions.blueprint.jee.web.WebLock;
import nyla.solutions.global.exception.ConcurrencyException;
import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.util.Config;

/**
 * @author Gregory Green
 *
 */
public class WebLockSessionRESTfulCommand extends AbstractRESTfulCommand
{
	/**
	 * 
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
	{		

		Serializable requestor = (Serializable)request.getParameter(this.requestorParamName);
		
		
		if(requestor == null)
			throw new RequiredException("requestor in request");
		
		//if(!WebLock.acquireApplicationLock(request,requestor))
		if(!WebLock.acquireApplicationLock(request,requestor))
		{
			throw new ConcurrencyException("Could not lock application for "+requestor);
		}	
		
	}//---------------------------------------------		
	/**
	 * @return the requestorParamName
	 */
	public String getRequestorParamName()
	{
		return requestorParamName;
	}//---------------------------------------------
	/**
	 * @param requestorParamName the requestorParamName to set
	 */
	public void setRequestorParamName(String requestorParamName)
	{
		this.requestorParamName = requestorParamName;
	}


	private String requestorParamName = Config.getProperty(WebLockSessionRESTfulCommand.class,"requestorParamName","requestor");
	
}
