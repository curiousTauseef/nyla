package nyla.solutions.blueprint.jee.web.security;

public interface Authentication
{

	/**
	    * Authenticates the user against the Merck Windows NT Authentication server
	    *
	    * @param domain    Windows domain of the user
	    * @param username      Merck IT identifier for the user
	    * @param password  Merck user's windows password
	    * @throws HELNException
	    * @return success for logging the user in
	    */
	   public boolean authenticate(String domain, String username, char[] password)
	   throws SecurityException;
}
