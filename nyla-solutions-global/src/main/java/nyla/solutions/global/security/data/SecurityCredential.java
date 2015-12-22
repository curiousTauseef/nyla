package nyla.solutions.global.security.data;

import nyla.solutions.global.data.Identifier;

/**
 * <pre>
 * SecurityCredentials authenticate interface
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public interface SecurityCredential extends Identifier
{
   /**
    * 
    * @return the credential login ID
    */
   public String getLoginID();  
   
   
}
