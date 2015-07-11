package nyla.solutions.global.exception;

import nyla.solutions.global.exception.fault.FaultException;



/**
 * Security related error
 * @author Gregory Green
 *
 */
public class SecurityException extends FaultException
{
	public static final String DEFAULT_ERROR_CODE = "SC0000";
	public static final String DEFAULT_ERROR_CATEGORY = "SECURITY";
	
	public SecurityException()
	{
		super("Security Exception");
		this.setCategory(DEFAULT_ERROR_CATEGORY);
	}

	/**
	 * 
	 * @param functionName the function name
	 */
	public SecurityException(Exception exception)
	{
		super(exception);

		this.setCategory(DEFAULT_ERROR_CATEGORY);
		this.setCode(DEFAULT_ERROR_CODE);
		
	}// -----------------------------------------------

	/**
	 * 
	 * @param functionName the function name
	 */
	public SecurityException(String message)
	{
		super(message);
		this.setCategory(DEFAULT_ERROR_CATEGORY);
		this.setCode(DEFAULT_ERROR_CODE);
	}// -----------------------------------------------

	/**
	 * 
	 * @param functionName the function name
	 */
	public SecurityException(String message, Exception e)
	{
		super(message, e);
		this.setCategory(DEFAULT_ERROR_CATEGORY);
		this.setCode(DEFAULT_ERROR_CODE);
	}// -----------------------------------------------
	/**
	 * 
	 */
	private static final long serialVersionUID = -3062061475584757100L;

}
