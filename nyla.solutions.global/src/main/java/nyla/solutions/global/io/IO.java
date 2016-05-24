package nyla.solutions.global.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

import nyla.solutions.global.data.Data;
import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Constants;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Text;


/**
 * <pre>
 *  IO provides a set function related to system IO reads/writes.
 * 
 *  This object is not thread safe
 *  &#064;see SynchronizedIO
 * 
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public class IO
{
	/**
	 * CHARSET_NM = "UTF-8"
	 */
	public static final String CHARSET_NM = Config.getProperty(IO.class,"CHARSET","UTF-8");
	
	
	/**
	 * CHARSET = Charset.forName("UTF-8")
	 */
	public static final Charset CHARSET = Charset.forName(CHARSET_NM);
	
	/**
	 * File name cannot have characters /\<>*:?|
	 */
	public static final String DEFAULT_FILE_NM_INVALID_CHARACTERS_RE = "\\/|\\\\|:|\"|\\*|\\?|<|>|\\|";

	
	/**
	 * 
	 * @return the System.getProperty("file.separator");
	 */
	public static String fileSperator()
	{
		return System.getProperty("file.separator");
	}// --------------------------------------------------------
	
	public static String readText(BufferedReader br) throws IOException 
	{
		String s = "";
		StringBuilder sb = new StringBuilder();
		while((s=br.readLine())!=null){
				 sb.append(s+IO.newline());
		}
		
		return sb.toString();
	}
	
	
	/**
	 * Write obj to file
	 * @param obj
	 * @param file
	 */
	public static void serializeToFile(Object obj, File file)
	{
	   ObjectOutputStream stream = null;
	   try
	   {
		   stream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
		   
		   stream.writeObject(obj);		
	   } 
	   catch (Exception e)
	   {
		throw new SystemException(Debugger.stackTrace(e));
	   }
	   finally
	   {
		if(stream != null)
		   try{ stream.close(); } catch(Exception e){}
		
	   }
	}// ----------------------------------------------

	/**
	 * Write obj to file
	 * @param obj
	 * @param file
	 */
	public static byte[] serializeToBytes(Object obj)
	{
	   ObjectOutputStream stream = null;
	   try
	   {
		   ByteArrayOutputStream out = new ByteArrayOutputStream();
		   
		   stream = new ObjectOutputStream(new BufferedOutputStream(out));
		   
		   stream.writeObject(obj);		
		   
		   return out.toByteArray();
	   } 
	   catch (Exception e)
	   {
		throw new SystemException(Debugger.stackTrace(e));
	   }
	   finally
	   {
		if(stream != null)
		   try{ stream.close(); } catch(Exception e){Debugger.printWarn(e);}
		
	   }
	}// ----------------------------------------------
	/**
	 * Read object from file
	 * @param file the file that has the object information
	 * @return the UN-serialized object
	 */
	public static Object deserialize(File file)
	{
	   ObjectInputStream stream = null;
	   try
	   {
		stream = new ObjectInputStream(new FileInputStream(file));
		return stream.readObject();
	   } 
	   catch (Exception e)
	   {
		throw new SystemException(Debugger.stackTrace(e));
	   }
	   finally
	   {
		if(stream != null)
		   try{ stream.close(); } catch(Exception e){Debugger.printWarn(e);}
	   }
	}// ----------------------------------------------
	/**
	 * Make a directory
	 * @param folder the folder/directory to create
	 * @throws IOException
	 */
	public static boolean mkdir(File folder)
	throws IOException
	{
		
		if(folder == null || folder.exists())
			return false;
		
		//check if parent directory exists
		File parent = folder.getParentFile();
		if(parent != null && !parent.exists())
			mkdir(parent); //recursively check parentb
		
		return folder.mkdir();

	}//---------------------------------------------
	/**
	 * 
	 * @param folder the top folder
	 * @return the nest folders in a director
	 */
	public static File[] listFolders(File folder)
	{
		
		if(folder == null)
			throw new RequiredException("folder in IO");
		
		if(!folder.isDirectory())
			throw new RequiredException(folder.getAbsolutePath()+" is not a folder");
		
		return folder.listFiles(new FolderFilter());
	}//---------------------------------------------
	/**
	 * Delete the file o folder
	 * @param file the file/folder to delete
	 */
	public static boolean delete(File file)
	throws IOException
	{
		if(file == null)
			throw new RequiredException("file");
		
		if(!file.exists())
			return false;
		
		if(file.isDirectory())
			return deleteFolder(file);
		else
		{
		    return file.delete();
		    	
		}
	}//---------------------------------------------	
	/**
	 * 
	 * @param aFilePath the file to check if it exists
	 * @return the true is file exists
	 */
	public static boolean exists(String aFilePath)
	{
		if (aFilePath == null)
			throw new IllegalArgumentException(
					"aFilePath required in IO.exists");

		File file = new File(aFilePath);

		return file.exists();
	}// --------------------------------------------
	   /**
	    * Write binary file data
	    * 
	    * @param file the file
	    * @param data
	    * @throws Exception
	    */
	   public static void writeFile(File file, byte[] data) throws IOException
	   {
		   writeFile(file.getAbsolutePath(), data);
	   }//---------------------------------------------
	   /**
	    * Write data to property file
	    * @param filePath the file to write
	    * @param properties
	    * @throws IOException
	    */
	   public static void writeProperties(String filePath, Properties properties)
	   throws IOException
	   {
		   Writer writer = null;
		   
		   try
			{
				   writer = new OutputStreamWriter(new FileOutputStream(filePath), IO.CHARSET);
				   
				   properties.store(writer, filePath);
			}
			finally
			{
				if(writer != null)
					try{ writer.close(); } catch(Exception e){e.printStackTrace();}
			}
		   
	   }// --------------------------------------------------------
	   /**
	    * Read properties file
	    * @param filePath the file to read
	    * @param properties
	    * @throws IOException
	    */
	   public static Properties readProperties(String filePath)
	   throws IOException
	   {
		   Reader reader = null;
		   Properties properties = new Properties();
		   
		   try
			{
			   reader = new InputStreamReader(new FileInputStream(filePath), CHARSET);
				   
			   properties.load(reader);
			   
			   return properties;
			}
			finally
			{
				if(reader != null)
					try{ reader.close(); } catch(Exception e){Debugger.printWarn(e);}
			}
		   
	   }// --------------------------------------------------------	   
	   /**
	    * 
	    * @param file the full file path of which to read
	    * @return string data
	    * @throws IOException
	    */
	   public static String readFile(File file) throws IOException
	   {
		   if (file == null)
			throw new RequiredException("file to read");
		   
		   if(!file.exists())
			   throw new RequiredException("file must exist "+file.getAbsolutePath());
		   
		   return readFile(file.getAbsolutePath());
	   }// ---------------------------------------------
	   /**
	    * 
	    * @param inputStream the input stream
	    * @param closeInputStream
	    * @return
	    * @throws IOException
	    */
	   public static String readText(InputStream inputStream, boolean closeInputStream)
	   throws IOException
	   {
		   Reader reader = null;
		   
		   try
		   {
			   	reader = toReader(inputStream);
		
			   	BufferedReader buffreader = new BufferedReader(new InputStreamReader(inputStream, IO.CHARSET));

			   	String tmp = buffreader.readLine();
			   	if (tmp == null)
			   		return null;

				StringBuilder line = new StringBuilder(tmp);
	
				while (tmp != null)
				{
					line.append("\n");
					tmp = buffreader.readLine();
					if (tmp != null)
						line.append(tmp);
				}

	
				return line.toString();
		} 
		finally
		{
			if(closeInputStream)
			{
				if (reader != null)
					try{  reader.close();} catch (Exception e){}
			
				
					if(inputStream != null)
						try{ inputStream.close(); } catch(Exception e){}
							
			}
		}
	}// --------------------------------------------
	/**
	 * 
	 * @return System.getProperty("line.separator")
	 */
	public static String newline()
	{
		return System.getProperty("line.separator");
	}// --------------------------------------------
	/**
	 * Copy a source folder to a destination folder
	 * @param sourceFolder the source directory
	 * @param destinationFolder the destination directory
	 * @throws IOException
	 */
	public static void copyDirectory(File sourceFolder, File destinationFolder)
	throws IOException
	{
		if(sourceFolder == null || !sourceFolder.isDirectory())
			throw new RequiredException("sourceFolder");
		
		if(destinationFolder == null)
			throw new RequiredException("destinationFolder");
		
		if(!destinationFolder.exists())	    
			IO.mkdir(destinationFolder);
		
		if(!destinationFolder.isDirectory())
			throw new RequiredException("destinationFile.isDirectory() in IO");
		
		
		File[] sourceNestedFiles = sourceFolder.listFiles();
		
		if(sourceNestedFiles == null)
			return;
		
		for(int i = 0 ;  i < sourceNestedFiles.length;i++)
		{
			//copy file
			if(sourceNestedFiles[i].isFile())
				copy(sourceNestedFiles[i], destinationFolder.getAbsolutePath());
			else
			{
				//copy directory
				copyDirectory(sourceNestedFiles[i],new File(destinationFolder.getAbsolutePath()+File.separator+sourceNestedFiles[i].getName()));
			}
		}
	}//---------------------------------------------
	public static void copyDirectory(String source, String destination, String pattern)
	throws IOException
	{
		File destinationFile = new File(destination);		
		if(!destinationFile.exists())
			Debugger.println(IO.class,"mkdir:"+destinationFile.mkdir());
		
		if(!destinationFile.isDirectory())
			throw new RequiredException("destinationFile \""+destinationFile+"\" is must a directory");
		
		File sourceFile = new File(source);
		
		File[] sourceNestedFiles = listFiles(sourceFile, pattern);
		
		for(int i = 0 ;  i < sourceNestedFiles.length;i++)
		{
			//copy file
			if(sourceNestedFiles[i].isFile())
				copy(sourceNestedFiles[i], destinationFile.getAbsolutePath());
			else
			{
				//copy directory
				copyDirectory(sourceNestedFiles[i].getAbsolutePath(),destinationFile.getAbsolutePath()+File.separator+sourceNestedFiles[i].getName(),pattern);
			}
		}
	}//---------------------------------------------
	/**
	 * Remove characters from file name
	 * 
	 * @param aFileName
	 *            the file name to format
	 * @return the formatted file with special characters removed
	 */
	public static String formatFileName(String aFileName)
	{
		if (aFileName == null | aFileName.length() == 0)
			return "";

		String invalidCharRE = Config.getProperty(IO.class.getName()
				+ ".formatFile.invalidCharRE",
				DEFAULT_FILE_NM_INVALID_CHARACTERS_RE);
		String replaceText = Config.getProperty(IO.class.getName()
				+ ".formatFile.replaceText", "");
		return Text
				.replaceForRegExprWith(aFileName, invalidCharRE, replaceText);

	}// --------------------------------------------

	/**
	 * @param aReader
	 *            the input reader
	 */
	private static String readFully(Reader aReader) throws IOException
	{
		if (aReader == null)
			throw new IllegalArgumentException(
					"aReader required in IO.readFully");

		BufferedReader buffreader = new BufferedReader(aReader);
		String tmp = buffreader.readLine();

		if (tmp == null)
			return null;

		StringBuffer line = new StringBuffer(tmp);

		while (tmp != null)
		{

			tmp = buffreader.readLine();

			if (tmp != null)

				line.append(tmp).append("\n");
		}

		return line.toString();
	}// --------------------------------------------

	/**
	 * Read Class Path resource
	 * 
	 * @param aPath
	 *            the classpath location i.e. /properties/config.properties
	 * @return the string content from the class location
	 */
	public static String readClassPath(String aPath) throws IOException
	{

		
		ClassPathResource resource = new ClassPathResource(aPath);
		
	
		InputStream inputStream = resource.getInputStream();

		if (inputStream == null)
			throw new IOException("path not found " + aPath);
		
		return readFully(new InputStreamReader(inputStream,CHARSET));
	}// -------------------------------------------

	/**
	 * 
	 * @param aFilePath
	 *            the file path
	 * @return the file name
	 */
	/*
	 * public static String fileNameForPath(String aFilePath) { if (aFilePath ==
	 * null || aFilePath.length() == 0) throw new IllegalArgumentException(
	 * "aFilePath required in fileNameForPath");
	 * 
	 * int lastIndexOfSlash = aFilePath.lastIndexOf(aFilePath);
	 * 
	 * if (lastIndexOfSlash + 1 != aFilePath.length()) aFilePath =
	 * aFilePath.substring(lastIndexOfSlash + 1, aFilePath .length()); else {
	 * throw new IllegalArgumentException("Illegal Argument path " + aFilePath);
	 * }
	 * 
	 * return aFilePath; }// --------------------------------------------
	 */
	public static File[] listFiles(String location, String pattern)
	{
		return listFiles(new File(location), pattern);
	}// --------------------------------------------

	public static String[] list(String location, String pattern)
	{
		return list(new File(location), pattern);
	}// --------------------------------------------

	/**
	 * List nested files
	 * @param location the location of the top folder
	 * @return 
	 */
	public static File[] listFiles(String location)
	{
		if(location == null || location.length() == 0)
			throw new RequiredException("location in IO");
		
		File folder = new File(location);
		
		return listFiles(folder);
	}// --------------------------------------------
	public static File[] listFiles(File folder)
	{
		if(!folder.isDirectory())
			throw new RequiredException(folder.getAbsolutePath()+" is not a directory");
		
		return folder.listFiles();
	}//---------------------------------------------
	/**
	 * List the file under a given directory
	 */
	public static String[] list(File directory, String pattern)
	{
		if(pattern == null)
			return directory.list();
		
		WildCardFilter filter = createFilter(directory, pattern);

		return directory.list(filter);
	}// --------------------------------------------

	/**
	 * List the file under a given directory
	 */
	public static File[] listFiles(File directory, String pattern)
	{
		
		if(pattern == null || pattern.length() == 0)
			throw new RequiredException("pattern in IO");
		
		validateDirectory(directory);
		
		//check for /
		int indexofSlash = pattern.indexOf("/");
		if( indexofSlash > 0)
		{
			//get text up still /
			String suffix = pattern.substring(0,indexofSlash);
			pattern = pattern.substring(indexofSlash+1);
			//append directory
			directory = new File(directory.getAbsolutePath()+"/"+suffix);
			validateDirectory(directory);
			
		}
		
		WildCardFilter filter = createFilter(directory, pattern);

		return directory.listFiles(filter);
	}// --------------------------------------------

	/**
	 * Common function to build file list filter
	 * 
	 * @param directory
	 *            the directory to filter
	 * @param pattern
	 *            the file patter (i.e. *.*)
	 * @return the WildCard filter
	 */
	private static WildCardFilter createFilter(File directory, String pattern)
	{
	
	      if (pattern == null || pattern.length() == 0)
		         throw new IllegalArgumentException(
		         "pattern required in list");
	      
	      WildCardFilter filter = new WildCardFilter(pattern);
		return filter;
	}// --------------------------------------------

	
	private static void validateDirectory(File directory)
	{
		if(directory == null)
	         throw new RequiredException("directory in IO.list");
	
	      if(!directory.exists())
	         throw new IllegalArgumentException("Directory does not exist "+directory.getAbsolutePath());
	
	      if(!directory.isDirectory())
	      {
	        throw new IllegalArgumentException("Must provide a directory "+directory.getAbsolutePath());
	      }
	}

	/**
	 * 
	 * @param urlAddress
	 *            the URL to read form
	 * @return the URL text content
	 */
	public static String readURL(String urlAddress) throws IOException
	{
		if (urlAddress == null)
			throw new RequiredException("url in IO.readURL");

		Reader reader = null;
		try
		{
			URL url = new URL(urlAddress);

			URLConnection connection = url.openConnection();

			reader = toReader(connection.getInputStream());

			return readFully(reader);
		} catch (MalformedURLException e)
		{
			throw new SystemException("URL=" + urlAddress + " " + e);
		} finally
		{
			if (reader != null)
				try
				{
					reader.close();
				} catch (Exception e)
				{
				}
		}

	}// --------------------------------------------

	/**
	 * 
	 * @param aPath
	 *            the path content
	 * @return the fixed path with / and trimmed spaces
	 */
	public static String fixPath(String aPath)
	{
		if (aPath == null || aPath.length() == 0)
			throw new IllegalArgumentException(
					"aPath required in Documentum.fixPath");

		aPath = aPath.replace('\\', '/');

		return aPath;
	}// --------------------------------------------

	/**
	 * Return the length of a given file
	 * 
	 * @param aFilePath
	 *            the location of the file
	 * @return the file length
	 * @throws IllegalArgumentException
	 */
	public static long getFileSize(String aFilePath)
			throws IllegalArgumentException
	{
		if (aFilePath == null)
			throw new IllegalArgumentException(
					"Cannot obtain file size, File Path not provided");

		return new File(aFilePath).length();
	}// -------------------------

	/**
	 * 
	 * @param aFilePath
	 *            the file name/path
	 * @return Map version of property file
	 */
	@SuppressWarnings("rawtypes")
	public static Map readMap(String aFilePath) throws IOException,
			FileNotFoundException
	{
		InputStream in = getFileInputStream(aFilePath);

		Properties prop = new Properties();
		prop.load(in);

		return prop;
	}// --------------------------------------------

	/**
	 * Retrieve contents of specified file. Retry reads the specified number of
	 * times with the specified delay when errors occur
	 * 
	 * @param aFilePath
	 *            the file path
	 * @param aRetryCount
	 *            number of times to retry read
	 * @param aRetryDelayMS
	 *            delay in between read failures
	 * @return file string content
	 * @throws IOException
	 */
	public static byte[] readBinaryFile(File aFile, int aRetryCount,
			long aRetryDelayMS) throws IOException
	{
		for (int i = 0; i <= aRetryCount; i++)
		{
			try
			{
				return IO.readBinaryFile(aFile);
			} catch (Exception e)
			{
				try
				{
					Thread.sleep(aRetryDelayMS);
				} catch (Exception interruptE)
				{
				}
			}
		}
		throw new IOException(aFile.getAbsolutePath());
	}// ----------------------------------------------------

	/**
	 * 
	 * @param aFileNM
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static byte[] readBinaryFile(String aFilePath)
			throws FileNotFoundException, IOException
	{
		return readBinaryFile(new File(aFilePath));
	}// -----------------------------------------------

	/**
	 * 
	 * @param file the file to read
	 * @return binary file content
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static byte[] readBinaryFile(File file)
			throws FileNotFoundException, IOException
	{
		BufferedInputStream in = null;
		try
		{
			in = new BufferedInputStream(new FileInputStream(file));

			byte[] b = new byte[Long.valueOf(file.length()).intValue()];

			in.read(b);
			return b;
		}
		finally
		{
			if (in != null)
				try
				{
					in.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
		}
	}// --------------------------------

	/**
	 * Retrieve contents of specified file. Retry reads the specified number of
	 * times with the specified delay when errors occur
	 * 
	 * @param aFilePath
	 *            the file path
	 * @param aRetryCount
	 *            number of times to retry read
	 * @param aRetryDelayMS
	 *            delay in between read failures
	 * @return file string content
	 * @throws IOException
	 */
	public static String readFile(String aFilePath, int aRetryCount,
			long aRetryDelayMS,Charset charset) throws IOException
	{
		for (int i = 0; i <= aRetryCount; i++)
		{
			try
			{
				return IO.readFile(aFilePath,charset);
			} catch (Exception e)
			{
				try
				{
					Thread.sleep(aRetryDelayMS);
				} catch (Exception interruptE)
				{
				}
			}
		}
		throw new IOException(aFilePath);
	}// ----------------------------------------------------
	/**
	 * 
	 * @param fileName  the full file path of which to read
	 * @return string data
	 * @throws IOException
	 */
	public static String readFile(String fileName) throws IOException
	{
		return readFile(fileName,IO.CHARSET);
	}//--------------------------------------------------------
	/**
	 * 
	 * @param fileName  the full file path of which to read
	 * @return string data
	 * @throws IOException
	 */
	public static String readFile(String fileName, Charset charSet) throws IOException
	{
		if (fileName == null || fileName.length() == 0)
			return null;

		BufferedReader buffreader = null;
		File file = new File(fileName);
		try
		{

			buffreader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), charSet));

			String tmp = buffreader.readLine();
			if (tmp == null)
				return null;

			StringBuilder results = new StringBuilder(tmp);

			String newline = newline();

			do
			{

				tmp = buffreader.readLine();

				if (tmp != null)
					results.append(newline).append(tmp);
			}
			while (tmp != null);

			return results.toString();
		}
		catch (FileNotFoundException e)
		{
			return null;
		}
		finally
		{
			if (buffreader != null)
				try
				{
					buffreader.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}

		}
	}// --------------------------------------------------
	/**
	 * 
	 * @param aFileNM  the full file path of which to read
	 * @return string data
	 * @throws IOException
	 */
	public static String[] readLines(String aFileNM, Charset charset) throws IOException
	{
		if (Data.isNull(aFileNM))
			throw new IllegalArgumentException("file name not provided");


		ArrayList<String> lines = new ArrayList<String>();
		BufferedReader buffreader = null;
		try
		{
			buffreader = new BufferedReader(new InputStreamReader(new FileInputStream(aFileNM),charset));

			String tmp = buffreader.readLine();
						
			if (tmp == null)
				throw new IOException(aFileNM + " empty file");
			
			lines.add(tmp);

			while (tmp != null)
			{
				tmp = buffreader.readLine();
				if (tmp != null)
				   lines.add(tmp);
			}

			String[] lineArray = new String[lines.size()];
			lines.toArray(lineArray);
			return lineArray;
		} 
		finally
		{
			if (buffreader != null)
				try
				{
					buffreader.close();
				} catch (Exception e){Debugger.printWarn(e);}
		}
	}// --------------------------------------------------
	public static void copy(File aFile, String aDestinationPath)
			throws FileNotFoundException, IOException
	{
		InputStream in = null;

		try
		{
			in = new BufferedInputStream(new FileInputStream(aFile));
			write(aDestinationPath + File.separator + aFile.getName(), in);
		} finally
		{
			if (in != null)
				try
				{
					in.close();
				} catch (Exception e)
				{
				}
		}

	}// ---------------------------------------------------

	/**
	 * Write binary file data
	 * 
	 * @param aFileName
	 * @param aData
	 * @throws Exception
	 */
	public static void writeFile(String aFileName, URL aData) throws Exception
	{
		OutputStream os = null;
		InputStream is = null;

		try
		{
			os = new FileOutputStream(aFileName);

			is = aData.openStream();
			int BYTE_BUFFER_SIZE = Config.getPropertyInteger(
					Constants.BYTE_BUFFER_SIZE_PROP,
					Constants.FILE_IO_BATCH_SIZE).intValue();

			byte[] bytes = new byte[BYTE_BUFFER_SIZE]; // 5 KB
			int cnt = 0;
			// while((cnt= is.read(bytes,0,(int)bytes.length)) != -1)
			while ((cnt = is.read(bytes)) != -1)
			{
				os.write(bytes, 0, cnt);
			}
		} finally
		{
			if (os != null)
				try
				{
					os.close();
				} catch (Exception e)
				{
				}
			if (is != null)
				try
				{
					is.close();
				} catch (Exception e)
				{
				}
		}
	}// -----------------------------------------------

	public String readInputStream(InputStream aInputStream) throws IOException
	{
		Reader reader = null;
		try
		{
			reader = toReader(aInputStream);

			return readFully(reader);
		} finally
		{
			if (reader != null)
				try
				{
					reader.close();
				} catch (Exception e)
				{
				}
		}
	}// --------------------------------------------

	/**
	 * Write output stream to input stream
	 * 
	 * @param aOutputStream
	 * @param aInputStream
	 * @throws IOException
	 */
	public static void write(OutputStream aOutputStream,
			InputStream aInputStream) throws IOException
	{
		int BYTE_BUFFER_SIZE = Config.getPropertyInteger(
				Constants.BYTE_BUFFER_SIZE_PROP, Constants.FILE_IO_BATCH_SIZE)
				.intValue();

		byte[] bytes = new byte[BYTE_BUFFER_SIZE]; // 5 KB
		// byte[] bytes = new byte[100]; //5 KB
		// int offset = 0;
		int cnt = 0;

		while ((cnt = aInputStream.read(bytes)) != -1)
		{
			aOutputStream.write(bytes, 0, cnt);
		}

		//aOutputStream.flush();
	}// ---------------------------------------------
	/**
	 * Write output stream to input stream
	 * 
	 * @param aOutputStream
	 * @param aInputStream
	 * @throws IOException
	 */
	public static void write(Writer aOutputStream,
			Reader aInputStream) throws IOException
	{
		int BYTE_BUFFER_SIZE = Config.getPropertyInteger(
				Constants.BYTE_BUFFER_SIZE_PROP, Constants.FILE_IO_BATCH_SIZE)
				.intValue();

		char[] chars = new char[BYTE_BUFFER_SIZE]; // 5 KB
		// byte[] bytes = new byte[100]; //5 KB
		// int offset = 0;
		int cnt = 0;

		while ((cnt = aInputStream.read(chars)) != -1)
		{
			aOutputStream.write(chars, 0, cnt);
		}
	}// ---------------------------------------------

	/**
	 * 
	 * @param aInputStream
	 * @return the inputStream Reader
	 * @throws IOException
	 */
	public static Reader toReader(InputStream aInputStream) throws IOException
	{
		return new java.io.InputStreamReader(aInputStream,CHARSET);

	}// ---------------------------------------------

	/**
	 * 
	 * @param aFilePath
	 *            the file path the write
	 * @param aInputStream
	 *            the input stream data to write
	 * @throws IOException
	 */
	public static void write(String aFilePath, InputStream aInputStream)
			throws IOException
	{
		FileOutputStream os = null;
		try
		{
			os = new FileOutputStream(aFilePath);
			write(os, aInputStream);
		} finally
		{
			if (os != null)
				try
				{
					os.close();
				} catch (Exception e)
				{
				}
		}
	}// ---------------------------------------------

	/**
	 * 
	 * @param aFilePath
	 *            the file path
	 * @return file input stream
	 * @throws FileNotFoundException
	 */
	public static InputStream getFileInputStream(String aFilePath)
			throws FileNotFoundException
	{
		return new FileInputStream(aFilePath);
	}// ------------------------------------------------

	/**
	 * Hide the file name extension test.doc = test and test = test
	 * 
	 * @param aFileName
	 *            the file name
	 * @return the file name without the extension
	 */
	public static String hideExtension(String aFileName)
	{
		if (aFileName == null || aFileName.length() == 0)
			throw new IllegalArgumentException(
					"File Name required in IO hideExtension");

		int lastPeriodIndex = aFileName.lastIndexOf(".");

		if (lastPeriodIndex > -1)
		{
			return aFileName.substring(0, lastPeriodIndex);
		}

		return aFileName;
	}// --------------------------------------------

	/**
	 * 
	 * @param aFileName
	 *            the file name
	 * @return file extension (null if file does not have an extension
	 */
	public static String parseFileExtension(String aFileName)
	{
		if (aFileName == null || aFileName.length() == 0)
			throw new IllegalArgumentException(
					"aFileName required in parseFileExtension");

		int lastPeriodIndex = aFileName.lastIndexOf(".");

		if (lastPeriodIndex > -1)
		{
			return aFileName.substring(lastPeriodIndex + 1, aFileName.length());
		}

		return null;
	}// --------------------------------------------
	/**
	 * Parse folder path
	 * @param filePath
	 * @return absolute path of root directory
	 */
	public static String parseFolderPath(String filePath)
	{
	   if (filePath == null || filePath.length() == 0)
		throw new RequiredException("filePath");
	   File file = new File(filePath);
	   
	   return file.getParentFile().getAbsolutePath();
	}// ----------------------------------------------
	/**
	 * 
	 * @param aFolderPath
	 * @return
	 */
	public static String parseFileName(String aFolderPath)
	{

		int lastFSIndex = aFolderPath.lastIndexOf("/");
		int lastBSIndex = aFolderPath.lastIndexOf("\\");
		int lastSeparatorIndex = lastFSIndex;
		if (lastBSIndex > lastSeparatorIndex)
			lastSeparatorIndex = lastBSIndex;

		if (lastSeparatorIndex > -1)
			return aFolderPath.substring(lastSeparatorIndex + 1, aFolderPath
					.length());

		return aFolderPath;
	}// ------------------------------------------------

	/**
	 * Write binary file data
	 * 
	 * @param filePath
	 * @param data
	 * @throws Exception
	 */
	public static void writeFile(String filePath, byte[] data)
			throws IOException
	{
		writeFile(filePath, data, false);
	}// --------------------------------------------

	/**
	 * Write binary file data
	 * 
	 * @param aFileName
	 * @param aData
	 * @throws Exception
	 */
	public static void writeFile(String aFilePath, byte[] aData, boolean append)
			throws IOException
	{
		if (aData == null)
			throw new IOException("No bytes provided for file " + aFilePath);

		if (aFilePath == null || aFilePath.length() == 0)
			throw new IllegalArgumentException(
					"aFilePath required in writeFile");

		OutputStream os = null;
		try
		{
			os = new FileOutputStream(aFilePath, append);
			os.write(aData);

			os.flush();
		} catch (FileNotFoundException e)
		{
			throw new IOException(Debugger.stackTrace(e) + " path=" + aFilePath);
		} finally
		{
			if (os != null)
				try
				{
					os.close();
				} catch (Exception e)
				{
				}
		}

	}// -----------------------------------------------
	public static void writeFile(String fileName, String text)
	throws IOException
	{
		writeFile( fileName,  text,IO.CHARSET);
	}//--------------------------------------------------------
	/**
	 * Write string file data
	 * 
	 * @param fileName the file to write
	 * @param text the text to write
	 * @throws IOException
	 */
	public static void writeFile(String fileName, String text,Charset charset)
	throws IOException
	{
		writeFile(fileName, text,false,charset);
	}//---------------------------------------------
	/**
	 * Write string file data
	 * 
	 * @param fileName the file
	 * @param text the text to write
	 * @throws Exception
	 */
	public static void writeFile(String fileName, String text, boolean append)
			throws IOException
	{
		 writeFile( fileName,  text,  append,IO.CHARSET);
	}//--------------------------------------------------------
	/**
	 * Write string file data
	 * 
	 * @param fileName the file
	 * @param text the text to write
	 * @throws Exception
	 */
	public static void writeFile(String fileName, String text, boolean append,Charset charset)
			throws IOException
	{
		writeFile(new File(fileName),text,append,charset);
	}// --------------------------------------------------------
	/**
	 * Write string file data
	 * 
	 * @param fileName the file
	 * @param text the text to write
	 * @throws Exception
	 */
	public static void writeFile(File file, String text)
			throws IOException
	{
		writeFile(file,text,IO.CHARSET);
	}//--------------------------------------------------------
	/**
	 * Write string file data
	 * 
	 * @param fileName the file
	 * @param text the text to write
	 * @throws Exception
	 */
	public static void writeFile(File file, String text,Charset charset)
			throws IOException
	{
		writeFile(file,text,false,charset);
	}// --------------------------------------------------------
	/**
	 * Write string file data
	 * 
	 * @param fileName the file
	 * @param text the text to write
	 * @throws Exception
	 */
	public static void writeFile(File file, String text, boolean append)
			throws IOException
	{
		writeFile( file,  text,  append, IO.CHARSET);
	}//--------------------------------------------------------
	/**
	 * Write string file data
	 * 
	 * @param fileName the file
	 * @param text the text to write
	 * @throws Exception
	 */
	public static void writeFile(File file, String text, boolean append, Charset charset)
			throws IOException
	{
		if (text == null)
			return; // nothing to write

		mkdir(file.getParentFile());

		Writer writer = null;

		try
		{
			writer = new OutputStreamWriter(new FileOutputStream(file,append),charset.newEncoder()) ;

			writer.write(text);
		}
		finally
		{
			if (writer != null)
				try
				{
					writer.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
		}

	}// ------------------------------------------------------
	/**
	 * 
	 * @param fileName
	 *            the file to write
	 * @param data
	 *            the data
	 * @throws IOException
	 */
	public static void writeAppend(String fileName, String data)
			throws IOException
	{
		IO.writeAppend(fileName,data,IO.CHARSET);
		
	}//--------------------------------------------------------
	/**
	 * 
	 * @param fileName
	 *            the file to write
	 * @param data
	 *            the data
	 * @throws IOException
	 */
	public static void writeAppend(String fileName, String data,Charset charset)
			throws IOException
	{
		writeFile(new File(fileName),data,true,charset);
	}// --------------------------------------------
	/**
	 * Delete a given the directory
	 * @param file the directory to delete
	 */
	private static boolean deleteFolder(File file)
	throws IOException
	{
		emptyFolder(file);
		
		return file.delete();
	}//---------------------------------------------
	/**
	 * Delete all files in a given folder
	 * @param directory the directory to empty
	 * @throws IOException
	 */
	public static void emptyFolder(File directory) throws IOException
	{
		File[] files = directory.listFiles();
		
		if(files != null && files.length > 0)
		{
			for(int i = 0; i < files.length;i++)
				delete(files[i]);
		}
	}

}
