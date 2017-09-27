package nyla.solutions.core.security.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class SecurityPermissionContainsTest
{

	@Test
	public void testIsAuthorized()
	{
		//assertEquals( new SecurityPermissionContains("GREEN"), new SecurityPermission("GREEN"));
		//assertEquals( new SecurityPermissionContains("GREEN"), new SecurityPermissionContains("GREEN"));
		assertTrue(new SecurityPermissionContains("NYLA").isAuthorized(new SecurityPermission("NYLA GREEN")));
		assertFalse(new SecurityPermissionContains("GREG").isAuthorized(new SecurityPermission("NYLA GREEN")));
		
	}

}
