package org.solutions.form.formatter.html;
import org.solutions.form.data.*;
/**
 * 
 * <pre>
 * HTMLColumnDecorator provides a set of functions to display HTML
 * for Column object
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class HTMLColumnDecorator extends HTMLDecorator
{
   /**
    * @param aComponent
    */
   public HTMLColumnDecorator(FormColumn column)
   {
      super(column);
      super.setStyleClass(HTMLFormQuestionDecorator.DEFAULT_QUESTION_TEXT_STYLE_CLASS);
   }//--------------------------------------------
   /**
    * (non-Javadoc)
    * 
    * @see FormComponent#getText()
    */
   public String getText()
   {
      StringBuffer text = new StringBuffer();
      FormColumn column = (FormColumn) this.getComponent();
      
      text.append("<td width=\"").append(this.decorateWidth(column)).append("\">")
      .append("<span class=\"").append(this.getStyleClass()).append("\">")
      .append(super.getText())
      .append("</span>")
      .append("</td>");
      return text.toString();
   }//--------------------------------------------
  /**
    * 
    * @return
    */
   public FormColumn getColumn()
   {
      return (FormColumn) this.getComponent();
   }//--------------------------------------------
}
