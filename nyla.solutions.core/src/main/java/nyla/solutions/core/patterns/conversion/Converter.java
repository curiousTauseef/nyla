package nyla.solutions.core.patterns.conversion;

public interface Converter<SourceType, TargetType>
{
	/**
	 * 
	 * @param sourceType
	 * @return the target type
	 */
	public TargetType convert(SourceType sourceObject);
	
}
