package nyla.solutions.core.patterns.creational.generator;


import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nyla.solutions.core.exception.SetupException;
import nyla.solutions.core.io.IO;
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
		this.creationClass  = clz;
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
			T obj = ClassPath.newInstance(creationClass);
			
			//Copy prototype properties
			if(this.prototype != null)
				JavaBean.populate(JavaBean.toMap(prototype), obj);
			
			PropertyDescriptor pd = null;
			Class<?> clz = null;
			
			final boolean throwExceptionForMissingProperty = false;
			
			for (String property : randomizeProperties)
			{
				try
				{
					pd = JavaBean.getPropertyDescriptor(obj, property);
					clz = pd.getPropertyType();
					
					if(clz.isAssignableFrom(Class.class) || clz.isEnum())
						continue;
					
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
					
					else if (clz.isAssignableFrom(short.class)  || clz.isAssignableFrom(Short.class))
					{
						JavaBean.setProperty(obj, property, digits.generateShort());
					}
					else if (clz.isAssignableFrom(double.class)  || clz.isAssignableFrom(Double.class))
					{
						JavaBean.setProperty(obj, property, digits.generateDouble());
					}
					else if (clz.isAssignableFrom(char.class)  || clz.isAssignableFrom(Character.class))
					{
						JavaBean.setProperty(obj, property, Text.generateId().charAt(0));
					}
					else if (clz.isAssignableFrom(float.class)  || clz.isAssignableFrom(Float.class))
					{
						JavaBean.setProperty(obj, property, digits.generateFloat());
					}
					else if (clz.isAssignableFrom(byte.class)  || clz.isAssignableFrom(Byte.class))
					{
						JavaBean.setProperty(obj, property,Byte.valueOf(Text.generateId().getBytes(IO.CHARSET)[0]));
					}
					else if (clz.isAssignableFrom(boolean.class)  || clz.isAssignableFrom(Boolean.class))
					{
						if(Calendar.getInstance().getTime().getTime()%2 ==0)
							JavaBean.setProperty(obj, property, true);
						else
							JavaBean.setProperty(obj, property, false);
					}
					else if (clz.equals(BigDecimal.class))
					{
						JavaBean.setProperty(obj, property,digits.generateBigDecimal());
					}
					else if (clz.equals(java.sql.Time.class))
					{
						JavaBean.setProperty(obj, property, 
						new java.sql.Time(
						Calendar.getInstance().getTime().getTime()));
					}
					else if (clz.equals(java.sql.Date.class))
					{
						JavaBean.setProperty(obj, property, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
					}
					else if (clz.equals(java.util.Date.class))
					{
						JavaBean.setProperty(obj, property, Calendar.getInstance().getTime());
					}
					//Timestamp
					else if (clz.equals(java.sql.Timestamp.class))
					{
						JavaBean.setProperty(obj, property, new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
					}
					else if (clz.equals(java.util.Calendar.class))
					{
						JavaBean.setProperty(obj, property, Calendar.getInstance());
					}
					else if (clz.equals(LocalTime.class))
					{
						JavaBean.setProperty(obj, property, LocalTime.now());
					}
					else if (clz.equals(LocalDate.class))
					{
						JavaBean.setProperty(obj, property, LocalDate.now());
					}
					else if (clz.equals(LocalDateTime.class))
					{
						JavaBean.setProperty(obj, property, LocalDateTime.now());
					}
					else
					{
						JavaBean.setProperty(obj, property, ClassPath.newInstance(clz));
					}
				}
				catch (Exception e)
				{
					throw new SetupException("Cannot create property:"+property+" for object class:"
									+this.creationClass.getName()+"  ERROR:"+e.getMessage(),e); 
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
	 * @return the creator instance
	 */
	public JavaBeanGeneratorCreator<T> randomizeProperty(String property)
	{
		if(property  ==null || property.length() == 0)
			return this;
		
		this.randomizeProperties.add(property);
		
		return this;
	}//------------------------------------------------
	public  JavaBeanGeneratorCreator<T> randomizeAll()
	{
		this.randomizeProperties = JavaBean.getPropertyNames(this.creationClass);
		return this;
	}
	/**
	 * Randomized to records that are not in fixed list
	 * @param fixedPropertyNames the fixed list of property names
	 * @return the creator factory
	 */
	public  JavaBeanGeneratorCreator<T> fixedProperties(String... fixedPropertyNames)
	{
		if(fixedPropertyNames == null || fixedPropertyNames.length == 0)
		{
			return this;
		}
		
		HashSet<String> fixSet = new HashSet<>(Arrays.asList(fixedPropertyNames));
		
		Map<Object,Object> map = null;
		
		if(this.prototype != null)
		{
			map  =JavaBean.toMap(this.prototype);
		}
		else
			map = JavaBean.toMap(ClassPath.newInstance(this.creationClass));
		
		if(map == null || map.isEmpty())
			return this;
		
		String propertyName = null;
		for(Object propertyNameObject : map.keySet())
		{
			propertyName = String.valueOf(propertyNameObject);
			if(!fixSet.contains(propertyName))
			{
				this.randomizeProperty(propertyName);
			}
		}
		
		return this;
	}//------------------------------------------------
	
	/**
	 * @return the randomizeProperties
	 */
	Set<String> getRandomizeProperties()
	{
		return randomizeProperties;
	}

	private Digits digits = new Digits();
	private Set<String> randomizeProperties =  new HashSet<String>();
	private final Class<T> creationClass;
	private final T prototype;
	


}
