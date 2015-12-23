package nyla.solutions.global.data;

import java.util.Calendar;
import java.util.Date;

import nyla.solutions.global.data.Day;
import nyla.solutions.global.data.Time;

/**
 * A class representing a moment in time. Extends Day which represents
 * the day of the moment, and defines the time within the day to
 * millisecond accuracy.
 * 
 */   
public class Time extends Day
{
  /**
	 * 
	 */
	private static final long serialVersionUID = -7760994938523990073L;



/**
   * Instantiate a Time object.
   * The time is lenient meaning that illegal day parameters can be
   * specified and results in a recomputed day with legal month/day
   * values.
   * 
   * @param year        Year of this time
   * @param month       Month of this time
   * @param dayOfMonth  Day of month of this time.
   * @param hours       Hours of this time [0-23]
   * @param minutes     Minutes of this time [0-23]
   * @param seconds     Seconds of this time [0-23]      
   */
  public Time (int year, int month, int dayOfMonth,
               int hours, int minutes, int seconds)
  {
    super (year, month, dayOfMonth);
    setHour24 (hours);
    setMinutes (minutes);
    setSeconds (seconds);
  }
    
  public Time(Date date)
  {     
     this.assignDate(date);
  }// --------------------------------------------

  public void assignDate(Date date)
  {
     if(date == null)
        return;
     
     this.setDay(new Day(date));
     
     Calendar cal = Calendar.getInstance();
     cal.setTime(date);
     
     
     setHour24 (cal.get(Calendar.HOUR_OF_DAY));
     setMinutes (cal.get(Calendar.MINUTE));
     setSeconds (cal.get(Calendar.SECOND));
  }// --------------------------------------------


  public Time (Day day, int hours, int minutes, int seconds)
  {
    this (day.getYear(), day.getMonth(), day.getDayOfMonth(),
          hours, minutes, seconds);
  }
    
    
  public Time (int hours, int minutes, int seconds)
  {
    this (new Day(), hours, minutes, seconds);
  }


  public Time()
  {
    //calendar_ = new GregorianCalendar(); // Now
  }

  /**
   * 
   * @param day
   */
  public void setDay (Day day)
  {
     super.initialize(day.getYear(), day.getMonth(), day.getDayOfMonth());
  }// --------------------------------------------

  public void setHour24 (int hours)
  {
    calendar_.set (Calendar.HOUR_OF_DAY, hours);
  }  

  public int getHour24 ()
  {
    return calendar_.get (Calendar.HOUR_OF_DAY);
  }// --------------------------------------------

  public int getHour()
  {
     return calendar_.get(Calendar.HOUR);
  }// --------------------------------------------
  /**
   * 
   * @return AM or PM
   */
  public String getAmOrPm()
  {
     if( Calendar.AM == calendar_.get(Calendar.AM_PM))
     {
        return "AM";
     }
     else
     {
        return "PM";
     }
  }// --------------------------------------------

  public void setMinutes (int minutes)
  {
    calendar_.set (Calendar.MINUTE, minutes);
  }


  public int getMinutes()
  {
    return calendar_.get (Calendar.MINUTE);
  }

    
  public void setSeconds (int seconds)
  {
    calendar_.set (Calendar.SECOND, seconds);
  }


  public int getSeconds()
  {
    return calendar_.get (Calendar.SECOND);
  }

    

  public void setMilliSeconds (int milliSeconds)
  {
    calendar_.set (Calendar.MILLISECOND, milliSeconds);
  }


    
  public int getMilliSeconds()
  {
    return calendar_.get (Calendar.MILLISECOND);
  }


    
  public boolean isAfter (Time time)
  {
    return calendar_.after (time.calendar_);
  }

    

  public boolean isBefore (Time time)
  {
    return calendar_.before (time.calendar_);
  }


    
  public boolean equals (Object obj)
  {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
	  
	  Time time = (Time)obj;
	  
    return this.calendar_.equals (time.calendar_);
  }


  public void addHours (int nHours)
  {
    calendar_.add (Calendar.HOUR_OF_DAY, nHours);
  }
    

  public void addMinutes (int nMinutes)
  {
    calendar_.add (Calendar.MINUTE, nMinutes);
  }
    
  public void addSeconds (int nSeconds)
  {
    calendar_.add (Calendar.SECOND, nSeconds);
  }


  public void addMilliSeconds (int nMilliSeconds)
  {
    calendar_.add (Calendar.MILLISECOND, nMilliSeconds);
  }


  public long milliSecondsBetween (Time time)
  {
    long millisBetween = calendar_.getTime().getTime() -
                         time.calendar_.getTime().getTime();
    return millisBetween;
  }


  public double secondsBetween (Time time)
  {
    long millisBetween = calendar_.getTime().getTime() -
                         time.calendar_.getTime().getTime();
    return millisBetween / 1000.00;
  }


    
  public double minutesBetween (Time time)
  {
    long millisBetween = calendar_.getTime().getTime() -
                         time.calendar_.getTime().getTime();
    return millisBetween / (1000 * 60.00);
  }


  public double hoursBetween (Time time)
  {
    long millisBetween = calendar_.getTime().getTime() -
                         time.calendar_.getTime().getTime();
    return millisBetween / (1000 * 60 * 60.00);
  }
    


  public String toString()
  {
    StringBuffer string = new StringBuffer();        
    string.append (super.toString());
    string.append (' ');
    if (getHour24() < 10) string.append ('0');
    string.append (getHour24());
    string.append (':');
    if (getMinutes() < 10) string.append ('0');
    string.append (getMinutes());
    string.append (':');
    if (getSeconds() < 10) string.append ('0');
    string.append (getSeconds());
    string.append (',');        
    string.append (getMilliSeconds());        
        
    return string.toString();
  }


    
  public static void main (String args[])
  {
    Time time = new Time (12,00,00);
    System.out.println (time);
  }
}


