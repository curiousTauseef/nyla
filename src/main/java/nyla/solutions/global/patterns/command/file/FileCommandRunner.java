package nyla.solutions.global.patterns.command.file;

import java.io.File;



import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.patterns.observer.Subject;
import nyla.solutions.global.util.Debugger;


/**
 * Runner implementation of a file command. 
 * This allows file command processing to be executed within a thread
 * @author Gregory Green
 *
 */
public class FileCommandRunner implements FileCommand, Runnable
{
	/**
	 * Constructor
	 * @param fileCommand the file command
	 * @param file the file runner
	 */
	public FileCommandRunner(FileCommand fileCommand, File file, Subject errorSubject)
	{
		if(file == null)
			throw new RequiredException("file in FileCommandRunner");
		
		
		if(fileCommand == null)
			throw new RequiredException("fileCommand in FileCommandRunner");
		
		this.setFileCommand(fileCommand);
		
		this.setFile(file);
		
		this.setErrorSubject(errorSubject);
	}//---------------------------------------------
	/**
	 * The execute the file command on a given file
	 */
	public void execute(File file)
	{
		if(file == null)
			throw new RequiredException("file command required");

		if(this.fileCommand == null)
			throw new RequiredException("fileCommand command required");
		
		//synchronize access to the file
		synchronized(file)
		{
			this.fileCommand.execute(file);	
		}
		
	}//---------------------------------------------
	/**
	 * 
	 */
	public void run()
	{
		try
		{
			this.execute(this.file);	
		}
		catch(RuntimeException e)
		{
			Debugger.printError(e);
			
			if(this.errorSubject != null)
				errorSubject.notify(e);
		}//---------------------------------------------
		
		
	}//---------------------------------------------	
	/**
	 * @return the fileCommand
	 */
	public FileCommand getFileCommand()
	{
		return fileCommand;
	}//---------------------------------------------
	/**
	 * @param fileCommand the fileCommand to set
	 */
	public void setFileCommand(FileCommand fileCommand)
	{
		this.fileCommand = fileCommand;
	}//---------------------------------------------
	/**
	 * @return the file
	 */
	public File getFile()
	{
		return file;
	}//---------------------------------------------
	/**
	 * @param file the file to set
	 */
	public void setFile(File file)
	{
		this.file = file;
	}//---------------------------------------------
	
	/**
	 * @return the subject
	 */
	public Subject getErrorSubject()
	{
		return errorSubject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setErrorSubject(Subject errorSubject)
	{
		this.errorSubject = errorSubject;
	}

	private Subject errorSubject = null;
	private File file = null;
	private FileCommand fileCommand = null;
}
