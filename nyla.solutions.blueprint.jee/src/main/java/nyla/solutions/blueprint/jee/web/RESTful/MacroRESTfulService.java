
package nyla.solutions.blueprint.jee.web.RESTful;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Macro implementation to execute 
 * @author Gregory Green
 *
 */
public class MacroRESTfulService implements RESTfulCommand
{

	/** 
	 * Execute the delete processing on each service
	 */
	public synchronized void delete(HttpServletRequest request, HttpServletResponse response)
	{
		//execute each RESTful service
		for(Iterator<RESTfulCommand> i = this.restfulServices.iterator();i.hasNext();)
		{
			((RESTfulCommand)i.next()).delete(request, response);
		}
	}//---------------------------------------------

	/* (non-Javadoc)
	 * @see com.merck.mrl.j2ee.web.RESTful.RESTfulCommand#get(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public  synchronized void get(HttpServletRequest request, HttpServletResponse response)
	{
		//execute each RESTful service
		for(Iterator<RESTfulCommand> i = this.restfulServices.iterator();i.hasNext();)
		{
			((RESTfulCommand)i.next()).get(request, response);
		}

	}

	/**
	 * Execute the delete processing on each service
	 */
	public  synchronized void post(HttpServletRequest request, HttpServletResponse response)
	{
		//execute each RESTful service
		for(Iterator<RESTfulCommand> i = this.restfulServices.iterator();i.hasNext();)
		{
			((RESTfulCommand)i.next()).post(request, response);
		}
	}//---------------------------------------------

	/**
	 * Execute the put processing on each service
	 */
	public  synchronized void put(HttpServletRequest request, HttpServletResponse response)
	{
		//execute each RESTful service
		for(Iterator<RESTfulCommand> i = this.restfulServices.iterator();i.hasNext();)
		{
			((RESTfulCommand)i.next()).put(request, response);
		}

	}//---------------------------------------------
	
	/**
	 * @return the restfulServices
	 */
	public synchronized  Collection<RESTfulCommand> getRestfulServices()
	{
		return restfulServices;
	}

	/**
	 * @param restfulServices the restfulServices to set
	 */
	public  synchronized void setRestfulServices(Collection<RESTfulCommand> restfulServices)
	{
		this.restfulServices = restfulServices;
	}

	private Collection<RESTfulCommand> restfulServices = null;

}
