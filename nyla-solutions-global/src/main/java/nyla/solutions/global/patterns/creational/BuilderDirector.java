package nyla.solutions.global.patterns.creational;

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
