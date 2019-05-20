package nyla.solutions.core.patterns.cache;

import static org.junit.Assert.*;

import org.junit.Test;

public class CacheFarmTest
{

	@Test
	public void testGet_Expire()
	{
		Cache<String, String> cache = CacheFarm.getCache();
		
		cache.put("test","hello");
		assertEquals("hello",cache.get("test"));
	}

}
