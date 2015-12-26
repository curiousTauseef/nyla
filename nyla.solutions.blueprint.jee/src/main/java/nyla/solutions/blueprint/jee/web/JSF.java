package nyla.solutions.blueprint.jee.web;

import javax.faces.application.Application;
import javax.faces.context.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
/**
 * <pre>
 * JSF Java Server Faces wrapper 
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class JSF
{
   /**
    * 
    * @return FacesContext.getCurrentInstance().getApplication()
    */
   public static Application getApplication()
   {
      return FacesContext.getCurrentInstance().getApplication();
   }//--------------------------------------------
   /**
    * 
    * @return (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()
    */
   public static HttpServletRequest getRequest()
   {
      return (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
   }//--------------------------------------------
   /**
    * 
    * @return (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse()
    */
   public static HttpServletResponse getResponse()
   {
      return (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
   }//--------------------------------------------
   /**
    * 
    * @param aEL the expression language
    * 
    * Replace by getExpressionFactory createValueBinding
    * @return ValueBinding for expression
    */
   /*public static ValueBinding createValueBinding(String aEL)
   {
      return getApplication().createValueBinding(aEL);
   }
   public static void setValueBinding(String aEL, String aAttributeKey, UIComponent aComponent)
   {
      aComponent.setValueBinding(aAttributeKey,createValueBinding(aEL));
   }
   */
   //--------------------------------------------
   /**
    * 
    * @param aPropertyName the property name
    * @return getRequestParameterMap().get(aPropertyName) or null
    */
   public static String getRequestParameter(Object aPropertyName)
   {
      Object p = getRequestParameterMap().get(aPropertyName);
            
      if(p == null)
         return null;
      
      
      
      return p.toString();
   }//--------------------------------------------
   /**
    * 
    * @return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
    */
   public static Map<String, String> getRequestParameterMap()
   {
      return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
   }//--------------------------------------------
   /**
    * 
    * @return FacesContext.getCurrentInstance().getExternalContext().getApplicationMap()
    */
   public static Map<String, Object> getApplicationMap()
   {
      return FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
   }//--------------------------------------------
   /**
    * 
    * @return FacesContext.getCurrentInstance().getExternalContext().getRemoteUser()
    */
   public static String getRemoteUser()
   {
      return FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
   }   
   /**
    * 
    * @return FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap()
    */
   public static Map<String,Object> getRequestCookieMap()
   {
      return FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap();
   }//--------------------------------------------
   /**
    * 
    * @return FacesContext.getCurrentInstance().getExternalContext().getRequestHeaderMap()
    */
   public static Map<String, String> getRequestHeaderMap()
   {
      return FacesContext.getCurrentInstance().getExternalContext().getRequestHeaderMap();
   }//--------------------------------------------
   /**
    * 
    * @returnFacesContext.getCurrentInstance().getExternalContext().getRequestMap()
    */
   public static Map<String,Object> getRequestMap()
   {
      return FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
   }//--------------------------------------------
   /**
    * 
    * @return FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
    */
   public static Map<String,Object> getSessionMap()
   {
      return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
   }//--------------------------------------------   
}

