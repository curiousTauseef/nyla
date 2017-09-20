package nyla.solutions.formInjection.formatter.html;

import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nyla.solutions.formInjection.FormGuide;
import nyla.solutions.formInjection.data.FormAnswer;
import nyla.solutions.formInjection.data.FormColumn;
import nyla.solutions.formInjection.data.FormQuestion;
import nyla.solutions.formInjection.data.FormRow;
import nyla.solutions.formInjection.data.FormTable;
import nyla.solutions.formInjection.data.QuestionChoice;
import nyla.solutions.formInjection.data.ResponseType;
import nyla.solutions.formInjection.formatter.FormDecorator;
import nyla.solutions.global.data.Copier;
import nyla.solutions.global.data.Textable;
import nyla.solutions.global.exception.SetupException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Text;
import nyla.solutions.global.web.View;
import nyla.solutions.global.web.validation.Validation;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * HTMLDecorator super class for HTML form component rendering
 * 
 * @author Gregory Green
 */
public abstract class HTMLDecorator extends FormDecorator implements Copier
{
   public static final String OFFLINE_INPUT_PREFIX = FormGuide.OFFLINE_INPUT_PREFIX;

   public static final String OFFLINE_INPUT_SUFFIX = FormGuide.OFFLINE_INPUT_SUFFIX;

   // protected static final String ANSWER_INPUT_ID_PREFIX = "_answer";
   protected static final String ANSWER_INPUT_ID_PREFIX = "";

   /**
    * DEFAULT_ANSWER_STYLE_CLASS = "answer"
    */
   public final static String DEFAULT_ANSWER_STYLE_CLASS = "answer";

   /**
    * DEFAULT_ANSWER_STYLE_CLASS = "questionText"
    */
   public static final String DEFAULT_QUESTION_TEXT_STYLE_CLASS = "questionText";


   private static Map stringTemplates = new HashMap();

   private static final Configuration cfg;
   static
   {

      cfg = new Configuration();
      // cfg.setClassForTemplateLoading(HTMLDecorator.class,
      // HTMLDecorator.HTML_TEMPLATE_CP_PATH);
      cfg.setObjectWrapper(new DefaultObjectWrapper());
   }

   /**
    * SPACE = "&nbsp;"
    */
   public static final String SPACE = "&nbsp;";

   /**
    * Print formatted output to writer
    * 
    * @param view the view information
    * @param out the output writer
    * @throws Exception
    */
   public static void print(FormComponentView view, Writer out, String contextPath) throws Exception
   {
      String templateName = view.getName();
      /*
       * if (view.isReadOnly()) templateName += ".readOnly";
       */
      templateName += ".fhtml";

      Map m = new HashMap();
      m.put("question", view);
      m.put("contextPath", contextPath);
      
      // long start = System.currentTimeMillis();
      // template.process(view, out);

      //contextPath
      
      Text.formatWriterFromTemplateName(templateName, view, out);
      // long end = System.currentTimeMillis();
      // Debugger.getCategory(HTMLDecorator.class).debug("time to process
      // template: " + (end-start));
   }// --------------------------------------------

   /**
    * Print template information to output writer
    * @param view the view information
    * @param template the template text
    * @param out the output writer
    * @throws Exception
    */
   public static void print(FormComponentView view, String template, Writer out) throws Exception
   {
      StringReader sr = new StringReader(template);
      Debugger.println("settings=" + cfg.getSettings());
      Template t = new Template(view.getName(), sr, cfg);
      long start = System.currentTimeMillis();
      t.process(view, out);
      long end = System.currentTimeMillis();
      Debugger.getLog(HTMLDecorator.class).debug(
      "time to process template: " + (end - start));
   }// --------------------------------------------

   /**
    * 
    * @param aTemplateName
    *            the template name relative to HTML_TEMPLATE_PATH
    * @return template text content
    */
   /*
    * protected static Template getTemplate(String aTemplateName) throws
    * IOException { if (aTemplateName == null) throw new
    * IllegalArgumentException( "aTemplateName required in
    * HTMLDecorator.getTemplate");
    * 
    * return IO.readFile(HTML_TEMPLATE_PATH+"/"+aTemplateName);
    * 
    * /*try { return cfg.getTemplate(aTemplateName); } catch (Throwable e) {
    * throw new
    * SystemException(Debugger.stackTrace(e)+"HTML_TEMPLATE_CP_PATH="+HTML_TEMPLATE_CP_PATH); }
    *  }
    */
   // --------------------------------------------
   /**
    * 
    * @param aTemplateName
    *            the template name
    * @return template text content
    */
   public String getTemplateAsString(String aTemplateName)

   {
      if (aTemplateName == null)
         throw new IllegalArgumentException(
         "aTemplateName required in HTMLDecorator.getTemplate");

      if (this.readOnly)
         aTemplateName = aTemplateName.concat(".readonly");

      aTemplateName = aTemplateName.concat(".fhtml");

      String template = (String) stringTemplates.get(aTemplateName);
      try
      {
         if (template == null)
         {
            synchronized (stringTemplates)
            {
               try
               {
                  template = Text.loadTemplate(aTemplateName, getLocale());
               }
               catch (IOException e)
               {
                  throw new SetupException(e);
               }
            }
         }
         return template;
      }
      catch (Exception e)
      {
         throw new SystemException(Debugger.stackTrace(e));
      }
   }// --------------------------------------------

   /**
    * 
    * @return text wrapped with fix HTML text
    * @see nyla.solutions.formInjection.data.FormComponent#getText()
    */
   public String getText()
   {
      return fix(super.getText());
   }// ---------------------------------------------

   /**
    * @param aComponent
    */
   public HTMLDecorator(Textable aComponent)
   {
      super(aComponent);
   }// --------------------------------------------

   /**
    * @return
    */
   public boolean isEditable()
   {
      return !this.readOnly;
   }// --------------------------------------------

   /**
    * @param isEditable
    */
   public void setEditable(boolean isEditable)
   {
      this.readOnly = !isEditable;
   }// --------------------------------------------

   public String getContextPath()
   {
         return this.view.getContextPath();
   }// --------------------------------------------

   /**
    * @return view
    */
   public View getView()
   {
      return view;
   }// --------------------------------------------

   /**
    * @param view
    */
   public void setView(View view)
   {
      this.view = view;
   }// --------------------------------------------

   /**
    * *
    * <tr>
    * <td>${text}</td>
    * <td>${answerTex</td>
    * </tr>
    * 
    * @param aLeftText
    * @param aRightText
    * @return
    */
   protected String toTableRow(String aLeftText, String aRightText,
                               String aAppendRight)
   {
      StringBuffer tr = new StringBuffer("<tr class=\"").append(styleClass)
      .append("\">").append("<td width=\"").append(this.getQuestionWidth())
      .append("\">").append(aLeftText).append("</td>").append("<td width=\"")
      .append(this.getAnswerWidth()).append("\">").append(aRightText).append(
      aAppendRight).append("</td>").append("</tr>").append(NEWLINE);
      return tr.toString();
      /*
       * Map map = new HashMap();
       * map.put("questionWidth",this.getQuestionWidth());
       * map.put("answerWidth",this.getAnswerWidth());
       * 
       * return format(map,tr.toString());
       */
   }// --------------------------------------------

   /**
    * Wrapper text with a tr with one cell. The single cell will have a colspan
    * of 2
    * 
    * @param aText
    *            the table data text
    */
   protected String toTableRow(String aText)
   {
      StringBuffer tr = new StringBuffer("<tr class=\"")
      .append(this.styleClass).append("\" width=\"100%\">").append(
      "<td colspan=\"2\" width=\"100%\">").append(aText).append("</td>")
      .append("</tr>").append(NEWLINE);

      return tr.toString();
   }// --------------------------------------------------

   /**
    * <table border="0" width="100%" id="q1" bgcolor="#CCCCCC"
    * bordercolor="#CCCCCC" style="">
    * <tr>
    * <td>${text}</td>
    * <td>${answerTex</td>
    * </tr>
    * </table>
    * 
    * @return
    */
   protected String toHTMLTable(String aLeftText, String aRightText,
                                String aAppendRightText)
   {
      StringBuffer htmlTable = new StringBuffer("<table border=\"0\" class=\"")
      .append(this.styleClass).append("\" width=\"100%\">").append(
      toTableRow(aLeftText, aRightText, aAppendRightText)).append("</table>")
      .append(NEWLINE);

      return htmlTable.toString();
   }// --------------------------------------------

   protected String toHTMLTable(String aText)
   {
      StringBuffer htmlTable = new StringBuffer("<table border=\"0\" class=\"")
      .append(this.styleClass).append("\" width=\"100%\">").append("<tr>")
      .append("<td width=\"100%\">").append(aText).append("</td>").append(
      "</tr>").append("</table>").append("\n");

      return htmlTable.toString();
   }// --------------------------------------------

   /**
    * decorate Question Name
    * 
    * @param aQuestion
    *            the question
    * @param aColumn
    *            the column with response type
    * @param isOffline
    *            prefix with %INPUT: if true
    * @return aQuestion.getPrimaryKey() prefixed with the character "q"
    */
   public static String decorateQuestionName(FormQuestion aQuestion,
                                             FormColumn aColumn,
                                             boolean isOffline)
   {
      Validation validator = new Validation();

      // validator.setFieldName("q"+aQuestion.getPrimaryKey());

      StringBuffer questionName = new StringBuffer("q"
      + aQuestion.getPrimaryKey());
      validator.setFieldName(questionName.toString());

      if (aColumn == null)
      {
         validator.setMaxLength(FormGuide.retrieveMaxLength(aQuestion));
         validator.setMinLength(FormGuide.retrieveMinLength(aQuestion));
         validator.setFormat(FormGuide.retrieveFormat(aQuestion));
         validator.setMessage(FormGuide
         .retrieveValidationErrorMessage(aQuestion));
         validator.setRequiredCode(String.valueOf(FormGuide
         .retrieveIsRequired(aQuestion)));
         validator.setTypeName(FormGuide.retrieveTypeName(aQuestion));
      }
      else
      {
         // Decorate for answer with a given column
         ResponseType columnResponseType = aColumn.getResponseType();
         // Set based on column
         validator
         .setMaxLength(FormGuide.retrieveMaxLength(columnResponseType));
         validator
         .setMinLength(FormGuide.retrieveMinLength(columnResponseType));
         validator.setFormat(FormGuide.retrieveFormat(columnResponseType));
         validator.setMessage(FormGuide
         .retrieveValidationErrorMessage(columnResponseType));
         validator.setRequiredCode(String
         .valueOf(FormGuide.isRequired(aColumn)));
         validator.setTypeName(FormGuide.retrieveTypeName(columnResponseType));
      }

      String inputName = validator.getInputName();

      if (isOffline)
      {
         // prefix OFFLINE input
         inputName = OFFLINE_INPUT_PREFIX + inputName + OFFLINE_INPUT_SUFFIX;
      }
      return inputName;
   }// --------------------------------------------

   /**
    * @return Returns the readOnly.
    */
   public boolean isReadOnly()
   {
      return readOnly;
   }// --------------------------------------------

   /**
    * @param readOnly
    *            The readOnly to set.
    */
   public void setReadOnly(boolean readOnly)
   {
      this.readOnly = readOnly;
   }// --------------------------------------------

   /**
    * 
    * @param aColumn
    *            the column
    * @return width percent
    */
   protected String decorateWidth(FormColumn aColumn)
   {
      if (aColumn.getSize() < 1)
         return "100%";
      else
         return aColumn.getSize() + "%";

   }// --------------------------------------------

   /**
    * Decorate question choice
    * 
    * @param aFormQuestion
    * @param aText
    */
   protected void decorateQuestionChoice(FormQuestion aFormQuestion,
                                         StringBuffer aText)
   {
      // Added Question Choices
      List choices = new ArrayList(aFormQuestion.getChoices());
      // Collections.sort(choices);

      if (aFormQuestion.isReadOnly() || this.isReadOnly())
      {
         decorateReadOnlyChoices(aFormQuestion, choices, aText);
      }
      else
      {
         decorateEditableChoices(aFormQuestion, choices, aText);
      }
      // end if
      // decorateErrorPlaceHolder
      decorateErrorPlaceHolder(aText, decorateQuestionName(aFormQuestion, null,
      this.isOffline()));
   } // -----------------------------------------------------------

   private void decorateReadOnlyChoices(FormQuestion aQuestion, List aChoices,
                                        StringBuffer aText)
   {
      QuestionChoice formQuestionChoice = null;

      // aText.append("<br/>");

      for (Iterator i = aChoices.iterator(); i.hasNext();)
      {
         formQuestionChoice = (QuestionChoice) i.next();

         aText.append(getHTMLFormQuestionChoiceDecorator(aQuestion.getAnswer(),
         formQuestionChoice).getText());
      }// end for
   }// --------------------------------------------

   /**
    * Decorate Editable choices
    * 
    * @param aQuestion
    * @param aChoices
    * @param aText
    */
   private void decorateEditableChoices(FormQuestion aQuestion, List aChoices,
                                        StringBuffer aText)
   {
      // prefix select HTML if needed
      if (aQuestion.getResponseType().getType() == ResponseType.SELECT_LIST_TYPE)
      {
         aText.append("<select size=\"1\" ").append(" name=\"").append(
         decorateQuestionName(aQuestion, null, isOffline())).append("\" ")
         .append(" id=\"").append(
         decorateQuestionName(aQuestion, null, isOffline())).append(
         ANSWER_INPUT_ID_PREFIX).append("\" ");

         decorateJS(aText);
         // end select
         aText.append(">");

         // aText.append(HTMLFormQuestionChoiceDecorator.decoratorBlankOption());
      }

      // decorate choices
      QuestionChoice formQuestionChoice = null;
      if (aChoices != null)
      {
         for (Iterator i = aChoices.iterator(); i.hasNext();)
         {
            formQuestionChoice = (QuestionChoice) i.next();

            aText.append(getHTMLFormQuestionChoiceDecorator(
            aQuestion.getAnswer(), formQuestionChoice).getText());
         }// end for

         // post fix select HTML tag if needed
         if (aQuestion.getResponseType().getType() == ResponseType.SELECT_LIST_TYPE)
         {
            // end select option
            aText.append("</select>");
         }

         // aText.append(aText);
      }
   }// --------------------------------------------

   /**
    * @param aFormQuestionChoice
    * @return
    */
   protected HTMLFormQuestionChoiceDecorator getHTMLFormQuestionChoiceDecorator(
                                                                                FormAnswer answer,
                                                                                QuestionChoice aFormQuestionChoice)
   {
      HTMLFormQuestionChoiceDecorator decorator = new HTMLFormQuestionChoiceDecorator(
      answer, aFormQuestionChoice);
      decorator.copy(this);
      return decorator;
   }// --------------------------------------------

   protected void decorateQuestionChoices(FormAnswer aAnswer, StringBuffer aText)
   {
      if (aAnswer.isReadOnly() || this.isReadOnly())
      {
         decorateReadOnlyQuestionChoices(aAnswer, aText);
      }
      else
      {
         decorateEditableQuestionChoices(aAnswer, aText);
      }
      // end if
   } // -----------------------------------------------------------

   //
   /**
    * Build and editable version of the question choices
    * 
    * @param aAnswer
    * @param aText
    */
   protected void decorateReadOnlyQuestionChoices(FormAnswer aAnswer,
                                                  StringBuffer aText)
   {
      StringBuffer choicesText = new StringBuffer();

      // decorate choices
      QuestionChoice questionChoice = null;
      Collection questionChoices = null;

      ResponseType responseType = aAnswer.getResponseType();

      if (responseType.isListable())
      {
         questionChoices = aAnswer.retrievePickedChoices();
      }
      else
      {
         questionChoices = FormGuide.retrieveQuestionChoices(aAnswer);
      }

      if (questionChoices == null)
      {
         return;
      }

      // loop thru question choices
      for (Iterator i = questionChoices.iterator(); i.hasNext();)
      {
         questionChoice = (QuestionChoice) i.next();

         choicesText.append(getHTMLFormQuestionChoiceDecorator(aAnswer,
         questionChoice).getText());

         if (responseType.isMultipleSelect() && i.hasNext())
         {
            choicesText.append(FormGuide.CHOICE_ID_SEPARATORS);
         }
      }// end for

      aText.append("<br/>").append(choicesText);
   }// --------------------------------------------

   /**
    * uild and editable version of the question choices
    * 
    * @param aAnswer
    * @param aText
    */
   private void decorateEditableQuestionChoices(FormAnswer aAnswer,
                                                StringBuffer aText)
   {
      StringBuffer choicesText = new StringBuffer();

      // prefix select HTML if needed
      if (aAnswer.getResponseType().getType() == ResponseType.SELECT_LIST_TYPE)
      {
         choicesText.append("<select size=\"1\" class=\"").append(
         getAnswerStyle()).append("\" ").append(" name=\"").append(
         decorateInputName(aAnswer)).append("\" ").append(" id=\"").append(
         decorateInputName(aAnswer) + ANSWER_INPUT_ID_PREFIX).append("\" ");

         decorateJS(choicesText);

         choicesText.append(">");

         // choicesText.append(HTMLFormQuestionChoiceDecorator.decoratorBlankOption());
      }

      // decorate choices
      Collection questionChoices = FormGuide.retrieveQuestionChoices(aAnswer);

      QuestionChoice questionChoice = null;
      if (questionChoices != null)
      {

         // questionChoices =
         // Organizer.sortByJavaBeanProperty("number",questionChoices);

         // loop thru question choices
         for (Iterator i = questionChoices.iterator(); i.hasNext();)
         {
            questionChoice = (QuestionChoice) i.next();

            choicesText.append(new HTMLFormQuestionChoiceDecorator(aAnswer,
            questionChoice).getText());
         }// end for

         // post fix select HTML tag if needed
         if (aAnswer.getResponseType().getType() == ResponseType.SELECT_LIST_TYPE)
         {
            // end select option
            choicesText.append("</select>");
         }

         aText.append(choicesText);
      }
   }// --------------------------------------------

   /**
    * @param aColumn
    *            the column
    * @return the HTML Column Decorator
    */
   protected HTMLColumnDecorator getHTMLColumnDecorator(FormColumn aColumn)
   {
      HTMLColumnDecorator decorator = new HTMLColumnDecorator(aColumn);
      decorator.copy(this);
      return decorator;
   }// --------------------------------------------

   /**
    * Used i.e. %INPUT:firstName when offline=true
    * 
    * @param aAnswer
    *            the answer
    * @return form input name for an answer
    */
   public static String decorateInputName(FormAnswer aAnswer)
   {
      if (aAnswer == null)
         throw new IllegalArgumentException(
         "aAnswer required in HTMLDecorator.decorateInputName");

      Validation validator = new Validation();

      // validator.setFieldName("q"+aQuestion.getPrimaryKey());
      FormQuestion question = aAnswer.getFormQuestion();

      StringBuffer questionName = new StringBuffer("q"
      + question.getPrimaryKey());

      validator.setFieldName(questionName.toString());
      validator.setMaxLength(FormGuide.retrieveMaxLength(aAnswer));
      validator.setMinLength(FormGuide.retrieveMinLength(aAnswer));
      validator.setFormat(FormGuide.retrieveFormat(aAnswer));
      validator.setMessage(FormGuide.retrieveValidationErrorMessage(aAnswer));
      validator.setRequiredCode(String.valueOf(FormGuide.isRequired(aAnswer)));
      validator.setTypeName(FormGuide.retrieveTypeName(aAnswer));

      String input = validator.getInputName();

      StringBuffer inputName = new StringBuffer(input);

      if (aAnswer.isWithinTable())
      {
         // logger.debug("ANSWER isWithinTable");

         FormColumn column = aAnswer.getFormColumn();

         // append column primary key and
         inputName.append("[").append(column.getNumber()).append("]").append(
         "[").append(aAnswer.getRowNumber()).append("]").append(";tablePK=")
         .append(aAnswer.getTablePK()).append(";");
      }
      /*
       * if(this.isOffline()) { //return offline tags return
       * OFFLINE_INPUT_PREFIX+inputName+OFFLINE_INPUT_SUFFIX; } else {
       */
      return inputName.toString();
      // }
   }// --------------------------------------------

   /**
    * @return Returns the styleClass.
    */
   public final String getStyleClass()
   {
      return styleClass;
   }// --------------------------------------------

   /**
    * @param styleClass
    *            The styleClass to set.
    */
   public final void setStyleClass(String styleClass)
   {
      if (styleClass == null)
         styleClass = "";

      this.styleClass = styleClass;
   }// --------------------------------------------

   /**
    * 
    * @return CONTEXT_PATH+"/skins/default/images"
    */
   public final String getSkinImagePath()
   {
         return view.getSkinPath() + "/images";
   }// --------------------------------------------

   /**
    * @return Returns the onBlurJS.
    */
   public final String getOnBlurJS()
   {
      return onBlurJS;
   }// --------------------------------------------

   /**
    * @param onBlurJS
    *            The onBlurJS to set.
    */
   public final void setOnBlurJS(String onBlurJS)
   {
      if (onBlurJS == null)
         onBlurJS = "";

      this.onBlurJS = onBlurJS;
   }// --------------------------------------------

   /**
    * @return Returns the onFocusJS.
    */
   public final String getOnFocusJS()
   {
      return onFocusJS;
   }// --------------------------------------------

   /**
    * @param onFocusJS
    *            The onFocusJS to set.
    */
   public final void setOnFocusJS(String onFocusJS)
   {
      if (onFocusJS == null)
         onFocusJS = "";

      this.onFocusJS = onFocusJS;
   }// --------------------------------------------

   /**
    * @return Returns the onChangeJS.
    */
   public final String getOnChangeJS()
   {
      return onChangeJS;
   }// --------------------------------------------

   /**
    * @param onChangeJS
    *            The onChangeJS to set.
    */
   public final void setOnChangeJS(String onChangeJS)
   {
      if (onChangeJS == null)
         onChangeJS = "";

      this.onChangeJS = onChangeJS;
   }// --------------------------------------------

   /**
    * @return Returns the onClickJS.
    */
   public final String getOnClickJS()
   {
      return onClickJS;
   }// --------------------------------------------

   /**
    * @param onClickJS
    *            The onClickJS to set.
    */
   public final void setOnClickJS(String onClickJS)
   {
      if (onClickJS == null)
         onClickJS = "";

      this.onClickJS = onClickJS;
   }// --------------------------------------------

   /**
    * Decorate Javascript
    * 
    * @param aText
    */
   protected void decorateJS(StringBuffer aText)
   {
      if (!Text.isNull(onChangeJS))
         aText.append(" onchange=\"").append(onChangeJS).append("\" ");

      if (!Text.isNull(onClickJS))
         aText.append(" onclick=\"").append(onClickJS).append("\" ");

      if (!Text.isNull(onBlurJS))
         aText.append(" onblur=\"").append(onBlurJS).append("\" ");

      if (!Text.isNull(onFocusJS))
         aText.append(" onfocus=\"").append(onFocusJS).append("\" ");

   }// --------------------------------------------


   public void copy(Copier aFrom)
   {
      HTMLDecorator from = (HTMLDecorator) aFrom;
      this.onBlurJS = from.onBlurJS;
      this.onChangeJS = from.onChangeJS;
      this.onClickJS = from.onClickJS;
      this.onFocusJS = from.onFocusJS;
      this.readOnly = from.readOnly;
      this.offline = from.offline;
      this.questionWidth = from.questionWidth;
      this.answerWidth = from.answerWidth;
      this.questionTextStyleClass = from.questionTextStyleClass;
      this.answerStyle = from.answerStyle;
      this.styleClass = from.styleClass;
   }// --------------------------------------------

   /**
    * Gets HTML Answer decorator
    * 
    * @param aAnswer
    * @return
    */
   protected HTMLAnswerDecorator getHTMLAnswerDecorator(FormAnswer aAnswer)
   {
      HTMLAnswerDecorator decorator = new HTMLAnswerDecorator(aAnswer);
      decorator.copy(this);
      return decorator;
   }// --------------------------------------------

   protected HTMLFormQuestionDecorator getHTMLFormQuestionDecorator(
                                                                    FormQuestion aFormQuestion)
   {
      HTMLFormQuestionDecorator decorator = new HTMLFormQuestionDecorator(
      aFormQuestion);
      decorator.copy(this);
      return decorator;
   }// --------------------------------------------

   /**
    * @param aFormRow
    *            the form row to decorator
    * @return the HTMLFormRowDecorator with copied property from this object
    */
   protected HTMLFormRowDecorator getHTMLFormRowDecorator(FormRow aFormRow)
   {
      HTMLFormRowDecorator decorator = new HTMLFormRowDecorator(aFormRow);
      decorator.copy(this);
      return decorator;
   }// --------------------------------------------

   /**
    * @param aFormRow
    *            the form row to decorator
    * @return the HTMLFormRowDecorator with copied property from this object
    */
   protected HTMLFormRowDecorator getHTMLFormRowDecorator(Collection rows)
   {
      HTMLFormRowDecorator decorator = new HTMLFormRowDecorator(rows);
      decorator.copy(this);
      return decorator;
   }// --------------------------------------------

   /**
    * 
    * @param aFormTable
    *            the form table
    * @return HTMLFormTableDecorator with copied attribute
    */
   protected HTMLFormTableDecorator getHTMLFormTableDecorator(
                                                              FormTable aFormTable)
   {
      HTMLFormTableDecorator decorator = new HTMLFormTableDecorator(aFormTable);
      decorator.copy(this);
      return decorator;
   }// --------------------------------------------

   /**
    * @return Returns the answerStyle.
    */
   public String getAnswerStyle()
   {
      return answerStyle;
   }// --------------------------------------------

   /**
    * @param answerStyle
    *            The answerStyle to set.
    */
   public void setAnswerStyle(String answerStyle)
   {
      if (answerStyle == null)
         answerStyle = "";

      this.answerStyle = answerStyle;
   }// --------------------------------------------

   /**
    * @return Returns the offline.
    */
   public boolean isOffline()
   {
      return offline;
   }// --------------------------------------------

   /**
    * @param offline
    *            The offline to set.
    */
   public void setOffline(boolean offline)
   {
      this.offline = offline;
   }// --------------------------------------------

   /**
    * @return Returns the answerWidth.
    */
   public String getAnswerWidth()
   {
      return answerWidth;
   }

   /**
    * @param answerWidth
    *            The answerWidth to set.
    */
   public void setAnswerWidth(String answerWidth)
   {
      if (answerWidth == null)
         answerWidth = "";

      this.answerWidth = answerWidth;
   }

   /**
    * @return Returns the questionWidth.
    */
   public String getQuestionWidth()
   {
      return questionWidth;
   }

   /**
    * @param questionWidth
    *            The questionWidth to set.
    */
   public void setQuestionWidth(String questionWidth)
   {
      if (questionWidth == null)
         questionWidth = "";

      this.questionWidth = questionWidth;
   }

   /**
    * @return Returns the questionTextStyleClass.
    */
   public final String getQuestionTextStyleClass()
   {
      return questionTextStyleClass;
   }// --------------------------------------------

   /**
    * @param questionTextStyleClass
    *            The questionTextStyleClass to set.
    */
   public final void setQuestionTextStyleClass(String questionTextStyleClass)
   {
      if (questionTextStyleClass == null)
         questionTextStyleClass = "";

      this.questionTextStyleClass = questionTextStyleClass;
   }// --------------------------------------------

   /**
    * @param aText
    * @param aInputName
    */
   protected void decorateErrorPlaceHolder(StringBuffer aText, String aInputName)
   {
      // Add place holder for error messages
      aText.append("<span id=\"").append(aInputName).append(
      "Error\" class=\"error\"></span> ");
   }// --------------------------------------------

   
   private String questionWidth = "40%";

   private String answerWidth = "60%";

   private boolean offline = false;

   private String answerStyle = DEFAULT_ANSWER_STYLE_CLASS;

   private String questionTextStyleClass = DEFAULT_QUESTION_TEXT_STYLE_CLASS;

   private String onChangeJS = "";

   private String onClickJS = "";

   private String onBlurJS = "";

   private String onFocusJS = "";

   private String styleClass = "";

   private boolean readOnly = false;

   private View view = null;
      
}
