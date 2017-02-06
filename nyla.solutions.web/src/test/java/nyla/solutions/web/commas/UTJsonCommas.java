package nyla.solutions.web.commas;

import nyla.solutions.commas.annotations.CMD;
import nyla.solutions.commas.annotations.COMMAS;
import nyla.solutions.commas.json.JsonAdvice;
import nyla.solutions.core.data.Envelope;
import nyla.solutions.core.data.Property;

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
