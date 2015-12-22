package nyla.solutions.global.patterns.decorator.style;
import java.io.*;
import java.util.*;

import freemarker.template.Configuration;
import nyla.solutions.global.exception.FormatException;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Text;



/**
 * <pre>
 * <b>Text </b> contains common functions for text strings.
 * 
 * Properties
 * 
 * solutions.global.util.Text.template.dir=/temp
 * 
 * #Default .txt
 * solutions.global.util.Text.template.extension=/temp 
 * </pre>
 * @author Gregory Green
 *  
 */

public class FreeMarkerTextStyles extends TextStylist implements TextStyles
{     

  

   /**
    * 
    * Templates location is based on the configuration property "temp.dir"
    * 
    * 
    * 
    * Each template file as the following format;
    * 
    * 
    * 
    * <templateName>_ <countryLowerCase>_ <languageLowerCase>.txt
    * 
    * @param aTemplateName
    *           the template name
    * 
    * @param aBindMap
    *           the binding name/values pairs to insert into the template
    * 
    * @param aLocale
    *           the locale indicates the location and country
    * 
    * @return formated string
    * 
    * @throws Exception
    *  
    */
   

   public final String formatFromTemplate(String aTemplateName,
                                                 Map<Object,Object> aBindMap, Locale aLocale)

   throws Exception

   {

      return format(Text.loadTemplate(aTemplateName, aLocale), aBindMap);

   }//--------------------------------------------

   /**
 * @see nyla.solutions.global.patterns.decorator.style.Styles#format(java.lang.String, java.lang.Object, java.lang.String)
 */

   public String format(String aBindText, Object obj,
                               String aDateFormat)

   throws FormatException

   {
      try
      {
   
         StringWriter writer = new StringWriter();
   
         formatWriter(aBindText,obj,aDateFormat, writer);
   
         return writer.getBuffer().toString();
      }
      catch(Exception e)
      {
         throw new FormatException("ERROR="+e.toString()+"\n BindText="+aBindText+"\n BindMap="+obj+" "+Debugger.stackTrace(e));
      }
   }//-----------------------------------------
   /**
 * @see nyla.solutions.global.patterns.decorator.style.Styles#format(java.lang.String, java.lang.Object)
 */

   public  String format(String aBindText, Object aBindMap)
   throws FormatException
   {

      return Text.format(aBindText, aBindMap, Text.DATE_FORMAT);

   }//------------------------------------------------------------------    
   
   /**
    * write formatted output to the writer
    * @param aBindMap
    * @param text
    * @param writer
    * @throws IOException
    * @throws TemplateException
    */
   public void formatWriter( String text, Object obj, Writer writer) 
   throws IOException, FormatException
   {
      formatWriter(text, obj, null, writer);  
   }//--------------------------------------------   
   /**
 * @see nyla.solutions.global.patterns.decorator.style.Styles#formatWriter(java.lang.String, java.lang.Object, java.lang.String, java.io.Writer)
 */
   public void formatWriter( String text, Object aBindMap, String dateFormat, Writer writer) 
   throws IOException, FormatException
   {
      StringReader reader = new StringReader(text);

      freemarker.template.Template template = new freemarker.template.Template("System", 
    		  reader,
    		  new Configuration(Configuration.VERSION_2_3_21));
      
      //TODO: freemarker.template.Configuration configuration = template.getConfiguration();
      //TODO: configuration.setObjectWrapper(new freemarker.template.DefaultObjectWrapper());
      
      if(dateFormat == null)
      {
         template.setDateTimeFormat(Config.getProperty("system.dateFormat","MM/dd/yyyy"));   
      }      
  
      try
      {
         template.process(aBindMap, writer);
      }
      catch (freemarker.template.TemplateException e)
      {
         throw new FormatException(Debugger.stackTrace(e));
      }
   }//--------------------------------------------
   /**
 * @see nyla.solutions.global.patterns.decorator.style.Styles#formatWriterFromTemplateName(java.lang.String, java.lang.Object, java.io.Writer)
 */
public void formatWriterFromTemplateName( String templateName, Object aBindMap, Writer writer) 
   throws IOException, FormatException
   {
      
      formatWriter(new File(Config.getProperty(Text.TEMPLATE_DIR_PROP_NM)), templateName,aBindMap, null, writer);
   }//--------------------------------------------
   /**
 * @see nyla.solutions.global.patterns.decorator.style.Styles#formatWriter(java.io.File, java.lang.String, java.lang.Object, java.lang.String, java.io.Writer)
 */
public void formatWriter( File templateDir, String templateName, Object aBindMap, String dateFormat, Writer writer) 
   throws IOException, FormatException
   {      
    

      freemarker.template.Configuration cfg = new freemarker.template.Configuration(Configuration.VERSION_2_3_21);
      cfg.setDirectoryForTemplateLoading(templateDir);
            
      //cfg.setClassForTemplateLoading(HTMLDecorator.class, "/org/solutions/form/formatter/html/");
      //TODO: cfg.setObjectWrapper(new freemarker.template.DefaultObjectWrapper());
      freemarker.template.Template template = cfg.getTemplate(templateName);
      
      
      if(dateFormat == null)
      {
         template.setDateTimeFormat(Config.getProperty("system.dateFormat","MM/dd/yyyy"));   
      }      
  
      try
      {
         template.process(aBindMap, writer);
      }
      catch (freemarker.template.TemplateException e)
      {
         throw new FormatException(Debugger.stackTrace(e));
      }
   }//--------------------------------------------
   
   //private static final String TEMPLATE_PREFIX = "${";
   //private static final String TEMPLATE_SUFFIX = "}";

}
