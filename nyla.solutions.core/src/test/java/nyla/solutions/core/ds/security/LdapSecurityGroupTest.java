package nyla.solutions.core.ds.security;

import static org.junit.Assert.*;
import nyla.solutions.core.security.data.SecurityGroup;
import nyla.solutions.core.util.Text;

import org.junit.BeforeClass;
import org.junit.Test;

public class LdapSecurityGroupTest
{

	@BeforeClass
	public static void setUP()
	{
		Text.toStrings(""); //load Text object
	}
	@Test
	public void testEqualsObject()
	{
		
		assertEquals(new LdapSecurityGroup("CN=abc,CN=user.com"), new LdapSecurityGroup("abc"));
		
		assertEquals(new LdapSecurityGroup("CN=abc,CN=user.com"), new LdapSecurityGroup("ABC"));
		
		assertEquals(new LdapSecurityGroup("CN=abc,CN=user.com"), new SecurityGroup("abc"));
		assertEquals(new LdapSecurityGroup("CN=abc,CN=user.com"), new SecurityGroup("abC "));
		
		
		assertNotEquals(new LdapSecurityGroup("CN=abc,CN=user.com"), new SecurityGroup("com"));
		assertEquals(new LdapSecurityGroup("CN=abc,CN=user.com"), new LdapSecurityGroup("CN=abc,CN=user.com"));
		
		assertNotEquals(new LdapSecurityGroup("CN=abc,CN=user.com"), new LdapSecurityGroup("CN=xyz,CN=xyz.com"));
	}

}
