package nyla.solutions.global.patterns.iteration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import nyla.solutions.global.exception.CommunicationException;
import nyla.solutions.global.patterns.Disposable;

/**
 * Treat a result set as an iterator
 * @author Gregory Green
 *
 */
public class ResultSetIterator implements Iterator<ResultSet>, Disposable
{
	/**
	 * 
	 * @param rs
	 */
	public ResultSetIterator(ResultSet rs)
	{
		resultSet = rs;
	}// --------------------------------------------------------

	@Override
	public boolean hasNext()
	{
		try
		{
			
		
			if(this.resultSet == null)
				return false;
			else if(!this.resultSet.next())
			{
				this.dispose();
				return false;
			}
			return true;
		}
		catch (SQLException e)
		{
			dispose();

			throw new CommunicationException(e.getMessage(),e);
		}
			
	}// --------------------------------------------------------

	@Override
	public ResultSet next()
	throws NoSuchElementException
	{
		if(resultSet == null)
			throw new NoSuchElementException();
		
		return resultSet;
	}// --------------------------------------------------------
	/**
	 * 
	 * @param resultSet the result set to cleanup
	 */
	public void dispose()
	{
		try
		{
			if(this.resultSet != null)
			this.resultSet.close();
		}
		catch(Exception e)
		{}
		
		resultSet = null;
	}// --------------------------------------------------------

	@Override
	public void remove()
	{
	}
	
	private ResultSet resultSet;

}
