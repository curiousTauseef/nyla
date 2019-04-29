package nyla.solutions.core.util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
   private static final long ZERO = 0;
   private Timer timer = new Timer();
	   
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
    * @return the new date time
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
   public static long durationMS(LocalDateTime start,LocalDateTime end)
   {
      if(start == null || end == null)
      {
         return 0;
      }
      
      return Duration.between(start, end).toMillis();
   }//--------------------------------------------
   /**
    * 1 millisecond = 0.001 seconds
    * @param start between time
    * @param end finish time
    * @return duration in seconds
    */
   public static double durationSeconds(LocalDateTime start, LocalDateTime end)
   {
	   return Duration.between(start, end).getSeconds();
   }//--------------------------------------------
   /**
    * 1 seconds = 1/60 minutes
    * @param start between time
    * @param end finish time
    * @return duration in minutes
    */
   public static double durationMinutes(LocalDateTime start, LocalDateTime end)
   {
	   
      return Duration.between(start, end).toMinutes();
   }//--------------------------------------------
   /**
    * 1 Hours = 60 minutes
    * @param start between time
    * @param end finish time
    * @return duration in hours
    */
   public static long durationHours(LocalDateTime start,LocalDateTime end)
   {
	   if(start == null || end == null)
		   return ZERO;
	   
      return Duration.between(start, end).toHours();
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
   public static LocalDateTime toDate(Day day, Time time)
   {

      time.assignDate(LocalDateTime.of(day.getLocalDate(),
      time.getLocalDateTime().toLocalTime()));
      
      return time.getLocalDateTime();
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
    * @return purge schedule
    * @see java.util.Timer#purge()
    */
   public int purgeSchedules()
   {
	return timer.purge();
   }// ----------------------------------------------
   /**
    * Convert timer task to a runnable
    * @param runnable
    * @return timer task for the runnable
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
	public static boolean isDateOrTime(Class<?> clz)
	{
		if(clz == null)
			return false;
		String className = clz.getName();
		
	
		return className.matches("(java.time.Local*|java.util.Date|java.sql.(Date|Time|DateTime))");
	}//------------------------------------------------
	public static LocalDateTime yesterday()
	{
		return LocalDateTime.now().minusDays(1);
	}//------------------------------------------------
	public static LocalDateTime toLocalDateTime(Date date)
	{
		if(date == null)
			return null;
		
		return date.toInstant().atZone(ZoneId.systemDefault())
		.toLocalDateTime();
	}//------------------------------------------------
	public static Date toDate(LocalDate date)
	{
		if(date == null)
			return null;
		
		return  java.util.Date
	      .from(date.atStartOfDay(ZoneId.systemDefault())
	      .toInstant());
	}
	public static Date toDate(LocalDateTime date)
	{
		if(date == null)
			return null;
		
		return  java.util.Date
	      .from(date.atZone(ZoneId.systemDefault())
	      .toInstant());
	}//------------------------------------------------
	public static LocalDate toLocalDate(Date time)
	{
		return time.toInstant().atZone(ZoneId.systemDefault())
		.toLocalDate();
	}//------------------------------------------------
	public static LocalDateTime tomorrow()
	{
		return LocalDateTime.now().plusDays(1);
	}
   
}
