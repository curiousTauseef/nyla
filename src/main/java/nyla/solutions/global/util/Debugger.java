package nyla.solutions.global.util;

import java.io.*;
import java.util.*;
import java.lang.reflect.*;

import nyla.solutions.global.exception.ConfigException;
import nyla.solutions.global.exception.SetupException;
import nyla.solutions.global.operations.Log;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.JavaBean;

/**
 * 
 * <pre>
 * Debugger provides useful methods for obtaining exception stack traces.
 * It can build reader friendly strings for objects 
 * that do not implement their toString method.
 * 
 * It also provides a set of print functions 
 * to log DEBUG, INFO, WARN and FATAL level messages using
 * the Debugger.println(...), Debugger.printInfo(...),
 * Debugger.printWarn(...) and Debugger.printFatal(...) methods respectively.
 * 
 * The default log object implementation is solutions.global.operations.Log4J.
 * 
 * Set the configuration property  to plug-in another logger (@see Config more information);
 * 
 * solutions.global.util.Debugger.logClass=className
 * 
 * The logClass class name indicated must implement the 
 * solutions.global.operations.Log interface.
 * 
 * Sample Property File
 * 
 *#-------------------------------
 *# Debugger log instance uses Log4J
 *# You can directly include the log4J properties in the configuration property files (@see Config object)
 *# Log4J properties
 *#
 *log4j.rootLogger=DEBUG, stdout
 *log4j.logger.PACKAGE_NAME=ERROR,file_error
 *log4j.logger.YYY=DEBUG, file_all
 *log4j.logger.org.apache=ERROR,stdout
 *log4j.logger.org.springframework=ERROR,stdout 	
 * 	
 *#Standard OUT
 *log4j.appender.stdout=org.apache.log4j.ConsoleAppender
 *log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
 *#log4j.appender.stdout.layout.ConversionPattern=%d [%F:%L] - %x %m%n
 *#log4j.appender.stdout.layout.ConversionPattern= %p: %d{HH:mm:ss} [%F:%L] - %x %m%n
 *#log4j.appender.stdout.layout.ConversionPattern=%d{HH:mm:ss} [%c:%L] %m%n
 *#log4j.appender.stdout.layout.ConversionPattern=%d{HH:mm:ss} %m%n
 *log4j.appender.stdout.layout.ConversionPattern=%p: %d [%c] - %m%n
 * 
 *#
 *# FILE Output
 *log4j.file_all.category=DEBUG
 *log4j.appender.file_all=org.apache.log4j.RollingFileAppender
 *log4j.appender.file_all.File=/temp/logs/system.log
 *log4j.appender.file_all.MaxFileSize=10MB
 *log4j.appender.file_all.MaxBackupIndex=3
 *log4j.appender.file_all.layout=org.apache.log4j.PatternLayout
 *log4j.appender.file_all.layout.ConversionPattern=%p: %d [%c] - %m%n
 * 	
 * 	
 *#
 *# FILE Output
 *#log4j.file_error.category=ERROR
 *log4j.appender.file_error=org.apache.log4j.RollingFileAppender
 *log4j.appender.file_error.File=temp/logs/error.log
 *log4j.appender.file_error.MaxFileSize=10MB
 *log4j.appender.file_error.MaxBackupIndex=3
 *log4j.appender.file_error.layout=org.apache.log4j.PatternLayout
 *log4j.appender.file_error.layout.ConversionPattern=%p: %d [%c] - %m%n
 * 	
 * 	
 *#Emailing example
 *#email appender
 *log4j.appender.mail=org.apache.log4j.net.SMTPAppender
 *log4j.appender.mail.BufferSize=1
 *log4j.appender.mail.SMTPHost=smtp.myservername.xx
 *log4j.appender.mail.From=fromemail@myservername.xx
 *log4j.appender.mail.To=toemail@myservername.xx
 *log4j.appender.mail.Subject=Log ...
 *log4j.appender.mail.threshold=error
 *log4j.appender.mail.layout=org.apache.log4j.PatternLayout
 *log4j.appender.mail.layout.ConversionPattern=%d{ABSOLUTE} %5p %c{1}:%L - %m%n
 * 
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.5
 */
public class Debugger
{
	/**
	 * LOG_CLASS_NAME_PROP = "solutions.global.util.Debugger.logClass"
	 */
	public static final String LOG_CLASS_NAME_PROP = "nyla.solutions.global.util.Debugger.logClass";

	private static Class<?> logClass;
	private static HashMap<Class<?>, Log> logMap = new HashMap<Class<?>, Log>();
	static
	{

		try
		{

			logClass = Class.forName(Config.getProperty(LOG_CLASS_NAME_PROP,
					"nyla.solutions.global.operations.Log4J"));
			         
			defaultLogger = (Log) logClass.newInstance();

			
			String configFile = Config.getLocation();
			if(configFile != null)
				getLog(Debugger.class).debug(
					"CONFIG: properties loaded from " +configFile );
			//else
				//getLog(Debugger.class).debug(
				//		"CONFIG: NO properties loaded");

		}
		catch(SetupException e)
		{
			throw e;
		}
		catch(ConfigException e)
		{
			throw e;
		}
		catch (RuntimeException e)
		{	
			throw new SetupException(e);

		}
		catch (NoClassDefFoundError e)
		{
			throw new SetupException("Check value of "+LOG_CLASS_NAME_PROP+" in confi file"+Config.getLocation(),e);
		}
		catch (ClassNotFoundException e)
		{
			throw new SetupException("Check value of "+LOG_CLASS_NAME_PROP+" in confi file"+Config.getLocation(),e);
		}
		catch (IllegalAccessException e)
		{
			throw new SetupException("Check value of "+LOG_CLASS_NAME_PROP+" in confi file"+Config.getLocation(),e);
		}	
		catch (InstantiationException e)
		{
			throw new SetupException("Check value of "+LOG_CLASS_NAME_PROP+" in confi file"+Config.getLocation(),e);
		}			

	} // ------------------------------------------------------

	/**
	 * 
	 * 
	 * 
	 * @param c the calling class
	 * 
	 * @return the log 4 J category
	 */

	/*
	 * public static Category getLog(Class c)
	 * 
	 * {
	 * 
	 * try
	 * 
	 * {
	 * 
	 * 
	 * 
	 * return Logger.getLogger(c);
	 * 
	 * }
	 * 
	 * catch(Exception e)
	 * 
	 * {
	 * 
	 * e.printStackTrace();
	 * 
	 * BasicConfigurator.configure();
	 * 
	 * return Logger.getLogger(c);
	 * 
	 * }
	 * 
	 * }
	 */
	// -----------------------------------------------

	/**
	 * 
	 * @param c the calling class
	 * @return the logger implementation
	 */

	@SuppressWarnings("rawtypes")
	public static Log getLog(Class c)
	{

		try
		{
			if (c == null || logClass == null)
				return defaultLogger;

			// check cache
			Log logger = logMap.get(c);

			if (logger == null)
			{
				// create an put in map
				logger = (Log) logClass.newInstance();
				logger.setLoggingClass(c);
				logMap.put(c, logger);
			}

			return logger;

		}
		catch (Exception e)
		{

			e.printStackTrace();

			return defaultLogger;

		}

	} // -----------------------------------------------

	/**
	 * 
	 * convert throwable to a stack trace
	 * 
	 * @param t the throwable
	 * 
	 * @return the stack trace string
	 */

	public static String stackTrace(Throwable t)
	{
		if (t == null)
		{
			return "Debugger.stackTrace(null) NULL EXCEPTION (NO TRACE AVAILABLE)!!";
		}

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		return sw.toString();
	} // -----------------------------------------

	/**
	 * 
	 * @param obj input object
	 * @return the string representation
	 */
	public static String toString(Object obj)
	{
		return toString(obj, new HashSet<Object>());
	}// ------------------------------------------------

	/**
	 * 
	 * @param obj input object
	 * @return the string representation
	 */
	private static String toString(Object obj, Set<Object> set)
	{

		if (obj == null)

			return "null";

		if (set.contains(obj))
			return ""; // already printed

		set.add(obj);

		if (obj instanceof Object[])

		{

			Object[] objs = (Object[]) obj;

			StringBuilder arrayText = new StringBuilder("{");

			for (int i = 0; i < objs.length; i++)

			{

				arrayText.append("[" + i + "]=").append(Debugger.toString(objs[i], set));

				// append comma

				if (i + 1 != objs.length)

					arrayText.append(" ,");

			}// end for

			arrayText.append("}");

			return arrayText.toString();

		}

		if (obj instanceof String

		|| obj instanceof Integer

		|| obj instanceof StringBuilder)

			return obj.toString();

		Class<?> cl = obj.getClass();

		StringBuilder r = new StringBuilder(cl.getName());

		// inspect the fields of this class and all superclasses

		do
		{

			r.append("[");

			Field[] fields = cl.getDeclaredFields();

			AccessibleObject.setAccessible(fields, true);

			// get the names and values of all fields

			for (int i = 0; i < fields.length; i++)

			{

				Field f = fields[i];

				r.append(f.getName()).append("=");

				try

				{

					Object val = f.get(obj);

					if (val != null)

					{

						if (val instanceof Object[])

						{

							Object[] objs = (Object[]) val;

							for (int c = 0; c < objs.length; c++)

							{

								r.append(Debugger.toString(objs[c], set));

							}

						}

						else

							r.append(val);

					}

					else

						r.append("null");

				}

				catch (Exception e)

				{

					e.printStackTrace();

				}

				if (i < fields.length - 1)

					r.append(",");

			}

			r.append("]");

			cl = cl.getSuperclass();

		}

		while (cl != Object.class);

		return r.toString();

	} // --------------------------------------------------------

	/**
	 * 
	 * @param aObject the object
	 * @return the map version of object
	 */
	public static  Map<Object,Object>  toMap(Object aObject)
	{

		try

		{

			return JavaBean.toMap(aObject);

		}

		catch (Exception e)

		{

			throw new RuntimeException(stackTrace(e));

		}

	}// ------------------------------

	/**
	 * 
	 * Uses reflection to the print all attributes of a given object
	 * @param obj the object to print
	 */

	public static void dump(Object obj)
	{
		println("DUMP:" + toString(obj));
	} // --------------------------------------------------------

	/**
	 * 
	 * Print aObject information and the caller module for tracing *
	 * @param aCaller the calling object
	 * @param message the message/object to print 
	 * 
	 */

	public static void println(Object aCaller, Object message)
	{

		Class<?> c = Debugger.class;

		
		if (DEBUG)
		{
			StringBuilder text = new StringBuilder();


			if (aCaller != null)
			{

				if (aCaller instanceof Class)

				{

					c = (Class<?>) aCaller;

					text.append(c.getName()).append(": ");

				}
				else

				if (aCaller instanceof String)
				{

					text.append(aCaller).append(": ");

				}
				else
				{

					c = aCaller.getClass();
					text.append(c.getName()).append(": ");
				}
			}

			getLog(c).debug(text.append(message));

		}

	} // -----------------------------------------
	/**
	 * Set a boolean to determine if Debugger.println messages will be printed.
	 * @param willDebug boolean to determine is print line message will be printed
	 */
	public void setDoDebug(boolean willDebug)
	{

		DEBUG = willDebug;

	} // -----------------------------------------

	/**
	 * Print a message using the configured Log
	 * @param message the message/print to print
	 */
	public static void println(Object message)
	{
		if (DEBUG)
		{
			getLog(Debugger.class).debug(message);

		}
	} // -----------------------------------------
	/**
	 * Print a error message. 
	 * The stack trace will be printed if
	 * the given message is an exception.
	 * @param caller the calling object
	 * @param message the message/object to print
	 */
	public static void printError(Object caller, Object message)
	{

		StringBuilder text = new StringBuilder();
		Class<?> c = Debugger.class;

		if (caller != null)
		{
			if (caller instanceof Class)
			{
				c = (Class<?>) caller;
				text.append(c.getName()).append(": ");
			}
			else
			if (caller instanceof String)
			{

				text.append(caller).append(": ");
			}
			else
			{
				c = caller.getClass();
				text.append(c.getName()).append(": ");

			}
		}

		getLog(c).debug(text.append(message));

	} // -----------------------------------------
	/**
	 * Print error message using the configured log.
	 * The stack trace will be printed if
	 * the given message is an exception.
	 * @param errorMessage the error/object message
	 */
	public static void printError(Object errorMessage)
	{

		if (errorMessage instanceof Throwable)
		{

			Throwable e = (Throwable) errorMessage;

			getLog(Debugger.class).error(stackTrace(e));

		}
		else
			getLog(Debugger.class).error(errorMessage);

	} // -----------------------------------------
	/**
	 * Print a fatal level message.
	 * The stack trace will be printed if
	 * the given message is an exception.
	 * @param message the fatal message
	 */
	public static void printFatal(Object message)
	{

		if (message instanceof Throwable)

		{

			Throwable e = (Throwable) message;

			e.printStackTrace();

		}

		Log log = getLog(Debugger.class);
		if(log != null)
			log.fatal(message);
		else
			System.err.println(message);
		
	} // -----------------------------------------
	/**
	 * Print a fatal message.
	 * The stack trace will be printed if
	 * the given message is an exception.
	 * @param caller the calling object
	 * @param message the fatal message
	 */
	public static void printFatal(Object caller, Object message)
	{

		if (message instanceof Throwable)

		{

			Throwable e = (Throwable) message;

			e.printStackTrace();

		}

		Log log = getLog(Debugger.class);
		if(log != null)
			log.fatal(message);
		else
			System.err.println(message);

	} // -----------------------------------------
	/**
	 * Print an INFO message
	 * @param caller the calling object
	 * @param message the INFO message
	 */
	public static void printInfo(Object caller, Object message)
	{

		StringBuilder text = new StringBuilder();

		Class<?> c = Debugger.class;

		if (caller != null)
		{

			if (caller instanceof Class)
			{

				c = (Class<?>) caller;
				text.append(c.getName()).append(": ");

			}

			else

			if (caller instanceof String)

			{

				text.append(caller ).append(": ");

			}

			else

			{

				c = caller.getClass();

				text.append(c.getName()).append(": ");

			}

		}

		getLog(c).info(text.append(message));

	} // -----------------------------------------
	/**
	 * Print a INFO level message
	 * @param message
	 */
	public static void printInfo(Object message)
	{

		if (message instanceof Throwable)
		{

			Throwable e = (Throwable) message;

			getLog(Debugger.class).info(stackTrace(e));

		}

		else

			getLog(Debugger.class).info(message);

	} // -----------------------------------------
	/**
	 * Print a warning level message. 
	 * The stack trace will be printed if
	 * the given message is an exception.
	 * @param caller the calling object
	 * @param message the message to print
	 */
	public static void printWarn(Object caller, Object message)
	{

		StringBuilder text = new StringBuilder();

		Class<?> c = Debugger.class;

		if (caller != null)
		{

			if (caller instanceof Class)

			{

				c = (Class<?>) caller;

				text.append(c.getName()).append(": ");

			}

			else

			if (caller instanceof String)
			{
				text.append(caller ).append(": ");
			}
			else
			{
				c = caller.getClass();
				text.append(c.getName()).append(": ");
			}

		}

		if(message instanceof Throwable)
			getLog(c).warn(text.append(stackTrace((Throwable)message)));
		else
			getLog(c).warn(text.append(message));

	} // -----------------------------------------
	/**
	 * Print A WARN message. The stack trace will be printed if
	 * the given message is an exception.
	 * @param message the message to stack
	 */
	public static void printWarn(Object message)
	{

		if (message instanceof Throwable)
		{

			Throwable e = (Throwable) message;

			getLog(Debugger.class).warn(stackTrace(e));

		}

		else

			getLog(Debugger.class).warn(message);

	} // -----------------------------------------

	private static boolean DEBUG = Config.getPropertyBoolean(Debugger.class,"DEBUG",true).booleanValue();
	private static Log defaultLogger;

} // end class
