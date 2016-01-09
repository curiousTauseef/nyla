# NYLA Solutions Global
This Java API provides support for basic application utilities (application configuration, 
data encryption, debugger, text processing and more). 

# Solutions Global	Overview


##	Package: nyla.solutions.global.util
###	Config

This class provides a central mechanism for applications to access key/value property settings and encrypted passwords. There are several ways to specify the configuration property file location.
 
	1.	Add file config.properties to CLASSPATH. This file will be loaded as a Java resource bundle. 
	2.	Add the JVM argument -Dconfig.properties where the value is equal to the location of the configuration file.

	Example: -Dconfig.properties=/dev/configurations/files/system/config.properties

There are methods to get the String value property such as Config.getProperty(key) method. There are also methods to get an expected property value of a type such as Integer, Boolean, etc.

	nyla.solutions.global.util.Config.mergeSystemProperties=false

It also supports formatting several property values into a single property by adding the following property;
 
	nyla.solutions.global.util.Config.useFormatting=true

 
By default the configuration is read only once when the application is initialized. Add the following to the configuration property file to always reload the property whenever a getProperty... method is called. Note that this is a potentially an expensive operation.
 
	nyla.solutions.global.util.Config.alwaysReloadProperties=true

Note the following is a property file used for the sample usage code below.

	
	application.name=JUNIT
	debug=true
	nyla.solutions.global.util.ConfigTest.integerProperty=24
	password={cryption}102 42 -22 24 12 66 -35 89 50 -15 21 9 -67 73 -128 -105
	
	nyla.solutions.global.util.Config.mergeSystemProperties=true
	
	nyla.solutions.global.util.Config.useFormatting=true
	application.name.debug=${application.name}.${debug}.${user.dir}


*USAGE*

```
 //Get a default string property
  //The following assumes;
  //application.name=JUNIT
  String property = Config.getProperty("application.name");
  Assert.assertEquals("JUNIT",property);
  
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
  Assert.assertEquals("default", property);
  
  //Properties can be retrieved by type (boolean, Integer, Character, Long, Bytes)
  //The following assumes;
  //debug=true
  boolean propertyBoolean = Config.getPropertyBoolean("debug");
  Assert.assertTrue(propertyBoolean);
  
  //Each getProperty<Type> accepts a default value
  //The following assumes;
  //missing.boolean.property=false
   propertyBoolean = Config.getPropertyBoolean("missing.boolean.property",false);
   Assert.assertFalse(propertyBoolean);
   
   //Config has a user friendly way to associate properties with classes
   //The properties can be prefixed with the class name
   //Each getProperty<Type> optional accept the class name as the first argument
   //The following assumes the property 
   //nyla.solutions.global.util.ConfigTest.integerProperty=24
   int integerProperty = Config.getPropertyInteger(nyla.solutions.global.util.ConfigTest.class, "integerProperty");
   Assert.assertEquals(24, integerProperty);
   
   
   //Passwords encrypted with the nyla.solutions.global.util.Cryption object 
   //can be retrieved with the Config.getPassword(key) method
   //An exception will be thrown if the password is not encrypted correctly in the property file
   //The following is example encrypted password stored in the property file
   //password={cryption} 2 -21 23 12 2 -21 23 12 2 -21 23 12 2 -21 23 12 2 -21 23 12
   char[] password = Config.getPropertyPassword("password");
   Assert.assertNotNull(password);
   
   
   //Properties in the System.getProperties() can be merged with the Config's object properties
   //This is done by setting the property
   //nyla.solutions.global.util.Config.mergeSystemProperties=true
   String jvmSystemPropertyName = "user.dir";
   property = Config.getProperty(jvmSystemPropertyName); 
   Assert.assertNotNull(property);
   
   
   //solutions.globa.util.Config.useFormatting property can be used to dynamically combine properties.
   //This feature uses the nyla.solutions.global.patterns.decorator.style  package (see Styles interface)
   //The value of property surrounded with ${property.name} will be formatted by replacing it with the
   //actual value from another property.
   
   //The following is based on the following properties (note this combines the system property "user.dir")
   //nyla.solutions.global.util.Config.useFormatting=true
   //application.name.debug=${application.name}.${debug}.${user.dir}

   property = Config.getProperty("application.name.debug");
   Debugger.println(this,"property="+property);
  
  Assert.assertTrue("All values formatted:"+property, property.indexOf("${") < 0);
```


###	Cryption

Cryption provides a set of functions to encrypt and decrypt bytes and text. It uses the javax.crypto package. 

The default encryption algorithm is the Advanced Encryption Standard (AES).

The default algorithm can be changed with a configuration property named nyla.solutions.global.util.Cryption.alogorithm.

	# The following sets the encryption algorithm to Data Encryption Standard (DES)
	nyla.solutions.global.util.Cryption.algorithm=DES

The Cryption object is used by nyla.solutions.global.util.Config object to decrypt properties prefixed with {cryption}. The Cryption class can be used to generate encrypted passwords that can be added to the config.properties file. The Cryption main method accepts a password and will print the encrypted password that can be added to the property file.

The printed password will be prefixed with the value “{cryption}”. Any properties prefixed with {cryption} in the config.properties is an indicator that content is encrypted.

The follow is a sample Cryption UNIX script:

```
export LIB_DIR=put correct directory here
export CP="$LIB_DIR/solution.global.jar"
java -classpath $CP nyla.solutions.global.util.Cryption $1
```

The following is a sample output of an encrypted password generated by the Cryption main method.

	{cryption}23 4 -3 -77 -128 -88 -34 -105 23 4 -3 -77 -128 -88 -34 -105
	
*USAGE*

```
//The cryption default constructor using the 
//AES algorithm with a default key
Cryption cryption = new Cryption();

//Use the encryptText method to encrypt strings
String original = "Text to encrypt";
String encrypted = cryption.encryptText(original);
Assert.assertTrue(!original.equals(encrypted));

//Use the decryptText method to decrypt strings
String decrypted = cryption.decryptText(encrypted);
Assert.assertEquals(decrypted, original);

//Use encrypt for bytes
byte[] orginalBytes = original.getBytes();
byte[] encryptedBytes = cryption.encrypt(orginalBytes);

//Use decrypt 
byte[] decryptedBytes = cryption.decrypt(encryptedBytes);
Assert.assertTrue(Arrays.equals(orginalBytes, decryptedBytes));


//Create crypt with a specific key and algorithm
byte[] keyBytes = {0x22, 0x15, 0x27, 0x36, 0x41, 0x11, 0x79, 0x76};
Cryption desCryption = new Cryption(keyBytes,"DES"); 

String desEncrypted = desCryption.encryptText(original);
Assert.assertTrue(!original.equals(encrypted) && !desEncrypted.equals(encrypted));
decrypted = desCryption.decryptText(desEncrypted);
Assert.assertEquals(decrypted, original);
```

###	Debugger

Debugger provides useful methods for obtaining exception stack traces.  It can build reader friendly strings for objects that do not implement their toString method.

It also provides a set of print functions to log DEBUG, INFO, WARN and FATAL level messages using the Debugger.println(...), Debugger.printInfo(...),Debugger.printWarn(...) and Debugger.printFatal(...) methods respectively.
 
 The default log object implementation is nyla.solutions.global.operations.Log4J.
 
 Set the configuration property to plug-in another logger (@see Config more information);
 
nyla.solutions.global.util.Debugger.logClass=className

The logClass class name indicated must implement the nyla.solutions.global.operations.Log interface.

*USAGE*

```
//The Debugger toString(Object) can be used to debug objects where the toString method is not implemented.
String[] arraysNicely = { "first","second"};
String text = Debugger.toString(arraysNicely); 
Assert.assertEquals("{[0]=first ,[1]=second}", text);

//The print method wraps log levels of DEBUG,INFO,WARN and FATAL
Debugger.printInfo("This is a INFO level message");

//Two arguments can be passed where the first is the calling object
//The debugger will prepend the calling objects class name to the logged output
Debugger.println(this,"This is a DEBUG level message");

//Debugger can be used to efficiently print exception information
text = null;
try{
text.toString(); //causes a null pointer exception
}
catch(NullPointerException e)
{
//Use the stackTrace method to get the string version of the exception call stack
String stackTrace = Debugger.stackTrace(e);

//Print warn level
Debugger.printWarn(this,stackTrace);

//stack trace will be automatically created if the exception object is passed
Debugger.printError(e); 

Debugger.printFatal(this,"Stack trace will not be printed, because this is not an exception object.");
}
```

Sample Debugger Properties (Config.properties)
 
	#-------------------------------
	# Debugger log instance uses Log4J
	# You can directly include the log4J properties in the configuration property files (@see Config object)
	# Log4J properties
	#
	log4j.rootLogger=DEBUG, stdout
	log4j.logger.PACKAGE_NAME=ERROR,file_error
	log4j.logger.YYY=DEBUG, file_all
	log4j.logger.org.apache=ERROR,stdout
	log4j.logger.org.springframework=ERROR,stdout 	
 	
	#Standard OUT
	log4j.appender.stdout=org.apache.log4j.ConsoleAppender
	log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
	#log4j.appender.stdout.layout.ConversionPattern=%d [%F:%L] - %x %m%n
	#log4j.appender.stdout.layout.ConversionPattern= %p: %d{HH:mm:ss} [%F:%L] - %x %m%n
	#log4j.appender.stdout.layout.ConversionPattern=%d{HH:mm:ss} [%c:%L] %m%n
	#log4j.appender.stdout.layout.ConversionPattern=%d{HH:mm:ss} %m%n
	log4j.appender.stdout.layout.ConversionPattern=%p: %d [%c] - %m%n
	 
	#
	# FILE Output
	log4j.file_all.category=DEBUG
	log4j.appender.file_all=org.apache.log4j.RollingFileAppender
	log4j.appender.file_all.File=/temp/logs/system.log
	log4j.appender.file_all.MaxFileSize=10MB
	log4j.appender.file_all.MaxBackupIndex=3
	log4j.appender.file_all.layout=org.apache.log4j.PatternLayout
	log4j.appender.file_all.layout.ConversionPattern=%p: %d [%c] - %m%n
	 	
	 	
	#
	# FILE Output
	#log4j.file_error.category=ERROR
	log4j.appender.file_error=org.apache.log4j.RollingFileAppender
	log4j.appender.file_error.File=temp/logs/error.log
	log4j.appender.file_error.MaxFileSize=10MB
	log4j.appender.file_error.MaxBackupIndex=3
	log4j.appender.file_error.layout=org.apache.log4j.PatternLayout
	log4j.appender.file_error.layout.ConversionPattern=%p: %d [%c] - %m%n
	 	
	 	
	#Emailing example
	#email appender
	log4j.appender.mail=org.apache.log4j.net.SMTPAppender
	log4j.appender.mail.BufferSize=1
	log4j.appender.mail.SMTPHost=smtp.myservername.xx
	log4j.appender.mail.From=fromemail@myservername.xx
	log4j.appender.mail.To=toemail@myservername.xx
	log4j.appender.mail.Subject=Log ...
	log4j.appender.mail.threshold=error
	log4j.appender.mail.layout=org.apache.log4j.PatternLayout
	log4j.appender.mail.layout.ConversionPattern=%d{ABSOLUTE} %5p %c{1}:%L - %m%n


### BeanComparator

BeanComparator is a generic Bean property java.util.Comparator implementation. It compares specified property beans using reflection. This object is internally used by the Organizer.sortByJavaBeanProperty(String,Collection) method.

*USAGE*

```
//The constructor accepts a JavaBean property name
BeanComparator beanComparator = new BeanComparator("firstName");

//The following are two sample user profile beans
UserProfile josiah = new UserProfile();
josiah.setFirstName("Josiah");
		
UserProfile nyla = new UserProfile();
nyla.setFirstName("Nyla");
		 
//Reflection is used to compare the properties of the beans
Assert.assertTrue(beanComparator.compare(josiah, nyla) < 0);
		 
//The following shows how the BeanComparator.sort method can be used
		 
//This method can be used to sort an given collection based on the JavaBean properties 
//of objects in the collection
ArrayList<UserProfile> unSorted = new ArrayList<UserProfile>();
unSorted.add(0, nyla);
unSorted.add(1, josiah);
		 
//Setting the descending will determine the sort order
beanComparator.setDescending(true);
beanComparator.sort(unSorted);

Assert.assertTrue(unSorted.get(0) == nyla);
		 
//Changing the descending flag changes the output of the sort method
beanComparator.setDescending(false);
beanComparator.sort(unSorted);
Assert.assertTrue(unSorted.get(0) == josiah);
```

### Text

Text is geared toward string based processing. It includes template engine support like Free Marker that builds composite strings/values dynamically at runtime (see http://freemarker.sourceforge.net/). There are also methods to support complex regular expressions with Boolean AND, OR and NOT logic, numerous string conversions, general text manipulation and parsing methods.

*USAGE*

```
//Format text replacing template place-holders prefixed with ${ and suffixed by } 
//with the corresponding values in a map.
String text = "${company} A2D2 Solution Global Application Testings";
Map<String,String> map = new HashMap<String,String>();
map.put("company", "EMC");
text = Text.formatText(text,map);
Assert.assertEquals("EMC A2D2 Solution Global Application Testings", text);


//Use complex text matching boolean logic to regular expressions by adding ${AND}, ${NOT} and $OR} tags
Assert.assertTrue(Text.matches("Kenya Africa", ".*Kenya.*"));
Assert.assertFalse(Text.matches("Kenya", "${NOT}.*Kenya.*"));
Assert.assertTrue(Text.matches("Kenya", "${NOT}${NOT}.*Kenya.*"));
Assert.assertFalse(Text.matches("America, Kenya, Paris", ".*Paris.*${AND}.${NOT}.*Kenya.*"));
Assert.assertFalse(Text.matches("America, Kenya, Paris", "(.*Paris.*${AND}.${NOT}.*Kenya.*)${OR}(.*Paris.*${AND}.${NOT}.*Kenya.*)"));
Assert.assertTrue(Text.matches("United States, Kenya, France", "${NOT}.*America.*${AND}.*Kenya.${NOT}.*Paris.*"));
Assert.assertTrue(Text.matches("United States, Kenya, France", "${NOT}.*America.*${AND}.*Kenya.${NOT}.*Paris.*"));

//Use the parse method to retrieve one or more token between a start and end strings
//Note the parse method can be used with non-regular expressions
String start = "Color:";
String end = ";";
Collection collection = Text.parse("Color:green; Weight:155oz; Color:Blue; Weight:23oz", start, end);
Assert.assertEquals(2,collection.size()); //two color
Iterator i  = collection.iterator();
Assert.assertEquals("green", i.next()); //first is green
Assert.assertEquals("Blue", i.next()); //second is Blue

//There methods to count of a given character
int count = Text.characterCount('A', "All Apples");
Assert.assertEquals(2, count);

//There are methods the get digit counts
count = Text.digitCount(text);
Assert.assertEquals(2, count);

//Format text numbers/decimals
String format = "#,###,###.###";
String formattedText = Text.formatNumber(123443243240.033423,format);
Assert.assertEquals("123,443,243,240.033",formattedText);

//Format text currency
formattedText = Text.formatCurrency("1000.33");
Assert.assertEquals("$1,000.33",formattedText);

//format text percentages
formattedText = Text.formatPercent("2.3");
Assert.assertEquals("2.3%",formattedText);

//Use grep to search for expressions across multiple lines in a string
text = "Greg on line 1\nGreen on line two";
String results = Text.grepText("Green", text);
Assert.assertEquals("Green on line two",results);
```
## Patterns

##nyla.solutions.global.patterns.search

###ReLookup

 ReLookup is a map that supports searching for values using a complex regular expression syntax. The key is a regular expression. This operation is similar to the lookup operation. The RELookup will iterate through the given expressions looking for a match on the corresponding source attribute. The value  of the lookup is used if the regular expression matches the source attribute value.


* Complex Regular Expression (And/Not) *
By default, regular expressions do not have an easy way to chain expressions together using AND/NOT logic. The OR logical expression is supported with the character “|”. The RELookup operation combines regular expressions with a special syntax to support AND/NOT logic.

#### AND Operation

The RELookup supports chaining expressions together with “AND” logic. This is accomplished by chaining expressions together with “${AND}”. The string “${AND}” can be used to separate two regular expressions. If any of the regular expressions return false then the entire regular expression is false. In the following example, the regular expression “.*USA.*${AND}.*Greece.*”, only returns true if the text contains both “USA” and “Greece”. 

| Complex RE 				|	Value 			|	Matches |
  ---------   			    |	-----   		|    -----
| .*USA.*${AND}.*Greece.*	|  USA and Greece | True		|
| .*USA.*${AND}.*Greece.*	| USA	          | False		|
| .*USA.*${AND}.*Greece.* 	|	Greece	      | False		|
| .*USA.*${AND}.*Greece.* 	|	Greece USA    | True		|

#### 	NOT Operation
The RELookup supports negative logic (NOT) for expressions. This is accomplished by prefixing the expressions with “${NOT}”. In the following example, the regular expression “.*USA.*” only returns true if the text does not contain the word “USA”. Note that multiple “${NOT}”(s) can be chained together with “${AND}”(s) (see table below).

| Complex RE 					|	Value 			|	Matches | 
| -----------					|	-------			| ---------- | 
| ${NOT}.*USA.*					| 	USA and Greece 	|	False | 
| ${NOT}.*USA.*					| USA				|	False | 
| ${NOT}.*USA.*					| Greece 			|	True  | 
| ${NOT}.*USA.*					| Greece USA 		| 	False | 
| .*Greece.*${AND}${NOT}.*USA.* ${AND}${NOT}.*Turkey.* 	|	Greece Turkey	| False | 
| .*Greece.*${AND}${NOT}.*USA.* ${AND}${NOT}.*Turkey.*	| Greece Africa		| True	| 


*USAGE*

	ReLookup<FaultError> lookup = new ReLookup<FaultError>();

	assertTrue(lookup instanceof Map);
		
	lookup.put("(001)*.*Green.*${AND}${NOT}.*Blue.*", new FaultError("0001","ERROR"));
	lookup.put("(002)*.*Green.*${AND}.*Blue.*", new FaultError("0002","GB"));
	lookup.put("(003)*.*Blue.*", new FaultError("0003","BLUE"));
		
	assertEquals(lookup.get("Green").getCode(), "0001");
	assertEquals(lookup.get("Blue Green").getCode(), "0002");
	assertEquals(lookup.get("Blue with Live of pure").getCode(), "0003");
	
# COMMAS
	

The **COMMAS** stands for **COMM**and **A**nnotation**S**.  COMMAS is a service/method execution framework that allows before and after advice to be applied during method execution. Each methods is converted into a a Command interface.  The before and after advice is simulates a light weight Aspect Oriented Programming (AOP) framework.


Each command is registered with the CommasServiceFactory. Add the following to the config.properties to configuration the CommasServiceFactory.

	
The Commas service factory looks for Command classes under a provided list of package roots. The package roots are set in the config.properties. The following is an example of the property. Note that each package root is separated by spaces.

If no packages are specified in config properties then the default is package nyla.solutions.commas will be used;

	#Configuration Properties
	#Package root separate by spaces
	nyla.solutions..commas.CommasServiceFactory.packageRoots=demo.package1 demo.package2

All Commas object classes must be given the @COMMAS annotation.

	import solutions.gedi.commas.annotations.Service;
	@COMMAS
	public class DemoRmiService implements Command


The following is the Command interface 

	package nyla.solutions.global.patterns.command;
	/**
	 * This is an abstract interface to execute an command operation.
	 *
	 */
	public interface Command<ReturnType  extends Object,InputType extends Object>
	{
		/**
		 * Implemented command interface to execute an operation on a argument and possibility return values
		 * @param input the input object 
		 * @return the altered result
		 */
		ReturnType execute(InputType input);
	
	}

The framework supports classes that implement the Command interface or POJOs.

**Performance Tip:** Object commands that implement the Command interface will generally perform faster than POJOs. All POJOs are executed using proxy reflections. This adds additional execution overhead.

	@COMMAS
	public class SayHelloService implements Command<String,Object>
	{
		@CMD(name="sayHello")
		@Override
		public String execute(String name)
		{
		
			return "Hello "+name;
		}
	}
	
The @COMMAS annotation is used at a object level to determine which objects may have its methods converted to Commands with advice injected into its execution. 



**Example Code**

    package nyla.solutions.global.demo.commas;
	
	import java.util.ArrayList;
	import java.util.Collection;
	
	import nyla.solutions.global.data.Criteria;
	import nyla.solutions.global.patterns.command.commas.annotations.Attribute;
	import nyla.solutions.global.patterns.command.commas.annotations.CMD;
	import nyla.solutions.global.patterns.command.commas.annotations.COMMAS;
	import nyla.solutions.global.patterns.command.remote.partitioning.RmiAllRoutesAdvice;
	import nyla.solutions.global.patterns.command.remote.partitioning.RmiOneRouteAdvice;
	import nyla.solutions.global.security.user.data.User;
	import nyla.solutions.global.security.user.data.UserProfile;
	import nyla.solutions.global.util.Debugger;

	@COMMAS
	public class RealSingleRouteCommand
	{
		@CMD(advice=RmiOneRouteAdvice.ADVICE_NAME,
				targetName="molecules",
				attributes={@Attribute(name=RmiOneRouteAdvice.LOOKUP_PROP_ATTRIB_NAME,value="primaryKey")})
		public Collection<User> findUsers(Criteria criteria)
		{
			Debugger.println(this,"finderUsers("+criteria+")");
			ArrayList<User> users = new ArrayList<User>();
			
			User nyla = new UserProfile("nyla@emc.com","nyla","Nyla","Green");
			
			users.add(nyla);
			
			return users;
		}
	
		
		@CMD(advice=RmiAllRoutesAdvice.ADVICE_NAME)
		public Collection<User> findUsersEveryWhere(Criteria criteria)
		{
			Debugger.println(this,"finderUsers("+criteria+")");
			ArrayList<User> users = new ArrayList<User>();
			
			String serverName = System.getProperty(RealSingleRouteCommand.class.getName()+".findUsersEveryWhere.serverName");
			User nyla = new UserProfile("nyla@"+serverName,"nyla","Nyla","Green");
			users.add(nyla);
			
			return users;
		}
	}

Each @COMMAS annotated object corresponds to a namespace. The default namespace contains the package.className. In the example above the name default is `nyla.solutions.global.demo.commas.RealSingleRouteCommand`.
Use the name attribute to change the namespace. Example @COMMAS(name=”nameOfService”). 

Each method can be given an @CMD annotation.
The method level annotation (@CMD) also have a name attribute. This name matches the registered Command method name. The exact method name is used if the name attribute is not provided. In the following example, the full command name is *nyla.solutions.global.demo.commas.findUsers".

	@CMD(advice=RmiOneRouteAdvice.ADVICE_NAME,
					targetName="molecules",
					attributes={@Attribute(name=RmiOneRouteAdvice.LOOKUP_PROP_ATTRIB_NAME,value="primaryKey")})
	public Collection<User> findUsers(Criteria criteria)



The following is definition for the CMD annotation.

	package nyla.solutions.global.patterns.command.commas.annotations;
	...
	/**
	 * Indicates that the execute command method
	 * 
	 * @author Gregory Green
	 */
	@Target({ElementType.METHOD})
	@Retention(RetentionPolicy.RUNTIME)
	@Inherited
	@Documented
	public @interface CMD
	{
		/** The unique name .  Please note that  names can be overwritten by 
		 * other modules with the same name.
		 * @return the module name
		 */
		public String name() default "";
		
		/**
		 * Placeholder for a controller method execution pattern 
		 * 
		 * Default "controller"
		 * @return @see controller
		 */
		public String controller() default "controller";
		
		/**
		 * The placeholder for an input container
		 * @return
		 */
		public String inputName() default "";
		
		/**
		 * @return the name of the target
		 */
		public String targetName() default "";
		
		/**
		 * @return alias names for the methods
		 */
		public String[] aliases() default {};
		
		/**
		 * Description of command method
		 */
		public String notes() default "";
	
		/**
		 * Transaction Type
		 * NONE - no transaction support
		 * READONLY- read (not write) transaction
		 * WRITE - read/write or read transaction data
		 * @return default WRITE
		 */
		public TransactionType transactionType() default TransactionType.WRITE;
		
		/**
		 * Aspect advice name
		 */
		public String advice()  default "";
		
		/**
		 * Command method attributes
		 * @return
		 */
		public Attribute[] attributes() default {};
		
		/**
		 * @return the input class
		 */
		public Class<?> inputClass() default Object.class;
		
		/**
		 * @return the return class
		 */
		public Class<?> returnClass() default Object.class;
	}
	
COMMAS advice is a convention that allows developers to introduce AOP like concerns into Command(s). Developers can introduce concerns such as transformation, security and auditing by implementing the Advice interface. The advice for a method command is specified by "advice" attribute in the CMD annotations.

	@CMD(name="FUNCTION_NAME", advice ="ADVICE_NAME_HERE")
	public Object execute(Object envAndFuncContextArray)
	{
	}

This interface allows before and after processing to be injected for a command. The before and after are represented by objects that implement the Command interface. Each advice will be provided  meta-data by CommasServiceFactory for the Command that it will give advice by the setFacts(CommandFacts) method.

See the Advice interface below.

	package nyla.solutions.global.patterns.aop;
	
	import nyla.solutions.global.patterns.command.Command;
	import nyla.solutions.global.patterns.command.commas.CommandFacts;
	
	/**
	 * Aspect similar to AOP based
	 *
	 */
	public interface Advice
	{
	
		/**
		 * Before advice in the form  a command
		 * @return
		 */
		Command<?,?> getBeforeCommand();
		
		/**
		 * After advice in the form  a command
		 * @return
		 */
		Command<?,?> getAfterCommand();
		
		/**
		 * 
		 * @return the function facts
		 */
		CommandFacts getFacts();
		
		/**
		 * 
		 * @param facts the function facts
		 */
		void setFacts(CommandFacts facts);
	}

The name of the Advice is specified through the Aspect annotation

	package nyla.solutions.global.patterns.command.commas.annotations;
	
	import java.lang.annotation.Documented;
	import java.lang.annotation.ElementType;
	import java.lang.annotation.Inherited;
	import java.lang.annotation.Retention;
	import java.lang.annotation.RetentionPolicy;
	import java.lang.annotation.Target;
	
	@Target({ElementType.TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@Inherited
	@Documented
	public @interface Aspect
	{
	   String name() default "";
	}


#Release Notes

- Updated the Executable interface to be a sub class of Command
- Update BooleanExpression to be a sub class of Command
- Added support for Groovy Scripting instance
- Update to Settings object to allow runtime updates
- Added support for Log4J2

	