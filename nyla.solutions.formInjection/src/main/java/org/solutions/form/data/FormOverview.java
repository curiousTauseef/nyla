package org.solutions.form.data;

import java.io.Serializable;
import java.util.Date;

import nyla.solutions.global.data.Data;
import nyla.solutions.global.data.PrimaryKey;
import nyla.solutions.global.security.user.data.User;


/**
 * <pre>
 * FormOverview contains overview information about the form
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class FormOverview implements Serializable, PrimaryKey
{ 
   /**
    * @return Returns the status.
    */
   public FormStatus getStatus()
   {
      return status;
   }//--------------------------------------------
   public FormStatus getFormStatus() {
       return getStatus();
   }
   /**
    * @param status The status to set.
    */
   public void setStatus(FormStatus status)
   {
      this.status = status;
   }//--------------------------------------------
   /**
    * @return Returns the type.
    */
   public FormType getType()
   {
      return type;
   }//--------------------------------------------
   /**
    * @param type The type to set.
    */
   public void setType(FormType type)
   {
      this.type = type;
   }//--------------------------------------------
   
   /**
    * @return Returns the contactDate.
    */
   public Date getContactDate()
   {
      return contactDate;
   }
   /**
    * @param contactDate The contactDate to set.
    */
   public void setContactDate(Date contactDate)
   {
      this.contactDate = contactDate;
   }//--------------------------------------------
   /**
    * @return Returns the deletedCode.
    */
   public String getDeletedCode()
   {
      return deletedCode;
   }//--------------------------------------------
   /**
    * @param deletedCode The deletedCode to set.
    */
   public void setDeletedCode(String deletedCode)
   {
      if (deletedCode == null)
         deletedCode = "";

      this.deletedCode = deletedCode;
   }//--------------------------------------------
   /**
    * @return Returns the orginator.
    */
   public User getOriginator()
   {
      return originator;
   }//--------------------------------------------
   /**
    * @param orginator The orginator to set.
    */
   public void setOriginator(User orginator)
   {
      this.originator = orginator;
   }//--------------------------------------------
   
   /**
    * @return Returns the primaryKey.
    */
   public int getPrimaryKey()
   {
      return primaryKey;
   }//--------------------------------------------
   /**
    * @param primaryKey The primaryKey to set.
    */
   public void setPrimaryKey(int primaryKey)
   {
      this.primaryKey = primaryKey;
   }//--------------------------------------------
   
   /**
    * @return Returns the updateDate.
    */
   public Date getUpdateDate()
   {
      return updateDate;
   }//--------------------------------------------
   /**
    * @param updateDate The updateDate to set.
    */
   public void setUpdateDate(Date updateDate)
   {
      this.updateDate = updateDate;
   }//--------------------------------------------
   public Integer getCreateUserID() {
       return createUserID;
   }
   public Integer getFormStatusId() {
       return formStatusId;
   }
   public Integer getFormTypeId() {
       return formTypeId;
   }

   private int primaryKey = Data.NULL;
   private String deletedCode = Data.NO;
   private Date contactDate =  null;
   private User originator = null;
   private FormStatus status = null;
   private FormType type  =null;
   private Date updateDate = null;
   private Integer createUserID = null;
   private Integer formTypeId;
   private Integer formStatusId;
   static final long serialVersionUID = FormOverview.class.getName().hashCode();
}
