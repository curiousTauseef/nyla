package nyla.solutions.formInjection.bre.operation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import nyla.solutions.formInjection.FormGuide;
import nyla.solutions.formInjection.UserDAO;
import nyla.solutions.formInjection.bre.Operation;
import nyla.solutions.formInjection.bre.OperationBluePrint;
import nyla.solutions.formInjection.bre.OperationParameter;
import nyla.solutions.formInjection.dao.BreDAO;
import nyla.solutions.formInjection.dao.FormDAOFactory;
import nyla.solutions.formInjection.data.Form;
import nyla.solutions.formInjection.data.FormAnswer;
import nyla.solutions.formInjection.data.FormColumn;
import nyla.solutions.formInjection.data.FormComponent;
import nyla.solutions.formInjection.data.FormQuestion;
import nyla.solutions.formInjection.data.FormRow;
import nyla.solutions.formInjection.data.FormTable;
import nyla.solutions.formInjection.data.QuestionChoice;
import nyla.solutions.global.data.Criteria;
import nyla.solutions.global.data.Data;
import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.exception.SetupException;
import nyla.solutions.global.operations.logging.Log;
import nyla.solutions.global.security.data.SecurityCredential;
import nyla.solutions.global.security.user.data.User;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Organizer;
import nyla.solutions.global.util.Text;


/**
 * <pre>
 * 
 *  
 *   AbstractOperation provides a set of functions to assist Form Operations
 *   
 *  
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public abstract class AbstractOperation extends Criteria implements Operation
{

   /**
    * Constructor for AbstractOperation initializes internal data settings.
    *  
    */
   public AbstractOperation()
   {
      super();
   }//--------------------------------------------
   /**
    * 
    * @param aFormComponent the form column
    * @return form column
    */
   protected FormColumn retrieveFormColumn (FormComponent aFormComponent)
   {
      if (aFormComponent == null)
         throw new IllegalArgumentException(
         "aFormComponent required in AbstractOperation.retrieveFormColumn");
      
      if (!(aFormComponent instanceof FormColumn))
      {
         throw new SetupException(getClass().getName()+" must executed for a response type of a column");
      }
      
      return (FormColumn) aFormComponent;
      
   }//--------------------------------------------
   /**
    * @param aFormComponent the form component
    * @return FormQuestion
    */
   protected FormQuestion retrieveFormQuestion(FormComponent aFormComponent)
   {
      if (aFormComponent == null)
         throw new IllegalArgumentException(
         "aFormComponent required in PopulateReasonChoicesOperation.execute");
      
      if(!(aFormComponent instanceof FormQuestion))
         throw new SetupException(getClass().getName()+" must executing for a response type not a rule");
      
      FormQuestion formQuestion = (FormQuestion)aFormComponent;
      return formQuestion;
   }//--------------------------------------------
   /**
    * @param aForm
    *           the form component
    * @return retrieve form from form component
    */
   protected Form retrieveForm(FormComponent aFormComponent)
   {
      if (aFormComponent == null)
         throw new IllegalArgumentException("aForm required in "
         + getClass().getName() + ".execute(aForm)");

      Form form = null; 
      if(aFormComponent instanceof Form)
      {
         form =  (Form)aFormComponent;
      }
      else if(aFormComponent instanceof FormColumn)
      {
         FormColumn formColumn = (FormColumn) aFormComponent;
         
         form = formColumn.retrieveForm();
         if (form == null)
            throw new IllegalArgumentException(
            "formColumn.retrieveForm required in AbstractOperation.retrieveForm");
         
      }
      else if(aFormComponent instanceof FormQuestion)
      {
         FormQuestion formQuestion = (FormQuestion)aFormComponent;
         form = formQuestion.retrieveForm();
         if (form == null)
            throw new IllegalArgumentException(
            "formQuestion.retrieveForm() required in AbstractOperation.retrieveForm");
      }
      else 
      {
         throw new IllegalArgumentException("aForm not an instance of Form or FormQuestion");
      }
         
      if(form.getAccessUser() == null)
      {
         if(Config.getPropertyBoolean("access.user.required",false).booleanValue())
         {
            throw new IllegalArgumentException("Form access user not provided");
         }
      }
      
      return form;
   } //--------------------------------------------
   /**
    * 
    * @param aFormComponent
    *           the form component
    * @return retrieveTargetFormQuestion((Form)aFormComponent)
    */
   protected FormQuestion retrieveTargetFormQuestion(FormComponent aFormComponent)
   {
         Form form = retrieveForm(aFormComponent);

         return retrieveTargetFormQuestion(form);
   }//--------------------------------------------
   /**
    * 
    * @see nyla.solutions.formInjection.bre.Operation#setPrimaryKey(int)
    */
   public void setPrimaryKey(int aPrimaryKey)
   {
      super.setPrimaryKey(aPrimaryKey);
   }//--------------------------------------------

   /**
    * 
    * @see nyla.solutions.formInjection.bre.Operation#setPrimaryKey(java.lang.Integer)
    */
   public void setPrimaryKey(Integer aPrimaryKey)
   {
      if (aPrimaryKey == null)
         throw new IllegalArgumentException(
         "aPrimaryKey required in AbstractOperation");

      super.setPrimaryKey(aPrimaryKey.intValue());
   }//--------------------------------------------

   /**
    * 
    * @see nyla.solutions.formInjection.bre.Operation#initialize(nyla.solutions.formInjection.bre.OperationParameter[])
    */
   public void initialize(OperationParameter[] aOperationParameters)
   {
      if (aOperationParameters == null || aOperationParameters.length == 0)
         return; //nothing to initialize

      this.parameters = new ArrayList(Arrays.asList(aOperationParameters));
   }//--------------------------------------------

   /**
    * 
    * @see nyla.solutions.formInjection.bre.Operation#removeParameter(nyla.solutions.formInjection.bre.OperationParameter)
    */
   public boolean removeParameter(OperationParameter aOperationParameter)
   {
      if (this.parameters == null)
         return false;

      return this.parameters.remove(aOperationParameter);
   }//--------------------------------------------

   /**
    * 
    * @see nyla.solutions.formInjection.bre.Operation#getName()
    */
   public String getName()
   {
      return this.name;
   }//--------------------------------------------

   /**
    * 
    * @see nyla.solutions.formInjection.bre.Operation#setName(java.lang.String)
    */
   public void setName(String aName)
   {
      this.name = aName;
   }//--------------------------------------------
   /**
    * 
    * @param aOperationPK the operation primary key
    * @param aUser the user information
    * @return OperationBluePrint matching the key
    * @throws Exception
    */
   protected OperationBluePrint retrieveOperationBluePrint(Integer aOperationPK, SecurityCredential aUser)
   throws Exception
   {
      BreDAO dao = null;
      try
      {
         dao = FormDAOFactory.createBreDAO();
         
         OperationBluePrint bluePrint = dao.selectOperationBluePrintByPK(aOperationPK);
         dao.commit();
         return bluePrint;
      }
      catch (Exception e) {
          if (dao != null)
              dao.rollback();
          throw e;
      }
      finally
      {
         if(dao != null)
            dao.dispose();
      }
   }//--------------------------------------------
   /**
    * 
    * @param aParameterName
    *           the parameter name
    * @return collection of parameters that have the property name
    */
   protected Collection findParametersByName(String aParameterName)
   {
      if (aParameterName == null)
         throw new IllegalArgumentException(
         "aParameterName required in AbstractOperation.findParametersByName");

      if (this.parameters == null)
         return null;

      OperationParameter operationParameter = null;
      ArrayList results = new ArrayList(this.parameters.size());
      int number = Data.NULL;
      for (Iterator i = this.parameters.iterator(); i.hasNext();)
      {
         operationParameter = (OperationParameter) i.next();

         //add operation at index in array list if found
         if (aParameterName.equals(operationParameter.getName()))
         {
            number = operationParameter.getNumber();
            
            if(number >= this.parameters.size())
            {
               throw new SetupException("Operation input paramater number "+number
               +" greater than or equal to the number of operation parameters "+this.parameters.size()
               +" for operation name="+this.name+" pk="+this.getPrimaryKey());
            }
            results.add(operationParameter);
         }
      }
      results.trimToSize();
      return results;
   }//--------------------------------------------
   /**
    * 
    * @param aName the operation name
    * @return single operation parameter
    */
   protected OperationParameter findSingleParameterByName(String aName)
   {
      Collection inputParameters = this
      .findParametersByName(aName);
      if (inputParameters == null || inputParameters.isEmpty())
      {
         throw new SetupException("No operation input parameter \""
         + aName + "\" provider for "
         + getClass().getName()+" operationID="+this.getPrimaryKey());
      }
      
      if (inputParameters.size() > 1)
      {
         throw new SetupException("Only one Operation input parameter \""
         + aName + "\" is allowed in "
         + getClass().getName());
      }
      
      return (OperationParameter)inputParameters.iterator().next();
   }//--------------------------------------------
   /**
    * 
    * @param aFormComponent the form
    * @return colleciton of FormQuestion(s)
    * @throws NoDataFoundException
    */
   protected Collection retrieveTargetFormQuestionsByTrueAttribute(FormComponent aFormComponent)
   throws NoDataFoundException
   {
      OperationParameter parameter = this.findSingleParameterByName(FormGuide.TARGET_TRUE_QUESTION_PROP_BOOL);
      
      if(parameter.getValue() == null || Text.isNull(parameter.getValue().toString()))
         throw new SetupException("Operation parameter value cannot be null for "+parameter);
      
      logger.debug("Operation parameter="+parameter);
      Form form = this.retrieveForm(aFormComponent);
      

      Collection formQuestions = form.findQuestionsWithAttribute(parameter.getValue().toString(),Boolean.TRUE);
      
      if(formQuestions == null || formQuestions.isEmpty())
         throw new NoDataFoundException("No question found with attribute "+(parameter.getValue()));
         
       return formQuestions;
   }//--------------------------------------------
   /**
    * 
    * @param aFormComponent the form
    * @return FormQuestion)retrieveTargetFormQuestionsByTrueAttribute(aFormComponent).iterator().next();
    * @throws NoDataFoundException
    */
   protected FormQuestion retrieveTargetFormQuestionByTrueAttribute(FormComponent aFormComponent)
   throws NoDataFoundException
   {
      return (FormQuestion)retrieveTargetFormQuestionsByTrueAttribute(aFormComponent).iterator().next();
   }//--------------------------------------------
   /**
    * Response PK specified by FormGuide.TARGET_RESPONSE_TYPE_PK_PROP_BOOL
    * @param aFormComponent the form
    * @return collection of FormQuestion 
    * @throws NoDataFoundException
    */
   protected Collection retrieveQuestionsWithResponseType(FormComponent aFormComponent)
   throws NoDataFoundException
   {
      OperationParameter parameter = this.findSingleParameterByName(FormGuide.TARGET_RESPONSE_TYPE_PK_PROP_BOOL);
      
      if(parameter.getValue() == null || Text.isNull(parameter.getValue().toString()))
         throw new SetupException("Operation parameter value cannot be null for "+parameter);

      logger.debug("Operation parameter="+parameter);
      Form form = this.retrieveForm(aFormComponent);
      
      return form.findQuestionsWithResponseTypePK(new Integer(parameter.getValue().toString()));
   }//--------------------------------------------
   protected Collection retrieveTargetColumnsByTrueAttribute(FormComponent aFormComponent)
   {
      OperationParameter parameter = this.findSingleParameterByName(FormGuide.TARGET_TRUE_COLUMN_ATTRIB_BOOL);
      
      if(parameter.getValue() == null || Text.isNull(parameter.getValue().toString()))
         throw new SetupException("Operation parameter value cannot be null for "+parameter);
      
      logger.debug("Operation parameter="+parameter);
      Form form = this.retrieveForm(aFormComponent);
      
      return form.findColumnsWithAttribute(parameter.getValue().toString(),Boolean.TRUE);
   }//--------------------------------------------
   /**
    * 
    * @param aForm
    *           the form object
    * @return the form question specify by the FormGuide.TARGET_QUESTION_PROP
    */
   protected FormQuestion retrieveTargetFormQuestion(Form aForm)
   {
      try
      {
         Collection inputParameters = this
         .findParametersByName(FormGuide.TARGET_QUESTION_PK_PROP);
         if (inputParameters == null || inputParameters.isEmpty())
         {
            throw new SetupException("Operation input parameter \""
            + FormGuide.TARGET_QUESTION_PK_PROP + "\" not provide in "
            + getClass().getName());
         }

         if (inputParameters.size() > 1)
         {
            throw new SetupException("Only one Operation input parameter \""
            + FormGuide.TARGET_QUESTION_PK_PROP + "\" is allowed in "
            + getClass().getName());
         }

         OperationParameter operationParameter = (OperationParameter) inputParameters
         .iterator().next();

         Object targetQuestionValue = operationParameter.getValue();
         if (targetQuestionValue == null)
            new SetupException(
            "Unexpected null target Question PK operation parameter "
            + Debugger.toString(operationParameter) + " class "
            + getName().getClass());

         Integer formQuestionPK = null;
         try
         {
            formQuestionPK = Integer.valueOf(targetQuestionValue.toString());
         }
         catch (RuntimeException e)
         {
            throw new SetupException(
            "Form expected with target question operation parameter "
            + Debugger.toString(operationParameter) + " " + e);
         }

         FormQuestion formQuestion = aForm.findQuestionByID(formQuestionPK);
         if (formQuestion == null)
            throw new SetupException("Form Question PK " + formQuestionPK
            + " not found in form " + aForm);

         return formQuestion;

      }
      catch (Exception e)
      {
         throw new SetupException(e);
      }
   }//--------------------------------------------
   /**
    * 
    * @param aUserID the user ID
    * @param aAccessUser the user request to information
    * @return GCSMUser
    * @throws Exception
    */
   protected User retrieveUserByID(Integer aUserID, SecurityCredential aAccessUser)
   throws Exception
   {
      if (aUserID == null)
         throw new IllegalArgumentException(
         "aUserID required in AbstractGCSMOperation.retrieveUserByID");
      
      UserDAO dao = null;
      try
      {
         dao = UserDAO.getUserDAOInstance(aAccessUser);
         return dao.selectUserByPrimaryKey(aUserID);
      }
      finally
      {
         if(dao != null)
            dao.dispose();
      }
   }//--------------------------------------------
   /**
    * 
    * @return Operation parameters sorted by number
    */
   public Collection getSortedParameters()
   {
      if(parameters == null)
         return new ArrayList();
      
      return Organizer.sortByJavaBeanProperty("number",parameters);
   }//--------------------------------------------
   /**
    * @return Returns the parameters.
    */
   public Collection getParameters()
   {
      if(parameters == null)
         return new ArrayList();
      
      return new ArrayList(parameters);
   }//--------------------------------------------
   /**
    * @param aFormQuestion
    *@return new or existing form
    */
   protected FormAnswer getAnswer(FormQuestion aFormQuestion, Form aForm)
   {
       FormAnswer answer = aFormQuestion.getAnswer();

      return answer;
   }//--------------------------------------------
   /**
    * @return form.isReadOnly(form.getAccessUser());
    * @see nyla.solutions.formInjection.bre.Operation#mustSkip(nyla.solutions.formInjection.data.FormComponent)
    */
   public boolean mustSkip(FormComponent aForm)
   {
      if(aForm == null)
         return true;
      
      Form form = this.retrieveForm(aForm);
      
      if (form.isReadOnly())
    	  return true;
      
      if (aForm instanceof FormQuestion) {
    	  FormQuestion q = (FormQuestion) aForm;
    	  return q.getAttribute(FormGuide.READONLY_AFTER_ANSWER_ATTRIB_NM) != null && q.hasAnswer();
      }
      return false;
   }//--------------------------------------------

   /**
    * Set question choices of user
    * @param aFormQuestion the form question
    * @param aForm the form obect
    */
   protected void populateFormChoices(Collection aChoices, FormColumn column, Form aForm)
   {
      
      
      if(!column.getResponseType().isSelectable())
      {
         throw new SetupException(getClass().getName()+" cannot be used on a column that is not selectable. i.e. listbox of checkboxes response type:  column="+column);
      }
      
      column.addDynamicChoices(aChoices);
      
   }//--------------------------------------------  
   /**
    * Set question choices of user
    * @param aFormQuestion the form question
    * @param aForm the form obect
    */
   protected void populateChoices(Collection aChoices, FormColumn aColumn, Form aForm)
   {
      populateFormChoices(aChoices, aColumn, aForm);
      
   }//--------------------------------------------   
   /**
    * @return create choice
    */
   protected QuestionChoice createDynamicQuestionChoice(int aPrimaryKey, String aName )
   {
      QuestionChoice choice = new QuestionChoice();
      choice.setChoiceId(new Integer(aPrimaryKey));
      choice.setNumber(aPrimaryKey);
      choice.setText(aName);
      return choice;
   }//--------------------------------------------   
   protected void answer(Serializable aValue, Collection aFormQuestions,Form aForm)
   {
      FormQuestion formQuestion = null;      
      for (Iterator i = aFormQuestions.iterator(); i.hasNext();)
      {
         formQuestion = (FormQuestion) i.next();
         formQuestion.setAnswer(aValue);
      }
      
   }//--------------------------------------------
   /**
    * @param aSAE
    * @param aForm
    * @param aFormQuestion
    * @param aFormTable
    * @param aRowNumber
    * @param formRow
    */
   protected void createRowAnswer(Serializable aValue, int aColumnNumber,Form aForm, FormQuestion aFormQuestion, FormTable aFormTable, int aRowNumber, FormRow formRow)
   {
      if(aValue != null && aValue instanceof Date)
      {
         try
         { aValue = Text.formatDate((Date)aValue); }
         catch(Exception e){}
      }
      formRow.addAnswer(aColumnNumber, aValue);
   }//--------------------------------------------  

   /**
    * @param parameters the parameters to set
    */
   public void setParameters(Collection parameters)
   {
      this.parameters = parameters;
   }
   private Collection parameters = null;
   private String name = null;
   protected Log logger = Debugger.getLog(getClass());
   static final long serialVersionUID = AbstractOperation.class.getName().hashCode();
}