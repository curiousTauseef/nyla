package nyla.solutions.global.patterns.reflection;

import java.util.Date;

public class SchemaUT
{
	enum TESTINGFORENUM
	{
		YES,
		NO
	};

	
	
	
	
	/**
	 * @return the mySchemaUT2
	 */
	public SchemaUT getMySchemaUT2()
	{
		return mySchemaUT2;
	}
	/**
	 * @param mySchemaUT2 the mySchemaUT2 to set
	 */
	public void setMySchemaUT2(SchemaUT mySchemaUT2)
	{
		this.mySchemaUT2 = mySchemaUT2;
	}
	/**
	 * @return the mySchemaUT
	 */
	public SchemaUT getMySchemaUT()
	{
		return mySchemaUT;
	}
	/**
	 * @param mySchemaUT the mySchemaUT to set
	 */
	public void setMySchemaUT(SchemaUT mySchemaUT)
	{
		this.mySchemaUT = mySchemaUT;
	}
	/**
	 * @return the myDate
	 */
	public Date getMyDate()
	{
		return myDate;
	}
	/**
	 * @param myDate the myDate to set
	 */
	public void setMyDate(Date myDate)
	{
		this.myDate = myDate;
	}
	/**
	 * @return the myString
	 */
	public String getMyString()
	{
		return myString;
	}
	/**
	 * @param myString the myString to set
	 */
	public void setMyString(String myString)
	{
		this.myString = myString;
	}
	String executeTest()
	{
		return myString;
	}
	
	
	/**
	 * @return the myENUM
	 */
	public TESTINGFORENUM getMyENUM()
	{
		return myENUM;
	}
	/**
	 * @param myENUM the myENUM to set
	 */
	public void setMyENUM(TESTINGFORENUM myENUM)
	{
		this.myENUM = myENUM;
	}


	private SchemaUT mySchemaUT2 = null;
	private SchemaUT mySchemaUT = null;
	private Date myDate = null;
	private String myString;

	private TESTINGFORENUM myENUM = TESTINGFORENUM.NO;
}
