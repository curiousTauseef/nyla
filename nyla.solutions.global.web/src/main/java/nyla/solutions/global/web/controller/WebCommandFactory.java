package nyla.solutions.global.web.controller;
import java.util.HashMap;

import javax.servlet.http.*;

import nyla.solutions.global.exception.SetupException;
import nyla.solutions.global.operations.ClassPath;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Text;
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

      
      Debugger.println("Command=>" + commandName);
      if (commandName == null)
         throw new SetupException(" method not found in cmd:" + commandName);
      
      WebCommand objCommand = commandMap.get(commandName);
      
      if(objCommand != null)
    	  return objCommand;
      
         Debugger.println("Creating class for " + commandName);
         
         objCommand = (WebCommand)ClassPath.newInstance(commandName);
       
     
      return objCommand;
   } //-----------------------------------------
   private static HashMap<String,WebCommand> commandMap = new HashMap<String,WebCommand>();
}