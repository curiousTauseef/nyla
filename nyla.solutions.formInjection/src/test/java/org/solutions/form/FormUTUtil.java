package org.solutions.form;

import java.util.ArrayList;
import java.util.List;

import org.solutions.form.data.FormProperty;

/**
 * <pre>
 * FormUTUtil testing utility class
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class FormUTUtil
{
   /**
    * Calls constructForm(aFormBuilder,1)
    * @param aFormBuilder the form builder
    */
   public static void constructForm(FormBuilder aFormBuilder)
   {
      constructForm(aFormBuilder,1);
   }// --------------------------------------------
   /**
    * Construct form with formTypeCode =  "TEST"
    * 
    * @param aFormBuilder the form builder 
    * @param aFormID form PK
    */
   public static void constructForm(FormBuilder aFormBuilder, int aFormID)
   {
      constructForm(aFormBuilder, aFormID, "TEST");
   }// --------------------------------------------

   public static void constructForm(FormBuilder aFormBuilder, int aFormID, String aFormTypeCode)
   {
      
      ArrayList formProperties = new ArrayList();
      FormProperty formProperty = new FormProperty();
      formProperty.setKey("status");
      formProperty.setValue("OPENED");
      formProperties.add(formProperty);
      
      aFormBuilder.buildForm(aFormID, aFormTypeCode, formProperties);
      String questionText = "This is a question";
      int questionID = 1;
      int questionNumber = 1;
      String responseTypeCode = "text";
      int sectionNumber = 1;
      
      aFormBuilder.buildQuestion(questionText, aFormTypeCode, questionID++, questionNumber,  
      responseTypeCode, sectionNumber, null, null);
      
      aFormBuilder.buildQuestion(questionText + "2", aFormTypeCode, questionID++, questionNumber, 
      responseTypeCode, sectionNumber, null, null);
      
      String answerValue = "Answer";
      List properties = null;
      Integer responseTableId = null; 
      Integer columnNumber  = null;
      Integer rowNumber = null;
      
      aFormBuilder.buildAnswer(answerValue+" 1", properties, 
      1, responseTableId, columnNumber, rowNumber);
      
      aFormBuilder.buildAnswer(answerValue+" 2", properties, 
      2, responseTableId, columnNumber, rowNumber);

   }// --------------------------------------------

   public static void constructFormWithTableQuestion(FormBuilder aFormBuilder, int aFormID, String aFormTypeCode, int questionID)
   {
      
      ArrayList formProperties = new ArrayList();
      FormProperty formProperty = new FormProperty();
      formProperty.setKey("status");
      formProperty.setValue("OPENED");
      formProperties.add(formProperty);
      
      aFormBuilder.buildForm(aFormID, aFormTypeCode, formProperties);
      String questionText = "This is a question";    
      int questionNumber = 1;
      String responseTypeCode = "table";
      int sectionNumber = 1;
      
      aFormBuilder.buildQuestion(questionText, aFormTypeCode, questionID, questionNumber,  
      responseTypeCode, sectionNumber, null, null);
      
      
      String answerValue = "Table Answer";
      List properties = null;
      Integer responseTableId = new Integer(1); 
      Integer columnNumber  = new Integer(0);
      Integer rowNumber = new Integer(0);
      
      aFormBuilder.buildAnswer(answerValue+" 1", properties, 
      questionID, responseTableId, columnNumber, rowNumber);
      

   }// --------------------------------------------

}
