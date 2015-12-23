package nyla.solutions.global.web.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Text;

/**
* <b>Dispatche</b> handles HTTP forward and redirects
* for the web application processing.
* @author Gregory Green
* @version 1.0
*/

public class Dispatcher
{
   /**
    * ERROR_PAGE = Config.getProperty(Dispatcher.class.getName()+".ERROR_PAGE")
    */
   public static final String ERROR_PAGE = Config.getProperty(Dispatcher.class.getName()+".ERROR_PAGE");
   
   /**
    * HOME_PAGE = Config.getProperty(Dispatcher.class.getName()+".HOME_PAGE")
    */
   public static final String HOME_PAGE = Config.getProperty(Dispatcher.class.getName()+".HOME_PAGE");
   
   /**
    * CURRENT_VIEW_PARAM_NAME = "cv"
    */
   public static final String CURRENT_VIEW_PARAM_NAME = "cv";
   
   /**
    * NEXT_VIEW_PARAM_NAME = "nv"
    */
   public static final String NEXT_VIEW_PARAM_NAME = "nv";
   
   /**
    * MSG_TAG_PARAM_NAME = "msg"
    */
   public static final String MSG_TAG_PARAM_NAME = "msg";
   
   
   public static final String ERROR_STACKTRACE_PARAM = "stackTrace";
   
   /**
    * forwardExceptions to the "ERROR_PAGE" 
    * @param aServlet the servlet in which the error occurred
    * @param aRequest the HTTP request object
    * @param aResponse the HTTP response object
    * @param aMsg  the Exception Message
    */
   public static void forwardException(HttpServlet aServlet,
                             HttpServletRequest aRequest, 
                             HttpServletResponse aResponse,
                             Exception aMsg)
   {
      try
      {
         if(aServlet == null || aRequest == null || aResponse == null )
            return;


         String exceptionName = aMsg.getClass().getName(); 

         Debugger.println("Exception Name="+exceptionName);

         String exceptionPage = aRequest.getParameter(exceptionName);

         if(Text.isNull(exceptionPage))
         {

            exceptionPage = toErrorPage(aRequest, aMsg);

         }
 
         aRequest.setAttribute(MSG_TAG_PARAM_NAME, aMsg);

         aRequest.setAttribute(ERROR_STACKTRACE_PARAM, Debugger.stackTrace(aMsg));
         
         aResponse.setContentType("text/html");
         aResponse.setStatus(500);
         forward(aServlet, aRequest, aResponse,exceptionPage);

      }
      catch(Exception e)
      {
         Debugger.printError(Dispatcher.class,e);
      }
      Debugger.printError(aServlet,aMsg);
   }//---------------------------------------------------------------------
   /**
    * forward to the Specified command 
    * @param aRequest the HTTP request object
    * @param aResponse the HTTP response object
    * @param aMsg  the Exception Message
    */   
   public static String forwardCommand(
                                HttpServletRequest aRequest, 
                                HttpServletResponse aResponse,
                                Class<?> aCommandClass)
   throws Exception
   {

         //Create command
         WebCommand cmd = WebCommandFactory.createCommand(aCommandClass.getName());
      
         return cmd.execute(aRequest, aResponse);
      
   }//-----------------------------------------------------
   /**
    * forward to the Specified page 
    * @param aServlet the servlet in which the error occurred
    * @param aRequest the HTTP request object
    * @param aResponse the HTTP response object
    * @param aMsg  the Exception Message
    */
   public static void forward(HttpServlet aServlet,
                             HttpServletRequest aRequest, 
                             HttpServletResponse aResponse,
                             String aPage)
   {
      Debugger.println(Dispatcher.class,"forwarding to "+aPage);
      try
      {
         if(aServlet != null && aRequest != null && aResponse != null )
         {

            aResponse.setContentType("text/html");

            if(aPage != null && aPage.startsWith("http"))
            {
               Debugger.println(Dispatcher.class,"Redirecting to page "+aPage);
               aResponse.sendRedirect(aPage);
            }
            else
            {
   
               RequestDispatcher aDispatcher = aServlet.getServletContext().getRequestDispatcher(
                                                 aPage);
               aDispatcher.forward(aRequest, aResponse);
            }
         }
      }
      catch(Exception e)
      {
         Debugger.printError(Dispatcher.class,e);
      }
   }//---------------------------------------------------------------------
   /**
    * Determine next view to display 
    * @param aRequest the HTTP request object
    * @returns the URL of the page to display
    */
    public static String nextView(HttpServletRequest aRequest )
    {
       String nv = aRequest.getParameter(NEXT_VIEW_PARAM_NAME);
       
       if(nv == null || nv.length() ==0)
          return HOME_PAGE;       

       return nv;
    }//----------------------------------------------------
    /**
     * 
     * @param aRequest the HTTP request
     * @return "themes/default/"+nextView(aRequest)
     */
    public static String nextThemeView(HttpServletRequest aRequest)
    {
       return "themes/default/"+nextView(aRequest);
    }//----------------------------------------
   
   /**
    * Determine next view to display 
    * @param aRequest the HTTP request object
    * @returns the URL of the page to display
    */
    public static String currentView(HttpServletRequest aRequest )
    {
       String cv = aRequest.getParameter(CURRENT_VIEW_PARAM_NAME);

       if(cv == null || cv.length() ==0)
          return HOME_PAGE;
       

       return cv;
    }//----------------------------------------------------
    /**
     * @return error page for the exception
     */
    private static String toErrorPage(HttpServletRequest aRequest, Exception e) 
    {
        if(e instanceof SecurityException)
           return "/themes/default/errors/unauthorized.jsp";
        else
           return ERROR_PAGE;  
         
    }//----------------------------------------------------

    
}