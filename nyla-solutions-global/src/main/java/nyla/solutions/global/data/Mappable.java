package nyla.solutions.global.data;



import java.io.Serializable;

import nyla.solutions.global.data.Key;



/**

 * <pre>

 * Mappable object that has a name and a value

 * </pre> 

 * @author Gregory Green

 * @version 1.0

 */

public interface Mappable<K,V> extends Serializable, Key<K>
{
  
   /**
    * 
    * @return the value
    */
   public V getValue();   

}

