package nyla.solutions.dao.executable;

import nyla.solutions.dao.AbstractDaoOperation;
import nyla.solutions.dao.SQL;
import nyla.solutions.global.exception.ConfigException;
import nyla.solutions.global.exception.SystemException;

import java.sql.*;

import nyla.solutions.global.patterns.command.Command;
import nyla.solutions.global.patterns.command.Environment;
import nyla.solutions.global.patterns.command.Executable;

/**
 *  * @deprecated use commands
 *  
 * @author Gregory Green
 * @version 1.0
 *
 * <pre>
 * <b>SqlExecutable</b>  represents an executed SQL statement
 * 

 * 
 */
public class  SqlExecutable extends AbstractDaoOperation
implements Executable, Command<Object, Environment>
{
	/**
	 * Execute a single SQL statement
	 */
	public void execute(Environment env, String[] args)
	{		
		 if(this.getSql() == null || this.getSql().length() == 0)
	         throw new ConfigException("Property \"sqlQuery\"  not setin "+this.getClass().getName());
	      
	      SQL sql = null;
	      try
	      {
	         sql = this.connect();
	         
	         sql.execute(this.getSql());
	         sql.commit();
	      }
	      catch(SQLException e)
	      {
	         sql.rollback();
	         throw new SystemException(e+" sql="+sql);
	      }
	      finally
	      {
	         if(sql != null)
	            sql.dispose();
	      }      
	}//---------------------------------------------
	
	/**
	 * Wrapper execution for a command
	 * @source
	 * @see solutions.global.patterns.command.Command#execute(java.lang.Object)
	 */
	public Object execute(Environment source)
	{
		execute(null,null);
		
		return null;
	}
}
