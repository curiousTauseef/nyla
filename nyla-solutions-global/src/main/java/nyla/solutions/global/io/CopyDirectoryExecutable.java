package nyla.solutions.global.io;

import java.io.IOException;



import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.io.IO;
import nyla.solutions.global.patterns.command.Environment;
import nyla.solutions.global.patterns.command.Executable;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;


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
