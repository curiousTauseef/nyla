package nyla.solutions.core.security.data;

import java.io.Serializable;
import java.security.acl.Permission;



/**
 * <pre>
 * SecurityPermission is a value object representation of 
 * the Permission table.
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class SecurityPermission 
implements Permission, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5321184181505071682L;


	/**
    * 
    * Constructor for SecurityPermission initializes internal 
    * data settings.
    * @param aPermission the permission name
    */
   public SecurityPermission(String aPermission)
   {
      this.permission = aPermission;
   }//--------------------------------------------
   
   /**
    * @return Returns the permission.
    */
   public String getPermission()
   {
      return permission;
   }//--------------------------------------------
 
   /**
 * @see java.lang.Object#hashCode()
 */
@Override
public int hashCode()
{
	final int prime = 31;
	int result = 1;
	result = prime * result
			+ ((permission == null) ? 0 : permission.hashCode());
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
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	SecurityPermission other = (SecurityPermission) obj;
	if (permission == null)
	{
		if (other.permission != null)
			return false;
	}
	else if (!permission.equals(other.permission))
		return false;
	return true;
}
  
   /**
    * 
    * @see java.security.acl.Permission#toString()
    */
   public String toString()
   {    
      return permission;
   }//--------------------------------------------
   
   
   private final String permission;
}