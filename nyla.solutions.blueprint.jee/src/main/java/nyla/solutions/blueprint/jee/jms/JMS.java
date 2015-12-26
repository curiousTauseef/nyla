package nyla.solutions.blueprint.jee.jms;

import java.util.Map;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.CommunicationException;
import javax.naming.InitialContext;

import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;



public class JMS
{
	/**
	 * Send JMS message over a queue
	 * @param message
	 * @param properties
	 */
	public void sendMessageQueue(String message, Map<String, String> properties)
	throws CommunicationException
	{
		QueueConnection cnn = null;
		QueueSender sender = null;
		QueueSession session = null;

		try
		{
			InitialContext ctx = new InitialContext();
			Queue queue = (Queue) ctx.lookup(queueJNDI);
			QueueConnectionFactory factory = (QueueConnectionFactory) ctx
					.lookup(queueConnectionFactoryJNDI);

			cnn = factory.createQueueConnection();
			session = cnn.createQueueSession(false,
					QueueSession.AUTO_ACKNOWLEDGE);

			TextMessage msg = session.createTextMessage(message);

			for (String key : properties.keySet())
			{
				msg.setStringProperty(key, properties.get(key));
			}

			sender = session.createSender(queue);
			sender.send(msg);
		}
		catch (Exception e)
		{
			Debugger.printError(e);
			throw new CommunicationException(Debugger.stackTrace(e));
		}
		finally
		{
			// Cleanup
			// close the connection
			if (cnn != null)
			{
				try
				{
					cnn.close();
				}
				catch (Exception e)
				{
				}
			}
		}
	}// --------------------------------------------
	
	public String getQueueJNDI()
	{
		return queueJNDI;
	}
	public void setQueueJNDI(String queueJNDI)
	{
		this.queueJNDI = queueJNDI;
	}
	public String getQueueConnectionFactoryJNDI()
	{
		return queueConnectionFactoryJNDI;
	}
	public void setQueueConnectionFactoryJNDI(String queueConnectionFactoryJNDI)
	{
		this.queueConnectionFactoryJNDI = queueConnectionFactoryJNDI;
	}

	private String queueJNDI = Config.getProperty(JMS.class,"queueJNDI");
   //"queue/tutorial/example";
	private String queueConnectionFactoryJNDI =Config.getProperty(JMS.class,"queueConnectionFactoryJNDI"); 
		//"ConnectionFactory";
}
