package nyla.solutions.global.security.data;

import java.io.Serializable;
import java.security.Principal;
import java.security.acl.*;
import java.util.*;

import nyla.solutions.global.data.*;
import nyla.solutions.global.operations.logging.Log;
import nyla.solutions.global.util.*;
/**
 * <pre>
 * SecurityAccess access control list entry
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class SecurityAccess implements Serializable, AclEntry
{
   /**
    * 
    * Constructor for SecurityAccess initializes internal 
    * data settings.
    * @param principal the user that has the security access
    */
   public SecurityAccess(Principal principal)
   {
       user = null;
       permissions = new Vector<Permission>(10, 10);
       negative = false;
       user = principal;
   }//--------------------------------------------
   /**
    * 
    * Constructor for SecurityAccess initalizes internal 
    * data settings.
    *
    */
   public SecurityAccess()
   {
       user = null;
       permissions = new Vector<Permission>(10, 10);
       negative = false;
   }//--------------------------------------------
   /**
    * Returnt the user
    * 
    * @see java.security.acl.AclEntry#setPrincipal(java.security.Principal)
    */
   public boolean setPrincipal(Principal principal)
   {
       if(user != null)
       {
          return false;
       } 
       else
       {
           user = principal;
           return true;
       }
   }//--------------------------------------------
   /**
    * 
    * @param permission the permission
    * @see java.security.acl.AclEntry#addPermission(java.security.acl.Permission)
    */
   public boolean addPermission(Permission permission)
   {
       if(permissions.contains(permission))
       {
           return false;
       } 
       else
       {
           permissions.add(permission);
           return true;
       }
   }//--------------------------------------------
   /**
    * Add collection of permissions the acl entry
    * @param aPermssions the collection of permissions
    */
   public void addPermissions(Collection<Permission> aPermssions)
   {
      if (aPermssions == null)
         throw new IllegalArgumentException(
         "aPermssions required in SecurityAccess");
      
      //SecurityPermission element = null;
      for (Iterator<Permission> i = aPermssions.iterator(); i.hasNext();)
      {
         addPermission((SecurityPermission) i.next());
      }
   }//--------------------------------------------
   /**
    * 
    * @param permission the permission to remove
    * @see java.security.acl.AclEntry#removePermission(java.security.acl.Permission)
    */
   public boolean removePermission(Permission permission)
   {
       return permissions.remove(permission);
   }//--------------------------------------------
   /**
    * 
    * @param permission the permission to check
    * @see java.security.acl.AclEntry#checkPermission(java.security.acl.Permission)
    */
   public boolean checkPermission(Permission permission)
   {
       if (logger !=null) {
        logger.debug("test if "+permission+" in "+getPermissions());
       }
       //System.out.println("test if "+permission+" in "+getPermissions());
       return getPermissions().contains(permission);
   }//--------------------------------------------
   /**
    * Return enumeration of the permissions
    * 
    * @see java.security.acl.AclEntry#permissions()
    */
   public Enumeration<Permission> permissions()
   {
      return Collections.enumeration(this.permissions);
   }//--------------------------------------------
   /**
    * 
    * @return the list of permission
    */
   public synchronized Collection<Permission> getPermissions()
   {
	   if(this.permissions == null)
		   return null;
	   
       return new ArrayList<Permission>(permissions);
   }//--------------------------------------------
   /**
    * @return the string text of the security access
    * 
    * @see java.lang.Object#toString()
    */
   public String toString()
   {
       StringBuffer stringbuffer = new StringBuffer();
       if(negative)
           stringbuffer.append("-");
       else
           stringbuffer.append("+");
       if(user instanceof Group)
           stringbuffer.append("Group.");
       else
           stringbuffer.append("User.");
       stringbuffer.append(user + "=");
       for(Iterator<Permission> i = permissions.iterator(); i.hasNext();)
       {
           Permission permission = (Permission)i.next();
           stringbuffer.append(permission);
           if(i.hasNext())
               stringbuffer.append(",");
       }

       return new String(stringbuffer);
   }//--------------------------------------------
   /**
    * @return a copy of this object
    * @see java.lang.Object#clone()
    */
   public synchronized Object clone()
   {
      try
      {
         return super.clone();
      }
      catch (Exception e)
      {
         throw new RuntimeException(Debugger.stackTrace(e));
      }
   }//--------------------------------------------
   /**
    * 
    * @see java.security.acl.AclEntry#setNegativePermissions()
    */
   public void setNegativePermissions()
   {
      this.negative = true;
   }//--------------------------------------------
   /**
    * 
    * @see java.security.acl.AclEntry#isNegative()
    */
   public boolean isNegative()
   {
       return negative;
   }//--------------------------------------------   
   /**
    * 
    * @see java.security.acl.AclEntry#getPrincipal()
    */
   public Principal getPrincipal()
   {
       return user;
   }//--------------------------------------------
   
   /**
    * @return Returns the primaryKey.
    */
   public synchronized int getPrimaryKey()
   {
      return primaryKey;
   }//--------------------------------------------
   /**
    * @param permissions The permissions to set.
    */
   public synchronized void setPermissions(Collection<Permission> aPermissions)
   {
      this.permissions.clear();
      this.permissions.addAll(aPermissions);
   }//--------------------------------------------
   /**
    * @param primaryKey The primaryKey to set.
    */
   public synchronized void setPrimaryKey(int primaryKey)
   {
      this.primaryKey = primaryKey;
   }//--------------------------------------------
   

   /**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (negative ? 1231 : 1237);
		result = prime * result
				+ ((permissions == null) ? 0 : permissions.hashCode());
		result = prime * result + primaryKey;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		SecurityAccess other = (SecurityAccess) obj;
		if (negative != other.negative)
			return false;
		if (permissions == null)
		{
			if (other.permissions != null)
				return false;
		}
		else if (!permissions.equals(other.permissions))
			return false;
		if (primaryKey != other.primaryKey)
			return false;
		if (user == null)
		{
			if (other.user != null)
				return false;
		}
		else if (!user.equals(other.user))
			return false;
		return true;
	}

private transient Log logger = Debugger.getLog(getClass());

private int primaryKey = Data.NULL;
   private Principal user = null;
   private Collection<Permission> permissions= new HashSet<Permission>();
   private boolean negative = false;
   static final long serialVersionUID = 1;
}