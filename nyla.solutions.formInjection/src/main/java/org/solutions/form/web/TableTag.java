package org.solutions.form.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;

import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Text;

import org.solutions.form.data.FormQuestion;
import org.solutions.form.data.FormTable;
import org.solutions.form.formatter.html.HTMLFormTableDecorator;

public class TableTag extends FormTag
{
    
   /**
    * Constructor for QuestionTag initalizes internal data settings.
    *  
    */
   public TableTag()
   {
      super();
   }//--------------------------------------------
   
   public int doStartTag() throws JspException {
       try {
	       String questionID = this.getId();
	       if (!Text.isNull(questionID))
	       {
	           formQuestion = getForm().findQuestionByID(new Integer(questionID));
	           if (!formQuestion.hasTable())
	               throw new JspException("Not a table");
	           
	           // create blank rows if necessary
	           FormTable table = formQuestion.getFormTable();
	           int defRows = Integer.parseInt(getDefaultRowCount());
	           int rowIndex = 0;
	           while (table.getRowCount() < defRows) {
	               HTMLFormTableDecorator.createBlankHorizontalRow(table, rowIndex++);
	           }
	           
	           if (getName() != null && !getName().equals(""))
	               pageContext.setAttribute(getName(), formQuestion.getFormTable());
	           
	           // need to request the number label anyway, to ensure
	           // counter is incremented
	           if (!"skip".equalsIgnoreCase(getAutoNumber()))
	               numberLabel = getNumberLabel(formQuestion);
	       }
       }
       catch (Exception e)
       {
          logger.error(Debugger.stackTrace(e));
          throw new JspException(Debugger.stackTrace(e));
       }
       return EVAL_BODY_BUFFERED;
   }
   
   /**
    * 
    * 
    * @see javax.servlet.jsp.tagext.Tag#doStartTag()
    */
   public int doEndTag() throws JspException
   {
       try {
          
           FormTable table = formQuestion.getFormTable();
           
           HTMLFormTableDecorator decorator = getHTMLFormTableDecorator(table, false);
           
           Map vals = decorator.toMap();
           vals.put("border", getBorder() != null ? getBorder() : "0");
           vals.put("cellpadding", getCellpadding() != null ? getCellpadding() : "0");
           vals.put("cellspacing", getCellspacing() != null ? getCellspacing() : "0");
           vals.put("width", getWidth() != null ? getWidth() : "100%");
           vals.put("styleClass", getStyleClass());
           
           if("vertical_table".equals(template))
           {
              vals.put("rows", this.header+vals.get("rows")+this.footer);
           }
           else
           {
              vals.put("rowdata", this.header+bodyContent.getString()+" "+this.footer);
           }
              
           
           if (!Boolean.valueOf(getAutoNumber()).booleanValue())
               numberLabel = "";
           else
               decorator.setAutoNumber(true);
           vals.put("numberLabel", numberLabel);
           
           //logger.debug("template="+template);
           
           String tmpl = decorator.getTemplateAsString(template);
           String text = decorator.format(vals, tmpl);
           pageContext.getOut().write(text);
           
           return EVAL_PAGE;
       }
       catch (Exception e) {
           logger.error(Debugger.stackTrace(e));
           throw new JspException(Debugger.stackTrace(e));
       }
       finally {
           reset();
       }
   }//--------------------------------------------
    public String getBorder() {
        return border;
    }
    public void setBorder(String border) {
        this.border = border;
    }
    public String getCellpadding() {
        return cellpadding;
    }
    public void setCellpadding(String cellpadding) {
        this.cellpadding = cellpadding;
    }
    public String getCellspacing() {
        return cellspacing;
    }
    public void setCellspacing(String cellspacing) {
        this.cellspacing = cellspacing;
    }
    public String getWidth() {
        return width;
    }
    public void setWidth(String width) {
        this.width = width;
    }
    public String getDefaultRowCount() {
        return defaultRowCount != null && !defaultRowCount.equals("") ? defaultRowCount : "1";
    }
    public void setDefaultRowCount(String defaultRowCount) {
        this.defaultRowCount = defaultRowCount;
    }
    /*
    public String getAutoNumber() {
        String s = super.getAutoNumber();
        return s != null && !s.equals("") ? "true" : "false";
    }
    */
    protected void reset() {
        border = null;
        cellpadding = null;
        cellspacing = null;
        width = null;
        setStyleClass(null);
        formQuestion = null;
        numberLabel = "";
        defaultRowCount = null;
        if (getName() != null && !getName().equals(""))
            pageContext.removeAttribute(getName());
    }//--------------------------------------------
    public FormQuestion getFormQuestion() 
    {
        return formQuestion;
    }//--------------------------------------------
    public FormTable getTable() 
    {
        return formQuestion != null ? formQuestion.getFormTable() : null;
    }//--------------------------------------------
    public void appendText(String text) throws IOException 
    {
        bodyContent.println(text);
    }//--------------------------------------------
    
   /**
    * @return Returns the footer.
    */
   public String getFooter()
   {
      return footer;
   }//--------------------------------------------
   /**
    * @param footer The footer to set.
    */
   public void setFooter(String footer)
   {
      if (footer == null)
         footer = "";

      this.footer = footer;
   }//--------------------------------------------
   
   /**
    * @return Returns the header.
    */
   public String getHeader()
   {
      return header;
   }//--------------------------------------------
   /**
    * @param header The header to set.
    */
   public void setHeader(String header)
   {
      if (header == null)
         header = "";

      this.header = header;
   }//-------------------------------------------
   
   /**
    * @return Returns the template.
    */
   public String getTemplate()
   {
      return template;
   }//--------------------------------------------
   /**
    * @param template The template to set.
    */
   public void setTemplate(String template)
   {
      if (template == null)
         template = "";

      this.template = template;
   }//--------------------------------------------
   private String template = "table_tag";
   private String border;
   private String cellpadding;
   private String cellspacing;
   private String width;
   private String defaultRowCount;
   private FormQuestion formQuestion;
   private String numberLabel;
   private String footer = "";
   private String header = "";
   static final long serialVersionUID = TableTag.class.getName().hashCode();
}
