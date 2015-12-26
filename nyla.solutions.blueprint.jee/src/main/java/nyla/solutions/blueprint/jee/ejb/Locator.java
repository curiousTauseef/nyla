package nyla.solutions.blueprint.jee.ejb;

import java.util.Hashtable;

import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import nyla.solutions.global.data.Data;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;


/**
 *
 * <pre>
 *
There is a framework for locating EJB's.
The class is Locator.

Please extend this class in order to create a custom service locator.
Locators have the responsibility of performing JNDI lookups of EJB remote home and or local EJB interfaces.


Please use the following naming convention for your EJB components.


XXX.ejb.session.XXXBeanHomeRemote.java - home remote interface
ejb.session.XXXBeanHomeLocal.java  - home local interface


XXX.ejb.session.XXXBeanRemote.java - EJB remote interface
XXX.ejb.session.XXXBeanLocal.java  - home local interface


com.bms.informatics.gcsm.XXX.XXXBean - EJB implementation


Note that service locators are referred to as "Lookup Service" in the GCSM
development standards document written by John (see section 2.2.3 Business Delegate).

The following is sample code of a Locator for the UserBean


package com.bms.informatics.gcsm.security.user;
import com.bms.informatics.gcsm.security.user.ejb.session.*;
import com.bms.informatics.gcsm.common.ejb.*;
import com.bms.informatics.gcsm.common.exception.*;
public class UserLocator extends Locator
{
   public UserLocator()
   throws Exception
   {
      super("UserBeanRemote", UserBeanHomeRemote.class);
   }//--------------------------------------------
   public static UserBeanRemote getUserBeanRemote()
   {
      try
      {
         return ((UserBeanHome)new UserLocator().locateRemoteHome()).create();
      }
      catch (Exception e)
      {
         throw new SystemException(e);
      }

   }//--------------------------------------------
}
 * </pre>
 * @author Gregory Green
 * @version 1.0
 */
public abstract class Locator
{
	/**
	 * 
	 * @param aJNDI the Java NAMING location
	 * @param aHomeClass the home class
	 * @throws Exception
	 */
    public Locator(String aJNDI, Class<?> aHomeClass)
     throws Exception
    {

        jndi = "";

        homeClass = null;

        if(Data.isNull(aJNDI))
            throw new IllegalArgumentException("JNDI not provided");

        
        if(aHomeClass == null)
        {
            throw new IllegalArgumentException("Home class not provided");

        } 
        else
        {

            jndi = aJNDI;

            homeClass = aHomeClass;

            the_context = getContext();

            return;

        }

    }// --------------------------------------------------------
    /**
     * 
     * @return the EJB local home object
     * @throws Exception
     */
    protected EJBLocalHome locateLocalHome()
    throws Exception
    {

        InitialContext context = getContext();

        Object homeObject = context.lookup(jndi);

        return (EJBLocalHome)homeObject;

    }// --------------------------------------------------------
    /**
     * 
     * @return the EJB local home object
     * @throws Exception
     */
    protected EJBHome locateRemoteHome()
        throws Exception
    {

        InitialContext context = getContext();

        Object ref = context.lookup(jndi);

        return (EJBHome)PortableRemoteObject.narrow(ref, homeClass);

    }// --------------------------------------------------------
    /**
     * 
     * @return the initial JNDI context
     * @throws Exception
     */
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
				Debugger.printInfo("No config parameter set for EJBJNDIProviderURL. Assuming inside container.");
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

    }// --------------------------------------------------------
    /**
     * 
     * @param providerUrl  the provide URL
     * @param principal the user
     * @param credential password
     * @return
     * @throws Exception
     */
	public static InitialContext getContextWithPrincipal(String providerUrl, String principal, String credential)
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
		
	}// --------------------------------------------------------
	/**
	 * 
	 * @param url the EJB location
	 * @return true if the URL is valid
	 * @throws Exception
	 */
	private static boolean validateURL(String url) throws Exception
	{
		if((url.startsWith("${") && url.endsWith("}")) || (url.trim().equals(""))){
			return false;
		}
		return true;
	}// --------------------------------------------------------

    private static InitialContext the_context = null;
    private String jndi;
    private Class<?> homeClass;
}

