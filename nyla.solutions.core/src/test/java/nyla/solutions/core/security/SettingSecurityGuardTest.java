package nyla.solutions.core.security;

import static org.junit.Assert.*;

import org.junit.Test;

import nyla.solutions.core.security.data.Acl;

public class SettingSecurityGuardTest
{

	@Test
	public void testGetAcl()
	{
		SettingSecurityGuard  guard = SettingSecurityGuard.getInstance();
		
		Acl acl = guard.getAcl();
		
		assertNotNull(acl);
		
		assertNotNull(acl.getName());
		
	}

}
