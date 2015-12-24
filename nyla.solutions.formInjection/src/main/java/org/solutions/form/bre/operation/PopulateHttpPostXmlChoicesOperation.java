package org.solutions.form.bre.operation;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.xml.transform.TransformerException;

import nyla.solutions.global.exception.SetupException;
import nyla.solutions.global.util.Debugger;

import org.solutions.form.bre.AutofillOperation;
import org.solutions.form.data.Form;
import org.solutions.form.data.FormColumn;
import org.solutions.form.data.FormComponent;
import org.solutions.form.data.FormQuestion;
import org.solutions.form.exception.BreException;



/**
 * <pre>
 * PopulateHttpXmlChoicesOperation provides functions to
 * populate a list of choices based on a response for an HTTP request
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class PopulateHttpPostXmlChoicesOperation extends AbstractHttpXmlOperation
implements AutofillOperation
{  
   
   /**
    * Constructor for PopulateUserChoicesOperation initializes internal 
    * data settings.
    * 
    */
   public PopulateHttpPostXmlChoicesOperation()
   {
      super();
   }//--------------------------------------------
   /**
    * 
    * @see org.solutions.form.bre.Operation#execute(org.solutions.form.data.FormComponent)
    */
   public void execute(FormComponent aFormComponent) throws BreException
   {
      try
      {
         Form form = this.retrieveForm(aFormComponent);
         
         if(aFormComponent instanceof FormColumn)
         {
            FormColumn formColumn = this.retrieveFormColumn(aFormComponent);
            
            if(formColumn.hasChoices())
            {
               logger.debug("Column has choices...returning");
               return; //do nothing
            }
            
            
            Collection choices = createChoices(form);
            populateChoices(choices,formColumn, form);
         }
         else if(aFormComponent instanceof FormQuestion) 
         {
         	
            FormQuestion formQuestion = this.retrieveFormQuestion(aFormComponent);
            logger.debug("Populating users for question: "+formQuestion.getPrimaryKey());
            
            
            if(formQuestion.hasChoices()) 
            {
            	logger.debug("Question has choices...returning");
            	return; // do nothing
            }
            
            Collection choices = createChoices(form);
            
            formQuestion.addDynamicChoices(choices);
         }
         else
         {
            throw new SetupException("Do run "+getClass().getName()+" as part of a rule");
         }
      }
      catch (Exception e)
      {
         throw new BreException(Debugger.stackTrace(e));
      }
   }//--------------------------------------------
    /**
    * 
    * @see org.solutions.form.bre.operation.AbstractOperation#mustSkip(org.solutions.form.data.FormComponent)
    */
   public boolean mustSkip(FormComponent aForm)
   {
      return false;
   }//--------------------------------------------
   /**
    * 
    * @param form the form that is used to created choices
    * @return
    */
   private Collection createChoices(Form form)
   throws TransformerException, IOException
   {

      return (Collection)super.readObjectFromPost(form);      
   }//--------------------------------------------
   public Map getHeaderMap()
   {
      return headerMap;
   }// --------------------------------------------



   /**
    * @param headerMap the headerMap to set
    */
   public void setHeaderMap(Map headerMap)
   {
      this.headerMap = headerMap;
   }
   
   private Map headerMap = null;
  

   static final long serialVersionUID = 2;

}
