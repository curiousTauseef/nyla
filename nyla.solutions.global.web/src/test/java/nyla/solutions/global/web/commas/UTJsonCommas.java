package nyla.solutions.global.web.commas;

import nyla.solutions.global.data.Envelope;
import nyla.solutions.global.data.Property;
import nyla.solutions.global.patterns.command.commas.annotations.CMD;
import nyla.solutions.global.patterns.command.commas.annotations.COMMAS;
import nyla.solutions.global.patterns.command.commas.json.JsonAdvice;

@COMMAS
public class UTJsonCommas
{
	@SuppressWarnings("unchecked")
	@CMD(advice= JsonAdvice.JSON_ADVICE_NAME, inputClass = Property.class, returnClass = Property.class)
	public Object execute(Object source)
	{
		Envelope<Property> env = (Envelope<Property>)source;
		
		Property property =  env.getPayload();
		
		return property;
	}
}
