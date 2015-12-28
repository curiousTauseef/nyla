package nyla.solutions.formInjection.dao;

import java.sql.SQLException;
import java.util.Collection;

import nyla.solutions.formInjection.FormSearchCriteria;
import nyla.solutions.formInjection.data.Form;
import nyla.solutions.formInjection.data.FormStatus;
import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.patterns.Disposable;


public interface FormDAO extends Disposable, BreDAO
{

   /**
    * 
    * @see org.solutions.dao.jdo.JDODAO#commit()
    */
   public void commit();//----------------------------------------

   /**
    * 
    * @see org.solutions.dao.jdo.JDODAO#rollback()
    */
   public void rollback();//--------------------------------------------

   /**
    * 
    * @see org.solutions.dao.jdo.JDODAO#setAutoCommit(boolean)
    */
   public void setAutoCommit(boolean autoCommit);//--------------------------------------------

   /**
    * 
    * @param aFormStatusName the form status name
    * @return
    * @throws SQLException
    * @throws NoDataFoundException
    */
   public FormStatus selectStatusByName(String aFormStatusName) throws SQLException, NoDataFoundException;//--------------------------------------------

   /**
    * 
    * @return collection of FormStatus objects
    * @throws SQLException
    * @throws NoDataFoundException
    */
   public Collection selectStatuses() throws SQLException, NoDataFoundException;//--------------------------------------------

   /**
    * Insert the form into the database
    * 
    * @param form the form to insert
    * @throws SQLException
    * @throws  NoDataFoundException when the INCOMPLETE status cannot be found
    */
   public Form insertForm(Form form) throws SQLException, NoDataFoundException;//--------------------------------------------


   /**
    * Select form by PK
    * @param aFormPK  a form primary key
    * @return the Form Injection form that matches the given primary key
    * @throws NoDataFoundException when the form does not exist
    */
   public Form selectFormByPK(int formPK, String formTypeCode) throws NoDataFoundException, SQLException;//--------------------------------------------

   public Collection selectDeletedAnswers(int formPK, String formTypeCode) throws SQLException;

   public void purgeAnswers(Collection answers) throws SQLException, NoDataFoundException;// --------------------------------------------

   public void saveAnswers(Form aForm) throws SQLException;

   /**
    * Update the form
    * @param aForm the form to update
    * @throws SQLException
    * @throws NoDataFoundException
    */
   public Form updateForm(Form aForm) throws SQLException, NoDataFoundException;//--------------------------------------------

   /**
    * Mark form as deleted form 
    * @param aFormPK the form primary key
    * @throws NoDataFoundException
    * @throws SQLException
    */
   public void deleteFormByPK(int aFormPK, String formTypeCode) throws NoDataFoundException, SQLException;//-------------------------------------------


   /**
    * Search for forms by a given FormSearchCriteria
    * @param aSearchCriteria the form search criteria
    * @return the collection for Form objects
    */
   public Collection searchForForms(FormSearchCriteria aSearchCriteria) throws NoDataFoundException, SQLException;//--------------------------------------------

   /**
    * Select form answers for question with a given row number 
    * @param aFormPK the form primary key
    * @param aQuestionPK the question primary key
    * @param aRowNumber the row number
    * @return collection of answer objects
    * @throws SQLException
    * @throws NoDataFoundException
    */
   public Collection selectAnswersByFormAndQuestionAndRow(int aFormPK,
                                                          int aQuestionPK,
                                                          Integer aRowNumber) throws SQLException, NoDataFoundException;//--------------------------------------------

}