package nyla.solutions.global.io;


import java.io.File;
import java.io.IOException;



import nyla.solutions.global.data.Textable;
import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.io.Fileable;
import nyla.solutions.global.io.IO;
import nyla.solutions.global.patterns.command.Environment;
import nyla.solutions.global.patterns.command.Executable;
import nyla.solutions.global.util.Debugger;


/**
 * 	<bean id="solutions.global.patterns.command.MainExecutable" class="solutions.global.io.WriteTextFileExecutable">	
		<property name="text">
				<ref bean="reportCHEMCARTText"/>
		</property>	
		<property name="path" value="C:/runtime/logs/monitoring/user_status.csv"/>
	</bean>
 * @author Gregory Green
 *
 */
public class WriteTextFileExecutable implements Fileable, Executable
{
   /**
    * @return new File(this.path)
    */
   public File getFile()
   {
	return new File(this.path);
   }// ----------------------------------------------
   /**
    * Write file context with Textable.getText results
    */
   public Integer execute(Environment env)
   {
	if (this.text == null)
	   throw new RequiredException("this.text");
	
	File file = this.getFile();
		
	try
	{
	   IO.writeFile(file.getAbsolutePath(), text.getText(),this.append);
	   
	   return 0;
	} 
	catch (IOException e)
	{
	  throw new SystemException(Debugger.stackTrace(e));
	}
   }// ----------------------------------------------
   
   /**
    * @return the append
    */
   public boolean isAppend()
   {
      return append;
   }
   /**
    * @param append the append to set
    */
   public void setAppend(boolean append)
   {
      this.append = append;
   }
   /**
    * @return the path
    */
   public String getPath()
   {
      return path;
   }
   /**
    * @param path the path to set
    */
   public void setPath(String path)
   {
      this.path = path;
   }
   /**
    * @return the text
    */
   public Textable getText()
   {
      return text;
   }
   /**
    * @param text the text to set
    */
   public void setText(Textable text)
   {
      this.text = text;
   }

   private boolean append = true;
   private String path = "./"+this.getClass().getName();
   private Textable text = null;
}
