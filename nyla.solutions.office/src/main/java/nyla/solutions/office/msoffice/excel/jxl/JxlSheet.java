package nyla.solutions.office.msoffice.excel.jxl;

import nyla.solutions.global.exception.NotImplementedException;
import nyla.solutions.office.msoffice.excel.ExcelSheet;
import jxl.Cell;
import jxl.Sheet;

public class JxlSheet implements ExcelSheet
{
   /**
	 * 
	 */
	private static final long serialVersionUID = -2618749411548196973L;


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
    * @param i
    * @param j
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
