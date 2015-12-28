package nyla.solutions.formInjection.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;

import nyla.solutions.formInjection.FormGuide;
import nyla.solutions.formInjection.data.Form;
import nyla.solutions.formInjection.data.FormRow;
import nyla.solutions.formInjection.data.FormTable;
import nyla.solutions.formInjection.formatter.html.HTMLFormTableDecorator;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.web.View;

public class TableRowTag extends FormTag
{
    //public static final String ATTR_FORMROW = "formRow";
    
   public TableRowTag()
   {
      super();
   }//--------------------------------------------
   
   public int doStartTag() throws JspException {
       try {
           tableTag = (TableTag) super.findAncestorWithClass(this, TableTag.class);
           if (tableTag == null)
               throw new JspException("TableRowTag must be nested inside a TableTag");
           
           rows = new ArrayList(getTable().getRows());
           if (hasMoreRows()) {
               FormRow row = getNextRow();
               if (getName() != null && !getName().equals(""))
                   pageContext.setAttribute(getName(), row);
               return EVAL_BODY_BUFFERED;
           }
           return SKIP_BODY;
       }
       catch (JspException e) {
           logger.error(Debugger.stackTrace(e));
           throw e;
       }
       catch (Exception e) {
           reset();
           logger.error(Debugger.stackTrace(e));
           throw new JspException(Debugger.stackTrace(e));
       }
   }
   
   public int doAfterBody() throws JspException {
       try {	
	       Map vals = new HashMap();
	       vals.put("rowNumber", String.valueOf(row.getNumber()));
	       vals.put("styleClass", getStyleClass());
	       vals.put("answers", bodyContent.getString());
	       vals.put("canDeleteRow", new Boolean(FormGuide.canDelete(row)));
	       vals.put("imgPath", View.getDefaultContextPath() + "/images");
	       vals.put("tableId", HTMLFormTableDecorator.decorateTableId(row.getFormTable()));
	       HTMLFormTableDecorator decorator = getHTMLFormTableDecorator(tableTag.getTable(), false);
          logger.debug("template name="+template);
          
	       String tmpl = decorator.getTemplateAsString(template);
	       String text = decorator.format(vals, tmpl);
           tableTag.appendText(text);
           bodyContent.clear();
	       if (hasMoreRows()) {
	           FormRow row = getNextRow();
	           if (getName() != null && !getName().equals(""))
	               pageContext.setAttribute(getName(), row);
	           return EVAL_BODY_AGAIN;
	       }
	       return SKIP_BODY;
       }
       catch (IOException e) {
           logger.error(e);
           throw new JspException(e);
       }
   }

   /**
    * 
    * 
    * @see javax.servlet.jsp.tagext.Tag#doStartTag()
    */
   public int doEndTag() throws JspException
   {
       reset();
       return EVAL_PAGE;
   }//--------------------------------------------
   
	protected void reset() {
	    setStyleClass(null);
	    tableTag = null;
	    rows = null;
	    row = null;
	    currentRowIndex = 0;
	    if (getName() != null && !getName().equals(""))
	        pageContext.removeAttribute(getName());
	}//--------------------------------------------
	
    public Form getForm() {
        return tableTag.getForm();
    }
    public FormTable getTable() {
        return tableTag.getTable();
    }
    protected FormRow getNextRow() {
        row = (FormRow) rows.get(currentRowIndex++);
        return row;
    }
    protected boolean hasMoreRows() {
        return currentRowIndex < rows.size();
    }
    public FormRow getCurrentRow() {
        return row;
    }
    public void appendText(String text) throws IOException {
        bodyContent.println(text);
    }//--------------------------------------------
    public String getAutoNumber() 
    {
        return tableTag.getAutoNumber();
    }//--------------------------------------------
    
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
    private String template = "horizontal_table_rows";
    private ArrayList rows;
    private FormRow row;
    private TableTag tableTag;
    private int currentRowIndex = 0;
   static final long serialVersionUID = TableRowTag.class.getName().hashCode();
}
