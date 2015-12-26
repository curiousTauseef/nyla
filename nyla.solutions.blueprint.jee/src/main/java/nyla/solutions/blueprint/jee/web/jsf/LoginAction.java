package nyla.solutions.blueprint.jee.web.jsf;


import nyla.solutions.blueprint.jee.web.JSF;
import nyla.solutions.blueprint.jee.web.security.Authentication;
import nyla.solutions.blueprint.jee.web.security.SecurityGuard;
import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.patterns.servicefactory.ServiceFactory;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;


/**
 * @author Gregory Green
 * 
 */
public class LoginAction
{
	/**
	 * 
	 * @return true or false if user is authenticated
	 */
	public String login()
	{
		if(this.password == null)
			throw new RequiredException("password");
		Debugger.println("username="+username);
		
		try 
		{
			Authentication authenticator = (Authentication)ServiceFactory.getInstance().create(Authentication.class);
			
			boolean authenticated = authenticator.authenticate(this.domain, this.username, this.password.toCharArray());
			
			if(authenticated)
			{
				JSF.getSessionMap().put(this.usernameSessionKey, this.username);
				
				//redirect
				//HttpServletRequest request = JSF.getRequest();
				//JSF.getResponse().sendRedirect(request.getContextPath()+redirectPage);
				//return null;
			}
			
			//get user name
			SecurityGuard guard = (SecurityGuard)ServiceFactory.getInstance().create(SecurityGuard.class);		
			if(!guard.authorized(JSF.getRequest().getSession()))
			{				
				return "403"; //not authorized
			}
			
			return new Boolean(authenticated).toString();
		} 
		catch (SecurityException e) 
		{
			Debugger.printWarn(e);
			return Boolean.FALSE.toString();
		}		
	}//---------------------------------------------
	
	/**
	 * @return the username
	 */
	public String getUsername()
	{
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username)
	{
		
		this.username = username;
	}//---------------------------------------------
	
	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}//---------------------------------------------
	//private String redirectPage = Config.getProperty(getClass(),"redirectPage","/injection/main.jsf");
	private String usernameSessionKey = Config.getProperty(getClass(),"userNameSessionKey","username");
	private String domain = Config.getProperty(getClass(),"domain","NORTHAMERICA");
	private String password = null;
	private String username = null;
	
}