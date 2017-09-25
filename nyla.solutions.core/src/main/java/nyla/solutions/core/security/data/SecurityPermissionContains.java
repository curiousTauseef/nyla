package nyla.solutions.core.security.data;

import java.io.Serializable;
import java.security.acl.Permission;

public class SecurityPermissionContains implements Permission, Serializable
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7042157206407846236L;



	public SecurityPermissionContains(String text)
	{
		if (text == null || text.length() == 0)
			throw new IllegalArgumentException("text is required");
		
		this.text = text;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		
		String string = obj.toString();
		if(string == null || string.length() == 0)
			return false;
		
		return string.contains(this.text);
	}


	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return text;
	}



	private final String text;

}
