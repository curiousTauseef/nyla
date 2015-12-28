package nyla.solutions.formInjection.web.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nyla.solutions.formInjection.FormDelegate;
import nyla.solutions.formInjection.data.Form;
import nyla.solutions.formInjection.web.FormConstants;
import nyla.solutions.formInjection.web.FormDecoder;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.security.data.SecurityCredential;
import nyla.solutions.global.util.Text;
import nyla.solutions.global.web.Web;
import nyla.solutions.web.spring.web.AbstractDispatchAction;

import org.springframework.web.servlet.ModelAndView;



//import org.solutions.ipresentation.core.web.Web;
//import org.solutions.ipresentation.spring.web.AbstractDispatchAction;
//import org.solutions.security.data.SecurityCredential;
//import org.solutions.util.Text;
//import org.springframework.web.servlet.ModelAndView;

/**
 * <pre>
 *  DocImportAction is a web controller. This class act a mediator
 *  for transform HTTP request to an Document object to be process to DocImport Service. 
 *  It also handles request validation/authorization.
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public class FormInjectionAction extends AbstractDispatchAction
{
   /**
    * 
    * @param request
    * @param response
    * @return
    * @throws Exception
    */
   public ModelAndView applyBuinessRules(HttpServletRequest request,
                                     HttpServletResponse response) throws Exception
   {
      System.out.println("Hello World");
      //ModelAndView mv = new ModelAndView("hello");
      
      FormDelegate formService = this.getFormService(request);
      
      Form form =getForm(request);
      
      logger.debug("saving form ID="+form.getPrimaryKey());
      
      FormDecoder decoder = new FormDecoder(form);

      decoder.decodeForm(Web.toMap(request));
      
      form = decoder.getForm();
      SecurityCredential user = formService.getUser();
      form.setAccessUser(user);
      
      form = formService.applyBusinessRules(form);
      
      setForm(request, form);
      
      return super.nextView();
   }// --------------------------------------------
   /**
    * set session attribute FormTags.FORM with aForm
    */
   protected void setForm(HttpServletRequest aRequest, Form aForm)
   {
      aRequest.getSession().setAttribute(FormConstants.FORM, aForm);
   }//--------------------------------------------
   /**
    * @return the form from session FormTags.FORM attribute
    */
   protected Form getForm(HttpServletRequest aRequest)
   throws Exception
   {
      Form form =  (Form)aRequest.getSession().getAttribute(FormConstants.FORM);
      if (form == null)
      {
         String formID = aRequest.getParameter(FormConstants.FORM_ID);
         if(Text.isInteger(formID))
         {
               //get form by ID
               form = this.getFormService(aRequest).retrieveForm(new Integer(formID));

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
   private FormDelegate getFormService(HttpServletRequest request)
   {
      return new FormDelegate(Web.getInstance().getUser(request));
   }//--------------------------------------------


}



