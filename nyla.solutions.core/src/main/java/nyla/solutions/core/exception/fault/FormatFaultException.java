package nyla.solutions.core.exception.fault;




/**
 * Security related error
 * ERROR
 * @author Gregory Green
 *
 */
public class FormatFaultException extends FaultException
{
	/**
	 * 
	 * @param functionName the function name
	 */
	public FormatFaultException()
	{
		init();
		
	}// -----------------------------------------------

	/**
	 * 
	 * @param functionName the function name
	 */
	public FormatFaultException(Exception exception)
	{
		super(exception);

		init();
		
	}// -----------------------------------------------

	/**
	 * 
	 * @param functionName the function name
	 */
	public FormatFaultException(String message)
	{
		super(message);
		init();
	}// -----------------------------------------------
	/**
	 * 
	 * @param functionName the function name
	 */
	public FormatFaultException(String message, Throwable cause)
	{
		super(message,cause);
		init();
	}// -----------------------------------------------
	private void init()
	{
		this.setCategory(FaultException.DEFAULT_ERROR_CATEGORY_NM);
		this.setCode("DF006");
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -3062061475584757100L;

}
