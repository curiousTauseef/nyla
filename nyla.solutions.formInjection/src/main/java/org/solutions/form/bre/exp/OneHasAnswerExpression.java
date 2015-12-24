package org.solutions.form.bre.exp;
import org.solutions.form.bre.LogicalExpression;
import org.solutions.form.data.FormComponent;

/** <b>OneHasAnswerExpression</b>
 *  Based on <b>Interpret</b> design pattern  
 * @author Gregory Green
 */
public class OneHasAnswerExpression extends LogicalExpression
{
   
   static final long serialVersionUID = OneHasAnswerExpression.class.getName()
   .hashCode();

   /**
    * @return findFormQuestionByQuestionID(aBusinessComponent,getQuestion1PK()).hasAnswer()
    * @see org.solutions.form.bre.LogicalExpression#interpret(org.solutions.form.bre.FormComponent)
    */
   public boolean interpret(FormComponent aBusinessComponent)
   {
      return findFormQuestionByQuestionID(aBusinessComponent,this.getQuestion1PK()).hasAnswer();
   }//--------------------------------------------
}
