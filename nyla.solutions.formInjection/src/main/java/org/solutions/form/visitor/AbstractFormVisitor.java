package org.solutions.form.visitor;

import org.solutions.form.FormVisitor;
import org.solutions.form.data.Form;
import org.solutions.form.data.FormAnswer;
import org.solutions.form.data.FormColumn;
import org.solutions.form.data.FormQuestion;
import org.solutions.form.data.FormRow;
import org.solutions.form.data.FormTable;
import org.solutions.form.data.QuestionChoice;
import org.solutions.form.data.Section;


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
    * @see org.solutions.form.FormVisitor#visit(org.solutions.form.data.Form)
    */
   public void visit(Form aForm)
   {
   }//--------------------------------------------
   /**
    * 
    * @see org.solutions.form.FormVisitor#visit(org.solutions.form.data.Section)
    */
   public void visit(Section aSection)
   {
   }//--------------------------------------------

   /**
    * 
    * @see org.solutions.form.FormVisitor#visit(org.solutions.form.data.FormQuestion)
    */
   public void visit(FormQuestion aFormQuestion)
   {
   }//--------------------------------------------

   /**
    * 
    * @see org.solutions.form.FormVisitor#visit(org.solutions.form.data.Answer)
    */
   public void visit(FormAnswer aAnswer)
   {
   }//--------------------------------------------

   /**
    * 
    * @see org.solutions.form.FormVisitor#visit(org.solutions.form.data.QuestionChoice)
    */
   public void visit(QuestionChoice aChoice)
   {
   }//--------------------------------------------
   /**
    * 
    * @see org.solutions.form.FormVisitor#visit(org.solutions.form.data.FormTable)
    */
   public void visit(FormTable aTable)
   {
   }//--------------------------------------------

   /**
    * 
    * @see org.solutions.form.FormVisitor#visit(org.solutions.form.data.FormRow)
    */
   public void visit(FormRow aRow)
   {
   }//--------------------------------------------
   /**
    * 
    * @see org.solutions.form.FormVisitor#visit(org.solutions.form.data.FormColumn)
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
