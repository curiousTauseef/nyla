/*
 * Created on Aug 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package nyla.solutions.formInjection.exception;


/**
 * 
 * <pre>
 * InvalidAttributeException provides a set of functions to
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class InvalidAttributeException extends Exception 
{
   
   static final long serialVersionUID = InvalidAttributeException.class
   .getName().hashCode();
   
	/**
	 * @param string
	 */
	public InvalidAttributeException(String string) {
		
		super(string);
	}
	
	public InvalidAttributeException() {
		super("InvalidAttributeException");
	}

	
}
