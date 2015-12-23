/**
 * Created on Feb 21, 2004
 *
 * <b>Delegate</b>
 */
package nyla.solutions.global.patterns;

import nyla.solutions.global.security.data.SecurityCredential;

/**
 * @author green_gregory
 * @version 1.0
 *
 * <b>Delegate</b>  abstract delegate class
 * 
 */
public  abstract class Delegate
{
   public Delegate()
   {
      
   }// --------------------------------------------

   public Delegate(SecurityCredential aUser)
   {
      setUser(aUser);
   }
   
   

   /**
    * @return Returns the user.
    */
   public final SecurityCredential getUser()
   {
      return user;
   }// --------------------------------------------


   /**
    * @param user The user to set.
    */
   public final void setUser(SecurityCredential user)
   {   
      this.user = user;
   }// --------------------------------------------

   
   private SecurityCredential user = null;
}
