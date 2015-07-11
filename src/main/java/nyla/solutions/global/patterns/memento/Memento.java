package nyla.solutions.global.patterns.memento;

/**
 * 
 * Interface to save and restore object information
 * @author Gregory Green
 *
 */
public interface Memento
{
   /**
    * Save the object information
    * @param savePoint the object reference ID
    * @param object the object to save
    */
   void store(String savePoint, Object object);
   
   /**
    * Retrieve the object information
    * @param savePoint the reference id
    * @param objClass the class of the object
    * @return
    */
   Object restore(String savePoint, Class<?> objClass);
   
   
}
