package nyla.solutions.core.data;

import junit.framework.TestCase;
import nyla.solutions.core.data.DataRow;
import nyla.solutions.core.data.DataRowCreator;
import nyla.solutions.core.security.user.data.UserProfile;
import nyla.solutions.core.util.JavaBean;

public class DataRowJavaBeanCreateVisitorTest extends TestCase
{

	public DataRowJavaBeanCreateVisitorTest(String name)
	{
		super(name);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
	}

	public void testDataRowJavaBeanCreateVisitor()
	{
		DataRowCreator v = new DataRowCreator();
		
		UserProfile greg = new UserProfile();
		greg.setEmail("gregory.green@nyla-solutions.com");
		greg.setFirstName("Gregory");
		greg.setLastName("Green");
		greg.setLoginID("greeng3");
		greg.setTitle("Senior Consultant");
		
		JavaBean.acceptVisitor(greg,v);
		
		DataRow dataRow = v.getDataRow();
		
		assertEquals(dataRow.retrieveString("email"),"gregory.green@nyla-solutions.com" );
		assertEquals(dataRow.retrieveString("firstName"),"Gregory" );
		assertEquals(dataRow.retrieveString("lastName"),"Green" );
		assertEquals(dataRow.retrieveString("title"),"Senior Consultant" );
		
	}

}
