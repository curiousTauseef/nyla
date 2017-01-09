package nyla.solutions.net.postit;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import nyla.solutions.net.postit.data.Recipient;

public class PostItMgrIntTest
{

	@Test
	public void testFindRecipients()
	{
		try(AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(PostItApp.class))
		{
			PostItMgr mgr = ctx.getBean(PostItMgr.class);
			
			Recipient recipient = new Recipient();
			recipient.setEmail("email");
			recipient.setRecipientId(recipient.getEmail());
			
			Assert.assertNotNull(recipient.getRecipientId());
			Assert.assertEquals("email", recipient.getRecipientId());
			
			mgr.saveRecipient(recipient);
			
			
			Iterable<Recipient> recipients = mgr.findRecipients();
			
			Assert.assertNotNull(recipients);
			Assert.assertTrue(recipients.iterator().hasNext());
			
		}
		
		
	}//------------------------------------------------

	@Test
	public void testSaveRecipient()
	{
		try(AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(PostItApp.class))
		{
			PostItMgr mgr = ctx.getBean(PostItMgr.class);
			Recipient recipient = new Recipient();
			try
			{
			
				mgr.saveRecipient(recipient);
				Assert.fail();
			}
			catch(RuntimeException e)
			{}
			
			recipient.setRecipientId("id");
			
			mgr.saveRecipient(recipient);
			
			
			Iterable<Recipient> recipients = mgr.findRecipients();
			
			Assert.assertNotNull(recipients);
			Assert.assertTrue(recipients.iterator().hasNext());
			
		}
	}//------------------------------------------------
	@Test
	@Ignore
	public void testSendMail()
	{
		
		try(AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(PostItApp.class))
		{
			PostItMgr mgr = ctx.getBean(PostItMgr.class);
			Recipient recipient = new Recipient();
			
			Package pack = new Package();
			pack.setText("<b>Hello World</b>");
			try
			{
				mgr.sendIt(pack);
				Assert.fail("required to");
			}
			catch(RuntimeException e)
			{}
			
			pack.setTo("ggreen@pivotal.io");
			
			mgr.sendIt(pack);

			
			
		}
	} //------------------------------------------------

}
