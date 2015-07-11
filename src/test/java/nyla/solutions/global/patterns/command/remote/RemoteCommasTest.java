package nyla.solutions.global.patterns.command.remote;

import java.io.Serializable;
import java.net.URI;
import java.util.Collection;

import org.junit.Ignore;

import nyla.solutions.global.data.Criteria;
import nyla.solutions.global.data.Envelope;
import nyla.solutions.global.demo.commas.RealSingleRouteCommand;
import nyla.solutions.global.net.rmi.RMI;
import nyla.solutions.global.patterns.command.Command;
import nyla.solutions.global.patterns.command.commas.CommasServiceFactory;
import nyla.solutions.global.patterns.command.remote.RemoteCommand;
import nyla.solutions.global.patterns.loadbalancer.LoadBalanceRegistry;
import nyla.solutions.global.patterns.loadbalancer.PropertiesLoadBalanceRegistry;
import nyla.solutions.global.patterns.servicefactory.ConfigServiceFactory;
import nyla.solutions.global.patterns.servicefactory.ServiceFactory;
import nyla.solutions.global.security.user.data.User;
import nyla.solutions.global.security.user.data.UserProfile;
import junit.framework.TestCase;

@Ignore
public class RemoteCommasTest extends TestCase
{
	@Override
	protected void setUp() throws Exception
	{
		  rmi = new RMI("usxxgreeng3m1.corp.emc.com",27001);
		  factory = ConfigServiceFactory.getConfigServiceFactoryInstance();
	}// --------------------------------------------------------
	
	public void testLookup()
	throws Exception
	{
		
		
		
		RemoteCommand<Serializable,Envelope<UserProfile>> command = rmi.lookup("demo");
		
		assertNotNull(command);
		
		String rmiUrl = "rmi://usxxgreeng3m1.corp.emc.com:27001/demo";
		command = RMI.lookup(new URI(rmiUrl));
		
		assertNotNull(command);
	}// --------------------------------------------------------
	public void testSingleRoute()
	{	
		
		
		PropertiesLoadBalanceRegistry registry = factory.create(LoadBalanceRegistry.class);
		
		String filePath = registry.getPropertyFilePath();
		
		assertTrue(filePath != null && filePath.length() > 0);
		
		assertEquals("./runtime/tmp/loadbalance.properties", filePath);
		
		String name = RealSingleRouteCommand.class.getName()+".findUsers";
		Command<Collection<User>,Criteria> cmd = CommasServiceFactory.getCommasServiceFactory().createCommand(name);
		
		for(int i=0; i <2; i++)
		{
			Criteria input = new Criteria(i);
			Collection<User> users = cmd.execute(input);		
			assertNotNull(users);
		
		}
			
		
	}// --------------------------------------------------------
	
	public void testAllRoutes() throws Exception
	{
		String name = RealSingleRouteCommand.class.getName()+".findUsersEveryWhere";
		Command<Collection<User>,Criteria> cmd = CommasServiceFactory.getCommasServiceFactory().createCommand(name);
		
		
		for(int i=0; i <1; i++)
		{
			Criteria input = new Criteria(i);
			Collection<User> users = cmd.execute(input);		
			assertNotNull(users);
			
			for (User user : users)
			{
				assertNotNull(user);
			}
		}
	}// --------------------------------------------------------
	private RMI rmi;
	private ServiceFactory factory;
	

}
