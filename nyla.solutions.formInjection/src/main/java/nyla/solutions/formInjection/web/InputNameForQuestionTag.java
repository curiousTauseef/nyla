package nyla.solutions.formInjection.web;


import javax.servlet.jsp.JspException;

import nyla.solutions.formInjection.data.FormQuestion;
import nyla.solutions.formInjection.formatter.html.HTMLDecorator;
import nyla.solutions.global.util.Debugger;


/**
 * <pre>
 * InputNameForQuestionTag JSP tag container for question input name rendering
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class InputNameForQuestionTag extends FormTag
{
   
   static final long serialVersionUID = InputNameForQuestionTag.class.getName()
   .hashCode();
   
   /**
    * Constructor for SectionContainerTag initializes internal 
    * data settings.
    * 
    */
   public InputNameForQuestionTag()
   {
      super();
   }//--------------------------------------------
   /**
    * 
    * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
    */
   public int doStartTag() throws JspException
   {
      try
      {
         FormQuestion question = this.getForm().findQuestionByID(new Integer(this.getId()));
         pageContext.getOut().write(HTMLDecorator.decorateQuestionName(question,null, false));
         return EVAL_BODY_INCLUDE;
      }
      catch(Exception e)
      {
         throw new JspException(Debugger.stackTrace(e));
      }
   }//--------------------------------------------

}
