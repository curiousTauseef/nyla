package nyla.solutions.web.commas;

import nyla.solutions.commas.CatalogCommas;
import nyla.solutions.commas.CommandFacts;
import nyla.solutions.commas.CommasInfo;
import nyla.solutions.commas.annotations.CMD;
import nyla.solutions.commas.annotations.COMMAS;
import nyla.solutions.commas.json.JsonAdvice;
import nyla.solutions.commas.json.JsonCommandSchema;
import nyla.solutions.core.exception.RequiredException;

/**
 * @author Gregory Green
 *
 */
@COMMAS
public class WebCommasCatalog extends CatalogCommas
{
	/**
	 * 
	 * @see nyla.solutions.global.patterns.command.commas.CatalogCommas#getCommandFacts(java.lang.String)
	 */
	@CMD(advice=JsonAdvice.JSON_ADVICE_NAME)
	@Override
	public CommandFacts getCommandFacts(String commandName)
	{
		return super.getCommandFacts(commandName);
	}// --------------------------------------------------------
	
	/**
	 * 
	 * @see nyla.solutions.global.patterns.command.commas.CatalogCommas#getCommasInfo(java.lang.String)
	 */
	@CMD(advice=JsonAdvice.JSON_ADVICE_NAME)
	@Override
	public CommasInfo getCommasInfo(String commasName)
	{
		if(commasName != null && commasName.length() > 0)
			return super.getCommasInfo(commasName);
		else
			return super.getCommasInfo();
	}// --------------------------------------------------------
	@CMD(advice=JsonAdvice.JSON_ADVICE_NAME)
	public JsonCommandSchema getJsonCommasSchema(String commandName)
	{
		if(commandName == null || commandName.length() == 0)
			throw new RequiredException("commandName");
		
		return new JsonCommandSchema(this.getCommandFacts(commandName));
		
	}// --------------------------------------------------------	
}
