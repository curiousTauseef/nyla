package nyla.solutions.blueprint.jee.web.security;

import javax.servlet.http.HttpSession;

public interface SecurityGuard
{
	/**
	 * Authorize access by session
	 * @param session the HTTP sesison
	 * @return true is session is authorized
	 * @throws SecurityException
	 */
	boolean authorized(HttpSession session)
	throws SecurityException;
	
}
