package nyla.solutions.office.fop;

import java.io.File;
import java.io.FileNotFoundException;

import nyla.solutions.global.data.Textable;
import nyla.solutions.global.exception.ConfigException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.io.Fileable;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Text;



/**
 * Creates a PDF file from the FO XML in a given textable object
 * See EmailFileExecutable for sample usage
 * @author Gregory Green
 *
 */
public class PdfFileDecorator implements Fileable
{
	/**
	 * The PDF file output and return the file handle
	 */
	public File getFile()
	{
		try
		{
			if(foTextable == null)
			{
				throw new ConfigException("fo required on "+this.getClass().getName());
			}

			if(Text.isNull(filePath))
			{
				throw new ConfigException("filePath required on "+this.getClass().getName());
			}

			File file= new File(filePath);
			
			FOP.writePDF(this.getFoTextable().getText(), file);
			
			return file;
		} 
		catch (FileNotFoundException e)
		{
			throw new SystemException(Debugger.stackTrace(e));
		}
	}//---------------------------------------------
	
	/**
	 * @return the foTextable
	 */
	public Textable getFoTextable()
	{
		return foTextable;
	}//---------------------------------------------
	/**
	 * @param fo the FO XML textable to set
	 */
	public void setFoTextable(Textable foTextable)
	{
		this.foTextable = foTextable;
	}

	private Textable foTextable = null;
	private String filePath = Config.getProperty(getClass(),"filePath");
	

}
