/**
 * 
 */
package nyla.solutions.dao;


import nyla.solutions.global.data.DataRow;
import nyla.solutions.global.data.Textable;
import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.util.Debugger;

/**
 * @author Gregory Green 
 * 
 * Query object text object to returns a single text SQL select result
 *
 */
public class TextQuery extends Query implements Textable
{

	/**
	 * @return first text results
	 */
	public String getText()
	{		
	   try
	   {
		   DataResultSet rs = super.getResults();
		   
		   DataRow dataRow = (DataRow)rs.getRows().iterator().next();
		
		   return dataRow.getStrings()[0];
	   } 
	   catch (NoDataFoundException e)
	   {
		   throw new SystemException(Debugger.stackTrace(e));
	   }
	}//---------------------------------------------
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object arg0)
	{
		// TODO Auto-generated method stub
		return 0;
	}//---------------------------------------------	

}
