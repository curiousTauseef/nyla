
package nyla.solutions.formInjection.formatter.pdf;

import java.util.Hashtable;

import nyla.solutions.formInjection.data.FormAnswer;
import nyla.solutions.formInjection.data.QuestionChoice;
import nyla.solutions.formInjection.formatter.FormDecorator;
import nyla.solutions.global.security.user.data.User;
import nyla.solutions.global.util.Text;

/**
 * @author Gregory Green
 * @version 1.0
 *
 * <b>PDFFormQuestionChoiceDecorator</b>   decorator 
 *
 */
public class PDFFormQuestionChoiceDecorator extends FormDecorator
{
   public static final String FO_NOT_CHOOSEN = "&#x274F;"; //ZapfDingbats:
   public static final String FO_CHOOSEN = "&#x2713;"; //ZapfDingbats:
   
   private FormAnswer answer;
   /**
    * @param aComponent the application VO
    */
   private PDFFormQuestionChoiceDecorator(FormAnswer answer, QuestionChoice aQuestionChoice, String aTemplateName, short aType,User aViewer)
   throws Exception
   {
      super(aQuestionChoice, aTemplateName,aType, aViewer);
      this.answer = answer;
   }//-----------------------------------------
   /**
    * 
    * @param aQuestionChoice
    * @return fo instance
    * @throws Exception
    */
   public static PDFFormQuestionChoiceDecorator getFOInstance(FormAnswer answer, QuestionChoice aQuestionChoice, User aViewer)
   throws Exception
   {
      if(answer.getResponseType().isListable())
      {
         return new PDFFormQuestionChoiceDecorator(answer, aQuestionChoice,"FO_QUESTION_CHOICE_LIST",PDF_TYPE, aViewer);
      }
      else
      {
         return new PDFFormQuestionChoiceDecorator(answer, aQuestionChoice,"FO_QUESTION_CHOICE",PDF_TYPE, aViewer);
      }
   }//----------------------------------------------
   /**
    * @return string decorated text
    */
   public String getText()
   {
      try
      {
         QuestionChoice questionChoice = (QuestionChoice)this.getComponent();
         
         //only decorator listable choices that are picked
         if(answer.getResponseType().isListable() &&
            !answer.isPicked(questionChoice))
         {
            return "";
         }
         
         StringBuffer text = new StringBuffer();
         Hashtable map = new Hashtable();
         
         if(answer.isPicked(questionChoice))
         {
            map.put("choice", getChosen());
         }
         else
         {
            map.put("choice", getNotChosen());
         }
         
          text.append(" ")
          .append(fix(super.getText()));
                                         
           map.put("text",text);
         
         return Text.format(getTemplate(),map);
      }
      catch (Exception e)
      {
        throw new RuntimeException(e);
      }
   }//-----------------------------------
   /**
    * 
    * @return the formatt string for a not selected choice
    */
   private String getNotChosen()
   {
         return FO_NOT_CHOOSEN;    
   }//------------------------------------------
   /**
    * 
    * @return FO_CHOOSEN
    */
   private String getChosen()
   {
         return FO_CHOOSEN;    
   }//------------------------------------------
}
