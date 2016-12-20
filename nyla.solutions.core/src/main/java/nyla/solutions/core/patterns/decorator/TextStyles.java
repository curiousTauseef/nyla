package nyla.solutions.core.patterns.decorator;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import nyla.solutions.core.exception.FormatException;


public interface TextStyles extends Styles
{
	/**
	 * The template prefix (mark the beginning of a place-holder)
	 */
	public String getTemplatePrefix();
	
	/**
	 * The template suffix
	 */
	public String getTemplateSuffix();
	
	/**
	 * 
	 * 
	 * 
	 * Inserts values from the object template into the Bind Str
	 * 
	 * For example if bindStr "Today is ${DAY}" and bindPara DAY="Monday"
	 * 
	 * results in a string "Today is Monday"
	 * 
	 * 
	 * 
	 * @param aBindText
	 * 
	 * @param aBindMap values to insert into the bind text
	 * 
	 * @param aDateFormat i.e. MM/dd/yyyy
	 * 
	 */

	public abstract String format(String aBindText, Object obj,
			String aDateFormat)

	throws FormatException;

	/**
	 * 
	 * 
	 * 
	 * Inserts values from the object template into the Bind Str
	 * 
	 * For example if bindStr "Today is ${DAY}" and bindPara DAY="Monday"
	 * 
	 * results in a string "Today is Monday"
	 * 
	 * Default dateFormat MM/dd/yyyy
	 * 
	 * Functions <#if x = 1> x is 1 <#elseif x = 2> x is 2 <#elseif x = 3>
	 * 
	 * x is 3 <#elseif x = 4> x is 4 <#else> x is not 1 nor 2 nor 3 nor 4 </#if>
	 * 
	 * <#if Tithes_1?exists> OK <#else> <#assign Tithes_1= 0> </#if>
	 * 
	 * Date formating ${lastUpdated?string("yyyy-MM-dd HH:mm:ss zzzz")}
	 * 
	 * @param aBindText
	 * 
	 * @param aBindMap values to insert into the bind text
	 * 
	 * 
	 * 
	 */

	public abstract String format(String aBindText, Object aBindMap)
			throws FormatException;

	/**
	 * write formatted output to the writer
	 * 
	 * @param aBindMap
	 * @param text
	 * @param writer
	 * @throws IOException
	 * @throws TemplateException
	 */
	public abstract void formatWriter(String text, Object bindObject,
			String dateFormat, Writer writer) throws IOException,
			FormatException;

	public abstract void formatWriterFromTemplateName(String templateName,
			Object aBindMap, Writer writer) throws IOException, FormatException;

	public abstract void formatWriter(File templateDir, String templateName,
			Object aBindMap, String dateFormat, Writer writer)
			throws IOException, FormatException;

}
