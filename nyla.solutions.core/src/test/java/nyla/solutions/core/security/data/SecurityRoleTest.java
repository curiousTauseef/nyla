package nyla.solutions.core.security.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class SecurityRoleTest
{

	@Test
	public void testCheckPermission()
	{
		SecurityRole role = new SecurityRole("role");
		
		assertFalse(role.checkPermission(null));
		
		assertFalse(role.checkPermission(new SecurityPermission("")));
		role.addPermission(new SecurityPermission(""));
		assertTrue(role.checkPermission(new SecurityPermission("")));
		
	}

}
