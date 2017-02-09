package nyla.solutions.core.patterns.servicefactory;


import org.junit.Assert;
import org.junit.Test;
import nyla.solutions.core.security.user.data.UserProfile;

/**
 * Test for the service factory
 * @author Gregory Green
 *
 */
public class ServiceFactoryTest
{
	/**
	 * Test get instance
	 */
	@Test
	public void testGetInstance()
	{
		Assert.assertNotNull(ServiceFactory.getInstance());
	}//------------------------------------------------

	@Test
	public void testGetInstanceString()
	{
		ServiceFactory factory = ServiceFactory.getInstance("config.properties");
		Assert.assertNotNull(factory);
	}//------------------------------------------------

	@Test
	public void testGetInstanceClassOfQ()
	{
		ServiceFactory factory = ServiceFactory.getInstance(ConfigServiceFactory.class);
		Assert.assertNotNull(factory);
		
	}//------------------------------------------------

	@Test
	public void testGetInstanceClassOfQString()
	{
		Assert.assertNotNull(ServiceFactory.getInstance("config.properties"));
	}//------------------------------------------------
	/**
	 * Testing factory.create(UserProfile.class, "userProfileTest");
	 */
	@Test
	public void testCreateClassOfQ()
	{
		ServiceFactory factory = ServiceFactory.getInstance();
		UserProfile users = factory.create(UserProfile.class, "userProfileTest");
		Assert.assertNotNull(users);
	}//------------------------------------------------
	/**
	 * testCreateClassOfQString
	 */
	@Test
	public void testCreateClassOfQString()
	{
		ServiceFactory factory = ServiceFactory.getInstance();
		UserProfile users = factory.create(UserProfile.class);
		Assert.assertNotNull(users);
	}//------------------------------------------------

	@Test
	public void testCreateString()
	{
		ServiceFactory factory = ServiceFactory.getInstance();
		Assert.assertNotNull(factory.create("userProfileTest"));
	}//------------------------------------------------
	/**
	 * Test  createForNames
	 */
	@Test
	public void testCreateForNames()
	{
		ServiceFactory factory = ServiceFactory.getInstance();
		
		UserProfile[] users = new UserProfile[3];
		String[] names = {"userProfileTest","userProfileTest","userProfileTest"};
		factory.createForNames(names, users);
		
		for (UserProfile userProfile : users)
		{
			Assert.assertNotNull(userProfile);
		}
	}//------------------------------------------------

	@Test
	public void testCreateStringObjectArray()
	{
		ServiceFactory factory = ServiceFactory.getInstance();
		
	  String email = "email", loginID ="login", firstName = "fn",
		    lastName = "ln";
	   Object[] params = { email, loginID,firstName,lastName};
	   
		UserProfile user = factory.create("userProfileTest", params);
		
		Assert.assertNotNull(user);
		Assert.assertEquals("email", user.getEmail());
		Assert.assertEquals("login", user.getId());
		Assert.assertEquals("ln", user.getLastName());
		Assert.assertEquals("fn", user.getFirstName());
	}//------------------------------------------------
	/**
	 * Test create string object
	 */
	@Test
	public void testCreateStringObject()
	{
		ServiceFactory factory = ServiceFactory.getInstance();
		
		UserProfile users = factory.create("userProfileTest",null);
		Assert.assertNotNull(users);
	}//------------------------------------------------

}
