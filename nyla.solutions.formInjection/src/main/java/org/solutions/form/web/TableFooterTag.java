package org.solutions.form.web;


import javax.servlet.jsp.JspException;

import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Text;


/**
 * 
 * <pre>
 * TableFooterTag provides a set of functions to add table footer rows
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class TableFooterTag extends FormTag
{
    //public static final String ATTR_COLUMN = "column";
    
    
    private TableTag tableTag;
    
   public TableFooterTag()
   {
      super();
   }//--------------------------------------------
   
   public int doStartTag() throws JspException 
   {
       tableTag = (TableTag) super.findAncestorWithClass(this, TableTag.class);
       
       logger.debug("Found table tag footer="+tableTag.getFooter());

       //if (Text.isNull(tableTag.getFooter())) 
       //{   
       //   logger.debug("returning EVAL_BODY_BUFFERED"); 
       //}
       return EVAL_BODY_BUFFERED;
       //return SKIP_BODY;
   }//--------------------------------------------
   
   public int doAfterBody() throws JspException 
   {
      try
      {
         String content = bodyContent.getString();
         //logger.debug("body content "+content);
         if(!Text.isNull(content))
         {
            //logger.debug("setting footer to content "+content);
            tableTag.setFooter(content);
            bodyContent.clear();	      
          }
         
         logger.debug("returning SKIP_BODY");
         return SKIP_BODY;
      }
      catch(Exception e)
      {
         throw new JspException(Debugger.stackTrace(e));
      }
      
   }//--------------------------------------------
   /**
    * 
    * 
    * @see javax.servlet.jsp.tagext.Tag#doStartTag()
    */
   public int doEndTag() throws JspException
   {
       return EVAL_PAGE;
   }//--------------------------------------------
   static final long serialVersionUID = TableFooterTag.class.getName()
   .hashCode();
}
