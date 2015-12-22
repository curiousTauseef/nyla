package nyla.solutions.global.patterns.reflection;

public interface TypeSchema
{

	/**
	 * @return the fieldName
	 */
	public abstract String getFieldName();

	
	public Class<?> getFieldClass();
	
	/**
	 * 
	 * @return the class name
	 */
	public String getClassName();
	
	
	/**
	 * 
	 * @return get the class type
	 */
	public ClassType getClassType();
	
	/**
	 * 
	 * 
	 * @return nest field type
	 */
	public TypeSchema[] getFieldsTypes();
	
}