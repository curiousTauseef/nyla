package nyla.solutions.web;

import nyla.solutions.core.data.Data;
import nyla.solutions.core.security.user.data.User;
import nyla.solutions.core.util.Config;

/**
 * @author Gregory Green
 * 
 * @version 1.0
 *
 * <b>View</b> view presentation object
 * 
 */
public class View extends Data
{
   /**
    * Comment for <code>serialVersionUID</code>
    */
   private static final long serialVersionUID = 1L;
   /**
    * @return the user's skin name
    */
   public String getSkin()
   {
         return skin;
   } //----------------------------------
   /**
    * @return the user's theme name
    */
   public String getTheme()
   {
         return theme;
   } //----------------------------------
   /**
    * @return user profile
    */
   public User getUser()
   {
      return user;
   }//--------------------------------------------
   /**
    * @param user the user profile
    */
   public void setUser(User user)
   {
      this.user = user;
   } //-------------------------------------
   /**
    * 
    * @return the user last  and first name
    */
   public String getUserLastFirstName()
   {
      if (user == null)
         return "";
      StringBuffer sb = new StringBuffer();
      sb.append(user.getLastName());
      if (sb.length() > 0)
      {
         sb.append(", ").append(user.getFirstName()).append(" ");
      }
      return sb.toString();
   } //----------------------------------------
   /**
    * 
    * @param aRole the Get Access Role Name
    * @return true if the user has the role
    * @throws IllegalStateException if the user roles are nto set or user not set
    * @throws IllegalArgumentException when role not provided
    */
   //public boolean hasRole(String aRole)
   //{
   //   if(isNull(aRole) )
   //      throw new IllegalArgumentException("Role not provided");
         
   //   if(user == null)
   //      throw new IllegalStateException("User not set on view");
       
   //   return  user.hasRole(aRole);         
   //}//--------------------------------------------
   /**
    * 
    * @return the user last  and first name
    */
   public String getUserFirstLastName()
   {
      if (user == null)
         return "";
      StringBuffer sb = new StringBuffer();
      sb.append(user.getFirstName()).append(" ").append(
         user.getLastName());
      return sb.toString();
   } //----------------------------------------   
   /**
    * @return the context Path
    */
   public String getContextPath()
   {
      return contextPath;
   }//---------------------------------
   /**
    * @param string the context path
    */
   public void setContextPath(String string)
   {
      contextPath = string;
   } //------------------------------
   /**
    * @return the skin path
    */
   public String getSkinPath()
   {
      if(skinPath == null)
      {
         skinPath = new StringBuffer(contextPath)
                                     .append("/skins/")
                                     .append(getSkin())
                                     .toString();
      }
      
      
      return skinPath;
   }//----------------------------------
   /**
    * @return the skin path without context path
    */
   public String getRelativeSkinPath()
   {
      if(skinPath == null)
      {
         skinPath = new StringBuffer("/skins/")
                                 .append(getSkin())
                                     .toString();
      }
      
      
      return skinPath;
   }//----------------------------------
   /**
    * 
    * @return the css path
    */   
   public String getCssPath()
   {
      return getSkinPath()+"/css";
   }//-------------------------------------
   /**
    * 
    * @return the image path
    */   
   public String getImagePath()
   {
      return getSkinPath()+"/images";
   }//-------------------------------------   
   /**
    * @return the theme path without context path
    */
   public String getRelativeThemePath()
   {
      if(themePath == null)
      {
         themePath = new StringBuffer("/themes/")
                                      .append(getTheme())
                                      .toString();
      }
            
      return themePath;
   }//----------------------------------      
   /**
    * @return the them path
    */
   public String getThemePath()
   {
      if(themePath == null)
      {
         themePath = new StringBuffer(contextPath).append("/themes/")
                                      .append(getTheme())
                                      .toString();
      }
            
      return themePath;
   }//----------------------------------   
   /**
    * @return  isReadOnly()
    */
   public boolean getReadOnly()
   {
      return isReadOnly();
   }//---------------------------------
   /**
    * @return  readOnly boolean
    */
   public boolean isReadOnly()
   {
      return readOnly;
   }//---------------------------------   
   /**
    * Set readOnly boolean
    */
   public void setReadOnly(boolean aReadOnly)
   {
      readOnly = aReadOnly;
   }//----------------------------------
   
   /**
    * @return viewer first name
    */
   public String getViewerFirstName()
   {
      return viewerFirstName;
   }//----------------------------------------

   /**
    * @return  viewer last name
    */
   public String getViewerLastName()
   {
      return viewerLastName;
   }//----------------------------------------

   /**
    * @param string  viewer first name
    */
   public void setViewerFirstName(String string)
   {
      viewerFirstName = string;
   }//----------------------------------------

   /**
    * @param string  viewer last name
    */
   public void setViewerLastName(String string)
   {
      viewerLastName = string;
   }//----------------------------------------
   /**
    * 
    * @return Config.getProperty(View.class.getName()+".contextPath")
    */
   public static String getDefaultContextPath()
   {
      return Config.getProperty(View.class.getName()+".contextPath");
   }//--------------------------------------------
   
   private boolean readOnly = false;
   private String themePath = null;
   private String skinPath  = null;
   private String theme = "default";
   private String skin  = "default";
   private String contextPath = getDefaultContextPath();
   private User user = null;
   private String viewerFirstName = "";
   private String viewerLastName = "";
}
