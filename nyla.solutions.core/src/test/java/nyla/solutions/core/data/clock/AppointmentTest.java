package nyla.solutions.core.data.clock;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

import nyla.solutions.core.util.Scheduler;

public class AppointmentTest extends Appointment
{

	@Test
	public void test_twoAppointmentCompare()
	{
		Appointment a1 = new Appointment();
		a1.setTimeSlot(new TimeSlot(LocalDateTime.now(), LocalDateTime.now()));
		
		Appointment a2 = new Appointment();
		a2.setTimeSlot(new TimeSlot(Scheduler.tomorrow(), Scheduler.tomorrow()));
		
		assertTrue(a1.compareTo(a2) < 0);
		
		
	}

}
