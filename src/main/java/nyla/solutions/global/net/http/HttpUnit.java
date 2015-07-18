
package nyla.solutions.global.net.http;

import java.io.*;
import java.util.*;

import nyla.solutions.global.exception.CommunicationException;
import nyla.solutions.global.io.IO;
import nyla.solutions.global.patterns.Disposable;
import nyla.solutions.global.util.*;


/**
 * 
 * <pre>
 * HTTP provides a set of functions to the communicate via HTTP.
 * makes object wraps HTTPUnit open source API
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */

public class HttpUnit extends HTTP implements Disposable
{     
  
   /**
    * Create HTTP object with conversations.
    * 
    * Default timeout is 60 seconds
    * 
    * Note: Must be destroyed
    * Constructor for HTTP initializes internal 
    * data settings.
    */
   public HttpUnit()
   {
      this.conversation = new com.meterware.httpunit.WebConversation();
      this.conversation.clearContents();
   }// --------------------------------------------
   /**
    * 
    * @param aUrl the UTL
    * @return Perform an HTTP get
    */
   public HTTPResult get(String aUrl)
   throws IOException
   {
      return get(aUrl,null);
   }// --------------------------------------------
   
   /**
    * 
    * @param aUrl the UTL
    * @return Perform an HTTP get
    */
   public HTTPResult get(String aUrl, Map<Object, Object> aParameters)
   throws IOException
   {
      try
      {
    	  com.meterware.httpunit.WebRequest     req = new com.meterware.httpunit.GetMethodWebRequest( aUrl );
         
         if(aParameters != null)
            buildParameters(req, aParameters);
         
         com.meterware.httpunit.WebResponse   resp = getConversation().getResponse( req );                  
         
         HTTPResult result = HTTPResult.create(resp);
         
         //Map cookies = result.getCookies();                 
         //this.addCookies(cookies);
         
         return result;
         
      }
      catch(Exception e)
      {
         throw new IOException(Debugger.stackTrace(e));
      }
   }// --------------------------------------------
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
       
       if (qId  != -1)
       {
    	   return request.substring(qId + 1).trim();
       }

       return request;	   
   }// --------------------------------------------
   /**
    * The map 
    * @param rawRequest
    * @return the map representation of the raw request
    * @throws TransformerException
    */
   public static Map<Object, Object> toMap(String rawRequest)
   {
	   String requestQuery = retrieveQuery(rawRequest);
	   
	   HashMap<Object, Object> map = new HashMap<Object, Object>();
	   
	   if(requestQuery == null || requestQuery.length() == 0)
		   return map;
	   
       
       //sample test=dfd&t2=2
       
       String[] nameValues = Text.split(rawRequest, "&");
       
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
   public HTTPResult post(String aURL, String aRawContent, String aContentType)
   throws IOException
   {
      //return this.invoke(aURL, aRawContent, this.timeout, contentType, !Text.isNull(this.username), this.username, password);
      
      try
      {
         //java.lang.String urlString, java.io.InputStream source, java.lang.String contentType)
         
    	  com.meterware.httpunit.PostMethodWebRequest     req = new com.meterware.httpunit.PostMethodWebRequest( aURL,Text.toInputStream(aRawContent), aContentType );
         
    	  com.meterware.httpunit.WebResponse   resp = getConversation().getResource(req);                  
         
         HTTPResult result = HTTPResult.create(resp);
         return result;
         
      }
      catch(com.meterware.httpunit.HttpInternalErrorException e)
      {         
         Debugger.printError(e.getResponseMessage()+ " "+e.getLocalizedMessage()+ " " );
         Debugger.printError(e);
         throw new CommunicationException(e.getResponseMessage());
      }
      catch(java.net.ConnectException e)
      {
         Debugger.printError(e);
         throw new CommunicationException(aURL,e);
      }
   }// --------------------------------------------

   /**
    * Call  aRequest.setParameter on each entry in map
    * @param aRequest the web response
    * @param aParameters the name/value pair parameters
    */
   private void buildParameters(com.meterware.httpunit.WebRequest aRequest, Map<Object, Object> aParameters)
   {
      Object name = null;
      Object value = null;
      
      String[] values = null;
      for (Map.Entry<Object, Object> entry : aParameters.entrySet())
      {
         name = entry.getKey();
         value = entry.getValue();
         if(value instanceof String[])
         {
            values = (String[])value;
            
            aRequest.setParameter(String.valueOf(name), values);
         }
         else
         {
            if(value ==null)
               value = "";
            
            aRequest.setParameter(String.valueOf(name), value.toString());
         }
      }
      
   }// --------------------------------------------

   /**
    * 
    * @return internal instance of Web Conversation
    */
   private com.meterware.httpunit.WebConversation getConversation()
   {
      if( !authorized && needsAuthorization )
      {
         //this.conversation.setAuthorization(this.username,new String(this.password));
       
         //this.conversation.setAuthorization(userName, password)
         this.conversation.setAuthentication(this.realm, this.getUsername(), new String(this.getPassword()));
         System.out.println("authorized="+authorized);
         
         authorized = true;
      }
      
      return this.conversation;
   }// --------------------------------------------
   
   
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
                                     Map<String, String> map)
   throws IOException   
   {
      return invoke(aHTTPConnURL, aQueryStr, TimeoutSecs,
      "text/xml", false, null, null, map);
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
    * @return map of cookies from the current conversation
    */
   public Map<Object, Object> getCookies()
   {
	   com.meterware.httpunit.WebConversation wc = this.getConversation();
      String[] cookieNames = wc.getCookieNames();
      
      
      if(cookieNames ==null || cookieNames.length == 0)
         return null;
      
      String cookieName = null;
      Map<Object, Object> cookies = new HashMap<Object, Object>();
      
      for (int i = 0; i < cookieNames.length; i++)
      {
         cookieName = cookieNames[i];
         cookies.put(cookieName, wc.getCookieValue(cookieName));
      }
      
      return cookies;
   }// --------------------------------------------
   /**
    * Adds a header to HTTP results
    * @param aName the header name
    * @param aValue the header value
    */
   public void addHeader(String aName, String aValue)
   {
      conversation.setHeaderField(aName, aValue);
   }// --------------------------------------------

   /**
    * 
    * Add cookies to HTTP conversation
    */
   public void addCookie(String aName, String aValue)
   {
      conversation.putCookie(aName, aValue);
   }// --------------------------------------------
   /**
    * 
    * @param aCookies the cookies map
    */
   @Override
   public void addCookies(Map<String, String> aCookies)
   {
      if(aCookies == null || aCookies.isEmpty())
         return; //nothing to add
      

      for (Map.Entry<String, String> entry : aCookies.entrySet())
      {       
         this.addCookie(entry.getKey(),entry.getValue());
      }
   }// --------------------------------------------
   
   /**
    * 
    * @param aProperty the property
    * @param aValue the value 
    */
   public void setAcceptGzip(boolean aAcceptZIP)
   {
      this.getConversation().getClientProperties().setAcceptGzip(aAcceptZIP);
   }// --------------------------------------------
   /**
    * 
    * @param aPostXML do POST XML
    */
   protected boolean isPostXML(boolean aPostXML, String aContentType)
   {
      return aContentType != null  && usePostXML && XML_CONTENT_TYPE.equalsIgnoreCase(aContentType.trim());
   }// --------------------------------------------
   /**
    * Clear the HTTP contents
    *
    * @see nyla.solutions.global.patterns.Disposable#dispose()
    */
   public void dispose()
   {
      try
      {
         conversation.clearContents();     
      }
      catch(Exception e)
      {
         Debugger.printWarn(e);
      }
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
   private boolean usePostXML = Config.getPropertyBoolean(this.getClass().getName()+".usePostXML",false).booleanValue();
   private boolean authorized  = false;
   private boolean needsAuthorization = false;
   //private int timeout = Config.getPropertyInteger(this.getClass().getName()+".timeout",60).intValue();
   private String realm = null;
   private com.meterware.httpunit.WebConversation conversation = null;   
   //private Category logger = Debugger.getCategory(getClass());
}

