package nyla.solutions.formInjection.bre.operation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import nyla.solutions.formInjection.bre.AutofillOperation;
import nyla.solutions.formInjection.bre.OperationParameter;
import nyla.solutions.formInjection.data.FormColumn;
import nyla.solutions.formInjection.data.FormComponent;
import nyla.solutions.formInjection.data.FormQuestion;
import nyla.solutions.formInjection.data.QuestionChoice;
import nyla.solutions.formInjection.exception.BreException;
import nyla.solutions.global.data.Data;
import nyla.solutions.global.exception.SetupException;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Organizer;



/**
 * <pre>
 * 
 *  
 *   PopulateChoicesOperation provides a function to
 *   set question choices with a choice from operation parameters.
 *  
 *   
 *  
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public class PopulateChoicesOperation extends AbstractOperation implements
AutofillOperation
{

   /**
    * Constructor for PopulateChoicesOperation initializes internal data
    * settings.
    *  
    */
   public PopulateChoicesOperation()
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
         Collection parameters = this.getParameters();

         //logger.debug(" populating choices ");

         if (parameters == null || parameters.isEmpty())
         {
            logger.warn("No parameters for " + this);
            return;
         }

         
         boolean hasChoices = false;
         
         if (aFormComponent instanceof FormColumn)
         {
            FormColumn formColumn = this.retrieveFormColumn(aFormComponent);

            hasChoices =formColumn.hasChoices(); 
            //logger.debug("hasChoices="+hasChoices+" column="+column);
            if (hasChoices)
               return; //column already has choices

            poulateChoices(formColumn, parameters);
         }
         else if (aFormComponent instanceof FormQuestion)
         {
            
            FormQuestion formQuestion = this
            .retrieveFormQuestion(aFormComponent);
            

            hasChoices = formQuestion.hasChoices();
            //logger.debug("hasChoices="+hasChoices+" formQuestion="+formQuestion);
            
            if(hasChoices)
               return; //question already has choices

            poulateChoices(formQuestion, parameters);
         }
         else
         {
            throw new SetupException(getClass().getName()
            + " cannot be execute within a rule");
         }
      }
      catch (Exception e)
      {
         throw new BreException(Debugger.stackTrace(e));
      }
   }//--------------------------------------------

   /**
    * 
    * @param aColumn
    *           the columns
    * @param aOperationParameters
    */
   private void poulateChoices(FormColumn aColumn, Collection aOperationParameters)
   {
      aColumn
      .addDynamicChoices(createChoicesFromParameters(aOperationParameters));
   }//--------------------------------------------

   private void poulateChoices(FormQuestion aFormQuestion,
                               Collection aOperationParameters)
   {
      Collection choices = createChoicesFromParameters(aOperationParameters);
      aFormQuestion.addDynamicChoices(choices);
      
   }//--------------------------------------------

   /**
    * @param aOperationParameters
    *           a Operation Parameters
    */
   private Collection createChoicesFromParameters(
                                                  Collection aOperationParameters)
   {
      //logger.debug("createChoicesFromParameters");

      Organizer.sortByJavaBeanProperty("number", aOperationParameters);
      Collection choices = new ArrayList(aOperationParameters.size());
      OperationParameter operationParameter = null;

      String value = null;
      String defaultSelected = null;
      //loop parametes
      for (Iterator i = aOperationParameters.iterator(); i.hasNext();)
      {
         operationParameter = (OperationParameter) i.next();
         value = operationParameter.getValue() != null ? String.valueOf(operationParameter.getValue()) : "";
         if ("defaultSelected".equals(operationParameter.getName()))
         {
            defaultSelected = value;
         }
         else
         {
            if (value == null)
               value = "";

            //create/add choice
            QuestionChoice choice = createDynamicQuestionChoice(
            operationParameter.getNumber(), value);
            if (value.equals(defaultSelected))
               choice.setDefaultCode(Data.YES);
            choices.add(choice);
         }
      }//end for loop

      //logger.debug("choices="+choices);
      return choices;
   }//--------------------------------------------

   /**
    * Never skip
    * 
    * @see nyla.solutions.formInjection.bre.operation.AbstractOperation#mustSkip(nyla.solutions.formInjection.data.FormComponent)
    */
   public boolean mustSkip(FormComponent aForm)
   {
      return false;
   }//--------------------------------------------
   static final long serialVersionUID = PopulateChoicesOperation.class
   .getName().hashCode();
}
