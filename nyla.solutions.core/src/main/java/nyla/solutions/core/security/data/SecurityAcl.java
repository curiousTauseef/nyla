package nyla.solutions.core.security.data;

import java.io.Serializable;
import java.security.Principal;
import java.security.acl.Acl;
import java.security.acl.AclEntry;
import java.security.acl.Group;
import java.security.acl.LastOwnerException;
import java.security.acl.NotOwnerException;
import java.security.acl.Permission;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import nyla.solutions.core.exception.NotImplementedException;

/**
 * <pre>
 * SecurityAcl security acl
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class SecurityAcl implements Acl, Serializable
{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 15616038981342591L;
	public SecurityAcl()
	 {
	     this("SecurityAcl");
	 }//--------------------------------------------
	 
   /**
    * Constructor for SecurityAcl initializes internal 
    * data settings.
    * 
    */
   public SecurityAcl(String aName)
   {
      name = aName;
   }//--------------------------------------------
   /**
    * 
    * @see java.security.acl.Acl#getName()
    */
   public synchronized String getName()
   {
      return name;
   }//--------------------------------------------
   /**
    * 
    * @see java.security.acl.Acl#entries()
    */
   @SuppressWarnings({ "unchecked", "rawtypes" })
public synchronized Enumeration<AclEntry> entries()
   {
	   Set<Object> set = this.entries
	      .values()
	      .stream()
	      .map(s -> s.stream()
	      					.collect(Collectors.toSet())).collect(Collectors.toSet());

	    return (Enumeration)Collections.enumeration(set);  					
      //return ;
      					
      
      
      //.enumeration(this.entries.values());
   }//--------------------------------------------
  
   /**
    * 
    * @see java.security.acl.Acl#setName(java.security.Principal, java.lang.String)
    */
   public synchronized void setName(Principal caller, String aName) throws NotOwnerException
   {
      this.name = aName;
   }//--------------------------------------------
   /**
    * 
    * @see java.security.acl.Acl#addEntry(java.security.Principal, java.security.acl.AclEntry)
    */
   public synchronized boolean addEntry(Principal caller,Principal principal, Permission permission)
   throws NotOwnerException
   {
	  return addEntry(caller,new SecurityAccess(principal,permission));
   }//------------------------------------------------
   public synchronized boolean addEntry(Principal caller,Principal principal, String permission)
   throws NotOwnerException
   {
			  return addEntry(caller,new SecurityAccess(principal,permission));
	}//------------------------------------------------
	public synchronized boolean addEntry(Principal caller,Principal principal, boolean negative, String permission)
   throws NotOwnerException
   {
	  return addEntry(caller,new SecurityAccess(principal,negative,permission));
   }
   /**
    * 
    * @see java.security.acl.Acl#addEntry(java.security.Principal, java.security.acl.AclEntry)
    */
   public synchronized boolean addEntry( Principal caller,AclEntry aclEntry)
   throws NotOwnerException
   {
	   if( aclEntry == null)
		   return false;

	   Principal principal = aclEntry.getPrincipal();
	   
	   Set<AclEntry> set = this.entries.get(principal);
	   
	   if(set == null)
		   set = new HashSet<AclEntry>();
	   
	   set.remove(aclEntry);
	   set.add(aclEntry);
      this.entries.put(principal,set);
      
      return true;
      
   }//--------------------------------------------
   /**
    * 
    * @see java.security.acl.Acl#removeEntry(java.security.Principal, java.security.acl.AclEntry)
    */
   public synchronized boolean removeEntry(Principal caller, AclEntry aclEntry)
    throws NotOwnerException
   {
	   if(aclEntry == null)
		   return false;
	   
	   Principal principal = aclEntry.getPrincipal();
	   
	   if(principal == null)
		   return false;
	   
	   Set<AclEntry> set = this.entries.get(aclEntry.getPrincipal());
	   if(set == null || set.isEmpty())
		   return false;
	   boolean r = set.remove(aclEntry);
	   
	   this.entries.put(principal, set);
	   
      return  r;
   }//--------------------------------------------

   /**
    * 
    * @see java.security.acl.Acl#checkPermission(java.security.Principal, java.security.acl.Permission)
    */
   public synchronized boolean checkPermission(Principal principal, Permission permission)
   {
      
      if(principal == null)
         return false;
      
      if(permission == null)
          return false; 
       
      Set<AclEntry> set = this.entries.get(principal);
      
      if(set != null && !set.isEmpty())
      {
          AclEntry  aclEntry = null;
          for (Iterator<AclEntry> i = set.iterator(); i.hasNext();)
          {
             aclEntry = i.next();

            	 if(aclEntry.checkPermission(permission))
                   return true;
          }
      }
      
      
      //did not find for user, not check for groups
      if(SecurityUser.class.isAssignableFrom(principal.getClass()))
      {
    	  	return checkPermission(((SecurityUser)principal).getGroups(),permission);
      }
      return false;
   }//--------------------------------------------
   public boolean checkPermission(Set<Group> groups, Permission permission)
   {
	   if(groups == null || groups.isEmpty())
		   return false;
	   
	   for (Group group : groups)
		{
			if(checkPermission(group, permission))
				return true;
		}
	   
	   return false;
   }
   /**
    * 
    * @see java.security.acl.Acl#checkPermission(java.security.Principal, java.security.acl.Permission)
    */
   public synchronized boolean checkPermission(Principal aPrincipal, String aPermission)
   {
      if(aPermission == null)
    	  	return false;
      
      SecurityPermission securityPermission = new SecurityPermission(aPermission);
      
      return this.checkPermission(aPrincipal, securityPermission);
   }//--------------------------------------------
   /**
    * 
    * @see java.security.acl.Acl#getPermissions(java.security.Principal)
    */
   public synchronized Enumeration<Permission> getPermissions(Principal principal)
   {
	   throw new NotImplementedException();
   }//--------------------------------------------
   /**
    * 
    * @see java.security.acl.Owner#isOwner(java.security.Principal)
    */
   public synchronized boolean isOwner(Principal principal)
   {
      return false;
   }//--------------------------------------------
   /**
    * 
    * @see java.security.acl.Owner#addOwner(java.security.Principal, java.security.Principal)
    */
   public synchronized boolean addOwner(Principal aOwner, Principal aPrincipal)
   throws NotOwnerException
   {
      return false;
   }//--------------------------------------------
   /**
    * 
    * @see java.security.acl.Owner#deleteOwner(java.security.Principal, java.security.Principal)
    */
   public synchronized boolean deleteOwner(Principal aOwner, Principal arg1)
   throws NotOwnerException, LastOwnerException
   {
      return false;
   }//--------------------------------------------
   /**
    * 
    * @return this.entries.isEmpty() 
    */
   public boolean isEmpty()
   {
      return this.entries == null || this.entries.isEmpty();
   }//--------------------------------------------
   
   public void removeAllEntries()
   {
	   this.entries.clear();
   }
   
   private Map<Principal,Set<AclEntry>> entries = new HashMap<Principal,Set<AclEntry>>(); 
   private String name = "";
}
