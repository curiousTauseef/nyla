package nyla.solutions.office.msoffice.excel.patterns;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import nyla.solutions.dao.SQL;
import nyla.solutions.dao.jdbc.JdbcConstants;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.io.IO;
import nyla.solutions.global.patterns.command.Command;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.office.msoffice.excel.Excel;

/**
 * <pre>
 * Return Excel files for a given direct and 
 * call the Excel.load method to insert records.
 * 
 * User can before a before and after SQL file for pre and post processing.
 * 
 * Example Configuration Properties
 * 
 * nyla.solutions.office.msoffice.excel.patterns.ExcelFileDirDbLoaderCommand.rootDirectory=/Projects/Merck/ASAP/dev/Merck.ASAP.integration/runtime/input/loader/
 * nyla.solutions.office.msoffice.excel.patterns.ExcelFileDirDbLoaderCommand.beforeSqlFilePath=/Projects/Merck/ASAP/dev/Merck.ASAP.integration/installation/db/hsqldb/asap_hsql_ddl.sql
 * 
 * </pre>
 * @author Gregory Green
 *
 */
public class ExcelFileDirDbLoaderCommand implements Command<Boolean, String[]>
{
	/**
	 * @param args args[0] contains the directory name (prefix with rooDirectory if configured)
	 * @see solutions.global.patterns.command.Command#execute(java.lang.Object)
	 */
	public Boolean execute(String[] args) 
	{
		if(args == null || args.length == 0)
			return Boolean.FALSE;
		
		String directory = args[0];
		
		if(directory ==null || directory.length() == 0)
			return Boolean.FALSE;
		
		if(!"".equals(this.rootDirectory))
			directory = rootDirectory+directory;
		
		Debugger.println(this,"Looking for files in directory "+directory);
		
		SQL dao = null;
		File file =null;
		try
		 {
				dao = SQL.connect(jdbcDriver, connectionURL, dbUserName, dbPassword);
				
				
				if(!"".equals(this.beforeSqlFilePath))
					dao.execute(IO.readFile(beforeSqlFilePath));
				
				File[] files = IO.listFiles(directory,"*.xls");
				
				if(files == null || files.length == 0)
					return Boolean.FALSE;
				
				for (int i = 0; i < files.length; i++)
				{
					file = files[i];
					
					Excel.load(file, dao, null);
				}
				
				
				if(!"".equals(this.afterSqlFilePath))
					dao.execute(IO.readFile(afterSqlFilePath));
				
				return Boolean.TRUE;
		 }
		catch(IOException e)
		{
			throw new SystemException("Issue with reading file "+file+" arguments:"+Debugger.toString(args)+" ERROR:"+e.getMessage(),e);
		}
		catch(SQLException e)
		{
			throw new SystemException("Issue with load from excel files arguemnts:"+Debugger.toString(args)+" ERROR:"+e.getMessage(),e);
		}
		 finally
		 {
			   if(dao != null)
				   try{ dao.dispose(); } catch(Exception e){}
		 }
		
		
	}// --------------------------------------------------------
	
	
	/**
	 * @return the jdbcDriver
	 */
	public String getJdbcDriver()
	{
		return jdbcDriver;
	}
	/**
	 * @param jdbcDriver the jdbcDriver to set
	 */
	public void setJdbcDriver(String jdbcDriver)
	{
		this.jdbcDriver = jdbcDriver;
	}
	/**
	 * @return the connectionURL
	 */
	public String getConnectionURL()
	{
		return connectionURL;
	}
	/**
	 * @param connectionURL the connectionURL to set
	 */
	public void setConnectionURL(String connectionURL)
	{
		this.connectionURL = connectionURL;
	}
	/**
	 * @return the dbUserName
	 */
	public String getDbUserName()
	{
		return dbUserName;
	}
	/**
	 * @param dbUserName the dbUserName to set
	 */
	public void setDbUserName(String dbUserName)
	{
		this.dbUserName = dbUserName;
	}
	/**
	 * @return the dbPassword
	 */
	public char[] getDbPassword()
	{
		return dbPassword;
	}
	/**
	 * @param dbPassword the dbPassword to set
	 */
	public void setDbPassword(char[] dbPassword)
	{
		this.dbPassword = dbPassword;
	}
	/**
	 * @return the rootDirectory
	 */
	public String getRootDirectory()
	{
		return rootDirectory;
	}
	/**
	 * @param rootDirectory the rootDirectory to set
	 */
	public void setRootDirectory(String rootDirectory)
	{
		this.rootDirectory = rootDirectory;
	}


	
	/**
	 * @return the beforeSqlFilePath
	 */
	public String getBeforeSqlFilePath()
	{
		return beforeSqlFilePath;
	}


	/**
	 * @param beforeSqlFilePath the beforeSqlFilePath to set
	 */
	public void setBeforeSqlFilePath(String beforeSqlFilePath)
	{
		this.beforeSqlFilePath = beforeSqlFilePath;
	}


	/**
	 * @return the afterSqlFilePath
	 */
	public String getAfterSqlFilePath()
	{
		return afterSqlFilePath;
	}


	/**
	 * @param afterSqlFilePath the afterSqlFilePath to set
	 */
	public void setAfterSqlFilePath(String afterSqlFilePath)
	{
		this.afterSqlFilePath = afterSqlFilePath;
	}



	private String jdbcDriver = Config.getProperty(ExcelFileDirDbLoaderCommand.class,"jdbcDriver",Config.getProperty(JdbcConstants.JDBC_DRIVER_PROP,""));
	private String connectionURL = Config.getProperty(ExcelFileDirDbLoaderCommand.class,"connectionURL",Config.getProperty(JdbcConstants.JDBC_CONNECTION_URL_PROP,""));
	private String dbUserName = Config.getProperty(ExcelFileDirDbLoaderCommand.class,"dbUserName",Config.getProperty(JdbcConstants.JDBC_USER_PROP,""));
	private char[] dbPassword = Config.getPropertyPassword(ExcelFileDirDbLoaderCommand.class,"dbPassword",Config.getPropertyPassword(JdbcConstants.JDBC_PASSWORD_PROP,""));
	private String rootDirectory = Config.getProperty(ExcelFileDirDbLoaderCommand.class,"rootDirectory","");
	private String beforeSqlFilePath = Config.getProperty(ExcelFileDirDbLoaderCommand.class,"beforeSqlFilePath","");
	private String afterSqlFilePath = Config.getProperty(ExcelFileDirDbLoaderCommand.class,"afterSqlFilePath","");
}
