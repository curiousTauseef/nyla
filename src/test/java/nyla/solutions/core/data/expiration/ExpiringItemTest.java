package nyla.solutions.core.data.expiration;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

public class ExpiringItemTest
{

	@Test
	public void test_expiration()
	throws Exception
	{
		ExpiringItem<String> e = new ExpiringItem<>("hello",
			LocalDateTime.now().plusSeconds(1));
		
		assertEquals("hello",e.value());
		
		Thread.sleep(1050);
		
		assertNull(e.value());
		
	}

}
