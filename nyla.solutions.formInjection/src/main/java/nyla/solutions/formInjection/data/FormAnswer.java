package nyla.solutions.formInjection.data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import nyla.solutions.formInjection.FormGuide;
import nyla.solutions.formInjection.bre.OperationBluePrint;
import nyla.solutions.global.data.Copier;
import nyla.solutions.global.data.Data;
import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Text;

/**
 * 
 * <pre>
 *  FormAnswer is a value object representation of the FormAnswer table
 *  and associated entities.
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public class FormAnswer extends Answer implements FormComponentWrapper
{
   private FormQuestion question;

   private Answer answer;

   public FormAnswer(FormQuestion question)
   {
      this(question, new Answer());
      
      
   }

   public FormAnswer(FormQuestion question, int row, int col)
   {
      this(question, new Answer(question.getQuestionId().intValue(), row, col));
   }

   public FormAnswer(FormQuestion question, Answer aAnswer)
   {
      if(question.getForm() == null)
         throw new RequiredException("question.getForm() in FormAnswer.FormAnswer");
      
      
      if (question == null)
      {
         throw new IllegalArgumentException("question is required answer="+aAnswer);
      }
      if (question.getQuestionId() == null)
      {
         throw new IllegalArgumentException("question id is required");
      }
      this.question = question;

      if (aAnswer == null)
         aAnswer = new Answer();
      this.answer = aAnswer;

      setFormId(question.getForm().getFormId());
      setFormTypeCode(question.getForm().getFormTypeCode());
      setQuestionId(question.getQuestionId());
      if (question.hasTable())
      {
         setResponseTableId(question.getResponseTable().getResponseTableId());
         // this.setColNumber(col >= 0 ? new Integer(col) : null);
         // this.setRowNumber(row >= 0 ? new Integer(row) : null);
      }
      if (answer.getValue() == null && answer.getAnswerId() == null)
         this.setValueFromDefaultChoices();

      copyQuestionAttributesForAnswers();
   }

   public void resetNew()
   {
      setAnswerId(null);
      setCreateDate(null);
      
    //TODO: updateAudit(getForm().getAccessUser().getId());
   }

   public boolean isWithinTable()
   {
      return question.hasTable();
   }

   public void delete()
   {
      getForm().deleteAnswer(this);
   }

   public void unDelete()
   {
      if (isDeleted())
      {
       //TODO: updateAudit(getForm().getAccessUser().getId());
      }
      setDeletedCode("N");
   }

   private void copyQuestionAttributesForAnswers()
   {
      
      if(question.getAttributeMap() == null)
      {
         return;
      }
      // loop thru attributes
      String neededNames = FormGuide.neededAttributeNamesForAnswers();

      Collection attributes = new ArrayList(question.getAttributeMap().values());
      if (question.hasTable())
      {
         Column col = question.getResponseTable().getColumn(0);
         if(col != null)
         {
            attributes.addAll(col.getAttributes().values());   
         }
         
      }

      for (Iterator i = attributes.iterator(); i.hasNext();)
      {
         QuestionAttribute attribute = (QuestionAttribute) i.next();
         String name = attribute.getName();

         // add to results if name in needed
         // worst case is some attributes are added that are note
         // really need, because indexOf operation is used
         // Debugger.getCategory(this.getClass()).debug("Got name: "+name+"
         // comparing to needNames: "+neededNames);
         if (name.indexOf(neededNames) > -1)
         {
            // Debugger.getCategory(this.getClass()).debug("Adding property:
            // "+attribute.getName()+"::"+attribute.getValue());
            addProperty(attribute.getName(), (Serializable) attribute
            .getValue(), null);
         }
      }
   }// --------------------------------------------

   /*
    * private void addChoices(Map choices) { if (choices == null) return; for
    * (Iterator i = choices.values().iterator(); i.hasNext(); ) { QuestionChoice
    * choice = (QuestionChoice) i.next();
    * choices.put(String.valueOf(choice.getKey()), choice); } }
    */

   protected Column getColumn()
   {
      if (getCol() == null)
         return null;
      Map cols = question.getResponseType().getResponseTable().getColumnMap();
      Column col = (Column) cols.get(getCol());
      return col;
   }

   public FormColumn getFormColumn()
   {
      return new FormColumn(question.getFormTable(), getColumn());
   }

   public Collection getQuestionChoices()
   {
      return getChoiceMap().values();
   }

   public QuestionChoice getChoice(Object key)
   {
      return (QuestionChoice) getChoiceMap().get(key);
   }//--------------------------------------------

   /***
    * 
    * @return a map choices for a given form answer
    */
   public Map getChoiceMap()
   {
      Map choices = new LinkedHashMap();
      if (question.hasTable())
      {
         Column col = getColumn();
         if (col != null)
         {
            choices.putAll(col.getChoiceMap());
            Map dynamicChoices = getForm().getFormContext().getDynamicChoices(
            question.getQuestionId(), col.getColNumber());
            choices.putAll(dynamicChoices);
         }
      }
      else
      {
         if(question.getChoiceMap() == null)
            return null;
         
         choices.putAll(question.getChoiceMap());
         Map dynamicChoices = getForm().getFormContext().getDynamicChoices(
         question.getQuestionId());
         choices.putAll(dynamicChoices);
      }
      return choices;
   }

   public boolean hasChoices()
   {
      return !getChoiceMap().isEmpty();
   }

   public boolean hasOperation()
   {
      return getResponseType().hasOperation();
   }

   public OperationBluePrint retrieveOperationBluePrint()
   {
      ResponseType responseType = this.getResponseType();
      if (responseType == null)
         return null;
      return responseType.getOperationBluePrint();
   }// --------------------------------------------

   public void pickChoice(String key)
   {
      QuestionChoice choice = (QuestionChoice) getChoiceMap().get(key);
      if (choice != null)
         setValue(choice.getKey().toString());
   }

   public void pickChoiceByText(String text)
   {
      QuestionChoice choice = this.findChoiceByText(text);
      Debugger.getLog(this.getClass())
      .debug("Looking for choice: " + text);
      if (choice != null)
         setValue(choice.getKey().toString());
   }

   public Collection retrievePickedChoices()
   {
      if (this.isBlank())
         return Collections.EMPTY_LIST;
      StringTokenizer tokenizer = new StringTokenizer(Text.toString(getValue()), ",");
      List l = new LinkedList();
      Map choiceMap = getChoiceMap();
      while (tokenizer.hasMoreTokens())
      {
         String next = tokenizer.nextToken();
         QuestionChoice choice = (QuestionChoice) choiceMap.get(next);
         if (choice != null)
            l.add(choice);
      }
      return l;
   }

   protected void setValueFromDefaultChoices()
   {
      Map choiceMap = getChoiceMap();
      StringBuffer buffer = new StringBuffer();
      if (choiceMap != null && !choiceMap.isEmpty())
      {
         for (Iterator i = choiceMap.values().iterator(); i.hasNext();)
         {
            QuestionChoice choice = (QuestionChoice) i.next();
            if (choice.isDefault())
               buffer.append(choice.getKey() + ",");
         }
      }
      String val = buffer.length() > 0 ? buffer.substring(0,
      buffer.length() - 1) : buffer.toString();
      answer.setValue(val);
   }

   public boolean isPicked(QuestionChoice choice)
   {
      // Debugger.getCategory(this.getClass()).debug("Looking for:
      // "+choice.getKey()+" in: "+getValue());
      String val = (String)this.getValue();
      if (val == null)
         return false;
      StringTokenizer tokenizer = new StringTokenizer(val, ",");
      while (tokenizer.hasMoreTokens())
      {
         if (tokenizer.nextToken().trim().equals(choice.getKey()))
            return true;
      }
      return false;
   }

   public QuestionChoice findChoiceByText(String text)
   {
      for (Iterator i = this.getChoiceMap().values().iterator(); i.hasNext();)
      {
         QuestionChoice choice = (QuestionChoice) i.next();
         if (choice.getText().equals(text))
            return choice;
      }
      return null;
   }

   public Form getForm()
   {
      return question.getForm();
   }

   public FormQuestion getFormQuestion()
   {
      return question;
   }

   public Object getManagedObject()
   {
      return answer;
   }

   public ResponseType getResponseType()
   {
      if (this.isWithinTable())
      {
         return this.getColumn().getResponseType();
      }
      return question.getResponseType();
   }

   public Integer getIntegerValue()
   {
      if (getValue() != null)
         return new Integer(getValue().toString());
      return null;
   }

   public String getText(boolean replaceNull)
   {

      if (this.getValue() == null && !replaceNull)
      {
         Debugger.getLog(this.getClass()).debug("returning null");
         return null;
      }
      return this.getText();
   }

   public String getText()
   {
      try
      {
         String type = getResponseType().getCode();
         if (type.equals(ResponseType.CURRENCY_CD))
            return Text.formatCurrency(String.valueOf(this.getValue()));
         if (type.equals(ResponseType.DATE_CD))
            return Text.formatDate(String.valueOf(this.getValue()));
         if (type.equals(ResponseType.NUMBER_CD))
            return Text.formatNumber(String.valueOf(this.getValue()));
         if (type.equals(ResponseType.PERCENT_CD))
            return Text.formatPercent(String.valueOf(this.getValue()));
      }
      catch (Exception e)
      {
         Debugger.printWarn(Debugger.stackTrace(e) + this.getValue());
      }

      return FormGuide.toText(this);

   }// --------------------------------------------------

   public boolean isReadOnly()
   {
      if (this.getForm().isReadOnly())
         return true;

      boolean hasAnswer = getValue() != null && !getValue().equals("");
      if (this.isWithinTable())
      {
         FormColumn c = getFormColumn();
         if (c.isReadOnly())
            return true;
         if (hasAnswer)
         {
            QuestionAttribute readOnlyAfterAnswer = c
            .getAttribute(FormGuide.READONLY_AFTER_ANSWER_ATTRIB_NM);
            if (readOnlyAfterAnswer != null)
               return true;
         }
      }
      else
      {
         FormQuestion q = getFormQuestion();
         if (q.isReadOnly())
            return true;
         if (hasAnswer)
         {
            QuestionAttribute readOnlyAfterAnswer = q
            .getAttribute(FormGuide.READONLY_AFTER_ANSWER_ATTRIB_NM);
            if (readOnlyAfterAnswer != null)
               return true;
         }
      }
      return false;
   }// --------------------------------------------

   public boolean isRequired()
   {
      if (this.isWithinTable())
      {
         return this.getFormColumn().isRequired();
      }
      else
      {
         return getFormQuestion().isRequired();
      }
   }

   public void addProperty(String key, AnswerProperty val, Integer userId)
   {
      answer.addProperty(key, val, userId);
   }

   public void addProperty(String key, Object value)
   {
      answer.addProperty(key, value, getForm().getAccessUser().getLoginID());
   }

   public Map getProperties()
   {
      return answer.getAnswerProps();
   }

   public int compareTo(Object o)
   {
      return answer.compareTo(o);
   }

   public void copy(Copier aFrom)
   {
      answer.copy(aFrom);
   }

   public boolean equals(Object obj)
   {
      return answer.equals(obj);
   }

   public String generateKey()
   {
      return answer.generateKey();
   }

   protected String generateKey(boolean setPK)
   {
      return answer.generateKey(setPK);
   }

   public String getAnswerId()
   {
      return answer.getAnswerId();
   }

   public Map getAnswerProps()
   {
      return answer.getAnswerProps();
   }

   public Integer getCol()
   {
      return answer.getCol();
   }// --------------------------------------------


   public Date getCreateDate()
   {
      return answer.getCreateDate();
   }

   public Object getCreateUserID()
   {
      return answer.getCreateUserID();
   }

   public String getDeletedCode()
   {
      return answer.getDeletedCode();
   }

   public Integer getFormId()
   {
      return answer.getFormId();
   }

   public String getFormTypeCode()
   {
      return answer.getFormTypeCode();
   }

   public Object getKey()
   {
      return answer.getKey();
   }

   public int getPrimaryKey()
   {
      return answer.getPrimaryKey();
   }

   public AnswerProperty getProperty(String key)
   {
      return answer.getProperty(key);
   }

   public Integer getQuestionId()
   {
      return answer.getQuestionId();
   }

   public Integer getResponseTableId()
   {
      return answer.getResponseTableId();
   }

   public Integer getRowNumber()
   {
      return answer.getRow();
   }

  

   public String getValue()
   {
      return answer.getValue();
   }

   public Date getValueDate()
   {
      if (isBlank())
         return null;

      return Text.toDate(getValue().toString());
   }

   public int hashCode()
   {
      return answer.hashCode();
   }

   public boolean isBlank()
   {
      return answer.isBlank();
   }

   public boolean isDeleted()
   {
      return answer.isDeleted();
   }

   public AnswerProperty retrieveProperty(String key)
   {
      return answer.retrieveProperty(key);
   }

   public void setAnswerId(String answerId)
   {
      answer.setAnswerId(answerId);
   }

   public void setColNumber(Integer col)
   {
      setCol(col);
   }// --------------------------------------------


   public void setDeleted()
   {
      setDeletedCode("Y");
   }

   public void setFormId(Integer formId)
   {
      answer.setFormId(formId);
   }

   public void setFormTypeCode(String aFormTypeCode)
   {
      answer.setFormTypeCode(aFormTypeCode);
   }

   public void setQuestionId(Integer questionId)
   {
      if (questionId == null)
         throw new IllegalArgumentException("question id is required");
      answer.setQuestionId(questionId);
   }

   public void setResponseTableId(Integer responseTableId)
   {
      answer.setResponseTableId(responseTableId);
   }

   public void setRowNumber(Integer row)
   {
      setRow(row);
   }

   public void setValue(String value)
   {
      if(this.answer == null)
         this.answer = new Answer(this.question);
      
      // use String.valueOf to avoid NPEs
      if (!String.valueOf(value).equals(String.valueOf(getValue())))
      {
         if(getForm().getAccessUser() != null)
         {
            updateAudit(getForm().getAccessUser().getLoginID());   
         }
      }
      if ("null".equals(value))
         answer.setValue(null);
      else
         answer.setValue(value);
      // getForm().addFormAnswer(this);
   }

   public String toString()
   {
      return answer.toString();
   }

   public void updateAudit(Integer userId)
   {
      answer.updateAudit(userId);
   }

   public FormRow getFormRow()
   {
      FormRow row = new FormRow(question.getFormTable(), getRowNumber()
      .intValue());
      return row;
   }

   public static String toKey(int formPK, int aQuestionPK, int aTablePK,
                              int aColumnNumber, int aRowNumber)
   {
      return Answer.toKey(formPK, aQuestionPK, aTablePK, aColumnNumber,
      aRowNumber);
   }

   public static String toKey(Object formId, Object aQuestionPK,
                              Object aTablePK, Object aColumnNumber,
                              Object aRowNumber)
   {
      return Answer.toKey(formId, aQuestionPK, aTablePK, aColumnNumber,
      aRowNumber);
   }

   public void addProperty(String key, Object value, Integer userId)
   {
      answer.addProperty(key, value, userId);
   }

   public Integer getRow()
   {
      return answer.getRow();
   }

   public void setAnswerProps(Map answerProps)
   {
      answer.setAnswerProps(answerProps);
   }

   public void setCol(Integer col)
   {
      // use String.valueOf to avoid NPEs
      if (!String.valueOf(col).equals(String.valueOf(getCol())))
         updateAudit(getForm().getAccessUser().getLoginID());
      answer.setCol(col);
   }

   public void setRow(Integer row)
   {
      // use String.valueOf to avoid NPEs
      if (!String.valueOf(row).equals(String.valueOf(getRow())))
         updateAudit(getForm().getAccessUser().getLoginID());
      answer.setRow(row);
   }

   public void setCreateDate(Date createDate)
   {
      answer.setCreateDate(createDate);
   }

   public void setCreateUserID(Integer createUserId)
   {
      answer.setCreateUserID(createUserId);
   }

   public void setDeletedCode(String deletedCode)
   {
      // use String.valueOf to avoid NPEs
      if (!String.valueOf(deletedCode).equals(String.valueOf(getDeletedCode())))
         updateAudit(getForm().getAccessUser().getLoginID());
      answer.setDeletedCode(deletedCode);
   }

   public void setUpdateDate(Timestamp updateDate)
   {
      answer.setUpdateDate(updateDate);
   }

   public void setUpdateUserID(Integer updateUserId)
   {
      answer.setUpdateUserID(updateUserId);
   }

   // --------------------------------------------------
   // for retrofit
   // --------------------------------------------------
   public QuestionAttribute findAttributeByName(String name)
   {
      if (question.hasTable())
         return getColumn().getAttribute(name);
      return question.getAttribute(name);
   }

   public Form retrieveForm()
   {
      return getForm();
   }

   public int getQuestionPK()
   {
      return getQuestionId().intValue();
   }

   public Integer getTablePK()
   {
      return getResponseTableId();
   }

   public Integer getColumnNumber()
   {
      return getCol();
   }

   public boolean hasProperty(String key, Object value)
   {
      AnswerProperty property = this.getProperty(key);
      String s = (property != null && property.getValue() != null) ? property
      .getValue().toString() : "";
      if (value.toString().equalsIgnoreCase(s))
         return true;
      return false;
   }

   public int getFormPK()
   {
      return getForm().getPrimaryKey();
   }

   public boolean isNew()
   {
      return super.isNew();
   }

   public String getAnswerId(boolean create)
   {
      return answer.getAnswerId(create);
   }
   static final long serialVersionUID = FormAnswer.class.getName().hashCode();
}
