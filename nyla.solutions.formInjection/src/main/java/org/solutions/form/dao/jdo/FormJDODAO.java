package org.solutions.form.dao.jdo;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.solutions.dao.jdo.JDODAO;
import org.solutions.dao.jdo.JDOQueryBuilder;
import org.solutions.data.Data;
import org.solutions.exception.NoDataFoundException;
import org.solutions.form.FormSearchCriteria;
import org.solutions.form.dao.FormDAO;
import org.solutions.form.data.Answer;
import org.solutions.form.data.Form;
import org.solutions.form.data.FormAnswer;
import org.solutions.form.data.FormComponentAttribute;
import org.solutions.form.data.FormHelper;
import org.solutions.form.data.FormStatus;
import org.solutions.form.data.FormType;
import org.solutions.form.data.Application;
import org.solutions.form.data.ManagedForm;
import org.solutions.security.data.SecurityCredential;
import org.solutions.util.Text;

/**
 * <pre>
 * 
 *  FormDAO is a data access object for Form data management
 *  
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public class FormJDODAO extends BreJDODAO implements FormDAO
{
   /**
    * Constructor for FormDAO initalizes internal data settings.
    *  
    */
   protected FormJDODAO()
   {
      super();
      super.setAutoCommit(false);
   }//-------------------------------------------
   /**
    * Constructor for FormDAO initalizes internal data settings.
    * 
    * @param aDAO
    */
   protected FormJDODAO(JDODAO aDAO)
   {
      super(aDAO);
      super.setAutoCommit(false);
   }//--------------------------------------------

   /**
    * Constructor for FormDAO initalizes internal data settings.
    * 
    * @param aUser
    */
   protected FormJDODAO(SecurityCredential aUser)
   {
      super(aUser);
      super.setAutoCommit(false);
   }//--------------------------------------------
   /**
    * 
    * @see org.solutions.form.dao.FormDAO#commit()
    */
   public void commit()
   {
      super.commit();
      
   }//----------------------------------------
   /**
    * 
    * @see org.solutions.form.dao.FormDAO#rollback()
    */
   public void rollback()
   {
      super.rollback();
   }//--------------------------------------------
   /**
    * 
    * @see org.solutions.form.dao.FormDAO#setAutoCommit(boolean)
    */
   public void setAutoCommit(boolean autoCommit)
   {
      super.setAutoCommit(autoCommit);
   }//--------------------------------------------

   public static FormDAO getFormDAOInstance()
   {
      return new FormJDODAO();
   }//--------------------------------------------

   /**
    * 
    * @param aUser
    *           the user
    * @return new instance of the form dao
    */
   public static FormDAO getFormDAOInstance(SecurityCredential aUser)
   {
      return new FormJDODAO(aUser);
   }//--------------------------------------------
   /**
    * 
    * @see org.solutions.form.dao.FormDAO#selectStatusByName(java.lang.String)
    */
   public FormStatus selectStatusByName(String aFormStatusName)
   throws SQLException, NoDataFoundException
   {
      if (aFormStatusName == null)
         throw new IllegalArgumentException("aFormStatus required in FormDAO");

      JDOQueryBuilder query = this.createQueryBuilder(FormStatus.class);
      JDOQueryBuilder notDeletedQuery = query.getColumn("deletedCode").equal(
      Data.NO);

      JDOQueryBuilder nameQuery = query.getColumn("name").trim().toUpperCase().equal(
      aFormStatusName.trim().toUpperCase());
      
      return (FormStatus) ((Collection) this.select(nameQuery.and(notDeletedQuery))).iterator().next();
   }//--------------------------------------------
   /**
    * 
    * @see org.solutions.form.dao.FormDAO#selectStatuses()
    */
   public Collection selectStatuses()
   throws SQLException, NoDataFoundException
   {

      JDOQueryBuilder query = this.createQueryBuilder(FormStatus.class);
      JDOQueryBuilder notDeletedQuery = query.getColumn("deletedCode").equal(
      Data.NO);
      query = query.and(notDeletedQuery);
      return (Collection) this.select(query);
   }//--------------------------------------------

   /**
    * 
    * @see org.solutions.form.dao.FormDAO#insertForm(org.solutions.form.data.Form)
    */
   public Form insertForm(Form form) 
   throws SQLException, NoDataFoundException
   {
      if (form == null)
         throw new IllegalArgumentException("form required in FormDAO");
      
      if (Text.isNull(form.getFormTypeCode()))
         throw new IllegalArgumentException("form.getTypeID() 'FORM_TYPE_ID cannot be less than 1 in FormDAO.insert");
            
      //TODO: form.updateAudit(getUser().getId());
      
      if(form.getStatusPK() < 1)
         form.setStatusPK(
            new Integer(this.selectStatusByName(form.getStatusName()).getPrimaryKey()));

      Map properties = form.clearProperties();
      
      //insert form
      super.insert(form);
      commit();	// make sure we add to db now so we get a generated pk
      
      form.setProperties(properties);
      super.insert(form.getProperties());
     
      Integer formId = form.getFormId();
      Set c = form.getFormAnswers();
      for (Iterator i = c.iterator(); i.hasNext(); ) {
          FormAnswer formAnswer = (FormAnswer) i.next();
          formAnswer.setFormId(formId);
          formAnswer.generateKey();
      }
      
      this.makePersistentAll(c);
      form.setAnswers(c);
      
      /*
      for (Iterator i = form.getProperties().iterator(); i.hasNext(); )
          ((FormProperty) i.next()).setFormId(formId);
      this.makePersistentAll(properties.values());
      form.setProperties(properties);
      */
      return form;
   }//--------------------------------------------
   
   
   
   /**
    * 
    * @see org.solutions.form.dao.FormDAO#saveForm(org.solutions.form.data.Form)
    */
   public Form saveForm(Form aForm)
   throws SQLException, NoDataFoundException
   {
      if(aForm.isNew())
      {
         //insert form
         aForm = this.insertForm(aForm);         
      }
      else
      {
         aForm = this.updateForm(aForm);
      }
      return aForm;
   }//--------------------------------------------
   
   private Application selectQuestionsAndAnswers(ManagedForm managedForm) 
   throws SQLException, NoDataFoundException 
   {
      /* Application form = new Application(managedForm);
       //form.setAccessUser(this.getUser());
       Collection answers = this.selectManagedAnswers(form.getPrimaryKey());
       Questionaire q = this.constructQuestioniareByFormTypeCode(managedForm.getFormTypeCode());
       form.setQuestionaire(q);
       form.addAnswers(answers);
       return form;*/
      return null;
   }// --------------------------------------------
   /**
    * 
    * @see org.solutions.form.dao.FormDAO#selectFormByPK(int)
    */
   public Form selectFormByPK(int formPK, String formTypeCode)
   throws NoDataFoundException, SQLException
   {
       long startTime = System.currentTimeMillis();
       ManagedForm form = selectManagedForm(formPK);
       Application gcsmForm = this.selectQuestionsAndAnswers(form);
       long endTime = System.currentTimeMillis();
       logger.debug("selectFormByPK(): " + (endTime-startTime) + " ms");
       return gcsmForm;
   }//--------------------------------------------

   /**
    * 
    * @see org.solutions.form.dao.FormDAO#selectManagedForm(int)
    */
   public ManagedForm selectManagedForm(int formPK) throws SQLException, NoDataFoundException {
       return selectManagedForm(formPK, false);
   }
   
   /**
    * 
    * @see org.solutions.form.dao.FormDAO#selectManagedForm(int, boolean)
    */
   public ManagedForm selectManagedForm(int formPK, boolean includeDeleted) throws SQLException, NoDataFoundException {
       long startTime = System.currentTimeMillis();
       JDOQueryBuilder query = this.createQueryBuilder(ManagedForm.class);
       JDOQueryBuilder pkQuery = query.getColumn("formId").equal(formPK);
       if (!includeDeleted)
           pkQuery = pkQuery.and(query.getColumn("deletedCode").equal(Data.NO));

       ManagedForm form = (ManagedForm)((Collection)super.select(pkQuery)).iterator().next();
       long endTime = System.currentTimeMillis();
       logger.debug("time to select form: " + (endTime-startTime) + " ms");   
       return form;
   }
   //private Collection selectManagedAnswers(int formPK) throws SQLException {
   ///    return selectManagedAnswers(formPK, false);
   //}
   /**
    * 
    * @see org.solutions.form.dao.FormDAO#selectDeletedAnswers(int)
    */
   public Collection selectDeletedAnswers(int formPK, String formTypeCode) throws SQLException {
       JDOQueryBuilder query = this.createQueryBuilder(Answer.class);
       JDOQueryBuilder formQuery = query.getColumn("formId").equal(formPK);
       formQuery = formQuery.and(query.getColumn("deletedCode").equal(Data.YES));
       Collection answers = null;
       try {
           answers = (Collection)super.select(formQuery);
       }
       catch (NoDataFoundException e) {
           answers = Collections.EMPTY_LIST;
       }
       return answers;
   }
   private Collection selectManagedAnswers(int formPK, boolean includeDeleted) throws SQLException {
       long startTime = System.currentTimeMillis();
       JDOQueryBuilder query = this.createQueryBuilder(Answer.class);
       JDOQueryBuilder formQuery = query.getColumn("formId").equal(formPK);
       if (!includeDeleted) {
           formQuery = formQuery.and(query.getColumn("deletedCode").equal(Data.NO));
       }
       Collection answers = null;
       try {
           answers = (Collection)super.select(formQuery);
       }
       catch (NoDataFoundException e) {
           answers = Collections.EMPTY_LIST;
       }
       long endTime = System.currentTimeMillis();
       logger.debug("time to select answers: " + (endTime-startTime) + " ms");
       return answers;
   }
   /**
    * 
    * @see org.solutions.form.dao.FormDAO#purgeAnswers(java.util.Collection)
    */
   public void purgeAnswers(Collection answers) throws SQLException, NoDataFoundException {
       super.deleteAll(answers);
   }// --------------------------------------------


   /**
    * 
    * @see org.solutions.form.dao.FormDAO#saveAnswers(org.solutions.form.data.Application)
    */
   public void saveAnswers(Form aForm) throws SQLException {
       Collection oldAnswers = this.selectManagedAnswers(aForm.getPrimaryKey(), true);
       Map newAnswers = aForm.getFormAnswerMap();

      //FormAnswer a = aForm.getAnswer(new Integer(22), 1, 7);

       /*
       for (Iterator i = oldAnswers.iterator(); i.hasNext(); ) {
           Answer a = (Answer) i.next();
           System.out.println(a.getAnswerId());
       }
       for (Iterator i = newAnswers.keySet().iterator(); i.hasNext(); ) {
           String key = (String) i.next();
           Answer a = (Answer) newAnswers.get(key);
           String answerId = a.getAnswerId();
           System.out.println(key + "=" + key + ", answerId=" + answerId);
       }
       */
       HashSet answers = new HashSet();
       for (Iterator i = oldAnswers.iterator(); i.hasNext(); ) {
           Answer answer = (Answer) i.next();
           Answer newAnswer = (Answer) newAnswers.remove(answer.getAnswerId());
           //do not delete answers from a submitted or completed form
           if (newAnswer == null && aForm.getFormStatusId().intValue() < 2) {
               answer.setDeletedCode("Y");
           }
           if (newAnswer != null) {
               answer.copy(newAnswer);
               copyAttributes(newAnswer.getAnswerProps(), answer.getAnswerProps());
           }
           answers.add(answer);
       }
       // add the rest of the answers for insert
       answers.addAll(newAnswers.values());
       
       super.makePersistentAll(answers); //fix debug
       aForm.setAnswers(answers);
   }
   /**
    * 
    * @see org.solutions.form.dao.FormDAO#updateForm(org.solutions.form.data.Form)
    */
   public Form updateForm(Form aForm)
   throws SQLException, NoDataFoundException
   {
      if (aForm == null)
         throw new IllegalArgumentException("aForm required in FormDAO.update");

      ManagedForm form = this.selectManagedForm(aForm.getPrimaryKey(), true);
      logger.debug("Form status: "+form.getFormStatusId());
      //TODO: form.copy((ManagedForm) aForm.getManagedObject());
        
      // properties aren't wrapped
      copyAttributes(aForm.getFormProps(), form.getFormProps());
      
      Application managedForm = new Application(form);
      //Map formPropsCopy = new HashMap(form.getFormProps());
      form.getFormProps().clear();
      super.makePersistent(form);
      
      //TODO: managedForm.addAllProperties(formPropsCopy);
      super.makePersistentAll(managedForm.getFormProps().values());
      
      // reset question & answer mappings, copy over the form context
      // so we don't lose dynamic choices
      managedForm.setQuestionaire(aForm.getQuestionaire());
      managedForm.setFormContext(aForm.getFormContext());
      managedForm.setAnswers(aForm.getFormAnswers());
      this.saveAnswers(managedForm);
      return managedForm;
      
   }//--------------------------------------------

   private void copyAttributes(Map src, Map dest) {
       if (src == dest) {
           return;
       }
       for (Iterator i = dest.keySet().iterator(); i.hasNext(); ) {
           String key = (String) i.next();
           FormComponentAttribute oldProp = (FormComponentAttribute) dest.get(key);
           FormComponentAttribute newProp = (FormComponentAttribute) src.remove(key);
           if (newProp != null)
               oldProp.copy(newProp);
           else
               oldProp.delete();
       }
       dest.putAll(src);
   }
   
   /**
    * 
    * @see org.solutions.form.dao.FormDAO#deleteFormByPK(int)
    */
   public void deleteFormByPK(int aFormPK, String formTypeCode)
   throws NoDataFoundException, SQLException
   {
      deleteForm(this.selectFormByPK(aFormPK,formTypeCode));
   }//-------------------------------------------
         
   /**
    * 
    * @see org.solutions.form.dao.FormDAO#deleteForm(org.solutions.form.data.Form)
    */
   public Form deleteForm(Form form)
   throws NoDataFoundException, SQLException
   {
      Form managedForm = this.selectFormByPK(form.getPrimaryKey(),form.getFormTypeCode());
      managedForm.setDeletedCode(Data.YES);
      //TODO: this.makePersistent(managedForm.getManagedObject());
      return managedForm;
      
   }//--------------------------------------------   
   /**
    * 
    * @see org.solutions.form.dao.FormDAO#searchForForms(org.solutions.form.FormSearchCriteria)
    */
   public Collection searchForForms(FormSearchCriteria aSearchCriteria)
   throws NoDataFoundException, SQLException
   {
      if (aSearchCriteria == null)
         throw new IllegalArgumentException(
         "aSearchCriteria required in FormDAO.searchForForms");
      
      JDOQueryBuilder formQuery = this.createQueryBuilder(ManagedForm.class);
      JDOQueryBuilder resultQuery = null;
      JDOQueryBuilder notdeletedQuery = formQuery.getColumn("deletedCode").equal(Data.NO);
      
      switch(aSearchCriteria.getType())
      {
//         case FormSearchCriteria.BY_CreateUser_AND_FormType: 
//            resultQuery = createByCreateUserQuery(aSearchCriteria, formQuery)
//                        .and(
//                              formQuery.getColumn("type").getColumn("name")
//                                   .equal(aSearchCriteria.getFormTypeName()));               
//         break;                  
//         case FormSearchCriteria.BY_CreateUser:
//            resultQuery = createByCreateUserQuery(aSearchCriteria, formQuery);
//         break;   
//         case FormSearchCriteria.BY_FormType:
//            resultQuery = createByFormTypeNameQuery(aSearchCriteria, formQuery);
//         break;   
         case FormSearchCriteria.BY_FormTypeCode:
            resultQuery = createByFormTypeCodeQuery(aSearchCriteria, formQuery);
         break;       
//         case FormSearchCriteria.BY_CreateUserOrNotStatus:
//            resultQuery = createByCreateUserOrNotStatusQuery(aSearchCriteria, formQuery);
//         break;             
         default: throw new NoDataFoundException("Unknown form search criteria type "+aSearchCriteria.getType());
      }
      
      
      return (Collection)super.select(resultQuery.and(notdeletedQuery));
      
   }//--------------------------------------------
   /**
    * 
    * @see org.solutions.form.dao.FormDAO#selectAnswersByFormAndQuestionAndRow(int, int, java.lang.Integer)
    */
   public Collection selectAnswersByFormAndQuestionAndRow(int aFormPK, int aQuestionPK, Integer aRowNumber)
   throws SQLException, NoDataFoundException
   {
      JDOQueryBuilder answerQuery = this.createQueryBuilder(Answer.class);
      JDOQueryBuilder formQuery = answerQuery.getColumn("formId").equal(aFormPK);
      JDOQueryBuilder questionQuery = answerQuery.getColumn("questionId").equal(aQuestionPK);
      
      //TODO: make sure row is not deleted
      JDOQueryBuilder rowQuery = answerQuery.getColumn("row").equal(aRowNumber);
      
      return (Collection)select(formQuery
                                  .and(questionQuery)
                                  .and(rowQuery)
                                  .and(this.createNotDeletedQuery(answerQuery)));
   }//--------------------------------------------
   /**
    * @param aSearchCriteria
    * @param formQuery
    * @return
    */
//   
//   private JDOQueryBuilder createByCreateUserQuery(FormSearchCriteria aSearchCriteria, JDOQueryBuilder formQuery)
//   {
//      return formQuery.getColumn("createUserID").equal(aSearchCriteria.getUserPK());
//   }// --------------------------------------------
   /**
    * 
    * @param aSearchCriteria the search criteria
    * @param aFormQuery the form query 
    * @return JDOQueryBuilder
    */
//   private JDOQueryBuilder createByFormTypeNameQuery(FormSearchCriteria aSearchCriteria, JDOQueryBuilder aFormQuery)
//   {
//       FormType type = FormHelper.getFormType(aSearchCriteria.getFormTypeName());
//       return aFormQuery.getColumn("formTypeId").equal(type.getPrimaryKey());
//   }//--------------------------------------------
   /**
    * 
    * @param aSearchCriteria
    * @param aFormQuery the form query
    * @return the JDOQueryBuilder instance
    */
   private JDOQueryBuilder createByFormTypeCodeQuery(FormSearchCriteria aSearchCriteria, JDOQueryBuilder aFormQuery)
   {  
       FormType type = FormHelper.getFormType(aSearchCriteria.getFormTypeCode());
      return aFormQuery.getColumn("formTypeId").equal(type.getFormTypeId());
   }//--------------------------------------------
//   private JDOQueryBuilder createByCreateUserOrNotStatusQuery(FormSearchCriteria aSearchCriteria, JDOQueryBuilder aFormQuery)
//   {
//      FormStatus status = FormHelper.getFormStatus(aSearchCriteria.getStatusName());
//      JDOQueryBuilder createUserQuery =aFormQuery.getColumn("createUserID").equal(aSearchCriteria.getUserPK());
//      JDOQueryBuilder notStatus = aFormQuery.getColumn("formStatusId").equal(status.getPrimaryKey()).not();
//      
//      return createUserQuery.or(notStatus).distinct();
//   }//--------------------------------------------
}
