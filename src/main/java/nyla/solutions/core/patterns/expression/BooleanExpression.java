package nyla.solutions.core.patterns.expression;

import java.util.function.Function;

public interface BooleanExpression<T> extends Function<T, Boolean>
{
}
