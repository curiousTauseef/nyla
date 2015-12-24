package org.solutions.form.visitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import nyla.solutions.global.data.Attribute;
import nyla.solutions.global.util.Text;

import org.solutions.form.data.FormComponent;
import org.solutions.form.data.FormQuestion;


/**
 * <pre>
 * SearchVisitor search for form components
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class SearchVisitor extends AbstractFormVisitor
{
   public static final short GCSM_QUESTION_ATTRIB_NM_CONTAINS = 1;
   
   public boolean visitQuestions() {
       return true;
   }

   /**
    * Constructor for SearchVisitor initalizes internal 
    * data settings.
    * 
    */
   private SearchVisitor()
   {
      super();
   }//--------------------------------------------
   /**
    * 
    * @see org.solutions.form.visitor.AbstractFormVisitor#visit(org.solutions.form.data.FormQuestion)
    */
   public void visit(FormQuestion aFormQuestion)
   {
      switch(searchType)
      {
       case GCSM_QUESTION_ATTRIB_NM_CONTAINS: buildAttributeNameThatContainText(aFormQuestion);
       break;
      }
   }//--------------------------------------------
   public static SearchVisitor gcsmQuestionsNonBlankAttributeNameContains(String aAttributeName)
   {
      if (aAttributeName == null)
         throw new IllegalArgumentException(
         "aAttributeName required in SearchVisitor.formQuestionsAttributeNameContains");
      
      aAttributeName = aAttributeName.toUpperCase();
      SearchVisitor visitor = new SearchVisitor();
      visitor.searchType = GCSM_QUESTION_ATTRIB_NM_CONTAINS;
      visitor.searchText = aAttributeName;
      return visitor;
   }//--------------------------------------------
   /**
    * buildAttributeNameThatContainText(aFormQuestion)
    * @param aFormQuestion
    */
   private void buildAttributeNameThatContainText(FormQuestion aFormQuestion)
   {
      Collection attributes = aFormQuestion.getAttributes();
      
      if(attributes == null || attributes.isEmpty())
         return;
      
      Attribute attribute = null;
      for (Iterator i = attributes.iterator(); i.hasNext();)
      {
         attribute = (Attribute) i.next();
         
         //skip blank attribute values
         if(attribute.getValue() == null
            || Text.isNull(String.valueOf(attribute.getValue())))
          {
            continue;
          }
         
         //test if contains text
         if(attribute.getName().toUpperCase().indexOf(this.searchText) > -1)
         {
            //add form question
            this.addResult(aFormQuestion);
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
         results = new ArrayList();
      
      results.add(aFormComponent);
   }//--------------------------------------------
   /**
    * @return Returns the results.
    */
   public Collection getResults()
   {
      return results == null ? Collections.EMPTY_LIST : results;
   }//--------------------------------------------
   private Collection results = null;
   
   private short searchType = GCSM_QUESTION_ATTRIB_NM_CONTAINS;
   private String searchText = null;
   
}
