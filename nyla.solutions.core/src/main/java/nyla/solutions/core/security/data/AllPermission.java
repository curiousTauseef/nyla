package nyla.solutions.core.security.data;

import java.security.acl.Permission;

/**
 * @author Gregory Green
 *
 */
public class AllPermission implements Permission
{

	@Override
	public String toString()
	{
		return "AllPermission";
	}//------------------------------------------------
	
	@Override
	public boolean equals(Object obj)
	{
		return true;
	}

}
