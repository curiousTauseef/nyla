package nyla.solutions.formInjection.formatter.pdf;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Hashtable;

import nyla.solutions.formInjection.data.Column;
import nyla.solutions.formInjection.formatter.FormDecorator;
import nyla.solutions.global.security.user.data.User;
import nyla.solutions.global.util.Text;


/**
 * @author Gregory Green
 * @version 1.0
 *
 * <b>PDFColumnDecorator</b>  generic column decorator 
 * 
 */
public class PDFColumnDecorator extends FormDecorator
{
   public static final double MAX_WIDTH = 19.0; //CM
   
   private PDFColumnDecorator(Column aColumn, String aTemplate, FormDecorator aDecorator)
   {
      super(aColumn, aTemplate,aDecorator);
   }//----------------------------------------------      
   
   /**
    * @param aComponent the Value object
    */
   private PDFColumnDecorator(Column aColumn, String aTemplate, short aType, User aViewer)
   throws Exception
   {
      super(aColumn, aTemplate, aType,aViewer);
   }//-----------------------------------------
   public static PDFColumnDecorator getFOInstance(Column aColumn, User aViewer)
   throws Exception
   {
         return  new PDFColumnDecorator(aColumn,"FO_COLUMN",PDF_TYPE,aViewer);
         
    
   }//----------------------------------------------

   public static PDFColumnDecorator getInstance(Column aColumn, FormDecorator aDecorator)  
   {
         return  new PDFColumnDecorator(aColumn,"FO_COLUMN",aDecorator);    
   }//----------------------------------------------   
   /**
    * @return string decorated text
    */
   public String getText()
   {
      try
      {
         
         StringBuffer text = new StringBuffer(fix(super.getText()));
         
         
         //logger.debug("COLUMN text="+text);
         Hashtable map = new Hashtable();
         map.put("text",text);
         
         //logger.debug("colum template="+getTemplate());
         return Text.format(getTemplate(),map);
                                
      }
      catch (Exception e)
      {
        throw new RuntimeException(e);
      }
   }//-----------------------------------   
   static String columWidth(int aColumCount, int aColNumber)
   {
     
         
         if(aColumCount == 0)
         {
            return String.valueOf(MAX_WIDTH);
         }
         
         NumberFormat f = DecimalFormat.getInstance();
         
         f.setMinimumFractionDigits(1);
         
         String width = f.format((1.0 / (aColumCount *1.0) ) *  MAX_WIDTH);
         
         //Debugger.println("COL WIDTH "+width);
         return width;
      }//------------------------------------------------------------------

}
