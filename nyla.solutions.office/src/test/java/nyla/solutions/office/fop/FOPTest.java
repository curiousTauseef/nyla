package nyla.solutions.office.fop;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import nyla.solutions.core.io.IO;

public class FOPTest
{

	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void test()
	throws Exception
	{
		String fo = IO.readClassPath("pdf/example.fop");
		
		File file = new File("src/test/resources/pdf/test.pdf");
		file.delete();
		
		FOP.writePDF(fo, file);
		Assert.assertTrue(file.exists());
		
	}

}
