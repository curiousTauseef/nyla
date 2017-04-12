package nyla.solutions.core.io.csv;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import nyla.solutions.core.io.IO;


public class CsvWriterTest
{

	@Test
	public void testAppendRowStringArray()
	throws IOException
	{
		File file = new File("target/runtime/CsvWriterTest.csv");
		CsvWriter writer = new CsvWriter(file);
		
		int cnt  = 0;
		String ts = String.valueOf(System.currentTimeMillis());
		writer.appendRow(ts,String.valueOf(cnt));
		
		Assert.assertTrue(file.exists());
		Assert.assertTrue(IO.readFile(file).contains(ts));
		
	}

}
