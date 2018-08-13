package nyla.solutions.core.patterns.creational.generator;


import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import nyla.solutions.core.operations.ClassPath;
import nyla.solutions.core.patterns.creational.Creator;
import nyla.solutions.core.util.Digits;
import nyla.solutions.core.util.JavaBean;
import nyla.solutions.core.util.Text;

/**
 * <pre>
 * 
 * UserProfile protoype = new UserProfile();
		protoype.setFirstName("Imani");
		protoype.setLastName("Green");
		
		JavaBeanGeneratorCreator<UserProfile> creator = new JavaBeanGeneratorCreator<UserProfile>(protoype);
		
		creator.randomizeProperty("email");
		
		UserProfile u1 = creator.create();
		assertEquals(u1.getFirstName(),protoype.getFirstName());
		assertEquals(u1.getLastName(),protoype.getLastName());
		assertNotNull(u1.getEmail());
		assertTrue(u1.getEmail().length() > 0);
 * </pre>
 * 
 * @author Gregory Green
 *
 * @param <T> the object type to create
 * 
 * 
 */
public class JavaBeanGeneratorCreator<T> implements Creator<T>
{
	/**
	 * 
	 * @param prototype the prototype
	 */
	@SuppressWarnings("unchecked")
	public JavaBeanGeneratorCreator(T prototype)
	{
		this((Class<T>)prototype.getClass(),prototype);
	}
	/**
	 * 
	 * @param clz the class
	 */
	public JavaBeanGeneratorCreator(Class<T> clz)
	{
		this(clz,null);
	}
	/**
	 * @param prototype the prototype to use for created objects
	 * @param clz the class
	 */
	public JavaBeanGeneratorCreator(Class<T> clz, T prototype)
	{
		this.clz  = clz;
		this.prototype = prototype;
	}//------------------------------------------------
	/**
	 * Create a new instance on the settings
	 * @return the created instance
	 */
	@Override
	public T create()
	{
		try
		{
			T obj = ClassPath.newInstance(clz);
			
			//Copy prototype properties
			if(this.prototype != null)
				JavaBean.populate(JavaBean.toMap(prototype), obj);
			
			PropertyDescriptor pd = null;
			Class<?> clz = null;
			
			final boolean throwExceptionForMissingProperty = false;
			
			for (String property : randomizeProperties)
			{
				pd = JavaBean.getPropertyDescriptor(obj, property);
				clz = pd.getPropertyType();
				
				if(clz.isAssignableFrom(String.class))
				{
					try
					{
						JavaBean.setProperty(obj, property, Text.generateId(),throwExceptionForMissingProperty);  
					}
					catch(IllegalArgumentException e)
					{
						throw new IllegalArgumentException("randomizeProperties:"+randomizeProperties+" invalid property:"+property,e);
					}
					
				}
				else if(clz.isAssignableFrom(Integer.class) || clz.isAssignableFrom(int.class) )
				{
					JavaBean.setProperty(obj, property, digits.generateInteger());
				}
				else if (clz.isAssignableFrom(long.class)  || clz.isAssignableFrom(Long.class))
				{
					JavaBean.setProperty(obj, property, digits.generateLong());
				}
				else
				{
					throw new IllegalArgumentException("Unsupport generation for property:"+property+" of class:"+clz.getName()+" ");
				}
			}
			
			return obj;
		}
		catch (RuntimeException e)
		{
			throw e;
			
		}
		catch (Exception e)
		{
			throw new RuntimeException(e.getMessage(),e);
		}
	}//------------------------------------------------
	/**
	 * Setup property to generate a random value
	 * @param property the property randomize
	 */
	public void randomizeProperty(String property)
	{
		if(property  ==null || property.length() == 0)
			return;
		
		this.randomizeProperties.add(property);
	}//------------------------------------------------
	public void randomizeAll()
	{
		this.randomizeProperties = JavaBean.getPropertyNames(this.clz);
	}
	/**
	 * Randomized to records that are not in fixed list
	 * @param fixedPropertyNames the fixed list of property names
	 */
	public void fixedProperties(String... fixedPropertyNames)
	{
		if(fixedPropertyNames == null || fixedPropertyNames.length == 0)
		{
			return;
		}
		
		HashSet<String> fixSet = new HashSet<>(Arrays.asList(fixedPropertyNames));
		
		Map<Object,Object> map = null;
		
		if(this.prototype != null)
		{
			map  =JavaBean.toMap(this.prototype);
		}
		else
			map = JavaBean.toMap(ClassPath.newInstance(this.clz));
		
		if(map == null || map.isEmpty())
			return;
		
		String propertyName = null;
		for(Object propertyNameObject : map.keySet())
		{
			propertyName = String.valueOf(propertyNameObject);
			if(!fixSet.contains(propertyName))
			{
				this.randomizeProperty(propertyName);
			}
		}
	}
	
	/**
	 * @return the randomizeProperties
	 */
	Set<String> getRandomizeProperties()
	{
		return randomizeProperties;
	}

	private Digits digits = new Digits();
	private Set<String> randomizeProperties =  new HashSet<String>();
	private final Class<T> clz;
	private final T prototype;
	


}
