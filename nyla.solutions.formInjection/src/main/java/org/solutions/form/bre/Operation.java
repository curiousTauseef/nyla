package org.solutions.form.bre;

import nyla.solutions.global.data.PrimaryKey;

import org.solutions.form.data.FormComponent;
import org.solutions.form.exception.BreException;

/**
 * 
 * <pre>
 * An operation represents an action to be performed of a question 
 * within a form. The operation concept is based on the command design pattern. 
 * Operations implements an execute method. It contains custom logic to satisfy 
 * the needed business processing.
 * 
 * Business operations deal with processing related to the retrieving data from 
 * the database and general logic that can only be performed on the server. 
 * 
 * For example, a business operation can populate a question on the current 
 * form with an answer a previous submitted form. Scripts should be used for 
 * client-side processing or offline form handling. Business rules provide a 
 * link between expressions and operations. Operations are executed when the 
 * form is retrieved for editing (in order to auto fill question answers see 
 * section 2.2 RESPONSE_TYPE.RESPONSE_OPERATION_ID) and when saving and 
 * submitting a form. Note that the complete list of operations will be 
 * maintained in a separate document (see GCSM Form Questionnaire spreadsheet). '
 * 
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public interface Operation extends PrimaryKey
{
   /**
    * 
    * @param aFormComponent execute the 
    * @throws BreException 
    */
   public void execute(FormComponent aFormComponent)
   throws BreException;
   
   /**
    * 
    * @param aFormComponent the form component
    * @return true if process must be skip for the given form
    */
   public boolean mustSkip(FormComponent aFormComponent);
   
   /**
    * Set the operations' primary key
    * @param aPrimaryKey the primary key 
    */
   public void setPrimaryKey(int aPrimaryKey);
   
   /**
    * Set the operation primary key
    * @param aPrimaryKey the pk
    */
   public void setPrimaryKey(Integer aPrimaryKey);
   
   /**
    * Initialize the operation with a array of operation parameters
    * @param aOperationParameters
    */
   public void initialize(OperationParameter[] aOperationParameters);
   
   /**
    * 
    * @param aOperationParameter the operation attribute to remove
    * @return boolean true if removed
    */
   public boolean removeParameter(OperationParameter aOperationParameter);
   
   /**
    * 
    * @return the return the operation name
    */
   public String getName();
   
   /**
    * Set the operation name
    * @param aName the operation name
    */
   public void setName(String aName);
   
   

}
