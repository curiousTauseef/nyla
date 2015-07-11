package nyla.solutions.global.patterns;

import nyla.solutions.global.exception.ConnectionException;
import nyla.solutions.global.patterns.Disposable;

/**
 * Represents a connect DAO
 * @author Gregory Green
 *
 */
public interface Connectable extends Disposable
{
	/**
	 * 
	 * @return true if is connected
	 */
	boolean isConnected();
	

	
	/**
	 * Establish a connection
	 * @throws ConnectionException
	 */
	void connect()
	throws ConnectionException;
}
