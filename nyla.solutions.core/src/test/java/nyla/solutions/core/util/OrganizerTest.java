package nyla.solutions.core.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.junit.Test;

import nyla.solutions.core.data.MapEntry;

public class OrganizerTest
{
		@Test
		public void testToPages()
		{
			assertNull(Organizer.toPages(null,0));
			assertNull(Organizer.toPages(new ArrayList<String>(),0));
		
			assertNotNull(Organizer.toPages(Collections.singleton(null),1));
			
			assertEquals(2,Organizer.toPages(Arrays.asList(1,1,1,1), 2).size());
			
			assertEquals(3,Organizer.toPages(Arrays.asList(1,1,1,1,3), 2).size());
			
			List<Collection<Integer>> list = Organizer.toPages(Arrays.asList(1,1,1,1,3),2);

			assertEquals(Integer.valueOf(3), list.get(2).iterator().next());
		}

		@Test
		public void testToKeyPages()
		{
			assertNull(Organizer.toKeyPages(null,0));
			assertNull(Organizer.toKeyPages(new ArrayList<Map.Entry<Object, Object>>(),0));
		
			assertNull(Organizer.toKeyPages(Collections.singleton(null),0));
			
			assertEquals(2,Organizer.toKeyPages(Arrays.asList(
			new MapEntry<Integer, Integer>(Integer.valueOf(1), Integer.valueOf(1)),
			new MapEntry<Integer, Integer>(Integer.valueOf(2), Integer.valueOf(2)),
			new MapEntry<Integer, Integer>(Integer.valueOf(3), Integer.valueOf(3)),
			new MapEntry<Integer, Integer>(Integer.valueOf(4), Integer.valueOf(4))
			), 2).size());
			
			assertEquals(3,Organizer.toKeyPages(Arrays.asList(
			new MapEntry<Integer, Integer>(Integer.valueOf(1), Integer.valueOf(1)),
			new MapEntry<Integer, Integer>(Integer.valueOf(2), Integer.valueOf(2)),
			new MapEntry<Integer, Integer>(Integer.valueOf(3), Integer.valueOf(3)),
			new MapEntry<Integer, Integer>(Integer.valueOf(4), Integer.valueOf(4)),
			new MapEntry<Integer, Integer>(Integer.valueOf(5), Integer.valueOf(5))
			), 2).size());
			
					
			TreeSet<Map.Entry<Integer, Integer>> set = new TreeSet<Map.Entry<Integer, Integer>>(new BeanComparator("value"));
			
			set.addAll(Arrays.asList(
			new MapEntry<Integer, Integer>(Integer.valueOf(1), Integer.valueOf(125)),
			new MapEntry<Integer, Integer>(Integer.valueOf(2), Integer.valueOf(122)),
			new MapEntry<Integer, Integer>(Integer.valueOf(3), Integer.valueOf(123)),
			new MapEntry<Integer, Integer>(Integer.valueOf(4), Integer.valueOf(124)),
			new MapEntry<Integer, Integer>(Integer.valueOf(5), Integer.valueOf(121))
			));
			
			List<Collection<Integer>> list = Organizer.toKeyPages(set, 2);

			assertEquals(Integer.valueOf(1), list.get(2).iterator().next());
		}
}