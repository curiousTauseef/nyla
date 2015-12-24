package org.solutions.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import nyla.solutions.global.data.Attribute;
import nyla.solutions.global.data.Property;
import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.security.data.SecurityCredential;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Constants;
import nyla.solutions.global.web.validation.Validation;

import org.solutions.form.bre.BreGuide;
import org.solutions.form.data.AnswerProperty;
import org.solutions.form.data.Column;
import org.solutions.form.data.Form;
import org.solutions.form.data.FormAnswer;
import org.solutions.form.data.FormQuestion;
import org.solutions.form.data.FormRow;
import org.solutions.form.data.FormTable;
import org.solutions.form.data.Question;
import org.solutions.form.data.QuestionAttribute;
import org.solutions.form.data.QuestionChoice;
import org.solutions.form.data.ResponseType;
import org.solutions.form.data.ResponseTypeAttribute;
//import org.solutions.ipresentation.core.web.validation.Validation;

/**
 * <pre>
 * 
 *  FormGuide provides interfaces and constants to manage FORMS
 *  
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public abstract class FormGuide implements BreGuide
{
   /**
    * Not available string
    */

   public static final String NA = "";
   
   /**
    * MAX_TABLE_ROW_PROP_NM = "form.max.table.row.capacity"
    */
   public static final String MAX_TABLE_ROW_PROP_NM = "form.max.table.row.capacity";
   
   /**
    * DEFAULT_MAX_TABLE_ROW_CAPACITY = 500
    */
   public static final int DEFAULT_MAX_TABLE_ROW_CAPACITY = 200;
   
   /**
    * MAX_TABLE_ROW_CAPACITY = Config.getPropertyInteger(MAX_TABLE_ROW_PROP_NM, DEFAULT_MAX_TABLE_ROW_CAPACITY).intValue()
    */
   public static final int MAX_TABLE_ROW_CAPACITY = Config.getPropertyInteger(MAX_TABLE_ROW_PROP_NM, DEFAULT_MAX_TABLE_ROW_CAPACITY).intValue();
   
   /**
    * INCOMPLETE_STATUS_PK = 1
    */
   public static final int INCOMPLETE_STATUS_PK = 1;
   
   /**
    * INTEGER_MAX_VALUE = new Integer(Integer.MAX_VALUE).toString().length()
    */
   public static final int INTEGER_MAX_VALUE = String.valueOf(Integer.MAX_VALUE).length();
   /**
    * RELATED_FORM_ID_PROP_NM = "relatedFormID"
    */
   public static final String RELATED_FORM_ID_PROP_NM = "relatedFormID";
   
   /**
    * AUTO_COUNTER_ATTRIB_NM = "counter"
    */
   public static final String AUTO_COUNTER_ATTRIB_NM = "counter";
   
   
   /**
    * FIXED_ATTRIB_NM 
    */
   public static final String FIXED_ATTRIB_NM = "fixed";
   
   /**
    * OFFLINE_INPUT_PREFIX = "%INPUT:"
    */
   public static final String OFFLINE_INPUT_PREFIX = "%INPUT:";

   /**
    * OFFLINE_INPUT_SUFFIX = "%"
    */
   public static final String OFFLINE_INPUT_SUFFIX = "%";

   /**
    * COMPLETE_USER_NAME_PROP = "completeUserName"
    */
   public static final String COMPLETE_USER_NAME_PROP = "completeUserName";

   /**
    * CREATE_USER_CATEGORY = "createUser"
    */
   public static final String CREATE_USER_CATEGORY = "createUser";

   /**
    * REVIEWER_USER_CATEGORY = "reviewer"
    */
   public static final String REVIEWER_USER_CATEGORY = "reviewer";

   /**
    * COMPLETE_USER_LOGIN_ID_PROP = "completeUserLoginID"
    */
   public static final String COMPLETE_USER_LOGIN_ID_PROP = "completeUserLoginID";

   /**
    * COMPLETE_USER_ROLE_PROP = "completeUserRole"
    */
   public static final String COMPLETE_USER_ROLE_PROP = "completeUserRole";

   /**
    * SUBMIT_USER_NAME_PROP = "submitUserName"
    */
   public static final String SUBMIT_USER_NAME_PROP = "submitUserName";

   /**
    * SUBMIT_USER_ROLE_PROP = "submitUserRole"
    */
   public static final String SUBMIT_USER_ROLE_PROP = "submitUserRole";

   /**
    * SUBMIT_USER_LOGIN_ID_PROP = "submitUserLoginID"
    */
   public static final String SUBMIT_USER_LOGIN_ID_PROP = "submitUserLoginID";

   /**
    * EVENT_ATTRIB_NM = "event"
    *  
    */
   public static final String EVENT_ATTRIB_NM = "event";

   /**
    * MULTI_SELECT_LABEL_PROP_NM = "multiSelectLabel"
    */
   public static final String MULTI_SELECT_LABEL_PROP_NM = "multiSelectLabel";

   /**
    * MULTI_SELECT_ACTION_LABEL_PROP = "multiSelectAction"
    */
   public static final String MULTI_SELECT_ACTION_LABEL_PROP = "multiSelectAction";

   /**
    * DEFAULT_MULTI_SELECT_ACTION_LABEL = "Edit"
    */
   public static final String DEFAULT_MULTI_SELECT_ACTION_LABEL = "Edit";

   /**
    * TARGET_RESPONSE_TYPE_PK_PROP_BOOL = "targetResponseTypePK"
    */
   public static final String TARGET_RESPONSE_TYPE_PK_PROP_BOOL = "targetResponseTypePK";

   /**
    * TARGET_TRUE_COLUMN_PROP_BOOL "targetTrueColumnAttributeBool"
    */
   public static final String TARGET_TRUE_COLUMN_ATTRIB_BOOL = "targetTrueColumnAttributeBool";

   /**
    * OPERATION_PK_PARAM_NM = "operationPK"
    */
   public static final String OPERATION_PK_PARAM_NM = "operationPK";

   /**
    * TARGET_TRUE_QUESTION_PROP_BOOL = "targetTrueQuestionPropBool"
    */
   public static final String TARGET_TRUE_QUESTION_PROP_BOOL = "targetTrueQuestionPropBool";

   /**
    * TABLE_PK_PREFIX = "tablePK="
    */
   public static final String TABLE_PK_PREFIX = "tablePK=";

   /**
    * CHOICE_ID_SEPARATORS= ", "
    */
   public static final String CHOICE_ID_SEPARATORS = ",";

   /**
    * COLUMN_NUMBER_START = "["
    */
   public static final String COLUMN_NUMBER_START = "[";

   /**
    * COLUMN_NUMBER_END = "]"
    */
   public static final String COLUMN_NUMBER_END = "]";

   /**
    * REQUIRED_PROPERTY_NM = "required"
    */
   public static final String REQUIRED_PROPERTY_NM = "required";

   /**
    * NUMBERED_PROPERTY_NM = "numbered"
    */
   public static final String NUMBERED_PROPERTY_NM = "numbered";

   /**
    * CAN_DELETE_ROW_PROPERTY_NM = "canDeleteRow"
    */
   public static final String CAN_DELETE_ROW_PROPERTY_NM = "canDeleteRow";

   /**
    * MULTIPLE_PROP_NM = "multiple"
    */
   public static final String MULTIPLE_PROP_NM = "multiple";

   /**
    * COMMENTED_PROPERTY_NM = "commented"
    */
   public static final String COMMENTED_PROPERTY_NM = "commented";

   /**
    * DESCRIPTION_PROP_NM = "description"
    */
   public static final String  DESCRIPTION_PROP_NM = "description";
   
   /**
    * PARENT_ID_PROPERTY_NM = "parentId"
    */
   public static final String PARENT_ID_PROPERTY_NM = "parentId";
   
   /**
    * MAX_ANSWER_VALUE_LENGTH = 2000
    */
   public static final int MAX_ANSWER_VALUE_LENGTH = 2000;

   /**
    * Target question primay key property
    */
   public static final String TARGET_QUESTION_PK_PROP = "targetQuestionPK";

   /**
    * Incomplete form status
    */
   public static final String INCOMPLETE_STATUS = "Incomplete";

   /**
    * submitted form status
    */
   public static final String SUBMIT_STATUS = "Submitted";

   /**
    * complete form status
    */
   public static final String COMPLETE_STATUS = "Complete";

   /**
    * approved form status
    */
   public static final String APPROVED_STATUS = "Approved";

   /**
    * rejected form status
    */
   public static final String REJECTED_STATUS = "Rejected";

   /**
    * MIN_LENGTH_ATTRIB_NM = minLength"
    */
   public static final String MIN_LENGTH_ATTRIB_NM = "minLength";

   /**
    * MAX_LENGTH_ATTRIB_NM = "maxLength"
    */
   public static final String MAX_LENGTH_ATTRIB_NM = "maxLength";

   /**
    * FORMAT_ATTRIB_NM = "format"
    */
   public static final String FORMAT_ATTRIB_NM = "format";

   /**
    * ERROR_MESSAGE_ATTRIB_NM = "errorMessage"
    */
   public static final String ERROR_MESSAGE_ATTRIB_NM = "errorMessage";

   /**
    * IS_REQUIRED_ATTRIB_NM = "required"
    */
   public static final String IS_REQUIRED_ATTRIB_NM = "required";

   /**
    * READONLY_ATTRIB_NM = "readOnly"
    */
   public static final String READONLY_ATTRIB_NM = "readOnly";

   /**
    * READONLY_ATTRIB_NM = "readOnly"
    */
   public static final String READONLY_AFTER_ANSWER_ATTRIB_NM = "readOnlyAfterAnswer";
   
   /**
    * HORIZONTAL_TABLE_PROP_NM = "horizontalTable"
    */
   public static final String HORIZONTAL_TABLE_ATTRIB_NM = "horizontalTable";
   
   /**
    * NEEDED_ATTRIB_NMs_4_ANSWER_PROP = "form.needed.attribute.names.for.answer"
    */
   public static final String NEEDED_ATTRIB_NMs_4_ANSWER_PROP = "form.needed.attribute.names.for.answer";
   
   /**
    * By default only the help text is copied to answer properties. 
    * We can add additional attribute names to the configuration property  
    * "form.needed.attribute.names.for.answer" (separated by spaces).
 
    * @return "helpText_"
    */
   public static final String neededAttributeNamesForAnswers()
   {
      return Config.getProperty(NEEDED_ATTRIB_NMs_4_ANSWER_PROP, "helpText_");
   }//--------------------------------------------
   /**
    * Determine if the form status is "Submitted" or "Approved" or "Completed"
    * 
    * @param aForm
    * @return !FormGuide.INCOMPLETE_STATUS.equals(status.getName())
    */
   public static boolean isSubmitted(Form aForm)
   {
      if (aForm == null)
         return false;

      return aForm.getStatusPK() > INCOMPLETE_STATUS_PK;
      
      //Status status = aForm.getStatus();
      //if (status == null)
      //return !FormGuide.INCOMPLETE_STATUS.equalsIgnoreCase(status.getName());
   }//--------------------------------------------

   /**
    * Determine if the form is complete
    * 
    * @param aForm
    *           the form
    * @return FormGuide.COMPLETE_STATUS.equals(aForm.getStatusName())
    */
   public static boolean isCompleted(Form aForm)
   {
      return FormGuide.COMPLETE_STATUS.equalsIgnoreCase(aForm.getStatusName());
   }//--------------------------------------------

   /**
    * Add question attributes to answer
    * 
    * @param aAnswer
    *           the answer to add attributes
    * @param question
    *           the question to get attributes form
    */
   /*
   public static void addQuestionAttributeToAnswer(Answer aAnswer,
                                                   Question question)
   {
      if(question == null || aAnswer == null)
         return;
      
      //Get question attributes
      Collection questionAttributes = question.getAttributesForAnswers();
      if (Organizer.isEmpty(questionAttributes))
      {
         return; //do nothing because question attributes are empty
      }
      //loop thriw question attributes
      QuestionAttribute questionAttribute = null;
      for (Iterator i = questionAttributes.iterator(); i.hasNext();)
      {
         questionAttribute = (QuestionAttribute) i.next();
         //add answer property
         aAnswer.addProperty(questionAttribute.getName(),
         (Serializable) questionAttribute.getValue());
      }
   }//--------------------------------------------
   */

   /**
    * 
    * @param aForm
    *           the form
    * @param aAttribute
    *           attribute name/value to compare
    * @return
    */
   /*
   public static Collection findColumnsWithAttribute(Form aForm,
                                                     Attribute aAttribute)
   {
      Questionaire questionaire = aForm.getQuestionaire();
      Column column = null;
      String name = aAttribute.getName();
      QuestionAttribute columnAttribute = null;

      ArrayList results = new ArrayList(10);
      String value = String.valueOf(aAttribute.getValue());
      for (Iterator i = questionaire.getColumns().iterator(); i.hasNext();)
      {
         column = (Column) i.next();
         columnAttribute = column.findAttributeByName(name);

         if (columnAttribute != null
         && value.equalsIgnoreCase(String.valueOf(columnAttribute.getValue())))
         {
            results.add(column);
         }
      }

      results.trimToSize();

      return results;
   }//--------------------------------------------
   */

   /**
    * Search form looking for question with response type PK
    * 
    * @param aForm
    *           the form to search
    * @param aResponseTypePK
    *           the response type primary key
    * @return formQuesitons
    */
   /*
   public static Collection findQuestionsWithResponseTypePK(
                                                            Form aForm,
                                                            Integer aResponseTypePK)
   {
      if (aResponseTypePK == null)
         throw new IllegalArgumentException(
         "aResponseTypePK required in FormGuide.findQuestionsWithResponseTypePK");

      return findQuestionsWithResponseTypePK(aForm, aResponseTypePK.intValue());
   }//--------------------------------------------
   */

   /**
    * 
    * @param aFormTable
    *           the form table
    * @param aColumnNumber
    *           the column number
    * @param aOptionName
    *           the option
    * @return QuestionChoice in the column with matching choice name
    * @throws BreException
    * @throws NoDataFoundException
    */
   /*
   public static QuestionChoice findColumnQuestionChoiceByColumnNbrAndChoiceNM(
                                                                               FormTable aFormTable,
                                                                               int aColumnNumber,
                                                                               String aOptionName) throws NoDataFoundException
   {
      if(aFormTable == null)
         throw new IllegalArgumentException("aFormTable required in FormGuide.findColumnQuestionChoiceByColumnNbrAndChoiceNM");
      
      Form form = aFormTable.retrieveForm();
      
      FormColumn formColumn = form.findFormColumnByTablePKAndColumnNumber(aFormTable.getPrimaryKey(), aColumnNumber);
      

      ResponseType responseType = formColumn.getResponseType();
      
      if (!formColumn.hasChoices() && responseType.hasOperation())
      {
         try
         {
            //populate options
            responseType.getOperationBluePrint().getOperation()
            .execute(formColumn);
         }
         catch (BreException e)
         {
            new NoDataFoundException(" " + formColumn
            + " bre oeperation exception " + e);
         }
      }

      return formColumn.findQuestionChoicesByByName(aOptionName);
   }//--------------------------------------------
   */

   /**
    * Search form looking for question with response type PK
    * 
    * @param aForm
    *           the form to search
    * @param aResponseTypePK
    *           the response type primary key
    * @return formQuesitons
    */
   /*
   public static Collection findQuestionsWithResponseTypePK(Form aForm,
                                                            int aResponseTypePK)
   {
      if (aForm == null || aResponseTypePK < 1)
         throw new IllegalArgumentException(
         "aForm and valid aResponseTypePK required in FormGuide.findQuestionsWithResponseTypePK");


      ArrayList results = new ArrayList();
      FormQuestion formQuestion = null;
      for (Iterator i = aForm.getFormQuestions().iterator(); i.hasNext();)
      {
         formQuestion = (FormQuestion) i.next();

         if (formQuestion.getResponseType().getPrimaryKey() == aResponseTypePK)
            results.add(formQuestion);
      }

      return results;
   }//--------------------------------------------
   */

   /**
    * find Form Questions With matching Attribute or response type attribute
    * name/value
    * 
    * @param aForm
    *           the form to look in
    * @param aAttribute
    *           the match attribute name/value
    * @return collection of formQuestions
    */
   public static Collection findQuestionsWithAttribute(Form aForm,
                                                       Attribute aAttribute)
   {
      if (aForm == null || aAttribute == null)
         throw new IllegalArgumentException(
         "aForm && aAttribute required in FormGuide.findQuestionsWithProperty");

      return aForm.findQuestionsWithAttribute(aAttribute.getName(), aAttribute.getValue());
   }//--------------------------------------------
   /**
    * 
    * @see org.solutions.form.FormGuide#retrieveMaxLength(org.solutions.form.data.Question)
    */
   public static int retrieveMaxLength(FormQuestion aQuestion)
   {
      QuestionAttribute attribute = aQuestion.findAttributeByName(MAX_LENGTH_ATTRIB_NM);
      
      if(attribute == null)
         return defaultMaxLengthFor(aQuestion.getResponseType());
      else
         return new Integer(String.valueOf(attribute.getValue()).trim()).intValue();
   }//--------------------------------------------
   /**
    * 
    * @see org.solutions.form.FormGuide#retrieveMaxLength(org.solutions.form.data.Question)
    */
   public static int retrieveMaxLength(FormAnswer aAnswer)
   {
      QuestionAttribute attribute = aAnswer.findAttributeByName(MAX_LENGTH_ATTRIB_NM);
      
      if(attribute == null)
         return defaultMaxLengthFor(aAnswer.getResponseType());
      else
         return new Integer(String.valueOf(attribute.getValue()).trim()).intValue();
   }//--------------------------------------------   

   /**
    * 
    * @param aAnswer
    *           the answer
    * @return Maximum expected length
    */
   public static int retrieveMaxLength(ResponseType aResponseType)
   {
      ResponseTypeAttribute attribute = aResponseType.findAttributeByName(FormGuide.MAX_LENGTH_ATTRIB_NM);
      
      if(attribute == null)
         return defaultMaxLengthFor(aResponseType);
       else
          return new Integer(String.valueOf(attribute.getValue())).intValue();
   }//--------------------------------------------
   public static boolean isTrue(Attribute aAttribute)
   {
      return aAttribute != null && 
         Boolean.TRUE.toString()
          .equalsIgnoreCase(String.valueOf(aAttribute.getValue()));
   }//--------------------------------------------
   /**
    * 
    * @see org.solutions.form.FormGuide#retrieveMinLength(org.solutions.form.data.Question)
    */
   public static int retrieveMinLength(Question aQuestion)
   {
      ResponseType responseType = retrieveResponseType(aQuestion);

      return retrieveMinLength(responseType);
   }//--------------------------------------------

   public static int retrieveMinLength(FormAnswer aAnswer)
   {
      ResponseType responseType = aAnswer.getResponseType();

      return retrieveMinLength(responseType);
   }//--------------------------------------------
   /**
    * @param responseType
    * @return
    */
   public static int retrieveMinLength(ResponseType responseType)
   {
      return getAttributeInteger(responseType, MIN_LENGTH_ATTRIB_NM,
      defaultMinLengthFor(responseType));
   }//--------------------------------------------

   /**
    * 
    * @see org.solutions.form.FormGuide#retrieveFormat(org.solutions.form.data.Question)
    */
   public static String retrieveFormat(Question aQuestion)
   {
      ResponseType responseType = retrieveResponseType(aQuestion);

      return retrieveFormat(responseType);
   }//--------------------------------------------

   public static String retrieveFormat(FormAnswer aAnswer)
   {
      ResponseType responseType = aAnswer.getResponseType();

      return retrieveFormat(responseType);
   }//--------------------------------------------

   /**
    * @param responseType
    * @return
    */
   public static String retrieveFormat(ResponseType responseType)
   {
      return getAttributeText(responseType, FORMAT_ATTRIB_NM, "");
   }//--------------------------------------------

   /**
    * 
    * @see org.solutions.form.FormGuide#retrieveValidationErrorMessage(org.solutions.form.data.Question)
    */
   public static String retrieveValidationErrorMessage(Question aQuestion)
   {
      ResponseType responseType = retrieveResponseType(aQuestion);

      return retrieveValidationErrorMessage(responseType);
   }//--------------------------------------------

   public static String retrieveValidationErrorMessage(FormAnswer aAnswer)
   {
      ResponseType responseType = aAnswer.getResponseType();

      return retrieveValidationErrorMessage(responseType);
   }//--------------------------------------------

   /**
    * @param responseType
    * @return
    */
   public static String retrieveValidationErrorMessage(
                                                        ResponseType responseType)
   {
      return getAttributeText(responseType, ERROR_MESSAGE_ATTRIB_NM, "");
   }//--------------------------------------------
   /**
    * Determine if the form table's questions has a response type or question attribute
    * with a value of "true"
    * @param aFormTable the form table
    * @return fixed attribute = true
    */
   public static boolean isFixed(FormTable aFormTable)
   {
      if (aFormTable == null)
         throw new IllegalArgumentException(
         "aFormTable required in FormGuide.isHorizontal");

      FormQuestion question = aFormTable.getFormQuestion();
      if (question == null)
         throw new IllegalArgumentException(
         "aFormTable.retrieveQuestion() required in FormGuide.isFixed");
      
      QuestionAttribute fixed = question.findAttributeByName(FormGuide.FIXED_ATTRIB_NM);
      
      return fixed != null && 
             Boolean.TRUE.toString()
             .equalsIgnoreCase(String.valueOf(fixed.getValue()));
   }//--------------------------------------------
   /**
    * 
    * @param aQuestion the question
    * @return true is question has an attribute "numbered" with value=true
    */
   public static boolean isNumbered(FormQuestion aQuestion)
   {
      if (aQuestion == null)
         throw new IllegalArgumentException(
         "aQuestion required in FormGuide.isNumbered");
      
      QuestionAttribute numbered = aQuestion.findAttributeByName(FormGuide.NUMBERED_PROPERTY_NM);
      
      return numbered != null &&
          Boolean.TRUE.toString().equalsIgnoreCase(String.valueOf(numbered.getValue()));
   }//--------------------------------------------
   /**
    * 
    * @param aFormTable
    * @return getAttributeBoolean(aFormTable.question.responseType,FormGuide.HORIZONTAL_TABLE_PROP_NM,false)
    */
   public static boolean isHorizontal(FormTable aFormTable)
   {
      if (aFormTable == null)
         throw new IllegalArgumentException(
         "aFormTable required in FormGuide.isHorizontal");

      Question question = aFormTable.getFormQuestion();
      if (question == null)
         throw new IllegalArgumentException(
         "aFormTable.retrieveQuestion() required in FormGuide.isHorizontal");

      ResponseType responseType = retrieveResponseType(question);
      return getAttributeBoolean(responseType,
      FormGuide.HORIZONTAL_TABLE_ATTRIB_NM, false);
   }//--------------------------------------------

   /**
    * 
    * @return true if question is required
    */
   public static boolean retrieveIsRequired(FormQuestion aQuestion)
   {
      return aQuestion.isRequired();

   }//--------------------------------------------
   /**
    * 
    * @return this.attributeMap.get(FormGuide.REQUIRED_PROPERTY_NM) = true
    */
   public static final boolean isRequired(Column aColumn)
   {  
      QuestionAttribute attribute = aColumn.findAttributeByName(FormGuide.REQUIRED_PROPERTY_NM);
      return isAttributeTrue(attribute);
   }//-------------------------------------------------
   
   /**
    * @param aAttribute
    * @return
    */
   public static boolean isAttributeTrue(QuestionAttribute aAttribute)
   {
      return aAttribute != null &&
             Boolean.TRUE.toString().toLowerCase().equals(
             String.valueOf(aAttribute.getValue()).toLowerCase());
   }//--------------------------------------------

   public static final boolean isRequired(FormAnswer aAnswer)
   {
      QuestionAttribute attribute = aAnswer.findAttributeByName(
      FormGuide.REQUIRED_PROPERTY_NM);

      return attribute != null
      && Boolean.TRUE.toString().equalsIgnoreCase(String.valueOf(attribute.getValue()));
   }//-------------------------------------------------

   /**
    * 
    * @see org.solutions.form.FormGuide#retrieveTypeName(org.solutions.form.data.Question)
    */
   public static String retrieveTypeName(Question aQuestion)
   {
      return aQuestion.getResponseType().getCode();
   }//----------------------------------------
   /**
    * 
    * @param aColumn
    * @return aColumn.getResponseType().getCode()
    */
   public static String retrieveTypeName(Column aColumn)
   {
      return aColumn.getResponseType().getCode();
   }//----------------------------------------
   /**
    * 
    * @param aResponseType the response type
    * @return aResponseType.getCode()
    */
   public static String retrieveTypeName(ResponseType aResponseType)
   {
      if (aResponseType == null)
         throw new IllegalArgumentException(
         "aResponseType required in FormGuide.retrieveTypeName");
      
      return aResponseType.getCode();
   }//----------------------------------------

   /**
    * 
    * @see org.solutions.form.FormGuide#retrieveTypeName(org.solutions.form.data.Question)
    */
   public static String retrieveTypeName(FormAnswer aAnswer)
   {
      return aAnswer.getResponseType().getCode();
   }//----------------------------------------

   /**
    * Determine whether the row can be deleted
    * 
    * @param aFormRow
    *           the form
    * @return true if row has an answer with property canDeleteRow =true
    */
   public static boolean canDelete(FormRow aFormRow)
   {
       /*
      if (!aFormRow.hasAnswer())
         return false;
         */
       /*
      FormAnswer answer = null;
      Property property = null;
      for (Iterator i = aFormRow.getAnswers().iterator(); i.hasNext();)
      {
         answer = (FormAnswer) i.next();
         if (canDeleteTableAnswer(answer))
            return true;
      }

      return false;
      */
       int rowIndex = aFormRow.getNumber();
       FormTable table = aFormRow.getFormTable();
       if (rowIndex == 0 || table.getForm().isReadOnly())
           return false;
       FormAnswer answer = table.getFormQuestion().getAnswer(rowIndex, 0);
       AnswerProperty canDeleteProp = answer.getProperty(FormGuide.CAN_DELETE_ROW_PROPERTY_NM);
       boolean canDelete = false;
       if (canDeleteProp != null &&
               canDeleteProp.getValue() != null) {
           canDelete = new Boolean(canDeleteProp.getStringValue().toLowerCase()).booleanValue();
       }
       return canDelete;

   }//--------------------------------------------

   /**
    * Find answers by question and column number/.table
    * 
    * @param aForm
    *           the form
    * @param aQuestionPK
    *           the question primary key
    * @param aTablePK
    *           the table primary key
    * @param aColumnNumber
    *           the column number
    * @return the collection of Answers
    */
   public static Collection findAnswersByQuestionAndColumn(Form aForm,
                                                           int aQuestionPK,
                                                           int aTablePK,
                                                           int aColumnNumber)
   {
      if (aForm == null)
         throw new IllegalArgumentException(
         "aForm required in Form.findAnswersByQuestionAndColumn");

      FormAnswer answer = null;
      ArrayList results = new ArrayList(Config.getPropertyInteger(
      Constants.BATCH_LIST_SIZE_PROP, 10).intValue());

      for (Iterator i = aForm.getFormAnswers().iterator(); i.hasNext();)
      {
         answer = (FormAnswer) i.next();
         if (answer.isWithinTable() && answer.getQuestionPK() == aQuestionPK
         && answer.getTablePK().intValue() == aTablePK
         && answer.getColumnNumber().intValue() == aColumnNumber)
         {
            results.add(answer);
         }
      }

      results.trimToSize();

      return results;
   }//--------------------------------------------

   public static Collection findAnswersByQuestionAndColumnWithAnswerValue(
                                                                     Form aForm,
                                                                     int aQuestionPK,
                                                                     int aTablePK,
                                                                     int aColumnNumber,
                                                                     String aAnswerValue)
   {
      if (aForm == null)
         throw new IllegalArgumentException(
         "aForm required in Form.findAnswersByQuestionAndColumn");
      
      if(aAnswerValue == null)
         throw new IllegalArgumentException("aAnswer required in FormGuide.findAnswersByQuestionAndColumnWithAnswer");
      
      aAnswerValue = aAnswerValue.trim();
      
      FormAnswer answer = null;
      ArrayList results = new ArrayList(Config.getPropertyInteger(
      Constants.BATCH_LIST_SIZE_PROP, 10).intValue());
//TODO: look at answers within tables
      for (Iterator i = aForm.getFormAnswers().iterator(); i.hasNext();)
      {
         answer = (FormAnswer) i.next();
         if (answer.isWithinTable() && answer.getQuestionPK() == aQuestionPK
         && answer.getTablePK().intValue() == aTablePK
         && answer.getColumnNumber().intValue() == aColumnNumber
         && String.valueOf(answer.getValue()).trim().equalsIgnoreCase(aAnswerValue))
         {
            results.add(answer);
         }
      }

      results.trimToSize();

      return results;
   }//--------------------------------------------

   /**
    * 
    * @param aForm
    *           the form
    * @return the user role who complete the form
    */
   public static String retrieveCompleteUserRole(Form aForm)
   {
      if (aForm == null)
         throw new IllegalArgumentException(
         "aForm required in FormGuide.retrieveCompleteUserRole");

      return (String) aForm.retrievePropertyValue(COMPLETE_USER_ROLE_PROP);
   }//--------------------------------------------

   /**
    * 
    * @param aForm
    *           the form
    * @return the user role who submitted the form
    */
   public static String retrieveSubmitUserRole(Form aForm) throws NoDataFoundException
   {
      if (aForm == null)
         throw new IllegalArgumentException(
         "aForm required in FormGuide.retrieveSubmitterRole");

      String submitUserRole = (String) aForm
      .retrievePropertyValue(SUBMIT_USER_ROLE_PROP);

      if (submitUserRole == null || submitUserRole.length() == 0)
         throw new NoDataFoundException(
         "submitUserRole not found in form properties ");

      return submitUserRole;
   }//--------------------------------------------

   /**
    * 
    * @param aAnswer
    *           the answer
    * @return true if property != null &&
    *         Boolean.TRUE.toString().equals(property.getValue()
    */
   public static boolean canDeleteTableAnswer(FormAnswer aAnswer)
   {
      if (aAnswer == null)
         return false;

      AnswerProperty property = aAnswer.retrieveProperty(CAN_DELETE_ROW_PROPERTY_NM);
      if (property != null
      && Boolean.TRUE.toString().equalsIgnoreCase(String.valueOf(property.getValue())))
         return true;
      else
         return false;
   }//--------------------------------------------
   /**
    * 
    * @param aForm
    *           the form
    * @param aStatusName
    *           the status name to check
    * @return aStatusName.equals(aForm.getStatusName())
    */
   public static boolean isFormInStatus(Form aForm, String aStatusName)
   {
      if (aForm == null)
         throw new IllegalArgumentException(
         "aForm required in FormGuide.isFormInStatus");

      if (aStatusName == null)
         throw new IllegalArgumentException(
         "aStatusName required in FormGuide.isFormInStatus");

      return aStatusName.equalsIgnoreCase(aForm.getStatusName());
   }//--------------------------------------------
   /**
    * merge Question Attributes And AnswerProperties
    * @param aAnswer
    */
   /*
   public static void mergeQuestionAttributesAndAnswerProperties(Answer aAnswer)
   {
      Question question = aAnswer.getQuestion();
      
      Integer zero = new Integer(0);
      if(!aAnswer.isWithinTable())
      {
        addQuestionAttributeToAnswer(aAnswer, question);
      }
      else if(aAnswer.isWithinTable() &&
              zero.equals(aAnswer.getColumnNumber()) &&
              zero.equals(aAnswer.getRowNumber()))
      {
         //all copy question attributes for the first answer
         //of the first row in the table
         addQuestionAttributeToAnswer(aAnswer, question);
      }
   }//--------------------------------------------
   */

   /**
    * decorate Question Name
    * 
    * @param aQuestion
    *           the question
    * @param isOffline
    *           prefix with %INPUT: if true
    * @return aQuestion.getPrimaryKey() prefixed with the character "q"
    */
   
   public static String createQuestionName(FormQuestion aQuestion, boolean isOffline)
   {

      return createQuestionName(aQuestion, 
      FormGuide.retrieveTypeName(aQuestion),isOffline);
   }//--------------------------------------------
   /**
    * 
    * @param aQuestion
    * @param aTypeCode
    * @param isOffline
    * @return
    */
   public static String createQuestionName(FormQuestion aQuestion, String aTypeCode, boolean isOffline)
   {
      Validation validator = new Validation();


      StringBuffer questionName = new StringBuffer("q"
      + aQuestion.getPrimaryKey());
      validator.setFieldName(questionName.toString());
      validator.setMaxLength(FormGuide.retrieveMaxLength(aQuestion));
      validator.setMinLength(FormGuide.retrieveMinLength(aQuestion));
      validator.setFormat(FormGuide.retrieveFormat(aQuestion));
      validator.setMessage(FormGuide.retrieveValidationErrorMessage(aQuestion));
      validator.setRequiredCode(String.valueOf(FormGuide
      .retrieveIsRequired(aQuestion)));
      validator.setTypeName(aTypeCode);

      String inputName = validator.getInputName();

      if (isOffline)
      {
         //prefix OFFLINE input
         inputName = OFFLINE_INPUT_PREFIX + inputName + OFFLINE_INPUT_SUFFIX;
      }
      return inputName;
   }//--------------------------------------------
   /**
    * Decorator question input with ResponseType.INTEGER_CD type name
    * @param aQuestion the question
    * @param isOffline is offline bool
    * @return createQuestionName(aQuestion, ResponseType.INTEGER_CD, isOffline)
    */
   public static String createIntegerQuestionName(FormQuestion aQuestion, boolean isOffline)
   {
      return createQuestionName(aQuestion, ResponseType.INTEGER_CD, isOffline);
   }//--------------------------------------------
   /**
    * Decorator question input with ResponseType.TEXT_CD type name
    * @param aQuestion the question
    * @param isOffline is offline bool
    * @return createQuestionName(aQuestion, ResponseType.TEXT_CD, isOffline)
    */
   public static String createTextQuestionName(FormQuestion aQuestion, boolean isOffline)
   {
      return createQuestionName(aQuestion, ResponseType.TEXT_CD, isOffline);
   }//--------------------------------------------  
   /**
    * Decorator question input with ResponseType.FLOAT_CD type name
    * @param aQuestion the question
    * @param isOffline is offline bool
    * @return createQuestionName(aQuestion, ResponseType.FLOAT_CD, isOffline)
    */
   public static String createFloatQuestionName(FormQuestion aQuestion, boolean isOffline)
   {
      return createQuestionName(aQuestion, ResponseType.FLOAT_CD, isOffline);
   }//--------------------------------------------
   
   public static boolean hasAnswersWithProperty(FormTable aFormTable,Property aProperty)
   {
      if(aFormTable == null)
         throw new IllegalArgumentException("aFormTable required in FormGuide.hasAnswersWithProperty");
      
      if(aProperty == null)
         throw new IllegalArgumentException("aProperty required in FormGuide.hasAnswersWithProperty");
      
      if(aFormTable.isEmpty())
         return false;
      
      //FormRow formRow = null;
      //for (Iterator i = aFormTable.getRows().iterator(); i.hasNext();)
     /// {
     //    formRow = (FormRow) i.next();
     //    if(hasAnswersWithProperty(formRow.getAnswers(), aProperty))
      //   {
      //      return true;
      //   }
      //}
      
      //Fix
      return hasAnswersWithProperty(aFormTable.getAnswers(), aProperty);
         
      //return false;
   }//--------------------------------------------
   /**
    * 
    * @param aAnswers
    * @param aProperty
    * @return
    */
   public static boolean hasAnswersWithProperty(Collection aAnswers, Property aProperty)
   {
      if(aAnswers == null || aAnswers.isEmpty())
         return false;
      
      
      FormAnswer answer = null;
      for (Iterator i = aAnswers.iterator(); i.hasNext();)
      {
         answer = (FormAnswer) i.next();
         if(answer.hasProperty(aProperty.getName(), aProperty.getValue()))
         {
            return true;
         }
      }
      
      return false;
   }//--------------------------------------------
   /**
    * 
    * @param aAnswer the answer to delete
    * @return
    */
   public static final void deleteAnswer(Form aForm, FormAnswer aAnswer, SecurityCredential aUser)
   throws NoDataFoundException
   {
      aForm.deleteAnswer(aAnswer);
   }//--------------------------------------------
   /**
    * 
    * @param aFormRow
    * @param aUser
    */
   public static final void deleteRow(Form aForm, FormRow aFormRow, SecurityCredential aUser)
   throws NoDataFoundException
   {
      if(aFormRow == null)
         return;
      
      aFormRow.getFormTable().deleteRow(aFormRow.getNumber());
   }//--------------------------------------------
   /**
    * 
    * @param aAnswer the answer to retrieve text
    * @return text version of answer
    */
   public static final String toText(FormAnswer aAnswer)
   {
      if (aAnswer == null)
         throw new IllegalArgumentException(
         "aAnswer required in FormGuide.toText");
      
      ResponseType responseType = aAnswer.getResponseType();
      
      if(responseType == null || !responseType.isSelectable())
      {
         return String.valueOf(aAnswer.getValue());
      }
      
      //response type is selectable
      StringBuffer choicesText = new StringBuffer();     
         
     //decorate choices
     QuestionChoice questionChoice = null;
     Collection questionChoices = null;

     questionChoices =  aAnswer.retrievePickedChoices();
         
     if(questionChoices == null || questionChoices.isEmpty())
        return "";
     
     //loop thru question choices
     for (Iterator i = questionChoices.iterator(); i.hasNext();)
     {
          questionChoice = (QuestionChoice) i.next();
                
          choicesText.append(questionChoice.getText());
             
          if(i.hasNext())
          {
             choicesText.append(FormGuide.CHOICE_ID_SEPARATORS);
          }
      }//end for
     
     return choicesText.toString();
   }//--------------------------------------------
   /**
    * 
    * @param aResponseType
    *           RESPONSE type
    * @param aAttributeName
    *           attribute name
    * @param aDefault
    *           the integer
    * @return attribute integer
    */
   private static int getAttributeInteger(ResponseType aResponseType,
                                          String aAttributeName, int aDefault)
   {
      if (!aResponseType.hasAttributes())
      {
         return aDefault;
      }

      ResponseTypeAttribute attribute = aResponseType.findAttributeByName(aAttributeName);
      if (attribute == null)
         return aDefault;

      Object value = attribute.getValue();

      if (value == null || value.toString().length() == 0)
         return aDefault;

      return Integer.valueOf(value.toString()).intValue();

   }//--------------------------------------------

   /**
    * 
    * @param aResponseType
    * @param aAttributeName
    * @param aDefault
    * @return boolean value for attribute name
    */
   private static boolean getAttributeBoolean(ResponseType aResponseType,
                                              String aAttributeName,
                                              boolean aDefault)
   {
      if (aResponseType == null)
         throw new IllegalArgumentException(
         "aResponseType required in FormGuide.getAttributeBoolean");

      if (!aResponseType.hasAttributes())
      {
         return aDefault;
      }

      ResponseTypeAttribute attribute = aResponseType.findAttributeByName(aAttributeName);
      if (attribute == null)
         return aDefault;

      Object value = attribute.getValue();

      if (value == null || value.toString().length() == 0)
         return aDefault;

      return Boolean.valueOf(value.toString()).booleanValue();

   }//--------------------------------------------
   /**
    * 
    * @param aResponseType
    *           the response type
    * @param aAttributeName
    *           the attribute name
    * @param aDefault
    *           the default value to use
    * @return
    */
   private static String getAttributeText(ResponseType aResponseType,
                                          String aAttributeName, String aDefault)
   {
      if (!aResponseType.hasAttributes())
      {
         return aDefault;
      }

      ResponseTypeAttribute attribute = aResponseType.findAttributeByName(aAttributeName);
      if (attribute == null)
         return aDefault;

      Object value = attribute.getValue();

      if (value == null || value.toString().length() == 0)
         return aDefault;

      return value.toString();
   }//--------------------------------------------

   public static int defaultMaxLengthFor(ResponseType aResponseType)
   {
      if (aResponseType.isNumeric())
         return INTEGER_MAX_VALUE;
      else if(aResponseType.isTable())
         return MAX_TABLE_ROW_CAPACITY;
      else
         return MAX_ANSWER_VALUE_LENGTH;
   }//--------------------------------------------

   private static int defaultMinLengthFor(ResponseType aResponseType)
   {
      return 0;
   }//--------------------------------------------
   /**
    * 
    * @param aAnswer the answer to retrieve answer from
    * @return collection of question choices
    */
   public static Collection retrieveQuestionChoices(FormAnswer aAnswer)
   {
      //decorate choices
      Collection questionChoices = aAnswer.getQuestionChoices();
      /*
      try
      {
         // patch for defect where choices are not being populated
         if((questionChoices == null || questionChoices.isEmpty()) 
            && aAnswer.hasOperation())
         {  
            Operation autoFillOperation = aAnswer.retrieveOperationBluePrint().getOperation();
            
            if(aAnswer.isWithinTable())
            {
               autoFillOperation.execute(aAnswer.getFormColumn());  
            }
            else
            {
               autoFillOperation.execute(aAnswer.getFormQuestion());
            }
            
            questionChoices = aAnswer.getQuestionChoices();
         }
      }
      catch (Exception e)
      {
         Debugger.getCategory(FormGuide.class).warn(Debugger.stackTrace(e));
      }
      */
      return questionChoices;
   }//--------------------------------------------
   /**
    * @param aQuestion
    *           the questionm
    * @return response type
    */
   private static ResponseType retrieveResponseType(Question aQuestion)
   {
      if (aQuestion == null)
         throw new IllegalArgumentException(
         "aQuestion required in AbstractFormGuide.retrieveMaxLength");

      ResponseType responseType = aQuestion.getResponseType();
      if (responseType == null)
         throw new IllegalArgumentException(
         "aQuestion.getResponseType() required in AbstractFormGuide.retrieveMaxLength");
      return responseType;
   }//--------------------------------------------

}
