package nyla.solutions.core.security.data;

import java.security.Principal;
import java.security.acl.Permission;
import java.util.Collection;
import java.util.Enumeration;

import nyla.solutions.core.data.Data;


/**
 * <pre>
 * SecurityRole is a value object representation of 
 * the ROLE table.
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class SecurityRole extends SecurityAccess 
implements java.security.Principal
{
   
   /**
    * 
    * @see java.security.acl.AclEntry#addPermission(java.security.acl.Permission)
    */
   public boolean addPermission(Permission permission)
   {
      
      return super.addPermission(permission);
      //----------------------------------------
   }
   /**
    * 
    * @see java.security.acl.AclEntry#checkPermission(java.security.acl.Permission)
    */
   public boolean checkPermission(Permission permission)
   {
      return super.checkPermission(permission);
   }//--------------------------------------------
   /**
    * 
    * @see java.lang.Object#clone()
    */
   public synchronized Object clone()
   {
      // TODO Auto-generated method stub
      return super.clone();
   }//--------------------------------------------
   public synchronized Collection<Permission> getPermissions()
   {
      return super.getPermissions();
   }//--------------------------------------------
 
   public synchronized void setPermissions(Collection<Permission> permissions)
   {
      // TODO Auto-generated method stub
      super.setPermissions(permissions);
      //----------------------------------------
   }
   /**
    * 
    * @see java.security.acl.AclEntry#getPrincipal()
    */
   public Principal getPrincipal()
   {
      // TODO Auto-generated method stub
      return super.getPrincipal();
      //----------------------------------------
   }
   /**
    * 
    * @see java.security.acl.AclEntry#isNegative()
    */
   public boolean isNegative()
   {
      // TODO Auto-generated method stub
      return super.isNegative();
      //----------------------------------------
   }
   /**
    * 
    * @see java.security.acl.AclEntry#permissions()
    */
   public Enumeration<Permission> permissions()
   {
      // TODO Auto-generated method stub
      return super.permissions();
   }//--------------------------------------------
   /**
    * 
    * @see java.security.acl.AclEntry#removePermission(java.security.acl.Permission)
    */
   public boolean removePermission(Permission permission)
   {
      // TODO Auto-generated method stub
      return super.removePermission(permission);
   }//--------------------------------------------
   /**
    * 
    * @see java.security.acl.AclEntry#setNegativePermissions()
    */
   public void setNegativePermissions()
   {
      // TODO Auto-generated method stub
      super.setNegativePermissions();
   }//--------------------------------------------
   /**
    * 
    * @see java.security.acl.AclEntry#setPrincipal(java.security.Principal)
    */
   public boolean setPrincipal(Principal principal)
   {
      // TODO Auto-generated method stub
       super.setPrincipal(principal);
       
       return true;
   }//--------------------------------------------
   /**
 * @see java.lang.Object#hashCode()
 */
@Override
public int hashCode()
{
	final int prime = 31;
	int result = 1;
	result = prime * result + ((code == null) ? 0 : code.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + priority;
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
	SecurityRole other = (SecurityRole) obj;
	if (code == null)
	{
		if (other.code != null)
			return false;
	}
	else if (!code.equals(other.code))
		return false;
	if (name == null)
	{
		if (other.name != null)
			return false;
	}
	else if (!name.equals(other.name))
		return false;
	if (priority != other.priority)
		return false;
	return true;
}
   /**
    * 
    * @see java.security.acl.Permission#toString()
    */
   public String toString()
   {    
      return name+super.getPermissions();
   }//--------------------------------------------
   /**
    * @return Returns the name.
    */
   public String getName()
   {
      return name;
   }//--------------------------------------------

    public int getPriority() {
        return priority;
    }//--------------------------------------------
    /**
     * 
     * @param priority the role priority
     */
    public void setPriority(int priority) 
    {
        this.priority = priority;
    }//--------------------------------------------
    /**
     * 
     * @return code
     */
    public String getCode() 
    {
        return code;
    }//--------------------------------------------
    public void setCode(String code) 
    {
       if(code == null)
          code = "";
       
        this.code = code.trim();
    }//--------------------------------------------
    /**
    * @param name The name to set.
    */
   public void setName(String name)
   {
      if (name == null)
         name = "";
      
      this.name = name.trim();
   }//--------------------------------------------
   /**
    * 
    * @param aRoles the role name or code
    * @return true if any role in roles equals this code and or name
    * 
    */
   public boolean equalsRole(String[] aRoles)
   {
      if(aRoles == null || aRoles.length == 0)
         return false;
      
      for (int i = 0; i < aRoles.length; i++)
      {
         if(equalsRole(aRoles[i]))
            return true;
      }
      
      return false;
   }//--------------------------------------------
   /**
    * 
    * @param aRole the role name or code
    * @return aRole.equalsIgnoreCase(name)
              || aRole.equalsIgnoreCase(code)
    */
   public boolean equalsRole(String aRole)
   {            
      if(aRole == null )
         return false;
      
      aRole = aRole.trim();
      
      return aRole.equalsIgnoreCase(name)
              || aRole.equalsIgnoreCase(code);
   }//--------------------------------------------
   
   private int priority = Data.NULL;
   private String code = "";
   private String name = "";
   static final long serialVersionUID = 1;
}