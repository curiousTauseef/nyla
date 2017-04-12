package nyla.solutions.core.io.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import nyla.solutions.core.io.csv.formulas.CsvFormula;

/**
 * 
 * @author Gregory Green
 *
 */
public class CsvReader
{
	/**
	 * Data typing enum
	 *
	 */
	public static enum DataType { String, Long};
	
	private final File file;
	private final ArrayList<List<String>> data;
	
	public CsvReader(File file)
	throws IOException
	{
		this.file = file;
		
		if(!file.exists())
		{
			throw new IllegalArgumentException("File:"+this.file.getAbsolutePath()+" does not exist");
		}
		
	    String line = null;
	    data = new ArrayList<List<String>>();
		try (BufferedReader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8))
		{
		    while ((line = reader.readLine()) != null) 
		    {
		    	this.data.add(parse(line));
		    }
		} 
		
	}//------------------------------------------------
	public <T> T get(int row, int col, DataType dataType)
	{
		List<String> rowList = row(row);
		
		if(rowList == null || rowList.isEmpty() || col >= rowList.size())
			return nullFor(dataType);
		
	
		String cell = rowList.get(col);
		
		
		
		T results = toType(cell,dataType);
		return results;
	}//------------------------------------------------
	@SuppressWarnings("unchecked")
	private <T> T nullFor(DataType dataType)
	{
		switch(dataType)
		{
			case Long: return (T)Long.valueOf(-1);
			default: return null;
		}
	}
	@SuppressWarnings("unchecked")
	private <T> T toType(String cell,DataType  dataType)
	{
		
		switch(dataType)
		{
			case Long: return cell != null && cell.length() > 0 ? (T)Long.valueOf(cell) : (T)Long.valueOf(-1); 
			default: return (T)cell;
		}
	}//------------------------------------------------
	/**
	 * 
	 * @return this.data.isEmpty()
	 */
	public boolean isEmpty()
	{
		return this.data.isEmpty();
	}
	//------------------------------------------------
	public void calc(CsvFormula csvFormula)
	{
		csvFormula.calc(this);
	}//------------------------------------------------
	
	private List<String> parse(String line)
	{
		if(line == null || line.length() ==0)
			return null;
		
		String[] cells = line.split(",");
		
		int startIndex, endIndex;
				
		for (int i = 0; i < cells.length; i++)
		{
			cells[i] = cells[i].trim();
			

			
			if(cells[i].startsWith("\""))
			{

				startIndex = 1;
				endIndex = cells[i].length();
				if(cells[i].endsWith("\""))
				{
					endIndex--;
				}
				cells[i] = cells[i].substring(startIndex, endIndex).replace("\"\"","\"");
			}
		}
		
		if(cells == null || cells.length == 0)
			return Collections.singletonList(line);
		
		return Arrays.asList(cells);
	
	}//------------------------------------------------
	/**
	 * 
	 * @param rowNumber the row number to get
	 * @return get the row (start at zero)
	 */
	public List<String> row(int rowNumber)
	{
		List<String> row = data.get(rowNumber);
		
		if(row == null)
			return null;
		
		//return a copy
		return new ArrayList<String>(row);
	}//------------------------------------------------
	public void sortRowsForIndexByType(int index, DataType dataType)
	{
		Comparator<List<String>> comparator = null;
		
		switch(dataType)
		{
		
			case Long : { comparator = (list1, list2) -> 
				Long.valueOf(list1.get(0)).compareTo(Long.valueOf(list2.get(0))); 
			   }
			break;
			default:
				comparator = (list1, list2) -> 
				list1.get(0).compareTo(list2.get(0));
		}

		sortRows(comparator);
	}//------------------------------------------------
	

	
	/**
	 * Sort the rows by comparator
	 * @param comparator the comparator used for sorting
	 */
	public void sortRows(Comparator<List<String>> comparator)
	{
		if(data.isEmpty())
			return;
		
		Collections.sort(data, comparator);
	}//------------------------------------------------
	
	public int size()
	{
		return data.size();
	}//------------------------------------------------
}
