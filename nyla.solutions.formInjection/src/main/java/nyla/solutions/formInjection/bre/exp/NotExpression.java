package nyla.solutions.formInjection.bre.exp;

import nyla.solutions.formInjection.bre.LogicalExpression;
import nyla.solutions.formInjection.data.FormComponent;


/**
 * @author Gregory Green
 * @version 1.0
 *
 * <b>NotExpression</b>  not logical expression
 * 
 */
public class NotExpression extends LogicalExpression
{
   static final long serialVersionUID = NotExpression.class.getName()
   .hashCode();
   
   /**
    * Not logical expression # 1
    * 
    * @see nyla.solutions.formInjection.bre.LogicalExpression#interpret(nyla.solutions.formInjection.data.FormComponent)
    */
   public boolean interpret(FormComponent aBusinessComponent)
   {
      return !this.getExpressionBluePrint()
              .getExpressionBluePrint1()
                  .getLogicalExpression().interpret(aBusinessComponent);
   }//--------------------------------------------------
}
