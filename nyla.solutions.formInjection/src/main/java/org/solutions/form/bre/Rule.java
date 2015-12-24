package org.solutions.form.bre;
import nyla.solutions.global.data.AbstractAudit;
import nyla.solutions.global.data.Data;

import org.solutions.form.data.FormComponent;
import org.solutions.form.exception.BreException;


/** 
 * <b>Rule</b> represents an application rule.
 * 
 * The rule engine is a set of logical expressions, 
 * operations, and rules related to questions. 
 * The operations rules only works for server-side processing.  
 */
public class Rule extends AbstractAudit
{      
   /**
    * 
    * Default Constructor for Rule initializes internal 
    * data settings.
    *    */
   public Rule()
   {
   }//--------------------------------------------
    /**
     * Apply the business rules the target form object (if logical expresssion,
     * is true and the operation must not be skipped.
     * @param aContext the answer context
     */
    public void apply(FormComponent aFormComponent)
    throws BreException
    {                    
       Operation operation = this.operationBluePrint.getOperation();
       
       if(this.isAlwaysApplied())
       {
          //Execute operation based on logical expression
          if(getExpectedBoolean() == getLogicalExpression().interpret(aFormComponent))
          {
            //execute operation
            operation.execute(aFormComponent);
          }          
       }
       else
       {
          //consider whether the operation must be skip
          if(!operation.mustSkip(aFormComponent) &&
          getExpectedBoolean() == getLogicalExpression().interpret(aFormComponent))
         {
            //execute operation
            operation.execute(aFormComponent);
         }          
       }
    }//-------------------------------------------------------
   /**
    * @return
    */
   private LogicalExpression getLogicalExpression()
   {
      return expressionBluePrint.getLogicalExpression();
   }//--------------------------------------------
   /**
    * @param expression  the logical epression
    */
   public void setExpressionBluePrint(ExpressionBluePrint expBluePrint)
   {
      if(expBluePrint == null )
         throw new IllegalArgumentException("expression not provided");  
             
      this.expressionBluePrint = expBluePrint;
   }//-----------------------------------------------------
   public String getExpectedBooleanCode()
   {
      return expectedBooleanCode;
   }//------------------------------------------------------
   /**
    * Set the  expected boolean code
    */
   public void setExpectedBooleanCode(String aExpectedBooleanCode)
   {
      if(aExpectedBooleanCode == null)
         aExpectedBooleanCode = "T";

      expectedBooleanCode = aExpectedBooleanCode;
   }//-------------------------------------------------------
   /**
    * return 'T' == expectedBooleanCode.charAt(0)
    */
   public boolean getExpectedBoolean()
   {
      return 'T' == expectedBooleanCode.charAt(0);
   }//--------------------------------------------------------
   
   /**
    * @return Returns the formTypeID.
    */
   public int getFormTypeID()
   {
      return formTypeID;
   }//--------------------------------------------
   /**
    * @param formTypeID The formTypeID to set.
    */
   public void setFormTypeID(int formTypeID)
   {
      this.formTypeID = formTypeID;
   }//--------------------------------------------
   
   /**
    * @return Returns the executeOrderNumber.
    */
   public int getExecuteOrderNumber()
   {
      return executeOrderNumber;
   }//--------------------------------------------
   /**
    * @param executeOrderNumber The executeOrderNumber to set.
    */
   public void setExecuteOrderNumber(int executeOrderNumber)
   {
      this.executeOrderNumber = executeOrderNumber;
   }//--------------------------------------------
   
   /**
    * @return Returns the operationBluePrint.
    */
   public OperationBluePrint getOperationBluePrint()
   {
      return operationBluePrint;
   }//--------------------------------------------
   /**
    * @param operationBluePrint The operationBluePrint to set.
    */
   public void setOperationBluePrint(OperationBluePrint operationBluePrint)
   {
      this.operationBluePrint = operationBluePrint;
   }//--------------------------------------------  
   /**
    * @return Returns the expressionBluePrint.
    */
   public ExpressionBluePrint getExpressionBluePrint()
   {
      return expressionBluePrint;
   }//--------------------------------------------
   /**
    * 
    * @return Data.YES.endsWith(alwaysAppiedCode)
    */
   public boolean isAlwaysApplied()
   {
      return Data.YES.endsWith(alwaysAppiedCode);
      
   }//--------------------------------------------
   
   /**
    * @return Returns the alwaysAppiedCode.
    */
   public String getAlwaysAppiedCode()
   {
      return alwaysAppiedCode;
   }//--------------------------------------------
   /**
    * 
    * Compare to execute order numbers
    * @see org.solutions.data.Criteria#compareTo(java.lang.Object)
    */
   public int compareTo(Object o)
   {
     return new Integer(executeOrderNumber).compareTo(new Integer(((Rule)o).executeOrderNumber));
   }//--------------------------------------------
   private String alwaysAppiedCode = Data.NO;
   /**
    * @label expression 
    */
   private ExpressionBluePrint expressionBluePrint;
   private String expectedBooleanCode = "T";
   private OperationBluePrint operationBluePrint = null;
   private int formTypeID =  Data.NULL;
   private int executeOrderNumber = 1;
   static final long serialVersionUID = Rule.class.getName().hashCode();
}
