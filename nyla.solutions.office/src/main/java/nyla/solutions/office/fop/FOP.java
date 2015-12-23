package nyla.solutions.office.fop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Text;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;


/**
 * Wrapper for Apache FOP API
 * 
 * Sample images
 * 
 *   <fo:block>
    <fo:external-graphic src="../graphics/xml_feather_transparent.gif"/>
  </fo:block>
 * @author Gregory Gren
 *
 */

public class FOP
{
	/**
	 * PDF_FORMAT = MimeConstants.MIME_PDF (application/pdf)
	 */
	public static final String PDF_FORMAT = MimeConstants.MIME_PDF;
	
      /**
       * Converts an FO file to a PDF file using FOP
       * @param fo the FO file
       * @param pdf the target PDF file
       * @throws IOException In case of an I/O problem
       * @throws FOPException In case of a FOP problem
       */
      public static byte[] toPDF(String fo) throws IOException, FOPException 
      {    
    	  return toBinary(fo, MimeConstants.MIME_PDF);
      }//---------------------------------------------
      /**
       * Create a PDF document
       * @param fo the FO-XML
       * @param file the output file
       * @throws FileNotFoundException
       */
      public static void writePDF(String fo, File file)
      throws FileNotFoundException
      {
    	  FileOutputStream fileOut = null;
    	  
    	  try
    	  {
    		  fileOut= new FileOutputStream(file);
    		  writeOutputStream(fo, PDF_FORMAT, fileOut);
    	  } 
    	  catch (Exception e)
    	  {	
    		  throw new SystemException(Debugger.stackTrace(e));
		  }    	
    	  finally
    	  {
    		  if(fileOut != null)
    			  try{ fileOut.close(); } catch(Exception e){}
    	  }    	  
      }//---------------------------------------------
      public static void writePDF(String fo, FileOutputStream fileOut)
      throws FileNotFoundException
      {
    	  try
    	  {    	
    		  writeOutputStream(fo, PDF_FORMAT, fileOut);
    	  } 
    	  catch (Exception e)
    	  {	
    		  throw new SystemException(Debugger.stackTrace(e));
		  }    	
    	  finally
    	  {    		
    	  }    	  
      }//---------------------------------------------      
          /**
           * Converts an FO file to a PDF file using FOP
           * @param fo the FO file
           * @param pdf the target PDF file
           * @throws IOException In case of an I/O problem
           * @throws FOPException In case of a FOP problem
           */
      private static byte[] toBinary(String fo, String outputFormat) throws IOException, FOPException 
      {  
    	  ByteArrayOutputStream pdfOUT = null;
    	  //BufferedOutputStream  out = null;
    	  InputStream templateIS = null;
    	  
          try 
          {
              // configure foUserAgent as desired
      
              // Setup output stream.  Note: Using BufferedOutputStream
              // for performance reasons (helpful with FileOutputStreams).
              
              pdfOUT = new ByteArrayOutputStream(byteBufferSize);
              templateIS = writeOutputStream(fo, outputFormat, pdfOUT);
             
              return pdfOUT.toByteArray();

          } 
          catch (Exception e) 
          {
              Debugger.printError(e);
              throw new SystemException(Debugger.stackTrace(e));
          } 
          finally 
          {
        	  if( pdfOUT != null)
        		  try{ pdfOUT.close();} catch(Exception e){}
        		  
        	  //if(out != null)
        	//	  try{out.close(); } catch(Exception e){} 
        		  
        	  if(templateIS != null)
        		  try{ templateIS.close(); } catch(Exception e){}
        		                
          }
      }//---------------------------------------------
      public static InputStream writeOutputStream(String fo,String outputFormat, OutputStream pdfOUT)
      throws  TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException
      {
    	  try
    	  {
	      	  FopFactory fopFactory = FopFactory.newInstance();
	    	  
	          FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
	          // configure foUserAgent as desired

          
			InputStream templateIS;
			//out = new BufferedOutputStream(pdfOUT);

              // Construct FOP with desired output format
              //private FopFactory fopFactory = FopFactory.newInstance();
              Fop fop = fopFactory.newFop(outputFormat, foUserAgent,pdfOUT );

              // Setup JAXP using identity transformer
              TransformerFactory factory = TransformerFactory.newInstance();
              Transformer transformer = factory.newTransformer(); // identity transformer
              
              // Setup input stream
              templateIS = Text.toInputStream(fo);
              Source src = new StreamSource(templateIS);

              // Resulting SAX events (the generated FO) must be piped through to FOP
              Result res = new SAXResult(fop.getDefaultHandler());
              
              // Start XSLT transformation and FOP processing
              transformer.transform(src, res);
			return templateIS;
    	  }
    	  catch(FOPException e)
    	  {
    		  throw new SystemException(e.getMessage(),e);
    	  }
		}

	private static int byteBufferSize = Config.getPropertyInteger(FOP.class,"byteBufferSize",50000).intValue();
      
}
