package nyla.solutions.formInjection.dao.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import nyla.solutions.dao.OR.query.QueryBuilder;
import nyla.solutions.dao.hibernate.HibernateQueryBuilder;
import nyla.solutions.formInjection.BRE;
import nyla.solutions.formInjection.FormSearchCriteria;
import nyla.solutions.formInjection.bre.ExpressionBluePrint;
import nyla.solutions.formInjection.bre.OperationBluePrint;
import nyla.solutions.formInjection.dao.FormDAO;
import nyla.solutions.formInjection.data.Answer;
import nyla.solutions.formInjection.data.AttributeFacts;
import nyla.solutions.formInjection.data.Form;
import nyla.solutions.formInjection.data.FormAnswer;
import nyla.solutions.formInjection.data.FormStatus;
import nyla.solutions.formInjection.data.FormType;
import nyla.solutions.formInjection.data.ManagedForm;
import nyla.solutions.formInjection.data.QuestionAttribute;
import nyla.solutions.formInjection.data.Questionaire;
import nyla.solutions.formInjection.data.ResponseTable;
import nyla.solutions.formInjection.data.ResponseType;
import nyla.solutions.global.exception.ConnectionException;
import nyla.solutions.global.exception.DuplicateRowException;
import nyla.solutions.global.exception.NoDataFoundException;

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
    * @see nyla.solutions.formInjection.dao.FormDAO#deleteForm(nyla.solutions.formInjection.data.Form)
    */
   public Form deleteForm(Form form) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see nyla.solutions.formInjection.dao.FormDAO#deleteFormByPK(int)
    */
   public void deleteFormByPK(int aFormPK, String formTypeCode) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub

   }

   /**
    * 
    * @see nyla.solutions.formInjection.dao.FormDAO#insertForm(nyla.solutions.formInjection.data.Form)
    */
   public Form insertForm(Form form) throws SQLException, NoDataFoundException
   {
      super.insert(form);
      
      return form;
   }// --------------------------------------------


   /**
    * 
    * @see nyla.solutions.formInjection.dao.FormDAO#purgeAnswers(java.util.Collection)
    */
   public void purgeAnswers(Collection answers) throws SQLException, NoDataFoundException
   {
      // TODO Auto-generated method stub
   }// --------------------------------------------

   /**
    * 
    * @see nyla.solutions.formInjection.dao.FormDAO#saveAnswers(nyla.solutions.formInjection.data.Application)
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
    * @see nyla.solutions.formInjection.dao.FormDAO#saveForm(nyla.solutions.formInjection.data.Form)
    */
   public Form saveForm(Form aForm) throws SQLException, NoDataFoundException
   {
      super.save(aForm);
      this.saveAnswers(aForm);
      
      return aForm;
   }// --------------------------------------------


   /**
    * 
    * @see nyla.solutions.formInjection.dao.FormDAO#searchForForms(nyla.solutions.formInjection.FormSearchCriteria)
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
    * @see nyla.solutions.formInjection.dao.FormDAO#selectAnswersByFormAndQuestionAndRow(int, int, java.lang.Integer)
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
    * @see nyla.solutions.formInjection.dao.FormDAO#selectDeletedAnswers(int)
    */
   public Collection selectDeletedAnswers(int formPK) throws SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see nyla.solutions.formInjection.dao.FormDAO#selectFormByPK(int)
    */
   public Form selectFormByPK(int formPK, String formTypeCode) throws NoDataFoundException, SQLException
   {
      return (Form) super.selectObjectByProperty(getFormClass(), "formId", new Integer(formPK));
   }// --------------------------------------------


   /**
    * 
    * @see nyla.solutions.formInjection.dao.FormDAO#selectManagedForm(int)
    */
   public ManagedForm selectManagedForm(int formPK) throws SQLException, NoDataFoundException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see nyla.solutions.formInjection.dao.FormDAO#selectManagedForm(int, boolean)
    */
   public ManagedForm selectManagedForm(int formPK, boolean includeDeleted) throws SQLException, NoDataFoundException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see nyla.solutions.formInjection.dao.FormDAO#selectStatusByName(java.lang.String)
    */
   public FormStatus selectStatusByName(String aFormStatusName) throws SQLException, NoDataFoundException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see nyla.solutions.formInjection.dao.FormDAO#selectStatuses()
    */
   public Collection selectStatuses() throws SQLException, NoDataFoundException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see nyla.solutions.formInjection.dao.FormDAO#setAutoCommit(boolean)
    */
   public void setAutoCommit(boolean autoCommit)
   {
      // TODO Auto-generated method stub

   }

   /**
    * 
    * @see nyla.solutions.formInjection.dao.FormDAO#updateForm(nyla.solutions.formInjection.data.Form)
    */
   public Form updateForm(Form aForm) throws SQLException, NoDataFoundException
   {
      // TODO Auto-generated method stub
      return null;
   }// --------------------------------------------

   /**
    * 
    * @see nyla.solutions.formInjection.dao.BreDAO#constructBRE(int, nyla.solutions.formInjection.data.Questionaire)
    */
   public BRE constructBRE(String aFormTypeID, Questionaire aQuestionaire) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see nyla.solutions.formInjection.dao.BreDAO#constructBRE(nyla.solutions.formInjection.data.Form)
    */
   public BRE constructBRE(Form aForm) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see nyla.solutions.formInjection.dao.BreDAO#selectExpressionByPK(java.lang.Integer)
    */
   public ExpressionBluePrint selectExpressionByPK(Integer aExpressionPK) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see nyla.solutions.formInjection.dao.BreDAO#selectFormTypes()
    */
   public Collection selectFormTypes() throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see nyla.solutions.formInjection.dao.BreDAO#selectOperationBluePrintByPK(java.lang.Integer)
    */
   public OperationBluePrint selectOperationBluePrintByPK(Integer aOperationPK) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see nyla.solutions.formInjection.dao.BreDAO#selectRulesByFormTypeCode(String)
    */
   public Collection selectRulesByFormTypeCode(String aFormTypeID) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see nyla.solutions.formInjection.dao.QuestionDAO#constructQuestioniareByFormTypeCode(java.lang.String)
    */
   public Questionaire constructQuestioniareByFormTypeCode(String aFormTypeName) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see nyla.solutions.formInjection.dao.QuestionDAO#constructQuestioniareByFormTypeCode(int)
    */
   public Questionaire constructQuestioniareByFormTypeCode(int aFormTypePK) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see nyla.solutions.formInjection.dao.QuestionDAO#insert(nyla.solutions.formInjection.data.AttributeFacts)
    */
   public AttributeFacts insert(AttributeFacts aAttributeFacts) throws SQLException, DuplicateRowException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see nyla.solutions.formInjection.dao.QuestionDAO#saveQuestionAttribute(nyla.solutions.formInjection.data.QuestionAttribute)
    */
   public QuestionAttribute saveQuestionAttribute(QuestionAttribute attribute)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see nyla.solutions.formInjection.dao.QuestionDAO#selectFormTypeByCode(java.lang.String)
    */
   public FormType selectFormTypeByCode(String aFormTypeName) throws SQLException, NoDataFoundException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see nyla.solutions.formInjection.dao.QuestionDAO#selectFormTypeByPK(int)
    */
   public FormType selectFormTypeByPK(int aFormTypeID) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @see nyla.solutions.formInjection.dao.QuestionDAO#selectQuestionAttributeByPK(java.lang.Integer, java.lang.Integer, java.lang.String)
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
    * @see nyla.solutions.formInjection.dao.QuestionDAO#saveResponseTable(nyla.solutions.formInjection.data.ResponseTable)
    */
   public ResponseTable saveResponseTable(ResponseTable aResponseTable)
   {
      this.save(aResponseTable);
      
      return aResponseTable;
   }// --------------------------------------------
   /**
    * 
    * @see nyla.solutions.formInjection.dao.QuestionDAO#saveResponseType(nyla.solutions.formInjection.data.ResponseType)
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
