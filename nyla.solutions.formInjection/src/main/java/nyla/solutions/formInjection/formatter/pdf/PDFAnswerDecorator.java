package nyla.solutions.formInjection.formatter.pdf;

import java.util.Hashtable;

import nyla.solutions.formInjection.QuestionGuide;
import nyla.solutions.formInjection.data.Answer;
import nyla.solutions.formInjection.data.FormAnswer;
import nyla.solutions.formInjection.data.ResponseType;
import nyla.solutions.formInjection.formatter.FormDecorator;
import nyla.solutions.global.security.user.data.User;
import nyla.solutions.global.util.Text;


/**
 * @author Gregory Green
 * @version 1.0
 *
 * <b>PDFAnswerDecorator</b>   application decorator for PDF-FO. 
 * formats.
 * 
 */
public class PDFAnswerDecorator extends PDFDecorator
{
   /**
    * @param aComponent the Value object
    */
   private PDFAnswerDecorator(FormAnswer aAnswer, String aTemplateName, short aType, User aViewer)
   {
      super(aAnswer,aTemplateName, aType, aViewer);
   }//-----------------------------------------
   private PDFAnswerDecorator(Answer aAnswer, String aTemplateName, FormDecorator aDecorator)
   throws Exception
   {
      super(aAnswer,aTemplateName, aDecorator);
   }//-----------------------------------------   
   public static PDFAnswerDecorator getFOInstance(FormAnswer aAnswer, User aViewer)
   {
      PDFAnswerDecorator d = new PDFAnswerDecorator(aAnswer,"FO_ANSWER",PDF_TYPE, aViewer);
      
      return d;
   }//----------------------------------------------
   public static PDFAnswerDecorator getFOInstance(FormAnswer aAnswer, FormDecorator aDecorator)
   throws Exception
   {
      PDFAnswerDecorator d = new PDFAnswerDecorator(aAnswer,"FO_ANSWER",aDecorator);
      
      return d;
   }//----------------------------------------------   
   /**
    * Factory method
    * @param aAnswer
    * @param aDecorator
    * @return the instane based of client decorator's type
    * @throws Exception
    */
   public static PDFAnswerDecorator getInstance(FormAnswer aAnswer, FormDecorator aDecorator)
    {
          return getFOInstance(aAnswer,aDecorator.getViewer());     
    }//---------------------------------------------------  
   /**
    * @return string decorated text
    */
   public String getText()
   {
      try
      {
         FormAnswer answer = (FormAnswer)this.getComponent();
         
         ResponseType responseType = answer.getResponseType();
         if(!responseType.isAnswerable())
            return "";
         
         StringBuffer text = new StringBuffer();
         
         switch (responseType.getType()) 
         {
            case QuestionGuide.HIDDEN_TYPE:
            break;
            case QuestionGuide.CURRENCY_TYPE:
            case QuestionGuide.PERCENT_TYPE:
            case QuestionGuide.DATE_TYPE:         
            case QuestionGuide.NUMBER_TYPE:
            case QuestionGuide.INTEGER_TYPE:
            case QuestionGuide.FLOAT_TYPE:
            case QuestionGuide.TEXT_TYPE:
            case QuestionGuide.TEXT_AREA_TYPE:          
                text.append(fix(answer.getValue()));
            break;
            case ResponseType.MULTI_SELECT_TYPE:
            case QuestionGuide.SELECT_LIST_TYPE:
            case QuestionGuide.RADIO_TYPE:
            case QuestionGuide.CHECKBOXES_TYPE:
                  //TODO: may need to apply patch where users are losing choices
                  text.append(super.decorateFormQuestionChoices(answer.getQuestionChoices(),answer));            
            break;            
            case QuestionGuide.TABLE_TYPE:
               text.append(PDFFormTableDecorator.getInstance(answer.getFormQuestion().getFormTable(),
                         this).getText());
               break;
            default:
               throw new IllegalStateException("responsetype.getType()="+responseType.getType()+" in decorateAnswerableNoSelectable");
         }
         
         Hashtable map = new Hashtable();
         map.put("text",text);
         
         return Text.format(getTemplate(),map);
      }
      catch (Exception e)
      {
        throw new RuntimeException(e);
      }
   }//-----------------------------------
}
