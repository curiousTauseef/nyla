package nyla.solutions.web.commas;

import java.util.Collection;

import org.junit.Ignore;

import junit.framework.TestCase;
import nyla.solutions.commas.CommasInfo;
import nyla.solutions.web.commas.CommandUIDecorator;

@Ignore
public class CommandUIDecoratorTest extends TestCase
{

	public CommandUIDecoratorTest(String name)
	{
		super(name);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
	}

	public void testInit()
	{
		
		CommandUIDecorator ui = new CommandUIDecorator();
		Collection<CommasInfo> infos = ui.getCommasInfos();
		
		assertNotNull(infos);
		
	}// --------------------------------------------------------
}
