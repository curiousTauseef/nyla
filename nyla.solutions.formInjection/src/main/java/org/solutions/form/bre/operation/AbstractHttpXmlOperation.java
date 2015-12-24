/**
 * AbstractHttpXmlOperation.java 
 * @author Gregory Green
 * @version 1.0
 */
package org.solutions.form.bre.operation;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.xml.transform.TransformerException;

import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.exception.SetupException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.net.http.HTTP;
import nyla.solutions.global.net.http.HTTPResult;
import nyla.solutions.global.net.http.HttpClientText;
import nyla.solutions.global.net.http.HttpUnit;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.xml.XML;
import nyla.solutions.global.xml.XSL;

import org.solutions.form.data.FormComponent;



/**
 * <b>AbstractHttpXmlOperation</b> is a 
 * @author Gregory Green
 *
 */
public abstract class AbstractHttpXmlOperation extends AbstractOperation
{
   /**
    * URL_PARAMETER_NM = "url"
    */
   public static final String URL_PARAMETER_NM = "url";
   
   /**
    * HEADER_PARAM_NM = "header"
    */
   public static final String SOAPACTION_HEADER_PARAM_NM = "SOAPAction";
   
   /**
    * file location of the xsl
    */
   public static final String XSL_REQUEST_PARAMETER_NM = "xslRequest";
   
   /**
    * XSL_RESPONSE_PARAMETER_NM = "xslResponse"
    */
   public static final String XSL_RESPONSE_PARAMETER_NM = "xslResponse";
   
   /**
    * 
    * @return (String)this.findSingleParameterByName(URL_PARAMETER_NM).getValue()
    */
   protected String getUrl()
   {
      return (String)this.findSingleParameterByName(URL_PARAMETER_NM).getValue();
   }// --------------------------------------------

   /**
    * @return file indicated by parameter "XSL"
    * @throws MalformedURLException 
    */
   protected URL getXlsRequest() throws MalformedURLException
   {
      try
      {
         String url = (String)this.findSingleParameterByName(XSL_REQUEST_PARAMETER_NM).getValue();
         
         if(url == null)
            return null;
         
         return new URL(url);
      }
      catch (SetupException e)
      {
         return null;
      }
   }// --------------------------------------------
   protected URL getXlsResponse() throws MalformedURLException
   {
      try
      {
         String url = (String)this.findSingleParameterByName(XSL_RESPONSE_PARAMETER_NM).getValue();
         
         if(url == null)
            return null;
        
         return new URL(url);         
      }
      catch(SetupException e)
      {
         return null;
      }
   }// --------------------------------------------
   /**
    * 
    * @return the HTTP instance
    */
   protected synchronized HTTP getHTTP()
   {
      if(http != null)
         return http;
      
      http = new HttpUnit();
      
      http.addHeaders(this.getHeaderMap());
      return http;
   }// --------------------------------------------
   public abstract Map getHeaderMap();
   
   /**
    * Convert the object to post data. 
    * The XML version of the object is sent (an XSL file may using be specified
    * @param formComponent the formComponent to convert to an XML document to be posted
    * @return the XML or translated formComponent information
    * @throws TransformerException
    */
   public String toPostData(FormComponent formComponent)
   throws TransformerException, IOException
   {
         String postData = XML.getInterpreter().toXML(formComponent);
         
         URL xls = this.getXlsRequest();
         
         if(xls != null)
         {
            //transform xml         
            Debugger.println(this, "xls="+xls.toString());
            postData = XSL.transform(xls, postData);         
         }       

         return postData;

   }// --------------------------------------------
   protected Object readObjectFromPost(FormComponent formComponent)
   throws TransformerException, IOException
   {
      if (formComponent == null)
         throw new RequiredException(
         "formComponent in PopulateHttpPostXmlChoicesOperation.readObjectFromPost");
      
      return XML.getInterpreter().toObject(post(formComponent));
   }// --------------------------------------------

   /**
    * @return the contextType
    */
   public String getContextType()
   {
      return contextType;
   }// --------------------------------------------

   /**
    * @param contextType the contextType to set
    */
   public void setContextType(String contextType)
   {
      this.contextType = contextType;
   }// --------------------------------------------

   /**
    * POSt the form component information
    * @param formComponent the form component to transform/post
    * @return the text response
    */
   protected String post(FormComponent formComponent)
   throws IOException, TransformerException
   {

         HTTP http = this.getHTTP();
         
         //convert form to XML      
         String postData = toPostData(formComponent);
                 
         String url = this.getUrl();
         Debugger.println(this, "post url="+url+" \n[data]\n"+postData);
         
         HTTPResult result = http.post(url, postData,getContextType());
         if(!result.isOK())
         {
            throw new SystemException("Invalid HTTP response code="+result.getStatusCode()+" text="+result.getContent());
         }
         
         return toPostResponse(result.getContent());           
   }// --------------------------------------------
   protected String toPostResponse(String rawResponse)
   throws MalformedURLException
   {
      URL xslResponse = this.getXlsResponse();
      
      if(xslResponse != null)
      {
         return XSL.transform(xslResponse, rawResponse);
      }
      
      return rawResponse;
   }// --------------------------------------------

   private String contextType = "text/xml";
   private HTTP http = null;
}
