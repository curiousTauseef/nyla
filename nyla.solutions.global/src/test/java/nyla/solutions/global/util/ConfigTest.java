package nyla.solutions.global.util;


import org.junit.Assert;
import org.junit.Test;

import nyla.solutions.global.exception.ConfigException;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;
import junit.framework.TestCase;

public class ConfigTest extends TestCase
{

	public ConfigTest(String name)
	{
		super(name);
	}

	public void testLoadFromPropertyFile()
	{
		System.setProperty(Config.SYS_PROPERTY, "src/test/resources/config.properties");
		Config.reLoad();
		
		try
		{
			System.setProperty(Config.SYS_PROPERTY, "src/test/resources/config.propertie");
			Config.reLoad();			
			Assert.fail();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}// --------------------------------------------------------
	public void testGetPropertyString()
	{
		//Get a default string property
		//The following assumes;
		//application.name=JUNIT
		String property = Config.getProperty("nyla.solutions.global.patterns.search.quest.QuestFactory.threadCount");
		assertNotNull(property);
		
		//An exception will be thrown if the referenced property does not exist in the property file
		//in this case the ConfigException will be thrown
		try
		{
			property = Config.getProperty("missing.property");
		}
		catch(ConfigException e)
		{
			//valid configuration exception
		}
		
		//Provide a default value if the default value is missing
		property = Config.getProperty("missing.property","default");
		assertEquals("default", property);
		
		
		//Properties can be retrieved by type (boolean, Integer, Character, Long, Bytes)
		//The following assumes;
		//debug=true
		boolean propertyBoolean = Config.getPropertyBoolean("debug");
		assertTrue(propertyBoolean);
		
		//Each getProperty<Type> accepts a default value
		//The following assumes;
		//missing.boolean.property=false
		 propertyBoolean = Config.getPropertyBoolean("missing.boolean.property",false);
		 assertFalse(propertyBoolean);
		 
		 //Config has a user friendly way to associate properties with classes
		 //The properties can be prefixed with the class name
		 //Each getProperty<Type> optional accept the class name as the first argument
		 //The following assumes the property 
		 //solutions.global.util.ConfigTest.integerProperty=24
		 int integerProperty = Config.getPropertyInteger(ConfigTest.class, "integerProperty");
		 assertEquals(24, integerProperty);
		 
		 
		 //Passwords encrypted with the solutions.global.util.Cryption object 
		 //can be retrieved with the Config.getPassword(key) method
		 //An exception will be thrown if the password is not encrypted correctly in the property file
		 //The following is example encrypted password stored in the property file
		 //password={cryption} 2 -21 23 12 2 -21 23 12 2 -21 23 12 2 -21 23 12 2 -21 23 12
		 char[] password = Config.getPropertyPassword("password");
		 assertNotNull(password);
		 
		 
		 //Properties in the System.getProperties() can be merged with the Config's object properties
		 //This is done by setting the property
		 //solutions.global.util.Config.mergeSystemProperties=true
		 String jvmSystemPropertyName = "user.dir";
		 property = Config.getProperty(jvmSystemPropertyName); 
		 assertNotNull(property);
		 
		 
		 //solutions.globa.util.Config.useFormatting property can be use to dynamically combine properties.
		 //This feature uses the solutions.global.patterns.decorator.style  package (see Styles interface)
		 //The value of property surrounded with ${property.name} will be formatted by replacing it with the
		 //actual value from another property.
		 
		 //The following is based on the following properties (note this combines the system property "user.dir")
		 //solutions.global.util.Config.useFormatting=true
		 //application.name.debug=${application.name}.${debug}.${user.dir}
	 
		 property = Config.getProperty("application.name.debug");
		 Debugger.println(this,"property="+property);
		
		assertTrue("All values formatted:"+property, property.indexOf("${") < 0);
	}// --------------------------------------------------------
	
	@Test
	public void testGetDouble() throws Exception
	{
		
		Assert.assertTrue(30.5 == Config.getPropertyDouble("nyla.solutions.global.util.ConfigTest.test.config.double").doubleValue());
		Assert.assertTrue(30.5 == Config.getPropertyDouble(this.getClass(),"test.config.double").doubleValue());
		Assert.assertTrue(5.55 == Config.getPropertyDouble("doesnotexits",5.55).doubleValue());
	}

}
