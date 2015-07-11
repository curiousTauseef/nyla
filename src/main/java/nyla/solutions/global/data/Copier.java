package nyla.solutions.global.data;

import nyla.solutions.global.data.Copier;




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
    * @param aCopier the object to copy from
    */
   public void copy(Copier aFrom);

}
