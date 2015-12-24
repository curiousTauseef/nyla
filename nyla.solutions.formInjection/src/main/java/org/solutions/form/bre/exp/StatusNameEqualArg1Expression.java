package org.solutions.form.bre.exp;
import org.solutions.form.bre.LogicalExpression;
import org.solutions.form.data.Form;
import org.solutions.form.data.FormComponent;
import org.solutions.form.data.FormStatus;

/**
 * <pre>
 * StatusNameEqualArg1Expression is submitted
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class StatusNameEqualArg1Expression extends LogicalExpression
{

   /**
    * Constructor for EqualExpression initializes internal 
    * data settings.
    * 
    */
   public StatusNameEqualArg1Expression()
   {
   }//--------------------------------------------
   /**
    * 
    * @see org.solutions.form.bre.LogicalExpression#interpret(org.solutions.form.bre.FormComponent)
    */
   public boolean interpret(FormComponent aFormComponent)
   {
      Form form = super.retrieveForm(aFormComponent);
      
      FormStatus status = form.getFormStatus();
      
      return status != null &&
              this.getExpressionBluePrint().getInputArgument1()
              .equals(status.getName());
   }//--------------------------------------------
   static final long serialVersionUID = StatusNameEqualArg1Expression.class
   .getName().hashCode();
}
