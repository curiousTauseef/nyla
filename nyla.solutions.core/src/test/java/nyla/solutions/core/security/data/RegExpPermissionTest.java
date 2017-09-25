package nyla.solutions.core.security.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegExpPermissionTest
{

	@Test
	public void testRegExpPermissionEquals()
	{
		assertEquals(new RegExpPermission(".*"), "ALL");
		assertEquals(new RegExpPermission(".*"), "");
		
		assertNotEquals(new RegExpPermission("NY.*"), "");
		assertNotEquals(new RegExpPermission(".*"), null);
		assertEquals(new RegExpPermission("NY.*"), "NY NJ");
	}

}
