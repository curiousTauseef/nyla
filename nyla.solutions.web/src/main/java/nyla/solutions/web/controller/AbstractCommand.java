package nyla.solutions.web.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import nyla.solutions.core.exception.SystemException;
import nyla.solutions.core.security.user.data.User;
import nyla.solutions.core.util.Debugger;
import nyla.solutions.web.View;
import nyla.solutions.web.Web;


/**
 * @author green_gregory
 * @version 1.0
 *
 * <b>AbstractCommand</b> abstract command for common command functions 
 * 
 */
public abstract class AbstractCommand implements WebCommand, ControllerConstants
{
   /**
    * 
    * @param aRequest the HTTP
    * @return the relative theme path
    * @throws Exception
    */
   protected String getThemePath(HttpServletRequest aRequest) throws Exception
   {
      return this.getView(aRequest).getRelativeThemePath();
   } //--------------------------------------------------------
   /**
    * 
    * @param aRequest return the user information
    * @return the user information
    */
   protected User getUser(HttpServletRequest aRequest)
      throws SecurityException, SystemException
   {
      javax.servlet.http.HttpSession session = aRequest.getSession();
      User u = (User) session.getAttribute(USER);
      if (u == null)
      {
         //init session with user and view
         u = getView(aRequest).getUser();
      }
      return u;
   } //------------------------------------
   /**
    * 
    * @param request initialize session with user and view
    * @return View object
    */
   protected View getView(HttpServletRequest request)
      throws SystemException, SecurityException
   {
      //get session attributes
      javax.servlet.http.HttpSession session = request.getSession();
      View view = (View) session.getAttribute(VIEW);
      if (view == null || view.getUser() == null)
      {
         view = createView(request, session);
         this.setView(view, request);
      }
      
      
      
      return view;
   } //--------------------------------------------------------
   /**
    * 
    * @param request the HTTP request
    * @param session the HTTP session
    * @return
    * @throws SecurityException 
    */
   private View createView(HttpServletRequest request, HttpSession session)
      throws SecurityException, SystemException
   {
      try
      {
         //Set view
         View view = new View();
         view.setContextPath(request.getContextPath());
         view.setUser(Web.getInstance().getUser(request));
         //set session attributes
         session.setAttribute(USER, view.getUser());
         session.setAttribute(VIEW, view);
         return view;
      }
      catch (Exception e)
      {
         Debugger.printError(e);
         throw new SecurityException(e.toString());
      }
   } //-----------------------------------
   /**
    * set the view in the session
    */
   private void setView(View aView, HttpServletRequest aRequest)
   {
     //get session attributes
      HttpSession session = aRequest.getSession();
      session.setAttribute(VIEW,aView);
   }//----------------------------------------------------------

    /**
     * 
     * @param aValueListHandler the value list handler
     * @param aRequest the HTTP list
     */
   //protected void setValueListAttributes(ValueListHandler<?> aValueListHandler, HttpServletRequest aRequest)
   //   {
  //       aRequest.setAttribute("isDone",new Boolean(aValueListHandler.isDone()));
   //      aRequest.setAttribute("hasPrevious",new Boolean(aValueListHandler.hasPrevious()));
      
  //    }//---------------------------------------------------------
 
   /**
    * Set the popup message
    * @param aMessage the message 
    * @param aRequest the HTTP request object
    */
   protected void setPopupMessage(String aMessage, HttpServletRequest aRequest)
   {
      aRequest.setAttribute(POPUP_MSG_TAG,aMessage);
      
   }//--------------------------------------------------------------------
   /**
    * 
    * @param request the request
    * @return Dispatcher.nextView(request)
    */
   protected String nextView(HttpServletRequest request)
   {
      return Dispatcher.nextView(request);
   }

}