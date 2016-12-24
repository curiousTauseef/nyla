package nyla.solutions.core.patterns.observer;

import java.util.HashMap;
import java.util.Map;

import nyla.solutions.core.util.Debugger;


/**
 * 
 * <b>SubjectRegistry</b> is a object/mapping of multiple topics and observers 
 * @author Gregory Green
 *
 */
public class SubjectRegistry
{
   /**
    * 
    * @param subjectName the subject name
    * @param data the information to send
    */
   public void notify(String subjectName, Object data)
   {
      
      //get subject
      Object object = this.registry.get(subjectName);
      
      if(object == null)
         return; //nobody to update
      
      
      
      try
      {
         Subject subject = (Subject)object;
         subject.notify(data);
      }
      catch (ClassCastException e)
      {
         throw new IllegalArgumentException(object.getClass().getName()+" "+Debugger.stackTrace(e));
      }
      
   }// --------------------------------------------
   /**
    * Add the subject Observer (default Topic implementation may be used)
    * @param subjectName the subject name
    * @param subjectObserver the subject observer
    */
   public void register(String subjectName, SubjectObserver subjectObserver)
   {
      Subject subject = (Subject)this.registry.get(subjectName);
      
      if(subject == null)
         subject = new Topic();
      
      register(subjectName, subjectObserver, subject);
      
   }// --------------------------------------------
   /**
    * Add subject observer to a subject
    * @param subjectName the subject name
    * @param subjectObserver the subject observer
    * @param subject the subject to add the observer
    */
   public void register(String subjectName,
                                   SubjectObserver subjectObserver,
                                   Subject subject)
   {
      subject.add(subjectObserver);
      
      this.registry.put(subjectName, subject);
   }// --------------------------------------------
   /**
    * Remove an observer for a registered observer
    * @param subjectName the subject name to remove
    * @param subjectObserver the subject observer
    */
   public void removeRegistraion(String subjectName, SubjectObserver subjectObserver)
   {
      Subject subject = (Subject)this.registry.get(subjectName);
      
      if(subject == null)
         return;
      
      subject.remove(subjectObserver);
      
   }// -------------------------------------------- 
   
   
   /**
    * @return the registry
    */
   public Map<String,Subject> getRegistry()
   {
      return registry;
   }
   /**
    * @param registry the registry to set
    */
   public void setSubjectObservers(Map<Object,SubjectObserver> registry)
   {
      //loop thru registry 
      
      for (Map.Entry<Object, SubjectObserver> entry: registry.entrySet())
      {
         
           this.register(entry.getKey().toString(), entry.getValue());
     
      }//end for check
   }// --------------------------------------------------------
   
   private Map<String,Subject> registry = new HashMap<String,Subject>();
}
