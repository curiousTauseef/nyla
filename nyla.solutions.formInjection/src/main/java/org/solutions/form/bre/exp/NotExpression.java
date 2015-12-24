package org.solutions.form.bre.exp;

import org.solutions.form.bre.LogicalExpression;
import org.solutions.form.data.FormComponent;


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
    * @see org.solutions.form.bre.LogicalExpression#interpret(org.solutions.form.data.FormComponent)
    */
   public boolean interpret(FormComponent aBusinessComponent)
   {
      return !this.getExpressionBluePrint()
              .getExpressionBluePrint1()
                  .getLogicalExpression().interpret(aBusinessComponent);
   }//--------------------------------------------------
}
