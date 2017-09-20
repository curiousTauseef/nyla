package nyla.solutions.formInjection.bre.operation;

import java.util.Collection;
import java.util.Iterator;

import nyla.solutions.formInjection.data.Form;
import nyla.solutions.formInjection.data.FormComponent;
import nyla.solutions.formInjection.data.FormQuestion;
import nyla.solutions.formInjection.exception.BreException;
import nyla.solutions.global.security.user.data.User;
import nyla.solutions.global.util.Debugger;


/**
 * <pre>
 * 
 *  
 * The class SetCreateUserOperation 
 * was created to answer a question with the create user�s title, first name, 
 * and last name. The most efficient way to use this operation is via the 
 * MacroOperation executed with a logical expression that is always true 
 * (LOGIC_EXP_TBL.LOGICAL_EXP_CD = "TRUE"). 
 *   
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public class SetCreateUserOperation extends AbstractOperation
{

   /**
    * Constructor for SetCreateUser initalizes internal data settings.
    *  
    */
   public SetCreateUserOperation()
   {
      super();
   }//--------------------------------------------
   /**
    * Set create user name to a given question's answer
    * 
    * @see nyla.solutions.formInjection.bre.Operation#execute(nyla.solutions.formInjection.data.FormComponent)
    */
   public void execute(FormComponent aFormComponent) throws BreException
   {

      Form form = this.retrieveForm(aFormComponent);

      User createUser = form.getCreateUser();
      
      if(createUser == null)
      {
         createUser = (User)form.retrieveAttributeValue("createUserOverview");
      }
      try
      {
         if (createUser == null)
         {
            //get by create user
            Object userPK = form.getCreateUserID();

            if (userPK == null )
            {
               logger
               .warn("Cannot set create user, form.createUser is null on form="
               + form);
               return;
            }

            //TODO: implement

            form.addAttribute("createUserOverview", createUser);
         }
         
         if(aFormComponent instanceof FormQuestion)
         {
            //answer single form question (auto filling)
            answerCreateUser(form, createUser, (FormQuestion)aFormComponent);
         }
         else
         {
            Collection formQuestions = this
            .retrieveTargetFormQuestionsByTrueAttribute(form);

            FormQuestion formQuestion = null;
            for (Iterator i = formQuestions.iterator(); i.hasNext();)
            {
               formQuestion = (FormQuestion) i.next();
               //answer question
               answerCreateUser(form, createUser, formQuestion);
            }
            
         }

      }
      catch (Exception e)
      {
         throw new BreException(Debugger.stackTrace(e));
      }
   }//--------------------------------------------

   /**
    * @param aForm
    * @param aCreateUser
    * @param aFormQuestion
    */
   private void answerCreateUser(Form aForm, User aCreateUser,
                                 FormQuestion aFormQuestion)
   {
      //logger.debug("Setting create user name to question " + aFormQuestion);
      if(!aFormQuestion.hasAnswer())
      {
         String name = aCreateUser.getName();
         aFormQuestion.setAnswer(name);
      }
   }//--------------------------------------------
   static final long serialVersionUID = 2;
}
