package nyla.solutions.core.net;

import java.net.Authenticator;

import nyla.solutions.core.exception.RequiredException;

/**
 * 
 * @author Gregory Green
 *
 */
public class CredentialAuthenticator extends Authenticator
{
   /**
    * 
    * Constructor for CredentialAuthenticator initializes internal
     * @param aUsername the user name
     * @param password the password
    */
   public CredentialAuthenticator(String aUsername, char[] password)
   {
      if(aUsername == null || aUsername.length() == 0)
         throw new RequiredException("aUsername in CredentialAuthenticator.CredentialAuthenticator");
      
      if (password == null || password.length == 0)
         throw new RequiredException(
         "password required in CredentialAuthenticator");
      
      this.userName = aUsername;
      
       this.password = password.clone();
   }// --------------------------------------------
   /**
    * 
    *
    * @see java.net.Authenticator#getPasswordAuthentication()
    */
   protected java.net.PasswordAuthentication getPasswordAuthentication()
   {
      return new java.net.PasswordAuthentication(userName, password);
   }// --------------------------------------------

   private String userName = null;
   private char[] password = null;

}
