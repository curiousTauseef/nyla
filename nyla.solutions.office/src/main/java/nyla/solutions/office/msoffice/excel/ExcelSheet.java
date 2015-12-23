package nyla.solutions.office.msoffice.excel;

import nyla.solutions.global.data.Nameable;

public interface ExcelSheet extends Nameable
{
	   public String getCell(int column, int row);
	   
	   
	   public String getCell(String cellName);
	   
	   public int getRowCount();
	   
	   public String[] getRow(int i);
	   
	   public int getColumnCount();
	   
}