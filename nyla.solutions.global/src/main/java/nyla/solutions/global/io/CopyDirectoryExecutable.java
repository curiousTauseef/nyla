package nyla.solutions.global.io;

import java.io.IOException;

import nyla.solutions.core.exception.SystemException;
import nyla.solutions.core.io.IO;
import nyla.solutions.core.util.Config;
import nyla.solutions.core.util.Debugger;
import nyla.solutions.global.patterns.command.Environment;
import nyla.solutions.global.patterns.command.Executable;


public class CopyDirectoryExecutable implements Executable
{

	/**
	 * Copy directory
	 */
	public Integer execute(Environment env)
	{
		try
		{
			Debugger.println(this,"copy from "+source+" to "+destination);
			IO.copyDirectory(source, destination, pattern);
			
			return 0;
		} 
		catch (IOException e)
		{
			throw new SystemException(e);
		}
	}//---------------------------------------------
	private String source = Config.getProperty(getClass(),"source");
	private String destination = Config.getProperty(getClass(),"destination");
	private String pattern = Config.getProperty(getClass(),"pattern","*");

	
}
