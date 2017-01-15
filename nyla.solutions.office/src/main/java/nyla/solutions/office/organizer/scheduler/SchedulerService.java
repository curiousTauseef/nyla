package nyla.solutions.office.organizer.scheduler;

import java.util.Date;

import nyla.solutions.core.data.Event;
import nyla.solutions.core.data.TimeInterval;
import nyla.solutions.core.security.user.data.User;


public interface SchedulerService
{
   /**
    * 
    * @param user the users' schedule
    * @param time the day request
    * @return the list of time slots
    */
   public TimeInterval[] listAvailableSlots(User user, Date time);
   
   /**
    * 
    * @param user
    * @param time
    * @return
    */
   public Event[] listEvents(User user, Date time);
   
   /**
    * 
    * @param user
    * @param event
    * @return
    */
   public Event saveEvent(User user, Event event);
}
