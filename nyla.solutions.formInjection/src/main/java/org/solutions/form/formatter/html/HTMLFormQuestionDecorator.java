package org.solutions.form.formatter.html;
import java.util.Map;

import nyla.solutions.global.data.Data;

import org.solutions.form.data.FormAnswer;
import org.solutions.form.data.FormQuestion;
import org.solutions.form.data.FormTable;
import org.solutions.form.data.ResponseType;


/**
 * HTMLFormQuestionDecorator handles rendering HTML for question 
 * of various types
 * @author Gregory Green
 * @version 1.0
 */
public class HTMLFormQuestionDecorator extends HTMLDecorator 
implements Comparable
{
   public static final String DEFAULT_QUESTION_STYLE_CLASS = "question";
   public static final String DEFAULT_QUESTION_TEXT_STYLE_CLASS = "questionText";
      

   /**
    * @param aFormQuestiont the form question to decorator
    */
   public HTMLFormQuestionDecorator(FormQuestion aFormQuestion)
   {
      super(aFormQuestion);
      this.setStyleClass(DEFAULT_QUESTION_STYLE_CLASS);
   }//--------------------------------------------

   
   public String format(Map aMap, String aTemplate) {
       return super.format(aMap, aTemplate);
   }

   public String getAnswerText() {
       FormQuestion question = this.getQuestion();
       StringBuffer text = new StringBuffer();
       
       if(question.getResponseType().isTable())
       {
          decorateResponseTable(question, text); 
       }
       else if(question.getResponseType().getType() == ResponseType.CHECKBOXES_TYPE
               || question.getResponseType().getType() == ResponseType.RADIO_TYPE)
       {
          decorateQuestionChoice(question, text);
       }
       else if(question.getResponseType().getType() == ResponseType.SELECT_LIST_TYPE)
       {
          if(question.hasAnswer())
          {
             decoratorAnswer(question, text);
          }
          else
          {
             decorateQuestionChoice(question, text);
          }
       }
       else
       {
          decoratorAnswer(question, text);
       }
       return text.toString();
   }
   /**
    * 
    * 
    * @see org.solutions.form.data.FormComponent#getText()
    */
   public String getText() 
   {
      FormQuestion question = this.getQuestion();
      logger.debug("getText(): questionId=" + question.getQuestionId());
      String text = super.getText();
      if (isAutoNumber())
          text = getNumberLabel() + " " + text;
      StringBuffer questionText = new StringBuffer();
      
      
      questionText.append("<span class=\"")
         .append(getQuestionTextStyleClass()).append("\">")
         .append(text)
         .append("</span>");      
      
      if(question.getResponseType().isTable())
      {
         //Response Table
         StringBuffer tableText =new StringBuffer(); 
         decorateResponseTable(question, tableText); 
         return tableText.toString();
         //return toTableRow(tableText.toString());
         
      }
      else if(question.getResponseType().getType() == ResponseType.CHECKBOXES_TYPE
              || question.getResponseType().getType() == ResponseType.RADIO_TYPE)
      {
         StringBuffer inputsText = new StringBuffer();
         decorateQuestionChoice(question, inputsText);
         
         return toTableRow(questionText.append(inputsText).toString());
      }
      else if(question.getResponseType().getType() == ResponseType.SELECT_LIST_TYPE)
      {
         StringBuffer rightSide = new StringBuffer();
         if(question.hasAnswer())
         {
            logger.debug("decoratorAnswer: " + question.getQuestionId());
            decoratorAnswer(question, rightSide);
         }
         else
         {
            logger.debug("decorateQuestionChoice: " + question.getQuestionId());
            decorateQuestionChoice(question, rightSide);
         }
         
         return toTableRow(questionText.toString(),rightSide.toString(),"");
      }
      else if (question.getResponseType().getType() == ResponseType.MULTI_SELECT_TYPE) {
          FormAnswer answer = question.getAnswer();
          String rightSide = getHTMLAnswerDecorator(answer).getText();
          return toTableRow(questionText.toString(), rightSide,"");
      }
      else
      {

         StringBuffer rightSide = new StringBuffer();
         decoratorAnswer(question, rightSide);
         
         //Question Choice
         //decorateQuestionChoice(question, rightSide);     
         
         return toTableRow(questionText.toString(),rightSide.toString(),"");
      }
      
   }//--------------------------------------------
   /**
    * 
    * @return (FormQuestion) this.getComponent()
    */
   public FormQuestion getQuestion()
   {
      return (FormQuestion) this.getComponent();
   }//--------------------------------------------
   /**
    * Decorate response table format
    * @param aQuestion the question information
    * @param aText the text to add to
    * @throws Exception
    */
   private void decorateResponseTable(FormQuestion aQuestion, StringBuffer aText)
   {
        FormTable formTable = aQuestion.getFormTable();
         if (formTable != null)
         {
               HTMLFormTableDecorator decorator = getHTMLFormTableDecorator(formTable);
               decorator.setNumberLabel(getNumberLabel());
               decorator.setAutoNumber(this.isAutoNumber());
               aText.append(decorator.getText());    
         }
   }//-------------------------------------------------
   /**
    * (non-Javadoc)
    * 
    * @see java.lang.Comparable#compareTo(java.lang.Object)
    */
   public int compareTo(Object aObject)
   {
      if (aObject == null || !(aObject instanceof HTMLFormQuestionDecorator))
      {
         return -1;
      }

      HTMLFormQuestionDecorator q = (HTMLFormQuestionDecorator) aObject;
      return this.getQuestion().compareTo(q.getQuestion());
   }//--------------------------------------------
   /**
    * Decorate answer
    * @param aQuestion the question
    * @param aText the string buffer
    * @throws Exception
    */
   private void decoratorAnswer(FormQuestion aQuestion, StringBuffer aText)
   {
      if(!aQuestion.getResponseType().isAnswerable())
         return;
         
      if (aQuestion.getAnswer() != null)
      {
            aText.append(
               getHTMLAnswerDecorator(aQuestion.getAnswer()).getText());
      }
      else if (aQuestion.getResponseType().isAnswerableNonSelectable())
      {
            //No answer   
            StringBuffer fillIN = new StringBuffer();
            FormAnswer blankAnswer = new FormAnswer(aQuestion);

            fillIN.append( "");
                                
            blankAnswer.setValue(fillIN.toString());
            
            aText.append(
                   getHTMLAnswerDecorator(blankAnswer).getText());
         }
   }//---------------------------------------------------------------   

   /**
    * @return Returns the number.
    */
   public int getNumber()
   {
      return number;
   }//--------------------------------------------
   /**
    * @param number The number to set.
    */
   public void setNumber(int number)
   {
      this.number = number;
   }//--------------------------------------------
   /**
    * 
    * @return HTMLDecorator.decorateQuestionName(this.getQuestion())
    */
   public String getQuestionName()
   {
      return HTMLDecorator.decorateQuestionName(this.getQuestion(), null, this.isOffline());
   }//--------------------------------------------

   /**
    * @return Returns the commentLabel.
    */
   public final String getCommentLabel()
   {
      return commentLabel;
   }//--------------------------------------------
   /**
    * @param commentLabel The commentLabel to set.
    */
   public final void setCommentLabel(String commentLabel)
   {
      if (commentLabel == null)
         commentLabel = "";

      this.commentLabel = commentLabel;
   }//--------------------------------------------

   
	public boolean isAutoNumber() {
	    return autoNumber;
	}
	public void setAutoNumber(boolean autoNumber) {
	    this.autoNumber = autoNumber;
	}
	
   private boolean autoNumber;
   private String commentLabel = "";
  
   private int number = Data.NULL;

}
