package org.solutions.form.ejb;
import org.solutions.form.ejb.session.*;
import org.solutions.j2ee.blueprint.elb.Locator;

/**
 * <pre>
 * FormLocator Service locator for Form services
 * 
 * Remote JNDI /gcsm/form/FormBeanHomeRemote
 * LOCAL  JNDI /gcsm/form/FormBeanHomeLocal
 *  
 * @author Gregory
 * @version 1.0
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class FormLocator extends Locator
{

   /**
    * Constructor for FormLocator initializes internal 
    * data settings.
    * @param aJNDI
    * @param aHomeClass
    * @throws Exception
    */
   protected FormLocator() throws Exception
   {
      super("/gcsm/form/FormBeanHomeRemote", FormBeanHomeRemote.class);   
   }//--------------------------------------------
   protected FormLocator(String aJNDI, Class aClass)
   throws Exception
   {
      super(aJNDI,aClass);
   }//--------------------------------------------
   /**
    * 
    * @return the form bean remote instance
    * @throws Exception
    */
   public static FormBeanRemote getFormBeanRemote()
   throws Exception
   {
      if(homeRemote == null)
      {
         homeRemote = (FormBeanHomeRemote)new FormLocator().locateRemoteHome();         
      }
      return homeRemote.create();
   }//--------------------------------------------
   /**
    * 
    * @return the form bean local instance
    * @throws Exception
    */
   public static FormBeanLocal getFormBeanLocal()
   throws Exception
   {
      if(homeLocal == null)
      {
         homeLocal = (FormBeanHomeLocal)new FormLocator("/gcsm/form/FormBeanHomeLocal",FormBeanHomeLocal.class).locateLocalHome();         
      }
      return homeLocal.create();
   }//--------------------------------------------   
   private static FormBeanHomeRemote homeRemote = null;
   private static FormBeanHomeLocal homeLocal = null; 

}
