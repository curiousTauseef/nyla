package org.solutions.form.formatter.pdf;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.solutions.data.Data;
import org.solutions.form.FormGuide;
import org.solutions.form.data.FormQuestion;
import org.solutions.form.data.FormTable;
import org.solutions.form.formatter.FormDecorator;
import org.solutions.security.user.data.User;
import org.solutions.util.Config;
import org.solutions.util.Text;
/**
 * @author Gregory Green
 * @version 1.0
 *
 * <b>PDFQuestionDecorator</b> form question decorator
 * 
 */
public class PDFQuestionDecorator extends PDFDecorator
{
   /**
    * @param aQuestion the question VO
    * @param aTemplateName the template name
    */
   private PDFQuestionDecorator(
      FormQuestion aQuestion,
      String aTemplateName,
      short aType, User aViewer, int aCurrentNumber)
      throws Exception
   {
      super(aQuestion, aTemplateName, aType, aViewer);
      
      currentNumber = aCurrentNumber;
       
   } //-----------------------------------------
   /**
    * 
    * @param aQuestion the question to decorate
    * @param aViewer the web user
    * @param aNumber the number
    * @return the word decorator instance
    * @throws Exception
    */
   private static PDFQuestionDecorator getFOInstance(FormQuestion aQuestion, User aViewer, int aNumber)
      throws Exception
   {
      return new PDFQuestionDecorator(aQuestion, "FO_QUESTION", PDF_TYPE,aViewer, aNumber);
   } //----------------------------------------------   
   /**
    * Factory method instanse based on calling smart decorator
    * @param aQuestion the smart component
    * @param aDecorator the decorator
    * @param aNumber the current number
    * @return Word or FO instance
    * @throws Exception
    */
   public static PDFQuestionDecorator getInstance(FormQuestion aQuestion,FormDecorator aDecorator,int aNumber)
        throws Exception
     {
         PDFQuestionDecorator decorator = PDFQuestionDecorator.getFOInstance(aQuestion, aDecorator.getViewer(),aNumber);
         decorator.copy(aDecorator);
         
         return decorator;
     } //----------------------------------------------      
   /**
    * @return string decorated text
    */
   public String getText()
   {
      try
      {
         FormQuestion question = (FormQuestion) this.getComponent();
         
         
         //question map
         Hashtable map = new Hashtable();
         
         map.put("indent","0");
         
         map.put("templateDIR",Config.getProperty("template.dir"));
                  
         
         
         //process numbering
         processNumbering(question, map);
         
        //Add Question
        if(question.getResponseType().isTable())
        {
           map.put("question","");
           //Response Table
           map.put("table", decorateFormTable(question));   
        }
        else           
        {
           map.put("question", fix(super.getText()));
           map.put("table", "");
        }

            
        //Question Choice
        //map.put("questionChoices", decorateFormQuestionChoices(question.getFormQuestionChoices())); 
        
         return Text.format(getTemplate(), map);
      }
      catch (Exception e)
      {
         throw new RuntimeException(e);
      }
   }//---------------------------------------------------------------

   /**
    * Decorate question comment
    * @param aComment the comment
    * @param aText the text to add to
    */
   protected static String decorateFormComment(String aComment, FormDecorator aDecorator)
   throws Exception
   {
      
      if(Data.isNull(aComment))
      {
         return "";
      }
      
      String qCommentTemplate = "";
      
         qCommentTemplate = qFOCommentTemplate;

       String appComment = aDecorator.fix(aComment);
       Map map = new HashMap();
       map.put("text",appComment);
      
       return Text.format(qCommentTemplate,map);
   }//-----------------------------------------------------------
   /**
    * Process question number
    * @param question the question
    * @param text the string buffer of currently formatted test
    * @param map the question value map
    */
   private void processNumbering(FormQuestion question, Map aMap)
   {          
      //Add number for top level questions
      //Question question = aFormQuestion.getQuestion();
      if(FormGuide.isNumbered(question))
      {
         aMap.put("number", this.getNumberLabel(question));
      }
      else
      {
         aMap.put("number", "");
      }
         
   }//--------------------------------------------------------------------------
   /**
    * Decorate response table format
    * @param aQuestion the question information
    * @param aText the text to add to
    * @throws Exception
    */
   private String decorateFormTable(FormQuestion aQuestion)
      throws Exception
   {
        FormTable table = aQuestion.getFormTable();
         if (table != null)
         {
           
               return
                  PDFFormTableDecorator
                     .getInstance(table, this)
                     .getText();                          
         }
         return "";
   }//-------------------------------------------------   
   /**
    * @return Returns the currentNumber.
    */
   public final int getCurrentNumber()
   {
      return currentNumber;
   }

   private final int currentNumber;
   private static String qFOCommentTemplate = "";
}//end class
