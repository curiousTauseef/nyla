package org.solutions.form.bre.exp;

import org.solutions.form.bre.LogicalExpression;
import org.solutions.form.data.FormComponent;


/** 
 * <pre>
 * <b>OrExpression</b> 
 * 
 * Or two logical expressions 
 * 
 * Permuations: 
 *  exp1:NULL   exp2:NULL     result:NULL
 *  exp1:NULL   exp2:TRUE     result:TRUE
 *  exp1:NULL   exp2:FALSE    result:NULL
 *  exp1:FALSE  exp2:NULL     result:NULL
 *  exp1:FALSE  exp2:TRUE     result:TRUE
 *  exp1:FALSE  exp2:FALSE    result:FALSE 
 *  exp1:TRUE   exp2:NULL     result:TRUE
 *  exp1:TRUE   exp2:TRUE     result:TRUE
 *  exp1:TRUE   exp2:FALSE    result:TRUE
 * </pre>
 * @author Gregory Green
 
 */
public class OrExpression extends LogicalExpression
{
   /**
    * @return true if exp or exp2
    *  * Permuations: 
    * 
    *  exp1:TRUE   exp2:NULL     result:TRUE
    *  exp1:TRUE   exp2:TRUE     result:TRUE 
    *  exp1:TRUE   exp2:FALSE    result:TRUE
    *  exp1:NULL   exp2:TRUE     result:TRUE 
    *  exp1:FALSE  exp2:TRUE     result:TRUE
    *  exp1:FALSE  exp2:FALSE    result:FALSE  
    * 
    *  exp1:NULL   exp2:NULL     result:NULL 
    *  exp1:NULL   exp2:FALSE    result:NULL
    *  exp1:FALSE  exp2:NULL     result:NULL

    * 
    */
   public boolean interpret(FormComponent aBusinessComponent)
   {
      return exp1.interpret(aBusinessComponent) || exp2.interpret(aBusinessComponent);  
   }//------------------------------------

   /**
    * @label expresson1 
    */
   private LogicalExpression exp1;
   /**
    * @label expression2 
    */
   private LogicalExpression exp2;
   
   static final long serialVersionUID = OrExpression.class.getName().hashCode();
}
