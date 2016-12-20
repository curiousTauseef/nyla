package nyla.solutions.core.patterns.creational;

/**
 * Director from builder design pattern
 * @author Gregory Green
 *
 * @param <T>
 */
public interface BuilderDirector<T>
{
	/**
	 * 
	 * @param builder
	 */
	void construct(T builder);
}
