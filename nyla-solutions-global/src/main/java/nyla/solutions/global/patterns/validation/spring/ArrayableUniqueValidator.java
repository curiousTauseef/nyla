package nyla.solutions.global.patterns.validation.spring;

import java.util.HashSet;
import java.util.Set;

import nyla.solutions.global.data.Arrayable;
import nyla.solutions.global.exception.DuplicateRowException;

import org.springframework.validation.Errors;

/**
 * @author Gregory Green
 *
 */
public class ArrayableUniqueValidator extends AbstractValidation
{


	/**
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void validate(Object obj, Errors errors)
	{
		if(set == null)
			set = new HashSet<Object>();
		
		Arrayable<Object> arrayable = (Arrayable<Object>)obj;
		
		Object key = arrayable.toArray()[keyPosition];
		
		if(set.contains(key))
			throw new DuplicateRowException(String.valueOf(key));

		set.add(key);
		
	}// --------------------------------------------------------
	
	private Set<Object> set = null;
	private int keyPosition = -1;

}
