package nyla.solutions.formInjection.bre.operation;


import java.util.Calendar;

import nyla.solutions.formInjection.data.FormComponent;
import nyla.solutions.formInjection.data.FormQuestion;
import nyla.solutions.formInjection.exception.BreException;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Text;

/**
 * 
 * <b>SetDateOperation</b> the date on a given question's answer 
 * @author Gregory Green
 *
 */
public class SetDateOperation extends AbstractOperation
{
   /**
    * 
    */
   private static final long serialVersionUID = 472540549356992334L;
   
   /**
    * 
    *
    * @see nyla.solutions.formInjection.bre.Operation#execute(nyla.solutions.formInjection.data.FormComponent)
    */
   public void execute(FormComponent formComponent) throws BreException
   {
      FormQuestion question = super.retrieveFormQuestion(formComponent);
      
      String answerDate = null;

      if(date == null  || date.length() == 0)
      {
         //current time
         answerDate = Text.formatDate(dateFormat,Calendar.getInstance().getTime());
         
      }
      else
      {
         answerDate = date;
      }
      
      question.setAnswer(answerDate);
   }//--------------------------------------------
   private String date = null;
   private String dateFormat = Config.getProperty(SetDateOperation.class.getName()+"dateFormat","MM/dd/yyyy");
}
