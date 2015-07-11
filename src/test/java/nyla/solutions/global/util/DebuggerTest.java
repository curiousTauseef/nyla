package nyla.solutions.global.util;

import org.junit.Ignore;

import nyla.solutions.global.util.Debugger;
import junit.framework.TestCase;

@Ignore
public class DebuggerTest extends TestCase
{

	public DebuggerTest(String name)
	{
		super(name);
	}

	@SuppressWarnings("null")
	public void testPrintlnObjectObject()
	{
		//The Debugger toString(Object) can be used to debug objects where the toString method is not implemented.
		String[] arraysNicely = { "first","second"};
		String text = Debugger.toString(arraysNicely); 
		assertEquals("{[0]=first ,[1]=second}", text);
		
		
		//The print method wraps log levels of DEBUG,INFO,WARN and FATAL
		Debugger.printInfo("This is a INFO level message");
		
		//Two arguments can be passed where the first is the calling object
		//The debugger will prepend the calling objects class name to the logged output
		Debugger.println(this,"This is a DEBUG level message");
		
		//Debugger can be used to efficiently print exception information
		text = null;
		try{
			text.toString(); //causes a null pointer exception
		}
		catch(NullPointerException e)
		{
		    //Use the stackTrace method to get the string version of the exception call stack
			String stackTrace = Debugger.stackTrace(e);
			
			//Print warn level
		    Debugger.printWarn(this,stackTrace);
		    
		    
		   //stack trace will be automatically to created if the exception object is passed
			Debugger.printError(e); 
			
		    Debugger.printFatal(this,"Stack trace will not be printed, because this is not an exception object.");
		}
	}

}
