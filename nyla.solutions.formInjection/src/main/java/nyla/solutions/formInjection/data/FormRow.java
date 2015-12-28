package nyla.solutions.formInjection.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import nyla.solutions.formInjection.bre.Operation;
import nyla.solutions.formInjection.exception.BreException;
import nyla.solutions.global.operations.logging.Log;
import nyla.solutions.global.util.Debugger;


/**
 * 
 * <pre>
 * FormRow provides a set of functions to
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public class FormRow extends FormComponent implements FormComponentWrapper
{

   protected FormRow(FormTable table, int rowNum)
   {
      if (table == null)
         throw new IllegalArgumentException("form table is required");
      this.table = table;
      this.rowNum = rowNum;
   }

   public Object getKey()
   {
      return new Integer(rowNum);
   }

   public String getText()
   {
      return "";
   }

   /*
    * public void unDelete() { for (Iterator i = getAnswers().iterator();
    * i.hasNext();) { FormAnswer answer = (FormAnswer) i.next();
    * answer.unDelete(); } }//--------------------------------------------
    */

   public Form getForm()
   {
      return table.getForm();
   }

   public Form retrieveForm()
   {
      return getForm();
   }

   public FormTable getFormTable()
   {
      return table;
   }

   public Object getManagedObject()
   {
      return null;
   }

   public int getColumnCount()
   {
      return table.getColumnCount();
   }

   public FormAnswer addAnswer(int col, Object value)
   {
      Column c = null;
      if (table.getColumnMap().containsKey(new Integer(col)))
         c = table.getColumn(col);
      else
      {
         List cols = new ArrayList(getFormColumns().values());
         c = (Column) cols.get(col);
      }
      return table.getFormQuestion().setAnswer(value, rowNum,
      c.getColNumber().intValue());
   }

   public FormAnswer pickChoice(int col, String choiceKey)
   {
      FormAnswer a = addAnswer(col, null);
      a.pickChoice(choiceKey);
      return a;
   }

   public FormAnswer pickChoiceByText(int col, String choiceText)
   {

      FormAnswer a = addAnswer(col, null);

      if (!a.hasChoices())
      {
         // TODO: fix PATCH

         ResponseType responseType = a.getResponseType();

         if (responseType.hasOperation())
         {
            Operation operation = responseType.getOperationBluePrint()
            .getOperation();
            Debugger.println("executing " + operation);

            try
            {
               if (a.isWithinTable())
               {
                  FormColumn column = a.getFormColumn();
                  Debugger.println("executing on column " + column);
                  operation.execute(column);
               }
               else
               {
                  Debugger.println("executing on question "
                  + a.getFormQuestion());
                  operation.execute(a.getFormQuestion());
               }
            }
            catch (BreException e)
            {

               Debugger.printWarn(e);
            }
         }
      }

      Debugger.getLog(this.getClass()).debug("In form row");
      // //Debugger.dump(a);
      // Debugger.dump(a.getFormColumn());
      a.pickChoiceByText(choiceText);
      return a;
   }

   public Map getFormColumns()
   {
      return getFormTable().getFormColumnMap();
   }

   public Collection getColumns()
   {
      return new TreeSet(getFormColumns().values());
   }//--------------------------------------------

   public List getAnswers()
   {
      FormAnswer[] answers = new FormAnswer[table.getColumnCount()];
      int index = 0;
      for (Iterator i = getFormColumns().keySet().iterator(); i.hasNext(); index++)
      {
         answers[index] = getAnswer(((Integer) i.next()).intValue());
      }
      return Arrays.asList(answers);
   }

   public FormAnswer getAnswer(int colNum)
   {
      FormAnswer answer = getForm().getAnswer(
      table.getFormQuestion().getQuestionId(), rowNum, colNum);
      if (answer == null)
         return table.getFormQuestion().setAnswer(null, rowNum, colNum);
      return answer;
   }

   private FormAnswer getAnswer(int colNum, boolean create)
   {
      if (create)
         return getAnswer(colNum);
      return table.getFormQuestion().getForm().getAnswer(
      table.getFormQuestion().getQuestionId(), rowNum, colNum);
   }

   public FormAnswer findAnswerByColNumber(int colNum)
   {
      return getAnswer(colNum);
   }

   /*
    * public void deleteRow() { List answers = getAnswers(); for (Iterator i =
    * answers.iterator(); i.hasNext(); ) { FormAnswer a = (FormAnswer) i.next();
    * a.delete(); } }
    */

   public void addProperty(String key, Object value)
   {
      List answers = this.getAnswers();
      for (Iterator i = answers.iterator(); i.hasNext();)
      {
         FormAnswer a = (FormAnswer) i.next();
         a.addProperty(key, value, getForm().getAccessUser().getId());
      }
   }

   public AnswerProperty findProperty(String key)
   {
	   Log log =  Debugger.getLog(this.getClass());
       log.debug(
      "Looing for property with key: " + key);
      FormAnswer a = getAnswer(1, false);
      if (a == null)
      {
         log.debug("Answer is null");
         for (int i = 0; i < 10; i++)
         {
            FormAnswer a1 = getAnswer(i, false);
            if (a1 == null)
            	log.debug(
               "Answer is null for col: " + i);
            else
            {
               log.debug(
               "Answer is not null for col: " + i);
               return a1.getProperty(key);
            }

         }
         return null;
      }
      return a.getProperty(key);
   }//--------------------------------------------
   public int getNumber()
   {
      return rowNum;
   }//--------------------------------------------

   static final long serialVersionUID = FormRow.class.getName().hashCode();
   private FormTable table;

   int rowNum;

}
