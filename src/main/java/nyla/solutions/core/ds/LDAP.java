package nyla.solutions.core.ds;

import java.io.Closeable;
import java.security.Principal;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.CompositeName;
import javax.naming.CompoundName;
import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import nyla.solutions.core.ds.security.LdapSecurityGroup;
import nyla.solutions.core.ds.security.LdapSecurityUser;
import nyla.solutions.core.exception.NoDataFoundException;
import nyla.solutions.core.util.Config;
import nyla.solutions.core.util.Debugger;


/**
 * 
 * <pre>
 * 
 * 
 *    LDAP provides a set of functions related to interfacing with directory servers
 * 
 *   
 *    The following is list of common LDAP attributes
 * 
 *   
 *    c = country
 *    o =  An organization or corporation
 *    ou - A division of an organization
 *    cn - common name of an entity ( often a user wher is can be first name
 *    or full name
 *    sn The surname (last name) of user
 *    uid - user unique ID
 *    
 *    Sample config.properties
 *    LDAP_SERVER_URL=@LDAP_SERVER_URL@ 9
 *    LDAP_ROOT_DN=ou=people, o=some.com
 *    LDAP_TIMEOUT_SECS=180
 *    
 *    #LDAP_SERVER_URL=ldap://localhost:389
 *    
 *  
 * </pre>
 * 
 * 
 * 
 * 
 * @version 1.0
 *  
 */

public class LDAP implements Closeable
{
	   public static final String JAVA_NAMING_PROVIDER_URL = "java.naming.provider.url";
	   private static final boolean trace = Config.getPropertyBoolean("LDAP_TRACE", false);

	   /**
	    * SERVER_URL_PROP = "LDAP_SERVER_URL"
	    * 
	    */
	   public final static String  SERVER_URL_PROP = "LDAP_SERVER_URL";
	   
	   /**
	    * TIMEOUT_SECS_PROP = "LDAP_TIMEOUT_SECS"
	    */
	   public final static String TIMEOUT_SECS_PROP = "LDAP_TIMEOUT_SECS";
	   
	   /**
	    * ROOT_DN_PROP = "LDAP_ROOT_DN"
	    */
	   public final static  String ROOT_DN_PROP = "LDAP_ROOT_DN";
	   
	   
	   public final static String UID_ATTRIB_NM_PROP = "LDAP_UID_ATTRIB_NM";
	   
	   /**
	    * GROUP_ATTRIB_NM_PROP = "LDAP_GROUP_ATTRIB_NM"
	    * 
	    * Example export LDAP_MEMBEROF_ATTRIB_NM=CN
	    */
	   public final static String GROUP_ATTRIB_NM_PROP = "LDAP_GROUP_ATTRIB_NM";
	   
	   /**
	    * MEMBEROF_ATTRIB_NM_PROP = "LDAP_MEMBEROF_ATTRIB_NM"
	    * 
	    * Example export LDAP_MEMBEROF_ATTRIB_NM=memberOf
	    */
	   public final static String MEMBEROF_ATTRIB_NM_PROP = "LDAP_MEMBEROF_ATTRIB_NM";
	   
	   
	   /**
	    * 
	    */
	   public final static String LDAP_USE_SSL_CONFIG_FACTORY_PROP = "LDAP_USE_SSL_CONFIG_FACTORY";
	   
	   /**
	    * DEFAULT_uidAttributeName = Config.getProperty(UID_ATTRIB_NM_PROP,"uid")
	    */
	   public final static String DEFAULT_uidAttributeName = Config.getProperty(UID_ATTRIB_NM_PROP,"uid");
	 
	   	   
   /**
    * 
    * Constructor for LDAP initializes internal 
    * data settings.
    * @param c the directory context
    * @param url the LDAP URL
    */
   public LDAP(DirContext c, String url)
   {


      existanceConstraints = new SearchControls();

      existanceConstraints.setSearchScope(0);

      existanceConstraints.setCountLimit(0L);

      existanceConstraints.setTimeLimit(0);

      existanceConstraints.setReturningAttributes(new String[] {

         "1.1" });

      ctx = c;
      
      this.url = url;
      
   }//--------------------------------------------

  
 
   /**
    * Initial LDAP for kerberos support
    * @param env
    * @throws NamingException
    */
   @SuppressWarnings({ "rawtypes", "unchecked" })
   protected DirContext setupKerberosContext(Hashtable<String,Object> env) 
   throws NamingException
   {

      LoginContext lc = null;
      try
      {

    	 lc = new LoginContext(getClass().getName(), new JXCallbackHandler());
         lc.login();

      }
      catch (LoginException ex)
      {
         throw new NamingException("login problem: " + ex);
      }

      DirContext ctx = (DirContext) Subject.doAs(lc.getSubject(), new JndiAction(env));

      if (ctx == null)
         throw new NamingException("another problem with GSSAPI");
      else
         return ctx;
    
   }//------------------------------------------------------------------
   /**
    * 
    * Constructor for LDAP initializes internal 
    * data settings.
    * @param url the LDAP server url i.e. ldap://directory.xxx.com
    * @throws NamingException
    */
   public LDAP(String url) throws NamingException
   {
	   if (url == null)
		throw new IllegalArgumentException("url is required");
	   
	   this.url = url;
	   
      existanceConstraints = new SearchControls();

      existanceConstraints.setSearchScope(0);

      existanceConstraints.setCountLimit(0L);

      existanceConstraints.setTimeLimit(0);

      existanceConstraints.setReturningAttributes(new String[] {

         "1.1" });

      Hashtable <String,Object> env = new Hashtable<String,Object> ();

      setupBasicProperties(env, url);

      ctx = openContext(env);

   }//--------------------------------------------

   /**
    * 
    * 
    * 
    * Example URL ldap://localhost
    * 
    * @param url
    * 
    * @param userDN
    *           example "cn=Manager,dc=green_gregory,dc=com"
    * 
    * @param pwd the user password
    * 
    * @throws NamingException
    *  
    */
   public LDAP(String url, String userDN, char pwd[]) throws NamingException
   {

      existanceConstraints = new SearchControls();

      existanceConstraints.setSearchScope(0);

      existanceConstraints.setCountLimit(0L);

      existanceConstraints.setTimeLimit(0);

      existanceConstraints.setReturningAttributes(new String[] {

         "1.1" });

      this.url = url;
      this.ctx = authenticateByDnForContext( userDN, pwd);

   }//--------------------------------------------
   public DirContext authenticateByDnForContext(String userDN, char[] pwd)
		throws NamingException
	{
		Hashtable<String,Object>  env = new Hashtable<String,Object> ();
	
	      setupBasicProperties(env, url);
	
	      setupSecurityProperties(env, userDN, pwd);
	
	      return openContext(env);
	}// --------------------------------------------------------------
   
   /**
    * Authenticate user ID and password against the LDAP server
    * @param uid i.e. greeng
    * @param password the user password
	 * @return the user principal details
	 * @throws SecurityException when security error occurs
    */
   public Principal authenicate(String uid, char[] password)
   throws  SecurityException
   {

      String rootDN = Config.getProperty(ROOT_DN_PROP);
      
      int timeout = Config.getPropertyInteger(TIMEOUT_SECS_PROP).intValue();
      Debugger.println(LDAP.class,"timeout="+timeout);
      
      String uidAttributeName = Config.getProperty(UID_ATTRIB_NM_PROP);
      String groupAttributeName = Config.getProperty(GROUP_ATTRIB_NM_PROP,"");
      String memberOfAttributeName = Config.getProperty(MEMBEROF_ATTRIB_NM_PROP,"");
      
      return authenicate(uid,  password,rootDN,uidAttributeName,memberOfAttributeName,groupAttributeName,timeout);
   }// --------------------------------------------------------------
   /**
    * 
    * @param uid ex: ggreen
    * @param password the password (unencrypted)
    * @param rootDN ex: ou
    * @param uidAttributeName (ex: uid)
	 * @param memberOfAttributeName the member attribute (ex: memberOf)
	 * @param groupNameAttributeName the group atribute name (ex: CN)
    * @param timeout the timeout
    * @return principal
    * @throws SecurityException
    */
    public Principal authenicate(String uid, char[] password,String rootDN,String uidAttributeName,String memberOfAttributeName,String groupNameAttributeName, int timeout)
    throws  SecurityException
    {
         if(uidAttributeName == null || uidAttributeName.length() == 00)
        	 {
        	 	uidAttributeName = DEFAULT_uidAttributeName;
        	 }
         
         try
         {       
            String uidSearch = new StringBuilder().append("(").append(uidAttributeName).append("=").append(uid).append(")").toString();
                 
            NamingEnumeration<?> results = searchSubTree(rootDN,
            		uidSearch,1,timeout,null);
            SearchResult searchResult = toSearchResult(results);
            String userDN = searchResult.getName()+", "+rootDN;
            
            
          //validate pass
            
            DirContext ctx = null;
            
            try
            { 
            		ctx = this.authenticateByDnForContext( userDN, password);
            		
            		
            		LdapSecurityUser securityUser = new LdapSecurityUser(uid,userDN);
            		Object groupEnumValue = null;
            		
            		if(memberOfAttributeName != null && memberOfAttributeName.length() > 0)
            		{
                		Attributes attributes = ctx.getAttributes(userDN); 
                		if(attributes != null)
                		{
                      		Attribute attribute = attributes.get(memberOfAttributeName);

                      		if(attribute != null )
                      		{
                          		NamingEnumeration<?> groupsEnumeration = attribute.getAll();
                          		if(groupsEnumeration  != null)
                          		{
                              		while(groupsEnumeration.hasMore())
                              		{
                              			groupEnumValue = groupsEnumeration.next();
                              			if(groupEnumValue == null)
                              				continue;
                              			
                              	
                              			securityUser.addGroup(new LdapSecurityGroup(groupEnumValue.toString(),groupNameAttributeName));
                              		}
                          			
                          		}
                      			
                      		}
                      		
                      	                			
                		}
                		
            		}
            		
            		return securityUser;
            		
            }
            finally
            {
            		if(ctx != null)
            			ctx.close();
            }	
               
            //
            //return new X500Principal(userDN);
            
       
         }
		catch (NamingException e)
		{
			throw new SecurityException(e.getMessage(),e);
		}
		catch (NoDataFoundException e)
		{
			throw new SecurityException(uidAttributeName+":\""+uid+"\" not found");
		}
   }//--------------------------------------------

   /**
    * 
    * @param env the properties
	 * @param url  the URL
	 * @throws NamingException when naming error occurs
    *  
    */
   public static void setupBasicProperties(Hashtable<String,Object>  env, String url) throws NamingException

   {

      if (url == null)

         throw new NamingException("URL not specified in openContext()!");

      if (trace)
         env.put("com.sun.jndi.ldap.trace.ber", System.err);

      env.put("java.naming.ldap.version", "3");

      if (env.get("java.naming.factory.initial") == null)

         env.put("java.naming.factory.initial",

         "com.sun.jndi.ldap.LdapCtxFactory");

      env.put("java.naming.ldap.deleteRDN", "false");

      env.put("java.naming.referral", "follow");

      env

      .put("java.naming.ldap.attributes.binary",

      "photo jpegphoto jpegPhoto");

      env.put("java.naming.ldap.derefAliases", "finding");

      env.put("java.naming.security.authentication", "none");

      env.put(JAVA_NAMING_PROVIDER_URL, url);

   }//--------------------------------------------

   public static void setupSecurityProperties(Hashtable<String,Object>  env,
   String userDN, char pwd[])

   {
	        
      if(userDN != null)
      {
          if (pwd == null)
              pwd = new char[0];

          env.put("java.naming.security.authentication", "simple");

          env.put("java.naming.security.principal", userDN);

          env.put("java.naming.security.credentials", new String(pwd));
    	  
      }
      
      boolean useSslConfigFactory = Config.getPropertyBoolean(LDAP_USE_SSL_CONFIG_FACTORY_PROP,false).booleanValue();
      
      String url = (String)env.get(LDAP.JAVA_NAMING_PROVIDER_URL);
      if(url != null && url.trim().toLowerCase().startsWith("ldaps")
         && useSslConfigFactory)
      {
    	  	env.put("java.naming.security.protocol","ssl");
    	  	env.put("java.naming.ldap.factory.socket", SSLConfigSocketFactory.class.getName());
      }

   }//------------------------------------------------
   
   public static DirContext openContext(Hashtable<?,?> env) throws NamingException
   {
      DirContext ctx = new InitialDirContext(env);
      
      return ctx;
   }// ------------------------------------------------

   public void renameEntry(Name oldDN, Name newDN) throws NamingException
   {

      Name rdn = newDN.getSuffix(newDN.size() - 1);

      Name oldRdn = oldDN.getSuffix(oldDN.size() - 1);

      if (!oldRdn.toString().equals(rdn.toString()))

         ctx.rename(oldDN, rdn);

   }

   public void copyEntry(Name fromDN, Name toDN) throws NamingException
   {

      addEntry(toDN, read(fromDN));

   }

   public void addEntry(Name dn, Attributes atts) throws NamingException
   {

      ctx.createSubcontext(dn, atts);

   }

   public void deleteEntry(Name dn) throws NamingException

   {

      ctx.destroySubcontext(dn);

   }

   public boolean exists(Name nodeDN)

   throws NamingException

   {

      try

      {

         ctx.search(nodeDN, "(objectclass=*)", existanceConstraints);

         return true;

      }

      catch (NameNotFoundException e)

      {

         return false;

      }

      catch (NullPointerException e)

      {

         if (ctx != null
         && ctx.getEnvironment().get("java.naming.factory.initial").toString()
         .indexOf("dsml") > 0)

            return false;

         else

            throw e;

      }

   }//---------------------------------------------------

   public boolean exists(String nodeDN)

   throws NamingException

   {

      try

      {

         ctx.search(nodeDN, "(objectclass=*)", existanceConstraints);

         return true;

      }

      catch (NameNotFoundException e)

      {

         return false;

      }

      catch (NullPointerException e)

      {

         if (ctx != null
         && ctx.getEnvironment().get("java.naming.factory.initial").toString()
         .indexOf("dsml") > 0)

            return false;

         else

            throw e;

      }

   }//-------------------------------------------------------

   public synchronized Attributes read(Name dn) throws NamingException

   {

      return read(dn, null);

   }

   public synchronized Attributes read(Name dn, String returnAttributes[])

   throws NamingException

   {

      return ctx.getAttributes(dn, returnAttributes);

   }

   public void modifyAttributes(Name dn, int mod_type, Attributes attr)

   throws NamingException

   {

      ctx.modifyAttributes(dn, mod_type, attr);

   }

   public void modifyAttributes(Name dn, ModificationItem modList[])

   throws NamingException

   {

      ctx.modifyAttributes(dn, modList);

   }

   public void updateEntry(Name dn, Attributes atts) throws NamingException

   {

      modifyAttributes(dn, 2, atts);

   }

   public void deleteAttribute(Name dn, Attribute a) throws NamingException

   {

      BasicAttributes atts = new BasicAttributes();

      atts.put(a);

      modifyAttributes(dn, 3, atts);

   }

   public void deleteAttributes(Name dn, Attributes a) throws NamingException

   {

      modifyAttributes(dn, 3, a);

   }

   public void updateAttribute(Name dn, Attribute a) throws NamingException

   {

      BasicAttributes atts = new BasicAttributes();

      atts.put(a);

      modifyAttributes(dn, 2, atts);

   }

   public void updateAttributes(Name dn, Attributes a) throws NamingException

   {

      modifyAttributes(dn, 2, a);

   }

   public void addAttribute(Name dn, Attribute a) throws NamingException

   {

      BasicAttributes atts = new BasicAttributes();

      atts.put(a);

      modifyAttributes(dn, 1, atts);

   }

   public void addAttributes(Name dn, Attributes a) throws NamingException

   {

      modifyAttributes(dn, 1, a);

   }

   public NamingEnumeration<?> list(Name Searchbase) throws NamingException

   {

      return rawSearchOneLevel(Searchbase, "(objectclass=*)", 0, 0,

      new String[] {

         "1.1" });

   }

   public NamingEnumeration<?> searchOneLevel(String searchbase, String filter,

   int limit, int timeout) throws NamingException
   {

      return searchOneLevel(searchbase, filter, limit, timeout, new String[] {

         "1.1" });

   }

   public NamingEnumeration<?> searchOneLevel(String searchbase, String filter,

   int limit, int timeout, String returnAttributes[])

   throws NamingException

   {

      return rawSearchOneLevel(new CompositeName(searchbase), filter, limit,

      timeout, returnAttributes);

   }

   public NamingEnumeration<?> searchOneLevel(Name searchbase, String filter,

   int limit, int timeout) throws NamingException

   {

      return rawSearchOneLevel(searchbase, filter, limit, timeout,

      new String[] {

         "1.1" });

   }

   public NamingEnumeration<?> searchOneLevel(Name searchbase, String filter,
   int limit, int timeout, String returnAttributes[])
   throws NamingException
   {

      return rawSearchOneLevel(searchbase, filter, limit, timeout,

      returnAttributes);

   }//--------------------------------------------------------

   protected NamingEnumeration<?> rawSearchOneLevel(Name searchbase,

   String filter, int limit, int timeout, String returnAttributes[])

   throws NamingException

   {

     SearchControls constraints = new SearchControls();

      constraints.setSearchScope(1);

      constraints.setCountLimit(limit);

      constraints.setTimeLimit(timeout);

      constraints.setReturningAttributes(returnAttributes);
      
      NamingEnumeration<?> results = ctx.search(searchbase, filter, constraints);

      return results;

   }

   public NamingEnumeration<?> searchSubTree(Name searchbase, String filter,

   int limit, int timeout) throws NamingException

   {

      return searchSubTree(searchbase, filter, limit, timeout, new String[] {

         "1.1" });

   }//--------------------------------------------
   /**
    *    NamingEnumeration enum = 
    * ldap.searchOneLevel("dc=green_gregory,dc=com","(cn=*)",20,20);
    * @param searchbase dc=green_gregory,dc=com
    * @param filter (cn=*)
    * @param limit the count limit
    * @param timeout the timeout
    * @return the matches
    * @throws NamingException
    */
   public NamingEnumeration<?> searchSubTree(String searchbase, String filter,
   int limit, int timeout) throws NamingException
   {

      return searchSubTree(((Name) (new CompositeName(searchbase))), filter,

      limit, timeout, new String[] {

         "1.1" });

   }//--------------------------------------------
   /**
    *     *    NamingEnumeration enum = 
    * ldap.searchOneLevel("dc=green_gregory,dc=com","(cn=*)",20,20);
    * @param searchbase the DN base
    * @param filter the search filter
    * @param limit the limit
    * @param timeout the timeout
    * @param returnAttributes
    * @return matches
    * @throws NamingException
    */
   public NamingEnumeration<?> searchSubTree(String searchbase, String filter,

   int limit, int timeout, String returnAttributes[])

   throws NamingException

   {

      return rawSearchSubTree(new CompositeName(searchbase), filter, limit,

      timeout, returnAttributes);

   }

   public NamingEnumeration<?> searchSubTree(Name searchbase, String filter,

   int limit, int timeout, String returnAttributes[])

   throws NamingException

   {

      return rawSearchSubTree(searchbase, filter, limit, timeout,

      returnAttributes);

   }

   protected NamingEnumeration<?> rawSearchSubTree(Name searchbase, String filter,

   int limit, int timeout, String returnAttributes[])

   throws NamingException

   {

      if (returnAttributes != null && returnAttributes.length == 0)

         returnAttributes = (new String[] {

            "objectClass" });

      SearchControls constraints1 = new SearchControls();

      constraints1.setSearchScope(2);

      constraints1.setCountLimit(limit);

      constraints1.setTimeLimit(timeout);

      constraints1.setReturningAttributes(returnAttributes);

      SearchControls constraints = constraints1;

      return ctx.search(searchbase, filter, constraints);

   }

   public NamingEnumeration<?> searchBaseEntry(Name searchbase, String filter,
   int limit, int timeout) throws NamingException

   {

      return rawSearchBaseEntry(searchbase, filter, limit, timeout,

      new String[] {

         "objectClass" });

   }

   public NamingEnumeration<?> searchBaseEntry(Name searchbase, String filter,
   int limit, int timeout, String returnAttributes[])

   throws NamingException

   {

      return rawSearchBaseEntry(searchbase, filter, limit, timeout,

      returnAttributes);

   }

   //--------------------------------------------------------
   public static SearchResult toSearchResult(NamingEnumeration<?> aNamingEnumeration)
   throws NoDataFoundException
   {
      if(aNamingEnumeration == null || !aNamingEnumeration.hasMoreElements())
         throw new NoDataFoundException("no results "+aNamingEnumeration);
      
      return (SearchResult)aNamingEnumeration.nextElement();
      
   }//--------------------------------------------
   /**
    * 
    * @param aEnum the enum
    * @return a string version of the enumeration
    *  
    */
   public static String toString(NamingEnumeration<?> aEnum)
   {
      if (aEnum == null)
      {
         return "";
      }
      StringBuffer sb = new StringBuffer();
      SearchResult element = null;
      while (aEnum.hasMoreElements())
      {
         element = (SearchResult)aEnum.nextElement();
         sb.append(" name=").append(element.getName())
         .append(" attributes=").append(toString(element.getAttributes())).append("\n");

      }

      return sb.toString();

   }//------------------------------------------------------
   /**
    * 
    * @param aAttributes
    * @return the string of the attributes
    */
   public static String toString(Attributes aAttributes)
   {
      if(aAttributes == null|| aAttributes.getAll() == null)
         return "";
      Attribute element = null;
      NamingEnumeration<?> nenum = aAttributes.getAll();
      StringBuffer text = new StringBuffer();      
      while( nenum.hasMoreElements())
      {
         try
         {
            element = (Attribute) nenum.next();
            text.append(" {").append(toString(element)).append("} ");
         }
         catch(NamingException e)
         {
         }
      } 
      return text.toString();
   }//--------------------------------------------
   /**
    * 
    * @param aAttribute the attribute
    * @return string version of attribute
    */
   public static String toString(Attribute aAttribute)
   {
      if(aAttribute == null)
         return "";
            
      StringBuffer text = new StringBuffer();
      
      text.append(" id=").append(aAttribute.getID()).append(">");
      
      for (int i = 0; i < aAttribute.size(); i++)
      {
         try
         {
           text.append(" ").append(Debugger.toString(aAttribute.get(i)));
         }
         catch(NamingException e)
         {
         }
      }
      return text.toString();
   }//--------------------------------------------
   protected NamingEnumeration<?> rawSearchBaseEntry(Name searchbase,

   String filter, int limit, int timeout, String returnAttributes[])

   throws NamingException

   {

      NamingEnumeration<?> result = null;

      if (returnAttributes != null && returnAttributes.length == 0)

         returnAttributes = (new String[] {

            "objectClass" });

      SearchControls constraints = new SearchControls();

      constraints.setSearchScope(0);

      constraints.setCountLimit(limit);

      constraints.setTimeLimit(timeout);

      constraints.setReturningAttributes(returnAttributes);

      result = ctx.search(searchbase, filter, constraints);

      return result;

   }

   public NamingEnumeration<?> searchBaseEntry(String searchbase, String filter,

   int limit, int timeout) throws NamingException

   {

      return rawSearchBaseEntry(new CompositeName(searchbase), filter, limit,

      timeout, new String[] {

         "objectClass" });

   }

   public NamingEnumeration<?> searchBaseEntry(String searchbase, String filter,

   int limit, int timeout, String returnAttributes[])

   throws NamingException

   {

      return rawSearchBaseEntry(new CompositeName(searchbase), filter, limit,

      timeout, returnAttributes);

   }

   public void renameEntry(Name OldDN, Name NewDN, boolean deleteOldRDN)

   throws NamingException

   {

      String value = deleteOldRDN ? "true" : "false";

      try

      {

         ctx.addToEnvironment("java.naming.ldap.deleteRDN", value);

         renameEntry(OldDN, NewDN);

         ctx.addToEnvironment("java.naming.ldap.deleteRDN", "false");

      }

      catch (NamingException e)

      {

         ctx.addToEnvironment("java.naming.ldap.deleteRDN", "false");

         throw e;

      }

   }

   public void renameEntry(String oldDN, String newDN) throws NamingException

   {

      ctx.rename(oldDN, newDN);

   }

   public void copyEntry(String fromDN, String toDN) throws NamingException

   {

      addEntry(toDN, read(fromDN));

   }

   public void addEntry(String dn, Attributes atts) throws NamingException

   {

      ctx.createSubcontext(dn, atts);

   }

   public void deleteEntry(String dn) throws NamingException

   {

      ctx.destroySubcontext(dn);

   }

   public synchronized Attributes read(String dn) throws NamingException

   {

      return read(dn, null);

   }

   public synchronized Attributes read(String dn, String returnAttributes[])

   throws NamingException

   {

      return ctx.getAttributes(dn, returnAttributes);

   }

   public void modifyAttributes(String dn, int mod_type, Attributes attr)
   throws NamingException
   {
      ctx.modifyAttributes(dn, mod_type, attr);
   }

   public void modifyAttributes(String dn, ModificationItem modList[])

   throws NamingException

   {

      ctx.modifyAttributes(dn, modList);

   }

   public void updateEntry(String dn, Attributes atts) throws NamingException

   {

      modifyAttributes(dn, 2, atts);

   }

   public void deleteAttribute(String dn, Attribute a) throws NamingException

   {

      BasicAttributes atts = new BasicAttributes();

      atts.put(a);

      modifyAttributes(dn, 3, atts);

   }

   public void deleteAttributes(String dn, Attributes a) throws NamingException

   {

      modifyAttributes(dn, 3, a);

   }

   public void updateAttribute(String dn, Attribute a) throws NamingException

   {

      BasicAttributes atts = new BasicAttributes();

      atts.put(a);

      modifyAttributes(dn, 2, atts);

   }

   public void updateAttributes(String dn, Attributes a) throws NamingException

   {

      modifyAttributes(dn, 2, a);

   }

   public void addAttribute(String dn, Attribute a) throws NamingException

   {

      BasicAttributes atts = new BasicAttributes();

      atts.put(a);

      modifyAttributes(dn, 1, atts);

   }

   public void addAttributes(String dn, Attributes a) throws NamingException

   {

      modifyAttributes(dn, 1, a);

   }

   public NamingEnumeration<?> list(String Searchbase) throws NamingException

   {

      return rawSearchOneLevel(new CompositeName(Searchbase),

      "(objectclass=*)", 0, 0, new String[] {

         "1.1" });

   }//--------------------------------

   /**
    * 
    * Close the LDAP connection
    * 
    * 
    *  
    */

   public void close()
   {

      try
      {

         if (ctx == null)
         {
            return;

         }
         else
         {
            ctx.close();
            return;
         }
      }
      catch (NamingException e)
      {
         Debugger.printWarn(e);
      }
   }

   public void renameEntry(String OldDN, String NewDN, boolean deleteOldRDN)

   throws NamingException

   {

      String value = deleteOldRDN ? "true" : "false";

      try

      {

         ctx.addToEnvironment("java.naming.ldap.deleteRDN", value);

         renameEntry(OldDN, NewDN);

         ctx.addToEnvironment("java.naming.ldap.deleteRDN", "false");

      }

      catch (NamingException e)

      {

         ctx.addToEnvironment("java.naming.ldap.deleteRDN", "false");

         throw e;

      }

   }

   private static void setupLDAPSyntax()

   {

      nameParserSyntax = new Properties();

      nameParserSyntax.put("jndi.syntax.direction", "right_to_left");

      nameParserSyntax.put("jndi.syntax.separator", ",");

      nameParserSyntax.put("jndi.syntax.escape", "\\");

      nameParserSyntax.put("jndi.syntax.trimblanks", "true");

      nameParserSyntax.put("jndi.syntax.separator.typeval", "=");

   }

   public static Name getNameFromString(String iDN) throws NamingException

   {

      String DN = iDN;

      Name CompositeFormDN = null;

      CompoundName CompoundFormDN = null;

      if (iDN.indexOf("ldap://") != -1)

      {

         CompositeFormDN = new CompositeName(iDN);

         if (CompositeFormDN.size() != 0)

            DN = CompositeFormDN.get(CompositeFormDN.size() - 1);

      }

      if (nameParserSyntax == null)

         setupLDAPSyntax();

      CompoundFormDN = new CompoundName(DN, nameParserSyntax);

      return CompoundFormDN;

   }

   public static Name getNameFromSearchResult(SearchResult iDirectoryEntry,

   Name iBaseDN) throws InvalidNameException, NamingException

   {

      String RDN = applyJNDIRDNBugWorkAround(iDirectoryEntry.getName());

      Name JNDIRDN = getNameFromString(RDN);



     if (iDirectoryEntry.isRelative())
            JNDIRDN.addAll(0, iBaseDN);
      else
      {
         JNDIRDN = (Name) iBaseDN.clone();

      }

      return JNDIRDN;

   }

   private static String applyJNDIRDNBugWorkAround(String iRDN)

   {

      int SlashPos = iRDN.lastIndexOf("\\\\");

      String ReturnString;

      if (SlashPos == iRDN.length() - 2)

         ReturnString = iRDN.substring(0, SlashPos);

      else

         ReturnString = iRDN;

      return ReturnString;

   }

   public DirContext getContext()
   {
      return ctx;
   }


   private SearchControls existanceConstraints;

   private final DirContext ctx;

   private final String url;
   private static Properties nameParserSyntax = null;


}