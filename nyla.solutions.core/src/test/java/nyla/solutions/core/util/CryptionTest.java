package nyla.solutions.core.util;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.junit.Test;

import junit.framework.TestCase;
import nyla.solutions.core.util.Cryption;

public class CryptionTest extends TestCase
{

	public CryptionTest(String name)
	{
		super(name);
	}// --------------------------------------------------------
	
	public void testCryption() throws Exception
	{
		//The cryption default constructor using the 
		//AES algorithm with a default key
		Cryption cryption = new Cryption();
		
		//Use the encryptText method to encrypt strings
		String original = "Text to encrypt";
		String encrypted = cryption.encryptText(original);
		
		System.out.println("encrypted:"+encrypted);
		assertTrue(!original.equals(encrypted));
		
		//Use the decryptText method to decrypt
		String decrypted = cryption.decryptText(encrypted);
		assertEquals(decrypted, original);
		
		//Use encrypt for bytes
		byte[] orginalBytes = original.getBytes(StandardCharsets.UTF_8);
		byte[] encryptedBytes = cryption.encrypt(orginalBytes);
		
		//Use decrypt 
		byte[] decryptedBytes = cryption.decrypt(encryptedBytes);
		assertTrue(Arrays.equals(orginalBytes, decryptedBytes));
		
		
		//Create crypt with a specific key and algorithm
		byte[] keyBytes = {0x22, 0x15, 0x27, 0x36, 0x41, 0x11, 0x79, 0x76};
		Cryption desCryption = new Cryption(keyBytes,"DES"); 
		
		String desEncrypted = desCryption.encryptText(original);
		assertTrue(!original.equals(encrypted) && !desEncrypted.equals(encrypted));
		decrypted = desCryption.decryptText(desEncrypted);
		assertEquals(decrypted, original);
	
		
	}
	@Test
	public void testMain() throws Exception
	{
		String [] args = {"PASSWORD"};
		
		Cryption.main(args);
	}

}
