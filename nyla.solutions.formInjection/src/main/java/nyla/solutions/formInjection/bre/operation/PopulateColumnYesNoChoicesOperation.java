package nyla.solutions.formInjection.bre.operation;

import java.util.Collection;
import java.util.LinkedList;

import nyla.solutions.formInjection.bre.AutofillOperation;
import nyla.solutions.formInjection.data.FormColumn;
import nyla.solutions.formInjection.data.FormComponent;
import nyla.solutions.formInjection.data.QuestionChoice;
import nyla.solutions.formInjection.exception.BreException;
import nyla.solutions.global.data.Data;
import nyla.solutions.global.util.Debugger;


/**
 * <pre>
 * 
 *  PopulateYesNoChoicesOperation provides a function to
 *  set question choices with a Yes or No value.
 * 
 * The class PopulateYesNoChoicesOperation  
 * dynamically creates choices Yes or No choices for any selectable response type 
 * (checkboxes, list box, radio boxes).
 * The most efficient way to use this operation is via the MacroOperation 
 * executed with a logical expression that is always true (LOGIC_EXP_TBL.LOGICAL_EXP_CD = "TRUE"). The effected selectable questions must have a question attribute or response type attribute "yesOrNO"=true in the QUESTION_ATTRIB_TBL or RESP_TYPE_ATTRIB_TBL. 
 * The current response type that has this attribute is RESPONT_TYPE_ID =22.
 *  
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public class PopulateColumnYesNoChoicesOperation extends AbstractOperation
implements AutofillOperation
{

   /**
    * Constructor for PopulateLocationChoicesOperation initalizes internal data
    * settings.
    *  
    */
   public PopulateColumnYesNoChoicesOperation()
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
         FormColumn formColumn = retrieveFormColumn(aFormComponent);
         Collection choices = new LinkedList();
         
         QuestionChoice yes = new QuestionChoice();
         yes.setPrimaryKey(1);
         yes.setNumber(1);
         yes.setName("Yes");
         choices.add(yes);
         
         QuestionChoice no = new QuestionChoice();
         no.setPrimaryKey(2);
         no.setNumber(2);
         no.setName("No");
         
         if(isNoDefault())
         {
            no.setDefaultCode(Data.YES);
         }
         
         choices.add(no);
         
         choices.add(createDynamicQuestionChoice(3,"N/E"));
         
         formColumn.addDynamicChoices(choices);
      }
      catch (Exception e)
      {
         throw new BreException(Debugger.stackTrace(e));
      }
   }//--------------------------------------------
   /**
    * Never skip
    * @see nyla.solutions.formInjection.bre.operation.AbstractOperation#mustSkip(nyla.solutions.formInjection.data.FormComponent)
    */
   public boolean mustSkip(FormComponent aForm)
   {
      return false;
   }//--------------------------------------------
   
   /**
    * @return Returns the noDefault.
    */
   public boolean isNoDefault()
   {
      return noDefault;
   }//--------------------------------------------
   private boolean noDefault = true;
   
   static final long serialVersionUID = PopulateColumnYesNoChoicesOperation.class
   .getName().hashCode();
}
