package nyla.solutions.formInjection.security;


import nyla.solutions.formInjection.data.Form;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.security.data.SecurityCredential;
import nyla.solutions.global.util.Config;



/**
 * <pre>
 * FormGuard to protected form component data
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class FormGuard
{
   /**
    * Constructor for FormSecurity initalizes internal 
    * data settings.
    * 
    */
   public FormGuard()
   {
      super();      
   }//--------------------------------------------
   /**
    * 
    * @param aCheckPointName the check point name
    * @param aUser the security credentials
    * @return true if user has cleared configured check point's canProceed
    */
   public static boolean canPassCheckPoint(String aCheckPointName, Form aForm, SecurityCredential aUser)
   {
      return createCheckPointForName(aCheckPointName).canProceed(aForm, aUser);
   }//--------------------------------------------
   /**
    * 
    * @param aCheckPointName the check (config file entry "form.FormGuard.createCheckPointForName."+aCheckPointName)
    * @return CheckPoint Strategy object instance
    */
   protected static CheckPoint createCheckPointForName(String aCheckPointName)
   {
      String className = Config.getProperty("form.FormGuard.createCheckPointForName."+aCheckPointName, EditCheckPoint.class.getName());
      
      try
      {
         return (CheckPoint)Class.forName(className).newInstance();   
      }
      catch(Exception e)
      {
         throw new SystemException(e);
      }
   }//--------------------------------------------
}
