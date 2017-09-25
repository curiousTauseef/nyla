package nyla.solutions.core.security.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class AllPermissionTest
{

	@Test
	public void test()
	{
		assertEquals(new AllPermission(), new SecurityPermission("TEST"));
		assertEquals(new AllPermission(), new SecurityPermissionContains("TEST"));
		assertEquals(new AllPermission(),"ALL");
		assertEquals(new AllPermission(),new AllPermission());
		
		assertNotEquals(new SecurityPermissionContains("All"), new AllPermission());

	}

}
