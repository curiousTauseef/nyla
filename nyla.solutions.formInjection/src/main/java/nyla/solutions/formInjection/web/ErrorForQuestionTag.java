package nyla.solutions.formInjection.web;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import nyla.solutions.global.util.Debugger;



/**
 * <pre>
 * ErrorForQuestionTag provides a set of functions to
 * decorator question answer input errors.
 * 
 * Such as; 
 * <span id="v_true_date__0_2000_q6Error" class="error"></span>
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class ErrorForQuestionTag extends FormTag
{
   static final long serialVersionUID = ErrorForQuestionTag.class.getName()
   .hashCode();
   
   /**
    * Constructor for ErrorForQuestionTag initalizes internal 
    * data settings.
    * 
    */
   public ErrorForQuestionTag()
   {
      super();      
   }//--------------------------------------------
   /**
    * 
    * @see javax.servlet.jsp.tagext.BodyTagSupport#doStartTag()
    */
   public int doStartTag() throws JspException
   {
      try
      {
         String inputName = retrieveQuestionInputName();
         JspWriter out = this.pageContext.getOut();
         
         //<span id="v_true_date__0_2000_q6[0][0];tablePK=10001;Error" class="error"></span>
         out.write("<span id=\"");
         out.write(inputName);
         out.write("Error\" class=\"error\"></span>");
         
         return EVAL_BODY_INCLUDE;
      }
      catch(Exception e)
      {
         this.logger.error(e);
         throw new JspException(Debugger.stackTrace(e));
      }
   }//--------------------------------------------
}
