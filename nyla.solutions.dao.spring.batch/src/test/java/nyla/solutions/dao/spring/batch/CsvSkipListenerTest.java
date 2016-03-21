package nyla.solutions.dao.spring.batch;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import nyla.solutions.dao.spring.batch.CsvArrayableSkipListener;
import nyla.solutions.global.data.DataRow;

@Ignore
public class CsvSkipListenerTest
{

	@Test
	public void testOnSkipInProcess()
	{
		CsvArrayableSkipListener skipper = new CsvArrayableSkipListener();
		
		skipper.setSkipInProcessFilePath("${inReadFile}");
		
		Exception exception = new Exception("error");
		
		Object[] inputs = {};
		DataRow dataRow = new DataRow(inputs);
		skipper.onSkipInProcess(dataRow, exception);
	}

	@Test
	public void testOnSkipInRead()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testOnSkipInWrite()
	{
		fail("Not yet implemented");
	}


}
