/**
 * 
 */
package nyla.solutions.global.patterns.conversion.exception;

/**
 * Generic exception for transformation
 * @author Gregory Green
 *
 */
public class TransformationException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -431917377869570575L;

	public TransformationException()
	{
		super();
		// TODO Auto-generated constructor stub
	}// --------------------------------------------

	public TransformationException(String message, Throwable cause)
	{
		super(message, cause);	
	}// --------------------------------------------

	public TransformationException(String message)
	{
		super(message);
	}// --------------------------------------------

	public TransformationException(Throwable cause)
	{
		super(cause);
	}// --------------------------------------------
	

}
