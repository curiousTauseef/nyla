/**
 * ApplyRulesCommand.java 
 * @author Gregory Green
 * @version 1.0
 */
package nyla.solutions.formInjection.web.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nyla.solutions.formInjection.data.Form;
import nyla.solutions.formInjection.web.FormDecoder;
import nyla.solutions.global.web.controller.WebCommand;

/**
 * <b>SaveCommand</b> is a command implementation to save a form Gregory Green
 *
 */
public class SaveCommand extends AbstractFICommand implements WebCommand
{

   /**
    *
    * @see org.solutions.ipresentation.core.web.controller.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
    */
   public String execute(HttpServletRequest request,
                         HttpServletResponse response) throws Exception
   {
      
      Form form = super.getForm(request);
      
      //decoder
      FormDecoder formDecoder = new FormDecoder(form);
      
      formDecoder.decodeForm(request);
      
      form = this.getFormDelegate(request).save(formDecoder.getForm());
      
      return nextView(request);
   }//--------------------------------------------

}
