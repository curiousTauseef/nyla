package nyla.solutions.blueprint.jee.web.RESTful;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nyla.solutions.blueprint.jee.web.WebLock;
import nyla.solutions.global.exception.ConcurrencyException;
import nyla.solutions.global.util.Config;

/**
 * @author Gregory Green
 *
 */
public class WebReleaseLockSessionRESTfulCommand extends AbstractRESTfulCommand
{
	public void post(HttpServletRequest request, HttpServletResponse response)
	{
		java.io.Serializable requestor = (java.io.Serializable)request.getSession().getAttribute(this.requestorKey);

		if(!WebLock.acquireApplicationLock(request, requestor))
		{
			throw new ConcurrencyException("requestor="+requestor); 
		}	
	}//---------------------------------------------
	
	/**
	 * @return the requestorKey
	 */
	public String getRequestorKey()
	{
		return requestorKey;
	}
	/**
	 * @param requestorKey the requestorKey to set
	 */
	public void setRequestorKey(String requestorKey)
	{
		this.requestorKey = requestorKey;
	}
	
	private String requestorKey = Config.getProperty(WebReleaseLockSessionRESTfulCommand.class,"requestorKey","user");
	
}
