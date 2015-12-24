package org.solutions.form.bre.operation;

import java.util.Date;

import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Text;

import org.solutions.form.data.Form;
import org.solutions.form.data.FormComponent;
import org.solutions.form.data.FormQuestion;
import org.solutions.form.exception.BreException;



/**
 * <pre>
 * SetCreateDateOperation provides a operation set the create date
 * on a given question
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class SetCreateDateOperation extends AbstractOperation
{

   /**
    * Constructor for SetCreateDateOperation initializes internal 
    * data settings.
    * 
    */
   public SetCreateDateOperation()
   {
      super();
   }//--------------------------------------------
   /**
    * 
    * @see org.solutions.form.bre.Operation#execute(org.solutions.form.data.FormComponent)
    */
   public void execute(FormComponent aFormComponent) throws BreException
   {
      
      try
      {
         
         Form form = this.retrieveForm(aFormComponent);
         
         Date createDate = form.getCreateDate();
         if(createDate == null)
            return; //nothing to prepopulate
         
         String value = Text.formatDate(createDate);
         
         FormQuestion formQuestion = this.retrieveFormQuestion(aFormComponent);
         answerCreateDate(form, value, formQuestion);
      }
      catch (Exception e)
      {
         throw new BreException(Debugger.stackTrace(e));
      }
   }//--------------------------------------------
   /**
    * Create answer for create date
    * @param aForm the form
    * @param aCreateDate the create date
    * @param aFormQuestion the form question
    */
   private void answerCreateDate(Form aForm, String aCreateDate, FormQuestion aFormQuestion)
   {
      if(!aFormQuestion.hasAnswer())
      {
         logger.debug("Setting create date on question "+aFormQuestion.getPrimaryKey());
         aFormQuestion.setAnswer(aCreateDate);
         
      }
   }//--------------------------------------------
   
   static final long serialVersionUID = SetCreateDateOperation.class.getName()
   .hashCode();
}
