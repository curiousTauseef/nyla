package nyla.solutions.global.patterns.command.file;

import java.io.File;
import java.io.IOException;



import nyla.solutions.global.data.Textable;
import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.io.IO;
import nyla.solutions.global.io.SynchronizedIO;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Text;


/**
 * Overwrite the file with textable.getText(). It replaces the this.regExp with the output of 
 * textable.getText().
 * 
 * @author Gregory Green
 *
 */
public class ReplaceWithTextCommand implements FileCommand
{
	/**
	 * Overwrite file with text output 
	 */
	public void execute(File file)
	{		
		if(this.textable == null)
			throw new RequiredException("this.textable in ReplaceWithTextCommand");

		try
		{
			if(regExp != null)
				regExp = regExp.trim();
			
			//read text
			String text = IO.readFile(file.getAbsolutePath());
			
			//replace text
			
			if(textable == null)
				throw new RequiredException("textable in ReplaceWithTextCommand");
			
			SynchronizedIO.getInstance().writeFile(file.getAbsolutePath(),Text.replaceForRegExprWith(text,this.regExp,this.textable.getText()));
		} 
		catch (IOException e)
		{
			throw new SystemException(Debugger.stackTrace(e));
		}
	}//---------------------------------------------
	/**
	 * @return the textable
	 */
	public Textable getTextable()
	{
		return textable;
	}//---------------------------------------------
	/**
	 * @param textable the textable to set
	 */
	public void setTextable(Textable textable)
	{
		this.textable = textable;
	}//---------------------------------------------

	/**
	 * @return the regExp
	 */
	public String getRegExp()
	{
		return regExp;
	}//---------------------------------------------
	/**
	 * @param regExp the regExp to set
	 */
	public void setRegExp(String regExp)
	{
		this.regExp = regExp;
	}//---------------------------------------------

	private String regExp = "";
	private Textable textable = null;
}
