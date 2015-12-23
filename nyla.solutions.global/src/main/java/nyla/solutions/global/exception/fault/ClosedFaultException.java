package nyla.solutions.global.exception.fault;



/**
 * ERROR Code: DF008
 * Connection closed error
 * @author Gregory Green
 *
 */
public class ClosedFaultException extends FaultException
{
	/**
	 * 
	 * @param functionName the function name
	 */
	public ClosedFaultException()
	{
	}// -----------------------------------------------
	/**
	 * 
	 * @param functionName the function name
	 */
	public ClosedFaultException(Exception exception)
	{
		super(exception);

		this.setCategory(FaultException.DEFAULT_ERROR_CATEGORY_NM);
		this.setCode("DF008");
		
	}// -----------------------------------------------


	/**
	 * 
	 * @param functionName the function name
	 */
	public ClosedFaultException(String message, Exception e)
	{
		super(message, e);
		this.setCategory(FaultException.DEFAULT_ERROR_CATEGORY_NM);
		this.setCode("DF008");
	}// -----------------------------------------------
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3062061475584757100L;

}
