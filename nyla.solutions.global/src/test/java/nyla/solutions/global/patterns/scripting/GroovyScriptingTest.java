package nyla.solutions.global.patterns.scripting;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import nyla.solutions.core.patterns.decorator.ScriptingText;
import nyla.solutions.core.util.Text;

public class GroovyScriptingTest
{

	public GroovyScriptingTest()
	{
	}

	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void testInterpret()
	{
		GroovyScripting<String,Object> groovyScripting = new GroovyScripting<String,Object>();
		
		String results = groovyScripting.interpret("System.out.println('hello'); "
		+"return new nyla.solutions.global.patterns.scripting.GroovyScriptingTest().getName();", null);
		
		Assert.assertEquals("GroovyScriptingTest",results );
		
		HashMap<String,String> variables = new HashMap<String,String>();
		variables.put("hello", "world");
		groovyScripting.setVariables(variables);
		
		Assert.assertEquals("world",groovyScripting.interpret("return hello;", null));
		
		
		Assert.assertEquals("Nyla Test",
							groovyScripting.interpret(
									"return "+groovyScripting.getEvaluationObjectPropertyName()+";", 
										"Nyla Test"));
		
	}// --------------------------------------------------------
	@Test
	public void testScription()
	{
		ScriptingText scriptingText = new ScriptingText();
		
		GroovyScripting<String,Object> groovyScripting = new GroovyScripting<String,Object>();
		
		scriptingText.setScripting(groovyScripting);
		
		
		String text = "java.util.Map<String,String> map = new java.util.HashMap<String,String>();"
		+"map.put('hello', 'world');"
		+Text.class.getName()+".format('${hello}', map);";
		
		scriptingText.setExpression(text);
		
		
		String results = scriptingText.getText();
		
		Assert.assertEquals("world", results);
	}// --------------------------------------------------------
	
	public String getName()
	{
		return name;
	}


	private final String name = "GroovyScriptingTest";
}
