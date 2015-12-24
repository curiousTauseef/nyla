package org.solutions.form.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Text;

import org.solutions.form.data.FormSection;
import org.solutions.form.formatter.Stylist;



/**
 * <pre>
 * SectionContainerTag JSP tag container for question form component
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class SectionContainerTag extends FormTag
{
   static final long serialVersionUID = SectionContainerTag.class.getName()
   .hashCode();
   
    private FormSection section;
    private String alignment="";



   /**
    * Constructor for SectionContainerTag initializes internal 
    * data settings.
    * 
    */
   public SectionContainerTag()
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
         section = this.getForm().findSectionByNumber(this.id);
                  
         if(section == null)
         {
            throw new SystemException("Section not found id="+id);
            //Debugger.printWarn("Section not found id="+this.id+" creating new ");
            //Section section = new Section();
            //section.setId(this.id);
            //section = this.getForm().createFormSection(id);
         }
         AutoCounter counter = new AutoCounter(Integer.parseInt(this.id));
         pageContext.setAttribute("counter", counter);
         // never changes -- used for alternating styles
         pageContext.setAttribute("sectionCounter", counter);
         
         Map map = new HashMap();
         map.put("text", Stylist.fix(section.getText()));
         map.put("number",section.getNumber());
         String styleClass = "sectionText";
        String tWidth = "100%";
          String alignment = this.getAlignment();
          if (alignment.length()>1) alignment = " align=" + alignment ;
          //System.out.println("In SectionContainer: TextStyle<" + this.getStyleClass() + "> Width<"+this.getQuestionWidth()+">");
       if (!this.getStyleClass().equalsIgnoreCase("")) styleClass=this.getStyleClass();
        if (!this.getQuestionWidth().equalsIgnoreCase("")) tWidth=this.getQuestionWidth();
         StringBuffer html = new StringBuffer("<table width=\"" + tWidth + "\"" + alignment + " border=\"0\" cellspacing=\"0\" cellpadding=\"3\"> ")
                       .append("   <tr class=\"" + styleClass + "\">")
                       .append("      <td colspan=\"2\" class=\"" + styleClass + "\"><a name=\"section${number}\">${number} - ${text}</a></td> ")
                       .append("   </tr>");
//            .append("   <tr class=\"sectionText\">")
//          String htmlCmd=Text.format(html.toString(),map);
//          System.out.println("htmlCommand:"+htmlCmd);
         pageContext.getOut().write(Text.format(html.toString(),map));
         return EVAL_BODY_INCLUDE;
      }
      catch(Exception e)
      {
         throw new JspException(Debugger.stackTrace(e));
      }
   }//--------------------------------------------
   /**
    * 
    * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
    */
   public int doEndTag() throws JspException
   {
      try
      {
          section = null;
          pageContext.removeAttribute("counter");
          pageContext.removeAttribute("sectionCounter");
         pageContext.getOut().write("</table>");
         return TagSupport.EVAL_PAGE;
      }
      catch(Exception e)
      {
         throw new JspException(Debugger.stackTrace(e));
      }
   }//--------------------------------------------
   
   public FormSection getSection() {
       return section;
   }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }
}
