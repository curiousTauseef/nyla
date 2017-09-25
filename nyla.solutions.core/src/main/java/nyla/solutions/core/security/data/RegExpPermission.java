package nyla.solutions.core.security.data;

import java.io.Serializable;
import java.security.acl.Permission;

/**
 * Permission based on matching  based on regular expressions against the permission toString
 * @author Gregory Green
 *
 */
public class RegExpPermission implements Permission, Serializable
{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2130916801947682213L;

	public RegExpPermission(String regExp)
	{
		if (regExp == null || regExp.length() == 0)
			throw new IllegalArgumentException("regExp is required");
		this.regExp = regExp;
	}
	
	@Override
	public String toString()
	{
		return this.regExp;
	}
	
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null)
			return false;
		
		String text = obj.toString();
		
		return text.matches(regExp);
	}//------------------------------------------------

	private final String regExp;
}
