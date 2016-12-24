
package nyla.solutions.global.net.http;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.meterware.httpunit.WebResponse;

import nyla.solutions.core.util.Debugger;

public class HTTPResult implements Serializable
{
    public static final int SC_OK = 200;
    public static final int SC_SWITCHING_PROTOCOLS = 101;
    public static final int SC_CREATED = 201;
   public static final int SC_ACCEPTED = 202;
   public static final int SC_NON_AUTHORITATIVE_INFORMATION = 203;
   public static final int SC_NO_CONTENT = 204;
   public static final int SC_RESET_CONTENT = 205;
   public static final int SC_PARTIAL_CONTENT = 206;
   public static final int SC_MULTIPLE_CHOICES = 300;
   public static final int SC_MOVED_PERMANENTLY = 301;
   public static final int SC_MOVED_TEMPORARILY = 302;
   public static final int SC_SEE_OTHER = 303;
   public static final int SC_NOT_MODIFIED = 304;
   public static final int SC_USE_PROXY = 305;
   public static final int SC_TEMPORARY_REDIRECT = 307;
   public static final int SC_BAD_REQUEST = 400;
   public static final int SC_UNAUTHORIZED = 401;
   public static final int SC_PAYMENT_REQUIRED = 402;
   public static final int SC_FORBIDDEN = 403;
   public static final int SC_NOT_FOUND = 404;
   public static final int SC_METHOD_NOT_ALLOWED = 405;
   public static final int SC_NOT_ACCEPTABLE = 406;
   public static final int SC_PROXY_AUTHENTICATION_REQUIRED = 407;
   public static final int SC_REQUEST_TIMEOUT = 408;
   public static final int SC_CONFLICT = 409;
   public static final int SC_GONE = 410;
   public static final int SC_LENGTH_REQUIRED = 411;
   public static final int SC_PRECONDITION_FAILED = 412;
   public static final int SC_REQUEST_ENTITY_TOO_LARGE = 413;
   public static final int SC_REQUEST_URI_TOO_LONG = 414;
   public static final int SC_UNSUPPORTED_MEDIA_TYPE = 415;
   public static final int SC_REQUESTED_RANGE_NOT_SATISFIABLE = 416;
   public static final int SC_EXPECTATION_FAILED = 417;
   public static final int SC_INTERNAL_SERVER_ERROR = 500;
   public static final int SC_NOT_IMPLEMENTED = 501;
   public static final int SC_BAD_GATEWAY = 502;
   public static final int SC_SERVICE_UNAVAILABLE = 503;
   public static final int SC_GATEWAY_TIMEOUT = 504;
   public static final int SC_HTTP_VERSION_NOT_SUPPORTED = 505;
   
   /**
    * 
    * @param aRequest the web response
    */
   public static HTTPResult create(WebResponse aResponse)
   {
      if (aResponse == null)
         throw new IllegalArgumentException(
         "aResponse required in HTTPResult.create");
      
      try
      {
         HTTPResult result = new HTTPResult();
         
         result.url = aResponse.getURL();
         
        
         result.content = aResponse.getText();
         result.statusCode = aResponse.getResponseCode();
         
         String[] headerNames = aResponse.getHeaderFieldNames();         
         
         //build headers
         if(headerNames != null)
         {
            String headerName = null;
            result.headers = new HashMap<Object, Object>(headerNames.length);
            for (int i = 0; i < headerNames.length; i++)
            {
               headerName = headerNames[i];
               result.headers.put(headerName, aResponse.getHeaderField(headerName));
            }   
         }
         
         //build cookies
         String[] cookieNames = aResponse.getNewCookieNames();         
                  
         if(cookieNames != null)
         {
            String cookieName = null;
            result.cookies = new HashMap<Object, Object>(cookieNames.length);
            for (int i = 0; i < cookieNames.length; i++)
            {
               cookieName = cookieNames[i];
               result.cookies.put(cookieName, aResponse.getNewCookieValue(cookieName));
            }   
         }
         
         return result;
      }
      catch (IOException e)
      {
         throw new RuntimeException(Debugger.stackTrace(e));
      }
   }// --------------------------------------------
   /**
    * 
    * Constructor for HTTPResult initializes internal 
    * data settings.
    * @param statusCode
    * @param content
    */
   public HTTPResult(int statusCode, String content)
   {
      this.statusCode = statusCode;
      this.content = content;
   }// --------------------------------------------
   /**
    * 
    * Constructor for HTTPResult initializes internal 
    * data settings.
    */
   protected HTTPResult()
   {
      return;
   }// --------------------------------------------   
   /**
    * 
    * @param statusCode the status code
    */
   public void setStatusCode(int statusCode)
   {
      this.statusCode = statusCode;

      return;
   }// --------------------------------------------
   /**
    * 
    * @return the HTTP response code
    */
   public int getStatusCode()
   {
      return (statusCode);
   }// --------------------------------------------
   /**
    * 
    * @see java.lang.Object#toString()
    */
   public String toString()
   {
      return (content);
   }// --------------------------------------------
   /**
    * @return Returns the content.
    */
   public final String getContent()
   {
      return content;
   }// --------------------------------------------
   /**
    * @return Returns the URL
    */
   public final URL getUrl()
   {
      return url;
   }// --------------------------------------------
   /**
    * @return Returns the headers.
    */
   public final Map<Object, Object> getHeaders()
   {
      return headers;
   }// --------------------------------------------
   /**
    * 
    * @return
    */
   public boolean isOK()
   {
      return SC_OK ==  statusCode;
   }// --------------------------------------------

   /**
    * @return Returns the cookies.
    */
   protected final Map<Object, Object> getCookies()
   {
      return cookies;
   }// --------------------------------------------
   
   private Map<Object, Object> headers = null;
   private Map<Object, Object> cookies = null;
   private URL url = null;
   static final long serialVersionUID = HTTPResult.class.getName().hashCode();
   private int    statusCode = SC_OK;
   private String content    = "";
  
}
