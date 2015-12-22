package nyla.solutions.global.security.user.data;


import java.util.*;

import nyla.solutions.global.data.Copier;
import nyla.solutions.global.data.Criteria;
import nyla.solutions.global.util.Text;
/**
 * 
 * <pre>
 * UserProfile is a value object representation of the 
 * User entity and associated entities.
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class UserProfile extends Criteria
implements User, Copier, Comparable<Object>
{
   /**
    * object
    * 
    * @see java.lang.Object#clone()
    */
    public Object clone()
        throws CloneNotSupportedException
    {
        return super.clone();
    }//--------------------------------------------
    public String getFirstName()
    {
        return firstName;
    }//--------------------------------------------
    /**
    * Constructor for UserProfile initializes internal 
    * data settings.
    * 
    */
   public UserProfile()
   {
   }//--------------------------------------------
   /**
    * Constructor for UserProfile initializes internal 
    * data settings.
    * @param email
    * @param loginID
    * @param firstName
    * @param lastName
    */
   public UserProfile(String email, String loginID, String firstName,
   String lastName)
   {
      super();
      this.email = email;
      this.loginID = loginID;
      this.firstName = firstName;
      this.lastName = lastName;
   }//--------------------------------------------
    public String getLastName()
    {
        return lastName;
    }//--------------------------------------------
    public String getName()
    {
        StringBuffer name = new StringBuffer(lastName);
        if(name.length() > 0)
            name.append(", ");
        name.append(firstName);
        return name.toString();
    }//--------------------------------------------
    public void setLastName(String lastName)
        throws IllegalArgumentException
    {
        if(Text.isNull(lastName))
            return;

        this.lastName = lastName;            
    }//--------------------------------------------
    public void setFirstName(String firstName)
        throws IllegalArgumentException
    {
        if(Text.isNull(firstName))
            return;
        if(!this.firstName.equals(firstName))
        {
            this.firstName = firstName;
        }
    }//--------------------------------------------
    public String getEmail()
    {
        return email;
    }//--------------------------------------------    
    public void setEmail(String email)
    {
        if(Text.isNull(email))
            return;

        this.email = email;
    }//--------------------------------------------
    /**
     * Compare user's by last name
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Object object)
    {
    	if(!(object instanceof User))
    	{
    		return -1;
    		
    	}
    	User user = (User)object;
       
        return getLastName().compareTo(user.getLastName());
        
    }//--------------------------------------------
    /**
     * 
     * @param aUsers the list of user
     * @return sorted list of users by last name
     */
    public static Collection<User> sortByLastName(Collection<User> aUsers)
    {
        if(!(aUsers instanceof List))
        {
            return null;
        } else
        {
            List<User> l = (List<User>)aUsers;
            Collections.sort(l);
            return l;
        }
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
    *
    * @see nyla.solutions.global.data.Criteria#copy(nyla.solutions.global.data.Copier)
    */
   public void copy(Copier from)
   {
      super.copy(from);
      
      if(!(from instanceof UserProfile))
         return;
      
      UserProfile other = (UserProfile)from;
      this.email = other.email;
      this.loginID = other.loginID;
      this.firstName = other.firstName;
      this.lastName = other.lastName;
      this.title = other.title;
   }//--------------------------------------------
   

   
   /**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((loginID == null) ? 0 : loginID.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserProfile other = (UserProfile) obj;
		if (email == null)
		{
			if (other.email != null)
				return false;
		}
		else if (!email.equals(other.email))
			return false;
		if (firstName == null)
		{
			if (other.firstName != null)
				return false;
		}
		else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null)
		{
			if (other.lastName != null)
				return false;
		}
		else if (!lastName.equals(other.lastName))
			return false;
		if (loginID == null)
		{
			if (other.loginID != null)
				return false;
		}
		else if (!loginID.equals(other.loginID))
			return false;
		if (title == null)
		{
			if (other.title != null)
				return false;
		}
		else if (!title.equals(other.title))
			return false;
		return true;
	}
	public void setId(String id)
   {
    this.setLoginID(id);
   }// --------------------------------------------

   public String getId()
   {
    
      return this.getLoginID();
   }// --------------------------------------------

    private String email = "";
    private String loginID = "";
    private String firstName = "";
    private String lastName = "";  
    private String title = "";
    static final long serialVersionUID = UserProfile.class.getName().hashCode();
}
