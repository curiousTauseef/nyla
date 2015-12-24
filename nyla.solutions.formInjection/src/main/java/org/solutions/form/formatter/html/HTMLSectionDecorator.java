package org.solutions.form.formatter.html;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.solutions.form.data.FormQuestion;
import org.solutions.form.data.FormSection;

public class HTMLSectionDecorator extends HTMLFormDecorator
{
   /**
    * @param aComponent
    */
   public HTMLSectionDecorator(FormSection section)
   {
      super(section);
      super.setStyleClass("sectionText");
   }//--------------------------------------------
   /**
    * 
    * 
    * @see org.solutions.form.data.FormComponent#getText()
    */
   public String getText()
   {
      FormSection section = (FormSection) this.getComponent();      
      Collection formQuestions = section.getFormQuestions();
      
      //formQuestions = Organizer.sortByJavaBeanProperty("number",formQuestions);
      FormQuestion formQuestion = null;      
       
      StringBuffer questions = new StringBuffer();

      for(Iterator i=formQuestions.iterator();i.hasNext();)
      {

         formQuestion = (FormQuestion) i.next();
         questions.append(getHTMLFormQuestionDecorator(formQuestion).getText()).append(NEWLINE);;
      }
      
      String template = this.getTemplateAsString("section");
      Map map = new HashMap();
      map.put("styleClass",this.getStyleClass());
      map.put("text",super.getText());
      map.put("number",section.getNumber());
      map.put("questions",questions);
      return format(map,template);
   }//--------------------------------------------
}
