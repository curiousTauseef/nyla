package nyla.solutions.global.patterns.observer;

import nyla.solutions.global.data.Identifier;

/**
 * 
 * <b>SubjectObserver</b> Provides an update interface to receive signal from subject 
 * @author Gregory Green
 *
 */
public interface SubjectObserver extends Identifier
{
   /**
    * 
    * @param subject the subject where the update is generated
    * @param data the data
    */
   public void update(Subject subject, Object data);

}
