/**
 * FrontControllerServlet.java 
 * @author Gregory Green
 * @version 1.0
 */
package nyla.solutions.global.web.controller;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.*;
import javax.servlet.*;
import nyla.solutions.global.util.Debugger;

/**
  <b>FrontControllerServlet</b> is the Front Controller for all
  
  
  http://localhost:port/contoler?cmd=classPath

  <web-app id="WebApp_1">
	<display-name>frontController</display-name>
	<description>frontController</description>	
  	<servlet>
		<servlet-name>frontController</servlet-name>
		<description>The Front Controller for the Application</description>
		<servlet-class>nyla.solutions.global.web.controller.FrontControllerServlet</servlet-class>
		<init-param>
			<param-name>cmd</param-name>
			<param-value>nyla.solutions.global.web.commas.CommasWebBridgeCommand</param-value>
		</init-param>
	</servlet>
      <!-- Servlet Mapping -->
	<servlet-mapping>
		<servlet-name>frontController</servlet-name>
		<url-pattern>/controller/*</url-pattern>
	</servlet-mapping>
	
</web-app>
	
  * web service request.
  * @author: Gregory Green
  * @version 1.0
 */
public class FrontControllerServlet extends HttpServlet
{
   /**
    * 
    */
   private static final long serialVersionUID = 8076718765805808729L;

   /** 
    * Initializes the servlet. 
    * @param config the configuration settings
    */
   public void init(ServletConfig config) throws ServletException
   {
      super.init(config);
      String defaultCommandName = config.getInitParameter(WebCommand.COMMAND_PARAM_NAME);
      
      Debugger.println(
         this,
         "Creating default command " + defaultCommandName);
      
      
      this.defaultCommand = WebCommandFactory.createCommand(defaultCommandName);
      
      
   } //--------------------------------------------------------
   /** Destroys the servlet. */
   public void destroy()
   {
   }//---------------------------------------------------
   /** Processes requests for both HTTP
    * <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
   protected void processRequest(
      HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, java.io.IOException
   {
      String page;
      try
      {
         Debugger.println(this, "processRequest()");
         
         //Use the command Factory
         WebCommand cmd = WebCommandFactory.createCommand(request);
         
         if( cmd == null)
         {
            Debugger.println(this,"Using default command "+defaultCommand);
            cmd = defaultCommand;
         }
         
            
         // Command helper perform custom operation
         page = cmd.execute(request, response);
         
         if (page != null)
         {
            // dispatch control to view
            Dispatcher.forward(this, request, response, page);
         }
      }

      catch (Exception e)
      {
         Debugger.printError(e);
         Dispatcher.forwardException(this, request, response, e);
         return;
      }
   }//---------------------------------------------------
   /** Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
   protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, java.io.IOException
   {
      processRequest(request, response);
   }//---------------------------------------------------
   /** Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
   protected void doPost(
      HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, java.io.IOException
   {
      processRequest(request, response);
   }//---------------------------------------------------
   /** Returns a short description of the servlet 
    */
   public String getServletInfo()
   {
      return "Front Controller";
   }//---------------------------------------------------

   private WebCommand defaultCommand = new nyla.solutions.global.web.commas.CommasWebBridgeCommand();
}
