package nyla.solutions.global.patterns.expression;

public interface BooleanExpression<T>
{
	public boolean getBoolean(T condition);
}
