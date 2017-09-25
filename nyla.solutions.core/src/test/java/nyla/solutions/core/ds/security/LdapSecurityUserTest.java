package nyla.solutions.core.ds.security;

import static org.junit.Assert.*;
import nyla.solutions.core.security.data.SecurityUser;

import org.junit.Test;

public class LdapSecurityUserTest
{

	@Test
	public void testEqualsObject()
	{
		
		assertEquals(new LdapSecurityUser("user1","CN=abc,CN=user.com"), new LdapSecurityUser("user1"));
		assertEquals(new LdapSecurityUser("user1","CN=abc,CN=user.com"), new LdapSecurityUser("USER1"));
	
		assertEquals(new LdapSecurityUser("user1","CN=abc,CN=user.com"), new LdapSecurityUser(" USER1"));
		assertEquals(new LdapSecurityUser("user1","CN=abc,CN=user.com"), new LdapSecurityUser("USER1 "));

		assertEquals(new LdapSecurityUser("user1","CN=abc,CN=user.com"), new LdapSecurityUser(" USER1 "));

		assertNotEquals(new LdapSecurityUser("user1","CN=abc,CN=user.com"), new SecurityUser("aBc "));
	
		assertNotEquals(new LdapSecurityUser("CN=abc,CN=user.com"), new SecurityUser("com"));

		assertEquals(new LdapSecurityUser("user1","CN=abc,CN=user.com"), new LdapSecurityUser("unknown","CN=abc,CN=user.com"));
		
		assertNotEquals(new LdapSecurityUser("CN=abc,CN=user.com"), new LdapSecurityUser("CN=xyz,CN=xyz.com"));
	}

}
