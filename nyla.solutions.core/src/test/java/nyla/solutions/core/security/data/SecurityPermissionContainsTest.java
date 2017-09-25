package nyla.solutions.core.security.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class SecurityPermissionContainsTest
{

	@Test
	public void test()
	{
		//assertEquals( new SecurityPermissionContains("GREEN"), new SecurityPermission("GREEN"));
		//assertEquals( new SecurityPermissionContains("GREEN"), new SecurityPermissionContains("GREEN"));
		assertEquals(new SecurityPermissionContains("NYLA"),"NYLA GREEN");
		assertNotEquals(new SecurityPermissionContains("GREG"),"NYLA GREEN");
		
	}

}
