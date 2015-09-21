package nyla.solutions.global.operations.logging;


import org.junit.Before;
import org.junit.Test;

public class Log4J2Test
{

	public Log4J2Test()
	{
	}

	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void testDebugObject()
	{
		Log4J2 log = new Log4J2();
		
		log.debug("hello world");
	}

}
