package nyla.solutions.core.patterns.decorator;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import nyla.solutions.core.exception.FormatException;
import nyla.solutions.core.util.Config;
import nyla.solutions.core.util.Text;

/**
 * Abstract TextStyle implementation
 * @author Gregory Green
 *
 */
public abstract class TextStylist implements TextStyles
{
	/**
	 * @return format(bindText,bindObj,Text.DATE_FORMAT) 
	 * @see nyla.solutions.core.patterns.decorator.style.TextStyles#format(java.lang.String, java.lang.Object)
	 */
	public String format(String bindText, Object bindObj)
			throws FormatException
	{
		return format(bindText,bindObj,Text.DATE_FORMAT);
	}// --------------------------------------------------------

	/**
	 * @see nyla.solutions.core.patterns.decorator.style.Styles#formatMap(java.util.Map)
	 */
	@Override
	public void formatMap(Map<Object,Object> map) throws FormatException
	{
		if (map == null || map.isEmpty())
			return;// nothing to process

		// format properties
		Object key = null;
		Object value = null;
		String text = null;

		for (Map.Entry<Object, Object> entry :map.entrySet())
		{
			key = entry.getKey();
			value = entry.getValue();

			if (!(value instanceof String))
				continue; // skip

			text = (String) value;

			// overwrite value with formatted version
			if (text.indexOf(getTemplatePrefix()) > -1)
				map.put(key, format(text, map));
		}

	}// ----------------------------------------------

	/**
	 * @return the templatePrefix
	 */
	public String getTemplatePrefix()
	{
		return templatePrefix;
	}// --------------------------------------------------------

	/**
	 * @param templatePrefix the templatePrefix to set
	 */
	public void setTemplatePrefix(String templatePrefix)
	{
		this.templatePrefix = templatePrefix;
	}// --------------------------------------------------------

	/**
	 * @return the templateSuffix
	 */
	public String getTemplateSuffix()
	{
		return templateSuffix;
	}// --------------------------------------------------------

	/**
	 * @param templateSuffix the templateSuffix to set
	 */
	public void setTemplateSuffix(String templateSuffix)
	{
		this.templateSuffix = templateSuffix;
	}// --------------------------------------------------------
	/**
	 * 
	 * @see nyla.solutions.core.patterns.decorator.style.TextStyles#formatWriter(java.lang.String, java.lang.Object, java.lang.String, java.io.Writer)
	 */
	public void formatWriter(String text, Object bindObj, String dateFormat,
			Writer writer) throws IOException, FormatException
	{
		writer.write(format(text,bindObj,dateFormat));
	}// --------------------------------------------------------

	private String templatePrefix = Config.getProperty(this.getClass(),
			"templatePrefix", DEFAULT_PREFIX);
	private String templateSuffix = Config.getProperty(this.getClass(),
			"templateSuffix", DEFAULT_SUFFIX);
}
