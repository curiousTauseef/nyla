package nyla.solutions.core.util;

import java.util.StringTokenizer;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;

import nyla.solutions.core.exception.ConfigException;
import nyla.solutions.core.exception.RequiredException;
import nyla.solutions.core.exception.SetupException;
import nyla.solutions.core.exception.SystemException;
import nyla.solutions.core.io.IO;

/**
 * 
 * <pre>
 * Cryption provides a set of functions to encrypt and 
 * decrypt bytes and text. It uses the javax.crypto package.
 * 
 * The default encryption algorithm is the Advanced Encryption Standard (AES). 
 * The default algorithm can be changed with a configuration property named 
 * solutions.global.util.Cryption.alogorithm.
 * 
 * # The following sets the encryption algorithm to Data Encryption Standard (DES)
 * solutions.global.util.Cryption.algorithm=DES
 * 
 * The Cryption object is used by solutions.global.util.Config object to decrypt properties
 * prefixed {cryption}. The Cryption class can be used to generate encrypted passwords that 
 * can be added to the config.properties files. The Cryption main method accepts a password 
 * and will print the encrypted password that can be added to the property file. 
 * The printed password will be prefixed with the value �{cryption}�. Any properties prefixed with {cryption} 
 * in the config.properties is an indicator  that content is encrypted.

 * The follow is a sample Cryption UNIX script:

export LIB_DIR=put correct directory here
export CP="$LIB_DIR/solution.global.jar"
java -classpath $CP solutions.global.util.Cryption $1

#The following is a sample output of an encrypted password generated by the Cryption main method.
{cryption}23 4 -3 -77 -128 -88 -34 -105 23 4 -3 -77 -128 -88 -34 -105
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public class Cryption
{
	/**
	 * DEFAULT_ALGORITHM = Config.getProperty(Cryption.class,
			"algorithm", "AES");
	 */
	public final static String DEFAULT_ALGORITHM = Config.getProperty(Cryption.class,
			"algorithm", "AES");
	
	/**
	 * CRYPTION_PREFIX = "{cryption}"
	 */
	public static final String CRYPTION_PREFIX = "{cryption}";

	/**
	 * MIN_KEY_BYTE_SIZE_PROP = "security.cryption.min.key.byte.size"
	 */
	public static final String MIN_KEY_BYTE_SIZE_PROP = "security.cryption.min.key.byte.size";

	/**
	 * Default algorithm is AES is used with a fixed key.
	 * 
	 * 
	 */
	public Cryption()
	{

		this(keyBytes, DEFAULT_ALGORITHM);

	}// --------------------------------------------

	/**
	 * 
	 * 
	 * 
	 * Constructor for Cryption initializes internal data settings.
	 * 
	 * @param keyBytes the symmetric key to use
	 * @param algorithm the encryption algorithm (example: AES or DES)
	 * @throws Exception
	 */

	public Cryption(byte[] keyBytes, String algorithm)
	{
		if(algorithm == null)
					throw new IllegalArgumentException("algorithm");
		
		this.algorithm = algorithm;
		
		int minKeySize = Config.getPropertyInteger(MIN_KEY_BYTE_SIZE_PROP, 8)
				.intValue();

		if (keyBytes.length < minKeySize)

			throw new IllegalArgumentException("Minum key size is "+minKeySize);


		try
		{
			SecretKeySpec skeySpec = new SecretKeySpec(keyBytes, algorithm);
			this.encryptCipher = Cipher.getInstance(algorithm);
			this.encryptCipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			this.decryptCipher = Cipher.getInstance(algorithm);
			decryptCipher.init(Cipher.DECRYPT_MODE, skeySpec);

		}
		catch (Exception e)
		{
			throw new SetupException(Debugger.stackTrace(e));
		}

	}// --------------------------------------------

	/**
	 * 
	 * Encrypt a given bytes
	 * 
	 * @param bytes the unencrypted bytes encrypt
	 * 
	 * @return  the encrypt bytes
	 * 
	 * @throws Exception
	 */
	public byte[] encrypt(byte[] bytes)
	{

		// Create the PKCS5Padding final block by encryption
		try
		{
			return encryptCipher.doFinal(bytes);
		}
		catch (IllegalBlockSizeException e)
		{
			throw new SystemException(e);
		}
		catch (BadPaddingException e)
		{
			throw new SystemException(e);
		}

	}// --------------------------------------------
	/**
	 * The text to encrypt
	 * @param text the original text
	 * @return the encrypted version of the text
	 * @throws Exception
	 */
	public String encryptText(String text)
	throws Exception
	{
		return toByteText(this.encrypt(text.getBytes(IO.CHARSET)));

	}// --------------------------------------------

	
	/**
	 * Decrypt a string
	 * @param text the text to decrypt
	 * @return the decrypted text
	 * @throws Exception
	 */
	public String decryptText(String text)
	throws Exception
	{
		return new String(this.decrypt(toBytesFromByteText(text)),IO.CHARSET);

	}// --------------------------------------------


	/**
	 * 
	 * Decrypt given the bytes
	 * 
	 * @param encryptBytes the encrypted bytes
	 * @return decrypt bytes
	 * 
	 * @throws Exception
	 */
	public byte[] decrypt(byte[] encryptBytes) throws Exception
	{
		try
		{
			return decryptCipher.doFinal(encryptBytes);

		}
		catch (IllegalBlockSizeException e)
		{
			throw new SystemException(e);
		}
		catch (BadPaddingException e)
		{
			throw new SystemException(e);
		}

	}// --------------------------------------------

	/**
	 * This method is used by the interpret method.
	 * to determine is the given text is encryption 
	 * based on whether it is prefixed with the "{cryption}" string 
	 * @param text the possible encrypted text
	 * @return text.startsWith(CRYPTION_PREFIX)
	 */
	public static boolean isEncrypted(char[] text)
	{
		if (text == null)
			return false;

		return isEncrypted(new String(text));
	}// ----------------------------------------------

	/**
	 * 	This method is used by the interpret method object.
	 * to determine is the given text is encryption 
	 * based on whether it is prefixed with the "{cryption}" string 
	 * @param text the possible encrypted text
	 * @return text.startsWith(CRYPTION_PREFIX)
	 */
	public static boolean isEncrypted(String text)
	{
		return text != null && text.startsWith(CRYPTION_PREFIX);
	}// ----------------------------------------------

	/**
	 * If the text is encrypted the decrypted value is returned.
	 * If the text is not encrypted to original text is returned.
	 * 
	 * @param text the string values (encrypt or decrypted). Encrypted are prefixed with {cryption}
	 * @return if the value starts with the {cryption} prefix the encrypted
	 *         value is return, else the given value is returned
	 */
	public static String interpret(String text)
	{
		if (text == null)
			return null;

		if (isEncrypted(text))
		{
			try
			{

				text = text.substring(CRYPTION_PREFIX.length());
				text = canonical.decryptText(text);
			}
			catch (Exception e)
			{
				throw new ConfigException("Cannot interpret:"+text,e);
			}
		}
		return text;
	}// ----------------------------------------------

	/**
	 * If the text is encrypted the decrypted value is returned.
	 * If the text is not encrypted to original text is returned.
	 * @param text the text to interpret
	 * @return if the value starts with the {cryption} prefix the encrypted
	 *         value is return, else the given value is returned
	 */
	public static char[] interpret(char[] text)
	{
		if (text == null)
			throw new RequiredException("text");

		return interpret(new String(text)).toCharArray();
	}// ----------------------------------------------
	/**
	 * Encrypt a given values
	 * 
	 * @param args args[0] contains the value to encrypt
	 */
	public static void main(String[] args)
	{
		try
		{
			if (args.length == 0)
			{

				System.err.println("Usage java " + Cryption.class.getName()
						+ " <text>");
				return;

			}

			if (args[0].equals("-d"))
				System.out.println(canonical.decryptText(args[1]));
			else
				System.out.println(CRYPTION_PREFIX
						+ canonical.encryptText(args[0]));

		}
		catch (Exception e)
		{

			e.printStackTrace();
		}

	}// --------------------------------------------

	/**
	 * @return the algorithm
	 */
	public String getAlgorithm()
	{
		return algorithm;
	}// --------------------------------------------------------
	
	private String toByteText(byte[] aByte)
	{

		if (aByte == null)

			return "";

		StringBuffer text = new StringBuffer();

		for (int i = 0; i < aByte.length; i++)

		{

			text.append(Byte.toString(aByte[i]));

			if (i + 1 < aByte.length)

				text.append(" ");

		}

		return text.toString();

	}// --------------------------------------------

	private static byte[] toBytesFromByteText(String aByteText)
	{

		if (aByteText == null)

			return new byte[0];

		StringTokenizer tok = new StringTokenizer(aByteText);

		byte[] bytes = new byte[tok.countTokens()];

		int i = 0;

		while (tok.hasMoreTokens())

		{

			bytes[i] = Byte.valueOf(tok.nextToken()).byteValue();

			i++;

		}

		return bytes;

	}// --------------------------------------------
	private final Cipher decryptCipher;
	private final Cipher encryptCipher;
	private final String algorithm;


	private static final byte[] keyBytes =
	{ -83, 48, 38, 87, -72, -49, 106, -28, -113, 60, 93, 58, -93, 7, -1, 16 };
	private static Cryption canonical = new Cryption(keyBytes,DEFAULT_ALGORITHM);

}