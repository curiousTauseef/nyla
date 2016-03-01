package nyla.solutions.formInjection.formatter.html;


import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import nyla.solutions.formInjection.FormGuide;
import nyla.solutions.formInjection.data.Form;
import nyla.solutions.formInjection.data.FormAnswer;
import nyla.solutions.formInjection.data.FormColumn;
import nyla.solutions.formInjection.data.FormQuestion;
import nyla.solutions.formInjection.data.FormRow;
import nyla.solutions.formInjection.data.FormTable;
import nyla.solutions.formInjection.data.ResponseTable;
import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.util.Text;

/**
 * 
 * HTML Form table decorator
 */
public class HTMLFormTableDecorator extends HTMLDecorator
{
   public static final String DEFAULT_QUESTION_TABLE_STYLE_CLASS = "questionTable";

   /**
    * @param aComponent
    */
   public HTMLFormTableDecorator(FormTable table)
   {
      super(table);
 
      super.setStyleClass(DEFAULT_QUESTION_TABLE_STYLE_CLASS);
   }//--------------------------------------------

   /**
    * 
    * 
    * @see nyla.solutions.formInjection.data.FormComponent#getText()
    */
   public String getText()
   {
      StringBuffer text = new StringBuffer();
      FormTable table = (FormTable) this.getComponent();

      String tableId = decorateTableId(table);

      if(FormGuide.isHorizontal(table))
      {
         decorateHorizontalTable(text, table, tableId);
      }
      else
      {
         decorateVerticalTable(text, table, tableId);
      }
      
      return toTableRow(text.toString());
   }//--------------------------------------------
    
   /**
    * <table width="100%" id="${tableId}">
    <tr id="tableHeader">
      <td width="5%">${questionNumber}</td>   
      <td width="95%">${questionText}</td>
    </tr>
    ${rows}
   </table>
    * @param aText
    * @param aFormTable
    * @param aTableId
    */
   private void decorateHorizontalTable(StringBuffer aText, FormTable aFormTable, String aTableId)
   {
      Hashtable map = new Hashtable();
      map.put("tableId",aTableId);
      
      String template = "";
       
      if(aFormTable.isFixed())
      {
          template = this.getTemplateAsString("horizontal_table.fixed");
      }
      else
      {
          template = this.getTemplateAsString("horizontal_table");
      }
      
      map.put("questionNumber",this.getNumberLabel());
      map.put("questionText",fix(aFormTable.getFormQuestion().getText()));
      map.put("styleClass",this.getStyleClass());
      map.put("textStyleClass",this.getQuestionTextStyleClass());      
      
      //build rows
      StringBuffer rows = new StringBuffer();
      if(aFormTable.hasRows())
      {
         this.decorateRows(aFormTable, rows);
      }
      else if (aFormTable.getFormQuestion().getResponseTable().hasColumns())
      {
         logger.debug("Creating blank row for columns");
         if(aFormTable.isFixed())
         {
            rows.append("<tr>");
                        
            rows.append("<td valign='top' ")
                   .append(" colspan=\"2\"> ")
                   .append(SPACE).append("</td>");
            rows.append("</tr>").append(NEWLINE);
         }
         else
         {
            for(int rowNumber = 0; rowNumber <aFormTable.getDefaultRowSize();rowNumber++)
            {
               decorateBlankHorizontalRow(aFormTable, rowNumber, rows);
               
            }            
         }
      }
      
      map.put("rows",rows.toString());       
      aText.append(format( map, template));
   }//--------------------------------------------
   /**
    * Create blank row
    * @param aFormTable
    * @param aRowNumber the row number
    * @param aRows the text to append
    */
   protected void decorateBlankHorizontalRow(FormTable aFormTable, int aRowNumber, StringBuffer aRowsText)
   {
      FormRow blankRow = createBlankHorizontalRow(aFormTable, aRowNumber);
      aRowsText.append(getHTMLFormQuestionDecorator(blankRow).getText());
   }//--------------------------------------------
   
   public static FormRow createBlankHorizontalRow(FormTable aFormTable, int aRowNumber) {
       FormRow blankRow = aFormTable.createRow(aRowNumber);
       blankRow.addAnswer(0, null);
       /*
       FormQuestion formQuestion = aFormTable.getFormQuestion();
       for (Iterator i = blankRow.getFormColumns().values().iterator(); i.hasNext(); )
       {
           FormColumn col = (FormColumn) i.next();
           blankRow.addAnswer(col.getNumber().intValue(), null);
           
       }
       */
       return blankRow;
   }

   protected HTMLFormRowDecorator getHTMLFormQuestionDecorator(FormRow aFormRow)
   {
      HTMLFormRowDecorator decorator = getHTMLFormRowDecorator(aFormRow);
      decorator.copy(this);
      decorator.setNumberLabel(this.getNumberLabel());
      decorator.setAutoNumber(this.isAutoNumber());
      return decorator;
   }//--------------------------------------------
   /**
    * @param aText
    * @param aFormTable
    * @param aTableId
    */
   private void decorateVerticalTable(StringBuffer aText, FormTable aFormTable, String aTableId)
   {
      //StringBuffer tabletext = new StringBuffer();
      
      Map map = toMap();      
      
      String template = "";
         
      if(aFormTable.isFixed())
      {
         template = this.getTemplateAsString("vertical_table.fixed");
      }
      else
      {
         template = this.getTemplateAsString("vertical_table");
      }
      
      
 
      aText.append(format(map,template));
   }//--------------------------------------------
   /**
    * @param aFormTable
    * @param aTableId
    * @param map
    */
   public Map toMap()   
   {   
      HashMap map = new HashMap();
      
      FormTable table = this.getFormTable();
      FormQuestion question = table.getFormQuestion();
      
      map.put("tableId", HTMLFormTableDecorator.decorateTableId(table));

      map.put("questionText", question.getText());
      map.put("questionNumber", "");
     
      
      if(table.isHorizontal())
      {
      }
      else
      {
         this.buildVerticalMap(table,decorateTableId(table),map);
      }
      
      return map;
   }//--------------------------------------------
   /**
    * @param aFormTable
    * @param aTableId
    * @param map
    */
   private void buildVerticalMap(FormTable aFormTable, String aTableId, Map map)
   {
       FormQuestion question = aFormTable.getFormQuestion();
      map.put("styleClass", getStyleClass());
      map.put("tableId", aTableId);
      map.put("colspan", new Integer(aFormTable.getColumnCount()));
      map.put("questionNumber", this.getNumberLabel());
      map.put("questionText", question.getText());
      
      map.put("help","");
      
      //decorator columns headers  
      StringBuffer columns = new StringBuffer();
      if (question.getResponseTable().hasColumns())
      {
         decorateColumns(columns, aFormTable);
      }
      
      map.put("columns", columns);
      
      //procecess rows
      //show data
      StringBuffer rows = new StringBuffer();      
      if (aFormTable.hasRows())
      {
         decorateRows(aFormTable, rows);
      }
      else if (question.getResponseTable().hasColumns())
      {
         if(aFormTable.isFixed())
         {
            //blank rows
            rows.append("<tr>");
                        
            rows.append("<td valign='top' ")
                   .append(" colspan=\"").append(aFormTable.getColumnCount()).append("\"> ")
                   .append(SPACE).append("</td>");
            rows.append("</tr>").append(NEWLINE);
         }
         else
         {
            //decorator one row
            for(int rowNumber = 0;rowNumber < aFormTable.getDefaultRowSize();rowNumber++)
            {         
               decorateBlankVerticalRow(rows, aFormTable,rowNumber);
            }            
         }
      }      
      map.put("rows", rows);
   }//--------------------------------------------

   /**
    * decorate a number of Blank Vertical table Rows
    * @param aText the string to append
    * @param aFormTable the form table
    * @param aRowNumber the row number
    */
   private void decorateBlankVerticalRow(StringBuffer aText, FormTable aFormTable, int aRowNumber)
   {
     
      aText.append("<tr>");
      FormRow row = aFormTable.createRow(aRowNumber);
      for (int i = 0; i < aFormTable.getColumnCount(); i++)
      {
          FormAnswer answer = row.addAnswer(i, null);
         aText.append("<td valign='top'>").append(getHTMLAnswerDecorator(answer).getText()).append("</td>");
      }

      aText.append("</tr>").append(NEWLINE);
   }//--------------------------------------------
   /**
    * decorate Column for a given table
    * @param aText the text to append
    * @param aTable the form table
    */
   private void decorateColumns(StringBuffer aText, FormTable aTable)
   {
      HTMLColumnDecorator cd;
      FormColumn column;
      aText.append("<tr height=\"20\" class=\"").append(columnRowStyleClass).append("\">");
      //column header
      //Collection columns = Organizer.sortByJavaBeanProperty("number", aTable.getFormQuestion().getFormTable().getFormColumns());
      Map columns = aTable.getFormColumnMap();
      for (Iterator i = columns.values().iterator(); i.hasNext();)
      {
         column = (FormColumn) i.next();
         cd = getHTMLColumnDecorator(column);
         //cd.setEditable(this.isEditable());

         aText.append(cd.getText());
      }

      aText.append("</tr>");
   }//--------------------------------------------
   /**
    * decorate Rows for a table
    * @param aTable the form table
    * @param aTabletext the text to append
    */
   protected void decorateRows(FormTable aTable, StringBuffer aTabletext)
   {
      HTMLFormRowDecorator rd = this.getHTMLFormRowDecorator(aTable.getRows());
      rd.setNumberLabel(this.getNumberLabel());
      rd.setAutoNumber(this.isAutoNumber());
      aTabletext.append(rd.getText());
      /*
      FormRow formRow;
      for (Iterator i = aTable.getRows().iterator(); i.hasNext();)
      {
         formRow = (FormRow) i.next();

         rd = getHTMLFormRowDecorator(formRow);   
         rd.setNumberLabel(this.getNumberLabel());
         rd.setAutoNumber(this.isAutoNumber());
         aTabletext.append(rd.getText());
      }
      (*/
   }//--------------------------------------------
   
   /**
    * 
    * @return response table from form table
    */
   public ResponseTable getResponseTable()
   {
      return getFormTable().getFormQuestion().getResponseTable();
   }//--------------------------------------------

   /**
    * 
    * @return the form table
    */
   public FormTable getFormTable()
   {
      return (FormTable) this.getComponent();
   }//-------------------------------------------
   /**
    * Find the form table by the given form table PK i.e. NoDataFoundException 
    * @param aForm the form object
    * @param aTableID the table id i.e. NoDataFoundException (i.e. t9000q11)
    * @return the matching form table
    * @throws NoDataFoundException
    */
   public static FormTable findFormTableByID(Form aForm, String aTableID)
   throws NoDataFoundException
   {
      if(!aTableID.startsWith(TABLE_PK_PREFIX) || aTableID.indexOf(QUESTION_TABLE_PK_PREFIX) < 0)
         throw new IllegalArgumentException("Invalid table ID "+aTableID
           +" must start with \"t\" and contain a \"q\"");
      
      
      String formTablePK = aTableID.substring(1,aTableID.indexOf(QUESTION_TABLE_PK_PREFIX));
      
      if(!Text.isInteger(formTablePK))
         throw new IllegalArgumentException("Form Table PK "+formTablePK+"is not an integer ");
      
      String questionPK = aTableID.substring(aTableID.indexOf(QUESTION_TABLE_PK_PREFIX)+1,
                           aTableID.length());
      
      if(Text.isNull(questionPK) || !Text.isInteger(questionPK))
      {
         throw new IllegalArgumentException("Question PK \""+questionPK+"\" is not an integer in "+aTableID);
      }
      
      //found form question
      FormQuestion formQuestion = aForm.findFormQuestion(new Integer(questionPK));
      if(!formQuestion.hasTable())
         throw new NoDataFoundException("No form table in formQuestion "+formQuestion);
      
      return formQuestion.getFormTable();
   }//--------------------------------------------
   public String getFirstColumnText()
   {
       //TODO: getFirstColumnText()
       return null;
      //return this.getFormTable().getFormQuestion().getResponseTable().getFirstColText();
   }//--------------------------------------------      
   public static String decorateTableId(FormTable aFormTable)
   {
      return "t" + aFormTable.getPrimaryKey() + "q"
      + aFormTable.getFormQuestion().getPrimaryKey();

   }//--------------------------------------------
	public boolean isAutoNumber() {
	    return autoNumber;
	}
	public void setAutoNumber(boolean autoNumber) {
	    this.autoNumber = autoNumber;
	}
	
   private boolean autoNumber;
   private String columnRowStyleClass = "questionTableHeader";
   private final static String QUESTION_TABLE_PK_PREFIX = "q";
   private final static String TABLE_PK_PREFIX    = "t";
}
