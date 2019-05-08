package nyla.solutions.core.security.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class SecurityPermissionTest
{

	@Test
	public void testEquals()
	{
		assertEquals(new SecurityPermission(""),new SecurityPermission(""));
		
		assertNotEquals(new SecurityPermission(""),new SecurityPermission("not"));
		

		assertEquals(new SecurityPermission(null),new SecurityPermission(null));
		assertNotEquals(new SecurityPermission("p"),new SecurityPermission(null));
	}
	

}
