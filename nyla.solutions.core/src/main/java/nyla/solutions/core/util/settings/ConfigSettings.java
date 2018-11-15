package nyla.solutions.core.util.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Observer;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import nyla.solutions.core.exception.ConfigException;
import nyla.solutions.core.exception.SystemException;
import nyla.solutions.core.io.FileMonitor;
import nyla.solutions.core.patterns.observer.SubjectObserver;
import nyla.solutions.core.patterns.observer.SubjectRegistry;
import nyla.solutions.core.util.Config;
import nyla.solutions.core.util.Debugger;
import nyla.solutions.core.util.Text;

public class ConfigSettings extends AbstractSettings
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see nyla.solutions.core.util.Settings#getProperties()
	 */

	@Override
	public synchronized Map<Object, Object> getProperties()
	{

		if (properties == null || this.isAlwaysReload())
		{
			loadProperties();
		}

		// return copy

		return new HashMap<Object, Object>(properties);
	}// ------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nyla.solutions.core.util.Settings#setProperties(java.util.Properties)
	 */
	@Override
	public synchronized void setProperties(Map<Object, Object> properties)
	{
		this.properties = new Properties();
		this.properties.putAll(properties);
	}// --------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see nyla.solutions.core.util.Settings#reLoad()
	 */
	@Override
	public void reLoad()
	{
		loadProperties();
	}

	/**
	 * Load the configuration properties from the properties file.
	 * <p/>
	 * <p/>
	 * <p/>
	 * Caller must test to ensure that properties is Non-null.
	 * 
	 * @throws IllegalArgumentException
	 *             Translates an IOException from reading
	 *             <p/>
	 *             the properties file into a run time exception.
	 */

	private synchronized void loadProperties()
	{
		// If multiple threads are waiting to invoke this method only allow
		// the first one to do so. The rest should just return since the first
		// thread through took care of loading the properties.
		try
		{
			this.file = null;
			
			boolean alwaysReload = this.isAlwaysReload();

			boolean useFormatting = this.isUseFormatting();

			String filePath = getSystemPropertyFile();
			if (filePath != null && filePath.length() > 0)
			{
				// System.out.println("CONFIG: LOADING CONFIG properties from "+
				// file);
				this.file = Paths.get(filePath).toFile();
				FileInputStream fis = new FileInputStream(file);

				try
				{
					properties = new Properties();
					// Load the properties object from the properties file
					properties.load(fis);
					// System.out.println("CONFIG: FINISHED LOADING CONFIG
					// properties from "+
					// file);

					configSourceLocation = file.getAbsolutePath();
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

				try
				{
					String bundleName = getBundleName();

					// try to get properties from resource bundle
					ResourceBundle rb = ResourceBundle.getBundle(bundleName);

					URL url = Config.class.getResource(bundleName + ".properties");

					if (url != null)
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
				} // end els load from resource bundle
				catch (MissingResourceException e)
				{
				}
			}//end else
			

			//Determine global settings
			String reloadBool = properties.getProperty(Config.class.getName() + ".alwaysReload");

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

			this.setAlwaysReload(alwaysReload);

			// Merge SystemProperteis
			mergeSystemProperties = Boolean
					.valueOf(properties.getProperty(Config.class.getName() + ".mergeSystemProperties", "true"))
					.booleanValue();

			if (mergeSystemProperties)
			{
				// add system properties
				properties.putAll((Properties)System.getProperties().clone());
			}

			// Envinronment SystemProperteis
			mergeEnvProperties = Boolean
					.valueOf(properties.getProperty(Config.class.getName() + ".mergeEnvProperties", "true"))
					.booleanValue();

			if (mergeEnvProperties)
			{
				// add env properties
				properties.putAll(System.getenv());
			}

			// process formatting
			String propName = Config.class.getName() + ".useFormatting";
			String useFormattingText = properties.getProperty(propName);

			// System.out.println("CONFIG:
			// "+propName+"="+useFormattingText);

			if (useFormattingText == null || useFormattingText.length() == 0)
			{

				// throw new ConfigException("System property
				// "+Constants.ALWAY_RELOAD_PROP+" is not set.");

				useFormatting = false;

			}
			else
			{
				useFormatting = Boolean.valueOf(useFormattingText).booleanValue();
			}

			if (useFormatting)
			{
				// System.out.println("CONFIG: FORMATTING MAP CONFIG
				// properties ");
				// format map (note this can be an expensive operation)

				Text.formatMap(properties);
				// System.out.println("CONFIG: FORMATTED MAP CONFIG
				// properties ");
			}

			propName = Config.class.getName() + ".setSystemProperties";
			setSystemProperties = Boolean.valueOf(properties.getProperty(propName, "false")).booleanValue();

			// Set system properties with values from configuration
			if (setSystemProperties)
			{
				Set<Object> keySet = properties.keySet();
				String key;
				String sysProp;
				for (Iterator<Object> i = keySet.iterator(); i.hasNext();)
				{
					key = (String) i.next();
					sysProp = System.getProperty(key);

					if (sysProp != null && sysProp.length() > 0)
						continue; // do not override values

					// set system property
					System.setProperty(key, properties.getProperty(key));
				}
			}
		}
		catch (ConfigException e)
		{
			throw e;
		}
		catch (FileNotFoundException e)
		{
			throw new ConfigException(e.getMessage(), e);
		}
		catch (Exception e)
		{
			throw new ConfigException(e.getMessage(), e);
		}
		
		try
		{
			this.registry.notify(this.getClass().getName(), this);
		}
		catch(Exception e)
		{
			Debugger.printWarn(e);
		}
	}// ------------------------------------------------------------

	/**
	 * @return the system property file
	 */

	private String getSystemPropertyFile()
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
	 * 
	 * @return the configuration location
	 */
	public String getLocation()
	{
		return configSourceLocation;
	}// --------------------------------------------------------
	@Override
	public synchronized void registerObserver(SubjectObserver<Settings> settingsObserver)
	{
		if (settingsObserver == null)
			throw new IllegalArgumentException("settingsObserver is required");
		
		if(file != null)
		{
			if(fileMonitor == null)
				fileMonitor = new FileMonitor();				
			
			fileMonitor.monitor(file.getParent(), file.getName(), false);
			Observer fileEventBridge = (observable,o) -> {this.reLoad();};
			
			fileMonitor.addObserver(fileEventBridge);
		}
		
		registry.register(getClass().getName(), settingsObserver);
	}

	private SubjectRegistry registry = new SubjectRegistry();
	private boolean mergeSystemProperties = true;
	private boolean mergeEnvProperties = true;
	private boolean setSystemProperties = false;

	private File file = null;
	private FileMonitor fileMonitor = null;
	private String configSourceLocation = null;
	private Properties properties = null; // configuration properties
	
}
