package nyla.solutions.global.patterns.command.file;

import java.io.File;




import javax.annotation.concurrent.NotThreadSafe;

import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.patterns.expression.ObjectBooleanExpression;
import nyla.solutions.global.util.Debugger;


/**
 * Execute a given file command if the boolean expression is true
 * @author Gregory Green
 *
 */
@NotThreadSafe
public class ConditionFileCommand implements FileCommand
{
	/**
	 * Execute fileCommand if expression is true
	 */
	public synchronized void execute(File file)
	{	
		if(this.objectBooleanExpression == null)
			throw new RequiredException("this.booleanExpression in ConditionFileCommand");
		
		if(this.fileCommand == null)
			throw new RequiredException("this.fileCommand in ConditionFileCommand");
		
		objectBooleanExpression.setEvaluationObject(file);

		if(objectBooleanExpression.getBoolean(file))
		{
			Debugger.println(this,"Executing "+this.fileCommand.getClass().getName());
			this.fileCommand.execute(file);
		}
	}//---------------------------------------------

	
	
	/**
	 * @return the objectBooleanExpression
	 */
	public ObjectBooleanExpression getObjectBooleanExpression()
	{
		return objectBooleanExpression;
	}



	/**
	 * @param objectBooleanExpression the objectBooleanExpression to set
	 */
	public void setObjectBooleanExpression(
			ObjectBooleanExpression objectBooleanExpression)
	{
		this.objectBooleanExpression = objectBooleanExpression;
	}



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
	}

	private FileCommand fileCommand = null;
	private ObjectBooleanExpression objectBooleanExpression = null;
	
}
