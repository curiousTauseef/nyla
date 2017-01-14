package nyla.solutions.core.patterns.observer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import nyla.solutions.core.data.Copier;
import nyla.solutions.core.exception.RequiredException;
import nyla.solutions.core.util.Debugger;






/**
 * 
 * <b>Topic</b> is a implementation of subject 
 * @author Gregory Green
 *
 */
public class Topic implements Subject
{

	/**
    * 
    * Constructor for Topic initializes internal
    */
   public Topic()
   {
   }//--------------------------------------------
   /**
    * 
    * Constructor for Topic initializes internal 
    * @param name subject name
    */
   public Topic(String name)
   {
      this.setName(name);
   }//--------------------------------------------
   
   /**
    * 
    * 
    * @see nyla.solutions.core.patterns.observer.Subject#add(nyla.solutions.core.patterns.observer.SubjectObserver)
    */
   public void add(SubjectObserver subjectObserver)
   {
      if (subjectObserver == null)
         throw new RequiredException("subjectObserver in Topic.add");
      
      String id = subjectObserver.getId();
      
      if (id == null || id.length() == 0)
         throw new IllegalArgumentException("Subject observer id required");
      
     //get previous
      SubjectObserver prev = (SubjectObserver)observerMap.get(subjectObserver.getId());
      
      if(prev != null && prev != subjectObserver)
      {
         Debugger.println(this,"Object registered, but observer id already exists "+subjectObserver.getId()+" for subject="+this.name);
      }
      
      //add       
      observerMap.put(subjectObserver.getId(), subjectObserver);
      
   }// --------------------------------------------
   /**
    * 
    *
    * @see nyla.solutions.core.patterns.observer.Subject#notify(java.lang.Object)
    */
   public void notify(Object object)
   {
      //loop thru observers
      SubjectObserver subjectObserver = null;
      for (Iterator<SubjectObserver>  i = observerMap.values().iterator(); i.hasNext();)
      {
         subjectObserver = (SubjectObserver) i.next();
         synchronized (subjectObserver) 
         {
            subjectObserver.update(this, object);   
         }         
      }
   }// --------------------------------------------

   public void remove(SubjectObserver subjectObserver)
   {
      if(subjectObserver == null || subjectObserver.getId() == null || subjectObserver.getId().length() == 0)
         return;//do nothing
      
      //remove
      observerMap.remove(subjectObserver.getId());
   }// --------------------------------------------   
   /**
    * @return the name
    */
   public String getName()
   {
      return name;
   }// --------------------------------------------

   /**
    * @param name the name to set
    */
   public void setName(String name)
   {
      if(name == null)
         name = "";
      
      this.name = name;
   }// --------------------------------------------
   /**
    * 
    * Compare the names of two subjects
    * @see java.lang.Comparable#compareTo(T)
    */
   public int compareTo(Object object)
   {
      if(!(object instanceof Subject))
         return -1;
      
      Subject subject = (Subject)object;
      
      return this.name.compareTo(subject.getName());
   }// --------------------------------------------
   /**
    * 
    *
    * @see nyla.solutions.core.data.Copier#copy(nyla.solutions.core.data.Copier)
    */
   public void copy(Copier from)
   {
      if(!(from instanceof Topic))
      {
         return;
      }
      
      Topic topic = (Topic)from;
      this.name = topic.name;
      this.observerMap = topic.observerMap;
   }// --------------------------------------------
   

   /**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((observerMap == null) ? 0 : observerMap.hashCode());
		return result;
	}
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topic other = (Topic) obj;
		if (name == null)
		{
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		if (observerMap == null)
		{
			if (other.observerMap != null)
				return false;
		}
		else if (!observerMap.equals(other.observerMap))
			return false;
		return true;
	}
/**
    *
    * @see java.lang.Object#toString()
    */
   public String toString()
   {
      
      return this.getClass().getName()+" name="+name;
   }
   private String name = this.getClass().getName();
   private Map<String,SubjectObserver> observerMap = new HashMap<String,SubjectObserver> ();
}
