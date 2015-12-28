package nyla.solutions.formInjection.web;

import javax.servlet.jsp.JspException;

import nyla.solutions.formInjection.data.Form;
import nyla.solutions.formInjection.security.FormGuard;


/**
 * <pre>
 * FormGuardTag provides a set of functions to protect form context.
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class FormGuardTag extends FormTag
{
   static final long serialVersionUID = FormGuardTag.class.getName().hashCode();

   /**
    * Constructor for FormGuardTag initalizes internal 
    * data settings.
    * 
    */
   public FormGuardTag()
   {
      super();
   }//--------------------------------------------
   /**
    * 
    * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
    */
   public int doStartTag() throws JspException
   {
      Form form = this.getForm();
      
      if(this.exclude)
      {
          
         //negative logic
         if(FormGuard.canPassCheckPoint(checkPoint, form, this.getUser()))
         {
            return SKIP_BODY;  
         }
         else
         {
            return EVAL_BODY_INCLUDE;
         }        
      }
      else
      {         
         //positive logic
         if(FormGuard.canPassCheckPoint(checkPoint, form, this.getUser()))
         {
            return EVAL_BODY_INCLUDE;
         }
         else
         {
            return SKIP_BODY;
         }            
      }
   }//----------------------------------------   
   /**
    * @return Returns the exclude.
    */
   public boolean isExclude()
   {
      return exclude;
   }//--------------------------------------------
   /**
    * @param exclude The exclude to set.
    */
   public void setExclude(boolean exclude)
   {
      this.exclude = exclude;
   }//--------------------------------------------
   
   /**
    * @return Returns the checkPoint.
    */
   public String getCheckPoint()
   {
      return checkPoint;
   }//--------------------------------------------
   /**
    * @param checkPoint The checkPoint to set.
    */
   public void setCheckPoint(String checkPoint)
   {
      if (checkPoint == null)
         checkPoint = "";

      this.checkPoint = checkPoint;
   }//--------------------------------------------
   private boolean exclude = false;
   private String checkPoint = ""; 
}