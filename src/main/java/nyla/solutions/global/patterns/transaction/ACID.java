package nyla.solutions.global.patterns.transaction;
import nyla.solutions.global.patterns.Disposable;

public interface ACID extends Disposable
{

    public abstract void commit();

    public abstract void rollback();
}