package nyla.solutions.formInjection.bre.exp;
import nyla.solutions.formInjection.data.FormComponent;
import nyla.solutions.formInjection.data.FormQuestion;
import nyla.solutions.global.exception.SetupException;

/**
 * <pre>
 * OneEqualsTwoExpression � Q1=Q2 Question 1�s  
 * numeric value = Question 2�s numeric value 
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class OneEqualsTwoExpression extends TwoQuestionOperandExpression
{

   
   /**
    * 
    * @see nyla.solutions.formInjection.bre.LogicalExpression#interpret(org.solutions.form.bre.FormComponent)
    */
   public boolean interpret(FormComponent aBusinessComponent)
   {
      
      FormQuestion formQuestion1 = this.findFormQuestionByQuestionID(aBusinessComponent,this.getQuestion1PK());
      formQuestion1.setWithinExpression(true);
      FormQuestion formQuestion2 = this.findFormQuestionByQuestionID(aBusinessComponent,this.getQuestion1PK());
      formQuestion2.setWithinExpression(true);
      
      return questionAnswersEqual(formQuestion1, formQuestion2);   
   }//--------------------------------------------
   /**
    * 
    * @param formQuestion1 first question
    * @param formQuestion2 question2
    * @return
    */
   public boolean questionAnswersEqual(FormQuestion formQuestion1,FormQuestion formQuestion2)
   {
      if(!formQuestion1.getResponseType().isAnswerable())
         throw new SetupException(this
                         +" can't evaluate a non-answerable question PK="+formQuestion1.getPrimaryKey());
      
      if(!formQuestion2.getResponseType().isAnswerable())
         throw new SetupException(this
                         +" can't evaluate a non-answerable question PK="+formQuestion2.getPrimaryKey());
      
      Comparable answer1 = (Comparable)formQuestion1.getAnswerValue();
      Comparable answer2 = (Comparable)formQuestion2.getAnswerValue();
      return answer1.compareTo(answer2) == 0;
   }//--------------------------------------------
   static final long serialVersionUID = OneEqualsTwoExpression.class.getName()
   .hashCode();
}
