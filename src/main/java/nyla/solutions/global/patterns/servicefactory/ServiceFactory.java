package nyla.solutions.global.patterns.servicefactory;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;

import nyla.solutions.global.exception.SetupException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.util.Config;

/**
 * 
 * <pre>
 * ServiceFactory provides a factory method implement
 * to obtain system service.
 * 
 * 
 * Override the default service factory with the following config.properties entry
 * 
 * ServiceFactory.config=someOtherFactory
 * #example
 * 
 * nyla.solutions.global.patterns.servicefactory.ServiceFactory=nyla.solutions.global.patterns.servicefactory.ConfigServiceFactory
 * 
 *  * You can also association a service factory with a particular class
 * 
 * #Example Property
 * solutions.global.patterns.servicefactory.ServiceFactory.solutions.global.patterns.command.remote.partitioning.RemoteCommasRegistry=solutions.global.patterns.servicefactory.ConfigServiceFactory
 * solutions.global.patterns.servicefactory.ServiceFactory.solutions.global.patterns.command.remote.partitioning.RmiOneRouteAdvice=solutions.global.patterns.servicefactory.ConfigServiceFactory
 * 
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public abstract class ServiceFactory
{
   /**
    * SERVICE_FACTORY_PROP_NM = ServiceFactory.class.getName()
    */
   public final static String SERVICE_FACTORY_PROP_NM = ServiceFactory.class.getName();
   
   /**
    * DEFAULT_SERVICE_FACTORY = SpringFactory.class.getName()
    */
   public static final String DEFAULT_SERVICE_FACTORY = "nyla.solutions.global.patterns.servicefactory.SpringFactory";

   
   /**
    * SERVICE_FACTORY_CONFIG_PROP = "ServiceFactory.config"
    */
   public static final String SERVICE_FACTORY_CONFIG_PROP = "ServiceFactory.config";
   
   /**
    * 
    * @return Config.getProperty("ServiceFactory.config")
    */
   public static String getConfigProperty()
   {
	  return Config.getProperty(SERVICE_FACTORY_CONFIG_PROP);
   }
   /**
    * Singleton factory method
    * @return a single instance of the ServiceFactory object 
    * for the JVM
    */
   public synchronized static ServiceFactory getInstance()
   {
      return getInstance(null,null);
      
   }// --------------------------------------------
   /**
    * Singleton factory method
    * @return a single instance of the ServiceFactory object 
    * for the JVM
    */
   public synchronized static ServiceFactory getInstance(String configurationPath)
   {
	   return getInstance(null,configurationPath);
   }// --------------------------------------------------------
   /**
    * Singleton factory method
    * @return a single instance of the ServiceFactory object 
    * for the JVM
    */
   public synchronized static ServiceFactory getInstance(Class<?> aClass)
   {
	   return getInstance(aClass,null);
   }// --------------------------------------------------------
      /**
       * Singleton factory method
       * @return a single instance of the ServiceFactory object 
       * for the JVM
       */
      public synchronized static ServiceFactory getInstance(Class<?> aClass, String configurationPath)
      {
         if(configurationPath == null)
         {
            configurationPath = "";   
         } 
         
         if (instances.get(configurationPath) == null)
         {
            instances.put(configurationPath, createServiceFactory(aClass,configurationPath));
         }         
         
         return (ServiceFactory)instances.get(configurationPath);
      }//--------------------------------------------
      private synchronized static ServiceFactory createServiceFactory(Class<?> aClass, String configurationFile)
      {
    	  String factoryClassName = null;
    	  if(aClass != null)
    	  {
    		  factoryClassName = Config.getProperty(new StringBuilder(ServiceFactory.class.getName()).append(".").append(aClass.getName()).toString());    
    	  }
    	  
    	  //check to use default
    	  if(factoryClassName == null || factoryClassName.length() == 0)
    		  factoryClassName = Config.getProperty(SERVICE_FACTORY_PROP_NM,DEFAULT_SERVICE_FACTORY);
        
         
         //Debugger.println(ServiceFactory.class,"factoryClassName="+factoryClassName);
         
         try
         {
                        
              Class<?> factoryClass =Class.forName(factoryClassName);
              
              if(configurationFile  == null || configurationFile.length() == 0)
              {
                 return (ServiceFactory)factoryClass.newInstance();
              }
              //Called passing configuration file to constructor
              Object[] inputs = { configurationFile };
              Class<?> [] parameterTypes = { String.class};
               
              return (ServiceFactory)factoryClass.getConstructor(parameterTypes).newInstance(inputs);                
         }
         catch (SetupException e)
         {
        	 throw e;
         }
         catch (Exception e)
         {
        	 
            throw new SetupException(e.getMessage(),e);
         }
      }// --------------------------------------------

      /**
       * Create object based on the object's full type name
       * @param aClass the object/service name
       * @return an instance of the given class
       */
      public abstract <T> T create(Class<?> aClass);
      

      /**
       * Create object and check if object is a sub class of serviceClass
       * @param serviceClass the service interface
       * @param componentName the object name
       * @return
       */
      public abstract <T> T create(Class<?> serviceClass, String name);
      
   /**
    * 
    * @param aServiceName the object/service name
    * @return an instance of the given class
    */
   public abstract <T> T create(String aName);
   
   
   /**
    * Example USage:
    * <code>
    *  StructureFinder[] finders  = new StructureFinder[sources.length];	
	   ServiceFactory.getInstance().createForNames(sources,finders);
	  </code>	
    * @param names the object/service names
    * @param objectOutput array to place results
    * @return an instance of the given class
    */
   public <T> void createForNames(String[] names, T[] objectOutput)
   {
	   
	   if(objectOutput == null || objectOutput.length == 0)
				throw new IllegalArgumentException("Non empty objectOutput");
	   
	   if(names ==null || names.length == 0)
	   {
		   return;
	   }
	  
	   String name = null;
	   Object obj = null;
	   try
	   {
		   for(int i=0; i < names.length;i++)
		   {
			   name = names[i];
			   obj = this.create(name);
			   objectOutput[i] =  this.create(name);
		   }
	   }
	   catch (ArrayStoreException e)
	   {   
		   if(obj == null)
			   throw e;
		   
		   throw new SystemException("Cannot assign bean \""+name+"\" class:"+obj.getClass().getName()
				   +" to array of objectOutput arrray class:"+Arrays.asList(objectOutput),e);
	   }
   }// --------------------------------------------------------
   

   /**
    * 
    * @param aServiceName the object/service name
    * @return an instance of the given class
    */
   public abstract <T> T create(String aName,Object [] aParams );
   
   /**
    * 
    * @param aServiceName the object/service name
    * @return an instance of the given class
    */
   public abstract <T> T create(String aName,Object aParam );
   
   private static Map<String,ServiceFactory>  instances = new Hashtable<String,ServiceFactory>();

}
