package nyla.solutions.formInjection.data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import nyla.solutions.formInjection.FormGuide;
import nyla.solutions.global.data.Copier;
import nyla.solutions.global.util.Organizer;

/**
 * 
 * <pre>
 *  FormColumn is a value object representation of the FormColumn table
 *  and associated entities.
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public class FormColumn extends Column implements FormComponentWrapper
{
   public FormColumn(FormTable table, int colNum)
   {
      this(table, table.getColumn(colNum));
   }

   public FormColumn(FormTable table, Column col)
   {
      if (table == null)
         throw new IllegalArgumentException("form table is required");
      this.table = table;
      this.column = col;
   }

   public FormTable getFormTable()
   {
      return table;
   }

   public FormQuestion getFormQuestion()
   {
      return table.getFormQuestion();
   }

   public FormAnswer getAnswer(int row)
   {
      FormAnswer answer = getForm().getAnswer(
      table.getFormQuestion().getQuestionId(), row,
      column.getColNumber().intValue());
      if (answer == null)
         return getFormQuestion().setAnswer(null, row,
         column.getColNumber().intValue());
      return answer;
   }

   public boolean isReadOnly()
   {
      QuestionAttribute readOnly = this
      .findAttributeByName(FormGuide.READONLY_ATTRIB_NM);
      return readOnly != null
      && Boolean.TRUE.toString().equalsIgnoreCase(
      String.valueOf(readOnly.getValue()));
   }// --------------------------------------------

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
   }//--------------------------------------------

   public Form getForm()
   {
      return table.getForm();
   }

   public Form retrieveForm()
   {
      return getForm();
   }

   public Object getManagedObject()
   {
      return column;
   }//--------------------------------------------

   public int compareTo(Object o)
   {
      if(column == null)
         return -1;

      return column.compareTo(o);
   }//--------------------------------------------

   public void copy(Copier aFrom)
   {
      if(column == null)
         return;

      column.copy(aFrom);
   }

   public boolean equals(Object obj)
   {
      if(column == null)
         return false;

      return column.equals(obj);
   }//--------------------------------------------

   public QuestionAttribute findAttributeByName(String name)
   {
      if(column == null)
         return null;
      
      return column.findAttributeByName(name);
   }//--------------------------------------------

   public QuestionAttribute getAttribute(String name)
   {
      if(column == null)
         return null;
      
      return column.getAttribute(name);
   }

   /**
    * 
    *
    * @see nyla.solutions.formInjection.data.Column#getAttributes()
    */
   public Map getAttributes()
   {
      if(column == null)
         return new HashMap();
      
      return column.getAttributes();
   }//--------------------------------------------

   public Map getChoiceMap()
   {
      Map m = new LinkedHashMap();
      m.putAll(column.getChoiceMap());
      m.putAll(getForm().getFormContext().getDynamicChoices(
      table.getFormQuestion().getQuestionId(), column.getColNumber()));
      return m;
   }// --------------------------------------------
   /**
    * 
    * @return dynamic and configured choices
    */
   public Collection getQuestionChoices()
   {
      ArrayList choices = new ArrayList();
      if(!getChoiceMap().isEmpty())
      {
         choices.addAll(getChoiceMap().values());
      }
       
      FormContext formContext = this.getForm().getFormContext();
      
      Map dynamicChoiceMap = formContext.getDynamicChoices(this.getFormQuestion().getQuestionId(), this.getColNumber());
      
      if(dynamicChoiceMap != null && !dynamicChoiceMap.isEmpty())
      {
         choices.addAll(dynamicChoiceMap.values());
      }
      
      return choices;
   }// --------------------------------------------


   public void addDynamicChoice(QuestionChoice choice)
   {
      getForm().getFormContext().addDynamicChoice(
      table.getFormQuestion().getQuestionId(), column.getColNumber(), choice);
   }

   public void addDynamicChoices(Collection choices)
   {
      getForm().getFormContext().addDynamicChoices(
      table.getFormQuestion().getQuestionId(), column.getColNumber(), choices);
   }

   public Integer getNumber()
   {
      return column.getColNumber();
   }

   public int getSize()
   {
      return column.getColSize();
   }

   public Date getCreateDate()
   {
      return column.getCreateDate();
   }

   public Object getCreateUserID()
   {
      return column.getCreateUserID();
   }

   public String getDeletedCode()
   {
      return column.getDeletedCode();
   }

   public Object getKey()
   {
      return column.getKey();
   }

   public String getName()
   {
      return column.getName();
   }

   public String getText()
   {
      return getName();
   }

   public int getPrimaryKey()
   {
      return column.getPrimaryKey();
   }

   public Integer getResponseTableId()
   {
      return column.getResponseTableId();
   }

   public ResponseType getResponseType()
   {
      return column.getResponseType();
   }

   public Integer getResponseTypeId()
   {
      return column.getResponseTypeId();
   }


   public boolean hasChoices()
   {
      return !Organizer.isEmpty(this.getQuestionChoices());
   }// --------------------------------------------


   public int hashCode()
   {
      return column.hashCode();
   }

   public boolean isDeleted()
   {
      return column.isDeleted();
   }

   public void setDeleted()
   {
      column.delete();
   }

   public String toString()
   {
      return column.toString();
   }

   public void updateAudit(Integer userId)
   {
      column.updateAudit(userId);
   }

   public Integer getColNumber()
   {
      return column.getColNumber();
   }

   public int getColSize()
   {
      return column.getColSize();
   }

   public boolean isNew()
   {
      return column.isNew();
   }

   public void setChoiceMap(Map choiceMap)
   {
      column.setChoiceMap(choiceMap);
   }

   public void setColNumber(Integer colNumber)
   {
      column.setColNumber(colNumber);
   }

   public void setColSize(int colSize)
   {
      column.setColSize(colSize);
   }

   public void setCreateDate(Date createDate)
   {
      column.setCreateDate(createDate);
   }

   public void setCreateUserID(Integer createUserId)
   {
      column.setCreateUserID(createUserId);
   }

   public void setDeletedCode(String deletedCode)
   {
      column.setDeletedCode(deletedCode);
   }

   public void setName(String name)
   {
      column.setName(name);
   }

   public void setResponseTableId(Integer responseTableId)
   {
      column.setResponseTableId(responseTableId);
   }

   public void setResponseTypeId(Integer responseTypeId)
   {
      column.setResponseTypeId(responseTypeId);
   }

   public void setUpdateDate(Timestamp updateDate)
   {
      column.setUpdateDate(updateDate);
   }

   public void setUpdateUserID(Integer updateUserId)
   {
      column.setUpdateUserID(updateUserId);
   }
   
   static final long serialVersionUID = FormColumn.class.getName().hashCode();
   private FormTable table;

   private Column column;
}
