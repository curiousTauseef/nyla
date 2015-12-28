package nyla.solutions.formInjection.dao.file.xml;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import nyla.solutions.formInjection.BRE;
import nyla.solutions.formInjection.bre.ExpressionBluePrint;
import nyla.solutions.formInjection.bre.OperationBluePrint;
import nyla.solutions.formInjection.dao.BreDAO;
import nyla.solutions.formInjection.data.Form;
import nyla.solutions.formInjection.data.Questionaire;
import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.util.Config;

public class BreXmlDAO extends QuestionXmlDAO implements BreDAO
{
   /**
    * 
    *
    * @see nyla.solutions.formInjection.dao.BreDAO#constructBRE(java.lang.String, nyla.solutions.formInjection.data.Questionaire)
    */
   public final BRE constructBRE(String formTypeCode, Questionaire questionaire) throws NoDataFoundException, SQLException
   {
      //check cache
      
      BRE bre = null;
      
      Collection rules = null;      
      try
      {
         rules = selectRulesByFormTypeCode(formTypeCode);
         
      }
      catch(NoDataFoundException e)
      {
         logger.warn("No rules found for aFormTypeCode="+formTypeCode);
         
         //create empty rules
         rules = new ArrayList();
      }
      
      if(questionaire == null)
         questionaire = this.constructQuestioniareByFormTypeCode(formTypeCode);
      
      //instance of BRE with rules
      bre = BRE.getInstance(rules,questionaire);
                 
      return bre;
   }//--------------------------------------------
   /**
    * 
    *
    * @see nyla.solutions.formInjection.dao.BreDAO#constructBRE(nyla.solutions.formInjection.data.Form)
    */
   public final BRE constructBRE(Form form) throws NoDataFoundException, SQLException
   {
      return constructBRE(form.getFormTypeCode(),form.getQuestionaire()); 
   }//--------------------------------------------

   public ExpressionBluePrint selectExpressionByPK(Integer expressionPK) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

   public final Collection selectFormTypes() throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

   public final OperationBluePrint selectOperationBluePrintByPK(Integer operationPK) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }//--------------------------------------------
   /**
    * 
    *
    * @see nyla.solutions.formInjection.dao.BreDAO#selectRulesByFormTypeCode(java.lang.String)
    */
   public final Collection selectRulesByFormTypeCode(String formTypeCode) throws NoDataFoundException, SQLException
   {
      String location = ruleLocation+"/"+formTypeCode+this.getExtension();
     
     Collection results = (Collection)readObject(location);
     
     if(results == null || results.isEmpty())
     {
        throw new NoDataFoundException(location);
     }
     return results;
   }//--------------------------------------------

   
   private String ruleLocation  =Config.getProperty(BreXmlDAO.class.getName()+".ruleLocation");
   
   
}
