package nyla.solutions.web.test;

import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.http.*;

/**
 * <pre>
 * TestHttpSession contains UNIT Test cases.
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class TestHttpSession implements HttpSession
{

   /**
    * Constructor for TestHttpSession initializes internal 
    * data settings.
    * 
    */
   public TestHttpSession()
   {
      super();
      // TODO Auto-generated constructor stub
   }

   /**
    * 
    * @see javax.servlet.http.HttpSession#getCreationTime()
    */
   public long getCreationTime()
   {
      // TODO Auto-generated method stub
      return 0;
      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.http.HttpSession#getId()
    */
   public String getId()
   {
      // TODO Auto-generated method stub
      return null;
      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.http.HttpSession#getLastAccessedTime()
    */
   public long getLastAccessedTime()
   {
      // TODO Auto-generated method stub
      return 0;
      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.http.HttpSession#getServletContext()
    */
   public ServletContext getServletContext()
   {
      // TODO Auto-generated method stub
      return null;
      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.http.HttpSession#setMaxInactiveInterval(int)
    */
   public void setMaxInactiveInterval(int arg0)
   {
      // TODO Auto-generated method stub

      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.http.HttpSession#getMaxInactiveInterval()
    */
   public int getMaxInactiveInterval()
   {
      // TODO Auto-generated method stub
      return 0;
      //----------------------------------------
   }

   /**
    * @deprecated
    * @see javax.servlet.http.HttpSession#getSessionContext()
    */
   public HttpSessionContext getSessionContext()
   {
      // TODO Auto-generated method stub
      return null;
      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.http.HttpSession#getAttribute(java.lang.String)
    */
   public Object getAttribute(String key)
   {
      return this.attributeMap.get(key);
   }//--------------------------------------------

   /**
    * 
    * @see javax.servlet.http.HttpSession#getValue(java.lang.String)
    */
   public Object getValue(String arg0)
   {
      // TODO Auto-generated method stub
      return null;
      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.http.HttpSession#getAttributeNames()
    */
   public Enumeration<String> getAttributeNames()
   {
      // TODO Auto-generated method stub
      return null;
      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.http.HttpSession#getValueNames()
    */
   public String[] getValueNames()
   {
      // TODO Auto-generated method stub
      return null;
      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.http.HttpSession#setAttribute(java.lang.String, java.lang.Object)
    */
   public void setAttribute(String key, Object value)
   {
      attributeMap.put(key,value);
   }//--------------------------------------------

   /**
    * 
    * @see javax.servlet.http.HttpSession#putValue(java.lang.String, java.lang.Object)
    */
   public void putValue(String arg0, Object arg1)
   {
      // TODO Auto-generated method stub

      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.http.HttpSession#removeAttribute(java.lang.String)
    */
   public void removeAttribute(String arg0)
   {
      // TODO Auto-generated method stub

      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.http.HttpSession#removeValue(java.lang.String)
    */
   public void removeValue(String arg0)
   {
      // TODO Auto-generated method stub

      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.http.HttpSession#invalidate()
    */
   public void invalidate()
   {
      // TODO Auto-generated method stub

      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.http.HttpSession#isNew()
    */
   public boolean isNew()
   {
      // TODO Auto-generated method stub
      return false;
      //----------------------------------------
   }
   private Map<String,Object> attributeMap = new Hashtable<String,Object>();
}
