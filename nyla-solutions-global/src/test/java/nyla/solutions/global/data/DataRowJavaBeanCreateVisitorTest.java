package nyla.solutions.global.data;

import nyla.solutions.global.data.DataRow;
import nyla.solutions.global.data.DataRowCreator;
import nyla.solutions.global.security.user.data.UserProfile;
import nyla.solutions.global.util.JavaBean;
import junit.framework.TestCase;

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
		greg.setEmail("gregory.green@emc.com");
		greg.setFirstName("Gregory");
		greg.setLastName("Green");
		greg.setLoginID("greeng3");
		greg.setTitle("Senior Consultant");
		
		JavaBean.acceptVisitor(greg,v);
		
		DataRow dataRow = v.getDataRow();
		
		assertEquals(dataRow.retrieveString("email"),"gregory.green@emc.com" );
		assertEquals(dataRow.retrieveString("firstName"),"Gregory" );
		assertEquals(dataRow.retrieveString("lastName"),"Green" );
		assertEquals(dataRow.retrieveString("title"),"Senior Consultant" );
		
	}

}
