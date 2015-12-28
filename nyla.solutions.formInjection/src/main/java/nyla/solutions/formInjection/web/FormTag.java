package nyla.solutions.formInjection.web;


import nyla.solutions.formInjection.data.FormAnswer;
import nyla.solutions.formInjection.data.FormQuestion;
import nyla.solutions.formInjection.data.FormTable;
import nyla.solutions.formInjection.data.QuestionAttribute;
import nyla.solutions.formInjection.formatter.html.HTMLAnswerDecorator;
import nyla.solutions.formInjection.formatter.html.HTMLDecorator;
import nyla.solutions.formInjection.formatter.html.HTMLFormQuestionDecorator;
import nyla.solutions.formInjection.formatter.html.HTMLFormTableDecorator;
import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.operations.logging.Log;
import nyla.solutions.global.security.data.SecurityCredential;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.web.Web;


/**
 * <pre>
 * QuestionTag form question JSP tag
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public abstract class FormTag extends AbstractFormTag
{
   /**
    * Constructor for QuestionTag initializes internal 
    * data settings.
    * 
    */
   public FormTag()
   {
      super();
   }//--------------------------------------------
   /**
    * @param aFormQuestion the form question
    * @return
    */
   protected HTMLFormQuestionDecorator getHTMLFormQuestionDecorator(FormQuestion aFormQuestion , boolean isOffline)
   {
      HTMLFormQuestionDecorator decorator = new HTMLFormQuestionDecorator(aFormQuestion);
      initDecorator( decorator, isOffline);
      return decorator;
   }//--------------------------------------------
   /*
   protected HTMLSectionDecorator getHTMLSectionDecorator(FormSection aSection , Form aForm, boolean isOffline)
   {
      HTMLSectionDecorator decorator = new HTMLSectionDecorator(aSection, aForm);
      initDecorator( decorator, isOffline);
      return decorator;
   }//--------------------------------------------
   */
   protected HTMLFormTableDecorator getHTMLFormTableDecorator(FormTable formTable , boolean isOffline)
   {
      HTMLFormTableDecorator decorator = new HTMLFormTableDecorator(formTable);
      initDecorator( decorator, isOffline);
      return decorator;
   }//--------------------------------------------
   protected HTMLAnswerDecorator getHTMLAnswerDecorator(FormAnswer answer, boolean isOffline)
   {
      HTMLAnswerDecorator decorator = new HTMLAnswerDecorator(answer);
      initDecorator( decorator, isOffline);
      return decorator;
   }//--------------------------------------------

   /**
    * @param aIsOffline
    * @param aDecorator
    */
   private void initDecorator(HTMLDecorator aDecorator, boolean aIsOffline)
   {
      aDecorator.setStyleClass(this.styleClass);
      aDecorator.setOnClickJS(this.onclick);
      aDecorator.setOnChangeJS(this.onchange);
      aDecorator.setOnBlurJS(this.onblur);
      aDecorator.setOnFocusJS(this.onfocus);
      aDecorator.setReadOnly(this.getForm().isReadOnly());
      aDecorator.setOffline(aIsOffline);
      aDecorator.setQuestionWidth(this.getQuestionWidth());
      aDecorator.setAnswerWidth(this.getAnswerWidth());
      aDecorator.setQuestionTextStyleClass(this.textStyleClass);
      aDecorator.setAnswerStyle(this.answerStyleClass);
   }//--------------------------------------------
   /**
    * @return Returns the answerStyleClass.
    */
   public final String getAnswerStyleClass()
   {
      return answerStyleClass;
   }//--------------------------------------------
   /**
    * @param answerStyleClass The answerStyleClass to set.
    */
   public final void setAnswerStyleClass(String answerStyleClass)
   {
      if (answerStyleClass == null || answerStyleClass.equals(""))
         answerStyleClass = HTMLFormQuestionDecorator.DEFAULT_ANSWER_STYLE_CLASS;

      this.answerStyleClass = answerStyleClass;
   }//--------------------------------------------
   /**
    * @return Returns the styleClass.
    */
   public final String getStyleClass()
   {
      return styleClass;
   }//--------------------------------------------
   /**
    * @param styleClass The styleClass to set.
    */
   public final void setStyleClass(String styleClass)
   {
      if (styleClass == null || styleClass.equals(""))
         styleClass = HTMLFormQuestionDecorator.DEFAULT_QUESTION_STYLE_CLASS;

      this.styleClass = styleClass;
   }//--------------------------------------------
   /**
    * @return Returns the textStyleClass.
    */
   public final String getTextStyleClass()
   {
      return textStyleClass;
   }//--------------------------------------------
   /**
    * @param textStyleClass The textStyleClass to set.
    */
   public final void setTextStyleClass(String textStyleClass)
   {
      if (textStyleClass == null || textStyleClass.equals(""))
         textStyleClass = HTMLFormQuestionDecorator.DEFAULT_QUESTION_TEXT_STYLE_CLASS;

      this.textStyleClass = textStyleClass;
   }//--------------------------------------------
   /**
    * @return Returns the onblur.
    */
   public final String getOnblur()
   {
      return onblur;
   }//--------------------------------------------
   /**
    * @param onblur The onblur to set.
    */
   public final void setOnblur(String onblur)
   {
      if (onblur == null)
         onblur = "";

      this.onblur = onblur;
   }//--------------------------------------------
   /**
    * @return Returns the onchange.
    */
   public final String getOnchange()
   {
      return onchange;
   }//--------------------------------------------
   /**
    * @param onchange The onchange to set.
    */
   public final void setOnchange(String onchange)
   {
      if (onchange == null)
         onchange = "";

      this.onchange = onchange;
   }//--------------------------------------------
   /**
    * @return Returns the onclick.
    */
   public final String getOnclick()
   {
      return onclick;
   }//--------------------------------------------
   /**
    * @param onclick The onclick to set.
    */
   public final void setOnclick(String onclick)
   {
      if (onclick == null)
         onclick = "";

      this.onclick = onclick;
   }//--------------------------------------------
   
   /**
    * @return Returns the onfocus.
    */
   public final String getOnfocus()
   {
      return onfocus;
   }//--------------------------------------------
   /**
    * @param onfocus The onfocus to set.
    */
   public final void setOnfocus(String onfocus)
   {
      if (onfocus == null)
         onfocus = "";

      this.onfocus = onfocus;
   }//--------------------------------------------
   
   /**
    * @return Returns the questionWidth.
    */
   public final String getQuestionWidth()
   {
      return questionWidth;
   }//--------------------------------------------
   /**
    * @param questionWidth The questionWidth to set.
    */
   public final void setQuestionWidth(String questionWidth)
   {
      if (questionWidth == null)
         questionWidth = "40%";

      this.questionWidth = questionWidth;
   }//--------------------------------------------
   /**
    * @return Returns the answerWidth.
    */
   public final String getAnswerWidth()
   {
      return answerWidth;
   }//--------------------------------------------
   /**
    * @param answerWidth The answerWidth to set.
    */
   public final void setAnswerWidth(String answerWidth)
   {
      if (answerWidth == null)
         answerWidth = "60%";

      this.answerWidth = answerWidth;
   }//--------------------------------------------
   
	public String getName() {
	    return name;
	}
	public void setName(String name) {
	    this.name = name;
	}
	public String getAutoNumber() {
	    return autoNumber;
	}
	public void setAutoNumber(String autoNumber) {
	    this.autoNumber = autoNumber;
	}
	public String getNumberFormat() {
	    return numberFormat;
	}
	public void setNumberFormat(String numberFormat) {
	    this.numberFormat = numberFormat;
	}
	public String getValidateOnSave() {
	    return validateOnSave;
	}
	public void setValidateOnSave(String validateOnSave) {
	    this.validateOnSave = validateOnSave;
	}
	public String getValidateOnSubmit() {
	    return validateOnSubmit;
	}
	public void setValidateOnSubmit(String validateOnSubmit) {
	    this.validateOnSubmit = validateOnSubmit;
	}
   /**
    * Get user from session
    * @return (SecurityCredential)Web.getSession(pageContext).getAttribute(FormTags.USER);
    */
   protected SecurityCredential getUser()
   {
      SecurityCredential user = (SecurityCredential)Web.getInstance().getSession(pageContext).getAttribute("user");
      if(user == null)
         throw new SecurityException("User not found in session");
      
      return user;
   }//--------------------------------------------   
   
   protected AutoCounter updateCounter(FormQuestion question) {
       AutoCounter counter = getCounter(question);
       if(counter == null)
          return null;//nothing to update
       
       counter.increment();
       counter.setFormComponentId(question.getPrimaryKey());
       return counter;
   }//--------------------------------------------
   
   
   protected AutoCounter getCounter(FormQuestion question) 
   {
      if(question == null || question.getSectionNumber() == null)
         return null;
            
       int sectionId = question.getSectionNumber().intValue();
       int questionId = question.getPrimaryKey();
       int parentId = 0;
       QuestionAttribute parentQuestionAttr = question.findAttributeByName("parentId");
       if (parentQuestionAttr != null) {
           String val = (String) parentQuestionAttr.getValue();
           if (val != null && !val.equals(""))
               parentId = Integer.parseInt(val);
       }
       return getCounter(sectionId, questionId, parentId);
   }
   
   protected AutoCounter getSectionCounter(int sectionId) {
       AutoCounter counter = (AutoCounter) pageContext.getAttribute("sectionCounter");
       if (counter == null || counter.getSectionId() != sectionId) {
           counter = new AutoCounter(sectionId);
           pageContext.setAttribute("sectionCounter", counter);
       }
       return counter;
   }//--------------------------------------------
   
   private AutoCounter getCounter(int sectionId, int questionId, int parentId) {
       AutoCounter counter = (AutoCounter) pageContext.getAttribute("counter");
       if (counter == null || counter.getSectionId() != sectionId) {
           //counter = new AutoCounter(sectionId);
           counter = getSectionCounter(sectionId);
           counter.setFormComponentId(questionId);
       }
       else if (parentId > 0 && counter.getFormComponentId() == parentId) {
           AutoCounter childCounter = new AutoCounter(sectionId);
           childCounter.setFormComponentId(questionId);
           childCounter.setParent(counter);
           //counter.setChild(childCounter);
           counter = childCounter;
       }
       else {
           AutoCounter current = counter;
           while (current.getParent() != null && current.getParent().getFormComponentId() != parentId)
               current = current.getParent();
           counter = current;
       }
       pageContext.setAttribute("counter", counter);
       return counter;
   }//--------------------------------------------
   
   protected String getNumberLabel(FormQuestion question) {
       AutoCounter counter = updateCounter(question);
       if(counter == null)
          return "";
       
       return counter.toString() + ".";
   }//--------------------------------------------
   protected String getNumberLabel(FormAnswer tableColAnswer) {
       int sectionId = tableColAnswer.getFormQuestion().getSectionNumber().intValue();
       int questionId = tableColAnswer.getColumnNumber().intValue();
       int parentId = tableColAnswer.getFormQuestion().getPrimaryKey();

       AutoCounter counter = getCounter(sectionId, questionId, parentId);
       /*
       AutoCounter childCounter = counter.getChild();
       if (childCounter == null) {
           childCounter = new AutoCounter(counter.getSectionId());
	       childCounter.setFormComponentId(tableColAnswer.getColumnNumber().intValue());
	       childCounter.setParent(counter);
	       counter.setChild(childCounter);
	       counter = childCounter;
       }
       */

       counter.increment();
       return counter.toString() + ".";
   }//--------------------------------------------
   /**
    * @return HTMLDecorator.decorateQuestionName(findQuestionByID(new Integer(this.getId()),false)
    * @throws NoDataFoundException
    */
   protected String retrieveQuestionInputName() throws NoDataFoundException
   {
      FormQuestion question = this.getForm().findQuestionByID(new Integer(this.getId()));
      
      String inputName = HTMLDecorator.decorateQuestionName(question,null,false);
      return inputName;
   }//--------------------------------------------
   
   private String validateOnSave;
   private String validateOnSubmit;
   private String questionWidth  = "40%";
   private String answerWidth = "60%";
   private String onblur = "";
   private String onfocus = "";
   private String onclick = "";
   private String onchange = "";
   private String name = "";
   private String autoNumber = "";
   private String numberFormat = "";
   protected Log logger = Debugger.getLog(getClass());
   private String textStyleClass = HTMLFormQuestionDecorator.DEFAULT_QUESTION_TEXT_STYLE_CLASS;
   private String styleClass = HTMLFormQuestionDecorator.DEFAULT_QUESTION_STYLE_CLASS;
   private String answerStyleClass = HTMLFormQuestionDecorator.DEFAULT_ANSWER_STYLE_CLASS;
      
}
