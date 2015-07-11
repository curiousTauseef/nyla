/**
 * 
 */
package nyla.solutions.global.patterns.conversion;

/**
 * Returns a fixed text independently of input
 * @author Gregory Green
 *
 */
public class FixedTextConverter implements TextConverter
{

	/** 
	 * Returns a fixed text independently of input
	 * @see nyla.solutions.global.patterns.conversion.TextConverter#toText(java.lang.Object)
	 */
	public String toText(Object input)
	{
		return fixedText;
	}
	
	
	/**
	 * @return the fixedText
	 */
	public String getFixedText()
	{
		return fixedText;
	}// --------------------------------------------


	/**
	 * @param fixedText the fixedText to set
	 */
	public void setFixedText(String fixedText)
	{
		this.fixedText = fixedText;
	}// --------------------------------------------

	private String fixedText = null;
}
