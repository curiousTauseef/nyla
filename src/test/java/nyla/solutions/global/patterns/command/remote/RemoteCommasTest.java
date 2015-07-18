package nyla.solutions.global.patterns.command.remote;

import java.io.Serializable;
import java.net.URI;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

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

@Ignore
public class RemoteCommasTest
{
	@BeforeClass
	public static void init()
	throws Exception
	{
		//int port = 27001;
		
		//RMI.startRmiRegistry(port);
		
		//Remote remote = new RemoteCommasServer();
		
		//RMI rmi =new RMI("localhost",port);
			
		//rmi.rebind("commasRegistry", remote);
		
	}// --------------------------------------------------------
	@Before
	public  void setUp()
	throws Exception
	{
		
		  rmi = new RMI("localhost",27001);
		  factory = ConfigServiceFactory.getConfigServiceFactoryInstance();
	}// --------------------------------------------------------
	
	@Test
	public void testLookup()
	throws Exception
	{
		
		
		RemoteCommand<Serializable,Envelope<UserProfile>> command = rmi.lookup("demo");
		
		Assert.assertNotNull(command);
		
		String rmiUrl = "rmi://localhost:27001/demo";
		command = RMI.lookup(new URI(rmiUrl));
		
		Assert.assertNotNull(command);
	}// --------------------------------------------------------
	@Test
	public void testSingleRoute()
	{	
		
		
		PropertiesLoadBalanceRegistry registry = factory.create(LoadBalanceRegistry.class);
		
		String filePath = registry.getPropertyFilePath();
		
		Assert.assertTrue(filePath != null && filePath.length() > 0);
		
		Assert.assertEquals("./runtime/tmp/loadbalance.properties", filePath);
		
		String name = RealSingleRouteCommand.class.getName()+".findUsers";
		Command<Collection<User>,Criteria> cmd = CommasServiceFactory.getCommasServiceFactory().createCommand(name);
		
		for(int i=0; i <2; i++)
		{
			Criteria input = new Criteria(i);
			Collection<User> users = cmd.execute(input);		
			Assert.assertNotNull(users);
		
		}
			
		
	}// --------------------------------------------------------
	
	@Test
	public void testAllRoutes() throws Exception
	{
		String name = RealSingleRouteCommand.class.getName()+".findUsersEveryWhere";
		Command<Collection<User>,Criteria> cmd = CommasServiceFactory.getCommasServiceFactory().createCommand(name);
		
		
		for(int i=0; i <1; i++)
		{
			Criteria input = new Criteria(i);
			Collection<User> users = cmd.execute(input);		
			Assert.assertNotNull(users);
			
			for (User user : users)
			{
				Assert.assertNotNull(user);
			}
		}
	}// --------------------------------------------------------
	private RMI rmi;
	private ServiceFactory factory;
	

}
