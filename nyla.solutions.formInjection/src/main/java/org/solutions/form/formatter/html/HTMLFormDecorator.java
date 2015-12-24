package org.solutions.form.formatter.html;

import nyla.solutions.global.data.Textable;

import org.solutions.form.data.FormComponent;


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
    * @see org.solutions.form.formatter.html.HTMLDecorator#getText()
    */
   public String getText()
   {
      // TODO Auto-generated method stub
      return super.getText();
   }
}