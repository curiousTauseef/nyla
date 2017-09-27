package nyla.solutions.core.data.conversation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


/**
 * Wrapper for ArrayList object to support serialization
 * @author Gregory Green
 *
 */
public class ArrayListBag implements Serializable, BaggedObject<ArrayList<Object>>
{
	public  ArrayListBag()
	{
	}

	public  ArrayListBag(ArrayList<Object> list)
	{
		bag(list);
	}// ------------------------a--------------------------------



	@SuppressWarnings("unchecked")
	@Override
	public void bag(ArrayList<Object> unBaggedObject)
	{
		if(unBaggedObject == null || unBaggedObject.isEmpty())
			return;
		
		
		arrayObj = new Object[unBaggedObject.size()];

		Object obj = null;
		
		for (int i=0; i < arrayObj.length; i ++)
		{
			obj = unBaggedObject.get(i);
			
			if(obj instanceof Date)
				obj = new DateBag((Date)obj);
			else if(obj instanceof ArrayList)
				obj = new ArrayListBag((ArrayList<Object>)obj);
			
			arrayObj[i] = obj;
		}
		
		
		
	}// --------------------------------------------------------
	

	/**
	 * @return the arrayObj
	 */
	public Object[] getArrayObj()
	{
		if(arrayObj == null)
			return null;
		
		Object[] copy = new Object[this.arrayObj.length];
		
		System.arraycopy(this.arrayObj, 0, copy, 0, copy.length);
		return copy;
				
	}

	/**
	 * @param arrayObj the arrayObj to set
	 */
	public void setArrayObj(Object[] arrayObj)
	{
		if(arrayObj == null)
			return;
		
		this.arrayObj = new Object[arrayObj.length]; 
		
		System.arraycopy(arrayObj, 0, this.arrayObj, 0, this.arrayObj.length);
	}// --------------------------------------------------------
	/*
	 * Creates arraylist from the wrapped object array 
	 */
	@SuppressWarnings({ "rawtypes"})
	@Override
	public ArrayList<Object> unbag()
	{
		if(arrayObj == null || arrayObj.length == 0)
			return null;
		
		ArrayList<Object> list = new ArrayList<Object>(this.arrayObj.length);
		
		Object value = null;
		for (int i = 0; i < arrayObj.length; i++)
		{
			value = arrayObj[i];
			
			if(value instanceof BaggedObject)
				value = ((BaggedObject)value).unbag();
			
			list.add(value);
		}
		return list;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4995496063381565327L;
	private Object[] arrayObj;
}
