package nyla.solutions.core.io.csv;


import java.io.File;
import java.nio.file.Paths;
import org.junit.Assert;
import org.junit.Test;

import nyla.solutions.core.io.IO;
import nyla.solutions.core.io.csv.CsvReader.DataType;
import nyla.solutions.core.io.csv.formulas.SumStatsByMillisecondsFormular;

public class CsvReaderTest
{


	
    @Test
	public void testMerge()
	throws Exception
	{
		File file = Paths.get("src/test/resources/csv/csvmerged.csv").toFile();
		
		SumStatsByMillisecondsFormular formula = new SumStatsByMillisecondsFormular(0, 1,1000);
		CsvReader reader = new CsvReader(file);
		reader.calc(formula);
		
		Assert.assertEquals(9,reader.size());
		Assert.assertEquals(10, formula.getMin());
		Assert.assertEquals(30, formula.getMax());
		Assert.assertEquals(20.0, formula.getAvg(),0);
		
	}//------------------------------------------------
	@Test
	public void testMergeCalculate()
	throws Exception
	{
		System.out.println(Paths.get("target/runtime").toFile().mkdirs());
		
		File file1 = Paths.get("target/runtime/csvReader1.csv").toFile();
		System.out.println(file1.delete());
		
		CsvWriter writer = new CsvWriter(file1);
		
		String oldest = String.valueOf(System.currentTimeMillis());
		String ts1 = String.valueOf(System.currentTimeMillis());
		Thread.sleep(2000);
		String ts2 = String.valueOf(System.currentTimeMillis());
		Thread.sleep(2000);
		String ts3 = String.valueOf(System.currentTimeMillis());
		
		writer.appendRow(ts3,"10");
		writer.appendRow(ts2,"20");
		writer.appendRow(ts1,"30");
		
		
		File file2 = Paths.get("target/runtime/csvReader2.csv").toFile();
		System.out.println(file2.delete());
		
		writer = new CsvWriter(file2);
		Thread.sleep(2000);
		ts1 = String.valueOf(System.currentTimeMillis());
		Thread.sleep(2000);
		ts2 = String.valueOf(System.currentTimeMillis());
		Thread.sleep(2000);
		ts3 = String.valueOf(System.currentTimeMillis());
		
		writer.appendRow(ts3,"10");
		writer.appendRow(ts2,"20");
		writer.appendRow(ts1,"30");
		
		
		File file3 = Paths.get("target/runtime/csvReader3.csv").toFile();
		System.out.println(file3.delete());
		
		writer = new CsvWriter(file3);
		Thread.sleep(2000);
		ts1 = String.valueOf(System.currentTimeMillis());
		Thread.sleep(2000);
		ts2 = String.valueOf(System.currentTimeMillis());
		Thread.sleep(2000);
		ts3 = String.valueOf(System.currentTimeMillis());
		
		
		writer.appendRow(ts3,"10");
		writer.appendRow(ts2,"20");
		writer.appendRow(ts1,"30");
		
		File merged = Paths.get("target/runtime/csvmerged.csv").toFile();
		IO.mergeFiles(merged, file1,file2,file3);
		
		CsvReader reader = new CsvReader(merged);
		
		Assert.assertEquals(9,reader.size());
		//Assert.assertEquals(ts1,reader.row(0).get(0));
		
		reader.sortRowsForIndexByType(0, DataType.Long);
		
		Assert.assertEquals(oldest,reader.row(0).get(0));
		
		SumStatsByMillisecondsFormular formula = new SumStatsByMillisecondsFormular(0, 1,1000);
		reader.calc(formula);
		
		System.out.println("formula:"+formula);
		
		Assert.assertEquals(20, formula.getAvg(),0);
		Assert.assertEquals(10, formula.getMin());
		Assert.assertEquals(30, formula.getMax());
		
		
		File file = Paths.get("target/runtime/csvReader.csv").toFile();
		System.out.println(file.delete());
		writer = new CsvWriter(file);
		
		writer.appendRow(ts3,"22");
		 reader = new CsvReader(file);
		 reader.calc(formula);
		 
		 System.out.println("formula:"+formula);
		 
		Assert.assertEquals(22, formula.getAvg(),0);
		Assert.assertEquals(22, formula.getMin());
		Assert.assertEquals(22, formula.getMax());
		
		
		System.out.println(file.delete());
		writer.appendRow("");
		 reader = new CsvReader(file);
		 reader.calc(formula);
			
			Assert.assertEquals(-1, formula.getAvg(),0);
			Assert.assertEquals(-1, formula.getMin());
			Assert.assertEquals(-1, formula.getMax());
			
	}

}
