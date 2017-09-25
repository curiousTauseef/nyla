package nyla.solutions.core.ds.security;

import java.security.acl.Group;
import java.util.Collection;

import nyla.solutions.core.security.data.SecurityGroup;
import nyla.solutions.core.util.Text;

public class LdapSecurityGroup extends SecurityGroup
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9061501642280480803L;

	public LdapSecurityGroup(String dn)
	{
		this(dn,"CN");
	}// --------------------------------------------------------------
	public LdapSecurityGroup(String dn, String attributeName)
	{
		super(dn);
		
		if(attributeName == null)
			this.attributeNames = null;
		else
		{
			String startsWith = new StringBuilder(attributeName).append("=").toString();
			

			this.attributeNames = Text.toUpperCase(nyla.solutions.core.util.Text.parse(dn, startsWith, ","));
			
			
		}
	
			
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((attributeNames == null) ? 0 : attributeNames.hashCode());
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
		if (super.equals(obj))
			return true;
		
		if (!Group.class.isAssignableFrom(obj.getClass()))
			return false;
		Group other = (Group) obj;
		
		String otherGroupName = other.getName();
		if(otherGroupName == null)
			return false;
		
		if (attributeNames == null)
			return false;
		
		return attributeNames.contains(otherGroupName);
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("LdapSecurityGroup [attributeNames=")
				.append(attributeNames).append(", getName()=")
				.append(getName()).append("]");
		return builder.toString();
	}



	private final Collection<String> attributeNames;
}
