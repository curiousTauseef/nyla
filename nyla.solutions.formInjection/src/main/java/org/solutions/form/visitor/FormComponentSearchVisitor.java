package org.solutions.form.visitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import nyla.solutions.global.data.Attribute;

import org.solutions.form.data.FormComponent;
import org.solutions.form.data.FormQuestion;



/**
 * <pre>
 * FormComponentSearchVisitor search for form questions
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class FormComponentSearchVisitor extends AbstractFormVisitor
{
   public static final short QUESTION_ATTRIB_NM_CONTAINS = 1;
   

   /**
    * Constructor for FormComponentSearchVisitor initalizes internal 
    * data settings.
    * 
    */
   private FormComponentSearchVisitor()
   {
      super();
   }//--------------------------------------------
   public boolean visitQuestions() {
       return true;
   }
   /**
    * 
    * @see org.solutions.form.visitor.AbstractFormVisitor#visit(org.solutions.form.data.FormQuestion)
    */
   public void visit(FormQuestion aFormQuestion)
   {
      switch(searchType)
      {
       case QUESTION_ATTRIB_NM_CONTAINS: buildAttributeNameThatContainText(aFormQuestion);
       break;
      }
   }//--------------------------------------------
   public static FormComponentSearchVisitor formQuestionsAttributeNameContains(String aAttributeName)
   {
      if (aAttributeName == null)
         throw new IllegalArgumentException(
         "aAttributeName required in FormComponentSearchVisitor.formQuestionsAttributeNameContains");
      
      aAttributeName = aAttributeName.toUpperCase();
      FormComponentSearchVisitor visitor = new FormComponentSearchVisitor();
      visitor.searchType = QUESTION_ATTRIB_NM_CONTAINS;
      visitor.searchText = aAttributeName;
      return visitor;
   }//--------------------------------------------
   /**
    * buildAttributeNameThatContainText(aFormQuestion)
    * @param aFormQuestion
    */
   private void buildAttributeNameThatContainText(FormQuestion question)
   {
      Collection attributes = question.getAttributes();
      
      if(attributes == null || attributes.isEmpty())
         return;
      
      Attribute attribute = null;
      for (Iterator i = attributes.iterator(); i.hasNext();)
      {
         attribute = (Attribute) i.next();
         
         //test if contains text
         if(attribute.getName().toUpperCase().indexOf(this.searchText) > -1)
         {
            //add form question
            this.addResult(question);
         }
      }
   }//--------------------------------------------
   /**
    * 
    * @param aFormComponent
    */
   private void addResult(FormComponent aFormComponent)
   {
      if(results == null)
         results = new ArrayList(10);
      
      results.add(aFormComponent);
   }//--------------------------------------------
   /**
    * @return Returns the results.
    */
   public Collection getResults()
   {
      return results;
   }//--------------------------------------------
   private Collection results = null;
   
   private short searchType = QUESTION_ATTRIB_NM_CONTAINS;
   private String searchText = null;
   
}
