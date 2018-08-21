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
	 * @return cloned object
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
	 * @return boolean if value exists
	 * @see java.util.Hashtable#contains(java.lang.Object)
	 */
	public boolean contains(Object value)
	{
		return map.containsValue(value);
	}

	/**
	 * @param key the key to check
	 * @return boolean if key exists
	 * @see java.util.Hashtable#containsKey(java.lang.Object)
	 */
	public boolean containsKey(Object key)
	{
		return map.containsKey(key);
	}

	/**
	 * @param value
	 * @return boolean if value exists
	 * @see java.util.Hashtable#containsValue(java.lang.Object)
	 */
	public boolean containsValue(Object value)
	{
		return map.containsValue(value);
	}

	/**
	 * @return enumeration of elements
	 * @see java.util.Hashtable#elements()
	 */
	public Enumeration<V> elements()
	{
		throw new NotImplementedException();
	}

	/**
	 * @return set of entries
	 * @see java.util.Hashtable#entrySet()
	 */
	public Set<Entry<K,V>> entrySet()
	{
		return map.entrySet();
	}// --------------------------------------------------------

	public boolean equals(Object o)
	{
		return map.equals(o);
	}

	/**
	 * @param key
	 * @return object value
	 * @see java.util.Hashtable#get(java.lang.Object)
	 */
	public V get(Object key)
	{
		return map.get(key);
	}

	/**
	 * @return map hash code
	 * @see java.util.Hashtable#hashCode()
	 */
	public int hashCode()
	{
		return map.hashCode();
	}

	/**
	 * @return is empty boolean
	 * @see java.util.Hashtable#isEmpty()
	 */
	public boolean isEmpty()
	{
		return map.isEmpty();
	}

	/**
	 * @return enumeration of keys
	 * @see java.util.Hashtable#keys()
	 */
	public Enumeration<K> keys()
	{
		throw new NotImplementedException();
	}

	/**
	 * @return the key set for the map
	 * @see java.util.Hashtable#keySet()
	 */
	public Set<K> keySet()
	{
		return map.keySet();
	}

	/**
	 * @param key key 
	 * @param value value
	 * @return previous value
	 * @see java.util.Hashtable#put(java.lang.Object, java.lang.Object)
	 */
	public V put(K key, V value)
	{
		return map.put(key, value);
	}

	/**
	 * @param m the map
	 * @see java.util.Hashtable#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends K, ? extends V> m)
	{
		this.map.putAll(m);
	}

	/**
	 * @param key
	 * @return the removed value
	 * @see java.util.Hashtable#remove(java.lang.Object)
	 */
	public V remove(Object key)
	{
		return map.remove(key);
	}

	/**
	 * @return the map size
	 * @see java.util.Hashtable#size()
	 */
	public int size()
	{
		return map.size();
	}

	public String toString()
	{
		return map.toString();
	}

	/**
	 * @return the map values
	 * @see java.util.Hashtable#values()
	 */
	public Collection<V> values()
	{
		return map.values();
	}//---------------------------------------------
	/**
	 * 
	 * @param <K> the key
	 * @param <V> the value
	 * @return singleton instance cache
	 */
	@SuppressWarnings("unchecked")
	public static<K,V> Cache<K,V> getCache()
	{
		if(cache == null)
			cache = new CacheFarm<>();
		
		return (Cache<K,V>)cache;
		
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
	 * @param objClass the object class to remove by name
	 */
	public void removeObject(Class<?> objClass)
	{
		this.map.remove(objClass.getName());
	}//---------------------------------------------
	private static Cache<Object,Object> cache = null;
	private WeakHashMap<K,V> map = new WeakHashMap<K,V>();

}
