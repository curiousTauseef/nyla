package nyla.solutions.global.net.http;
import java.net.URL;
import java.util.Map;


/*
 * import java.util.Iterator;
 * import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import solutions.global.util.Debugger;
*/
//import org.apache.http.params.BasicHttpParams;


import nyla.solutions.global.data.Textable;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.util.Config;



/**
 * 
 * Initiates a HTTP call using the Apache HTTP client API
 * @author Gregory Green
 *
 */
public class HttpClientText implements Textable
{
	public HttpClientText()
	{
		
	}// ----------------------------------------------
	public HttpClientText(URL url)
	{
		this.url = url.toString();
	}// --------------------------------------------
	/**
	 * Return the response HTTP text
	 */
	public String getText()
	{
		throw new SystemException("not implemented");
		/*
		if(cache && cacheText != null && cacheText.length() > 0 )
        	return cacheText; //return cached results
		
		HttpClient httpclient = null;
		try 
		{
			httpclient = new DefaultHttpClient();
			
			HttpUriRequest request =null;
			
			if("PUT".equalsIgnoreCase(this.method))
			{
				request = new HttpPut(this.url);
			}
			else if("POST".equalsIgnoreCase(this.method))
			{
				request = new HttpPost(this.url);	
			}
			else
			{
				request = new HttpGet(this.url);
			}
		
			//Build head
			if(this.headerMap != null && !this.headerMap.isEmpty())
			{
			   Object key = null;
			   Object value = null;
			   for(Iterator i = headerMap.keySet().iterator();i.hasNext();)
			   {
				key = i.next();
				
				value = headerMap.get(key);
				if(value != null)
				   request.setHeader(key.toString(), key.toString());
				else
				   request.setHeader(key.toString(), "");
			   }
			}
			
			//build parameters
			if(this.requestParameters != null && !this.requestParameters.isEmpty())
			{
			   Object key = null;
			   Object value = null;
			   //BasicHttpParams params = new BasicHttpParams();
			   HttpParams params = new DefaultHttpParams();
			   for(Iterator i = requestParameters.keySet().iterator();i.hasNext();)
			   {
				key = i.next();
				value = requestParameters.get(key);
				params.setParameter(key.toString(), value);
			   }
			}

			Debugger.println(this,"executing request " + request.getURI());

			// Create a response handler
			ResponseHandler responseHandler = new BasicResponseHandler();
			String responseBody = httpclient.execute(request, responseHandler).toString();
	            
			
			if(cache)
			   cacheText = responseBody;
	        
			return responseBody;
		} 
		catch (Exception e) 
		{
			throw new SystemException(Debugger.stackTrace(e));
		}
		finally
		{
			 // When HttpClient instance is no longer needed, 
	        // shut down the connection manager to ensure
	        // immediate deallocation of all system resources

			if(httpclient != null)
				try {httpclient.getConnectionManager().shutdown();}catch(Exception e){}
		}*/
	}// ----------------------------------------------

	
	/**
	 * @return the method
	 */
	public String getMethod()
	{
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(String method)
	{
		this.method = method;
	}

	/**
	 * @return the url
	 */
	public String getUrl()
	{
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url)
	{
		this.url = url;
	}

	
	/**
	 * @return the cache
	 */
	public boolean isCache()
	{
		return cache;
	}

	/**
	 * @param cache the cache to set
	 */
	public void setCache(boolean cache)
	{
		this.cache = cache;
	}
	
	
	/**
	 * @return the headerMap
	 */
	public Map<Object, Object> getHeaderMap()
	{
	   return headerMap;
	}
	/**
	 * @param headerMap the headerMap to set
	 */
	public void setHeaderMap(Map<Object, Object> headerMap)
	{
	   this.headerMap = headerMap;
	}

	
	/**
	 * @return the requestParameters
	 */
	public Map<Object, Object> getRequestParameters()
	{
	   return requestParameters;
	}
	/**
	 * @param requestParameters the requestParameters to set
	 */
	public void setRequestParameters(Map<Object, Object> requestParameters)
	{
	   this.requestParameters = requestParameters;
	}


	private Map<Object, Object> requestParameters = null;
	private Map<Object, Object> headerMap = null;
	//private String cacheText = null;
	private boolean cache = Config.getPropertyBoolean(getClass(),"cache",false).booleanValue();
	private String method = "GET";
	private String url = Config.getProperty(this.getClass(),"url","");

}
