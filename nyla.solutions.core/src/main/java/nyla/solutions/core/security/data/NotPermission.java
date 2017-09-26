package nyla.solutions.core.security.data;

import java.security.acl.Permission;

public class NotPermission implements Permission
{
	/**
	 * @param permission
	 */
	public NotPermission(Permission permission)
	{
		if (permission == null)
			throw new IllegalArgumentException("permission is required");
		
		this.permission = permission;
	}

	
	/* (non-Javadoc)
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


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		return !permission.equals(obj);
	}


	private final Permission permission;
}
