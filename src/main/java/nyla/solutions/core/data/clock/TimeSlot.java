package nyla.solutions.core.data.clock;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

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
	private  Optional<LocalDateTime> start;
	private  Optional<LocalDateTime> end;

	
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
		this.start = Optional.empty();
		this.end = Optional.empty();
	}// --------------------------------------------

	public TimeSlot(LocalDateTime start, LocalDateTime end)
	{
		this.start = Optional.of(start);
		this.end = Optional.of(end);
	}
	
	/**
	 * 
	 * @return the duration in hours
	 */
	public long getDurationhours()
	{
		return Scheduler.durationHours(start.orElse(null), end.orElse(null));
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
		//Date newStart = (Date) this.end.clone();

		LocalDateTime newEnd = this.end.get().plusSeconds(intervalSeconds);
		//newEnd.add(Calendar.SECOND, intervalSeconds);

		//Date newEndDate = newEnd.getTime();

		TimeSlot newTimeSlot = new TimeSlot(this.end.get(), newEnd);

		if (cutOffTime == null)
			return newTimeSlot;

		// check cut off time
		if (newEnd.isAfter(cutOffTime.getLocalDateTime()))
		{
			return null;
		}

		if (newTimeSlot.getEndDate().isAfter(cutOffTime.getLocalDateTime()))
			return null;

		return newTimeSlot;
	}// --------------------------------------------

	/**
	 * 
	 * @return the duration in minutes
	 */
	public double getDurationMinutes()
	{
		return Scheduler.durationMinutes(start.get(), end.get());
	}// --------------------------------------------

	/**
	 * 
	 * @return the duration in seconds
	 */
	public double getDurationSeconds()
	{
		return Scheduler.durationSeconds(start.get(), end.get());
	}// --------------------------------------------
	/**
	 *
	 * @see nyla.solutions.core.data.clock.TimeInterval#getStartDate()
	 */
	public LocalDateTime getStartDate()
	{

		return start.orElseGet(null);
	}// --------------------------------------------

	/**
	 *
	 * @return the end date
	 */
	public LocalDateTime getEndDate()
	{
		if (end == null)
			return null;

		return end.get();
	}// --------------------------------------------

	/**
	 * 
	 * @return the end time
	 */
	public Time getEndTime()
	{
		return new Time(end.get());
	}// --------------------------------------------

	public Time getStartTime()
	{
		return new Time(this.start.get());
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

		LocalDateTime startDate = Scheduler.toDate(day, start);

		LocalDateTime end = startDate.plusSeconds(intervalSeconds);


		return new TimeSlot(startDate, end);
	}// --------------------------------------------

	/* (non-Javadoc)
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

	/* (non-Javadoc)
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
	}

	public int compareTo(Object object)
	{
		TimeSlot other = (TimeSlot) object;

		if (this.equals(other))
			return 0;

		if(!this.start.isPresent())
			return -1;
		else if(!other.start.isPresent())
			return 1;
		
		if (this.start.get().isBefore(other.start.get()))
		{
			return -1;
		}
		else
		{
			// start is after
			return 1;
		}
	}// --------------------------------------------


	@Override
	public void setStartDate(LocalDateTime start)
	{
		this.start = Optional.of(start);
		
	}

	@Override
	public void setEndDate(LocalDateTime end)
	{
		this.end = Optional.of(end);
		
	}

}
