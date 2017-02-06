package nyla.solutions.web.controller;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nyla.solutions.core.exception.RequiredException;
import nyla.solutions.core.exception.SetupException;
import nyla.solutions.core.operations.ClassPath;
import nyla.solutions.core.util.Debugger;
import nyla.solutions.core.util.Text;

/**
  <b>CommandFactory</b> for creating Command implementation related objects
  @Author: Gregory Green
  @Businessedge solutions
  @version 1.0
 */
public class WebCommandFactory
{
   /**
    This method creates an instance of Command based on the
    the a class name
    @param aCommand the command name
   */
   public static WebCommand createCommand(HttpServletRequest aReq)
      throws Exception
   {
      String cmdAsAttr = (String) aReq.getAttribute(WebCommand.COMMAND_PARAM_NAME);
      String cmd = aReq.getParameter(WebCommand.COMMAND_PARAM_NAME);
      if (!Text.isNull(cmdAsAttr))
      {
         return createCommand(cmdAsAttr);
      }
      else if (!Text.isNull(cmd))
      {
            return createCommand(cmd);
      }
      else
      {
         return null;
      }
   }//---------------------------------------------------------
   /**
    * This method creates an instance of Command based on the
    * the a class name
    * @param aCommand the command class
    */
   public static WebCommand createCommand(Class<?>  aClass) throws Exception
   {
      return createCommand(aClass.getName());
   }//-----------------------------------------------------------
   /**
    * 
    * @param aRequest the request 
    * @param aResponse the response
    * @param aClass the command
    * @return the result for command.execute
    * @throws Exception
    */
   public static String execute(Class<?> aClass, HttpServletRequest aRequest, 
                                HttpServletResponse aResponse)
   throws Exception
   {
      return createCommand(aClass).execute(aRequest, aResponse);
   }//-------------------------------------------------------
   /**
    This method creates an instance of Command based on the
    the a class name
    @param commandName the command name
   */
   public static WebCommand createCommand(String commandName)
   {

	   if (commandName == null)
		   throw new RequiredException("commandName");
      
      Debugger.println("Command=>" + commandName);
      WebCommand objCommand = commandMap.get(commandName);
      
      if(objCommand != null)
    	  return objCommand;
      
         Debugger.println("Creating class for " + commandName);
         
         objCommand = (WebCommand)ClassPath.newInstance(commandName);
       
     
      return objCommand;
   } //-----------------------------------------
   private static HashMap<String,WebCommand> commandMap = new HashMap<String,WebCommand>();
}