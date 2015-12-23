package nyla.solutions.global.patterns.cache;

import java.util.Map;

import nyla.solutions.global.exception.NoDataFoundException;


/**
 * Cache of objects
 * @author Gregory Green
 *
 */
public interface Cache<K,V> extends Map<K,V>
{
	/**
	 * 
	 * @param objClass the class key
	 * @return the object with matching class key
	 * @throws NoDataFoundException
	 */
	Object getObject(Class<?> objClass)
	throws NoDataFoundException;
	
	/**
	 * 
	 * @param objClass the object class key
	 * @param obj the Cooresponding object
	 */
	void setObject(Class<?> objClass, V obj);
	
	
	/**
	 * Remove an object from the cache
	 * @param objClass the object class key
	 */
	void removeObject(Class<?> objClass);
}
