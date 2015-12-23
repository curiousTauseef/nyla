package nyla.solutions.global.web.commas;

import org.junit.Ignore;

import nyla.solutions.global.patterns.command.Command;
import nyla.solutions.global.patterns.command.commas.CommasServiceFactory;
import nyla.solutions.global.patterns.command.commas.json.JsonBridge;
import nyla.solutions.global.patterns.reflection.Mirror;
import junit.framework.TestCase;

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
		

		Command<Object,Object> cmd = (Command<Object,Object>)CommasServiceFactory.getCommasServiceFactory().create("nyla.solutions.global.web.commas.UTJsonCommand.execute");
		assertNotNull(cmd);
		
		String jsonText = "{name : \"JUNIT_NAME\"}";

		String response = JsonBridge.executeCommand("nyla.solutions.global.web.commas.UTJsonCommand.execute", jsonText);
		
		assertNotNull(response);
	}
	
	@SuppressWarnings("unchecked")
	public void testJsonAdvice1() throws Exception
	{
		Command<Object,Object> cmd = (Command<Object,Object>)CommasServiceFactory.getCommasServiceFactory().create("nyla.solutions.global.web.commas.UTJsonCommand.execute");
		assertNotNull(cmd);
		
		String jsonText = "{name : \"JUNIT_NAME2\"}";

		String response = JsonBridge.executeCommand("nyla.solutions.global.web.commas.UTJsonCommand.execute", jsonText);
		
		assertNotNull(response);
		
		
		
	}
}
