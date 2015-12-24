package org.solutions.form;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.exception.SummaryException;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Organizer;

import org.solutions.form.bre.ExpressionBluePrint;
import org.solutions.form.bre.Rule;
import org.solutions.form.data.Form;
import org.solutions.form.data.FormColumn;
import org.solutions.form.data.FormQuestion;
import org.solutions.form.data.Questionaire;
import org.solutions.form.exception.BreException;

/**
 * @author Gregory Green
 * @version 1.0
 * 
 * <b>BRE </b> business rule engine. Business Rule Engine Class controls what
 * operations/scripts are executed on questions when certain logical expressions
 * are evaluated to true or false.
 * 
 * This is the rule engine that contains all expressions, rules and
 * operations/scripts to be performed and passed on to the form questions., In
 * order to reduce database accessing and duplication of question rule
 * information in memory, the BRE object is implemented as a singleton.
 *  
 */
public class BRE implements Serializable
{
   private BRE()
   {
   }

   /**
    * @return instance for given rules
    */
   public static BRE getInstance(Collection aRules, Questionaire aQuestionaire)
   {
      if (aRules == null)
         throw new IllegalArgumentException(
         "aRules required in BRE.getInstance");
      
      if(aQuestionaire == null)
         throw new IllegalArgumentException("aQuestionaire required in BRE.getInstance");
      
      
      BRE bre = new BRE();
      
      //set questionaire
      bre.questionaire = aQuestionaire;
      
      Rule rule = null;
      for (Iterator i = aRules.iterator(); i.hasNext();)
      {
         rule = (Rule) i.next();
         buildRule(bre, rule);
      }
      return bre;
   }//----------------------------------

   /**
    * 
    * @param aRule
    *           the rule to
    */
   private static void buildRule(BRE aBRE, Rule aRule)
   {
      if (aRule == null)
         throw new IllegalArgumentException("aRule required in BRE.buildRule");

      if (aRule.getOperationBluePrint() == null)
         throw new IllegalArgumentException(
         "aRule.getOperationBluePrint() required in BRE.buildRule");

      //add rules
      aBRE.ruleMap.put(aRule.getKey(), aRule);
      
      //builds expressions
      ExpressionBluePrint exp = aRule.getExpressionBluePrint();

      if (exp == null)
         throw new IllegalArgumentException("exp required in BRE.buildRule");

      aBRE.expressionMap.put(exp.getKey(), exp);
      
      if(exp.hasExpression1())
      {
        ExpressionBluePrint exp1 = exp.getExpressionBluePrint1();
         aBRE.expressionMap.put(exp1.getKey(), exp1);
      }
      
      if(exp.hasExpression2())
      {
         ExpressionBluePrint exp2 = exp.getExpressionBluePrint2();
         aBRE.expressionMap.put(exp2.getKey(), exp2);
      }
   }//--------------------------------------------

   
   /**
    * Autofill is used to describe of the function of pre-populating answers or question attributes. 
    * This population is preformed by Operation that are assigned to the response type of the question.
    * 
    * @param aForm the form to autofill
    */
   public void autofill(Form aForm)
   throws BreException
   {
      if (aForm == null)
         throw new IllegalArgumentException("aForm required in BRE.autofill");
      
      //form set Questionaire
      aForm.setQuestionaire(this.questionaire);
      SummaryException summaryException = null;

         Collection formQuestions = aForm.findQuestionsWithOperation();
         
         FormQuestion formQuestion = null;
         
         Debugger.println(this,"Auto filling rules to "+formQuestions.size()+" questions");
         
         for (Iterator i = formQuestions.iterator(); i.hasNext();)
         {
            formQuestion = (FormQuestion) i.next();
            //formQuestion.assignForm(aForm);
            
            //question = formQuestion.getQuestion();
               //execute auto fill operation
               try
               {
                   formQuestion.getResponseType().getOperationBluePrint().getOperation()
                  .execute(formQuestion);
               }
               catch(Exception e)
               {
                  if(summaryException == null)
                     summaryException = new SummaryException();
                  
                  summaryException.addException(new BreException(Debugger.stackTrace(e)+
                  " AUTOFILL question="+formQuestion));
               }               
         }//end for


 
         //Loop thru Columns
         FormColumn column = null;
         for (Iterator i = aForm.findColumnsWithOperation().iterator(); i.hasNext();)
         {
            column = (FormColumn) i.next();
            //column.assignQuestionaire(questionaire);
            
            if(column.getResponseType().hasOperation()) 
            {
               try
               {
                  //create form column 
                  //Executing response type of column
                  column.getResponseType()
                      .getOperationBluePrint()
                         .getOperation()
                            .execute(column);
               }
               catch(Exception e)
               {
                  if(summaryException == null)
                     summaryException = new SummaryException();
                  
                  summaryException.addException(new BreException(Debugger.stackTrace(e)+
                  " AUTOFILL column="+column));
               }
            }//if has operation
         }//end for loop
    
         if(summaryException!= null)
         {
            throw new BreException(summaryException);
         }
   }//--------------------------------------------

   /**
    * Apply section, question and row rules
    *  
    */
   public void applyRules(Form aForm)
   throws BreException
   {
      
      if (aForm == null)
         throw new IllegalArgumentException("aForm required in BRE.applyRules");
      
      //SecurityCredential accessUser = aForm.getAccessUser();
      //if ( accessUser == null)
      //   throw new IllegalArgumentException("aForm.getAccessUser() required in BRE.applyRules");

         //autofill(aForm);
         Rule rule = null;              
         
         for (Iterator i = this.getRules().iterator(); i.hasNext();)
         {
            rule = (Rule) i.next();
            rule.apply(aForm);
         }
     // }
   }//-----------------------------
   private Collection getRules()
   {
      if(rules == null)
         rules = Organizer.sortByJavaBeanProperty("executeOrderNumber", ruleMap.values());
      
      return rules;
      
   }//--------------------------------------------
   /**
    * 
    * @param aLogicalStatementID
    *           the logical statement ID
    * @return instance of the ExpressionBluePrint cached in memory
    */
   protected ExpressionBluePrint findExpressionByFormPKAndCode(int aFormTypePK, String aExpressionCode)
   throws NoDataFoundException
   {
      
      ExpressionBluePrint exp = (ExpressionBluePrint) expressionMap
      .get(aFormTypePK+"-"+aExpressionCode);
      if (exp == null)
         throw new NoDataFoundException("findExpressionByPK aExpressionID="
         + aFormTypePK+"-"+aExpressionCode);

      return exp;
   }//--------------------------------------------
   /**
    * 
    * @see java.lang.Object#toString()
    */
   public String toString()
   {
      StringBuffer text= new StringBuffer(getClass().getName())
      .append(" questionaire=").append(questionaire)
      .append(" ruleMp=").append(ruleMap)
      .append(" expressionMap=").append(expressionMap);

      return text.toString();
    
   }//--------------------------------------------
   /**
    * 
    * @param aRuleID the rule PK
    * @return (Rule)this.ruleMap.get(aRuleID)
    */
   protected Rule findRuleByID(Integer aRuleID)
   {
      return (Rule)this.ruleMap.get(aRuleID);
   }//--------------------------------------------
   private Collection rules = null;
   private Map ruleMap = new HashMap();
   private Questionaire questionaire = null;
   private Map expressionMap = new Hashtable();
   static final long serialVersionUID = BRE.class.getName().hashCode();
}