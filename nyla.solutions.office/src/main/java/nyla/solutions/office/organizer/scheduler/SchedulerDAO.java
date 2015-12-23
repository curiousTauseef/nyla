package nyla.solutions.office.organizer.scheduler;

import java.util.Collection;
import java.util.Date;


import nyla.solutions.global.data.Event;
import nyla.solutions.global.data.Identifier;
import nyla.solutions.global.exception.DuplicateRowException;
import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.patterns.Disposable;

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
