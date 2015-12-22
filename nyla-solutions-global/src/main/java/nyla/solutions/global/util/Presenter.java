package nyla.solutions.global.util;


import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;



import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Presenter;
import nyla.solutions.global.util.Text;


/**
 * 
 * <pre>
 * 
 *  Presenter message view helper.
 *  
 *  All presentation messages can be written by the system configuration module.
 *  @see Config
 * 
 * </pre>
 * 
 * @author Gregory Green
 * 
 * @version 1.0
 * 
 */

public class Presenter
{

   /**
    * 
    * Constructor for Presenter initalizes internal 
    * data settings.
    */
   private Presenter()
   {
      this(Locale.US);

   }// --------------------------------------------
   /**
    * 
    * Constructor for Presenter initalizes internal 
    * data settings.
    * @param aLocale the local
    */
   private Presenter(Locale aLocale)
   {
      this(Presenter.class.getName(), aLocale);
   }// --------------------------------------------

   /**
    * Constructor for Presenter initalizes internal data settings.
    * 
    */
   protected Presenter(String aBundleName, Locale aLocale)
   {

      if (aBundleName == null)

         throw new IllegalArgumentException("aBundleName required in Presenter");

      if (aLocale == null)

         throw new IllegalArgumentException("aLocale required in Presenter");

      init(ResourceBundle.getBundle(aBundleName, aLocale));
      
   }// --------------------------------------------
   /**
    * 
    * Constructor for Presenter initalizes internal 
    * data settings.
    * @param aResourceBundle
    */
   private Presenter(ResourceBundle aResourceBundle)
   {
      init(aResourceBundle);
   }// --------------------------------------------
   /**
    * Initial the present with the given resource bundle
    * @param aResourceBundle
    */
   private void init(ResourceBundle aResourceBundle)
   {
      if (aResourceBundle == null)
         throw new IllegalArgumentException(
         "aResourceBundle required in Presenter.init");
      
      resourceBundle = aResourceBundle; 
   }// --------------------------------------------
   /**
    * 
    * Singleton factory method
    * 
    * @return a single instance of the Presenter object
    * 
    * for the JVM
    * 
    */

   public static Presenter getPresenter(ResourceBundle aBundle)
   {
      return new Presenter(aBundle);
   }// --------------------------------------------
   /**
    * 
    * @return a new instance of the Present with en_US locale
    */
   public static Presenter getPresenter()
   {
      return new Presenter();
      
   }// --------------------------------------------
   /**
    * 
    * New instance for the locale
    * 
    * @return a new instance of the Presenter
    * 
    */

   public static Presenter getPresenter(Locale aLocale)
   {
      return new Presenter(aLocale);
   }// --------------------------------------------
   /**
    * 
    * New instance for the class
    * 
    * @return a new instance of the Presenter
    * 
    */

   public static Presenter getPresenter(Class<?> aClass)
   {
      if (aClass == null)
         throw new IllegalArgumentException(
         "aClass required in Presenter.getPresenter");
      
      return new Presenter(ResourceBundle.getBundle(aClass.getName()));
   }// --------------------------------------------
   /**
    * 
    * 
    * 
    * @param aKey
    *           bundle message key
    * 
    * @return resourceBundle.getString(aKey)
    * 
    */

   public String getText(String aKey)
   {
      String defaultMessage = "";
      
      try
      {
         defaultMessage = resourceBundle.getString(aKey);
      }
      catch(MissingResourceException e)
      {
         Debugger.printWarn(e);
      }
      
      return Config.getProperty(aKey, defaultMessage);

   }// --------------------------------------------
   /**
    * 
    * 
    * 
    * @param aKey
    *           bundle message key
    * 
    * @return resourceBundle.getString(aKey)
    * 
    */
   public <K,V> String getText(String aKey, Map<K,V>  aParameters)
   {
      try
      {

         return Text.format(getText(aKey), aParameters);
      }

      catch (Exception e)
      {
         throw new SystemException(e);
      }
   }// --------------------------------------------
   /**
    * 
    * @param aKey the resource bundle keyu
    * @param aValue the single place holder in resource text i.e. 
    *  test=A single value placed here ${0} 
    * @return
    */
   public String getText(String aKey, String [] aValues)
   {
      if(aValues == null)
         throw new IllegalArgumentException("aValues required in Presenter.getText");
      try
      {
         HashMap<String,String> map = new HashMap<String,String>();
         
         for (int i = 0; i < aValues.length; i++)
         {
            map.put(Integer.toString(i),aValues[i]);   
         }
         

         return getText(aKey,map);
      }
      catch (Exception e)
      {
         throw new SystemException(e);
      }
   }// --------------------------------------------

   private ResourceBundle resourceBundle = null;

}

