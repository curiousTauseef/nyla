package nyla.solutions.office.msoffice.excel.jxl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import jxl.Cell;
import jxl.Sheet;
import nyla.solutions.core.exception.NotImplementedException;
import nyla.solutions.office.msoffice.excel.ExcelSheet;

/**
 * Facade for the JXL Excel API to the implement the ExcelSheet interface
 * @author Gregory Green
 *
 */
public class JxlSheet implements ExcelSheet
{

  public JxlSheet(Sheet sheet)
   {
      this.sheet = sheet;
   }// --------------------------------------------

   /**
    * Return the sheet name
    * @see solutions.global.data.Nameable#getName()
    */
   @Override
	public String getName()
	{
		return this.sheet.getName();
	}// --------------------------------------------------------
   /**
    * Set the sheet name
    * @see solutions.global.data.Nameable#setName(java.lang.String)
    */
   @Override
	public void setName(String name)
	{
		throw new NotImplementedException();
		
	}// --------------------------------------------------------
   /**
    * @param row row count starts from zero
    * @see nyla.solutions.office.msoffice.excel.ExcelSheet#getRow(int)
    */
   public String[] getRow(int row)
	{
	   	Cell[] cells = this.sheet.getRow(row);
	   	
	   	if(cells == null || cells.length == 0)
	   		return null;
	   	
	   	String[] rowValues = new String[cells.length];
	   	
	   	for (int i = 0; i < rowValues.length; i++)
		{
			rowValues[i] = cells[i].getContents();
		}
	   	
	   	
		return rowValues;
	}// --------------------------------------------------------
   /**
    * @param i the column
    * @param j the row
    * @return
    * @see jxl.Sheet#getCell(int, int)
    */
   public String getCell(int column, int row)
   {
      try
      {
         return sheet.getCell(column, row).getContents();   
      }
      catch(ArrayIndexOutOfBoundsException e)
      {
         return null;
      }
      
   }// --------------------------------------------------------
   /**
    * 
    * @see nyla.solutions.office.msoffice.excel.ExcelSheet#getRows()
    */
   @SuppressWarnings("unchecked")
   @Override
	public Collection<String[]> getRows()
	{
		int rowCount = this.getRowCount();
		if(rowCount <= 0)
			return Collections.EMPTY_LIST;
		
		ArrayList<String[]> list = new ArrayList<>(rowCount);
		
		for(int i =0; i < rowCount; i++)
		{
			list.add(this.getRow(i));
		}
		
		return list;
	}
   
   /**
    * @param cellName
    * @return
    * @see jxl.Sheet#getCell(java.lang.String)
    */
   public String getCell(String cellName)
   {
      return sheet.getCell(cellName).getContents();
   }// --------------------------------------------------------
   
   /**
	 * @return
	 * @see jxl.Sheet#getColumns()
	 */
	public int getColumnCount()
	{
		return sheet.getColumns();
	}
	
	/**
	 * @return
	 * @see jxl.Sheet#getRows()
	 */
	public int getRowCount()
	{
		return sheet.getRows();
	}
	
	
	private final jxl.Sheet  sheet;

}
