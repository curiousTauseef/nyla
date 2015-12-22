/*
 * Created on Feb 6, 2004
 *
 * <b>ConnectionException</b>
 */
package nyla.solutions.global.exception;

import nyla.solutions.global.exception.ConfigException;
import nyla.solutions.global.exception.FatalException;


/**
 * @author Gregory Grene
 * @version 1.0
 *
 * <b>ConnectionException</b>  
 * 
 */
public class ConfigException extends FatalException
{
	public static final String DEFAULT_ERROR_CODE = "CONF000";
   /**
    * 
    */
   public ConfigException()
   {
      super("Configuration error");
      this.setCode(DEFAULT_ERROR_CODE);
   }//-------------------------------------------
   /**
    * @param arg0
    */
   public ConfigException(String aMessage)
   {
      super(aMessage);
      this.setCode(DEFAULT_ERROR_CODE);
   }//---------------------------------------------
   /**
    * @param aMessage configuration
    * @param aThrowable
    */
   public ConfigException(String aMessage, Throwable aThrowable)
   {
      super(aMessage, aThrowable);
      this.setCode(DEFAULT_ERROR_CODE);
   }//---------------------------------------------\
   /**
    * @param aThrowable
    */
   public ConfigException(Throwable aThrowable)
   {
      super(aThrowable);
      this.setCode(DEFAULT_ERROR_CODE);
   }// --------------------------------------------
   static final long serialVersionUID = ConfigException.class.getName()
   .hashCode();
}
