package nyla.solutions.global.patterns.creational.builder.mapped;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import nyla.solutions.core.exception.SystemException;
import nyla.solutions.core.patterns.cache.CacheFarm;
import nyla.solutions.core.patterns.creational.builder.mapped.MappedKeyDirector;
import nyla.solutions.core.patterns.creational.builder.mapped.MappedKeyEngineer;
import nyla.solutions.core.util.Debugger;
import nyla.solutions.core.util.Text;
import nyla.solutions.global.net.http.HTTP;
import nyla.solutions.global.xml.DOM4J;
import nyla.solutions.global.xml.DomJsoupHtmlStragety;

/**
 * Use MappedKeyEngineer to construct output from HTML pages.
 * Each HREF in the HTML will be processed.
 * 
 *  Set the quota and ignorePathRegExp to limit crawled pages.
 * @author Gregory Green
 *
 */
public class WebCrawlerMappedKeyDirector<K,V>  extends MappedKeyDirector<K,V>
{
	/**
	 * Director method to construct a document
	 * @param path the 
	 * @param engineer
	 */
	public void constructDocument(String urlPath, MappedKeyEngineer<K,V> engineer)
	{
		//clear cache
		CacheFarm.getCache().remove(this.documentCacheKey);
		try
		{
			crawl(urlPath, engineer);	
		}
		finally
		{
			CacheFarm.getCache().remove(this.documentCacheKey);
			
			crawledPaths.clear();

		}		
	}// --------------------------------------------
	/**
	 * Director method to construct a document
	 * @param path the 
	 * @param engineer
	 */
	protected void crawl(String urlPath, MappedKeyEngineer<K,V> engineer)
	{
				
		
		Debugger.println(this, "checked ="+urlPath);
		
		if(Text.matches(urlPath, ignorePathRegExp) || crawledPaths.contains(urlPath))
		{
			Debugger.println(this, "skipped ="+urlPath);
			return;
		}
		
		Debugger.println(this, "processing ="+urlPath);
		
		try
		{
			URL url = new URL(urlPath);
			//open url
			URLConnection connection = url.openConnection();		
			
			//test if html
			String contentType = connection.getContentType();
			
			//String html = new HttpClientText(url).getText();			
			
			org.w3c.dom.Document document =new DomJsoupHtmlStragety().toDocument(connection.getInputStream());
			
			//get document in cache, to potentially be used by the engineer
			CacheFarm.getCache().put(documentCacheKey, document); 
			
			//cache XML
			CacheFarm.getCache().put(xmlCacheKey, new DOM4J(document));
			
			Map<K,V> mapText = super.constructMapToText(urlPath);
			
			//save document information
			engineer.construct(urlPath, mapText);
			
			if(!HTTP.isHtmlContentType(contentType))
			{
				//add single index
				return; //do not check links
			}
			
			//find all hrefs
			NodeList nodes = document.getElementsByTagName("a");
			NamedNodeMap attributes = null;
			Node href = null;
			String path = null;
			for (int i = 0; i < nodes.getLength(); i++)
			{
				attributes = nodes.item(i).getAttributes();
				if(attributes == null)
					continue; //skip
				
				href = attributes.getNamedItem("href");
				
				path = href.getNodeValue();
				
				if(!this.mustSkip(path))
				{
					try
					{
						//recursive build
						crawl(HTTP.toURL(url,path).toString(),engineer);
					}
					catch(MalformedURLException e)
					{
						Debugger.printWarn(this,"skipped parent url="+url+" link="+path+" "+e);
					}
				}				
			}
		}
		catch (Exception e)
		{
			throw new SystemException("unable to process url="+urlPath+" error="+Debugger.stackTrace(e));
		}		
	}// --------------------------------------------
	private boolean mustSkip(String urlPath)
	{
		
		if(Text.matches(urlPath, ignorePathRegExp) || crawledPaths.contains(urlPath))
		{
			Debugger.println(this, "skipped ="+urlPath);
			return true;
		}
		return false;
	}// --------------------------------------------
	
	/**
	 * @return the xmlCacheKey
	 */
	public String getXmlCacheKey()
	{
		return xmlCacheKey;
	}
	/**
	 * @param xmlCacheKey the xmlCacheKey to set
	 */
	public void setXmlCacheKey(String xmlCacheKey)
	{
		this.xmlCacheKey = xmlCacheKey;
	}

	private String xmlCacheKey = "xml";
	private String documentCacheKey = "document";
	private Set<Object> crawledPaths = new HashSet<Object>();
	private String ignorePathRegExp = "";
	//private int quota = Integer.MAX_VALUE;
}
