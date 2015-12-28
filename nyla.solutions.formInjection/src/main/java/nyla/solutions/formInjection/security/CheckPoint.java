package nyla.solutions.formInjection.security;

import nyla.solutions.formInjection.data.Form;
import nyla.solutions.global.security.data.SecurityCredential;


/**
 * <pre>
 * CheckPoint provides a interface to protected form access
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public interface CheckPoint
{
   /**
    * Determines whether user access should be granted
    * @param aForm the form the check
    * @param aUser the access user
    * @return true if user access should be granted
    */
   public boolean canProceed(Form aForm, SecurityCredential aUser);

}
