package nyla.solutions.global.patterns.aop;
import nyla.solutions.global.patterns.command.Command;
import nyla.solutions.global.patterns.command.commas.CommandFacts;

/**
 * Aspect similar to AOP based
 * @author Gregory Green
 *
 */
public interface Advice
{

	/**
	 * Before advice in the form  a command
	 * @return
	 */
	Command<?,?> getBeforeCommand();
	
	/**
	 * After advice in the form  a command
	 * @return
	 */
	Command<?,?> getAfterCommand();
	
	/**
	 * 
	 * @return the function facts
	 */
	CommandFacts getFacts();
	
	/**
	 * 
	 * @param facts the function facts
	 */
	void setFacts(CommandFacts facts);
}
