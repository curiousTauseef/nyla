package nyla.solutions.core.security.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegExpPermissionTest
{

	@Test
	public void testRegExpPermissionIsAuthorized()
	{
		assertTrue(new RegExpPermission(".*").isAuthorized(new SecurityPermission("ALL")));
		assertTrue(new RegExpPermission(".*").isAuthorized(new SecurityPermission("")));
		
		assertFalse(new RegExpPermission("NY.*").isAuthorized(new SecurityPermission("")));
		assertFalse(new RegExpPermission(".*").isAuthorized(new SecurityPermission(null)));
		assertFalse(new RegExpPermission("NY NJ NC.*").isAuthorized(new SecurityPermission("NY NJ")));
	}

}
