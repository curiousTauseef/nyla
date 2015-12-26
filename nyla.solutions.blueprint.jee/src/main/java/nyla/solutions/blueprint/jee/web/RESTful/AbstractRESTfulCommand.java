/**
 * 
 */
package nyla.solutions.blueprint.jee.web.RESTful;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Gregory Green
 *
 */
public class AbstractRESTfulCommand implements RESTfulCommand
{
	/**
	 * Default delete implementation
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response)
	{
		service(request, response);
	}//--------------------------------------------
	/**
	 * Default get implementation
	 */

	public void get(HttpServletRequest request, HttpServletResponse response)
	{
		service(request, response);
	}//---------------------------------------------
	/**
	 * Default post implementation
	 */
	
	public void post(HttpServletRequest request, HttpServletResponse response)
	{
		service(request, response);
	}//---------------------------------------------
	/**
	 * Default put implementation
	 */
	public void put(HttpServletRequest request, HttpServletResponse response)
	{
		service(request, response);
	}//---------------------------------------------
	protected void service(HttpServletRequest request, HttpServletResponse response)
	{	
	}//---------------------------------------------

}
