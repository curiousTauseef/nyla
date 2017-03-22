package nyla.solutions.core.patterns.conversion.csv;


import org.junit.Assert;
import org.junit.Test;

import nyla.solutions.core.io.csv.BeanPropertiesToCsvConverter;
import nyla.solutions.core.security.user.data.UserProfile;
import nyla.solutions.core.util.Text;

public class BeanPropertiesToCsvConverterTest
{

	@Test
	public void testConvert()
	{
		UserProfile user = new UserProfile();
		
		user.setFirstName("Gregory");
		user.setLastName("Green");
		
		BeanPropertiesToCsvConverter<UserProfile> converter = new BeanPropertiesToCsvConverter<UserProfile>(UserProfile.class);
		
		String csv = converter.convert(user);
		System.out.println("header:"+converter.getHeaderRow());
		System.out.println("csv:"+csv);
		
		Assert.assertTrue(csv.contains(user.getFirstName()));
		Assert.assertTrue(csv.contains(user.getLastName()));
	}

}
