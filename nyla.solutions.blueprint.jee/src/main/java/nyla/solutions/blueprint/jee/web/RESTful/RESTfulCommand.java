package nyla.solutions.blueprint.jee.web.RESTful;

import javax.servlet.http.*;

/**
 * Interface to implement a restful command
 * @author Gregory Green
 *
 */
public interface RESTfulCommand
{
	/**
	 * Create operation
	 * @param request
	 * @param respinse
	 */
	public void post(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * Read operation
	 * @param request the HTTP request
	 * @param response the HTTP response
	 */
	public void get(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * Update operation
	 * @param request HTTP request
	 * @param response the HTTP response
	 */
	public void put(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * Delete operation
	 * @param request HTTP request
	 * @param response the HTTP response
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response);
}
