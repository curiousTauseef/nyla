package nyla.solutions.global.data;

import nyla.solutions.global.data.Createable;
import nyla.solutions.global.data.Updateable;



/**

 * <pre>

 * Auditable provides a set of methods to provide audit tracking

 * </pre> 

 * @author Gregory Green

 * @version 1.0

 */

public interface Auditable extends Updateable, Createable
{
   /**
    * Retrieve the deleted code
    * @return the deleted code
    */
   public String getDeletedCode();
   
   /**
    * 
    * @param aDeletedCode the deleted code
    */
   public void setDeletedCode(String aDeletedCode);
   
   
   /**
    * 
    * @return true if the object is deleleted
    */
   public boolean isDeleted();
   
   /**
    * Delete the auditable object
    * @return
    */
   public void delete();
}
