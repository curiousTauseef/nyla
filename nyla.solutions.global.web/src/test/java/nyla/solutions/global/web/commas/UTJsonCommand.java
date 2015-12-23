package nyla.solutions.global.web.commas;
import nyla.solutions.global.data.Envelope;
import nyla.solutions.global.data.Property;
import nyla.solutions.global.patterns.command.commas.annotations.CMD;
import nyla.solutions.global.patterns.command.commas.annotations.COMMAS;
import nyla.solutions.global.patterns.command.commas.json.JsonAdvice;

@COMMAS
public class UTJsonCommand
{
	//@SuppressWarnings("unchecked")
	@CMD(advice= JsonAdvice.JSON_ADVICE_NAME, inputClass = Property.class, returnClass = Property.class)
	public Object execute(Envelope<Property> source)
	{
		
		return source.getPayload();
	}

}
