package nyla.solutions.web.commas;

import org.junit.Ignore;

import junit.framework.TestCase;
import nyla.solutions.commas.Command;
import nyla.solutions.commas.CommasServiceFactory;
import nyla.solutions.commas.json.JsonBridge;
import nyla.solutions.core.patterns.reflection.Mirror;

@Ignore
public class JsonAdviceTest extends TestCase
{

	public JsonAdviceTest(String name)
	{
		super(name);
	}
	@SuppressWarnings("unchecked")
	public void testJsonAdvice() throws Exception
	{
		assertTrue(Mirror.isPrimitive(String.class));
		

		Command<Object,Object> cmd = (Command<Object,Object>)CommasServiceFactory.getCommasServiceFactory().create("nyla.solutions.web.commas.UTJsonCommand.execute");
		assertNotNull(cmd);
		
		String jsonText = "{name : \"JUNIT_NAME\"}";

		String response = JsonBridge.executeCommand("nyla.solutions.web.commas.UTJsonCommand.execute", jsonText);
		
		assertNotNull(response);
	}
	
	@SuppressWarnings("unchecked")
	public void testJsonAdvice1() throws Exception
	{
		Command<Object,Object> cmd = (Command<Object,Object>)CommasServiceFactory.getCommasServiceFactory().create("nyla.solutions.web.commas.UTJsonCommand.execute");
		assertNotNull(cmd);
		
		String jsonText = "{name : \"JUNIT_NAME2\"}";

		String response = JsonBridge.executeCommand("nyla.solutions.web.commas.UTJsonCommand.execute", jsonText);
		
		assertNotNull(response);
		
		
		
	}
}
