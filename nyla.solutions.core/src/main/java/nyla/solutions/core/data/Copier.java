package nyla.solutions.core.data;

import nyla.solutions.core.data.Copier;




/**
 * <pre>
 * Copier provides a function object data
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public interface Copier
{
   /**
    * Abstract method to copy data from a given object
    * @param aFrom the object to copy from
    */
   public void copy(Copier aFrom);

}
