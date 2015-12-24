package org.solutions.form.web;


import junit.framework.TestCase;
import nyla.solutions.global.web.test.TestPageContext;

public class SectionContainerTagTest extends TestCase
{

   protected void setUp() throws Exception
   {
      pageContext.getTestHttpServletRequest().addParameter("formTypeCode", "registration");
      
      tag.setPageContext(pageContext);
   }//--------------------------------------------
   public SectionContainerTagTest(String name)
   {
      super(name);
   }//--------------------------------------------
   public void testDoStartTag()
   throws Exception
   {
      tag.setId("1");
      
      tag.doStartTag();
      
   }//--------------------------------------------
   public void testQuestionaireDoStartTag()
   throws Exception
   {
      QuestionaireTag questionaireTag = new QuestionaireTag();
      
      questionaireTag.setPageContext(pageContext);
            
      questionaireTag.doStartTag();
      
   }//--------------------------------------------

   TestPageContext pageContext = new TestPageContext();
   SectionContainerTag tag = new SectionContainerTag();
}
