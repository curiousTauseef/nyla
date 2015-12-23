package nyla.solutions.global.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import nyla.solutions.global.security.user.data.User;
import nyla.solutions.global.security.user.data.UserProfile;


public class CoreWeb extends Web
{

   /**
    * 
    *@return request.getSession()
    * @see nyla.solutions.global.web.Web#getSession(javax.servlet.http.HttpServletRequest)
    */
   public HttpSession getSession(HttpServletRequest request)
   {      
      return request.getSession();
   }//--------------------------------------------

   public User getUser(HttpServletRequest request)
   {
      UserProfile user = new UserProfile();
      
      user.setLoginID(request.getRemoteUser());      
      return user;
   }//--------------------------------------------

}
