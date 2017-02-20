package nyla.solutions.core.patterns.conversion;

import java.util.Collection;
import nyla.solutions.core.util.Text;

/**
 * Get the emails from the given text
 * @author Gregory Green
 *
 */
public class TextToEmailsConverter implements Converter<String, Collection<String>>
{
	/**
	 * EMAIL_REG_EXP ="([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,25})$"
	 */
	public static final String EMAIL_REG_EXP ="([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,25})$";
	
	@Override
	public Collection<String> convert(String text)
	{
		if(text == null)
			return null;
		
		//find @
		return Text.grepAllTexts(EMAIL_REG_EXP, text);
	}//------------------------------------------------
}
