package nyla.solutions.core.io.csv;
import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import nyla.solutions.core.io.IO;
import nyla.solutions.core.io.converter.ConversionFileAuthor;
import nyla.solutions.core.security.user.data.UserProfile;

public class ConvertBeanFileAuthorTest
{

	@Test
	public void testAppendFile()
	throws IOException
	{
		BeanPropertiesToCsvConverter<UserProfile> converter = new BeanPropertiesToCsvConverter<>(UserProfile.class);
		File file = new File("runtime/tmp/csv/UserProfiles.csv");
		IO.delete(file);
		
		ConversionFileAuthor<UserProfile> author = new ConversionFileAuthor<>(file, new BeanPropertiesToCsvHeaderConverter<>(),converter);
		
		UserProfile  user1 = new UserProfile("ggreen@pivotal.io","ggreenId","Greg","Green");
		UserProfile  user2 = new UserProfile("nyla@futurepivots.com","nyla","Nyla","Green");
		author.appendFile(user1);
		author.appendFile(user2);
		
		Assert.assertTrue(file.exists());
		
		String context = IO.readFile(file);
		String header = converter.toHeaderRow(UserProfile.class);
		System.out.println("header:"+header);
		Assert.assertTrue(header+" ======> "+context,context.contains(header));
		Assert.assertTrue(context,context.contains(converter.convert(user1)));
		
		String row2 = converter.convert(user2);
		
		Assert.assertTrue(row2+" in "+context,context.contains(row2.substring(0, row2.length()-2)));
		
		
	}

}
