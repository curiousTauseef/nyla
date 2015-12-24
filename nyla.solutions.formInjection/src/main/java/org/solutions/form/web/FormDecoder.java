package org.solutions.form.web;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.operations.logging.Log;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.web.Web;
import nyla.solutions.global.web.validation.Validation;

import org.solutions.form.FormGuide;
import org.solutions.form.FormVisitor;
import org.solutions.form.data.Form;
import org.solutions.form.data.FormAnswer;
import org.solutions.form.data.FormColumn;
import org.solutions.form.data.FormQuestion;
import org.solutions.form.data.FormRow;
import org.solutions.form.data.FormTable;
import org.solutions.form.data.ResponseTable;
import org.solutions.form.data.ResponseType;
import org.solutions.form.exception.AnswerException;
import org.solutions.form.formatter.html.HTMLFormQuestionDecorator;
import org.solutions.form.formatter.html.HTMLFormTableDecorator;


/**
 * <pre>
 * 
 *  FormDecoder provides a set of functions to decode form questions/answers
 *  
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public class FormDecoder
{
   public final static String seperator_string = "-";

   /**
    * Constructor for FormDecoder initializes internal data settings.
    *  
    */
   public FormDecoder(Form aForm)
   {
      setForm(aForm);
   }//--------------------------------------------
   /**
    * Decode form with data from the HTTP request
    * @param aRequest the HTTP request
    */
   public void decodeForm(HttpServletRequest aRequest)
   {
      this.decodeForm(Web.toMap(aRequest));
   }//--------------------------------------------
   /**
    * @param aForm the form
    * @param aParams
    *           the HTTP parameters
    * @return
    */
   public void decodeForm(Map aParams)
   {
      if (aParams == null)
         throw new IllegalArgumentException(
         "aParams required in FormDecoder.decodeForm");
      
      if (this.form == null)
         throw new IllegalArgumentException(
         "this.form required in FormDecoder.answerQuestions");
      
      //form.accept(visitor);
      //form.clearAnswers();
      
      // store answers retrieved here, so we can replace in the form object
      // when done.
      Map formAnswerMap = new HashMap();


      Object paramKey = null;
      List propKeys = new LinkedList();

      try
      {      
         Validation validation = null;
         //loop thru parameters
         //logger.debug("aParams="+aParams);
         
         for (Iterator i = aParams.keySet().iterator(); i.hasNext();)
         {
            paramKey = i.next();
            
            if(paramKey == null)
               continue;
            
            validation = Validation.getInstance(paramKey.toString());
        
            if(validation != null) {
               FormAnswer formAnswer = answer(validation.getFieldName(), aParams.get(paramKey));
               formAnswerMap.put(formAnswer.getKey(), formAnswer);
            }
            
            //Test for answer property 
            //i.e. <INPUT type='hidden' name='prop[canDeleteRow]_aTableId_row"
            //+ newAnswerRowIndex+" value='true'/>
            if(paramKey.toString().indexOf(ANSWER_PROP_PREFIX) > -1 )
            {
               //logger.debug(paramKey+" has property");
               //saveProperty(paramKey.toString(), (String)aParams.get(paramKey));
               propKeys.add(paramKey);
            }

         }
         
         // set the answers back on the form
         form.addFormAnswers(new HashSet(formAnswerMap.values()));
         
         // now do properties
         for (Iterator i = propKeys.iterator(); i.hasNext(); ) {
             paramKey = i.next();
             String val = (String)aParams.get(paramKey);
             saveProperty(paramKey.toString(), val);
         }
      }
      catch(Exception e)
      {
         throw new AnswerException( Debugger.stackTrace(e));
      }

   }//--------------------------------------------
   private String toCommaList(String[] s) {
       if (s == null)
           return null;
       StringBuffer buffer = new StringBuffer();
       for (int i = 0; i < s.length; i++) {
           buffer.append(s[i].trim() + ",");
       }
       if (buffer.length() > 0)
           return buffer.substring(0, buffer.length()-1);
       return buffer.toString();
   }
   /**
    * 
    * @param aFieldName field name
    * @param aAnswerValue answer value
    */
   private FormAnswer answer(String aFieldName, Object aAnswerValue)
   throws NoDataFoundException
   {
       String val = null;
       if (aAnswerValue instanceof String[]) {
           val = toCommaList((String[]) aAnswerValue);
       }
       else {
           val = aAnswerValue != null ? aAnswerValue.toString() : "";
       }
      //get form question
      logger.debug("Answering ="+aFieldName+" value="+val);
      FormAnswer answer = setAnswer(aFieldName, val);
      return answer;
      //logger.debug("FormAnswer PK before: "+answer.getKey());
      //form.unDelete(answer);
      //logger.debug("FormAnswer PK after: "+answer.getKey());
      //answer.setValue(val);
   }//--------------------------------------------
   /**
    * 
    * @param aFieldName field name i.e q1[0][1]
    * @return new or existing form question
    */
   private FormAnswer setAnswer(String aFieldName, String val)
   throws NoDataFoundException
   {    
       FormAnswer answer = null;
      //logger.debug("aFieldName="+aFieldName);
      int questionPK = this.parseQuestionPK(aFieldName);
      int column = -1;
      int row = -1;
      int tablePK = -1;
      //logger.debug("questionPK="+questionPK);
      FormQuestion question = form.findQuestionByID(new Integer(questionPK));
      if (question == null)
          throw new SystemException("No question with id " + questionPK + "can't set answer for input name " + aFieldName);
      
      if(isTableEntryField(aFieldName))
      {
         //answer by column and row
         column = parseColumnNumber(aFieldName);
         row = parseRowNumber(aFieldName);
         tablePK = parseTablePK(aFieldName);
         ResponseTable table = question.getResponseTable();
         if (table == null)
             throw new SystemException("Question does not have a table: can't set answer for input name " + aFieldName);
         if (table.getResponseTableId().intValue() != tablePK)
             throw new SystemException("Wrong table id: from question=" + table.getResponseTableId() + ", from web=" + tablePK + ": can't set answer for input name " + aFieldName);
         answer = question.setAnswer(val, row, column);
      }
      else {
          answer = question.setAnswer(val);
      }
      return answer;
      
   }//--------------------------------------------
   /**
    * 
    * @param aFieldName
    * @return column number from field name
    */
   private int parseColumnNumber(String aFieldName)
   {
      int colstart = aFieldName.indexOf(FormGuide.COLUMN_NUMBER_START);
      int colend = aFieldName.indexOf(FormGuide.COLUMN_NUMBER_END);
      String colNumber = aFieldName.substring(colstart+1,colend);
      
      return new Integer(colNumber).intValue();
   }//--------------------------------------------
   /**
    * 
    * @param aFieldName field name q1[0][1]
    * @return
    */
   private int parseRowNumber(String aFieldName)
   {
      int colstart = aFieldName.indexOf(FormGuide.COLUMN_NUMBER_START);
      colstart = aFieldName.indexOf(FormGuide.COLUMN_NUMBER_START,colstart+1);
      int colend = aFieldName.indexOf(FormGuide.COLUMN_NUMBER_END,colstart);
      String colNumber = aFieldName.substring(colstart+1,colend);
      
      return new Integer(colNumber).intValue();
   }//--------------------------------------------
   /**
    * 
    * @param aFieldName i.e q1[1][1]
    * @return aFieldName.indexOf(FormTags.COLUMN_NUMBER_START) > -1
    */
   private boolean isTableEntryField(String aFieldName)
   {
      return aFieldName.indexOf(FormGuide.COLUMN_NUMBER_START) > -1;
   }//--------------------------------------------
   /**
    * 
    * @param aFieldName i.e. q12 or q1[2][2]
    * @return
    */
   private int parseQuestionPK(String aFieldName)
   {
      int columnBraceStart = aFieldName.indexOf("[");
      
      if(columnBraceStart > -1)
         return new Integer(aFieldName.substring(1,columnBraceStart)).intValue();
      else
         return new Integer(aFieldName.substring(1)).intValue();
   }//--------------------------------------------
   /**
    * 
    * @param aFieldName ;tablePK=22;
    * @return
    */
   private int parseTablePK(String aFieldName)
   {
      //logger.debug("fieldName="+aFieldName);
      
      
      int tablePK = aFieldName.indexOf(FormGuide.TABLE_PK_PREFIX);
      
      if(tablePK < 0)
         throw new IllegalArgumentException
         ("TABLE_PK_PREFIX "+FormGuide.TABLE_PK_PREFIX+" not found in field name "+aFieldName);
      
      int endSemiColon = aFieldName.indexOf(";",tablePK+1);
      
      String value = aFieldName.substring(tablePK+FormGuide.TABLE_PK_PREFIX.length(),
                                          endSemiColon );
      
      //logger.debug("value="+value);
      
      return Integer.parseInt(value);
   }//--------------------------------------------
   /**
    * FormAnswer question from input parameters
    * @param aFormQuestion the form question
    * @param aParams the input parameters
    */
   protected  void decodeFormQuestion(FormQuestion aFormQuestion, Map aParams)
   throws NoDataFoundException
   {
      answerFormQuestion(aFormQuestion, this.getAnswerText(aFormQuestion, aParams));
   }//--------------------------------------------  
   /**
    * Set the value text of the answer to given answer text
    * @param aFormQuestion the form question
    * @param aAnswerText answer text
    */
   private void setTextAnswer(FormQuestion aFormQuestion, String aAnswerText)
   {            
      //form question   
      FormAnswer answer = aFormQuestion.getAnswer();
      if (answer == null)
      {
         //create new form question
         answer = new FormAnswer(aFormQuestion);
      }
      
      answer.setValue(aAnswerText);
      //logger.debug("Answering FI question:" + aFormQuestion.getText()
      //+ ": with answer:" + aFormQuestion.getAnswer().getValue());      
   }//--------------------------------------------

   /**
    * @param question
    * @param answertext
    */
   private void setCheckBoxAnswer(FormQuestion aFormQuestion, String aAnswerText)
   throws NoDataFoundException
   {
      if (aFormQuestion == null || !aFormQuestion.getResponseType().isSelectable())
      {
         Debugger.printInfo(this, "NOT ANSWERING check box "
         + aFormQuestion.getText() + " answer=" + aAnswerText);
         return;
      }

      Debugger.println(this, "FormAnswer check box " + aFormQuestion.getText()
      + " answer=" + aAnswerText + " isYesOrNoCheckBox="
      + aFormQuestion.isYesOrNoCheckBox() + " isOneChoiceOnly="
      + aFormQuestion.isOneChoiceOnly());
      
      FormAnswer answer = aFormQuestion.getAnswer();
      if(answer == null)
      {
         answer = new FormAnswer(aFormQuestion);
      }
      
      answer.setValue(aAnswerText);
      aFormQuestion.retrieveForm().addAnswer(answer);      

//      if (Data.isNull(aAnswerText))
//      {
//         if (aFormQuestion.getQuestion().isYesOrNoCheckBox())
//         {
//            aFormQuestion.pickChoiceByName("No");
//         }
//         else
//         {
//            aFormQuestion.pickDefaultChoice();
//         }
//      }
//      else
//      {
//         aFormQuestion.pickChoiceByID(Integer.parseInt(aAnswerText));
//      }
   }//--------------------------------------------

   /**
    * @param question
    * @param answertext
    */
   private void setMultipleChoiceAnswer(FormQuestion aFormQuestion,
                                        String aAnswerText)
   throws NoDataFoundException
   {
      //logger.debug("FormAnswer multipe choice box "+ aFormQuestion.getText() + " answer=" + aAnswerText
      //+ " isYesOrNoCheckBox=" + aFormQuestion.getQuestion().isYesOrNoCheckBox()
      //+ " isOneChoiceOnly=" + aFormQuestion.getQuestion().isOneChoiceOnly());
      /*
      if (Data.isNull(aAnswerText))
      {  
         aFormQuestion.unpickChoices();
      }
      else
      {
         aFormQuestion.pickChoiceByID(Integer.parseInt(aAnswerText));
      }
      */
      aFormQuestion.pickChoice(aAnswerText);
   }//--------------------------------------------

   /**
    * @param question
    * @param answertext
    */
   protected void answerFormQuestion(FormQuestion aFormQuestion, String aAnswerText)
   throws NoDataFoundException
   {

      ResponseType rt = aFormQuestion.getResponseType();

      if (rt.isSelectable())
      {

         if (rt.getType() == ResponseType.CHECKBOXES_TYPE)
         {
            this.setCheckBoxAnswer(aFormQuestion, aAnswerText);
         }
         else
         {
            this.setMultipleChoiceAnswer(aFormQuestion, aAnswerText);
         }

      }
      else if (rt.isAnswerableNonSelectable())
      {
         this.setTextAnswer(aFormQuestion, aAnswerText);
      }

   }//--------------------------------------------
   /**
    * 
    * @param aFormQuestion the form question
    * @param aParams iinput parameter
    * @return anwser text for form question
    */
   protected String getAnswerText(FormQuestion aFormQuestion, Map aParams)
   {
      String answertext = "";
      if (aFormQuestion.getResponseType().isAnswerable())
      {
         HTMLFormQuestionDecorator decorator = new HTMLFormQuestionDecorator(
         aFormQuestion);
         answertext = (String) aParams.get(decorator.getQuestionName());
         if (answertext == null)
         {
            answertext = "";
         }
      }
      return answertext;
   }//--------------------------------------------
   /**
    * @param aColumn the column
    * @param aFormRow  the form row
    * @param aFormTable the form table
    * @param answermap
    * @return
    */
   protected String getAnswerText(FormColumn aColumn, FormRow aFormRow,
                           FormTable aFormTable, Map aParam)
   {
      String answertext = "";
      if (aColumn.getResponseType().isAnswerable())
      {
         
                  
         //TODO: getAnswerText
         answertext = (String) aParam.get("");
         if (answertext == null)
         {
            answertext = "";
         }
      }
      return answertext;
   }//---------------------------------------------------------------------
   /**
    * @return the decoded form
    */
   public Form getForm()
   {
      return form;
   }//---------------------------------------------------------------------
   /**
    * @param form
    *           The form to set.
    */
   private void setForm(Form form)
   {
      if (form == null)
         throw new IllegalArgumentException(
         "form required in FormDecoder.setForm");

      this.form = form;
   }//--------------------------------------------
   /**
    * Add/replace answer property
    * @param aPropertyParamKey property key name from HTTP request i.e. 
    * property=canDeleteRow;table=t1q1;row=1;
    * @param aPropertyValue the property value
    */
   private void saveProperty(String aPropertyParamKey,String aPropertyValue)
   throws NoDataFoundException
   {
      //logger.debug("aPropertyParamKey="+aPropertyParamKey);
      
      
      //Get property name
      StringTokenizer tok = new StringTokenizer(aPropertyParamKey,";");
      
      String propertyName = tok.nextToken().split("=")[1];     
      //logger.debug("propertyName="+propertyName);
      
      FormTable formTable = null;
      FormRow formRow = null;
      if(aPropertyParamKey.indexOf("tableId=") > -1)
      {
         //table=DDDD
         String []tableId = tok.nextToken().split("=");
         
         
         //row=DDDD
         String [] rowNumber = tok.nextToken().split("=");
         
         //process table, 
         //TODO: two different questions cannot share the same table
         formTable = HTMLFormTableDecorator.findFormTableByID(form, tableId[1]);
         
         int row = new Integer(rowNumber[1]).intValue();
         formRow = formTable.getRow(row);
         if (formRow == null)
             formRow = formTable.createRow(row);
         
         //logger.debug("adding property "+propertyName+"="+aPropertyValue);
         formRow.addProperty(propertyName, aPropertyValue);         
      }      
   }//--------------------------------------------
   /**
    * @param setUpVisitior The setUpVisitior to set.
    */
   public void setVisitor(FormVisitor aVisitor)
   {
      if (aVisitor == null)
         throw new IllegalArgumentException(
         "aVisitor required in FormDecoder.setVisitor");
      
      this.visitor = aVisitor;
   }//--------------------------------------------
   
   
   /**
    * ANSWER_PROP_PREFIX = "property"
    */
   private static final String ANSWER_PROP_PREFIX = "property";
   private Log logger = Debugger.getLog(getClass());
   private Form form = null;
   private FormVisitor visitor = null;

   /**
    * @return Returns the visitor.
    */
   public final FormVisitor getVisitor()
   {
      return visitor;
   }
}
