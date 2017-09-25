package nyla.solutions.core.security.data;

import java.security.Principal;
import java.security.acl.Group;
import java.util.HashSet;
import java.util.Set;

public class SecurityUser implements Principal
{	
	public SecurityUser()
	{
	}
	public SecurityUser(String name)
	{
		this(name,null);
	}
	public SecurityUser(String name,Set<Group> groups)
	{
		super();
		this.groups = groups;
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SecurityUser other = (SecurityUser) obj;
		if (name == null)
		{
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		return true;
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("SecurityUser [groups=").append(groups).append(", name=").append(name).append("]");
		return builder.toString();
	}

	public void addGroup(Group group)
	{
		this.groups.add(group);
	}

	/**
	 * @return the groups
	 */
	public Set<Group> getGroups()
	{
		if(groups.isEmpty())
			return null;
		
		return new HashSet<Group>(groups);
	}

	/**
	 * @param groups the groups to set
	 */
	public void setGroups(Set<Group> groups)
	{
		if(groups == null)
			this.groups.clear();
		else	
			this.groups = new HashSet<Group>(groups);
	}


	private Set<Group> groups = new HashSet<Group>();

	private String name;
}
