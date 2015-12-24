package org.solutions.form.dao.hibernate;

import java.util.Collection;

import junit.framework.TestCase;
import nyla.solutions.global.patterns.servicefactory.ServiceFactory;
import nyla.solutions.global.security.data.SecurityClient;
import nyla.solutions.global.util.Debugger;

import org.solutions.form.ApplicationBuilder;
import org.solutions.form.FormSearchCriteria;
import org.solutions.form.FormUTUtil;
import org.solutions.form.dao.FormDAO;
import org.solutions.form.dao.FormDAOFactory;
import org.solutions.form.dao.QuestionDAO;
import org.solutions.form.data.Application;
import org.solutions.form.data.Form;
import org.solutions.form.data.Question;
import org.solutions.form.data.ResponseType;
//import org.solutions.patterns.servicefactory.ServiceFactory;
//import org.solutions.security.data.SecurityClient;
//import org.solutions.util.Debugger;
import nyla.solutions.global.exception.NoDataFoundException;

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
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#deleteForm(org.solutions.form.data.Form)}.
    */
   public void testDeleteForm()
   {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#deleteFormByPK(int)}.
    */
   public void testDeleteFormByPK()
   {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#insertForm(org.solutions.form.data.Form)}.
    */
   public void testInsertForm()
   throws Exception
   {
      Application app = new Application();
      Integer id = new Integer(1);
      
      app.setFormId(id);
      FormDAO formDAO = null;
      try
      {
         formDAO = (FormDAO)ServiceFactory.getInstance().create(FormDAO.class);
         formDAO.insertForm(app);
         
         Form app2 = formDAO.selectFormByPK(id.intValue(),"test");
         
         Assert.assertEquals(id, app2.getId());
      }
      finally
      {
         if(formDAO != null)
             formDAO.dispose();
      }
   }// --------------------------------------------


   /**
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#purgeAnswers(java.util.Collection)}.
    */
   public void testPurgeAnswers()
   {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#saveAnswers(org.solutions.form.data.Application)}.
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
         
         Assert.assertTrue(app.hasAnswers());
         Assert.assertTrue(app.hasQuestionaire());
         
         dao.insertForm(app);
         
         dao.saveAnswers(app);
         
         Form form = dao.selectFormByPK(app.getPrimaryKey(),"test");
         
         Assert.assertEquals(formId, form.getId());
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
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#saveForm(org.solutions.form.data.Form)}.
    */
   public void testSaveForm()
   throws Exception
   {
      ApplicationBuilder builder = new ApplicationBuilder();
      int formPK  = 2;
      FormUTUtil.constructForm(builder, formPK);
      Form form = builder.getForm();
      
      
      FormDAO dao = null;
      try
      {
         dao = FormDAOFactory.createFormDAO();
         dao.insertForm(form);
         
         
         form = dao.selectFormByPK(formPK,"test");
         
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
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#searchForForms(org.solutions.form.FormSearchCriteria)}.
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
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#selectAnswersByFormAndQuestionAndRow(int, int, java.lang.Integer)}.
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
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#selectDeletedAnswers(int)}.
    */
   public void testSelectDeletedAnswers()
   {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#selectFormByPK(int)}.
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
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#selectManagedForm(int)}.
    */
   public void testSelectManagedFormInt()
   {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#selectManagedForm(int, boolean)}.
    */
   public void testSelectManagedFormIntBoolean()
   {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#selectStatusByName(java.lang.String)}.
    */
   public void testSelectStatusByName()
   {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#selectStatuses()}.
    */
   public void testSelectStatuses()
   {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#setAutoCommit(boolean)}.
    */
   public void testSetAutoCommit()
   {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#updateForm(org.solutions.form.data.Form)}.
    */
   public void testUpdateForm()
   {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#constructBRE(int, org.solutions.form.data.Questionaire)}.
    */
   public void testConstructBREIntQuestionaire()
   {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#constructBRE(org.solutions.form.data.Form)}.
    */
   public void testConstructBREForm()
   {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#selectExpressionByPK(java.lang.Integer)}.
    */
   public void testSelectExpressionByPK()
   {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#selectFormTypes()}.
    */
   public void testSelectFormTypes()
   {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#selectOperationBluePrintByPK(java.lang.Integer)}.
    */
   public void testSelectOperationBluePrintByPK()
   {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#selectRulesByFormTypeCode(int)}.
    */
   public void testSelectRulesByFormTypeID()
   {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#constructQuestioniareByFormTypeCode(java.lang.String)}.
    */
   public void testConstructQuestioniareByFormTypeName()
   {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#constructQuestioniareByFormTypeCode(int)}.
    */
   public void testConstructQuestioniareByFormTypePK()
   {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#insert(org.solutions.form.data.AttributeFacts)}.
    */
   public void testInsertAttributeFacts()
   {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#saveQuestionAttribute(org.solutions.form.data.QuestionAttribute)}.
    */
   public void testSaveQuestionAttribute()
   {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#selectFormTypeByCode(java.lang.String)}.
    */
   public void testSelectFormTypeByName()
   {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#selectFormTypeByPK(int)}.
    */
   public void testSelectFormTypeByPK()
   {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#selectQuestionAttributeByPK(java.lang.Integer, java.lang.Integer, java.lang.String)}.
    */
   public void testSelectQuestionAttributeByPK()
   {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#selectQuestionsByFormTypeCode(java.lang.String)}.
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
    * Test method for {@link org.solutions.form.dao.hibernate.FormHibernateDAO#selectSectionsByFormTypePK(int)}.
    */
   public void testSelectSectionsByFormTypeCode()
   {
      fail("Not yet implemented"); // TODO
   }
   private Integer formId = new Integer(1);
   private String loginID = "test";
}
