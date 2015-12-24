package org.solutions.form.formatter.pdf;

import java.util.Iterator;
import java.util.Map;

import org.solutions.form.FormGuide;
import org.solutions.form.data.Form;
import org.solutions.form.data.FormAnswer;
import org.solutions.form.data.FormQuestion;
import org.solutions.form.data.FormSection;
import org.solutions.form.data.FormType;
import org.solutions.form.data.Section;
import org.solutions.form.formatter.FormDecorator;
import org.solutions.security.user.data.User;
import org.solutions.util.Config;
import org.solutions.util.JavaBean;
import org.solutions.util.Text;
/**
 * @author Gregory Green
 * @version 1.0
 *
 * <b>PDFFormDecorator</b> is the generic form decorator 
 * 
 */
public class PDFFormDecorator extends FormDecorator
{
   /**
    * 
    * Constructor for PDFFormDecorator initalizes internal 
    * data settings.
    *
    */
   protected PDFFormDecorator()
   {
      super(PDF_TYPE);
   }//--------------------------------------------
   /**
    * @param aComponent the application VO
    */
   protected PDFFormDecorator(
      Form aForm,
      String aTemplateName,
      short aType, User aViewer)
      throws Exception
   {
      super(aForm, aTemplateName, aType, aViewer);
   } //-----------------------------------------
   /**
    * @param aViewer the user 
    * @param aForm the form object
    * @return the application decorator
    */
   public static PDFFormDecorator getFOInstance(Form aForm, User aViewer)
      throws Exception
   {
      if(aForm == null)
         throw new IllegalArgumentException("aForm required in XMLFormDecorator.getFOInstance");
      
      FormType formType = aForm.getType();
      
      if(formType == null)
         throw new IllegalArgumentException("formType required in XMLFormDecorator.getFOInstance");
      
      //previous auto counter
      aForm.removeAttribute(FormGuide.AUTO_COUNTER_ATTRIB_NM);
      
      StringBuffer templateName = new StringBuffer("FO_")
         .append(formType.getCode()).append("_")
         .append("version").append("_")
         .append(formType.getVersion());
      
      //Factory method implementation
      String className = Config.getProperty(templateName+".form.decorator", PDFFormDecorator.class.getName());
      
      PDFFormDecorator decorator = (PDFFormDecorator)Class.forName(className).newInstance();
      decorator.init(aForm, aViewer, templateName.toString());
      
      return decorator;
   }//-----------------------------------------------
   /**
    * @param aForm
    * @param aViewer
    * @param aTemplateName
    * @param aDecorator
    */
   protected void init(Form aForm, User aViewer, String aTemplateName)
   {
      setComponent(aForm);
      
      setViewer(aViewer);
      setTemplate(loadTemplate(aTemplateName,this));
   }//--------------------------------------------
   /**
    * @return decorator text version of the form
    */
   public String getText()
   {
      sectionNumber = 1;
      currentNumber = 1;
      
      try
      {         
         Map map = toMap();
         
         
         return Text.format(getTemplate(), map);
      }
      catch (Exception e)
      {
         throw new RuntimeException(e);
      }
   } //-----------------------------------
   /**
    * Add information to map
    * @param aMap the map to add information
    * @throws Exception
    */
   public Map toMap()
   throws Exception
   {
      Form form = (Form) this.getComponent();
      Map map = JavaBean.toMap(form);
         
      //format application information      
                  
      if(type == PDF_TYPE)
      {
          //Set template dir for FO to load image file
           map.put("templateDIR", Config.getProperty("template.dir"));
      }      
      
      //process sections
      Section section = null;
      FormQuestion formQuestion = null;
      for (Iterator i = form.getSections().iterator(); i.hasNext();)
      {
         section = (FormSection) i.next();
         map.put("sectionHeader"+section.getNumber(),PDFSectionDecorator.getInstance(section,this).getText());
         map.put("sectionName"+section.getNumber(),fix(section.getText()));
         
         
         for (Iterator questionI = form.findQuestionsBySection(section.getNumber()).iterator(); questionI.hasNext();)
         {
            formQuestion = (FormQuestion) questionI.next();
            
            map.put("question"+formQuestion.getPrimaryKey(), decorateQuestion(formQuestion));
            
            map.put("answer"+formQuestion.getPrimaryKey(), decorateAnswer(formQuestion));
         }
      }
      
      //Get form properties
      map.putAll(form.getFormProps());
      
      
      //Put signatures
      String signatures = PDFSignaturesDecorator.getInstance(form, this.getViewer()).getText();
      map.put("signatures",signatures);
      
      return map;
   }//-----------------------------------------------------------
   /**
    * 
    * @return the bytes
    * @throws Exception
    */
   public byte[] toPDF() throws Exception
   {
         return super.toPDF(this.getText());
   } //---------------------------------------
   /**
    * @return true if the question must be skipped
    */
   protected boolean mustSkip(FormQuestion aQuestion)
   {
      if(aQuestion == null)
      {
         return true;
      }

      return super.mustSkip(aQuestion);
   }//---------------------------------------   
   /**
    * 
    * @param question the question to be decorated
    * @param appText the application text
    * @throws Exception
    */
   protected String decorateQuestion(FormQuestion aFormQuestion)
   throws Exception
   {
      if(mustSkip(aFormQuestion))
      {
         return ""; // do nothing
      }
            
      return PDFQuestionDecorator.getInstance(aFormQuestion,this,currentNumber).getText();            
   }//-----------------------------------------------------  
   /**
    * 
    * @param aAnswer the answer to decorate
    * @return PDFAnswerDecorator.getFOInstance.text
    * @throws Exception
    */
   protected String decorateAnswer(FormQuestion aFormQuestion)
   throws Exception
   {
      if(mustSkip(aFormQuestion))
      {
         return ""; // do nothing
      }

      if (aFormQuestion == null)
         throw new IllegalArgumentException(
         "aFormQuestion required in PDFFormDecorator.decorateAnswer");
      
      FormAnswer answer = aFormQuestion.getAnswer();
            
      return PDFAnswerDecorator.getFOInstance(answer,this.getViewer()).getText();            
   }//-----------------------------------------------------     
   protected int sectionNumber = 1;
   protected int currentNumber = 1;

}
