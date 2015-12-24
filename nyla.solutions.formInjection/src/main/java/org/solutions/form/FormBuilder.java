package org.solutions.form;

import java.util.List;

/**
 * <pre>
 * FormBuilder builts form objects
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public abstract class FormBuilder extends QuestionaireBuilder
{
   /**
    * 
    * @param aAnswerValue the answer
    * @param aProperties the answer properties
    * @param aQuestionId the question ID
    * @param aResponseTableId the response table
    * @param aColumnNumber the column number
    * @param aRowNumber the row Number
    */
   public void buildAnswer(String aAnswerValue, List aProperties,
                           int aQuestionId, Integer aResponseTableId,Integer aColumnNumber, Integer aRowNumber)
   {}
   
   /**
    * 
    * Constructor for ApplicationBuilder initalizes internal 
    * data settings.
    * @param aForm
    */
   public void buildForm(int aFormID, String aFormTypeCode, List formProps )
   {}
}
