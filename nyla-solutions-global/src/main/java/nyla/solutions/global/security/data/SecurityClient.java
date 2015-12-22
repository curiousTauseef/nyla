package nyla.solutions.global.security.data;

import java.io.Serializable;
import java.security.Principal;

/**
 * <pre>
 * ServiceClient implements for SecurityCredential
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class SecurityClient implements SecurityCredential, Serializable, Principal
{
   /**
    * Constructor for ServiceClient initializes internal 
    * data settings.
    * 
    */
   public SecurityClient()
   {
   }//--------------------------------------------
   
   
   /**
    * Constructor for ServiceClient initializes internal 
    * data settings.
    * @param loginID
    * @param id
    */
   public SecurityClient(String loginID, Integer id)
   {
      this.loginID = loginID;
      this.id = id;
   }//--------------------------------------------
   
   /**
    * Constructor for ServiceClient initializes internal 
    * data settings.
    * @param loginID
    */
   public SecurityClient(String loginID)
   {
      this.loginID = loginID;
   }//--------------------------------------------
   /**
    * Implement the principal interface
    * @return the loginID
    */
   public String getName()
   {
		return loginID;
   }// ------------------------------------------------
   
   /**
    * Constructor for ServiceClient initializes internal 
    * data settings.
    * @param id
    */
   public SecurityClient(Integer id)
   {
      this.id = id;
   }//--------------------------------------------
   /**
    * Constructor for ServiceClient initializes internal 
    * data settings.
    * @param id
    */
   public SecurityClient(int id)
   {
      this.id = Integer.valueOf(id);
   }//--------------------------------------------   
   /**
    * @return Returns the id.
    */
   public String getId()
   {
      return id.toString();
   }//--------------------------------------------
   /**
    * @param id The id to set.
    */
   public void setId(Integer id)
   {
      this.id = id;
   }//--------------------------------------------
   public void setId(String id)
   {
      if (id == null || id.length() == 0)
         throw new IllegalArgumentException(
         "id required in setId");
      
      this.setId(Integer.valueOf(id));
   }//--------------------------------------------
   
   /**
    * @return Returns the loginID.
    */
   public String getLoginID()
   {
      return loginID;
   }//--------------------------------------------
   /**
    * @param loginID The loginID to set.
    */
   public void setLoginID(String loginID)
   {
      if (loginID == null)
         loginID = "";

      this.loginID = loginID;
   }//--------------------------------------------
   private String loginID = null;
   private Integer id = null;
   static final long serialVersionUID = SecurityClient.class.getName()
   .hashCode();

}
