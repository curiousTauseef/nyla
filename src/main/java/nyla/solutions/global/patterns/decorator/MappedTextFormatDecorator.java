package nyla.solutions.global.patterns.decorator;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import nyla.solutions.global.data.Mapped;
import nyla.solutions.global.data.Textable;
import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.io.IO;
import nyla.solutions.global.patterns.decorator.MappedTextFormatDecorator;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Text;




/**
 * This object using a FreeMarker. The place holder values are obtained from 
 * text decorators. The Decorator can be set by ServiceFactory object.
 * 
 * @author Gregory Green
 *
 */
public class MappedTextFormatDecorator implements Mapped<Object,Object>, Textable
{
   /**
    * 
    *
    * @see nyla.solutions.global.data.Mapped#getMap()
    */
   public Map<Object,Object> getMap()
   {      
      return map;
   }//--------------------------------------------
   /**
    * 
    *asset all values are Textable
    * @see nyla.solutions.global.data.Mapped#setMap(java.util.Map)
    */
   @Override
   public void setMap(Map<Object,Object> map)
   {
      if(map == null)
         throw new RequiredException("map in MappedTextFormatDecorator.setMap");
      
      //asset all values are Textable
      
      for(Iterator<Object> i = map.values().iterator();i.hasNext(); )
      {
         if(!(i.next() instanceof Textable))
        	 throw new RequiredException("Textable in map values "+map);
      }
      
      //set map
      this.map = map;

   }//--------------------------------------------
   /**
    * 
    * @return the freemarker template
    * @throws IOException
    */
   private String getTemplate()
   throws IOException
   {
	   if(this.template != null && this.template.length() > 0)
		   return template;
	   
	    if(templateUrl == null || templateUrl.length() == 0)         
	          throw new RequiredException("templateUrl in MappedTextFormatDecorator.getText");
	    
	    
	    return IO.readURL(templateUrl);
   }//---------------------------------------------
   /**
    * Convert get text output from each Textable in map.
    * Return the format output using Text.format.
    * 
    * Note the bind template is retrieved from
    * the URL provided in templateUrl.
    *
    * @see nyla.solutions.global.data.Textable#getText()
    */
   public String getText()
   {
      
     //convert textable to map of text
      
      try 
      {
    	  //read bindTemplate
          String bindTemplate = getTemplate();
         Map<Object,String> textMap = new Hashtable<Object,String>();
         Object key = null;
         for(Map.Entry<Object, Object> entry: map.entrySet())
         {
            key = entry.getKey();
            //convert to text
            textMap.put(key,((Textable)entry.getValue()).getText());         
         }
         
         Debugger.println(this, "bindTemplate="+bindTemplate);
         String formattedOutput = Text.format(bindTemplate, textMap);
         Debugger.println(this, "formattedOutput="+formattedOutput);
         return formattedOutput;
         
      } 
      catch (Exception e) 
      {
         throw new SystemException(Debugger.stackTrace(e));
      }
   }//--------------------------------------------   
   /**
    * @return the templateUrl
    */
   public String getTemplateUrl()
   {
      return templateUrl;
   }//--------------------------------------------
   /**
    * @param templateUrl the templateUrl to set
    */
   public void setTemplateUrl(String templateUrl)
   {
      if (templateUrl == null || templateUrl.length() == 0)
         throw new IllegalArgumentException(
         "templateUrl required in setTemplateUrl");
      
      this.templateUrl = templateUrl;
   }//--------------------------------------------
   
   /**
	 * @param template the template to set
	 */
	public void setTemplate(String template)
	{
		this.template = template;
	}

	private String templateUrl = Config.getProperty(MappedTextFormatDecorator.class.getName()+".templateUrl","");
   private Map<Object,Object> map = new Hashtable<Object,Object>();
   private String template = Config.getProperty(MappedTextFormatDecorator.class.getName()+".template","");
}
