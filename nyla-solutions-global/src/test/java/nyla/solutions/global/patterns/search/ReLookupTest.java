package nyla.solutions.global.patterns.search;

import java.util.Map;

import nyla.solutions.global.exception.fault.FaultError;
import nyla.solutions.global.patterns.search.ReLookup;
import junit.framework.TestCase;

public class ReLookupTest extends TestCase
{

	public void testLookup()
	{
		ReLookup<FaultError> lookup = new ReLookup<FaultError>();
		
		assertTrue(lookup instanceof Map);
		
		lookup.put("(001)*.*Green.*${AND}${NOT}.*Blue.*", new FaultError("0001","ERROR"));
		lookup.put("(002)*.*Green.*${AND}.*Blue.*", new FaultError("0002","GB"));
		lookup.put("(003)*.*Blue.*", new FaultError("0003","BLUE"));
		
		assertEquals(lookup.get("Green").getCode(), "0001");
		assertEquals(lookup.get("Blue Green").getCode(), "0002");
		assertEquals(lookup.get("Blue with Live of pure").getCode(), "0003");
	}

}
