package org.solutions.form.dao.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import nyla.solutions.dao.OR.query.QueryBuilder;
import nyla.solutions.global.exception.ConnectionException;
import nyla.solutions.global.exception.DuplicateRowException;
import nyla.solutions.global.exception.NoDataFoundException;

import org.solutions.dao.hibernate.HibernateQueryBuilder;
import org.solutions.form.BRE;
import org.solutions.form.FormSearchCriteria;
import org.solutions.form.bre.ExpressionBluePrint;
import org.solutions.form.bre.OperationBluePrint;
import org.solutions.form.dao.FormDAO;
import org.solutions.form.data.Answer;
import org.solutions.form.data.AttributeFacts;
import org.solutions.form.data.Form;
import org.solutions.form.data.FormAnswer;
import org.solutions.form.data.FormStatus;
import org.solutions.form.data.FormType;
import org.solutions.form.data.ManagedForm;
import org.solutions.form.data.QuestionAttribute;
import org.solutions.form.data.Questionaire;
import org.solutions.form.data.ResponseTable;
import org.solutions.form.data.ResponseType;

/**
 * <pre>
 * FormHibernateDAO is a data access object for Form data management.
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class FormHibernateDAO extends QuestionHibernateDAO implements FormDAO
{
   /**
    * 
    * Constructor for FormHibernateDAO initializes internal 
    * data settings.
    */
   public FormHibernateDAO()
   {
      super();      
   }// --------------------------------------------

   /**
    * 
    * @see org.solutions.form.dao.FormDAO#deleteForm(org.solutions.form.data.Form)
    */
   public Form deleteForm(Form form) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see org.solutions.form.dao.FormDAO#deleteFormByPK(int)
    */
   public void deleteFormByPK(int aFormPK, String formTypeCode) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub

   }

   /**
    * 
    * @see org.solutions.form.dao.FormDAO#insertForm(org.solutions.form.data.Form)
    */
   public Form insertForm(Form form) throws SQLException, NoDataFoundException
   {
      super.insert(form);
      
      return form;
   }// --------------------------------------------


   /**
    * 
    * @see org.solutions.form.dao.FormDAO#purgeAnswers(java.util.Collection)
    */
   public void purgeAnswers(Collection answers) throws SQLException, NoDataFoundException
   {
      // TODO Auto-generated method stub
   }// --------------------------------------------

   /**
    * 
    * @see org.solutions.form.dao.FormDAO#saveAnswers(org.solutions.form.data.Application)
    */
   public void saveAnswers(Form aForm) throws SQLException
   {
      
      if(!aForm.hasAnswers())
         return;
      
      FormAnswer formAnswer = null;
      for (Iterator i = aForm.getFormAnswers().iterator(); i.hasNext();)
      {
         formAnswer = (FormAnswer) i.next();
         formAnswer.generateKey();
         this.save(formAnswer);  
      }
   }// --------------------------------------------


   /**
    * 
    * @see org.solutions.form.dao.FormDAO#saveForm(org.solutions.form.data.Form)
    */
   public Form saveForm(Form aForm) throws SQLException, NoDataFoundException
   {
      super.save(aForm);
      this.saveAnswers(aForm);
      
      return aForm;
   }// --------------------------------------------


   /**
    * 
    * @see org.solutions.form.dao.FormDAO#searchForForms(org.solutions.form.FormSearchCriteria)
    */
   public Collection searchForForms(FormSearchCriteria aSearchCriteria) throws NoDataFoundException, SQLException
   {
      switch (aSearchCriteria.getType())
      {
      default:
         return super.selectObjectsByProperty(getFormClass(), "formTypeCode", aSearchCriteria.getFormTypeCode());
      }
   }// --------------------------------------------


   /**
    * 
    * @see org.solutions.form.dao.FormDAO#selectAnswersByFormAndQuestionAndRow(int, int, java.lang.Integer)
    */
   public Collection selectAnswersByFormAndQuestionAndRow(int aFormPK,
                                                          int aQuestionPK,
                                                          Integer aRowNumber) throws SQLException, NoDataFoundException
   {
      HibernateQueryBuilder queryBuilder = (HibernateQueryBuilder)this.createQueryBuilder(Answer.class);
      QueryBuilder formId = queryBuilder.getColumn("formId").equal(new Integer(aFormPK));
      QueryBuilder questionID = queryBuilder.getColumn("questionId").equal(new Integer(aQuestionPK));
      QueryBuilder rowNumber = queryBuilder.getColumn("row").equal(aRowNumber);
      
      return select(formId.and(questionID.and(rowNumber)));
   }// --------------------------------------------

   /**
    * 
    * @see org.solutions.form.dao.FormDAO#selectDeletedAnswers(int)
    */
   public Collection selectDeletedAnswers(int formPK) throws SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see org.solutions.form.dao.FormDAO#selectFormByPK(int)
    */
   public Form selectFormByPK(int formPK, String formTypeCode) throws NoDataFoundException, SQLException
   {
      return (Form) super.selectObjectByProperty(getFormClass(), "formId", new Integer(formPK));
   }// --------------------------------------------


   /**
    * 
    * @see org.solutions.form.dao.FormDAO#selectManagedForm(int)
    */
   public ManagedForm selectManagedForm(int formPK) throws SQLException, NoDataFoundException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see org.solutions.form.dao.FormDAO#selectManagedForm(int, boolean)
    */
   public ManagedForm selectManagedForm(int formPK, boolean includeDeleted) throws SQLException, NoDataFoundException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see org.solutions.form.dao.FormDAO#selectStatusByName(java.lang.String)
    */
   public FormStatus selectStatusByName(String aFormStatusName) throws SQLException, NoDataFoundException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see org.solutions.form.dao.FormDAO#selectStatuses()
    */
   public Collection selectStatuses() throws SQLException, NoDataFoundException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see org.solutions.form.dao.FormDAO#setAutoCommit(boolean)
    */
   public void setAutoCommit(boolean autoCommit)
   {
      // TODO Auto-generated method stub

   }

   /**
    * 
    * @see org.solutions.form.dao.FormDAO#updateForm(org.solutions.form.data.Form)
    */
   public Form updateForm(Form aForm) throws SQLException, NoDataFoundException
   {
      // TODO Auto-generated method stub
      return null;
   }// --------------------------------------------

   /**
    * 
    * @see org.solutions.form.dao.BreDAO#constructBRE(int, org.solutions.form.data.Questionaire)
    */
   public BRE constructBRE(String aFormTypeID, Questionaire aQuestionaire) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see org.solutions.form.dao.BreDAO#constructBRE(org.solutions.form.data.Form)
    */
   public BRE constructBRE(Form aForm) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see org.solutions.form.dao.BreDAO#selectExpressionByPK(java.lang.Integer)
    */
   public ExpressionBluePrint selectExpressionByPK(Integer aExpressionPK) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see org.solutions.form.dao.BreDAO#selectFormTypes()
    */
   public Collection selectFormTypes() throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see org.solutions.form.dao.BreDAO#selectOperationBluePrintByPK(java.lang.Integer)
    */
   public OperationBluePrint selectOperationBluePrintByPK(Integer aOperationPK) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see org.solutions.form.dao.BreDAO#selectRulesByFormTypeCode(String)
    */
   public Collection selectRulesByFormTypeCode(String aFormTypeID) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see org.solutions.form.dao.QuestionDAO#constructQuestioniareByFormTypeCode(java.lang.String)
    */
   public Questionaire constructQuestioniareByFormTypeCode(String aFormTypeName) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see org.solutions.form.dao.QuestionDAO#constructQuestioniareByFormTypeCode(int)
    */
   public Questionaire constructQuestioniareByFormTypeCode(int aFormTypePK) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see org.solutions.form.dao.QuestionDAO#insert(org.solutions.form.data.AttributeFacts)
    */
   public AttributeFacts insert(AttributeFacts aAttributeFacts) throws SQLException, DuplicateRowException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see org.solutions.form.dao.QuestionDAO#saveQuestionAttribute(org.solutions.form.data.QuestionAttribute)
    */
   public QuestionAttribute saveQuestionAttribute(QuestionAttribute attribute)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see org.solutions.form.dao.QuestionDAO#selectFormTypeByCode(java.lang.String)
    */
   public FormType selectFormTypeByCode(String aFormTypeName) throws SQLException, NoDataFoundException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see org.solutions.form.dao.QuestionDAO#selectFormTypeByPK(int)
    */
   public FormType selectFormTypeByPK(int aFormTypeID) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see org.solutions.form.dao.QuestionDAO#selectQuestionAttributeByPK(java.lang.Integer, java.lang.Integer, java.lang.String)
    */
   public QuestionAttribute selectQuestionAttributeByPK(Integer formTypeId,
                                                        Integer questionId,
                                                        String attrName) throws SQLException, NoDataFoundException
   {
      // TODO Auto-generated method stub
      return null;
   }// --------------------------------------------


   /**
    * 
    * @see org.solutions.form.dao.QuestionDAO#saveResponseTable(org.solutions.form.data.ResponseTable)
    */
   public ResponseTable saveResponseTable(ResponseTable aResponseTable)
   {
      this.save(aResponseTable);
      
      return aResponseTable;
   }// --------------------------------------------
   /**
    * 
    * @see org.solutions.form.dao.QuestionDAO#saveResponseType(org.solutions.form.data.ResponseType)
    */
   public ResponseType saveResponseType(ResponseType aResponseType)
   {
      this.save(aResponseType);
      return aResponseType;
   }// --------------------------------------------
   /**
    * 
    * @return created instance of class that this DAO will manage
    */
   private Class getFormClass()
   {
      if(formClass == null)
      {
         try
         {
            formClass = Class.forName(formClassName);
         }
         catch (ClassNotFoundException e)
         {
            throw new ConnectionException(e);
         }
      }
      
      return formClass;
   }// --------------------------------------------
   /**
    * @param formClassName the formClassName to set
    */
   public void setFormClassName(String formClassName)
   {
      if (formClassName == null)
         formClassName = "";
   
      this.formClassName = formClassName;
   }// --------------------------------------------
   public Collection selectDeletedAnswers(int formPK, String formTypeCode) throws SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

   private Class formClass = null;
   private String formClassName = null;

}
