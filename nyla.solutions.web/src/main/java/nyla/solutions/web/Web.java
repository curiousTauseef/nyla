package nyla.solutions.web;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

/**
 * <b>Web</b> web related utility class
 * @author Gregory Green
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import nyla.solutions.core.io.IO;
import nyla.solutions.core.patterns.servicefactory.ServiceFactory;
import nyla.solutions.core.security.user.data.User;
import nyla.solutions.core.util.Debugger;
import nyla.solutions.core.util.Text;

public abstract class Web
{
	/**
	 * CONTENT_TYPE_HEADER = "Content-Type"
	 */
	public static final String CONTENT_TYPE_HEADER = "Content-Type";
	
	
	
   /**
    * USER_AGENT = "User-Agent"
    */
   public static final String USER_AGENT = "User-Agent";
   
   /**
    * 
    * @return User-Agent HTTP Header
    */
   public static String getUserAgent(HttpServletRequest aRequest)
   {
      return aRequest.getHeader(USER_AGENT);
   }// --------------------------------------------
   
	/**

	 * Returns the request content as a string (payload or query string

	 * 

	 * @param request

	 *            The HttpServletRequest to use

	 * @return payload

	 * @throws IOException 

	 */

	public static String getPayload(HttpServletRequest request) throws IOException
	{
		//support query string
		String text = IO.readText(request.getReader());

		if(text == null || text.length() == 0)
			text = request.getQueryString();		

		return text;
	}// -----------------------------------------------
    /**
     * Convert request parameters to a MAp
     */
    public static Map<?,?> toMap(HttpServletRequest aRequest)
    {
      Enumeration<?> paramEnum = aRequest.getParameterNames();
      Hashtable<Object,Object> map = new Hashtable<Object,Object>();
      Object param = null;
      String[] values = null; 
      while(paramEnum.hasMoreElements())
      {
        param =  paramEnum.nextElement(); 
        
        values = aRequest.getParameterValues((String)param);
        
        if(values != null && values.length > 1)
        {
           map.put(param,toParameterValue(values));
        }
        else
        {
           map.put(param,aRequest.getParameter(param+""));
        }
      }
      return map;
    }//----------------------------------------------------
    private static String toParameterValue(String[] aValues)
    {
       StringBuffer value = new StringBuffer();
       for (int i = 0; i < aValues.length; i++)
       {
          if(Text.isNull(aValues[i]))
             continue;
          
          value.append(aValues[i]);
          if(i +  1 <  aValues.length)
             value.append(WebTags.PARAMETER_VALUES_SEPARATOR);
       }
       return value.toString();
    }//--------------------------------------------
   /**
    * Set request parameters to attributes 
    */
   public static void populateAttributesFromParameters(HttpServletRequest aRequest)
   {
     Enumeration<?> paramEnum = aRequest.getParameterNames();
     String param = null;
     while(paramEnum.hasMoreElements())
     {
       param =  String.valueOf(paramEnum.nextElement()); 
       //Debugger.println(Helper.class,"param="+param);
       aRequest.setAttribute(param,aRequest.getParameter(param));
     }
   }//----------------------------------------------------

   
   /**
    * 
    * @param aSession the HTTP session
    * @return the user data information
    */
   public abstract User getUser(HttpServletRequest aRequest);
   //------------------------------------------------
   
  /**
   * 
   * @param aPageContext
   * @return
   */
   public HttpSession getSession(PageContext aPageContext)
   {
      return getSession((HttpServletRequest)aPageContext.getRequest());
   }//--------------------------------------------
   /**
    * 
    * @return (Web)ServiceFactory.getInstance().create(Web.class)
    */
   public static Web getInstance()
   {
      return getInstance(Web.class);
   }// --------------------------------------------
   
   /**
    * 
    * @return (Web)ServiceFactory.getInstance().create(aClass)
    */
   public static Web getInstance(Class<?> aClass)
   {
      try
      {
         return (Web)ServiceFactory.getInstance().create(aClass);
      }
      catch(Exception e)
      {
         Debugger.printInfo(Web.class, "Web instance not configured returing default istnace");
         return new CoreWeb();
         
      }
   }// --------------------------------------------
   
   /**
    * 
    * @return (Web)ServiceFactory.getInstance().create(aKey)
    */
   public static Web getInstance(String aKey)
   {
      return (Web)ServiceFactory.getInstance().create(aKey);
   }// --------------------------------------------
 /**
  *
  * @param aRequest the HTTP request
  * @return the HTTP session object
  */
   public abstract HttpSession getSession(HttpServletRequest aRequest);
   //------------------------------------------------
   static final long serialVersionUID = 2;
}
