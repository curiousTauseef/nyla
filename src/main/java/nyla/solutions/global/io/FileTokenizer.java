package nyla.solutions.global.io;

import java.io.File;
import java.util.StringTokenizer;

import nyla.solutions.global.util.Text;

/**
 * Parses tokenizer from a given file name
 * @author Gregory Green
 *
 */
public class FileTokenizer
{	
	/**
	 * default constructor
	 */
	public FileTokenizer()
	{
	}//---------------------------------------------
	public FileTokenizer(File file)
	{
		this.setFile(file);
	}//---------------------------------------------
	/**
	 * @return the file
	 */
	public File getFile()
	{
		return this.file;
	}//---------------------------------------------
	/**
	 * @param file the file to set
	 */
	public void setFile(File file)
	{
		this.file = file;
		
		if(file == null)
			return;
		
		StringTokenizer tokenizer = new StringTokenizer(this.file.getName(),this.separatorChars);
		
		fileNameTokens = Text.toStrings(tokenizer);
	}//---------------------------------------------
	/**
	 * @return the separatorChars
	 */
	public String getSeparatorChars()
	{
		return separatorChars;
	}//---------------------------------------------
	/**
	 * @param separatorChars the separatorChars to set
	 */
	public void setSeparatorChars(String separatorChars)
	{
		this.separatorChars = separatorChars;
	}//---------------------------------------------
	/**
	 * 
	 * @return the tokenized file name
	 */
	public String[] getFileNameTokens()
	{
		if(fileNameTokens == null)
			return null;
		
		return fileNameTokens.clone();
	}//---------------------------------------------
	/**
	 * @return
	 * @see java.io.File#getName()
	 */
	public String getName()
	{
		return file.getName();
	}//---------------------------------------------
	private String[] fileNameTokens = null;
	private File file = null;
	private String separatorChars = ".";
}
