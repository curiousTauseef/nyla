
package nyla.solutions.formInjection.formatter.pdf;

import java.util.Hashtable;

import nyla.solutions.formInjection.data.FormQuestion;
import nyla.solutions.formInjection.data.Section;
import nyla.solutions.formInjection.formatter.FormDecorator;
import nyla.solutions.global.security.user.data.User;
import nyla.solutions.global.util.Text;


/**
 * @author Gregory Green
 * @version 1.0
 *
 * <b>PDFSectionDecorator</b>  the generic section decorator
 * 
 */
public class PDFSectionDecorator extends PDFDecorator
{
   /**
    * 
    * Constructor for PDFSectionDecorator initalizes internal 
    * data settings.
    * @param aSection
    * @param aTemplateName
    * @param aType
    * @param aViewer
    * @throws Exception
    */
   private PDFSectionDecorator(Section aSection,String aTemplateName, short aType, User aViewer)
   throws Exception
   {
      super(aSection,aTemplateName,aType,aViewer);
   }//-----------------------------------------

   /**
    * Factory method for PDF decorator
    * @param aSection
    * @param aViewer
    * @return the doc decorator instance
    * @throws Exception
    */
   public static PDFSectionDecorator getFOInstance(Section aSection, User aViewer)
   throws Exception
   {
      PDFSectionDecorator d = new PDFSectionDecorator(aSection,"FO_SECTION",PDF_TYPE, aViewer);
      
      return d;
   }//----------------------------------------------
   /**
    * Factory method for FO decorator
    * @param aSection the section
    * @param aDecorator the client decorator
    * @return the doc decorator instance
    * @throws Exceptioln
    */
   public static PDFSectionDecorator getInstance(Section aSection, FormDecorator aDecorator)
   throws Exception
   {
         return getFOInstance(aSection,aDecorator.getViewer());    
      
   }//------------------------------------------
   /**
    * @return decorated section text
    */
   public String getText()
   {
      
     try
      {
          Hashtable map = new Hashtable();
            
           Section section = (Section)this.getComponent();
                     
                                         
            map.put("text",super.getText());
            
            map.put("number",section.getNumber());
                        
            //Added Question
            
            return Text.format(getTemplate(),map);
      }
      catch (Exception e)
      {
         throw new RuntimeException(e);
      }
   }//----------------------------------

   /**
    * @return true if the question must be skipped
    */
   protected boolean mustSkip(FormQuestion aQuestion)
   {
      if(aQuestion == null)
      {
         return true;
      }
      
      
      return super.mustSkip(aQuestion);
      
   }//---------------------------------------
}
