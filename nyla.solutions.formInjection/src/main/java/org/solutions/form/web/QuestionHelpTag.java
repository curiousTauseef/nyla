package org.solutions.form.web;


import javax.servlet.jsp.JspException;

import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Text;

/**
 * <pre>
 * QuestionHelpTag JSP tag to render question help text
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class QuestionHelpTag extends FormTag
{
   /**
    * QUESTION_ID = "questionID"
    */
   public static final String QUESTION_ID = "questionID";

   /**
    * Constructor for QuestionHelpTag initalizes internal 
    * data settings.
    * 
    */
   public QuestionHelpTag()
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
         if(Text.isNull(this.id))
         {
            id = pageContext.getRequest().getParameter(QUESTION_ID);
         }
         
         if(Text.isNull(this.id))
         {
            throw new JspException("question ID not included.");
         }
         
         //FormQuestion question = this.getForm().findQuestionByID(new Integer(this.getId()));
         
         //TODO: String help  = question.getHelp();
         String help  = "";
         if(help == null)
            help = "";
         
         pageContext.getOut().write(help);
         
         return EVAL_BODY_INCLUDE;
      }
      catch(Exception e)
      {
         throw new JspException(Debugger.stackTrace(e));
      }
   }//--------------------------------------------
   
   static final long serialVersionUID = QuestionHelpTag.class.getName()
   .hashCode();
}
