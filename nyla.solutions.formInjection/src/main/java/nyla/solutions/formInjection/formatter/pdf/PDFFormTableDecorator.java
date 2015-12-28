package nyla.solutions.formInjection.formatter.pdf;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

import nyla.solutions.formInjection.data.Column;
import nyla.solutions.formInjection.data.FormRow;
import nyla.solutions.formInjection.data.FormTable;
import nyla.solutions.formInjection.formatter.FormDecorator;
import nyla.solutions.global.security.user.data.User;
import nyla.solutions.global.util.Text;

//import org.dom4j.*;
/**
 * @author Gregory Green
 * @version 1.0
 *
 * <b>PDFFormTableDecorator</b>  form table decorator for FO-PDF.
 * 
 * 
 */
public class PDFFormTableDecorator extends FormDecorator
{
   /**
    * @param aComponent the Value object
    */
   private PDFFormTableDecorator(FormTable aTable, String aTemplate, short aType, User aViewer)
   throws Exception
   {
      super(aTable,aTemplate,aType, aViewer);
      
 
   }//-----------------------------------------
   /**
    * @param aResponseTable
    * @return XMLFormTableDecorator instance to process XML-FO documents
    * @throws Exception
    */
   public static PDFFormTableDecorator getInstance( FormTable aTable, FormDecorator aDecorator)
   throws Exception
   {
         return PDFFormTableDecorator.getFOInstance(aTable,aDecorator.getViewer());
   }//----------------------------------------------         
   /**
    * @param aResponseTable
    * @return XMLFormTableDecorator instance to process XML-FO documents
    * @throws Exception
    */
   public static PDFFormTableDecorator getFOInstance(FormTable aFormTable, User aViewer)
   throws Exception
   {
      if(aFormTable.isHorizontal())
      {
         return new PDFFormTableDecorator(aFormTable,"FO_RESPONSE_TABLE_HORIZONTAL",PDF_TYPE, aViewer);
      }
      else
      {
         return new PDFFormTableDecorator(aFormTable,"FO_RESPONSE_TABLE",PDF_TYPE, aViewer);
      }
   }//----------------------------------------------
   /**
    * @return string decorated text
    */
   public String getText()
   {
      //clear comments
      
      
      StringBuffer text = new StringBuffer();
      
      FormTable table = (FormTable)this.getComponent();
      
      if(table.isHorizontal())
      {
         text.append(toHorizontal());
      }
      else
      {
         text.append(toVertical());
      }
      
      //text.append(rowComments);
      
      return text.toString();
      
      
   }//------------------------------------------------------
   /**
    * Get row decorator instance
    * @param aRow the row information
    * @param aSmartDecorator smart decorator
    * @return row decorator instance
    */
   private PDFFormRowDecorator getRowDecorator(FormRow aRow,FormDecorator aSmartDecorator)
   throws Exception
   {

         return PDFFormRowDecorator.getInstance(aRow,aSmartDecorator);
   }//----------------------------------------
   
   /**
    * 
    * @return FO XML 
    */
   private String toVertical()
   {
      try
      {
         
         FormTable table = (FormTable)this.getComponent();
         
         logger.debug("Decoratoring table PK="+table.getPrimaryKey());
                            
         Hashtable map = new Hashtable(); 
         map.put("name",fix(super.getText()));
         map.put("questionText",table.getFormQuestion().getText());
         
         
         //Column Headers
         
         StringBuffer tableText = new StringBuffer();

         //Columns
         Collection c = table.getFormColumnMap().values();
         
         //Added blank column
         StringBuffer colsText = new StringBuffer();
         
         //Process columns
         processColumns(table, map, c, colsText);
         tableText.append(this.decorateRow(colsText.toString()));
         
         
         //Rows
         c = getRows(table);
         FormRow row = null;
         StringBuffer rowCommentsText = new StringBuffer();
         
         PDFFormRowDecorator rowDecorator = null;
         if(c != null  && !c.isEmpty())
         {
            int rowNumber = 1;
            for(Iterator i = c.iterator();i.hasNext();)
            {
               
               row = (FormRow)i.next();
               //logger.debug("Decorating row "+row.getName());   
               rowDecorator = this.getRowDecorator(row,this);
               
               tableText.append(rowDecorator.getText());
               
               //TODO: if(! row.isDeleted())
               
                  rowDecorator.setRowNumber(rowNumber);
                  rowNumber++;
               
            }
         }
         
         
         map.put("text",tableText);

         String format = Text.format(getTemplate(),map);
         
         // Add row comments
         tableText.setLength(0);
         tableText.append(format);
         tableText.append(rowCommentsText);
         
         return tableText.toString();
      }
      catch (Exception e)
      {
        throw new RuntimeException(e);
      }
   }//-----------------------------------------------------------
   /**
    * Process columns with width 
    * @param responseTable
    * @param map 
    * @param c
    * @param colsText
    * @throws Exception
    */
   private void processColumns(
      FormTable aTable,
      Hashtable map,
      Collection c,
      StringBuffer colsText)
      throws Exception
   {
      Column col;
      
      FormTable table = (FormTable)this.getComponent();
     

      if(c != null)
      {
         //process first column
         Hashtable widthMap = new Hashtable();

         int tableColumnCount =  table.getColumnCount();

         StringBuffer tableColumns = new StringBuffer();

        
         //Added additional columns
         int colNumber = 1;         
         for(Iterator i = c.iterator();i.hasNext();colNumber++)
         {
      
            col = (Column)i.next();
            //logger.debug("Decorating col "+col.getName());               
            colsText.append(PDFColumnDecorator.getFOInstance(col,this.getViewer()).getText());
            
            widthMap.put("width", PDFColumnDecorator.columWidth( tableColumnCount,colNumber));
            
            tableColumns.append(Text.format(this.getColumnDeclarationTemplate(),widthMap));
         }
         
         
         map.put("tableColumns",tableColumns);
      
      }
   }//-----------------------------------
   /**
    * 
    * @return FO XML 
    */
   private String toHorizontal()
   {
      try
      {
         
         FormTable table = (FormTable)this.getComponent();
         
         logger.debug("Decoratoring table PK="+table.getPrimaryKey());
                            
         Hashtable map = new Hashtable(); 
         map.put("name",fix(super.getText()));
         map.put("questionText",table.getFormQuestion().getText());         
         
         StringBuffer tableText = new StringBuffer();      
         
         //Rows
         Collection rows = getRows(table);
         FormRow row = null;
         
         PDFFormRowDecorator rowDecorator = null;
         if(rows != null  && !rows.isEmpty())
         {
            int rowNumber = 1;
            for(Iterator i = rows.iterator();i.hasNext();)
            {
               
               row = (FormRow)i.next();
               //logger.debug("Decorating row "+row.getName());   
               rowDecorator = this.getRowDecorator(row,this);
                           
               tableText.append(rowDecorator.getText());
               
               //TODO: if(! row.isDeleted())
               
                  rowDecorator.setRowNumber(rowNumber);
                  rowNumber++;
               
            }
         }
         
         
         map.put("text",tableText);

         String format = Text.format(getTemplate(),map);
         
         // Add row comments
         tableText.setLength(0);
         tableText.append(format);
         
         return tableText.toString();
      }
      catch (Exception e)
      {
        throw new RuntimeException(e);
      }
   }//-----------------------------------------------------------
   /**
    * 
    * @param aFormTable the form table
    * @return collection of form rows
    */
   private Collection getRows(FormTable aFormTable)
   {
      if(aFormTable.isEmpty())
      {
         ArrayList results = new ArrayList(1);
         
         results.add(this.decorateBlankHorizontalRow(aFormTable,0));
         
         return results;
      }
      else
      {
         return aFormTable.getRows();
      }
      
   }//--------------------------------------------
  

}
