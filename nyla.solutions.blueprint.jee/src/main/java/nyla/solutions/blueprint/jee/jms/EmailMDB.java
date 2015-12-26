package nyla.solutions.blueprint.jee.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.net.email.Email;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;


/**
 * @author Gregory Green
 * 
 */
/*@MessageDriven(activationConfig =
{
	@ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
	@ActivationConfigProperty(propertyName="destination", propertyValue="/queue/email")
})
@Depends ("jboss.mq.destination:service=Queue,name=emailQueue")*/
public class EmailMDB implements MessageListener
{
	public void onMessage(Message message)
	{
		System.out.println("Testing email");
		
		System.out.println("system properties keys="+System.getProperties().keySet());
		
		Debugger.println(this,"got message "+message);
		
		
		try
		{
			//get headers
		
			String to = message.getStringProperty(toProp);
			String from = message.getStringProperty(fromProp);
			String subject = message.getStringProperty(subjectProp);
			
			Email email = new Email();
			
			email.sendMail(to, from, subject, getEmailBody(message));
		}
		catch (Exception e)
		{
			Debugger.println(e);
			throw new SystemException(e.getMessage(),e);
		}
		
	}// --------------------------------------------
	private String getEmailBody(Message message)
	throws JMSException
	{
		TextMessage textMessage = (TextMessage)message;
		
		return textMessage.getText();
	}// --------------------------------------------
	private String toProp = Config.getProperty(EmailMDB.class,"toProp","to");
	private String fromProp = Config.getProperty(EmailMDB.class,"fromProp","from");
	private String subjectProp = Config.getProperty(EmailMDB.class,"subjectProp","subject");
}
