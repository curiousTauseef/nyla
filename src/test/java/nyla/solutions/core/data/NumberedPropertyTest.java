package nyla.solutions.core.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class NumberedPropertyTest
{

	@Test
	public void testNumberedPropertyStringInt()
	{
		NumberedProperty np = new NumberedProperty("test",1);
		assertEquals(np.getNumber(), 1);
		
	}

}
