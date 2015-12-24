package org.solutions.form.bre.operation;

import java.util.Collection;
import java.util.Iterator;

import nyla.solutions.global.util.Text;

import org.solutions.form.FormGuide;
import org.solutions.form.bre.OperationBluePrint;
import org.solutions.form.bre.OperationParameter;
import org.solutions.form.data.Form;
import org.solutions.form.data.FormComponent;
import org.solutions.form.exception.BreException;


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
    * @see org.solutions.form.bre.Operation#execute(org.solutions.form.data.FormComponent)
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
    * @see org.solutions.form.bre.operation.AbstractOperation#mustSkip(org.solutions.form.data.FormComponent)
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