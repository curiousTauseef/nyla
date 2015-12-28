package nyla.solutions.formInjection.formatter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import nyla.solutions.formInjection.data.Form;
import nyla.solutions.formInjection.data.FormAnswer;
import nyla.solutions.formInjection.data.FormComponent;
import nyla.solutions.formInjection.data.FormQuestion;
import nyla.solutions.formInjection.data.FormRow;
import nyla.solutions.formInjection.data.FormTable;
import nyla.solutions.formInjection.data.QuestionAttribute;
import nyla.solutions.formInjection.data.ResponseType;
import nyla.solutions.global.data.Copier;
import nyla.solutions.global.data.Data;
import nyla.solutions.global.data.Textable;
import nyla.solutions.global.exception.SetupException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.io.IO;
import nyla.solutions.global.operations.logging.Log;
import nyla.solutions.global.security.user.data.User;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Text;
import nyla.solutions.global.xml.XML;
import nyla.solutions.office.fop.FOP;

//SAX



/**
 * @author Gregory Green
 * @version 1.0
 * 
 * <b>FormDecorator </b> abstract decorator.
 * 
 * See Decorator Design pattern.
 *  
 */
public abstract class FormDecorator implements Textable, Copier
{

   public static final short PDF_TYPE = 1;

   public static final short WORD_TYPE = 2;

   public static final short HTML_TYPE = 3;

   public static final short XML_TYPE = 4;

   private static final short UNKNOWN = -1;

   public static final String BLANK = "______________________________________________________";

   public static final String NEWLINE = "\n";

   protected FormDecorator(short aType)
   {
      this.type = aType;
   }//--------------------------------------------

   /**
    * @param aComponent
    *           the component
    * @param aDecorator
    *           the copied decorator
    * @param aTemplateName
    *           the template name
    * @throws Exception
    */
   protected FormDecorator(Textable aComponent, String aTemplateName,
   FormDecorator aDecorator)
   {
      this(aComponent, aTemplateName, aDecorator.type, aDecorator.getViewer());

   }//------------------------------------------

   /**
    * @param aApplicationComponent
    *           aApplication Component
    */
   protected FormDecorator(Textable aComponent, String aTemplateName,
   short aType, User aViewer)
   {
      this(aComponent);

      type = aType;
      setViewer(aViewer);
      template = loadTemplate(aTemplateName, this);

   }//------------------------------------------

   /**
    * @param aApplicationComponent
    *           aApplication Component
    */
   protected FormDecorator(Textable aComponent)
   {
      if (aComponent == null)
         throw new IllegalArgumentException("component not provided");

      component = aComponent;

      try
      {
         the_rb = ResourceBundle.getBundle(FormDecorator.class.getName());
      }
      catch (Exception e)
      {
         logger.warn(e);
      }
   }//------------------------------------------

   /**
    * @return this.component.compareTo(aObject);
    */
   public int compareTo(Object aObject)
   {
      return this.component.getText().compareTo(((Textable)aObject).getText());
   }

   /**
    * @return ApplicationComponent
    */
   protected Textable getComponent()
   {
      return component;
   }//------------------------------------------

   /**
    * 
    * 
    * @see nyla.solutions.formInjection.data.FormComponent#getText()
    */
   public String getText()
   {
      String text = component.getText();

      if (text == null)
         text = "";

      return text;
   }//---------------------------------------------

   public static final String PDF_TEMPLATE_DIR_CLASSPATH = "/org/solutions/form/formatter/pdf";

   public static final String HTML_TEMPLATE_DIR_CLASSPATH = "/org/solutions/form/formatter/html";

   /**
    * 
    * @param aName
    *           template name
    * @return
    * @throws Exception
    */
   protected static String loadTemplate(String aName,
                                        FormDecorator aFormDecorator)
   {
      if (Text.isNull(aName))
         throw new IllegalArgumentException(
         "Text.isNull()aName required in FormDecorator.loadTemplate");

      if (aFormDecorator == null)
         throw new IllegalArgumentException(
         "aFormDecorator required in FormDecorator.loadTemplate");

      try
      {
         switch (aFormDecorator.getType())
         {
         case PDF_TYPE:
            return IO.readClassPath(PDF_TEMPLATE_DIR_CLASSPATH + "/" + aName
            + ".fpdf");
         case HTML_TYPE:
            return IO.readClassPath(HTML_TEMPLATE_DIR_CLASSPATH + "/" + aName
            + ".fpdf");
         default:
            throw new SystemException("Unknow type " + aFormDecorator.getType()
            + " decorator=" + aFormDecorator);
         }
      }
      catch (Exception e)
      {
         throw new SetupException(e);
      }
   }//---------------------------------------------------------

   /**
    * 
    * @param aFO
    *           the XML FO definition
    * @return
    * @throws Exception
    */
   protected byte[] toPDF(String aFO) throws Exception
   {
      return FOP.toPDF(aFO);
   }//-------------------------------------

   /**
    * @param component
    *           the smart component
    */
   protected void setComponent(FormComponent component)
   {
       //TODO bad casting, but what can you do
      this.component = (Textable) component;
   }//-----------------------------------------------

   /**
    * @return the configured template string
    */
   protected String getTemplate()
   {
      return template;
   }//-----------------------------------------------

   /**
    * 
    * @return the_rb.getString("fo.cell")
    */
   protected String decorateCell(String aText) throws Exception
   {
      Hashtable map = new Hashtable();
      map.put("text", aText);

      return Text.format(getCellTemplate(), map);

   }//--------------------------------------------------

   /**
    * 
    * @return fo.cell or word.cell template values
    */
   protected String getCellTemplate()
   {
      if (type == PDF_TYPE)
      {
         return the_rb.getString("fo.cell");
      }
      else
      {
         return the_rb.getString("word.cell");
      }
   }//-----------------------------------------------------

   /**
    * 
    * @return fo.row or word.row template values
    */
   protected String getRowTemplate()
   {
      if (type == PDF_TYPE)
      {
         return the_rb.getString("fo.row");
      }
      else
      {
         return the_rb.getString("word.row");
      }

   }//--------------------------------------------------

   /**
    * 
    * @param aText
    * @return the decorator row format
    * @throws Exception
    */
   protected String decorateRow(String aText) throws Exception
   {

      Hashtable map = new Hashtable();
      map.put("text", aText);

      return Text.format(getRowTemplate(), map);

   }//--------------------------------------------------

   protected String getFirstColumnTemplate()
   {
      if (type == PDF_TYPE)
      {
         return the_rb.getString("fo.column.first");
      }
      else
      {
         return the_rb.getString("word.column.first");
      }

   }//---------------------------------------------------

   protected String getColumnDeclarationTemplate()
   {
      if (type == PDF_TYPE)
      {
         return the_rb.getString("fo.column");
      }
      else
      {
         return the_rb.getString("word.column");

      }
   }//---------------------------------------------

   /**
    * 
    * @return group.header resource bundle for the instance type
    */
   protected String getGroupHeaderTemplate()
   {
      if (type == PDF_TYPE)
      {
         return the_rb.getString("fo.group.header");
      }
      else
      {
         return the_rb.getString("word.group.header");
      }
   } //---------------------------------------

   /**
    * 
    * @return subsection.header resource bundle for the instance type
    */
   protected String getSubSectionHeaderTemplate()
   {
      if (type == PDF_TYPE)
      {
         return the_rb.getString("fo.subsection.header");
      }
      else
      {
         return the_rb.getString("word.subsection.header");
      }
   }//-------------------------------------------------

   /**
    * 
    * @return the header only template (word or fo "header.only" resource
    *         bundle)
    */
   protected String getHeaderOnlyTemplate()
   {
      if (type == PDF_TYPE)
      {
         return the_rb.getString("fo.header.only");
      }
      else
      {
         return the_rb.getString("word.header.only");
      }
   }//-------------------------------------------------

   /**
    * 
    * @return (fo or word) instruction.header
    */
   protected String getInstructionHeaderTemplate()
   {
      if (type == PDF_TYPE)
      {
         return the_rb.getString("fo.instruction.header");
      }
      else
      {
         return the_rb.getString("word.instruction.header");
      }
   }//-------------------------------------------------

   protected String getQuestionHeaderTemplate()
   {
      if (type == PDF_TYPE)
      {
         return the_rb.getString("fo.question.header");
      }
      else
      {
         return the_rb.getString("word.question.header");
      }
   }//---------------------------------------------------------

   protected String getKeepTemplate()
   {
      if (type == PDF_TYPE)
      {
         return the_rb.getString("fo.keep");
      }
      else
      {
         return the_rb.getString("word.keep");
      }

   }//---------------------------------------------------------
   protected String getLine()
   {
      if(type == PDF_TYPE)
      {
         return the_rb.getString("fo.line");
      }
      else
      {
         return the_rb.getString("html.line");
      }
   }//--------------------------------------------

   public String fix(Object aText)
   {
      if (aText == null)
         aText = "";

      return fix(aText.toString());
   }//--------------------------------------------

   /**
    * Escape any special characters
    * 
    * @param aText
    *           the text to fixed
    * @return
    */
   public String fix(String aText)
   {
      if (Text.isNull(aText))
         return "";

      switch (this.type)
      {
      case PDF_TYPE:
         return XML.escapeElementEntities(aText);
      case WORD_TYPE:
         return Stylist.fix(aText);
      default:
         return XML.escapeElementEntities(aText);
      }
   }//--------------------------------------------------------------

   /**
    * 
    * @return indent a string (resource name [word.indent | fo.indent]
    */
   protected String indent()
   {
      if (getType() == WORD_TYPE)
      {
         return the_rb.getString("word.indent");
      }
      else
      {
         return the_rb.getString("fo.indent");
      }
   }//-------------------------------------------------------

   /**
    * 
    * @return deocorator type (Word, PDF, etc)
    */
   public short getType()
   {
      return type;
   }//------------------------------------------------

   /**
    * 
    * @param aDate
    *           SimpleDateFormat for MM/dd/yy hh:mm a
    */
   protected String format(Date aDate)
   {
      if (aDate == null)
         return BLANK;
      else
         return dateFormat.format(aDate);
   }//------------------------------------------------

   /**
    * 
    * @param aDate
    *           to format
    * @param aFormat
    *           SimpleDateFormat for MM/dd/yy hh:mm a
    */
   protected String format(Date aDate, String aFormat)
   {
      return format(aDate, aFormat, BLANK);
   }//------------------------------------------------

   /**
    * 
    * @param aDatedate
    *           to format
    * @param aFormat
    *           SimpleDateFormat for MM/dd/yy hh:mm a
    * @param aDefault
    *           default format
    */
   protected String format(Date aDate, String aFormat, String aDefault)
   {
      if (aDate == null)
         return aDefault;
      else
         return new SimpleDateFormat(aFormat).format(aDate);
   }//------------------------------------------------

   /**
    * 
    * @param aText
    *           the text to format
    * @return formatted test (nulls will be converted to FormDecorator.BLANK)
    */
   protected String format(String aText)
   {
      if (Data.isNull(aText))
      {
         return BLANK;
      }
      else
      {
         return aText;
      }
   }//------------------------------------------------

   /**
    * @param aTemplate
    *           override template value
    */
   public void setTemplate(String aTemplate)
   {
      template = aTemplate;
   }//---------------------------------------------------

   /**
    * 
    * @param aNumber
    *           the number to format
    * @return
    */
   protected String format(int aNumber)
   {
      if (aNumber < 1)
      {
         return "";
      }
      else
      {
         return String.valueOf(aNumber);
      }
   }//------------------------------------------------

   /**
    * 
    * @param aQuestion
    * @return true if (is underwriter and not viewed by underwriter) || is
    *         online or is hidden or is renewnal and not a renewal application
    *         is new business and not a new business application
    *  
    */
   protected boolean mustSkip(FormQuestion aQuestion)
   {
      return false;
   }//-------------------------------------------------------------

   /**
    * 
    * @param aCurrentNumber
    *           the current question number count
    * @param aQuestion
    *           the question
    * @return next display number sequence
    */
   protected int nextNumber(int aCurrentNumber, FormQuestion aQuestion)
   {
      int nextNumber = aCurrentNumber;

      //Process numbering
      if ((this.getType() == WORD_TYPE || this.getType() == PDF_TYPE))
      {
         //reset current number
         nextNumber = 1;
      }
      else if (aQuestion.mustNumber() && !this.mustSkip(aQuestion))
      {
         //Increment current number if the previous question was number
         nextNumber++;
      }

      return nextNumber;
   }//-------------------------------------------------

   /**
    * Set the viewer
    * 
    * @param aViewer
    *           the viewer
    */
   protected void setViewer(User aViewer)
   {
      if (aViewer == null)
      {
         throw new IllegalArgumentException("Viewer not provider");
      }
      viewer = aViewer;
   }//---------------------------------------------------

   /**
    * 
    * @return the user viewer
    */
   public User getViewer()
   {
      return viewer;

   }//-------------------------------------------------

   /**
    * 
    * @param aResponseType
    *           the response type
    * @return aResponseType.getMaxLength()
    */
   protected int getSize(ResponseType aResponseType)
   {
      return aResponseType.getMaxLength();
   }//--------------------------------------------

   /**
    * @param aText
    * @param aMap
    * @param aTemplate
    */
   public String format(Map aMap, String aTemplate)
   {
      try
      {
         return Text.format(aTemplate, aMap);
      }
      catch (Exception e)
      {
         throw new SetupException("Format error map=" + aMap + " template="
         + aTemplate + " error=" + e);
      }
   }//--------------------------------------------

   /**
    * @return Returns the numberLabel.
    */
   public String getNumberLabel()
   {
      return numberLabel;
   }//--------------------------------------------

   /**
    * @param numberLabel
    *           The numberLabel to set.
    */
   public void setNumberLabel(String numberLabel)
   {
      if (numberLabel == null)
         numberLabel = "";

      this.numberLabel = numberLabel;
   }//--------------------------------------------

   protected FormRow decorateBlankHorizontalRow(FormTable aFormTable,
                                                int aRowNumber)
   {
      FormRow blankRow = aFormTable.createRow(aRowNumber);
      

      for (int i = 0; i < aFormTable.getColumnCount(); i++)
      {
         blankRow.addAnswer(i, null);
      }

      return blankRow;
   }//--------------------------------------------

   /**
    * @param aFormRow
    * @return
    */
   public Collection retrieveSortedAnswers(FormRow aFormRow)
   {
       /*
      Collection answers = Organizer.sortByJavaBeanProperty("columnNumber",
      aFormRow.getAnswers());
      */
       Collection answers = aFormRow.getAnswers();
      return answers;
   }//--------------------------------------------

   protected NumberLabelDecorator getCounter(FormQuestion aFormQuestion)
   {
      int sectionId = aFormQuestion.getSectionNumber() != null ? aFormQuestion.getSectionNumber().intValue() : 0;
      int questionId = aFormQuestion.getPrimaryKey();
      int parentId = 0;
      QuestionAttribute parentQuestionAttr = aFormQuestion.findAttributeByName("parentId");
      if (parentQuestionAttr != null)
      {
         String val = (String) parentQuestionAttr.getValue();
         if (val != null && !val.equals(""))
            parentId = Integer.parseInt(val);
      }
      return getCounter(aFormQuestion.retrieveForm(), sectionId, questionId,
      parentId);
   }//--------------------------------------------

   protected NumberLabelDecorator getSectionCounter(Form aForm, int sectionId)
   {
      NumberLabelDecorator counter = (NumberLabelDecorator) aForm
      .retrieveAttributeValue("sectionCounter");

      if (counter == null || counter.getSectionId() != sectionId)
      {
         counter = new NumberLabelDecorator(sectionId);
         aForm.addAttribute("sectionCounter", counter);
      }
      return counter;
   }//--------------------------------------------

   private NumberLabelDecorator getCounter(Form aForm, int sectionId,
                                           int questionId, int parentId)
   {
      NumberLabelDecorator counter = (NumberLabelDecorator) aForm
      .retrieveAttributeValue("counter");
      if (counter == null || counter.getSectionId() != sectionId)
      {
         //counter = new AutoCounter(sectionId);
         counter = getSectionCounter(aForm, sectionId);
         counter.setFormComponentId(questionId);
      }
      else if (parentId > 0 && counter.getFormComponentId() == parentId)
      {
         NumberLabelDecorator childCounter = new NumberLabelDecorator(sectionId);
         childCounter.setFormComponentId(questionId);
         childCounter.setParent(counter);
         //counter.setChild(childCounter);
         counter = childCounter;
      }
      else
      {
         NumberLabelDecorator current = counter;
         while (current.getParent() != null
         && current.getParent().getFormComponentId() != parentId)
            current = current.getParent();
         counter = current;
      }
      aForm.addAttribute("counter", counter);
      return counter;
   }

   protected String getNumberLabel(FormQuestion question)
   {
      NumberLabelDecorator counter = updateCounter(question);
      return counter.toString() + ".";
   }//--------------------------------------------

   protected NumberLabelDecorator updateCounter(FormQuestion aFormQuestion)
   {
      NumberLabelDecorator counter = getCounter(aFormQuestion);
      counter.increment();
      counter.setFormComponentId(aFormQuestion.getPrimaryKey());
      return counter;
   }//--------------------------------------------

   protected String getNumberLabel(FormAnswer tableColAnswer)
   {
      int sectionId = tableColAnswer.getFormQuestion().getSectionNumber().intValue();
      int questionId = tableColAnswer.getColumnNumber().intValue();
      int parentId = tableColAnswer.getFormQuestion().getPrimaryKey();

      NumberLabelDecorator counter = getCounter(tableColAnswer.retrieveForm(),
      sectionId, questionId, parentId);

      counter.increment();
      return counter.toString() + ".";
   }//--------------------------------------------

   /**
    * 
    * @see org.solutions.data.Copier#copy(org.solutions.data.Copier)
    */
   public void copy(Copier aFrom)
   {
      if (aFrom == null)
         return;

      FormDecorator other = (FormDecorator) aFrom;
      this.viewer = other.viewer;
   }//--------------------------------------------

   /**
    * @return the locale
    */
   public Locale getLocale()
   {
      return locale;
   }

   /**
    * @param locale the locale to set
    */
   public void setLocale(Locale locale)
   {
      this.locale = locale;
   }
   private Locale locale = Locale.US;
   private String numberLabel = "";

   private User viewer = null;

   private ResourceBundle the_rb = null;

   private String template = "";

   protected short type = UNKNOWN;

   private Textable component = null;

   protected Log logger = Debugger.getLog(getClass());

   private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

}
