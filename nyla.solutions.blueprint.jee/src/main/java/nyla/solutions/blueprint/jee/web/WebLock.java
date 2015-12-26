package nyla.solutions.blueprint.jee.web;

import java.io.Serializable;
import java.io.SyncFailedException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import nyla.solutions.global.util.Debugger;


public class WebLock
{
	/**
	 * 
	 * @param request the HTTP request
	 * @param requestor the HTTP response
	 * @return true is application lock was acquired
	 * @throws SyncFailedException
	 * @throws SecurityException
	 */
	public synchronized static boolean acquireApplicationLock(HttpServletRequest request)
	{
		return acquireApplicationLock(request,"SYSTEM");
	}//---------------------------------------------
	/**
	 * 
	 * @param request the HTTP request
	 * @param requestor the HTTP response
	 * @return true is application lock was acquired
	 * @throws SyncFailedException
	 * @throws SecurityException
	 */
	public synchronized static boolean acquireApplicationLock(HttpServletRequest request, Serializable requestor) 
	{		

		if(request == null)
			return false;
		
		if(requestor == null)
			return false;
		
		ServletContext application = Web.getApplication(request);
		//get previous
		Serializable previous = (Serializable)application.getAttribute(lockKey); 
		
		Debugger.println("previous="+previous);
		
		if(previous != null && previous.toString().length() > 0)
	    {
			return false;	
	    }
		
		application.setAttribute(lockKey, requestor);
		
		return true;
	}//---------------------------------------------	
	/**
	 * release the application lock
	 * @param request the HTTP request
	 * @param requestor the HTTP requestor
	 * @return
	 * @throws SyncFailedException
	 * @throws SecurityException
	 */
	public synchronized static boolean releaseApplicationLock(HttpServletRequest request, Serializable requestor) 
	throws SyncFailedException, SecurityException
	{		

		if(request == null)
			return false;
		
		ServletContext application = Web.getApplication(request);
		
		//get previous
		Serializable previous = (Serializable)application.getAttribute(lockKey); 
		
		if(previous != null && previous.toString().length() > 0)
	    {
			Debugger.printWarn(WebLock.class,"Lock was owner by "+previous+" but release by "+requestor);
	    }
		
		application.setAttribute(lockKey, null);
		
		return true;
	}//---------------------------------------------
	/**
	 * Get the web lock owner
	 * @param request the HTTP Servlet request
	 * @return
	 */
	public synchronized Serializable acquireApplicationLockOwner(HttpServletRequest request)
	{
		if(request == null)
			return null;
				
			
		ServletContext application = Web.getApplication(request);
		
		//get previous
		return (Serializable)application.getAttribute(lockKey); 
		
	}//---------------------------------------------
	private static String lockKey = "lock";
}
