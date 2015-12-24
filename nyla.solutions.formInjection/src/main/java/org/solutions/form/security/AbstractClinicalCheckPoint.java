package org.solutions.form.security;

/**
 * <pre>
 * AbstractClinicalCheckPoint provides a set of functions to
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public abstract class AbstractClinicalCheckPoint extends AbstractCheckPoint
{
   public static final String ALL_ROLE_NM    = "ALL";
   public static final String WILDCARD       = "*";
   public static final String ROLE_NAME_SEPARATOR = ",";

   /**
    * Constructor for AbstractClinicalCheckPoint initalizes internal 
    * data settings.
    * 
    */
   public AbstractClinicalCheckPoint()
   {
      super();
   }//--------------------------------------------
   /**
    * 
    * @param aRoles the array of roles
    * @return true a role equals ALL or *
    */
   protected boolean containsWildCardRole(String[] aRoles)
   {
      if(aRoles == null || aRoles.length == 0)
         return false;
      
      for (int i = 0; i < aRoles.length; i++)
      {
         if(aRoles[i] == null)
            continue; //skip
         
         aRoles[i] = aRoles[i].trim();
         if(ALL_ROLE_NM.equalsIgnoreCase(aRoles[i])||
            WILDCARD.equals(aRoles[i]))
         {
            return true; //found
         }
      }//end for
      return false; //not found
   }//--------------------------------------------
}
