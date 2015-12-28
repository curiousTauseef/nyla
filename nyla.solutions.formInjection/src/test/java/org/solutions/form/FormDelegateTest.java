package org.solutions.form;

import java.util.Hashtable;
import java.util.TreeSet;

import junit.framework.Assert;
import junit.framework.TestCase;
import nyla.solutions.formInjection.ApplicationBuilder;
import nyla.solutions.formInjection.BRE;
import nyla.solutions.formInjection.FormDelegate;
import nyla.solutions.formInjection.bre.ExpressionBluePrint;
import nyla.solutions.formInjection.bre.Rule;
import nyla.solutions.formInjection.dao.BreDAO;
import nyla.solutions.formInjection.dao.FormDAOFactory;
import nyla.solutions.formInjection.data.Answer;
import nyla.solutions.formInjection.data.Form;
import nyla.solutions.formInjection.data.FormAnswer;
import nyla.solutions.formInjection.data.FormColumn;
import nyla.solutions.formInjection.data.FormQuestion;
import nyla.solutions.formInjection.data.FormType;
import nyla.solutions.formInjection.data.Question;
import nyla.solutions.formInjection.data.QuestionChoice;
import nyla.solutions.formInjection.data.Questionaire;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.security.data.LoginCredential;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.xml.XML;

public class FormDelegateTest extends TestCase
{

   protected void setUp() throws Exception
   {
      LoginCredential loginCredential = new LoginCredential();
      loginCredential.setLoginID("junit");
      formDelegate = new FormDelegate(loginCredential);
   }//--------------------------------------------
   public FormDelegateTest(String name)
   {
      super(name);
   }//--------------------------------------------

   public void testBre() 
   throws Exception
   {
      TreeSet rules = new TreeSet();
      
      ExpressionBluePrint expBluePrint = new ExpressionBluePrint();
      expBluePrint.setCode("current-time");
      expBluePrint.setColumnNumber(new Integer(1));
      expBluePrint.setInputArgument1("1");
      
      Rule rule1 = new Rule();
      rule1.setExecuteOrderNumber(1);
      rule1.setExpectedBooleanCode("true");
      rule1.setExpressionBluePrint(expBluePrint);
      rule1.setFormTypeID(1);
      rule1.setId("1");
      rules.add(rule1);
      
      Rule rule2 = new Rule();
      rule2.setExecuteOrderNumber(2);
      rule2.setExpectedBooleanCode("true");
      rule2.setExpressionBluePrint(expBluePrint);
      rule2.setFormTypeID(2);
      rule2.setId("2");
      rules.add(rule2);
      
      Debugger.println(XML.getInterpreter().toXML(rules));
      
      BreDAO dao = null;
      try
      {
         dao = FormDAOFactory.createBreDAO();
         
         Form form = this.formDelegate.retrieveNewForm(formTypeCode);
                  
         Debugger.dump(form);
         
         //test date question
         FormQuestion formQuestion = form.findQuestionByID(dateQuestionID);                  
         Assert.assertTrue(formQuestion.getAnswerValue() != null && formQuestion.getAnswerValue().length() > 0);
         
         //test state question
         FormQuestion stateQuestion = form.findQuestionByID(this.stateQuestionID);                  
         Assert.assertTrue(stateQuestion.hasChoices());
         QuestionChoice choice = (QuestionChoice)stateQuestion.getChoices().iterator().next();
         Assert.assertTrue(choice != null);
         Assert.assertTrue(choice.getKey() != null);
         
         
         
         Assert.assertTrue(form.getFormTypeCode(),form.getFormTypeCode() != null && form.getFormTypeCode().length() > 0);
         Assert.assertNotNull(form.getFormType());
         BRE bre = dao.constructBRE(form);
         
         Assert.assertNotNull(bre);
         
         this.formDelegate.applyBusinessRules(form);
         
         Debugger.println("value="+formQuestion.getAnswerValue());
         
         FormAnswer answer = formQuestion.getAnswer();
         
         Assert.assertTrue(answer.getResponseType().isAnswerable());
         Assert.assertTrue(answer.getResponseType().isAnswerableNonSelectable());
         
      }
      catch(RuntimeException e)
      {
        throw e;
      }      
      catch(Exception e)
      {
         throw new SystemException(e.getMessage(),e);
      }
      finally
      {
         if(dao != null)
            dao.dispose();
      }
      

   }//--------------------------------------------
   public void testConstructQuestioniareString()
   throws Exception
   {
      try
      {
         Questionaire test = new Questionaire();
         
         
         Question question = new Question();
         
         question.setId("1");
         question.setQuestionText("What are you doing?");
         ApplicationBuilder formBuilder =  new ApplicationBuilder();
         FormUTUtil.constructForm(formBuilder);
         
         Form form = formBuilder.getForm();
         Debugger.println(this,XML.getInterpreter().toXML(form));
         
         Hashtable list = new Hashtable();
         list.put(question.getKey(),question);
               
         test.setQuestions(list);
         Debugger.println(this,XML.getInterpreter().toXML(test));
         Questionaire questionaire = formDelegate.constructQuestioniare(this.formTypeCode);
         
         Assert.assertNotNull(questionaire);
         Debugger.dump(questionaire);
         Assert.assertTrue(!questionaire.getQuestions().isEmpty());
         
         Debugger.println(this, "questionaire.getSectionMap()="+questionaire.getSectionMap());
         Assert.assertNotNull(questionaire.getSectionMap());
         Assert.assertNotNull(questionaire.getSection(new Integer(1)));
         Assert.assertTrue(!questionaire.getSectionMap().isEmpty());
         
      }
      catch (Throwable e)
      {
         // TODO Auto-generated catch block
         throw new SystemException(Debugger.stackTrace(e));
      }
   }//--------------------------------------------

   public void testNewForm() 
   throws Exception
   {
      FormType formType = new FormType();
      Debugger.println(this,XML.getInterpreter().toXML(formType));
      
      Form newForm = this.formDelegate.retrieveNewForm(formTypeCode);
      
      Integer id = new Integer(1);
      Assert.assertNotNull(newForm.getQuestionaire());
      Assert.assertNotNull(newForm.getFormType());
       
      nyla.solutions.formInjection.data.FormQuestion formQuestion =newForm.getFormQuestion(id); 
      Assert.assertNotNull(formQuestion);
      formQuestion.getAnswer().setValue("1");
      
      
      Debugger.println(this, "newForm.findSectionByNumber(1)="+newForm.findSectionByNumber("1"));
      Assert.assertNotNull(newForm.findSectionByNumber("1"));
      
   }//--------------------------------------------
   public void testEdit() 
   throws Exception
   {
      Form form = this.formDelegate.retrieveNewForm(formTypeCode);
      
      Assert.assertTrue(form.isNew());
      //answer second question
      form.findQuestionByID(1).setAnswer("Answer 1");
      form.findQuestionByID(2).setAnswer("Answer 2");
      Assert.assertTrue(form.findQuestionByID(2).getAnswerValue().equals("Answer 2"));
      
      form = this.formDelegate.create(form);
      
      Assert.assertTrue(!form.isNew());
      
      form = this.formDelegate.edit(form);
      
      Assert.assertTrue(form.findQuestionByID(2).getAnswerValue().equals("Answer 2"));
   }//--------------------------------------------
//   public void testHTTPOperation()
//   throws Exception
//   {
//      Form form = this.formDelegate.retrieveNewForm(formTypeCode);
//      
//      FormQuestion formQuestion = form.findQuestionByID(appointmentQID);
//      FormColumn formColumn = formQuestion.getFormColumn(1);
//      
//      Assert.assertTrue(formColumn.hasChoices());
//      
//      Answer answer = formQuestion.getAnswer(0,1);
//      
//      Assert.assertNotNull(answer);
//   }// --------------------------------------------

   private Integer appointmentQID  = new Integer(9);
   private int stateQuestionID = 7;
   private int dateQuestionID = 1;
   private String formTypeCode = "registration";
   private FormDelegate formDelegate = null;
}
