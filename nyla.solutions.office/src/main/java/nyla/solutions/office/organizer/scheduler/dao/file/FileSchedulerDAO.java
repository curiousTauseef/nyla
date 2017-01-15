package nyla.solutions.office.organizer.scheduler.dao.file;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.TreeSet;

import nyla.solutions.core.data.Event;
import nyla.solutions.core.data.Identifier;
import nyla.solutions.core.exception.DuplicateRowException;
import nyla.solutions.core.exception.NoDataFoundException;
import nyla.solutions.core.exception.RequiredException;
import nyla.solutions.core.exception.SystemException;
import nyla.solutions.core.util.Config;
import nyla.solutions.core.util.Debugger;
import nyla.solutions.core.util.Text;
import nyla.solutions.office.organizer.scheduler.SchedulerDAO;
import nyla.solutions.xml.XML;
import nyla.solutions.xml.XMLInterpreter;

/**
 * 
 * @author Gregory Green
 *
 */
public class FileSchedulerDAO implements SchedulerDAO
{  
	/**
	 * 
	 * @see nyla.solutions.office.organizer.scheduler.SchedulerDAO#selectEvents(
	 * solutions.global.data.Identifier, java.util.Date)
	 */
   @SuppressWarnings("unchecked")
   public Collection<Event> selectEvents(Identifier owner, Date date)
   throws NoDataFoundException
   {
      
      
      //read time slots
      
      File file = this.getFile(owner, date);
      
      if(!file.exists())
      {
         throw new NoDataFoundException("File not found "+file.getAbsolutePath());
      }
      
      try
      {         
         return (Collection<Event>) xml.toObject(file);
      }
      catch (Exception e)
      {
         throw new SystemException("identifier="+owner+" date="+date+" error="+Debugger.stackTrace(e));
      }
   }// --------------------------------------------

   private File getFile(Identifier owner, Date date)
   {
      if(owner == null)
         throw new RequiredException("owner in FileSchedulerDAO.getFile");
      
      String dateText = Text.formatDate(dateFormat, date);
      
      String location = rootDirectory+"/events/"+owner.getId()+"/"+dateText+extension;
      
      Debugger.println(this, "location="+location);
      return new File(location);
   }// --------------------------------------------

   
   public void dispose()
   { 
   }// --------------------------------------------
   /**
    * 
    * @see nyla.solutions.office.organizer.scheduler.SchedulerDAO#deleteEvent(
    * solutions.global.data.Identifier, solutions.global.data.Event)
    */
   public void deleteEvent(Identifier owner, Event event)
   throws NoDataFoundException
   {
      Collection<Event> events = this.selectEvents(owner, event.getTimeSlot().getStartDate());
      
      events.remove(event);
      
      try
      {
         xml.toXMLFile(events, this.getFile(owner, event.getTimeSlot().getStartDate()));
      }
      catch (IOException e)
      {
         throw new SystemException(Debugger.stackTrace(e));
      }
      
   }// --------------------------------------------
   /**
    * 
    * @see nyla.solutions.office.organizer.scheduler.SchedulerDAO#insertEvent(
    * solutions.global.data.Identifier, solutions.global.data.Event)
    */
   public void insertEvent(Identifier owner, Event event) 
   throws DuplicateRowException
   {
      Collection<Event> events = null;
      try
      {
         try
         {
            events = this.selectEvents(owner, event.getTimeSlot()
            .getStartDate());
            
            events.add(event);
         }
         catch (NoDataFoundException e)
         {
            Debugger.println(this, "Creating new evnets for owner "+owner);
            events = new TreeSet<Event>();
            
         }
         
         events.add(event);
         
         xml.toXMLFile(events, this.getFile(owner, event.getTimeSlot().getStartDate()));
      }
      catch (IOException e)
      {
         throw new SystemException(e.getMessage(),e);
      }
   }// --------------------------------------------

   private XMLInterpreter xml = XML.getInterpreter();
   private String dateFormat = "MM-dd-yyyy";
   private String rootDirectory = Config.getProperty(FileSchedulerDAO.class.getName()+".rootDirectory");
   private String extension = ".xml";
}
