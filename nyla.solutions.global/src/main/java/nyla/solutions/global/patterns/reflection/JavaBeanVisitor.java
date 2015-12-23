package nyla.solutions.global.patterns.reflection;

public interface JavaBeanVisitor
{
	/**
	 * 
	 * @param className
	 */
	public void visitClass(Class<?> aClass, Object object);
	
	
	/**
	 * 
	 * @param name the property
	 * @param value the property value
	 * @param object the parent object
	 */
	public void visitProperty(String name, Object value, Object object);

}
