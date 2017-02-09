package nyla.solutions.email;

import java.util.Collection;

import javax.mail.Message;

import org.junit.Assert;
import org.junit.Test;

import nyla.solutions.email.data.EmailMessage;

public class ReadMailTest
{
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRead()
	throws Exception
	{
		
		Email email = new Email();
		int count = 10;
		int startIndex = 1;
		String subjectPattern = ".*";
		
		Collection<EmailMessage> results = email.readMail(count, startIndex, subjectPattern);
		
		Assert.assertTrue(results != null && !results.isEmpty());
		
		Assert.assertEquals(10, results.size());
		
		
		subjectPattern = ".*fail.*";
		
		results = email.readMail(count, startIndex, subjectPattern);
		
		Assert.assertTrue(results != null && !results.isEmpty());
		
		for (EmailMessage message : results)
		{
			System.out.println("message:"+message);
		}
		
	}//------------------------------------------------

}
