package nyla.solutions.formInjection.formatter.html;



import java.text.MessageFormat;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import nyla.solutions.formInjection.FormGuide;
import nyla.solutions.formInjection.data.AnswerProperty;
import nyla.solutions.formInjection.data.FormAnswer;
import nyla.solutions.formInjection.data.FormRow;
import nyla.solutions.formInjection.web.AutoCounter;
import nyla.solutions.global.data.Textable;
import nyla.solutions.global.exception.SystemException;

/**
 * 
 * <pre>
 * HTMLFormRowDecorator provides a set of functions to
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class HTMLFormRowDecorator extends HTMLDecorator
{    
    private Collection rows;
    public HTMLFormRowDecorator(Collection rows) {
        super(
            new Textable() {
            	public String getText() { return ""; }
            	public int compareTo(Object o) { return 0; }
            }
         );
        this.rows = rows;
    }
    
   /**
    * @param aComponent
    */
   public HTMLFormRowDecorator(FormRow aFormRow)
   {
      this(toList(aFormRow));
   }//--------------------------------------------
   private static List toList(FormRow row) {
       List l = new LinkedList();
       l.add(row);
       return l;
   }
   /**
    * 
    * 
    * @see nyla.solutions.formInjection.data.FormComponent#getText()
    */
   public String getText()
   {
       StringBuffer text = new StringBuffer();
       
       for (Iterator i = rows.iterator(); i.hasNext(); ) {
      FormRow formRow = (FormRow) i.next();
     
      
      //Test if row is deleted
      if(formRow.isDeleted())
         return "";
      
      
      
      if(FormGuide.isHorizontal(formRow.getFormTable()))
      {
         decorateHorizontalRow(formRow,text);
      }
      else
      {
         decorateVerticalRow(formRow, text);
      }
       }
      return text.toString();
      
   }//--------------------------------------------
   /**
    *     <tr id="row${rowNumber}">
      <td colspan="2">
         <table>
            ${answers}
         </table>
         </td>
      </tr>
    * @param aFormRow
    * @param aText
    */
   private void decorateHorizontalRow(FormRow aFormRow, StringBuffer aText)
   {
      String template = this.getTemplateAsString("horizontal_table_rows");
      Hashtable map = new Hashtable();
      
      map.put("rowNumber",new Integer(aFormRow.getNumber()));
      
      //builds answers
      StringBuffer answers = new StringBuffer();
      FormAnswer answer = null;
      List al = new LinkedList(aFormRow.getAnswers());
      //Collections.reverse(al);
      AutoCounter counter = new AutoCounter();
      for (Iterator i = al.iterator(); i.hasNext();)
      {
         answer = (FormAnswer) i.next();
         // just in case db col numbers are wrong
         //answer.setColumnNumber(index++);
         buildHorizontalRowAnswer(answers,answer, counter);
      }
      
      if(FormGuide.canDelete(aFormRow))
      {
        this.decorateDeleteControls(answers,aFormRow);
      }
      
      map.put("answers",answers);
      
      //add results
      aText.append(format(map,template));
   }//--------------------------------------------
   /**      <tr id="col${colNumber}">
                <td id="number" width="2%">${number}</td>            
                <td id="questionText" width="13%">${columnText}</td>
                <td id="answer" width="85%">${answer}</td>
            </tr>
    * 
    * @param aText
    * @param aAnswer
    */
   private void buildHorizontalRowAnswer(StringBuffer aText,FormAnswer aAnswer, AutoCounter counter)
   {
      if(aAnswer == null)
         return;
      
      if(!aAnswer.isWithinTable())
         throw new SystemException("aAnswer "+aAnswer+"  not within a table");
      
      String template = getTemplateAsString("horizontal_table_row_answer");
      
      Hashtable map = new Hashtable();
      map.put("colNumber",aAnswer.getColumnNumber());
      map.put("columnText", fix(aAnswer.getFormColumn().getName()));
      String answer = this.getHTMLAnswerDecorator(aAnswer).getText();
      map.put("answer",answer);
      map.put("textStyleClass", this.getQuestionTextStyleClass());
      map.put("answerStyleClass", this.getAnswerStyle());
      map.put("questionWidth", this.getQuestionWidth());
      map.put("answerWidth", this.getAnswerWidth());
      if (isAutoNumber())
          map.put("numberLabel",this.getNumberLabel() + counter.increment() + ".");
      else
          map.put("numberLabel", "");
      
      String rowText =format(map,template);
      
      //logger.debug("rowText="+rowText);
      
      aText.append(rowText);
   }//--------------------------------------------
   /**
    * Render normall vertical table rows
    * @param aFormRow the form row
    * @param aText the string to append to
    */
   private void decorateVerticalRow(FormRow aFormRow, StringBuffer aText)
   {
      FormAnswer answer;
      aText.append("<tr ").append("id=\"row\" >");
      
      HTMLAnswerDecorator answerDecorator = null;
      
      Collection answers = retrieveSortedAnswers(aFormRow);
      
      boolean canDelete = false;
      AnswerProperty canDeleteProp = null;
      
      for (Iterator i = answers.iterator(); i.hasNext();)
      {
         answer = (FormAnswer) i.next();
         
         aText.append("<td valign=\"top\" width=\"")
           .append(this.decorateWidth(answer.getFormColumn())).append("\">");
         
         canDeleteProp = answer.retrieveProperty(FormGuide.CAN_DELETE_ROW_PROPERTY_NM);
         
         //logger.debug("canDeleteProp="+canDeleteProp);
         if(canDeleteProp != null && 
              Boolean.TRUE.toString().equalsIgnoreCase(String.valueOf(canDeleteProp.getValue())))
         {
            canDelete = true;
         }
         
         answerDecorator = getHTMLAnswerDecorator(answer);
         aText.append(answerDecorator.getText());
         
         if(!i.hasNext() && canDelete)
         {            
            decorateDeleteControls(aText,aFormRow);
         }
         
         aText.append("</td>");         
      }
      
      aText.append("</tr>");
   }//--------------------------------------------
   /**
    * If not readonly view add form_delete_dynamic_row images with deleteRow JavaScript 
    * function when clicked
    * @param aText the text to append
    * @param aFormRow the form row
    */
   private void decorateDeleteControls(StringBuffer aText,FormRow aFormRow)
   {
      if(this.isEditable())
      {
         if(FormGuide.isHorizontal(aFormRow.getFormTable()))
         {
            String horizontalDeleteRowControlTemplate = this.getTemplateAsString("horizontal_delete_row_controls");
            Map map = new Hashtable();
            map.put("imagePath",getSkinImagePath());
            map.put("tableId",HTMLFormTableDecorator.decorateTableId(aFormRow.getFormTable()));
            map.put("rowNumber", String.valueOf(aFormRow.getNumber()));
            aText.append(super.format(map,horizontalDeleteRowControlTemplate));
         }
         else
         {
            String [] inputs = { super.getSkinImagePath(), 
                                 "'"+HTMLFormTableDecorator.decorateTableId(aFormRow.getFormTable())+"'", 
                                 String.valueOf(aFormRow.getNumber())};
            aText.append(MessageFormat.format("<IMG src=\"{0}/form_delete_dynamic_row.gif\" onclick=\"deleteRow({1},{2});\"/>",
            inputs));
         }
      }
   }//--------------------------------------------
   /**
    * 
    * @return autoNumber boolean
    */
   public boolean isAutoNumber() 
   {
       return autoNumber;
   }//--------------------------------------------
   /**
    * 
    * @param autoNumber boolean to auto number decoration
    */
   public void setAutoNumber(boolean autoNumber) 
   {
       this.autoNumber = autoNumber;
   }//--------------------------------------------
   private boolean autoNumber = false;;
}
