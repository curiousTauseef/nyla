package nyla.solutions.core.security.user.conversion;

import org.junit.Assert;
import org.junit.Test;

import nyla.solutions.core.security.user.data.UserProfile;

/**
 * Test VcfStringToUserProfileConverter
 * @author Gregory Green
 *
 */
public class VcfStringToUserProfileConverterTest
{
	/**
	 * Testing the conversion method
	 */
	@Test
	public void testConvert()
	{
		VcfStringToUserProfileConverter converter = new VcfStringToUserProfileConverter();
		
		
		Assert.assertNull(converter.convert(null));
		Assert.assertNull(converter.convert(""));
		Assert.assertNull(converter.convert(" "));
		Assert.assertNull(converter.convert("\t   "));
		
		String text = "begin:vcard\n"+
				"version:3.0\n"+
				"prodid:Microsoft-MacOutlook/f.15.1.160411\n"+
				"UID:86584FBF-3DA4-4ECD-AA68-0D4155EA1210\n"+
				"fn;charset=utf-8:1st Timothy\n"+
				"n;charset=utf-8:;;;;\n"+
				"org;charset=utf-8:1st Timothy;\n"+
				"adr;charset=utf-8;type=work;type=pref:;;215 Chancellor Ave;Newark;NJ;;\n"+
				"label;charset=utf-8;type=work;type=pref:215 Chancellor Ave Newark, NJ\n"+
				"tel;charset=utf-8;type=work:9739269375\n"+
				"email;charset =utf-8;type=internet;type=pref;type=other:Tim@nyla.com\n"+
				"end:vcard\n";
		
		
		UserProfile user = converter.convert(text);
		
		Assert.assertNotNull(user);
		
		Assert.assertEquals("1st Timothy", user.getFirstName());
		Assert.assertEquals("Tim@nyla.com", user.getEmail());
	}

}
