package nyla.solutions.core.exception.fault;




/**
 * Handles exception mapping thrown exceptions/arguments
 * @author Gregory Green
 *
 */
public interface FaultService
{
	
	/**
	 * Raise an  exception based on the given  exception/argument
	 * @param e the thrown error
	 * @param argument the processed argument
	 * @throws FaultException the GEDI exception will be thrown
	 */
	public FaultException raise(Throwable e, Object argument);
	
	
	/**
	 * Raise an  exception based on the given  exception
	 * @param e the thrown error
	 * @throws FaultException the GEDI exception will be thrown
	 */
	public FaultException raise(Throwable e);
	
}
