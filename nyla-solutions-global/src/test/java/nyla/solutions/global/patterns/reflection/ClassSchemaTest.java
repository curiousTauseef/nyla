package nyla.solutions.global.patterns.reflection;

import nyla.solutions.global.patterns.reflection.ClassSchema;
import nyla.solutions.global.patterns.reflection.TypeSchema;
import nyla.solutions.global.util.Debugger;
import junit.framework.TestCase;

public class ClassSchemaTest extends TestCase
{

	public ClassSchemaTest(String name)
	{
		super(name);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
	}// -----------------------------------------------
	/**
	 * Class construction testing
	 */
	public void testClassSchemaClass()
	{
		ClassSchema classSchema = new ClassSchema(SchemaUT.class);
		
		TypeSchema[] fieldSchemas = classSchema.getFieldSchemas();
		
		assertNotNull(fieldSchemas);
		assertTrue(SchemaUT.class.getDeclaredFields().length > 0 );
		assertEquals(SchemaUT.class.getDeclaredFields().length, fieldSchemas.length);
		
		Debugger.println(classSchema);
	}// -----------------------------------------------

}
