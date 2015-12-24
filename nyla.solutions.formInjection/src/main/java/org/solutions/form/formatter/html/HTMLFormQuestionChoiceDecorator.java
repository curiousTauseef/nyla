package org.solutions.form.formatter.html;
import nyla.solutions.global.exception.SetupException;
import nyla.solutions.global.util.Text;

import org.solutions.form.data.FormAnswer;
import org.solutions.form.data.QuestionChoice;
import org.solutions.form.data.ResponseType;


/**
 * 
 * <pre>
 *  HTMLFormQuestionChoiceDecorator HTML presentation object for the object
 * FormQuestionChoice.
 * 
 * Sample Output
 * <pre>
 *    <table border="0" width="100%" id="q1"  class="">
 *       <tr><td width="100%"> <input type="checkbox" name="q9"  value="qc4"/> Check 2</td></tr>
 *    </table>
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class HTMLFormQuestionChoiceDecorator extends HTMLFormDecorator
{
    private FormAnswer answer;

   /**
    * @param aFormQuestionchoice
    */
   protected HTMLFormQuestionChoiceDecorator(FormAnswer a, QuestionChoice aFormQuestionchoice)
   {
      super(aFormQuestionchoice);
      this.answer = a;
      super.setStyleClass(HTMLFormQuestionDecorator.DEFAULT_ANSWER_STYLE_CLASS);
   }//--------------------------------------------
   /**
    * 
    * 
    * @see org.solutions.form.data.FormComponent#getText()
    */
   public String getText()
   {
      StringBuffer text = new StringBuffer();
      QuestionChoice formQuestionChoice = (QuestionChoice) this.getComponent();      
      ResponseType rt = answer.getResponseType();
      
      //if(!formQuestion.hasAnswer())
      //   formQuestion.pickDefaultChoice();
      
      if(answer.getFormQuestion().isReadOnly() || this.isReadOnly())
      {
         decorateReadOnly(formQuestionChoice,answer, rt, text);    
      }
      else
      {
         switch (rt.getType())
         {
             case ResponseType.MULTI_SELECT_TYPE:
                text.append(fix(formQuestionChoice.getText()));
             break;
             case ResponseType.SELECT_LIST_TYPE:
                 decorateOption(formQuestionChoice,answer, text);
             break;
             case ResponseType.CHECKBOXES_TYPE:
                decorateCheckRadioBox(formQuestionChoice, answer, text);
             break;
             case ResponseType.RADIO_TYPE:
                decorateCheckRadioBox(formQuestionChoice, answer, text);
             break;    
             default:
                throw new SetupException("Unsupport form question choice rendering of response type "+rt
                                         +" FormQuestionChoice="+formQuestionChoice);
         }
      }
      
      return text.toString();
   }//--------------------------------------------
   /**
    * Display a readonly version of the form question choice
    * @param aFormQuestionChoice the form question choice
    * @param aResponseType the response type
    * @param aText the string buffer
    */
   private void decorateReadOnly(QuestionChoice aFormQuestionChoice, FormAnswer answer, ResponseType  aResponseType, StringBuffer aText)
   {
      if(aResponseType.getType() == ResponseType.CHECKBOXES_TYPE || 
         aResponseType.getType() == ResponseType.RADIO_TYPE)
      {
         aText.append("<span class=\"").append("choice").append("\">");   
      }
      else
      {
         aText.append("<span class=\"").append(this.getAnswerStyle()).append("\">");
      }
      
      short type = aResponseType.getType();
      if(type == ResponseType.CHECKBOXES_TYPE ||
         type == ResponseType.RADIO_TYPE)
      {
         //Process checkboxes and radio
         if(answer.isPicked(aFormQuestionChoice))
         {
            aText.append(this.decorateReadOnlyPicked());
         }
         else
         {
            aText.append(this.decorateReadOnlyUnPicked());         
         }
         aText.append(fix(aFormQuestionChoice.getText())).append("<br/>");
                  
      }
      else if(type == ResponseType.SELECT_LIST_TYPE ||
              type == ResponseType.MULTI_SELECT_TYPE)
      {
         //process multiple select
         //only display option if picked
         if(answer.isPicked(aFormQuestionChoice))
         {
            //aText.append(this.decorateReadOnlyPicked());
            aText.append(fix(aFormQuestionChoice.getText()));
         }
      }     
      aText.append("</span>");
      
      //if (aResponseType.getType() != ResponseType.MULTI_SELECT_TYPE)
      //    aText.append("<br/>");
      //else
      //    aText.append(" ");
   }//--------------------------------------------
   /**
    * <option value="I" selected="true">Who my I?</option>
    */
   private void decorateOption(QuestionChoice aFormQuestionChoice, FormAnswer answer, StringBuffer aText)
   {         
       Object val = aFormQuestionChoice.getKey();
       if(Text.isNull(String.valueOf(val)))
          val = "";
       
         aText.append(" <option value=\"")
         .append(val)
         .append("\" ")
         .append(decorateSelectedOption(aFormQuestionChoice, answer))
         .append(">").append(fix(aFormQuestionChoice.getText()))
         .append("</option>");
   }//--------------------------------------------
   /**
    * 
    * @return
    */
   private String decorateReadOnlyPicked()
   {
      StringBuffer html = new StringBuffer("<img src=\"")
                    .append(getSkinImagePath())
                    .append("/picked_choice.jpg")
                    .append("\"/>"+SPACE);
      
      return html.toString();
   }//--------------------------------------------
   private String decorateReadOnlyUnPicked()
   {
      StringBuffer html = new StringBuffer("<img src=\"")
                    .append(getSkinImagePath())
                    .append("/unpicked_choice.jpg")
                    .append("\"/>"+SPACE);
      
      return html.toString();
   }//--------------------------------------------   
   private void decorateCheckRadioBox(QuestionChoice aFormQuestionChoice ,FormAnswer answer, StringBuffer aText)
   {
      
      String typeName ="";
      
      ResponseType responseType = answer.getResponseType();
      if(responseType.getType() == ResponseType.CHECKBOXES_TYPE)
      {
         typeName = "checkbox";
      }
      else if(responseType.getType() == ResponseType.RADIO_TYPE)
      {
         typeName = "radio";
      }
      else
      {
         throw new IllegalArgumentException(" Unknown type code responseType="+responseType);
         
      }
      StringBuffer tableText = new StringBuffer(" <input type=\"").append(typeName).append("\" ")
      .append(" class=\"").append(super.getAnswerStyle()).append("\" ");
      
      
      String inputName = "";
      
      if(answer != null)
      {
         //logger.debug("Form Choice Has Answer");
         inputName= super.decorateInputName(answer);
      }
      else
      {
         //logger.debug("Form Choice Does Not have Answer");
         inputName=super.decorateQuestionName(answer.getFormQuestion(), null, this.isOffline());
      }
      
      tableText.append(" name=\"");
      tableText.append(inputName);      
      tableText.append("\" ");
      
      tableText.append(" id=\"");
      tableText.append(inputName).append(ANSWER_INPUT_ID_PREFIX);      
      tableText.append("\" ");
      
      
      decorateJS(tableText);
      
      tableText.append(decorateChecked(aFormQuestionChoice, answer)).append(" value=\"").append(decorateChoiceValue(aFormQuestionChoice)).append("\"/> ")
      .append(fix(aFormQuestionChoice.getText()));
      
      aText.append(toHTMLTable(tableText.toString()));
      
   }//--------------------------------------------   
   
   /**
    * 
    * @param aFormQuestionChoice the form choice
    * @return HTML "check text if choice is picked
    */
   private String decorateChecked(QuestionChoice aFormQuestionChoice, FormAnswer answer)
   {
      if(answer.isPicked(aFormQuestionChoice))
         return "checked=\"true\"";
      else
         return "";
      
   }//--------------------------------------------
   private String decorateSelectedOption(QuestionChoice aFormQuestionChoice, FormAnswer answer)
   {
      if(answer.isPicked(aFormQuestionChoice))
         return "selected=\"true\"";
      else
         return "";      
   }//--------------------------------------------
   private String decorateChoiceValue(QuestionChoice aFormQuestionChoice)
   {
      return String.valueOf(aFormQuestionChoice.getPrimaryKey());
   }//--------------------------------------------
   /**
    * 
    * @return <option></option
    */
   protected static String decoratorBlankOption()
   {
      return "<option></option>";
   }//--------------------------------------------
}
