package nyla.solutions.formInjection.bre.exp;

import nyla.solutions.formInjection.bre.LogicalExpression;
import nyla.solutions.formInjection.data.FormComponent;


/**
 * <pre>
 * TrueExpression alway true expression
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class TrueExpression extends LogicalExpression
{
   /**
    * @return true
    * @see nyla.solutions.formInjection.bre.LogicalExpression#interpret(org.solutions.form.bre.FormComponent)
    */
   public boolean interpret(FormComponent aBusinessComponent)
   {
      return true;
   }//--------------------------------------------
   static final long serialVersionUID = TrueExpression.class.getName()
   .hashCode();
}
