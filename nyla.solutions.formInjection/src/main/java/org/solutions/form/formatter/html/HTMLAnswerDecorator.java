package org.solutions.form.formatter.html;

import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.util.Debugger;

import org.solutions.form.FormGuide;
import org.solutions.form.QuestionGuide;
import org.solutions.form.data.FormAnswer;
import org.solutions.form.data.FormQuestion;
import org.solutions.form.data.QuestionAttribute;
import org.solutions.form.data.QuestionChoice;
import org.solutions.form.data.ResponseType;


/**
 * HTMLAnswerDecorator presents HTML view of an form
 */
public class HTMLAnswerDecorator extends HTMLDecorator {

   /**
    * @param aComponent
    */
   public HTMLAnswerDecorator(FormAnswer FormAnswer) 
   {
      super(FormAnswer);
      
      this.setAnswerStyle(HTMLFormQuestionDecorator.DEFAULT_ANSWER_STYLE_CLASS);
   }//--------------------------------------------
   /**
    * 
    * 
    * @see org.solutions.form.data.FormComponent#getText()
    */
   public String getText() 
   {
      StringBuffer text = new StringBuffer();
      FormAnswer FormAnswer = (FormAnswer)this.getComponent();
      
      //ResponseType responsetype = FormAnswer.getResponseType();
      
      if(FormAnswer == null)
         throw new IllegalArgumentException("aAnswer required in HTMLAnswerDecorator.decorateAnswer");
      
      if(FormAnswer.getResponseType() == null)
         throw new IllegalArgumentException("aAnswer.getResponseType() required in HTMLAnswerDecorator.decorateAnswer");
      
      ResponseType responseType = FormAnswer.getResponseType();
      
      if(responseType.isAnswerable())
         decorateAnswerable(text, FormAnswer, FormAnswer.getFormQuestion(),responseType);         

      return text.toString();
   }//--------------------------------------------
   /**
    * @param text the string buffer
    * @param aAnswer
    * @param question
    * @param responsetype
    */
   private void decorateAnswerable(StringBuffer text, FormAnswer aAnswer, FormQuestion aFormQuestion, ResponseType aResponsetype)
   {
      if(aFormQuestion == null)
         throw new IllegalArgumentException("aFormQuestion required in HTMLAnswerDecorator.decorateAnswerable aAnswer="+aAnswer);
      
      if( aAnswer.isReadOnly() || this.isReadOnly())
      {
         decoratorReadOnlyAnswer(text, aAnswer, aFormQuestion, aResponsetype);
      }
      else
      {
         decoratorEditableAnswer(text, aAnswer, aFormQuestion, aResponsetype);
      }
   }//--------------------------------------------   
   /**
    * @param aText
    * @param aAnswer
    * @param aFormQuestion
    * @param aResponsetype
    */
   private void decoratorReadOnlyAnswer(StringBuffer aText, FormAnswer aAnswer, FormQuestion aFormQuestion, ResponseType aResponsetype)
   {
      switch (aResponsetype.getType()) 
      {
         case QuestionGuide.HIDDEN_TYPE:
              aText.append("<!-- HIDDEN value=").append(fix(aAnswer.getValue())).append(" -->");
         break;
         case QuestionGuide.CURRENCY_TYPE:
         case QuestionGuide.PERCENT_TYPE:
         case QuestionGuide.DATE_TYPE:         
         case QuestionGuide.NUMBER_TYPE:
         case QuestionGuide.INTEGER_TYPE:
         case QuestionGuide.FLOAT_TYPE:
         case QuestionGuide.TEXT_TYPE:
         case QuestionGuide.TEXT_AREA_TYPE:          
            aText.append("<span class=\"")
            .append(this.getAnswerStyle()).append("\">");
         
            if(aAnswer.isBlank())
               aText.append(SPACE);
            else               
             aText.append(fix(aAnswer.getValue()));
         
             aText.append("</span>");  
         break;
         case FormGuide.MULTI_SELECT_TYPE:
         case QuestionGuide.SELECT_LIST_TYPE:
         case QuestionGuide.RADIO_TYPE:
         case QuestionGuide.CHECKBOXES_TYPE:
               super.decorateQuestionChoices( aAnswer, aText);            
         break;            
         case QuestionGuide.TABLE_TYPE:
            HTMLFormTableDecorator r = getHTMLFormTableDecorator(aFormQuestion.getFormTable());
            aText.append(r.getText());
            break;
         default:
            throw new IllegalStateException("responsetype.getType()="+aResponsetype.getType()+" in decorateAnswerableNoSelectable");
      }
   }//--------------------------------------------
   
   private void decoratorEditableAnswer(StringBuffer aText, FormAnswer aAnswer, FormQuestion aFormQuestion, ResponseType aResponsetype)
   {
      if(aFormQuestion == null)
         throw new IllegalArgumentException("aFormQuestion required in HTMLAnswerDecorator.decoratorEditableAnswer");
      
      String inputName = decorateInputName(aAnswer);
      switch (aResponsetype.getType()) 
      {
         case  ResponseType.HIDDEN_TYPE:
            //Object [] inputs = {inputName, fix(aAnswer.getValue()),
            //                    inputName+ANSWER_INPUT_ID_PREFIX};
            //String template = "<input type=\"hidden\" id=\"{0}\" name=\"{0}\" value=\"{1}\"/>";
            //aText.append(MessageFormat.format(template,inputs));
            aText.append("<input type=\"hidden\" id=\"").append(inputName).append("\" name=\"").append("}\" value=\"").append(inputName+ANSWER_INPUT_ID_PREFIX).append("\"/>");
         break;
         case ResponseType.CURRENCY_TYPE:
         case ResponseType.PERCENT_TYPE:
         case ResponseType.DATE_TYPE:         
         case ResponseType.NUMBER_TYPE:
         case ResponseType.INTEGER_TYPE:
         case ResponseType.FLOAT_TYPE:
         case ResponseType.TEXT_TYPE:
            aText.append("<input type=\"text\" ").append(" class=\"").append(super.getAnswerStyle()).append("\" ")
            .append(" name=\"").append(inputName).append("\"")            
            .append(" id=\"").append(inputName).append(ANSWER_INPUT_ID_PREFIX).append("\"")            
            .append(" maxlength=\"").append(FormGuide.retrieveMaxLength(aAnswer)).append("\" ")
            .append(" value=\"").append(fix(aAnswer.getValue())).append("\" ");
         
            decorateJS(aText);
            aText.append("/>");  
            
            if (aResponsetype.getType() == ResponseType.DATE_TYPE) {
                String tmpl = super.getTemplateAsString("date_control");
                Map m = new HashMap();
                m.put("inputName", inputName);
                aText.append(format(m, tmpl));
            }
         break;
         case ResponseType.MULTI_SELECT_TYPE:
             decorateMultiSelect(aAnswer, aText);
         break;
         case ResponseType.SELECT_LIST_TYPE:
         case ResponseType.CHECKBOXES_TYPE:
         case ResponseType.RADIO_TYPE:
               super.decorateQuestionChoices(aAnswer, aText);
         break;
         case ResponseType.TEXT_AREA_TYPE:
            aText.append("<textarea id=\""+inputName+ANSWER_INPUT_ID_PREFIX+"\" rows=\"5\" cols=\"27\" class=\"").append(super.getAnswerStyle()).append("\" ")
                                    .append(" maxlength=\"").append(FormGuide.retrieveMaxLength(aAnswer)).append("\" ");
         
             decorateJS(aText);
                                    
             aText.append(" name=\"").append(inputName).append("\">")
                  .append(fix(aAnswer.getValue())).append("</Textarea>");
            break;
         case ResponseType.TABLE_TYPE:
            HTMLFormTableDecorator r = getHTMLFormTableDecorator(aFormQuestion.getFormTable());
            aText.append(r.getText());
            break;
         default:
            throw new IllegalStateException("responsetype.getType()="+aResponsetype.getType()+" in decorateAnswerableNoSelectable "+aResponsetype);
      }
     
      decorateErrorPlaceHolder(aText, inputName);
      
   }//--------------------------------------------
   /**
    * Sample format
    *   <div class="FormAnswer">
    <input id="${answerID}" name="${inputName}" type="hidden" value="${answerValue}"/>
   <span id="${answerID}_Text" class="FormAnswer">
       ${answerText}
    </span>
    <a href="javascript:showMultiSelect('${name}','${answerID}', '${choices}');"> Edit ${name}</a>
  </div>
    * @param aAnswer
    * @param aText
    */
   private void decorateMultiSelect(FormAnswer aAnswer, StringBuffer aText)
   {
     StringBuffer choices = new StringBuffer();
     Map map = new HashMap();
     
     //All possible Choices
     Collection questionChoices = FormGuide.retrieveQuestionChoices(aAnswer);
     
     if(questionChoices != null && !questionChoices.isEmpty())
     {
         QuestionChoice questionChoice = null;
         
         //Collection sortedChoices = Organizer.sortByJavaBeanProperty("number", questionChoices);    
         for (Iterator i = questionChoices.iterator(); i.hasNext();)
         {
            questionChoice = (QuestionChoice) i.next();
            try
            {
            String value = URLEncoder.encode(questionChoice.getText(), "US-ASCII");
            
            choices.append(questionChoice.getKey())
                               .append("=")
                               .append(value)
                               .append(";");
            }
            catch(Exception e)
            {
               throw new SystemException(Debugger.stackTrace(e));
            }
         }//end for loop
     }
     map.put("choices",choices);
     
     //inputName
     String inputName = decorateInputName(aAnswer);
     map.put("inputName",inputName);
     
     //answerID
     String answerID = inputName+ANSWER_INPUT_ID_PREFIX;
     map.put("answerID",answerID);
     
     //answerValue
     Object answerValue = aAnswer.getValue();
     answerValue = (answerValue == null) ? "" : answerValue;
     map.put("answerValue",answerValue);
     
     //label
     String label = "";
     //decorate label with column or question name
     boolean isWithinTable = aAnswer.isWithinTable();
     if(isWithinTable)
     {
        label = aAnswer.getFormColumn().getText();
     }
     else
     {
        //get multi select label
        QuestionAttribute attribute = aAnswer.findAttributeByName(FormGuide.MULTI_SELECT_LABEL_PROP_NM);
        if(attribute != null && attribute.getValue() != null)
        {
           label = attribute.getValue().toString();
        }
     }
     
     //Default action is "Edit"
     String action = FormGuide.DEFAULT_MULTI_SELECT_ACTION_LABEL;
     QuestionAttribute actionAttribute = aAnswer.findAttributeByName(FormGuide.MULTI_SELECT_ACTION_LABEL_PROP);
     
     
     if(actionAttribute != null && 
        actionAttribute.getValue() != null)
     {
        action = actionAttribute.getValue().toString();
     }
     
     map.put("action", action);
     map.put("label",label);
     map.put("questionID", String.valueOf(aAnswer.getQuestionPK()));
     
     //answerText
     StringBuffer answerText = new StringBuffer();
     this.decorateReadOnlyQuestionChoices(aAnswer,answerText);
 
     map.put("answerText",answerText);
     
     String template = super.getTemplateAsString("multi_select");
     
     aText.append(super.format(map,template));
   }//--------------------------------------------
  
}
