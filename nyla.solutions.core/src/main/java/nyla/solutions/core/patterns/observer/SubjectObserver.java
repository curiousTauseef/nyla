package nyla.solutions.core.patterns.observer;

import java.util.Observable;
import java.util.Observer;

import nyla.solutions.core.data.Identifier;

/**
 * 
 * <b>SubjectObserver</b> Provides an update interface to receive signal from subject 
 * @author Gregory Green
 * @param <T> the update data type
 *
 */
public interface SubjectObserver<T> extends Identifier, Observer
{
   /**
    * 
    * @param subjectName the subject name
 * @param data the data
    */
   public void update(String subjectName, T data);
   
   /**
    * 
    * @return default id is the class name
    */
   @Override
	default String getId()
	{
		return this.getClass().getName();
	}
   
   @SuppressWarnings("unchecked")
   @Override
	default void update(Observable o, Object arg)
	{
	   update(String.valueOf(o),(T)arg);
	}

}
