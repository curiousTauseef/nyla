package nyla.solutions.global.patterns.command.remote.partitioning;

import java.rmi.RemoteException;
import java.util.Collection;

import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.net.rmi.RMI;
import nyla.solutions.global.patterns.command.commas.CommandFacts;
import nyla.solutions.global.patterns.loadbalancer.LoadBalanceRegistry;
import nyla.solutions.global.patterns.servicefactory.ServiceFactory;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;

/**
 * Configuration properties
 * 
 * 
	solutions.global.patterns.command.remote.partitioning.RemoteCommasRegistry.host=localhost
	solutions.global.patterns.command.remote.partitioning.RemoteCommasRegistry.port=27001
	solutions.global.patterns.command.remote.partitioning.RemoteCommasRegistry.name=commasRegistry

 * @author Gregory Green
 *
 */
public class PartitionCommasRemoteRegistry implements CommasRemoteRegistry<String,String>
{
	public PartitionCommasRemoteRegistry()
	{
		ServiceFactory factory = ServiceFactory.getInstance(PartitionCommasRemoteRegistry.class);
		
		this.loadBalanceRegistry = factory.create(LoadBalanceRegistry.class);
	}// -------------------------------------------------------
	public String whereIs(String key, CommandFacts commandFacts)
	throws RemoteException
	{
		
		//Build property based on region name
		String lookupKey = constructLookupKey(key, commandFacts);
		
		return this.loadBalanceRegistry.lookup(lookupKey);
			
	}// --------------------------------------------------------

	/**
	 * List registered locations
	 * @see nyla.solutions.global.patterns.command.remote.partitioning.CommasRemoteRegistry#listRegisteredLocations()
	 */
	@Override
	public Collection<String> listRegisteredLocations()
	throws RemoteException
	{
		return this.loadBalanceRegistry.listRegistered();
	}// --------------------------------------------------------
	@Override
	public void unregisterLocation(String location) throws RemoteException
	{
		//remove all keys
		this.loadBalanceRegistry.unregister(location);
	}// --------------------------------------------------------
	
	/**
	 * 
	 * @param type
	 * @param key
	 * @param remote
	 */
	public void registerKey(String key, CommandFacts commandFacts, String location )
	throws RemoteException
	{
		this.loadBalanceRegistry.register(key, location);
	}// --------------------------------------------------------
	/**
	 * Regsiter a location
	 * @see nyla.solutions.global.patterns.command.remote.partitioning.CommasRemoteRegistry#registerLocation(java.lang.Object)
	 */
	public void registerLocation(String location)
	throws RemoteException
	{
		loadBalanceRegistry.register(location);
	}// --------------------------------------------------------

	public static CommasRemoteRegistry<String,String> getRegistry()
	throws RemoteException
	{
		
		 
		RMI rmi = new RMI(host,port);
		
		return rmi.lookup(name);
	}// --------------------------------------------------------

	
	public static void main(String[] args)
	{
		try
		{
			 
			RMI rmi = new RMI(host,port);
			PartitionCommasRemoteRegistry registry = new PartitionCommasRemoteRegistry();
			
			rmi.rebind(name, registry);
		
		}
		catch (Exception e)
		{
			Debugger.printError(e);
		}
	}// --------------------------------------------------------
	
	private String constructLookupKey(String key, CommandFacts commandFacts)
	{
		try
		{
			return new StringBuilder(commandFacts.getTargetName()).append(".").append(key).toString();
		}
		catch(NullPointerException e)
		{
			throw new RequiredException("key:"+key+" commandFacts(region):"+commandFacts);
		}
	}// --------------------------------------------------------

	private static final String host = Config.getProperty(PartitionCommasRemoteRegistry.class,"host");
	private static final int port = Config.getPropertyInteger(PartitionCommasRemoteRegistry.class,"port").intValue();
	private static final String name = Config.getProperty(PartitionCommasRemoteRegistry.class,"name");
	private final LoadBalanceRegistry<String, String> loadBalanceRegistry;
	
}
