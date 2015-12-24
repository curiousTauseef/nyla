/*
 * Created on Nov 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.solutions.form.data;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import nyla.solutions.global.data.Copier;

import org.solutions.form.FormGuide;

/**
 * 
 * <pre>
 * FormQuestion is a value object representation of the FormQuestion table
 * and associated entities.
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public class FormQuestion extends Question implements FormComponentWrapper
{
   static final long serialVersionUID = FormQuestion.class.getName().hashCode();

   private Question question;

   private Form form;

   public FormQuestion(Form form)
   {
      question = new Question();

      this.form = form;
   }

   public FormQuestion(Form form, Question question)
   {
      if (form == null || question == null)
         throw new IllegalArgumentException(
         "form and question are both required");
      if (question instanceof FormQuestion)
      {
         throw new IllegalArgumentException("FormQuestion provided");
      }
      this.form = form;
      this.question = question;
   }

   public Form getForm()
   {
      return form;
   }//--------------------------------------------

   /**
    * @param form the form to set
    */
   public void setForm(Form form)
   {
      this.form = form;
   }//--------------------------------------------

   public void resetNew()
   {
      setQuestionId(null);
      setCreateDate(null);

      this.updateAudit(getForm().getAccessUser().getId());
   }

   public String getText()
   {
      return this.getQuestionText();
   }

   public FormTable getFormTable()
   {
      return new FormTable(this);
   }

   public FormColumn getFormColumn(int colNumber)
   {
      return getFormTable().getFormColumn(colNumber);
   }

   public String getAnswerValue()
   {
      FormAnswer a = getAnswer();
      if (a == null)
         return null;
      return (String) a.getValue();
   }

   public FormAnswer getAnswer()
   {
      FormAnswer a = (FormAnswer) form.getAnswer(getQuestionId());
      if (a == null)
         return setAnswer(null);
      return a;
   }//--------------------------------------------

   /**
    * Get the answer for a giving row and column
    * @param row the row number
    * @param col the column number
    * @return the form answer at a given position
    */
   public FormAnswer getAnswer(int row, int col)
   {
      FormAnswer a = (FormAnswer) form.getAnswer(getQuestionId(), row, col);
      if (a == null)
         return setAnswer(null, row, col);
      return a;
   }//--------------------------------------------

   private FormAnswer getAnswer(boolean create)
   {
      if (create)
         return getAnswer();
      return form.getAnswer(getQuestionId());
   }

   private FormAnswer getAnswer(int row, int col, boolean create)
   {
      if (create)
         return getAnswer(row, col);
      return form.getAnswer(getQuestionId(), row, col);
   }

   public boolean hasAnswer()
   {
      FormAnswer a = getAnswer(false);
      return a != null && !a.isDeleted() && !a.isBlank();
   }

   public boolean hasAnswer(int row, int col)
   {
      FormAnswer a = getAnswer(row, col, false);
      return a != null && !a.isDeleted() && !a.isBlank();
   }

   public FormAnswer setAnswer(Object value)
   {
      if (value != null)
      {
         if (value instanceof FormAnswer)
         {
            return form.addFormAnswer((FormAnswer) value);
         }
         if (value instanceof Answer)
         {
            return form.addAnswer((Answer) value);
         }
         // continue
      }

      FormAnswer a = getAnswer(false);
      if (a == null)
         a = new FormAnswer(this);
      a.setValue(value != null ? String.valueOf(value) : null);
      a.setResponseTableId(null);
      a.setCol(null);
      a.setRow(null);
      a.setDeletedCode("N");
      return form.addFormAnswer(a);
   }

   public FormAnswer setAnswer(Object value, int row, int col)
   {
      if (value != null)
      {
         if (value instanceof FormAnswer)
         {
            FormAnswer a = (FormAnswer) value;
            a.setRow(row >= 0 ? new Integer(row) : null);
            a.setCol(col >= 0 ? new Integer(col) : null);
            return form.addFormAnswer(a);
         }
         if (value instanceof Answer)
         {
            Answer a = (Answer) value;
            a.setRow(row >= 0 ? new Integer(row) : null);
            a.setCol(col >= 0 ? new Integer(col) : null);
            return form.addAnswer(a);
         }
         // continue
      }
      ResponseTable table = getResponseType().getResponseTable();
      if (table == null)
         throw new IllegalArgumentException(
         "question does not have a response table");
      FormAnswer a = form.getAnswer(getQuestionId(), row, col);
      if (a == null)
      {
         Column c = table.getColumn(col);
         a = new FormAnswer(this, row, c.getColNumber().intValue());
      }
      // a.setRow(new Integer(row));
      a.setValue(value != null ? String.valueOf(value) : null);
      a.setDeletedCode("N");
      form.addFormAnswer(a);
      return a;
   }

   public FormAnswer pickChoice(String choiceKey)
   {
      FormAnswer a = getAnswer();
      a.pickChoice(choiceKey);
      return a;
   }

   public FormAnswer pickChoice(int row, int col, String choiceKey)
   {
      FormAnswer a = getAnswer(row, col);
      a.pickChoice(choiceKey);
      return a;
   }

   public FormAnswer pickChoiceByText(String text)
   {
      FormAnswer a = getAnswer();
      a.pickChoiceByText(text);
      return a;
   }

   public FormAnswer pickChoiceByText(int row, int col, String text)
   {
      FormAnswer a = getAnswer(row, col);
      a.pickChoiceByText(text);
      return a;
   }

   public Collection getChoices()
   {
      return getChoiceMap().values();
   }

   public final boolean isReadOnly()
   {
      QuestionAttribute attribute = findAttributeByName(FormGuide.READONLY_ATTRIB_NM);
      return FormGuide.isAttributeTrue(attribute);
   }// -------------------------------------------------

   public int compareTo(Object o)
   {
      return question.compareTo(o);
   }

   public void copy(Copier aFrom)
   {
      question.copy(aFrom);
   }

   public ResponseTable getResponseTable()
   {
      return question.getResponseTable();
   }

   public boolean hasResponseTable()
   {
      return question.hasResponseTable();
   }
   
   /**
    * 
    *
    * @see org.solutions.form.data.Question#hasHelpText()
    */
   public boolean hasHelpText()
   {
      return question.hasHelpText();
   }

   public void setDeleted()
   {
      question.delete();
   }

   public void updateAudit(Integer userId)
   {
      question.updateAudit(userId);
   }

   public boolean equals(Object obj)
   {
      return question.equals(obj);
   }

   public Map getAttributeMap()
   {
      return question.getAttributeMap();
   }

   public QuestionAttribute getAttribute(String key)
   {
      return question.getAttribute(key);
   }

   public Collection getAttributes()
   {
      return question.getAttributeMap().values();
   }

   public Map getChoiceMap()
   {
      if(question.getChoiceMap() == null)
         return null;
      
         
      Map m = new LinkedHashMap();     
      m.putAll(question.getChoiceMap());
      m.putAll(getForm().getFormContext().getDynamicChoices(getQuestionId()));
      return m;
   }

   public void addDynamicChoice(QuestionChoice choice)
   {
      getForm().getFormContext().addDynamicChoice(getQuestionId(), choice);
   }

   public void addDynamicChoices(Collection choices)
   {
      getForm().getFormContext().addDynamicChoices(getQuestionId(), choices);
   }

   public Date getCreateDate()
   {
      return question.getCreateDate();
   }

   public Object getCreateUserID()
   {
      return question.getCreateUserID();
   }

   public String getDeletedCode()
   {
      return question.getDeletedCode();
   }

   public String getFormTypeCode()
   {
      return question.getFormTypeCode();
   }

   public Object getKey()
   {
      return question.getKey();
   }

   public int getPrimaryKey()
   {
      return question.getPrimaryKey();
   }

   public Integer getQuestionId()
   {
      return question.getQuestionId();
   }

   public Integer getQuestionNumber()
   {
      return question.getQuestionNumber();
   }

   public Integer getNumber()
   {
      return getQuestionNumber();
   }

   public String getQuestionText()
   {
      return question.getQuestionText();
   }

   public ResponseType getResponseType()
   {
      return question.getResponseType();
   }

   public String getResponseTypeCode()
   {
      return question.getResponseTypeCode();
   }

   public Integer getSectionNumber()
   {
      return question.getSectionNumber();
   }

   public boolean hasChoices()
   {
      return question.hasChoices();
   }

   public boolean hasTable()
   {
      return question.hasResponseTable();
   }

   public int hashCode()
   {
      return question.hashCode();
   }

   public boolean isDeleted()
   {
      return question.isDeleted();
   }

   public String toString()
   {
      return question.toString();
   }

   public Object getManagedObject()
   {
      return question;
   }

   // ------------------------------------
   // for retrofit
   // ------------------------------------

   private boolean withinExpression;

   public void setWithinExpression(boolean b)
   {
      withinExpression = b;
   }

   public Form retrieveForm()
   {
      return getForm();
   }

   public QuestionAttribute findAttributeByName(String name)
   {
      return question.getAttribute(name);
   }

   public final boolean isRequired()
   {
      QuestionAttribute attribute = this
      .findAttributeByName(FormGuide.REQUIRED_PROPERTY_NM);
      if (attribute == null)
         return false;
      String value = attribute.getStringValue();
      if (value == null)
         return false;
      return new Boolean(value.toLowerCase()).booleanValue();
   }

   // -------------------------------------------------
   // retrofit
   // -------------------------------------------------
   public int getFormPK()
   {
      return getForm().getPrimaryKey();
   }

   /**
    * 
    * @return properties.contains(NUMBERED);
    */
   public final boolean isNumbered()
   {
      QuestionAttribute attribute = findAttributeByName(FormGuide.NUMBERED_PROPERTY_NM);
      return FormGuide.isAttributeTrue(attribute);

   }// -----------------------------------------------

   /**
    * 
    * @return retrieveQuestion().isNumbered()
    */
   public boolean mustNumber()
   {
      return isNumbered();
   }// --------------------------------------------

   /**
    * @return flag to determinate whether the question is yes/no checkbox
    */
   public boolean isYesOrNoCheckBox()
   {
      return yesOrNoCheckBox;
   }// ---------------------------------------

   private boolean yesOrNoCheckBox;

   /**
    * 
    * @return true question is selectable and type is radio, YES|NO checkbox, or
    *         select list
    */
   public boolean isOneChoiceOnly()
   {
      if (!this.getResponseType().isSelectable())
      {
         return false;
      }

      short type = getResponseType().getType();
      return type == FormGuide.RADIO_TYPE || type == FormGuide.SELECT_LIST_TYPE
      || this.isYesOrNoCheckBox();
   }// -----------------------------------------------

   public boolean isNew()
   {
      return question.isNew();
   }

   public void setAttributeMap(Map attributeMap)
   {
      question.setAttributeMap(attributeMap);
   }

   public void setChoiceMap(Map choiceMap)
   {
      question.setChoiceMap(choiceMap);
   }

   public void setCreateDate(Date createDate)
   {
      question.setCreateDate(createDate);
   }

   public void setCreateUserID(Integer createUserId)
   {
      question.setCreateUserID(createUserId);
   }

   public void setDeletedCode(String deletedCode)
   {
      question.setDeletedCode(deletedCode);
   }

   public void setFormTypeCode(String formTypeCode)
   {
      question.setFormTypeCode(formTypeCode);
   }

   public void setQuestionId(Integer id)
   {
      question.setQuestionId(id);
   }

   public void setQuestionNumber(Integer questionNumber)
   {
      question.setQuestionNumber(questionNumber);
   }

   public void setQuestionText(String questionText)
   {
      question.setQuestionText(questionText);
   }

   public void setText(String text)
   {
      setQuestionText(text);
   }

   public void setResponseType(ResponseType responseType)
   {
      question.setResponseType(responseType);
   }

   public void setResponseTypeCode(String responseTypeCode)
   {
      question.setResponseTypeCode(responseTypeCode);
   }

   public void setSectionNumber(Integer sectionNumber)
   {
      question.setSectionNumber(sectionNumber);
   }

   public void setUpdateDate(Timestamp updateDate)
   {
      question.setUpdateDate(updateDate);
   }

   public void setUpdateUserID(Integer updateUserId)
   {
      question.setUpdateUserID(updateUserId);
   }

   // ======================================================

   /**
    * @return FormGuide.createQuestionName(this.getQuestion(),this.offline)
    */
   public String getInputName()
   {
      return FormGuide.createQuestionName(this, this.offline);
   }// --------------------------------------------

   /**
    * 
    * @return FormGuide.createIntegerQuestionName(this.getQuestion(),this.offline)
    */
   public String getIntegerInputName()
   {
      return FormGuide.createIntegerQuestionName(this, this.offline);
   }// --------------------------------------------

   /**
    * @return Returns the offline.
    */
   public boolean isOffline()
   {
      return offline;
   }// --------------------------------------------

   /**
    * @param offline
    *            The offline to set.
    */
   public void setOffline(boolean offline)
   {
      this.offline = offline;
   }// --------------------------------------------

   /**
    * 
    * @return (GCSMForm)this.retrieveForm()
    */
   public Application retrieveGCSMForm()
   {
      return (Application) this.retrieveForm();
   }// --------------------------------------------

   private boolean offline = false;

   /**
    * @return Returns the withinExpression.
    */
   public final boolean isWithinExpression()
   {
      return withinExpression;
   }

   public Map getColumnMap(FormTable formTable)
   {
      return formTable.table.getColumnMap();
   }
}
