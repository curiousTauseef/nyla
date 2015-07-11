package nyla.solutions.global.patterns.command.file;

import java.io.File;



import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.io.IO;
import nyla.solutions.global.io.SynchronizedIO;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Text;


/**
 * This class includes the text content of a related file into the given file.
 * The related file is read.
 * The input text indicated by the replacementRE in the given execute method is replaced at runtime.
 * @author Gregory Green
 *
 */
public class ReplaceIncludeRelatedFileCommand  implements FileCommand
{

	/**
	 * Implement the file processing
	 * @param 
	 */
	public void execute(File file)
	{			
		if(this.replacementRE == null)
			throw new RequiredException("this. in ReplaceWithFormattedFileCommand");
		
		//read RACI HTML
		try 
		{
			String includeFileContent = IO.readFile(file.getAbsolutePath()+includeFileSufix);
			
			//applied formatting
			String fileText = IO.readFile(file);
			
			//replace text in processMapHTMLFile with formatted output
			SynchronizedIO.getInstance().writeFile(file.getAbsolutePath(),Text.replaceForRegExprWith(fileText,
					this.replacementRE,includeFileContent));
		} 
		catch (Exception e) 
		{
			throw new SystemException(Debugger.stackTrace(e));
		}
		
	}//---------------------------------------------
	
	/**
	 * @return the replacementRE
	 */
	public String getReplacementRE()
	{
		return replacementRE;
	}
	/**
	 * @param replacementRE the replacementRE to set
	 */
	public void setReplacementRE(String replacementRE)
	{
		this.replacementRE = replacementRE;
	}
	/**
	 * @return the includeFileSufix
	 */
	public String getIncludeFileSufix()
	{
		return includeFileSufix;
	}
	/**
	 * @param includeFileSufix the includeFileSufix to set
	 */
	public void setIncludeFileSufix(String includeFileSufix)
	{
		this.includeFileSufix = includeFileSufix;
	}
	private String replacementRE = null;
	private String includeFileSufix = Config.getProperty(this.getClass(),"includeFileSufix");
	
}
