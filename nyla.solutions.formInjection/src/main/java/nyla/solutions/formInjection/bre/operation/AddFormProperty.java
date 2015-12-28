package nyla.solutions.formInjection.bre.operation;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

import nyla.solutions.formInjection.bre.OperationParameter;
import nyla.solutions.formInjection.data.*;
import nyla.solutions.formInjection.exception.BreException;


/**
 * <pre>
 * AddFormProperty add a property to an form.
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class AddFormProperty extends AbstractOperation
{

   /**
    * Constructor for AddFormProperty initalizes internal 
    * data settings.
    * 
    */
   public AddFormProperty()
   {
      super();
   }//--------------------------------------------

   /**
    * 
    * @see nyla.solutions.formInjection.bre.Operation#execute(nyla.solutions.formInjection.data.FormComponent)
    */
   public void execute(FormComponent aFormComponent) throws BreException
   {
      Form form = this.retrieveForm(aFormComponent);
      
      Collection params = this.getParameters();
      OperationParameter operationParameter = null;
      for (Iterator i = params.iterator(); i.hasNext();)
      {
         operationParameter = (OperationParameter) i.next();
         
         form.addProperty(operationParameter.getName(), (Serializable)operationParameter.getValue());
      }
   }//--------------------------------------------
   /**
    * Never skip operation
    * @see nyla.solutions.formInjection.bre.operation.AbstractOperation#mustSkip(nyla.solutions.formInjection.data.FormComponent)
    */
   public boolean mustSkip(FormComponent aForm)
   {
      return false;
   }//--------------------------------------------
   static final long serialVersionUID = AddFormProperty.class.getName()
   .hashCode();

}
