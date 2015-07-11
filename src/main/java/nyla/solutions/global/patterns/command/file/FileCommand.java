package nyla.solutions.global.patterns.command.file;

import java.io.File;

/**
 * Interface to process a file
 * @author Gregory Green
 *
 */
public interface FileCommand
{
	/**
	 * Perform an operation on a file/directory
	 * @param file the file to process
	 */
	public void execute(File file);
}
