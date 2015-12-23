package nyla.solutions.office.msoffice.excel;

import java.io.File;
import java.io.IOException;


import nyla.solutions.global.data.Textable;
import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.io.IO;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Text;

/**
 * 
 * <b>CSV</b> is a object to create a CSV file 
 * @author Gregory Green
 *
 */
public class CSV implements Textable
{

   public String getText()
   {
      return null;
   }// --------------------------------------------
   /**
    * 
    * @param file
    * @param objects
    */
   public static void appendFile(java.io.File file, Object[] objects)
   throws IOException
   {
      if(file == null)
         throw new RequiredException("file in CSV.appendFile");
      
      String filePath = file.getAbsolutePath();      
      
      
      IO.writeAppend(filePath, canonlized.toRow(objects));
   }// --------------------------------------------
   /**
    * 
    * @param objects
    * @return CSV format version of the objects values
    */
   public  String toRow(Object [] objects)
   {
      if (objects == null)
         throw new RequiredException("objects in CSV.toRow");
      
      StringBuilder text = new StringBuilder();
      
      for (int i = 0; i < objects.length; i++)
      {
         if(i!= 0)
            text.append(separator);
         
         text.append("\"").append(format(objects[i])).append("\"");
         
      }
      return text.toString()+IO.newline();
   }// --------------------------------------------
   /**
    * 
    * @param object the text version of the object will be formatted
    * @return text where the separator is replaced with replacement character
    */
   private String format(Object object)
   {
      String text = Text.toString(object);
      return Text.replace("\"", "\"\"", text); //TODO: need to check "
      //return text.replaceAll(this.separator, this.replacement);
   }// --------------------------------------------

   public static void writeHeader(File file, String header)
   throws IOException
   {
	   if(!file.exists() && header != null)
		{
			IO.writeFile(file, Text.appendNewLine(header));
		}
   }// --------------------------------------------------------
   /**
    * 
    *
    * @see java.lang.Comparable#compareTo(java.lang.Object)
    */
   public int compareTo(Object arg0)
   {
      return 0;
   }// --------------------------------------------   
   /**
    * @return the separator
    */
   public String getSeparator()
   {
      return separator;
   }// --------------------------------------------

   /**
    * @param separator the separator to set
    */
   public void setSeparator(String separator)
   {
      this.separator = separator;
   }// --------------------------------------------
   /**
    * @return the replacement
    */
   public String getReplacement()
   {
      return replacement;
   }//--------------------------------------------
   /**
    * @param replacement the replacement to set
    */
   public void setReplacement(String replacement)
   {
      this.replacement = replacement;
   }//--------------------------------------------
   private static CSV canonlized = new CSV();
   private String replacement = Config.getProperty(this.getClass().getName()+".replacement","");
   private String separator = Config.getProperty(this.getClass().getName()+".separator", ",");
}
