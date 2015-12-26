package nyla.solutions.blueprint.jee.web;


/**
 * <b>Web</b> web related utility class
 * @author Gregory Green
 */

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import nyla.solutions.global.util.Text;

public class Web
{
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
    * 
    * @param request the 
    * @return Servlet context
    */
   public static ServletContext getApplication(HttpServletRequest request)
	{
		HttpSession session = request.getSession();		
			
		ServletContext application = session.getServletContext();
		return application;
	}//---------------------------------------------
    /**
     * Convert request parameters to a MAp
     */
    public static Map<?,?> toMap(HttpServletRequest aRequest)
    {
      Enumeration<String> paramEnum = aRequest.getParameterNames();
      Hashtable<String,Object> map = new Hashtable<String,Object>();
      String param = null;
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
    /**
     * 
     * @param aValues the param value
     * @return 
     */
    private static String toParameterValue(String[] aValues)
    {
       StringBuffer value = new StringBuffer();
       for (int i = 0; i < aValues.length; i++)
       {
          if(Text.isNull(aValues[i]))
             continue;

          value.append(aValues[i]);
          if(i +  1 <  aValues.length)
             value.append(";");
       }
       return value.toString();
    }//--------------------------------------------
   /**
    * Set request parameters to attributes
    */
   public static void setAttributesFromParameters(HttpServletRequest aRequest)
   {
     Enumeration<String> paramEnum = aRequest.getParameterNames();
     String param = null;
     while(paramEnum.hasMoreElements())
     {
       param =  String.valueOf(paramEnum.nextElement());
       //Debugger.println(Helper.class,"param="+param);
       aRequest.setAttribute(param,aRequest.getParameter(param));
     }
   }//----------------------------------------------------

   static final long serialVersionUID = 2;
}
