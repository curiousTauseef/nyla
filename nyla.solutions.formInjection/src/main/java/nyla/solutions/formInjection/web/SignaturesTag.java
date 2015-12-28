package nyla.solutions.formInjection.web;

import javax.servlet.jsp.JspException;

import nyla.solutions.formInjection.data.Application;
import nyla.solutions.global.util.Debugger;

/**
 * <pre>
 * SignaturesTag this tag display the form signature on a form
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class SignaturesTag extends FormTag
{
   static final long serialVersionUID = SignaturesTag.class.getName()
   .hashCode();
   
   /**
    * Constructor for SignaturesTag initalizes internal 
    * data settings.
    * 
    */
   public SignaturesTag()
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
         //Get form
         Application form = (Application)this.getForm();
         
         if(!form.isSubmitted())
            return SKIP_BODY;
         
         //ServletRequest request = pageContext.getRequest();
         
         pageContext.include("/views/form/user_signatures.jsp");
         
         
         return EVAL_BODY_INCLUDE;
      }
      catch(Exception e)
      {
         throw new JspException(Debugger.stackTrace(e));
      }
   }//----------------------------------------
}
