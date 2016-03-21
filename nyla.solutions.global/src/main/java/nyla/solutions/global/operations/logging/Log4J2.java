package nyla.solutions.global.operations.logging;

import nyla.solutions.global.util.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <pre>
 * Config properties
 * 
 * 
 * log4j.patternLayout=%d{MM/dd/yyyy HH:mm:ss.SSS} %-5level  - %msg%n
 * log4j.configurationFile=config.properties
 * log4j.configurationFactory=nyla.solutions.global.operations.logging.Log4J2ConfigurationFactory
 * org.apache.logging.log4j.level=DEBUG
 * 
 * </pre>
 * @author Gregory Green
 *
 */
public class Log4J2 implements Log
{
	/**
	 * Ex: 
	 */
	public static final String DEFALT_LAYOUT = Config.getProperty("log4j.patternLayout",
			"%d{MM/dd/yyyy HH:mm:ss.SSS} %-5level  - %msg%n");

	 static 
	   {

	      try
	      {
	    	
	    	  System.setProperty("log4j.configurationFile",
	    			  System.getProperty("log4j.configurationFile",Config.getProperty("log4j.configurationFile",Config.DEFAULT_PROP_FILE_NAME)));
	    	  
	    	  System.setProperty("log4j.configurationFactory", 
	    			  System.getProperty("log4j.configurationFactory", Config.getProperty("log4j.configurationFactory",Log4J2ConfigurationFactory.class.getName())));
	   
	     	  System.setProperty("org.apache.logging.log4j.level", 
	    			  System.getProperty("org.apache.logging.log4j.level", Config.getProperty("org.apache.logging.log4j.level","DEBUG")));
	   
	     	  //Log4jContextSelector
	     	  //System.setProperty("Log4jContextSelector", 
	    	  //		  System.getProperty("Log4jContextSelector", Config.getProperty("Log4jContextSelector",AsyncLoggerContextSelector.class.getName())));
	     	  
	      }
	      catch(Exception e)
	      {
	    	  e.printStackTrace();
	      }       

	   } //------------------------------------------------------

	 public Log4J2()
	 {
		setLoggingClass(Log4J2.class);
	 }// --------------------------------------------------------
	 
	/**
	 * @see nyla.solutions.global.operations.logging.Log#setLoggingClass(java.lang.Class)
	 */
	@Override
	public void setLoggingClass(Class<?> aClass)
	{
		logger = LogManager.getLogger(aClass);

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

	 private Logger logger = null;
}
