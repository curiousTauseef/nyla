package nyla.solutions.core.security.data;

import java.security.Principal;
import java.util.Collection;
import java.util.List;


/**
 * <pre>
 * SecurityRole is a value object representation of 
 * the ROLE table.
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class SecurityRole extends SecurityAccessControl 
implements java.security.Principal
{
	
	  /**
		 * @param principal
		 * @param name
		 */
		public SecurityRole(String name)
		{
			super();
			this.name = name;
		}
   /**
	 * @param principal
	 * @param name
	 */
	public SecurityRole(Principal principal, String name)
	{
		super(principal);
		this.name = name;
	}
/**
    * 
    * @see java.security.acl.AclEntry#addPermission(java.security.acl.Permission)
    */
   public boolean addPermission(Permission permission)
   {
      
      return super.addPermission(permission); 
   }
   /**
    * 
    * @see java.security.acl.AclEntry#checkPermission(java.security.acl.Permission)
    */
   public boolean checkPermission(Permission permission)
   {
      return super.checkPermission(permission);
   }//--------------------------------------------
   public synchronized List<Permission> getPermissions()
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
    * @see java.security.acl.AclEntry#setNegativePermissions()
    */
   public void setNegativePermissions()
   {
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
   /* (non-Javadoc)
 * @see java.lang.Object#hashCode()
 */
@Override
public int hashCode()
{
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	return result;
}
/* (non-Javadoc)
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
	SecurityRole other = (SecurityRole) obj;
	if (name == null)
	{
		if (other.name != null)
			return false;
	}
	else if (!name.equals(other.name))
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
      
      return aRole.equalsIgnoreCase(name);
   }//--------------------------------------------
   
   private final String name;
   static final long serialVersionUID = 1;
}