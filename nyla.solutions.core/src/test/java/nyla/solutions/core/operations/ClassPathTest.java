package nyla.solutions.core.operations;

import java.io.File;

import org.junit.Ignore;

import junit.framework.TestCase;
import nyla.solutions.core.operations.ClassPath;

@Ignore
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
							        
		Class<?> keyClass  = classPath.loadClass("nyla.solutions.core.security.user.data.UserProfiler");
		
		
		File file = new File("bin/nyla/solutions/global/security/user/data/UserProfile.class");
		keyClass  = classPath.loadClass(expectedClassName, file);
		
		assertNotNull(keyClass);
		
	}// -----------------------------------------------

}
