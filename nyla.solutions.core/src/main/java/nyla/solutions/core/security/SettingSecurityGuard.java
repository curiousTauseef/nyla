package nyla.solutions.core.security;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import nyla.solutions.core.security.data.Acl;
import nyla.solutions.core.security.data.SecurityAcl;
import nyla.solutions.core.util.Config;

public class SettingSecurityGuard
{
	public static final String PROP_KEY_PREFIX  = "security-acl-user-";
	
	/**
	 * @return the acl
	 */
	public Acl getAcl()
	{
		try
		{
			lock.lock();
		
			
			if(acl == null)
			{
				acl = new SecurityAcl();
				
				// security-acl-user-
				Config.getProperties().forEach( (k,v) -> 
				{ 
					
				}
					
				);
			}
			
			return null;
		}
		finally
		{
			lock.unlock();
		}
	}//------------------------------------------------

	public static SettingSecurityGuard getInstance()
	{
		return instance;
	}
	private static SettingSecurityGuard instance = new SettingSecurityGuard();
	
	private SecurityAcl acl = null;
	private static Lock lock = new ReentrantLock(true);
	

}
