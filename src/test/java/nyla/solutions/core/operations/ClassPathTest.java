package nyla.solutions.core.operations;


import java.util.Date;

import org.junit.Test;

import junit.framework.TestCase;
import nyla.solutions.core.operations.ClassPath;
import nyla.solutions.core.security.user.data.UserProfile;

public class ClassPathTest extends TestCase
{
	
	/**
	 * Test the creation of classes
	 * @throws Exception
	 */
	public void testClasspath() throws Exception
	{
		ClassPath classPath = new ClassPath();
		String expectedClassName = "nyla.solutions.core.security.user.data.UserProfile";
							        
		Class<?> keyClass  = classPath.loadClass(expectedClassName);
		
		
//		File file = new File("bin/nyla/solutions/global/security/user/data/UserProfile.class");
//		keyClass  = classPath.loadClass(expectedClassName, file);
		
		assertNotNull(keyClass);
		
	}// -----------------------------------------------
	
	@Test
	public void testNewInstance() throws Exception
	{
		UserProfile up = ClassPath.newInstance(UserProfile.class);
		
		assertNotNull(up);
	}
	@Test
	public void testNewInstanceFloat() throws Exception
	{
		assertNotNull(ClassPath.newInstance(float.class));
		assertNotNull(ClassPath.newInstance(Float.class));
		
	}
	@Test
	public void testNewInstanceDouble() throws Exception
	{
		assertNotNull(ClassPath.newInstance(Double.class));
		assertNotNull(ClassPath.newInstance(double.class));
		
	}
	@Test
	public void testNewInstanceLong() throws Exception
	{
		assertNotNull(ClassPath.newInstance(Long.class));
		assertNotNull(ClassPath.newInstance(long.class));
		
	}
	@Test
	public void testNewInstanceDate() throws Exception
	{
		assertNotNull(ClassPath.newInstance(Date.class));
		
	}
	@Test
	public void testNewInstanceChar() throws Exception
	{
		assertNotNull(ClassPath.newInstance(char.class));
		assertNotNull(ClassPath.newInstance(Character.class));
		
	}


}
