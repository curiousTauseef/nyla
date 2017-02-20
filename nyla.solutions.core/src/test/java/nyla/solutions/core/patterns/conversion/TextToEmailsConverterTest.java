package nyla.solutions.core.patterns.conversion;

import java.util.Collection;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test for TextToEmailsConverter
 * @author Gregory Green
 *
 */
public class TextToEmailsConverterTest
{

	@Test
	public void testConvert()
	{
		String email =  "";
		TextToEmailsConverter converter = new TextToEmailsConverter();
		Collection<String> emails = converter.convert(email);
		Assert.assertNull(emails);
		
		email =  "nyla";
		emails = converter.convert(email);
		Assert.assertNull(emails);
		
		email =  "nyla@solu";
		emails = converter.convert(email);
		Assert.assertNull(emails);

		email =  "nyla@solu.com";
		emails = converter.convert(email);
		Assert.assertNotNull(emails);
		Assert.assertTrue(emails.contains("nyla@solu.com"));
		
		email =  "rgreen@excellency.org";
		emails = converter.convert(email);
		Assert.assertNotNull(emails);
		Assert.assertTrue(!emails.contains("nyla@solu.com"));
		Assert.assertTrue(emails.contains(email));

		email =  " rgreen@excellency.org";
		emails = converter.convert(email);
		Assert.assertTrue(emails.contains("rgreen@excellency.org"));

		email =  " rgreen@excellency.org ";
		emails = converter.convert(email);
		Assert.assertTrue(emails.contains("rgreen@excellency.org"));

		email =  " rgreen@excellency.org and nyla@solu.com ";
		emails = converter.convert(email);
		Assert.assertTrue(emails.size() == 2);
		Assert.assertTrue(emails.contains("rgreen@excellency.org"));
		Assert.assertTrue(emails.contains("nyla@solu.com"));
		
		
		email =  "Please remove rgreen@excellency.org and nyla@solu.marketing ";
		emails = converter.convert(email);
		Assert.assertEquals(2,emails.size());
		Assert.assertTrue(emails.contains("nyla@solu.marketing"));
	}

}
