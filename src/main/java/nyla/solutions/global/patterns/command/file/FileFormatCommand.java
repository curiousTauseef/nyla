package nyla.solutions.global.patterns.command.file;

import java.io.File;
import java.util.HashMap;
import java.util.Map;



import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.io.IO;
import nyla.solutions.global.io.SynchronizedIO;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.JavaBean;
import nyla.solutions.global.util.Text;


/**
 * Read the file content and applied text formatting
 * @author Gregory Green
 *
 */
public class FileFormatCommand implements FileCommand<Object>
{
	/**
	 * Applied formatting to the file content
	 */
	public Object execute(File file)
	{		
		try 
		{
			Map<Object,Object> map = JavaBean.toMap(file);
			map.putAll(valueMap);
			map.putAll(Config.getProperties());
			String content = IO.readFile(file.getAbsolutePath());
			map.put(this.contentKey,content);
			String output = Text.format(template, map);
			//overwrite output
			SynchronizedIO.getInstance().writeFile(file.getAbsolutePath(),output);
			
			return null;
		} 
		catch (Exception e) 
		{
			throw new SystemException(Debugger.stackTrace(e));
		}
	}//---------------------------------------------
	
	/**
	 * @return the contentKey
	 */
	public String getContentKey()
	{
		return contentKey;
	}
	/**
	 * @param contentKey the contentKey to set
	 */
	public void setContentKey(String contentKey)
	{
		this.contentKey = contentKey;
	}//---------------------------------------------
	
	/**
	 * @return the template
	 */
	public String getTemplate()
	{
		return template;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(String template)
	{
		this.template = template;
	}

	
	/**
	 * @return the valueMap
	 */
	public HashMap<Object,Object> getValueMap()
	{
		return valueMap;
	}

	/**
	 * @param valueMap the valueMap to set
	 */
	public void setValueMap(HashMap<Object,Object> valueMap)
	{
		this.valueMap = valueMap;
	}


	private String template =Config.getProperty(getClass(),"template","");

	private String contentKey = Config.getProperty(getClass(),"contentKey","content");
	private HashMap<Object,Object> valueMap = new HashMap<Object,Object>();

}
