package nyla.solutions.blueprint.jee.blueprint.jms;


import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

import junit.framework.TestCase;
import nyla.solutions.global.net.email.Email;
import nyla.solutions.global.util.Debugger;



public class EmailMDBTest extends TestCase
{

	public void testOnMessage()
	throws Exception
	{
		Email email = new Email();
		email.sendMail(to, from, subject, "PRE TEST");

		Debugger.println(this,"Hello word");
		
		QueueConnection cnn = null;
	      QueueSender sender = null;
	      QueueSession session = null;
	      InitialContext ctx = new InitialContext();
	      Queue queue = (Queue) ctx.lookup("queue/tutorial/example");
	      QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
	      try {
	         cnn = factory.createQueueConnection();
	         session = cnn.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
	  
	         TextMessage msg = session.createTextMessage("Hello World");
	   
	         msg.setStringProperty("to", to);
	         msg.setStringProperty("from", from);
	         msg.setStringProperty("subject", subject);
	         sender = session.createSender(queue);
	         sender.send(msg);
	         System.out.println("Message sent successfully to remote queue.");
	      } finally {
	         // Cleanup
	         // close the connection
	         if (cnn != null)
	         {
	            cnn.close();
	         }
	      }
	}// --------------------------------------------
	private String to = "green.gregory@gmail.com";
	private String from = "myminority@s292399007.onlinehome.us";
	private String subject = "Testing";
}
