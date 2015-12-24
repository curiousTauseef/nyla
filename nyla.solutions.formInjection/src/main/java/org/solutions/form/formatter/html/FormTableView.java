package org.solutions.form.formatter.html;

import java.util.Collection;

import nyla.solutions.global.exception.FatalException;

import org.solutions.form.FormGuide;
import org.solutions.form.data.AnswerProperty;
import org.solutions.form.data.Form;
import org.solutions.form.data.FormAnswer;
import org.solutions.form.data.FormComponent;
import org.solutions.form.data.FormQuestion;
import org.solutions.form.data.FormTable;
import org.solutions.form.web.AutoCounter;

/**
 * 
 * <pre>
 * FormTableView provides a set of functions to
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public class FormTableView extends FormComponentView
{
   public static final String DEFAULT_HEADER_STYLE_CLASS = "questionTableHeader";

   public static final String DEFAULT_ANSWER_STYLE_CLASS = "answer";

   public static final String DEFAULT_TEXT_STYLE_CLASS = "questionText";

   public static final String DEFAULT_QUESTION_WIDTH = "40%";

   public static final String DEFAULT_ANSWER_WIDTH = "60%";

   public static final String DEFAULT_WIDTH = "100%";

   public static final String DEFAULT_CELLPADDING = "0";

   public static final String DEFAULT_CELLSPACING = "0";


   public FormTableView(FormTable table)
   {
      setTable(table);
   }//--------------------------------------------

   public String getCellpadding()
   {
      return fix(cellpadding, DEFAULT_CELLPADDING);
   }

   public void setCellpadding(String cellpadding)
   {
      this.cellpadding = cellpadding;
   }

   public String getCellspacing()
   {
      return fix(cellspacing, DEFAULT_CELLSPACING);
   }

   public void setCellspacing(String cellspacing)
   {
      this.cellspacing = cellspacing;
   }

   public FormTable getTable()
   {
      return table;
   }

   public void setTable(FormTable table)
   {
      this.table = table;
      if (!table.hasRows())
         table.createRow();
   }

   public FormComponent getFormComponent()
   {
      return getTable();
   }

   public Form getForm()
   {
      return table.getForm();
   }

   public void setFormComponent(FormComponent component)
   {
      setTable((FormTable) component);
   }//--------------------------------------------
   /**
    * 
    * @return the rows of the table
    */
   public Collection getRows()
   {
      
      
      Collection rows = table.getRows();
      
      if((rows == null || rows.isEmpty()) && table.isFixed() && table.getDefaultRowSize() > 0)
      {
         //create blank answers
         FormQuestion formQuestion = table.getFormQuestion();
         
         for (int col = 0; col < table.getColumnCount();col++)
         {
            formQuestion.setAnswer(" ", 0, col);            
            
         }      
         rows = table.getRows();
      }
       
      if(rows == null || rows.isEmpty())
      {
         throw new FatalException("Rows were not populated");
      }
      
      return rows;
   }//--------------------------------------------

   public boolean canDeleteRow(int rowIndex)
   {
      if (rowIndex == 0 || table.getForm().isReadOnly())
         return false;
      FormAnswer answer = table.getFormQuestion().getAnswer(rowIndex, 0);
      AnswerProperty canDeleteProp = answer
      .getProperty(FormGuide.CAN_DELETE_ROW_PROPERTY_NM);
      boolean canDelete = false;
      if (canDeleteProp != null && canDeleteProp.getValue() != null)
      {
         canDelete = new Boolean(canDeleteProp.getStringValue().toLowerCase())
         .booleanValue();
      }
      return canDelete;
   }

   public String getTableId()
   {
      return HTMLFormTableDecorator.decorateTableId(table);
   }

   public String getWidth()
   {
      return fix(width, DEFAULT_WIDTH);
   }

   public void setWidth(String width)
   {
      this.width = width;
   }

   public String getName()
   {
      if (table.isHorizontal())
         return "full_horizontal_table";
      return "full_vertical_table";
   }

   public boolean isFixed()
   {
      return table.isFixed();
   }

   public String getHeaderStyleClass()
   {
      return fix(headerStyleClass, DEFAULT_HEADER_STYLE_CLASS);
   }

   public void setHeaderStyleClass(String headerStyleClass)
   {
      this.headerStyleClass = headerStyleClass;
   }

   public String getAnswerWidth()
   {
      return fix(answerWidth, DEFAULT_ANSWER_WIDTH);
   }

   public void setAnswerWidth(String answerWidth)
   {
      this.answerWidth = answerWidth;
   }

   public String getQuestionWidth()
   {
      return fix(questionWidth, DEFAULT_QUESTION_WIDTH);
   }

   public void setQuestionWidth(String questionWidth)
   {
      this.questionWidth = questionWidth;
   }

   public String getAnswerStyleClass()
   {
      return fix(answerStyleClass, DEFAULT_ANSWER_STYLE_CLASS);
   }

   public void setAnswerStyleClass(String answerStyleClass)
   {
      this.answerStyleClass = answerStyleClass;
   }

   public String getTextStyleClass()
   {
      return fix(textStyleClass, DEFAULT_TEXT_STYLE_CLASS);
   }

   public void setTextStyleClass(String textStyleClass)
   {
      this.textStyleClass = textStyleClass;
   }

   public String getNumberLabel(Integer columnNumber)
   {
      if (isAutoNumber() && getCounter() != null)
         // return getNumberLabel() + getCounter().increment() + ".";
         return getCounter().toString() + "." + tableCounter.increment() + ".";
      return "";
   }

   public int getColumnCount()
   {
      return table.getColumnCount();
   }

   public Collection getColumns()
   {
      return new java.util.TreeSet(table.getFormColumnMap().values());
   }//--------------------------------------------

   public String getColHeadWidth()
   {
      return fix(colHeadWidth, (100 / table.getColumnCount()) + "%");
   }

   public void setColHeadWidth(String colHeadWidth)
   {
      this.colHeadWidth = colHeadWidth;
   }

   public String getNumberLabel()
   {
      return fix(numberLabel);
   }

   public void setNumberLabel(String numberLabel)
   {
      this.numberLabel = numberLabel;
   }

   public FormAnswerView getAnswer(int row, int col)
   {
      FormAnswerView view = new FormAnswerView(table.getFormQuestion()
      .getAnswer(row, col));
      view.setStyleClass(answerStyleClass);
      view.setWidth(answerWidth);
      view.setMaxLength(maxLength);
      view.setOnBlurJS(onBlurJS);
      view.setOnChangeJS(onChangeJS);
      view.setOnFocusJS(onFocusJS);
      // view.setValidateOnSave(validateOnSave);
      // view.setValidateOnSubmit(validateOnSubmit);
      return view;
   }// --------------------------------------------

   public String getMaxLength()
   {
      return maxLength;
   }

   public void setMaxLength(String maxLength)
   {
      this.maxLength = maxLength;
   }

   public String getOnBlurJS()
   {
      return onBlurJS;
   }

   public void setOnBlurJS(String onBlurJS)
   {
      this.onBlurJS = onBlurJS;
   }

   public String getOnChangeJS()
   {
      return onChangeJS;
   }

   public void setOnChangeJS(String onChangeJS)
   {
      this.onChangeJS = onChangeJS;
   }

   public String getOnClickJS()
   {
      return onClickJS;
   }

   public void setOnClickJS(String onClickJS)
   {
      this.onClickJS = onClickJS;
   }

   public String getOnFocusJS()
   {
      return onFocusJS;
   }

   public void setOnFocusJS(String onFocusJS)
   {
      this.onFocusJS = onFocusJS;
   }
   /*
    * public boolean isValidateOnSave() { return validateOnSave; } public void
    * setValidateOnSave(boolean validateOnSave) { this.validateOnSave =
    * validateOnSave; } public boolean isValidateOnSubmit() { return
    * validateOnSubmit; } public void setValidateOnSubmit(boolean
    * validateOnSubmit) { this.validateOnSubmit = validateOnSubmit; }
    */
   private FormTable table;

   private String cellpadding;

   private String cellspacing;

   private String width;

   private String headerStyleClass;

   private String questionWidth;

   private String answerWidth;

   private String answerStyleClass;

   private String textStyleClass;

   private String numberLabel;

   private String colHeadWidth;

   // to propogate through to answers
   private String maxLength;

   private String onBlurJS;

   private String onChangeJS;

   private String onClickJS;

   private String onFocusJS;

   private AutoCounter tableCounter = new AutoCounter();

}
