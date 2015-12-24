package org.solutions.form.web;

import java.io.BufferedWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

import javax.servlet.jsp.JspException;

import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Text;

import org.apache.log4j.Category;
import org.solutions.form.data.FormQuestion;
import org.solutions.form.data.FormSection;
import org.solutions.form.formatter.html.FormQuestionView;
import org.solutions.form.formatter.html.FormQuestionViewExtra;
import org.solutions.form.formatter.html.HTMLDecorator;


/**
 * <pre>
 * 
 *  QuestionaireTag form question JSP tag
 *  
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public class QuestionaireTag extends FormTag
{
    private List questions = new LinkedList();
    private FormQuestion question;
    private FormQuestionView view;
    private int currentIndex;
    
    private long start;
    private String altStyleClass;
    private String altTextStyleClass;
    private String altAnswerStyleClass;
    private String maxLength;
    
   /**
    * Constructor for QuestionTag initializes internal data settings.
    *  
    */
   public QuestionaireTag()
   {
      super();
   }//--------------------------------------------

   /**
    * 
    *
    * @see javax.servlet.jsp.tagext.BodyTagSupport#doStartTag()
    */
   public int doStartTag() throws JspException
   {
       start = System.currentTimeMillis();
       try
       {
          FormQuestion formQuestion = null;
          String commaSeparatedIds = this.getId();
          String questionID = null;
          
          Debugger.println(this,"commaSeparatedIds="+commaSeparatedIds);
          
          if (!Text.isNull(commaSeparatedIds))
          {
             for (StringTokenizer qIdTok = new StringTokenizer(
             commaSeparatedIds, ","); qIdTok.hasMoreTokens();)
             {
                questionID = qIdTok.nextToken();
                formQuestion = getForm().findQuestionByID(new Integer(questionID));
                questions.add(formQuestion);
             }
             
             Debugger.println(this,"questions="+questions);
          }
          else {
              // check if we're in a sectionContainer tag
              // if so, use all questions in that section
              SectionContainerTag tag = (SectionContainerTag) findAncestorWithClass(this, SectionContainerTag.class);
              if (tag == null) 
              {
                  printFormQuestions(new TreeSet(this.getForm().getFormQuestions()));
              }
              else
              {
                 FormSection section = tag.getSection();
                 questions = new LinkedList(section.getFormQuestions());   
              }
                            
              //Organizer.sortByJavaBeanProperty("number", questions);
              //Collections.reverse(questions);
          }
          
          if (hasMoreQuestions()) {
              FormQuestion q = getNextQuestion();
              //view = new FormQuestionView(q);
              if (getName() != null && !getName().equals(""))
                  pageContext.setAttribute(getName(), q);
              return EVAL_BODY_BUFFERED;
          }
          return SKIP_BODY;
       }
       catch (Exception e)
       {
           reset();
          logger.error(e);
          throw new JspException(Debugger.stackTrace(e));
       }
   }//--------------------------------------------
   protected void printFormQuestions(Collection formQuestions)
   throws Exception
   {
      FormQuestion formQuestion = null;
      
      for (Iterator i = formQuestions.iterator(); i.hasNext(); ) {
         formQuestion = (FormQuestion) i.next();
         print(formQuestion);
      }
   }//--------------------------------------------

   /**
    * 
    * 
    * @see javax.servlet.jsp.tagext.Tag#doStartTag()
    */
   public int doAfterBody() throws JspException
   {
      try
      {
         FormQuestion formQuestion = getCurrentQuestion();
         Debugger.println(this,"formQuestion.current="+formQuestion);
         
         if (formQuestion != null) {
             
	         AutoCounter sectionCounter = this.getSectionCounter(formQuestion.getSectionNumber().intValue());
	         String numberLabel = "";
	         if (!"skip".equalsIgnoreCase(getAutoNumber()))
	             numberLabel = getNumberLabel(question);
	         
             
	         String styleClass = getStyleClass();
	         String textStyleClass = getTextStyleClass();
	         String answerStyleClass = getAnswerStyleClass();
	         
	         if (sectionCounter.getNumber() % 2 == 0) {
		         styleClass = getAltStyleClass();
		         textStyleClass = getAltTextStyleClass();
		         answerStyleClass = getAltAnswerStyleClass();
	         }	         

	         String tmpl = bodyContent.getString();
             Writer out = new BufferedWriter(bodyContent.getEnclosingWriter());
     
	         if (tmpl != null && !tmpl.trim().equals("")) {
	             bodyContent.clear();
	             
		         // for backwards compatibility
	             FormQuestionViewExtra view = new FormQuestionViewExtra(formQuestion);
		         view.setStyleClass(styleClass);
		         view.setTextStyleClass(textStyleClass);
		         view.setAnswerStyleClass(answerStyleClass);
		         view.setQuestionWidth(getQuestionWidth());
		         view.setAnswerWidth(getAnswerWidth());
		         view.setMaxLength(getMaxLength());
		         view.setOnBlurJS(getOnblur());
		         view.setOnChangeJS(getOnchange());
		         view.setOnFocusJS(getOnfocus());
		         view.setOnClickJS(getOnclick());
		         //view.setValidateOnSave(new Boolean(getValidateOnSave()).booleanValue());
		         //view.setValidateOnSubmit(new Boolean(getValidateOnSubmit()).booleanValue());

		         view.setName("answer");
		         StringWriter sw = new StringWriter();
		         HTMLDecorator.print(view, sw, this.getContextPath());
		         view.setAnswerText(sw.toString());
		         view.setName("question");
		         
		         view.setNumberLabel(numberLabel);
	             HTMLDecorator.print(view, tmpl, out);
	         }
	         else {
	             view = new FormQuestionView(formQuestion);
	             //System.out.println("using default template: questionId=" + formQuestion.getQuestionId());
		         view.setStyleClass(styleClass);
		         view.setTextStyleClass(textStyleClass);
		         view.setAnswerStyleClass(answerStyleClass);
		         view.setAnswerWidth(getAnswerWidth());
		         view.setWidth(getQuestionWidth());
		         view.setMaxLength(getMaxLength());
		         view.setOnBlurJS(getOnblur());
		         view.setOnChangeJS(getOnchange());
		         view.setOnFocusJS(getOnfocus());
		         view.setOnClickJS(getOnclick());
		         view.setCounter(sectionCounter);
		         //view.setValidateOnSave(new Boolean(getValidateOnSave()).booleanValue());
		         //view.setValidateOnSubmit(new Boolean(getValidateOnSubmit()).booleanValue());
		         if (Boolean.valueOf(getAutoNumber()).booleanValue()) {
		             view.setAutoNumber(true);
		             view.setNumberLabel(numberLabel);
		         }
		         //System.out.println("before printing");
	             HTMLDecorator.print(view, out,this.getContextPath());
	             //System.out.println("after printing");
	         }
	         //content.append(out.toString());
	         //System.out.println(out.toString());
	         if (hasMoreQuestions()) {
	             //System.out.println("has more questions");
	             FormQuestion q = getNextQuestion();
	             if (getName() != null && !getName().equals(""))
	                 pageContext.setAttribute(getName(), q);
	             return EVAL_BODY_AGAIN;
	         }
         }
         return SKIP_BODY;
      }
      catch (Exception e)
      {
          reset();
          e.printStackTrace();
         throw new JspException(e);
      }
   }//--------------------------------------------
   /**
    * 
    * @param formQuestion
    * @throws Exception
    */
   protected void print(FormQuestion formQuestion)
   throws Exception
   {
      FormQuestionView  view = new FormQuestionView(formQuestion);
                    //System.out.println("using default template: questionId=" + formQuestion.getQuestionId());
                    view.setStyleClass(this.getStyleClass());
                    view.setTextStyleClass(this.getTextStyleClass());
                    view.setAnswerStyleClass(this.getAnswerWidth());
                    view.setAnswerWidth(getAnswerWidth());
                    view.setWidth(getQuestionWidth());
                    view.setMaxLength(getMaxLength());
                    view.setOnBlurJS(getOnblur());
                    view.setOnChangeJS(getOnchange());
                    view.setOnFocusJS(getOnfocus());
                    view.setOnClickJS(getOnclick());
                    
                    view.setCounter(this.getSectionCounter(formQuestion.getSectionNumber().intValue()));
                    //view.setValidateOnSave(new Boolean(getValidateOnSave()).booleanValue());
                    //view.setValidateOnSubmit(new Boolean(getValidateOnSubmit()).booleanValue());
                    
                    String numberLabel = "";
                    if (!"skip".equalsIgnoreCase(getAutoNumber()))
                        numberLabel = getNumberLabel(question);
                    
                    if (Boolean.valueOf(getAutoNumber()).booleanValue()) {
                        view.setAutoNumber(true);
                        view.setNumberLabel(numberLabel);
                    }
                    //System.out.println("before printing");
                    HTMLDecorator.print(view, this.pageContext.getOut(),this.getContextPath());
   }//--------------------------------------------
   
   /**
    * 
    * 
    * @see javax.servlet.jsp.tagext.Tag#doStartTag()
    */
   public int doEndTag() throws JspException
   {
       try {
           //pageContext.getOut().println(bodyContent.toString());
           long end = System.currentTimeMillis();
           Category.getInstance("timer").debug("time (ms): " + (end-start));
           start = 0L;
           return EVAL_PAGE;
       }
       /*
       catch (IOException e) {
           throw new JspException(e);
       }
       */
       catch (Exception e) {
           logger.error(e);
           if (e instanceof JspException)
               throw (JspException) e;
           throw new JspException(e);
       }
       finally {
           reset();
       }
   }//--------------------------------------------
   
   protected FormQuestion getNextQuestion() {
       question = (FormQuestion) questions.get(currentIndex++);
       return question;
   }
   protected boolean hasMoreQuestions() {
       return currentIndex < questions.size();
   }
   public FormQuestion getCurrentQuestion() {
       return question;
   }
   /*
   public String getAutoNumber() {
       String autoNumber = super.getAutoNumber();
       return autoNumber == null || autoNumber.equals("") ? "true" : autoNumber;
   }
   */
    public String getAltAnswerStyleClass() {
        return altAnswerStyleClass != null && !altAnswerStyleClass.equals("") ? altAnswerStyleClass : getAnswerStyleClass();
    }
    public void setAltAnswerStyleClass(String altAnswerStyleClass) {
        this.altAnswerStyleClass = altAnswerStyleClass;
    }
    public String getAltStyleClass() {
        return altStyleClass != null && !altStyleClass.equals("") ? altStyleClass : getStyleClass();
    }
    public void setAltStyleClass(String altStyleClass) {
        this.altStyleClass = altStyleClass;
    }
    public String getAltTextStyleClass() {
        return altTextStyleClass != null && !altTextStyleClass.equals("") ? altTextStyleClass : getTextStyleClass();
    }
    public void setAltTextStyleClass(String altTextStyleClass) {
        this.altTextStyleClass = altTextStyleClass;
    }
    
    public String getMaxLength() {
        return maxLength;
    }
    public void setMaxLength(String maxLength) {
        this.maxLength = maxLength;
    }
   protected void reset() {
       questions = new ArrayList();
       question = null;
       currentIndex = 0;
       
       altStyleClass = null;
       altTextStyleClass = null;
       altAnswerStyleClass = null;
       setStyleClass(null);
       setTextStyleClass(null);
       setAnswerStyleClass(null);
       setAutoNumber(null);
       setMaxLength(null);
       view = null;
   }
   static final long serialVersionUID = QuestionaireTag.class.getName()
   .hashCode();
}
