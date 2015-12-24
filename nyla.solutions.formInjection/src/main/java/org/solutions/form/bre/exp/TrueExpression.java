package org.solutions.form.bre.exp;

import org.solutions.form.bre.LogicalExpression;
import org.solutions.form.data.FormComponent;


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
    * @see org.solutions.form.bre.LogicalExpression#interpret(org.solutions.form.bre.FormComponent)
    */
   public boolean interpret(FormComponent aBusinessComponent)
   {
      return true;
   }//--------------------------------------------
   static final long serialVersionUID = TrueExpression.class.getName()
   .hashCode();
}
