package nyla.solutions.core.security.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class AllPermissionTest
{

	@Test
	public void testIsAuthorized()
	{
		assertTrue(new AllPermission().isAuthorized(new SecurityPermission("TEST")));
		assertTrue(new AllPermission().isAuthorized( new SecurityPermissionContains("TEST")));
		assertTrue(new AllPermission().isAuthorized(new SecurityPermission("ALL")));
		assertTrue(new AllPermission().isAuthorized(new AllPermission()));
		
		assertFalse(new SecurityPermissionContains("All").isAuthorized(new AllPermission()));

	}

}
