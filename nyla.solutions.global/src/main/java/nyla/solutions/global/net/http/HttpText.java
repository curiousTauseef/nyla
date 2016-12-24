package nyla.solutions.global.net.http;

import java.util.HashMap;
import java.util.Map;

import nyla.solutions.core.data.Textable;
import nyla.solutions.core.exception.SystemException;
import nyla.solutions.core.util.Config;
import nyla.solutions.core.util.Debugger;




/**
 * Text implementation to retrieve HTTP text results
 * @author Gregory Green
 *
 */
public class HttpText implements Textable
{
	/**
	 * The text results from an HTML GET or POST
	 */
	public String getText()
	{
		try 
		{
			HTTP http = new HttpUnit();
			
			if("POST".equalsIgnoreCase(method))
			{
				return http.post(this.url, parameters).toString();
			}
			else
			{
				return  http.get(url, parameters).toString();
				//return http.get(url);
			}
		} 
		catch (Exception e) 
		{
			throw new SystemException(Debugger.stackTrace(e));
		}
	}//---------------------------------------------
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
	 * @return the parameters
	 */
	public Map<Object, Object> getParameters()
	{
		return parameters;
	}

	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(Map<Object, Object> parameters)
	{
		this.parameters = parameters;
	}

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

	private String url = Config.getProperty(this.getClass(),"url");
	private Map<Object, Object> parameters = new HashMap<Object, Object>();
	private String method = "GET";

}
