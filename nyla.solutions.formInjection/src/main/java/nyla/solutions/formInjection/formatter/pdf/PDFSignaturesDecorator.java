package nyla.solutions.formInjection.formatter.pdf;

import java.util.Map;

import nyla.solutions.formInjection.data.Application;
import nyla.solutions.formInjection.data.Form;
import nyla.solutions.global.data.Textable;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.security.user.data.User;
import nyla.solutions.global.util.JavaBean;


/**
 * <pre>
 * PDFSignaturesDecorator provides a set of functions to
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class PDFSignaturesDecorator extends PDFDecorator
{


   /**
    * Constructor for PDFSignaturesDecorator initalizes internal 
    * data settings.
    * @param aComponent
    * @param aTemplateName
    * @param aType
    * @param aViewer
    */
   private PDFSignaturesDecorator(Textable aComponent, String aTemplateName,
   short aType, User aViewer)
   {
      super(aComponent, aTemplateName, aType, aViewer);
   }//--------------------------------------------

   public static PDFSignaturesDecorator getInstance(Form aForm, User aViewer)
   {
      return new PDFSignaturesDecorator(aForm, "FO_SIGNATURES",PDF_TYPE, aViewer);
   }//--------------------------------------------
   /**
    * 
    * 
    * @see com.bms.informatics.gcsm.common.data.Textable#getText()
    */
   public String getText()
   {
      try
      {
         
         Application form = (Application)this.getComponent();
         Map map = JavaBean.toMap(form);
         
         //map.putAll(form.getFormPropertyMap());
         
         
                  
         return this.format(map,this.getTemplate());
      }
      catch(Exception e)
      {
         throw new SystemException(e);
      }
   }//--------------------------------------------
}
