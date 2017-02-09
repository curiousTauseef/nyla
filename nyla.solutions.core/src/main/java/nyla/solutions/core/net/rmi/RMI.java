package nyla.solutions.core.net.rmi;

//import java.rmi.Naming;
import java.net.URI;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import nyla.solutions.core.data.Identifier;
import nyla.solutions.core.exception.ConfigException;
import nyla.solutions.core.exception.ConnectionException;
import nyla.solutions.core.util.Config;
import nyla.solutions.core.util.Debugger;
import nyla.solutions.core.util.Text;


/**
 * <pre>
 * Starting the Server

Before starting the compute engine, you need to start the RMI registry. 
The RMI registry is a simple server-side bootstrap naming facility that enables remote clients 
to obtain a reference to an initial remote object. It can be started with the rmiregistry command. 
Before you execute rmiregistry, you must make sure that the shell or window in which you will run 
rmiregistry either has no CLASSPATH environment variable set or has a CLASSPATH environment variable that does not include the path to any classes that you want downloaded to clients of your remote objects.

To start the registry on the server, execute the rmiregistry command. This command produces no output and is typically run in the background. For this example, the registry is started on the host mycomputer.

Microsoft Windows (use javaw if start is not available):

start rmiregistry

By default, the registry runs on port 1099. 
To start the registry on a different port, specify the port number on the command line. 
Do not forget to unset your CLASSPATH environment variable.

Microsoft Windows:

start rmiregistry 2001


 rmi = new RMI("usxxgreeng3m1.corp.emc.com",27001);
 
 Remote command = rmi.lookup("commas");
</pre>
 * 
 * @author Gregory Green
 *
 */
public class RMI
{
	/**
	 * 
	 * @param host the registry host
	 * @param port the registry port
	 * @throws RemoteException
	 */
	public RMI(String host, int port)
	throws RemoteException
	{
		this.host = host;
		this.port = port;
		
		this.registry = getRegistry(host, port);
	}// --------------------------------------------------------
   /**
    * 
    * @param name the rmi Url rmi://localhost:port/serviceName
    * @return the RemoteObject
    */
   @SuppressWarnings("unchecked")
   public <T> T lookup(String name)   
   {
	try
	{
		//String name = "Compute";
		//mycomputer.example.com 45
		//Registry registry = LocateRegistry.getRegistry("usxxgreeng3m1.corp.emc.com",27001);
        return (T)registry.lookup(name);

	   //return (T)Naming.lookup(rmiUrl);
	}
	catch(Exception e)
	{
		String [] list = null;
		try
		{
			list = registry.list();
		}
		catch(Exception exp)
		{
			Debugger.printWarn(this,exp.getMessage());
		}
	   throw new ConnectionException("Cannot find:"+name+" in list "+Debugger.toString(list)+" rmi//:"+host+":"+port+"/"+name+" ERROR:"+e.getMessage(),e);
	}	
   }// ---------------------------------------------- 
   
   @SuppressWarnings("unchecked")
 public static <T> T lookup(URI rmiUrl)   
   {
	try
	{
	   return (T)Naming.lookup(rmiUrl.toString());
	}
	catch(Exception e)
	{
	   throw new ConnectionException(rmiUrl+" ERROR:"+Debugger.stackTrace(e));
	}	
   }// ---------------------------------------------- 
   /**
    * 
    * @param rmiUrl rmi://localhost:port/serviceName
    * @param remote the remote object to bind
    */
   public void rebind(String name,Remote remote)
   {
	try
	{
	   /*createRegistry(port);
	   
	   Naming.rebind(rmiUrl, remote);
	   */
		
		Remote stub = UnicastRemoteObject.exportObject(remote, 0);
		
		this.registry.rebind(name, stub);
		
		Debugger.println(RMI.class,"Binded object:"+remote+" host:"+host+" port:"+port);
		
	} 
	catch (Exception e)
	{
	   throw new ConfigException(Debugger.stackTrace(e));
	}	
   }// ----------------------------------------------
   public String[] list ()
   {
	  try
		{
			return this.registry.list();
		}
	
		catch (Exception e)
		{
			return null;
		}
   }
   /**
    * 
    * @param rmiUrl rmi://localhost:port/serviceName
    * @param remote the object to bind
    */
   /*public static void rebind(String rmiUrl, Remote remote)
   {
	try
	{
	   
	   Naming.rebind(rmiUrl, remote);   
	   
	   Debugger.println(RMI.class,"Binded "+rmiUrl+" to class "+remote.getClass().getName());
	   
	} 
	catch (Exception e)
	{
	   throw new SetupException("RMI url="+rmiUrl+" exception:"+Debugger.stackTrace(e));
	}	
   }*/
   // ----------------------------------------------
   /**
    * Bind all object.
    * Note that the rmiUrl in located in the config.properties
    * 
    * ${class}.rmiUrl
    * 
    * Example: solutions.test.RemoteObjectText.bind.rmi.url=rmi://localhost:port/serviceName
    * 
    * Note that is the remote object is an instance of Identifier, 
    * the object will be binded based on its &quot;id&quot; property if the value is provided
    * 
    * @param remotes the object to rebind
    */
   public void rebind(Remote[] remotes)
   {
	String rmiUrl = null;
	//loop thru remote objects
	for (int i = 0; i < remotes.length; i++) 
	{
	   
	       //use is if instance of Identifier
	      if(remotes[i] instanceof Identifier 
	         && !Text.isNull(((Identifier)remotes[i]).getId()))
	      {
	         rmiUrl = ((Identifier)remotes[i]).getId();
	      }
	      else
	      {
		       //get rmiUrl
			rmiUrl = Config.getProperty(remotes[i].getClass(), "bind.rmi.url");
	         
	      }
		
		
		//rebind
		rebind(rmiUrl,remotes[i]);
	   
	}
   }// ----------------------------------------------
   /**
    * Create a new local registry on a local port
    * @param port
    * @return
    * @throws RemoteException
    */
   /*public static Registry createRegistry(int port)
   throws RemoteException
   {
	try
	{
	   return LocateRegistry.createRegistry(port);
	}
	catch(ExportException exception)
	{
	   return getRegistry("localhost",port);
	}
   }*/
   // ----------------------------------------------
   public static Registry getRegistry()
   throws RemoteException
	{
			return LocateRegistry.getRegistry(Config.getProperty(RMI.class,"host"), 
												Config.getPropertyInteger(RMI.class,"host").intValue());
	}// ----------------------------------------------
   
   public static Registry getRegistry(String host, int port)
   throws RemoteException
   {
	return LocateRegistry.getRegistry(host, port);
   }// ----------------------------------------------
   public static void startRmiRegistry(int port)
   throws RemoteException
   {
	   LocateRegistry.createRegistry(port);
   }// --------------------------------------------------------
   public static void main(String[] args)
	{
	   
		try
		{
			RMI.startRmiRegistry(Integer.parseInt(args[0]));
			System.in.read();
		}
		catch (Exception e)
		{
		
			e.printStackTrace();
		}
	}
   private final String host;
   private final int port;
   private final Registry registry; 
   
}
