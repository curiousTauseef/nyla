package nyla.solutions.global.io;


import java.io.File;
import java.io.IOException;

import nyla.solutions.core.exception.SystemException;
import nyla.solutions.core.io.IO;
import nyla.solutions.core.util.Config;
import nyla.solutions.core.util.Debugger;
import nyla.solutions.global.patterns.command.Environment;
import nyla.solutions.global.patterns.command.Executable;


/**
 * Deletes a given file/folders
 * @author Gregory Green
 *
 */
public class EmptyFolderExecutable  implements Executable
{
   /**
    * Delete the a given file/folder
    * @param env the environment information
    * @param args the input arguments
    */
   public void execute(Environment env, String[] args)
   {
	try
	{
	   IO.emptyFolder(new File(this.folderPath));   
	} 
	catch (IOException e)
	{
	   throw new SystemException(Debugger.stackTrace(e));
	}	
   }// ----------------------------------------------
   /**
    * @return destination
    * @see nyla.solutions.global.patterns.command.Command#execute(java.lang.Object)
    */
   public Integer execute(Environment source)
   {
     execute(source, null);
   	  return 0;
   }// --------------------------------------------------------
   public void execute()
   {
	   execute(null);
   }
      
   
   
   /**
	 * @return the folderPath
	 */
	public String getFolderPath()
	{
		return folderPath;
	}
	/**
	 * @param folderPath the folderPath to set
	 */
	public void setFolderPath(String folderPath)
	{
		this.folderPath = folderPath;
	}


	private String folderPath = Config.getProperty(EmptyFolderExecutable.class,"folderPath","");

}
