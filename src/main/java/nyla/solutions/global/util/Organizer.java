package nyla.solutions.global.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import nyla.solutions.global.data.AbstractAuditable;
import nyla.solutions.global.data.Auditable;
import nyla.solutions.global.data.Copier;
import nyla.solutions.global.data.Criteria;
import nyla.solutions.global.data.Mappable;
import nyla.solutions.global.data.NumberedProperty;
import nyla.solutions.global.data.PrimaryKey;
import nyla.solutions.global.data.Property;
import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.operations.Log;
import nyla.solutions.global.patterns.expression.BooleanExpression;
import nyla.solutions.global.patterns.iteration.PageCriteria;
import nyla.solutions.global.patterns.iteration.Paging;
import nyla.solutions.global.patterns.iteration.PagingCollection;
import nyla.solutions.global.util.BeanComparator;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.JavaBean;
import nyla.solutions.global.util.Organizer;

/**
 * <pre>
 * 
 *  Organizer provides a set of functions to sort, filter and find elements in container objects 
 *  such as collections, arrays and maps.
 *  
 *  This object has methods to sort collections of object. It can compare a particular
 *  bean property value. Note that this class uses the JavaBean object that is
 *  also located in the same util directory.
 *  
 *  The following is a sample web action that uses the Organizer to sort a list of sites Java bean objects.
 *  
 *   ...
 *  
 *  public Collection getSitesDetailList()
 *  {
 *  //     Here SiteAction Must be Called Like SiteAction.getSiteDetails
 *  this.sitesDetailList = MockUtils.getSomeSiteDetails();
 *  
 *  String sortProperty = JSF.getRequestParameter(&quot;sortProperty&quot;);
 *  String filterNameProperty = JSF.getRequestParameter(&quot;filterPropertyName&quot;);      
 *  Comparable filterValueProperty =    
 *  (Comparable)JSF.getRequestParameter(&quot;filterValueProperty&quot;);
 *  
 *  if (!Text.isNull(sortProperty))
 *  {
 *  sitesDetailList = Organizer.sortByJavaBeanProperty(sortProperty,
 *  sitesDetailList);
 *  }
 *  else if(!Text.isNull(filterNameProperty))
 *  {
 *  sitesDetailList = Organizer.filterByJavaBeanProperty((ArrayList)sitesDetailList,filterNameProperty,filterValueProperty);
 *  }
 *  
 *  return sitesDetailList;
 *  }//--------------------------------------------
 * 
 * 
 * </pre>
 * 
 * @author Gregory Green
 * @version 2.0
 */
public final class Organizer
{

	/**
	 * 
	 * Constructor for Organizer initializes internal
	 */
	private Organizer()
	{
	}

	/**
	 * 
	 * @param input the item to add
	 * @param array the array where item is added
	 * @return the update array
	 */
	public static <T> T[] add(T input, T[] array)
	{
		ArrayList<T> list = new ArrayList<T>(array.length + 1);
		list.addAll(Arrays.asList(array));
		list.add(input);

		return (T[]) list.toArray(array);
	}// --------------------------------------------------------

	/**
	 * <pre>
	 * Flatten collection of collections into a collection of objects
	 * 
	 * Usage
	 * Collection<Serializable> flattedCollection = null;
			    
			    switch(returnType)
			    {
			    	case tree: flattedCollection = new TreeSet<Serializable>();break;
			    	case set: flattedCollection = new HashSet<Serializable>(results.size());break;
			    	default: flattedCollection = new HashSet<Serializable>(results.size());
			    }
				
			    Organizer.flatten((Collection)results, flattedCollection);
	   </pre>
	 * @param input 
	 * @param flattenOutput
	 */
	@SuppressWarnings("unchecked")
	public static <T> void flatten(Collection<Object> input,
			Collection<T> flattenOutput)
	{
		if (input == null || input.isEmpty() || flattenOutput == null)
			return;

		for (Object obj : input)
		{
			if(obj instanceof Collection)
				flatten((Collection<Object>)obj,flattenOutput);

		}

	}// --------------------------------------------------------
	/**
	 * Aggregates multiple collections into a single paging collection
	 * @param collectionOfPaging the collection paging that 
	 * @return the flatten collections into a single collection
	 */
	public static <T> Paging<T> flattenPaging(Collection<Paging<T>> collectionOfPaging)
	{
		if(collectionOfPaging == null || collectionOfPaging.isEmpty())
			return null;
		
		PageCriteria pageCriteria =  null;
		//get first page criteria
		Paging<T> firstPaging = collectionOfPaging.iterator().next();
		
		if(firstPaging != null)
			pageCriteria =  firstPaging.getPageCriteria();
		
		return flattenPaging(collectionOfPaging,pageCriteria,null,null);
		
	}// --------------------------------------------------------
	/**
	 * Aggregates multiple collections into a single paging collection
	 * @param collectionOfPaging the collection paging that need to be flatten
	 * @param sorter optional comparable for sorting
	 * @param filter optional filter, if filter.
	 * @return the flatten collections into a single collection
	 */	
	public static <T> Paging<T> flattenPaging(Collection<Paging<T>> collectionOfPaging, 
			                                  Comparator<T> sorter,BooleanExpression<T> filter)
	{
		if(collectionOfPaging == null || collectionOfPaging.isEmpty())
			return null;
		
		PageCriteria pageCriteria = collectionOfPaging.iterator().next().getPageCriteria();
		return flattenPaging(collectionOfPaging,pageCriteria,sorter,filter);
	}// --------------------------------------------------------
	/**
	 * Aggregates multiple collections into a single paging collection
	 * @param collectionOfPaging the collection paging that need to be flatten
	 * @param sorter optional comparable for sorting
	 * @param filter optional filter, if filter.
	 * @return the flatten collections into a single collection
	 */	
	@SuppressWarnings("unchecked")
	public static <T> Paging<T> flattenPaging(Collection<?> collectionOfPaging, PageCriteria pageCriteria, 
			                                  Comparator<T> sorter,BooleanExpression<T> filter)
	{
		if(collectionOfPaging == null || collectionOfPaging.isEmpty())
			return null;
		
		Paging<T> pagingResults = null;
		
		if(sorter != null)
		{
			//Create tree set based paging
			TreeSet<T> treeSet = new TreeSet<T>(sorter);
			pagingResults = new PagingCollection<T>(treeSet, pageCriteria);
		}
		
		//Add all to an aggregated collection 
		Paging<T> paging = null;
		for (Object item : collectionOfPaging)
		{
			if(item instanceof Paging)
			{
				paging = (Paging<T>)item;
				
				if(pagingResults != null)
					addAll(pagingResults, paging,filter);
				else
					pagingResults = paging;
			}
			else if(item != null)
			{
				//initialize paging results if needed
				if(pagingResults == null)
				{
					if(sorter != null)
					{
						//Create tree set based paging
						TreeSet<T> treeSet = new TreeSet<T>(sorter);
						pagingResults = new PagingCollection<T>(treeSet, pageCriteria);
					}
					else
						pagingResults = new PagingCollection<T>(new ArrayList<T>(), pageCriteria);

				}
				
				pagingResults.add((T)item);
			}
		}
		
		return pagingResults;
	}// --------------------------------------------------------
	/**
	 * Add all collections
	 * @param pagingResults the results to add to
	 * @param filter remove object where filter.getBoolean() == true
	 */
	public static <T> void addAll(Collection<T> pagingResults,Collection<T> paging, BooleanExpression<T> filter)
	{
		if(pagingResults == null || paging == null)
			return;
		
		if(filter != null )
		{
			for (T obj : paging)
			{
				if(filter.getBoolean(obj))
					pagingResults.add(obj);			
			}
		}
		else
		{
			//add independent of a filter
			for (T obj : paging)
			{
				pagingResults.add(obj);			
			}
		}
	}// --------------------------------------------------------
	/**
	 * Find the value with a given key in the map.
	 * 
	 * @param key the map key
	 * @param map the map with key/value pairs
	 * @param defaultValue this is returned if the value is now found
	 * @return the single value found
	 */
	public static Object findMapValueByKey(Object key, Map<?, ?> map,
			Object defaultValue)
	{
		if (key == null || map == null)
			return defaultValue;

		Object value = map.get(key);

		if (value == null)
			return defaultValue;

		return value;
	}// --------------------------------------------

	/**
	 * 
	 * @param text the text to search for
	 * @param list the list of strings
	 * @return true if aText in aList
	 */
	public static boolean isStringIn(String aText, String[] aList)
	{
		if (aText == null)
			return false;

		for (int i = 0; i < aList.length; i++)
		{
			if (aText.equals(aList[i]))
				return true;
		}

		return false;
	}// --------------------------------------------

	public static Object findByTextIgnoreCase(Collection<?> aCollection,
			String aText)
	{
		if (aText == null)
			throw new RequiredException("aName in Organizer.findIgnoreCase");

		if (aCollection == null)
			throw new RequiredException(
					"aCollection in Organizer.findIgnoreCase");

		Object element = null;
		for (Iterator<?> i = aCollection.iterator(); i.hasNext();)
		{
			element = i.next();

			if (element == null)
				continue;

			if (aText.equalsIgnoreCase(element.toString()))
				return element;
		}

		throw new SystemException("Text=" + aText + " in collection  "
				+ aCollection);
	}// --------------------------------------------

	/**
	 * Add object to a list
	 * 
	 * @param list where objects will be added
	 * @param objects the object add
	 */
	public static void addAll(Collection<Object> list, Object[] objects)
	{
		list.addAll(Arrays.asList(objects));
	}// --------------------------------------------

	/**
	 * 
	 * @param objects
	 * @param aData
	 * @return true if the object's property contains data in test Map
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	static <K,V> boolean doesListContainData(Object[] objects, Map<K,V> aData)
			throws Exception
	{
		if (objects == null || objects.length == 0)
			return false;

		Map<K,V> objectMap = null;
		for (int i = 0; i < objects.length; i++)
		{
			// get properties for first object
			objectMap = (Map<K,V>)JavaBean.toMap(objects[i]);

			if (doesMapContainData((Map<Object,Object>)objectMap, (Map<Object,Object>)aData))
				return true;
		}

		return false;

	}// --------------------------------------------

	/**
	 * 
	 * @param aMap the map
	 * @param aData the key/values to check
	 * @return true if all data in aData exist in aMap
	 */
	public static boolean doesMapContainData(Map<Object,Object> aMap, Map<Object,Object> aData)
	{
		// compare with testMap
		Object testMapKey = null;
		for (Map.Entry<Object,Object> entry : aData.entrySet())
		{
			// get testMap Key
			testMapKey = entry.getKey();

			// test if values are equals
		
			if (!String.valueOf(aMap.get(testMapKey)).equals(
					String.valueOf(entry.getValue())))
			{
				// not equal continue skip
				return false;
			}
		}
		return true;
	}// --------------------------------------------

	public static Object[] copy(Object[] objs)
	{
		if(objs == null)
			return null;
		
		Object[]  results = new Object[objs.length];
		System.arraycopy(objs, 0, results, 0, results.length);
		
		return results;
	}// --------------------------------------------------------
	/**
	 * Copy collection objects to a given array
	 * 
	 * @param aCollection the collection source
	 * @param aObject array destination
	 */
	public static void copyToArray(Collection<Object> aCollection, Object[] aObjects)
	{
		System.arraycopy(aCollection.toArray(), 0, aObjects, 0, aObjects.length);
	}// --------------------------------------------

	/**
	 * Add mappable to map
	 * 
	 * @param aMappables the collection of Mappables that must implement the
	 *            Copier interface
	 * @param aMap the map to add to
	 */
	public static <K,V> void addMappableCopiesToMap(Collection<Mappable<K,V>> aMappables, Map<K,V> aMap)
	{
		if (aMappables == null || aMap == null)
			return;

		Mappable<K,V> mappable = null;
		Copier previous = null;
		for (Iterator<Mappable<K,V>> i = aMappables.iterator(); i.hasNext();)
		{
			mappable = i.next();

			previous = (Copier) aMap.get(mappable.getKey());

			if (previous != null)
			{
				// copy data
				previous.copy((Copier) mappable);
			}
			else
			{
				// add to map
				aMap.put((K)mappable.getKey(), (V)mappable.getValue());
			}
		}
	}// --------------------------------------------

	/**
	 * Find values in map that match a given key
	 * 
	 * @param aKeys the keys
	 * @param aMap the map containing the data
	 * @return Collection of values
	 */
	public static <T, K> Collection<T> findMapValuesByKey(Collection<K> aKeys,
			Map<K, T> aMap)
	{
		if (aKeys == null || aMap == null)
			return null;

		Object key = null;
		ArrayList<T> results = new ArrayList<T>(aMap.size());
		for (Iterator<K> i = aKeys.iterator(); i.hasNext();)
		{
			key = i.next();
			results.add(aMap.get(key));
		}

		results.trimToSize();

		return results;
	}// --------------------------------------------

	/**
	 * All object to a given collection
	 * 
	 * @param aFrom the collection to add from
	 * @param aTo the collecto to add to.
	 */
	public static <T> void addAll(Collection<T> aFrom, Collection<T> aTo)
	{
		if (aFrom == null || aTo == null)
			return; // do nothing

		T object = null;
		for (Iterator<T> i = aFrom.iterator(); i.hasNext();)
		{
			object = i.next();
			if (object != null)
			{
				aTo.add(object);
			}
		}
	}// --------------------------------------------

	/**
	 * 
	 * @param aCollection the collection of objects
	 * @return aCollection == null || aCollection.isEmpty()
	 */
	public static boolean isEmpty(Collection<?> aCollection)
	{
		return aCollection == null || aCollection.isEmpty();
	}// --------------------------------------------

	public static boolean isEmpty(Object[] objects)
	{
		return objects == null || objects.length == 0;
	}// --------------------------------------------

	/**
	 * 
	 * @param aInt the search criteria
	 * @param aIntegers the list of integers
	 * @return true is aInts exist in aIntegers list
	 */
	public static boolean isIntegerIn(Integer aInt, Integer[] aIntegers)
	{
		if (aIntegers == null || aInt == null)
			return false;

		for (int i = 0; i < aIntegers.length; i++)
		{
			if (aInt.equals(aIntegers[i]))
				return true;
		}

		return false;
	}// --------------------------------------------

	/**
	 * construct map for collection of criteria object wher the key is
	 * Criteria.getId
	 * 
	 * @param aCriterias
	 * @return the map Criteria is the value and Criteria.getId is the key
	 */
	public static Map<String,Criteria> constructCriteriaMap(Collection<Criteria> aCriterias)
	{
		if (aCriterias == null)
			return null;

		Map<String,Criteria> map = new HashMap<String,Criteria>(aCriterias.size());
		Criteria criteria = null;
		for (Iterator<Criteria> i = aCriterias.iterator(); i.hasNext();)
		{
			criteria = (Criteria) i.next();
			map.put(criteria.getId(), criteria);
		}
		return map;
	}// --------------------------------------------

	/**
	 * construct map for collection of criteria object wher the key is
	 * Criteria.getId
	 * 
	 * @param aCriterias
	 * @return the map Criteria is the value and Criteria.getId is the key
	 */
	public static Map<Integer,PrimaryKey>  constructPrimaryKeyMap(Collection<PrimaryKey> aPrimaryKeys)
	{
		if (aPrimaryKeys == null)
			return null;

		Map<Integer,PrimaryKey> map = new HashMap<Integer,PrimaryKey>(aPrimaryKeys.size());
		PrimaryKey primaryKey = null;
		for (Iterator<PrimaryKey> i = aPrimaryKeys.iterator(); i.hasNext();)
		{
			primaryKey = (PrimaryKey) i.next();
			map.put(Integer.valueOf(primaryKey.getPrimaryKey()), primaryKey);
		}
		return map;
	}// --------------------------------------------

	/**
	 * 
	 * @param aName the property name
	 * @param aProperties
	 * @return null if not found, else return matching propertu
	 */
	public static Property findPropertyByName(String aName,
			Collection<Property> aProperties)
	{
		if (aName == null)
			throw new IllegalArgumentException(
					"aName required in Organizer.findPropertyByName");

		if (aProperties == null)
			throw new IllegalArgumentException(
					"aProperties required in Organizer.findPropertyByName");

		Property property = null;
		for (Iterator<Property> i = aProperties.iterator(); i.hasNext();)
		{
			property = i.next();
			if (aName.equals(property.getName()))
				return property;
		}
		return null;
	}// --------------------------------------------
		// makeAuditableCopies

	/**
	 * Copy data from object to object
	 * 
	 * @param aFrom the object to copy from
	 * @param aTo the object to copy to
	 */
	public static <K> void makeCopies(Map<K,Copier> aFrom, Map<K,Copier>  aTo)
	{
		makeAuditableCopies(aFrom, aTo, null);
	}// --------------------------------------------

	/**
	 * Copy data from object to object
	 * 
	 * @param aFrom the object to copy from
	 * @param aTo the object to copy to
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void makeCopies(Collection<Copier> aFrom, Collection<Copier> aTo)
	{
		if (aFrom == null || aTo == null)
			return;

		List<Copier> fromList = new ArrayList<Copier>(aFrom);
		List<Copier> toList = new ArrayList<Copier>(aTo);
		Collections.sort((List)fromList);
		Collections.sort((List)toList);

		Copier from = null;
		Copier to = null;
		Iterator<Copier> toIter = toList.iterator();
		for (Iterator<Copier> i = fromList.iterator(); i.hasNext() && toIter.hasNext();)
		{
			from = (Copier) i.next();
			to = (Copier) toIter.next();

			// copy data
			to.copy(from);
		}
	}// --------------------------------------------

	/**
	 * Copy value form one map to another
	 * 
	 * @param aFormMap
	 * @param aToMap
	 */
	public static <K> void  makeAuditableCopies(Map<K,Copier> aFormMap, Map<K,Copier> aToMap,
			Auditable aAuditable)

	{
		if (aFormMap == null || aToMap == null)
			return;

		// for thru froms
		K fromKey = null;
		Copier to = null;
		Copier from = null;
		for (Map.Entry<K,Copier> entry : aFormMap.entrySet())
		{
			fromKey = entry.getKey();

			if (aToMap.keySet().contains(fromKey))
			{
				// copy existening data
				to = (Copier) aToMap.get(fromKey);
				to.copy((Copier) entry.getValue());

				// copy auditing info
				if (aAuditable != null && to instanceof Auditable)
				{
					AbstractAuditable.copy(aAuditable, (Auditable) to);
				}
			}
			else
			{
				from = (Copier) aFormMap.get(fromKey);

				// copy auditing info
				if (aAuditable != null && from instanceof Auditable)
				{
					AbstractAuditable.copy(aAuditable, (Auditable) from);
				}

				// add to
				aToMap.put(fromKey, from);
			}
		}
	}// --------------------------------------------

	/**
	 * Sort collection of object by a given property name
	 * 
	 * @param aProperyName the proeprty name
	 * @param aCollection the collection of object to sort
	 * @return
	 */
	public static <T> Collection<T> sortByJavaBeanProperty(String aProperyName,
			Collection<T> aCollection)
	{
		return sortByJavaBeanProperty(aProperyName, aCollection, false);
	}// --------------------------------------------

	/**
	 * Sort collection of object by a given property name
	 * 
	 * @param aProperyName the proeprty name
	 * @param aCollection the collection of object to sort
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Collection<T> sortByJavaBeanProperty(String aProperyName,
			Collection<T> aCollection, boolean aDescending)
	{
		if (aCollection == null)
			return (Collection<T>)new ArrayList<T>();

		if (aProperyName == null)
			throw new IllegalArgumentException(
					"aProperyName required in Organizer");

		BeanComparator bc = new BeanComparator(aProperyName, aDescending);

		return(Collection<T>)bc.sort(aCollection);

	}// --------------------------------------------

	/**
	 * 
	 * @param aCollection the collection to construct set from (this object)
	 *            must have an javaBean property that matches aPropertyName
	 * 
	 * @return set of bean properties (HashSet)
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static <T> Set<T> constructSortedSetForProperty(Collection<T> aCollection,
			String aPropertyName) throws Exception
	{
		if (aCollection == null || aCollection.isEmpty())
			return null;

		Set<T> set = new TreeSet<T>();
		Object bean = null;
		for (Iterator<T> i = aCollection.iterator(); i.hasNext();)
		{
			bean = (Object) i.next();
			set.add((T)JavaBean.getProperty(bean, aPropertyName));
		}

		return set;
	}// --------------------------------------------

	/**
	 * 
	 * 
	 * 
	 * @param aCollection the collection to filter
	 * 
	 * @return the filtered list
	 * 
	 */
	public static Collection<Object> filterByJavaBeanProperty(
			List<Object> aList, String aPropertyName, Comparable<Object> aValue)
	{

		logger.debug("In Organizer filtering: " + aPropertyName
				+ " for value: " + aValue);
		try
		{
			if (aList == null)
				throw new IllegalArgumentException(
						"aCollection required in filterByJavaBeanProperty");

			ArrayList<Object> filteredList = new ArrayList<Object>(aList.size());

			Object bean = null;
			Object beanPropertyValue = null;
			for (Iterator<Object> i = aList.iterator(); i.hasNext();)
			{
				bean = i.next();
				beanPropertyValue = JavaBean.getProperty(bean, aPropertyName);
				logger.debug("Got propertyValue: " + beanPropertyValue
						+ " for propertyName: " + aPropertyName);
				if (aValue.compareTo(beanPropertyValue) == 0)
				{
					// only add equal this bean
					filteredList.add(bean);
					logger.debug("Organizer added bean");
				}
			}
			filteredList.trimToSize();
			return filteredList;
		}
		catch (Exception e)
		{
			throw new SystemException(e);
		}
	}// -----------------------------------------

	/**
	 * 
	 * 
	 * 
	 * @param aCollection the collection to filter
	 * 
	 * @return the filtered list
	 * 
	 */
	public static Collection<Object> filterByJavaBeanDateProperty(ArrayList<Object> aList,
			String aPropertyName, Comparable<Object> aValue, Comparable<Object> bValue)
	{

		logger.debug("In Organizer filtering: " + aPropertyName
				+ " for date value between : " + aValue + " and " + bValue);
		try
		{
			if (aList == null)
				throw new IllegalArgumentException(
						"aCollection required in filterByJavaBeanProperty");

			ArrayList<Object> filteredList = new ArrayList<Object>(aList.size());

			Object bean = null;
			Object beanPropertyValue = null;
			for (Iterator<Object> i = aList.iterator(); i.hasNext();)
			{
				try
				{
					bean = i.next();
					beanPropertyValue = JavaBean.getProperty(bean,
							aPropertyName);
					// logger.debug("Got propertyValue: " + beanPropertyValue+
					// " for propertyName: " + aPropertyName);

					// DateFormat localFormat = DateFormat.getDateInstance();
					DateFormat format = new SimpleDateFormat(
							Config.getProperty("document.date.format"));
					Date propDate = format.parse(beanPropertyValue.toString());
					Date aDate = format.parse(aValue.toString());
					Date bDate = format.parse(bValue.toString());

					// logger.debug("Organizer ::  propDate : " + propDate);
					// logger.debug("Organizer ::  aDate : " + aDate);
					// logger.debug("Organizer ::  bDate : " + bDate);
					if (propDate.after(aDate) && propDate.before(bDate))
					{
						filteredList.add(bean);
						// logger.debug("Organizer added bean");
					}
				}
				catch (Exception e)
				{
					logger.debug("error occured : " + e);
				}
			}
			filteredList.trimToSize();
			return filteredList;
		}
		catch (Exception e)
		{
			throw new SystemException(e);
		}
	}// -----------------------------------------

	public static Collection<Object> filterByJavaBeanPageProperty(ArrayList<Object> aList,
			String aPropertyName, int fromIndex, int toIndex)
	{

		logger.debug("In Organizer filtering: " + aPropertyName);
		try
		{
			if (aList == null)
				throw new IllegalArgumentException(
						"aCollection required in filterByJavaBeanProperty");

			ArrayList<Object> filteredList = new ArrayList<Object>(aList.size());

			Object bean = null;
			Object beanPropertyValue = null;
			for (Iterator<Object> i = aList.iterator(); i.hasNext();)
			{
				try
				{
					bean = i.next();
					beanPropertyValue = JavaBean.getProperty(bean,
							aPropertyName);
					int beanPropIntVal =  Integer.parseInt(
							beanPropertyValue.toString());
					// logger.debug("Got propertyValue: " + beanPropertyValue +
					// " for propertyName: " + beanPropIntVal);
					if ((fromIndex <= beanPropIntVal)
							&& (beanPropIntVal <= toIndex))
					{
						filteredList.add(bean);
						// logger.debug("Organizer added bean");
					}
				}
				catch (Exception e)
				{
					logger.debug("error occured : " + e);
				}
			}
			filteredList.trimToSize();
			return filteredList;
		}
		catch (Exception e)
		{
			throw new SystemException(e);
		}
	}// -----------------------------------------

	/**
	 * 
	 * @param aNumberedProperties
	 * @return Map with (Integer)NumberedProperty.getNumber as the key
	 */
	public static Map<Integer,NumberedProperty> constructNumberedPropertyMap(
			Collection<NumberedProperty> aNumberedProperties)
	{
		if (aNumberedProperties == null)
			throw new IllegalArgumentException(
					"aNumberedProperties required in Organizer");

		Map<Integer,NumberedProperty> map = new HashMap<Integer,NumberedProperty>(aNumberedProperties.size());
		NumberedProperty numberedProperty = null;
		for (Iterator<NumberedProperty> i = aNumberedProperties.iterator(); i.hasNext();)
		{
			numberedProperty = (NumberedProperty) i.next();
			map.put(Integer.valueOf(numberedProperty.getNumber()), numberedProperty);
		}

		return map;
	}// --------------------------------------------

	/**
	 * 
	 * @param aProperties collection of Property objects
	 * @return Map with (Integer)Property.getName as the key
	 */
	public static Map<String,Property> constructPropertyMap(Collection<Property> aProperties)
	{
		if (aProperties == null)
			throw new IllegalArgumentException(
					"aProperties required in Organizer");

		Map<String,Property> map = new HashMap<String,Property>(aProperties.size());
		Property property = null;
		for (Iterator<Property> i = aProperties.iterator(); i.hasNext();)
		{
			property = (Property) i.next();
			map.put(property.getName(), property);
		}

		return map;
	}// --------------------------------------------

	/**
	 * key=Mappable.getKey() value=Mappable.getValue()
	 * 
	 * @param aMappables
	 * @return
	 */
	public static <K,V> Map<K,V> toMap(Collection<Mappable<K,V>> aMappables)
	{
		if (aMappables == null)
			throw new IllegalArgumentException(
					"aMappables required in Organizer");

		Map<K,V> map = new HashMap<K,V>(aMappables.size());
		Mappable<K,V> mappable = null;
		for (Iterator<Mappable<K,V>> i = aMappables.iterator(); i.hasNext();)
		{
			mappable = i.next();
			map.put((K)mappable.getKey(), (V)mappable.getValue());
		}

		return map;
	}// --------------------------------------------
	public static <K,V> Map<K,V> toMap(Mappable<K,V>[] aMappables)
	{
		if (aMappables == null)
			throw new IllegalArgumentException(
					"aMappables required in Organizer");

		Map<K,V> map = new HashMap<K,V>(aMappables.length);
		Mappable<K,V> mappable = null;
		for (int i =0; i < aMappables.length; i++)
		{
			mappable = aMappables[i];
			map.put((K)mappable.getKey(), (V)mappable.getValue());
		}

		return map;
	}// --------------------------------------------

	/**
	 * Cast into an array of objects or create a array with a single entry
	 * 
	 * @param obj the Object[] or single object
	 * @return converted Object[]
	 */
	public static Object[] toArray(Object obj)
	{
		if (obj instanceof Object[])
			return (Object[]) obj;
		else
		{
			Object[] returnArray =
			{ obj };
			return returnArray;

		}
	}// ------------------------------------------------

	/**
	 * 
	 * @param aObjects
	 * @return
	 */
	public static Integer[] toIntegers(Object[] aObjects)
	{
		if (aObjects == null)
			throw new IllegalArgumentException(
					"aObjects required in Organizer.toIntegers");

		if (aObjects.length < 1)
			throw new IllegalArgumentException("aObjects.length < 1 ");

		Integer[] ints = new Integer[aObjects.length];

		System.arraycopy(aObjects, 0, ints, 0, ints.length);
		return ints;

	}// --------------------------------------------

	private static Log logger = Debugger.getLog(Organizer.class);
}
