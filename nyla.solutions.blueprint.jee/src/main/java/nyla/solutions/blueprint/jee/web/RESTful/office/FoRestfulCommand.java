package nyla.solutions.blueprint.jee.web.RESTful.office;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nyla.solutions.blueprint.jee.web.Web;
import nyla.solutions.blueprint.jee.web.RESTful.AbstractRESTfulCommand;
import nyla.solutions.global.data.Textable;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Text;
import nyla.solutions.office.fop.FOP;


/**
 * THE FOP wrapper REST web service
 * @author Gregory Green
 *
 */
public class FoRestfulCommand extends AbstractRESTfulCommand
{
	/**
	 * Implement the get method
	 * Creates an FO documentation based on a the given file name information
	 */
	public void get(HttpServletRequest request, HttpServletResponse response)
	{
		try 
		{
			//set content type
			response.setContentType(this.format);
			
			writeOutputStream(Web.toMap(request),response.getOutputStream());
		} 
		catch (Exception e) 
		{
			Debugger.printError(e);
			
			throw new SystemException(e.getMessage(),e);
		}
	}//---------------------------------------------
	/**
	 * Creates an FO documentation based on a the given file name information
	 */
	protected void writeOutputStream(Map<?,?> requestMap, OutputStream outputStream)
	{
		try 
		{			
			//get FO text
			String foTemplate = foTextableTemplate.getText();
			
			//build map
			Map<Object,Object> map = Config.getProperties();

			map.putAll(requestMap);		
			
			//format
			String foXML = Text.format(foTemplate, map);
			
			//created PDF output
			
			FOP.writeOutputStream(foXML, format, outputStream);			
		} 
		catch (Exception e) 
		{
			throw new SystemException(Debugger.stackTrace(e));
		}
	}//---------------------------------------------
	/**
	 * @return the foTextableTemplate
	 */
	public Textable getFoTextableTemplate()
	{
		return foTextableTemplate;
	}//---------------------------------------------
	/**
	 * @param foTextableTemplate the foTextableTemplate to set
	 */
	public void setFoTextableTemplate(Textable foTextableTemplate)
	{
		this.foTextableTemplate = foTextableTemplate;
	}//---------------------------------------------
	private String format = Config.getProperty(this.getClass(),"format");	
	private Textable foTextableTemplate = null;
}
