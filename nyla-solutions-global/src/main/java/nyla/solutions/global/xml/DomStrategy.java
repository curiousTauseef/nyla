package nyla.solutions.global.xml;

import java.io.InputStream;
import java.net.URL;
import org.w3c.dom.Document;


public interface DomStrategy
{

	/**
	 * Convert XML to DOM object
	 * 
	 * @param aXML
	 *            the input XML
	 * @return DOM object for the XML
	 * @throws org.dom4j.DocumentException
	 */
	public Document toDocument(String aXML);
	
	public Document toDocument(InputStream inputStream);
	/**
	 * Convert XML to DOM object
	 * 
	 * @param aXML
	 *            the input XML
	 * @return DOM object for the XML
	 * @throws org.dom4j.DocumentException
	 */
	public Document toDocument(URL url);
}
