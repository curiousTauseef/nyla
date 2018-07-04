package nyla.solutions.core.util;

import java.util.Random;

/**
 * utilize for the numbers
 * @author Gregory Green
 *
 */
public class Digits
{
	private Random random = new Random(System.currentTimeMillis());
	
	/**
	 * 
	 * @return the generated integer
	 */
	public int  generateInteger()
	{
		return Math.abs(random.nextInt()+1);
	}//------------------------------------------------
	/**
	 * 
	 * @return the generated long
	 */
	public long generateLong()
	{
		return Math.abs(random.nextLong()+1);
	}
}
