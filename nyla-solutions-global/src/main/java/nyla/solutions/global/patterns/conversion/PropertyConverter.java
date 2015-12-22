package nyla.solutions.global.patterns.conversion;

public interface PropertyConverter<SourceType, TargetType>
{
	/**
	 * 
	 * @param sourceType
	 * @return the target type
	 */
	public TargetType convert(Object sourceObject, Class<?> aClass);
	
}
