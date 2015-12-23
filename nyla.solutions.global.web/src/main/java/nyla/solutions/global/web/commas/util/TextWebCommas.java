package nyla.solutions.global.web.commas.util;

import java.util.Set;

import nyla.solutions.global.patterns.command.commas.annotations.CMD;
import nyla.solutions.global.patterns.command.commas.annotations.COMMAS;
import nyla.solutions.global.patterns.command.commas.json.JsonAdvice;
import nyla.solutions.global.util.Text;

@COMMAS(name="Text")
public class TextWebCommas
{
	/**
	 * 
	 * @param lines input lines
	 * @return the 
	 */
	@CMD(advice= JsonAdvice.JSON_ADVICE_NAME)
	public Set<String> toSet(String[] lines)
	{
		return Text.toSet(lines);
	}

}
