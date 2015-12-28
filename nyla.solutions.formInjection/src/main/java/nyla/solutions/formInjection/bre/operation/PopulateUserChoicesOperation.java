package nyla.solutions.formInjection.bre.operation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import nyla.solutions.formInjection.bre.AutofillOperation;
import nyla.solutions.formInjection.data.Form;
import nyla.solutions.formInjection.data.FormColumn;
import nyla.solutions.formInjection.data.FormComponent;
import nyla.solutions.formInjection.data.FormQuestion;
import nyla.solutions.formInjection.data.QuestionChoice;
import nyla.solutions.formInjection.exception.BreException;
import nyla.solutions.global.exception.SetupException;
import nyla.solutions.global.security.user.data.User;
import nyla.solutions.global.util.Debugger;



/**
 * <pre>
 * PopulateUserChoicesOperation provides functions to
 * populate a list of users of a question list box
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class PopulateUserChoicesOperation extends AbstractOperation
implements AutofillOperation
{

   /**
    * Constructor for PopulateUserChoicesOperation initalizes internal 
    * data settings.
    * 
    */
   public PopulateUserChoicesOperation()
   {
      super();
   }//--------------------------------------------
   /**
    * 
    * @see nyla.solutions.formInjection.bre.Operation#execute(nyla.solutions.formInjection.data.FormComponent)
    */
   public void execute(FormComponent aFormComponent) throws BreException
   {
      try
      {
         Form form = this.retrieveForm(aFormComponent);
         
         if(aFormComponent instanceof FormColumn)
         {
            FormColumn formColumn = this.retrieveFormColumn(aFormComponent);
            
            if(formColumn.hasChoices())
            {
               logger.debug("Column has choices...returning");
               return; //do nothing
            }
            
            
            Collection choices = createChoicesForUsers(form);
            populateChoices(choices,formColumn, form);
         }
         else if(aFormComponent instanceof FormQuestion) 
         {
         	
            FormQuestion formQuestion = this.retrieveFormQuestion(aFormComponent);
            logger.debug("Populating users for question: "+formQuestion.getPrimaryKey());
            
            
            if(formQuestion.hasChoices()) 
            {
            	logger.debug("Question has choices...returning");
            	return; // do nothing
            }
            
            Collection choices = createChoicesForUsers(form);
            
            formQuestion.addDynamicChoices(choices);
         }
         else
         {
            throw new SetupException("Do run "+getClass().getName()+" as part of a rule");
         }
      }
      catch (Exception e)
      {
         throw new BreException(Debugger.stackTrace(e));
      }
   }//--------------------------------------------
  /*private void populateForm(Form aForm, Collection aChoices)
   {
//    get formQuestions
      Collection columns = this.retrieveTargetColumnsByTrueAttribute(aForm);
      
      if(columns == null || columns.isEmpty())
      {
         logger.debug("No columns to populated");
         return;
      }
      
      
      FormColumn column = null;
      for (Iterator i = columns.iterator(); i.hasNext();)
      {
         column = (FormColumn) i.next();
         
         populateChoices(aChoices,column, aForm);
      }      
   }//--------------------------------------------
*/   /**
    * 
    * @see nyla.solutions.formInjection.bre.operation.AbstractOperation#mustSkip(nyla.solutions.formInjection.data.FormComponent)
    */
   public boolean mustSkip(FormComponent aForm)
   {
      return false;
   }//--------------------------------------------
   private Collection createChoicesForUsers(Form aForm)
   
   {

         Collection users = null;
         //IMPLEMENTS
         ArrayList choices = (ArrayList)aForm.retrieveAttributeValue("choicesForUsers");
         
         if(choices != null)
            return choices;
         
         User user = null;
         choices = new ArrayList(users.size());
         QuestionChoice questionChoice = new QuestionChoice();
         int questionNumber = 0;
         questionChoice.setNumber(questionNumber++);
         questionChoice.setText("");
         choices.add(questionChoice);
         
         for (Iterator i = users.iterator(); i.hasNext();)
         {
            user = (User) i.next();
            questionChoice = new QuestionChoice();
            questionChoice.setText(decorateUserName(user));
            questionChoice.setNumber(questionNumber++);
            questionChoice.setPrimaryKey(user.getPrimaryKey());
            choices.add(questionChoice);
         }
         
         choices.trimToSize();
         
         //cache choices
         aForm.addAttribute("choicesForUsers",choices);
         
         return choices;

      
   }//--------------------------------------------
   protected String decorateUserName(User aUser)
   {
      if(aUser == null)
         return "";
      
      StringBuffer name = new StringBuffer();
      //if(!Text.isNull(aUser.getTitle()))
      //{
       //  name.append(aUser.getTitle()).append(" ");  
      //}
      
      name.append(aUser.getFirstName()).append(" ")
      .append(aUser.getLastName());
      return name.toString();
   }
   
   static final long serialVersionUID = 2;
}
