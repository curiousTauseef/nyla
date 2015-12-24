package org.solutions.form.formatter.html;

import java.io.StringWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import junit.framework.Assert;
import junit.framework.TestCase;
import nyla.solutions.global.security.data.LoginCredential;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.xml.XML;

import org.solutions.form.ApplicationBuilder;
import org.solutions.form.FormDelegate;
import org.solutions.form.FormUTUtil;
import org.solutions.form.data.Column;
import org.solutions.form.data.Form;
import org.solutions.form.data.FormTable;
import org.solutions.form.data.FormType;

public class HTMLDecoratorTest extends TestCase
{

   public HTMLDecoratorTest(String name)
   {
      super(name);
      LoginCredential loginCredential = new LoginCredential();
      loginCredential.setLoginID("junit");
      formDelegate = new FormDelegate(loginCredential);

   }//--------------------------------------------

   /**
    * Test the retrieval of HTML templates
    * @throws Exception
    */
   public void testGetTemplate()
   throws Exception
   {
      
      ApplicationBuilder builder  = new ApplicationBuilder();
      FormUTUtil.constructForm(builder);
      
      Form form = builder.getForm();
      
      FormAnswerViewExtra formAnswerView = new FormAnswerViewExtra(form.getAnswer(new Integer(1)));
      formAnswerView.setName("answer");
      StringWriter out = new StringWriter();
      
//      Configuration cfg = new Configuration();
//      cfg.setDirectoryForTemplateLoading(new File(Config.getProperty(Text.TEMPLATE_DIR_PROP_NM)));
//      
//      
//      //cfg.setClassForTemplateLoading(HTMLDecorator.class, "/org/solutions/form/formatter/html/");
//      cfg.setObjectWrapper(new DefaultObjectWrapper());
//      Template template = cfg.getTemplate("answer.fhtml");
//      
//      S
//     // template.process(formAnswerView, out);
//      
//      Debugger.println(HTMLDecorator.HTML_TEMPLATE_DIR_CLASSPATH);      
            
      Map m = new HashMap();
      m.put("question", formAnswerView);
      
      HTMLDecorator.print(formAnswerView, out,"test");
      Debugger.println(this, "out="+out.getBuffer());
   }//--------------------------------------------
   public void testDecorateTable() 
   throws Exception
   {
      FormType formType = new FormType();
      Debugger.println(this,XML.getInterpreter().toXML(formType));
      
      Form newForm = this.formDelegate.retrieveNewForm(formTypeCode);
      
      
      org.solutions.form.data.FormQuestion formQuestion =newForm.getFormQuestion(tableQuestionId);       
      
      Assert.assertNotNull(formQuestion);
      
      FormTable formTable = formQuestion.getFormTable();      
      Assert.assertNotNull(formTable);
      
      FormQuestionViewExtra view = new FormQuestionViewExtra(formQuestion);
             
      Collection cols = view.getTable().getColumns();
      Debugger.println(this, "cols="+cols);
      Iterator colI = cols.iterator();
      Column col1 = (Column)colI.next();
      Assert.assertTrue("col="+col1.getColNumber().intValue(), col1.getColNumber().intValue() == 0);
      Column col2 = (Column)colI.next();
      Assert.assertTrue(col2.getColNumber().intValue() == 1);
      
      Assert.assertTrue(cols != null && !cols.isEmpty() && cols.size() == 2);
      
      
      
      FormAnswerView answer1 = view.getTable().getAnswer(0, 0);
      Debugger.println(this, "answer1="+answer1);
      FormAnswerView answer2 = view.getTable().getAnswer(0, 1);
      Debugger.println(this, "answer2="+answer2);
      
      Assert.assertTrue(answer1.getAnswer().getResponseType().getCode(),answer1.getAnswer().getResponseType().getCode().equals("date"));
      Assert.assertTrue(answer2.getAnswer().getResponseType().getCode(),answer2.getAnswer().getResponseType().getCode().equals("listbox"));
            
      Collection rows = view.getTable().getRows();
      Debugger.println(this, "rows="+rows);
      
      Assert.assertTrue(rows != null & !rows.isEmpty());
      
      StringWriter out = new StringWriter();      
      HTMLDecorator.print(view, out,"test");
      
      Assert.assertTrue(out.getBuffer().length() > 0);
      
      Debugger.println(this, "out="+out.getBuffer());
      
      
      
   }//--------------------------------------------
   private Integer tableQuestionId = new Integer(9);
   private String formTypeCode = "registration";
   private FormDelegate formDelegate = null;
}
