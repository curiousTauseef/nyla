package nyla.solutions.global.patterns.servicefactory;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import nyla.solutions.core.operations.logging.Log;
import nyla.solutions.core.util.Config;
import nyla.solutions.core.util.Debugger;




/**
 *
 * <pre>
 *
	There is a framework for locating EJB's.
	
	
	Note that service locators are referred to as "Lookup Service" in the GCSM
	development standards document written by John (see section 2.2.3 Business Delegate).
	
	The following is sample code of a Locator for the UserBean
	
 * </pre>
 * @author Gregory Green
 * @version 1.0
 */
public abstract class Locator

{








   

    public static synchronized InitialContext getContext()

        throws Exception

    {

        if(the_context == null)

        {

            Hashtable<String,String> ht = new Hashtable<String,String>();

            String factory = Config.getProperty("EJBJNDIInitialContextFactory");

			String url = null;
			try {
            	url = Config.getProperty("EJBJNDIProviderURL");
			}catch (Exception e) {
				logger.info("No config parameter set for EJBJNDIProviderURL. Assuming inside container.");
			}
			if(url != null && validateURL(url)){
	            Debugger.println("url=" + url);
	            ht.put("java.naming.provider.url", url);
			}
            Debugger.println("factory=" + factory);

            ht.put("java.naming.factory.initial", factory);

            the_context = new InitialContext(ht);

        }

        return the_context;

    }

	public static synchronized InitialContext getContextWithPrincipal(String providerUrl, String principal, String credential)
	throws Exception
	{
		
		if(the_context == null)
			
		{
			
			Hashtable<String,String> ht = new Hashtable<String,String>();
			
			String factory = Config.getProperty("EJBJNDIInitialContextFactory");
			
			Debugger.println("url=" + providerUrl);
			ht.put("java.naming.provider.url", providerUrl);

			Debugger.println("factory=" + factory);
			
			ht.put("java.naming.factory.initial", factory);
			
			ht.put(Context.SECURITY_CREDENTIALS, credential);
			ht.put(Context.SECURITY_PRINCIPAL, principal);
			
			the_context = new InitialContext(ht);
			
		}
		
		return the_context;
		
	}

	private static boolean validateURL(String url) throws Exception{
		if((url.startsWith("${") && url.endsWith("}")) || (url.trim().equals(""))){
			return false;
		}
		return true;
	}

    private static InitialContext the_context = null;


	private static Log logger = Debugger.getLog(Locator.class);

}


