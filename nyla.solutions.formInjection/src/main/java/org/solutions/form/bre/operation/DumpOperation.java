package org.solutions.form.bre.operation;

import nyla.solutions.global.util.Debugger;

import org.solutions.form.bre.Operation;
import org.solutions.form.data.FormComponent;
import org.solutions.form.exception.BreException;


public class DumpOperation extends AbstractOperation implements Operation
{

   /**
    * 
    */
   private static final long serialVersionUID = -2697379543192057012L;

   /**
    * 
    *
    * @see org.solutions.form.bre.Operation#execute(org.solutions.form.data.FormComponent)
    */
   public void execute(FormComponent formComponent) throws BreException
   {
      Debugger.dump(formComponent);

   }//--------------------------------------------

}
