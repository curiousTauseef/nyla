package nyla.solutions.formInjection.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.BodyTagSupport;

import nyla.solutions.formInjection.FormDelegate;
import nyla.solutions.formInjection.data.Form;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.security.data.SecurityClient;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.web.Web;

/**
 * 
 * <b>AbstractFormTag</b> is a 
 * @author Gregory Green
 *
 */
public abstract class AbstractFormTag extends BodyTagSupport
{
   /**
    * 
    * @return form from session
    */
   protected Form getForm()
   {
      HttpSession session = Web.getInstance().getSession(pageContext);
      Form form = (Form)session.getAttribute(FormConstants.FORM);
      
      try
      {
         if(form == null)
         {
            Debugger.println(this,"Building blank form ");
            
            form = getFormDelegate().retrieveNewForm(pageContext.getRequest().getParameter(formTypeParamName));
            session.setAttribute(FormConstants.FORM, form);
            
         }
      }
      catch (Exception e)
      {
         throw new SystemException(Debugger.stackTrace(e));
      }
      
      return form;
   }//--------------------------------------------
   /**
    * 
    * @return ((HttpServletRequest)pageContext.getRequest()).getContextPath()
    */
   public String getContextPath()
   {
      return ((HttpServletRequest)pageContext.getRequest()).getContextPath();
   }//--------------------------------------------
   /**
    * 
    * @return form delegate (user login obtain from HTTP request
    */
   protected FormDelegate getFormDelegate()
   {
      SecurityClient client = new SecurityClient();
      client.setLoginID(((HttpServletRequest)this.pageContext.getRequest()).getRemoteUser());
      
      
      return new FormDelegate(client);
   }//--------------------------------------------

   private String formTypeParamName = Config.getProperty("form.injection.web.form.type.param","formTypeCode");
}
