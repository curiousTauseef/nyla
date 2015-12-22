package nyla.solutions.global.patterns.reflection;

import java.util.Date;

public class SchemaUT2
{
	
	
	/**
	 * @return the mySchemaUT
	 */
	public SchemaUT2 getMySchemaUT()
	{
		return mySchemaUT;
	}
	/**
	 * @param mySchemaUT the mySchemaUT to set
	 */
	public void setMySchemaUT(SchemaUT2 mySchemaUT)
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
	private SchemaUT2 mySchemaUT = null;
	private Date myDate = null;
	private String myString;

}
