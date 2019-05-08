package nyla.solutions.core.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class PropertyTest
{

	@Test
	public void testCompareTo()
	{
		assertTrue(new Property("test0","test0").compareTo(new Property("test0","test0")) == 0);
		
		int compare = new Property("test0","test0").compareTo(new Property("test1","test0")) ;
		assertTrue(compare+" ",compare < 0);
		
		compare = new Property("test1","test0").compareTo(new Property("test0","test0")) ;
		assertTrue(compare+" ",compare > 0);
		
		compare = new Property("test0","test0").compareTo(new Property("test0","test1")) ;
		
		assertTrue(compare+" ",compare < 0);
		
		compare = new Property("test0","test1").compareTo(new Property("test0","test0")) ;
		
		assertTrue(compare+" ",compare > 0);
	}

}
