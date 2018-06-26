package nyla.solutions.core.patterns.iteration;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Test;

public class PagingCollectionTest
{

	@Test
	public void testEqualsObject()
	{
		
		PagingCollection<String> collection1 = new PagingCollection<>(Collections.singleton("hello"), new PageCriteria());
		PagingCollection<String> collection2 = new PagingCollection<>(Collections.singleton("hello"), new PageCriteria());
		assertEquals(collection1, collection2);
		
		collection2 = new PagingCollection<>(Collections.singleton("world"), new PageCriteria());
		assertNotEquals(collection1, collection2);
	}

}
