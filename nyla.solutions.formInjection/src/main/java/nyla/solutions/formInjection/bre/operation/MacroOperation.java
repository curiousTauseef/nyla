package nyla.solutions.formInjection.bre.operation;

import java.util.Collection;
import java.util.Iterator;

import nyla.solutions.formInjection.FormGuide;
import nyla.solutions.formInjection.bre.OperationBluePrint;
import nyla.solutions.formInjection.bre.OperationParameter;
import nyla.solutions.formInjection.data.Form;
import nyla.solutions.formInjection.data.FormComponent;
import nyla.solutions.formInjection.exception.BreException;
import nyla.solutions.global.util.Text;


/**
 * <pre>
 * MacroOperation operation that executes other operations
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class MacroOperation extends AbstractOperation
{
   /**
    * Constructor for MacroOperation initializes internal 
    * data settings.
    * 
    */
   public MacroOperation()
   {
      super();
   }//--------------------------------------------

   /**
    * 
    * @see nyla.solutions.formInjection.bre.Operation#execute(nyla.solutions.formInjection.data.FormComponent)
    */
   public void execute(FormComponent aForm) throws BreException
   {
      Form form = this.retrieveForm(aForm);
      //OperationBluePrints
      Collection parameters = this.getSortedParameters();
      
      if(parameters == null || parameters.isEmpty())
      {
         logger.warn("Not parameters configured for "+this);
         return;
      }
      
      try
      {
         OperationParameter operationParameter = null;
         for (Iterator i = parameters.iterator(); i.hasNext();)
         {
            operationParameter = (OperationParameter) i.next();
            if(FormGuide.OPERATION_PK_PARAM_NM.equals(operationParameter.getName()))
            {
               executeOperationByPK(operationParameter.getValue(), form);
            }
         }
      }
      catch (Exception e)
      {
         throw new BreException(e);
      }
   }//--------------------------------------------
   /**
    * Never skip macros
    * @see nyla.solutions.formInjection.bre.operation.AbstractOperation#mustSkip(nyla.solutions.formInjection.data.FormComponent)
    */
   public boolean mustSkip(FormComponent aForm)
   {
      return false;
   }//--------------------------------------------
   private void executeOperationByPK(Object aOperationPK, Form aForm)
   throws Exception
   {
      if (aOperationPK == null)
         throw new IllegalArgumentException(
         "aOperationPK required in MacroOperation.executeOperationByPK");
      
      if (!Text.isInteger(aOperationPK.toString()))
         throw new IllegalArgumentException(
         "Integer aOperationPK required in MacroOperation.executeOperationByPK");
      
      executeOperationByPK(Integer.valueOf(aOperationPK.toString()), aForm);
   }//--------------------------------------------
   private void executeOperationByPK(Integer aOperationPK, Form aForm)
   throws Exception
   {
      OperationBluePrint operationBluePrint = super.retrieveOperationBluePrint(aOperationPK, 
                                aForm.getAccessUser());
      operationBluePrint.getOperation().execute(aForm);
   }//--------------------------------------------
   static final long serialVersionUID = MacroOperation.class.getName()
   .hashCode();
}