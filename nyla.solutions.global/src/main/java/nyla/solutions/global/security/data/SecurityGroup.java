package nyla.solutions.global.security.data;
import java.security.acl.*;
import java.security.*;
import java.io.*;
import java.util.*;
/**
 * <pre>
 * SecurityGroup provides a set of functions to
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class SecurityGroup
implements Group, Serializable
{
   /**
    * 
    * Constructor for SecurityGroup initializes internal 
    * data settings.
    *
    */
   public SecurityGroup()
   {
      groupMembers = new HashSet<Principal>(50, 100);      
   }//--------------------------------------------
   public SecurityGroup(String aGroupName)
   {
      groupMembers = new HashSet<Principal>(50, 100);
      //groupMembers = new HashSet(50, 100);
       name = aGroupName;
   }//--------------------------------------------
   public boolean addMember(Principal principal)
   {
       if(groupMembers.contains(principal))
           return false;

       groupMembers.add(principal);
       return true;
   }//--------------------------------------------
   public boolean removeMember(Principal principal)
   {
       return groupMembers.remove(principal);
   }//--------------------------------------------
  public Enumeration<Principal> members()
  {
       return Collections.enumeration(groupMembers);
  }//--------------------------------------------
   public boolean equals(Group group1)
   {
       return name.equals(group1.toString());
   }//--------------------------------------------
   public String toString()
   {
       return name;
   }//--------------------------------------------
   public int hashCode()
   {
       return name.hashCode();
   }//--------------------------------------------
   /**
    * 
    * Recursively look into groups for members
    * @see java.security.acl.Group#isMember(java.security.Principal)
    * @return true if the principal is a member of the group
    */
   public boolean isMember(Principal principal)
   {
       if(groupMembers.contains(principal))
       {
           return true;
       } 
       else
       {
           Vector<Principal> vector = new Vector<Principal>(10);
           return isMemberRecurse(principal, vector);
       }
   }//--------------------------------------------
   public String getName()
   {
       return name;
   }//--------------------------------------------
   private boolean isMemberRecurse(Principal principal, Vector<Principal> vector)
   {
       for(Enumeration<Principal> enumeration = members(); enumeration.hasMoreElements();)
       {
           boolean flag = false;
           Principal principal1 = (Principal)enumeration.nextElement();
           if(principal1.equals(principal))
               return true;
           if(principal1 instanceof SecurityGroup)
           {
              SecurityGroup groupimpl = (SecurityGroup)principal1;
               vector.addElement(this);
               if(!vector.contains(groupimpl))
                   flag = groupimpl.isMemberRecurse(principal, vector);
           } 
           else
           if(principal1 instanceof Group)
           {
               Group group1 = (Group)principal1;
               if(!vector.contains(group1))
                   flag = group1.isMember(principal);
           }

           if(flag)
               return flag;
       }

       return false;
   }//--------------------------------------------
   /**
    * @param name The name to set.
    */
   public void setName(String name)
   {
      if (name == null)
         name = "";

      this.name = name;
   }//--------------------------------------------
   private HashSet<Principal> groupMembers = null;
   private String name;   
   static final long serialVersionUID = 1;
}
