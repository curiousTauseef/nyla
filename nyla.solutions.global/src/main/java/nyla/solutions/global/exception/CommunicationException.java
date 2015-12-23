package nyla.solutions.global.exception;

import nyla.solutions.global.exception.CommunicationException;
import nyla.solutions.global.exception.FatalException;


/**
 * <pre>
 * CommunicationException provides a set of functions to
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class CommunicationException extends FatalException
{

	public static final String DEFAULT_ERROR_CODE = "COM000";
	public static final String DEFAULT_ERROR_CATEGORY = "COMM";
	
   /**
    * 
    */
   public CommunicationException()
   {
      super();
      this.setCode(DEFAULT_ERROR_CODE);
      this.setCategory(DEFAULT_ERROR_CATEGORY);
   }

   /**
    * @param aMessage
    */
   public CommunicationException(String aMessage)
   {
      super(aMessage);
      this.setCode(DEFAULT_ERROR_CODE);
      this.setCategory(DEFAULT_ERROR_CATEGORY);
   }

   /**
    * @param aThrowable
    */
   public CommunicationException(Throwable aThrowable)
   {
      super(aThrowable);
      this.setCode(DEFAULT_ERROR_CODE);
      this.setCategory(DEFAULT_ERROR_CATEGORY);
   }// --------------------------------------------


   /**
    * @param aMessage
    * @param aThrowable
    */
   public CommunicationException(String aMessage, Throwable aThrowable)
   {
      super(aMessage, aThrowable);
      this.setCode(DEFAULT_ERROR_CODE);
      this.setCategory(DEFAULT_ERROR_CATEGORY);
   }
   
   static final long serialVersionUID = CommunicationException.class.getName()
   .hashCode();
}
