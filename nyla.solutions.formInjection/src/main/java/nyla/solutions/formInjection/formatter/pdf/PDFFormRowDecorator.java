package nyla.solutions.formInjection.formatter.pdf;

import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nyla.solutions.formInjection.data.Comment;
import nyla.solutions.formInjection.data.FormAnswer;
import nyla.solutions.formInjection.data.FormRow;
import nyla.solutions.formInjection.formatter.FormDecorator;
import nyla.solutions.global.data.Data;
import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.security.user.data.User;
import nyla.solutions.global.util.Organizer;
import nyla.solutions.global.util.Text;


/**
 * @author green_gregory
 * @version 1.0
 *
 * <b>PDFFormRowDecorator</b>  row document decorator
 * 
 */
public class PDFFormRowDecorator extends PDFDecorator
{
   /**
    * @param aComponent the Value object
    */
   private PDFFormRowDecorator(FormRow aRow, String aTemplate, short aType, User aViewer)
      throws Exception
   {
      super(aRow, aTemplate, aType,aViewer);
      
      if(aRow.getFormTable().isHorizontal())
         isHorizontal = true;
   } //-----------------------------------------
   public static PDFFormRowDecorator getFOInstance(FormRow aRow, User aViewer) throws Exception
   {
      if(aRow.getFormTable().isHorizontal())
      {
         return new PDFFormRowDecorator(aRow, "FO_ROW_HORIZONTAL", PDF_TYPE, aViewer);
      }
      else
      {
         return new PDFFormRowDecorator(aRow, "FO_ROW", PDF_TYPE, aViewer);
      }
   } //----------------------------------------------
   /**
    * Factory method
    * @param aRow the row the decorate
    * @param aDecorator the client decorate (use the same type)
    * @return FO or word instance based on the smart deocorator's type
    * @throws Exception
    */
   public static PDFFormRowDecorator getInstance(FormRow aRow, FormDecorator aDecorator)
      throws Exception
   {
         return getFOInstance(aRow,aDecorator.getViewer());    
   } //----------------------------------------------   
   
   public String getText()
   {
      if(isHorizontal)
      {
         return toHorizontal();
      }
      else
      {
         return toVertical();
      }
   }//--------------------------------------------
   /**
    * @return string decorated text
    */
   public String toVertical()
   {
      try
      {
         FormRow row = (FormRow) this.getComponent();
         
         
         //logger.debug("Decorating row #"+row.getNumber()
         //             +" name="+row.getName()
         //             +" pk="+row.getPrimaryKey());
         
         //Row header
         StringBuffer text = new StringBuffer();
         
         
         //Row answer
         Collection answers = retrieveSortedAnswers(row);
         
         FormAnswer answer = null;
         for (Iterator i = answers.iterator(); i.hasNext();)
         {
            answer = (FormAnswer) i.next();
            
            text.append(
                  decorateCell(PDFAnswerDecorator.getInstance(answer,this).getText()));
         }//--------------------------------------------
         
         Hashtable map = new Hashtable();
         map.put("text", text);
         map.put("name",fix(super.getText()));
         return Text.format(getTemplate(), map);
      }
      catch (Exception e)
      {
         throw new RuntimeException(e);
      }
   } //-----------------------------------
   /**
    * @return string decorated text
    */
   public String toHorizontal()
   {
      FormRow row = (FormRow)this.getComponent();
      
      FormAnswer answer = null;
      String answerText = "";
      String template = this.getTemplate();
      StringBuffer text = new StringBuffer();
      String rowText = null;
      Collection answers = Organizer.sortByJavaBeanProperty("columnNumber",row.getAnswers());
      
      for (Iterator i = answers.iterator(); i.hasNext();)
      {
         answer = (FormAnswer) i.next();
         if(!answer.isWithinTable())
            throw new SystemException("aAnswer "+answer+"  not within a table");
         
         Hashtable map = new Hashtable();
         map.put("colNumber",answer.getColumnNumber());
         map.put("columnText", answer.getFormColumn().getText());
         answerText = PDFAnswerDecorator.getInstance(answer,this).getText();
         map.put("answer",answerText);
         map.put("number",this.getNumberLabel());
         
         rowText =format(map,template);
         
         //logger.debug("rowText="+rowText);
         
         text.append(rowText);      
      }
      
      text.append(this.getLine());
      return text.toString();
   } //-----------------------------------
   /**
    * Decorate question comment
    * @param aComment the comment
    * @param aText the text to add to
    */
   protected void decorateFormComment(Comment aComment, StringBuffer aText)
   throws Exception
   {
      
      if(aComment == null || Data.isNull(aComment.getText()))
      {
         return;
      }
      
      String qCommentTemplate = "";
      

         qCommentTemplate = qFOCommentTemplate;

       String appComment = fix(aComment.getText());
       Map map = new HashMap();
       
       map.put("rowName",getRowName());
       
       map.put("text",appComment);
      
       aText.append(Text.format(qCommentTemplate,map));
   }//-----------------------------------------------------------
   public String getRowName()
   {      
      String rowName = String.valueOf(this.getRowNumber());
      //}
      return fix(rowName);
   }//------------------------------------------------   
   /**
    * Place row answers into the map
    * @param aMap map to add value
    * @param aRowNumber the row number
    */
   void addMap(Map aMap, int aRowNumber)
    throws NoDataFoundException
   {
      FormRow row = (FormRow)super.getComponent();
      List answers = row.getAnswers();
      for (Iterator i = answers.iterator(); i.hasNext(); ) {
          FormAnswer answer = (FormAnswer) i.next();
          int colNumber = answer.getColumnNumber().intValue()-1;
          String key = "Row" + aRowNumber + "a" + (colNumber-1);
          aMap.put(key, fix(answer.getText()));
          
          /* 
            logger.debug("Decorating NA answer");
            aMap.put(key, FormGuide.NA);
           */
      }
   } //---------------------------------------------------
   /**
    * @return row number
    */
   public int getRowNumber()
   {
      return rowNumber;
   }//-------------------------------------------------------

   /**
    * @param i  row number
    */
   public void setRowNumber(int i)
   {
      rowNumber = i;
   }
   private static String qFOCommentTemplate = "";
   private int rowNumber = 1;
   private boolean isHorizontal = false;
}
