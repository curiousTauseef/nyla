package org.solutions.form.data;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * <pre>
 * Question is a value object representation of the Question table
 * and associated entities.
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public class Question extends FormComponent
{

   /**
    * 
    * @return this.responseType !=  null && this.responseType.hasOperation()
    */
   public boolean hasOperation()
   {
      return this.responseType !=  null && this.responseType.hasOperation();
   }//--------------------------------------------
   /**
    * 
    * @return the primary key
    */
   public Integer getQuestionId()
   {
      return new Integer(super.getPrimaryKey());
   }

   public void setQuestionId(Integer id)
   {
      this.setPrimaryKeyInteger(id);
   }// --------------------------------------------

   public Object getKey()
   {
      return getQuestionId();
   }

   public Map getAttributeMap()
   {
      return attributeMap;
   }//--------------------------------------------

   public void setAttributeMap(Map attributeMap)
   {
      this.attributeMap = attributeMap != null ? attributeMap : new HashMap();
   }//--------------------------------------------
   /**
    * 
    * @param key the question attribute key
    * @return
    */
   public QuestionAttribute getAttribute(String key)
   {
      if(attributeMap == null)
         return null;
      
      return (QuestionAttribute) attributeMap.get(key);
   }

   public ResponseType getResponseType()
   {
      return responseType;
   }

   public void setResponseType(ResponseType responseType)
   {
      this.responseType = responseType;
   }

   public String getFormTypeCode()
   {
      return formTypeCode;
   }
   

   public void setFormTypeCode(String formTypeCode)
   {
      this.formTypeCode = formTypeCode;
   }

   public Integer getQuestionNumber()
   {
      return questionNumber;
   }

   public void setQuestionNumber(Integer questionNumber)
   {
      this.questionNumber = questionNumber;
   }
   
   public boolean hasHelpText()
   {
      return false;
   }//--------------------------------------------

   public String getQuestionText()
   {
      return questionText;
   }

   public void setQuestionText(String questionText)
   {
      this.questionText = questionText;
   }

   public String getResponseTypeCode()
   {
      return responseTypeCode;
   }

   protected void setResponseTypeCode(String responseTypeCode)
   {
      this.responseTypeCode = responseTypeCode;
   }

   public Integer getSectionNumber()
   {
      return sectionNumber;
   }

   public void setSectionNumber(Integer sectionNumber)
   {
      this.sectionNumber = sectionNumber;
   }

   public Map getChoiceMap()
   {
      return choiceMap;
   }

   public void setChoiceMap(Map choiceMap)
   {
      this.choiceMap = choiceMap != null ? choiceMap : new LinkedHashMap();
   }

   public boolean hasChoices()
   {
      return choiceMap != null && !choiceMap.isEmpty();
   }

   public boolean hasResponseTable()
   {
      return responseType.getResponseTable() != null;
   }

   public ResponseTable getResponseTable()
   {
      return responseType.getResponseTable();
   }//--------------------------------------------

   public String getText()
   {
      return this.getQuestionText();
   }

   static final long serialVersionUID = Question.class.getName().hashCode();

   private String formTypeCode;

   private String responseTypeCode;

   private Integer questionNumber;

   private String questionText;

   private Integer sectionNumber;

   private ResponseType responseType;

   private Map choiceMap = new LinkedHashMap();

   private Map attributeMap = new HashMap();
}
