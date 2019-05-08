package nyla.solutions.core.security.user.conversion;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

import nyla.solutions.core.security.user.data.UserProfile;

/**
 * Test cased on for TextEmailsToUserProfilesConverter
 * @author Gregory Green
 *
 */
public class TextEmailsToUserProfilesConverterTest
{

	@Test
	public void testConvert()
	{
		TextEmailsToUserProfilesConverter converter = new TextEmailsToUserProfilesConverter();
		
		
		Assert.assertNull(converter.convert(null));
		Assert.assertNull(converter.convert(""));
		UserProfile up = new UserProfile("g@green.com", null, null, null);
		
		Assert.assertEquals(converter.convert("g@green.com"),Collections.singleton(up));
		
		Assert.assertEquals(converter.convert("g@green.com").size(),1);
		
		Assert.assertEquals(converter.convert("g@green.com;rgreen@my.com").size(),2);
		
		Assert.assertEquals(converter.convert("g@green.com").iterator().next().getEmail(),"g@green.com");
	}

}
