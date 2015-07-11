package nyla.solutions.global.operations;

import java.io.File;

import org.junit.Ignore;

import nyla.solutions.global.operations.ClassPath;
import junit.framework.TestCase;

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
		String expectedClassName = "nyla.solutions.global.security.user.data.UserProfile";
							        
		Class<?> keyClass  = classPath.loadClass("nyla.solutions.global.security.user.data.UserProfiler");
		
		
		File file = new File("bin/nyla/solutions/global/security/user/data/UserProfile.class");
		keyClass  = classPath.loadClass(expectedClassName, file);
		
		assertNotNull(keyClass);
		
	}// -----------------------------------------------

}
