package nyla.solutions.global.web.commas;

import java.util.Collection;

import org.junit.Ignore;

import nyla.solutions.global.patterns.command.commas.CommasInfo;
import nyla.solutions.global.web.commas.CommandUIDecorator;
import junit.framework.TestCase;

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
