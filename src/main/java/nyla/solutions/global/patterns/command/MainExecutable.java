package nyla.solutions.global.patterns.command;

import nyla.solutions.global.exception.FatalException;
import nyla.solutions.global.patterns.command.Environment;
import nyla.solutions.global.patterns.command.Executable;
import nyla.solutions.global.patterns.command.MainExecutable;
import nyla.solutions.global.patterns.servicefactory.ServiceFactory;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;


/**
 * Main executable to run one or more tasks.
 * 
 * Set the JVM properties -Dsolutions.global.patterns.command.MainExecutable=beanName
 * @author Gregory Green
 *
 */
public class MainExecutable implements Executable
{
	/**
	 * Execute executables with name that matches this class
	 * @param args the first argument may contain the 
	 */
	public void execute(Environment env,String[] args)
	{	
	   String executableName = (String)env.get(MainExecutable.class.getName());
	   if(executableName == null || executableName.length() == 0)
		executableName = mainExecutable;
	   
		try
		{
		   Debugger.println(this,"executableName="+executableName);
		   
			Executable executable = (Executable)ServiceFactory.getInstance().create(executableName);
			
			executable.execute(env,args);
		} 
		catch (RuntimeException e)
		{			
			Debugger.printError(e);
			throw e;
		}
	}//---------------------------------------------
	public static void main(String[] args)
	{
		try 
		{
			MainExecutable program = new MainExecutable();
			
			Environment env = new Environment();
			env.putAll(System.getProperties());
			
			program.execute(env, args);
			
		} 
		catch (Exception e) 
		{
			Debugger.printError(e);
			throw new FatalException(e);
			
		}		
	}//---------------------------------------------
	
	/**
	 * @return the mainExecutable
	 */
	public String getMainExecutable()
	{
		return mainExecutable;
	}//---------------------------------------------
	/**
	 * @param mainExecutable the mainExecutable to set
	 */
	public void setMainExecutable(String mainExecutable)
	{
		this.mainExecutable = mainExecutable;
	}//---------------------------------------------

	private String mainExecutable = Config.getProperty(this.getClass(),"mainExecutable",this.getClass().getName());

}
