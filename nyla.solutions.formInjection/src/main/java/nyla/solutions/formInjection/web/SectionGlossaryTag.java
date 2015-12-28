package nyla.solutions.formInjection.web;

import java.util.StringTokenizer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import nyla.solutions.formInjection.data.FormSection;
import nyla.solutions.formInjection.formatter.Stylist;
import nyla.solutions.global.util.Debugger;

/**
 * <pre>
 * SectionContainerTag JSP tag container for section form component
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class SectionGlossaryTag extends FormTag
{

   /**
    * Constructor for SectionContainerTag initalizes internal 
    * data settings.
    * 
    */
   public SectionGlossaryTag()
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
         FormSection section = null;
         JspWriter out = pageContext.getOut();
         
         for(StringTokenizer sectionTok= new StringTokenizer(this.getId(),",");sectionTok.hasMoreTokens();)
         {
            section = this.getForm().findSectionByNumber(sectionTok.nextToken());
            out.print("<a id=\"sectionGlossary\" name=\"sectionGlossary\" href=\"#section");
            out.print(section.getNumber());
            out.print("\">");
            out.print(Stylist.fix(section.getText()));
            out.print("</a>");
            
            if(sectionTok.hasMoreTokens())
               pageContext.getOut().write(separator); //append separator
         }
         return EVAL_BODY_INCLUDE;
      }
      catch(Exception e)
      {
         throw new JspException(Debugger.stackTrace(e));
      }
   }//--------------------------------------------
   /**
    * @return Returns the separator.
    */
   public String getSeparator()
   {
      return separator;
   }//--------------------------------------------
   /**
    * @param separator The separator to set.
    */
   public void setSeparator(String separator)
   {
      if (separator == null)
         separator = "";

      this.separator = separator;
   }//--------------------------------------------
   static final long serialVersionUID = SectionGlossaryTag.class.getName()
   .hashCode();
   private String separator = " | ";
   
}
