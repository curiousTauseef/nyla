package nyla.solutions.global.net.ftp;

import java.io.IOException;
import java.nio.charset.Charset;

import nyla.solutions.global.io.IO;
import nyla.solutions.global.util.Config;

/**
 * An abstract FTP object
 * @author Gregory Green
 */
public abstract class Ftp
{
	
	/**
	 * Connect to a FTP server
	 */
	public abstract void connect()
	throws IOException, SecurityException;
	
	/**
	 * Disconnection from the FTP server
	 */
	public abstract void disconnect();

	/**
	 * GET remote path
	 * @param localPath the LOCAL path
	 * @param remotePath the REMOTE path
	 * @throws IOException
	 */
	public abstract void executeGet(String localPath,String remotePath)
	throws IOException;
	

	/**
	 * 
	 * @param path the file PATH
	 * @return true if the file exist
	 */
	public abstract boolean existFile(String directory,String fileRegExp)
	throws IOException;
	
	/**
	 * PUT local file in remote path
	 * @param localPath the LOCAL path
	 * @param remotePath the REMOTE path
	 * @throws IOException
	 */
	public abstract void executePut(String localPath,String remotePath)
	throws IOException;
	
	/**
	 * @return the USER name
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @param username the USER name to set
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}


	/**
	 * @return the password
	 */
	protected char[] getPassword()
	{
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(char[] password)
	{
		if(password == null)
			this.password = null;
		else
			this.password = password.clone();
	}


	/**
	 * @return the useEpsvWithIPv4
	 */
	public boolean isUseEpsvWithIPv4()
	{
		return useEpsvWithIPv4;
	}


	/**
	 * @param useEpsvWithIPv4 the useEpsvWithIPv4 to set
	 */
	public void setUseEpsvWithIPv4(boolean useEpsvWithIPv4)
	{
		this.useEpsvWithIPv4 = useEpsvWithIPv4;
	}


	/**
	 * @return the localActive
	 */
	public boolean isLocalActive()
	{
		return localActive;
	}


	/**
	 * @param localActive the localActive to set
	 */
	public void setLocalActive(boolean localActive)
	{
		this.localActive = localActive;
	}


	/**
	 * @return the binaryTransfer
	 */
	public boolean isBinaryTransfer()
	{
		return binaryTransfer;
	}

	/**
	 * @param binaryTransfer the binary transfer to set
	 */
	public void setBinaryTransfer(boolean binaryTransfer)
	{
		this.binaryTransfer = binaryTransfer;
	}


	/**
	 * @return the host
	 */
	public String getHost()
	{
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host)
	{
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public int getPort()
	{
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port)
	{
		this.port = port;
	}
	
	/**
	 * 
	 * @return the new Ftp instance
	 */
	public static Ftp getInstance()
	{
		return new ApacheFtp();
	}
	
	
	
	/**
	 * @return the controlKeepAliveReplyTimeout
	 */
	public int getControlKeepAliveReplyTimeout()
	{
		return controlKeepAliveReplyTimeout;
	}

	/**
	 * @param controlKeepAliveReplyTimeout the controlKeepAliveReplyTimeout to set
	 */
	public void setControlKeepAliveReplyTimeout(int controlKeepAliveReplyTimeout)
	{
		this.controlKeepAliveReplyTimeout = controlKeepAliveReplyTimeout;
	}

	/**
	 * @return the keepAliveTimeout
	 */
	public int getKeepAliveTimeout()
	{
		return keepAliveTimeout;
	}

	/**
	 * @param keepAliveTimeout the keepAliveTimeout to set
	 */
	public void setKeepAliveTimeout(int keepAliveTimeout)
	{
		this.keepAliveTimeout = keepAliveTimeout;
	}

	/**
	 * @return the secured
	 */
	public boolean isSecured()
	{
		return secured;
	}

	/**
	 * @param secured the secured to set
	 */
	public void setSecured(boolean secured)
	{
		this.secured = secured;
	}
	

	/**
	 * @return the charSet
	 */
	public String getEncoding()
	{
		return charset.name();
	}

	/**
	 * Set this.setCharset(Charset.forName(encoding));
	 * 
	 * @param charSet the charSet to set
	 */
	public void setEncoding(String encoding)
	{
		if(encoding == null || encoding.length() == 0)
			return;
		
		this.setCharset(Charset.forName(encoding));
	}//-------------------------------------------------------------------


	/**
	 * @return the charset
	 */
	public Charset getCharset()
	{
		return charset;
	}

	/**
	 * @param charset the charset to set
	 */
	public void setCharset(Charset charset)
	{
		this.charset = charset;
	}




	private Charset charset = IO.CHARSET;
	private int controlKeepAliveReplyTimeout;
	private int keepAliveTimeout;
	private boolean secured;
	private String username;
	private char[] password;
	private boolean useEpsvWithIPv4;
	private boolean localActive = false;
	private boolean binaryTransfer;
	private String host = Config.getProperty(Ftp.class,"host","");
	private int port;
}
