package nyla.solutions.global.patterns.command.commas.json;

import nyla.solutions.global.patterns.command.Command;
import nyla.solutions.global.patterns.command.aop.Advice;
import nyla.solutions.global.patterns.command.commas.CommandFacts;
import nyla.solutions.global.patterns.command.commas.annotations.Aspect;

@Aspect(name=JsonAdvice.JSON_ADVICE_NAME)
public class JsonAdvice implements Advice
{
	public static final String JSON_ADVICE_NAME  = "jsonAdvice";
	
	public Command<?,?> getBeforeCommand()
	{
		return new JsonBeforeCommand();
	}

	public Command<?,?> getAfterCommand()
	{
		return new JsonAfterCommand();
	}

	public CommandFacts getFacts()
	{
		return facts;
	}

	public void setFacts(CommandFacts facts)
	{
		this.facts = facts;
	}

	private CommandFacts facts;
}
