package nyla.solutions.formInjection.visitor;

import nyla.solutions.formInjection.FormVisitor;
import nyla.solutions.formInjection.data.Form;
import nyla.solutions.formInjection.data.FormAnswer;
import nyla.solutions.formInjection.data.FormColumn;
import nyla.solutions.formInjection.data.FormQuestion;
import nyla.solutions.formInjection.data.FormRow;
import nyla.solutions.formInjection.data.FormTable;
import nyla.solutions.formInjection.data.QuestionChoice;
import nyla.solutions.formInjection.data.Section;


/**
 * <pre>
 * AbstractFormVisitor abstract implementation for FormVisitor
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class AbstractFormVisitor implements FormVisitor
{

   /**
    * Constructor for AbstractFormVisitor initalizes internal 
    * data settings.
    * 
    */
   public AbstractFormVisitor()
   {
      super();
   }//--------------------------------------------

   /**
    * 
    * @see nyla.solutions.formInjection.FormVisitor#visit(nyla.solutions.formInjection.data.Form)
    */
   public void visit(Form aForm)
   {
   }//--------------------------------------------
   /**
    * 
    * @see nyla.solutions.formInjection.FormVisitor#visit(nyla.solutions.formInjection.data.Section)
    */
   public void visit(Section aSection)
   {
   }//--------------------------------------------

   /**
    * 
    * @see nyla.solutions.formInjection.FormVisitor#visit(nyla.solutions.formInjection.data.FormQuestion)
    */
   public void visit(FormQuestion aFormQuestion)
   {
   }//--------------------------------------------

   /**
    * 
    * @see nyla.solutions.formInjection.FormVisitor#visit(nyla.solutions.formInjection.data.Answer)
    */
   public void visit(FormAnswer aAnswer)
   {
   }//--------------------------------------------

   /**
    * 
    * @see nyla.solutions.formInjection.FormVisitor#visit(nyla.solutions.formInjection.data.QuestionChoice)
    */
   public void visit(QuestionChoice aChoice)
   {
   }//--------------------------------------------
   /**
    * 
    * @see nyla.solutions.formInjection.FormVisitor#visit(nyla.solutions.formInjection.data.FormTable)
    */
   public void visit(FormTable aTable)
   {
   }//--------------------------------------------

   /**
    * 
    * @see nyla.solutions.formInjection.FormVisitor#visit(nyla.solutions.formInjection.data.FormRow)
    */
   public void visit(FormRow aRow)
   {
   }//--------------------------------------------
   /**
    * 
    * @see nyla.solutions.formInjection.FormVisitor#visit(nyla.solutions.formInjection.data.FormColumn)
    */
   public void visit(FormColumn aFormColumn)
   {
   }//--------------------------------------------
   
   public boolean visitAnswers() {
       return false;
   }
   public boolean visitQuestions() {
       return false;
   }
}
