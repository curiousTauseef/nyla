package nyla.solutions.blueprint.jee.web.security;

import javax.servlet.http.HttpSession;

import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;

/**
 * 
 * @author Gregory Green
 *
 */
public class ConfigSecurityGuard implements SecurityGuard
{
	/**
	 * authorize the session
	 */
	public boolean authorized(HttpSession session)
	{
		if(session == null)
			return false;
		
		String username = (String)session.getAttribute(usernameSessionKey);	
		
		Debugger.println(this,"authorizing username:"+username);
		
		if(username==null)
			throw new SecurityException("user not found in session");
		
		//access denied
		if(username.length() == 0 || !allowsUsers.contains(username))
		{
			return false;
		}		
		return true;
	}//---------------------------------------------
	private String allowsUsers = Config.getProperty(getClass(),"allowedUsers");
	private String usernameSessionKey = Config.getProperty(getClass(),"userNameSessionKey","username");
}
