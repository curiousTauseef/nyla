package nyla.solutions.dao.jdbc.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import nyla.solutions.dao.ResultSetDataRow;
import nyla.solutions.dao.ResultSetObjectCreator;
import nyla.solutions.global.data.DataRow;
import nyla.solutions.global.exception.DataException;

/**
 * @author Gregory Green
 *
 */
public class DataRowMapper implements ResultSetObjectCreator<DataRow>
{

	public DataRow create(ResultSet rs, int index) 
	{
		try
		{
			DataRow dataRow =  new ResultSetDataRow(rs);
			
			dataRow.setRowNum(index);
			
			return dataRow;
			
		}
		catch(SQLException e)
		{
			throw new DataException(e.getMessage(),e);
		}
	}

}
