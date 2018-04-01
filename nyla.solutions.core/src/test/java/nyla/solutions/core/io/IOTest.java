package nyla.solutions.core.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;


import nyla.solutions.core.io.csv.CsvReader;
import nyla.solutions.core.io.csv.CsvWriter;

public class IOTest
{
	
	@Test
	public void testFind() throws Exception
	{
		List<String> filePaths = null;
		String pattern  =null;
		
		Assert.assertNull(IO.find(filePaths, pattern));
		
		List<File> results = null;
		
		File dir = Paths.get("target/runtime/io").toFile();
		System.out.println(dir.mkdirs());
		
		File file1 = Paths.get("target/runtime/io/1.txt").toFile();
		IO.writeFile(file1, "Test A\n  TestB");
		
		File file2 = Paths.get("target/runtime/io/2.txt").toFile();
		IO.writeFile(file2, "Test X\n  TestZ");
		
		pattern = "1.*";
		filePaths = new ArrayList<String>();
		filePaths.add(file1.getAbsolutePath());
		filePaths.add(file2.getAbsolutePath());
		results = IO.find(filePaths, pattern);
		Assert.assertTrue(results != null && !results.isEmpty());
		
		
		//test nested directories
		
		File dir1 = Paths.get("target/runtime/io/d1").toFile();
		System.out.println(dir1.mkdirs());
		
		IO.writeFile(dir1.getAbsolutePath()+"/d1f1.nested", "Test A\n  TestB");
		
		File dir2 = Paths.get("target/runtime/io/d2").toFile();
		System.out.println(dir2.mkdirs());
		
		IO.writeFile(dir1.getAbsolutePath()+"/d2f2.nested", "Test X\n  TestZ");
		
		filePaths.add("target/runtime/io");
		results = IO.find(filePaths, "*.nested");
		
		Assert.assertTrue(results != null && !results.isEmpty());
		
	}//------------------------------------------------
	@Test
	public void testGrep() throws Exception
	{

		List<File> filePaths = null;
		String pattern  =null;
		
		Assert.assertNull(IO.grep(pattern,filePaths));
		
		Map<File,Collection<String>> results = null;
		
		File dir = Paths.get("target/runtime/io").toFile();
		System.out.println(dir.mkdirs());
		
		File file1 = Paths.get("target/runtime/io/1.txt").toFile();
		IO.writeFile(file1, "Test A\n  TestB");
		
		File file2 = Paths.get("target/runtime/io/2.txt").toFile();
		IO.writeFile(file2, "Test X\n  TestZ");
		
		pattern = "TestZ";
		filePaths = new ArrayList<File>();
		filePaths.add(file1);
		filePaths.add(file2);
		results = IO.grep(pattern, filePaths );
		Assert.assertTrue(results != null && !results.isEmpty());
		
	}//------------------------------------------------
	@Test
	public void testMergeFiles()
	throws IOException
	{
		if(!Paths.get("target/runtime/").toFile().mkdirs())
			System.out.println("Existing directoy deleted");
		
		File csv1 = new File("target/runtime/csv1.csv");
		System.out.println(csv1.delete());
		File csv2 = new File("target/runtime/csv2.csv");
		System.out.println(csv2.delete());
		
		CsvWriter csvWriter1 = new CsvWriter(csv1);

		csvWriter1.appendRow("1","1-1","1-2");
		
		CsvWriter csvWriter2 = new CsvWriter(csv2);

		csvWriter2.appendRow("2","2-1","2-2","\"ny\"la");


		File[] filesToMerge = {csv1, csv2};
		File output = Paths.get("target/runtime/merged.csv").toFile();
		System.out.println(output.delete());
		
		Assert.assertFalse(output.exists());
		
		IO.mergeFiles(output, filesToMerge);
		
		Assert.assertTrue(output.exists());
		
		CsvReader reader = new CsvReader(output);
		
		Assert.assertEquals(2, reader.size());
		
		
		Assert.assertEquals(reader.row(0).get(0), "1");
		Assert.assertEquals(reader.row(0).get(1), "1-1");
		Assert.assertEquals(reader.row(0).get(2), "1-2");
		Assert.assertEquals(reader.row(1).get(0), "2");
		Assert.assertEquals(reader.row(1).get(1), "2-1");
		Assert.assertEquals(reader.row(1).get(2), "2-2");
		
		String result = reader.row(1).get(3);
		System.out.println("result:"+result);
		Assert.assertEquals( "\"ny\"la", result);
		
		
		
		
		
	}//------------------------------------------------
	@Test
	public void testOverview()
	throws IOException
	{
	  //Use mkdir to create entire directory paths 
	   File inputDirectory = new File("./runtime/tmp/input");
	   IO.mkdir(inputDirectory);
	   Assert.assertTrue(inputDirectory.exists());
	   
	   //Write text or binary files
	   String fileName = inputDirectory.getAbsolutePath()+"/test.txt";
	   IO.writeFile(fileName, "Hello"+IO.newline()+"world",IO.CHARSET);
	   IO.writeFile(fileName, "Hello"+IO.newline()+"world");
	   
	   //Read text or binary files 
	   String output = IO.readFile(fileName);
	   Assert.assertEquals("Hello"+IO.newline()+"world",output);
	   
	   //Copy entire Directories and files nested files
	   File outputDirectory = new File("./runtime/tmp/output");
	   IO.copyDirectory(inputDirectory, outputDirectory);
	   Assert.assertTrue(outputDirectory.length() >= inputDirectory.length());
	   
	    //Use Wildcard pattern to list files
	   Assert.assertTrue(IO.list(outputDirectory, "*.txt").length > 0);
	   
	   //Delete file or directory (all files are deleted from the given directory)
	   IO.delete(inputDirectory);
	   Assert.assertTrue(!inputDirectory.exists()); 
	   
	}//------------------------------------------------
	@Test
	public void testListFileRecursive()
	throws Exception
	{
		String dir = "runtime/tmp/listfileRecursive";
		//File parent  = null ;
		Assert.assertNull(IO.listFileRecursive((File)null, null));
		IO.mkdir(dir);
		int LEN  =3;
		for (int i = 0; i < LEN; i++)
		{
			String nestedDir = dir+"/dir"+i;
			IO.mkdir(nestedDir);
			
			IO.writeFile(nestedDir+"/"+i+".txt", String.valueOf("i"));
			
		}
		
		
		//parent = Paths.get(dir).toFile();
		Set<File> results = IO.listFileRecursive(dir, "*.txt") ;
		assertNotNull(results);
		assertEquals(LEN,results.size());
		
		for (File file : results)
		{
			assertTrue("Is .txt "+ file.toString(),file.getName().endsWith(".txt"));
		}
		
	}//------------------------------------------------
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
