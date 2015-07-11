package nyla.solutions.global.exception;

import nyla.solutions.global.exception.fault.FaultException;



/**
 * Session time-out error
 * ERROR-CODE: DF009
 * @author Gregory Green
 *
 */
public class SessionExpiredException extends SecurityException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -62434440533146476L;

	/**
	 * 
	 * @param functionName the function name
	 */
	public SessionExpiredException()
	{
		this.setCategory("DEFAULT");
		this.setCode("DF009");
		
	}// -----------------------------------------------

	/**
	 * 
	 * @param functionName the function name
	 */
	public SessionExpiredException(Exception exception)
	{
		super(exception);

		this.setCategory("DEFAULT");
		this.setCode("DF009");
		
	}// -----------------------------------------------

	/**
	 * 
	 * @param functionName the function name
	 */
	public SessionExpiredException(String message)
	{
		super(message);
		this.setCategory(FaultException.DEFAULT_ERROR_CATEGORY_NM);
		this.setCode("DF009");
	}// -----------------------------------------------
	

}
