package nyla.solutions.global.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import nyla.solutions.global.util.BeanComparator;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.JavaBean;

/**
 * <pre>
 * 
 * 
 * <b>BeanComparator </b> is a generic Bean property java.util.Comparator implementation. 
 * It compares specified property beans using reflection. This object is internally used
 * by the Organizer.sortByJavaBeanProperty(String,Collection) method.
 * 
 * <b>Example code</b>
 * 
	<span style="color:blue">//The constructor accepts a JavaBean property name</span>
	BeanComparator beanComparator = new BeanComparator("firstName");
		
	<span style="color:blue">//The following are two sample user profile beans</span>
	UserProfile josiah = new UserProfile();
	josiah.setFirstName("Josiah");
		
	UserProfile nyla = new UserProfile();
	nyla.setFirstName("Nyla");
		 
	<span style="color:blue">//Reflection is used to compare the properties of the beans</span>
	Assert.assertTrue(beanComparator.compare(josiah, nyla) < 0);
		 
	<span style="color:blue">//The following shows how the BeanComparator.sort method can be used
	//This method can be used to sort an given collection based on the JavaBean properties 
	 //of objects in the collection</span>
	ArrayList<UserProfile> unSorted = new ArrayList<UserProfile>();
	unSorted.add(0, nyla);
	unSorted.add(1, josiah);
		 
	<span style="color:blue">//Setting the descending will determine the sort order</span>
	beanComparator.setDescending(true);
	beanComparator.sort(unSorted);

	Assert.assertTrue(unSorted.get(0) == nyla);
		 
	<span style="color:blue">//Changing the descending flag changes the output of the sort method</span>
	beanComparator.setDescending(false);
	beanComparator.sort(unSorted);
	Assert.assertTrue(unSorted.get(0) == josiah);
 * 
 * </pre>
 * 
 * @author Gregory Green
 * 
 * @version 1.0
 * 
 */


public class BeanComparator implements Comparator<Object>, Serializable

{
	/**
	 * 
	 * @param aPropertyName property name format (nested property use . operator
	 *            i.e. bean.child.value)
	 * 
	 */

	public BeanComparator(String aPropertyName, boolean aDescending)
	{

		this.setPropertyName(aPropertyName);
		this.descending = aDescending;

	} // -----------------------------------------
	
	
	/**
	 * @return the descending
	 */
	public boolean isDescending()
	{
		return descending;
	}


	/**
	 * @param descending the descending to set
	 */
	public final void setDescending(boolean descending)
	{
		this.descending = descending;
	}


	/**
	 * 
	 * @param aPropertyName property name format (nested property use . operator
	 *            i.e. bean.child.value)
	 * 
	 */

	public BeanComparator(String aPropertyName)

	{

		this(aPropertyName, false);

	} // -----------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */

	@SuppressWarnings("unchecked")
	public int compare(Object aBean1, Object aBean2)

	{

		if (this.propertyName == null || this.propertyName.length() == 0)

		{

			Debugger.printWarn(this,
					"Bean property not set, the list will not be sorted");

			return 0;

		}

		try

		{

			Comparable<Object> comparable1 =

			(Comparable<Object>) JavaBean.getProperty(aBean1, propertyName);

			Comparable<Object> comparable2 =

			(Comparable<Object>) JavaBean.getProperty(aBean2, propertyName);

			return comparable1.compareTo(comparable2);

		}

		catch (Exception e)

		{

			throw new RuntimeException(Debugger.stackTrace(e));

		}

	} // ---------------------------------------

	/**
	 * 
	 * @return property name format (nested property use . operator i.e.
	 *         bean.child.value)
	 * 
	 */

	public String getPropertyName()

	{

		return propertyName;

	} // -----------------------------------------

	/**
	 * 
	 * @param property name format (nested property use . operator i.e.
	 *            bean.child.value)
	 * 
	 */

	public void setPropertyName(String aProperyName)

	{

		propertyName = aProperyName;

	} // -----------------------------------------

	/**
	 * 
	 * 
	 * 
	 * @param aCollection the collection to sort
	 * 
	 * @return the sorted list
	 * 
	 */

	public List<?> sort(Collection<?> aCollection)

	{

		List<?> list = null;

		// convert to list

		if (aCollection instanceof List)

		{

			list = (List<?>) aCollection;

		}

		else

		{

			list = new ArrayList<Object>(aCollection);

		}

		if (this.descending)
		{
			Collections.sort(list, Collections.reverseOrder());
		}
		else
		{
			Collections.sort(list, this);
		}

		return list;

	}// -----------------------------------------

	private boolean descending = false;
	private String propertyName = "";
	static final long serialVersionUID = BeanComparator.class.getName()
			.hashCode();

}
