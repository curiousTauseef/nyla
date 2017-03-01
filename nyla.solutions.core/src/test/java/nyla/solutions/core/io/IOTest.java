package nyla.solutions.core.io;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;

public class IOTest extends TestCase
{

	public IOTest(String name)
	{
		super(name);
		//123
	}

	public void testOverview()
	throws IOException
	{
	  //Use mkdir to create entire directory paths 
	   File inputDirectory = new File("./runtime/tmp/input");
	   IO.mkdir(inputDirectory);
	   assertTrue(inputDirectory.exists());
	   
	   //Write text or binary files
	   String fileName = inputDirectory.getAbsolutePath()+"/test.txt";
	   IO.writeFile(fileName, "Hello"+IO.newline()+"world",IO.CHARSET);
	   IO.writeFile(fileName, "Hello"+IO.newline()+"world");
	   
	   //Read text or binary files 
	   String output = IO.readFile(fileName);
	   assertEquals("Hello"+IO.newline()+"world",output);
	   
	   //Copy entire Directories and files nested files
	   File outputDirectory = new File("./runtime/tmp/output");
	   IO.copyDirectory(inputDirectory, outputDirectory);
	   assertTrue(outputDirectory.length() >= inputDirectory.length());
	   
	    //Use Wildcard pattern to list files
	   assertTrue(IO.list(outputDirectory, "*.txt").length > 0);
	   
	   //Delete file or directory (all files are deleted from the given directory)
	   IO.delete(inputDirectory);
	   assertTrue(!inputDirectory.exists()); 
	   
	}
	@Test
	public void testlistFiles() throws Exception
	{
		File[] files = IO.listFiles(new File("src/test/resources/iotest"), "*");
		
		Assert.assertNotNull(files);
		Assert.assertEquals(5,files.length);
		files = IO.listFiles(new File("src/test/resources/iotest"), "*.xml");
		Assert.assertTrue(files.length == 2);
	}
	//private String directoryPath = "./runtime/tmp";
}
