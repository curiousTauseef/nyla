package nyla.solutions.global.patterns.command.commas;

import nyla.solutions.global.patterns.command.commas.annotations.CMD;
import nyla.solutions.global.patterns.command.commas.annotations.COMMAS;

@COMMAS
public class HelloWorldCommand
{
	@CMD
	public String hello(String message)
	{
	
		return message;
	}
	@CMD 
	public String helloEnv(String env, String text)
	{
		return text+env; 
		
	}
}
