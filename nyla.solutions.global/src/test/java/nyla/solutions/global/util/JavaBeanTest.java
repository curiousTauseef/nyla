package nyla.solutions.global.util;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import nyla.solutions.global.data.Named;

public class JavaBeanTest
{

	@Before
	public void setUp() throws Exception
	{
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testNested()
	throws Exception
	{
		Named nyla = new Named();
		
		nyla.setName("Nyla Gianni");
		
		Named josiah = new Named();
		
		josiah.setName("Josiah Gregory");
	
		ArrayList<Named> list = new ArrayList<Named>();
		list.add(nyla);
		list.add(josiah);
		
		Object results = JavaBean.getNestedProperty(list, "name");
		
		
		Assert.assertTrue(results instanceof Collection<?>);
		
		Collection<String> collection = (Collection<String>)results;
		
		for (String string : collection)
		{
			System.out.println(string);
		}
		
		Assert.assertEquals(null,JavaBean.getNestedProperty(null, "name"));
		
		ComplexObject complexObject = new ComplexObject();
		
		complexObject.setName(list);
		complexObject.setState("NJ");
		
	    collection = (Collection<String>)JavaBean.getNestedProperty(complexObject, "name.name");
		
		for (String string : collection)
		{
			System.out.println(string);
		}
		complexObject.setName(null);

		Assert.assertNull(JavaBean.getNestedProperty(complexObject, "name.name"));
		
	}
	
	public static class ComplexObject
	{
		
		
		/**
		 * @return the state
		 */
		public String getState()
		{
			return state;
		}
		/**
		 * @param state the state to set
		 */
		public void setState(String state)
		{
			this.state = state;
		}

		/**
		 * @return the name
		 */
		public Collection<Named> getName()
		{
			return name;
		}
		/**
		 * @param name the name to set
		 */
		public void setName(Collection<Named> name)
		{
			this.name = name;
		}

		private String state;
		private Collection<Named> name;
	}

}
