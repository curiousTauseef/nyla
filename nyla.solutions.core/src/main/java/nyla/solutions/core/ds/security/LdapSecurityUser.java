package nyla.solutions.core.ds.security;

import nyla.solutions.core.security.data.SecurityUser;


public class LdapSecurityUser extends SecurityUser
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1090403112093383311L;


	public LdapSecurityUser(String uid)
	{
		this(uid,null);
	}// --------------------------------------------------------------
	public LdapSecurityUser(String uid,String dn)
	{
		super(uid);
		
		this.dn = dn;
			
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("LdapSecurityUser [dn=").append(dn)
				.append(", getName()=").append(getName())
				.append(", getGroups()=").append(getGroups()).append("]");
		return builder.toString();
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
		if (getClass() != obj.getClass())
			return false;
		LdapSecurityUser other = (LdapSecurityUser) obj;
		if (dn == null || other.dn == null)
		{
				return false;
		}

		return dn.equals(other.dn);
			
	}


	private final String dn;
}
