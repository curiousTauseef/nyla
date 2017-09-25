package nyla.solutions.core.security.data;

import static org.junit.Assert.*;

import java.security.Principal;
import java.util.Collections;

import org.junit.Test;

public class SecurityAclTest
{

	@Test
	public void testCheckPermissionPrincipalString()
	throws Exception
	{
		 Principal caller = null;
	    Principal principal = null;
	    
		SecurityAcl securityAcl = new SecurityAcl();
		assertFalse(securityAcl.checkPermission(principal, "CLUSTER"));
		
		principal = new SecurityClient("greenID");
		caller = new SecurityClient("admin");
		securityAcl.addEntry(caller,principal, "CLUSTER");
		assertTrue(securityAcl.checkPermission(principal, "CLUSTER"));
		
		assertFalse(securityAcl.checkPermission(new SecurityGroup(principal.getName()), "CLUSTER"));
		
	}
	
	@Test
	public void testCheckPermissionGroupString()
	throws Exception
	{
		 Principal caller = null;
	    Principal principal = null;
	    
		SecurityAcl securityAcl = new SecurityAcl();
		
		principal = new SecurityGroup("group");
		caller = new SecurityClient("admin");
		securityAcl.addEntry(caller,principal, "CLUSTER");
		assertTrue(securityAcl.checkPermission(principal, "CLUSTER"));
		
		assertFalse(securityAcl.checkPermission(new SecurityClient(principal.getName()), "CLUSTER"));
		
	}
	@Test
	public void testCheckPermissionSecurityUser()
	throws Exception
	{
		 Principal caller = null;
	    Principal principal = null;
	    
		SecurityAcl securityAcl = new SecurityAcl();
		
		SecurityGroup group = new SecurityGroup("group");
		principal = new SecurityUser("user",Collections.singleton(group));
		
		caller = new SecurityClient("admin");
		assertFalse(securityAcl.checkPermission(principal, "CLUSTER"));
		
		securityAcl.addEntry(caller,group, "CLUSTER");
		
		assertTrue(securityAcl.checkPermission(principal, "CLUSTER"));
		securityAcl.addEntry(caller,group, true,"CLUSTER");
		assertFalse(securityAcl.checkPermission(group, "CLUSTER"));
		
		securityAcl.addEntry(caller,principal, "CLUSTER");
		assertTrue(securityAcl.checkPermission(principal, "CLUSTER"));

		
	}

	@Test
	public void testNegativeCheckPermissionGroupString()
	throws Exception
	{
		 Principal caller = null;
	    Principal principal = null;
	    
		SecurityAcl securityAcl = new SecurityAcl();
		
		principal = new SecurityGroup("group");
		caller = new SecurityClient("admin");
		securityAcl.addEntry(caller,principal, false,"CLUSTER");
		assertTrue(securityAcl.checkPermission(principal, "CLUSTER"));
		
		securityAcl.addEntry(caller,principal, true,"CLUSTER");
		assertFalse(securityAcl.checkPermission(principal, "CLUSTER"));	
	}
}
