package org.solutions.form.dao.file.xml;

import java.sql.SQLException;
import java.util.Collection;

import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.io.IO;
import nyla.solutions.global.util.Config;

import org.solutions.form.FormSearchCriteria;
import org.solutions.form.dao.FormDAO;
import org.solutions.form.data.Form;
import org.solutions.form.data.FormStatus;

public class FormXmlDAO extends BreXmlDAO implements FormDAO
{
   /**
    * 
    *
    * @see org.solutions.form.dao.FormDAO#commit()
    */
   public void commit()
   {
   }//--------------------------------------------

   public void deleteFormByPK(int formPK, String formTypeCode) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub

   }//--------------------------------------------

   /**
    * 
    *
    * @see org.solutions.form.dao.FormDAO#insertForm(org.solutions.form.data.Form)
    */
   public Form insertForm(Form form) throws SQLException, NoDataFoundException
   {
      if(form == null)
         throw new RequiredException("form in FormXmlDAO.insertForm");
      
      
      String formTypeCode = form.getFormTypeCode();
      
      if (formTypeCode == null || formTypeCode.length() == 0)
         throw new IllegalArgumentException(
         "formTypeCode required in insertForm");
      
      
      String[] files = IO.list(this.formLocation+"/"+formTypeCode,"*"+this.getExtension());
      
      int formID = 1;
      
      if(files != null )
      {
         formID = files.length+1;
      }
      
      form.setPrimaryKey(formID);
      
      String location = this.formLocation+"/"+formTypeCode+form.getPrimaryKey()+getExtension();
      super.writeObject(form,location);
      return form;
   }//--------------------------------------------

   public void purgeAnswers(Collection answers) throws SQLException, NoDataFoundException
   {
      // TODO Auto-generated method stub

   }

   public void rollback()
   {
      // TODO Auto-generated method stub

   }

   public void saveAnswers(Form form) throws SQLException
   {
      // TODO Auto-generated method stub

   }
   public Collection searchForForms(FormSearchCriteria searchCriteria) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

   public Collection selectAnswersByFormAndQuestionAndRow(int formPK,
                                                          int questionPK,
                                                          Integer rowNumber) throws SQLException, NoDataFoundException
   {
      // TODO Auto-generated method stub
      return null;
   }

   public Collection selectDeletedAnswers(int formPK, String formTypeCode) throws SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }
   /**
    * 
    *
    * @see org.solutions.form.dao.FormDAO#selectFormByPK(int, java.lang.String)
    */
   public Form selectFormByPK(int formPK, String formTypeCode) throws NoDataFoundException, SQLException
   {
      String location = this.formLocation+"/"+formTypeCode+formPK+getExtension();
      
      return (Form)this.readObject(location);
   }//--------------------------------------------


   public FormStatus selectStatusByName(String formStatusName) throws SQLException, NoDataFoundException
   {
      // TODO Auto-generated method stub
      return null;
   }

   public Collection selectStatuses() throws SQLException, NoDataFoundException
   {
      // TODO Auto-generated method stub
      return null;
   }

   public void setAutoCommit(boolean autoCommit)
   {
      // TODO Auto-generated method stub

   }//--------------------------------------------
   /**
    * 
    *
    * @see org.solutions.form.dao.FormDAO#updateForm(org.solutions.form.data.Form)
    */
   public Form updateForm(Form form) throws SQLException, NoDataFoundException
   {
      Form managedForm = this.selectFormByPK(form.getPrimaryKey(), form.getFormTypeCode());
      managedForm.copy(form);
      
      this.writeObject(form, whereIs(form));
      
      return managedForm;
   }//--------------------------------------------

   private String whereIs(Form form)
   {
      return this.formLocation+form.getFormTypeCode()+"/"+form.getPrimaryKey()+getExtension();
   }

   public void dispose()
   {
   }//--------------------------------------------
   
   private String formLocation  = Config.getProperty(FormXmlDAO.class.getName()+".formLocation");
   
   
}
