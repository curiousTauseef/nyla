/**
 * AbstractFICommand.java 
 * @author Gregory Green
 * @version 1.0
 */
package nyla.solutions.formInjection.web.core;
import javax.servlet.http.HttpServletRequest;

import nyla.solutions.formInjection.FormDelegate;
import nyla.solutions.formInjection.data.Form;
import nyla.solutions.formInjection.web.FormConstants;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.util.Text;
import nyla.solutions.global.web.Web;
import nyla.solutions.global.web.controller.AbstractCommand;


//import org.solutions.ipresentation.core.web.Web;
//import org.solutions.ipresentation.core.web.controller.AbstractCommand;
//import org.solutions.util.Text;

/**
 * <b>AbstractFICommand</b> is a 
 * @author Gregory Green
 *
 */
public abstract class AbstractFICommand extends AbstractCommand
{

   /**
    * @return the form from session FormTags.FORM attribute
    */
   public Form getForm(HttpServletRequest aRequest)
   throws Exception
   {
      Form form =  (Form)aRequest.getSession().getAttribute(FormConstants.FORM);
      if (form == null)
      {
         String formID = aRequest.getParameter(FormConstants.FORM_ID);
         if(Text.isInteger(formID))
         {
               //get form by ID
               form = this.getFormDelegate(aRequest).retrieveForm(new Integer(formID));

            //cache form in session
            this.setForm(aRequest,form);
         }
      }

      if(form == null)
         throw new SystemException("Form not found in session and HTTP param "
         +FormConstants.FORM_ID+" not provided");

      form.setAccessUser(Web.getInstance().getUser(aRequest));

      return form;
   }//--------------------------------------------
   public FormDelegate getFormDelegate(HttpServletRequest request)
   {
      return new FormDelegate(Web.getInstance().getUser(request));
   }//--------------------------------------------
   /**
    * set session attribute FormTags.FORM with aForm
    */
   public void setForm(HttpServletRequest aRequest, Form aForm)
   {
      aRequest.getSession().setAttribute(FormConstants.FORM, aForm);
   }//--------------------------------------------

}
