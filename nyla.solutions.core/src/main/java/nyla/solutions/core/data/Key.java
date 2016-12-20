package nyla.solutions.core.data;

import java.io.Serializable;

/**
 * <pre>
 * Key provides a set of functions to
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public interface Key<T> extends Serializable
{

   /**
    * 
    * @return the object's key
    */
   public T getKey();
   
   /**
    * @param key The key to set.
    */
   //public void setKey(T key);
   
}
