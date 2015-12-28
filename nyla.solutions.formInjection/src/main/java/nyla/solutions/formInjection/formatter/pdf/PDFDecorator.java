package nyla.solutions.formInjection.formatter.pdf;

import java.util.Collection;
import java.util.Iterator;

import nyla.solutions.formInjection.data.FormAnswer;
import nyla.solutions.formInjection.data.FormQuestion;
import nyla.solutions.formInjection.data.QuestionChoice;
import nyla.solutions.formInjection.data.ResponseType;
import nyla.solutions.formInjection.formatter.FormDecorator;
import nyla.solutions.formInjection.formatter.NumberLabelDecorator;
import nyla.solutions.global.data.Textable;
import nyla.solutions.global.security.user.data.User;
import nyla.solutions.global.util.Organizer;


/**
 * <pre>
 * PDFDecorator abstract PDF render 
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public abstract class PDFDecorator extends FormDecorator
{

   /**
    * Constructor for PDFDecorator initalizes internal 
    * data settings.
    * @param aComponent
    * @param aTemplateName
    * @param aDecorator
    * @throws Exception
    */
   public PDFDecorator(Textable aComponent, String aTemplateName,
   FormDecorator aDecorator) throws Exception
   {
      super(aComponent, aTemplateName, aDecorator);
   }//--------------------------------------------

   /**
    * Constructor for PDFDecorator initalizes internal 
    * data settings.
    * @param aComponent
    * @param aTemplateName
    * @param aType
    * @param aViewer
    * @throws Exception
    */
   public PDFDecorator(Textable aComponent, String aTemplateName, short aType,
   User aViewer)
   {
      super(aComponent, aTemplateName, aType, aViewer);
   }//--------------------------------------------
   /**
    * Constructor for PDFDecorator initalizes internal 
    * data settings.
    * @param aComponent
    */
   public PDFDecorator(Textable aComponent)
   {
      super(aComponent);
   }//--------------------------------------------   
   /**
    * Decorate question choice
    * @param aQuestion the question
    * @param aText the text to add to
    * @throws Exception
    */
   protected String decorateFormQuestionChoices(Collection aChoices, FormAnswer aAnswer)
      throws Exception
   {         
         StringBuffer choicesText = new StringBuffer();
         //
         if (aChoices == null || aChoices.isEmpty())
         {
            return "";
         }
                   
         aChoices = Organizer.sortByJavaBeanProperty("number",aChoices);
         
         switch(aAnswer.getResponseType().getType())
         {
            case ResponseType.MULTI_SELECT_TYPE: 
              decoratorMultiSelect(aAnswer, aChoices, choicesText);
            break;
            case ResponseType.SELECT_LIST_TYPE:
               decoratorListBox(aAnswer, aChoices, choicesText);
            break;
            default: 
               decoratorSelect(aAnswer, aChoices, choicesText);
         }                     
         return choicesText.toString();
   } //-----------------------------------------------------------

   /**
    * @param aChoices
    * @param choicesText
    * @throws Exception
    */
   private void decoratorMultiSelect(FormAnswer answer, Collection aChoices, StringBuffer choicesText) throws Exception
   {
      QuestionChoice questionChoice = null;
      boolean firstFound = true;
      for (Iterator i = aChoices.iterator(); i.hasNext();)
      {
         questionChoice = (QuestionChoice) i.next();
         
         if(answer.isPicked(questionChoice))
         {
            if(!firstFound && i.hasNext())
               
            {
               choicesText.append(", ");
               
            }
            firstFound = false;
            choicesText.append(
               PDFFormQuestionChoiceDecorator
                  .getFOInstance(answer, questionChoice,this.getViewer())
                  .getText());                        
  
         }
      }//end for
   }//--------------------------------------------
   /**
    * @param aChoices
    * @param choicesText
    * @throws Exception
    */
   private void decoratorSelect(FormAnswer answer, Collection aChoices, StringBuffer choicesText) throws Exception
   {
      QuestionChoice questionChoice;
      for (Iterator i = aChoices.iterator(); i.hasNext();)
      {
         questionChoice = (QuestionChoice) i.next();
            choicesText.append(
               PDFFormQuestionChoiceDecorator
                  .getFOInstance(answer, questionChoice,this.getViewer())
                  .getText());                         
      }//end for
   }//--------------------------------------------
   private void decoratorListBox(FormAnswer answer, Collection aChoices, StringBuffer choicesText) throws Exception
   {
      QuestionChoice questionChoice;
      for (Iterator i = aChoices.iterator(); i.hasNext();)
      {
         questionChoice = (QuestionChoice) i.next();
         if(answer.isPicked(questionChoice))
         {
            choicesText.append(
               PDFFormQuestionChoiceDecorator
                  .getFOInstance(answer, questionChoice,this.getViewer())
                  .getText());
            
            return;
         }
      }//end for
   }//--------------------------------------------
   /**
    * 
    * Decroate section and question number i.e. 1-1.
    * @see nyla.solutions.formInjection.formatter.FormDecorator#getNumberLabel(nyla.solutions.formInjection.data.FormQuestion)
    */
   protected String getNumberLabel(FormQuestion question) 
   {
      NumberLabelDecorator counter = updateCounter(question);
      return counter.getText() + ".";
  }//--------------------------------------------
   protected NumberLabelDecorator updateCounter(FormQuestion question) 
   {
      NumberLabelDecorator counter = getCounter(question);
      counter.increment();
      counter.setFormComponentId(question.getPrimaryKey());
      return counter;
  }//--------------------------------------------
}
