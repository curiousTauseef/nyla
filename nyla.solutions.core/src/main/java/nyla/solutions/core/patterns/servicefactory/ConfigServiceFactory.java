package nyla.solutions.core.patterns.servicefactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import nyla.solutions.core.exception.SetupException;
import nyla.solutions.core.operations.ClassPath;
import nyla.solutions.core.patterns.SetUpable;
import nyla.solutions.core.util.Config;
import nyla.solutions.core.util.JavaBean;



/**
 * Service Factory implementation that expects the class name in the 
 * config.properties
 * 
 * Example:
 * 
 * factory.myServiceName=solution.someBean
 * 
 * 
 * @author Gregory Green
 *
 */
public class ConfigServiceFactory extends ServiceFactory implements SetUpable
{
	/**
	 * PROP_PREFIX = Config.getProperty(ConfigServiceFactory.class,"PROP_PREFIX","factory.")
	 */
	public static final String PROP_PREFIX = Config.getProperty(ConfigServiceFactory.class,"PROP_PREFIX","factory.");

	public ConfigServiceFactory()
	{
	}// --------------------------------------------------------
	public synchronized void setUp()
	{	
			if(initialized)
				return;

			String className = null;
			
			//Load
			Map<Object,Object> properties = Config.getProperties();
			
			String key = null;
			
			Object serviceObject = null;
			
			for(Map.Entry<Object,Object> entry : properties.entrySet())
			{
				key = String.valueOf(entry.getKey());
				
				if(key.startsWith(PROP_PREFIX))
				{
					try
					{
						className = (String)entry.getValue();
						serviceObject = Class.forName(className).newInstance();
						
						factoryMap.put(key, serviceObject);
					}
					catch (Throwable e)
					{
						throw new SetupException("CLASS:"+className+" ERROR:"+e.getMessage(),e);
					}
				}
			}
			
			initialized = true;
		}// --------------------------------------------------------
	/**
	 * @return create(aClass.getName())
	 */
	@Override
	public <T> T create(Class<?> aClass)
	{	
		
		return create(aClass.getName());
	}// ------------------------------------------------
	/**
	 * @return Object service = create(name);
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T create(Class<?> serviceClass, String name)
	{
		Object service = create(name);
			
		return (T)service;
	}// ------------------------------------------------
	@SuppressWarnings("unchecked")
	@Override
	public <T> T create(String aName)
	{
		if(aName == null || aName.length() == 0)
			return null;
		
		String factoryName = new StringBuilder(PROP_PREFIX).append(aName).toString();
		
		Object serviceObj = null;
		
		if(singletonCreation)
		    serviceObj = factoryMap.get(factoryName.toString());
		
		if(serviceObj == null)
		{
			//create
			String className = Config.getProperty(factoryName);
			serviceObj = ClassPath.newInstance(className);
			
			if(setNestedProperties)
				setProperties(factoryName, serviceObj);
			
			if(singletonCreation)
			   factoryMap.put(factoryName, serviceObj);		
		}
		
		return (T)serviceObj;
	}// ------------------------------------------------
	/**
	 * 
	 * @param factoryName the factory
	 * @param serviceObj the service object
	 */
	private void setProperties(String factoryName, Object serviceObj)
	{
		//look for properties
		Set<Object> propertyNames = JavaBean.keySet(serviceObj);
		
		String configPropertyValue = null;
		String propertyNameText = null;
		
		for (Object propertyName : propertyNames)
		{
			propertyNameText = propertyName.toString();
			
			configPropertyValue = Config.getProperty(new StringBuilder(factoryName).append(".").append(propertyNameText).toString(),"");
			
			if("".equals(configPropertyValue))
				continue;
			
			
			JavaBean.setProperty(serviceObj, propertyNameText, configPropertyValue);
		}
	}
	/**
	 * @return create(aName);
	 */
	public <T> T create(String aName, Object[] aParams)
	{
		
		return create(aName);
	}// ------------------------------------------------
	/**
	 * @return create(aName);
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T create(String aName, Object aParam)
	{
		return (T)create(aName);
	}// ------------------------------------------------
	/**
	 * 
	 * @return the 
	 */
	public static final ConfigServiceFactory getConfigServiceFactoryInstance()
	{
		synchronized (ConfigServiceFactory.class)
		{
			if(instance == null)
				instance = new ConfigServiceFactory();
		}
		
		return instance;
	}// ------------------------------------------------
	private static ConfigServiceFactory instance = null;
	private static boolean initialized = false;
	private static boolean singletonCreation =  Config.getPropertyBoolean(ConfigServiceFactory.class,"singletonCreation",false).booleanValue();
	private static boolean setNestedProperties =  Config.getPropertyBoolean(ConfigServiceFactory.class,"setNestedProperties",false).booleanValue();
	private final static HashMap<String,Object> factoryMap = new HashMap<String,Object>();
}
