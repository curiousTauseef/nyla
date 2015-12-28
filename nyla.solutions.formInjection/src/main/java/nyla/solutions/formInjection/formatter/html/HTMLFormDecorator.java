package nyla.solutions.formInjection.formatter.html;

import nyla.solutions.formInjection.data.FormComponent;
import nyla.solutions.global.data.Textable;


public class HTMLFormDecorator extends HTMLDecorator
{

   /**
    * @param aComponent
    */
   public HTMLFormDecorator(FormComponent aComponent)
   {       
      super((Textable) aComponent);
   }//--------------------------------------------
   /**
    * 
    * @see nyla.solutions.formInjection.formatter.html.HTMLDecorator#getText()
    */
   public String getText()
   {
      // TODO Auto-generated method stub
      return super.getText();
   }
}