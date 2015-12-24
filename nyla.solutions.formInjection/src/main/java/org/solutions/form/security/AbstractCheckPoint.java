package org.solutions.form.security;

import nyla.solutions.global.operations.logging.Log;
import nyla.solutions.global.util.Debugger;

import org.apache.log4j.Category;





/**
 * <pre>
 * AbstractCheckPoint provides a set of functions to
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public abstract class AbstractCheckPoint implements CheckPoint
{

   /**
    * Constructor for AbstractCheckPoint initializes internal 
    * data settings.
    * 
    */
   public AbstractCheckPoint()
   {
      super();
   }//--------------------------------------------
   protected Log logger = Debugger.getLog(getClass());

}
