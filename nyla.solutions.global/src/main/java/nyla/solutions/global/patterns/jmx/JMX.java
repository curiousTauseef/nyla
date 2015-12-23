package nyla.solutions.global.patterns.jmx;
import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;



import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.patterns.Disposable;
import nyla.solutions.global.util.Debugger;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Wrapper class that interfaces with JMX
 * 
 *  Sample URL 
 *  "service:jmx:rmi:///jndi/rmi://:9999/jmxrmi";
 *  String urlText = "service:jmx:rmi:///jndi/rmi://host:port/jmxrmi";
 *  
 * @author Gregory Green
 *
 */
public class JMX implements Disposable
{
   /**
    * MemoryMX_NAME = "java.lang:type=Memory"
    */
   public static final String MemoryMX_NAME = "java.lang:type=Memory";
   
   /**
    * TheadMX_NAME = "java.lang:type=Threading"
    */
   public static final String TheadMX_NAME = "java.lang:type=Threading";
   
  
   
   /**
    * Constructor
    * @param connectionURL the JMX connection URL
    * @param userName the connection user name
    * @param password the connection password
    */
   private JMX(String connectionURL, String userName, char[] password)
   {
	init(connectionURL,userName, password);
	
   }// ----------------------------------------------
   /**
    * Connect to a remote JMX server
    * @param connectionURL (example: service:jmx:rmi:///jndi/rmi://host:9999/jmxrmi)
    * @param userName the user name
    * @param password the user's password
    */
   private void init(String connectionURL, String userName, char[] password)
   {
	if (connectionURL == null || connectionURL.length() == 0)
	   throw new RequiredException("connectionURL");
	
      try
	{
	   JMXServiceURL url = new JMXServiceURL(connectionURL);
	   
	   
	   Map<String,String[]> map = null;
	   
	   //Populate credentials
	   if(userName !=  null  || password != null)
	   {
		   map = new HashMap<String,String[]>();
	   	   
		   String[] credentials = new String[]{ userName, new String(password) };
		   
		   map.put(JMXConnector.CREDENTIALS, credentials);		
	   }
	   
	   jmxc = JMXConnectorFactory.connect(url, map);
	   
	   connection = jmxc.getMBeanServerConnection();
	} 
      catch (Exception e)
	{
	   throw new nyla.solutions.global.exception.ConnectionException("connectionURL="+connectionURL+" "+Debugger.stackTrace(e));
	}
   }// ----------------------------------------------
   
   public static List<String> getLocalRuntimeArguments()
   {
	   return ManagementFactory.getRuntimeMXBean().getInputArguments();
   }// -----------------------------------------------
   /**
    * Create a connection to JMX
    * @param url the JMX remote URL
    * @param user the user name 
    * @param password the password
    * @return a new JMX instance
    */
   public static JMX connect(String url, String user, char[] password)
   {
	return new JMX(url,user,password);
   }// ----------------------------------------------
   /**
    * @return domains from connection
    * @throws IOException
    * @see javax.management.MBeanServerConnection#getDomains()
    */
   public String[] getDomains() throws IOException
   {
	return connection.getDomains();
   }// ----------------------------------------------
   /**
    * 
    * @param args (URL) (user) (password)
    * @throws Exception
    */
public static void main(String[] args) throws Exception 
   {	
	String url = null;
	String userName = null;
	char[] password = null;
	if(args.length < 1)
	{
		System.out.println("Usage java "+JMX.class.getCanonicalName()+" url (userName password)?");
		System.exit(-1);
	}
	
	url = args[0];
	
	if(args.length > 1)
	{
	   if(args.length < 3)
	   {
		System.out.println("Usage java "+JMX.class.getCanonicalName()+" url(ex:service:jmx:rmi:///jndi/rmi://host:port/jmxrmi) userName password");
		System.exit(-1);
	   }
	   
	   userName = args[1];	   
	   
	   password = args[2].toCharArray();
	}
	
	
	JMX jmx = JMX.connect(url, userName, password);
	
       // Get domains from MBeanServer
       //
       Debugger.println("\nDomains:");
       String domains[] = jmx.getDomains();
       Arrays.sort(domains);
       for (int i = 0; i < domains.length; i++) 
       {
          Debugger.println("\tDomain = " + domains[i]);
       }
       
       //waitForEnterPressed();

       // Get MBeanServer's default domain
       //
       Debugger.println("\nMBeanServer default domain = " + jmx.getDefaultDomain());

       // Get MBean count
       //
       Debugger.println("\nMBean count = " + jmx.getMBeanCount());

       // Query MBean names
       //
       Debugger.println("\nQuery MBeanServer MBeans:");
       Set<ObjectName> names =jmx.queryNames(null, null);
          
       
       ObjectName objectName = null;
       for (Iterator<ObjectName> itera = names.iterator();itera.hasNext();) 
       {
          objectName = itera.next();

          Debugger.println("getCanonicalName="+objectName.getCanonicalName());
          
       }
       
       Debugger.println("=========Memory Usage=========");
       
       MemoryMXBean memory = jmx.getMemory();
       Debugger.println("memory.getObjectPendingFinalizationCount="+memory.getObjectPendingFinalizationCount());
       MemoryUsage memoryUsage = memory.getHeapMemoryUsage();
       Debugger.println("memory.getCommitted="+memoryUsage.getCommitted());
       Debugger.println("memory.getUsed="+memoryUsage.getUsed());
       
       
       Debugger.println("=========Thread Usage=========");
       
       ThreadMXBean thread = jmx.getThread();
       Debugger.println("thread.getPeakThreadCount="+thread.getPeakThreadCount());

       Debugger.println("thread.getDaemonThreadCount="+thread.getDaemonThreadCount());
       Debugger.println("thread.getThreadCount="+thread.getThreadCount());
       Debugger.println("thread.getTotalStartedThreadCount="+thread.getTotalStartedThreadCount());
       
       
       jmx.registerMemoryNotifications(new ClientListener(), null);
       
       System.out.println("Enter key to exit");
       
       System.in.read();

    }// ----------------------------------------------
   public static class ClientListener implements NotificationListener 
   {
      public void handleNotification(Notification notification,
                                     Object handback) {
          Debugger.println("\nReceived notification:");
          Debugger.println("\tClassName: " + notification.getClass().getName());
          Debugger.println("\tSource: " + notification.getSource());
          Debugger.println("\tType: " + notification.getType());
          Debugger.println("\tMessage: " + notification.getMessage());
          if (notification instanceof AttributeChangeNotification) {
              AttributeChangeNotification acn =
                  (AttributeChangeNotification) notification;
              Debugger.println("\tAttributeName: " + acn.getAttributeName());
              Debugger.println("\tAttributeType: " + acn.getAttributeType());
              Debugger.println("\tNewValue: " + acn.getNewValue());
              Debugger.println("\tOldValue: " + acn.getOldValue());
          }
      }
  }
   /**
    * @return
    * @throws IOException
    * @see javax.management.MBeanServerConnection#getDefaultDomain()
    */
   public String getDefaultDomain() throws IOException
   {
	return connection.getDefaultDomain();
   }
   /**
    * @return
    * @throws IOException
    * @see javax.management.MBeanServerConnection#getMBeanCount()
    */
   public Integer getMBeanCount() throws IOException
   {
	return connection.getMBeanCount();
   }
   /**
    * @param arg0
    * @param arg1
    * @return
    * @throws IOException
    * @see javax.management.MBeanServerConnection#queryNames(javax.management.ObjectName, javax.management.QueryExp)
    */
   public Set<ObjectName> queryNames(ObjectName arg0, QueryExp arg1) throws IOException
   {
	return connection.queryNames(arg0, arg1);
   }
   /**
    * 
    * @return MemoryMXBean referred by java.lang:type=Memory
    */
   public MemoryMXBean getMemory()   
   {
	try
	{
	   return (MemoryMXBean)ManagementFactory.newPlatformMXBeanProxy(connection, MemoryMX_NAME, MemoryMXBean.class);
	   
	} 
	catch (Exception e)
	{
	   throw new SystemException(Debugger.stackTrace(e));
	}
	
   }// ----------------------------------------------   
   public ThreadMXBean getThread()   
   {
	try
	{
	   return (ThreadMXBean)ManagementFactory.newPlatformMXBeanProxy(connection, TheadMX_NAME, ThreadMXBean.class);
	   
	} 
	catch (Exception e)
	{
	   throw new SystemException(Debugger.stackTrace(e));
	}
	
   }// ----------------------------------------------
   
   /**
    * Implements the disposable interface to close the JMX connection
    */
   public void dispose()
   {
	try 
	{ 
	   connection = null;
	   
	   jmxc.close(); 
	   jmxc = null;
	} 
	catch(Exception e)
	{}	
   }// ----------------------------------------------
   /**
    * Print warning is JMC connection were not closed
    */
   protected void finalize() throws Throwable
   {
      super.finalize();
      
      if(this.connection != null || this.jmxc != null)
         Debugger.printWarn(this,"MEMORY LEAK, JMX connection has not been closed!");
   }// ----------------------------------------------
  
   
   /**
    * 
    * Adds a listener to a registered MBean. Notifications emitted by the MBean will be forwarded to the listener.
    * @param name - The name of the MBean on which the listener should be added.
    * @param listener - The listener object which will handle the notifications emitted by the registered MBean.
    * @param filter - The filter object. If filter is null, no filtering will be performed before handling notifications.
    * @param handback - The context to be sent to the listener when a notification is emitted.
    * @throws InstanceNotFoundException
    * @throws IOException
    */
   public void addNotificationListener(ObjectName objectName,
	   NotificationListener notificationListener, NotificationFilter notificationFilter, Object handback)
	   throws InstanceNotFoundException, IOException
   {
	connection.addNotificationListener(objectName, notificationListener, notificationFilter, handback);
   }// ----------------------------------------------
   /**
    * Allows a listener to be registered within the MemoryMXBean as a notification listener
    * usage threshold exceeded notification - for notifying that the memory usage of a memory pool is increased 
    * and has reached or exceeded its usage threshold value.
    * 
    * collection usage threshold exceeded notification - for notifying that the memory usage 
    * of a memory pool is greater than or equal to its collection usage threshold 
    * after the Java virtual machine has expended effort in recycling unused objects in that memory pool.
    * 
    * The notification emitted is a Notification instance whose user data is set to a CompositeData that 
    * represents a MemoryNotificationInfo object containing information about the memory pool when the 
    * notification was constructed. The CompositeData contains the attributes as described in MemoryNotificationInfo.
    * 
    * @param notificationListener listener to be alerted
    * @param handback object to be passed back to notification listener when notification occurs
    */
   public void registerMemoryNotifications(NotificationListener notificationListener, Object handback)
   {
	NotificationEmitter emitter = (NotificationEmitter) this.getMemory();
	emitter.addNotificationListener(notificationListener, null, handback);
	
   }// ----------------------------------------------
   private JMXConnector jmxc = null;
   private MBeanServerConnection  connection = null;

   /**
    * Inner class that will handle the notifications.
    */
   
   // ----------------------------------------------
}
