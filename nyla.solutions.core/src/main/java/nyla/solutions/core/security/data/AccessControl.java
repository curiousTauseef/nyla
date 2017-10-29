package nyla.solutions.core.security.data;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

public interface AccessControl
{

	/**
	 * 
	 * @param permission
	 *            the permission
	 * @return the boolean if the permission was set
	 * @see java.security.acl.AclEntry#addPermission(java.security.acl.Permission)
	 */
	boolean addPermission(Permission permission);
	
	/**
	 * Add collection of permissions the acl entry
	 * 
	 * @param aPermssions
	 *            the collection of permissions
	 */
	void addPermissions(Collection<Permission> aPermssions);
	
	/**
	 * 
	 * @param permission the permission to remove
	 *  @return the permission that was removed
	 * @see java.security.acl.AclEntry#removePermission(java.security.acl.Permission)
	 */
	boolean removePermission(Permission permission);
	
	/**
	 * 
	 * @param permission the permission to check
	 * @return the permission is allowed
	 * @see java.security.acl.AclEntry#checkPermission(java.security.acl.Permission)
	 */
	boolean checkPermission(Permission permission);
	
	/**
	 * @return list of the permissions
	 * 
	 * @see java.security.acl.AclEntry#permissions()
	 */
	List<Permission> getPermissions();
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	
	/**
	 * 
	 * @see java.security.acl.AclEntry#setNegativePermissions()
	 */
	void setNegativePermissions();
	

	/**
	 * 
	 * @return true if access control is negative
	 */
	boolean isNegative();
	
	/**
	 * 
	 * @return the principal
	 */
	Principal getPrincipal();
	
	/**
	 * @param  permissions
	 *            The permissions to set.
	 */
	void setPermissions(Collection<Permission> permissions);
	

}