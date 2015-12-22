package nyla.solutions.global.exception;


import nyla.solutions.global.exception.ConfigException;
import nyla.solutions.global.exception.MissingConfigPropertiesException;
import nyla.solutions.global.operations.ClassPath;
import nyla.solutions.global.util.Config;

public class MissingConfigPropertiesException extends ConfigException
{

   /**
	 * 
	 */
	private static final long serialVersionUID = 5689827263132067133L;

/**

    * Constructor for SetupException initializes internal 

    * data settings.

    * 

    */

   public MissingConfigPropertiesException()
   {
	   
	   try
		{
		   StringBuilder msg = new StringBuilder("Missing ")
		   .append(Config.RESOURCE_BUNDLE_NAME)
		   .append(".properties")
		   .append(" in classpath: ")
		   .append(ClassPath.getClassPathText());
			
			
			message = msg.toString();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
   }//--------------------------------------------

   @Override
	public String getMessage()
	{
	   return message;
	}
   private String message = null;
	   

}
