package nyla.solutions.formInjection.security;

import nyla.solutions.formInjection.data.Form;
import nyla.solutions.global.security.data.SecurityCredential;


/**
 * <pre>
 * EditCheckPoint chekc if form is read only
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class EditCheckPoint extends AbstractCheckPoint
{

   /**
    * Constructor for EditCheckPoint initalizes internal 
    * data settings.
    * 
    */
   public EditCheckPoint()
   {
      super();
   }//--------------------------------------------
   public boolean canProceed(Form aForm, SecurityCredential aUser)
   {
      return !aForm.isReadOnly();
   }//--------------------------------------------
}
