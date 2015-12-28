package nyla.solutions.formInjection.web;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.jsp.JspException;

import nyla.solutions.formInjection.data.FormAnswer;
import nyla.solutions.formInjection.formatter.html.HTMLFormTableDecorator;
import nyla.solutions.global.util.Debugger;

public class TableColumnTag extends FormTag
{
    //public static final String ATTR_COLUMN = "column";
    private FormAnswer answer;
    private ArrayList answers;
    private String colNumbers;
    private int currentColIndex;
    private TableRowTag tableRowTag;
    
   public TableColumnTag()
   {
      super();
   }//--------------------------------------------
   
   public int doStartTag() throws JspException 
   {
       tableRowTag = (TableRowTag) super.findAncestorWithClass(this, TableRowTag.class);
       if (tableRowTag == null)
           throw new JspException("TableColumnTag must be nested inside a TableRowTag");

       ArrayList a = new ArrayList(tableRowTag.getCurrentRow().getAnswers());
       //Collections.reverse(a);
       answers = new ArrayList();
       
       String colNumbers = getColNumbers();
       if (colNumbers == null || colNumbers.equals(""))
           answers.addAll(a);
       else {
           StringTokenizer tokenizer = new StringTokenizer(colNumbers, ",");
           while (tokenizer.hasMoreTokens()) {
               int num = Integer.parseInt(tokenizer.nextToken());
               answers.add(a.get(num));
           }
       }
       if (hasMoreColumns()) {
           FormAnswer answer = getNextColumn();
           if (getName() != null && !getName().equals(""))
               pageContext.setAttribute(getName(), answer);
	       return EVAL_BODY_BUFFERED;
       }
       return SKIP_BODY;
   }
   
   public int doAfterBody() throws JspException {
       try {
           FormAnswer answer = getCurrentColumn();
           if (answer != null) {
	           Map vals = new HashMap();
	           vals.put("colNumber", answer.getColumnNumber());
	           vals.put("textStyleClass", this.getTextStyleClass());
	           vals.put("answerStyleClass", this.getAnswerStyleClass());
	           vals.put("questionWidth", this.getQuestionWidth());
	           vals.put("answerWidth", this.getAnswerWidth());
	           vals.put("onclick", this.getOnclick());
	           vals.put("onchange", this.getOnchange());
	           vals.put("onfocus", this.getOnfocus());
	           vals.put("onblur", this.getOnblur());
	           vals.put("columnText", answer.getFormColumn().getName());
	           vals.put("answer", getHTMLAnswerDecorator(answer, false).getText());
	           
	           // need to request the number label anyway, to ensure
	           // counter is incremented
	           String numberLabel = "";
	           if (!"skip".equalsIgnoreCase(getAutoNumber()))
	               numberLabel = getNumberLabel(answer);

	           HTMLFormTableDecorator decorator = getHTMLFormTableDecorator(tableRowTag.getTable(), false);
	           if (!Boolean.valueOf(getAutoNumber()).booleanValue())
	               numberLabel = "";
	           else
	           	   decorator.setAutoNumber(true);
	           vals.put("numberLabel", numberLabel);
	           
	           
	           String tmpl = null;
	           if (bodyContent.getString() != null && !bodyContent.getString().trim().equals("")) {
	               tmpl = bodyContent.getString();
	               bodyContent.clear();
	           }
	           else {
	               tmpl = decorator.getTemplateAsString("horizontal_table_row_answer");
	           }
	           String text = decorator.format(vals, tmpl);
	           tableRowTag.appendText(text);
           }
	       if (this.hasMoreColumns()) {
	           FormAnswer a = getNextColumn();
	           if (getName() != null && !getName().equals(""))
	               pageContext.setAttribute(getName(), a);
	           return EVAL_BODY_AGAIN;
	       }
	       return SKIP_BODY;
       }
       catch (Exception e) {
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
       try {
           reset();
           return EVAL_PAGE;
       }
       catch (Exception e) {
           reset();
           logger.error(Debugger.stackTrace(e));
           throw new JspException(Debugger.stackTrace(e));
       }
   }//--------------------------------------------
   
   public String getColNumbers() {
       return colNumbers;
   }
   public void setColNumbers(String colNumbers) {
       this.colNumbers = colNumbers;
   }//--------------------------------------------
   
   protected boolean hasMoreColumns() {
       return currentColIndex < answers.size();
   }
   protected FormAnswer getNextColumn() {
       answer = (FormAnswer) answers.get(currentColIndex++);
       return answer;
   }
   public FormAnswer getCurrentColumn() {
       return answer;
   }
   public String getAutoNumber() {
       return tableRowTag.getAutoNumber();
   }
   
	protected void reset() {
	    answers = null;
	    answer = null;
	    setTextStyleClass(null);
	    setAnswerStyleClass(null);
	    setQuestionWidth(null);
	    setAnswerWidth(null);
	    setOnclick(null);
	    setOnchange(null);
	    setOnblur(null);
	    setOnfocus(null);
	    colNumbers = null;
	    tableRowTag = null;
	    currentColIndex = 0;
	    if (getName() != null && !getName().equals(""))
	        pageContext.removeAttribute(getName());
	}//--------------------------------------------
    static final long serialVersionUID = TableColumnTag.class.getName()
   .hashCode();
}
