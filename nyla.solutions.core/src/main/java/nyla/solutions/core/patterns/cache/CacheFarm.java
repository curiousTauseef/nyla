package nyla.solutions.core.patterns.cache;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import nyla.solutions.core.exception.NoDataFoundException;
import nyla.solutions.core.exception.NotImplementedException;
import nyla.solutions.core.exception.RequiredException;





public class CacheFarm<K,V> implements Cache<K,V>, Cloneable
{	
	/**
	 * 
	 * @see java.util.Hashtable#clear()
	 */
	public void clear()
	{
		map.clear();
	}

	/**
	 * @return
	 * @see java.util.Hashtable#clone()
	 */
	public Object clone()
	{
		/*CacheFarm<K,V> copy = new CacheFarm<K,V>();
		copy.map = new WeakHashMap<K,V>();
		copy.map.putAll(this.map);
		
		return copy;*/
		
		try
		{
			return super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			throw new RuntimeException(e.getMessage(),e);
		}
	}

	/**
	 * @param value
	 * @return
	 * @see java.util.Hashtable#contains(java.lang.Object)
	 */
	public boolean contains(Object value)
	{
		return map.containsValue(value);
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Hashtable#containsKey(java.lang.Object)
	 */
	public boolean containsKey(Object key)
	{
		return map.containsKey(key);
	}

	/**
	 * @param value
	 * @return
	 * @see java.util.Hashtable#containsValue(java.lang.Object)
	 */
	public boolean containsValue(Object value)
	{
		return map.containsValue(value);
	}

	/**
	 * @return
	 * @see java.util.Hashtable#elements()
	 */
	public Enumeration<V> elements()
	{
		throw new NotImplementedException();
	}

	/**
	 * @return
	 * @see java.util.Hashtable#entrySet()
	 */
	public Set<Entry<K,V>> entrySet()
	{
		return map.entrySet();
	}// --------------------------------------------------------

	/**
	 * @param o
	 * @return
	 * @see java.util.Hashtable#equals(java.lang.Object)
	 */
	public boolean equals(Object o)
	{
		return map.equals(o);
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Hashtable#get(java.lang.Object)
	 */
	public V get(Object key)
	{
		return map.get(key);
	}

	/**
	 * @return
	 * @see java.util.Hashtable#hashCode()
	 */
	public int hashCode()
	{
		return map.hashCode();
	}

	/**
	 * @return
	 * @see java.util.Hashtable#isEmpty()
	 */
	public boolean isEmpty()
	{
		return map.isEmpty();
	}

	/**
	 * @return
	 * @see java.util.Hashtable#keys()
	 */
	public Enumeration<K> keys()
	{
		throw new NotImplementedException();
	}

	/**
	 * @return
	 * @see java.util.Hashtable#keySet()
	 */
	public Set<K> keySet()
	{
		return map.keySet();
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @see java.util.Hashtable#put(java.lang.Object, java.lang.Object)
	 */
	public V put(K arg0, V arg1)
	{
		return map.put(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @see java.util.Hashtable#putAll(java.util.Map)
	 */
	public void putAll(Map<? extends K, ? extends V> arg0)
	{
		map.putAll(arg0);
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Hashtable#remove(java.lang.Object)
	 */
	public V remove(Object key)
	{
		return map.remove(key);
	}

	/**
	 * @return
	 * @see java.util.Hashtable#size()
	 */
	public int size()
	{
		return map.size();
	}

	/**
	 * @return
	 * @see java.util.Hashtable#toString()
	 */
	public String toString()
	{
		return map.toString();
	}

	/**
	 * @return
	 * @see java.util.Hashtable#values()
	 */
	public Collection<V> values()
	{
		return map.values();
	}//---------------------------------------------
	/**
	 * 
	 * @return singleton instance cache
	 */
	public static Cache<Object,Object> getCache()
	{
		if(cache == null)
			cache = new CacheFarm<Object,Object>();
		
		return cache;
		
	}//---------------------------------------------
	
	/**
	 * @see nyla.solutions.core.patterns.cache.Cache#getObject(java.lang.Class)
	 */
	public Object getObject(Class<?> objClass)
	throws NoDataFoundException
	{
		String name = objClass.getName();
		Object obj = this.get(objClass.getName());
		
		if(obj == null)
			throw new NoDataFoundException("name="+name+" no found in keys="+this.map.keySet());
		
		
		return obj;
	}//---------------------------------------------

	/**
	 * @see nyla.solutions.core.patterns.cache.Cache#setObject(java.lang.Class, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public void setObject(Class<?> objClass, V obj)
	{
		if(objClass == null)
			throw new RequiredException("objClass in CacheFarm");
		
		if(obj == null)
			throw new RequiredException("obj in CacheFarm");
		
		this.map.put((K)objClass.getName(), obj);
		
	}//---------------------------------------------
	/**
	 * Remove object from cache
	 */
	public void removeObject(Class<?> objClass)
	{
		this.map.remove(objClass.getName());
	}//---------------------------------------------
	private static Cache<Object,Object> cache = null;
	private WeakHashMap<K,V> map = new WeakHashMap<K,V>();

}
