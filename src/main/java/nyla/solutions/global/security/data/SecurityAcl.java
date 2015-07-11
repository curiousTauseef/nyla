package nyla.solutions.global.security.data;

import java.security.Principal;
import java.security.acl.Acl;
import java.security.acl.AclEntry;
import java.security.acl.LastOwnerException;
import java.security.acl.NotOwnerException;
import java.security.acl.Permission;
import java.util.Enumeration;



import java.util.*;

import nyla.solutions.global.operations.Log;
import nyla.solutions.global.util.*;
/**
 * <pre>
 * SecurityAcl security acl
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class SecurityAcl implements Acl
{

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
   public synchronized Enumeration<AclEntry> entries()
   {
      return Collections.enumeration(this.entries);
   }//--------------------------------------------
   /**
    * 
    * @return the collection Security Access objects
    */
   public synchronized Collection<AclEntry> getSecurityAccessEntries()
   {
      return entries;
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
   public synchronized boolean addEntry(Principal caller, AclEntry aclEntry)
   throws NotOwnerException
   {
      return this.entries.add(aclEntry);
   }//--------------------------------------------
   /**
    * 
    * @see java.security.acl.Acl#removeEntry(java.security.Principal, java.security.acl.AclEntry)
    */
   public synchronized boolean removeEntry(Principal caller, AclEntry aAclEntry)
    throws NotOwnerException
   {
      return this.entries.remove(aAclEntry);
   }//--------------------------------------------

   /**
    * 
    * @see java.security.acl.Acl#checkPermission(java.security.Principal, java.security.acl.Permission)
    */
   public synchronized boolean checkPermission(Principal aPrincipal, Permission aPermission)
   {
      if(aPermission == null)
         throw new IllegalArgumentException("aPermission required in SecurityAcl");
      
      if(aPrincipal == null)
         throw new IllegalArgumentException("aPrincipal required in SecurityAcl");
      
      logger.debug("Checking for permission \""+aPermission+"\" principal=\""+aPrincipal.getName());
      
      
      //loop thru ACLS
      AclEntry aclEntry = null;
      for (Iterator<AclEntry> i = this.entries.iterator(); i.hasNext();)
      {
         aclEntry = (AclEntry) i.next();
//         logger.debug("Check acl for principal aclEntry \""+aclEntry.getPrincipal().getName()
 //                    +"="+aPrincipal.getName());
         if(aPrincipal.getName().equals(aclEntry.getPrincipal().getName()))
         {
//             logger.debug("====== Dumping Permissions ======");
             //Debugger.dump(aPermission);
            if(aclEntry.checkPermission(aPermission))
               return true;
         }
      }
      return false;
   }//--------------------------------------------
   /**
    * 
    * @see java.security.acl.Acl#checkPermission(java.security.Principal, java.security.acl.Permission)
    */
   public synchronized boolean checkPermission(Principal aPrincipal, String aPermission)
   {
      if(aPermission == null)
         throw new IllegalArgumentException("aPermission required in SecurityAcl");
      
      SecurityPermission securityPermission = new SecurityPermission(aPermission);
      
      return this.checkPermission(aPrincipal, securityPermission);
   }//--------------------------------------------
   /**
    * 
    * @see java.security.acl.Acl#getPermissions(java.security.Principal)
    */
   public synchronized Enumeration<Permission> getPermissions(Principal aPrincipal)
   {
      if (aPrincipal == null)
         throw new IllegalArgumentException(
         "aPrincipal required in SecurityAcl");
      
      HashSet<Permission> permissions = new HashSet<Permission>(10);
      AclEntry aclEntry = null;
      for (Iterator<AclEntry> i = this.entries.iterator(); i.hasNext();)
      {
         aclEntry = (AclEntry) i.next();
         
         if(aPrincipal.equals(aclEntry.getPrincipal()))
            permissions.addAll(Collections.list(aclEntry.permissions()));
      }
      
      return Collections.enumeration(permissions);
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
   private transient Log logger = Debugger.getLog(getClass());
   private List<AclEntry> entries = new ArrayList<AclEntry>(); 
   private String name = "";
}
