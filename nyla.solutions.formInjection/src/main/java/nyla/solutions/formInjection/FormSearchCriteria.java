package nyla.solutions.formInjection;

import java.io.Serializable;

import nyla.solutions.global.security.data.SecurityCredential;


/**
 * <pre>
 * FormSearchCriteria data search that represents a search for forms
 * of a given criteria.
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class FormSearchCriteria implements Serializable
{
   /**
    * BY_FormTypeCode = 5
    */
   public static final short BY_FormTypeCode = 5;   
   
   /**
    * 
    * Constructor for FormSearchCriteria initalizes internal 
    * data settings.
    *
    */
   private FormSearchCriteria()
   {}
   //--------------------------------------------
   /**
    * Search by form type code
    * @param aUser the user accessed the data
    * @param aTypeCode the form type code
    * @return new instance FormSearchCriteria
    */
   public static FormSearchCriteria searchForTypeCode(SecurityCredential aUser, String aTypeCode)
   {
      if(aUser == null)
         throw new IllegalArgumentException("aUser required in FormSearchCriteria.searchForCreateUserAndType");
            
      FormSearchCriteria search = new FormSearchCriteria();
      search.user = aUser;
      search.formTypeCode = aTypeCode;
      
      search.type = BY_FormTypeCode;      
      return search;
   }//--------------------------------------------          
   /**
    * @return Returns the type.
    */
   public short getType()
   {
      return type;
   }//--------------------------------------------
   
   /**
    * @return Returns the user.
    */
   public SecurityCredential getUser()
   {
      return user;
   }//--------------------------------------------
   
   /**
    * @return Returns the formTypeCode.
    */
   public String getFormTypeCode()
   {
      return formTypeCode;
   }//--------------------------------------------
   
   private SecurityCredential user = null;
   private short type = BY_FormTypeCode;
   private String formTypeCode = "";
   static final long serialVersionUID = FormSearchCriteria.class.getName()
   .hashCode();
}
