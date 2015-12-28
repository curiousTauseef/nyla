package nyla.solutions.formInjection.bre.exp;

import nyla.solutions.formInjection.bre.LogicalExpression;
import nyla.solutions.formInjection.data.FormComponent;


/** <b>AndExpression</b> 
 * 
 * And two logical expressions 
 * 
 * Permuations: 
 *  exp1:NULL   exp2:NULL     result:NULL
 *  exp1:NULL   exp2:TRUE     result:NULL
 *  exp1:NULL   exp2:FALSE    result:NULL
 *  exp1:FALSE  exp2:NULL     result:NULL
 *  exp1:FALSE  exp2:TRUE     result:FALSE
 *  exp1:FALSE  exp2:FALSE    result:FALSE
 *  exp1:TRUE   exp2:FALSE    result:FALSE  
 *  exp1:TRUE   exp2:NULL     result:NULL
 *  exp1:TRUE   exp2:TRUE     result:TRUE 
 * 
 */
public class AndExpression extends LogicalExpression
{
   /**
    * @return true if exp or exp2
    * 
    * <pre>
    * And two logical expressions 
    * 
    * Permuations: 
    *  exp1:NULL   exp2:NULL     result:NULL
    *  exp1:NULL   exp2:TRUE     result:NULL
    *  exp1:NULL   exp2:FALSE    result:NULL
    *  exp1:FALSE  exp2:NULL     result:NULL
    *  exp1:TRUE   exp2:NULL     result:NULL
    * 
    *  exp1:FALSE  exp2:TRUE     result:FALSE
    *  exp1:FALSE  exp2:FALSE    result:FALSE
    *  exp1:TRUE   exp2:FALSE    result:FALSE   
    *  exp1:TRUE   exp2:TRUE     result:TRUE
    * </pre> 
    */
   public boolean interpret(FormComponent aBusinessComponent)
   {
      return super.getExpressionBluePrint().getExpressionBluePrint1().getLogicalExpression().interpret(aBusinessComponent) && 
              super.getExpressionBluePrint().getExpressionBluePrint2().getLogicalExpression().interpret(aBusinessComponent);  
   }//------------------------------------
   static final long serialVersionUID = AndExpression.class.getName()
   .hashCode();
}
