package nyla.solutions.global.operations.logging;

import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import nyla.solutions.core.operations.logging.Log;
import nyla.solutions.core.util.Config;

/**
 * @author Gregory Green
 *
 */
public class Log4J implements Log
{
	 static 
	   {

	      try
	      {
	    	  
	    	  Properties properties = Config.getProperties();
	    	  
	    	  if(properties != null && 
	    			  String.valueOf(properties.keySet()).contains("log4j"))
	    	  {
	    		  PropertyConfigurator.configure(properties);
	    	  }
	    	  else
	    	  {
	    		  //use basic configuration
	    		  BasicConfigurator.configure();
	    	  }
	      }
	      catch(Exception e)
	      {
	         BasicConfigurator.configure();

	      }       

	   } //------------------------------------------------------
	 /**
	  * Default logger
	  */
     public Log4J()
     {
    	 this.logger = Logger.getLogger(Log4J.class);
     }// --------------------------------------------------------
	/**
	 * @see nyla.solutions.global.operations.logging.Log#setLoggingClass(java.lang.Class)
	 */
	public void setLoggingClass(Class<?> aClass)
	{
		logger = Logger.getLogger(aClass);

	}


	/**
	 * @see nyla.solutions.global.operations.logging.Log#debug(java.lang.Object)
	 */
	public void debug(Object message)
	{
		logger.debug(message);

	}

	/**
	 * @see nyla.solutions.global.operations.logging.Log#debug(java.lang.Object, java.lang.Throwable)
	 */
	public void debug(Object message, Throwable t)
	{
		logger.debug(message, t);
	}// --------------------------------------------------------

	/**
	 * @see nyla.solutions.global.operations.logging.Log#info(java.lang.Object)
	 */
	public void info(Object message)
	{
		this.logger.info(message);
	}// --------------------------------------------------------

	/**
	 * @see nyla.solutions.global.operations.logging.Log#info(java.lang.Object, java.lang.Throwable)
	 */
	public void info(Object message, Throwable t)
	{
		logger.info(message, t);
	}// --------------------------------------------------------

	/**
	 * @see nyla.solutions.global.operations.logging.Log#error(java.lang.Object)
	 */
	public void error(Object message)
	{
		this.logger.error(message);

	}// --------------------------------------------------------

	/**
	 * @see nyla.solutions.global.operations.logging.Log#error(java.lang.Object, java.lang.Throwable)
	 */
	public void error(Object message, Throwable t)
	{
		logger.error(message, t);

	}// --------------------------------------------------------

	/**
	 * @see nyla.solutions.global.operations.logging.Log#fatal(java.lang.Object)
	 */
	public void fatal(Object message)
	{
		logger.fatal(message);
	}// --------------------------------------------------------

	/**
	 * @see nyla.solutions.global.operations.logging.Log#fatal(java.lang.Object, java.lang.Throwable)
	 */
	public void fatal(Object message, Throwable t)
	{
		logger.fatal(message, t);
	}// --------------------------------------------------------

	/**
	 * @see nyla.solutions.global.operations.logging.Log#warn(java.lang.Object)
	 */
	public void warn(Object message)
	{
		logger.warn(message);

	}// --------------------------------------------------------

	/**
	 * @see nyla.solutions.global.operations.logging.Log#warn(java.lang.Object, java.lang.Throwable)
	 */
	public void warn(Object message, Throwable t)
	{
		logger.warn(message, t);

	}
	private Logger logger;
}
