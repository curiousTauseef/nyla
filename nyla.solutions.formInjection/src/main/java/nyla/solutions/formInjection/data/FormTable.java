/*
 * Created on Nov 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package nyla.solutions.formInjection.data;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import nyla.solutions.formInjection.FormGuide;
import nyla.solutions.global.data.Copier;

/**
 * 
 * <pre>
 * FormTable is a value object representation of the FormTable table
 * and associated entities.
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public class FormTable extends ResponseTable implements FormComponentWrapper
{

   public FormTable(FormQuestion question)
   {
      if (question == null)
         throw new IllegalArgumentException("form and question are required");
      this.question = question;
      this.table = question.getResponseType().getResponseTable();
      if (table == null)
         throw new IllegalArgumentException(
         "question does not have a response table");

      rowSize = calculateRowSize();
   }//--------------------------------------------

   public String getText()
   {
      return table.getTableName();
   }

   public boolean isEmpty()
   {
      return rowSize == 0;
   }

   public int getRowCapacity()
   {
      return FormGuide.retrieveMaxLength(question);
   }// --------------------------------------------

   public Form getForm()
   {
      return question.getForm();
   }//--------------------------------------------
   /**
    * 
    * @return the row
    */
   private int calculateRowSize()
   {
      if (!hasRow(0))
         return 0;

      int i = 1;
      while (hasRow(i))
         i += 10;

      int min = i - 10;
      // TODO: should to binary to optimize
      while (!hasRow(i) && i > min)
         i--;
      return i + 1;
   }//--------------------------------------------

   /**
    * 
    * @param rowNum determine if given row has answers
    * @return true if the row has at least one answer
    */
   private boolean hasRow(int rowNum)
   {
      Map cols = this.getColumnMap();
      Form form = getForm();
      for (Iterator i = cols.keySet().iterator(); i.hasNext();)
      {
         Integer colNumber = (Integer) i.next();
         if (form.findAnswer(question.getQuestionId(), rowNum, colNumber
         .intValue()) != null)
            return true;
      }
      return false;
   }//--------------------------------------------
   /**
    * This is expensive. Use the deleteRows(int[] rowNum) method for batch
    * deletes.
    * 
    * @param rowNum
    */
   public void deleteRow(int rowNum)
   {
      int currentRowSize = rowSize;
      List modifiedAnswers = new LinkedList();
      // delete the answers in the row
      FormRow row = getRow(rowNum);
      for (Iterator i = row.getAnswers().iterator(); i.hasNext();)
      {
         FormAnswer answer = (FormAnswer) i.next();
         answer.delete();
      }
      // now renumber the later rows
      for (int i = rowNum + 1; i < currentRowSize; i++)
      {
         row = this.getRow(i);
         List answers = row.getAnswers();
         Integer newRow = new Integer(i - 1);
         for (Iterator j = answers.iterator(); j.hasNext();)
         {
            FormAnswer answer = (FormAnswer) j.next();
            answer.setRow(newRow);
            modifiedAnswers.add(answer);
         }
      }
      this.getForm().addAnswers(modifiedAnswers);
      rowSize--;
   }

   public int getRowCount()
   {
      return rowSize;
   }

   public int getColumnCount()
   {
      return getColumnMap().size();
   }

   public FormQuestion getFormQuestion()
   {
      return question;
   }

   public FormRow createRow()
   {
      return createRow(rowSize);
   }// --------------------------------------------

   /**
    * 
    * @return
    */
   public Collection getRows()
   {
      List rows = new LinkedList();
      for (int i = 0; i < rowSize; i++)
      {
         FormRow row = new FormRow(this, i);
         rows.add(row);
      }
      return rows;
   }// --------------------------------------------

   public FormRow createRow(int rowNum)
   {
      FormRow row = new FormRow(this, rowNum);
      rowSize++;
      return row;
   }

   public FormRow getRow(int rowNum)
   {
      if (rowNum < 0 || rowNum > rowSize)
         return null;
      FormRow row = new FormRow(this, rowNum);
      return row;
   }

   public boolean hasRows()
   {
      return rowSize > 0;
   }

   public Collection getProperties()
   {
      if (rowSize == 0)
         return Collections.EMPTY_LIST;
      FormRow row = new FormRow(this, 0);
      List l = row.getAnswers();
      Map properties = new HashMap();
      for (Iterator i = l.iterator(); i.hasNext();)
      {
         FormAnswer a = (FormAnswer) i.next();
         properties.putAll(a.getProperties());
      }
      return properties.values();
   }

   public Collection findAnswersByAttribute(String key, Object val)
   {
      Collection l = getAnswers();
      List result = new LinkedList();
      for (Iterator i = l.iterator(); i.hasNext();)
      {
         Answer a = (Answer) i.next();
         AnswerProperty property = a.getProperty(key);
         String s = (property != null && property.getValue() != null) ? property
         .getValue().toString()
         : "";
         if (val.toString().equalsIgnoreCase(s))
            result.add(a);
      }
      return result;
   }

   public boolean hasAnswersWithProperty(String key, Object val)
   {
      return !findAnswersByAttribute(key, val).isEmpty();
   }

   public Collection getAnswers()
   {
      return question.getForm().findAnswerList(question.getQuestionId());
   }

   public int compareTo(Object o)
   {
      return table.compareTo(o);
   }

   public void copy(Copier aFrom)
   {
      table.copy(aFrom);
   }

   public boolean equals(Object obj)
   {
      return table.equals(obj);
   }

   public Map getAttributes()
   {
      return table.getAttributes();
   }

   /**
    * return question column map
    */
   public Map getColumnMap()
   {
      return question.getColumnMap(this);
   }//--------------------------------------------
   /**
    * 
    * @return the map FormColumn where the key is the column number
    */
   public Map getFormColumnMap()
   {
      Map cols = table.getColumnMap();
      Map formCols = new LinkedHashMap();
      for (Iterator i = cols.values().iterator(); i.hasNext();)
      {
         Column c = (Column) i.next();
         FormColumn formColumn = new FormColumn(this, c);
         formCols.put(formColumn.getNumber(), formColumn);
      }
      return formCols;
   }

   public Date getCreateDate()
   {
      return table.getCreateDate();
   }

   public Object getCreateUserID()
   {
      return table.getCreateUserID();
   }

   public int getDefaultRowSize()
   {
      return table.getDefaultRowSize();
   }

   public String getDeletedCode()
   {
      return table.getDeletedCode();
   }

   public Object getKey()
   {
      return table.getKey();
   }

   public int getPrimaryKey()
   {
      return table.getPrimaryKey();
   }

   public Integer getResponseTableId()
   {
      return table.getResponseTableId();
   }

   public String getTableName()
   {
      return table.getTableName();
   }

   public int hashCode()
   {
      return table.hashCode();
   }

   public boolean isDeleted()
   {
      return table.isDeleted();
   }

   public String toString()
   {
      return table.toString();
   }

   public Object getManagedObject()
   {
      return table;
   }

   /**
    * 
    * @return FormGuide.isHorizontal(this)
    */
   public boolean isHorizontal()
   {
      return FormGuide.isHorizontal(this);

   }// --------------------------------------------

   /**
    * 
    * @return FormGuide.isFixed(this)
    */
   public boolean isFixed()
   {
      return FormGuide.isFixed(this);

   }// --------------------------------------------

   // ------------------------------------------
   // for retrofit
   // ------------------------------------------
   public Form retrieveForm()
   {
      return getForm();
   }

   public Column getColumn(int colNumber)
   {
      return table.getColumn(colNumber);
   }

   public FormColumn getFormColumn(int index)
   {
      return new FormColumn(this, getColumn(index));
   }

   public boolean hasColumns()
   {
      return table.hasColumns();
   }

   public boolean isNew()
   {
      return table.isNew();
   }

   public void setDeleted()
   {
      table.delete();
   }

   public void updateAudit(Integer userId)
   {
      table.updateAudit(userId);
   }

   protected void setAttributes(Map attributes)
   {
      table.setAttributes(attributes);
   }

   protected void setColumns(Map columns)
   {
      table.setColumns(columns);
   }

   public void setCreateDate(Date createDate)
   {
      table.setCreateDate(createDate);
   }

   public void setCreateUserID(Integer createUserId)
   {
      table.setCreateUserID(createUserId);
   }

   protected void setDefaultRowSize(int size)
   {
      table.setDefaultRowSize(size);
   }

   public void setDeletedCode(String deletedCode)
   {
      table.setDeletedCode(deletedCode);
   }

   protected void setResponseTableId(Integer responseTableId)
   {
      table.setResponseTableId(responseTableId);
   }

   protected void setTableName(String tableName)
   {
      table.setTableName(tableName);
   }

   public void setUpdateDate(Timestamp updateDate)
   {
      table.setUpdateDate(updateDate);
   }

   public void setUpdateUserID(Integer updateUserId)
   {
      table.setUpdateUserID(updateUserId);
   }

   static final long serialVersionUID = FormTable.class.getName().hashCode();

   private FormQuestion question;

   ResponseTable table;

   private int rowSize;
}
