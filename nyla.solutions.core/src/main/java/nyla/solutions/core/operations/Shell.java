package nyla.solutions.core.operations;
import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import nyla.solutions.core.io.IO;
import nyla.solutions.core.util.Debugger;

/**
 * Wrapper class to execute environment scripts
 * @author Gregory Green
 *
 */
public class Shell
{
	public Shell()
	{
		this(null,null);
	}// --------------------------------------------------------
	public Shell(File workingDirectory, File logFile)
	{
		this.log = logFile;
		this.workingDirectory = workingDirectory;
	}// --------------------------------------------------------
	/**
	 * 
	 * @param commands the commands to execute
	 * @return the process information
	 * @throws IOException
	 */
	public ProcessInfo execute(List<String>  commands)
	throws IOException
	{
		 ProcessBuilder pb = new ProcessBuilder();
		 
		 pb.command(commands);

		return executeProcess(pb);
	}// --------------------------------------------------------
	/**
	 * 
	 * @param command the commands to execute
	 * @throws IOException
	 */
	public ProcessInfo execute(String...  command)
	{
		 ProcessBuilder pb = new ProcessBuilder(command);

		 
		return executeProcess(pb);
		
	}// --------------------------------------------------------
	/**
	 * 
	 * @param pb the process builder
	 * @return the process information
	 * @throws IOException
	 */
	private ProcessInfo executeProcess(ProcessBuilder pb) 
	{
		try
		{
		
			
			 pb.directory(workingDirectory);
			 //pb.redirectErrorStream(true);
			
			if(log != null)
				pb.redirectOutput(Redirect.appendTo(log));
			
			Process p = pb.start();

			String out = IO.readText(p.getInputStream(),true);
			
			String error = IO.readText(p.getErrorStream(),true);
				
  	
			return new ProcessInfo(p.waitFor(),out,error);
		}
		catch (Exception e)
		{
			return new ProcessInfo(-1, null, Debugger.stackTrace(e));
		}
	}// --------------------------------------------------------

	public static final class ProcessInfo
	{
		
		public ProcessInfo(int exitValue, String output, String error)
		{
			super();
			this.exitValue = exitValue;
			this.output = output;
			this.error = error;
		}// --------------------------------------------------------
		
		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder();
			builder.append("ProcessInfo [exitValue=").append(exitValue)
					.append(", output=").append(output).append(", error=")
					.append(error).append("]");
			return builder.toString();
		}
		

		/**
		 * 
		 * @return true is stand error or
		 */
		public boolean hasError()
		{
			return this.exitValue != 0 || (this.error != null && this.error.length() > 0);
		}// --------------------------------------------------------
		public final int exitValue;
		public final String output, error; 
		
	}
    private final File workingDirectory;
    private final File log;
    
}
