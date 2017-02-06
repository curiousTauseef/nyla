package nyla.solutions.web.commas;

import java.util.Collection;

import nyla.solutions.commas.CommandFacts;
import nyla.solutions.commas.CommasInfo;
import nyla.solutions.commas.CommasServiceFactory;
import nyla.solutions.core.util.Debugger;
import nyla.solutions.core.util.Organizer;



/**
 * Example
 * <select dojoType="dijit.form.ComboBox" id="commas" name="commas" autoComplete="true">
 *		  <option>Apples</option>
 *		  <option selected>Oranges</option>
 *		  <option>Pears</option>
 * </select>
 */
public class CommandUIDecorator
{
	
	public String getCratesSelectHtml()
	{

		String start = "<select dojoType=\"dijit.form.ComboBox\" id=\"commas\" name=\"commas\" autoComplete=\"true\">";

		StringBuilder html = new StringBuilder(start);
		
		CommasServiceFactory factory = CommasServiceFactory.getCommasServiceFactory();

		Collection<CommasInfo> commas = factory.getCommasInfos();
		

		for (CommasInfo crate : commas)
		{
			html.append("<option>").append(crate.getName()).append("</option>");
		}

		html.append("</select>");

		

		return html.toString();

	}// -----------------------------------------------
	/**
	 * 
	 * @return meta-data for all commands
	 */
	public Collection<CommasInfo> getCommasInfos()
	{
		if(sortedCommas != null)
			return sortedCommas;

		try
		{
			Collection<CommasInfo> commas = CommasServiceFactory.getCommasServiceFactory().getCommasInfos();

			sortedCommas = (Collection<CommasInfo>)Organizer.sortByJavaBeanProperty("name", commas);

			return sortedCommas;
		}
		catch (RuntimeException e)
		{
			Debugger.printError(e);
			throw e;
		}
	}// -----------------------------------------------
	
	public Collection<CommandFacts> getDefaultCommasCommandFacts()
	{
		CommasInfo commasInfo = CommasServiceFactory.getCommasServiceFactory().getCommasInfo();
		
		if(commasInfo == null)
			return null;
		Collection<CommandFacts> facts = commasInfo.getCommandFacties();
		
		if(facts == null || facts.isEmpty())
			return null;
		
		if(sortedCommas != null)
			return facts;
		
		facts = Organizer.sortByJavaBeanProperty("name", facts);
		
		return facts;
		
	}// --------------------------------------------------------

	private Collection<CommasInfo> sortedCommas = null;

	

}

