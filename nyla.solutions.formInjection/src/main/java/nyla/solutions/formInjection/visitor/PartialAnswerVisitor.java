package nyla.solutions.formInjection.visitor;
import nyla.solutions.formInjection.FormVisitor;
import nyla.solutions.formInjection.data.Form;


/**
 * <pre>
 * PartialAnswerVisitor provides a set of functions to setup answering
 * form answer, where not all questions are supplied.
 *  
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class PartialAnswerVisitor extends AbstractFormVisitor
implements FormVisitor
{

   /**
    * Constructor for SetupAnswerVisitor initalizes internal 
    * data settings.
    * 
    */
   public PartialAnswerVisitor()
   {
      super();
   }//--------------------------------------------
   public void visit(Form aForm)
   {
      //aForm.clearNewAnswers();
   }//--------------------------------------------
}
