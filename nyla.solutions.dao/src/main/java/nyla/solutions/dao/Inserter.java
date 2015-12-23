package nyla.solutions.dao;

import java.sql.SQLException;

import nyla.solutions.global.exception.DuplicateRowException;
import nyla.solutions.global.patterns.Disposable;

/**
 * Interface to insert data
 * @author greeng3
 *
 */
public interface Inserter extends Disposable
{
	/**
	 * Insert method
	 * @param aInputs the input records
	 * @param aSQL the insert prepared statement
	 */
	public boolean insert(Object [] aInputs, String aSQL)
	throws SQLException, DuplicateRowException;

}
