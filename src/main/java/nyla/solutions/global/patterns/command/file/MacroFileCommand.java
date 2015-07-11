package nyla.solutions.global.patterns.command.file;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;


import nyla.solutions.global.exception.RequiredException;

/**
 * The Macro file command
 * @author Gregory Green
 *
 */
public class MacroFileCommand implements FileCommand
{
	/**
	 * Execute the file on each given file command
	 */
	public synchronized void execute(File file)
	{
		if(this.fileCommands == null)
			throw new RequiredException("this.fileCommands in MacroFileCommand");

		FileCommand fileCommand = null;
		for(Iterator<FileCommand> i = (Iterator<FileCommand>)this.fileCommands.iterator();i.hasNext();)
		{
			fileCommand = i.next();
			
			fileCommand.execute(file);
		}
	}//---------------------------------------------
	/**
	 * @return the fileCommands
	 */
	public synchronized Collection<FileCommand> getFileCommands()
	{
		return fileCommands;
	}//---------------------------------------------


	/**
	 * @param fileCommands the fileCommands to set
	 */
	public synchronized void setFileCommands(Collection<FileCommand> fileCommands)
	{
		this.fileCommands = fileCommands;
	}


	private Collection<FileCommand> fileCommands = null;
	

}
