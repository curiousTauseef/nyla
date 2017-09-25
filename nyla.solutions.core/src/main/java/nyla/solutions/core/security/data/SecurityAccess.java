package nyla.solutions.core.security.data;

import java.io.Serializable;
import java.security.Principal;
import java.security.acl.AclEntry;
import java.security.acl.Permission;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import nyla.solutions.core.util.Debugger;

/**
 * <pre>
 * SecurityAccess access control list entry
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public class SecurityAccess implements Serializable, AclEntry
{
	/**
	 * 
	 * Constructor for SecurityAccess initializes internal data settings.
	 * 
	 * @param principal
	 *            the principal that has the security access
	 */
	public SecurityAccess(Principal principal)
	{
		this.principal = principal;
	}// --------------------------------------------

	/**
	 * 
	 * Constructor for SecurityAccess initializes internal data settings.
	 *
	 */
	public SecurityAccess()
	{
		principal = null;
		negative = false;
	}// --------------------------------------------

	public SecurityAccess(Principal principal, Permission permission)
	{
		if (principal == null)
			throw new IllegalArgumentException("principal is required");

		if (permission == null)
			throw new IllegalArgumentException("permission is required");

		this.principal = principal;
		this.permissions.add(permission);
	}// ------------------------------------------------

	public SecurityAccess(Principal principal, boolean negative, String permission)
	{
		this(principal,permission);
		this.negative = negative;
	}

	public SecurityAccess(Principal principal, String permission)
	{
		this(principal, new SecurityPermission(permission));
	}// ------------------------------------------------

	/**
	 * 
	 * @param permission
	 *            the permission
	 * @see java.security.acl.AclEntry#addPermission(java.security.acl.Permission)
	 */
	public boolean addPermission(Permission permission)
	{
		return this.permissions.add(permission);
	}// --------------------------------------------

	/**
	 * Add collection of permissions the acl entry
	 * 
	 * @param aPermssions
	 *            the collection of permissions
	 */
	public void addPermissions(Collection<Permission> aPermssions)
	{
		if (aPermssions == null)
			throw new IllegalArgumentException(
			"aPermssions required in SecurityAccess");

		// SecurityPermission element = null;
		for (Iterator<Permission> i = aPermssions.iterator(); i.hasNext();)
		{
			addPermission((SecurityPermission) i.next());
		}
	}// --------------------------------------------

	/**
	 * 
	 * @param permission
	 *            the permission to remove
	 * @see java.security.acl.AclEntry#removePermission(java.security.acl.Permission)
	 */
	public boolean removePermission(Permission permission)
	{
		return permissions.remove(permission);
	}// --------------------------------------------

	/**
	 * 
	 * @param permission
	 *            the permission to check
	 * @see java.security.acl.AclEntry#checkPermission(java.security.acl.Permission)
	 */
	public boolean checkPermission(Permission permission)
	{
		if (permission == null)
			return false;

		if(negative)
			return !this.permissions.contains(permission);
		else
			return this.permissions.contains(permission);
	}// --------------------------------------------

	/**
	 * Return enumeration of the permissions
	 * 
	 * @see java.security.acl.AclEntry#permissions()
	 */
	public Enumeration<Permission> permissions()
	{
		if (permissions == null || permissions.isEmpty())
			return null;

		return Collections.enumeration(this.permissions);
	}// --------------------------------------------

	/**
	 * 
	 * @return the list of permission
	 */
	public synchronized Collection<Permission> getPermissions()
	{
		if (this.permissions == null || permissions.isEmpty())
			return null;

		return new ArrayList<Permission>(permissions);
	}// --------------------------------------------
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("SecurityAccess [principal=").append(principal).append(", permissions=").append(permissions)
		.append(", negative=").append(negative).append("]");
		return builder.toString();
	}

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
	}// --------------------------------------------

	/**
	 * 
	 * @see java.security.acl.AclEntry#setNegativePermissions()
	 */
	public void setNegativePermissions()
	{
		this.negative = true;
	}// --------------------------------------------

	/**
	 * 
	 * @see java.security.acl.AclEntry#isNegative()
	 */
	public boolean isNegative()
	{
		return negative;
	}// --------------------------------------------

	/**
	 * 
	 * @see java.security.acl.AclEntry#getPrincipal()
	 */
	public Principal getPrincipal()
	{
		return principal;
	}// --------------------------------------------b

	/**
	 * @param permissions
	 *            The permissions to set.
	 */
	public synchronized void setPermissions(Collection<Permission> aPermissions)
	{
		this.permissions.clear();
		if (aPermissions == null || aPermissions.isEmpty())
			return;

		Set<Permission> set =new HashSet<Permission>(aPermissions);

		this.permissions = set;

	}// --------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((permissions == null) ? 0 : permissions.hashCode());
		result = prime * result + ((principal == null) ? 0 : principal.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		if (permissions == null)
		{
			if (other.permissions != null)
				return false;
		}
		else if (!permissions.equals(other.permissions))
			return false;
		if (principal == null)
		{
			if (other.principal != null)
				return false;
		}
		else if (!principal.equals(other.principal))
			return false;
		return true;
	}

	/**
	 * @param principal
	 *            the principal to set
	 */
	public boolean setPrincipal(Principal principal)
	{
		this.principal = principal;

		return true;
	}

	// private transient Log logger = Debugger.getLog(getClass());

	private Principal principal;
	private Set<Permission> permissions = new HashSet<Permission>();
	private boolean negative = false;
	static final long serialVersionUID = 1;
}