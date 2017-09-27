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
	 * @param permission
	 *            the permission to remove
	 * @see java.security.acl.AclEntry#removePermission(java.security.acl.Permission)
	 */
	boolean removePermission(Permission permission);
	
	/**
	 * 
	 * @param permission
	 *            the permission to check
	 * @see java.security.acl.AclEntry#checkPermission(java.security.acl.Permission)
	 */
	boolean checkPermission(Permission permission);// --------------------------------------------

	/**
	 * Return enumeration of the permissions
	 * 
	 * @see java.security.acl.AclEntry#permissions()
	 */
	List<Permission> getPermissions();// --------------------------------------------
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
	 * @see java.security.acl.AclEntry#isNegative()
	 */
	boolean isNegative();
	
	/**
	 * 
	 * @see java.security.acl.AclEntry#getPrincipal()
	 */
	Principal getPrincipal();
	
	/**
	 * @param permissions
	 *            The permissions to set.
	 */
	void setPermissions(Collection<Permission> aPermissions);
	

}