package nyla.solutions.core.patterns.memento;

import nyla.solutions.core.data.MethodCallFact;

public interface MethodCallObjectPreparer
{
   public Object prepare(Object target, MethodCallFact methodCallFact, String savePoint);
}
