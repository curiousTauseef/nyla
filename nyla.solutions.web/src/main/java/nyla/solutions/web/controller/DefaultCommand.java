package nyla.solutions.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nyla.solutions.core.security.user.data.User;
import nyla.solutions.core.util.Config;
import nyla.solutions.core.util.Debugger;



/**
 * @author green_gregory
 * @version 1.0
 *
 * <b>DefaultCommand</b> default command processing 
 * 
 */
public class DefaultCommand extends AbstractCommand 
{
   /**
    * Default command processing for user to access thier first view of the system
    * @see com.WebCommand.sa.eo.web.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
    * @param request the HTTP request
    * @param repsonse the HTTP response
    */
   public String execute(
      HttpServletRequest request,
      HttpServletResponse response)
      throws Exception
   {      
      User user = this.getView(request).getUser(); 
     
      Debugger.println("user="+user);
      
      return this.getThemePath(request)+"/"+defaultView;
   }//---------------------------------------------------------------
   
   /**
    * Default index.jsp (can be overwritten)
    * @return the defaultView
    */
   protected String getDefaultView()
   {
      return defaultView;
   }

   /**
    * @param defaultView the defaultView to set
    */
   protected void setDefaultView(String defaultView)
   {
      this.defaultView = defaultView;
   }
   
   private String defaultView = Config.getProperty(getClass().getName()+".defaultView","index.jsp");
}