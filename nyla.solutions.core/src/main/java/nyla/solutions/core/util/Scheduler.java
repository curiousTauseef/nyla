package nyla.solutions.core.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;

import nyla.solutions.core.data.clock.Day;
import nyla.solutions.core.data.clock.Time;
import nyla.solutions.core.data.clock.TimeInterval;
import nyla.solutions.core.data.clock.TimeSlot;




/**
 * <pre>
 * Scheduler provides a set of functions to
 * perform date operations
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class Scheduler
{
   public static final String DATE_FORMAT = "mm/dd/yyyy";
	   
   /**
    * 
    * @param aDate the date
    * @return convert the date to a calendar
    */
   public static Calendar toCalendar(Date aDate)
   {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(aDate);
      return calendar;
   }// --------------------------------------------
   /**
    * Add days of the year (positive or negative),
    * then set day of week
    * 
    * @param days the days to add or subtract
    * @param dayOfWeek the day of week to set
    * @return
    */
   public static Date toDateAddDaysSetOfWeek(int days, int dayOfWeek)
   {
	Calendar cal = Calendar.getInstance();
	
	cal.add(Calendar.DAY_OF_YEAR, days);
	
	
	return cal.getTime();
   }// ----------------------------------------------
   /**
    * cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
    * @param dayOfWeek
    * @return time date
    */
   public static Date toDateDayOfWeek(int dayOfWeek)
   {
	Calendar cal = Calendar.getInstance();
	cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
	
	return cal.getTime();
   }// ----------------------------------------------
   
   /**
    * The time between two dates
    * @param start the begin time
    * @param end the finish time
    * @return duration in milliseconds
    */
   public static long durationMS(Date start,Date end)
   {
      if(start == null || end == null)
      {
         return 0;
      }
      
      return end.getTime() - start.getTime();
   }//--------------------------------------------
   /**
    * 1 millisecond = 0.001 seconds
    * @param start between time
    * @param end finish time
    * @return duration in seconds
    */
   public static double durationSeconds(Date start, Date end)
   {
      return durationMS(start, end) * 0.001;
   }//--------------------------------------------
   /**
    * 1 seconds = 1/60 minutes
    * @param start between time
    * @param end finish time
    * @return duration in minutes
    */
   public static double durationMinutes(Date start, Date end)
   {
      return durationSeconds(start, end)/(60.0);
   }//--------------------------------------------
   /**
    * 1 Hours = 60 minutes
    * @param start between time
    * @param end finish time
    * @return duration in hours
    */
   public static double durationHours(Date start,Date end)
   {
      return durationMinutes(start, end)/(60.0);
   }//--------------------------------------------
   public static Collection<TimeSlot> calculateAvailableSots(Collection<TimeInterval> takenTimeSlots, Date date, int intervalSeconds,Time startTime, Time endTime)
   {
      Day day = new Day(date);
      
      Collection<TimeSlot> allTimeSlots = calculateTimeSots(day,intervalSeconds,startTime, endTime);
      
      if(takenTimeSlots == null || takenTimeSlots.isEmpty())
         return allTimeSlots;
      
      TimeSlot timeSlot = null;
      TreeSet<TimeSlot> availableSlots = new TreeSet<TimeSlot>();
      for (Iterator<TimeSlot> i = allTimeSlots.iterator(); i.hasNext();)
      {
         timeSlot =  i.next();
         
         if(!takenTimeSlots.contains(timeSlot))
         {
            availableSlots.add(timeSlot);
         }         
      }
      return availableSlots;
   }// --------------------------------------------


   public static Collection<TimeSlot> calculateTimeSots( Day day, int intervalSeconds, Time startTime, Time endTime)
   {
      if(intervalSeconds <1)
      {
         throw new IllegalArgumentException("interval seconds cannot be less than one");
      }
      
      int count = 1*60*60*24/intervalSeconds;
      
      ArrayList<TimeSlot> slots = new ArrayList<TimeSlot>(count);   
      
      TimeSlot startSlot = TimeSlot.firstSlot(day,startTime, intervalSeconds);
      TimeSlot slot = startSlot;
      
      while(slot != null)
      {
         slots.add(slot);
         slot = slot.nextTimeSlot(intervalSeconds,endTime);
      }          
      
      return slots;
   }// --------------------------------------------
   public static Date toDate(Day day, Time time)
   {

      time.setDay(day);
      
      return time.getDate();
   }// --------------------------------------------
   /**
    * Schedule runnable to run a given interval
    * @param runnable the runnable to 
    * @param firstTime
    * @param period
    */
   public void scheduleRecurring(Runnable runnable, Date firstTime, long period)
   {
	timer.scheduleAtFixedRate(toTimerTask(runnable), firstTime, period);
	
   }// ----------------------------------------------
   
   /**
    * @return
    * @see java.util.Timer#purge()
    */
   public int purgeSchedules()
   {
	return timer.purge();
   }// ----------------------------------------------
   /**
    * Convert timer task to a runnable
    * @param runnable
    * @return
    */
   public static TimerTask toTimerTask(Runnable runnable)
   {
	if(runnable instanceof TimerTask)
	   return (TimerTask) runnable;
	
	return new TimerTaskRunnerAdapter(runnable);
   }// ----------------------------------------------
   /**
    * Wrapper to create a timer task from a runnable
    * @author Gregory Green
    *
    */
   private static class TimerTaskRunnerAdapter extends TimerTask
   {
	TimerTaskRunnerAdapter(Runnable runnable)
	{
	   this.runnable = runnable;
	   
	}// ----------------------------------------------
	/**
	 * Runs the given runnable
	 */
	public void run()
	{
	   this.runnable.run();
	   
	}
	private Runnable runnable = null;
	
   }// ----------------------------------------------
   //private static ThreadGroup scheduleThreadGroup = new ThreadGroup(Scheduler.class.getName());
   private Timer timer = new Timer();
   
}
