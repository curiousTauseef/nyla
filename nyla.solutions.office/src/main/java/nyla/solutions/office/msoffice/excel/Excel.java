package nyla.solutions.office.msoffice.excel;


import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;




import nyla.solutions.dao.Inserter;
import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.operations.ClassPath;
import nyla.solutions.global.patterns.Disposable;
import nyla.solutions.global.patterns.creational.KeyValueCRUD;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.JavaBean;
import nyla.solutions.office.msoffice.excel.jxl.JxlSheet;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 
 * <pre>
 * Excel provides a set of functions to handle Excel files
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class Excel implements Disposable
{
   /**
    * 
    * Constructor for Excel initializes internal 
    * data settings.
    */
   private Excel(File aFile)
   throws IOException
   {
      
      //Open file for read
      try
	{
		this.workbook = Workbook.getWorkbook(aFile);
	}
	catch (BiffException e)
	{
		throw new IOException(e);
	}
	

   }// -------------------------------------------
   /**
    * Create workable workbook
    * @param aFile the file to write
    * @return the writable workbook
    * @throws IOException
    */
   public static WritableWorkbook createWritableWorkbook(String  aFile) throws IOException
   {
     return createWritableWorkbook(new File(aFile));
   }// --------------------------------------------

   public static void close(WritableWorkbook writableWorkBook)
   throws Exception
   {
      if(writableWorkBook == null)
         return;
      
      writableWorkBook.write();
      writableWorkBook.close();
   }// --------------------------------------------

   
   /**
    * @return the writable workbook
    * @throws IOException
    */
   public static WritableWorkbook createWritableWorkbook(File  aFile) throws IOException
   {
      WritableWorkbook writableWorkbook = Workbook.createWorkbook(aFile);
      
      return writableWorkbook;
   }// --------------------------------------------



   /**
    * Singleton factory method
    * @return a single instance of the Excel object 
    * for the JVM
    */
   public static Excel getExcel(String aFile)
   throws IOException
   {
      return new Excel(new File(aFile));
   }// --------------------------------------------
   /**
    * Singleton factory method
    * @return a single instance of the Excel object 
    * for the JVM
    */
   public static Excel getExcel(File aFile)
   throws IOException
   {
      return new Excel(aFile);
   }//--------------------------------------------

   /**
    * Return the sheet within a workbook
    * @param aSheetName the sheet name
    * @return the sheet object
    * @throws Exception
    */
   public ExcelSheet retrieveSheet(String aSheetName)   
   {
	   Sheet sheet = workbook.getSheet(aSheetName);
	   if(sheet == null)
		   return null;
	   
      return new JxlSheet(sheet);
   }// --------------------------------------------
   /**
    * 
    * @param aSheetNumber 1 or higher
    * @return
    */
   public ExcelSheet retrieveSheet(int sheetNumber)   
   {
	   Sheet sheet = workbook.getSheet(sheetNumber-1);
	  
	   if(sheet == null)
		   return null;
	   
		return new JxlSheet(sheet);
   }// --------------------------------------------
   /**
    * Sample code
    * closeFailureSheet = closeFailureWorkBook.createSheet(SHEET_NAME,0);
               
               String[] Headers = { "I_MatterNumber", "ERROR_DESC"};
               
               Excel.writeRow(closeFailureSheet, Headers,closeMatterFailureRow++);
    * @param aInputs the columns to append
    */
   public static void writeRow(WritableSheet aWritableSheet, Object [] aInputs, int aRowNumber)
   {
      writeRow(aWritableSheet, aInputs, null, aRowNumber);
   }// --------------------------------------------
   /**
    * Write input to the excel row
    * @param aRowNumber the row number
    * @param aInputs the columns to append
    * @param aObject appended to end of row
    */
   public static void writeRow(WritableSheet aWritableSheet, Object [] aInputs, Object aObject, int aRowNumber)
   {
      if (aInputs == null)
         throw new IllegalArgumentException("aInputs required in Excel.append");

      try
      {         
         Object value = null;
         
         for (int col = 0; col < aInputs.length; col++)
         {
            value = aInputs[col];
            if(value == null)
               continue;
                        
            
            aWritableSheet.addCell(toWritableCell(col, aRowNumber, value));  
         }
         if(aObject != null)
         {
            //Append object to end of wor
            aWritableSheet.addCell(toWritableCell(aInputs.length,aRowNumber,aObject.toString()));
         }
      }   
      catch (Exception e)
      {
         throw new SystemException(Debugger.stackTrace(e));
      }

   }// --------------------------------------------
   /**
    * Create a WritableCell (DateTime or Label for the given value
    * @param aColumnNumber the column number
    * @param aRowNumber the row number
    * @param aValue the value 
    * @return the format excel cell
    */
   private static WritableCell toWritableCell(int aColumnNumber, int aRowNumber, Object aValue)
   {
      if(aValue == null)
      {
         return new Label(aColumnNumber,aRowNumber,"");
      }
      else if(aValue instanceof Date)
      {
         return new DateTime(aColumnNumber,aRowNumber,(Date)aValue);
      }     
      else if (aValue instanceof Cell)
      {
         return new Label(aColumnNumber,aRowNumber,((Cell)aValue).getContents());
      }
      else
      {
         return new Label(aColumnNumber,aRowNumber,aValue.toString());
      }
   }// --------------------------------------------

   /**
    * User insert to load data from a file based on information in a given sheet
    * @param file
    * @param inserter
    * @param sheetName
    */
   public static  <T> void loadObjects(File file, KeyValueCRUD<String, T> creator, String sheetName, int keyColumnPosition, String className)
   throws IOException
   {
	   Excel excel = Excel.getExcel(file);
	   
	   ExcelSheet sheet = null;
	   
	   if(sheetName == null || sheetName.length() == 0)
	   {
		   //get first sheet
		   sheet = excel.retrieveSheet(1);
		   if(sheet == null)
			   throw new RequiredException("Could not retrieve first sheet");
		   
		   sheetName = sheet.getName();
	   }
	   else
	   {
		   sheet = excel.retrieveSheet(sheetName);
		   
		   if(sheet == null)
			   throw new RequiredException("Sheet \""+sheetName+"\" not found");
	   }
	   		   
	  //loop thru rows
	   int rowCount = sheet.getRowCount();
	   		   
	   if(rowCount == 0)
		   throw new SystemException("More than one row required");
	   
	   String[] rowCells = null;
	   
	  
	   String[] columnHeaders = sheet.getRow(0);

	   T value;
	   String key = null;
	   for(int row=1; row < rowCount;row++)
	   {
		   rowCells = sheet.getRow(row);
		
		   value = ClassPath.newInstance(className);
		   
		   key = rowCells[keyColumnPosition];
		   
		   for(int cols = 0; cols < columnHeaders.length;cols++)
		   {
			   //loop through columns			   
			   JavaBean.setProperty(value, columnHeaders[cols], rowCells[cols]);
		   }
		   
		   creator.save(key, value);
		
	   }

	   
   }// --------------------------------------------------------
   /**
    * User insert to load data from a file based on information in a given sheet
    * @param file
    * @param inserter
    * @param sheetName
    */
   public static void load(File file, Inserter inserter, String sheetName)
   throws IOException, SQLException
   {

		   Excel excel = Excel.getExcel(file);
		   
		   ExcelSheet sheet = null;
		   
		   if(sheetName == null || sheetName.length() == 0)
		   {
			   //get first sheet
			   sheet = excel.retrieveSheet(1);
			   if(sheet == null)
				   throw new RequiredException("Could not retrieve first sheet");
			   
			   sheetName = sheet.getName();
		   }
		   else
		   {
			   sheet = excel.retrieveSheet(sheetName);
			   
			   if(sheet == null)
				   throw new RequiredException("Sheet name \""+sheetName+"\" not found");
		   }
		   		   
		  //loop thru rows
		   int rowCount = sheet.getRowCount();
		   		   
		   if(rowCount == 0)
			   throw new SystemException("More than one row required");
		   
		   Object[] rowCells = null;
		   
		   StringBuilder sqlBuffer = new StringBuilder("insert into ");
		   Object[] columnHeaders = sheet.getRow(0);
		   
		   StringBuilder columnsSQL = new StringBuilder();
		   StringBuilder valuesSQL = new StringBuilder();
		   for(int cols = 0; cols < columnHeaders.length;cols++)
		   {
			   if(cols != 0)
				 {
				   valuesSQL.append(",");
				   columnsSQL.append(",");
				   
				 }
			   
			   valuesSQL.append("?");
			   columnsSQL.append(columnHeaders[cols]);
			      
		   }

		   sqlBuffer.append(sheetName)
		   .append(" (").append(columnsSQL).append(" )")
		   .append(" values (").append(valuesSQL).append(");");
		   
		   String sql = sqlBuffer.toString();

		   for(int row=1; row < rowCount;row++)
		   {
			   rowCells = sheet.getRow(row);
			   
			   //loop through columns
			   inserter.insert(rowCells, sql);
		   }

		   
	   
   }// --------------------------------------------
   /**
    * 
    * 
    * @see com.pfizer.ldm.global.patterns.Disposable#dispose()
    */
   public void dispose()
   {
      try
      {
         if(workbook != null)
         {
            workbook.close();
         }         
      }
      catch(Exception e)
      {
         Debugger.printError(e);
      }
   }// --------------------------------------------   
   //private static DateFormat dateFormat = new SimpleDateFormat(Config.getProperty("nyla.solutions.office.msoffice.excel.Excel.dateFormat", "MM/dd/yyyy"));
   //private static String defaultSheetName = "Sheet1";
   private Workbook workbook = null;
}