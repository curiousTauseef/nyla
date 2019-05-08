package nyla.solutions.core.patterns;

/**
  <b>Disposable</b> is  public interface interface
  for objects to free held resource when requested
*/
public interface Disposable extends AutoCloseable
{
	/**
	 * Default close method call close
	 */
	default @Override void close() throws Exception
	{
		dispose();
		
	}
	/**
	 * Dispose of needed resources
	 */
   void dispose();
}
