package nyla.solutions.global.exception.fault;




/**
 * Security related error
 * @author Gregory Green
 *
 */
public class RequiredFaultException extends FaultException
{
	/**
	 * 
	 * @param functionName the function name
	 */
	public RequiredFaultException()
	{
		this.setCategory(FaultException.DEFAULT_ERROR_CATEGORY_NM);
		this.setCode("DF005");
		
	}// -----------------------------------------------
	/**
	 * 
	 * @param functionName the function name
	 */
	public RequiredFaultException(Exception exception)
	{
		super(exception);

		this.setCategory(FaultException.DEFAULT_ERROR_CATEGORY_NM);
		this.setCode("DF005");
		
	}// -----------------------------------------------

	/**
	 * 
	 * @param functionName the function name
	 */
	public RequiredFaultException(String message)
	{
		super(message);
		this.setCategory(FaultException.DEFAULT_ERROR_CATEGORY_NM);
		this.setCode("DF005");
	}// -----------------------------------------------
	/**
	 * 
	 */
	private static final long serialVersionUID = -3062061475584757100L;

}
