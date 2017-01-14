package nyla.solutions.core.patterns.observer;

import java.util.Calendar;
import java.util.Date;

import nyla.solutions.core.data.TimeInterval;
import nyla.solutions.core.exception.SystemException;
import nyla.solutions.core.patterns.decorator.TimeIntervalDecorator;
import nyla.solutions.core.util.Debugger;
import nyla.solutions.core.util.Text;

/**
 * 
 * <b>SubjectTimerObserver</b> is a observer to track the time between two subject events 
 * @author Gregory Green
 *
 */
public class SubjectTimerObserver implements SubjectObserver, TimeInterval
{
   /**
    * Constructor for SubjectTimerObserver initializes internal
    */
   public SubjectTimerObserver()
   {
   }//--------------------------------------------
   /**
    * 
    * Constructor for SubjectTimerObserver initializes internal 
    * @param timeIntervalDecorator
    */
   public SubjectTimerObserver(TimeIntervalDecorator timeIntervalDecorator)
   {
      this.decorator = timeIntervalDecorator;
   }//--------------------------------------------
   /**
    *
    * @see nyla.solutions.core.patterns.observer.SubjectObserver#update(nyla.solutions.core.patterns.observer.Subject, java.lang.Object)
    */
   public void update(Subject subject, Object data)
   {
         Debugger.println(this,"Recieve subject="+subject);
         
         if(isStart(subject))
         {          
            this.startData = data;
            this.startDate = Calendar.getInstance().getTime();
            Debugger.printInfo(this,"TIMER START DATE ["+Text.formatDate(startDate)+"]\n "+Text.toString(startData));
            
         }
         else if(isEnd(subject))
         {            
            this.endData = data;
            this.endDate = Calendar.getInstance().getTime();
            
            Debugger.printInfo(this,"TIMER END DATE ["+endDate+"]\n "+endData);
            
            if(this.decorator != null)
               decorator.decorator(this);
         }   
         else
         {
            throw new SystemException("Unknown subject "+subject.getName());
         }
   }//--------------------------------------------
   /**
    * 
    * @param subject the subject
    * @return true is subject name matches the regular expression
    */
   public boolean isStart(Subject subject)
   {
      if(subject == null || subject.getName() == null || this.startSubjectNamePattern == null)
         return false;
      
      return Text.matches(subject.getName(), this.startSubjectNamePattern);
   }//--------------------------------------------
   /**
    * 
    * @param subject the subject
    * @return true is subject name matches the regular expression
    */
   public boolean isEnd(Subject subject)
   {
      if(subject == null || subject.getName() == null || this.endSubjectNamePattern == null)
         return false;
      
      return Text.matches(subject.getName(), this.endSubjectNamePattern);
   }//--------------------------------------------
   /**
    * @return this.getClass().getName()+"START:"+startSubjectNamePattern+" END:"+endSubjectNamePattern
    * @see nyla.solutions.core.data.Identifier#getId()
    */
   public String getId()
   {
      return this.getClass().getName()+" START:"+startSubjectNamePattern+" END:"+endSubjectNamePattern;
   }//--------------------------------------------
   public void setId(String id)
   {
   }//--------------------------------------------
   /**
    * @return the startSubjectNamePattern
    */
   public String getStartSubjectNamePattern()
   {
      return startSubjectNamePattern;
   }//--------------------------------------------
   /**
    * @param startSubjectNamePattern the startSubjectNamePattern to set
    */
   public void setStartSubjectNamePattern(String startSubjectNamePattern)
   {
      this.startSubjectNamePattern = startSubjectNamePattern;
   }//--------------------------------------------
   /**
    * @return the endSubjectNamePattern
    */
   public String getEndSubjectNamePattern()
   {
      return endSubjectNamePattern;
   }//--------------------------------------------
   /**
    * @param endSubjectNamePattern the endSubjectNamePattern to set
    */
   public void setEndSubjectNamePattern(String endSubjectNamePattern)
   {
      this.endSubjectNamePattern = endSubjectNamePattern;
   }//--------------------------------------------
   /**
    * @return the decorator
    */
   public TimeIntervalDecorator getDecorator()
   {
      return decorator;
   }
   /**
    * @param decorator the decorator to set
    */
   public void setDecorator(TimeIntervalDecorator decorator)
   {
      this.decorator = decorator;
   }//--------------------------------------------
   /**
    * @return the startDate
    */
   public Date getStartDate()
   {
	   if(startDate == null)
		   return null;
	   
      return new Date(startDate.getTime());
   }//--------------------------------------------
   /**
    * @param startDate the startDate to set
    */
   public void setStartDate(Date startDate)
   {
	   if(startDate == null)
		 {
		   this.startDate = null;
		   return;
		 }
	   
      this.startDate = new Date(startDate.getTime()) ;
   }//--------------------------------------------
   /**
    * @return the endDate
    */
   public Date getEndDate()
   {
	   if(endDate == null)
		   return null;
	   
      return new Date(endDate.getTime());
   }//--------------------------------------------
   /**
    * @param endDate the endDate to set
    */
   public void setEndDate(Date endDate)
   {
	   if(endDate == null)
	   {
		   this.endDate = null;
		   return;
	   }
	   
      this.endDate = new Date(endDate.getTime());
   }//--------------------------------------------   
   /**
    * @return the startData
    */
   public Object getStartData()
   {
      return startData;
   }//--------------------------------------------
   /**
    * @param startData the startData to set
    */
   public void setStartData(Object startData)
   {
      this.startData = startData;
   }//--------------------------------------------
   /**
    * @return the endData
    */
   public Object getEndData()
   {
      return endData;
   }//--------------------------------------------
   /**
    * @param endData the endData to set
    */
   public void setEndData(Object endData)
   {
      this.endData = endData;
   }//--------------------------------------------

   private Object startData = null;
   private Object endData = null;
   private TimeIntervalDecorator decorator = null;
   private Date startDate = null;
   private Date endDate = null;
   private String startSubjectNamePattern = null;
   private String endSubjectNamePattern = null;
}
