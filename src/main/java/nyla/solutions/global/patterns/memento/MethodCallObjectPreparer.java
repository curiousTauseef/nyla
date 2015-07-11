package nyla.solutions.global.patterns.memento;

import nyla.solutions.global.data.MethodCallFact;

public interface MethodCallObjectPreparer
{
   public Object prepare(Object target, MethodCallFact methodCallFact, String savePoint);
}
