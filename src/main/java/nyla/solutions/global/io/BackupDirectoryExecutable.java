package nyla.solutions.global.io;
import java.io.File;
import java.io.IOException;



import nyla.solutions.global.exception.FatalException;
import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.exception.SetupException;
import nyla.solutions.global.io.BackupDirectoryExecutable;
import nyla.solutions.global.io.CopyDirectoryExecutable;
import nyla.solutions.global.io.IO;
import nyla.solutions.global.patterns.command.Environment;
import nyla.solutions.global.patterns.command.Executable;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;


/**
 * Backup the content of given file/folder
 * @author Gregory Green
 *
 */
public class BackupDirectoryExecutable implements Executable
{

	/**
	 * Constructor 
	 */
	public BackupDirectoryExecutable()
	{
		
	}//---------------------------------------------
	/**
	 * Delete a given backup directory and overwrite with a source directory
	 */
	public Integer execute(Environment env)
	{
		if(this.sourcePath == null || this.sourcePath.length() == 0)
			throw new RequiredException("this.sourcePath in BackupDirectoryExecutable");
		
	
		if(this.backupPath == null || backupPath.length() == 0)
			throw new RequiredException("backupPath in BackupDirectoryExecutable");
		
		File backupFolder = new File(this.backupPath);
		
		if(!backupFolder.exists())
		{
			if(backupFolder.mkdir())
			{
				Debugger.println(this,"Create directory:"+backupFolder);
			}
		}
			
		if(!backupFolder.isDirectory())
			throw new SetupException("The provided backup path \""+backupFolder+"\" must be a directory");
		
		try 
		{
			
			
			if(backupFolder.list().length > 0)
			{
				//delete previous backup
				IO.delete(backupFolder);
				
				//recreate backup
				if(IO.mkdir(backupFolder))
					Debugger.println(this,"Made directory:"+backupFolder);
			}

			Debugger.println(this,"Backing up directory "+sourcePath);
			
			//copy file to backup folder
			IO.copyDirectory(new File(this.sourcePath), backupFolder);
			
			
			return 0;
		}
		catch (IOException e) 
		{
		
			throw new FatalException(Debugger.stackTrace(e));
		}
	}//---------------------------------------------
	
	/**
	 * @return the backupPath
	 */
	public String getBackupPath()
	{
		return backupPath;
	}
	/**
	 * @param backupPath the backupPath to set
	 */
	public void setBackupPath(String backupPath)
	{
		this.backupPath = backupPath;
	}//---------------------------------------------
	

	/**
	 * @return the sourcePath
	 */
	public String getSourcePath()
	{
		return sourcePath;
	}
	/**
	 * @param sourcePath the sourcePath to set
	 */
	public void setSourcePath(String sourcePath)
	{
		this.sourcePath = sourcePath;
	}


	private String sourcePath = Config.getProperty(CopyDirectoryExecutable.class,"destination","");
	private String backupPath = Config.getProperty(BackupDirectoryExecutable.class,"backupPath","");

}
