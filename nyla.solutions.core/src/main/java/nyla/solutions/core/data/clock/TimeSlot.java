package nyla.solutions.core.data.clock;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import nyla.solutions.core.exception.RequiredException;
import nyla.solutions.core.util.Scheduler;

/**
 * 
 * <b>TimeSlot</b> maintains the start and end time
 * 
 * @author Gregory Green
 *
 */
public class TimeSlot implements Serializable, Comparable<Object>, TimeInterval
{
	/**
	* 
	*/
	private static final long serialVersionUID = -6155840093186057564L;

	/**
	 * 
	 * Constructor for TimeSlot initializes internal
	 */
	public TimeSlot()
	{
	}// --------------------------------------------

	/**
	 * 
	 * Constructor for TimeSlot initializes internal
	 * 
	 * @param start
	 *            the start time
	 * @param end
	 *            the end time
	 */
	public TimeSlot(Date start, Date end)
	{
		setStartDate(start);
		setEndDate(end);
	}// --------------------------------------------

	/**
	 * 
	 * @return the duration in hours
	 */
	public double getDurationhours()
	{
		check();

		return Scheduler.durationHours(start, end);
	}// --------------------------------------------

	/**
	 * 
	 * @param intervalSeconds
	 *            the time slot durations
	 * @param cutOffTime
	 *            the cut off time
	 * @return the next time slot instance
	 */
	public TimeSlot nextTimeSlot(int intervalSeconds, Time cutOffTime)
	{
		Date newStart = (Date) this.end.clone();

		Calendar newEnd = Calendar.getInstance();
		newEnd.setTime(newStart);
		newEnd.add(Calendar.SECOND, intervalSeconds);

		Date newEndDate = newEnd.getTime();

		TimeSlot newTimeSlot = new TimeSlot(newStart, newEndDate);

		if (cutOffTime == null || cutOffTime.getDate() == null)
			return newTimeSlot;

		// check cut off time
		if (newEndDate.after(cutOffTime.getDate()))
		{
			return null;
		}

		if (newTimeSlot.getEndDate().after(cutOffTime.getDate()))
			return null;

		return newTimeSlot;
	}// --------------------------------------------

	/**
	 * 
	 * @return the duration in minutes
	 */
	public double getDurationMinutes()
	{
		check();

		return Scheduler.durationMinutes(start, end);
	}// --------------------------------------------

	/**
	 * 
	 * @return the duration in seconds
	 */
	public double getDurationSeconds()
	{
		check();

		return Scheduler.durationSeconds(start, end);
	}// --------------------------------------------

	/**
	 * Check that start is after end
	 */
	private void check()
	{
		if (start == null)
			return;

		if (end == null)
			return;

		if (start.after(end))
		{
			throw new IllegalStateException("Start " + start + "must be before end " + end);
		}
	}// --------------------------------------------

	/**
	 *
	 * @see nyla.solutions.core.data.clock.TimeInterval#getStartDate()
	 */
	public Date getStartDate()
	{
		if (start == null)
			return null;

		return new Date(start.getTime());
	}// --------------------------------------------

	/**
	 * @param start the start date
	 */
	public void setStartDate(Date start)
	{
		if (start == null)
			this.start = null;
		else
			this.start = new Date(start.getTime());

		check();
	}

	/**
	 *
	 * @return the end date
	 */
	public Date getEndDate()
	{
		if (end == null)
			return null;

		return (Date) end.clone();
	}// --------------------------------------------

	/**
	 * 
	 * @return the end time
	 */
	public Time getEndTime()
	{
		return new Time(end);
	}// --------------------------------------------

	public Time getStartTime()
	{
		return new Time(start);
	}// --------------------------------------------

	/**
	 * @param end the end date
	 */
	public void setEndDate(Date end)
	{
		if (end == null)
			this.end = null;
		else
			this.end = new Date(end.getTime());

		check();
	}// --------------------------------------------

	/**
	 * 
	 * @param day the day/time
	 * @param start the start time
	 * @param intervalSeconds  time slot duration in seconds
	 * @return the first instance of a timeslot
	 */
	public static TimeSlot firstSlot(Day day, Time start, int intervalSeconds)
	{
		if (day == null)
			throw new RequiredException("date in TimeSlot.firstSlot");

		Date startDate = Scheduler.toDate(day, start);

		java.util.Calendar end = java.util.Calendar.getInstance();

		end.setTime(startDate);

		// add seconds
		end.add(Calendar.SECOND, intervalSeconds);

		return new TimeSlot(startDate, end.getTime());
	}// --------------------------------------------

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
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
		TimeSlot other = (TimeSlot) obj;
		if (end == null)
		{
			if (other.end != null)
				return false;
		}
		else if (!end.equals(other.end))
			return false;
		if (start == null)
		{
			if (other.start != null)
				return false;
		}
		else if (!start.equals(other.start))
			return false;
		return true;
	}// ------------------------------------------------

	public int compareTo(Object object)
	{
		TimeSlot other = (TimeSlot) object;

		if (this.equals(other))
			return 0;

		if (this.start.before(other.start))
		{
			return -1;
		}
		else
		{
			// start is after
			return 1;
		}
	}// --------------------------------------------

	private Date start = null;
	private Date end = null;

}
