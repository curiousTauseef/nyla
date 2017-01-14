package nyla.solutions.global.patterns.decorator;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import nyla.solutions.xml.XML;

public class Html
{
   
   public String toHTML(String aText)
   {
      return XML.toElementText("html",aText);
   }// --------------------------------------------
   public String toTable(String aText)
   {
      //return XML.toElementText("table",aText);
      StringBuffer table = new StringBuffer("<table border=\""+border+"\">")
        .append(aText)
        .append("</table>");
      
      return table.toString();
   }// --------------------------------------------
   
   public String toTR(Object [] aTds)
   {
      if (aTds == null || aTds.length == 0)
         throw new IllegalArgumentException("aTds required in HTML.toTR");
      
      Object td = null;
      StringBuffer tr = new StringBuffer("<tr>");
      for (int i = 0; i < aTds.length; i++)
      {
         td = aTds[i];
         
         if( td == null)
            td = "";
         
         tr.append(XML.toElementText("td",td.toString()));
      }
      
      tr.append("</tr>");
      
      return tr.toString();
   }// --------------------------------------------
   /**
    * @return Returns the border.
    */
   public final int getBorder()
   {
      return border;
   }// --------------------------------------------

   /**
    * @param border The border to set.
    */
   public final void setBorder(int border)
   {
      if (border < 0)
         throw new IllegalArgumentException(
         "border > -1 required in HTML.setBorder");
      
      this.border = border;
   }// --------------------------------------------
   /**
    * 
    * @param document the w3c document
    * @return the HREF links in a document
    */
   public static String[] findHrefs(Document document)
   {
	   NodeList nodes = document.getElementsByTagName("a");
		NamedNodeMap attributes = null;
		Node href = null;
		
		ArrayList<Object> hrefs= new ArrayList<Object>();
		
		for (int i = 0; i < nodes.getLength(); i++)
		{
			attributes = nodes.item(i).getAttributes();
			if(attributes == null)
				continue; //skip
			
			href = attributes.getNamedItem("href");
			
			hrefs.add(href.getNodeValue());
						
		}
		if(hrefs.isEmpty())
			return null;
		
		String [] hrefsLinks = new String[hrefs.size()];
		hrefs.toArray(hrefsLinks);
		
		return hrefsLinks;
   }// --------------------------------------------
   private int border = 1;
}
