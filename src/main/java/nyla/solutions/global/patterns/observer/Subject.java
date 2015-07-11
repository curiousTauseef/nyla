package nyla.solutions.global.patterns.observer;


import nyla.solutions.global.data.Nameable;

/**
 * 
 * <b>Subject</b> Send notify signal to observer object whenever data changes
 * @author Gregory Green
 *
 */
public interface Subject extends Nameable
{
   /**
    * 
    * @param obsever the observer to add
    */
   public void add(SubjectObserver obsever);
     
   /**
    * 
    * @param observer the observer to 
    */
   public void remove(SubjectObserver observer);
      
   /**
    * 
    * @param object notify all observers
    */
   void notify(Object object);
}
