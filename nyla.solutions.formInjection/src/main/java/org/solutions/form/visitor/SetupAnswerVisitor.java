package org.solutions.form.visitor;

import org.solutions.form.FormGuide;
import org.solutions.form.FormVisitor;
import org.solutions.form.data.Form;
import org.solutions.form.data.FormAnswer;


/**
 * <pre>
 * SetupAnswerVisitor provides a set of functions to to setup a form 
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class SetupAnswerVisitor extends AbstractFormVisitor
implements FormVisitor
{

   /**
    * Constructor for SetupAnswerVisitor initalizes internal 
    * data settings.
    * 
    */
   public SetupAnswerVisitor()
   {
      //TODO: mark rows as deleted to support manually adding and deleting dynamic rows
      super();
   }//--------------------------------------------
   public void visit(Form aForm)
   {
      //aForm.clearNewAnswers();
   }//--------------------------------------------
   
   /**
    * 
    * @see org.solutions.form.FormVisitor#visit(org.solutions.form.data.Answer)
    */
   public void visit(FormAnswer aAnswer)
   {
      if(aAnswer == null || aAnswer.isReadOnly())
         return;

      
      //if(aAnswer.hasChoices())
      //   aAnswer.clear();
      
      //delete answer
      if(aAnswer.isWithinTable() && 
         FormGuide.canDeleteTableAnswer(aAnswer))
      {
         //mark answer as deleted, it will be undeleted by the FormDecoder
         try
         {
            aAnswer.retrieveForm().delete(aAnswer);
         }
         catch(Exception e)
         {
            aAnswer.delete();
         }
      }
      /*
      if(!aAnswer.isDeleted())
      {
         //Copy question attributes to the answer's properties
         aAnswer.getForm().mergeQuestionAttributesAndAnswerProperties(aAnswer);
      }
      */
   }//--------------------------------------------
   
   public boolean visitAnswers() {
       return true;
   }
   
}
