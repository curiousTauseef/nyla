package nyla.solutions.global.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import nyla.solutions.global.exception.ConfigException;
import nyla.solutions.global.exception.FormatException;
import nyla.solutions.global.exception.MissingConfigPropertiesException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.patterns.decorator.style.Styles;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Cryption;

/**
 * <pre>
 * This class provides a central mechanism for applications to access
 * key/value property settings and encrypted passwords.
 * 
 * There are several ways to set to specify the 
 * configuration property file location.
 * <ol><li>Add file config.properties to CLASSPATH.
 *This file will be loaded as a Java resource bundle.</li>
 *   <li>Add the JVM argument -Dconfig.properties where the value is equal to 
 *the location of configuration file.
 *
 *Example:
 *-Dconfig.properties=/dev/configurations/files/system/config.properties</li></ol>
 * There are methods to get the String value property  such as <i>Config.getProperty(key)</i> method
 * or get an expected property value of a type such as Integer, Boolean, etc.
 * 
 * JVM argument system properties can also by accessed by adding the
 * following to the configuration file; 
 * <span style="color:blue">
 * nyla.solutions.global.util.Config.mergeSystemProperties=true
 * </span>
 * 
 * Values in the System properties can be set with values for the configuration by using the following
 * solutions.global.util.Config.setSystemProperties=true
 * 
 * It also supports formatting several property values into a single property
 * by the added the following property;
 * 
 * <span style="color:blue">
 * nyla.solutions.global.util.Config.useFormatting=true
 * 
 * <i>Example</i>
 * machineName=localhost
 * host=${machineName}.mycompany.com
 * </span>
 * 
 * By default the configuration is read only once when the 
 * application is initialized. Add the following to the 
 * configuration property file to always reload the property whenever 
 * a getProperty... method is called.
 * 
 * <span style="color:blue">nyla.solutions.global.util.Config.alwaysReloadProperties=true</span>
 * </pre>
 * 
 * @author Gregory Green
 */

public class Config
{

	public static final String RESOURCE_BUNDLE_NAME = "config";

	/**
	 * SMP_PROPERTY_FILE
	 */
	public static final String SYS_PROPERTY = "config.properties";


	/**
	 * Property may reference properties in example ${prop.name}+somethingElse
	 * @param property the property
	 * @return the formatted value
	 * @throws ConfigException when format exception occur
	 */
	public static String interpret(String property)
	{
		try
		{
			property = Cryption.interpret(property);
			
			if(property != null && property.indexOf(Styles.DEFAULT_PREFIX) > -1)
			{
				property = Text.format(property, Config.getProperties());
			}
		}
		catch (FormatException e)
		{
			throw new ConfigException("Format exception for \""+property+"\"",e);
		}
		
		return Cryption.interpret(property);
	}// --------------------------------------------------------
	
	/**
	 * @return the system property file
	 */

	private static String getSystemPropertyFile()
	{

		String file = System.getProperty(SYS_PROPERTY);
		if (file == null || file.length() == 0)
		{

			try
			{
				// file = (String) new InitialContext().lookup(SYS_PROPERTY);
			}
			catch (Throwable e)
			{
				throw new SystemException(e);
			}
		}

		return file;

	}// -----------------------------------------------------------

	/**
	 * @return resource bundle name
	 */

	static String getBundleName()
	{
		return RESOURCE_BUNDLE_NAME;

	}// -------------------------------------------------------------

	/**
	 * Load the configuration properties from the properties file.
	 * <p/>
	 * <p/>
	 * <p/>
	 * Caller must test to ensure that properties is Non-null.
	 * 
	 * @throws IllegalArgumentException Translates an IOException from reading
	 *             <p/>
	 *             the properties file into a run time exception.
	 */

	private static synchronized void loadProperties()
	{
		// If multiple threads are waiting to invoke this method only allow
		// the first one to do so. The rest should just return since the first
		// thread through took care of loading the properties.
		try
		{
			String file = getSystemPropertyFile();
			if (file != null && file.length() > 0)
			{
				// System.out.println("CONFIG: LOADING CONFIG properties  from "+
				// file);
				FileInputStream fis = new FileInputStream(file);

				try
				{
					properties = new Properties();
					// Load the properties object from the properties file
					properties.load(fis);
					// System.out.println("CONFIG: FINISHED LOADING CONFIG properties  from "+
					// file);
					
					
					configSourceLocation = file;
				}
				catch (Exception e)
				{
					e.printStackTrace();
					throw new ConfigException(e.toString());
				}
				finally
				{
					if (fis != null)
						fis.close(); // Always close the file, even on exception

				}

			}
			else
			{
				properties = new Properties();

				String bundleName = getBundleName();

				// try to get properties from resource bundle
				ResourceBundle rb = ResourceBundle.getBundle(bundleName);

				
				URL url = Config.class.getResource(
					bundleName + ".properties");
				
				if(url != null)
					configSourceLocation = url.toString();
				else
					configSourceLocation = bundleName + ".properties";
				
				Enumeration<?> keys = rb.getKeys();

				Object k = null;			

				while (keys.hasMoreElements())
				{

					k = keys.nextElement();

					properties.put(k, rb.getString(k + ""));

				}
			}// end els load from resource bundle

			String reloadBool = properties.getProperty(Config.class.getName()
					+ ".alwaysReload");

			if (reloadBool == null || reloadBool.length() == 0)
			{

				// throw new ConfigException("System property
				// "+Constants.ALWAY_RELOAD_PROP+" is not set.");

				alwaysReload = false;

			}
			else
			{
				alwaysReload = Boolean.valueOf(reloadBool).booleanValue();
			}


			//Merge SystemProperteis
			mergeSystemProperties = Boolean.valueOf(properties.getProperty(Config.class.getName()
					+ ".mergeSystemProperties", "false")).booleanValue();
			
			if(mergeSystemProperties)
			{
				//add system properties
				properties.putAll(System.getProperties());
			}
			
			// process formatting
			String propName = Config.class.getName() + ".useFormatting";
			String useFormattingText = properties.getProperty(propName);
	
			// System.out.println("CONFIG:  "+propName+"="+useFormattingText);

			if (useFormattingText == null || useFormattingText.length() == 0)
			{

				// throw new ConfigException("System property
				// "+Constants.ALWAY_RELOAD_PROP+" is not set.");

				useFormatting = false;

			}
			else
			{
				useFormatting = Boolean.valueOf(useFormattingText)
						.booleanValue();
			}

			if (useFormatting)
			{
				// System.out.println("CONFIG: FORMATTING MAP CONFIG properties ");
				// format map (note this can be an expensive operation)
				
				Text.formatMap(properties);
				// System.out.println("CONFIG: FORMATTED MAP CONFIG properties ");
			}
			
			propName = Config.class.getName() + ".setSystemProperties";
			setSystemProperties =  Boolean.valueOf(properties.getProperty(propName,"false")).booleanValue();
			
			//Set system properties with values from configuration
			if(setSystemProperties)
			{
				Set<Object> keySet = properties.keySet();
				String key;
				String sysProp;
				for (Iterator<Object> i = keySet.iterator(); i.hasNext();)
				{
					key = (String)i.next();
					sysProp= System.getProperty(key);
					
					if(sysProp != null && sysProp.length() > 0)
						continue; //do not override values
					
					//set system property
					System.setProperty(key, properties.getProperty(key));
				}
			}
		}
		catch (MissingResourceException e)
		{

			/*
			 * System.out.println("problem loading config properties from "
			 * 
			 * + propertiesFile
			 * 
			 * + "\n"
			 * 
			 * + e.toString());
			 * 
			 * System.out.println(Config.class.getName() +
			 * "... continuing ...");
			 */
			// throw new MissingConfigPropertiesException();

		}
		catch(ConfigException e)
		{
			throw e;
		}
		catch(FileNotFoundException e)
		{
			throw new ConfigException(e.getMessage());
		}
		catch (Exception e)
		{
			throw new ConfigException(e.getMessage(),e);
		}
	}// ------------------------------------------------------------
	/**
	 * 
	 * @param alwaysReload boolean to determine you should always relaod
	 */
	public static void setAlwaysReload(boolean alwaysReload)
	{
		Config.alwaysReload = alwaysReload;
	}// --------------------------------------------------------
	
	public static void reLoad()
	{
		loadProperties();
	}
	
	/**
	 * 
	 * @return the configuration location
	 */
	public static String getLocation()
	{
		return configSourceLocation;
	}// --------------------------------------------------------

	/**
	 * 
	 * @return System.getProperty("java.io.tmpdir")
	 */
	public static String getTempDir()
	{
		return System.getProperty("java.io.tmpdir");
	}// ----------------------------------------------

	/**
	 * Retrieves a configuration property as a String object.
	 * <p/>
	 * Loads the file if not already initialized.
	 * 
	 * @param Key Name of the property to be returned.
	 * 
	 * @return Value of the property as a string or null if no property found.
	 */

	public static String getProperty(String key)
	{

		String retval = null;

		if (properties == null || alwaysReload)
		{
			loadProperties();
		}
		
		retval = properties.getProperty(key);

		if (retval == null || retval.length() == 0)
		{
			if (configSourceLocation == null)
				throw new MissingConfigPropertiesException();

			throw new ConfigException("Configuration property \"" + key
					+ "\" not found in keys " + getProperties().keySet()
					+ " file:" + configSourceLocation);
		}

		return Cryption.interpret(retval);

	}// ------------------------------------------------------------

	/**
	 * 
	 * @param key the key of the property
	 * @return Text.split(getProperty(key))
	 */
	public static String[] getPropertyStrings(String key)
	{
		return Text.split(getProperty(key));
	}// ------------------------------------------------------------

	/**
	 * Multiple properties separated by white spaces
	 * 
	 * @param aClass
	 * @param key
	 * @return
	 */
	public static String[] getPropertyStrings(Class<?> aClass, String key)
	{
		return Text.split(getProperty(aClass, key));
	}// -----------------------------------------------
	/**
	 * Multiple properties separated by white spaces
	 * 
	 * @param aClass
	 * @param key Name of the property to be returned.
	 * @param aDefault the default value
	 * @return Text.split(getProperty(aClass,key,aDefault))
	 */
	public static String[] getPropertyStrings(Class<?> aClass, String key,
			String aDefault)
	{
		String[] defaultArray = {aDefault};
		
		return getPropertyStrings(aClass, key, defaultArray);
	}
	/**
	 * Get the property 
	 * @param aClass the class associate with property
	 * @param key the property key
	 * @param resourceBundle the resource bundle default used if property not found
	 * @return the property key
	 */
	public static String getProperty(Class<?> aClass,String key,ResourceBundle resourceBundle)
	{
		String results = getProperty(aClass,key, "");
		
		if(results == null || results.length() == 0)
			results = resourceBundle.getString(key);
		
		return results;
	}// --------------------------------------------------------
	
	/**
	 * Multiple properties separated by white spaces
	 * 
	 * @param aClass
	 * @param key Name of the property to be returned.
	 * @param aDefault the default value
	 * @return Text.split(getProperty(aClass,key,aDefault))
	 */
	public static String[] getPropertyStrings(Class<?> aClass, String key,
			String[] aDefault)
	{
		String property = getProperty(aClass, key,"");
		
		if("".equals(property))
			return aDefault;
		
		return Text.split(property);
	}// -----------------------------------------------

	/**
	 * Retrieves a configuration property as a String object.
	 * <p/>
	 * Loads the file if not already initialized.
	 * 
	 * @param aClass the calling class
	 * @param key property key
	 * 
	 * @return Value of the property as a string or null if no property found.
	 */

	public static String getProperty(Class<?> aClass, String key)
	{
		return getProperty(new StringBuilder(aClass.getName()).append(".").append(key).toString());
	}// ---------------------------------------------

	/**
	 * Retrieves a configuration property as a String object.
	 * <p/>
	 * Loads the file if not already initialized.
	 * 
	 * @param Key Name of the property to be returned.
	 * 
	 * @return Value of the property as a string or null if no property found.
	 */

	public static String getProperty(Class<?> aClass, String key, String aDefault)
	{
		return getProperty(new StringBuilder(aClass.getName()).append(".").append(key).toString(), aDefault);

	}// ---------------------------------------------

	/**
	 * Retrieves a configuration property as a String object.
	 * <p/>
	 * Loads the file if not already initialized.
	 * 
	 * @param Key Name of the property to be returned.
	 * 
	 * @return Value of the property as a string or null if no property found.
	 */

	public static String getProperty(String key, String aDefault)
	{
		String retval = null;
		if (properties == null || alwaysReload)
		{
			loadProperties();
		}
		
		retval = properties.getProperty(key);
		if (retval == null || retval.length() == 0)
		{
			retval = aDefault;
		}

		return Cryption.interpret(retval);

	}// ------------------------------------------------------------

	/**
	 * Get a configuration property as an Integer object.
	 * 
	 * @param aClass calling class
	 * @param Key Name of the numeric property to be returned.
	 * 
	 * @return Value of the property as an Integer or null if no property found.
	 */

	public static Integer getPropertyInteger(Class<?> aClass, String key,
			int defaultValue)
	{
		return getPropertyInteger(new StringBuilder(aClass.getName()).append(".")
				.append(key).toString(), defaultValue);
	}// ------------------------------------------------------------

	/**
	 * Get a configuration property as an c object.
	 * 
	 * @param aClass the class the property is related to
	 * @param key the configuration name
	 * @param defaultValue the default value to return if the property does not
	 *            exist
	 * @return the configuration character
	 */
	public static Character getPropertyCharacter(Class<?> aClass, String key,
			char defaultValue)
	{
		String results = getProperty(aClass, key, "");

		if (results.length() == 0)
			return Character.valueOf(defaultValue);
		else
			return Character.valueOf(results.charAt(0));// return first character

	}// ---------------------------------------------

	/**
	 * Get a configuration property as an Integer object.
	 * 
	 * @param Key Name of the numeric property to be returned.
	 * 
	 * @return Value of the property as an Integer or null if no property found.
	 */

	public static Integer getPropertyInteger(String key)
	{
		Integer iVal = null;
		String sVal = getProperty(key);

		if ((sVal != null) && (sVal.length() > 0))
		{

			iVal = Integer.valueOf(sVal);

		}
		return iVal;

	}// ------------------------------------------------------------

	public static Integer getPropertyInteger(String key, int aDefault)
	{

		return getPropertyInteger(key, Integer.valueOf(aDefault));

	}// -------------------------------------------------------------

	public static Integer getPropertyInteger(Class<?> cls, String key)
	{
		return getPropertyInteger(new StringBuilder(cls.getName()).append(".").append(key).toString());

	}// ---------------------------------------------

	public static Integer getPropertyInteger(Class<?> cls, String key,
			Integer aDefault)
	{
		return getPropertyInteger(new StringBuilder(cls.getName()).append(".").append(key).toString(), aDefault);

	}// ---------------------------------------------

	public static Integer getPropertyInteger(String key, Integer aDefault)
	{
		Integer iVal = null;
		if (properties == null || alwaysReload)
		{
			loadProperties();
		}
		
		String sVal = properties.getProperty(key);
		if ((sVal != null) && (sVal.length() > 0))
		{
			iVal = Integer.valueOf(sVal);
		}
		else
		{
			iVal = aDefault;
		}
		return iVal;
	}// ------------------------------------------------------------

	/**
	 * Get a configuration property as a Boolean object.
	 * 
	 * @param Key Name of the numeric property to be returned.
	 * 
	 * @return Value of the property as an Boolean or null if no property found.
	 *         <p/>
	 *         <p/>
	 *         <p/>
	 *         Note that the value of the returned Boolean will be false if the
	 *         <p/>
	 *         property sought after exists but is not equal to "true" (ignoring
	 *         case).
	 */

	public static Boolean getPropertyBoolean(String key)
	{
		Boolean bVal = null;
		String sVal = getProperty(key);
		if ((sVal != null) && (sVal.length() > 0))
		{
			bVal = Boolean.valueOf(sVal);
		}
		return bVal;
	}// ------------------------------------------------------------

	public static Boolean getPropertyBoolean(String key, Boolean aBool)
	{
		Boolean bVal = null;
		if (properties == null || alwaysReload)
		{
			loadProperties();
		}
		
		String sVal = properties.getProperty(key);
		if ((sVal != null) && (sVal.length() > 0))
		{
			bVal = Boolean.valueOf(sVal);
		}
		else
		{
			bVal = aBool;
		}
		return bVal;

	}// ------------------------------------------------------------

	/**
	 * @param key the configuration key
	 * @param aBool default value
	 * 
	 * @return aBool if the configuration value for the key is blank
	 */

	public static Boolean getPropertyBoolean(Class<?> aClass, String key,
			boolean aBool)
	{
		return getPropertyBoolean(new StringBuilder(aClass.getName()).append(".").append(key).toString(), aBool);
	}// ---------------------------------------------

	/**
	 * @param key the configuration key
	 * @param aBool default value
	 * 
	 * @return aBool if the configuration value for the key is blank
	 */

	public static Boolean getPropertyBoolean(String key, boolean aBool)
	{
		Boolean bVal = null;
		
		if (properties == null || alwaysReload)
		{
			loadProperties();
		}
		
		String sVal = properties.getProperty(key);
		if ((sVal != null) && (sVal.length() > 0))
		{
			bVal =  Boolean.valueOf(sVal);
		}
		else
		{
			bVal = Boolean.valueOf(aBool);
		}
		return bVal;

	}// ------------------------------------------------------------

	public static Long getPropertyLong(String key)
	{
		Long longValue = null;
		String sVal = getProperty(key);
		if ((sVal != null) && (sVal.length() > 0))
		{
			longValue = Long.valueOf(sVal);
		}
		return longValue;
	}// ------------------------------------------------------------
	public static Long getPropertyLong(Class<?> aClass, String key,  long aDefault)
	{
		return getPropertyLong(new StringBuilder(aClass.getName()).append(".").append(key).toString(), Long.valueOf(aDefault));
	}// ------------------------------------------------------------
	public static Long getPropertyLong(Class<?> aClass, String key)
	{
		return getPropertyLong(new StringBuilder(aClass.getName()).append(".").append(key).toString());
	}// ------------------------------------------------------------
	public static Long getPropertyLong(String key, long aDefault)
	{
		return getPropertyLong(key, Long.valueOf(aDefault));
	}// -------------------------------------------------------------

	public static Long getPropertyLong(String key, Long aDefault)
	{
		Long longValue = null;
		
		if (properties == null || alwaysReload)
		{
			loadProperties();
		}
		
		String sVal = properties.getProperty(key);
		if ((sVal != null) && (sVal.length() > 0))
		{
			longValue = Long.valueOf(sVal);
		}
		else
		{
			longValue = aDefault;
		}
		return longValue;

	}// ------------------------------------------------------------

	/**
	 * Get a configuration property as a Password object.
	 * 
	 * @param Key Name of the numeric property to be returned.
	 * 
	 * @return Value of the property as an Password or null if no property
	 *         found.
	 *         <p/>
	 *         <p/>
	 *         <p/>
	 *         Note that the value of the returned Password will be false if the
	 *         <p/>
	 *         property sought after exists but is not equal to "true" (ignoring
	 *         case).
	 */

	public static char[] getPropertyPassword(String key)
	{
		char[] bVal = null;
		String sVal = getSecureProperty(key);

		if (sVal == null || sVal.length() == 0)
			throw new ConfigException("Configuration property \"" + key
					+ "\"+ not found in keys [" + properties.keySet()
					+ "]");

		bVal = sVal.toCharArray();

		return bVal;
	}// ------------------------------------------------------------


	/**
	 * Get the an encrypted password
	 * 
	 * @param key the key
	 * @param defaultPassword
	 * @return the default password if no password exists in the configuration
	 */
	public static char[] getPropertyPassword(String key, char[] defaultPassword)
	{
		char[] bVal = null;
		String sVal = getSecureProperty(key);
		if ((sVal != null) && (sVal.length() > 0))
		{
			bVal = sVal.toCharArray();
		}
		else
		{
			bVal = defaultPassword;
		}
		return bVal;

	}// ------------------------------------------------------------

	/**
	 * Get the an encrypted password
	 * 
	 * @param key the key
	 * @param defaultPassword
	 * @return the default password if no password exists in the configuration
	 */
	public static char[] getPropertyPassword(String key, String defaultPassword)
	{
		char[] bVal = null;
		String sVal = getSecureProperty(key);
		if ((sVal != null) && (sVal.length() > 0))
		{
			bVal = sVal.toCharArray();
		}
		else
		{
			if (defaultPassword == null)
				return null;

			bVal = defaultPassword.toCharArray();
		}
		return bVal;

	}// ------------------------------------------------------------

	/**
	 * @param key the configuration key
	 * @param defaultPassword default value
	 * 
	 * @return defaultPassword if the configuration value for the key is blank
	 */

	public static char[] getPropertyPassword(Class<?> aClass, String key,
			char[] defaultPassword)
	{
		return getPropertyPassword(new StringBuilder(aClass.getName()).append(".").append(key).toString(),
				defaultPassword);
	}// ---------------------------------------------

	/**
	 * Retrieves a configuration property as a encrypted value.
	 * <p/>
	 * Loads the file if not already initialized.
	 * 
	 * @param Key Name of the property to be returned.
	 * 
	 * @return Value of the property as a string or null if no property found.
	 */

	private static String getSecureProperty(String key)
	{

		String retval = null;

		retval = getProperties().getProperty(key);

		if (retval == null || retval.length() == 0)
		{
			return null;
		}

		if (!retval.startsWith(Cryption.CRYPTION_PREFIX))
		{
			throw new ConfigException("Configuration key \"" + key
					+ "\" must be encypted");
		}

		return Cryption.interpret(retval);
	}// ------------------------------------------------------------

	/**
	 * @return a copy of the configured properties
	 */

	public static Properties getProperties()
	{

		if (properties == null || alwaysReload)
		{
			loadProperties();
		}

		// return copy
		Properties prop = new Properties();
		if (properties != null && !properties.isEmpty())
			prop.putAll(properties);
		
		

		return prop;
	}// ------------------------------------------------------------

	public static void setProperties(Properties properties)
	{
		Config.properties = new Properties(properties);
	}// --------------------------------------------

	/**
	 * 
	 * @return System.getProperty("user.dir")
	 */
	public static String getUserDir()
	{
		return System.getProperty("user.dir");
	}// --------------------------------------------
	/**
	 * 
	 * @return System.getProperty("file.separator")
	 */
	public static String getFileSeparator()
	{
		return System.getProperty("file.separator");
	}// --------------------------------------------
	public static boolean isUseFormatting()
	{
		return useFormatting;
	}// --------------------------------------------------------

	private static Properties properties = null; // configuration properties

	
	// private static long lastCheckTime = 0;
	private static String configSourceLocation = null;
	private static boolean alwaysReload = false;
	private static boolean mergeSystemProperties = false;
	private static boolean setSystemProperties = false;
	private static boolean useFormatting = false;

	public static final int CONFIG_FILE_CHECK_INTERVAL_IN_MS = 30000;

}
