package nyla.solutions.core.security.data;

import java.security.acl.Permission;

public class RegExpPermission implements Permission
{
	

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
