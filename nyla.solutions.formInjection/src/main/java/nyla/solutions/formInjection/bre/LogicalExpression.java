package nyla.solutions.formInjection.bre;
import java.io.Serializable;

import nyla.solutions.formInjection.data.Form;
import nyla.solutions.formInjection.data.FormComponent;
import nyla.solutions.formInjection.data.FormQuestion;
import nyla.solutions.global.exception.FatalException;
import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.exception.SetupException;
import nyla.solutions.global.exception.SystemException;



/** 
 *<b>LogicalExpression</b> 
 * Based on the Interpreter design pattern.
 * 
 * Logical expression provides an interface to determine 
 * whether a question or set of questions match a configured 
 * criteria. This design is based on the interpreter design pattern.  
 * 
 * Each object that implements this interpret interface must return 
 * a boolean result of true or false. AND, OR, NOT, greater than, 
 * and less than are examples of  logical expressions.
 * 
 * @author Gregory Green
 * @version 1.0
 */
public abstract class LogicalExpression
implements Serializable
{  
   /**
    * 
    *default constructor
    */
   public LogicalExpression()
   {
      super();
   } //-----------------------------------  
    /**
     * 
     * @return true if expression is true
     */
    public abstract boolean interpret(FormComponent aBusinessComponent);
    

    /**
     * Initialize logical expression
     * @param aExpressionBluePrint the expression blue print
     */
    public void initialize(ExpressionBluePrint aExpressionBluePrint)
    {
       this.setExpressionBluePrint(aExpressionBluePrint);
    }//--------------------------------------------    
   /** 
    * @param aBusinessComponent business component
    * @return
    */
   protected Form retrieveForm(FormComponent aBusinessComponent)
   {
      if (aBusinessComponent == null)
         throw new IllegalArgumentException(
         "aBusinessComponent required in SingleExpression");
      
      if(!(aBusinessComponent instanceof Form))
      {
         throw new FatalException("aBusinessComponent "+aBusinessComponent.getClass().getName()
                                 +" not instanceof "+Form.class.getName());
      }
      Form form = (Form)aBusinessComponent;
      return form;
   }//--------------------------------------------
   /**
    * 
    * @param aBusinessComponent the business component (form)
    * @param aQuestionID the question primary key
    * @return retrieveForm(aBusinessComponent).findQuestionByID(aQuestionID)
    */
   protected FormQuestion findFormQuestionByQuestionID(FormComponent aBusinessComponent, Integer aQuestionID)  
   {

         FormQuestion formQuestion = this.retrieveForm(aBusinessComponent).getFormQuestion(aQuestionID);
         if (formQuestion == null)
             throw new SetupException("no form question with id " + aQuestionID);
         formQuestion.setWithinExpression(true);
         return formQuestion;

      
   }//--------------------------------------------      
   /**
    * 
    * @param aBusinessComponent the business component (form)
    * @param aQuestionID the question primary key
    * @return retrieveForm(aBusinessComponent).findQuestionByID(aQuestionID)
    */
   protected FormQuestion findFormQuestionByQuestionID(FormComponent aBusinessComponent, int aQuestionID)
   throws NoDataFoundException
   {
      return this.retrieveForm(aBusinessComponent).getFormQuestion(new Integer(aQuestionID));
   }//--------------------------------------------  
   /**
    * @return Returns the question1PK.
    */
   public Integer getQuestion1PK()
   {
      return this.expressionBluePrint.getQuestion1PK();
      
   }//--------------------------------------------

   /**
    * @return Returns the question2PK.
    */
   public Integer getQuestion2PK()
   {
      Integer question2PK = this.expressionBluePrint.getQuestion2PK();
      if(question2PK == null )
         throw new SetupException("question2PK required in "+this);
      
      return question2PK;
   }//--------------------------------------------
   
   /**
    * @return Returns the expressionBluePrint.
    */
   public ExpressionBluePrint getExpressionBluePrint()
   {
      if(expressionBluePrint == null )
         throw new SystemException("expressionBluePrint is null");
      
      return expressionBluePrint;
   }//--------------------------------------------
   /**
    * @param expressionBluePrint The expressionBluePrint to set.
    */
   public void setExpressionBluePrint(ExpressionBluePrint expressionBluePrint)
   {
      if (expressionBluePrint == null)
         throw new IllegalArgumentException(
         "expressionBluePrint required in LogicalExpression.setExpressionBluePrint");
      this.expressionBluePrint = expressionBluePrint;
   }//--------------------------------------------
   private ExpressionBluePrint expressionBluePrint = null;
}
