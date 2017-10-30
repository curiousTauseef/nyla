package nyla.solutions.core.net.ftp;

import java.io.IOException;

import junit.framework.TestCase;

/**
 * @author Gregory Green
 *
 */
public class FtpTest extends TestCase
{

	/**
	 * @param name
	 */
	public FtpTest(String name)
	{
		super(name);
	}

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception
	{
		super.setUp();
	}

	public void testGet()
	throws IOException
	{
		//Ftp ftp = Ftp.getInstance();
		
		/*ftp.setUsername("pharmatech01");
		ftp.setPassword("Rb13rEg".toCharArray());
		ftp.setHost("172.16.89.169");
		*/
		
		//ftp.setHost("localhost")
		
		/*ftp.setSecured(true);
		ftp.setUsername("grid");
		ftp.setPassword("security".toCharArray());
		ftp.setHost("sclo157.lss.emc.com");
		*/
		//ftp.setUseEpsvWithIPv4(true);
		//ftp.connect();
		
		
		//ftp.setKeepAliveTimeout(20000);
		//ftp.setControlKeepAliveReplyTimeout(20000);
		//ftp.setUseEpsvWithIPv4(true);
		
		//ftp.setBinaryTransfer(true);
		//ftp.setBinaryTransfer(false);
		
		/*
		Assert.assertTrue(ftp.existFile("/testing", "New Text Document.txt"));
		Assert.assertTrue(ftp.existFile("", "input.sdf"));
		
		ftp.executeGet("runtime/tmp/local.sdf", "input.sdf");
		
		Assert.assertTrue(!ftp.existFile("/testing", "invalid.txt"));
		
		ftp.disconnect();
		*/
		
	}// --------------------------------------------------------

}
