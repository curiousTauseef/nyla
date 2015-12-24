package org.solutions.form.web;


import javax.servlet.jsp.JspException;

import nyla.solutions.global.data.Data;
import nyla.solutions.global.util.Debugger;

import org.solutions.form.data.Form;
import org.solutions.form.data.FormColumn;
import org.solutions.form.data.FormQuestion;
import org.solutions.form.formatter.html.HTMLDecorator;

/**
 * <pre>
 * InputNameForQuestionTag JSP tag container for question input name rendering
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class InputNameForColumnTag extends FormTag
{
   static final long serialVersionUID = InputNameForColumnTag.class.getName()
   .hashCode();

   /**
    * Constructor for SectionContainerTag initalizes internal 
    * data settings.
    * 
    */
   public InputNameForColumnTag()
   {
      super();
   }//--------------------------------------------
   /**
    * v_false_date__0_2000_q5
    * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
    */
   public int doStartTag() throws JspException
   {
      try
      {
         Form form = this.getForm();
         FormQuestion question = form.findQuestionByID(new Integer(this.getId()));
         FormColumn column = question.getFormColumn(columnNumber);
         pageContext.getOut().write(HTMLDecorator.decorateQuestionName(question,column, false));
         return EVAL_BODY_INCLUDE;
      }
      catch(Exception e)
      {
         throw new JspException(Debugger.stackTrace(e));
      }
   }//--------------------------------------------
   
   /**
    * @return Returns the columnNumber.
    */
   public int getColumnNumber()
   {
      return columnNumber;
   }//--------------------------------------------
   /**
    * @param columnNumber The columnNumber to set.
    */
   public void setColumnNumber(int columnNumber)
   {
      if (columnNumber < 0)
         throw new IllegalArgumentException(
         "columnNumber > -1 required in InputNameForColumnTag.setColumnNumber");
      
      this.columnNumber = columnNumber;
   }//--------------------------------------------
   
   /**
    * @return Returns the tableID.
    */
   public int getTableID()
   {
      return tableID;
   }//--------------------------------------------
   /**
    * @param tableID The tableID to set.
    */
   public void setTableID(int tableID)
   {
      if (tableID < 1)
         throw new IllegalArgumentException(
         "tableID > 0 required in InputNameForColumnTag.setTableID");

      this.tableID = tableID;
   }//--------------------------------------------
   private int tableID = Data.NULL;
   private int columnNumber = Data.NULL;
}
