package nyla.solutions.formInjection.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import nyla.solutions.global.util.Organizer;

/**
 * 
 * <pre>
 * Questionaire is a value object representation of the Questionaire table
 * and associated entities.
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public class Questionaire implements Serializable
{
   static final long serialVersionUID = Questionaire.class.getName().hashCode();


   public Questionaire()
   {
   }//--------------------------------------------
   public Questionaire(Integer formTypeId, Collection questions)
   {
      this(formTypeId, questions, null);
   }

   public Questionaire(Integer formTypeId, Collection questions,
   Collection sections)
   {
      this.formTypeId = formTypeId;
      setQuestions(questions, sections);
   }

   public Integer getFormTypeId()
   {
      return formTypeId;
   }

   public Map getQuestions()
   {
      return questions != null ? questions : Collections.EMPTY_MAP;
   }

   public Question getQuestion(Integer id)
   {
      return (Question) questions.get(id);
   }

   public List getQuestionsWithAttribute(String name)
   {
      List l = (List) questionsByAttribute.get(name);
      if (l == null)
         return Collections.EMPTY_LIST;
      return l;
   }

   public Collection retrieveQuestionsWithResponseType(Integer id)
   {
      Collection l = (List) questionsByResponseType.get(id);
      if (l == null)
         return Collections.EMPTY_LIST;
      return l;
   }

   public List getColumnsWithAttribute(String name)
   {
      List l = (List) columnsByAttribute.get(name);
      if (l == null)
         return Collections.EMPTY_LIST;
      return l;
   }
   /**
    * 
    * @return questions that with operations
    */
   public Collection retrieveQuestionsWithOperation()
   {
      if(this.questionsWithOperation != null || this.questions == null || this.questions.isEmpty())         
      {
         return this.questionsWithOperation;
      }
       
      this.questionsWithOperation = new TreeSet();
         
      
      initCache();
      
      return questionsWithOperation;
   }//--------------------------------------------
   /**
    * Initialize the cache of questions and columns
    */
   private synchronized void initCache()
   {
      if(intializedCache)
         return;
      
      if(this.columnsWithOperation == null)
      {
         columnsWithOperation = new HashSet();
      }
      
      Question question = null;
      Column column = null;
      Collection columns = null;
      //find all questions that have operations
      for (Iterator i = questions.values().iterator(); i.hasNext(); ) 
      {
           question = (Question) i.next();
           if(question.hasOperation())
           {
              this.questionsWithOperation.add(question);  
           }
           
           //search for columns with operations
           if(question.getResponseTable() != null && question.getResponseTable().hasColumns())
           {
              columns = question.getResponseTable().getColumns();
              
             
               for (Iterator colIterator = columns.iterator(); colIterator.hasNext();)
               {
                  column = (Column) colIterator.next();
                  if(column.getResponseType() != null && column.getResponseType().hasOperation())
                  {
                     Object[] objects = {question, column};
                     //add to cache of columns with operations
                     this.columnsWithOperation.add(objects);
                  }
               }
           }
       }
      intializedCache = true;
   }// --------------------------------------------
   /**
    * 
    * @return find all columns with operation
    */
   public Collection retrieveColumnsWithOperation()
   {
      if(this.columnsWithOperation == null || this.columnsWithOperation.isEmpty())
      {
         initCache();         
      }
     
      return this.columnsWithOperation;      
   }// --------------------------------------------


   public Collection getQuestionsBySection(Integer sectionNumber)
   {
      List questions = (List) sectionMap.get(sectionNumber);
      if (questions == null)
         return Collections.EMPTY_LIST;
      return questions;
   }

   public Section getSection(Integer sectionNumber)
   {
      if(sectionMap == null)
         return null;
      
      return (Section) sectionMap.get(sectionNumber);
   }//--------------------------------------------

   public Collection retrieveSections()
   {
      Collection c = sectionMap.values();
      c = (List) Organizer.sortByJavaBeanProperty("number", c, false);
      return c;
   }

   private Map prepareSections(Collection sections)
   {
      if (sections == null)
         return Collections.EMPTY_MAP;
      Map m = new HashMap();
      for (Iterator i = sections.iterator(); i.hasNext();)
      {
         Section s = (Section) i.next();
         m.put(s.getNumber(), s);
      }
      this.sectionMap = m;
      return m;
   }

   private void setQuestions(Collection questions, Collection sections)
   {
      Map qm = new HashMap();
      Map qa = new HashMap();
      Map qr = new HashMap();
      Map ca = new HashMap();
      List qop = new LinkedList();
      List cop = new LinkedList();
      Map sm = new TreeMap();

      Map sectionMap = prepareSections(sections);
      for (Iterator i = questions.iterator(); i.hasNext();)
      {
         Question q = (Question) i.next();
         qm.put(q.getQuestionId(), q);

         Section s = (Section) sectionMap.get(q.getSectionNumber());
         if (s != null)
         {
            List l = (List) sm.get(s.getNumber());
            if (l == null)
            {
               l = new LinkedList();
               sm.put(s.getNumber(), l);
            }
            l.add(q);
         }

         Map attributeMap = q.getAttributeMap();
         for (Iterator ii = attributeMap.keySet().iterator(); ii.hasNext();)
         {
            String name = (String) ii.next();
            List l = (List) qa.get(name);
            if (l == null)
            {
               l = new LinkedList();
               qa.put(name, l);
            }
            l.add(q);
         }
         if (q.getResponseTable() != null)
         {
            Map cols = q.getResponseTable().getColumnMap();
            for (Iterator j = cols.values().iterator(); j.hasNext();)
            {
               Column c = (Column) j.next();
               Object[] entry = {
               q, c };
               attributeMap = c.getAttributes();
               for (Iterator ii = attributeMap.keySet().iterator(); ii
               .hasNext();)
               {
                  String name = (String) ii.next();
                  List l = (List) ca.get(name);
                  if (l == null)
                  {
                     l = new LinkedList();
                     ca.put(name, l);
                  }
                  l.add(entry);
               }
               if (c.getResponseType().hasOperation())
                  cop.add(entry);
            }
         }
         String responseTypeCode = q.getResponseTypeCode();
         List l = (List) qr.get(responseTypeCode);
         if (l == null)
         {
            l = new LinkedList();
            qr.put(responseTypeCode, l);
         }
         l.add(q);

         if (q.getResponseType().hasOperation())
            qop.add(q);
      }
      this.questions = qm;
      this.questionsByAttribute = qa;
      this.questionsByResponseType = qr;
      this.columnsByAttribute = ca;
      //this.questionsWithOperation = qop;
      this.columnsWithOperation = cop;
      this.sectionMap = sm;
   }




   /**
    * @return the sectionMap
    */
   public Map getSectionMap()
   {
      return sectionMap;
   }
   /**
    * @param formTypeId the formTypeId to set
    */
   public void setFormTypeId(Integer formTypeId)
   {
      this.formTypeId = formTypeId;
   }//--------------------------------------------
   /**
    * @param questions the questions to set
    */
   public void setQuestions(Map questions)
   {
      this.questions = questions;
   }//--------------------------------------------

   private boolean intializedCache = false;
   private Integer formTypeId = null;

   private Map questions = null;

   private Map questionsByAttribute = null;

   private Map questionsByResponseType = null;

   private Map columnsByAttribute = null;

   private Collection questionsWithOperation = null;

   private Collection columnsWithOperation = null;

   private Map sectionMap;

}
