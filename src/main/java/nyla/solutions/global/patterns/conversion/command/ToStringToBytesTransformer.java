package nyla.solutions.global.patterns.conversion.command;

import java.io.UnsupportedEncodingException;

import nyla.solutions.global.exception.SetupException;
import nyla.solutions.global.patterns.command.Command;
import nyla.solutions.global.util.Config;

/**
 * 
 * @author Gregory Green
 *
 */
public class ToStringToBytesTransformer implements Command<byte[],Object>
{
	/**
	 * Transform the object.toString method bytes
	 * @see nyla.solutions.global.patterns.command.Command#transform(java.lang.Object)
	 */
	public byte[] execute(Object source)
	{
		if(source == null)
			return null;
		
		try
		{
			String text = source.toString();
			
			return text.getBytes(encoding);
		}
		catch (UnsupportedEncodingException e)
		{
			throw new SetupException("Unsupported encoding "+encoding);
		}
	}// --------------------------------------------	
	/**
	 * @return the encoding
	 */
	public String getEncoding()
	{
		return encoding;
	}// --------------------------------------------

	/**
	 * @param encoding the encoding to set
	 */
	public void setEncoding(String encoding)
	{
		this.encoding = encoding;
	}// --------------------------------------------

	private String encoding = Config.getProperty(ToStringToBytesTransformer.class,"encoding","UTF-8");
}

