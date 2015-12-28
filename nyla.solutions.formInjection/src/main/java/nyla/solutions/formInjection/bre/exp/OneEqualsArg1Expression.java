package nyla.solutions.formInjection.bre.exp;
import nyla.solutions.formInjection.bre.ExpressionBluePrint;
import nyla.solutions.formInjection.bre.LogicalExpression;
import nyla.solutions.formInjection.data.Form;
import nyla.solutions.formInjection.data.FormComponent;
import nyla.solutions.formInjection.data.FormQuestion;
import nyla.solutions.global.util.Organizer;

/**
 * <pre>
 * OneEqualsArg1Expression � Q1=C Question 1�s  
 * constant value = constant value 
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class OneEqualsArg1Expression 
extends LogicalExpression
{  
   static final long serialVersionUID = OneEqualsArg1Expression.class.getName()
   .hashCode();
   /**
    * @return this.constant.compareTo(formQuestion1.getAnswerValue()) == 0
    * @see nyla.solutions.formInjection.bre.LogicalExpression#interpret(org.solutions.form.bre.FormComponent)
    */
   public boolean interpret(FormComponent aFormComponent)
   {
      
      ExpressionBluePrint exp = this.getExpressionBluePrint();
      
      Form form = this.retrieveForm(aFormComponent);
      
      FormQuestion formQuestion1 = this.findFormQuestionByQuestionID(
               aFormComponent,exp.getQuestion1PK());  
      
      String arg1 = exp.getInputArgument1();
      
      if(exp.hasColumn())
      {
            return !Organizer.isEmpty(
             (form.findAnswersByQuestionAndColumnWithAnswerValue(formQuestion1.getPrimaryKey(),
                exp.getTablePK().intValue(), exp.getColumnNumber().intValue(), arg1)));
      }
      else
      {
         String answerText = String.valueOf(formQuestion1.getAnswerValue());
         return arg1.equals(answerText);
      }
   }//--------------------------------------------

}
