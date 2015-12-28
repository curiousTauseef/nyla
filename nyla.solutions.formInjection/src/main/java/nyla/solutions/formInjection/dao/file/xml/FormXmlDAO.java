package nyla.solutions.formInjection.dao.file.xml;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import nyla.solutions.formInjection.FormSearchCriteria;
import nyla.solutions.formInjection.dao.FormDAO;
import nyla.solutions.formInjection.data.Form;
import nyla.solutions.formInjection.data.FormStatus;
import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.exception.SetupException;
import nyla.solutions.global.io.IO;
import nyla.solutions.global.util.Config;

public class FormXmlDAO extends BreXmlDAO implements FormDAO
{
   /**
    * 
    *
    * @see nyla.solutions.formInjection.dao.FormDAO#commit()
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
    * @see nyla.solutions.formInjection.dao.FormDAO#insertForm(nyla.solutions.formInjection.data.Form)
    */
   public Form insertForm(Form form) throws SQLException, NoDataFoundException
   {
      if(form == null)
         throw new RequiredException("form in FormXmlDAO.insertForm");
      
      
      String formTypeCode = form.getFormTypeCode();
      
      if (formTypeCode == null || formTypeCode.length() == 0)
         throw new IllegalArgumentException(
         "formTypeCode required in insertForm");
      
      File directory = new File(new StringBuilder(this.formLocation).append("/").append(formTypeCode).toString());

      boolean folderExists = directory.exists();
      
      if(!folderExists)
	  {
		  try
		  {
    		  if(!IO.mkdir(directory))
    			  throw new SetupException("Unable to create directory:"+directory);
		  }
		  catch(IOException e)
		  {
			  throw new SetupException("Unable to create directory:"+directory+"  ERROR:"+e.getMessage(),e);
		  }
	  }
      
      int formID = form.getPrimaryKey();
      
      if(formID < 1)
      {
    	  String[] files = IO.list(directory,"*"+this.getExtension());
          
          if(files != null )
          {
             formID = files.length+1;
          }
          else
          {
        	  formID = 1;
          }
          
          form.setPrimaryKey(formID);
      }   	  
      
      
      String location = new StringBuilder(directory.getAbsolutePath())
      			.append("/").append(formTypeCode)
    		  .append(form.getPrimaryKey()).append(getExtension()).toString();
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
    * @see nyla.solutions.formInjection.dao.FormDAO#selectFormByPK(int, java.lang.String)
    */
   public Form selectFormByPK(int formPK, String formTypeCode) throws NoDataFoundException, SQLException
   {
      String location = this.formLocation+"/"+formTypeCode+"/"+formTypeCode+formPK+getExtension();
      
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
    * @see nyla.solutions.formInjection.dao.FormDAO#updateForm(nyla.solutions.formInjection.data.Form)
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
