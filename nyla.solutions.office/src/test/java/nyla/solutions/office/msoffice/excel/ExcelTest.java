package nyla.solutions.office.msoffice.excel;

import junit.framework.TestCase;
import nyla.solutions.global.util.Debugger;

import org.junit.Assert;
import org.junit.Ignore;

@Ignore
public class ExcelTest extends TestCase
{

	public ExcelTest(String name)
	{
		super(name);
	}

	public void testRetrieveSheetString()
	throws Exception
	{
		Excel excel = Excel.getExcel(filePath);
		
		Assert.assertNotNull(excel);
		
		ExcelSheet sheet = excel.retrieveSheet(sheetName);
		
		Assert.assertNotNull(sheet);
		
		String[] row = sheet.getRow(0);
		
		Assert.assertTrue(row != null && row.length > 0);
		
		Debugger.println(this,Debugger.toString(row));
		
		Assert.assertTrue(sheet.getColumnCount() >  1);
		
		Assert.assertTrue(sheet.getRowCount() >  1);
		
		Debugger.println(this,"sheet.getRowCount()="+sheet.getRowCount());
		
	}

	private String sheetName = "RYN-CC";
	private String filePath = "runtime/input/input.xls";
}
