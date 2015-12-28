package org.solutions.form.dao.hibernate;

import java.util.Collection;

import junit.framework.TestCase;
import nyla.solutions.formInjection.ApplicationBuilder;
import nyla.solutions.formInjection.FormSearchCriteria;
import nyla.solutions.formInjection.dao.FormDAO;
import nyla.solutions.formInjection.dao.FormDAOFactory;
import nyla.solutions.formInjection.dao.QuestionDAO;
import nyla.solutions.formInjection.data.Application;
import nyla.solutions.formInjection.data.Form;
import nyla.solutions.formInjection.data.FormType;
import nyla.solutions.formInjection.data.Question;
import nyla.solutions.formInjection.data.ResponseType;
import nyla.solutions.global.patterns.servicefactory.ServiceFactory;
import nyla.solutions.global.security.data.SecurityClient;
import nyla.solutions.global.util.Debugger;

import org.solutions.form.FormUTUtil;



//import org.solutions.patterns.servicefactory.ServiceFactory;
//import org.solutions.security.data.SecurityClient;
//import org.solutions.util.Debugger;
import junit.framework.Assert;

/**
 * <pre>
 * FormHibernateDAOTest contains UNIT Test cases.
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class FormHibernateDAOTest extends TestCase
{

   public FormHibernateDAOTest(String name)
   {
      super(name);
   }

 


   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#deleteForm(nyla.solutions.formInjection.data.Form)}.
    */
   public void testDeleteForm()
   {
      //TODO:fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#deleteFormByPK(int)}.
    */
   public void testDeleteFormByPK()
   {
	   //TODO:fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#insertForm(nyla.solutions.formInjection.data.Form)}.
    */
   public void testInsertForm()
   throws Exception
   {
      Application app = new Application();
      FormType formType = new FormType();
      formType.setFormTypeCode("test");
      app.setFormType(formType);
      
      Assert.assertTrue(app.getFormTypeCode() !=null && app.getFormTypeCode().length() > 0);
      Integer id = new Integer(1);
      
      app.setFormId(id);
      FormDAO formDAO = null;
      try
      {
         formDAO = (FormDAO)ServiceFactory.getInstance().create(FormDAO.class);
         formDAO.insertForm(app);
         
         Form app2 = formDAO.selectFormByPK(id.intValue(),"test");
         
         Assert.assertEquals(id.intValue(), app2.getPrimaryKey());
      }
      finally
      {
         if(formDAO != null)
             formDAO.dispose();
      }
   }// --------------------------------------------


   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#purgeAnswers(java.util.Collection)}.
    */
   public void testPurgeAnswers()
   {
	 //TODO:fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#saveAnswers(nyla.solutions.formInjection.data.Application)}.
    */
   public void testSaveAnswers()
   throws Exception
   {
      FormDAO dao = null;
      try
      {
         dao = FormDAOFactory.createFormDAO();

         ApplicationBuilder builder = new ApplicationBuilder();
         FormUTUtil.constructForm(builder);
         
         
         Form app = builder.getForm();
        
         Assert.assertTrue(app.getFormTypeCode() != null && app.getFormTypeCode().length() > 0);
         
         
         Assert.assertTrue(app.hasAnswers());
         Assert.assertTrue(app.hasQuestionaire());
         
         dao.insertForm(app);
         
         dao.saveAnswers(app);
         
         Form form = dao.selectFormByPK(app.getPrimaryKey(),"test");
         
         
         Assert.assertTrue(form.hasAnswers());
         
         Debugger.dump(form);
         dao.commit();

      }
      finally
      {
         if (dao != null)
            dao.dispose();
      }
   }

   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#saveForm(nyla.solutions.formInjection.data.Form)}.
    */
   public void testSaveForm()
   throws Exception
   {
      ApplicationBuilder builder = new ApplicationBuilder();
      int formPK  = 2;
      FormUTUtil.constructForm(builder, formPK);
      Form form = builder.getForm();
      form.setFormTypeCode("test");
      form.setPrimaryKey(formPK);
      Assert.assertTrue(form.getFormTypeCode() != null && form.getFormTypeCode().length() > 0);
      
      FormDAO dao = null;
      try
      {
         dao = FormDAOFactory.createFormDAO();
         dao.insertForm(form);
         
         
         form = dao.selectFormByPK(formPK,form.getFormTypeCode());
         
         Assert.assertTrue(form.getPrimaryKey() == formPK);
         Assert.assertTrue(form.hasAnswers());
         Assert.assertTrue(form.hasQuestionaire());
         
         dao.commit();
      }
      finally
      {
         if (dao != null)
            dao.dispose();
      }
   }// --------------------------------------------


   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#searchForForms(nyla.solutions.formInjection.FormSearchCriteria)}.
    */
   public void testSearchForForms()
   throws Exception
   {
      FormDAO dao = null;
      try
      {
         dao = FormDAOFactory.createFormDAO();
         SecurityClient user = new SecurityClient();
         user.setLoginID(loginID);
         ApplicationBuilder builder = new ApplicationBuilder();
         int formPK = 4;
         String typeCode = "TEST_SEARCH";
         
         FormUTUtil.constructForm(builder,formPK, typeCode);
         
         Form form = builder.getForm();
         
         Assert.assertTrue(form.getFormTypeCode() !=null && form.getFormTypeCode().length() > 0);
         
         Assert.assertTrue(form.getPrimaryKey() == formPK);
         
         dao.insertForm(form);
         
         FormSearchCriteria formSearchCriteria
         = FormSearchCriteria.searchForTypeCode(user, typeCode);
         
         Collection forms = dao.searchForForms(formSearchCriteria);
         
         Assert.assertTrue(!forms.isEmpty());

         dao.commit();
      }
      finally
      {
         if (dao != null)
            dao.dispose();
      }
   }// --------------------------------------------


   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#selectAnswersByFormAndQuestionAndRow(int, int, java.lang.Integer)}.
    */
   public void testSelectAnswersByFormAndQuestionAndRow()
   throws Exception
   {
      FormDAO dao = null;
      try
      {
         dao = FormDAOFactory.createFormDAO();
         int formPK = 15;
         int questionPK = 10;
         String formTypeCode = "TEST";
         int rowNumber = 0;
         ApplicationBuilder builder = new ApplicationBuilder();
         FormUTUtil.constructFormWithTableQuestion(builder, formPK, formTypeCode,questionPK);
         
         Form form = builder.getForm();
         
         dao.insertForm(form);
         
         dao.commit();
         
         Collection answers = dao.selectAnswersByFormAndQuestionAndRow(formPK, questionPK, new Integer(rowNumber));
         
         Assert.assertTrue(answers != null && !answers.isEmpty());
      }
      finally
      {
         if (dao != null)
            dao.dispose();
      }
   }// --------------------------------------------

   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#selectDeletedAnswers(int)}.
    */
   public void testSelectDeletedAnswers()
   {
	 //TODO:fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#selectFormByPK(int)}.
    */
   public void testSelectFormByPK()
   throws Exception
   {
      FormDAO dao = null;
      try
      {
         dao = FormDAOFactory.createFormDAO();
         ApplicationBuilder builder = new ApplicationBuilder();
         int formPK = 20;
         
         FormUTUtil.constructForm(builder,formPK, "TEST");
         Form form = builder.getForm();
         
         dao.insertForm(form);
         
         Form app = dao.selectFormByPK(form.getPrimaryKey(),"test");
         Assert.assertNotNull(app);
         Assert.assertTrue(app.hasQuestionaire());
         Assert.assertTrue(form.getPrimaryKey() == app.getPrimaryKey());
         dao.commit();
      }
      finally
      {
         if (dao != null)
            dao.dispose();
      }
      
   }

   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#selectManagedForm(int)}.
    */
   public void testSelectManagedFormInt()
   {
	 //TODO:fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#selectManagedForm(int, boolean)}.
    */
   public void testSelectManagedFormIntBoolean()
   {
	 //TODO:fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#selectStatusByName(java.lang.String)}.
    */
   public void testSelectStatusByName()
   {
	 //TODO:fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#selectStatuses()}.
    */
   public void testSelectStatuses()
   {
	 //TODO:fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#setAutoCommit(boolean)}.
    */
   public void testSetAutoCommit()
   {
	 //TODO:fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#updateForm(nyla.solutions.formInjection.data.Form)}.
    */
   public void testUpdateForm()
   {
	 //TODO:fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#constructBRE(int, nyla.solutions.formInjection.data.Questionaire)}.
    */
   public void testConstructBREIntQuestionaire()
   {
	 //TODO:fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#constructBRE(nyla.solutions.formInjection.data.Form)}.
    */
   public void testConstructBREForm()
   {
	 //TODO:fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#selectExpressionByPK(java.lang.Integer)}.
    */
   public void testSelectExpressionByPK()
   {
	 //TODO:fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#selectFormTypes()}.
    */
   public void testSelectFormTypes()
   {
	 //TODO:fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#selectOperationBluePrintByPK(java.lang.Integer)}.
    */
   public void testSelectOperationBluePrintByPK()
   {
	 //TODO:fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#selectRulesByFormTypeCode(int)}.
    */
   public void testSelectRulesByFormTypeID()
   {
	 //TODO:fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#constructQuestioniareByFormTypeCode(java.lang.String)}.
    */
   public void testConstructQuestioniareByFormTypeName()
   {
	 //TODO:fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#constructQuestioniareByFormTypeCode(int)}.
    */
   public void testConstructQuestioniareByFormTypePK()
   {
	 //TODO:fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#insert(nyla.solutions.formInjection.data.AttributeFacts)}.
    */
   public void testInsertAttributeFacts()
   {
	 //TODO:fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#saveQuestionAttribute(nyla.solutions.formInjection.data.QuestionAttribute)}.
    */
   public void testSaveQuestionAttribute()
   {
	 //TODO:fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#selectFormTypeByCode(java.lang.String)}.
    */
   public void testSelectFormTypeByName()
   {
	 //TODO:fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#selectFormTypeByPK(int)}.
    */
   public void testSelectFormTypeByPK()
   {
	 //TODO:fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#selectQuestionAttributeByPK(java.lang.Integer, java.lang.Integer, java.lang.String)}.
    */
   public void testSelectQuestionAttributeByPK()
   {
	 //TODO:fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#selectQuestionsByFormTypeCode(java.lang.String)}.
    */
   public void testSelectQuestionsByFormTypeCode()
   throws Exception
   {
      String formTypeCode = "test";
      
      QuestionDAO dao = null;
      try
      {
         dao = FormDAOFactory.createQuestionDAO();
         
         ResponseType responseType = new ResponseType();
         String code  = "text";
         responseType.setCode(code);
         
         dao.saveResponseType(responseType);
         
         
         Question question = new Question();
         Integer id = new Integer(1);
         Integer questionNumber = new Integer(1);
         String questionText = "Question test";
         Integer sectionNumber = new Integer(1);
         
         question.setQuestionId(id);
         question.setQuestionNumber(questionNumber);
         question.setQuestionText(questionText);
         question.setSectionNumber(sectionNumber);
         question.setResponseType(responseType);
         question.setFormTypeCode(formTypeCode);
         
         dao.saveQuestion(question);
         
         dao.commit();
      }
      finally
      {
         if (dao != null)
            dao.dispose();
      }
      
      FormDAO formDAO = null;
      try
      {
         formDAO = FormDAOFactory.createFormDAO();
         
         Collection questions = formDAO.selectQuestionsByFormTypeCode(formTypeCode);
         
         Assert.assertTrue(Debugger.toString(questions), questions != null && !questions.isEmpty());

      }
      finally
      {
         if (dao != null)
            dao.dispose();
      }
   }// --------------------------------------------

   /**
    * Test method for {@link nyla.solutions.formInjection.dao.hibernate.FormHibernateDAO#selectSectionsByFormTypePK(int)}.
    */
   public void testSelectSectionsByFormTypeCode()
   {
      fail("Not yet implemented"); // TODO
   }
   private Integer formId = new Integer(1);
   private String loginID = "test";
}
