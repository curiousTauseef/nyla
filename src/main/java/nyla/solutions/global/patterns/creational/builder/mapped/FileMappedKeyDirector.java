package nyla.solutions.global.patterns.creational.builder.mapped;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.io.IO;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Text;


public class FileMappedKeyDirector<K,V> extends MappedKeyDirector<K,V>
{
	
	/**
	 * Director method to construct a document
	 * @param path the 
	 * @param engineer
	 */
	public void constructDocument(String filePath, MappedKeyEngineer<K,V> engineer)
	{
		//clear cache		
		try
		{
			crawl(new File(filePath), engineer);	
		}
		finally
		{
			crawledPaths.clear();
		}		
	}// --------------------------------------------
	/**
	 * Director method to construct a document
	 * @param path the 
	 * @param engineer
	 */
	protected void crawl(File file, MappedKeyEngineer<K,V> engineer)
	{
		
		if (!file.exists())
		{
			Debugger.println(file + " does not exist.");
			return;
		}
		if (file.isDirectory())
		{
			File[] files = IO.listFiles(file, listPattern);

			for (int i = 0; i < files.length; i++)
			{
				//recursive
				if(!this.mustSkip(files[i]))
				crawl(files[i],engineer);
			}
		}
		else
		{
			try
			{
				engineer.construct(file.getPath(), this.constructMapToText(file.getPath()));
				crawledPaths.add(file.getPath());				
			}
			catch(NoDataFoundException e)
			{
				//print warning if found not 
				Debugger.printWarn(e);
			}
		}
	}// --------------------------------------------
	private boolean mustSkip(File file)
	{
		String path = file.getPath();
		
		if(Text.matches(path, ignorePathRegExp) || crawledPaths.contains(path))
		{
			Debugger.println(this, "skipped ="+path);
			return true;
		}
		return false;
	}// --------------------------------------------
	private String listPattern = Config.getProperty(this.getClass(),"listPattern","*.([xX][mM][lL]|[hH][tT][mM][lL]?|txt|TXT|xml|XML)");
	private Set<String> crawledPaths = new HashSet<String>();
	private String ignorePathRegExp = "";
	//private int quota = Integer.MAX_VALUE;
}
