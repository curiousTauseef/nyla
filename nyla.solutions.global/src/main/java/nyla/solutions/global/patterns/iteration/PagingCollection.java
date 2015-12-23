package nyla.solutions.global.patterns.iteration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Keep items with a fixed size
 * @author Gregory Green
 *
 * @param <T> the Type of the item in the size
 */
public class PagingCollection<T> implements Paging<T>, Serializable, Collection<T>
{	
	/**
	 * Set the collection
	 * @param collection
	 */
	public PagingCollection(Collection<T> collection, PageCriteria  pageCriteria)
	{
		if(collection == null)
			collection = new ArrayList<T>();
		
		this.collection = collection;
				
		this.pageCriteria = pageCriteria;

		//Set last
		if(pageCriteria == null || pageCriteria.getBeginIndex() <= 1)
			this.first = true;
	}// --------------------------------------------------------

	/**
	 * @return the pageCriteria
	 */
	public PageCriteria getPageCriteria()
	{
		return pageCriteria;
	}
	
	/**
	 * @return the last
	 */
	public boolean isLast()
	{
		return last;
	}

	/**
	 * @param last the last to set
	 */
	public void setLast(boolean last)
	{
		this.last = last;
	}

	/**
	 * @return the first
	 */
	public boolean isFirst()
	{
		return first;
	}

	/**
	 * @param first the first to set
	 */
	public void setFirst(boolean first)
	{
		this.first = first;
	}

	
	/**
	 * @param arg0
	 * @return
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	public boolean add(T value)
	{
		if(this.pageCriteria != null && 
		   this.size() > this.pageCriteria.getSize())
		{
			this.last = false;
			return false; // do not add
			
		}
		
		return collection.add(value);
	}// --------------------------------------------------------
	/**
	 * @param arg0
	 * @return
	 * @see java.util.Collection#addAll(java.util.Collection)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean addAll(Collection<? extends T> values)
	{
		boolean wasAllAdded = true;
		for (Iterator iterator = values.iterator(); iterator.hasNext();)
		{
			if(!add((T)iterator.next()))
				wasAllAdded = false;
			
		}
		
		return wasAllAdded;
	}

	/**
	 * 
	 * @see java.util.Collection#clear()
	 */
	public void clear()
	{
		collection.clear();
	}

	/**
	 * @param arg0
	 * @return
	 * @see java.util.Collection#contains(java.lang.Object)
	 */
	public boolean contains(Object arg0)
	{
		return collection.contains(arg0);
	}

	/**
	 * @param arg0
	 * @return
	 * @see java.util.Collection#containsAll(java.util.Collection)
	 */
	public boolean containsAll(Collection<?> arg0)
	{
		return collection.containsAll(arg0);
	}

	/**
	 * @param arg0
	 * @return
	 * @see java.util.Collection#equals(java.lang.Object)
	 */
	public boolean equals(Object arg0)
	{
		return collection.equals(arg0);
	}

	/**
	 * @return
	 * @see java.util.Collection#hashCode()
	 */
	public int hashCode()
	{
		return collection.hashCode();
	}

	/**
	 * @return
	 * @see java.util.Collection#isEmpty()
	 */
	public boolean isEmpty()
	{
		if(collection == null)
			return true;
		
		return collection.isEmpty();
	}

	/**
	 * @return
	 * @see java.util.Collection#iterator()
	 */
	public Iterator<T> iterator()
	{
		if(this.collection == null)
			return null;
		
		return collection.iterator();
	}

	/**
	 * @param arg0
	 * @return
	 * @see java.util.Collection#remove(java.lang.Object)
	 */
	public boolean remove(Object arg0)
	{
		return collection.remove(arg0);
	}

	/**
	 * @param arg0
	 * @return
	 * @see java.util.Collection#removeAll(java.util.Collection)
	 */
	public boolean removeAll(Collection<?> arg0)
	{
		return collection.removeAll(arg0);
	}

	/**
	 * @param arg0
	 * @return
	 * @see java.util.Collection#retainAll(java.util.Collection)
	 */
	public boolean retainAll(Collection<?> arg0)
	{
		return collection.retainAll(arg0);
	}

	/**
	 * @return
	 * @see java.util.Collection#size()
	 */
	public int size()
	{
		return collection.size();
	}

	/**
	 * @return
	 * @see java.util.Collection#toArray()
	 */
	public Object[] toArray()
	{
		return collection.toArray();
	}

	/**
	 * @param arg0
	 * @return
	 * @see java.util.Collection#toArray(T[])
	 */
	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] arg0)
	{
		return collection.toArray(arg0);
	}


	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PagingCollection [collection=" + collection + ", pageCriteria="
				+ pageCriteria + ", last=" + last + ", first=" + first + "]";
	}


	private final Collection<T> collection;
	private final PageCriteria pageCriteria;
	/**
	 * 
	 */
	private static final long serialVersionUID = 7655893737037419650L;
	private boolean last;
	private boolean first;
	
}
