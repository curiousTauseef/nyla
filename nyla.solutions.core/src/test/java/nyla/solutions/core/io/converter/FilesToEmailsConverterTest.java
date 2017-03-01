package nyla.solutions.core.io.converter;

import java.io.File;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test cases for FilesToEmailsConverter
 * 
 * @author Gregory Green
 *
 */
public class FilesToEmailsConverterTest
{
	/**
	 * Test the convert method
	 */
	@Test
	public void testConvert()
	{
		FilesToEmailsConverter converter = new FilesToEmailsConverter();
		Set<String> emails = converter.convert(new File("src/test/resources/iotest/emails"));
		
		Assert.assertNotNull(emails);
		Assert.assertTrue(!emails.isEmpty());
		
		Assert.assertTrue(emails.contains("nyla@green.com"));
		
	}

}
