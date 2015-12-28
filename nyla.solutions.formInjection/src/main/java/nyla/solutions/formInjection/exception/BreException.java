package nyla.solutions.formInjection.exception;

/**
 * <pre>
 * BreException business rule exception
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class BreException extends Exception
{

   /**
    * Constructor for BreException initalizes internal 
    * data settings.
    * 
    */
   public BreException()
   {
      super();
   }//--------------------------------------------
   

   /**
    * Constructor for BreException initalizes internal 
    * data settings.
    * @param aMessage
    */
   public BreException(String aMessage)
   {
      super(aMessage);
   }//--------------------------------------------
   /**
    * Constructor for BreException initalizes internal 
    * data settings.
    * @param aMessage
    * @param aThrowable
    */
   public BreException(String aMessage, Throwable aThrowable)
   {
      super(aMessage, aThrowable);
   }//--------------------------------------------
   /**
    * Constructor for BreException initalizes internal 
    * data settings.
    * @param aThrowable
    */
   public BreException(Throwable aThrowable)
   {
      super(aThrowable);      
   }//--------------------------------------------
   static final long serialVersionUID = BreException.class.getName()
   .hashCode();
}
