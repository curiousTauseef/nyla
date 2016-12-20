package nyla.solutions.core.patterns.conversion;

public interface ArrayConversion<ConvertType,InputType>
{
	/**
	 * 
	 * @param input the input array
	 * @return the covnert output
	 */
	 ConvertType convert(InputType[] input); 	
}
