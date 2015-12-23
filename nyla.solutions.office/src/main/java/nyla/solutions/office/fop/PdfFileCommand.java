package nyla.solutions.office.fop;

import java.io.File;
import java.util.Map;

import nyla.solutions.global.data.Textable;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.patterns.command.file.FileCommand;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.JavaBean;
import nyla.solutions.global.util.Text;



/**
 * This class uses FO XML to generate a PDF file contains a given image file contain
 * @author Gregory Green
 */
public class PdfFileCommand implements FileCommand
{
	/**
	 * Implements the the FileCommand interface
	 * 
	 * Creates the output PDF in the directory
	 * 
	 * this.outputPath/<file-name>.pdf
	 */
	public void execute(File file)
	{		
		try 
		{
			//get FO text
			String foTemplate = foTextableTemplate.getText();
			
			Map<Object,Object> map = Config.getProperties();

			//add current file location
			//add file properties
			map.putAll(JavaBean.toMap(file));			
			
			//format
			String foXML = Text.format(foTemplate, map);
			
			//created PDF output
			FOP.writePDF(foXML, new File(outputPath+Config.getFileSeparator()+file.getName()+".pdf"));			
		} 
		catch (Exception e) 
		{
			throw new SystemException(Debugger.stackTrace(e));
		}
	}//---------------------------------------------
	
	/**
	 * @return the outputPath
	 */
	public String getOutputPath()
	{
		return outputPath;
	}
	/**
	 * @param outputPath the outputPath to set
	 */
	public void setOutputPath(String outputPath)
	{
		this.outputPath = outputPath;
	}
	/**
	 * @return the foTextableTemplate
	 */
	public Textable getFoTextableTemplate()
	{
		return foTextableTemplate;
	}
	/**
	 * @param foTextableTemplate the foTextableTemplate to set
	 */
	public void setFoTextableTemplate(Textable foTextableTemplate)
	{
		this.foTextableTemplate = foTextableTemplate;
	}
	private String outputPath = Config.getProperty(getClass(),"outputPath");
	private Textable foTextableTemplate = null;
}
