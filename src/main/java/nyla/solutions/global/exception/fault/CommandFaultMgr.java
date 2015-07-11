package nyla.solutions.global.exception.fault;

import nyla.solutions.global.exception.EmailFaultCommand;
import nyla.solutions.global.operations.ClassPath;
import nyla.solutions.global.patterns.command.Command;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;

/**
 * By default the EmailFaultCommand is used.
 * 
 * @author Gregory Green
 *
 */
public class CommandFaultMgr extends FaultMgr implements FaultService
{
	public CommandFaultMgr()
	{
		
	}// --------------------------------------------------------

	private void init() 
	throws IllegalAccessException, InstantiationException
	{
		if(this.command == null)
			command = getCommand();
	}// --------------------------------------------------------
	/**
	 * @see nyla.solutions.global.exception.fault.FaultMgr#raise(java.lang.Throwable)
	 */
	@Override
	public FaultException raise(Throwable e)
	{
	   
		FaultException faultException = super.raise(e);
		
		try
		{
			
			
			if(this.command == null)
				   init();
			
			this.command.execute(faultException);
			
			return faultException;
		}
		catch (Exception initException)
		{
			
			Debugger.printWarn(initException);
			return faultException;
		}
	}// --------------------------------------------------------

	/**
	 * @see nyla.solutions.global.exception.fault.FaultMgr#raise(java.lang.Throwable, java.lang.Object)
	 */
	@Override
	public FaultException raise(Throwable e, Object argument)
	{
		   
		FaultException faultException = super.raise(e, argument);
		
		try
		{
			if(this.command == null)
				   init();
			  
			   this.command.execute(faultException);
		}
		
		catch (Exception e1)
		{
			// print warning
			Debugger.printWarn(e1);
		}
		  
		return faultException;
	}// --------------------------------------------------------

	public static Command<Object,Exception> getCommand() 
	throws IllegalAccessException, InstantiationException
	{
		return ClassPath.newInstance(COMMAND_CLASS_NAME);
	}// --------------------------------------------------------
	
	private static final String COMMAND_CLASS_NAME = Config.getProperty(CommandFaultMgr.class,"COMMAND_CLASS_NAME",EmailFaultCommand.class.getName());
	private Command<Object, Exception> command;

}
