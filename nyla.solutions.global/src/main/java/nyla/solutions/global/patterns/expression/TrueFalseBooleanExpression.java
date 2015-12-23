package nyla.solutions.global.patterns.expression;


/**
 * @author Gregory Green
 *
 */
public class TrueFalseBooleanExpression<T> implements BooleanExpression<T>
{
	/**
	 * 
	 */
	public Boolean execute(T obj)
	{		
		return value;
	}//---------------------------------------------
	/**
	 * @param value the value to set
	 */
	public void setBoolean(boolean value)
	{
		this.value = value;
	}//---------------------------------------------
	
	private boolean value = false;
}
