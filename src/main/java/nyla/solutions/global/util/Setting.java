package nyla.solutions.global.util;

import java.util.Hashtable;
import java.util.Map;

import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.operations.Log;
import nyla.solutions.global.util.Cryption;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Setting;

/**
 * Setting <p/><p/><p/>This class provides read access to key/value pairs
 * loaded from the <p/>properties file properties. <p/><p/><p/><p/><p/>
 * <UL>
 * Defaults <p/>
 * <LI><CODE>..\Setting.properties</CODE> <p/>
 * </UL>
 * <p/><p/><p/>
 * 
 * <PRE><CODE><p/>Use: <p/>... <p/><p/><p/>String stringPropertyValue = null;
 * <p/>Integer integerPropertyValue = null; <p/>Boolean booleanPropertyValue =
 * null; <p/>... <p/>try { <p/>stringPropertyValue =
 * Setting.getProperty("STRING_PROPERTY_NAME"); <p/>integerPropertyValue =
 * Setting.getPropertyInteger("PROPERTY_NAME"); <p/>booleanPropertyValue =
 * Setting.getPropertyBoolean("BOOLEAN_PROPERTY_NAME"); <p/>} catch
 * (RuntimeException e) { <p/>... <p/></CODE></PRE>
 * 
 * <p/><p/><p/>The Settings property file location is set by the a JVM
 * property or JNDI entries with an name of <p/>smartApp_Setting_properties <p/>
 * <p/><p/>Example JVM
 * -Dgcsm_Setting_properties=/dev/Settingss/files/system/Setting.properties
 * <p/><p/><p/>The Settings properties will be loaded as a resource
 * bundle if the location is not <p/>specified as a JVM or JNDI property.
 * 
 * @author Gregory Green
 */

public class Setting
{

   public static final String CRYPTION_PREFIX = "{cryption}";

   public static final String RESOURCE_BUNDLE_NAME = "Setting";

  
   /**
    * Retrieves a Settings property as a String object. <p/>Loads the file
    * if not already initialized.
    * 
    * @param Key
    *           Name of the property to be returned.
    * 
    * @return Value of the property as a string or null if no property found.
    */

   public  String getText(String key)
   {

      String retval = null;

      retval = (String)getSettingMap().get(key);

      if (retval == null || retval.length() == 0)
      {
         throw new SystemException("Settings property \"" + key
         + "\" not found in keys "+getSettingMap().keySet());
      }

      if (retval.startsWith(CRYPTION_PREFIX))
      {

         try
         {

            retval = retval.substring(CRYPTION_PREFIX.length());

            retval = new Cryption().decryptText(retval);

            //Debugger.printInfo("YO="+retval);

         }
         catch (Exception e)
         {

            throw new SystemException(e);

         }

      }

      return retval;

   }//------------------------------------------------------------
   /**
    * Retrieves a Settings property as a String object. <p/>Loads the file
    * if not already initialized.
    * @param aClass  the calling class
    * @param key property key
    * 
    * @return Value of the property as a string or null if no property found.
    */

   public String getText(Class<?> aClass, String key)   
   {
	   return getText(aClass.getName()+"."+key);
   }//---------------------------------------------
   /**
    * Retrieves a Settings property as a String object. <p/>Loads the file
    * if not already initialized.
    * 
    * @param Key
    *           Name of the property to be returned.
    * 
    * @return Value of the property as a string or null if no property found.
    */

   public String getText(Class<?> aClass, String key, String aDefault)
   {
	   return getText(aClass.getName()+"."+key, aDefault);
	   
   }//---------------------------------------------
   /**
    * Retrieves a Settings property as a String object. <p/>Loads the file
    * if not already initialized.
    * 
    * @param Key
    *           Name of the property to be returned.
    * 
    * @return Value of the property as a string or null if no property found.
    */

   public String getText(String key, String aDefault)
   {
      String retval = null;
      retval = getText(key);
      if (retval == null || retval.length() == 0)
      {
         retval = aDefault;
      }
      if (retval.startsWith(CRYPTION_PREFIX))
      {
         try
         {
            retval = retval.substring(CRYPTION_PREFIX.length());
            retval = new Cryption().decryptText(retval);
         }
         catch (Exception e)
         {
            throw new SystemException(e);
         }
      }
      return retval;

   }//------------------------------------------------------------
   /**
    * Get a Setting property as an Integer object.
    * 
    * @param aClass calling class
    * @param Key
    *           Name of the numeric property to be returned.
    * 
    * @return Value of the property as an Integer or null if no property found.
    */

   public Integer getInteger(Class<?> aClass, String key, int defaultValue)
   {
	   return getInteger(aClass.getName()+".key",defaultValue);
   }//------------------------------------------------------------
   /**
    * Get a Settings property as an c object.
    * @param aClass the class the property is related to
    * @param key the Settings name
    * @param defaultValue the default value to return if the property does not exist
    * @return the Settings character
    */
   public Character getCharacter(Class<?> aClass,String key,char defaultValue)
   {
	   String results = getText(aClass,key, "");
	   
	   if(results.length() == 0)
		   return Character.valueOf(defaultValue);
	   else return Character.valueOf(results.charAt(0));//return first character
	   
   }//---------------------------------------------
   /**
    * Get a Settings property as an Integer object.
    * 
    * @param Key
    *           Name of the numeric property to be returned.
    * 
    * @return Value of the property as an Integer or null if no property found.
    */

   public Integer getInteger(String key)
   {
      Integer iVal = null;
      String sVal = getText(key);

      if ((sVal != null) && (sVal.length() > 0))
      {

         iVal = Integer.valueOf(sVal);

      }
      return iVal;

   }//------------------------------------------------------------

   public Integer getInteger(String key, int aDefault)
   {

      return getInteger(key, Integer.valueOf(aDefault));

   }//-------------------------------------------------------------
   public Integer getInteger(Class<?> cls, String key)
   {
	   return getInteger(cls.getName()+"."+key);
	   
   }//---------------------------------------------
   public Integer getInteger(Class<?> cls, String key, Integer aDefault)
   {
	   return getInteger(cls.getName()+"."+key,aDefault);
	   
   }//---------------------------------------------
   public Integer getInteger(String key, Integer aDefault)
   {
      Integer iVal = null;
      String sVal = getText(key);
      if ((sVal != null) && (sVal.length() > 0))
      {
         iVal = Integer.valueOf(sVal);
      }
      else
      {
         iVal = aDefault;
      }
      return iVal;
   }//------------------------------------------------------------

   /**
    * Get a Setting property as a Boolean object.
    * 
    * @param Key
    *           Name of the numeric property to be returned.
    * 
    * @return Value of the property as an Boolean or null if no property found.
    *         <p/><p/><p/>Note that the value of the returned Boolean will be
    *         false if the <p/>property sought after exists but is not equal to
    *         "true" (ignoring case).
    */

   public Boolean getBoolean(String key)
   {
      Boolean bVal = null;
      String sVal = getText(key);
      if ((sVal != null) && (sVal.length() > 0))
      {
         bVal =  Boolean.valueOf(sVal);
      }
      return bVal;
   }//------------------------------------------------------------

   public Boolean getBoolean(String key, Boolean aBool)
   {
      Boolean bVal = null;
      String sVal = getText(key);
      if ((sVal != null) && (sVal.length() > 0))
      {
         bVal =  Boolean.valueOf(sVal);
      }
      else
      {
         bVal = aBool;
      }
      return bVal;

   }//------------------------------------------------------------
   /**
    * @param key
    *           the Settings key
    * @param aBool
    *           default value
    * 
    * @return aBool if the Settings value for the key is blank
    */

   public Boolean getBoolean(Class<?> aClass, String key, boolean aBool)
   {
	   return getBoolean(aClass.getName()+"."+key,aBool);
   }//---------------------------------------------
   /**
    * @param key
    *           the Settings key
    * @param aBool
    *           default value
    * 
    * @return aBool if the Settings value for the key is blank
    */

   public Boolean getBoolean(String key, boolean aBool)
   {
      Boolean bVal = null;
      String sVal = getText(key);
      if ((sVal != null) && (sVal.length() > 0))
      {
         bVal = Boolean.valueOf(sVal);
      }
      else
      {
         bVal =  Boolean.valueOf(aBool);
      }
      return bVal;

   }//------------------------------------------------------------

   public Long getLong(String key)
   {
      Long longValue = null;
      String sVal = getText(key);
      if ((sVal != null) && (sVal.length() > 0))
      {
         longValue = Long.valueOf(sVal);
      }
      return longValue;
   }//------------------------------------------------------------

   public Long getLong(String key, long aDefault)
   {
      return getLong(key, Long.valueOf(aDefault));
   }//-------------------------------------------------------------

   public Long getLong(String key, Long aDefault)
   {
      Long longValue = null;
      String sVal = getText(key);
      if ((sVal != null) && (sVal.length() > 0))
      {
         longValue = Long.valueOf(sVal);
      }
      else
      {
         longValue = aDefault;
      }
      return longValue;

   }//------------------------------------------------------------

   public final synchronized static Setting getInstance()
   {
	   if(_instance == null) {
		   _instance = new Setting();
		   _instance.setSettingMap(Config.getProperties());
	   }
	   
	   return _instance;
	   
   }// --------------------------------------------------------
   private static Setting _instance = null;
   /**
    * @return a copy of the Setting properties
    */
   @SuppressWarnings("unchecked")
   public <K,V> Map<K,V> getSettingMap()
   {

      //return copy
      Hashtable<K,V> prop = new Hashtable<K,V>();
      
      prop.putAll((Map<K,V>)settingMap);
      
      return prop;
   }//------------------------------------------------------------

   public <K,V> void setSettingMap(Map<K,V> map)
   {
      this.settingMap = new Hashtable<Object,Object> (map);
   }// --------------------------------------------
   private  Map<Object,Object> settingMap = new Hashtable<Object,Object>(); // Setting properties

   //private static long lastCheckTime = 0;


   protected transient Log logger = Debugger.getLog(Setting.class);;
}

