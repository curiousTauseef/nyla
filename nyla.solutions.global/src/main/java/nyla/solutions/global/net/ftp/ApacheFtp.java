package nyla.solutions.global.net.ftp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import nyla.solutions.global.io.IO;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilters;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.apache.commons.net.util.TrustManagerUtils;


/**
 * FTP implements uses the APACHE COMMONS NET framework
 * @author Gregory Green
 *
 */
public class ApacheFtp extends Ftp
{
	/**
	 * Constructor
	 */
	public ApacheFtp()
	{	
	}// --------------------------------------------------------
	
	/**
	 * 
	 * @param path the file PATH
	 * @return true if the file exist
	 */
	public boolean existFile(String directory,String fileRegExp)
	throws IOException
	{

	
		if(!connected)
			this.connect();
		
		FTPFile[] files = this.ftpClient.listFiles(directory,FTPFileFilters.ALL);
		
		if(files == null || files.length == 0)
			return false;
		
		
		for (int i = 0; i < files.length; i++)
		{
			if(!files[i].isFile())
				continue;
			
			if(files[i].getName().matches(fileRegExp))
				return true;
		}
		
		return false;
	}// --------------------------------------------------------
	/**
	 * Connect to a FTP server
	 */
	public void connect()
	throws IOException, SecurityException
	{
		if(this.isSecured())
		{
			ftpClient = new FTPSClient(false);
			((FTPSClient)ftpClient).setTrustManager(TrustManagerUtils.getAcceptAllTrustManager());
		}
		else
			ftpClient = new FTPClient();
		
		// suppress login details

        ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(
        		new OutputStreamWriter(System.out,IO.CHARSET)), true));
        
		int keepAliveTimeout = this.getKeepAliveTimeout();
		if (keepAliveTimeout > 0) {

            ftpClient.setControlKeepAliveTimeout(keepAliveTimeout);

        }

		int controlKeepAliveReplyTimeout = this.getControlKeepAliveReplyTimeout();
        if (controlKeepAliveReplyTimeout > 0) 
            ftpClient.setControlKeepAliveReplyTimeout(controlKeepAliveReplyTimeout);
        
        
        int port = this.getPort();
        if(port > 0)
        	ftpClient.connect(getHost(), getPort());
        else
        	ftpClient.connect(getHost());
	
		int reply = ftpClient.getReplyCode();

        if (!FTPReply.isPositiveCompletion(reply))
        {
        	disconnect();
        	return;
        }
        
        if (!ftpClient.login(this.getUsername(), new String(getPassword())))
           throw new SecurityException("login failed");
        
        if (isBinaryTransfer())
        {
        	ftpClient.setFileType(FTP.BINARY_FILE_TYPE); 
        }
        else
        {
        	ftpClient.setFileType(FTP.NON_PRINT_TEXT_FORMAT);
        	ftpClient.setAutodetectUTF8(true);
        }
        
        if (isLocalActive()) 
            ftpClient.enterLocalActiveMode();
        else
           	ftpClient.enterLocalPassiveMode();

        ftpClient.setUseEPSVwithIPv4(isUseEpsvWithIPv4());
        
        ftpClient.setCharset(this.getCharset());
        
        this.connected = true;
        
	}// --------------------------------------------------------
	/**
	 * PUT local file in remote path
	 * @param localPath the LOCAL path
	 * @param remotePath the REMOTE path
	 * @throws IOException
	 */
	public void executePut(String localPath,String remotePath)
	throws IOException
	{
		
		InputStream input = null;
		 try
		 {
			  input = new FileInputStream(localPath);

             if(!ftpClient.storeFile(remotePath, input))
            	 throw new IOException("localPath:"+localPath+" remotePath:"+remotePath);
		 }
		 finally
		 {
			 if(input != null)
				 try{ input.close(); } catch(Exception e){e.printStackTrace();}
		 }
	}// --------------------------------------------------------
	/**
	 * GET remote path and local
	 * @param localPath the LOCAL path
	 * @param remotePath the REMOTE path
	 * @throws IOException
	 */
	public void executeGet(String localPath,String remotePath)
	throws IOException
	{
		 OutputStream output = null;
		 try
		 {
			 if(!connected)
				 this.connect();

			 
			 output =  new FileOutputStream(localPath);
			 if(!ftpClient.retrieveFile(remotePath, output))
				 throw new IOException("localPath:"+localPath+" remotePath:"+remotePath);
		 }
		 finally
		 {
			 if(output != null)
				 try{ output.close(); } catch(Exception e){e.printStackTrace();}
		 }
	}// --------------------------------------------------------
	/**
	 * Disconnection from the FTP server
	 */
	public void disconnect()
	{
		
		if(ftpClient != null)
		{
			try{ ftpClient.logout(); } catch(Exception e){}
			
			try{ ftpClient.disconnect(); } catch(Exception e){}
			
			ftpClient = null;
		}
	}// --------------------------------------------------------

	
	private boolean connected = false;
	//private FTPSClient ftpClient;
	private FTPClient ftpClient;
}
