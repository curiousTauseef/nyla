package nyla.solutions.web.commas.util;

import java.util.Set;

import nyla.solutions.commas.annotations.CMD;
import nyla.solutions.commas.annotations.COMMAS;
import nyla.solutions.commas.json.JsonAdvice;
import nyla.solutions.core.util.Text;


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
