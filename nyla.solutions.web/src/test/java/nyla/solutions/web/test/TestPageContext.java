package nyla.solutions.web.test;

import java.io.IOException;
import java.util.Enumeration;

import javax.el.ELContext;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.el.ExpressionEvaluator;
import javax.servlet.jsp.el.VariableResolver;
//import javax.servlet.jsp.el.ExpressionEvaluator;
//import javax.servlet.jsp.el.VariableResolver;

/**
 * <pre>
 * TestPageContext provides a set of functions to
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
@SuppressWarnings("deprecation")
public class TestPageContext extends PageContext
{

   /**
    * Constructor for TestPageContext initializes internal 
    * data settings.
    * 
    */
   public TestPageContext()
   {
      super();
   }//--------------------------------------------

   /**
    * 
    * @see javax.servlet.jsp.PageContext#initialize(javax.servlet.Servlet, javax.servlet.ServletRequest, javax.servlet.ServletResponse, java.lang.String, boolean, int, boolean)
    */
   public void initialize(Servlet arg0, ServletRequest arg1,
                          ServletResponse arg2, String arg3, boolean arg4,
                          int arg5, boolean arg6) throws IOException, IllegalStateException, IllegalArgumentException
   {
   }//--------------------------------------------
   /**
    * 
    * @see javax.servlet.jsp.PageContext#release()
    */
   public void release()
   {
   }//--------------------------------------------

   /**
    * 
    * @see javax.servlet.jsp.PageContext#setAttribute(java.lang.String, java.lang.Object)
    */
   public void setAttribute(String aName, Object aValue)
   {
   }//--------------------------------------------

   /**
    * 
    * @see javax.servlet.jsp.PageContext#setAttribute(java.lang.String, java.lang.Object, int)
    */
   public void setAttribute(String arg0, Object arg1, int arg2)
   {
   }//--------------------------------------------

   /**
    * 
    * @see javax.servlet.jsp.PageContext#getAttribute(java.lang.String)
    */
   public Object getAttribute(String arg0)
   {
      return null;
      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.jsp.PageContext#getAttribute(java.lang.String, int)
    */
   public Object getAttribute(String arg0, int arg1)
   {
      return null;
   }//--------------------------------------------
   /**
    * 
    * @see javax.servlet.jsp.PageContext#findAttribute(java.lang.String)
    */
   public Object findAttribute(String arg0)
   {
      return null;
   }//--------------------------------------------

   /**
    * 
    * @see javax.servlet.jsp.PageContext#removeAttribute(java.lang.String)
    */
   public void removeAttribute(String arg0)
   {
      // TODO Auto-generated method stub

      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.jsp.PageContext#removeAttribute(java.lang.String, int)
    */
   public void removeAttribute(String arg0, int arg1)
   {
      // TODO Auto-generated method stub

      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.jsp.PageContext#getAttributesScope(java.lang.String)
    */
   public int getAttributesScope(String arg0)
   {
      // TODO Auto-generated method stub
      return 0;
      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.jsp.PageContext#getAttributeNamesInScope(int)
    */
   public Enumeration<String> getAttributeNamesInScope(int arg0)
   {
      return null;
   }//--------------------------------------------
   /**
    * 
    * @see javax.servlet.jsp.PageContext#getOut()
    */
   public JspWriter getOut()
   {
      return jspWriter;
   }//--------------------------------------------

   /**
    * 
    * @see javax.servlet.jsp.PageContext#getSession()
    */
   public HttpSession getSession()
   {
      return session;     
   }//--------------------------------------------

   /**
    * 
    * @see javax.servlet.jsp.PageContext#getPage()
    */
   public Object getPage()
   {
      // TODO Auto-generated method stub
      return null;
      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.jsp.PageContext#getRequest()
    */
   public ServletRequest getRequest()
   {      
      return request;
   }//--------------------------------------------
   /**
    * 
    * @see javax.servlet.jsp.PageContext#getResponse()
    */
   public ServletResponse getResponse()
   {
      // TODO Auto-generated method stub
      return null;
      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.jsp.PageContext#getException()
    */
   public Exception getException()
   {
      // TODO Auto-generated method stub
      return null;
      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.jsp.PageContext#getServletConfig()
    */
   public ServletConfig getServletConfig()
   {
      // TODO Auto-generated method stub
      return null;
      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.jsp.PageContext#getServletContext()
    */
   public ServletContext getServletContext()
   {
      // TODO Auto-generated method stub
      return null;
      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.jsp.PageContext#forward(java.lang.String)
    */
   public void forward(String arg0) throws ServletException, IOException
   {
      // TODO Auto-generated method stub

      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.jsp.PageContext#include(java.lang.String)
    */
   public void include(String arg0) throws ServletException, IOException
   {
      // TODO Auto-generated method stub

      //----------------------------------------
   }
   
   public void include(String arg0, boolean arg1) throws ServletException, IOException
   {
      // TODO Auto-generated method stub

      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.jsp.PageContext#handlePageException(java.lang.Exception)
    */
   public void handlePageException(Exception arg0) throws ServletException, IOException
   {
      // TODO Auto-generated method stub

      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.jsp.PageContext#handlePageException(java.lang.Throwable)
    */
   public void handlePageException(Throwable arg0) throws ServletException, IOException
   {
   }//--------------------------------------------
   /**
    * Calls request.addParameter(aName, aValue)
    * @param aName the parameter to add
    * @param aValue th evalue
    */
   public void addParameter(String aName, String aValue)
   {
      request.addParameter(aName, aValue);
   }//--------------------------------------------
   public TestHttpServletRequest getTestHttpServletRequest()
   {
      return request;
   }//--------------------------------------------
//   public VariableResolver getVariableResolver() {
//   	
//   		return null;
//   }
//   
//   public ExpressionEvaluator getExpressionEvaluator() {
//   	return null;
//   }
   
	
	public ELContext getELContext()
	{
		// TODO Auto-generated method stub
		return null;
	}
	

	public ExpressionEvaluator getExpressionEvaluator()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public VariableResolver getVariableResolver()
	{
		// TODO Auto-generated method stub
		return null;
	}

private TestJspWriter jspWriter = new TestJspWriter();
private TestHttpServletRequest request = new TestHttpServletRequest();
private HttpSession session = request.getSession();
}
