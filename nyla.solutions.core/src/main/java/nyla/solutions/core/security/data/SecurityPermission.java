package nyla.solutions.core.security.data;

import java.io.Serializable;
import java.security.acl.Permission;

import nyla.solutions.core.data.Data;
import nyla.solutions.core.data.PrimaryKey;



/**
 * <pre>
 * SecurityPermission is a value object representation of 
 * the Permission table.
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class SecurityPermission 
implements Permission, PrimaryKey, Serializable
{
   public SecurityPermission()
   {}//--------------------------------------------
   /**
    * 
    * Constructor for SecurityPermission initializes internal 
    * data settings.
    * @param aPermission the permission name
    */
   public SecurityPermission(String aPermission)
   {
      this.setPermission(aPermission);
   }//--------------------------------------------
   /**
    * @return Returns the permission.
    */
   public String getPermission()
   {
      return permission;
   }//--------------------------------------------
   /**
    * @param permission The permission to set.
    */
   public void setPermission(String permission)
   {
      if (permission == null|| permission.length() == 0)
         throw new IllegalArgumentException(
         "permission required in SecurityPermission");

      this.permission = permission.trim();
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
	result = prime * result + primaryKey;
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
	if (primaryKey != other.primaryKey)
		return false;
	return true;
}
   /**
    * @return Returns the primaryKey.
    */
   public synchronized int getPrimaryKey()
   {
      return primaryKey;
   }//--------------------------------------------
   /**
    * @param primaryKey The primaryKey to set.
    */
   public synchronized void setPrimaryKey(int primaryKey)
   {
      this.primaryKey = primaryKey;
   }//--------------------------------------------
   /**
    * 
    * @see java.security.acl.Permission#toString()
    */
   public String toString()
   {    
      return permission;
   }//--------------------------------------------
   
   
   private String permission = "";
   private int primaryKey = Data.NULL;
   static final long serialVersionUID = 1;
}