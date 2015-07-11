package nyla.solutions.global.patterns.conversion;

public interface ArrayConversion<ConvertType,InputType>
{
	/**
	 * 
	 * @param input the input array
	 * @return the covnert output
	 */
	 ConvertType convert(InputType[] input); 	
}
