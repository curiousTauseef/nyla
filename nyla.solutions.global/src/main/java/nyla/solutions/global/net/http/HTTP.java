
package nyla.solutions.global.net.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.TransformerException;

import nyla.solutions.core.exception.SystemException;
import nyla.solutions.core.exception.TimeOutException;
import nyla.solutions.core.io.IO;
import nyla.solutions.core.patterns.Disposable;
import nyla.solutions.core.util.Config;
import nyla.solutions.core.util.Debugger;
import nyla.solutions.core.util.Text;






/**
 * 
 * <pre>
 * HTTP provides a set of functions to the communicate via HTTP.
 * makes object wraps HTTPUnit open source API
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */

public abstract class HTTP implements Disposable
{  
   public static final String XML_CONTENT_TYPE = "text/xml";
   
   /**
    * absoluteUrlRegExp = Config.getProperty(HTTP.class,"absoluteUrlRegExp",".+\\:\\/\\/")
    */
   public static final String absoluteUrlRegExp = Config.getProperty(HTTP.class,"absoluteUrlRegExp",".+://.+");
   
   
   /**
    * 
    * @param aUrl the UTL
    * @return Perform an HTTP get
    */
   public abstract HTTPResult get(String aUrl)
   throws IOException;
   
   /**
    * 
    * @param path the path to text
    * @return Text.matchesRE(absoluteUrlRegExp, path)
    */
   public static boolean isAbsoluteURL(String path)
   {
	   return  path.matches(absoluteUrlRegExp);
   }// --------------------------------------------
   /**
    * 
    * @param parentURL
    * @param link
    * @return
    * @throws MalformedURLException
    */
	public static URL toURL(URL parentURL, String link)
	throws MalformedURLException
	{
		if(HTTP.isAbsoluteURL(link))
			return new URL(link);
		else
		{
			String parentPath = parentURL.toString();
			if(!parentPath.endsWith("/") && ! link.startsWith("/"))
				parentPath = parentPath+ "/";
			
			return new URL(parentPath+link); //
		}
	}// --------------------------------------------
   
	public static boolean isHtmlContentType(String contentType)
	{
		return contentType != null && contentType.toLowerCase().indexOf("html") > 0;
	}// --------------------------------------------
   /**
    * Create HTTP object with conversations.
    * 
    * Default timeout is 60 seconds
    * 
    * Note: Must be destroyed
    * Constructor for HTTP initializes internal 
    * data settings.
    */
   public HTTP()
   {
   }// --------------------------------------------
   
   /**
    * Encode the text
    * @param aText the text to encode
    * @return the UTF encoded value
    */
   public static String encode(String aText)
   {
      try
      {
         return URLEncoder.encode(aText,"UTF-8");
      }
      catch (UnsupportedEncodingException e)
      {
         throw new SystemException(aText);
      }
   }// --------------------------------------------
   /**
    * Decode the text URL value
    * @param aText the text to encode
    * @return the UTF encoded value
    */
   public static String decode(String aText)
   {
      try
      {
         return URLDecoder.decode(aText,"UTF-8");
      }
      catch (UnsupportedEncodingException e)
      {
         throw new SystemException(aText);
      }
   }// --------------------------------------------
   /**
    * Call addHear for each entry in map
    * @param headers the header name/value pairs
    */
   public void addHeaders(Map<Object, Object> headers)
   {
      if(headers == null || headers.isEmpty())
      {
         return; //nothing to add
      }
      
      for (Map.Entry<Object, Object> entry : headers.entrySet()) 
      {      
         this.addHeader((String)entry.getKey(), Text.toString(entry.getValue()));
      }      
   }//--------------------------------------------
   /**
    * 
    * @param aUrl the UTL
    * @return Perform an HTTP get
    */
   public abstract HTTPResult get(String aUrl, Map<Object, Object> aParameters)
   throws IOException;
  
   /**
    * 
    * @param aURL the URL to POST data to
    * @param aRawContent the RAW post content
    * @return HTTP CODE/Content
    */
   public HTTPResult post(String aURL, String aRawContent)
   throws IOException
   {
      return post(aURL,aRawContent,"application/x-www-form-urlencoded");
      
   }// --------------------------------------------
   /**
    * 
    * @param aURL the URL to POST data to
    * @param aRawContent the RAW post content
    * @return HTTP CODE/Content
    */
   public HTTPResult post(String aURL, Map<Object, Object> aRawContent)
   throws IOException
   {
      
      return post(aURL,toQuery(aRawContent),"text");
      
   }// --------------------------------------------
   /**
    * 
    * @param RAW HTTP Query response
    * @return the HTTP Query
    */
   public static String retrieveQuery(String request)
   {
	   if(request == null || request.length() == 1)
		   return request;
	   
       int qId = request.indexOf('?');
       
       if (qId  > -1)
       {
    	   return request.substring(qId + 1).trim();
       }

       return request;	   
   }// --------------------------------------------
   /**
    * Convert the HTTP raw request to a map object
    * @param rawRequest the HTTP raw request
    * @return the map representation of the raw request
    * @throws TransformerException
    */
   public static Map<Object, Object> toMap(String request)
   {
	   
	   Debugger.println(HTTP.class, "request="+request);
	   
	   HashMap<Object, Object> map = new HashMap<Object, Object>();
	   
	   map.put("request",request);
	   
	   if(request == null || request.length() == 0)
		   return map;
	   
	   String requestQuery = retrieveQuery(request);
	   
	   //Decode query URL
	   requestQuery = HTTP.decode(requestQuery);
	   
	   Debugger.println(HTTP.class, "requestQuery="+requestQuery);
	   
       
       //sample test=dfd&t2=2
       
       String[] nameValues = Text.split(requestQuery, "&");
       
       String[] nvPair = null;
       
       for(int i=0 ; i < nameValues.length; i++)
       {
    	   nvPair = Text.split(nameValues[i], "=");
    	   	if(nvPair == null || nvPair.length == 0)
    	   		continue;
    	   	
    	   	
    		if(nvPair.length > 1)
    		{
    			map.put(nvPair[0], nvPair[1]);	
    		}
    		else
    			map.put(nvPair[0], "");
    		    	   
       }
       
       Debugger.println(HTTP.class,"return "+map);
       return map;
   }// --------------------------------------------
   /**
    * The query for the given map values
    * @param map the map data
    * @return the HTTP query
    */
   public static String toQuery(Map<Object, Object> map)
   {
      if(map == null || map.isEmpty())
         return "";
      
      StringBuffer query = new StringBuffer();
      
      for(Map.Entry<Object, Object> entry : map.entrySet())
      {
         query.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
         
      }
      
      return query.toString();
   }// --------------------------------------------

   /**
    * Make a HTTP post with raw content
    * @param aURL the URL to POST data to
    * @param aRawContent the RAW post content
    * @return HTTP CODE/Content
    */
   public abstract HTTPResult post(String aURL, String aRawContent, String aContentType)
   throws IOException;
   

   
   /**
    * Make the HTTP request
    * @param HTTPConnURL the URL
    * @param QueryStr the query text
    * @param TimeoutSecs the time out in seconds
    * @return the HTTP results
    * @throws Exception
    */
   public  HTTPResult invoke(String HTTPConnURL,
                              String QueryStr,
                              int    TimeoutSecs)
                       throws IOException
   {
      return (invoke(HTTPConnURL, QueryStr, TimeoutSecs,
                     false, null, null));
   }// --------------------------------------------
   /**
    * Make the HTTP request
    * @param HTTPConnURL the URL
    * @param QueryStr the query text
    * @param TimeoutSecs the time out in seconds
    * @param SecurityEnabled
    * @param UserName the user
    * @param UserPassword the user's password
    * @return
    * @throws Exception
    */
   public  HTTPResult invoke(String  HTTPConnURL,
                              String  QueryStr,
                              int     TimeoutSecs,
                              boolean SecurityEnabled,
                              String  UserName,
                              String  UserPassword)
                       throws IOException
   {
      return (invoke(HTTPConnURL, QueryStr, TimeoutSecs,
                     "application/x-www-form-urlencoded", false, null, (String)null));
   }// --------------------------------------------
   /**
    * Make the HTTP request where the expected response is an XML message
    * @param HTTPConnURL
    * @param QueryStr
    * @param TimeoutSecs
    * @return
    * @throws Exception
    */
   public  HTTPResult postXML(String HTTPConnURL,
                                     String QueryStr,
                                     int    TimeoutSecs)
                       throws Exception
   {
      return (postXML(HTTPConnURL, QueryStr, TimeoutSecs,
                            false, null, null));
   }// --------------------------------------------
   /**
    * Make the HTTP request where the expected response is an XML message
    * @param HTTPConnURL
    * @param QueryStr
    * @param TimeoutSecs
    * @param SecurityEnabled indicates whether the username and password should be submitted
    * @return HTTP data and code
    * @throws Exception
    */
   public  HTTPResult postXML(String  HTTPConnURL,
                                     String  QueryStr,
                                     int     TimeoutSecs,
                                     boolean SecurityEnabled,
                                     String  UserName,
                                     String  UserPassword)
                       throws Exception
   {
      return (invoke(HTTPConnURL, QueryStr, TimeoutSecs,
                     "text/xml", SecurityEnabled, UserName, UserPassword));
   }// --------------------------------------------
   /**
    * Make the HTTP request where the expected response is an XML message
    * @param HTTPConnURL
    * @param QueryStr
    * @param TimeoutSecs
    * @param SecurityEnabled indicates whether the username and password should be submitted
    * @return HTTP data and code
    * @throws Exception
    */
   public  HTTPResult postXML(String  aHTTPConnURL,
                                     String  aQueryStr,
                                     int     TimeoutSecs,
                                     Map<String, String> aMap)
   throws IOException   
   {
      return invoke(aHTTPConnURL, aQueryStr, TimeoutSecs,
      "text/xml", false, null, null, aMap);
   }// --------------------------------------------

   /**
    * Make the HTTP request where the expected response is an XML message
    * @param HTTPConnURL
    * @param QueryStr
    * @param TimeoutSecs
    * @param SecurityEnabled indicates whether the username and password should be submitted
    * @return HTTP data and code
    * @throws Exception
    */
   public  HTTPResult postXML(String  HTTPConnURL,
                                     String  QueryStr,
                                     int     TimeoutSecs,
                                     boolean SecurityEnabled,
                                     String  UserName,
                                     String  UserPassword, 
                                     Map<String, String> aMap)
                       throws IOException
   {
      return invoke(HTTPConnURL, QueryStr, TimeoutSecs,
                     "text/xml", SecurityEnabled, UserName, UserPassword, aMap);
   }//-----------------------------------------------
   
   /**
    * 
    * @param HTTPConnURL the URL to connection
    * @param QueryStr the HTTP content to POST
    * @param TimeoutSecs the time in seconds
    * @param ContentType the response content type (such as (text/xml)
    * @param SecurityEnabled use basic authentication
    * @param UserName the user to use with basci authentication
    * @param UserPassword the password
    * @return the HTTP code and data response
    * @throws IOException
    */
   private  HTTPResult invoke(String  HTTPConnURL,
                               String  QueryStr,
                               int     TimeoutSecs,
                               String  ContentType,
                               boolean SecurityEnabled,
                               String  UserName,
                               String  UserPassword)
   throws IOException   
   {
      return invoke(HTTPConnURL,QueryStr, TimeoutSecs, ContentType, SecurityEnabled, UserName,UserPassword, null);
   }// --------------------------------------------

   /**
    * 
    * @param HTTPConnURL the URL to connection
    * @param QueryStr the HTTP content to POST
    * @param TimeoutSecs the time in seconds
    * @param ContentType the response content type (such as (text/xml)
    * @param SecurityEnabled use basic authentication
    * @param UserName the user to use with basci authentication
    * @param UserPassword the password
    * @return the HTTP code and data response
    * @throws IOException
    */
   @SuppressWarnings("restriction")
 protected  HTTPResult invoke(String  HTTPConnURL,
                               String  QueryStr,
                               int     TimeoutSecs,
                               String  ContentType,
                               boolean SecurityEnabled,
                               String  UserName,
                               String  UserPassword,
                               Map<String, String> aMap)
   throws IOException
   {

      URL url = null;
      HttpURLConnection urlConn = null;
      try
      {
         java.security.Security.addProvider(
                       new com.sun.net.ssl.internal.ssl.Provider());

         System.setProperty("java.protocol.handler.pkgs",
                            "com.sun.net.ssl.internal.www.protocol");

         int beg = -1;
         int end = -1;

         if ((beg = (HTTPConnURL.toLowerCase()).indexOf(
                                   "https:")) == -1)
         {
            url = new URL(HTTPConnURL);

            Debugger.println("Opened a HTTP URL connection to (" +
                               HTTPConnURL + ").");
         }
         else
         {
            String HostName   = null;
            int    PortNumber = 443;
            String FilePath   = "/";

            end = HTTPConnURL.indexOf("/",
                              beg + "https".length());

            if (end == -1)
            {
               end = HTTPConnURL.length();
            }
            else
            {
               try
               {
                  FilePath = HTTPConnURL.substring(end,
                                                   HTTPConnURL.length());
               }
               catch (StringIndexOutOfBoundsException e)
               {
                  throw new Exception( "Invalid URL - (" + HTTPConnURL + ").");
               }
            }

            String FullURL = null;

            try
            {
               FullURL = HTTPConnURL.substring(
                                     beg + "https".length(),
                                     end);
            }
            catch (StringIndexOutOfBoundsException e)
            {
               throw (new Exception(
                                    "Invalid URL - (" + HTTPConnURL + ")."));
            }

            if ((beg = FullURL.indexOf(":")) == -1)
            {
               HostName = FullURL;
            }
            else
            {
               try
               {
                  HostName = FullURL.substring(0, beg);
               }
               catch (StringIndexOutOfBoundsException e)
               {
                  throw (new Exception(
                                       "Invalid URL - (" + HTTPConnURL + ")."));
               }

               //TODO
               /*try
               {
                  PortNumber = (Integer.valueOf(FullURL.substring(beg + 1,
                                            FullURL.length()))).intValue();
               }
               catch (Exception e)
               {
                  throw (new Exception(
                                       "PortNumber:"+PortNumber+" Invalid URL  - (" + HTTPConnURL + ")."));
               }
               */
            }


             url = new URL("https", HostName, PortNumber, FilePath);

            Debugger.println("Opened a HTTPS URL connection to (" +
                               "https"+ HostName + ":" +
                               PortNumber + FilePath + ").");
         }
      }
      catch (Exception e)
      {
         throw new IOException(
                              "Could not open URL to " + HTTPConnURL +
                              " - [" + Debugger.stackTrace(e) + "].");
      }

      try
      {
         urlConn = (HttpURLConnection) url.openConnection();
      }
      catch (Exception e)
      {
         throw (new SystemException(
                              "Could not open HTTP URL connection to " +
                              HTTPConnURL + " - (" + e.getMessage() + ")."));
      }

      urlConn.setDoInput(true);
      urlConn.setDoOutput(true);
      urlConn.setUseCaches(false);
      urlConn.setRequestProperty("Content-Type", ContentType);

      if (SecurityEnabled)
      {
         urlConn.setRequestProperty("Authorization", "Basic " + base64Encode(
                                                     UserName + ":" +
                                                     UserPassword));
      }
      
      if(aMap != null && !aMap.isEmpty())
      {
         for (Map.Entry<String, String> entry: aMap.entrySet())
         {
           
            urlConn.setRequestProperty(entry.getKey(), entry.getValue());   
         }
      }
      
      DataOutputStream printout = null;


      printout = new DataOutputStream(urlConn.getOutputStream());

      printout.write(QueryStr.getBytes(IO.CHARSET));
      printout.flush();

      printout.close();


      TimeoutThread TheTimer = new TimeoutThread(urlConn);

      TheTimer.start();

      try
      {
         Debugger.println( "Timer started and waits for " +
                                         TimeoutSecs + " second(s).");

         TheTimer.join(TimeoutSecs * 1000);

         Debugger.println( "Timer terminated.");
      }
      catch (InterruptedException e)
      {
         Debugger.println("Has not waited for " +
                                         TimeoutSecs + " second(s), " +
                                         "but timer was interrupted.");
      }

      try
      {
         TheTimer.interrupt();
      }
      catch (SecurityException e)
      {
      }

      if (RetData == null)
      {
         throw (new TimeOutException(
                              "Timeout when waiting for the responses from " +
                              "HTTP URL connection - (" +
                              HTTPConnURL + ")."));
      }

      return (RetData);
   }// --------------------------------------------
   /**
    * Perform BASE 64 encoding
    * @param string the text to encode
    * @return the encoded value
    */
   protected String base64Encode(String string)
   {
      StringBuilder encodedString = new StringBuilder();

      byte bytes [] = string.getBytes(IO.CHARSET);

      int i   = 0;
      int pad = 0;

      while (i < bytes.length)
      {
         byte b1 = bytes[i ++];
         byte b2;
         byte b3;

         if (i >= bytes.length)
         {
            b2  = 0;
            b3  = 0;
            pad = 2;
         }
         else
         {
            b2 = bytes[i ++];

            if (i >= bytes.length)
            {
               b3  = 0;
               pad = 1;
            }
            else
            {
               b3 = bytes[i ++];
            }
         }

         byte c1 = (byte) (b1 >> 2);
         byte c2 = (byte) ((b1 & 3) << 4 | b2 >> 4);
         byte c3 = (byte) ((b2 & 0xf) << 2 | b3 >> 6);
         byte c4 = (byte) (b3 & 0x3f);

         encodedString.append(base64Array[c1]);
         encodedString.append(base64Array[c2]);

         if(pad ==0)
         {
                 encodedString.append(base64Array[c3]);
                 encodedString.append(base64Array[c4]);
         }
         else if(pad == 1)
         {
                 encodedString.append(base64Array[c3]);
                 encodedString.append(encodedString).append("=");
                 break;
         }
         else if(pad == 2)
         {
                 encodedString.append("==");       
         }
      }

      return encodedString.toString();
   }// --------------------------------------------

   /**
    * 
    * <pre>
    * TimeoutThread used to timeout HTTP request
    * </pre> 
    * @author Gregory Green
    * @version 1.0
    * @deprecated
    */
   class TimeoutThread extends Thread
   {
      private HttpURLConnection urlConn = null;

      public TimeoutThread(HttpURLConnection urlConn)
      {
         this.urlConn = urlConn;

         return;
      }
	public void run()
      {
         String ModuleName = "[TimeoutThread::run] ";

         BufferedReader input = null;

         try
         {
            input = new BufferedReader(new InputStreamReader(
                                                urlConn.getInputStream(),IO.CHARSET));

            StringBuilder returnStr = new StringBuilder();

            String line = null;

            while ((line = input.readLine()) != null)
            {
               if (interrupted())
               {
                  Debugger.println(ModuleName +
                                     "Received timeout interruption, aborted.");

                  try
                  {
                     input.close();
                  }
                  catch (Exception e)
                  {
                  }

                  return;
               }

               returnStr.append(line);
            }
            try
            {
               input.close();
            }
            catch (Exception e)
            {
            }

            int response_code = HttpURLConnection.HTTP_OK;

            try
            {
               response_code = urlConn.getResponseCode();
            }
            catch(Exception e)
            {
            }

            RetData = new HTTPResult(response_code, returnStr.toString());
         }
         catch (Exception e)
         {
            try
            {
            	if(input != null)
            		input.close();
            }
            catch (Exception e1)
            {
            	e1.printStackTrace();
            }

            int    response_code = 0;
            String response_msg  = null;

            try
            {
               response_code = urlConn.getResponseCode();
               response_msg  = urlConn.getResponseMessage();
            }
            catch(Exception e1)
            {
            	e1.printStackTrace();
            }

            BufferedReader errInput = null;

            try
            {
               errInput = new BufferedReader(new InputStreamReader(
                                                 urlConn.getErrorStream(),IO.CHARSET));

               StringBuilder retStr = new StringBuilder();

               String line = null;

               while ((line = errInput.readLine()) != null)
               {
            	   retStr.append(line);
               }

               try
               {
                  errInput.close();
               }
               catch (Exception e1)
               {
               }

               response_msg = retStr.toString();
            }
            catch(Exception e1)
            {
               try
               {
            	   if(errInput != null)
            		   errInput.close();
               }
               catch (Exception e2)
               {
            	   e1.printStackTrace();
               }
            }

            if (response_code == 0 || response_msg == null)
            {
               response_msg = e.getMessage();
            }

            RetData = new HTTPResult(response_code, response_msg);
         }

         return;
      }
   }//--------------------------------------------

   public static void main(String [] args)
   {
      if(args.length != 2)
      {
         System.out.println("Usage url file");
         System.exit(1);
      }
      try
      {
      
          System.out.println(new HttpUnit().postXML(args[0], IO.readFile(args[1]).trim(), 40));
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }//-------------------------------------------------
   /**
    * 
    * @return the password
    */
   public final char[] getPassword()
   {
	   if(password == null)
		   return null;
	   
      return password.clone();
   }// --------------------------------------------
   /**
    * 
    * @param aPassword the password
    */
   public final void setPassword(String aPassword)
   {
      if (aPassword == null)
         aPassword = "";
      
      this.setPassword(aPassword.toCharArray());
   }// --------------------------------------------
   /**
    * 
    * @param password the password
    */
   public final void setPassword(char[] password)
   {   
	   if(password == null)
	   {
		   this.password = null;
		   this.needsAuthorization = false;
	   }
	   else
	   {
		   this.password = password.clone();
		   
		   if(this.password.length > 0 )
		         this.needsAuthorization = true;
	   }
   }// --------------------------------------------
   /**
    * 
    * @return the username
    */
   public final String getUsername()
   {
      return username;
   }// --------------------------------------------
   /**
    * 
    * @return map of cookies from the current conversation
    */
   public abstract Map<Object, Object> getCookies();
   
   /**
    * Adds a header to HTTP results
    * @param aName the header name
    * @param aValue the header value
    */
   public abstract void addHeader(String aName, String aValue);   
   

   /**
    * 
    * Add cookies to HTTP conversation
    */
   public abstract void addCookie(String aName, String aValue);
   
   /**
    * 
    * @param aCookies the cookies map
    */
   public void addCookies(Map<String, String> aCookies)
   {
      if(aCookies == null || aCookies.isEmpty())
         return; //nothing to add
      

      for (Map.Entry<String, String> entry: aCookies.entrySet())
      {
       
         this.addCookie(entry.getKey(),entry.getValue());
      }
   }// --------------------------------------------
   /**
    * 
    * @param username set the user name
    */
   public final void setUsername(String username)
   {
      if (username == null)
         username = "";
   
      this.username = username;
      
      //conversation needs authorization
      if(this.username.length() > 0 )
         this.needsAuthorization = true;
      else
         this.needsAuthorization = false;
   }// --------------------------------------------
   /**
    * 
    * @param aProperty the property
    * @param aValue the value 
    */
   public abstract void  setAcceptGzip(boolean aAcceptZIP);   
   
   private static  final char base64Array [] =
   {
      'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
      'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
      'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
      'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
   };
   /**
    * 
    * @param aPostXML do POST XML
    */
   protected boolean isPostXML(boolean aPostXML, String aContentType)
   {
      return aContentType != null  && usePostXML && XML_CONTENT_TYPE.equalsIgnoreCase(aContentType.trim());
   }// --------------------------------------------
   
   /**
    * @return the realm
    */
   public String getRealm()
   {
      return realm;
   }
   /**
    * @param realm the realm to set
    */
   public void setRealm(String realm)
   {
      this.realm = realm;
   }
   
   /**
 * @return the authorized
 */
public boolean isAuthorized()
{
	return authorized;
}

/**
 * @param authorized the authorized to set
 */
public void setAuthorized(boolean authorized)
{
	this.authorized = authorized;
}

/**
 * @return the needsAuthorization
 */
public boolean isNeedsAuthorization()
{
	return needsAuthorization;
}

/**
 * @param needsAuthorization the needsAuthorization to set
 */
public void setNeedsAuthorization(boolean needsAuthorization)
{
	this.needsAuthorization = needsAuthorization;
}

private boolean usePostXML = Config.getPropertyBoolean(this.getClass().getName()+".usePostXML",false).booleanValue();
   private boolean authorized  = false;
   private boolean needsAuthorization = false;
   //private int timeout = Config.getPropertyInteger(this.getClass().getName()+".timeout",60).intValue();
   private String realm = null;
   private String username = null;
   private char [] password = null;
   private HTTPResult RetData = null;
   //private Category logger = Debugger.getCategory(getClass());
}

