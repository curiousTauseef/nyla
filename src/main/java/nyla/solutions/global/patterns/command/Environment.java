package nyla.solutions.global.patterns.command;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class Environment
{
	/**
	 * 
	 * @see java.util.Map#clear()
	 */
	public void clear()
	{
		map.clear();
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	public boolean containsKey(Object key)
	{
		return map.containsKey(key);
	}

	/**
	 * @param value
	 * @return
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	public boolean containsValue(Object value)
	{
		return map.containsValue(value);
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.Map#equals(java.lang.Object)
	 */
	public boolean equals(Object o)
	{
		return map.equals(o);
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Map#get(java.lang.Object)
	 */
	public Object get(Object key)
	{
		return map.get(key);
	}

	/**
	 * @return
	 * @see java.util.Map#hashCode()
	 */
	public int hashCode()
	{
		return map.hashCode();
	}

	/**
	 * @return
	 * @see java.util.Map#isEmpty()
	 */
	public boolean isEmpty()
	{
		return map.isEmpty();
	}

	/**
	 * @return
	 * @see java.util.Map#keySet()
	 */
	public Set<Object> keySet()
	{
		return map.keySet();
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	public Object put(Object key, Object value)
	{
		return map.put(key, value);
	}

	/**
	 * @param m
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	public void putAll(Map<Object,Object> m)
	{
		map.putAll(m);
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	public Object remove(Object key)
	{
		return map.remove(key);
	}

	/**
	 * @return
	 * @see java.util.Map#size()
	 */
	public int size()
	{
		return map.size();
	}

	/**
	 * @return
	 * @see java.util.Map#values()
	 */
	public Collection<Object> values()
	{
		return map.values();
	}

	
	/**
	 * @return the map
	 */
	public Map<Object,Object> getMap()
	{
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map<Object,Object> map)
	{
		this.map = map;
	}


	private Map<Object,Object> map = new Hashtable<Object,Object>();
}
