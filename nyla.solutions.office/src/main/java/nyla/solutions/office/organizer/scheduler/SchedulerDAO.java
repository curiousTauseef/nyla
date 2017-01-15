package nyla.solutions.office.organizer.scheduler;

import java.util.Collection;
import java.util.Date;

import nyla.solutions.core.data.Event;
import nyla.solutions.core.data.Identifier;
import nyla.solutions.core.exception.DuplicateRowException;
import nyla.solutions.core.exception.NoDataFoundException;
import nyla.solutions.core.patterns.Disposable;



/**
 * Data access interface schedule
 * @author Gregory Green
 *
 */
public interface SchedulerDAO extends Disposable
{
   Collection<Event> selectEvents(Identifier owner, Date date)
   throws NoDataFoundException;
   
   void insertEvent(Identifier owner, Event event)
   throws DuplicateRowException;
   
   void deleteEvent(Identifier owner, Event event)
   throws NoDataFoundException;
   
}
