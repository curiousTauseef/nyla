package nyla.solutions.formInjection.bre.operation;

import java.io.Serializable;

import nyla.solutions.formInjection.bre.OperationParameter;
import nyla.solutions.formInjection.data.Form;
import nyla.solutions.formInjection.data.FormAnswer;
import nyla.solutions.formInjection.data.FormComponent;
import nyla.solutions.formInjection.data.FormQuestion;
import nyla.solutions.formInjection.exception.BreException;
import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.exception.SetupException;



/**
 * <pre>
 * AddAnswerToFormPropertyOperation add a given question's answer to
 * a form property.
 * 
 * 
 * Operation init parameters
 * formPropertyName - the form property name to add
 * targetTrueQuestionPropBool - form question attribute to look for
 * 
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class AddAnswerToFormPropertyOperation extends AbstractOperation
{

   /**
    * Constructor for AddAnswerToFormPropertyOperation initalizes internal 
    * data settings.
    * 
    */
   public AddAnswerToFormPropertyOperation()
   {
      super();
   }//--------------------------------------------

   /**
    * 
    * @see nyla.solutions.formInjection.bre.Operation#execute(nyla.solutions.formInjection.data.FormComponent)
    */
   public void execute(FormComponent aFormComponent) throws BreException
   {
       Form form = this.retrieveForm(aFormComponent);
      
      OperationParameter formPropertyName = this.findSingleParameterByName("formPropertyName");
      
      if(formPropertyName.getValue() == null)
         throw new SetupException(getClass().getName()+" required parameter has non-null value, "+formPropertyName);
      
      //test if form already has property value
      //if(!Text.isNull(String.valueOf(form.retrievePropertyValue(String.valueOf(formPropertyName.getValue())))))
      //{
      //   logger.debug("Form "+form.getPrimaryKey()+" already has value for property "+formPropertyName.getValue());
      //   return;
      //}
      
      try
      {
         logger.debug("formPropertyName="+formPropertyName);
         
         //Get form question
         FormQuestion formQuestion = super.retrieveTargetFormQuestionByTrueAttribute(aFormComponent);
         
         FormAnswer answer = formQuestion.getAnswer();
         //logger.debug("answer="+answer);
         
         
         //Add form property
         form.addProperty((String)formPropertyName.getValue(),(Serializable)answer.getValue());
      }
      catch (NoDataFoundException e)
      {
         logger.debug("Cannot find question "+ e);
      }
   }//--------------------------------------------
   static final long serialVersionUID = AddAnswerToFormPropertyOperation.class
   .getName().hashCode();
}
