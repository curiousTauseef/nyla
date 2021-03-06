package nyla.solutions.core.patterns.conversion;

/**
 * 
 * @param <SourceType> the source type
 * @param <TargetType> the target type
 * @author Gregory Green
 */
public class FixedNamedConverter<SourceType, TargetType> implements NameableConverter<SourceType, TargetType>
{
	
	@Override
	public TargetType convert(Object sourceObject)
	{
		return target;
	}// --------------------------------------------------------
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	
	/**
	 * @return the target
	 */
	public TargetType getTarget()
	{
		return target;
	}
	/**
	 * @param target the target to set
	 */
	public void setTarget(TargetType target)
	{
		this.target = target;
	}


	private String name;
	private TargetType target;
}
