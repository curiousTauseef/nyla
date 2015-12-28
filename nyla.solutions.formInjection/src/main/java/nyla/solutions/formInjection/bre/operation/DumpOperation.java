package nyla.solutions.formInjection.bre.operation;

import nyla.solutions.formInjection.bre.Operation;
import nyla.solutions.formInjection.data.FormComponent;
import nyla.solutions.formInjection.exception.BreException;
import nyla.solutions.global.util.Debugger;


public class DumpOperation extends AbstractOperation implements Operation
{

   /**
    * 
    */
   private static final long serialVersionUID = -2697379543192057012L;

   /**
    * 
    *
    * @see nyla.solutions.formInjection.bre.Operation#execute(nyla.solutions.formInjection.data.FormComponent)
    */
   public void execute(FormComponent formComponent) throws BreException
   {
      Debugger.dump(formComponent);

   }//--------------------------------------------

}
