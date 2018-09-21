package nyla.solutions.email;

import java.util.Collection;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import nyla.solutions.IntegrationTest;
import nyla.solutions.email.data.EmailMessage;

@Ignore
@Category(IntegrationTest.class)
public class ReadMailTest
{
	/**
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	@Category(IntegrationTest.class)
	@Test
	public void testRead()
	throws Exception
	{
		
		Email email = new Email();
		int count = 10;
		int startIndex = 1;
		String subjectPattern = ".*";
		
		Collection<EmailMessage> results = email.readMatches(count, startIndex, subjectPattern);
		
		Assert.assertTrue(results != null && !results.isEmpty());
		
		Assert.assertEquals(10, results.size());
		
		
		subjectPattern = ".*fail.*";
		
		results = email.readMatches(count, startIndex, subjectPattern);
		
		Assert.assertTrue(results != null && !results.isEmpty());
		
		for (EmailMessage message : results)
		{
			System.out.println("message:"+message);
		}
		
	}//------------------------------------------------

}
