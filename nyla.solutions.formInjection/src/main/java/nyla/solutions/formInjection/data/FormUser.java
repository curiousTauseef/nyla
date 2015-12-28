package nyla.solutions.formInjection.data;

import java.io.Serializable;

import nyla.solutions.global.data.Criteria;
import nyla.solutions.global.security.user.data.User;
import nyla.solutions.global.security.user.data.UserProfile;



/**
 * <pre>
 * FormUser provides a set of functions to
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class FormUser extends UserProfile 
implements User, Serializable
{
   
   /**
    * Constructor for FormUser initializes internal 
    * data settings.
    * 
    */
   public FormUser()
   {
      super();
   }//--------------------------------------------
   /**
    * Constructor for FormUser initalizes internal 
    * data settings.
    * @param aPK
    * @throws IllegalArgumentException
    */
   public FormUser(Criteria aPK) throws IllegalArgumentException
   {
      this.copy(aPK);
   }//--------------------------------------------
   /**
    * Constructor for FormUser initalizes internal 
    * data settings.
    * @param aPK
    * @throws IllegalArgumentException
    */
   public FormUser(int aPK) throws IllegalArgumentException
   {
      this.setPrimaryKey(aPK);
   }//--------------------------------------------
   /**
    * @return Returns the firstName.
    */
   public String getFirstName()
   {
      if(firstName == null)
         return "";
      
      return firstName;
   }//--------------------------------------------
   /**
    * @param firstName The firstName to set.
    */
   public void setFirstName(String firstName)
   {
      if (firstName == null)
         firstName = "";

      this.firstName = firstName;
   }//--------------------------------------------
   /**
    * @return Returns the lastName.
    */
   public String getLastName()
   {
      if(lastName == null)
         return "";
            
      return lastName;
   }//--------------------------------------------
   /**
    * @param lastName The lastName to set.
    */
   public void setLastName(String lastName)
   {
      if (lastName == null)
         lastName = "";

      this.lastName = lastName;
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
   /**
    * @return Returns the title.
    */
   public String getTitle()
   {
      if(title == null)
         return "";
      
      return title;
   }//--------------------------------------------
   /**
    * @param title The title to set.
    */
   public void setTitle(String title)
   {
      if (title == null)
         title = "";

      this.title = title;
   }//--------------------------------------------
   /**
    * 
    * @return title+" "+firstName+" "+lastName 
    * @see java.security.Principal#getName()
    */
   public String getName()
   {
      return getTitle()+" "+getFirstName()+" "+getLastName();
   }//--------------------------------------------
   
   /**
    * @return Returns the email.
    */
   public String getEmail()
   {
      return email;
   }//--------------------------------------------
   /**
    * @param email The email to set.
    */
   public void setEmail(String email)
   {
      if (email == null)
         email = "";

      this.email = email;
   }//--------------------------------------------
   private String email = "";
   private String title = "";
   private String loginID = "";
   private String firstName = "";
   private String lastName = "";
   static final long serialVersionUID = FormUser.class.getName().hashCode();
}
