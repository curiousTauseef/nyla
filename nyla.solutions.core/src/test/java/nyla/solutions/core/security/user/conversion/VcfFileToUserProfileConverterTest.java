package nyla.solutions.core.security.user.conversion;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import nyla.solutions.core.security.user.data.UserProfile;

/**
 * Test for VcfFileToUserProfileConverter
 * @author Gregory Green
 *
 */
public class VcfFileToUserProfileConverterTest
{
	/**
	 * Test the conversion from a file VCF
	 */
	@Test
	public void testConvert()
	{
		VcfFileToUserProfileConverter converter = new VcfFileToUserProfileConverter();
		
		converter.setUserProfileClass(VcfTestPerson.class);
		File file = new File("src/test/resources/security/user/vcard1.vcf");
		
		UserProfile user = converter.convert(file);
		
		Assert.assertTrue(user instanceof VcfTestPerson);
		
		Assert.assertEquals("ggreen@nyla.com", user.getEmail());
		Assert.assertEquals("Greg", user.getFirstName());
		Assert.assertEquals("Green", user.getLastName());
		
	}//------------------------------------------------
	
	public static class VcfTestPerson extends UserProfile
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = 7411811054048170140L;
		
	}

}
